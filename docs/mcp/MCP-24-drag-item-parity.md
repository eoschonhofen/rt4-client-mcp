# MCP-24 — 🐛 `drag_item` always swaps locally, unlike a real drag

**Type:** bug · **Severity:** low · **Depends on:** MCP-10

**Goal:** `drag_item` updates the local inventory exactly as a real drag does, and refuses drags the client
wouldn't allow.

## Problem
`tools/InputTools.java:112` always calls `component.swapObjs(fromSlot, toSlot)`. The real drag handler
(`Protocol.java` around 2740-2767) has three cases:
- `isObjReplaceEnabled()` → move the item into the target slot and clear the source
- `inserting == 1` (bank insert mode) → shift the slots in between
- otherwise → swap

In bank insert mode the local view and the server disagree until the next inventory update, and later
`if:…:slot` targets point at the wrong items. The client also only sends packet 231 when the component's
server-active properties allow dragging. The tool skips that check.

## Files
- `client/src/main/java/rt4/mcp/tools/InputTools.java`
- `client/src/main/java/rt4/mcp/DragPackets.java` (the local-update logic can live here as a pure function
  over `int[]` arrays)
- `client/src/test/java/rt4/mcp/DragPacketsTest.java`

## Implementation
- Extract `DragPackets.applyLocal(int[] types, int[] counts, int from, int to, boolean replace, boolean insert)`
  that mirrors the three branches.
- Look up `InterfaceList.getServerActiveProperties(component)`. Refuse with a `ToolException` when
  `isObjSwapEnabled()` (or the equivalent drag flag the client checks) is false.
- Reject `fromSlot == toSlot` and slots past the end.

## Acceptance criteria
- Bank with insert mode on: dragging slot 5 to slot 1 shifts slots 1–4 right, both locally and on the
  server (re-open the bank and compare).

## Tests
- `DragPacketsTest.applyLocal`: swap, insert forward, insert backward, and replace.
