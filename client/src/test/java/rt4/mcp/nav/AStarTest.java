package rt4.mcp.nav;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
		assertTrue(through.doors.contains(AStar.key(1, 1)), "the door tile must be marked on the path");
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
}
