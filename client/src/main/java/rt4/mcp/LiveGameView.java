package rt4.mcp;

import rt4.Chat;
import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;
import rt4.Inv;
import rt4.Npc;
import rt4.NpcList;
import rt4.Player;
import rt4.PlayerList;
import rt4.PlayerSkillXpTable;
import rt4.client;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * MCP-12 — the real {@link Conditions.GameView}, reading the client's statics. Every method
 * runs on the game thread.
 */
public final class LiveGameView implements Conditions.GameView {
	private static final int HP_SKILL = 3;

	@Override
	public boolean loggedIn() {
		return client.gameState == 30 && PlayerList.self != null;
	}

	@Override
	public boolean loggedOut() {
		return client.gameState == 10;
	}

	@Override
	public int tick() {
		return TickTracker.tick();
	}

	@Override
	public int frame() {
		return (int) GameThread.frame();
	}

	@Override
	public boolean idle() {
		return PlayerList.self != null && rt4.mcp.tools.StatusTools.isIdle(PlayerList.self);
	}

	@Override
	public int x() {
		return Coords.worldX(PlayerList.self.movementQueueX[0]);
	}

	@Override
	public int y() {
		return Coords.worldY(PlayerList.self.movementQueueY[0]);
	}

	@Override
	public int plane() {
		return Player.plane;
	}

	@Override
	public boolean dialogueOpen() {
		return Dialogue.isOpen();
	}

	@Override
	public boolean interfaceOpen(int interfaceId) {
		if (InterfaceList.topLevelInterface == interfaceId) {
			return true;
		}
		if (InterfaceList.openInterfaces == null) {
			return false;
		}
		HashTableIterator iterator = new HashTableIterator(InterfaceList.openInterfaces);
		for (ComponentPointer pointer = (ComponentPointer) iterator.first(); pointer != null; pointer = (ComponentPointer) iterator.next()) {
			if (pointer.interfaceId == interfaceId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int inventoryHash() {
		Inv inventory = (Inv) Inv.objectContainerCache.get(rt4.mcp.tools.StatusTools.BACKPACK_INVENTORY);
		if (inventory == null) {
			return 0;
		}
		int hash = 1;
		for (int slot = 0; slot < inventory.objectIds.length; slot++) {
			hash = hash * 31 + inventory.objectIds[slot];
			hash = hash * 31 + inventory.objectStackSizes[slot];
		}
		return hash;
	}

	@Override
	public int itemCount(int itemId) {
		Inv inventory = (Inv) Inv.objectContainerCache.get(rt4.mcp.tools.StatusTools.BACKPACK_INVENTORY);
		if (inventory == null) {
			return 0;
		}
		int total = 0;
		for (int slot = 0; slot < inventory.objectIds.length; slot++) {
			if (inventory.objectIds[slot] == itemId) {
				total += inventory.objectStackSizes[slot];
			}
		}
		return total;
	}

	@Override
	public int totalXp(int skill) {
		if (skill >= 0) {
			return PlayerSkillXpTable.experience[skill];
		}
		int total = 0;
		for (int value : PlayerSkillXpTable.experience) {
			total += value;
		}
		return total;
	}

	@Override
	public int hp() {
		return PlayerSkillXpTable.boostedLevels[HP_SKILL];
	}

	@Override
	public boolean entityAlive(String target, int expectedType) {
		Target parsed;
		try {
			parsed = Targets.parse(target);
		} catch (ToolException malformed) {
			return false;
		}
		if (parsed instanceof NpcTarget) {
			int index = ((NpcTarget) parsed).index;
			Npc npc = index < NpcList.npcs.length ? NpcList.npcs[index] : null;
			if (npc == null || npc.type == null) {
				return false;
			}
			return expectedType < 0 || npc.type.id == expectedType;
		}
		if (parsed instanceof PlayerTarget) {
			int index = ((PlayerTarget) parsed).index;
			return index < PlayerList.players.length && PlayerList.players[index] != null;
		}
		if (parsed instanceof LocTarget) {
			return Targets.findLocKey(((LocTarget) parsed).plane,
					Coords.sceneX(((LocTarget) parsed).x), Coords.sceneY(((LocTarget) parsed).y),
					((LocTarget) parsed).id) != 0L;
		}
		if (parsed instanceof ObjTarget) {
			return Targets.findObjStack(((ObjTarget) parsed).plane,
					Coords.sceneX(((ObjTarget) parsed).x), Coords.sceneY(((ObjTarget) parsed).y),
					((ObjTarget) parsed).id) != null;
		}
		return true;
	}

	@Override
	public int entityType(String target) {
		try {
			Target parsed = Targets.parse(target);
			if (parsed instanceof NpcTarget) {
				int index = ((NpcTarget) parsed).index;
				Npc npc = index < NpcList.npcs.length ? NpcList.npcs[index] : null;
				return npc == null || npc.type == null ? -1 : npc.type.id;
			}
		} catch (ToolException ignored) {
			// Treated as gone.
		}
		return -1;
	}

	@Override
	public boolean navFinished(int task) {
		return rt4.mcp.nav.NavTask.isFinished(task);
	}

	@Override
	public int animation() {
		return PlayerList.self == null ? -1 : PlayerList.self.seqId;
	}

	@Override
	public boolean chatMatches(int sinceSize, Pattern regex, Set<Integer> types) {
		int added = Chat.size - sinceSize;
		if (added <= 0) {
			return false;
		}
		int buffered = Math.min(added, Chat.messages.length);
		for (int i = 0; i < buffered; i++) {
			if (types != null && !types.isEmpty() && !types.contains(Chat.types[i])) {
				continue;
			}
			String text = Names.plain(Chat.messages[i]);
			String name = Names.plain(Chat.names[i]);
			if (regex != null && ((text != null && regex.matcher(text).find())
					|| (name != null && regex.matcher(name).find()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int chatSizeHint() {
		return Chat.size;
	}
}
