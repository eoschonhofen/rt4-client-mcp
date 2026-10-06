package rt4.mcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NamesTest {
	@Test
	void stripsColorTags() {
		assertEquals("Zezima", Names.strip("<col=ffffff>Zezima</col>"));
		assertEquals("You get a log.", Names.strip("<col=00ff00>You get a log.</col>"));
	}

	@Test
	void stripsImageTags() {
		assertEquals("Zezima", Names.strip("<img=0>Zezima"));
		assertEquals("Zezima", Names.strip("<img=1>Zezima"));
	}

	@Test
	void stripsLineBreaksAndShadowTags() {
		assertEquals("ab", Names.strip("a<br>b"));
		assertEquals("text", Names.strip("<shad=0>text"));
	}

	@Test
	void leavesPlainTextAlone() {
		assertEquals("Buy 10 logs", Names.strip("Buy 10 logs"));
	}

	@Test
	void handlesNull() {
		assertNull(Names.strip(null));
		assertNull(Names.plain(null));
	}

	@Test
	void unescapesAngleBrackets() {
		assertEquals("a < b", Names.strip("a <lt> b"));
		assertEquals("a > b", Names.strip("a <gt> b"));
	}

	@Test
	void skillTableHasAll530Skills() {
		assertEquals(25, Names.SKILLS.length);
		assertEquals("Attack", Names.SKILLS[0]);
		assertEquals("Hitpoints", Names.SKILLS[3]);
		assertEquals("Prayer", Names.SKILLS[5]);
		assertEquals("Slayer", Names.SKILLS[18]);
		assertEquals("Summoning", Names.SKILLS[23]);
	}

	@Test
	void equipmentSlotTableIsComplete() {
		assertEquals(11, Names.EQUIPMENT_SLOTS.length);
		assertEquals("head", Names.EQUIPMENT_SLOTS[0]);
		assertEquals("weapon", Names.EQUIPMENT_SLOTS[3]);
		assertEquals("ammo", Names.EQUIPMENT_SLOTS[10]);
	}

	@Test
	void skillLookupIsCaseInsensitiveAndForgiving() {
		assertEquals(8, Names.skillIndex("Woodcutting"));
		assertEquals(8, Names.skillIndex("woodcutting"));
		assertEquals(3, Names.skillIndex("hp"));
		assertEquals(20, Names.skillIndex("Runecraft"));
		assertEquals(-1, Names.skillIndex("Invention"));
		assertEquals(-1, Names.skillIndex(null));
	}

	@Test
	void skillNameFallsBackForUnknownIndex() {
		assertEquals("Attack", Names.skillName(0));
		assertTrue(Names.skillName(99).contains("99"));
	}

	@Test
	void chatTypeNamesCoverTheProtocolTypes() {
		assertEquals("game", Names.chatTypeName(0));
		assertEquals("public", Names.chatTypeName(2));
		assertEquals("private_in", Names.chatTypeName(3));
		assertEquals("private_out", Names.chatTypeName(6));
		assertEquals("trade", Names.chatTypeName(4));
		assertEquals("clan", Names.chatTypeName(9));
		assertEquals("clan", Names.chatTypeName(20));
		assertEquals("type99", Names.chatTypeName(99));
	}
}
