package rt4.mcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DragPacketsTest {
	private static int unsigned(byte value) {
		return value & 0xFF;
	}

	@Test
	void payloadIsNineBytesInTheProtocolOrder() {
		byte[] encoded = DragPackets.encode(27, 0x00950000, 0, 0);

		assertEquals(9, encoded.length);
		// p2(toSlot)
		assertEquals(0, unsigned(encoded[0]));
		assertEquals(27, unsigned(encoded[1]));
		// ip4(componentId), little endian
		assertEquals(0x00, unsigned(encoded[2]));
		assertEquals(0x00, unsigned(encoded[3]));
		assertEquals(0x95, unsigned(encoded[4]));
		assertEquals(0x00, unsigned(encoded[5]));
		// p2add(fromSlot)
		assertEquals(0, unsigned(encoded[6]));
		assertEquals(128, unsigned(encoded[7]));
		// p1sub(inserting)
		assertEquals(128, unsigned(encoded[8]));
	}

	@Test
	void largeValuesAreBigEndianForP2AndLittleEndianForIp4() {
		byte[] encoded = DragPackets.encode(0x1234, 0x89ABCDEF, 0x0F0F, 0);

		assertEquals(0x12, unsigned(encoded[0]));
		assertEquals(0x34, unsigned(encoded[1]));
		assertEquals(0xEF, unsigned(encoded[2]));
		assertEquals(0xCD, unsigned(encoded[3]));
		assertEquals(0xAB, unsigned(encoded[4]));
		assertEquals(0x89, unsigned(encoded[5]));
		assertEquals(0x0F, unsigned(encoded[6]));
		assertEquals((0x0F + 128) & 0xFF, unsigned(encoded[7]));
	}

	@Test
	void insertingIsSubtractedFrom128() {
		assertEquals(128, unsigned(DragPackets.encode(0, 0, 0, 0)[8]));
		assertEquals(127, unsigned(DragPackets.encode(0, 0, 0, 1)[8]));
	}

	@Test
	void fromSlotIsOffsetBy128() {
		assertEquals(129, unsigned(DragPackets.encode(0, 0, 1, 0)[7]));
		assertEquals(255, unsigned(DragPackets.encode(0, 0, 127, 0)[7]));
		assertEquals(0, unsigned(DragPackets.encode(0, 0, 128, 0)[7]));
	}

	@Test
	void insertFlagOnlyForTheBankInInsertMode() {
		// Component clientCode 206 is the bank, varp 1 is insert mode.
		assertEquals(1, DragPackets.insertFlag(1, 206, 995));
		assertEquals(0, DragPackets.insertFlag(0, 206, 995));
		assertEquals(0, DragPackets.insertFlag(1, 0, 995));
		assertEquals(0, DragPackets.insertFlag(1, 206, -1));
		assertEquals(0, DragPackets.insertFlag(1, 206, 0));
	}
}
