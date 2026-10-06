package rt4.mcp;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MCP-03 — request authorization checks. Pure, so the whole policy is unit-testable.
 */
public final class Auth {
	private Auth() {
	}

	/**
	 * {@code Authorization: Bearer <token>}, compared in constant time.
	 * A missing header, a missing configured token or any mismatch is false.
	 */
	public static boolean bearerMatches(String authorization, String token) {
		if (authorization == null || token == null || token.isEmpty()) {
			return false;
		}
		byte[] expected = ("Bearer " + token).getBytes(StandardCharsets.UTF_8);
		byte[] actual = authorization.getBytes(StandardCharsets.UTF_8);
		return MessageDigest.isEqual(expected, actual);
	}

	/**
	 * An absent Origin is allowed (non-browser clients send none). If present it must be
	 * a URI on the loopback host, otherwise it is a cross-origin browser request.
	 */
	public static boolean originAllowed(String origin) {
		if (origin == null || origin.isEmpty()) {
			return true;
		}
		try {
			String host = new URI(origin).getHost();
			if (host == null) {
				return false;
			}
			if (host.startsWith("[") && host.endsWith("]")) {
				host = host.substring(1, host.length() - 1);
			}
			return "localhost".equals(host) || "127.0.0.1".equals(host) || "::1".equals(host);
		} catch (URISyntaxException malformed) {
			return false;
		}
	}

	/**
	 * DNS-rebinding defense: the Host header must name the loopback address we bound to,
	 * with the exact port.
	 */
	public static boolean hostAllowed(String host, int port) {
		if (host == null) {
			return false;
		}
		return host.equals("127.0.0.1:" + port) || host.equalsIgnoreCase("localhost:" + port);
	}
}
