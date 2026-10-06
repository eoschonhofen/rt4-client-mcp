package rt4.aionly;

import rt4.Keyboard;
import rt4.Mouse;
import rt4.mcp.GameThread;
import rt4.mcp.ToolException;

/**
 * AIO-11 — a real tool call counts as activity.
 *
 * <p>The client logs out after roughly five minutes without mouse or keyboard activity, and
 * an agent acting only through MCP never touches those counters. Every tool that changes
 * something resets them on the game thread; a purely read-only tool does not.</p>
 */
public final class IdleKeepAlive {
	/** Short: this runs after the reply is decided, and is never worth blocking a response for. */
	private static final long RESET_TIMEOUT_MS = 500L;

	private IdleKeepAlive() {
	}

	/** Best effort: if the game thread is busy or gone, the next call tries again. */
	public static void reset() {
		try {
			GameThread.call(() -> {
				Mouse.setIdleLoops(0);
				Keyboard.idleLoops = 0;
				return null;
			}, RESET_TIMEOUT_MS);
		} catch (ToolException ignored) {
			// The game thread did not answer in time; the idle counters simply keep counting.
		}
	}
}
