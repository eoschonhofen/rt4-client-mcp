# MCP-13 — `walk_to`: scene A*, doors, background nav task

**Goal:** Walk to a world tile on the current plane, crossing region loads and opening doors and gates.
It runs in the background and fails honestly when it can't continue.

**Depends on:** MCP-08, MCP-12

## Scope
- In scope: same plane. Any destination; if it's beyond the loaded scene, head toward it and re-plan as regions load. Closed doors and gates that have an "Open" op.
- Out of scope, and reported as failure with a reason: plane changes (stairs, ladders), agility shortcuts, teleports, ferries, quest-locked doors that don't open.

## Files
- `rt4/mcp/nav/AStar.java`: pure, over a `CollisionSource` interface
- `rt4/mcp/nav/CollisionSource.java`, `SceneCollision.java` (adapter over `PathFinder.collisionMaps[plane].flags`)
- `rt4/mcp/nav/DoorIndex.java`: door/gate locs from `SceneScan` (MCP-07)
- `rt4/mcp/nav/NavTask.java`: per-frame driver
- `rt4/mcp/tools/WalkTool.java`: `walk_to`, `nav_status` and `nav_cancel`
- `rt4/Mouse.java`: one flag set on real (non-synthetic) presses, for cancellation

## Collision
- `CollisionMap.flags[x][y]` is 104×104 (minus `xOffset`/`yOffset`; check the constructor).
  It uses the standard RS flag bits:
  - walls N/E/S/W and diagonals: `0x2, 0x8, 0x20, 0x80, 0x1, 0x4, 0x10, 0x40`
  - blocked: `0x100` (loc), `0x200000` (floor deco/blocked), `0x40000` (unwalkable)
  Copy the exact masks from the movement checks in `PathFinder.findPath1` (`PathFinder.java:377`). Don't trust this list blindly.
- `CollisionSource { int flags(int x, int y); int width(); int height(); }`. The tests implement it with a char-grid fixture.

## A* (`AStar.find(src, dst, CollisionSource, Set<DoorEdge> passableDoors) → Path | null`)
- 8-directional with RS diagonal rules: diagonal movement needs both orthogonal moves clear, the same as `findPath1`.
- Octile heuristic. Max 104×104 nodes, so plain arrays plus a binary heap are enough.
- A door edge (wall flags on a tile that holds a door loc with "Open") is traversable at extra cost (+5), and marked on the path.
- If `dst` is outside the scene, use the scene-edge tile closest to `dst` that's reachable and in the right direction as an intermediate goal.

## NavTask (driven by `GameThread.drain()` each frame)
States: `PLANNING → WALKING → OPENING_DOOR → WALKING … → ARRIVED | FAILED(reason) | CANCELLED`.
1. **Plan:** A* from the player's scene tile to the scene-local goal.
2. **Leg:** take the path up to the first door edge, or 20 tiles max (stay within minimap click range, roughly what the UI allows per click).
   Issue the walk the way the UI does: `do_action(tile:x,y, "Walk here")`, which goes through `MenuSynth`.
   The real `doAction` WALK_HERE branch then calls `PathFinder` and sends the packet.
3. **Progress:** each tick, check the player's position. When within 2 tiles of the leg end (or the leg end is reached), start the next leg.
   No progress for 8 ticks → re-plan once (`STUCK_TICKS`, see MCP-16), then `FAILED("stuck at x,y")`.
4. **Doors:** at a door edge, run `do_action(loc:…, "Open")`.
   Wait until the collision flag clears (the server sends a loc change, and `SceneGraph` updates the collision map), or 6 ticks pass.
   Then re-plan. A door that stays shut after 2 attempts → `FAILED("door at x,y won't open")`.
5. **Region change:** if `Camera.originX`/`originY` change (scene rebuilt), re-plan from the new scene.
6. **Arrive:** within `radius` (arg, default 0) of the destination → `ARRIVED`.
7. **Unreachable:** A* returns null with no door options → `FAILED("no path on plane P; may need stairs/ladder/shortcut")`.

## Cancellation
- Any `do_action`, `walk_to`, `login`/`logout`, or `nav_cancel` that doesn't come from `NavTask` cancels the active task. Only one task exists at a time.
- **Human click cancels:** in `Mouse.mousePressed`, set `Mouse.realPressSeq++`, but only for non-synthetic events (MCP-10 tags its events).
  `NavTask` compares the counter each frame.

## Tool surface
- `walk_to(x, y, plane?, radius=0)` → `{ task: 7, state: "PLANNING", est_tiles: 54 }`. It returns immediately.
  A `plane` different from the current one → immediate `ToolException`.
- `nav_status(task?)` → `{ task, state, reason?, position, remaining_tiles, legs_done, doors_opened }`.
- `nav_cancel()`.
- Completion is visible through `wait_for([nav_done])` (MCP-12) and the `get_status.nav` field.

## Acceptance criteria
- Lumbridge castle courtyard → Lumbridge general store: ARRIVED. The route crosses at least one region boundary on the way.
- A route through a closed door (e.g. into a Lumbridge house) opens it and arrives.
- A destination upstairs fails immediately with the plane message.
- A destination behind a locked door fails with "won't open" within about 15 s.
- Clicking the game window during a walk cancels the task, and `nav_status` shows CANCELLED.

## Tests
- `AStarTest` with char-grid fixtures:
  - straight line
  - around a wall
  - diagonal corner-cutting forbidden
  - dead end → null
  - door edge used only when needed
  - out-of-scene goal → edge tile
- `NavTaskTest` with fake `CollisionSource`, `GameView` and action sink:
  - leg splitting at 20 tiles and at doors
  - stuck detection
  - re-plan on origin change
  - cancellation by the human-click counter
