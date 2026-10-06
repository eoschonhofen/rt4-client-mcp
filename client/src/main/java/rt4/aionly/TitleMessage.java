package rt4.aionly;

/**
 * AIO-12 — a one-line notice drawn under the login box.
 *
 * <p>Used to tell a human that logging in happens through MCP, and (AIO-13) that no MCP port
 * could be bound. It is pure state plus a draw call; the draw hook lives in
 * {@link Overlays}.</p>
 */
public final class TitleMessage {
	public static final long DEFAULT_DURATION_MS = 5000L;

	private static volatile String message;
	private static volatile long expiresAtMillis;

	private TitleMessage() {
	}

	public static void show(String text) {
		show(text, DEFAULT_DURATION_MS);
	}

	public static void show(String text, long durationMs) {
		message = text;
		expiresAtMillis = System.currentTimeMillis() + Math.max(0L, durationMs);
	}

	/** A message that stays until {@link #dismiss()} — for a condition that is still true. */
	public static void showPersistent(String text) {
		message = text;
		expiresAtMillis = Long.MAX_VALUE;
	}

	public static void dismiss() {
		message = null;
		expiresAtMillis = 0L;
	}

	/** The text to show right now, or null when there is none. */
	public static String current() {
		String text = message;
		if (text == null) {
			return null;
		}
		if (System.currentTimeMillis() >= expiresAtMillis) {
			dismiss();
			return null;
		}
		return text;
	}

	static void draw(int canvasWidth, int canvasHeight) {
		String text = current();
		if (text == null) {
			return;
		}
		int centerX = canvasWidth / 2;
		int y = canvasHeight * 3 / 5 + 20;
		Overlay.box(centerX - 170, y - 13, 340, 18, 0x000000, 170);
		Overlay.textCenter(text, centerX, y, Overlay.YELLOW);
	}
}
