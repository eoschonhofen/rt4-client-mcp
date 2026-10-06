# MCP-17 — 🐛 A* uses large-NPC collision masks and skips the diagonal tile

**Type:** bug · **Severity:** high · **Depends on:** MCP-13

**Goal:** A* accepts exactly the steps the client's own size-1 pathfinder accepts.

## Problem
1. **Wrong masks.** `nav/AStar.java:23-26` uses `0x12C013E / 0x12C01E3 / 0x12C018F / 0x12C01F8`. These are
   the inner-tile masks from the large-NPC routine (`PathFinder.java:434-485`). They include the wall bits of
   the perpendicular edges, so a tile with any wall on a side can't be entered from most directions. Walking
   along fences, building walls and room edges fails with "no path" or takes long detours. The class Javadoc
   claims these are the `findPath1` masks, which is wrong.
2. **Diagonal tile never checked.** For a diagonal step, `canStep` (`AStar.java:71`) checks only the two
   straight neighbours. The destination tile itself is never checked, so paths can cut diagonally into a
   tree, rock or table (`0x100`).
3. **Swapped names.** `dy == -1` uses a constant called `NORTH_MASK`, but in client coordinates `y - 1` is
   south.

## Reference: size-1 player masks (`PathFinder.java:249-298`)
| Step | Check |
|---|---|
| W `(-1, 0)` | `flags[x-1][y] & 0x12C0108` |
| E `(+1, 0)` | `flags[x+1][y] & 0x12C0180` |
| S `(0, -1)` | `flags[x][y-1] & 0x12C0102` |
| N `(0, +1)` | `flags[x][y+1] & 0x12C0120` |
| SW `(-1,-1)` | `flags[x-1][y-1] & 0x12C010E` and W and S as above |
| SE `(+1,-1)` | `flags[x+1][y-1] & 0x12C0183` and E and S |
| NW `(-1,+1)` | `flags[x-1][y+1] & 0x12C0138` and W and N |
| NE `(+1,+1)` | `flags[x+1][y+1] & 0x12C01E0` and E and N |

## Files
- `client/src/main/java/rt4/mcp/nav/AStar.java`
- `client/src/test/java/rt4/mcp/nav/AStarTest.java`

## Implementation
- Replace the four constants with `STEP_W/E/S/N` and `DIAG_SW/SE/NW/NE` from the table, named by the real
  compass direction.
- `canStep`: a straight step checks one mask. A diagonal step checks the diagonal tile against its corner
  mask and both straight steps against their masks.
- Door handling (straight steps only) keeps working. MCP-18 changes how doors are matched.
- Move the "all wall bits" union used by `LiveNavDriver.doorPassable` out of `AStar`. MCP-18 replaces that
  check anyway.
- Fix the class Javadoc.

## Acceptance criteria
- In Lumbridge castle, `walk_to` from the courtyard to a tile next to an inner wall finds a path that hugs
  the wall.
- No planned path steps diagonally into a tile whose flags contain `0x100`.

## Tests
- `AStarTest` fixtures need a way to place single-edge wall bits (for example a per-tile flag override),
  not only whole blocked tiles:
  - walking parallel to a wall on the N or S edge of the row succeeds in a straight line
  - entering a tile through a walled edge is blocked, and through an open edge is allowed
  - a diagonal into a blocked (`0x100`) tile is refused even when both straight neighbours are clear
  - parity test: for random flag grids, `canStep` agrees with a port of the `findPath1` checks
