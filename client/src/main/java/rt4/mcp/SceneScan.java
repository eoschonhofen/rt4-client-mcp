package rt4.mcp;

import com.google.gson.JsonObject;
import rt4.JagString;
import rt4.LinkedList;
import rt4.LocType;
import rt4.LocTypeList;
import rt4.Npc;
import rt4.NpcList;
import rt4.NpcType;
import rt4.ObjStack;
import rt4.ObjStackNode;
import rt4.ObjType;
import rt4.ObjTypeList;
import rt4.Player;
import rt4.PlayerList;
import rt4.Scenery;
import rt4.SceneGraph;
import rt4.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * MCP-07 — walks each entity source in the loaded scene and produces
 * {@link EntityFilter.Entity} records. Game thread only.
 *
 * <p>Coordinates reported to the agent are always world coordinates. Locs are deduped by
 * scene key and reported at their origin (south-west) tile, which is what the loc packet
 * in {@code MiniMenu.doAction} needs.</p>
 */
public final class SceneScan {
	private SceneScan() {
	}

	public static List<EntityFilter.Entity> scan(String type, int radius) {
		List<EntityFilter.Entity> all = new ArrayList<EntityFilter.Entity>();
		boolean any = type == null || type.isEmpty() || "any".equalsIgnoreCase(type);
		if (any || "npc".equalsIgnoreCase(type)) {
			all.addAll(npcs(radius));
		}
		if (any || "player".equalsIgnoreCase(type)) {
			all.addAll(players(radius));
		}
		if (any || "loc".equalsIgnoreCase(type)) {
			all.addAll(locs(radius));
		}
		if (any || "obj".equalsIgnoreCase(type)) {
			all.addAll(groundItems(radius));
		}
		return all;
	}

	// ------------------------------------------------------------------ npcs

	public static List<EntityFilter.Entity> npcs(int radius) {
		List<EntityFilter.Entity> out = new ArrayList<EntityFilter.Entity>();
		int playerX = playerSceneX();
		int playerY = playerSceneY();
		int plane = Player.plane;

		for (int i = 0; i < NpcList.size; i++) {
			int index = NpcList.ids[i];
			if (index < 0 || index >= NpcList.npcs.length) {
				continue;
			}
			Npc npc = NpcList.npcs[index];
			if (npc == null || npc.type == null) {
				continue;
			}

			NpcType type = npc.type;
			if (type.multiNpcs != null) {
				NpcType resolved = type.getMultiNpc();
				if (resolved != null) {
					type = resolved;
				}
			}
			if (!type.interactive) {
				continue;
			}
			String name = Names.plain(type.name);
			if (isNullName(name)) {
				continue;
			}

			int sceneX = npc.movementQueueX[0];
			int sceneY = npc.movementQueueY[0];
			int size = Math.max(1, type.size);
			int distance = boxDistance(playerX, playerY, sceneX, sceneY, sceneX + size - 1, sceneY + size - 1);
			if (distance > radius) {
				continue;
			}

			JsonObject extra = new JsonObject();
			extra.addProperty("combat_level", type.combatLevel);
			extra.addProperty("animation", npc.seqId);
			String interacting = rt4.mcp.tools.StatusTools.interacting(npc.faceEntity);
			if (interacting != null) {
				extra.addProperty("interacting", interacting);
			}

			out.add(new EntityFilter.Entity(Targets.npc(index), "npc", type.id, name,
					Coords.worldX(sceneX), Coords.worldY(sceneY), plane, distance, opsOf(type.ops), extra));
		}
		return out;
	}

	// ------------------------------------------------------------------ players

	public static List<EntityFilter.Entity> players(int radius) {
		List<EntityFilter.Entity> out = new ArrayList<EntityFilter.Entity>();
		int playerX = playerSceneX();
		int playerY = playerSceneY();
		int plane = Player.plane;

		for (int i = 0; i < PlayerList.size; i++) {
			int index = PlayerList.ids[i];
			if (index < 0 || index >= PlayerList.players.length) {
				continue;
			}
			Player player = PlayerList.players[index];
			if (player == null || player == PlayerList.self) {
				continue;
			}

			int sceneX = player.movementQueueX[0];
			int sceneY = player.movementQueueY[0];
			int size = Math.max(1, player.getSize());
			int distance = boxDistance(playerX, playerY, sceneX, sceneY, sceneX + size - 1, sceneY + size - 1);
			if (distance > radius) {
				continue;
			}

			JsonObject extra = new JsonObject();
			extra.addProperty("combat_level", player.combatLevel);

			out.add(new EntityFilter.Entity(Targets.player(index), "player", index, Names.plain(player.getName()),
					Coords.worldX(sceneX), Coords.worldY(sceneY), plane, distance, opsOf(Player.options), extra));
		}
		return out;
	}

	// ------------------------------------------------------------------ ground items

