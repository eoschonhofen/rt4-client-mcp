package rt4.mcp;

/**
 * MCP-05 — a parsed target id. Every id the state and find tools emit round-trips
 * through {@link Targets#parse}.
 */
public abstract class Target {
	/** The id prefix: {@code npc}, {@code player}, {@code loc}, {@code obj}, {@code tile} or {@code if}. */
	public abstract String kind();

	/** The canonical string form, as the agent sees it. */
	public abstract String format();

	@Override
	public final String toString() {
		return format();
	}

	@Override
	public abstract boolean equals(Object other);

	@Override
	public abstract int hashCode();
}
