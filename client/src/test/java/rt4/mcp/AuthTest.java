package rt4.mcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthTest {
	private static final String TOKEN = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";

	@Test
	void bearerMissingWrongAndRight() {
		assertFalse(Auth.bearerMatches(null, TOKEN));
		assertFalse(Auth.bearerMatches("", TOKEN));
		assertFalse(Auth.bearerMatches("Bearer", TOKEN));
		assertFalse(Auth.bearerMatches("Bearer wrong", TOKEN));
		assertFalse(Auth.bearerMatches(TOKEN, TOKEN));
		assertTrue(Auth.bearerMatches("Bearer " + TOKEN, TOKEN));
	}

	@Test
	void bearerIsCaseSensitiveOnTheScheme() {
		assertFalse(Auth.bearerMatches("bearer " + TOKEN, TOKEN));
	}

	@Test
	void bearerWithoutConfiguredTokenIsRejected() {
		assertFalse(Auth.bearerMatches("Bearer anything", ""));
		assertFalse(Auth.bearerMatches("Bearer anything", null));
	}

	@Test
	void originPolicy() {
		assertTrue(Auth.originAllowed(null));
		assertTrue(Auth.originAllowed(""));
		assertTrue(Auth.originAllowed("http://localhost:3000"));
		assertTrue(Auth.originAllowed("http://127.0.0.1:3000"));
		assertTrue(Auth.originAllowed("http://[::1]:3000"));
		assertFalse(Auth.originAllowed("http://evil.com"));
		assertFalse(Auth.originAllowed("https://localhost.evil.com"));
		assertFalse(Auth.originAllowed("not a uri"));
	}

	@Test
	void originLiteralNullIsRejected() {
		assertFalse(Auth.originAllowed("null"));
	}

	@Test
	void hostPolicy() {
		assertTrue(Auth.hostAllowed("127.0.0.1:43600", 43600));
		assertTrue(Auth.hostAllowed("localhost:43600", 43600));
		assertTrue(Auth.hostAllowed("LOCALHOST:43600", 43600));
		assertFalse(Auth.hostAllowed("127.0.0.1:43601", 43600));
		assertFalse(Auth.hostAllowed("evil.com:43600", 43600));
		assertFalse(Auth.hostAllowed("127.0.0.1", 43600));
		assertFalse(Auth.hostAllowed(null, 43600));
	}
}
