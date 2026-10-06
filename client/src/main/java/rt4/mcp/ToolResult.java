package rt4.mcp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Base64;

/**
 * MCP-03 — the MCP content block(s) a tool returns.
 *
 * <p>MCP-19 — {@code structuredContent} is always a JSON object, as the MCP schema requires.
 * Tools that naturally return a list wrap it under a descriptive key.</p>
 */
public final class ToolResult {
	private static final Gson COMPACT = new Gson();

	private final JsonArray content = new JsonArray();
	private JsonObject structured;
	private boolean error;

	private ToolResult() {
	}

	/** A single text content block. */
	public static ToolResult text(String text) {
		ToolResult result = new ToolResult();
		JsonObject block = new JsonObject();
		block.addProperty("type", "text");
		block.addProperty("text", text == null ? "" : text);
		result.content.add(block);
		return result;
	}

	/** Compact JSON as text, plus the same object as {@code structuredContent}. */
	public static ToolResult json(JsonObject value) {
		ToolResult result = text(COMPACT.toJson(value));
		result.structured = value;
		return result;
	}

	/** A PNG image content block, base64 encoded. */
	public static ToolResult image(byte[] png) {
		ToolResult result = new ToolResult();
		JsonObject block = new JsonObject();
		block.addProperty("type", "image");
		block.addProperty("data", Base64.getEncoder().encodeToString(png));
		block.addProperty("mimeType", "image/png");
		result.content.add(block);
		return result;
	}

	/** A PNG image plus a text line describing it, e.g. its size. */
	public static ToolResult imageWithText(byte[] png, String text) {
		ToolResult result = new ToolResult();
		JsonObject block = new JsonObject();
		block.addProperty("type", "image");
		block.addProperty("data", Base64.getEncoder().encodeToString(png));
		block.addProperty("mimeType", "image/png");
		result.content.add(block);

		JsonObject note = new JsonObject();
		note.addProperty("type", "text");
		note.addProperty("text", text == null ? "" : text);
		result.content.add(note);
		return result;
	}

	public static int base64Length(byte[] png) {
		return Base64.getEncoder().encodeToString(png).length();
	}

	/** A tool error: the agent sees the text and {@code isError: true}. */
	public static ToolResult error(String message) {
		ToolResult result = text(message);
		result.error = true;
		return result;
	}

	/** A tool error with a structured payload alongside the message. */
	public static ToolResult error(String message, JsonObject structured) {
		ToolResult result = error(message);
		result.structured = structured;
		return result;
	}

	public boolean isError() {
		return error;
	}

	public JsonObject toJson() {
		JsonObject out = new JsonObject();
		out.add("content", content);
		if (structured != null) {
			out.add("structuredContent", structured);
		}
		if (error) {
			out.addProperty("isError", true);
		}
		return out;
	}
}
