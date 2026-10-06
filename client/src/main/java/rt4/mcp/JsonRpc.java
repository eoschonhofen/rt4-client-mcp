package rt4.mcp;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * MCP-03 — JSON-RPC 2.0 message parsing, validation and building. Pure: no game state,
 * so it is unit-tested directly.
 */
public final class JsonRpc {
	public static final int PARSE_ERROR = -32700;
	public static final int INVALID_REQUEST = -32600;
	public static final int METHOD_NOT_FOUND = -32601;
	public static final int INVALID_PARAMS = -32602;
	public static final int INTERNAL_ERROR = -32603;

	private static final Gson COMPACT = new Gson();

	public enum Kind {
		REQUEST,
		NOTIFICATION,
		RESPONSE
	}

	/** A parsed, validated message. */
	public static final class Message {
		public final Kind kind;
		public final String method;
		public final JsonObject params;
		public final JsonElement id;

		Message(Kind kind, String method, JsonObject params, JsonElement id) {
			this.kind = kind;
			this.method = method;
			this.params = params;
			this.id = id;
		}

		public boolean isNotification() {
			return kind == Kind.NOTIFICATION;
		}

		public boolean isRequest() {
			return kind == Kind.REQUEST;
		}
	}

	/** A JSON-RPC level failure carrying a standard error code. */
	public static final class RpcException extends Exception {
		private static final long serialVersionUID = 1L;
		public final int code;

		public RpcException(int code, String message) {
			super(message);
			this.code = code;
		}
	}

	private JsonRpc() {
	}

	/**
	 * Classifies one raw body. Throws {@link RpcException} for malformed JSON
	 * (-32700) and for anything that is not a valid JSON-RPC 2.0 message (-32600).
	 */
	public static Message parse(String raw) throws RpcException {
		if (raw == null || raw.trim().isEmpty()) {
			throw new RpcException(PARSE_ERROR, "empty request body");
		}

		JsonElement parsed;
		try {
			parsed = JsonParser.parseString(raw);
		} catch (JsonParseException | IllegalStateException malformed) {
			throw new RpcException(PARSE_ERROR, "invalid JSON");
		}

		if (parsed.isJsonArray()) {
			// Batching was dropped from the transport spec.
			throw new RpcException(INVALID_REQUEST, "batch requests are not supported");
		}
		if (!parsed.isJsonObject()) {
			throw new RpcException(INVALID_REQUEST, "request must be a JSON object");
		}

		JsonObject object = parsed.getAsJsonObject();
		JsonElement version = object.get("jsonrpc");
		if (version == null || !version.isJsonPrimitive() || !"2.0".equals(version.getAsString())) {
			throw new RpcException(INVALID_REQUEST, "jsonrpc must be \"2.0\"");
		}

		if (object.has("method")) {
			JsonElement methodElement = object.get("method");
			if (methodElement == null || !methodElement.isJsonPrimitive() || !methodElement.getAsJsonPrimitive().isString()) {
				throw new RpcException(INVALID_REQUEST, "method must be a string");
			}

			JsonObject params = null;
			JsonElement paramsElement = object.get("params");
			if (paramsElement != null && !paramsElement.isJsonNull()) {
				if (!paramsElement.isJsonObject()) {
					throw new RpcException(INVALID_PARAMS, "params must be an object");
				}
				params = paramsElement.getAsJsonObject();
			}

			JsonElement id = object.get("id");
			if (id == null || id.isJsonNull()) {
				return new Message(Kind.NOTIFICATION, methodElement.getAsString(), params, null);
			}
			validateId(id);
			return new Message(Kind.REQUEST, methodElement.getAsString(), params, id);
		}

		if (object.has("result") || object.has("error")) {
			return new Message(Kind.RESPONSE, null, null, object.get("id"));
		}

		throw new RpcException(INVALID_REQUEST, "not a JSON-RPC request, notification or response");
	}

	private static void validateId(JsonElement id) throws RpcException {
		if (id.isJsonPrimitive()) {
			JsonPrimitive primitive = id.getAsJsonPrimitive();
			if (primitive.isString() || primitive.isNumber()) {
				return;
			}
		}
		throw new RpcException(INVALID_REQUEST, "id must be a string or a number");
	}

	public static JsonObject result(JsonElement id, JsonElement result) {
		JsonObject out = new JsonObject();
		out.addProperty("jsonrpc", "2.0");
		out.add("id", id == null ? com.google.gson.JsonNull.INSTANCE : id);
		out.add("result", result == null ? new JsonObject() : result);
		return out;
	}

	public static JsonObject result(JsonElement id, String key, JsonElement value) {
		JsonObject result = new JsonObject();
		result.add(key, value);
		return result(id, result);
	}

	public static JsonObject error(JsonElement id, int code, String message) {
		return error(id, code, message, null);
	}

	public static JsonObject error(JsonElement id, int code, String message, JsonElement data) {
		JsonObject error = new JsonObject();
		error.addProperty("code", code);
		error.addProperty("message", message);
		if (data != null) {
			error.add("data", data);
		}

		JsonObject out = new JsonObject();
		out.addProperty("jsonrpc", "2.0");
		out.add("id", id == null ? com.google.gson.JsonNull.INSTANCE : id);
		out.add("error", error);
		return out;
	}

	public static String toJson(JsonElement element) {
		return COMPACT.toJson(element);
	}
}
