package rt4.mcp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * MCP-03 — insertion-ordered tool table. Tools are registered from {@link McpServer#start}.
 */
public final class ToolRegistry {
	private final Map<String, Tool> tools = new LinkedHashMap<String, Tool>();

	public ToolRegistry register(Tool tool) {
		if (tools.put(tool.name(), tool) != null) {
			throw new IllegalStateException("duplicate tool name: " + tool.name());
		}
		return this;
	}

	public Tool get(String name) {
		return tools.get(name);
	}

	public boolean contains(String name) {
		return tools.containsKey(name);
	}

	public int size() {
		return tools.size();
	}

	public List<Tool> tools() {
		return Collections.unmodifiableList(new ArrayList<Tool>(tools.values()));
	}
}
