package rt4.mcp;

/**
 * MCP-05 — {@code tile:<x>,<y>[,<plane>]}, a walk target.
 * A plane of {@code -1} means "the player's current plane" and is resolved on the game thread.
 */
public final class TileTarget extends Target {
	public final int x;
	public final int y;
	public final int plane;

	public TileTarget(int x, int y, int plane) {
		this.x = x;
		this.y = y;
		this.plane = plane;
	}

	@Override
	public String kind() {
		return "tile";
	}

	public boolean hasPlane() {
		return plane >= 0;
	}

	/** The plane to use, given the player's current plane. */
	public int planeOr(int currentPlane) {
		return plane >= 0 ? plane : currentPlane;
	}

	@Override
	public String format() {
		return plane >= 0 ? Targets.tile(x, y, plane) : Targets.tile(x, y);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof TileTarget)) {
			return false;
		}
		TileTarget that = (TileTarget) other;
		return x == that.x && y == that.y && plane == that.plane;
	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		result = 31 * result + plane;
		return result;
	}
}
