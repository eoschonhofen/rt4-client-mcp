package rt4.mcp;

import org.junit.jupiter.api.Test;
import rt4.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** MCP-26 — a target on another plane is refused instead of building the wrong menu. */
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
}
