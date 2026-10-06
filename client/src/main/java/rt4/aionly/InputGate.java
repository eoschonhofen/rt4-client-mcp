package rt4.aionly;

/**
 * AIO-11 / AIO-12 — which input events the client is willing to act on.
 *
 * <p>In a locked build, real mouse and keyboard events are dropped, synthetic events from
 * MCP are kept, and the title screen stays live so a human can create an account and pick a
 * world (AIO-12). The decision is pure, so the whole truth table is covered by a test.</p>
 */
public final class InputGate {
	/** {@code client.gameState} on the title screen, the one state a human may still touch. */
	public static final int TITLE_SCREEN_STATE = 10;

	private InputGate() {
	}

	public static boolean allowMouse(boolean synthetic, int gameState) {
		return allow(synthetic, gameState, Lockdown.ENABLED);
	}

	public static boolean allowKey(boolean synthetic, int gameState) {
		return allow(synthetic, gameState, Lockdown.ENABLED);
	}

	/** The truth table over locked x synthetic x state, with the build flag passed in. */
	static boolean allow(boolean synthetic, int gameState, boolean locked) {
		if (!locked) {
			return true;
		}
		if (synthetic) {
			return true;
		}
		// Loading states below the title screen need no input, and every in-game state is locked.
		return gameState == TITLE_SCREEN_STATE;
	}
}
