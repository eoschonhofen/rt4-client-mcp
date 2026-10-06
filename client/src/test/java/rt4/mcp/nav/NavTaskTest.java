package rt4.mcp.nav;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rt4.mcp.GameThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NavTaskTest {
	private static final class FakeDriver implements NavTask.Driver {
		int px;
		int py;
		int originX;
		int originY;
		int pressSeq;
		int tick;
		int plane;
		CollisionSource collision;
		final Map<Integer, DoorIndex.Door> doors = new HashMap<Integer, DoorIndex.Door>();
		final Set<Integer> passable = new HashSet<Integer>();
		final List<int[]> walks = new ArrayList<int[]>();
		final List<String> opens = new ArrayList<String>();

		@Override
		public int playerSceneX() {
			return px;
		}

		@Override
		public int playerSceneY() {
			return py;
		}

		@Override
		public int plane() {
			return plane;
		}

		@Override
		public int originX() {
			return originX;
		}

		@Override
		public int originY() {
			return originY;
		}

		@Override
		public int tick() {
			return tick;
		}

		@Override
		public CollisionSource collision() {
			return collision;
		}

		@Override
		public Set<Integer> doorTiles() {
			return new HashSet<Integer>(doors.keySet());
		}

		@Override
		public void walk(int sceneX, int sceneY) {
			walks.add(new int[]{sceneX, sceneY});
		}

		@Override
		public void open(String target) {
			opens.add(target);
		}

		@Override
		public boolean doorPassable(int fromX, int fromY, int toX, int toY) {
			int fromKey = AStar.key(fromX, fromY);
			int toKey = AStar.key(toX, toY);
			if (passable.contains(fromKey) || passable.contains(toKey)) {
				return true;
			}
			if (collision != null
					&& AStar.canStep(collision, fromX, fromY, toX - fromX, toY - fromY, null)) {
				return true;
			}
			// The "Open" loc rotated away: the server changed the door.
			return !doors.containsKey(fromKey) && !doors.containsKey(toKey);
		}

		@Override
		public int realPressSeq() {
			return pressSeq;
		}

		@Override
		public String doorTarget(int sceneX, int sceneY) {
			DoorIndex.Door door = doors.get(AStar.key(sceneX, sceneY));
			return door == null ? null : door.target;
		}
	}

	private final FakeDriver driver = new FakeDriver();

	@AfterEach
	void cleanup() {
		NavTask.cancel("test cleanup");
		NavTask.setDriver(null);
	}

	private CollisionSource open(int width) {
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < width; i++) {
			row.append('.');
		}
		return new AStarTest.Grid(row.toString());
	}

	private static AStarTest.Grid grid(String row) {
		return new AStarTest.Grid(row);
	}

	private static String dots(int width) {
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < width; i++) {
			row.append('.');
		}
		return row.toString();
	}

	/** A whole blocked tile at {@code wallX}. */
	private CollisionSource wallAt(int width, int wallX) {
		return grid(new StringBuilder(dots(width)).replace(wallX, wallX + 1, "#").toString());
	}

	/** A mirrored door wall between {@code doorX - 1} and {@code doorX}. */
	private static AStarTest.Grid doorWall(int width, int doorX) {
		AStarTest.Grid result = grid(dots(width));
		result.add(doorX - 1, 0, 0x8);
		result.add(doorX, 0, 0x80);
		return result;
	}

	private static DoorIndex.Door door(String target) {
		return new DoorIndex.Door(target, 5, 0, 0);
	}

	@Test
	void legsSplitAtTwentyTiles() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(59, 0, 0, 0);
		NavTask.step();

		assertEquals(NavTask.State.WALKING, task.state());
		assertEquals(1, driver.walks.size());
		assertEquals(NavTask.MAX_LEG, driver.walks.get(0)[0]);
	}

	@Test
	void legsSplitBeforeADoor() {
		driver.collision = wallAt(60, 5);
		driver.doors.put(AStar.key(5, 0), door("loc:1516@0,0,0"));
		NavTask.setDriver(driver);

		NavTask.start(59, 0, 0, 0);
		NavTask.step();

		assertEquals(4, driver.walks.get(0)[0], "the leg must stop on the tile in front of the door");
	}

	@Test
	void originChangeTriggersAReplan() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(59, 0, 0, 0);
		NavTask.step();
		int walksBefore = driver.walks.size();

		driver.originX = 8;
		NavTask.step();

		assertEquals(NavTask.State.WALKING, task.state());
		assertTrue(driver.walks.size() > walksBefore, "a scene rebuild must re-plan");
	}

	@Test
	void realMouseClickCancelsTheWalk() {
		driver.collision = open(30);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(20, 0, 0, 0);
		NavTask.step();
		assertFalse(NavTask.isFinished(task.id()));

		driver.pressSeq = 1;
		NavTask.step();

		assertEquals(NavTask.State.CANCELLED, task.state());
		assertTrue(NavTask.isFinished(task.id()));
		assertTrue(NavTask.isFinished(-1));
	}

	@Test
	void arrivalIsReported() {
		driver.collision = open(10);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(3, 0, 0, 0);
		NavTask.step();

		driver.px = 3;
		driver.py = 0;
		NavTask.step();

		assertEquals(NavTask.State.ARRIVED, task.state());
		assertTrue(NavTask.isFinished(task.id()));
	}

	@Test
	void radiusCountsAsArrival() {
		driver.collision = open(10);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(5, 0, 0, 1);
		NavTask.step();

		driver.px = 4;
		NavTask.step();

		assertEquals(NavTask.State.ARRIVED, task.state());
	}

	@Test
	void unloadedCollisionMapFailsCleanly() {
		driver.collision = new CollisionSource() {
			@Override
			public int flags(int x, int y) {
				return 0;
			}

			@Override
			public int width() {
				return 0;
			}

			@Override
			public int height() {
				return 0;
			}
		};
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(5, 0, 0, 0);
		NavTask.step();

		assertEquals(NavTask.State.FAILED, task.state());
	}

	@Test
	void unreachableGoalFailsWithThePlaneMessage() {
		// A complete wall splits the grid in two.
		driver.collision = new AStarTest.Grid(".#.", ".#.", ".#.");
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(2, 0, 0, 0);
		NavTask.step();

		assertEquals(NavTask.State.FAILED, task.state());
		assertTrue(task.reason().contains("no path on plane 0"), task.reason());
		assertTrue(task.reason().contains("stairs"), task.reason());
	}

	@Test
	void startedTaskReplacesThePreviousOne() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask first = NavTask.start(30, 0, 0, 0);
		NavTask.step();
		NavTask second = NavTask.start(50, 0, 0, 0);

		assertEquals(NavTask.State.CANCELLED, first.state());
		assertEquals(second, NavTask.active());
	}

	@Test
	void cancelStopsTheActiveTask() {
		driver.collision = open(30);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(20, 0, 0, 0);
		NavTask.step();
		NavTask.cancel("by request");

		assertEquals(NavTask.State.CANCELLED, task.state());
		assertEquals("by request", task.reason());
		assertTrue(NavTask.isFinished(-1));
	}

	@Test
	void aReplacedTaskStillCountsAsFinished() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask first = NavTask.start(30, 0, 0, 0);
		NavTask.step();
		NavTask second = NavTask.start(50, 0, 0, 0);
		NavTask.step();

		assertTrue(NavTask.isFinished(first.id()), "the cancelled task id must stay queryable");
		assertFalse(NavTask.isFinished(second.id()));
	}

	@Test
	void aPlaneChangeFailsTheTask() {
		driver.collision = open(30);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(20, 0, 0, 0);
		NavTask.step();
		assertFalse(NavTask.isFinished(task.id()), "the walk starts on the goal plane");

		driver.plane = 1;
		NavTask.step();

		assertEquals(NavTask.State.FAILED, task.state());
		assertEquals("plane changed", task.reason());
	}

	@Test
	void aThrowingStepFailsTheTaskWithoutEscapingTheFrame() {
		driver.collision = new CollisionSource() {
			@Override
			public int flags(int x, int y) {
				throw new IllegalStateException("scene boom");
			}

			@Override
			public int width() {
				return 30;
			}

			@Override
			public int height() {
				return 30;
			}
		};
		NavTask.setDriver(driver);
		NavTask task = NavTask.start(20, 0, 0, 0);
		GameThread.navigationStep = NavTask::step;
		try {
			GameThread.drain(); // MCP-22 — must swallow the failure

			assertEquals(NavTask.State.FAILED, task.state(), "nav_status must show FAILED, not CANCELLED");
			assertTrue(task.reason().startsWith("internal error: "), task.reason());
			assertTrue(task.reason().contains("scene boom"), task.reason());
		} finally {
			GameThread.reset();
		}
	}

	@Test
	void legEndStopsAtTheDoorOrTheCap() {
		List<int[]> tiles = new ArrayList<int[]>();
		for (int i = 0; i < 40; i++) {
			tiles.add(new int[]{i, 0});
		}
		List<AStar.Crossing> none = new ArrayList<AStar.Crossing>();
		List<AStar.Crossing> door = Arrays.asList(new AStar.Crossing(5, 6, AStar.key(5, 0)));

		assertEquals(20, NavTask.legEnd(tiles, 0, none, 20));
		assertEquals(20, NavTask.legEnd(tiles, 0, null, 20));
		assertEquals(5, NavTask.legEnd(tiles, 0, door, 20));
		assertEquals(39, NavTask.legEnd(tiles, 30, none, 20));
		assertEquals(30, NavTask.legEnd(tiles, 30, none, 0));
	}

	@Test
	void statusJsonDescribesTheTask() {
		driver.collision = open(30);
		driver.originX = 3200;
		driver.originY = 3200;
		driver.px = 10;
		driver.py = 0;
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(20, 0, 0, 0);
		NavTask.step();

		assertEquals(task.id(), task.status().get("task").getAsInt());
		assertEquals("WALKING", task.status().get("state").getAsString());
		assertEquals(3210, task.status().getAsJsonObject("position").get("x").getAsInt());
		assertNotNull(task.status().getAsJsonObject("goal"));
	}

	@Test
	void idleStatusWhenNothingIsWalking() {
		NavTask.cancel("none");
		assertEquals(-1, NavTask.statusJson().get("task").getAsInt());
		assertEquals("idle", NavTask.statusJson().get("state").getAsString());
	}

	// ------------------------------------------------------------------ MCP-16

	@Test
	void noStuckFailureWhileMovingOneTilePerTick() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(59, 0, 0, 0);
		NavTask.step();

		for (int i = 1; i <= 30; i++) {
			driver.px = i;
			driver.tick = i;
			NavTask.step();
		}

		assertEquals(NavTask.State.WALKING, task.state(), "a moving player must never read as stuck");
	}

	@Test
	void stuckOnlyAfterMoreThanEightTicksWithoutMovement() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(59, 0, 0, 0);
		NavTask.step();

		driver.tick = NavTask.STUCK_TICKS; // not yet "more than STUCK_TICKS"
		NavTask.step();
		assertEquals(NavTask.State.WALKING, task.state());

		driver.tick = NavTask.STUCK_TICKS + 1; // one tick past: one re-plan
		NavTask.step();
		assertEquals(NavTask.State.WALKING, task.state());
		NavTask.step(); // the re-plan runs and issues a fresh leg
		assertEquals(NavTask.State.WALKING, task.state());
		assertTrue(driver.walks.size() >= 2, "it must have re-planned once");

		int walksAfterReplan = driver.walks.size();
		driver.tick = NavTask.STUCK_TICKS + 1 + NavTask.STUCK_TICKS + 1;
		NavTask.step();

		assertEquals(NavTask.State.FAILED, task.state());
		assertTrue(task.reason().contains("stuck"), task.reason());
		assertEquals(walksAfterReplan, driver.walks.size(), "exactly one re-plan before failing");
	}

	@Test
	void replanResetsTheStuckTimer() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(59, 0, 0, 0);
		NavTask.step();

		driver.tick = NavTask.STUCK_TICKS + 1;
		NavTask.step(); // stuck -> re-plan requested
		NavTask.step(); // re-plan runs, the timer restarts

		driver.tick = NavTask.STUCK_TICKS + 1 + NavTask.STUCK_TICKS; // STUCK_TICKS since the re-plan, not more
		NavTask.step();
		assertEquals(NavTask.State.WALKING, task.state(), "the re-plan must grant a full grace period");
	}

	@Test
	void doorWaitSpansSixTicksNotSixSteps() {
		driver.collision = doorWall(20, 5);
		driver.doors.put(AStar.key(5, 0), door("loc:1516@0,0,0"));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		driver.px = 4;
		NavTask.step(); // enters OPENING_DOOR
		NavTask.step(); // sends the open
		assertEquals(NavTask.State.OPENING_DOOR, task.state());

		for (int i = 0; i < 30; i++) {
			NavTask.step();
		}
		assertEquals(NavTask.State.OPENING_DOOR, task.state(),
				"many frames are not many ticks; the door wait must not expire");

		driver.tick = NavTask.DOOR_WAIT_TICKS;
		NavTask.step();
		assertEquals(NavTask.State.OPENING_DOOR, task.state(), "6 ticks is one retry, not a failure");
	}

	@Test
	void doorThatNeverOpensFails() {
		driver.collision = doorWall(20, 5);
		driver.doors.put(AStar.key(5, 0), door("loc:1516@0,0,0"));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		driver.px = 4;
		NavTask.step(); // enters OPENING_DOOR
		NavTask.step(); // sends the open

		driver.tick = NavTask.DOOR_WAIT_TICKS;
		NavTask.step(); // the second attempt
		NavTask.step(); // sends the open again

		driver.tick = NavTask.DOOR_WAIT_TICKS * 2;
		NavTask.step();

		assertEquals(NavTask.State.FAILED, task.state());
		assertTrue(task.reason().contains("won't open"), task.reason());
	}

	// ------------------------------------------------------------------ MCP-18

	@Test
	void doorIsOpenedThenTheWalkContinues() {
		AStarTest.Grid collision = doorWall(20, 5);
		driver.collision = collision;
		driver.doors.put(AStar.key(5, 0), door("loc:1516@0,0,0"));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		assertEquals(4, driver.walks.get(0)[0]);

		// Standing next to the door.
		driver.px = 4;
		NavTask.step();
		assertEquals(NavTask.State.OPENING_DOOR, task.state());

		NavTask.step();
		assertEquals(Arrays.asList("loc:1516@0,0,0"), driver.opens);

		// The server rotates the door: the crossing edge clears, another edge gains a wall bit.
		collision.clear(5, 0, 0x80);
		collision.clear(4, 0, 0x8);
		NavTask.step();

		assertEquals(NavTask.State.WALKING, task.state());
		assertTrue(task.status().get("doors_opened").getAsInt() >= 1);
	}

	@Test
	void openedDoorWithARotatedWallBitStillArrives() {
		AStarTest.Grid collision = doorWall(20, 5);
		driver.collision = collision;
		driver.doors.put(AStar.key(5, 0), door("loc:1516@0,0,0"));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		driver.px = 4;
		NavTask.step();
		NavTask.step(); // open

		// Rotate: the wall bits stay non-zero, but the crossing edge opens.
		collision.clear(5, 0, 0x80);
		collision.add(5, 0, 0x20);
		collision.clear(4, 0, 0x8);
		NavTask.step(); // notices it opened and re-plans

		// Walk through and arrive.
		driver.px = 19;
		NavTask.step();

		assertEquals(NavTask.State.ARRIVED, task.state());
		assertEquals(1, task.status().get("doors_opened").getAsInt());
	}

	@Test
	void doorLocDisappearingCountsAsOpened() {
		AStarTest.Grid collision = doorWall(20, 5);
		driver.collision = collision;
		driver.doors.put(AStar.key(5, 0), door("loc:1516@0,0,0"));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		driver.px = 4;
		NavTask.step();
		NavTask.step(); // open

		// The loc rotates to its closed form and the crossing clears.
		driver.doors.remove(AStar.key(5, 0));
		collision.clear(5, 0, 0x80);
		collision.clear(4, 0, 0x8);
		NavTask.step();

		assertEquals(NavTask.State.WALKING, task.state());
		assertTrue(task.status().get("doors_opened").getAsInt() >= 1);
	}
}
