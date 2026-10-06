package rt4.mcp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class McpProtocolTest {
	private static JsonRpc.Message request(String json) throws Exception {
		return JsonRpc.parse(json);
	}

	private static ToolRegistry registryWith(Tool tool) {
		return new ToolRegistry().register(tool);
	}

	private static Tool honestTool() {
		return new Tool() {
			@Override
			public String name() {
				return "echo";
			}

			@Override
			public String description() {
				return "echoes its argument";
			}

			@Override
			public JsonObject inputSchema() {
				JsonObject schema = new JsonObject();
				schema.addProperty("type", "object");
				return schema;
			}

			@Override
			public ToolResult call(JsonObject args) {
				return ToolResult.json(args == null ? new JsonObject() : args);
			}
		};
	}

	@Test
	void initializeEchoesSupportedVersion() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\",\"params\":{\"protocolVersion\":\"2025-03-26\"}}"));

		JsonObject result = response.getAsJsonObject("result");
		assertEquals("2025-03-26", result.get("protocolVersion").getAsString());
		assertEquals("rt4-client", result.getAsJsonObject("serverInfo").get("name").getAsString());
		assertEquals("1.0.0", result.getAsJsonObject("serverInfo").get("version").getAsString());
		assertFalse(result.getAsJsonObject("capabilities").getAsJsonObject("tools").get("listChanged").getAsBoolean());
		assertTrue(result.get("instructions").getAsString().contains("wait_for"));
	}

	@Test
	void initializeFallsBackForUnsupportedVersion() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\",\"params\":{\"protocolVersion\":\"1999-01-01\"}}"));

		assertEquals(McpProtocol.FALLBACK_VERSION,
				response.getAsJsonObject("result").get("protocolVersion").getAsString());
	}

	@Test
	void initializeWithoutParamsFallsBack() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"initialize\"}"));

		assertEquals(McpProtocol.FALLBACK_VERSION,
				response.getAsJsonObject("result").get("protocolVersion").getAsString());
	}

	@Test
	void pingReturnsEmptyObject() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":7,\"method\":\"ping\"}"));

		assertTrue(response.getAsJsonObject("result").entrySet().isEmpty());
		assertEquals(7, response.get("id").getAsInt());
	}

	@Test
	void notificationsReturnNull() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		assertNull(protocol.handle(JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"method\":\"notifications/initialized\"}")));
	}

	@Test
	void toolsListHasTheMcpShape() throws Exception {
		McpProtocol protocol = new McpProtocol(registryWith(honestTool()));
		JsonObject response = protocol.handle(JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":2,\"method\":\"tools/list\"}"));

		JsonObject entry = response.getAsJsonObject("result").getAsJsonArray("tools").get(0).getAsJsonObject();
		assertEquals("echo", entry.get("name").getAsString());
		assertEquals("echoes its argument", entry.get("description").getAsString());
		assertEquals("object", entry.getAsJsonObject("inputSchema").get("type").getAsString());
	}

	@Test
	void toolsCallSuccess() throws Exception {
		McpProtocol protocol = new McpProtocol(registryWith(honestTool()));
		JsonObject response = protocol.handle(JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":3,\"method\":\"tools/call\","
						+ "\"params\":{\"name\":\"echo\",\"arguments\":{\"hello\":\"world\"}}}"));

		JsonObject result = response.getAsJsonObject("result");
		assertFalse(result.has("isError"));
		assertEquals("{\"hello\":\"world\"}",
				result.getAsJsonArray("content").get(0).getAsJsonObject().get("text").getAsString());
		assertEquals("world", result.getAsJsonObject("structuredContent").get("hello").getAsString());
	}

	@Test
	void toolExceptionBecomesAToolErrorResult() throws Exception {
		Tool failing = new Tool() {
			@Override
			public String name() {
				return "boom";
			}

			@Override
			public String description() {
				return "always fails";
			}

			@Override
			public JsonObject inputSchema() {
				return new JsonObject();
			}

			@Override
			public ToolResult call(JsonObject args) throws ToolException {
				throw new ToolException("target gone: npc:1423");
			}
		};

		McpProtocol protocol = new McpProtocol(registryWith(failing));
		JsonObject response = protocol.handle(JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":4,\"method\":\"tools/call\",\"params\":{\"name\":\"boom\"}}"));

		assertFalse(response.has("error"), "a failing tool is a result, not a JSON-RPC error");
		JsonObject result = response.getAsJsonObject("result");
		assertTrue(result.get("isError").getAsBoolean());
		assertEquals("target gone: npc:1423",
				result.getAsJsonArray("content").get(0).getAsJsonObject().get("text").getAsString());
	}

	@Test
	void unknownToolIsInvalidParams() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":5,\"method\":\"tools/call\",\"params\":{\"name\":\"nope\"}}"));

		assertEquals(JsonRpc.INVALID_PARAMS, response.getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void nonObjectArgumentsIsInvalidParams() throws Exception {
		McpProtocol protocol = new McpProtocol(registryWith(honestTool()));
		JsonObject response = protocol.handle(JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":6,\"method\":\"tools/call\","
						+ "\"params\":{\"name\":\"echo\",\"arguments\":\"nope\"}}"));

		assertEquals(JsonRpc.INVALID_PARAMS, response.getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void unknownMethodIsMethodNotFound() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":8,\"method\":\"does/not/exist\"}"));

		assertEquals(JsonRpc.METHOD_NOT_FOUND, response.getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void createsSessionOnlyForInitialize() {
		assertTrue(McpProtocol.createsSession("initialize"));
		assertFalse(McpProtocol.createsSession("tools/list"));
	}

	@Test
	void idEchoesThroughTheProtocol() throws Exception {
		McpProtocol protocol = new McpProtocol(new ToolRegistry());
		JsonObject response = protocol.handle(request("{\"jsonrpc\":\"2.0\",\"id\":\"abc\",\"method\":\"ping\"}"));
		JsonElement id = response.get("id");
		assertEquals("abc", id.getAsString());
	}
}