	public static List<EntityFilter.Entity> groundItems(int radius) {
		List<EntityFilter.Entity> out = new ArrayList<EntityFilter.Entity>();
		int plane = Player.plane;
		if (SceneGraph.objStacks == null || plane < 0 || plane >= SceneGraph.objStacks.length || SceneGraph.objStacks[plane] == null) {
			return out;
		}
		int playerX = playerSceneX();
		int playerY = playerSceneY();

		int minX = Math.max(0, playerX - radius);
		int maxX = Math.min(Coords.SCENE_SIZE - 1, playerX + radius);
		int minY = Math.max(0, playerY - radius);
		int maxY = Math.min(Coords.SCENE_SIZE - 1, playerY + radius);

		for (int sceneX = minX; sceneX <= maxX; sceneX++) {
			for (int sceneY = minY; sceneY <= maxY; sceneY++) {
				LinkedList stack = SceneGraph.objStacks[plane][sceneX][sceneY];
				if (stack == null) {
					continue;
				}
				for (ObjStackNode node = (ObjStackNode) stack.tail(); node != null; node = (ObjStackNode) stack.prev()) {
					ObjStack object = node.value;
					if (object == null) {
						continue;
					}
					ObjType type = ObjTypeList.get(object.type);
					if (type == null) {
						continue;
					}
					int distance = chebyshev(playerX, playerY, sceneX, sceneY);
					if (distance > radius) {
						continue;
					}
					JsonObject extra = new JsonObject();
					extra.addProperty("count", object.amount);
					out.add(new EntityFilter.Entity(
							Targets.obj(object.type, Coords.worldX(sceneX), Coords.worldY(sceneY), plane), "obj",
							object.type, Names.plain(type.name), Coords.worldX(sceneX), Coords.worldY(sceneY),
							plane, distance, opsOf(type.ops), extra));
				}
			}
		}
		return out;
	}

	// ------------------------------------------------------------------ locs

	public static List<EntityFilter.Entity> locs(int radius) {
		List<EntityFilter.Entity> out = new ArrayList<EntityFilter.Entity>();
		int plane = Player.plane;
		if (SceneGraph.tiles == null || plane < 0 || plane >= SceneGraph.tiles.length || SceneGraph.tiles[plane] == null) {
			return out;
		}
		int playerX = playerSceneX();
		int playerY = playerSceneY();

		int minX = Math.max(0, playerX - radius);
		int maxX = Math.min(Coords.SCENE_SIZE - 1, playerX + radius);
		int minY = Math.max(0, playerY - radius);
		int maxY = Math.min(Coords.SCENE_SIZE - 1, playerY + radius);

		Set<Long> seen = new HashSet<Long>();
		for (int sceneX = minX; sceneX <= maxX; sceneX++) {
			for (int sceneY = minY; sceneY <= maxY; sceneY++) {
				Tile tile = SceneGraph.tiles[plane][sceneX][sceneY];
				if (tile == null) {
					continue;
				}
				if (tile.wall != null) {
					addLoc(out, seen, tile.wall.key, sceneX, sceneY, sceneX, sceneY, plane, playerX, playerY);
				}
				if (tile.wallDecor != null) {
					addLoc(out, seen, tile.wallDecor.key, sceneX, sceneY, sceneX, sceneY, plane, playerX, playerY);
				}
				if (tile.groundDecor != null) {
					addLoc(out, seen, tile.groundDecor.key, sceneX, sceneY, sceneX, sceneY, plane, playerX, playerY);
				}
				for (int i = 0; i < tile.sceneryLen; i++) {
					Scenery scenery = tile.scenery[i];
					if (scenery != null) {
						addLoc(out, seen, scenery.key, scenery.xMin, scenery.yMin, scenery.xMax, scenery.yMax,
								plane, playerX, playerY);
					}
				}
			}
		}
		return out;
	}

	private static void addLoc(List<EntityFilter.Entity> out, Set<Long> seen, long key,
							   int minX, int minY, int maxX, int maxY, int plane, int playerX, int playerY) {
		if (key == 0L || !seen.add(key)) {
			return;
		}
		int id = Targets.baseId(key);
		LocType type = LocTypeList.get(id);
		if (type == null) {
			return;
		}

		LocType resolved = type;
		if (type.multiLocs != null) {
			LocType multi = type.getMultiLoc();
			if (multi != null) {
				resolved = multi;
			}
		}
		String name = Names.plain(resolved.name);
		if (isNullName(name)) {
			name = Names.plain(type.name);
		}
		if (isNullName(name)) {
			return;
		}

		int distance = boxDistance(playerX, playerY, minX, minY, maxX, maxY);
		int originX = Targets.keySceneX(key);
		int originY = Targets.keySceneY(key);

		JsonObject extra = new JsonObject();
		extra.addProperty("shape", (int) (key >> 14) & 0x3F);
		extra.addProperty("rotation", (int) (key >> 20) & 0x1FF);
		extra.addProperty("size_x", maxX - minX + 1);
		extra.addProperty("size_y", maxY - minY + 1);

		out.add(new EntityFilter.Entity(Targets.loc(id, Coords.worldX(originX), Coords.worldY(originY), plane),
				"loc", id, name, Coords.worldX(originX), Coords.worldY(originY), plane, distance,
				opsOf(resolved.ops), extra));
	}

	// ------------------------------------------------------------------ helpers

	private static int playerSceneX() {
		return PlayerList.self.movementQueueX[0];
	}

	private static int playerSceneY() {
		return PlayerList.self.movementQueueY[0];
	}

	/** Chebyshev distance from a tile to a box, 0 when the tile is inside it. */
	public static int boxDistance(int x, int y, int minX, int minY, int maxX, int maxY) {
		int dx = x < minX ? minX - x : (x > maxX ? x - maxX : 0);
		int dy = y < minY ? minY - y : (y > maxY ? y - maxY : 0);
		return Math.max(dx, dy);
	}

	public static int chebyshev(int x1, int y1, int x2, int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	public static List<String> opsOf(JagString[] ops) {
		List<String> out = new ArrayList<String>();
		if (ops != null) {
			for (JagString op : ops) {
				if (op != null) {
					String text = Names.plain(op);
					if (text != null && !text.isEmpty()) {
						out.add(text);
					}
				}
			}
		}
		return out;
	}

	public static boolean isNullName(String name) {
		return name == null || name.isEmpty() || "null".equals(name.toLowerCase(Locale.ROOT));
	}
}
