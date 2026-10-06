package rt4.mcp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;

/**
 * MCP-03 — the Streamable HTTP endpoint. One context, {@code /mcp}, bound to loopback.
 *
 * <p>Only plain JSON responses are used: the spec permits them and this server never sends
 * server-to-client requests, so there is no SSE stream.</p>
 *
 * <p>MCP-25 — a cached daemon pool serves every request on its own thread, and a semaphore caps
 * how many tool calls run at once. Only authenticated {@code tools/call} requests take a slot,
 * and the cheap control tools in {@link #UNCAPPED_TOOLS} never do, so {@code ping},
 * {@code tools/list} and {@code nav_cancel} answer even when every slot is held by a long
 * {@code wait_for}. Sessions expire after {@link #SESSION_TTL_NANOS} of idleness and the table
 * is capped at {@link #MAX_SESSIONS}.</p>
 */
public final class McpHttpServer {
	public static final String CONTEXT = "/mcp";
	public static final String SESSION_HEADER = "Mcp-Session-Id";

	/** How long a session may stay idle before the next request evicts it. */
	static final long SESSION_TTL_NANOS = TimeUnit.MINUTES.toNanos(30);
	/** The most sessions kept at once; the oldest is evicted to make room. */
	static final int MAX_SESSIONS = 64;
	/** The most tool calls served at once, so a storm cannot spawn unbounded work. */
	static final int MAX_CONCURRENT_REQUESTS = 32;
	/** Short control tools that must stay usable while the cap is full. */
	static final Set<String> UNCAPPED_TOOLS = Collections.unmodifiableSet(new HashSet<String>(
			Arrays.asList("nav_cancel", "nav_status", "get_status")));

	private final HttpServer server;
	private final McpProtocol protocol;
	private final McpConfig config;
	private final ExecutorService executor;
	private final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<String, Session>();
	/** Guards the evict-then-insert on initialize, so concurrent inits cannot overshoot the cap. */
	private final Object sessionLock = new Object();
	private volatile Semaphore slots = new Semaphore(MAX_CONCURRENT_REQUESTS);
	private final int port;
	/** The clock the session TTL is measured against; a test seam. */
	private volatile LongSupplier clock = new LongSupplier() {
		@Override
		public long getAsLong() {
			return System.nanoTime();
		}
	};

	private static final class Session {
		final String id;
		volatile long lastSeenNanos;

		Session(String id, long lastSeenNanos) {
			this.id = id;
			this.lastSeenNanos = lastSeenNanos;
		}
	}

	private McpHttpServer(HttpServer server, McpConfig config, ToolRegistry tools, ExecutorService executor) {
		this.server = server;
		this.config = config;
		this.executor = executor;
		this.protocol = new McpProtocol(tools);
		this.port = server.getAddress().getPort();
	}

	/** Binds and starts the listener. Throws if the port is taken. */
	public static McpHttpServer start(McpConfig config, ToolRegistry tools) throws IOException {
		InetSocketAddress address = new InetSocketAddress(InetAddress.getLoopbackAddress(), config.port);
		HttpServer server = HttpServer.create(address, 0);
		// MCP-25 — a cached pool gives every request a thread; the semaphore in handle() is what
		// bounds concurrent work, so a blocked wait_for cannot starve ping.
		ExecutorService executor = Executors.newCachedThreadPool(daemonFactory());
		server.setExecutor(executor);
		McpHttpServer wrapper = new McpHttpServer(server, config, tools, executor);
		server.createContext(CONTEXT, wrapper::handle);
		server.start();
		return wrapper;
	}

	private static ThreadFactory daemonFactory() {
		return new ThreadFactory() {
			@Override
			public Thread newThread(Runnable runnable) {
				Thread thread = new Thread(runnable, "mcp-http");
				thread.setDaemon(true);
				return thread;
			}
		};
	}

	/** The port actually bound, which matters when the config asked for port 0. */
	public int port() {
		return port;
	}

