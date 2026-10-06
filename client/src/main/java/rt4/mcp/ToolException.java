package rt4.mcp;

/**
 * MCP-03 — thrown by a tool when the agent's request cannot be carried out
 * (target gone, not logged in, bad argument, ...). The message is surfaced to the
 * agent as a tool error result, not as a JSON-RPC error.
 */
public class ToolException extends Exception {
	private static final long serialVersionUID = 1L;

	public ToolException(String message) {
		super(message);
	}

	public ToolException(String message, Throwable cause) {
		super(message, cause);
	}
}
