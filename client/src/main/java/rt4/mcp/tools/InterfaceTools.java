package rt4.mcp.tools;

import com.google.gson.JsonObject;
import rt4.mcp.GameThread;
import rt4.mcp.InterfaceWalker;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

/**
 * MCP-09 — {@code get_interfaces}: read the open interfaces and everything in them.
 */
public final class InterfaceTools {
	private InterfaceTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(interfaces());
	}

	private static Tool interfaces() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "interface_id", Tools.integer(
				"Only this interface id, e.g. 149 for the backpack or the bank's id. Omit to walk every open interface."));
		Tools.prop(schema, "include_hidden", Tools.withDefault(Tools.bool(
				"Include hidden components and their subtrees."), false));
		Tools.prop(schema, "max_depth", Tools.withDefault(Tools.integer(
				"How deep to walk CS2-created children."), InterfaceWalker.DEFAULT_MAX_DEPTH));

		return Tools.gameTool("get_interfaces",
				"Every open interface (dialogue, bank, shop, tabs, prayer book, spellbook, GE) as "
						+ "{ interfaces: [{ id, parent?, components }] }. Each component has a target id "
						+ "(if:<interface>:<child>, or if:<interface>:<child>:<slot> for an inventory slot), "
						+ "plus text, ops, a button (ok/close/toggle/continue/select/logout), a single obj "
						+ "or its inventory slots. Use the slot targets with do_action, e.g. "
						+ "do_action('if:149:0:3', 'Wield'). Pass interface_id to dump just one interface.",
				schema,
				args -> {
					GameThread.requireLoggedIn();

					Integer only = null;
					if (Tools.has(args, "interface_id")) {
						only = Tools.getInt(args, "interface_id");
					}
					boolean includeHidden = Tools.optBool(args, "include_hidden", false);
					int maxDepth = Tools.optInt(args, "max_depth", InterfaceWalker.DEFAULT_MAX_DEPTH);

					JsonObject out = new JsonObject();
					out.add("interfaces", InterfaceWalker.describe(only, includeHidden, maxDepth));
					return ToolResult.json(out);
				});
	}
}
