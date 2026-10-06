# MCP-27 — ✅ Make the smoke test catch these bugs, and update the docs

**Type:** test + docs · **Severity:** low · **Depends on:** MCP-16 … MCP-19

**Goal:** The e2e smoke run fails loudly on the class of bugs found in the 2026-10-06 review, and the setup
docs describe what is actually installed.

## Problem
- `step_walk` in `scripts/mcp-smoke.py` accepts any `nav_done` and turns a wrong final position into a
  `Skip`. A `FAILED("stuck")` walk passes as "server refused it" (see MCP-16).
- `step_door` sends a raw `do_action(Open)`. It never exercises `walk_to` through a door (MCP-18).
- No step checks that results are schema-valid MCP (MCP-19).
- `SETUP.md` (outer repo) still says "the only edit to the client is the two IPs in config.json".

## Files
- `scripts/mcp-smoke.py`
- `docs/mcp/USAGE.md`
- `../SETUP.md` (outer 2009scape folder, not in this repo)

## Implementation
- `step_walk`:
  - require `nav_status.last_state == "ARRIVED"`
  - walk at least 15 tiles
  - keep the `Skip` only when the server log or chat actually shows a refusal, and the nav state is not
    `FAILED("stuck…")`
- New `step_walk_door`: find a closed door within 20 tiles, then `walk_to` a tile on the far side, then
  `wait_for(nav_done)`, then require ARRIVED and `doors_opened >= 1`. Walk back the same way.
- New `step_schema`: for each tool called during the run, assert `structuredContent` is absent or a dict,
  `content` is a list, and image blocks have `data` and `mimeType`.
- `SETUP.md`: replace the "files added" paragraph with a short note that the client is on branch
  `mcp-server` with the MCP server, and point to `client/docs/mcp/USAGE.md`.
- Outer repo hygiene: add `client/`, `.toolchain/`, `.mavenhome/`, `.gradlehome/`, `.home/`, `logs/`,
  `builddir/` to `.git/info/exclude`, so `git status` in 2009scape stays clean. This is local only and
  touches no tracked file.

## Acceptance criteria
- Before MCP-16/17/18 land, the smoke run *fails* (not skips) at `step_walk` or `step_walk_door`. After
  they land, it passes.
