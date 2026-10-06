package rt4.aionly;

import java.awt.event.KeyEvent;

/**
 * AIO-09 — the "account created" panel on the title screen.
 *
 * <p>The server shows a token exactly once, so this is the human's only chance to read it.
 * The durable copy is {@code accounts.json}, which the agent reads with {@code get_account};
 * the panel drops the token from memory as soon as it is dismissed.</p>
 */
public final class TokenPanel {
	public static final int PANEL_WIDTH = 360;
	public static final int PANEL_HEIGHT = 96;
	public static final int OK_WIDTH = 40;
	public static final int OK_HEIGHT = 16;

	private static volatile String name;
	private static volatile String token;
	private static volatile boolean open;

	private TokenPanel() {
	}

	public static void show(String accountName, String accountToken) {
		name = accountName;
		token = accountToken;
		open = true;
	}

	public static void dismiss() {
		open = false;
		// The token must not outlive the panel; accounts.json is the durable copy.
		token = null;
		name = null;
	}

	public static boolean isOpen() {
		return open;
	}

	/** The account name being shown, or null. */
	public static String shownName() {
		return name;
	}

	/** The token while the panel is open, null afterwards. */
	public static String shownToken() {
		return token;
	}

	/** The OK button's top-left corner, in canvas coordinates. */
	static int[] okButton(int canvasWidth, int canvasHeight) {
		int left = canvasWidth / 2 - PANEL_WIDTH / 2;
		int top = canvasHeight / 2 - 20;
		return new int[] { left + PANEL_WIDTH - OK_WIDTH - 10, top + PANEL_HEIGHT - OK_HEIGHT - 8 };
	}

	/**
	 * Handles a click at canvas coordinates while the panel is open.
	 *
	 * @return true when the panel swallowed the click, so the title screen must not see it.
	 */
	public static boolean handleClick(int x, int y, int canvasWidth, int canvasHeight) {
		if (!open) {
			return false;
		}
		int[] ok = okButton(canvasWidth, canvasHeight);
		if (x >= ok[0] && x <= ok[0] + OK_WIDTH && y >= ok[1] && y <= ok[1] + OK_HEIGHT) {
			dismiss();
		}
		// Every click goes to the panel while it is open, so a human cannot click through it.
		return true;
	}

	/** Enter dismisses the panel. Returns true when the key was consumed. */
	public static boolean handleKey(int keyCode) {
		if (open && keyCode == KeyEvent.VK_ENTER) {
			dismiss();
			return true;
		}
		return false;
	}

	static void draw(int canvasWidth, int canvasHeight) {
		if (!open || token == null) {
			return;
		}
		int left = canvasWidth / 2 - PANEL_WIDTH / 2;
		int top = canvasHeight / 2 - 20;

		Overlay.box(left, top, PANEL_WIDTH, PANEL_HEIGHT, 0x000000, 190);
		Overlay.textLeft("Account created: " + name, left + 10, top + 20, Overlay.WHITE);
		Overlay.textLeft("Token: " + TokenFormat.group(token), left + 10, top + 38, Overlay.YELLOW);
		Overlay.textLeft("Saved to accounts.json — your agent can read it with get_account.",
			left + 10, top + 58, Overlay.GREY);
		Overlay.textLeft("This token is shown once by the server.", left + 10, top + 72, Overlay.GREY);

		int[] ok = okButton(canvasWidth, canvasHeight);
		Overlay.box(ok[0], ok[1], OK_WIDTH, OK_HEIGHT, 0x303030, 220);
		Overlay.textCenter("OK", ok[0] + OK_WIDTH / 2, ok[1] + 12, Overlay.WHITE);
	}
}
