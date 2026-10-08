package rt4.mcp;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import rt4.PlayerAppearance;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * MCP-29 — the appearance read has to report the id the game uses, and has to
 * tell an empty slot from the first style, because the first hair style is the
 * bare head and reading it as "nothing" (or nothing as it) is the whole bug.
 */
class LooksTest {
	@Test
	void everyPartComesBackAsItsIdentikitId() {
		JsonObject out = Looks.of(appearance(hair(2), hair(14), hair(18), hair(26), hair(33), hair(36), hair(42)));

		assertEquals(2, out.get("hair").getAsInt());
		assertEquals(14, out.get("jaw").getAsInt());
		assertEquals(18, out.get("torso").getAsInt());
		assertEquals(26, out.get("arms").getAsInt());
		assertEquals(33, out.get("wrists").getAsInt());
		assertEquals(36, out.get("legs").getAsInt());
		assertEquals(42, out.get("feet").getAsInt());
	}

	@Test
	void anEmptySlotIsNothingRatherThanTheFirstId() {
		JsonObject out = Looks.of(appearance(0, 0, 0, 0, 0, 0, 0));

		assertEquals(-1, out.get("hair").getAsInt());
		assertEquals(-1, out.get("jaw").getAsInt());
	}

	@Test
	void theBareHeadIsStillAStyleAndNotAnEmptySlot() {
		// Id 0 is the bald head: a slot holding 0x80000000|0 holds a real head.
		JsonObject out = Looks.of(appearance(hair(0), 0, 0, 0, 0, 0, 0));

		assertEquals(0, out.get("hair").getAsInt());
	}

	@Test
	void somethingWornIsNotReadAsABodyPart() {
		// A slot carrying an item uses the flag below the body-part one.
		JsonObject out = Looks.of(appearance(0x40000000 | 1234, 0, 0, 0, 0, 0, 0));

		assertEquals(1234, out.get("hair").getAsInt());
	}

	@Test
	void theColoursComeBackInPayloadOrder() {
		int[] colours = {20, 24, 0, 0, 7};
		PlayerAppearance look = appearance(0, 0, 0, 0, 0, 0, 0);
		look.set(colours, -1, false, new int[12], -1);

		JsonObject out = Looks.of(look);
		assertEquals(20, out.get("hair_colour").getAsInt());
		assertEquals(24, out.get("torso_colour").getAsInt());
		assertEquals(0, out.get("legs_colour").getAsInt());
		assertEquals(0, out.get("feet_colour").getAsInt());
		assertEquals(7, out.get("skin_colour").getAsInt());
	}

	@Test
	void theGenderIsTheFemaleFlag() {
		PlayerAppearance look = appearance(0, 0, 0, 0, 0, 0, 0);
		look.set(new int[5], -1, true, new int[12], -1);

		assertEquals(true, Looks.of(look).get("female").getAsBoolean());
	}

	private static int hair(int id) {
		return Integer.MIN_VALUE | id;
	}

	private static PlayerAppearance appearance(int hair, int jaw, int torso, int arms, int wrists, int legs, int feet) {
		int[] identikit = new int[12];
		identikit[Looks.SLOT_HAIR] = hair;
		identikit[Looks.SLOT_JAW] = jaw;
		identikit[Looks.SLOT_TORSO] = torso;
		identikit[Looks.SLOT_ARMS] = arms;
		identikit[Looks.SLOT_WRISTS] = wrists;
		identikit[Looks.SLOT_LEGS] = legs;
		identikit[Looks.SLOT_FEET] = feet;

		PlayerAppearance look = new PlayerAppearance();
		look.set(new int[5], -1, false, identikit, -1);
		return look;
	}
}
