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
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * MCP-03 — the Streamable HTTP endpoint. One context, {@code /mcp}, bound to loopback.
 *
 * <p>Only plain JSON responses are used: the spec permits them and this server never sends
 * server-to-client requests, so there is no SSE stream.</p>
 */
public final class McpHttpServer {
	public static final String CONTEXT = "/mcp";
	public static final String SESSION_HEADER = "Mcp-Session-Id";

	private final HttpServer server;
	private final McpProtocol protocol;
	private final McpConfig config;
	private final ExecutorService executor;
	private final ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<String, Session>();
	private final int port;

	private static final class Session {
		final String id;

		Session(String id) {
			this.id = id;
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
		ExecutorService executor = Executors.newFixedThreadPool(4, daemonFactory());
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
		} catch (Throwable error) {
			System.err.println("[MCP] request failed: " + error);
			safeError(exchange);
		} finally {
			exchange.close();
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

		if (initialize) {
			sessionId = UUID.randomUUID().toString();
			sessions.put(sessionId, new Session(sessionId));
		} else if (sessionId == null || sessionId.isEmpty()) {
			sendJson(exchange, 400, JsonRpc.error(message.id, JsonRpc.INVALID_REQUEST,
					"missing " + SESSION_HEADER + "; send initialize first"));
			return;
		} else if (!sessions.containsKey(sessionId)) {
			sendJson(exchange, 404, JsonRpc.error(message.id, JsonRpc.INVALID_REQUEST,
					"unknown " + SESSION_HEADER + "; re-initialize"));
			return;
		}

		JsonObject response = protocol.handle(message);
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
