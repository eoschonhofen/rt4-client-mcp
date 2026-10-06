package rt4.mcp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class McpHttpServerTest {
	private static final String TOKEN = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
	/** MCP-25 — the blocking tool parks every caller here until the server is stopped. */
	private static final CountDownLatch BLOCKED = new CountDownLatch(1);
	private static final AtomicInteger BLOCKING = new AtomicInteger();

	private McpHttpServer server;
	private int port;

	@BeforeEach
	void startServer() throws Exception {
		ToolRegistry registry = new ToolRegistry().register(new Tool() {
			@Override
			public String name() {
				return "echo";
			}

			@Override
			public String description() {
				return "echoes";
			}

			@Override
			public JsonObject inputSchema() {
				JsonObject schema = new JsonObject();
				schema.addProperty("type", "object");
				return schema;
			}

			@Override
			public ToolResult call(JsonObject args) {
				return ToolResult.json(args);
			}
		});

		registry.register(new Tool() {
			@Override
			public String name() {
				return "block";
			}

			@Override
			public String description() {
				return "parks the calling request until the test ends";
			}

			@Override
			public JsonObject inputSchema() {
				JsonObject schema = new JsonObject();
				schema.addProperty("type", "object");
				return schema;
			}

			@Override
			public ToolResult call(JsonObject args) throws ToolException {
				BLOCKING.incrementAndGet();
				try {
					BLOCKED.await(30L, TimeUnit.SECONDS);
				} catch (InterruptedException stopped) {
					Thread.currentThread().interrupt();
					throw new ToolException("interrupted");
				}
				return ToolResult.json(args);
			}
		});
		BLOCKING.set(0);

		server = McpHttpServer.start(new McpConfig(true, 0, TOKEN, null), registry);
		port = server.port();
	}

	@AfterEach
	void stopServer() {
		if (server != null) {
			server.stop();
		}
	}

	private static final class Resp {
		final int status;
		final String body;
		final Map<String, String> headers;

		Resp(int status, String body, Map<String, String> headers) {
			this.status = status;
			this.body = body;
			this.headers = headers;
		}

		String header(String name) {
			return headers.get(name.toLowerCase());
		}

		JsonObject json() {
			return JsonParser.parseString(body).getAsJsonObject();
		}
	}

	/**
	 * A raw HTTP/1.1 request. {@link java.net.HttpURLConnection} treats {@code Origin} as a
	 * restricted header and drops it, and we also want to control {@code Host}.
	 */
	private Resp send(String method, String body, String session, String bearer, String origin, String host)
			throws IOException {
		try (Socket socket = new Socket(InetAddress.getLoopbackAddress(), port)) {
			socket.setSoTimeout(5000);

			StringBuilder head = new StringBuilder();
			head.append(method).append(" /mcp HTTP/1.1\r\n");
			head.append("Host: ").append(host != null ? host : "127.0.0.1:" + port).append("\r\n");
			if (bearer != null) {
				head.append("Authorization: ").append(bearer).append("\r\n");
			}
			if (session != null) {
				head.append("Mcp-Session-Id: ").append(session).append("\r\n");
			}
			if (origin != null) {
				head.append("Origin: ").append(origin).append("\r\n");
			}
			byte[] bodyBytes = body == null ? new byte[0] : body.getBytes(StandardCharsets.UTF_8);
			if (body != null) {
				head.append("Content-Type: application/json\r\n");
			}
			head.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
			head.append("Connection: close\r\n\r\n");

			OutputStream out = socket.getOutputStream();
			out.write(head.toString().getBytes(StandardCharsets.UTF_8));
			if (bodyBytes.length > 0) {
				out.write(bodyBytes);
			}
			out.flush();

			InputStream in = socket.getInputStream();
			ByteArrayOutputStream raw = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int read;
			while ((read = in.read(buffer)) != -1) {
				raw.write(buffer, 0, read);
			}

			String response = new String(raw.toByteArray(), StandardCharsets.ISO_8859_1);
			int headerEnd = response.indexOf("\r\n\r\n");
			if (headerEnd < 0) {
				throw new IOException("no HTTP header terminator in response: " + response);
			}

			String[] lines = response.substring(0, headerEnd).split("\r\n");
			int status = Integer.parseInt(lines[0].split(" ")[1]);
			Map<String, String> headers = new LinkedHashMap<String, String>();
			for (int i = 1; i < lines.length; i++) {
				int colon = lines[i].indexOf(':');
				if (colon > 0) {
					headers.put(lines[i].substring(0, colon).trim().toLowerCase(),
							lines[i].substring(colon + 1).trim());
				}
			}
			return new Resp(status, response.substring(headerEnd + 4), headers);
		}
	}

	private Resp post(String body, String session) throws IOException {
		return send("POST", body, session, "Bearer " + TOKEN, null, null);
	}

	private Resp initialize() throws IOException {
		return post("{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\","
				+ "\"params\":{\"protocolVersion\":\"2025-06-18\",\"capabilities\":{},"
				+ "\"clientInfo\":{\"name\":\"test\",\"version\":\"1\"}}}", null);
	}

	@Test
	void fullHandshake() throws IOException {
		Resp init = initialize();
		assertEquals(200, init.status);
		assertNotNull(init.header("mcp-session-id"));
		assertEquals("2025-06-18", init.json().getAsJsonObject("result").get("protocolVersion").getAsString());

		Resp initialized = post("{\"jsonrpc\":\"2.0\",\"method\":\"notifications/initialized\"}",
				init.header("mcp-session-id"));
		assertEquals(202, initialized.status);
		assertEquals("", initialized.body);

		Resp list = post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\"}", init.header("mcp-session-id"));
		assertEquals(200, list.status);
		assertEquals("echo", list.json().getAsJsonObject("result").getAsJsonArray("tools")
				.get(0).getAsJsonObject().get("name").getAsString());
	}

	@Test
	void toolsCallOverHttp() throws IOException {
		Resp init = initialize();
		Resp call = post("{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"tools/call\","
				+ "\"params\":{\"name\":\"echo\",\"arguments\":{\"a\":1}}}", init.header("mcp-session-id"));

		assertEquals(200, call.status);
		assertEquals("{\"a\":1}", call.json().getAsJsonObject("result")
				.getAsJsonArray("content").get(0).getAsJsonObject().get("text").getAsString());
	}

	@Test
	void missingTokenIsUnauthorized() throws IOException {
		Resp response = send("POST", "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\"}",
				null, null, null, null);

		assertEquals(401, response.status);
		assertEquals("Bearer", response.header("www-authenticate"));
	}

	@Test
	void wrongTokenIsUnauthorized() throws IOException {
		Resp response = send("POST", "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\"}",
				null, "Bearer nope", null, null);
		assertEquals(401, response.status);
	}

	@Test
	void crossOriginIsForbidden() throws IOException {
		Resp response = send("POST", "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\"}",
				null, "Bearer " + TOKEN, "http://evil.com", null);

		assertEquals(403, response.status);
	}

	@Test
	void loopbackOriginIsAllowed() throws IOException {
		Resp response = send("POST", "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\"}",
				null, "Bearer " + TOKEN, "http://localhost:3000", null);

		assertEquals(200, response.status);
	}

	@Test
	void badHostIsForbidden() throws IOException {
		Resp response = send("POST", "{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\"}",
				null, "Bearer " + TOKEN, null, "evil.com:" + port);

		assertEquals(403, response.status);
	}

	@Test
	void getIsMethodNotAllowed() throws IOException {
		Resp response = send("GET", null, null, "Bearer " + TOKEN, null, null);
		assertEquals(405, response.status);
	}

	@Test
	void requestWithoutSessionIsBadRequest() throws IOException {
		Resp response = post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\"}", null);

		assertEquals(400, response.status);
		assertEquals(JsonRpc.INVALID_REQUEST, response.json().getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void unknownSessionIsNotFound() throws IOException {
		Resp response = post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\"}", "not-a-session");

		assertEquals(404, response.status);
	}

	@Test
	void deleteTerminatesTheSession() throws IOException {
		Resp init = initialize();
		String session = init.header("mcp-session-id");

		Resp deleted = send("DELETE", null, session, "Bearer " + TOKEN, null, null);
		assertEquals(200, deleted.status);

		Resp after = post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\"}", session);
		assertEquals(404, after.status);
	}

	@Test
	void malformedJsonIsBadRequestWithParseError() throws IOException {
		Resp response = post("{not json", null);

		assertEquals(400, response.status);
		assertEquals(JsonRpc.PARSE_ERROR, response.json().getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void batchIsBadRequest() throws IOException {
		Resp response = post("[{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"ping\"}]", null);

		assertEquals(400, response.status);
		assertEquals(JsonRpc.INVALID_REQUEST, response.json().getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void deleteWithoutSessionIsBadRequest() throws IOException {
		Resp response = send("DELETE", null, null, "Bearer " + TOKEN, null, null);
		assertEquals(400, response.status);
	}

	@Test
	void pingWorksAfterInitialize() throws IOException {
		Resp init = initialize();
		Resp ping = post("{\"jsonrpc\":\"2.0\",\"id\":9,\"method\":\"ping\"}", init.header("mcp-session-id"));

		assertEquals(200, ping.status);
		assertTrue(ping.json().getAsJsonObject("result").entrySet().isEmpty());
	}

	@Test
	void sessionHeaderIsAbsentOnInitializeError() throws IOException {
		Resp response = send("POST", "{oops", null, "Bearer " + TOKEN, null, null);
		assertNull(response.header("mcp-session-id"));
	}

	// ------------------------------------------------------------------ MCP-25

	@Test
	void aBlockedCallDoesNotStarvePing() throws Exception {
		Resp init = initialize();
		String session = init.header("mcp-session-id");
		// Warm the HTTP path up, so the measured ping is not the first request.
		assertEquals(200, post("{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"ping\"}", session).status);

		int pending = 5;
		ExecutorService callers = Executors.newFixedThreadPool(pending);
		try {
			for (int i = 0; i < pending; i++) {
				callers.submit(() -> {
					post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/call\","
							+ "\"params\":{\"name\":\"block\",\"arguments\":{}}}", session);
					return null;
				});
			}

			long startDeadline = System.currentTimeMillis() + 5000L;
			while (BLOCKING.get() < pending && System.currentTimeMillis() < startDeadline) {
				Thread.sleep(10L);
			}
			assertEquals(pending, BLOCKING.get(), "every blocking call should be inside its tool");

			long start = System.nanoTime();
			Resp ping = post("{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"ping\"}", session);
			long millis = (System.nanoTime() - start) / 1_000_000L;

			assertEquals(200, ping.status);
			assertTrue(millis < 100L, "ping took " + millis + "ms while " + pending + " calls were blocked");
		} finally {
			callers.shutdownNow();
		}
	}

	@Test
	void idleSessionsAreEvictedAfterTheTtl() throws IOException {
		long[] now = {0L};
		server.setClock(() -> now[0]);

		Resp init = initialize();
		String session = init.header("mcp-session-id");
		assertEquals(1, server.sessionCount());

		now[0] = McpHttpServer.SESSION_TTL_NANOS + 1L;
		Resp after = post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\"}", session);

		assertEquals(404, after.status, "an idle session must be evicted after the TTL");
		assertEquals(0, server.sessionCount());
	}

	@Test
	void anActiveSessionSurvivesPastTheTtl() throws IOException {
		long[] now = {0L};
		server.setClock(() -> now[0]);
		String session = initialize().header("mcp-session-id");

		now[0] += TimeUnit.MINUTES.toNanos(20);
		assertEquals(200, post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"ping\"}", session).status);

		now[0] += TimeUnit.MINUTES.toNanos(20);
		assertEquals(200, post("{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"tools/list\"}", session).status,
				"a request must refresh the session's last-seen time");
	}

	@Test
	void theSessionTableIsCapped() throws IOException {
		for (int i = 0; i < McpHttpServer.MAX_SESSIONS + 3; i++) {
			assertEquals(200, initialize().status);
		}

		assertEquals(McpHttpServer.MAX_SESSIONS, server.sessionCount());
	}

	/** Starts {@code count} blocking tool calls and waits until each is parked inside its tool. */
	private ExecutorService park(int count, String session) throws Exception {
		ExecutorService callers = Executors.newFixedThreadPool(count);
		for (int i = 0; i < count; i++) {
			callers.submit(() -> {
				post("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/call\","
						+ "\"params\":{\"name\":\"block\",\"arguments\":{}}}", session);
				return null;
			});
		}
		long deadline = System.currentTimeMillis() + 5000L;
		while (BLOCKING.get() < count && System.currentTimeMillis() < deadline) {
			Thread.sleep(10L);
		}
		assertEquals(count, BLOCKING.get(), "every blocking call should be inside its tool");
		return callers;
	}

	@Test
	void aFullCapRejectsToolCallsButNotControlTraffic() throws Exception {
		server.setToolCallLimit(2);
		String session = initialize().header("mcp-session-id");

		ExecutorService callers = park(2, session);
		try {
			Resp third = post("{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"tools/call\","
					+ "\"params\":{\"name\":\"echo\",\"arguments\":{}}}", session);
			assertEquals(503, third.status, "a tool call past the cap is rejected");

			assertEquals(200, post("{\"jsonrpc\":\"2.0\",\"id\":4,\"method\":\"ping\"}", session).status);
			assertEquals(200, post("{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"tools/list\"}", session).status);
			Resp cancel = post("{\"jsonrpc\":\"2.0\",\"id\":6,\"method\":\"tools/call\","
					+ "\"params\":{\"name\":\"nav_cancel\",\"arguments\":{}}}", session);
			assertEquals(200, cancel.status, "nav_cancel never waits for a slot");
		} finally {
			callers.shutdownNow();
		}
	}

	@Test
	void authIsCheckedBeforeTheCap() throws Exception {
		server.setToolCallLimit(1);
		String session = initialize().header("mcp-session-id");

		ExecutorService callers = park(1, session);
		try {
			Resp anonymous = send("POST", "{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"tools/call\","
					+ "\"params\":{\"name\":\"echo\",\"arguments\":{}}}", session, null, null, null);
			assertEquals(401, anonymous.status, "an unauthenticated request is refused, not counted");
		} finally {
			callers.shutdownNow();
		}
	}

	@Test
	void concurrentInitializesNeverOvershootTheSessionCap() throws Exception {
		int attempts = McpHttpServer.MAX_SESSIONS * 2;
		ExecutorService callers = Executors.newFixedThreadPool(16);
		try {
			List<Future<Resp>> results = new ArrayList<Future<Resp>>();
			for (int i = 0; i < attempts; i++) {
				results.add(callers.submit(this::initialize));
			}
			for (Future<Resp> result : results) {
				assertEquals(200, result.get(10L, TimeUnit.SECONDS).status);
			}
		} finally {
			callers.shutdownNow();
		}

		assertEquals(McpHttpServer.MAX_SESSIONS, server.sessionCount());
	}
}
