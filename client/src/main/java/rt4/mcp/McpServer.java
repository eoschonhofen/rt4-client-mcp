package rt4.mcp;

import java.io.IOException;
import java.net.BindException;

/**
 * MCP-03 — builds the tool registry and starts the transport, without letting any MCP
 * failure stop the game from launching.
 */
public final class McpServer {
	private static volatile McpHttpServer running;

	private McpServer() {
	}

	/**
	 * Starts the server when the config enables it. Returns the server, or null when it
	 * could not start (already logged). Never throws.
	 */
	public static synchronized McpHttpServer start(McpConfig config) {
		if (!config.enabled) {
			return null;
		}
		if (running != null) {
			return running;
		}

		ToolRegistry registry = new ToolRegistry();
		registerTools(registry);

		try {
			McpHttpServer server = McpHttpServer.start(config, registry);
			running = server;
			System.err.println("[MCP] listening on " + server.url());
			System.err.println("[MCP] claude mcp add --transport http rt4 " + server.url()
					+ " --header \"Authorization: Bearer " + config.token + "\"");
			return server;
		} catch (BindException inUse) {
			System.err.println("[MCP] port " + config.port + " in use, MCP disabled");
			return null;
		} catch (IOException other) {
			System.err.println("[MCP] failed to bind port " + config.port + ": " + other);
			return null;
		} catch (Throwable error) {
			System.err.println("[MCP] failed to start: " + error);
			return null;
		}
	}

	/** Tools are added here, one register line per class, as each ticket lands. */
	private static void registerTools(ToolRegistry registry) {
		rt4.mcp.tools.StatusTools.register(registry);
		rt4.mcp.tools.ChatTools.register(registry);
		rt4.mcp.tools.FindEntities.register(registry);
		rt4.mcp.tools.ActionTools.register(registry);
		rt4.mcp.tools.InterfaceTools.register(registry);
	}

	public static McpHttpServer running() {
		return running;
	}

	public static synchronized void stop() {
		if (running != null) {
			running.stop();
			running = null;
		}
	}
}
