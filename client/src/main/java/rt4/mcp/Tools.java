package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.concurrent.Callable;

/**
 * MCP-06 — small helpers for declaring tools and their JSON Schemas, and for reading
 * arguments. Keeps each tool body short.
 */
public final class Tools {
	/** A tool body. */
	public interface Body {
		ToolResult call(JsonObject args) throws ToolException;
	}

	private Tools() {
	}

	// ------------------------------------------------------------------ tool construction

	/** A tool that runs wherever it is called. */
	public static Tool tool(final String name, final String description, final JsonObject schema, final Body body) {
		return new Tool() {
			@Override
			public String name() {
				return name;
			}

			@Override
			public String description() {
				return description;
			}

			@Override
			public JsonObject inputSchema() {
				return schema;
			}

			@Override
			public ToolResult call(JsonObject args) throws ToolException {
				return body.call(args == null ? new JsonObject() : args);
			}
		};
	}

	/** A tool whose body runs on the game thread. */
	public static Tool gameTool(String name, String description, JsonObject schema, final Body body) {
		return tool(name, description, schema, new Body() {
			@Override
			public ToolResult call(final JsonObject args) throws ToolException {
				return GameThread.call(new Callable<ToolResult>() {
					@Override
					public ToolResult call() throws Exception {
						return body.call(args);
					}
				});
			}
		});
	}

	// ------------------------------------------------------------------ schema building

	public static JsonObject obj() {
		JsonObject schema = new JsonObject();
		schema.addProperty("type", "object");
		schema.add("properties", new JsonObject());
		return schema;
	}

	public static JsonObject prop(JsonObject schema, String name, JsonObject property) {
		schema.getAsJsonObject("properties").add(name, property);
		return schema;
	}

	public static JsonObject require(JsonObject schema, String... names) {
		JsonArray required = new JsonArray();
		for (String name : names) {
			required.add(name);
		}
		schema.add("required", required);
		return schema;
	}

	public static JsonObject string(String description) {
		return typed("string", description);
	}

	public static JsonObject integer(String description) {
		return typed("integer", description);
	}

	public static JsonObject number(String description) {
		return typed("number", description);
	}

	public static JsonObject bool(String description) {
		return typed("boolean", description);
	}

	public static JsonObject withDefault(JsonObject property, Object value) {
		if (value instanceof Number) {
			property.addProperty("default", (Number) value);
		} else if (value instanceof Boolean) {
			property.addProperty("default", (Boolean) value);
		} else if (value != null) {
			property.addProperty("default", String.valueOf(value));
		}
		return property;
	}

	public static JsonObject stringEnum(String description, String... values) {
		JsonObject property = typed("string", description);
		JsonArray allowed = new JsonArray();
		for (String value : values) {
			allowed.add(value);
		}
		property.add("enum", allowed);
		return property;
	}

	public static JsonObject array(String description, JsonObject items) {
		JsonObject property = typed("array", description);
		property.add("items", items);
		return property;
	}

	private static JsonObject typed(String type, String description) {
		JsonObject property = new JsonObject();
		property.addProperty("type", type);
		if (description != null) {
			property.addProperty("description", description);
		}
		return property;
	}

	// ------------------------------------------------------------------ argument reading

	public static boolean has(JsonObject args, String name) {
		return args != null && args.has(name) && !args.get(name).isJsonNull();
	}

	public static String optString(JsonObject args, String name, String fallback) {
		return has(args, name) ? args.get(name).getAsString() : fallback;
	}

	public static String getString(JsonObject args, String name) throws ToolException {
		if (!has(args, name)) {
			throw new ToolException("missing required argument '" + name + "'");
		}
		return args.get(name).getAsString();
	}

	public static int optInt(JsonObject args, String name, int fallback) throws ToolException {
		return has(args, name) ? intOf(args.get(name), name) : fallback;
	}

	public static int getInt(JsonObject args, String name) throws ToolException {
		if (!has(args, name)) {
			throw new ToolException("missing required argument '" + name + "'");
		}
		return intOf(args.get(name), name);
	}

	private static int intOf(JsonElement element, String name) throws ToolException {
		if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isNumber()) {
			throw new ToolException("argument '" + name + "' must be a number");
		}
		return element.getAsInt();
	}

	public static double optDouble(JsonObject args, String name, double fallback) throws ToolException {
		if (!has(args, name)) {
			return fallback;
		}
		JsonElement element = args.get(name);
		if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isNumber()) {
			throw new ToolException("argument '" + name + "' must be a number");
		}
		return element.getAsDouble();
	}

	public static boolean optBool(JsonObject args, String name, boolean fallback) throws ToolException {
		if (!has(args, name)) {
			return fallback;
		}
		JsonElement element = args.get(name);
		if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isBoolean()) {
			throw new ToolException("argument '" + name + "' must be a boolean");
		}
		return element.getAsBoolean();
	}

	public static JsonObject optObject(JsonObject args, String name) throws ToolException {
		if (!has(args, name)) {
			return null;
		}
		JsonElement element = args.get(name);
		if (!element.isJsonObject()) {
			throw new ToolException("argument '" + name + "' must be an object");
		}
		return element.getAsJsonObject();
	}

	public static JsonArray optArray(JsonObject args, String name) throws ToolException {
		if (!has(args, name)) {
			return null;
		}
		JsonElement element = args.get(name);
		if (!element.isJsonArray()) {
			throw new ToolException("argument '" + name + "' must be an array");
		}
		return element.getAsJsonArray();
	}

	/** A JSON string, or null for a null/absent value. */
	public static JsonElement text(String value) {
		return value == null ? com.google.gson.JsonNull.INSTANCE : new JsonPrimitive(value);
	}

	public static int clamp(int value, int min, int max) {
		return value < min ? min : Math.min(value, max);
	}
}
