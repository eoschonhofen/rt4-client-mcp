package rt4.mcp;

import rt4.Component;
import rt4.InterfaceList;
import rt4.LinkedList;
import rt4.LocType;
import rt4.LocTypeList;
import rt4.Npc;
import rt4.NpcList;
import rt4.ObjStack;
import rt4.ObjStackNode;
import rt4.Player;
import rt4.PlayerList;
import rt4.Scenery;
import rt4.SceneGraph;
import rt4.Tile;

/**
 * MCP-05 — parses, formats and (on the game thread) resolves target ids.
 *
 * <table>
 * <tr><td>{@code npc:<index>}</td><td>{@code npc:1423}</td><td>{@code NpcList.npcs[index]}</td></tr>
 * <tr><td>{@code player:<index>}</td><td>{@code player:7}</td><td>{@code PlayerList.players[index]}</td></tr>
 * <tr><td>{@code loc:<locId>@<x>,<y>,<plane>}</td><td>{@code loc:1530@3222,3218,0}</td><td>scenery/wall/decor</td></tr>
 * <tr><td>{@code obj:<objId>@<x>,<y>,<plane>}</td><td>{@code obj:995@3221,3219,0}</td><td>obj stack</td></tr>
 * <tr><td>{@code tile:<x>,<y>[,<plane>]}</td><td>{@code tile:3222,3218}</td><td>walk target</td></tr>
 * <tr><td>{@code if:<interfaceId>:<childId>[:<slot>]}</td><td>{@code if:149:0:3}</td><td>component/slot</td></tr>
 * </table>
 *
 * <p>Parse and format are pure; {@code resolve*} touch game state and must run inside
 * {@link GameThread#call}.</p>
 */
public final class Targets {
	private Targets() {
	}

	// ------------------------------------------------------------------ formatting

	public static String npc(int index) {
		return "npc:" + index;
	}

	public static String player(int index) {
		return "player:" + index;
	}

	public static String loc(int id, int x, int y, int plane) {
		return "loc:" + id + "@" + x + "," + y + "," + plane;
	}

	public static String obj(int id, int x, int y, int plane) {
		return "obj:" + id + "@" + x + "," + y + "," + plane;
	}

	public static String tile(int x, int y) {
		return "tile:" + x + "," + y;
	}

	public static String tile(int x, int y, int plane) {
		return "tile:" + x + "," + y + "," + plane;
	}

	public static String component(int interfaceId, int childId) {
		return "if:" + interfaceId + ":" + childId;
	}

	public static String slot(int interfaceId, int childId, int slot) {
		return "if:" + interfaceId + ":" + childId + ":" + slot;
	}

	// ------------------------------------------------------------------ parsing

	/** Parses one target id. The message is written for the agent to act on. */
	public static Target parse(String text) throws ToolException {
		if (text == null || text.trim().isEmpty()) {
			throw new ToolException("target is required");
		}
		String value = text.trim();
		int colon = value.indexOf(':');
		if (colon <= 0) {
			throw malformed(value, "<kind>:<args>, e.g. npc:1423 or loc:1530@3222,3218,0");
		}

		String kind = value.substring(0, colon);
		String rest = value.substring(colon + 1);
		if ("npc".equals(kind)) {
			return new NpcTarget(index(rest, value, "npc:<index>"));
		}
		if ("player".equals(kind)) {
			return new PlayerTarget(index(rest, value, "player:<index>"));
		}
		if ("loc".equals(kind)) {
			return parseLoc(rest, value);
		}
		if ("obj".equals(kind)) {
			return parseObj(rest, value);
		}
		if ("tile".equals(kind)) {
			return parseTile(rest, value);
		}
		if ("if".equals(kind)) {
			return parseComponent(rest, value);
		}
		throw new ToolException("unknown target kind '" + kind + "' in '" + value
				+ "'; expected npc, player, loc, obj, tile or if");
	}

	private static LocTarget parseLoc(String rest, String whole) throws ToolException {
		int at = rest.indexOf('@');
		if (at < 0) {
			throw malformed(whole, "loc:<locId>@<x>,<y>,<plane>, e.g. loc:1530@3222,3218,0");
		}
		int id = nonNegative(rest.substring(0, at), whole, "a non-negative loc id");
		int[] xyz = coordinates(rest.substring(at + 1), whole, true);
		return new LocTarget(id, xyz[0], xyz[1], xyz[2]);
	}

	private static ObjTarget parseObj(String rest, String whole) throws ToolException {
		int at = rest.indexOf('@');
		if (at < 0) {
			throw malformed(whole, "obj:<objId>@<x>,<y>,<plane>, e.g. obj:995@3221,3219,0");
		}
		int id = nonNegative(rest.substring(0, at), whole, "a non-negative obj id");
		int[] xyz = coordinates(rest.substring(at + 1), whole, true);
		return new ObjTarget(id, xyz[0], xyz[1], xyz[2]);
	}

