package rt4.mcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TargetsTest {
	private static void roundTrip(String text) throws Exception {
		Target target = Targets.parse(text);
		assertEquals(text, target.format(), "format must round-trip " + text);
		assertEquals(target, Targets.parse(target.format()), "equality must survive a round trip");
	}

	@Test
	void roundTripsEveryKind() throws Exception {
		roundTrip("npc:1423");
		roundTrip("player:7");
		roundTrip("loc:1530@3222,3218,0");
		roundTrip("obj:995@3221,3219,0");
		roundTrip("tile:3222,3218");
		roundTrip("tile:3222,3218,1");
		roundTrip("if:149:0");
		roundTrip("if:149:0:3");
	}

	@Test
	void parsesIntoTheRightTypes() throws Exception {
		assertTrue(Targets.parse("npc:1") instanceof NpcTarget);
		assertTrue(Targets.parse("player:1") instanceof PlayerTarget);
		assertTrue(Targets.parse("loc:1@2,3,0") instanceof LocTarget);
		assertTrue(Targets.parse("obj:1@2,3,0") instanceof ObjTarget);
		assertTrue(Targets.parse("tile:2,3") instanceof TileTarget);
		assertTrue(Targets.parse("if:149:0") instanceof ComponentTarget);
	}

	@Test
	void targetKindsMatchTheirPrefix() throws Exception {
		assertEquals("npc", Targets.parse("npc:1").kind());
		assertEquals("player", Targets.parse("player:1").kind());
		assertEquals("loc", Targets.parse("loc:1@2,3,0").kind());
		assertEquals("obj", Targets.parse("obj:1@2,3,0").kind());
		assertEquals("tile", Targets.parse("tile:2,3").kind());
		assertEquals("if", Targets.parse("if:149:0").kind());
	}

	@Test
	void locAndObjFieldsAreParsed() throws Exception {
		LocTarget loc = (LocTarget) Targets.parse("loc:1530@3222,3218,2");
		assertEquals(1530, loc.id);
		assertEquals(3222, loc.x);
		assertEquals(3218, loc.y);
		assertEquals(2, loc.plane);

		ObjTarget obj = (ObjTarget) Targets.parse("obj:995@3221,3219,1");
		assertEquals(995, obj.id);
		assertEquals(3221, obj.x);
		assertEquals(3219, obj.y);
		assertEquals(1, obj.plane);
	}

	@Test
	void tilePlaneDefaultsToTheCurrentPlane() throws Exception {
		TileTarget target = (TileTarget) Targets.parse("tile:3222,3218");

		assertEquals(-1, target.plane);
		assertFalse(target.hasPlane());
		assertEquals(3, target.planeOr(3));
		assertEquals("tile:3222,3218", target.format());
	}

	@Test
	void tileWithPlaneKeepsIt() throws Exception {
		TileTarget target = (TileTarget) Targets.parse("tile:3222,3218,2");

		assertTrue(target.hasPlane());
		assertEquals(2, target.planeOr(0));
		assertEquals("tile:3222,3218,2", target.format());
	}

	@Test
	void componentSlotIsOptional() throws Exception {
		ComponentTarget component = (ComponentTarget) Targets.parse("if:149:0");
		assertFalse(component.isSlot());
		assertEquals(-1, component.slot);

		ComponentTarget slot = (ComponentTarget) Targets.parse("if:149:0:3");
		assertTrue(slot.isSlot());
		assertEquals(3, slot.slot);
	}

	@Test
	void emptyNpcIndexIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("npc:"));
		assertTrue(error.getMessage().contains("npc:<index>"), error.getMessage());
	}

	@Test
	void locWithoutPlaneIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("loc:1@1,2"));
		assertTrue(error.getMessage().contains("malformed target 'loc:1@1,2'"), error.getMessage());
		assertTrue(error.getMessage().contains("<plane>"), error.getMessage());
	}

	@Test
	void locWithoutAtIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("loc:1530"));
		assertTrue(error.getMessage().contains("loc:<locId>@<x>,<y>,<plane>"), error.getMessage());
	}

	@Test
	void negativeSlotIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("if:149:0:-1"));
		assertTrue(error.getMessage().contains("non-negative slot"), error.getMessage());
	}

	@Test
	void tileWithOneCoordinateIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("tile:1"));
		assertTrue(error.getMessage().contains("tile"), error.getMessage());
	}

	@Test
	void nonNumericCoordinateIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("tile:abc,2"));
		assertTrue(error.getMessage().contains("non-negative x"), error.getMessage());
	}

	@Test
	void unknownKindIsRejected() {
		ToolException error = assertThrows(ToolException.class, () -> Targets.parse("wat:1"));
		assertTrue(error.getMessage().contains("unknown target kind 'wat'"), error.getMessage());
		assertTrue(error.getMessage().contains("npc, player, loc, obj, tile or if"), error.getMessage());
	}

	@Test
	void missingKindSeparatorIsRejected() {
		assertThrows(ToolException.class, () -> Targets.parse("npc1423"));
	}

	@Test
	void nullAndEmptyAreRejected() {
		assertThrows(ToolException.class, () -> Targets.parse(null));
		assertThrows(ToolException.class, () -> Targets.parse("   "));
	}

	@Test
	void tooManyCoordinatesAreRejected() {
		assertThrows(ToolException.class, () -> Targets.parse("loc:1@1,2,0,3"));
		assertThrows(ToolException.class, () -> Targets.parse("tile:1,2,0,3"));
	}

	@Test
	void planeOutOfRangeIsRejected() {
		assertThrows(ToolException.class, () -> Targets.parse("tile:1,2,4"));
		assertThrows(ToolException.class, () -> Targets.parse("loc:1@1,2,4"));
	}

	@Test
	void extraFieldsAreRejected() {
		assertThrows(ToolException.class, () -> Targets.parse("if:149"));
		assertThrows(ToolException.class, () -> Targets.parse("if:149:0:3:4"));
	}

	@Test
	void targetToStringIsItsFormat() throws Exception {
		assertEquals("npc:5", Targets.parse("npc:5").toString());
	}

	@Test
	void formatsMatchParsing() throws Exception {
		assertEquals(Targets.parse("npc:1423"), new NpcTarget(1423));
		assertEquals(Targets.parse("loc:1530@3222,3218,0"), new LocTarget(1530, 3222, 3218, 0));
		assertEquals(Targets.parse("if:149:0:3"), new ComponentTarget(149, 0, 3));
		assertEquals(Targets.parse("tile:3222,3218"), new TileTarget(3222, 3218, -1));
	}

	@Test
	void sceneKeysDecodeIdAndOrigin() {
		long key = 5L | 0x40000000L | 9L << 7 | 1530L << 32;

		assertEquals(1530, Targets.baseId(key));
		assertEquals(5, Targets.keySceneX(key));
		assertEquals(9, Targets.keySceneY(key));
	}

	@Test
	void sceneKeyIdIgnoresTheHighFlagBit() {
		long key = (long) 1530 << 32 | Long.MIN_VALUE;
		assertEquals(1530, Targets.baseId(key));
	}

	@Test
	void coordsConvertBothWays() {
		assertEquals(22, Coords.toScene(3222, 3200));
		assertEquals(3222, Coords.toWorld(22, 3200));
		assertEquals(-58, Coords.toScene(3200, 3258));
	}

	@Test
	void sceneBoundsAreChecked() {
		assertTrue(Coords.inScene(0, 0));
		assertTrue(Coords.inScene(103, 103));
		assertFalse(Coords.inScene(104, 0));
		assertFalse(Coords.inScene(-1, 0));
		assertFalse(Coords.inScene(0, 104));
	}

	@Test
	void planeBoundsAreChecked() {
		assertTrue(Coords.validPlane(0));
		assertTrue(Coords.validPlane(3));
		assertFalse(Coords.validPlane(-1));
		assertFalse(Coords.validPlane(4));
	}
}
