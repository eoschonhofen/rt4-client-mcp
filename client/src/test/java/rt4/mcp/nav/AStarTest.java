package rt4.mcp.nav;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AStarTest {
	/** A character-grid fixture: {@code '#'} is blocked, anything else is walkable. */
	static final class Grid implements CollisionSource {
		private final int[][] flags;

		Grid(String... rows) {
			flags = new int[rows[0].length()][rows.length];
			for (int y = 0; y < rows.length; y++) {
				assertEquals(rows[0].length(), rows[y].length(), "rows must have equal width");
				for (int x = 0; x < rows[y].length(); x++) {
					flags[x][y] = rows[y].charAt(x) == '#' ? 0x100 : 0;
				}
			}
		}

		private Grid(int[][] raw) {
			flags = raw;
		}

		static Grid raw(int[][] raw) {
			return new Grid(raw);
		}

		/** Sets the raw flags of one tile, so a single wall edge bit can be placed. */
		Grid set(int x, int y, int value) {
			flags[x][y] = value;
			return this;
		}

		Grid add(int x, int y, int bits) {
			flags[x][y] |= bits;
			return this;
		}

		Grid clear(int x, int y, int bits) {
			flags[x][y] &= ~bits;
			return this;
		}

		@Override
		public int flags(int x, int y) {
			return flags[x][y];
		}

		@Override
		public int width() {
			return flags.length;
		}

		@Override
		public int height() {
			return flags[0].length;
		}
	}

	private static Set<Integer> door(int x, int y) {
		Set<Integer> doors = new HashSet<Integer>();
		doors.add(AStar.key(x, y));
		return doors;
	}

	private static List<int[]> path(int srcX, int srcY, int dstX, int dstY, Grid grid, Set<Integer> doors) {
		AStar.Path found = AStar.find(srcX, srcY, dstX, dstY, grid, doors);
		return found == null ? null : found.tiles;
	}

	@Test
	void straightLineAcrossAnOpenGrid() {
		Grid grid = new Grid(".....");
		List<int[]> tiles = path(0, 0, 4, 0, grid, null);

		assertNotNull(tiles);
		assertEquals(5, tiles.size());
		assertEquals(4, tiles.get(4)[0]);
		assertEquals(0, tiles.get(4)[1]);
	}

	@Test
	void alreadyAtTheDestination() {
		Grid grid = new Grid("...");
		List<int[]> tiles = path(1, 0, 1, 0, grid, null);

		assertNotNull(tiles);
		assertEquals(1, tiles.size());
	}

	@Test
	void goesAroundAWall() {
		Grid grid = new Grid(
				".#.",
				".#.",
				"...");
		List<int[]> tiles = path(0, 0, 2, 0, grid, null);

		assertNotNull(tiles);
		assertEquals(2, tiles.get(tiles.size() - 1)[0]);
		assertEquals(0, tiles.get(tiles.size() - 1)[1]);
		// The wall is only breached around the bottom, never through it.
		for (int[] tile : tiles) {
			assertFalse(grid.flags(tile[0], tile[1]) != 0, "path must not enter a blocked tile");
		}
		assertTrue(tiles.size() > 3, "the detour must be longer than the straight line");
	}

	@Test
	void diagonalCornerCuttingIsForbidden() {
		// (0,0) and (1,1) are open, (1,0) and (0,1) are blocked: the diagonal is not allowed.
		Grid grid = new Grid(
				".#",
				"#.");
		assertNull(path(0, 0, 1, 1, grid, null));
	}

	@Test
	void diagonalIsAllowedWhenBothOrthogonalsAreClear() {
		Grid grid = new Grid(
				"..",
				"..");
		List<int[]> tiles = path(0, 1, 1, 0, grid, null);

		assertNotNull(tiles);
		assertEquals(2, tiles.size());
	}

	@Test
	void deadEndReturnsNull() {
		Grid grid = new Grid(
				".#.",
				"###",
				".#.");
		assertNull(path(0, 0, 2, 2, grid, null));
	}

	@Test
	void doorIsUsedOnlyWhenItIsInThePassableSet() {
		// A full-height wall with a single door tile: the door is the only way through.
		Grid grid = new Grid(
				".#.",
				".#.",
				".#.");

		assertNull(path(0, 1, 2, 1, grid, null), "without the door the goal is unreachable");

		AStar.Path through = AStar.find(0, 1, 2, 1, grid, door(1, 1));
		assertNotNull(through);
		assertNotNull(through.crossingAt(0), "the door crossing must be recorded on the path");
		assertEquals(AStar.key(1, 1), through.crossingAt(0).doorKey);
		assertEquals(3, through.tiles.size());
	}

	@Test
	void outOfSceneGoalUsesAnEdgeTile() {
		Grid grid = new Grid(
				"....",
				"....");

		AStar.Path found = AStar.find(0, 0, 99, 0, grid, null);

		assertNotNull(found);
		assertTrue(found.approximate);
		assertEquals(3, found.destination()[0]);
		assertEquals(0, found.destination()[1]);
	}

	@Test
	void sourceOutsideTheSceneReturnsNull() {
		Grid grid = new Grid("..");
		assertNull(AStar.find(-1, 0, 1, 0, grid, null));
		assertNull(AStar.find(5, 0, 1, 0, grid, null));
	}

	@Test
	void stepMasksBlockTheRightDirections() {
		// A wall between (0,0) and (1,0): moving east is blocked, moving west into it from (1,0) too.
		Grid grid = new Grid(".#.");
		assertFalse(AStar.canStep(grid, 0, 0, 1, 0, null));
		assertFalse(AStar.canStep(grid, 2, 0, -1, 0, null));
		assertTrue(AStar.canStep(grid, 0, 0, -1, 0, null) == false, "west is out of bounds");
	}

	@Test
	void doorTilesAreStepable() {
		Grid grid = new Grid(".#.");
		assertTrue(AStar.canStep(grid, 0, 0, 1, 0, door(1, 0)));
	}

	@Test
	void heuristicIsAdmissible() {
		assertEquals(0, AStar.heuristic(3, 3, 3, 3));
		assertEquals(10, AStar.heuristic(0, 0, 1, 0));
		assertEquals(14, AStar.heuristic(0, 0, 1, 1));
		assertEquals(24, AStar.heuristic(0, 0, 2, 1));
	}

	@Test
	void keyRoundTrips() {
		assertEquals(0x0305, AStar.key(3, 5));
		assertEquals(3, AStar.key(3, 5) >> 8);
		assertEquals(5, AStar.key(3, 5) & 0xFF);
	}

	@Test
	void emptyDoorSetBehavesLikeNoDoors() {
		Grid grid = new Grid(". #".replace(" ", ""));
		AStar.Path found = AStar.find(0, 0, 2, 0, grid, Collections.<Integer>emptySet());
		assertNull(found);
	}

	// ------------------------------------------------------------------ MCP-17

	@Test
	void walkingParallelToAWallSucceedsInAStraightLine() {
		// Every tile of the row has a wall on its south edge (bit 0x2): walking east is fine.
		Grid grid = new Grid(".....");
		for (int x = 0; x < 5; x++) {
			grid.add(x, 0, 0x2);
		}
		List<int[]> tiles = path(0, 0, 4, 0, grid, null);

		assertNotNull(tiles);
		assertEquals(5, tiles.size());
	}

	@Test
	void enteringThroughAWalledEdgeIsBlockedAndAnOpenEdgeIsAllowed() {
		Grid grid = new Grid(
				"..",
				"..");
		grid.set(1, 0, 0x80); // an east-edge wall bit on (1,0)

		assertFalse(AStar.canStep(grid, 0, 0, 1, 0, null), "the walled edge must block the step");
		assertTrue(AStar.canStep(grid, 0, 1, 1, 0, null), "the open edge below must allow it");
	}

	@Test
	void diagonalIntoABlockedTileIsRefusedEvenWhenBothOrthogonalsAreClear() {
		Grid grid = new Grid(
				"..",
				"..");
		grid.set(1, 1, 0x100);

		assertTrue(AStar.canStep(grid, 0, 0, 1, 0, null), "the east neighbour is clear");
		assertTrue(AStar.canStep(grid, 0, 0, 0, 1, null), "the north neighbour is clear");
		assertFalse(AStar.canStep(grid, 0, 0, 1, 1, null), "the diagonal tile is blocked");
	}

	@Test
	void masksMatchTheClientPathFinder() {
		Random random = new Random(1234L);
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
		for (int trial = 0; trial < 25; trial++) {
			int width = 10;
			int height = 10;
			int[][] raw = new int[width][height];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					raw[x][y] = random.nextInt() & 0x7FFFFFFF;
				}
			}
			Grid grid = Grid.raw(raw);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					for (int[] direction : directions) {
						assertEquals(referenceCanStep(grid, x, y, direction[0], direction[1]),
								AStar.canStep(grid, x, y, direction[0], direction[1], null),
								"step " + direction[0] + "," + direction[1] + " from " + x + "," + y);
					}
				}
			}
		}
	}

	/** A direct port of {@code PathFinder.findPath1}'s size-1 player checks. */
	private static boolean referenceCanStep(Grid grid, int x, int y, int dx, int dy) {
		int toX = x + dx;
		int toY = y + dy;
		if (toX < 0 || toY < 0 || toX >= grid.width() || toY >= grid.height()) {
			return false;
		}
		if (dx == 0) {
			int mask = dy == -1 ? 0x12C0102 : 0x12C0120;
			return (grid.flags(toX, toY) & mask) == 0;
		}
		if (dy == 0) {
			int mask = dx == -1 ? 0x12C0108 : 0x12C0180;
			return (grid.flags(toX, toY) & mask) == 0;
		}
		int corner;
		if (dx == -1 && dy == -1) {
			corner = 0x12C010E;
		} else if (dx == 1 && dy == -1) {
			corner = 0x12C0183;
		} else if (dx == -1 && dy == 1) {
			corner = 0x12C0138;
		} else {
			corner = 0x12C01E0;
		}
		return (grid.flags(toX, toY) & corner) == 0
				&& (grid.flags(toX, y) & (dx == -1 ? 0x12C0108 : 0x12C0180)) == 0
				&& (grid.flags(x, toY) & (dy == -1 ? 0x12C0102 : 0x12C0120)) == 0;
	}

	// ------------------------------------------------------------------ MCP-18

	@Test
	void doorOnTheNearTileIsCrossedInBothDirections() {
		Grid grid = wallBetween(1, 0, 2, 0);

		AStar.Path east = AStar.find(0, 0, 3, 0, grid, door(1, 0));
		assertNotNull(east);
		assertEquals(1, east.crossingAt(1).fromIndex);
		assertEquals(AStar.key(1, 0), east.crossingAt(1).doorKey);

		AStar.Path west = AStar.find(3, 0, 0, 0, grid, door(1, 0));
		assertNotNull(west);
		assertNotNull(west.crossingAt(1), "crossing back must be recorded too");
	}

	@Test
	void doorOnTheFarTileIsCrossedInBothDirections() {
		Grid grid = wallBetween(1, 0, 2, 0);

		AStar.Path east = AStar.find(0, 0, 3, 0, grid, door(2, 0));
		assertNotNull(east);
		assertNotNull(east.crossingAt(1));
		assertEquals(AStar.key(2, 0), east.crossingAt(1).doorKey);

		AStar.Path west = AStar.find(3, 0, 0, 0, grid, door(2, 0));
		assertNotNull(west);
		assertNotNull(west.crossingAt(1));
	}

	@Test
	void noDoorCrossingOnDiagonals() {
		Grid grid = new Grid(
				"..",
				"..");
		grid.set(1, 1, AStar.DIAG_NE);

		assertFalse(AStar.canStep(grid, 0, 0, 1, 1, door(1, 1)), "a diagonal must not waive a door");
	}

	@Test
	void doorCrossingCostAppliesOnce() {
		Grid grid = wallBetween(1, 0, 2, 0);

		AStar.Path found = AStar.find(0, 0, 2, 0, grid, door(2, 0));

		assertNotNull(found);
		assertEquals(10 + 10 + AStar.DOOR_COST * 10, found.cost, "two steps and one door crossing");
	}

	/** A row of four tiles with a mirrored single-edge wall bit between {@code (ax,ay)} and {@code (bx,by)}. */
	private static Grid wallBetween(int ax, int ay, int bx, int by) {
		Grid grid = new Grid("....");
		grid.add(ax, ay, 0x8); // entering the near tile from the east is blocked
		grid.add(bx, by, 0x80); // entering the far tile from the west is blocked
		return grid;
	}

	@Test
	void crossingAtFindsTheRightCrossing() {
		Grid grid = wallBetween(1, 0, 2, 0);
		AStar.Path found = AStar.find(0, 0, 3, 0, grid, door(1, 0));

		assertNotNull(found);
		assertNull(found.crossingAt(0), "no crossing before the door");
		assertNotNull(found.crossingAt(1));
		assertNull(found.crossingAt(2));
	}
}
