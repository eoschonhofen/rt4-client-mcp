package rt4.mcp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WaitersTest {
	@AfterEach
	void tearDown() {
		Waiters.cancelAll("test cleanup");
		Waiters.setView(null);
	}

	private static JsonObject json(String text) {
		return JsonParser.parseString(text).getAsJsonObject();
	}

	private static List<Conditions.Condition> conditions(String... texts) throws Exception {
		java.util.List<JsonObject> objects = new java.util.ArrayList<JsonObject>();
		for (String text : texts) {
			objects.add(json(text));
		}
		return Conditions.parseAll(objects);
	}

	@Test
	void snapshotIsTakenAtRegistration() throws Exception {
		FakeGameView view = new FakeGameView();
		view.tick = 42;
		view.inventoryHash = 7;
		view.chatSize = 11;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"inventory_changed\"}"), Conditions.Mode.ANY, 60000L);

		assertEquals(42, waiter.conditions.get(0).startTick);
		assertEquals(7, waiter.conditions.get(0).startHash);
		assertEquals(11, waiter.conditions.get(0).startChatSize);
		Waiters.cancelAll("done");
	}

	@Test
	void completesWhenTheConditionHolds() throws Exception {
		FakeGameView view = new FakeGameView();
		view.inventoryHash = 1;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"inventory_changed\"}"), Conditions.Mode.ANY, 60000L);
		Waiters.evaluate();
		assertFalse(waiter.future.isDone());

		view.inventoryHash = 2;
		Waiters.evaluate();

		assertTrue(waiter.future.isDone());
		Waiters.Outcome outcome = waiter.future.get();
		assertTrue(outcome.met);
		assertFalse(outcome.timedOut);
		assertNull(outcome.reason);
		assertEquals(Arrays.asList("inventory_changed"), outcome.which);
		assertEquals(0, Waiters.activeWaiters());
	}

	@Test
	void allModeWaitsForEveryCondition() throws Exception {
		FakeGameView view = new FakeGameView();
		view.interfaces.put(149, true);
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions(
				"{\"condition\":\"interface_open\",\"id\":149}",
				"{\"condition\":\"dialogue_open\"}"), Conditions.Mode.ALL, 60000L);

		Waiters.evaluate();
		assertFalse(waiter.future.isDone());

		view.dialogueOpen = true;
		Waiters.evaluate();

		Waiters.Outcome outcome = waiter.future.get();
		assertTrue(outcome.met);
		assertEquals(Arrays.asList("interface_open", "dialogue_open"), outcome.which);
	}

	@Test
	void anyModeReturnsOnTheFirstMatch() throws Exception {
		FakeGameView view = new FakeGameView();
		view.hp = 3;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions(
				"{\"condition\":\"ticks\",\"n\":5}",
				"{\"condition\":\"hp_below\",\"n\":5}"), Conditions.Mode.ANY, 60000L);

		Waiters.evaluate();

		Waiters.Outcome outcome = waiter.future.get();
		assertTrue(outcome.met);
		assertEquals(Arrays.asList("hp_below"), outcome.which);
	}

	@Test
	void timeoutRemovesTheWaiter() throws Exception {
		FakeGameView view = new FakeGameView();
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"ticks\",\"n\":999}"), Conditions.Mode.ANY, 60000L);
		Waiters.Outcome outcome = Waiters.await(waiter, 40L);

		assertFalse(outcome.met);
		assertTrue(outcome.timedOut);
		assertTrue(waiter.future.isCancelled());
		assertEquals(0, Waiters.activeWaiters());
	}

	@Test
	void logoutAbortsWaitersThatAreNotWaitingForIt() throws Exception {
		FakeGameView view = new FakeGameView();
		view.loggedIn = true;
		view.loggedOut = false;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"ticks\",\"n\":999}"), Conditions.Mode.ANY, 60000L);
		view.loggedOut = true;
		view.loggedIn = false;
		Waiters.evaluate();

		Waiters.Outcome outcome = waiter.future.get();
		assertFalse(outcome.met);
		assertEquals("logged_out", outcome.reason);
	}

	@Test
	void logoutStillCompletesAWaiterThatWantsLoggedOut() throws Exception {
		FakeGameView view = new FakeGameView();
		view.loggedIn = true;
		view.loggedOut = false;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"logged_out\"}"), Conditions.Mode.ANY, 60000L);
		view.loggedOut = true;
		view.loggedIn = false;
		Waiters.evaluate();

		Waiters.Outcome outcome = waiter.future.get();
		assertTrue(outcome.met);
		assertNull(outcome.reason);
	}

	@Test
	void waitForLoggedInSurvivesTheLogoutGuard() throws Exception {
		FakeGameView view = new FakeGameView();
		view.loggedIn = false;
		view.loggedOut = true;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"logged_in\"}"), Conditions.Mode.ANY, 60000L);
		Waiters.evaluate();
		assertFalse(waiter.future.isDone(), "a wait that starts on the title screen must not be aborted");

		view.loggedOut = false;
		view.loggedIn = true;
		Waiters.evaluate();

		assertTrue(waiter.future.get().met);
	}

	@Test
	void cancelAllAbortsEverything() throws Exception {
		FakeGameView view = new FakeGameView();
		Waiters.setView(view);

		Waiters.Waiter first = Waiters.register(conditions("{\"condition\":\"ticks\",\"n\":999}"), Conditions.Mode.ANY, 60000L);
		Waiters.Waiter second = Waiters.register(conditions("{\"condition\":\"dialogue_open\"}"), Conditions.Mode.ANY, 60000L);

		Waiters.cancelAll("shutting down");

		assertEquals("shutting down", first.future.get().reason);
		assertEquals("shutting down", second.future.get().reason);
		assertEquals(0, Waiters.activeWaiters());
	}

	@Test
	void evaluateWithNoWaitersIsANoOp() {
		Waiters.cancelAll("none");
		Waiters.evaluate();
		assertEquals(0, Waiters.activeWaiters());
	}

	@Test
	void outcomeJsonCarriesTheUsefulFields() throws Exception {
		FakeGameView view = new FakeGameView();
		view.inventoryHash = 1;
		view.x = 3222;
		view.y = 3218;
		view.hp = 9;
		view.animation = -1;
		view.idle = true;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(conditions("{\"condition\":\"inventory_changed\"}"), Conditions.Mode.ANY, 60000L);
		view.inventoryHash = 5;
		Waiters.evaluate();

		JsonObject json = waiter.future.get().toJson();
		assertTrue(json.get("met").getAsBoolean());
		assertEquals("inventory_changed", json.getAsJsonArray("which").get(0).getAsString());
		JsonObject status = json.getAsJsonObject("status");
		assertEquals(3222, status.getAsJsonObject("position").get("x").getAsInt());
		assertEquals(9, status.getAsJsonObject("hp").get("current").getAsInt());
		assertTrue(status.get("idle").getAsBoolean());
	}

	// ------------------------------------------------------------------ MCP-20

	@Test
	void ticksWaitedIsRelativeToTheWait() throws Exception {
		FakeGameView view = new FakeGameView();
		view.tick = 1000;
		view.inventoryHash = 1;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(
				conditions("{\"condition\":\"inventory_changed\"}"), Conditions.Mode.ANY, 60000L);
		view.inventoryHash = 2;
		view.tick = 1005;
		Waiters.evaluate();

		Waiters.Outcome outcome = waiter.future.get();
		assertTrue(outcome.met);
		assertEquals(5, outcome.ticksWaited, "ticks_waited must be relative to the wait, not absolute");
	}

	@Test
	void timeoutIsEnforcedOnTheGameThreadWithAStatus() throws Exception {
		FakeGameView view = new FakeGameView();
		view.tick = 1000;
		view.x = 3222;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(
				conditions("{\"condition\":\"ticks\",\"n\":999}"), Conditions.Mode.ANY, 0L);
		view.tick = 1005;
		Waiters.evaluate(); // the game thread expires it

		Waiters.Outcome outcome = Waiters.await(waiter, 50L);

		assertFalse(outcome.met);
		assertTrue(outcome.timedOut);
		assertEquals(5, outcome.ticksWaited);
		JsonObject json = outcome.toJson();
		assertTrue(json.has("status"), "a timeout enforced on the game thread still carries a status");
		assertEquals(3222, json.getAsJsonObject("status").getAsJsonObject("position").get("x").getAsInt());
	}

	@Test
	void timeoutWithoutTheGameThreadDoesNotReadGameState() throws Exception {
		FakeGameView view = new FakeGameView();
		view.x = 3222;
		Waiters.setView(view);

		Waiters.Waiter waiter = Waiters.register(
				conditions("{\"condition\":\"ticks\",\"n\":999}"), Conditions.Mode.ANY, 60000L);
		Waiters.Outcome outcome = Waiters.await(waiter, 20L); // evaluate() never runs

		assertTrue(outcome.timedOut);
		assertFalse(outcome.toJson().has("status"), "the HTTP thread must not build a status block");
	}
}
