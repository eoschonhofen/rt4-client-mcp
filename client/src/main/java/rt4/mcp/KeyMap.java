package rt4.mcp;

/**
 * MCP-10 — maps the names and characters the agent sends to AWT virtual key codes.
 * Pure, so the mapping is unit-tested.
 */
public final class KeyMap {
	public static final int VK_A = java.awt.event.KeyEvent.VK_A;
	public static final int VK_0 = java.awt.event.KeyEvent.VK_0;

	private KeyMap() {
	}

	/** A named key such as {@code enter}, {@code f5} or {@code up}; -1 when unknown. */
	public static int namedKey(String name) {
		if (name == null) {
			return -1;
		}
		String key = name;
		if (key.length() == 1) {
			return charKey(key.charAt(0));
		}
		key = key.trim();
		if (key.length() == 1) {
			return charKey(key.charAt(0));
		}
		String lower = key.toLowerCase(java.util.Locale.ROOT);
		switch (lower) {
			case "enter":
			case "return":
				return java.awt.event.KeyEvent.VK_ENTER;
			case "escape":
			case "esc":
				return java.awt.event.KeyEvent.VK_ESCAPE;
			case "backspace":
			case "back":
				return java.awt.event.KeyEvent.VK_BACK_SPACE;
			case "tab":
				return java.awt.event.KeyEvent.VK_TAB;
			case "space":
			case "spacebar":
				return java.awt.event.KeyEvent.VK_SPACE;
			case "up":
				return java.awt.event.KeyEvent.VK_UP;
			case "down":
				return java.awt.event.KeyEvent.VK_DOWN;
			case "left":
				return java.awt.event.KeyEvent.VK_LEFT;
			case "right":
				return java.awt.event.KeyEvent.VK_RIGHT;
			case "home":
				return java.awt.event.KeyEvent.VK_HOME;
			case "end":
				return java.awt.event.KeyEvent.VK_END;
			case "pageup":
				return java.awt.event.KeyEvent.VK_PAGE_UP;
			case "pagedown":
				return java.awt.event.KeyEvent.VK_PAGE_DOWN;
			case "delete":
			case "del":
				return java.awt.event.KeyEvent.VK_DELETE;
			case "insert":
				return java.awt.event.KeyEvent.VK_INSERT;
			case "shift":
				return java.awt.event.KeyEvent.VK_SHIFT;
			case "ctrl":
			case "control":
				return java.awt.event.KeyEvent.VK_CONTROL;
			case "alt":
				return java.awt.event.KeyEvent.VK_ALT;
			default:
				break;
		}
		if (lower.length() >= 2 && lower.charAt(0) == 'f') {
			try {
				int number = Integer.parseInt(lower.substring(1));
				if (number >= 1 && number <= 12) {
					return java.awt.event.KeyEvent.VK_F1 + number - 1;
				}
			} catch (NumberFormatException notAFunctionKey) {
				return -1;
			}
		}
		return -1;
	}

	/** The base key that types this character, or -1. Shifted punctuation maps to its unshifted key. */
	public static int charKey(char c) {
		if (c >= 'a' && c <= 'z') {
			return VK_A + (c - 'a');
		}
		if (c >= 'A' && c <= 'Z') {
			return VK_A + (c - 'A');
		}
		if (c >= '0' && c <= '9') {
			return VK_0 + (c - '0');
		}
		switch (c) {
			case ' ':
				return java.awt.event.KeyEvent.VK_SPACE;
			case '\t':
				return java.awt.event.KeyEvent.VK_TAB;
			case '\n':
			case '\r':
				return java.awt.event.KeyEvent.VK_ENTER;
			case '`':
			case '~':
				return java.awt.event.KeyEvent.VK_BACK_QUOTE;
			case '-':
			case '_':
				return java.awt.event.KeyEvent.VK_MINUS;
			case '=':
			case '+':
				return java.awt.event.KeyEvent.VK_EQUALS;
			case '[':
			case '{':
				return java.awt.event.KeyEvent.VK_OPEN_BRACKET;
			case ']':
			case '}':
				return java.awt.event.KeyEvent.VK_CLOSE_BRACKET;
			case '\\':
			case '|':
				return java.awt.event.KeyEvent.VK_BACK_SLASH;
			case ';':
			case ':':
				return java.awt.event.KeyEvent.VK_SEMICOLON;
			case '\'':
			case '"':
				return java.awt.event.KeyEvent.VK_QUOTE;
			case ',':
			case '<':
				return java.awt.event.KeyEvent.VK_COMMA;
			case '.':
			case '>':
				return java.awt.event.KeyEvent.VK_PERIOD;
			case '/':
			case '?':
				return java.awt.event.KeyEvent.VK_SLASH;
			case '!':
				return java.awt.event.KeyEvent.VK_1;
			case '@':
				return java.awt.event.KeyEvent.VK_2;
			case '#':
				return java.awt.event.KeyEvent.VK_3;
			case '$':
				return java.awt.event.KeyEvent.VK_4;
			case '%':
				return java.awt.event.KeyEvent.VK_5;
			case '^':
				return java.awt.event.KeyEvent.VK_6;
			case '&':
				return java.awt.event.KeyEvent.VK_7;
			case '*':
				return java.awt.event.KeyEvent.VK_8;
			case '(':
				return java.awt.event.KeyEvent.VK_9;
			case ')':
				return java.awt.event.KeyEvent.VK_0;
			default:
				return -1;
		}
	}

	/** True when the character needs Shift held, i.e. an uppercase letter or shifted punctuation. */
	public static boolean needsShift(char c) {
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		return "~_+{}|:\"<>?!@#$%^&*()".indexOf(c) >= 0;
	}

	/** The client only handles characters below 256 (plus the euro sign). */
	public static boolean isTypable(char c) {
		return (c > 0 && c < 256) || c == 8364;
	}

	/** The character the client sees for a typable input, matching {@code Keyboard.getKeyChar}. */
	public static int clientChar(char c) {
		return c == 8364 ? 128 : c;
	}
}
