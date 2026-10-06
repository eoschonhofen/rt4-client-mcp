package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

	// AIO-11 — the synthetic flag must cover exactly the injected call, and nothing else.

	@Test
	void theInjectionFlagIsSetOnlyDuringTheAction() {
		AtomicBoolean seen = new AtomicBoolean(false);
		assertFalse(InputInjector.injecting);

		InputInjector.withInjection(() -> seen.set(InputInjector.injecting));

		assertTrue(seen.get(), "the flag must be set while the keyboard call runs");
		assertFalse(InputInjector.injecting, "the flag must be cleared afterwards");
	}

	@Test
	void theInjectionFlagIsClearedWhenTheActionThrows() {
		assertThrows(RuntimeException.class, () -> InputInjector.withInjection(() -> {
			throw new RuntimeException("boom");
		}));
		assertFalse(InputInjector.injecting);
	}

	@Test
	void aSyntheticKeyNeedsTheGameThreadToo() {
		GameThread.reset();
		InputInjector.withInjection(() -> assertFalse(InputInjector.isSyntheticKey(),
			"a real event on the AWT thread must not pass as synthetic"));
	}
}
