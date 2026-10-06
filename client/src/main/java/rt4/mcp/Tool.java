package rt4.mcp;

import com.google.gson.JsonObject;

/**
 * MCP-03 — a tool the agent can call. Adding one is a class plus one register line.
 */
public interface Tool {
	/** Unique name, as it appears in {@code tools/list}. */
	String name();

	/** Written for the LLM: what it does, when to use it and any gotchas. */
	String description();

	/** JSON Schema object describing the arguments. */
	JsonObject inputSchema();

	/** Runs on the game thread when it touches game state. */
	ToolResult call(JsonObject args) throws ToolException;
}
