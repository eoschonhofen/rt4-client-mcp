package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.mcp.EntryMatcher;
import rt4.mcp.GameThread;
import rt4.mcp.MenuSynth;
import rt4.mcp.Target;
import rt4.mcp.Targets;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

import java.util.List;

/**
 * MCP-08 — {@code list_actions}, {@code do_action} and {@code cancel_selection}.
 *
 * <p>Actions are built from scene data, not from hovering, so they work on entities that
 * are off screen. The packet is queued through the real {@code MiniMenu.doAction}, so it is
 * byte-identical to a human right-click.</p>
 */
public final class ActionTools {
	public static final String TARGET_DESCRIPTION =
			"Target id: npc:<index>, player:<index>, loc:<locId>@<x>,<y>,<plane>, "
					+ "obj:<objId>@<x>,<y>,<plane>, tile:<x>,<y>[,<plane>] or if:<interfaceId>:<childId>[:<slot>]. "
					+ "Coordinates are world coordinates.";

	private ActionTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(listActions());
		registry.register(doAction());
		registry.register(cancelSelection());
	}

	private static Tool listActions() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "target", Tools.string(TARGET_DESCRIPTION));
		Tools.require(schema, "target");

		return Tools.gameTool("list_actions",
				"The minimenu entries the UI would show for a target, exactly as a right-click "
						+ "would build them. The first entry is the left-click default. Use the returned "
						+ "'op' (and 'subject' when two entries share an op) with do_action.",
				schema,
				args -> {
					GameThread.requireLoggedIn();
					Target target = Targets.parse(Tools.getString(args, "target"));
					List<EntryMatcher.Entry> entries = MenuSynth.list(target);

					JsonArray actions = new JsonArray();
					for (EntryMatcher.Entry entry : entries) {
						JsonObject action = new JsonObject();
						action.addProperty("op", entry.op);
						action.add("subject", Tools.text(entry.subject));
						actions.add(action);
					}

					JsonObject out = new JsonObject();
					out.addProperty("target", target.format());
					out.add("actions", actions);
					if (entries.isEmpty()) {
						out.addProperty("note", "no actions; the target may have gone");
					}
					String hint = MenuSynth.selectionHint();
					if (hint != null) {
						out.addProperty("cancel_hint", hint);
					}
					return ToolResult.json(out);
				});
	}

	private static Tool doAction() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "target", Tools.string(TARGET_DESCRIPTION));
		Tools.prop(schema, "op", Tools.string("The op to run, e.g. 'Talk-to', 'Open', 'Attack', 'Bank', 'Wield'."));
		Tools.prop(schema, "subject", Tools.string(
				"Optional subject to disambiguate entries with the same op, e.g. 'Logs'."));
		Tools.require(schema, "target", "op");

		return Tools.gameTool("do_action",
				"Run one minimenu action on a target. The packet is queued exactly like a human "
						+ "right-click; 'ok' only means it was queued, not that the server accepted it. "
						+ "Follow it with wait_for(...) and a state read. Cancel an open selection first if "
						+ "you do not mean to consume it.",
				schema,
				args -> {
					GameThread.requireLoggedIn();
					Target target = Targets.parse(Tools.getString(args, "target"));
					String op = Tools.getString(args, "op");
					String subject = Tools.optString(args, "subject", null);

					MenuSynth.Ack ack = MenuSynth.act(target, op, subject);

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("op", ack.op);
					out.add("subject", Tools.text(ack.subject));
					out.addProperty("target", ack.target);
					out.addProperty("tick", ack.tick);
					return ToolResult.json(out);
				});
	}

	private static Tool cancelSelection() {
		return Tools.gameTool("cancel_selection",
				"Clear a pending 'Use item' selection or a selected spell so the next do_action does "
						+ "not consume it.",
				Tools.obj(),
				args -> {
					MenuSynth.cancelSelection();
					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					return ToolResult.json(out);
				});
	}
}
