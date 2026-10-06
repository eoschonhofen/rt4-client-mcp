package rt4.aionly;

import java.security.SecureRandom;

/**
 * AIO-08 — the client half of the agent token, mirroring the server's rules (AIO-04).
 *
 * <p>Twenty characters of {@code [a-z0-9]} from {@link SecureRandom}. The human never sees
 * this; it is generated here and sent as the registration password inside the RSA block.</p>
 */
public final class AgentToken {
	public static final int LENGTH = 20;

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	private AgentToken() {
	}

	/** True when {@code token} is exactly {@link #LENGTH} characters of {@code [a-z0-9]}. */
	public static boolean isValid(String token) {
		if (token == null || token.length() != LENGTH) {
			return false;
		}
		for (int i = 0; i < token.length(); i++) {
			if (ALPHABET.indexOf(token.charAt(i)) < 0) {
				return false;
			}
		}
		return true;
	}

	/** A fresh token. Never log the result. */
	public static String generate() {
		char[] chars = new char[LENGTH];
		for (int i = 0; i < LENGTH; i++) {
			chars[i] = ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()));
		}
		return new String(chars);
	}
}
