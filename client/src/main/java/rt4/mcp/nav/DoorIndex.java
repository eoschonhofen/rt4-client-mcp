package rt4.mcp.nav;

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

		Door(String target, int sceneX, int sceneY, int plane) {
			this.target = target;
			this.sceneX = sceneX;
			this.sceneY = sceneY;
			this.plane = plane;
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
			doors.put(AStar.key(sceneX, sceneY), new Door(loc.target, sceneX, sceneY, loc.plane));
		}
		return doors;
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
