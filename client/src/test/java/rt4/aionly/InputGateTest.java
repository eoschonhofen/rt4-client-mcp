package rt4.aionly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** AIO-11 / AIO-12 — the full truth table over locked x synthetic x gameState. */
class InputGateTest {

	@Test
	void aDevBuildAllowsEverything() {
		for (int state : new int[] {0, 5, 10, 25, 30, 40}) {
			Assertions.assertTrue(InputGate.allow(false, state, false), "real input at state " + state);
			Assertions.assertTrue(InputGate.allow(true, state, false), "synthetic input at state " + state);
		}
	}

	@Test
	void lockdownDropsRealInputEverywhereButTheTitleScreen() {
		Assertions.assertFalse(InputGate.allow(false, 0, true));
		Assertions.assertFalse(InputGate.allow(false, 5, true));
		Assertions.assertFalse(InputGate.allow(false, 25, true));
		Assertions.assertFalse(InputGate.allow(false, 30, true));
		Assertions.assertFalse(InputGate.allow(false, 40, true));
	}

	@Test
	void theTitleScreenHoleStaysLive() {
		Assertions.assertTrue(InputGate.allow(false, InputGate.TITLE_SCREEN_STATE, true));
	}

	@Test
	void syntheticInputAlwaysWorks() {
		Assertions.assertTrue(InputGate.allow(true, 25, true));
		Assertions.assertTrue(InputGate.allow(true, 30, true));
	}
}
