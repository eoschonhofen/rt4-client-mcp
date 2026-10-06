package rt4.aionly;

/**
 * AIO-11 / AIO-14 — which MCP tools only read state.
 *
 * <p>Read-only tools never reset the idle-logout counters and never appear on the spectator
 * overlay: an agent that only watches is treated exactly like a human who only watches.</p>
 */
public final class ToolKinds {
	private ToolKinds() {
	}

	public static boolean isReadOnly(String tool) {
		if (tool == null) {
			return false;
		}
		return tool.startsWith("get_")
			|| "find_entities".equals(tool)
			|| "list_actions".equals(tool)
			|| "wait_for".equals(tool);
	}
}
