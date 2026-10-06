package rt4.mcp.nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MCP-13 — 8-directional A* with the client's collision masks.
 *
 * <p>The masks are the size-1 player combinations taken from
 * {@code PathFinder.findPath1}: a move checks the destination tile against the union of the
 * two sub-masks for that direction. Diagonal moves additionally require both orthogonal
 * moves to be clear, which forbids corner cutting, exactly like {@code findPath1}.</p>
 *
 * <p>Pure: all state comes from the {@link CollisionSource}, so it is unit-tested with a
 * character-grid fixture.</p>
 */
public final class AStar {
	/** Blocked-by-loc, unwalkable and floor-decoration bits are inside these masks. */
	public static final int WEST_MASK = 0x12C013E;
	public static final int EAST_MASK = 0x12C01E3;
	public static final int NORTH_MASK = 0x12C018F;
	public static final int SOUTH_MASK = 0x12C01F8;

	public static final int DOOR_COST = 5;

	private static final int[] DX = {-1, 1, 0, 0, -1, 1, -1, 1};
	private static final int[] DY = {0, 0, -1, 1, -1, -1, 1, 1};

	public static final class Path {
		public final List<int[]> tiles;
		public final int cost;
		/** Tile keys ({@code (x << 8) | y}) the path crosses only because a door can be opened. */
		public final Set<Integer> doors;
		/** True when the goal was outside the scene and an edge tile was used instead. */
		public final boolean approximate;

		Path(List<int[]> tiles, int cost, Set<Integer> doors, boolean approximate) {
			this.tiles = tiles;
			this.cost = cost;
			this.doors = doors;
			this.approximate = approximate;
		}

		public int[] destination() {
			return tiles.get(tiles.size() - 1);
		}
	}

	private AStar() {
	}

	public static int key(int x, int y) {
		return x << 8 | y;
	}

	public static boolean inBounds(CollisionSource source, int x, int y) {
		return x >= 0 && y >= 0 && x < source.width() && y < source.height();
	}

	/** Whether a single orthogonal/diagonal step is allowed (doors at extra cost). */
	public static boolean canStep(CollisionSource source, int fromX, int fromY, int dx, int dy, Set<Integer> doors) {
		int toX = fromX + dx;
		int toY = fromY + dy;
		if (!inBounds(source, toX, toY)) {
			return false;
		}
		if (dx != 0 && dy != 0) {
			// No corner cutting: both orthogonal moves have to be clear.
			return canStep(source, fromX, fromY, dx, 0, doors) && canStep(source, fromX, fromY, 0, dy, doors);
		}
		int mask;
		if (dx == -1) {
			mask = WEST_MASK;
		} else if (dx == 1) {
			mask = EAST_MASK;
		} else if (dy == -1) {
			mask = NORTH_MASK;
		} else {
			mask = SOUTH_MASK;
		}
		if ((source.flags(toX, toY) & mask) == 0) {
			return true;
		}
		return doors != null && doors.contains(key(toX, toY));
	}

