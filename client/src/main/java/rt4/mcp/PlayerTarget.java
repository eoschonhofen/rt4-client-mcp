package rt4.mcp;

/** MCP-05 — {@code player:<index>}, a slot in {@code PlayerList.players}. */
public final class PlayerTarget extends Target {
	public final int index;

	public PlayerTarget(int index) {
		this.index = index;
	}

	@Override
	public String kind() {
		return "player";
	}

	@Override
	public String format() {
		return Targets.player(index);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof PlayerTarget && ((PlayerTarget) other).index == index;
	}

	@Override
	public int hashCode() {
		return 31 * 2 + index;
	}
}
