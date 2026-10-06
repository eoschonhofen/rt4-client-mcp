# AIO-14 — Spectator overlay: badge plus last action

**Goal:** Someone watching a client window can tell it's agent-controlled and what the agent did
last, without the overlay polluting what the agent sees.

**Repo:** client · **Depends on:** AIO-11, AIO-13

## Files
- `client/src/main/java/rt4/aionly/SpectatorOverlay.java` (new)
- `client/src/main/java/rt4/aionly/ActionSummary.java` (new, pure)
- `client/src/main/java/rt4/mcp/McpProtocol.java` (record each `tools/call`)
- `client/src/main/java/rt4/mcp/Screenshot.java` (capture order)
- The in-game frame draw path in `client.java`. Hook after interfaces are drawn and before the buffer flip.

## Implementation
1. `ActionSummary.of(toolName, args)` → `String`, or `null` for read-only tools. Read-only tools:
   - `get_*`, `find_entities`, `list_actions`, `wait_for`, `get_screenshot`.
   Redaction:
   - `login` → `login <username>`; the password is never shown.
   - `get_account` → `get_account`.
   - In any string argument, replace every `[a-z0-9]{20}` run with `••••`, in case the agent types a token.
   - Cap at 60 chars.
   Examples:
   - `walk_to 3222,3218`
   - `do_action Attack npc:1234`
   - `type_text "hello"`
2. `McpProtocol` `tools/call`: on success or tool error, set `SpectatorOverlay.lastAction` (volatile String) and
   `lastCallMillis`.
3. Badge text: `AI-CONTROLLED · MCP :43601 · active` (a call in the last 30 s), `idle`, or `no MCP`.
   Second line: `last: <summary> (<n>s ago)`.
4. Draw it top-left in a small translucent box. Use `API.DrawRect`/`DrawText`-equivalent calls so it works in SD and HD.
   Draw only under lockdown. Dev builds don't show it.
5. **Keep it out of `get_screenshot`.**
   - SD: `Screenshot.capture` copies `SoftwareRaster.pixels`. Draw the overlay after the frame is snapshotted
     for MCP, or keep a pre-overlay copy at the draw hook. Pick the cheaper option after measuring.
   - HD: `GlRenderer.pixelData` comes from `glReadPixels`. Read it before the overlay is drawn, or draw the
     overlay as the last GL step after readback.
   - If either renderer can't do this cheaply, fall back to drawing in the top-left 200×36 px corner, and document
     that the corner is overlay in the `get_screenshot` description.

## Acceptance criteria
- In a release build in game, the badge shows the bound port. After `walk_to`, "last: walk_to x,y" appears
  within one frame.
- `login` never shows the token. Typing a 20-char token via `type_text` shows `••••`.
- `get_screenshot` has no overlay pixels, or the documented fallback corner only, in SD and HD.
- Frame time cost is under 0.5 ms.

## Tests
- `ActionSummaryTest`:
  - Read-only tools → null.
  - login redaction.
  - Token redaction inside text.
  - Truncation.
  - Coordinate and target formatting.
