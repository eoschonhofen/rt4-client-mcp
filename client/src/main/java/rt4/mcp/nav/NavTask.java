package rt4.mcp.nav;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MCP-13 — the background navigation driver, stepped once per frame from
 * {@code GameThread.drain()}.
 *
 * <p>States: {@code PLANNING -> WALKING -> OPENING_DOOR -> WALKING ... -> ARRIVED | FAILED |
 * CANCELLED}. All game access goes through {@link Driver}, so the state machine is tested
 * with a fake.</p>
 */
public final class NavTask {
	public enum State {
		PLANNING,
		WALKING,
		OPENING_DOOR,
		ARRIVED,
		FAILED,
		CANCELLED
	}

	/** Everything the driver needs from the live game. */
	public interface Driver {
		int playerSceneX();

		int playerSceneY();

		int plane();

		int originX();

		int originY();

		CollisionSource collision();

		Map<Integer, DoorIndex.Door> doors();

		void walk(int sceneX, int sceneY) throws Exception;

		void open(String target) throws Exception;

		boolean doorPassable(int sceneX, int sceneY);

		int realPressSeq();

		String doorTarget(int sceneX, int sceneY);
	}

	public static final int MAX_LEG = 20;
	public static final int STUCK_FRAMES = 8;
	public static final int DOOR_WAIT_FRAMES = 6;
	public static final int MAX_DOOR_ATTEMPTS = 2;
	/** How many finished task outcomes to remember for {@code wait_for(nav_done)}. */
	static final int FINISHED_HISTORY = 16;

	private static volatile NavTask active;
	private static volatile int lastFinishedId = -1;
	private static volatile String lastFinishedState;
	private static volatile String lastFinishedReason;
	private static volatile Driver driver = new LiveNavDriver();
	private static int nextId = 1;

