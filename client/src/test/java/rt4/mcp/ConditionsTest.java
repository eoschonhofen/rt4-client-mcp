package rt4.mcp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionsTest {
	private static JsonObject json(String text) {
		return JsonParser.parseString(text).getAsJsonObject();
	}

	private static Conditions.Condition parse(String text) throws Exception {
		return Conditions.parse(json(text));
	}

	private static boolean evaluate(Conditions.Condition condition, FakeGameView view) {
		Conditions.snapshot(condition, view);
		return Conditions.evaluate(condition, view);
	}

	@Test
	void parsesEveryKnownCondition() throws Exception {
		String[] names = {
				"{\"condition\":\"idle\",\"for_ticks\":3}",
				"{\"condition\":\"ticks\",\"n\":5}",
				"{\"condition\":\"logged_in\"}",
				"{\"condition\":\"logged_out\"}",
				"{\"condition\":\"position\",\"x\":1,\"y\":2,\"plane\":0,\"radius\":1}",
				"{\"condition\":\"dialogue_open\"}",
				"{\"condition\":\"interface_open\",\"id\":149}",
				"{\"condition\":\"interface_closed\",\"id\":149}",
				"{\"condition\":\"inventory_changed\"}",
				"{\"condition\":\"item_count\",\"id\":995,\"op\":\">=\",\"n\":1}",
				"{\"condition\":\"chat_matches\",\"regex\":\"log\",\"types\":[0,2]}",
				"{\"condition\":\"skill_xp_changed\",\"skill\":\"Woodcutting\"}",
				"{\"condition\":\"hp_below\",\"n\":5}",
				"{\"condition\":\"npc_gone\",\"target\":\"npc:1423\"}",
				"{\"condition\":\"nav_done\",\"task\":3}",
				"{\"condition\":\"animation\",\"id\":733}"
		};
		for (String text : names) {
			assertEquals(16, names.length);
			parse(text);
		}
	}

	@Test
	void acceptsTypeAndNameAsTheKey() throws Exception {
		assertEquals("idle", parse("{\"type\":\"idle\"}").name);
		assertEquals("idle", parse("{\"name\":\"idle\"}").name);
		assertEquals("idle", parse("{\"condition\":\"IDLE\"}").name);
	}

	@Test
	void unknownConditionIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> parse("{\"condition\":\"teleport\"}"));
		assertTrue(error.getMessage().contains("unknown condition 'teleport'"), error.getMessage());
	}

	@Test
	void missingConditionNameIsRejected() {
		assertThrows(ToolException.class, () -> parse("{\"x\":1}"));
	}

	@Test
	void badRegexIsRejected() {
		ToolException error = assertThrows(ToolException.class,
				() -> parse("{\"condition\":\"chat_matches\",\"regex\":\"[unclosed\"}"));
		assertTrue(error.getMessage().contains("invalid regex"), error.getMessage());
	}

	@Test
	void badItemComparisonIsRejected() {
		ToolException error = assertThrows(ToolException.class,
				() -> parse("{\"condition\":\"item_count\",\"id\":1,\"op\":\"!=\",\"n\":1}"));
		assertTrue(error.getMessage().contains("'op' must be"), error.getMessage());
	}

	@Test
	void unknownSkillIsRejected() {
		ToolException error = assertThrows(ToolException.class,
				() -> parse("{\"condition\":\"skill_xp_changed\",\"skill\":\"Invention\"}"));
		assertTrue(error.getMessage().contains("unknown skill"), error.getMessage());
	}

	@Test
	void missingRequiredArgumentIsRejected() {
		assertThrows(ToolException.class, () -> parse("{\"condition\":\"position\",\"x\":1}"));
		assertThrows(ToolException.class, () -> parse("{\"condition\":\"ticks\"}"));
	}

	@Test
	void malformedTargetInNpcGoneIsRejected() {
		assertThrows(ToolException.class, () -> parse("{\"condition\":\"npc_gone\",\"target\":\"banana\"}"));
	}

	@Test
	void emptyConditionListIsRejected() {
		assertThrows(ToolException.class, () -> Conditions.parseAll(Collections.<JsonObject>emptyList()));
		assertThrows(ToolException.class, () -> Conditions.parseAll(null));
	}

	@Test
	void modeParsing() throws Exception {
		assertEquals(Conditions.Mode.ANY, Conditions.parseMode(null));
		assertEquals(Conditions.Mode.ANY, Conditions.parseMode("any"));
		assertEquals(Conditions.Mode.ALL, Conditions.parseMode("all"));
		assertThrows(ToolException.class, () -> Conditions.parseMode("either"));
	}

	@Test
	void ticksConditionWaitsForTheServerTick() throws Exception {
		FakeGameView view = new FakeGameView();
		view.tick = 100;
		Conditions.Condition condition = parse("{\"condition\":\"ticks\",\"n\":5}");
		Conditions.snapshot(condition, view);

		assertFalse(Conditions.evaluate(condition, view));
		view.tick = 104;
		assertFalse(Conditions.evaluate(condition, view));
		view.tick = 105;
		assertTrue(Conditions.evaluate(condition, view));
	}

	@Test
	void idleConditionNeedsConsecutiveTicks() throws Exception {
		FakeGameView view = new FakeGameView();
		view.tick = 10;
		Conditions.Condition condition = parse("{\"condition\":\"idle\",\"for_ticks\":3}");
		Conditions.snapshot(condition, view);

		assertFalse(Conditions.evaluate(condition, view)); // tick 10 counts as 1
		view.tick = 11;
		assertFalse(Conditions.evaluate(condition, view)); // 2
		view.tick = 12;
		assertTrue(Conditions.evaluate(condition, view)); // 3

		view.idle = false;
		assertFalse(Conditions.evaluate(condition, view));
		view.idle = true;
		assertFalse(Conditions.evaluate(condition, view)); // streak restarts
		view.tick = 13;
		assertFalse(Conditions.evaluate(condition, view));
		view.tick = 14;
		assertTrue(Conditions.evaluate(condition, view));
	}

	@Test
	void positionUsesChebyshevDistanceAndPlane() throws Exception {
		FakeGameView view = new FakeGameView();
		view.x = 3222;
		view.y = 3218;
		view.plane = 0;

		Conditions.Condition adjacent = parse("{\"condition\":\"position\",\"x\":3223,\"y\":3219,\"radius\":1}");
		assertTrue(evaluate(adjacent, view));

		Conditions.Condition tooFar = parse("{\"condition\":\"position\",\"x\":3230,\"y\":3218,\"radius\":1}");
		assertFalse(evaluate(tooFar, view));

		Conditions.Condition wrongPlane = parse("{\"condition\":\"position\",\"x\":3222,\"y\":3218,\"plane\":1}");
		assertFalse(evaluate(wrongPlane, view));

		Conditions.Condition rightPlane = parse("{\"condition\":\"position\",\"x\":3222,\"y\":3218,\"plane\":0}");
		assertTrue(evaluate(rightPlane, view));
	}

	@Test
	void loginConditions() throws Exception {
		FakeGameView view = new FakeGameView();
		view.loggedIn = true;
		view.loggedOut = false;
		assertTrue(evaluate(parse("{\"condition\":\"logged_in\"}"), view));
		assertFalse(evaluate(parse("{\"condition\":\"logged_out\"}"), view));

		view.loggedIn = false;
		view.loggedOut = true;
		assertFalse(evaluate(parse("{\"condition\":\"logged_in\"}"), view));
		assertTrue(evaluate(parse("{\"condition\":\"logged_out\"}"), view));
	}

	@Test
	void interfaceOpenAndClosed() throws Exception {
		FakeGameView view = new FakeGameView();
		view.interfaces.put(149, true);

		assertTrue(evaluate(parse("{\"condition\":\"interface_open\",\"id\":149}"), view));
		assertFalse(evaluate(parse("{\"condition\":\"interface_closed\",\"id\":149}"), view));
		assertTrue(evaluate(parse("{\"condition\":\"interface_closed\",\"id\":548}"), view));
	}

	@Test
	void inventoryChangedComparesTheHash() throws Exception {
		FakeGameView view = new FakeGameView();
		view.inventoryHash = 42;
		Conditions.Condition condition = parse("{\"condition\":\"inventory_changed\"}");
		Conditions.snapshot(condition, view);

		assertFalse(Conditions.evaluate(condition, view));
		view.inventoryHash = 43;
		assertTrue(Conditions.evaluate(condition, view));
	}

	@Test
	void itemCountComparisons() throws Exception {
		FakeGameView view = new FakeGameView();
		view.itemCounts.put(995, 7);

		assertTrue(evaluate(parse("{\"condition\":\"item_count\",\"id\":995,\"op\":\">=\",\"n\":7}"), view));
		assertTrue(evaluate(parse("{\"condition\":\"item_count\",\"id\":995,\"op\":\"<=\",\"n\":10}"), view));
		assertTrue(evaluate(parse("{\"condition\":\"item_count\",\"id\":995,\"op\":\"==\",\"n\":7}"), view));
		assertFalse(evaluate(parse("{\"condition\":\"item_count\",\"id\":995,\"op\":\">=\",\"n\":8}"), view));
	}

	@Test
	void chatMatchesOnlyLooksAtNewMessages() throws Exception {
		FakeGameView view = new FakeGameView();
		view.addChat(0, "old message");
		Conditions.Condition condition = parse("{\"condition\":\"chat_matches\",\"regex\":\"old\"}");
		Conditions.snapshot(condition, view);

		assertFalse(Conditions.evaluate(condition, view), "messages from before the call must not match");

		view.addChat(2, "a new log appeared");
		Conditions.Condition fresh = parse("{\"condition\":\"chat_matches\",\"regex\":\"log\"}");
		Conditions.snapshot(fresh, view);
		view.addChat(0, "you get a log");
		assertTrue(Conditions.evaluate(fresh, view));
	}

	@Test
	void chatMatchesRespectsTheTypeFilter() throws Exception {
		FakeGameView view = new FakeGameView();
		Conditions.Condition condition = parse("{\"condition\":\"chat_matches\",\"regex\":\"hello\",\"types\":[2]}");
		Conditions.snapshot(condition, view);

		view.addChat(0, "hello there"); // type 0, filtered out
		assertFalse(Conditions.evaluate(condition, view));

		view.addChat(2, "hello again"); // type 2
		assertTrue(Conditions.evaluate(condition, view));
	}

	@Test
	void skillXpChanged() throws Exception {
		FakeGameView view = new FakeGameView();
		view.xp.put(8, 1000);
		Conditions.Condition woodcutting = parse("{\"condition\":\"skill_xp_changed\",\"skill\":\"Woodcutting\"}");
		Conditions.snapshot(woodcutting, view);

		view.xp.put(8, 1000);
		assertFalse(Conditions.evaluate(woodcutting, view));
		view.xp.put(8, 1025);
		assertTrue(Conditions.evaluate(woodcutting, view));

		Conditions.Condition anySkill = parse("{\"condition\":\"skill_xp_changed\"}");
		Conditions.snapshot(anySkill, view);
		assertFalse(Conditions.evaluate(anySkill, view));
		view.xp.put(0, 500);
		assertTrue(Conditions.evaluate(anySkill, view));
	}

	@Test
	void hpBelowAndAnimation() throws Exception {
		FakeGameView view = new FakeGameView();
		view.hp = 4;
		assertTrue(evaluate(parse("{\"condition\":\"hp_below\",\"n\":5}"), view));
		assertFalse(evaluate(parse("{\"condition\":\"hp_below\",\"n\":4}"), view));

		view.animation = -1;
		assertFalse(evaluate(parse("{\"condition\":\"animation\"}"), view));
		view.animation = 733;
		assertTrue(evaluate(parse("{\"condition\":\"animation\"}"), view));
		assertTrue(evaluate(parse("{\"condition\":\"animation\",\"id\":733}"), view));
		assertFalse(evaluate(parse("{\"condition\":\"animation\",\"id\":734}"), view));
	}

	@Test
	void npcGoneUsesTheSnapshottedType() throws Exception {
		FakeGameView view = new FakeGameView();
		view.alive.put("npc:1423", true);
		view.types.put("npc:1423", 9);
		Conditions.Condition condition = parse("{\"condition\":\"npc_gone\",\"target\":\"npc:1423\"}");
		Conditions.snapshot(condition, view);
		assertEquals(9, condition.expectedType);

		assertFalse(Conditions.evaluate(condition, view));
		view.alive.put("npc:1423", false);
		assertTrue(Conditions.evaluate(condition, view));

		view.alive.put("npc:1423", true);
		view.types.put("npc:1423", 12); // a different NPC in the same index
		assertTrue(Conditions.evaluate(condition, view));
	}

	@Test
	void navDone() throws Exception {
		FakeGameView view = new FakeGameView();
		view.navDone.put(3, true);
		assertTrue(evaluate(parse("{\"condition\":\"nav_done\",\"task\":3}"), view));
		assertFalse(evaluate(parse("{\"condition\":\"nav_done\",\"task\":4}"), view));
	}

	@Test
	void dialogueOpen() throws Exception {
		FakeGameView view = new FakeGameView();
		assertFalse(evaluate(parse("{\"condition\":\"dialogue_open\"}"), view));
		view.dialogueOpen = true;
		assertTrue(evaluate(parse("{\"condition\":\"dialogue_open\"}"), view));
	}

	@Test
	void combineHonoursTheMode() {
		assertTrue(Conditions.combine(Conditions.Mode.ANY, new boolean[]{false, true}));
		assertFalse(Conditions.combine(Conditions.Mode.ANY, new boolean[]{false, false}));
		assertTrue(Conditions.combine(Conditions.Mode.ALL, new boolean[]{true, true}));
		assertFalse(Conditions.combine(Conditions.Mode.ALL, new boolean[]{true, false}));
		assertFalse(Conditions.combine(Conditions.Mode.ANY, new boolean[0]));
	}

	@Test
	void whichListsTheMetConditions() throws Exception {
		List<Conditions.Condition> conditions = Arrays.asList(
				parse("{\"condition\":\"logged_in\"}"),
				parse("{\"condition\":\"ticks\",\"n\":1}"),
				parse("{\"condition\":\"dialogue_open\"}"));
		assertEquals(Arrays.asList("logged_in", "ticks"),
				Conditions.which(conditions, new boolean[]{true, true, false}));
	}

	@Test
	void timeoutIsClamped() {
		assertEquals(10000, Conditions.clampTimeout(0));
		assertEquals(10000, Conditions.clampTimeout(-5));
		assertEquals(3000, Conditions.clampTimeout(3000));
		assertEquals(Conditions.MAX_TIMEOUT_MS, Conditions.clampTimeout(999999));
	}

	@Test
	void chatTypesParsingIsOrderIndependent() throws Exception {
		Conditions.Condition condition = parse("{\"condition\":\"chat_matches\",\"regex\":\"x\",\"types\":[2,0]}");
		assertEquals(new HashSet<Integer>(Arrays.asList(0, 2)), condition.chatTypes);
	}
}
