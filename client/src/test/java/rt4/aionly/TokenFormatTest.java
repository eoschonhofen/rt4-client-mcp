package rt4.aionly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** AIO-09 — token grouping for display. */
class TokenFormatTest {

	@Test
	void groupsATwentyCharacterTokenInFives() {
		Assertions.assertEquals("k3j9q 0z8m1 x7c4v 2b6n5",
			TokenFormat.group("k3j9q0z8m1x7c4v2b6n5"));
	}

	@Test
	void handlesShortAndEmptyTokens() {
		Assertions.assertEquals("", TokenFormat.group(null));
		Assertions.assertEquals("", TokenFormat.group(""));
		Assertions.assertEquals("abc", TokenFormat.group("abc"));
		Assertions.assertEquals("abcde f", TokenFormat.group("abcdef"));
	}
}
