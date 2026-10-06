package rt4.mcp;

import rt4.Cs1ScriptRunner;
import rt4.JagString;
import rt4.LocalizedText;
import rt4.MiniMenu;
import rt4.Mouse;
import rt4.Npc;
import rt4.ObjType;
import rt4.ObjTypeList;
import rt4.Player;
import rt4.Rasteriser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MCP-08 — builds the minimenu entries the UI would show for a target, without hovering it,
 * and executes one through the real {@link MiniMenu#doAction}.
 *
 * <p>The live menu is saved before building and restored afterwards, so a real right-click
 * menu is untouched and the selection fields ({@code selectedObjId}, spell targeting) are
 * never clobbered. Only the entry arrays and {@code size} are saved.</p>
 */
public final class MenuSynth {
	/** The ack of a queued action. {@code ok} means the packet was queued, not that it worked. */
	public static final class Ack {
		public final String op;
		public final String subject;
		public final String target;
		public final long tick;

		Ack(String op, String subject, String target, long tick) {
			this.op = op;
			this.subject = subject;
			this.target = target;
			this.tick = tick;
		}
	}

	private static final int CAPACITY = 500;

	private static final class Snapshot {
		int size;
		boolean menuOpen;
		final JagString[] ops = new JagString[CAPACITY];
		final JagString[] opBases = new JagString[CAPACITY];
		final short[] actions = new short[CAPACITY];
		final int[] cursors = new int[CAPACITY];
		final long[] keys = new long[CAPACITY];
		final int[] intArgs1 = new int[CAPACITY];
		final int[] intArgs2 = new int[CAPACITY];
	}

	private MenuSynth() {
	}

	/**
	 * The entries for a target, in the order the menu is drawn (the first one is the
	 * left-click default). Leaves the live menu exactly as it was.
	 */
	public static List<EntryMatcher.Entry> list(Target target) throws ToolException {
		Snapshot saved = save();
		try {
			populate(target);
			MiniMenu.sort();
			return entries();
		} finally {
			restore(saved);
		}
	}

	/** Builds the menu and runs the matching entry through {@code MiniMenu.doAction}. */
	public static Ack act(Target target, String op, String subject) throws ToolException {
		Snapshot saved = save();
		try {
			populate(target);
			MiniMenu.sort();
			List<EntryMatcher.Entry> entries = entries();
			EntryMatcher.Entry match = EntryMatcher.match(entries, op, subject, target.format());

			// Purely cosmetic: the yellow/red Cross is drawn at the click point.
			Mouse.clickX = Rasteriser.centerX;
			Mouse.clickY = Rasteriser.centerY;

			MiniMenu.doAction(match.index);
			return new Ack(op, match.subject, target.format(), GameThread.frame());
		} finally {
			restore(saved);
		}
	}

	/** Clears "Use item" and spell targeting. */
	public static void cancelSelection() {		MiniMenu.cancelTargeting();
		MiniMenu.itemTargetMode = 0;
		MiniMenu.selectedObjId = 0;
		MiniMenu.selectedObjSlot = 0;
		MiniMenu.selectedObjText = null;
		MiniMenu.targetOpBase = null;
		MiniMenu.targetVerb = null;
		MiniMenu.targetMask = 0;
		MiniMenu.targetParamId = -1;
		MiniMenu.targetChildId = -1;
		MiniMenu.targetInterfaceId = 0;
	}

	// ------------------------------------------------------------------ building

	private static void populate(Target target) throws ToolException {
		MiniMenu.size = 0;
		// MenuSynth must be able to add entries even while a CS2 menu is open; the saved
		// value is restored straight afterwards.
		Cs1ScriptRunner.isMenuOpen = false;

		if (target instanceof NpcTarget) {
			NpcTarget npcTarget = (NpcTarget) target;
			Npc npc = Targets.resolveNpc(npcTarget);
			MiniMenu.addNpcEntries(npc.type, npc.movementQueueX[0], npcTarget.index, npc.movementQueueY[0]);
		} else if (target instanceof PlayerTarget) {
			PlayerTarget playerTarget = (PlayerTarget) target;
			Player player = Targets.resolvePlayer(playerTarget);
			MiniMenu.addPlayerEntries(playerTarget.index, player.movementQueueY[0], player, player.movementQueueX[0]);
		} else if (target instanceof LocTarget) {
			Targets.ResolvedLoc loc = Targets.resolveLoc((LocTarget) target);
			MiniMenu.addLocEntries(loc.key, loc.sceneX, loc.sceneY);
		} else if (target instanceof ObjTarget) {
			ObjTarget objTarget = (ObjTarget) target;
			int sceneX = Coords.sceneX(objTarget.x);
			int sceneY = Coords.sceneY(objTarget.y);
			if (!Coords.validPlane(objTarget.plane) || !Coords.inScene(sceneX, sceneY)) {
				throw new ToolException("out of loaded scene: " + objTarget.format());
			}
			MiniMenu.addObjStackEntries(sceneX, sceneY);
			keepOnlyObjEntries(objTarget.id);
		} else if (target instanceof TileTarget) {
			populateTile((TileTarget) target);
		} else {
			throw new ToolException("this target kind is not supported yet: " + target.format());
		}
	}

	/**
	 * A synthetic WALK_HERE entry. The key is 1, which is the branch {@code doAction} uses
	 * to run {@code PathFinder.findPath(moveType=1)}; findPath sends the walk packet
	 * itself, so the walk goes out in this frame.
	 */
	private static void populateTile(TileTarget target) throws ToolException {
		int[] resolved = Targets.resolveTile(target);
		JagString label = MiniMenu.walkText == null ? LocalizedText.WALKHERE : MiniMenu.walkText;
		MiniMenu.add(-1, 1L, JagString.EMPTY, resolved[0], (short) MiniMenu.WALK_HERE, label, resolved[1]);
	}

	/** {@code addObjStackEntries} adds every stack on the tile; keep the requested item's. */
	private static void keepOnlyObjEntries(int objId) {
		int kept = 0;
		for (int i = 0; i < MiniMenu.size; i++) {
			if (MiniMenu.keys[i] != objId) {
				continue;
			}
			if (kept != i) {
				MiniMenu.ops[kept] = MiniMenu.ops[i];
				MiniMenu.opBases[kept] = MiniMenu.opBases[i];
				MiniMenu.actions[kept] = MiniMenu.actions[i];
				MiniMenu.cursors[kept] = MiniMenu.cursors[i];
				MiniMenu.keys[kept] = MiniMenu.keys[i];
				MiniMenu.intArgs1[kept] = MiniMenu.intArgs1[i];
				MiniMenu.intArgs2[kept] = MiniMenu.intArgs2[i];
			}
			kept++;
		}
		MiniMenu.size = kept;
	}

	/** Copies the live entries out in display order: index size-1 first, the left-click default. */
	private static List<EntryMatcher.Entry> entries() {
		List<EntryMatcher.Entry> out = new ArrayList<EntryMatcher.Entry>();
		for (int i = MiniMenu.size - 1; i >= 0; i--) {
			out.add(new EntryMatcher.Entry(i, Names.plain(MiniMenu.ops[i]), Names.plain(MiniMenu.opBases[i]), MiniMenu.actions[i]));
		}
		return Collections.unmodifiableList(out);
	}

	// ------------------------------------------------------------------ menu state

	private static Snapshot save() {
		Snapshot saved = new Snapshot();
		saved.size = MiniMenu.size;
		saved.menuOpen = Cs1ScriptRunner.isMenuOpen;
		if (saved.size > 0) {
			System.arraycopy(MiniMenu.ops, 0, saved.ops, 0, saved.size);
			System.arraycopy(MiniMenu.opBases, 0, saved.opBases, 0, saved.size);
			System.arraycopy(MiniMenu.actions, 0, saved.actions, 0, saved.size);
			System.arraycopy(MiniMenu.cursors, 0, saved.cursors, 0, saved.size);
			System.arraycopy(MiniMenu.keys, 0, saved.keys, 0, saved.size);
			System.arraycopy(MiniMenu.intArgs1, 0, saved.intArgs1, 0, saved.size);
			System.arraycopy(MiniMenu.intArgs2, 0, saved.intArgs2, 0, saved.size);
		}
		return saved;
	}

	private static void restore(Snapshot saved) {
		if (saved.size > 0) {
			System.arraycopy(saved.ops, 0, MiniMenu.ops, 0, saved.size);
			System.arraycopy(saved.opBases, 0, MiniMenu.opBases, 0, saved.size);
			System.arraycopy(saved.actions, 0, MiniMenu.actions, 0, saved.size);
			System.arraycopy(saved.cursors, 0, MiniMenu.cursors, 0, saved.size);
			System.arraycopy(saved.keys, 0, MiniMenu.keys, 0, saved.size);
			System.arraycopy(saved.intArgs1, 0, MiniMenu.intArgs1, 0, saved.size);
			System.arraycopy(saved.intArgs2, 0, MiniMenu.intArgs2, 0, saved.size);
		}
		MiniMenu.size = saved.size;
		Cs1ScriptRunner.isMenuOpen = saved.menuOpen;
	}

	/** The subject line the UI would show for an item stack, used by the helper tools. */
	public static String objName(int objId) {
		ObjType type = ObjTypeList.get(objId);
		return type == null ? null : Names.plain(type.name);
	}

	/** An empty entry list, for targets that offer nothing. */
	public static List<EntryMatcher.Entry> emptyEntries() {
		return Collections.emptyList();
	}

	/**
	 * A human-readable note when "Use item" or a spell is selected, so {@code list_actions}
	 * can warn that the next {@code do_action} will consume the selection.
	 */
	public static String selectionHint() {
		if (MiniMenu.itemTargetMode == 1) {
			String item = Names.plain(MiniMenu.selectedObjText);
			return "using " + (item == null ? "an item" : item)
					+ "; the next do_action consumes it (cancel_selection clears it)";
		}
		if (MiniMenu.isTargeting) {
			String verb = Names.plain(MiniMenu.targetVerb);
			String subject = Names.plain(MiniMenu.targetOpBase);
			return "casting " + (verb == null ? "a spell" : verb) + " on " + (subject == null ? "a target" : subject)
					+ "; the next do_action consumes it (cancel_selection clears it)";
		}
		return null;
	}
}
