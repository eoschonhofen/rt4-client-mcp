package rt4.mcp;

import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * MCP-06/MCP-14 — chatbox dialogue detection.
 *
 * <p>530 mounts dialogue interfaces as sub-interfaces of the chatbox, so a dialogue is
 * open when the top-level interface, or any open sub-interface, is one of the chatbox
 * dialogue ids. MCP-14 adds the classification and option extraction on top of this.</p>
 */
public final class Dialogue {
	/** NPC chat heads (1–4 lines). */
	public static final Set<Integer> NPC_CHAT = new HashSet<Integer>(Arrays.asList(241, 242, 243, 244));
	/** Player chat heads (1–4 lines). */
	public static final Set<Integer> PLAYER_CHAT = new HashSet<Integer>(Arrays.asList(64, 65, 66, 67));
	/** Option menus (2–5 options). */
	public static final Set<Integer> OPTIONS = new HashSet<Integer>(Arrays.asList(228, 230, 232, 234));
	/** Plain message / "click here to continue". */
	public static final Set<Integer> MESSAGE = new HashSet<Integer>(Arrays.asList(210, 211, 212, 213, 214, 519));

	private static final Set<Integer> ALL = new HashSet<Integer>();

	static {
		ALL.addAll(NPC_CHAT);
		ALL.addAll(PLAYER_CHAT);
		ALL.addAll(OPTIONS);
		ALL.addAll(MESSAGE);
	}

	private Dialogue() {
	}

	/** Every interface id known to be a chatbox dialogue. */
	public static Set<Integer> dialogueInterfaces() {
		return ALL;
	}

	/** The dialogue interface currently mounted in the chatbox, or -1. */
	public static int openInterfaceId() {
		int top = InterfaceList.topLevelInterface;
		if (top != -1 && ALL.contains(top)) {
			return top;
		}
		HashTableIterator iterator = new HashTableIterator(InterfaceList.openInterfaces);
		for (ComponentPointer pointer = (ComponentPointer) iterator.first(); pointer != null; pointer = (ComponentPointer) iterator.next()) {
			if (ALL.contains(pointer.interfaceId)) {
				return pointer.interfaceId;
			}
		}
		return -1;
	}

	/** Whether a chatbox dialogue is on screen. */
	public static boolean isOpen() {
		return openInterfaceId() != -1;
	}
}
