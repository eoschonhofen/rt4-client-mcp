package rt4.mcp;

/**
 * MCP-10 — the drag packet (opcode 231) payload, built as bytes so the layout is
 * unit-testable without loading {@code rt4.Buffer}.
 *
 * <p>Matches {@code Protocol}: {@code p2(toSlot)}, {@code ip4(componentId)},
 * {@code p2add(fromSlot)}, {@code p1sub(inserting)}.</p>
 */
public final class DragPackets {
	/** The drag-and-drop opcode, {@code p1isaac}'d by the caller. */
	public static final int DRAG_OPCODE = 231;

	public static final int PAYLOAD_LENGTH = 9;

	private DragPackets() {
	}

	public static byte[] encode(int toSlot, int componentId, int fromSlot, int inserting) {
		byte[] out = new byte[PAYLOAD_LENGTH];
		int i = 0;
		// p2
		out[i++] = (byte) (toSlot >> 8);
		out[i++] = (byte) toSlot;
		// ip4 (little endian)
		out[i++] = (byte) componentId;
		out[i++] = (byte) (componentId >> 8);
		out[i++] = (byte) (componentId >> 16);
		out[i++] = (byte) (componentId >> 24);
		// p2add
		out[i++] = (byte) (fromSlot >> 8);
		out[i++] = (byte) (fromSlot + 128);
		// p1sub
		out[i] = (byte) (128 - inserting);
		return out;
	}

	/**
	 * Whether a drag acts as an insert (shifting the other slots) rather than a swap.
	 * The bank is the only component that inserts, and only when its varp says so.
	 */
	public static int insertFlag(int insertingVarp, int componentClientCode, int sourceObjId) {
		if (insertingVarp != 1 || componentClientCode != 206) {
			return 0;
		}
		return sourceObjId <= 0 ? 0 : 1;
	}
}
