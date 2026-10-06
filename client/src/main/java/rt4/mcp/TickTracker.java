package rt4.mcp;

/**
 * MCP-12 — the server tick counter.
 *
 * <p>The exact self player-update handler is hard to pin down reliably, so this uses the
 * wall-clock fallback the ticket allows: one tick every 600 ms. {@link #APPROXIMATE} is
 * reported to the agent so it knows tick counts are close, not exact.</p>
 */
public final class TickTracker {
	public static final long TICK_NANOS = 600_000_000L;
	public static final boolean APPROXIMATE = true;

	private static final long START = System.nanoTime();

	private TickTracker() {
	}

	public static int tick() {
		return (int) ((System.nanoTime() - START) / TICK_NANOS);
	}

	public static long millisIntoCurrentTick() {
		return ((System.nanoTime() - START) % TICK_NANOS) / 1_000_000L;
	}
}
