package rt4.mcp.nav;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MCP-13 / MCP-17 / MCP-18 — 8-directional A* with the client's collision masks.
 *
 * <p>The masks are the size-1 player combinations taken from {@code PathFinder.findPath1}: a
 * straight move checks the destination tile against its direction mask, and a diagonal move
 * checks the destination tile against its corner mask and both orthogonal neighbours against
 * theirs, which forbids corner cutting exactly like {@code findPath1}.</p>
 *
 * <p>Doors are edges, not tiles (MCP-18): a blocked straight step {@code from -> to} is a
 * crossing when either end holds a door loc that offers "Open". Diagonal steps never cross a
 * door. Every crossing is recorded on the {@link Path} so the walker can stop in front of it
 * and open it.</p>
 *
 * <p>Pure: all state comes from the {@link CollisionSource}, so it is unit-tested with a
 * character-grid fixture.</p>
 */
public final class AStar {
	/**
	 * The size-1 player step masks from {@code PathFinder.findPath1}. They include the
	 * blocked-by-loc, unwalkable and floor-decoration bits, plus the wall bits of the edge the
	 * step crosses.
	 */
	public static final int STEP_W = 0x12C0108;
	public static final int STEP_E = 0x12C0180;
	public static final int STEP_S = 0x12C0102;
	public static final int STEP_N = 0x12C0120;
	public static final int DIAG_SW = 0x12C010E;
	public static final int DIAG_SE = 0x12C0183;
	public static final int DIAG_NW = 0x12C0138;
	public static final int DIAG_NE = 0x12C01E0;

	public static final int DOOR_COST = 5;

	private static final int[] DX = {-1, 1, 0, 0, -1, 1, -1, 1};
	private static final int[] DY = {0, 0, -1, 1, -1, -1, 1, 1};

	/** A door crossing: the step {@code tiles[fromIndex] -> tiles[toIndex]}, and the door loc. */
	public static final class Crossing {
		/** Index in {@link Path#tiles} of the tile walked from (always reachable). */
		public final int fromIndex;
		/** Index in {@link Path#tiles} of the tile behind the door. */
		public final int toIndex;
		/** {@code (x << 8) | y} of the tile that holds the door loc. */
		public final int doorKey;

		Crossing(int fromIndex, int toIndex, int doorKey) {
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
			this.doorKey = doorKey;
		}

		public int doorX() {
			return keyX(doorKey);
		}

		public int doorY() {
			return keyY(doorKey);
		}
	}

	public static final class Path {
		public final List<int[]> tiles;
		public final int cost;
		/** The crossings on the path, in order. Empty when there is no door. */
		public final List<Crossing> crossings;
		/** True when the goal was outside the scene and an edge tile was used instead. */
		public final boolean approximate;

		Path(List<int[]> tiles, int cost, List<Crossing> crossings, boolean approximate) {
			this.tiles = tiles;
			this.cost = cost;
			this.crossings = crossings;
			this.approximate = approximate;
		}

		public int[] destination() {
			return tiles.get(tiles.size() - 1);
		}

		/** The crossing that starts at {@code fromIndex}, or null. */
		public Crossing crossingAt(int fromIndex) {
			for (Crossing crossing : crossings) {
				if (crossing.fromIndex == fromIndex) {
					return crossing;
				}
			}
			return null;
		}
	}

	private AStar() {
	}

	public static int key(int x, int y) {
		return x << 8 | y;
	}

	public static int keyX(int key) {
		return key >> 8;
	}

	public static int keyY(int key) {
		return key & 0xFF;
	}

	public static boolean inBounds(CollisionSource source, int x, int y) {
		return x >= 0 && y >= 0 && x < source.width() && y < source.height();
	}

	/** The mask a straight step in that direction checks on the destination tile. */
	public static int straightMask(int dx, int dy) {
		if (dx == -1) {
			return STEP_W;
		}
		if (dx == 1) {
			return STEP_E;
		}
		if (dy == -1) {
			return STEP_S;
		}
		return STEP_N;
	}

	/** The mask a diagonal step in that direction checks on the destination tile. */
	public static int diagonalMask(int dx, int dy) {
		if (dx == -1) {
			return dy == -1 ? DIAG_SW : DIAG_NW;
		}
		return dy == -1 ? DIAG_SE : DIAG_NE;
	}

