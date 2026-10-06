package rt4.mcp.nav;

import rt4.CollisionMap;
import rt4.PathFinder;

/**
 * MCP-13 — adapts the client's collision map for the current plane to {@link CollisionSource}.
 * Offsets are zero in this client, so scene coordinates index {@code flags} directly.
 */
public final class SceneCollision implements CollisionSource {
	private final CollisionMap map;

	public SceneCollision(int plane) {
		if (plane < 0 || plane >= PathFinder.collisionMaps.length) {
			map = null;
		} else {
			map = PathFinder.collisionMaps[plane];
		}
	}

	public boolean ready() {
		return map != null && map.flags != null;
	}

	@Override
	public int flags(int x, int y) {
		if (map == null || map.flags == null || x < 0 || y < 0 || x >= map.flags.length || map.flags[x] == null
				|| y >= map.flags[x].length) {
			return 0x100;
		}
		return map.flags[x][y];
	}

	@Override
	public int width() {
		return map == null || map.flags == null ? 0 : map.flags.length;
	}

	@Override
	public int height() {
		return map == null || map.flags == null || map.flags.length == 0 ? 0 : map.flags[0].length;
	}
}