	/** MCP-20 — the ids (and states) of recently finished tasks, newest last. */
	private static final Map<Integer, String> FINISHED = Collections.synchronizedMap(
			new LinkedHashMap<Integer, String>() {
				@Override
				protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
					return size() > FINISHED_HISTORY;
				}
			});

	private final int id;
	private final int goalSceneX;
	private final int goalSceneY;
	private final int goalPlane;
	private final int radius;
	private final int startPressSeq;

	private State state = State.PLANNING;
	private String reason;
	private AStar.Path path;
	private int legFrom;
	private int legEnd;
	private int legDoorKey = -1;
	private int legsDone;
	private int doorsOpened;
	private int stuckFrames;
	private int replans;
	private int doorAttempts;
	private int doorWaitFrames;
	private int doorKey = -1;
	private int lastX = Integer.MIN_VALUE;
	private int lastY = Integer.MIN_VALUE;
	private int lastOriginX = Integer.MIN_VALUE;
	private int lastOriginY = Integer.MIN_VALUE;
	private boolean replanRequested;
	private boolean doorAttempted;

	NavTask(int id, int goalSceneX, int goalSceneY, int goalPlane, int radius, int startPressSeq) {
		this.id = id;
		this.goalSceneX = goalSceneX;
		this.goalSceneY = goalSceneY;
		this.goalPlane = goalPlane;
		this.radius = radius;
		this.startPressSeq = startPressSeq;
	}

	// ------------------------------------------------------------------ public surface

	public static void setDriver(Driver newDriver) {
		driver = newDriver == null ? new LiveNavDriver() : newDriver;
	}

	public static Driver driver() {
		return driver;
	}

	/** Replaces any running task. Call from the game thread. */
	public static synchronized NavTask start(int goalSceneX, int goalSceneY, int plane, int radius) {
		if (active != null) {
			active.finish(State.CANCELLED, "replaced by a new walk_to");
		}
		Driver current = driver;
		NavTask task = new NavTask(nextId++, goalSceneX, goalSceneY, plane, radius, current.realPressSeq());
		task.lastOriginX = current.originX();
		task.lastOriginY = current.originY();
		active = task;
		return task;
	}

	public static NavTask active() {
		return active;
	}

	/** {@code task < 0} asks about any task. */
	public static boolean isFinished(int task) {
		if (task < 0) {
			return active == null;
		}
		return FINISHED.containsKey(task);
	}

	public static synchronized void cancel(String reason) {
		if (active != null) {
			active.finish(State.CANCELLED, reason);
		}
	}

	/** Runs every frame on the game thread. A no-op when nothing is walking. */
	public static void step() {
		NavTask current = active;
		if (current == null) {
			return;
		}
		current.tick();
	}

	public static JsonObject statusJson() {
		NavTask current = active;
		if (current == null) {
			JsonObject idle = new JsonObject();
			idle.addProperty("task", -1);
			idle.addProperty("state", "idle");
			if (lastFinishedId >= 0) {
				idle.addProperty("last_task", lastFinishedId);
				if (lastFinishedState != null) {
					idle.addProperty("last_state", lastFinishedState);
				}
				if (lastFinishedReason != null) {
					idle.addProperty("last_reason", lastFinishedReason);
				}
			}
			return idle;
		}
		return current.status();
	}

	// ------------------------------------------------------------------ per-frame

	private void tick() {
		Driver current = driver;
		if (current == null || state == State.ARRIVED || state == State.FAILED || state == State.CANCELLED) {
			return;
		}

		if (current.realPressSeq() != startPressSeq) {
			finish(State.CANCELLED, "a real mouse click cancelled the walk");
			return;
		}

		int originX = current.originX();
		int originY = current.originY();
		if (originX != lastOriginX || originY != lastOriginY) {
			lastOriginX = originX;
			lastOriginY = originY;
			legFrom = 0;
			replanRequested = true;
		}

		int px = current.playerSceneX();
		int py = current.playerSceneY();

		if (state == State.OPENING_DOOR) {
			tickDoor(current, px, py);
			return;
		}

		if (replanRequested || state == State.PLANNING) {
			if (!plan(current, px, py)) {
				return;
			}
		}

		if (state == State.WALKING) {
			tickWalk(current, px, py);
		}
	}

	private boolean plan(Driver current, int px, int py) {
		replanRequested = false;
		CollisionSource collision = current.collision();
		if (collision == null || collision.width() == 0 || collision.height() == 0) {
			finish(State.FAILED, "the collision map is not loaded yet");
			return false;
		}
		Map<Integer, DoorIndex.Door> doors = current.doors();
		Set<Integer> doorTiles = doors == null ? java.util.Collections.<Integer>emptySet() : doors.keySet();

		AStar.Path found = AStar.find(px, py, goalSceneX, goalSceneY, collision, doorTiles);
		if (found == null) {
			finish(State.FAILED, "no path on plane " + goalPlane + "; may need stairs/ladder/shortcut");
			return false;
		}
		path = found;
		legFrom = 0;
		state = State.WALKING;
		issueLeg(current);
		return true;
	}

	private void issueLeg(Driver current) {
		if (path == null || path.tiles.isEmpty()) {
			finish(State.FAILED, "empty path");
			return;
		}
		legEnd = legEnd(path.tiles, legFrom, path.doors, MAX_LEG);
		int[] target = path.tiles.get(legEnd);
		legDoorKey = path.doors.contains(AStar.key(target[0], target[1])) ? AStar.key(target[0], target[1]) : -1;
		try {
			current.walk(target[0], target[1]);
		} catch (Exception failure) {
			finish(State.FAILED, "could not start walking: " + failure.getMessage());
			return;
		}
		legsDone++;
	}

	private void tickWalk(Driver current, int px, int py) {
		if (chebyshev(px, py, goalSceneX, goalSceneY) <= radius) {
			finish(State.ARRIVED, null);
			return;
		}
		if (path == null || legEnd >= path.tiles.size()) {
			replanRequested = true;
			return;
		}

		int[] target = path.tiles.get(legEnd);
		if (chebyshev(px, py, target[0], target[1]) <= 2) {
			if (legDoorKey >= 0) {
				String doorTargetId = current.doorTarget(legDoorKey >> 8, legDoorKey & 0xFF);
				if (doorTargetId == null) {
					replanRequested = true;
					return;
				}
				doorKey = legDoorKey;
				doorAttempts = 1;
				doorWaitFrames = 0;
				doorAttempted = false;
				state = State.OPENING_DOOR;
				return;
			}
			legFrom = legEnd;
			issueLeg(current);
			return;
		}

		if (px == lastX && py == lastY) {
			stuckFrames++;
		} else {
			stuckFrames = 0;
		}
		lastX = px;
		lastY = py;

		if (stuckFrames > STUCK_FRAMES) {
			if (replans == 0) {
				replans++;
				replanRequested = true;
			} else {
				finish(State.FAILED, "stuck at " + worldX(current, px) + "," + worldY(current, py));
			}
		}
	}

	private void tickDoor(Driver current, int px, int py) {
		int doorX = doorKey >> 8;
		int doorY = doorKey & 0xFF;

		if (!doorAttempted) {
			String targetId = current.doorTarget(doorX, doorY);
			if (targetId == null) {
				finish(State.FAILED, "door at " + worldX(current, doorX) + "," + worldY(current, doorY) + " won't open");
				return;
			}
			try {
				current.open(targetId);
			} catch (Exception failure) {
				finish(State.FAILED, "could not open the door: " + failure.getMessage());
				return;
			}
			doorAttempted = true;
			return;
		}

		doorWaitFrames++;
		if (current.doorPassable(doorX, doorY)) {
			doorsOpened++;
			legFrom = legEnd;
			doorAttempted = false;
			replanRequested = true;
			state = State.WALKING;
			return;
		}
		if (doorWaitFrames < DOOR_WAIT_FRAMES) {
			return;
		}
		if (doorAttempts < MAX_DOOR_ATTEMPTS) {
			doorAttempts++;
			doorWaitFrames = 0;
			doorAttempted = false;
			return;
		}
		finish(State.FAILED, "door at " + worldX(current, doorX) + "," + worldY(current, doorY) + " won't open");
	}

	private void finish(State endState, String endReason) {
		state = endState;
		reason = endReason;
		lastFinishedId = id;
		lastFinishedState = endState.name();
		lastFinishedReason = endReason;
		FINISHED.put(id, endState.name());
		if (active == this) {
			active = null;
		}
	}

	// ------------------------------------------------------------------ helpers

	/**
	 * The index the next leg walks to: up to {@code maxLength} tiles ahead, or the first
	 * door edge, whichever comes first.
	 */
	public static int legEnd(List<int[]> tiles, int from, Set<Integer> doors, int maxLength) {
		int limit = Math.min(tiles.size() - 1, from + maxLength);
		if (doors != null) {
			for (int i = from + 1; i <= limit; i++) {
				int[] tile = tiles.get(i);
				if (doors.contains(AStar.key(tile[0], tile[1]))) {
					return i;
				}
			}
		}
		return limit;
	}

	public static int chebyshev(int x1, int y1, int x2, int y2) {
		return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
	}

	private static int worldX(Driver current, int sceneX) {
		return sceneX + current.originX();
	}

	private static int worldY(Driver current, int sceneY) {
		return sceneY + current.originY();
	}

	// ------------------------------------------------------------------ reporting

	public int id() {
		return id;
	}

	public State state() {
		return state;
	}

	public String reason() {
		return reason;
	}

	public int remainingTiles() {
		if (path == null || legEnd >= path.tiles.size()) {
			return 0;
		}
		int[] goal = path.tiles.get(path.tiles.size() - 1);
		Driver current = driver;
		return current == null ? 0 : chebyshev(current.playerSceneX(), current.playerSceneY(), goal[0], goal[1]);
	}

	public JsonObject status() {
		JsonObject out = new JsonObject();
		out.addProperty("task", id);
		out.addProperty("state", state.name());
		if (reason != null) {
			out.addProperty("reason", reason);
		}
		int[] goal = goalWorld();
		Driver current = driver;
		if (current != null) {
			JsonObject position = new JsonObject();
			position.addProperty("x", worldX(current, current.playerSceneX()));
			position.addProperty("y", worldY(current, current.playerSceneY()));
			position.addProperty("plane", goalPlane);
			out.add("position", position);
		}
		JsonObject goalJson = new JsonObject();
		goalJson.addProperty("x", goal[0]);
		goalJson.addProperty("y", goal[1]);
		goalJson.addProperty("plane", goal[2]);
		out.add("goal", goalJson);
		out.addProperty("remaining_tiles", remainingTiles());
		out.addProperty("legs_done", legsDone);
		out.addProperty("doors_opened", doorsOpened);
		return out;
	}

	/** The goal in world coordinates, when an origin is known. */
	public int[] goalWorld() {
		Driver current = driver;
		int originX = current == null ? 0 : current.originX();
		int originY = current == null ? 0 : current.originY();
		return new int[]{goalSceneX + originX, goalSceneY + originY, goalPlane};
	}

	/** Rough tile count for the immediate ack. */
	public int estimatedTiles() {
		Driver current = driver;
		if (current == null) {
			return 0;
		}
		return chebyshev(current.playerSceneX(), current.playerSceneY(), goalSceneX, goalSceneY);
	}

	public int goalPlane() {
		return goalPlane;
	}

	public int radius() {
		return radius;
	}

	public List<int[]> pathTiles() {
		return path == null ? new ArrayList<int[]>() : path.tiles;
	}
}