	/**
	 * Finds a path from {@code (srcX, srcY)} to {@code (dstX, dstY)}. When the goal is
	 * outside the scene the reachable tile closest to it is used and the result is marked
	 * approximate. Returns null when nothing is reachable.
	 */
	public static Path find(int srcX, int srcY, int dstX, int dstY, CollisionSource source, Set<Integer> doorTiles) {
		if (!inBounds(source, srcX, srcY)) {
			return null;
		}
		int width = source.width();
		int height = source.height();
		boolean approximate = !inBounds(source, dstX, dstY);
		int goalX = clamp(dstX, 0, width - 1);
		int goalY = clamp(dstY, 0, height - 1);

		if (srcX == goalX && srcY == goalY) {
			List<int[]> single = new ArrayList<int[]>();
			single.add(new int[]{srcX, srcY});
			return new Path(single, 0, new HashSet<Integer>(), approximate);
		}

		int size = width * height;
		int[] cameFrom = new int[size];
		int[] costs = new int[size];
		boolean[] closed = new boolean[size];
		java.util.Arrays.fill(cameFrom, -1);
		java.util.Arrays.fill(costs, Integer.MAX_VALUE);

		Set<Integer> doors = doorTiles == null ? new HashSet<Integer>() : doorTiles;
		java.util.PriorityQueue<Node> open = new java.util.PriorityQueue<Node>();
		int startIndex = index(srcX, srcY, width);
		costs[startIndex] = 0;
		open.add(new Node(startIndex, heuristic(srcX, srcY, goalX, goalY)));

		int bestIndex = startIndex;
		int bestHeuristic = heuristic(srcX, srcY, goalX, goalY);

		while (!open.isEmpty()) {
			Node current = open.poll();
			if (closed[current.index]) {
				continue;
			}
			closed[current.index] = true;

			int cx = current.index % width;
			int cy = current.index / width;
			int h = heuristic(cx, cy, goalX, goalY);
			if (h < bestHeuristic) {
				bestHeuristic = h;
				bestIndex = current.index;
			}
			if (cx == goalX && cy == goalY) {
				return build(cameFrom, current.index, costs[current.index], doors, approximate, source, width);
			}

			for (int direction = 0; direction < DX.length; direction++) {
				int dx = DX[direction];
				int dy = DY[direction];
				if (!canStep(source, cx, cy, dx, dy, doors)) {
					continue;
				}
				int nx = cx + dx;
				int ny = cy + dy;
				int nIndex = index(nx, ny, width);
				if (closed[nIndex]) {
					continue;
				}
				boolean isDoor = (source.flags(nx, ny) & maskFor(dx, dy)) != 0;
				int step = (dx != 0 && dy != 0 ? 14 : 10) + (isDoor ? DOOR_COST * 10 : 0);
				int tentative = costs[current.index] + step;
				if (tentative < costs[nIndex]) {
					costs[nIndex] = tentative;
					cameFrom[nIndex] = current.index;
					open.add(new Node(nIndex, tentative + heuristic(nx, ny, goalX, goalY)));
				}
			}
		}

		if (approximate && bestIndex != startIndex) {
			return build(cameFrom, bestIndex, costs[bestIndex], doors, true, source, width);
		}
		return null;
	}

	private static int index(int x, int y, int width) {
		return y * width + x;
	}

	private static int maskFor(int dx, int dy) {
		if (dx == -1) {
			return WEST_MASK;
		}
		if (dx == 1) {
			return EAST_MASK;
		}
		if (dy == -1) {
			return NORTH_MASK;
		}
		return SOUTH_MASK;
	}

	private static Path build(int[] cameFrom, int goalIndex, int cost, Set<Integer> doors, boolean approximate,
							  CollisionSource source, int width) {
		List<int[]> reversed = new ArrayList<int[]>();
		int current = goalIndex;
		Set<Integer> crossed = new HashSet<Integer>();
		while (current != -1) {
			int x = current % width;
			int y = current / width;
			reversed.add(new int[]{x, y});
			int tileKey = key(x, y);
			if (doors.contains(tileKey)
					&& (source.flags(x, y) & (WEST_MASK | EAST_MASK | NORTH_MASK | SOUTH_MASK)) != 0) {
				crossed.add(tileKey);
			}
			current = cameFrom[current];
		}
		List<int[]> tiles = new ArrayList<int[]>(reversed.size());
		for (int i = reversed.size() - 1; i >= 0; i--) {
			tiles.add(reversed.get(i));
		}
		return new Path(tiles, cost, crossed, approximate);
	}

	/** Octile distance, scaled by 10 so it stays integral. */
	public static int heuristic(int x, int y, int goalX, int goalY) {
		int dx = Math.abs(x - goalX);
		int dy = Math.abs(y - goalY);
		return 10 * Math.max(dx, dy) + 4 * Math.min(dx, dy);
	}

	private static int clamp(int value, int min, int max) {
		return value < min ? min : Math.min(value, max);
	}

	private static final class Node implements Comparable<Node> {
		final int index;
		final int score;

		Node(int index, int score) {
			this.index = index;
			this.score = score;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(score, other.score);
		}
	}
}
