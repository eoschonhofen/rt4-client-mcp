package rt4.aionly;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * AIO-14 — a short, redacted description of what an MCP tool just did.
 *
 * <p>The spectator overlay shows this to a human watching the window, so it must never
 * reveal a credential: {@code login} drops its password, and any 20-character
 * {@code [a-z0-9]} run (the shape of a token) is replaced with bullets.</p>
 */
public final class ActionSummary {
	public static final int MAX_LENGTH = 60;
	public static final String REDACTED = "••••";

	/** A run of 20 [a-z0-9] characters is an agent token shape; never show one. */
	private static final Pattern TOKEN_SHAPE = Pattern.compile("[a-z0-9]{20}");

	private ActionSummary() {
	}

	/** The summary, or null for a read-only tool (the overlay ignores those). */
	public static String of(String toolName, JsonObject args) {
		if (toolName == null) {
			return null;
		}
		// get_account is read-only for AIO-11's idle logic, but it is the agent's first real
		// step; show it with no arguments at all.
		if ("get_account".equals(toolName)) {
			return "get_account";
		}
		if (ToolKinds.isReadOnly(toolName)) {
			return null;
		}
		return truncate(redact(describe(toolName, args)));
	}

	/** Replaces every token-shaped run, in any argument. */
	public static String redact(String text) {
		if (text == null) {
			return "";
		}
		return TOKEN_SHAPE.matcher(text).replaceAll(REDACTED);
	}

	private static String describe(String tool, JsonObject args) {
		if ("login".equals(tool)) {
			// The password is deliberately never part of the summary.
			return ("login " + text(args, "username")).trim();
		}
		if ("walk_to".equals(tool)) {
			return "walk_to " + number(args, "x") + "," + number(args, "y");
		}
		if ("do_action".equals(tool)) {
			return ("do_action " + text(args, "option") + " " + text(args, "target")).trim();
		}
		if ("type_text".equals(tool)) {
			return "type_text \"" + text(args, "text") + "\"";
		}
		if ("mouse_click".equals(tool)) {
			return "mouse_click " + number(args, "x") + "," + number(args, "y");
		}
		if ("press_key".equals(tool)) {
			return ("press_key " + text(args, "key")).trim();
		}

		// Anything else: the name plus the first two scalar arguments.
		StringBuilder out = new StringBuilder(tool);
		if (args != null) {
			int appended = 0;
			for (Map.Entry<String, JsonElement> entry : args.entrySet()) {
				if (appended >= 2 || !entry.getValue().isJsonPrimitive()) {
					break;
				}
				out.append(' ').append(entry.getValue().getAsString());
				appended++;
			}
		}
		return out.toString();
	}

	private static String text(JsonObject args, String name) {
		if (args == null || !args.has(name) || args.get(name).isJsonNull()
				|| !args.get(name).isJsonPrimitive()) {
			return "";
		}
		return args.get(name).getAsString();
	}

	private static String number(JsonObject args, String name) {
		if (args == null || !args.has(name) || !args.get(name).isJsonPrimitive()) {
			return "?";
		}
		return args.get(name).getAsString();
	}

	private static String truncate(String summary) {
		if (summary.length() <= MAX_LENGTH) {
			return summary;
		}
		return summary.substring(0, MAX_LENGTH - 1) + "…";
	}
}
