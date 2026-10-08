package rt4.mcp;

import org.junit.jupiter.api.Test;
import rt4.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * MCP-26 — a target on another plane is refused instead of building the wrong menu.
 * MCP-30 — a slot target on a component that is not an inventory is refused by name.
 */
class MenuSynthTest {
	@Test
	void aLocOnAnotherPlaneIsRefused() {
		int here = Player.plane;
		int elsewhere = here + 1;

		ToolException refused = assertThrows(ToolException.class,
				() -> MenuSynth.list(new LocTarget(12345, 3222, 3222, elsewhere)));

		assertEquals("target is on plane " + elsewhere + ", you are on plane " + here, refused.getMessage());
	}

	@Test
	void anObjOnAnotherPlaneIsRefused() {
		int here = Player.plane;
		int elsewhere = here + 1;

		ToolException refused = assertThrows(ToolException.class,
				() -> MenuSynth.list(new ObjTarget(995, 3222, 3222, elsewhere)));

		assertEquals("target is on plane " + elsewhere + ", you are on plane " + here, refused.getMessage());
	}

	@Test
	void aSlotOnAComponentThatIsNotAnInventoryIsRefusedByName() {
		// The emotes tab icon: type 5, 33 x 36, so the old message invented "1188 slots".
		ComponentTarget target = new ComponentTarget(548, 25, 0);

		assertEquals("if:548:25 is not an inventory, so if:548:25:0 names no slot;"
						+ " list_actions on if:548:25 shows what it offers",
				MenuSynth.slotRefusal(target, 5, 1188));
	}

	@Test
	void aSlotOutsideAnInventoryNamesItsSize() {
		ComponentTarget target = new ComponentTarget(149, 0, 30);

		assertEquals("no slot 30 in if:149:0:30; this inventory has 28 slots",
				MenuSynth.slotRefusal(target, 2, 28));
	}
}
