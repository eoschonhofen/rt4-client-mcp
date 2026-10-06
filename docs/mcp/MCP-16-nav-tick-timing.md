# MCP-16 — 🐛 `walk_to` stuck and door timers count frames, not ticks

**Type:** bug · **Severity:** high · **Depends on:** MCP-13

**Goal:** Make stuck detection and door waits use game ticks (600 ms), as MCP-13 specifies, so normal walks
are not reported as `FAILED("stuck at …")`.

## Problem
`nav/NavTask.java:56-57` defines `STUCK_FRAMES = 8` and `DOOR_WAIT_FRAMES = 6`. `NavTask.step()` runs once
per `GameThread.drain()`, which is one logic cycle (`GameShell.FIXED_UPDATE_RATE` = 20 ms), so:

- stuck fires after about 9 × 20 ms = 180 ms without a tile change. The player only changes tile once per
  600 ms server tick, so every walk is "stuck" before the first step lands
- `stuckFrames` is not reset after the single re-plan, so the next frame on the same tile fails the task
- door waits give up after 6 × 20 ms = 120 ms, before the server can send the loc change

The server keeps walking the first leg that was already sent, which hides the bug on short walks. The smoke
test (`scripts/mcp-smoke.py`, `step_walk`) then falls into its "server refused it" `Skip` branch.

## Files
- `client/src/main/java/rt4/mcp/nav/NavTask.java`
- `client/src/main/java/rt4/mcp/nav/LiveNavDriver.java`
- `client/src/test/java/rt4/mcp/nav/NavTaskTest.java`

## Implementation
1. Add `int tick()` to `NavTask.Driver`. `LiveNavDriver` returns `TickTracker.tick()`, and the test fake
   advances it explicitly.
2. Rename the constants to `STUCK_TICKS = 8` and `DOOR_WAIT_TICKS = 6`.
3. Stuck detection: store `lastProgressTick` (the tick at which the player tile last changed, or at which
   the current leg was issued). Treat it as stuck when `driver.tick() - lastProgressTick > STUCK_TICKS`.
4. Reset the progress tick on every `issueLeg` and after the re-plan. Without that, the first leg has no
   grace period and the re-plan fails immediately.
5. Door wait: store `doorOpenedTick` when `open()` is sent, and give up after
   `driver.tick() - doorOpenedTick >= DOOR_WAIT_TICKS`.
6. Keep per-frame stepping for responsiveness (cancellation, arrival). Only the timers change unit.

## Acceptance criteria
- A 15-tile open-field walk ends `ARRIVED`, and `nav_status.last_reason` is null.
- A walk that is really blocked (a target behind an unopenable fence) ends `FAILED("stuck …")` after about
  5–10 s, not instantly.

## Tests
- `NavTaskTest`, with a fake driver whose tick advances once every 30 `step()` calls:
  - no stuck failure while the player moves one tile per tick
  - stuck failure only after more than 8 ticks without movement, with exactly one re-plan before it
  - the re-plan resets the timer
  - the door wait spans 6 ticks, not 6 steps
