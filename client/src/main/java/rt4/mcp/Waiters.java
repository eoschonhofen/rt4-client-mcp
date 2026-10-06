package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * MCP-12 — the registry of blocked {@code wait_for} calls, evaluated once per frame on the
 * game thread by {@code GameThread.drain()}.
 */
public final class Waiters {
	/** The result of a wait: met, timed out, or aborted because the player logged out. */
	public static final class Outcome {
		public final boolean met;
		public final boolean timedOut;
		public final String reason;
		public final List<String> which;
		public final int ticksWaited;
		private final JsonObject status;

		Outcome(boolean met, boolean timedOut, String reason, List<String> which, int ticksWaited, JsonObject status) {
			this.met = met;
			this.timedOut = timedOut;
			this.reason = reason;
			this.which = which;
			this.ticksWaited = ticksWaited;
			this.status = status;
		}

		public JsonObject toJson() {
			JsonObject out = new JsonObject();
			out.addProperty("met", met);
			if (timedOut) {
				out.addProperty("timed_out", true);
			}
			if (reason != null) {
				out.addProperty("reason", reason);
			}
			if (!which.isEmpty()) {
				JsonArray names = new JsonArray();
				for (String name : which) {
					names.add(name);
				}
				out.add("which", names);
			}
			out.addProperty("ticks_waited", ticksWaited);
			if (status != null) {
				out.add("status", status);
			}
			return out;
		}
	}

	public static final class Waiter {
		public final long id;
		public final List<Conditions.Condition> conditions;
		public final Conditions.Mode mode;
		/**
		 * Whether the player was already logged out when the wait started. A wait begun on the
		 * title screen must survive the logout guard, otherwise wait_for(logged_in) could never
		 * succeed.
		 */
		final boolean startedLoggedOut;
		final CompletableFuture<Outcome> future = new CompletableFuture<Outcome>();

		Waiter(long id, List<Conditions.Condition> conditions, Conditions.Mode mode, boolean startedLoggedOut) {
			this.id = id;
			this.conditions = conditions;
			this.mode = mode;
			this.startedLoggedOut = startedLoggedOut;
		}

		boolean expectsLoggedOut() {
			for (Conditions.Condition condition : conditions) {
				if (condition.expectsLoggedOut()) {
					return true;
				}
			}
			return false;
		}
	}

	private static final AtomicLong NEXT_ID = new AtomicLong(1L);
	private static final Map<Long, Waiter> ACTIVE = new ConcurrentHashMap<Long, Waiter>();

	private static volatile Conditions.GameView view = new LiveGameView();

	private Waiters() {
	}

	/** Swapped by the tests for a fake. */
	public static void setView(Conditions.GameView gameView) {
		view = gameView == null ? new LiveGameView() : gameView;
	}

	public static Conditions.GameView view() {
		return view;
	}

	/** Takes the snapshot and starts waiting. Must run on the game thread. */
	public static Waiter register(List<Conditions.Condition> conditions, Conditions.Mode mode) {
		Conditions.GameView current = view;
		for (Conditions.Condition condition : conditions) {
			Conditions.snapshot(condition, current);
		}
		Waiter waiter = new Waiter(NEXT_ID.getAndIncrement(), conditions, mode, current.loggedOut());
		ACTIVE.put(waiter.id, waiter);
		return waiter;
	}

	/** Blocks the calling (HTTP) thread until the waiter completes or the timeout expires. */
	public static Outcome await(Waiter waiter, long timeoutMs) {
		try {
			return waiter.future.get(timeoutMs, TimeUnit.MILLISECONDS);
		} catch (TimeoutException timedOut) {
			ACTIVE.remove(waiter.id);
			waiter.future.cancel(false);
			return new Outcome(false, true, null, new ArrayList<String>(), 0, status());
		} catch (InterruptedException interrupted) {
			Thread.currentThread().interrupt();
			ACTIVE.remove(waiter.id);
			waiter.future.cancel(false);
			return new Outcome(false, false, "interrupted", new ArrayList<String>(), 0, status());
		} catch (Exception failure) {
			ACTIVE.remove(waiter.id);
			return new Outcome(false, false, failure.toString(), new ArrayList<String>(), 0, status());
		}
	}

	/** Runs every frame on the game thread. With no waiters it does not touch game state. */
	public static void evaluate() {
		if (ACTIVE.isEmpty()) {
			return;
		}
		Conditions.GameView current = view;
		boolean loggedOut = current.loggedOut();

		for (Waiter waiter : ACTIVE.values()) {
			if (loggedOut && !waiter.startedLoggedOut && !waiter.expectsLoggedOut()) {
				complete(waiter, new Outcome(false, false, "logged_out", new ArrayList<String>(),
						current.tick(), status()));
				continue;
			}

			boolean[] results = new boolean[waiter.conditions.size()];
			for (int i = 0; i < results.length; i++) {
				results[i] = Conditions.evaluate(waiter.conditions.get(i), current);
			}
			if (Conditions.combine(waiter.mode, results)) {
				complete(waiter, new Outcome(true, false, null, Conditions.which(waiter.conditions, results),
						current.tick(), status()));
			}
		}
	}

	private static void complete(Waiter waiter, Outcome outcome) {
		ACTIVE.remove(waiter.id);
		waiter.future.complete(outcome);
	}

	/** Aborts everything, e.g. when the client is shutting down. */
	public static void cancelAll(String reason) {
		for (Waiter waiter : ACTIVE.values()) {
			complete(waiter, new Outcome(false, false, reason, new ArrayList<String>(), 0, null));
		}
	}

	public static int activeWaiters() {
		return ACTIVE.size();
	}

	/** The small status block embedded in every outcome, to save the agent a follow-up call. */
	public static JsonObject status() {
		Conditions.GameView current = view;
		JsonObject status = new JsonObject();
		status.addProperty("logged_in", current.loggedIn());
		if (current.loggedIn()) {
			JsonObject position = new JsonObject();
			position.addProperty("x", current.x());
			position.addProperty("y", current.y());
			position.addProperty("plane", current.plane());
			status.add("position", position);
			JsonObject hp = new JsonObject();
			hp.addProperty("current", current.hp());
			status.add("hp", hp);
			status.addProperty("animation", current.animation());
			status.addProperty("idle", current.idle());
		}
		return status;
	}
}
