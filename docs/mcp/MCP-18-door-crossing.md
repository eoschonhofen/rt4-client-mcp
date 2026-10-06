# MCP-18 — 🐛 Doors: "opened" is never detected, and they can only be crossed from one side

**Type:** bug · **Severity:** high · **Depends on:** MCP-17

**Goal:** `walk_to` opens a closed door or gate on the route, notices that it opened, and crosses it in
either direction.

## Problem
1. **Passability check never succeeds.** `LiveNavDriver.doorPassable` (`nav/LiveNavDriver.java:72`) requires
   the door tile to have *no* wall bits. An opened door is still a wall loc, rotated, so it sets a wall bit on
   another edge of the same tile. The check stays false, and after the wait the second attempt finds no
   "Open" op (the loc now offers "Close"). The task fails with "door at x,y won't open" after the door opened.
2. **One-way crossing.** A wall sets mirrored bits on both tiles it separates. `DoorIndex` keys a door only
   by the loc's own tile, and `AStar.canStep` only waives the block when the *destination* is a door tile.
   Leaving the door tile toward the far side checks the neighbour's mirrored bit, which still blocks. Doors
   whose loc sits on the far tile can't be planned through at all.

## Files
- `client/src/main/java/rt4/mcp/nav/AStar.java`
- `client/src/main/java/rt4/mcp/nav/NavTask.java`
- `client/src/main/java/rt4/mcp/nav/LiveNavDriver.java`
- `client/src/main/java/rt4/mcp/nav/DoorIndex.java`
- tests: `AStarTest`, `NavTaskTest`

## Implementation
- **Door edges, not door tiles.** A blocked *straight* step `from → to` counts as a door crossing when
  `from` or `to` holds a door loc with "Open" *on the edge being crossed*. The edge comes from the loc's
  shape and rotation (`CollisionMap.flagWall`: a straight wall stands on edge `rotation`, an L wall on that
  edge and the next one clockwise). Doors of any other shape may be crossed on any edge. A destination
  blocked as a whole tile (loc or floor) is only explained by a door of unknown shape on that tile.
  Diagonal steps never cross doors.
- **Path records crossings.** `Path.doors` becomes a list of step indices (or `(fromKey, toKey)` pairs) plus
  the door tile, instead of a set of tile keys.
- **Legs.** A leg ends on the tile *before* a crossing, which is always reachable. At the leg end, enter
  `OPENING_DOOR` with the crossing's door target.
- **Opened = the step is allowed now.** `doorPassable(from, to)` returns true when
  `AStar.canStep(collision, from, dir, noDoors)` is true, or when the "Open" loc at the door tile is gone
  (`Targets.findLocKey(...) == 0` for that loc id). Either signal means the server changed the loc.
- On success, re-plan from the current tile, as today.
- Second attempt: if the door target has no "Open" op any more, re-check passability before failing.

## Acceptance criteria
- A Lumbridge house with a closed door: walking in from outside and back out both end `ARRIVED`, and each
  run reports `doors_opened == 1`.
- A door that really stays shut still fails with "won't open" within about 15 s.

## Tests
- `AStarTest`:
  - a door loc on the near tile and on the far tile, crossed in both directions
  - no door crossing on diagonals
  - the cost of a door crossing applies once
- `NavTaskTest`:
  - after `open()` the fake rotates the wall bits (instead of clearing them), and the task still continues
    and arrives
  - the loc disappearing also counts as opened
