# MCP-26 — ♻️ Cleanup: dead code, misleading names, key-release order, plane handling

**Type:** refactor · **Severity:** low · **Depends on:** —

**Goal:** Small correctness fixes and tidy-ups that don't deserve their own tickets. Make one atomic commit
per bullet group.

## Items

### Dead code and comments
- `McpConfig.logStartup()` is unused and duplicates the startup lines in `McpServer.start`. Delete it, or
  make `McpServer` call it.
- The comment at `McpConfig.java:107` ("fall back to a fresh one rather than destroying whatever is there")
  contradicts the code, which returns false. Reword it.
- `McpConfig.writeToken`: delete the temp file when `Files.write` or `Files.move` throws, so
  `config.json.*.tmp` files don't pile up.
- Formatting: the line break after `{` is missing in `ToolResult.error(String)` and
  `MenuSynth.cancelSelection()`.

### Naming
- `MenuSynth.Ack.tick` holds `GameThread.frame()`. Either rename it to `frame`, or put
  `TickTracker.tick()` in it. The `do_action` output field `tick` should match the other tools, which
  report ticks.
- `MenuSynth.act` sets `Mouse.clickX/Y` to the canvas centre for the cross sprite and never restores
  them. Save and restore them with the rest of the snapshot.

### `InputInjector` held keys
- `PENDING` is a FIFO, and `tick()` stops at the first entry that isn't due yet. A long hold followed by a
  short one delays the short release. Use a `PriorityQueue` ordered by `releaseFrame`, guarded the same way
  (game thread only).

### Plane handling
- `MenuSynth.populate` for `LocTarget` and `ObjTarget` builds the menu through `MiniMenu.addLocEntries` and
  `addObjStackEntries`, which read `Player.plane`, not the target's plane. When they differ, return a clear
  `ToolException("target is on plane P, you are on plane Q")` instead of the confusing "no op 'X'".
- `NavTask.tick`: if `driver.plane() != goalPlane` (the player climbed or teleported mid-walk), finish with
  `FAILED("plane changed")`.

### Config defaults
- `mcp_enabled` defaults to `true`, and the server binds on every client start, including against a live
  server. Consider defaulting to `false`, so it is opt-in, and logging a warning when `ip_address` isn't
  loopback. This is a policy decision: confirm before changing it.
- **Decision (2026-10-06):** keep `true`. The AI-only world plan (`docs/ai-only/`) points the client at a
  remote server on purpose, so opt-in would only add a step. The client logs a warning at startup when
  `ip_address` is not loopback (`localhost`, `127.0.0.0/8` or `::1`).

## Tests
- `InputInjector`: two holds, 500 ms then 40 ms, release in deadline order.
- `NavTaskTest`: a plane change fails the task.
- `McpConfigTest`: no temp file is left after a simulated write failure.
