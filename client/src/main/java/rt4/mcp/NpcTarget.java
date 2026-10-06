package rt4.mcp;

/** MCP-05 — {@code npc:<index>}, a slot in {@code NpcList.npcs}. */
public final class NpcTarget extends Target {
	public final int index;

	public NpcTarget(int index) {
		this.index = index;
	}

	@Override
	public String kind() {
		return "npc";
	}

	@Override
	public String format() {
		return Targets.npc(index);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof NpcTarget && ((NpcTarget) other).index == index;
	}

	@Override
	public int hashCode() {
		return 31 * 1 + index;
	}
}
