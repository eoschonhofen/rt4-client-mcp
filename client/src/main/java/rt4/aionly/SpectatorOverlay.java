package rt4.aionly;

import com.google.gson.JsonObject;

import rt4.mcp.McpServer;

/**
 * AIO-14 — the in-game badge that tells a spectator this client is agent-driven.
 *
 * <p>It is drawn only in a locked (release) build, and the box lives in the top-left
 * {@link #BOX_WIDTH}x{@link #BOX_HEIGHT} corner, which is the documented fallback for keeping
 * it out of {@code get_screenshot}.</p>
 */
public final class SpectatorOverlay {
	public static final int BOX_WIDTH = 200;
	public static final int BOX_HEIGHT = 36;
	/** A call this recent means the agent is active rather than idle. */
	public static final long ACTIVE_WINDOW_MS = 30_000L;

	private static volatile String lastAction;
	private static volatile long lastCallMillis;

	private SpectatorOverlay() {
	}

	/** Records one tool call. A read-only tool does not change what the badge shows. */
	public static void record(String toolName, JsonObject args) {
		String summary = ActionSummary.of(toolName, args);
		if (summary == null) {
			return;
		}
		lastAction = summary;
		lastCallMillis = System.currentTimeMillis();
	}

	public static String lastAction() {
		return lastAction;
	}

	public static void reset() {
		lastAction = null;
		lastCallMillis = 0L;
	}

	/** The first line: who is driving and on which port. */
	public static String badgeText(int boundPort) {
		if (boundPort < 0) {
			return "AI-CONTROLLED · no MCP";
		}
		boolean active = lastCallMillis != 0L
			&& System.currentTimeMillis() - lastCallMillis <= ACTIVE_WINDOW_MS;
		return "AI-CONTROLLED · MCP :" + boundPort + " · " + (active ? "active" : "idle");
	}

	/** The second line: the last action and how long ago it was. */
	public static String lastActionLine() {
		String action = lastAction;
		if (action == null) {
			return "last: -";
		}
		long seconds = lastCallMillis == 0L ? 0L : (System.currentTimeMillis() - lastCallMillis) / 1000L;
		return "last: " + action + " (" + seconds + "s ago)";
	}

	static void draw() {
		if (!Lockdown.ENABLED) {
			return;
		}
		Overlay.box(4, 4, BOX_WIDTH, BOX_HEIGHT, 0x000000, 150);
		Overlay.textLeft(badgeText(McpServer.boundPort()), 8, 16, Overlay.GREEN);
		Overlay.textLeft(lastActionLine(), 8, 30, Overlay.WHITE);
	}
}
