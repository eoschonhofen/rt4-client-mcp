package rt4.aionly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** AIO-08 — the client token rules mirror the server's (AIO-04). */
class AgentTokenTest {

	@Test
	void generatesValidDistinctTokens() {
		java.util.Set<String> seen = new java.util.HashSet<String>();
		for (int i = 0; i < 1000; i++) {
			String token = AgentToken.generate();
			Assertions.assertTrue(AgentToken.isValid(token), token);
			seen.add(token);
		}
		Assertions.assertEquals(1000, seen.size());
	}

	@Test
	void rejectsWrongShapes() {
		Assertions.assertFalse(AgentToken.isValid(null));
		Assertions.assertFalse(AgentToken.isValid("a".repeat(AgentToken.LENGTH - 1)));
		Assertions.assertFalse(AgentToken.isValid("a".repeat(AgentToken.LENGTH + 1)));
		Assertions.assertFalse(AgentToken.isValid("ABCDEFGHIJKLMNOPQRST"));
		Assertions.assertFalse(AgentToken.isValid("abc_defghijklmnopqr"));
		Assertions.assertFalse(AgentToken.isValid("hunter22"));
	}

	@Test
	void acceptsTheFullAlphabet() {
		Assertions.assertTrue(AgentToken.isValid("abcdefghijklmnopqrst"));
		Assertions.assertTrue(AgentToken.isValid("01234567890123456789"));
	}
}
