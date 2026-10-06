package rt4.mcp;

/** MCP-05 — {@code loc:<locId>@<x>,<y>,<plane>} in world coordinates. */
public final class LocTarget extends Target {
	public final int id;
	public final int x;
	public final int y;
	public final int plane;

	public LocTarget(int id, int x, int y, int plane) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.plane = plane;
	}

	@Override
	public String kind() {
		return "loc";
	}

	@Override
	public String format() {
		return Targets.loc(id, x, y, plane);
	}

	/** The base loc id, which is what the packets carry. */
	public int baseId() {
		return id;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof LocTarget)) {
			return false;
		}
		LocTarget that = (LocTarget) other;
		return id == that.id && x == that.x && y == that.y && plane == that.plane;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + x;
		result = 31 * result + y;
		result = 31 * result + plane;
		return result;
	}
}