	private static TileTarget parseTile(String rest, String whole) throws ToolException {
		int[] xyz = coordinates(rest, whole, false);
		return new TileTarget(xyz[0], xyz[1], xyz[2]);
	}

	private static ComponentTarget parseComponent(String rest, String whole) throws ToolException {
		String[] parts = rest.split(":", -1);
		if (parts.length < 2 || parts.length > 3) {
			throw malformed(whole, "if:<interfaceId>:<childId>[:<slot>], e.g. if:149:0 or if:149:0:3");
		}
		int interfaceId = nonNegative(parts[0], whole, "a non-negative interface id");
		int childId = nonNegative(parts[1], whole, "a non-negative child id");
		if (parts.length == 2) {
			return ComponentTarget.component(interfaceId, childId);
		}
		int slot = nonNegative(parts[2], whole, "a non-negative slot index");
		return new ComponentTarget(interfaceId, childId, slot);
	}

	/** Parses {@code x,y[,plane]}. The plane is -1 when omitted. */
	private static int[] coordinates(String rest, String whole, boolean planeRequired) throws ToolException {
		String[] parts = rest.split(",", -1);
		if (planeRequired ? parts.length != 3 : parts.length < 2 || parts.length > 3) {
			throw malformed(whole, planeRequired
					? "<id>@<x>,<y>,<plane> (all three required)"
					: "<x>,<y>[,<plane>], e.g. tile:3222,3218 or tile:3222,3218,1");
		}
		int x = nonNegative(parts[0], whole, "a non-negative x");
		int y = nonNegative(parts[1], whole, "a non-negative y");
		int plane = -1;
		if (parts.length == 3) {
			plane = nonNegative(parts[2], whole, "a non-negative plane");
			if (plane >= Coords.PLANES) {
				throw malformed(whole, "a plane between 0 and " + (Coords.PLANES - 1));
			}
		}
		return new int[]{x, y, plane};
	}

	private static int index(String rest, String whole, String expected) throws ToolException {
		int value = nonNegative(rest, whole, expected);
		return value;
	}

	private static int nonNegative(String part, String whole, String expected) throws ToolException {
		if (part == null || part.trim().isEmpty()) {
			throw malformed(whole, expected);
		}
		int value;
		try {
			value = Integer.parseInt(part.trim());
		} catch (NumberFormatException notANumber) {
			throw malformed(whole, expected);
		}
		if (value < 0) {
			throw malformed(whole, expected);
		}
		return value;
	}

	private static ToolException malformed(String whole, String expected) {
		return new ToolException("malformed target '" + whole + "': expected " + expected);
	}

	// ------------------------------------------------------------------ loc keys

	/** The base entity id packed into a scene key (a wall, decor or scenery key). */
	public static int baseId(long key) {
		return (int) (key >>> 32) & 0x7FFFFFFF;
	}

	/** The origin (south-west) scene x packed into a scene key. */
	public static int keySceneX(long key) {
		return (int) key & 0x7F;
	}

	/** The origin (south-west) scene y packed into a scene key. */
	public static int keySceneY(long key) {
		return (int) (key >> 7) & 0x7F;
	}

	// ------------------------------------------------------------------ live resolution

	public static Npc resolveNpc(NpcTarget target) throws ToolException {
		Npc npc = target.index < NpcList.npcs.length ? NpcList.npcs[target.index] : null;
		if (npc == null) {
			throw new ToolException("target gone: " + target.format());
		}
		return npc;
	}

	public static Player resolvePlayer(PlayerTarget target) throws ToolException {
		Player player = target.index < PlayerList.players.length ? PlayerList.players[target.index] : null;
		if (player == null) {
			throw new ToolException("target gone: " + target.format());
		}
		return player;
	}

	public static Component resolveComponent(ComponentTarget target) throws ToolException {
		Component component = InterfaceList.getComponent(target.interfaceId, target.childId);
		if (component == null) {
			throw new ToolException("target gone: " + target.format());
		}
		return component;
	}

	public static ResolvedLoc resolveLoc(LocTarget target) throws ToolException {
		int sceneX = Coords.sceneX(target.x);
		int sceneY = Coords.sceneY(target.y);
		if (!Coords.validPlane(target.plane) || !Coords.inScene(sceneX, sceneY)) {
			throw new ToolException("out of loaded scene: " + target.format());
		}

		long key = findLocKey(target.plane, sceneX, sceneY, target.id);
		if (key == 0L) {
			throw new ToolException("target gone: " + target.format());
		}

		LocType type = LocTypeList.get(target.id);
		if (type != null && type.multiLocs != null) {
			LocType resolved = type.getMultiLoc();
			if (resolved != null) {
				type = resolved;
			}
		}
		return new ResolvedLoc(target.id, type, key, keySceneX(key), keySceneY(key), target.plane);
	}

