package rt4.aionly;

/**
 * AIO-11 / AIO-12 — which input events the client is willing to act on.
 *
 * <p>In a locked build, real mouse and keyboard events are dropped (except hover, the wheel and camera keys), synthetic events from
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

	/**
	 * Passive viewing a human may do in a locked build: pointer movement (hover) and the wheel.
	 * No press, so nothing is clicked, selected or typed.
	 */
	public static boolean allowPassiveMouse() {
		return true;
	}

	/** Arrow keys and Page Up/Down move the camera; no other real key passes in game. */
	public static boolean allowKey(boolean synthetic, int gameState, int awtKeyCode) {
		return allowKey(synthetic, gameState) || isCameraKey(awtKeyCode);
	}

	static boolean isCameraKey(int awtKeyCode) {
		switch (awtKeyCode) {
			case java.awt.event.KeyEvent.VK_LEFT:
			case java.awt.event.KeyEvent.VK_RIGHT:
			case java.awt.event.KeyEvent.VK_UP:
			case java.awt.event.KeyEvent.VK_DOWN:
			case java.awt.event.KeyEvent.VK_PAGE_UP:
			case java.awt.event.KeyEvent.VK_PAGE_DOWN:
				return true;
			default:
				return false;
		}
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
