package rt4.mcp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonRpcTest {
	@Test
	void classifiesARequest() throws Exception {
		JsonRpc.Message message = JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"tools/list\",\"params\":{\"cursor\":\"x\"}}");

		assertEquals(JsonRpc.Kind.REQUEST, message.kind);
		assertTrue(message.isRequest());
		assertFalse(message.isNotification());
		assertEquals("tools/list", message.method);
		assertEquals("x", message.params.get("cursor").getAsString());
		assertEquals(1, message.id.getAsInt());
	}

	@Test
	void classifiesANotification() throws Exception {
		JsonRpc.Message message = JsonRpc.parse(
				"{\"jsonrpc\":\"2.0\",\"method\":\"notifications/initialized\"}");

		assertEquals(JsonRpc.Kind.NOTIFICATION, message.kind);
		assertTrue(message.isNotification());
		assertNull(message.id);
	}

	@Test
	void nullIdIsANotification() throws Exception {
		JsonRpc.Message message = JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":null,\"method\":\"ping\"}");
		assertTrue(message.isNotification());
	}

	@Test
	void classifiesAResponse() throws Exception {
		JsonRpc.Message message = JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":\"abc\",\"result\":{}}");
		assertEquals(JsonRpc.Kind.RESPONSE, message.kind);
		assertNull(message.method);
	}

	@Test
	void malformedJsonIsParseError() {
		JsonRpc.RpcException error = assertThrows(JsonRpc.RpcException.class, () -> JsonRpc.parse("{oops"));
		assertEquals(JsonRpc.PARSE_ERROR, error.code);
	}

	@Test
	void emptyBodyIsParseError() {
		JsonRpc.RpcException error = assertThrows(JsonRpc.RpcException.class, () -> JsonRpc.parse("   "));
		assertEquals(JsonRpc.PARSE_ERROR, error.code);
	}

	@Test
	void batchIsInvalidRequest() {
		JsonRpc.RpcException error = assertThrows(JsonRpc.RpcException.class,
				() -> JsonRpc.parse("[{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"ping\"}]"));
		assertEquals(JsonRpc.INVALID_REQUEST, error.code);
	}

	@Test
	void missingVersionIsInvalidRequest() {
		JsonRpc.RpcException error = assertThrows(JsonRpc.RpcException.class,
				() -> JsonRpc.parse("{\"id\":1,\"method\":\"ping\"}"));
		assertEquals(JsonRpc.INVALID_REQUEST, error.code);
	}

	@Test
	void nonObjectParamsIsInvalidParams() {
		JsonRpc.RpcException error = assertThrows(JsonRpc.RpcException.class,
				() -> JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":\"ping\",\"params\":[1,2]}"));
		assertEquals(JsonRpc.INVALID_PARAMS, error.code);
	}

	@Test
	void primitiveNonStringMethodIsInvalidRequest() {
		JsonRpc.RpcException error = assertThrows(JsonRpc.RpcException.class,
				() -> JsonRpc.parse("{\"jsonrpc\":\"2.0\",\"id\":1,\"method\":5}"));
		assertEquals(JsonRpc.INVALID_REQUEST, error.code);
	}

	@Test
	void methodNotFoundAndInvalidParamsCodesAreBuildable() {
		JsonObject notFound = JsonRpc.error(null, JsonRpc.METHOD_NOT_FOUND, "unknown method");
		assertEquals(-32601, notFound.getAsJsonObject("error").get("code").getAsInt());

		JsonObject invalidParams = JsonRpc.error(null, JsonRpc.INVALID_PARAMS, "bad params");
		assertEquals(-32602, invalidParams.getAsJsonObject("error").get("code").getAsInt());
	}

	@Test
	void idIsEchoedForStringAndNumber() {
		JsonObject stringId = JsonRpc.result(JsonParser.parseString("\"abc\""), new JsonObject());
		assertEquals("abc", stringId.get("id").getAsString());
		assertEquals("2.0", stringId.get("jsonrpc").getAsString());

		JsonObject numberId = JsonRpc.result(JsonParser.parseString("42"), new JsonObject());
		assertEquals(42, numberId.get("id").getAsInt());
	}
}
