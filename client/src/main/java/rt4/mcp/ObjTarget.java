package rt4.mcp;

/** MCP-05 — {@code obj:<objId>@<x>,<y>,<plane>}, a ground item, in world coordinates. */
public final class ObjTarget extends Target {
	public final int id;
	public final int x;
	public final int y;
	public final int plane;

	public ObjTarget(int id, int x, int y, int plane) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.plane = plane;
	}

	@Override
	public String kind() {
		return "obj";
	}

	@Override
	public String format() {
		return Targets.obj(id, x, y, plane);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ObjTarget)) {
			return false;
		}
		ObjTarget that = (ObjTarget) other;
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
