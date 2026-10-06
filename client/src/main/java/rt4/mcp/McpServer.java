package rt4.mcp;

import java.io.IOException;
import java.net.BindException;

/**
 * MCP-03 — builds the tool registry and starts the transport, without letting any MCP
 * failure stop the game from launching.
 *
 * <p>AIO-13 — several clients run on one machine. The listener walks up from the configured
 * port until one binds, and the bound port is what the window title and the
 * {@code claude mcp add} line report.</p>
 */
public final class McpServer {
	/** How many ports above the configured one are tried before giving up. */
	public static final int PORT_SEARCH_SPAN = 9;
	/** The first port keeps the plain server name, so existing setups do not break. */
	public static final int PLAIN_NAME_PORT = McpConfig.DEFAULT_PORT;

	private static volatile McpHttpServer running;
	private static volatile int boundPort = -1;
	private static volatile boolean enabled;

	private McpServer() {
	}

	/**
	 * Starts the server when the config enables it. Returns the server, or null when it
	 * could not start (already logged). Never throws.
	 */
	public static synchronized McpHttpServer start(McpConfig config) {
		enabled = config.enabled;
		if (!config.enabled) {
			boundPort = -1;
			return null;
		}
		if (running != null) {
			return running;
		}

		ToolRegistry registry = new ToolRegistry();
		registerTools(registry);

		IOException otherFailure = null;
		for (int offset = 0; offset <= PORT_SEARCH_SPAN; offset++) {
			try {
				McpHttpServer server = McpHttpServer.start(config.withPort(config.port + offset), registry);
				running = server;
				boundPort = server.port();
				System.err.println("[MCP] listening on " + server.url());
				System.err.println("[MCP] claude mcp add --transport http " + serverName(boundPort)
						+ " " + server.url()
						+ " --header \"Authorization: Bearer " + config.token + "\"");
				return server;
			} catch (BindException inUse) {
				// Another client on this machine has this port; try the next one.
				continue;
			} catch (IOException other) {
				otherFailure = other;
				break;
			} catch (Throwable error) {
				System.err.println("[MCP] failed to start: " + error);
				boundPort = -1;
				return null;
			}
		}

		boundPort = -1;
		if (otherFailure != null) {
			System.err.println("[MCP] failed to bind " + config.port + ": " + otherFailure);
		} else {
			System.err.println("[MCP] ports " + config.port + "-" + (config.port + PORT_SEARCH_SPAN)
					+ " are all in use, MCP disabled; close a client and restart this one");
		}
		return null;
	}

	/** The name for {@code claude mcp add}: the first port keeps the plain name. */
	public static String serverName(int port) {
		return port == PLAIN_NAME_PORT ? "rt4" : "rt4-" + port;
	}

	/** Tools are added here, one register line per class, as each ticket lands. */
	static void registerTools(ToolRegistry registry) {
		rt4.mcp.tools.StatusTools.register(registry);
		rt4.mcp.tools.ChatTools.register(registry);
		rt4.mcp.tools.FindEntities.register(registry);
		rt4.mcp.tools.ActionTools.register(registry);
		rt4.mcp.tools.InterfaceTools.register(registry);
		rt4.mcp.tools.InputTools.register(registry);
		rt4.mcp.tools.SessionTools.register(registry);
		rt4.mcp.tools.AccountTools.register(registry);
		rt4.mcp.tools.ScreenshotTool.register(registry);
		rt4.mcp.tools.WaitTool.register(registry);
		rt4.mcp.tools.WalkTool.register(registry);
		rt4.mcp.tools.HelperTools.register(registry);

		// Run the wait_for registry and the nav driver every frame, after the task queue.
		GameThread.navigationStep = rt4.mcp.nav.NavTask::step;
		GameThread.waiterTick = Waiters::evaluate;
	}

	public static McpHttpServer running() {
		return running;
	}

	/** The port actually bound, or -1 when no listener is up. */
	public static int boundPort() {
		return boundPort;
	}

	/** Whether the config asked for MCP at all; a disabled client shows no title suffix. */
	public static boolean enabled() {
		return enabled;
	}

	public static synchronized void stop() {
		if (running != null) {
			running.stop();
			running = null;
		}
		boundPort = -1;
	}
}
