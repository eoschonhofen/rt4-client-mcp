package rt4.mcp.nav;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

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
			return 0;
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
		public CollisionSource collision() {
			return collision;
		}

		@Override
		public Map<Integer, DoorIndex.Door> doors() {
			return doors;
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
		public boolean doorPassable(int sceneX, int sceneY) {
			return passable.contains(AStar.key(sceneX, sceneY));
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

	private CollisionSource wallAt(int width, int wallX) {
		StringBuilder row = new StringBuilder();
		for (int i = 0; i < width; i++) {
			row.append(i == wallX ? '#' : '.');
		}
		return new AStarTest.Grid(row.toString());
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
	void legsSplitAtADoor() {
		driver.collision = wallAt(60, 5);
		driver.doors.put(AStar.key(5, 0), new DoorIndex.Door("loc:1516@0,0,0", 5, 0, 0));
		NavTask.setDriver(driver);

		NavTask.start(59, 0, 0, 0);
		NavTask.step();

		assertEquals(5, driver.walks.get(0)[0]);
	}

	@Test
	void stuckDetectionReplansOnceThenFails() {
		driver.collision = open(60);
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(59, 0, 0, 0);
		NavTask.step();
		assertEquals(NavTask.State.WALKING, task.state());

		for (int i = 0; i < 40 && task.state() == NavTask.State.WALKING; i++) {
			NavTask.step();
		}

		assertEquals(NavTask.State.FAILED, task.state());
		assertTrue(task.reason().contains("stuck"), task.reason());
		assertTrue(driver.walks.size() >= 2, "it must have re-planned at least once");
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
	void doorIsOpenedThenTheWalkContinues() {
		driver.collision = wallAt(20, 5);
		driver.doors.put(AStar.key(5, 0), new DoorIndex.Door("loc:1516@0,0,0", 5, 0, 0));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		assertEquals(5, driver.walks.get(0)[0]);

		// Standing next to the door.
		driver.px = 4;
		NavTask.step();
		assertEquals(NavTask.State.OPENING_DOOR, task.state());

		NavTask.step();
		assertEquals(Arrays.asList("loc:1516@0,0,0"), driver.opens);

		driver.passable.add(AStar.key(5, 0));
		NavTask.step();

		assertEquals(NavTask.State.WALKING, task.state());
		assertTrue(task.status().get("doors_opened").getAsInt() >= 1);
	}

	@Test
	void doorThatNeverOpensFails() {
		driver.collision = wallAt(20, 5);
		driver.doors.put(AStar.key(5, 0), new DoorIndex.Door("loc:1516@0,0,0", 5, 0, 0));
		NavTask.setDriver(driver);

		NavTask task = NavTask.start(19, 0, 0, 0);
		NavTask.step();
		driver.px = 4;
		NavTask.step(); // enters OPENING_DOOR

		for (int i = 0; i < 40 && task.state() == NavTask.State.OPENING_DOOR; i++) {
			NavTask.step();
		}

		assertEquals(NavTask.State.FAILED, task.state());
		assertTrue(task.reason().contains("won't open"), task.reason());
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
	void legEndStopsAtTheDoorOrTheCap() {
		List<int[]> tiles = new ArrayList<int[]>();
		for (int i = 0; i < 40; i++) {
			tiles.add(new int[]{i, 0});
		}

		assertEquals(20, NavTask.legEnd(tiles, 0, null, 20));
		assertEquals(20, NavTask.legEnd(tiles, 0, new HashSet<Integer>(), 20));
		assertEquals(5, NavTask.legEnd(tiles, 0, new HashSet<Integer>(Arrays.asList(AStar.key(5, 0))), 20));
		assertEquals(39, NavTask.legEnd(tiles, 30, null, 20));
		assertEquals(30, NavTask.legEnd(tiles, 30, null, 0));
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
}
