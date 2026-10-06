package rt4.mcp.nav;

import rt4.Camera;
import rt4.Mouse;
import rt4.Player;
import rt4.PlayerList;
import rt4.mcp.Coords;
import rt4.mcp.MenuSynth;
import rt4.mcp.Target;
import rt4.mcp.Targets;
import rt4.mcp.TileTarget;

import java.util.Map;

/**
 * MCP-13 — the live {@link NavTask.Driver}: reads positions and collision from the client and
 * sends walks and door opens through the real minimenu.
 */
public final class LiveNavDriver implements NavTask.Driver {
	private static final int DOOR_SCAN_RADIUS = 52;

	@Override
	public int playerSceneX() {
		return PlayerList.self.movementQueueX[0];
	}

	@Override
	public int playerSceneY() {
		return PlayerList.self.movementQueueY[0];
	}

	@Override
	public int plane() {
		return Player.plane;
	}

	@Override
	public int originX() {
		return Camera.originX;
	}

	@Override
	public int originY() {
		return Camera.originY;
	}

	@Override
	public CollisionSource collision() {
		return new SceneCollision(Player.plane);
	}

	@Override
	public Map<Integer, DoorIndex.Door> doors() {
		return DoorIndex.doors(DOOR_SCAN_RADIUS);
	}

	@Override
	public void walk(int sceneX, int sceneY) throws Exception {
		Target target = new TileTarget(Coords.worldX(sceneX), Coords.worldY(sceneY), Player.plane);
		MenuSynth.act(target, "Walk here", null);
	}

	@Override
	public void open(String target) throws Exception {
		MenuSynth.act(Targets.parse(target), "Open", null);
	}

	@Override
	public boolean doorPassable(int sceneX, int sceneY) {
		CollisionSource collision = collision();
		// The server clears the wall flags when the door opens.
		return (collision.flags(sceneX, sceneY) & (AStar.WEST_MASK | AStar.EAST_MASK | AStar.NORTH_MASK | AStar.SOUTH_MASK)) == 0;
	}

	@Override
	public int realPressSeq() {
		return Mouse.realPressSeq;
	}

	@Override
	public String doorTarget(int sceneX, int sceneY) {
		Map<Integer, DoorIndex.Door> doors = doors();
		DoorIndex.Door door = doors.get(AStar.key(sceneX, sceneY));
		if (door != null) {
			return door.target;
		}
		return null;
	}
}
