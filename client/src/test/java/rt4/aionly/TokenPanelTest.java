package rt4.aionly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

/** AIO-09 — the panel's state machine and its click/key handling. */
class TokenPanelTest {

	@AfterEach
	void closePanel() {
		TokenPanel.dismiss();
	}

	@Test
	void dismissingDropsTheTokenFromMemory() {
		TokenPanel.show("bob", "k3j9q0z8m1x7c4v2b6n5");
		Assertions.assertTrue(TokenPanel.isOpen());
		Assertions.assertEquals("bob", TokenPanel.shownName());
		Assertions.assertEquals("k3j9q0z8m1x7c4v2b6n5", TokenPanel.shownToken());

		TokenPanel.dismiss();

		Assertions.assertFalse(TokenPanel.isOpen());
		Assertions.assertNull(TokenPanel.shownToken());
		Assertions.assertNull(TokenPanel.shownName());
	}

	@Test
	void swallowsEveryClickAndOnlyOkDismisses() {
		TokenPanel.show("bob", "k3j9q0z8m1x7c4v2b6n5");

		Assertions.assertTrue(TokenPanel.handleClick(1, 1, 765, 503), "a stray click must not reach the title screen");
		Assertions.assertTrue(TokenPanel.isOpen());

		int[] ok = TokenPanel.okButton(765, 503);
		Assertions.assertTrue(TokenPanel.handleClick(ok[0] + 2, ok[1] + 2, 765, 503));
		Assertions.assertFalse(TokenPanel.isOpen());
	}

	@Test
	void aClosedPanelDoesNotSwallowClicks() {
		Assertions.assertFalse(TokenPanel.handleClick(10, 10, 765, 503));
	}

	@Test
	void enterDismissesAndOtherKeysDoNot() {
		TokenPanel.show("bob", "k3j9q0z8m1x7c4v2b6n5");

		Assertions.assertFalse(TokenPanel.handleKey(KeyEvent.VK_A));
		Assertions.assertTrue(TokenPanel.isOpen());

		Assertions.assertTrue(TokenPanel.handleKey(KeyEvent.VK_ENTER));
		Assertions.assertFalse(TokenPanel.isOpen());
	}
}
