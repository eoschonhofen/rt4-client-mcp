package rt4.mcp;

import rt4.PlayerList;
import rt4.client;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * MCP-04 — every read or mutation of game state happens on the game thread.
 *
 * <p>HTTP threads submit a {@link Callable}, then block on it with a timeout. The game
 * thread calls {@link #drain()} once per frame from {@code client.mainLoop()}, right
 * after {@code Mouse.loop()}, so packets written by a tool leave in the same frame flush
 * as real input.</p>
 */
public final class GameThread {
	public static final long DEFAULT_TIMEOUT_MS = 2000L;

	/** Per-frame limits so a burst of tool calls cannot stall rendering. */
	static final int MAX_TASKS_PER_FRAME = 50;
	static final long MAX_MILLIS_PER_FRAME = 8L;

	private static final ConcurrentLinkedQueue<FutureTask<?>> queue = new ConcurrentLinkedQueue<FutureTask<?>>();
	private static volatile Thread owner;
	private static volatile long frame;

	/** MCP-22 — distinct hook failures already logged, so a per-frame error cannot spam the log. */
	private static final Set<String> REPORTED_HOOK_FAILURES =
			Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
	private static final int MAX_HOOK_REPORTS = 32;

	/** Set by MCP-13; runs after the task queue, before the waiters. */
	public static volatile Runnable navigationStep;
	/** Set by MCP-12; runs last, so waiters observe this frame's actions. */
	public static volatile Runnable waiterTick;

	private GameThread() {
	}

	public static <T> T call(Callable<T> fn) throws ToolException {
		return call(fn, DEFAULT_TIMEOUT_MS);
	}

	/**
	 * Runs {@code fn} on the game thread and waits up to {@code timeoutMs} for the result.
	 * Called from the game thread itself it runs inline, which prevents a deadlock.
	 */
	public static <T> T call(Callable<T> fn, long timeoutMs) throws ToolException {
		if (Thread.currentThread() == owner) {
			try {
				return fn.call();
			} catch (ToolException expected) {
				throw expected;
			} catch (Exception other) {
				throw new ToolException(other.toString(), other);
			}
		}

		FutureTask<T> task = new FutureTask<T>(fn);
		queue.add(task);
		try {
			return task.get(timeoutMs, TimeUnit.MILLISECONDS);
		} catch (TimeoutException timedOut) {
			boolean wasQueued = queue.remove(task);
			// MCP-22 — never interrupt the game thread: a task that already started finishes
			// normally, only a queued one is dropped.
			task.cancel(false);
			throw new ToolException("game thread did not respond in " + timeoutMs + "ms (loading or frozen?); the task "
					+ (wasQueued ? "was still queued, so it did not run" : "was already running, so it may have executed"));
		} catch (ExecutionException failed) {
			Throwable cause = failed.getCause();
			if (cause instanceof ToolException) {
				throw (ToolException) cause;
			}
			throw new ToolException(cause == null ? failed.toString() : cause.toString());
		} catch (InterruptedException interrupted) {
			Thread.currentThread().interrupt();
			throw new ToolException("interrupted while waiting for the game thread");
		}
	}

	/**
	 * Runs the queued calls, then the per-frame hooks. Called from {@code client.mainLoop()}
	 * only. Leftovers run next frame.
	 */
	public static void drain() {
		owner = Thread.currentThread();
		frame++;

		long deadline = System.nanoTime() + MAX_MILLIS_PER_FRAME * 1_000_000L;
		int processed = 0;
		FutureTask<?> task;
		while (processed < MAX_TASKS_PER_FRAME && (task = queue.poll()) != null) {
			task.run();
			processed++;
			if (System.nanoTime() >= deadline) {
				break;
			}
		}

		Runnable navigation = navigationStep;
		if (navigation != null) {
			try {
				navigation.run();
			} catch (Throwable failure) {
				reportHookFailure("navigation", failure);
				try {
					rt4.mcp.nav.NavTask.cancel("internal error: " + failure);
				} catch (Throwable ignored) {
					// the nav task is already broken; nothing else to do
				}
			}
		}
		Runnable waiters = waiterTick;
		if (waiters != null) {
			try {
				waiters.run();
			} catch (Throwable failure) {
				reportHookFailure("waiters", failure);
			}
		}
		try {
			InputInjector.tick();
		} catch (Throwable failure) {
			reportHookFailure("input", failure);
		}
	}

	/** MCP-22 — log the first occurrence of each distinct hook failure, with its stack trace. */
	private static void reportHookFailure(String hook, Throwable failure) {
		String message = "[MCP] " + hook + " hook failed: " + failure;
		if (REPORTED_HOOK_FAILURES.size() >= MAX_HOOK_REPORTS || !REPORTED_HOOK_FAILURES.add(message)) {
			return;
		}
		System.err.println(message);
		failure.printStackTrace();
	}

	/** Frames drained so far, i.e. one per rendered frame. */
	public static long frame() {
		return frame;
	}

	public static Thread owner() {
		return owner;
	}

	public static int pending() {
		return queue.size();
	}

	/**
	 * MCP-04 guard for the world tools. {@code login}, {@code get_status} and
	 * {@code get_screenshot} deliberately do not call it.
	 */
	public static void requireLoggedIn() throws ToolException {
		if (client.gameState != 30 || PlayerList.self == null) {
			throw new ToolException("not logged in");
		}
	}

	/** Test support: forget the owner, the queue and the frame counter. */
	public static void reset() {
		queue.clear();
		owner = null;
		frame = 0L;
		navigationStep = null;
		waiterTick = null;
		REPORTED_HOOK_FAILURES.clear();
	}
}
