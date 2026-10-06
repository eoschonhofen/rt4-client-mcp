package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * MCP-12 — parses and evaluates {@code wait_for} conditions.
 *
 * <p>Every predicate reads through {@link GameView}, so the whole table is unit-tested
 * against a fake and the real implementation is the only part that touches game statics.</p>
 */
public final class Conditions {
	public static final int MAX_TIMEOUT_MS = 60000;

	public enum Mode {
		ANY,
		ALL
	}

	/** Everything a condition may ask about. The MCP-12 tests fake this. */
	public interface GameView {
		boolean loggedIn();

		boolean loggedOut();

		int tick();

		int frame();

		boolean idle();

		int x();

		int y();

		int plane();

		boolean dialogueOpen();

		boolean interfaceOpen(int interfaceId);

		int inventoryHash();

		int itemCount(int itemId);

		int totalXp(int skill);

		int hp();

		/** Whether the entity behind a target id still exists with the same type. */
		boolean entityAlive(String target, int expectedType);

		/** The type id for a target now, or -1. Used to snapshot {@code npc_gone}. */
		int entityType(String target);

		boolean navFinished(int task);

		int animation();

		/** Whether any chat message newer than {@code sinceSize} matches. */
		boolean chatMatches(int sinceSize, Pattern regex, Set<Integer> types);

		/** The client's monotonic count of chat messages added, used as a chat cursor. */
		int chatSizeHint();
	}

	/** One parsed condition, with the registration-time snapshot it compares against. */
	public static final class Condition {
		public final String name;
		public final JsonObject args;

		public int forTicks = 1;
		public int ticks;
		public int x;
		public int y;
		public int plane = -1;
		public int radius;
		public int interfaceId;
		public int itemId;
		public String itemOp = ">=";
		public int itemCount;
		public Pattern chatRegex;
		public Set<Integer> chatTypes;
		public int skill = -1;
		public int hp;
		public String target;
		public int task = -1;
		public int animationId = -2;

		// Snapshot, filled on the game thread at registration.
		public int startTick;
		public int startHash;
		public int startChatSize;
		public int startXp = Integer.MIN_VALUE;
		public int expectedType = -1;
		public int idleSinceTick = Integer.MIN_VALUE;

		Condition(String name, JsonObject args) {
			this.name = name;
			this.args = args;
		}

		public boolean expectsLoggedOut() {
			return "logged_out".equals(name);
		}
	}

	private static final Set<String> KNOWN = new HashSet<String>(java.util.Arrays.asList(
			"idle", "ticks", "logged_in", "logged_out", "position", "dialogue_open", "interface_open",
			"interface_closed", "inventory_changed", "item_count", "chat_matches", "skill_xp_changed",
			"hp_below", "npc_gone", "nav_done", "animation"));

	private Conditions() {
	}

	// ------------------------------------------------------------------ parsing

	/** Validates one condition object; the message is written for the agent to fix. */
	public static Condition parse(JsonObject json) throws ToolException {
		if (json == null) {
			throw new ToolException("each condition must be an object, e.g. {\"condition\": \"idle\", \"for_ticks\": 2}");
		}
		String name = null;
		for (String key : new String[]{"condition", "type", "name"}) {
			if (json.has(key) && json.get(key).isJsonPrimitive()) {
				name = json.get(key).getAsString();
				break;
			}
		}
		if (name == null) {
			throw new ToolException("a condition needs a \"condition\" field, one of " + KNOWN);
		}
		name = name.trim().toLowerCase(Locale.ROOT);
		if (!KNOWN.contains(name)) {
			throw new ToolException("unknown condition '" + name + "'; expected one of " + KNOWN);
		}

		Condition condition = new Condition(name, json);
		try {
			switch (name) {
				case "idle":
					condition.forTicks = Math.max(1, Tools.optInt(json, "for_ticks", 1));
					break;
				case "ticks":
					condition.ticks = Tools.getInt(json, "n");
					if (condition.ticks < 0) {
						throw new ToolException("'n' must be non-negative");
					}
					break;
				case "position":
					condition.x = Tools.getInt(json, "x");
					condition.y = Tools.getInt(json, "y");
					condition.plane = Tools.has(json, "plane") ? Tools.getInt(json, "plane") : -1;
					condition.radius = Math.max(0, Tools.optInt(json, "radius", 0));
					break;
				case "interface_open":
				case "interface_closed":
					condition.interfaceId = Tools.getInt(json, "id");
					break;
				case "item_count":
					condition.itemId = Tools.getInt(json, "id");
					condition.itemOp = Tools.optString(json, "op", ">=");
					if (!">=".equals(condition.itemOp) && !"<=".equals(condition.itemOp) && !"==".equals(condition.itemOp)) {
						throw new ToolException("'op' must be >=, <= or ==");
					}
					condition.itemCount = Tools.getInt(json, "n");
					break;
				case "chat_matches":
					String regex = Tools.getString(json, "regex");
					try {
						condition.chatRegex = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
					} catch (PatternSyntaxException bad) {
						throw new ToolException("invalid regex: " + bad.getDescription());
					}
					if (Tools.has(json, "types")) {
						JsonArray types = Tools.optArray(json, "types");
						condition.chatTypes = new HashSet<Integer>();
						for (JsonElement element : types) {
							condition.chatTypes.add(element.getAsInt());
						}
					}
					break;
				case "skill_xp_changed":
					if (Tools.has(json, "skill")) {
						String skill = Tools.getString(json, "skill");
						condition.skill = Names.skillIndex(skill);
						if (condition.skill < 0) {
							throw new ToolException("unknown skill '" + skill + "'");
						}
					}
					break;
				case "hp_below":
					condition.hp = Tools.getInt(json, "n");
					break;
				case "npc_gone":
					condition.target = Tools.getString(json, "target");
					Targets.parse(condition.target);
					break;
				case "nav_done":
					condition.task = Tools.has(json, "task") ? Tools.getInt(json, "task") : -1;
					break;
				case "animation":
					condition.animationId = Tools.has(json, "id") ? Tools.getInt(json, "id") : -2;
					break;
				default:
					break;
			}
		} catch (ToolException invalid) {
			throw new ToolException("condition '" + name + "': " + invalid.getMessage());
		}
		return condition;
	}

