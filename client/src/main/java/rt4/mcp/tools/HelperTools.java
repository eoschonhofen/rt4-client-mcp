package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.Component;
import rt4.mcp.Dialogue;
import rt4.mcp.EntityFilter;
import java.util.List;
import rt4.mcp.GameThread;
import rt4.mcp.MenuSynth;
import rt4.mcp.SceneScan;
import rt4.mcp.Targets;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

import java.util.Locale;
import java.util.concurrent.Callable;

/**
 * MCP-14 — {@code interact}, {@code continue_dialogue} and {@code choose_option}: the
 * conveniences that cut agent round trips out of primitives from MCP-07/08/09.
 */
public final class HelperTools {
	private static final int POLL_MILLIS = 20;
	private static final int CHANGE_TIMEOUT_MS = 3000;

	private HelperTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(interact());
		registry.register(continueDialogue());
		registry.register(chooseOption());
	}

	// ------------------------------------------------------------------ interact

	private static Tool interact() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "name", Tools.string("Entity or item name, case-insensitive substring."));
		Tools.prop(schema, "op", Tools.string("The op to run, e.g. 'Bank', 'Talk-to', 'Bury', 'Wield'."));
		Tools.prop(schema, "type", Tools.withDefault(Tools.stringEnum(
				"Where to look. 'inv' forces the backpack.", "any", "npc", "player", "loc", "obj", "inv"), "any"));
		Tools.prop(schema, "radius", Tools.withDefault(Tools.integer("Search radius in tiles."), EntityFilter.DEFAULT_RADIUS));
		Tools.require(schema, "name", "op");

		return Tools.gameTool("interact",
				"Find the nearest thing with a given name and run one op on it: find_entities + "
						+ "do_action in one call. Falls back to the backpack when type is 'inv', or when "
						+ "type is 'any' and nothing in the world matches. Returns the target and distance.",
				schema,
				args -> {
					GameThread.requireLoggedIn();

					String name = Tools.getString(args, "name");
					String op = Tools.getString(args, "op");
					String type = Tools.optString(args, "type", "any");
					int radius = EntityFilter.clampRadius(Tools.optInt(args, "radius", EntityFilter.DEFAULT_RADIUS));

					if ("inv".equalsIgnoreCase(type)) {
						JsonObject inventory = interactInventory(name, op, radius);
						if (inventory == null) {
							throw new ToolException("no backpack item matching '" + name + "' with op '" + op + "'");
						}
						return ToolResult.json(inventory);
					}

					EntityFilter.Entity found = FindEntities.nearest(type, name, "contains", radius, op);
					if (found != null) {
						final EntityFilter.Entity target = found;
						MenuSynth.Ack ack = MenuSynth.act(Targets.parse(target.target), op, null);
						rt4.mcp.nav.NavTask.cancel("cancelled by interact");

						JsonObject out = new JsonObject();
						out.addProperty("ok", true);
						out.addProperty("target", target.target);
						out.addProperty("name", target.name);
						out.addProperty("op", ack.op);
						out.addProperty("x", target.x);
						out.addProperty("y", target.y);
						out.addProperty("distance", target.distance);
						return ToolResult.json(out);
					}

					if ("any".equalsIgnoreCase(type)) {
						JsonObject inventory = interactInventory(name, op, radius);
						if (inventory != null) {
							return ToolResult.json(inventory);
						}
					}

					throw new ToolException("no '" + name + "' with op '" + op + "' within " + radius
							+ "; nearest names: " + nearestNames(type, radius));
				});
	}

	/** Runs {@code op} on the first backpack item whose name matches. Null when nothing matches. */
	private static JsonObject interactInventory(String name, String op, int radius) throws ToolException {
		Component component = StatusTools.backpackComponent();
		if (component == null) {
			return null;
		}
		JsonArray items = StatusTools.inventoryContents();
		String wanted = name.toLowerCase(Locale.ROOT);
		for (int i = 0; i < items.size(); i++) {
			JsonObject item = items.get(i).getAsJsonObject();
			String itemName = item.has("name") && !item.get("name").isJsonNull() ? item.get("name").getAsString() : "";
			if (!itemName.toLowerCase(Locale.ROOT).contains(wanted)) {
				continue;
			}
			JsonArray ops = item.has("ops") ? item.getAsJsonArray("ops") : new JsonArray();
			String matchedOp = null;
			for (int o = 0; o < ops.size(); o++) {
				if (ops.get(o).getAsString().equalsIgnoreCase(op)) {
					matchedOp = ops.get(o).getAsString();
					break;
				}
			}
			if (matchedOp == null) {
				continue;
			}
			String target = item.get("target").getAsString();
			MenuSynth.Ack ack = MenuSynth.act(Targets.parse(target), matchedOp, null);
			rt4.mcp.nav.NavTask.cancel("cancelled by interact");

			JsonObject out = new JsonObject();
			out.addProperty("ok", true);
			out.addProperty("target", target);
			out.addProperty("name", itemName);
			out.addProperty("op", ack.op);
			out.addProperty("distance", 0);
			out.addProperty("slot", item.get("slot").getAsInt());
			return out;
		}
		return null;
	}

	/** A few nearby names, so a typo is easy to correct. */
	private static JsonArray nearestNames(String type, int radius) {
		List<EntityFilter.Entity> candidates = SceneScan.scan(type, radius);
		List<EntityFilter.Entity> relaxed = EntityFilter.select(candidates, type, null, "contains", radius, null, 12);
		JsonArray names = new JsonArray();
		for (EntityFilter.Entity entity : relaxed) {
			if (entity.name != null && !names.contains(new com.google.gson.JsonPrimitive(entity.name))) {
				names.add(entity.name);
			}
		}
		return names;
	}

	// ------------------------------------------------------------------ continue_dialogue

	private static Tool continueDialogue() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "max_steps", Tools.withDefault(Tools.integer("Stop after this many clicks."), 10));

		return Tools.tool("continue_dialogue",
				"Click 'continue' through a dialogue, collecting the text, and stop at an option menu "
						+ "or when the dialogue closes. Use choose_option next when it stops at options. "
						+ "Returns { transcript, stopped, dialogue }.",
				schema,
				args -> {
					int maxSteps = Tools.clamp(Tools.optInt(args, "max_steps", 10), 1, 50);
					JsonArray transcript = new JsonArray();
					String stopped = "max_steps";
					JsonObject dialogue = null;

					for (int step = 0; step < maxSteps; step++) {
						dialogue = currentDialogue();
						if (dialogue == null) {
							stopped = "closed";
							break;
						}
						appendLines(transcript, dialogue);
						if ("options".equals(dialogue.get("kind").getAsString())) {
							stopped = "options";
							break;
						}
						JsonObject cont = dialogue.has("continue") ? dialogue.getAsJsonObject("continue") : null;
						if (cont == null || cont.get("target") == null || cont.get("target").isJsonNull()) {
							stopped = "no_continue";
							break;
						}
						String before = dialogue.toString();
						final String target = cont.get("target").getAsString();
						final String op = cont.get("op").getAsString();
						click(target, op);
						if (!waitForChange(before)) {
							stopped = "no_change";
							break;
						}
					}

					JsonObject out = new JsonObject();
					out.add("transcript", transcript);
					out.addProperty("stopped", stopped);
					out.add("dialogue", dialogue == null ? com.google.gson.JsonNull.INSTANCE : dialogue);
					return ToolResult.json(out);
				});
	}

	// ------------------------------------------------------------------ choose_option

	private static Tool chooseOption() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "text", Tools.string("Case-insensitive substring of the option text."));
		Tools.prop(schema, "index", Tools.integer("1-based option index, as an alternative to text."));

		return Tools.tool("choose_option",
				"Pick an option from an open dialogue option menu, by text (case-insensitive substring) "
						+ "or by 1-based index. Returns the dialogue that follows.",
				schema,
				args -> {
					JsonObject dialogue = currentDialogue();
					if (dialogue == null || !"options".equals(dialogue.get("kind").getAsString())) {
						throw new ToolException("no option menu is open");
					}
					JsonArray options = dialogue.has("options") ? dialogue.getAsJsonArray("options") : new JsonArray();
					String text = Tools.optString(args, "text", null);
					Integer index = Tools.has(args, "index") ? Tools.getInt(args, "index") : null;
					JsonObject option = Dialogue.findOption(options, text, index);

					String before = dialogue.toString();
					String optionOp = option.has("op") ? option.get("op").getAsString() : option.get("text").getAsString();
					click(option.get("target").getAsString(), optionOp);
					waitForChange(before);

					JsonObject out = new JsonObject();
					out.addProperty("chosen", option.get("text").getAsString());
					JsonObject after = currentDialogue();
					out.add("dialogue", after == null ? com.google.gson.JsonNull.INSTANCE : after);
					return ToolResult.json(out);
				});
	}

	// ------------------------------------------------------------------ shared plumbing

	private static JsonObject currentDialogue() throws ToolException {
		return GameThread.call(new Callable<JsonObject>() {
			@Override
			public JsonObject call() {
				return Dialogue.current();
			}
		});
	}

	private static void click(final String target, final String op) throws ToolException {
		GameThread.call(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				MenuSynth.act(Targets.parse(target), op, null);
				return null;
			}
		});
	}

	/** Polls the dialogue until it changes or closes, up to three seconds. */
	private static boolean waitForChange(String before) throws ToolException {
		long deadline = System.currentTimeMillis() + CHANGE_TIMEOUT_MS;
		while (System.currentTimeMillis() < deadline) {
			JsonObject now = currentDialogue();
			if (now == null || !now.toString().equals(before)) {
				return true;
			}
			try {
				Thread.sleep(POLL_MILLIS);
			} catch (InterruptedException interrupted) {
				Thread.currentThread().interrupt();
				return false;
			}
		}
		return false;
	}

	private static void appendLines(JsonArray transcript, JsonObject dialogue) {
		if (!dialogue.has("lines")) {
			return;
		}
		JsonArray lines = dialogue.getAsJsonArray("lines");
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i).getAsString();
			if (transcript.size() == 0 || !transcript.get(transcript.size() - 1).getAsString().equals(line)) {
				transcript.add(line);
			}
		}
	}
}
