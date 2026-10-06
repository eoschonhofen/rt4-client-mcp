package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * MCP-03 — MCP lifecycle and tool dispatch. Stateless apart from the tool registry;
 * sessions live in {@link McpHttpServer}.
 */
public final class McpProtocol {
	public static final String SERVER_NAME = "rt4-client";
	public static final String SERVER_VERSION = "1.0.0";
	public static final String FALLBACK_VERSION = "2025-06-18";

	private static final Set<String> SUPPORTED_VERSIONS = new HashSet<String>(Arrays.asList(
			"2025-11-25",
			"2025-06-18",
			"2025-03-26"
	));

	private static final String INSTRUCTIONS =
			"RT4 game client. All coordinates are world coordinates (x, y, plane). "
					+ "Targets are strings: npc:<index>, player:<index>, "
					+ "loc:<locId>@<x>,<y>,<plane>, obj:<objId>@<x>,<y>,<plane>, "
					+ "tile:<x>,<y>[,<plane>], if:<interfaceId>:<childId>[:<slot>]. "
					+ "Discover targets with find_entities, inspect what they offer with list_actions, "
					+ "then act with do_action. Actions are only acknowledged: the packet is queued, "
					+ "the server has not necessarily accepted it. Follow an action with wait_for(...) "
					+ "and read state (get_status, get_inventory, get_chat) to confirm what happened. "
					+ "To start: get_account -> login(name, token) -> wait_for(logged_in).";

	private final ToolRegistry tools;

	public McpProtocol(ToolRegistry tools) {
		this.tools = tools;
	}

	/**
	 * Dispatches one request. Returns null for notifications and responses, which the
	 * transport answers with 202 and an empty body.
	 */
	public JsonObject handle(JsonRpc.Message message) {
		if (!message.isRequest()) {
			return null;
		}
		switch (message.method) {
			case "initialize":
				return initialize(message.id, message.params);
			case "ping":
				return JsonRpc.result(message.id, new JsonObject());
			case "tools/list":
				return toolsList(message.id);
			case "tools/call":
				return toolsCall(message.id, message.params);
			default:
				return JsonRpc.error(message.id, JsonRpc.METHOD_NOT_FOUND, "unknown method: " + message.method);
		}
	}

	/** {@code initialize} is the only request that creates a session. */
	public static boolean createsSession(String method) {
		return "initialize".equals(method);
	}

	private JsonObject initialize(JsonElement id, JsonObject params) {
		String requested = null;
		if (params != null && params.has("protocolVersion") && params.get("protocolVersion").isJsonPrimitive()) {
			requested = params.get("protocolVersion").getAsString();
		}
		String negotiated = requested != null && SUPPORTED_VERSIONS.contains(requested) ? requested : FALLBACK_VERSION;

		JsonObject toolsCapability = new JsonObject();
		toolsCapability.addProperty("listChanged", false);

		JsonObject capabilities = new JsonObject();
		capabilities.add("tools", toolsCapability);

		JsonObject serverInfo = new JsonObject();
		serverInfo.addProperty("name", SERVER_NAME);
		serverInfo.addProperty("version", SERVER_VERSION);

		JsonObject result = new JsonObject();
		result.addProperty("protocolVersion", negotiated);
		result.add("capabilities", capabilities);
		result.add("serverInfo", serverInfo);
		result.addProperty("instructions", INSTRUCTIONS);

		return JsonRpc.result(id, result);
	}

	private JsonObject toolsList(JsonElement id) {
		JsonArray list = new JsonArray();
		for (Tool tool : tools.tools()) {
			JsonObject entry = new JsonObject();
			entry.addProperty("name", tool.name());
			entry.addProperty("description", tool.description());
			entry.add("inputSchema", tool.inputSchema());
			list.add(entry);
		}

		JsonObject result = new JsonObject();
		result.add("tools", list);
		return JsonRpc.result(id, result);
	}

	private JsonObject toolsCall(JsonElement id, JsonObject params) {
		if (params == null || !params.has("name") || !params.get("name").isJsonPrimitive()) {
			return JsonRpc.error(id, JsonRpc.INVALID_PARAMS, "tools/call requires a string 'name'");
		}
		String name = params.get("name").getAsString();

		JsonObject arguments = new JsonObject();
		if (params.has("arguments") && !params.get("arguments").isJsonNull()) {
			if (!params.get("arguments").isJsonObject()) {
				return JsonRpc.error(id, JsonRpc.INVALID_PARAMS, "'arguments' must be an object");
			}
			arguments = params.getAsJsonObject("arguments");
		}

		Tool tool = tools.get(name);
		if (tool == null) {
			return JsonRpc.error(id, JsonRpc.INVALID_PARAMS, "unknown tool: " + name);
		}

		long started = System.nanoTime();
		try {
			ToolResult result = tool.call(arguments);
			logCall(name, started, false);
			return JsonRpc.result(id, result == null ? ToolResult.text("").toJson() : result.toJson());
		} catch (ToolException expected) {
			logCall(name, started, true);
			return JsonRpc.result(id, ToolResult.error(expected.getMessage()).toJson());
		} catch (RuntimeException unexpected) {
			logCall(name, started, true);
			return JsonRpc.result(id, ToolResult.error(unexpected.toString()).toJson());
		}
	}

	private static void logCall(String tool, long startedNanos, boolean error) {
		if (!Boolean.getBoolean("mcp.debug")) {
			return;
		}
		long millis = (System.nanoTime() - startedNanos) / 1_000_000L;
		System.err.println("[MCP] tools/call " + tool + " " + millis + "ms" + (error ? " ERROR" : ""));
	}
}
