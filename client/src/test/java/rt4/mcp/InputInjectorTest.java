package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** MCP-26 — held keys are released in deadline order, not insertion order. */
class InputInjectorTest {
	private static final int KEY_A = 65;
	private static final int KEY_B = 66;

	@Test
	void aShortHoldIsReleasedBeforeALongOneQueuedFirst() {
		long longHold = 500L / InputInjector.MILLIS_PER_FRAME;
		long shortHold = 40L / InputInjector.MILLIS_PER_FRAME;

		InputInjector.ReleaseQueue queue = new InputInjector.ReleaseQueue();
		queue.add(new InputInjector.PendingRelease(KEY_A, longHold));
		queue.add(new InputInjector.PendingRelease(KEY_B, shortHold));

		assertTrue(queue.due(shortHold - 1L).isEmpty(), "nothing is due before the shortest hold");
		assertEquals(2, queue.size());

		List<InputInjector.PendingRelease> early = queue.due(shortHold);
		assertEquals(1, early.size());
		assertEquals(KEY_B, early.get(0).virtualKey, "the 40 ms hold must not wait for the 500 ms one");

		List<InputInjector.PendingRelease> late = queue.due(longHold);
		assertEquals(1, late.size());
		assertEquals(KEY_A, late.get(0).virtualKey);
		assertTrue(queue.isEmpty());
	}

	@Test
	void releasesDueOnTheSameFrameKeepTheirHoldOrder() {
		InputInjector.ReleaseQueue queue = new InputInjector.ReleaseQueue();
		for (int key = 0; key < 20; key++) {
			queue.add(new InputInjector.PendingRelease(KEY_A + key, 5L));
		}

		List<InputInjector.PendingRelease> due = queue.due(5L);

		assertEquals(20, due.size());
		for (int i = 0; i < due.size(); i++) {
			assertEquals(KEY_A + i, due.get(i).virtualKey, "first held, first released");
		}
	}
}
