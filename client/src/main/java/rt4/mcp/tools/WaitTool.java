package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import rt4.mcp.Conditions;
import rt4.mcp.GameThread;
import rt4.mcp.TickTracker;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;
import rt4.mcp.Waiters;

import java.util.ArrayList;
import java.util.List;

/**
 * MCP-12 — {@code wait_for}: block until a condition holds instead of polling in a loop.
 */
public final class WaitTool {
	private WaitTool() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(waitFor());
	}

	private static Tool waitFor() {
		JsonObject conditionSchema = Tools.obj();
		Tools.prop(conditionSchema, "condition", Tools.string(
				"idle, ticks, logged_in, logged_out, position, dialogue_open, interface_open, interface_closed, "
						+ "inventory_changed, item_count, chat_matches, skill_xp_changed, hp_below, npc_gone, "
						+ "nav_done or animation."));
		Tools.prop(conditionSchema, "for_ticks", Tools.integer("idle: how many consecutive ticks."));
		Tools.prop(conditionSchema, "n", Tools.integer("ticks/item_count/hp_below: the number."));
		Tools.prop(conditionSchema, "id", Tools.integer("interface_open/interface_closed/item_count/animation: the id."));
		Tools.prop(conditionSchema, "x", Tools.integer("position: world x."));
		Tools.prop(conditionSchema, "y", Tools.integer("position: world y."));
		Tools.prop(conditionSchema, "plane", Tools.integer("position: plane."));
		Tools.prop(conditionSchema, "radius", Tools.integer("position: allowed Chebyshev distance."));
		Tools.prop(conditionSchema, "op", Tools.stringEnum("item_count: comparison.", ">=", "<=", "=="));
		Tools.prop(conditionSchema, "regex", Tools.string("chat_matches: Java regular expression."));
		Tools.prop(conditionSchema, "types", Tools.array("chat_matches: only these chat type ids.", Tools.integer(null)));
		Tools.prop(conditionSchema, "skill", Tools.string("skill_xp_changed: a skill name, or omit for any."));
		Tools.prop(conditionSchema, "target", Tools.string("npc_gone: the target id."));
		Tools.prop(conditionSchema, "task", Tools.integer("nav_done: the nav task id, or omit for any."));

		JsonObject schema = Tools.obj();
		Tools.prop(schema, "conditions", Tools.array("The conditions to test.", conditionSchema));
		Tools.prop(schema, "mode", Tools.withDefault(Tools.stringEnum(
				"any = return when one holds, all = when every one does.", "any", "all"), "any"));
		Tools.prop(schema, "timeout_ms", Tools.withDefault(Tools.integer(
				"How long to wait before giving up, capped at 60000."), 10000));
		Tools.require(schema, "conditions");

		return Tools.tool("wait_for",
				"Block until a condition holds (or the timeout expires). A timeout is not an error: it "
						+ "returns met=false. Conditions are validated before waiting. Use this instead of "
						+ "polling after do_action, e.g. wait_for([inventory_changed, idle(for_ticks=3)], 'any').",
				schema,
				args -> {
					JsonArray raw = Tools.optArray(args, "conditions");
					if (raw == null) {
						throw new ToolException("'conditions' must be an array");
					}
					List<JsonObject> objects = new ArrayList<JsonObject>();
					for (JsonElement element : raw) {
						if (!element.isJsonObject()) {
							throw new ToolException("each condition must be an object");
						}
						objects.add(element.getAsJsonObject());
					}
					// Validate on the HTTP thread so bad input never reaches the game thread.
					final List<Conditions.Condition> conditions = Conditions.parseAll(objects);
					final Conditions.Mode mode = Conditions.parseMode(Tools.optString(args, "mode", "any"));
					final int timeoutMs = Conditions.clampTimeout(Tools.optInt(args, "timeout_ms", 10000));

					// Snapshot and register on the game thread, then block here.
					final Waiters.Waiter waiter = GameThread.call(() -> Waiters.register(conditions, mode));
					Waiters.Outcome outcome = Waiters.await(waiter, timeoutMs);

					JsonObject json = outcome.toJson();
					if (!json.has("status")) {
						json.add("status", Waiters.status());
					}
					if (TickTracker.APPROXIMATE) {
						json.addProperty("ticks_approximate", true);
					}
					return ToolResult.json(json);
				});
	}
}