	public static List<Condition> parseAll(List<JsonObject> objects) throws ToolException {
		if (objects == null || objects.isEmpty()) {
			throw new ToolException("at least one condition is required");
		}
		List<Condition> conditions = new ArrayList<Condition>();
		for (JsonObject object : objects) {
			conditions.add(parse(object));
		}
		return conditions;
	}

	public static Mode parseMode(String mode) throws ToolException {
		if (mode == null || mode.isEmpty() || "any".equalsIgnoreCase(mode)) {
			return Mode.ANY;
		}
		if ("all".equalsIgnoreCase(mode)) {
			return Mode.ALL;
		}
		throw new ToolException("mode must be 'any' or 'all', not '" + mode + "'");
	}

	// ------------------------------------------------------------------ snapshot

	/** Captures the call-time values a condition compares against. Game thread only. */
	public static void snapshot(Condition condition, GameView view) {
		condition.startTick = view.tick();
		condition.startHash = view.inventoryHash();
		condition.startChatSize = view.chatSizeHint();
		if ("skill_xp_changed".equals(condition.name)) {
			condition.startXp = view.totalXp(condition.skill);
		}
		if ("npc_gone".equals(condition.name)) {
			condition.expectedType = view.entityType(condition.target);
		}
		condition.idleSinceTick = Integer.MIN_VALUE;
	}

	// ------------------------------------------------------------------ evaluation

	public static boolean evaluate(Condition condition, GameView view) {
		switch (condition.name) {
			case "idle":
				if (!view.idle()) {
					condition.idleSinceTick = Integer.MIN_VALUE;
					return false;
				}
				if (condition.idleSinceTick == Integer.MIN_VALUE) {
					condition.idleSinceTick = view.tick();
				}
				return view.tick() - condition.idleSinceTick + 1 >= condition.forTicks;
			case "ticks":
				return view.tick() - condition.startTick >= condition.ticks;
			case "logged_in":
				return view.loggedIn();
			case "logged_out":
				return view.loggedOut();
			case "position":
				if (condition.plane >= 0 && view.plane() != condition.plane) {
					return false;
				}
				return Math.max(Math.abs(view.x() - condition.x), Math.abs(view.y() - condition.y)) <= condition.radius;
			case "dialogue_open":
				return view.dialogueOpen();
			case "interface_open":
				return view.interfaceOpen(condition.interfaceId);
			case "interface_closed":
				return !view.interfaceOpen(condition.interfaceId);
			case "inventory_changed":
				return view.inventoryHash() != condition.startHash;
			case "item_count":
				int count = view.itemCount(condition.itemId);
				if (">=".equals(condition.itemOp)) {
					return count >= condition.itemCount;
				}
				if ("<=".equals(condition.itemOp)) {
					return count <= condition.itemCount;
				}
				return count == condition.itemCount;
			case "chat_matches":
				return view.chatMatches(condition.startChatSize, condition.chatRegex, condition.chatTypes);
			case "skill_xp_changed":
				return view.totalXp(condition.skill) != condition.startXp;
			case "hp_below":
				return view.hp() < condition.hp;
			case "npc_gone":
				return !view.entityAlive(condition.target, condition.expectedType);
			case "nav_done":
				return view.navFinished(condition.task);
			case "animation":
				return condition.animationId == -2 ? view.animation() != -1 : view.animation() == condition.animationId;
			default:
				return false;
		}
	}

	public static boolean combine(Mode mode, boolean[] results) {
		if (results.length == 0) {
			return false;
		}
		if (mode == Mode.ALL) {
			for (boolean result : results) {
				if (!result) {
					return false;
				}
			}
			return true;
		}
		for (boolean result : results) {
			if (result) {
				return true;
			}
		}
		return false;
	}

	/** The names that evaluated true, in declaration order. */
	public static List<String> which(List<Condition> conditions, boolean[] results) {
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < conditions.size() && i < results.length; i++) {
			if (results[i]) {
				names.add(conditions.get(i).name);
			}
		}
		return names;
	}

	public static int clampTimeout(int timeoutMs) {
		if (timeoutMs <= 0) {
			return 10000;
		}
		return Math.min(timeoutMs, MAX_TIMEOUT_MS);
	}
}
