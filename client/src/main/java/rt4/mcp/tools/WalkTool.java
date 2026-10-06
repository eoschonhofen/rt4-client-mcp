package rt4.mcp.tools;

import com.google.gson.JsonObject;
import rt4.Player;
import rt4.mcp.Coords;
import rt4.mcp.GameThread;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;
import rt4.mcp.nav.NavTask;

/**
 * MCP-13 — {@code walk_to}, {@code nav_status} and {@code nav_cancel}.
 */
public final class WalkTool {
	private static final int MAX_RADIUS = 10;

	private WalkTool() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(walkTo());
		registry.register(navStatus());
		registry.register(navCancel());
	}

	private static Tool walkTo() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "x", Tools.integer("Destination world x."));
		Tools.prop(schema, "y", Tools.integer("Destination world y."));
		Tools.prop(schema, "plane", Tools.integer("Destination plane; must be the one you are on."));
		Tools.prop(schema, "radius", Tools.withDefault(Tools.integer("How close counts as arrived."), 0));
		Tools.require(schema, "x", "y");

		return Tools.gameTool("walk_to",
				"Start walking to a world tile on your current plane. This returns immediately with a "
						+ "task id; the walk runs in the background, opening doors and re-planning as "
						+ "regions load. Watch it with wait_for(nav_done) or nav_status. Stairs, ladders, "
						+ "shortcuts and teleports are out of scope and fail with a reason. A real mouse "
						+ "click cancels the walk.",
				schema,
				args -> {
					GameThread.requireLoggedIn();

					int x = Tools.getInt(args, "x");
					int y = Tools.getInt(args, "y");
					int radius = Tools.clamp(Tools.optInt(args, "radius", 0), 0, MAX_RADIUS);

					if (Tools.has(args, "plane")) {
						int plane = Tools.getInt(args, "plane");
						if (plane != Player.plane) {
							throw new ToolException("walk_to cannot change plane: you are on plane " + Player.plane
									+ ", plane " + plane + " needs stairs, a ladder or a shortcut");
						}
					}

					int sceneX = Coords.sceneX(x);
					int sceneY = Coords.sceneY(y);
					if (!Coords.inScene(sceneX, sceneY)) {
						throw new ToolException("out of loaded scene: tile:" + x + "," + y
								+ " is not in the 104x104 scene around you");
					}

					NavTask task = NavTask.start(sceneX, sceneY, Player.plane, radius);

					JsonObject out = new JsonObject();
					out.addProperty("task", task.id());
					out.addProperty("state", task.state().name());
					out.addProperty("est_tiles", task.estimatedTiles());
					out.addProperty("plane", task.goalPlane());
					return ToolResult.json(out);
				});
	}

	private static Tool navStatus() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "task", Tools.integer("Optional task id to check; omit for the active task."));

		return Tools.gameTool("nav_status",
				"The state of the background walk: task id, state (PLANNING/WALKING/OPENING_DOOR/"
						+ "ARRIVED/FAILED/CANCELLED), reason on failure, your position, the goal, tiles "
						+ "remaining, legs done and doors opened.",
				schema,
				args -> {
					JsonObject status = NavTask.statusJson();
					if (Tools.has(args, "task")) {
						int requested = Tools.getInt(args, "task");
						if (status.get("task").getAsInt() != requested) {
							throw new ToolException("task " + requested + " is not the active task (active: "
									+ status.get("task").getAsInt() + ")");
						}
					}
					return ToolResult.json(status);
				});
	}

	private static Tool navCancel() {
		return Tools.gameTool("nav_cancel",
				"Stop the background walk immediately.",
				Tools.obj(),
				args -> {
					NavTask.cancel("cancelled by nav_cancel");
					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					return ToolResult.json(out);
				});
	}
}