	/**
	 * The door this blocked straight step crosses, as a tile key, or -1 when the step is clear
	 * or no door permits it. Diagonal steps never cross a door.
	 */
	static int crossingKey(CollisionSource source, int fromX, int fromY, int dx, int dy, Set<Integer> doors) {
		if (doors == null || doors.isEmpty() || (dx != 0 && dy != 0)) {
			return -1;
		}
		int toX = fromX + dx;
		int toY = fromY + dy;
		if (!inBounds(source, toX, toY)) {
			return -1;
		}
		if ((source.flags(toX, toY) & straightMask(dx, dy)) == 0) {
			return -1;
		}
		int toKey = key(toX, toY);
		if (doors.contains(toKey)) {
			return toKey;
		}
		int fromKey = key(fromX, fromY);
		if (doors.contains(fromKey)) {
			return fromKey;
		}
		return -1;
	}

	/** Whether a single orthogonal/diagonal step is allowed (doors at extra cost). */
	public static boolean canStep(CollisionSource source, int fromX, int fromY, int dx, int dy, Set<Integer> doors) {
		int toX = fromX + dx;
		int toY = fromY + dy;
		if (!inBounds(source, toX, toY)) {
			return false;
		}
		if (dx != 0 && dy != 0) {
			// The destination's corner mask, then both orthogonal moves — no corner cutting,
			// and no door waiving (diagonals never cross a door).
			if ((source.flags(toX, toY) & diagonalMask(dx, dy)) != 0) {
				return false;
			}
			return canStep(source, fromX, fromY, dx, 0, null) && canStep(source, fromX, fromY, 0, dy, null);
		}
		if ((source.flags(toX, toY) & straightMask(dx, dy)) == 0) {
			return true;
		}
		return crossingKey(source, fromX, fromY, dx, dy, doors) >= 0;
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
			return new Path(single, 0, new ArrayList<Crossing>(), approximate);
		}

		int size = width * height;
		int[] cameFrom = new int[size];
		int[] costs = new int[size];
		int[] doorUsed = new int[size];
		boolean[] closed = new boolean[size];
		java.util.Arrays.fill(cameFrom, -1);
		java.util.Arrays.fill(costs, Integer.MAX_VALUE);
		java.util.Arrays.fill(doorUsed, -1);

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
				return build(cameFrom, doorUsed, current.index, costs[current.index], approximate, width);
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
				int doorKey = crossingKey(source, cx, cy, dx, dy, doors);
				int step = (dx != 0 && dy != 0 ? 14 : 10) + (doorKey >= 0 ? DOOR_COST * 10 : 0);
				int tentative = costs[current.index] + step;
				if (tentative < costs[nIndex]) {
					costs[nIndex] = tentative;
					cameFrom[nIndex] = current.index;
					doorUsed[nIndex] = doorKey;
					open.add(new Node(nIndex, tentative + heuristic(nx, ny, goalX, goalY)));
				}
			}
		}

		if (approximate && bestIndex != startIndex) {
			return build(cameFrom, doorUsed, bestIndex, costs[bestIndex], true, width);
		}
		return null;
	}

	private static int index(int x, int y, int width) {
		return y * width + x;
	}

	private static Path build(int[] cameFrom, int[] doorUsed, int goalIndex, int cost, boolean approximate, int width) {
		List<int[]> reversed = new ArrayList<int[]>();
		int current = goalIndex;
		while (current != -1) {
			int x = current % width;
			int y = current / width;
			reversed.add(new int[]{x, y});
			current = cameFrom[current];
		}
		List<int[]> tiles = new ArrayList<int[]>(reversed.size());
		for (int i = reversed.size() - 1; i >= 0; i--) {
			tiles.add(reversed.get(i));
		}

		List<Crossing> crossings = new ArrayList<Crossing>();
		for (int i = 0; i + 1 < tiles.size(); i++) {
			int[] to = tiles.get(i + 1);
			int doorKey = doorUsed[index(to[0], to[1], width)];
			if (doorKey >= 0) {
				crossings.add(new Crossing(i, i + 1, doorKey));
			}
		}
		return new Path(tiles, cost, crossings, approximate);
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
