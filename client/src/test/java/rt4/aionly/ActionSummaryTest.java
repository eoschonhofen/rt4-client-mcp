package rt4.aionly;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** AIO-14 — summaries are short, formatted and never carry a credential. */
class ActionSummaryTest {

	@Test
	void readOnlyToolsHaveNoSummary() {
		Assertions.assertNull(ActionSummary.of("get_status", args()));
		Assertions.assertNull(ActionSummary.of("get_screenshot", args()));
		Assertions.assertNull(ActionSummary.of("find_entities", args()));
		Assertions.assertNull(ActionSummary.of("list_actions", args()));
		Assertions.assertNull(ActionSummary.of("wait_for", args()));
	}

	@Test
	void getAccountIsShownWithoutItsArguments() {
		Assertions.assertEquals("get_account", ActionSummary.of("get_account", args("name", "bob")));
	}

	@Test
	void loginShowsTheNameAndNeverThePassword() {
		String summary = ActionSummary.of("login",
			args("username", "bob", "password", "k3j9q0z8m1x7c4v2b6n5"));

		Assertions.assertEquals("login bob", summary);
		Assertions.assertFalse(summary.contains("k3j9q"), summary);
	}

	@Test
	void aTokenInsideTextIsRedacted() {
		Assertions.assertEquals("type_text \"my token is •••• ok\"",
			ActionSummary.of("type_text", args("text", "my token is k3j9q0z8m1x7c4v2b6n5 ok")));
	}

	@Test
	void coordinatesAndTargetsAreFormatted() {
		Assertions.assertEquals("walk_to 3222,3218",
			ActionSummary.of("walk_to", args("x", 3222, "y", 3218, "plane", 0)));
		Assertions.assertEquals("do_action Attack npc:1234",
			ActionSummary.of("do_action", args("option", "Attack", "target", "npc:1234")));
		Assertions.assertEquals("type_text \"hello\"",
			ActionSummary.of("type_text", args("text", "hello")));
		Assertions.assertEquals("press_key enter",
			ActionSummary.of("press_key", args("key", "enter")));
	}

	@Test
	void longSummariesAreTruncated() {
		String summary = ActionSummary.of("type_text", args("text", "word ".repeat(30)));

		Assertions.assertEquals(ActionSummary.MAX_LENGTH, summary.length());
		Assertions.assertTrue(summary.endsWith("…"), summary);
	}

	private static JsonObject args(Object... pairs) {
		JsonObject args = new JsonObject();
		for (int i = 0; i + 1 < pairs.length; i += 2) {
			String name = String.valueOf(pairs[i]);
			Object value = pairs[i + 1];
			if (value instanceof Number) {
				args.addProperty(name, (Number) value);
			} else {
				args.addProperty(name, String.valueOf(value));
			}
		}
		return args;
	}
}
