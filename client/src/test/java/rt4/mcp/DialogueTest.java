package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DialogueTest {
	/** A fake dialogue component. */
	private static final class View implements InterfaceWalker.ComponentView {
		private final String target;
		private final String text;
		private final List<String> options;
		private final String continueOp;

		View(String target, String text, List<String> options, String continueOp) {
			this.target = target;
			this.text = text;
			this.options = options;
			this.continueOp = continueOp;
		}

		static View line(String target, String text) {
			return new View(target, text, Collections.<String>emptyList(), null);
		}

		static View options(String target, String... options) {
			return new View(target, null, Arrays.asList(options), null);
		}

		static View button(String target, String op) {
			return new View(target, null, Collections.<String>emptyList(), op);
		}

		@Override
		public boolean hidden() {
			return false;
		}

		@Override
		public boolean inventory() {
			return false;
		}

		@Override
		public boolean hasText() {
			return text != null;
		}

		@Override
		public boolean hasOps() {
			return !options.isEmpty();
		}

		@Override
		public int buttonType() {
			return 0;
		}

		@Override
		public boolean hasItem() {
			return false;
		}

		@Override
		public int itemCount() {
			return 0;
		}

		@Override
		public List<InterfaceWalker.ComponentView> children() {
			return Collections.emptyList();
		}

		@Override
		public String targetId() {
			return target;
		}

		@Override
		public String textValue() {
			return text;
		}

		@Override
		public List<String> optionTexts() {
			return options;
		}

		@Override
		public String continueOp() {
			return continueOp;
		}
	}

	@Test
	void kindsFollowThe530InterfaceSets() {
		assertEquals("npc", Dialogue.kindOf(241));
		assertEquals("npc", Dialogue.kindOf(244));
		assertEquals("player", Dialogue.kindOf(64));
		assertEquals("player", Dialogue.kindOf(67));
		assertEquals("options", Dialogue.kindOf(228));
		assertEquals("options", Dialogue.kindOf(234));
		assertEquals("message", Dialogue.kindOf(210));
		assertEquals("message", Dialogue.kindOf(519));
	}

	@Test
	void npcDialogueCollectsLinesAndTheContinueTarget() {
		JsonObject dialogue = Dialogue.classify(241, Arrays.<InterfaceWalker.ComponentView>asList(
				View.line("if:241:1", "Hello, adventurer."),
				View.line("if:241:2", "How can I help?"),
				View.button("if:241:5", "Continue")));

		assertEquals("npc", dialogue.get("kind").getAsString());
		assertEquals(241, dialogue.get("interface").getAsInt());
		JsonArray lines = dialogue.getAsJsonArray("lines");
		assertEquals(2, lines.size());
		assertEquals("Hello, adventurer.", lines.get(0).getAsString());
		JsonObject cont = dialogue.getAsJsonObject("continue");
		assertEquals("if:241:5", cont.get("target").getAsString());
		assertEquals("Continue", cont.get("op").getAsString());
	}

	@Test
	void playerDialogueIsClassifiedSeparately() {
		JsonObject dialogue = Dialogue.classify(64, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.line("if:64:1", "I am you.")));
		assertEquals("player", dialogue.get("kind").getAsString());
	}

	@Test
	void optionMenuExtractsOneBasedOptions() {
		JsonObject dialogue = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "Where can I find a quest?", "Tell me about yourself.", "Goodbye.")));

		assertEquals("options", dialogue.get("kind").getAsString());
		JsonArray options = dialogue.getAsJsonArray("options");
		assertEquals(3, options.size());
		assertEquals(1, options.get(0).getAsJsonObject().get("index").getAsInt());
		assertEquals("Where can I find a quest?", options.get(0).getAsJsonObject().get("text").getAsString());
		assertEquals("if:230:1", options.get(0).getAsJsonObject().get("target").getAsString());
		assertEquals(3, options.get(2).getAsJsonObject().get("index").getAsInt());
	}

	@Test
	void optionsFromANonOptionInterfaceStillSwitchTheKind() {
		JsonObject dialogue = Dialogue.classify(241, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:241:1", "Yes", "No")));
		assertEquals("options", dialogue.get("kind").getAsString());
	}

	@Test
	void plainMessageHasNoOptions() {
		JsonObject dialogue = Dialogue.classify(210, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.line("if:210:1", "Click here to continue")));
		assertEquals("message", dialogue.get("kind").getAsString());
		assertFalse(dialogue.has("options"));
		assertFalse(dialogue.has("continue"));
		assertTrue(dialogue.has("lines"));
	}

	@Test
	void blankTextIsIgnored() {
		JsonObject dialogue = Dialogue.classify(241, Arrays.<InterfaceWalker.ComponentView>asList(
				View.line("if:241:1", "  "),
				View.line("if:241:2", "Real line")));
		assertEquals(1, dialogue.getAsJsonArray("lines").size());
	}

	@Test
	void findOptionBySubstringIsCaseInsensitive() throws Exception {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "Where can I find a quest?", "Goodbye."))).getAsJsonArray("options");

		JsonObject match = Dialogue.findOption(options, "quest", null);
		assertEquals("Where can I find a quest?", match.get("text").getAsString());
		assertEquals(1, match.get("index").getAsInt());
	}

	@Test
	void findOptionByIndex() throws Exception {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "One", "Two", "Three"))).getAsJsonArray("options");

		assertEquals("Three", Dialogue.findOption(options, null, 3).get("text").getAsString());
	}

	@Test
	void findOptionRejectsAnOutOfRangeIndex() {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "One", "Two"))).getAsJsonArray("options");

		ToolException error = assertThrows(ToolException.class, () -> Dialogue.findOption(options, null, 5));
		assertTrue(error.getMessage().contains("out of range"), error.getMessage());
		assertTrue(error.getMessage().contains("1=One"), error.getMessage());
	}

	@Test
	void findOptionReportsAmbiguity() {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "Tell me about quests", "About the quest"))).getAsJsonArray("options");

		ToolException error = assertThrows(ToolException.class, () -> Dialogue.findOption(options, "quest", null));
		assertTrue(error.getMessage().contains("matches 2 options"), error.getMessage());
		assertTrue(error.getMessage().contains("pass an index"), error.getMessage());
	}

	@Test
	void findOptionReportsNoMatch() {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "One", "Two"))).getAsJsonArray("options");

		ToolException error = assertThrows(ToolException.class, () -> Dialogue.findOption(options, "nine", null));
		assertTrue(error.getMessage().contains("no option matches 'nine'"), error.getMessage());
		assertTrue(error.getMessage().contains("2=Two"), error.getMessage());
	}

	@Test
	void findOptionWithNoOptionsIsClear() {
		ToolException error = assertThrows(ToolException.class,
				() -> Dialogue.findOption(new JsonArray(), "x", null));
		assertTrue(error.getMessage().contains("no option menu is open"), error.getMessage());
	}

	@Test
	void findOptionWithNeitherTextNorIndexExplainsItself() {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "One"))).getAsJsonArray("options");

		ToolException error = assertThrows(ToolException.class, () -> Dialogue.findOption(options, null, null));
		assertTrue(error.getMessage().contains("give an option text or an index"), error.getMessage());
	}

	@Test
	void optionTextsAreNumbered() {
		JsonArray options = Dialogue.classify(230, Collections.<InterfaceWalker.ComponentView>singletonList(
				View.options("if:230:1", "One", "Two"))).getAsJsonArray("options");
		assertEquals("[1=One, 2=Two]", Dialogue.optionTexts(options));
	}

	@Test
	void dialogueInterfacesCoverEveryFamily() {
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(
				241, 242, 243, 244, 64, 65, 66, 67, 228, 230, 232, 234, 210, 211, 212, 213, 214, 519));
		assertTrue(Dialogue.dialogueInterfaces().containsAll(expected));
		assertNull(null);
	}
}