	public static ResolvedObj resolveObj(ObjTarget target) throws ToolException {
		int sceneX = Coords.sceneX(target.x);
		int sceneY = Coords.sceneY(target.y);
		if (!Coords.validPlane(target.plane) || !Coords.inScene(sceneX, sceneY)) {
			throw new ToolException("out of loaded scene: " + target.format());
		}

		ObjStack stack = findObjStack(target.plane, sceneX, sceneY, target.id);
		if (stack == null) {
			throw new ToolException("target gone: " + target.format());
		}
		return new ResolvedObj(target.id, stack, sceneX, sceneY, target.plane);
	}

	/** Resolves a tile to {@code {sceneX, sceneY, plane}}, defaulting the plane to the player's. */
	public static int[] resolveTile(TileTarget target) throws ToolException {
		int plane = target.planeOr(Player.plane);
		int sceneX = Coords.sceneX(target.x);
		int sceneY = Coords.sceneY(target.y);
		if (!Coords.validPlane(plane) || !Coords.inScene(sceneX, sceneY)) {
			throw new ToolException("out of loaded scene: " + target.format());
		}
		return new int[]{sceneX, sceneY, plane};
	}

	/** The scene key of the loc with {@code id} on that tile, or 0 when there is none. */
	public static long findLocKey(int plane, int sceneX, int sceneY, int id) {
		if (SceneGraph.tiles == null || SceneGraph.tiles[plane] == null) {
			return 0L;
		}
		Tile tile = SceneGraph.tiles[plane][sceneX][sceneY];
		if (tile == null) {
			return 0L;
		}
		if (tile.wall != null && baseId(tile.wall.key) == id && SceneGraph.isLocValid(plane, sceneX, sceneY, tile.wall.key)) {
			return tile.wall.key;
		}
		if (tile.wallDecor != null && baseId(tile.wallDecor.key) == id) {
			return tile.wallDecor.key;
		}
		if (tile.groundDecor != null && baseId(tile.groundDecor.key) == id) {
			return tile.groundDecor.key;
		}
		for (int i = 0; i < tile.sceneryLen; i++) {
			Scenery scenery = tile.scenery[i];
			if (scenery != null && baseId(scenery.key) == id) {
				return scenery.key;
			}
		}
		return 0L;
	}

	/** The ground-item stack of {@code id} on that tile, or null. */
	public static ObjStack findObjStack(int plane, int sceneX, int sceneY, int id) {
		if (SceneGraph.objStacks == null || SceneGraph.objStacks[plane] == null) {
			return null;
		}
		LinkedList stack = SceneGraph.objStacks[plane][sceneX][sceneY];
		if (stack == null) {
			return null;
		}
		for (ObjStackNode node = (ObjStackNode) stack.tail(); node != null; node = (ObjStackNode) stack.prev()) {
			if (node.value != null && node.value.type == id) {
				return node.value;
			}
		}
		return null;
	}

	/** A resolved loc: the live type plus the scene key the packets need. */
	public static final class ResolvedLoc {
		public final int id;
		public final LocType type;
		public final long key;
		public final int sceneX;
		public final int sceneY;
		public final int plane;

		ResolvedLoc(int id, LocType type, long key, int sceneX, int sceneY, int plane) {
			this.id = id;
			this.type = type;
			this.key = key;
			this.sceneX = sceneX;
			this.sceneY = sceneY;
			this.plane = plane;
		}

		public int worldX() {
			return Coords.worldX(sceneX);
		}

		public int worldY() {
			return Coords.worldY(sceneY);
		}

		public String format() {
			return Targets.loc(id, worldX(), worldY(), plane);
		}
	}

	/** A resolved ground item. */
	public static final class ResolvedObj {
		public final int id;
		public final ObjStack stack;
		public final int sceneX;
		public final int sceneY;
		public final int plane;

		ResolvedObj(int id, ObjStack stack, int sceneX, int sceneY, int plane) {
			this.id = id;
			this.stack = stack;
			this.sceneX = sceneX;
			this.sceneY = sceneY;
			this.plane = plane;
		}

		public int count() {
			return stack.amount;
		}

		public int worldX() {
			return Coords.worldX(sceneX);
		}

		public int worldY() {
			return Coords.worldY(sceneY);
		}

		public String format() {
			return Targets.obj(id, worldX(), worldY(), plane);
		}
	}
}
