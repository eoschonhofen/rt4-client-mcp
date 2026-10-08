package rt4.mcp;

import com.google.gson.JsonObject;
import rt4.PlayerAppearance;

/**
 * MCP-29 — what the character looks like, part by part.
 *
 * The client has always held this: {@code Player.appearance} is the very copy
 * the model is drawn from, and the server now puts each choice on it as it is
 * made ({@code CharacterDesign.changeLook} and {@code changeColor}). Nobody read
 * it back, so an agent asked to pick a hairstyle saw no more than "Select next
 * hair style" — and the designer opens on the first style, which is the bare
 * head, so a character that could not say what it was wearing stayed bald.
 *
 * The slots and the colours are in the order the server writes them
 * ({@code PlayerFlags530.Appearance.writeTo}), which is also the order
 * {@code Appearance.prepareBodyData} fills: a body part in its own slot, or 0
 * when the slot is empty. The ids are the game's own identikit ids, the same
 * numbers the server's character designer counts its styles by; naming them is
 * the caller's business, so this read invents nothing.
 */
public final class Looks {
	/** Slots of the 12-part appearance payload, named by the part on them. */
	public static final int SLOT_TORSO = 4;
	public static final int SLOT_ARMS = 6;
	public static final int SLOT_LEGS = 7;
	public static final int SLOT_HAIR = 8;
	public static final int SLOT_WRISTS = 9;
	public static final int SLOT_FEET = 10;
	public static final int SLOT_JAW = 11;

	/** The five colours, in payload order: {@code Appearance.hair} comes first. */
	public static final int COLOUR_HAIR = 0;
	public static final int COLOUR_TORSO = 1;
	public static final int COLOUR_LEGS = 2;
	public static final int COLOUR_FEET = 3;
	public static final int COLOUR_SKIN = 4;

	private Looks() {
	}

	/** The appearance as JSON: one id per part, one index per colour. */
	public static JsonObject of(PlayerAppearance appearance) {
		JsonObject out = new JsonObject();
		out.addProperty("female", appearance.isFemale());
		out.addProperty("hair", appearance.identikitId(SLOT_HAIR));
		out.addProperty("jaw", appearance.identikitId(SLOT_JAW));
		out.addProperty("torso", appearance.identikitId(SLOT_TORSO));
		out.addProperty("arms", appearance.identikitId(SLOT_ARMS));
		out.addProperty("wrists", appearance.identikitId(SLOT_WRISTS));
		out.addProperty("legs", appearance.identikitId(SLOT_LEGS));
		out.addProperty("feet", appearance.identikitId(SLOT_FEET));
		out.addProperty("hair_colour", appearance.colour(COLOUR_HAIR));
		out.addProperty("torso_colour", appearance.colour(COLOUR_TORSO));
		out.addProperty("legs_colour", appearance.colour(COLOUR_LEGS));
		out.addProperty("feet_colour", appearance.colour(COLOUR_FEET));
		out.addProperty("skin_colour", appearance.colour(COLOUR_SKIN));
		return out;
	}
}