	public String url() {
		return "http://" + McpConfig.BIND_ADDRESS + ":" + port + CONTEXT;
	}

	public void stop() {
		try {
			server.stop(0);
		} finally {
			executor.shutdownNow();
		}
	}

	public String token() {
		return config.token;
	}

	private void handle(HttpExchange exchange) throws IOException {
		try {
			handleRequest(exchange);
		} catch (Throwable error) {
			System.err.println("[MCP] request failed: " + error);
			safeError(exchange);
		} finally {
			exchange.close();
		}
	}

	private void handleRequest(HttpExchange exchange) throws IOException {
		if (!Auth.bearerMatches(exchange.getRequestHeaders().getFirst("Authorization"), config.token)) {
			sendText(exchange, 401, "missing or invalid bearer token", "WWW-Authenticate", "Bearer");
			return;
		}
		if (!Auth.originAllowed(exchange.getRequestHeaders().getFirst("Origin"))) {
			sendText(exchange, 403, "forbidden origin");
			return;
		}
		if (!Auth.hostAllowed(exchange.getRequestHeaders().getFirst("Host"), port)) {
			sendText(exchange, 403, "forbidden host");
			return;
		}

		String method = exchange.getRequestMethod();
		if ("POST".equals(method)) {
			handlePost(exchange);
		} else if ("DELETE".equals(method)) {
			handleDelete(exchange);
		} else {
			sendText(exchange, 405, "method not allowed; use POST", "Allow", "POST, DELETE");
		}
	}

	private void handlePost(HttpExchange exchange) throws IOException {
		JsonRpc.Message message;
		try {
			message = JsonRpc.parse(readBody(exchange.getRequestBody()));
		} catch (JsonRpc.RpcException malformed) {
			sendJson(exchange, 400, JsonRpc.error(null, malformed.code, malformed.getMessage()));
			return;
		}

		boolean initialize = message.isRequest() && McpProtocol.createsSession(message.method);
		String sessionId = exchange.getRequestHeaders().getFirst(SESSION_HEADER);

		long now = clock.getAsLong();
		evictIdleSessions(now);

		if (initialize) {
			sessionId = UUID.randomUUID().toString();
			synchronized (sessionLock) {
				evictOldestSessionIfFull();
				sessions.put(sessionId, new Session(sessionId, now));
			}
		} else if (sessionId == null || sessionId.isEmpty()) {
			sendJson(exchange, 400, JsonRpc.error(message.id, JsonRpc.INVALID_REQUEST,
					"missing " + SESSION_HEADER + "; send initialize first"));
			return;
		} else {
			Session session = sessions.get(sessionId);
			if (session == null) {
				sendJson(exchange, 404, JsonRpc.error(message.id, JsonRpc.INVALID_REQUEST,
						"unknown " + SESSION_HEADER + "; re-initialize"));
				return;
			}
			session.lastSeenNanos = now;
		}

		JsonObject response;
		if (takesSlot(message)) {
			Semaphore held = slots;
			if (!held.tryAcquire()) {
				sendText(exchange, 503, "server busy; " + MAX_CONCURRENT_REQUESTS
						+ " tool calls are already in flight");
				return;
			}
			try {
				response = protocol.handle(message);
			} finally {
				held.release();
			}
		} else {
			response = protocol.handle(message);
		}
		if (response == null) {
			// Notification or response: accepted, no body.
			if (initialize) {
				sendEmpty(exchange, 202, SESSION_HEADER, sessionId);
			} else {
				sendEmpty(exchange, 202);
			}
			return;
		}

		if (initialize) {
			sendJson(exchange, 200, response, SESSION_HEADER, sessionId);
		} else {
			sendJson(exchange, 200, response);
		}
	}

