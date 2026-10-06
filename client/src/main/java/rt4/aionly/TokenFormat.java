package rt4.aionly;

/** AIO-09 — display formatting for a token. The spaces are presentation only. */
public final class TokenFormat {
	public static final int GROUP_SIZE = 5;

	private TokenFormat() {
	}

	/** {@code "k3j9q0z8m1x7c4v2b6n5"} becomes {@code "k3j9q 0z8m1 x7c4v 2b6n5"}. */
	public static String group(String token) {
		if (token == null || token.isEmpty()) {
			return "";
		}
		StringBuilder out = new StringBuilder(token.length() + token.length() / GROUP_SIZE);
		for (int i = 0; i < token.length(); i++) {
			if (i > 0 && i % GROUP_SIZE == 0) {
				out.append(' ');
			}
			out.append(token.charAt(i));
		}
		return out.toString();
	}
}
