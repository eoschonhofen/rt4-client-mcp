package rt4.mcp.nav;

import com.google.gson.JsonObject;
import rt4.mcp.Coords;
import rt4.mcp.EntityFilter;
import rt4.mcp.SceneScan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MCP-13 — the doors and gates in the loaded scene that offer an "Open" op, indexed by
 * scene tile. Built from the same scan {@code find_entities} uses, so the op strings match
 * exactly what {@code do_action} accepts.
 */
public final class DoorIndex {
	public static final class Door {
		public final String target;
		public final int sceneX;
		public final int sceneY;
		public final int plane;
		/** MCP-18 — the tile edges the door stands on ({@link AStar#wallEdges}). */
		public final int edges;

		Door(String target, int sceneX, int sceneY, int plane) {
			this(target, sceneX, sceneY, plane, AStar.EDGE_ANY);
		}

		Door(String target, int sceneX, int sceneY, int plane, int edges) {
			this.target = target;
			this.sceneX = sceneX;
			this.sceneY = sceneY;
			this.plane = plane;
			this.edges = edges;
		}
	}

	private DoorIndex() {
	}

	/** Doors within {@code radius} of the player, keyed by {@link AStar#key}. */
	public static Map<Integer, Door> doors(int radius) {
		Map<Integer, Door> doors = new HashMap<Integer, Door>();
		List<EntityFilter.Entity> locs = SceneScan.locs(radius);
		for (EntityFilter.Entity loc : locs) {
			if (!loc.hasOp("Open")) {
				continue;
			}
			int sceneX = Coords.sceneX(loc.x);
			int sceneY = Coords.sceneY(loc.y);
			if (!Coords.inScene(sceneX, sceneY)) {
				continue;
			}
			doors.put(AStar.key(sceneX, sceneY), new Door(loc.target, sceneX, sceneY, loc.plane, edgesOf(loc)));
		}
		return doors;
	}

	/** Each door tile's edges, keyed by {@link AStar#key}, for {@link AStar#findThroughDoors}. */
	public static Map<Integer, Integer> edges(Map<Integer, Door> doors) {
		Map<Integer, Integer> edges = new HashMap<Integer, Integer>();
		for (Map.Entry<Integer, Door> entry : doors.entrySet()) {
			edges.put(entry.getKey(), entry.getValue().edges);
		}
		return edges;
	}

	/** The edges a door loc stands on, from the shape and rotation {@code SceneScan} reports. */
	static int edgesOf(EntityFilter.Entity loc) {
		JsonObject extra = loc.extra;
		if (extra == null || !extra.has("shape") || !extra.has("rotation")) {
			return AStar.EDGE_ANY;
		}
		return AStar.wallEdges(extra.get("shape").getAsInt(), extra.get("rotation").getAsInt());
	}

	public static Set<Integer> tiles(int radius) {
		return new HashSet<Integer>(doors(radius).keySet());
	}

	/** A loc target id for a door tile, or null. */
	public static String targetAt(Map<Integer, Door> doors, int sceneX, int sceneY) {
		Door door = doors.get(AStar.key(sceneX, sceneY));
		return door == null ? null : door.target;
	}
}