	private void handleDelete(HttpExchange exchange) throws IOException {
		String sessionId = exchange.getRequestHeaders().getFirst(SESSION_HEADER);
		if (sessionId == null || sessionId.isEmpty()) {
			sendJson(exchange, 400, JsonRpc.error(null, JsonRpc.INVALID_REQUEST, "missing " + SESSION_HEADER));
			return;
		}
		if (sessions.remove(sessionId) == null) {
			sendJson(exchange, 404, JsonRpc.error(null, JsonRpc.INVALID_REQUEST, "unknown " + SESSION_HEADER));
			return;
		}
		sendEmpty(exchange, 200);
	}

	/**
	 * MCP-25 — whether a message counts against the concurrency cap: every {@code tools/call}
	 * except the control tools. Lifecycle, {@code ping} and {@code tools/list} are cheap.
	 */
	static boolean takesSlot(JsonRpc.Message message) {
		if (!"tools/call".equals(message.method)) {
			return false;
		}
		JsonObject params = message.params;
		if (params == null || !params.has("name") || !params.get("name").isJsonPrimitive()) {
			return true;
		}
		return !UNCAPPED_TOOLS.contains(params.get("name").getAsString());
	}

	/** MCP-25 — drop every session untouched for longer than the TTL. Called on each request. */
	private void evictIdleSessions(long now) {
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			if (now - entry.getValue().lastSeenNanos > SESSION_TTL_NANOS) {
				sessions.remove(entry.getKey(), entry.getValue());
			}
		}
	}

	/** MCP-25 — make room for a new session by dropping the least recently used one. Hold {@link #sessionLock}. */
	private void evictOldestSessionIfFull() {
		while (sessions.size() >= MAX_SESSIONS) {
			String oldestId = null;
			long oldestSeen = Long.MAX_VALUE;
			for (Map.Entry<String, Session> entry : sessions.entrySet()) {
				if (entry.getValue().lastSeenNanos <= oldestSeen) {
					oldestSeen = entry.getValue().lastSeenNanos;
					oldestId = entry.getKey();
				}
			}
			if (oldestId == null) {
				return;
			}
			sessions.remove(oldestId);
		}
	}

	/** Test seam: the clock the session TTL is measured against. */
	void setClock(LongSupplier clock) {
		this.clock = clock;
	}

	/** Test seam: a smaller tool-call cap, so the 503 path is reachable without 32 threads. */
	void setToolCallLimit(int limit) {
		this.slots = new Semaphore(limit);
	}

	/** Test seam: the number of live sessions. */
	int sessionCount() {
		return sessions.size();
	}

	private static String readBody(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		return new String(out.toByteArray(), StandardCharsets.UTF_8);
	}

	private void safeError(HttpExchange exchange) {
		try {
			sendJson(exchange, 500, JsonRpc.error(null, JsonRpc.INTERNAL_ERROR, "internal error"));
		} catch (Throwable ignored) {
			// The response may already be partially written; closing is all that is left.
		}
	}

	private void sendJson(HttpExchange exchange, int status, JsonElement body, String... headers) throws IOException {
		byte[] bytes = JsonRpc.toJson(body).getBytes(StandardCharsets.UTF_8);
		exchange.getResponseHeaders().set("Content-Type", "application/json");
		applyHeaders(exchange, headers);
		exchange.sendResponseHeaders(status, bytes.length);
		try (OutputStream out = exchange.getResponseBody()) {
			out.write(bytes);
		}
	}

	private void sendText(HttpExchange exchange, int status, String text, String... headers) throws IOException {
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
		exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
		applyHeaders(exchange, headers);
		exchange.sendResponseHeaders(status, bytes.length);
		try (OutputStream out = exchange.getResponseBody()) {
			out.write(bytes);
		}
	}

	private void sendEmpty(HttpExchange exchange, int status, String... headers) throws IOException {
		applyHeaders(exchange, headers);
		exchange.sendResponseHeaders(status, -1);
	}

	private static void applyHeaders(HttpExchange exchange, String... headers) {
		for (int i = 0; i + 1 < headers.length; i += 2) {
			exchange.getResponseHeaders().set(headers[i], headers[i + 1]);
		}
	}
}
