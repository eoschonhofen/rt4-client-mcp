package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rt4.mcp.Dialogue;
import rt4.mcp.GameThread;
import rt4.mcp.MenuSynth;
import rt4.mcp.Targets;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** MCP-25 — {@code continue_dialogue} stops at its overall deadline. */
class HelperToolsTest {
	@BeforeEach
	void ownGameThread() {
		GameThread.drain(); // this thread becomes the owner, so game calls run inline
	}

	@AfterEach
	void cleanup() {
		HelperTools.deadlineMs = HelperTools.DEADLINE_MS;
		HelperTools.dialogueSource = Dialogue::current;
		HelperTools.clicker = (target, op) -> MenuSynth.act(Targets.parse(target), op, null);
		GameThread.reset();
	}

	private static Tool continueDialogue() {
		ToolRegistry registry = new ToolRegistry();
		HelperTools.register(registry);
		return registry.get("continue_dialogue");
	}

	private static JsonObject npcLine(int n) {
		JsonObject dialogue = new JsonObject();
		dialogue.addProperty("kind", "npc");
		JsonArray lines = new JsonArray();
		lines.add("line " + n);
		dialogue.add("lines", lines);
		JsonObject cont = new JsonObject();
		cont.addProperty("target", "if:241:5");
		cont.addProperty("op", "Continue");
		dialogue.add("continue", cont);
		return dialogue;
	}

	@Test
	void aLongDialogueStopsAtTheDeadline() throws Exception {
		AtomicInteger page = new AtomicInteger();
		HelperTools.deadlineMs = 150L;
		HelperTools.dialogueSource = () -> npcLine(page.get());
		HelperTools.clicker = (target, op) -> {
			Thread.sleep(20L);
			page.incrementAndGet();
		};

		JsonObject args = JsonParser.parseString("{\"max_steps\":50}").getAsJsonObject();
		JsonObject out = continueDialogue().call(args).toJson().getAsJsonObject("structuredContent");

		assertEquals("deadline", out.get("stopped").getAsString());
		assertTrue(page.get() < 50, "it must stop before max_steps, after " + page.get() + " clicks");
	}

	@Test
	void aStuckStepReportsTheDeadlineNotNoChange() throws Exception {
		HelperTools.deadlineMs = 100L;
		HelperTools.dialogueSource = () -> npcLine(0); // never changes
		HelperTools.clicker = (target, op) -> {
		};

		long start = System.currentTimeMillis();
		JsonObject out = continueDialogue().call(new JsonObject()).toJson().getAsJsonObject("structuredContent");
		long millis = System.currentTimeMillis() - start;

		assertEquals("deadline", out.get("stopped").getAsString());
		assertTrue(millis < 2000L, "the per-step wait must not outlast the deadline, took " + millis + "ms");
	}
}
