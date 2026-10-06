package rt4.mcp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class GameThreadTest {
	private ExecutorService http;

	@BeforeEach
	void setUp() {
		GameThread.reset();
		http = Executors.newCachedThreadPool();
	}

	@AfterEach
	void tearDown() {
		GameThread.reset();
		http.shutdownNow();
	}

	private static void awaitPending(int expected) throws InterruptedException {
		long deadline = System.currentTimeMillis() + 3000L;
		while (GameThread.pending() < expected) {
			if (System.currentTimeMillis() > deadline) {
				fail("only " + GameThread.pending() + " of " + expected + " tasks were enqueued");
			}
			Thread.sleep(1L);
		}
	}

	@Test
	void resultPropagation() throws Exception {
		Future<String> future = http.submit(() -> GameThread.call(() -> "hello", 2000L));
		awaitPending(1);

		GameThread.drain();

		assertEquals("hello", future.get(2, TimeUnit.SECONDS));
		assertEquals(0, GameThread.pending());
	}

	@Test
	void toolExceptionIsRethrownAsIs() throws Exception {
		Future<String> future = http.submit(() -> {
			try {
				GameThread.call(() -> {
					throw new ToolException("target gone: npc:1423");
				}, 2000L);
				return "no exception";
			} catch (ToolException expected) {
				return expected.getMessage();
			}
		});
		awaitPending(1);

		GameThread.drain();

		assertEquals("target gone: npc:1423", future.get(2, TimeUnit.SECONDS));
	}

	@Test
	void otherExceptionsAreWrappedWithTheirToString() throws Exception {
		Future<String> future = http.submit(() -> {
			try {
				GameThread.call(() -> {
					throw new IllegalStateException("broken");
				}, 2000L);
				return "no exception";
			} catch (ToolException wrapped) {
				return wrapped.getMessage();
			}
		});
		awaitPending(1);

		GameThread.drain();

		assertTrue(future.get(2, TimeUnit.SECONDS).contains("broken"));
	}

	@Test
	void timeoutWhenDrainIsNeverCalled() {
		long started = System.nanoTime();

		ToolException error = assertThrows(ToolException.class, () -> GameThread.call(() -> "never", 80L));

		long elapsedMs = (System.nanoTime() - started) / 1_000_000L;
		assertTrue(elapsedMs >= 60L, "timed out too early: " + elapsedMs + "ms");
		assertTrue(error.getMessage().contains("80ms"), error.getMessage());
		assertTrue(error.getMessage().contains("game thread"), error.getMessage());
		assertEquals(0, GameThread.pending(), "the timed-out task must be removed from the queue");
	}

	@Test
	void inlineExecutionOnTheOwnerThread() throws Exception {
		AtomicInteger result = new AtomicInteger(-1);
		CountDownLatch done = new CountDownLatch(1);

		Thread gameThread = new Thread(() -> {
			GameThread.drain(); // claims ownership
			try {
				result.set(GameThread.call(() -> 42, 2000L));
			} catch (ToolException error) {
				result.set(-2);
			} finally {
				done.countDown();
			}
		}, "fake-game-thread");
		gameThread.start();

		assertTrue(done.await(3, TimeUnit.SECONDS), "inline call deadlocked");
		assertEquals(42, result.get());
		assertEquals(0, GameThread.pending());
	}

	@Test
	void perFrameTaskCapIsRespected() throws Exception {
		int total = GameThread.MAX_TASKS_PER_FRAME * 2 + 10;
		AtomicInteger ran = new AtomicInteger();
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		for (int i = 0; i < total; i++) {
			futures.add(http.submit(() -> GameThread.call(ran::incrementAndGet, 5000L)));
		}
		awaitPending(total);

		GameThread.drain();
		assertEquals(GameThread.MAX_TASKS_PER_FRAME, ran.get(), "one frame must run at most the cap");

		long deadline = System.currentTimeMillis() + 3000L;
		while (GameThread.pending() > 0) {
			GameThread.drain();
			if (System.currentTimeMillis() > deadline) {
				fail("leftovers never drained");
			}
		}
		assertEquals(total, ran.get());
		for (Future<Integer> future : futures) {
			assertTrue(future.get(2, TimeUnit.SECONDS) > 0);
		}
	}

	@Test
	void frameHooksRunAfterTasksInOrder() throws Exception {
		List<String> order = new CopyOnWriteArrayList<String>();
		GameThread.navigationStep = () -> order.add("navigation");
		GameThread.waiterTick = () -> order.add("waiters");

		Future<String> future = http.submit(() -> GameThread.call(() -> {
			order.add("task");
			return "ok";
		}, 2000L));
		awaitPending(1);

		GameThread.drain();

		assertEquals("ok", future.get(2, TimeUnit.SECONDS));
		assertEquals(3, order.size());
		assertEquals("task", order.get(0));
		assertEquals("navigation", order.get(1));
		assertEquals("waiters", order.get(2));
	}

	@Test
	void frameCounterAdvancesPerDrain() {
		long before = GameThread.frame();
		GameThread.drain();
		GameThread.drain();
		assertEquals(before + 2, GameThread.frame());
	}

	@Test
	void drainDoesNotDeadlockWithoutHooks() {
		GameThread.drain();
		assertEquals(0, GameThread.pending());
	}

	// ------------------------------------------------------------------ MCP-22

	@Test
	void throwingNavigationHookDoesNotEscapeDrain() throws Exception {
		List<String> ran = new CopyOnWriteArrayList<String>();
		GameThread.navigationStep = () -> {
			throw new IllegalStateException("nav boom");
		};
		GameThread.waiterTick = () -> ran.add("waiters");

		GameThread.drain(); // must swallow the hook failure

		assertEquals(1, ran.size(), "the waiters hook must still run that frame");
	}

	@Test
	void timeoutMessageSaysTheTaskWasStillQueued() {
		ToolException error = assertThrows(ToolException.class, () -> GameThread.call(() -> "never", 60L));

		assertTrue(error.getMessage().contains("still queued"), error.getMessage());
		assertTrue(error.getMessage().contains("did not run"), error.getMessage());
	}

	@Test
	void timedOutRunningTaskDoesNotInterruptTheDrainingThread() throws Exception {
		CountDownLatch started = new CountDownLatch(1);
		Future<String> caller = http.submit(() -> {
			try {
				return GameThread.call(() -> {
					started.countDown();
					long end = System.nanoTime() + 300_000_000L;
					while (System.nanoTime() < end) {
						// busy wait, deliberately ignoring interrupts
					}
					return "done";
				}, 80L);
			} catch (ToolException timedOut) {
				return "timed out";
			}
		});
		awaitPending(1);

		GameThread.drain();

		assertTrue(started.await(1, TimeUnit.SECONDS), "the task must have run on the draining thread");
		assertEquals("timed out", caller.get(3, TimeUnit.SECONDS));
		assertFalse(Thread.interrupted(), "the draining thread must never be interrupted");
	}
}
