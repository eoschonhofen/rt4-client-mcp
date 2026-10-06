package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.mcp.EntityFilter;
import rt4.mcp.GameThread;
import rt4.mcp.SceneScan;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

import java.util.List;

/**
 * MCP-07 — {@code find_entities}: discover NPCs, players, locs and ground items in the
 * loaded scene, by name, op and distance. Results are sorted nearest-first and carry the
 * target ids the action tools accept.
 */
public final class FindEntities {
	private FindEntities() {
	}

	public static void register(ToolRegistry registry) {
		com.google.gson.JsonObject schema = Tools.obj();
		Tools.prop(schema, "type", Tools.withDefault(Tools.stringEnum(
				"Entity kind to search.", "npc", "player", "loc", "obj", "any"), "any"));
		Tools.prop(schema, "name", Tools.string("Case-insensitive name filter, e.g. 'Banker' or 'door'."));
		Tools.prop(schema, "match", Tools.withDefault(Tools.stringEnum(
				"How 'name' is matched.", "contains", "exact"), "contains"));
		Tools.prop(schema, "radius", Tools.withDefault(Tools.integer(
				"Chebyshev distance from you in tiles, 0-52."), EntityFilter.DEFAULT_RADIUS));
		Tools.prop(schema, "has_op", Tools.string(
				"Only entities that offer this op, e.g. 'Bank', 'Open', 'Attack', 'Take'."));
		Tools.prop(schema, "limit", Tools.withDefault(Tools.integer(
				"Maximum results, 1-100."), EntityFilter.DEFAULT_LIMIT));

		registry.register(Tools.gameTool("find_entities",
				"Find NPCs, players, locs (objects) and ground items around you. Filter by name, "
						+ "kind, distance and available op. Returns { entities: [...], count: n } sorted "
						+ "nearest-first; each entity is { target, type, id, name, x, y, plane, distance, "
						+ "ops, extra } and 'target' is the id to pass to list_actions/do_action/interact. "
						+ "Coordinates are world coordinates.",
				schema,
				args -> {
					GameThread.requireLoggedIn();

					String type = Tools.optString(args, "type", "any");
					String name = Tools.optString(args, "name", null);
					String match = Tools.optString(args, "match", "contains");
					String hasOp = Tools.optString(args, "has_op", null);
					int radius = EntityFilter.clampRadius(Tools.optInt(args, "radius", EntityFilter.DEFAULT_RADIUS));
					int limit = EntityFilter.clampLimit(Tools.optInt(args, "limit", EntityFilter.DEFAULT_LIMIT));

					List<EntityFilter.Entity> candidates = SceneScan.scan(type, radius);
					List<EntityFilter.Entity> selected =
							EntityFilter.select(candidates, type, name, match, radius, hasOp, limit);

					JsonArray result = new JsonArray();
					for (EntityFilter.Entity entity : selected) {
						result.add(entity.toJson());
					}
					JsonObject out = new JsonObject();
					out.add("entities", result);
					out.addProperty("count", result.size());
					return ToolResult.json(out);
				}));
	}

	/** Shared with {@code interact}: the nearest entity matching a name and op. */
	public static EntityFilter.Entity nearest(String type, String name, String match, int radius, String hasOp) {
		List<EntityFilter.Entity> candidates = SceneScan.scan(type, radius);
		List<EntityFilter.Entity> selected =
				EntityFilter.select(candidates, type, name, match, radius, hasOp, 1);
		return selected.isEmpty() ? null : selected.get(0);
	}
}
