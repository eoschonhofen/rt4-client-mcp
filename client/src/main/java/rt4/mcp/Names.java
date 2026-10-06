package rt4.mcp;

import rt4.JagString;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * MCP-06 — converts the client's {@link JagString}s into plain text and holds the 530
 * name tables.
 */
public final class Names {
	/** 530 skill order, as indexed by {@code PlayerSkillXpTable}. */
	public static final String[] SKILLS = {
			"Attack", "Defence", "Strength", "Hitpoints", "Ranged", "Prayer", "Magic",
			"Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting",
			"Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming",
			"Runecrafting", "Hunter", "Construction", "Summoning", "Dungeoneering"
	};

	/** 530 worn-equipment slot order, as indexed by inventory 94. */
	public static final String[] EQUIPMENT_SLOTS = {
			"head", "cape", "neck", "weapon", "body", "shield", "legs", "hands", "feet", "ring", "ammo"
	};

	/** Chat message types, from the {@code Chat.add} call sites in {@code Protocol}. */
	private static final String[] CHAT_TYPES = {
			"game",          // 0
			"public",        // 1  (with a crown/icon prefix)
			"public",        // 2
			"private_in",    // 3
			"trade",         // 4
			"friend",        // 5
			"private_out",   // 6
			"private_in",    // 7  (with an icon prefix)
			"challenge",     // 8
			"clan",          // 9
			"assist",        // 10
			"clan",          // 11
			"trade",         // 12
			"assist",        // 13
			"duel",          // 14
			"duel",          // 15
			"clan",          // 16
			"public",        // 17 (quick chat)
			"private_in",    // 18 (quick chat)
			"private_out",   // 19 (quick chat echo)
			"clan",          // 20 (quick chat)
			"clan"           // 21
	};

	private static final Pattern TAG = Pattern.compile("<[^>]*>");

	private Names() {
	}

	/** A {@link JagString} as plain text with every markup tag removed. */
	public static String plain(JagString value) {
		return value == null ? null : strip(value.toString());
	}

	/**
	 * Removes the client's chat markup: {@code <col=…>}, {@code </col>}, {@code <img=…>},
	 * {@code <br>}, {@code <shad=…>} and friends. {@code <lt>}/{@code <gt>} become the
	 * literal characters they escape.
	 */
	public static String strip(String text) {
		if (text == null) {
			return null;
		}
		String unescaped = text.replace("<lt>", "<").replace("<gt>", ">");
		return TAG.matcher(unescaped).replaceAll("");
	}

	public static String skillName(int index) {
		return index >= 0 && index < SKILLS.length ? SKILLS[index] : "unknown(" + index + ")";
	}

	/** Case-insensitive skill lookup; -1 when the name is not a 530 skill. */
	public static int skillIndex(String name) {
		if (name == null) {
			return -1;
		}
		String wanted = name.trim().toLowerCase(Locale.ROOT);
		if (wanted.isEmpty()) {
			return -1;
		}
		if ("hp".equals(wanted) || "hitpoint".equals(wanted)) {
			wanted = "hitpoints";
		}
		if ("runecraft".equals(wanted) || "runecrafting".equals(wanted) || "rc".equals(wanted)) {
			wanted = "runecrafting";
		}
		if ("wc".equals(wanted) || "woodcut".equals(wanted)) {
			wanted = "woodcutting";
		}
		for (int i = 0; i < SKILLS.length; i++) {
			if (SKILLS[i].toLowerCase(Locale.ROOT).equals(wanted)) {
				return i;
			}
		}
		return -1;
	}

	public static String chatTypeName(int type) {
		return type >= 0 && type < CHAT_TYPES.length ? CHAT_TYPES[type] : "type" + type;
	}
}
