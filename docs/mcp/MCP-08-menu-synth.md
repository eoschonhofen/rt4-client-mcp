# MCP-08 — Menu synthesizer: `list_actions` / `do_action` for world targets

**Goal:** For any target, produce the exact minimenu entries the UI would show if you right-clicked it.
Then execute one through the real `MiniMenu.doAction`.
The packets come out byte-identical to a human click.

**Depends on:** MCP-05, MCP-07

## Background (how the UI does it)
- Every frame, `MiniMenu.addEntries(...)` (`MiniMenu.java:1184`) rebuilds the menu from `Model.pickResults` (entities under the mouse).
  It calls `add(cursor, key, opName, intArg1, action, op, intArg2)`, which fills the parallel arrays `ops`, `opBases`, `actions`, `keys`, `intArgs1`, `intArgs2` and `cursors`, with size `MiniMenu.size`.
- Clicking calls `MiniMenu.doAction(index)` (`:446`). It switches on `actions[index]` (constants at `:148–214`) and writes the packet, usually after a client-side `PathFinder.findPath*`.
- Entry builders:
  - `addNpcEntries(npcType, sceneX, key, sceneY)` at `:1411`
  - `addPlayerEntries(playerIdx, sceneY, player, sceneX)` at `:1504`
  - Locs and objstacks are **inlined** in `addEntries`, starting around `:1220`

## Files
- `rt4/MiniMenu.java`, a refactor that changes no behavior. Extract the inlined loc branch and the objstack branch of `addEntries` into
  `static void addLocEntries(long pickKey, int sceneX, int sceneY)` and `static void addObjStackEntries(int sceneX, int sceneY)`.
  `addEntries` then calls them. Keep the `itemTargetMode` / `isTargeting` sub-branches inside the extracted methods.
- `rt4/mcp/MenuSynth.java`
- `rt4/mcp/tools/ActionTools.java`

## Implementation

### `MenuSynth.build(Target) → List<Entry>` (game thread)
1. **Save** the live menu state: `size` plus the seven arrays (copy the `size` prefix only), and `menuOpen` / layout fields if a menu is open.
2. Set `MiniMenu.size = 0`.
3. Dispatch by target kind:
   - NPC → `addNpcEntries(npc.type, npc.movementQueueX[0], index, npc.movementQueueY[0])`. Check the arg order against the call at `:1299`.
   - Player → `addPlayerEntries(index, sy, player, sx)`.
   - Loc → `addLocEntries(key, sx, sy)` with the key from `Targets.resolve`.
   - Obj → `addObjStackEntries(sx, sy)`, then filter the entries to the requested obj id (`keys`/`intArgs` carry it).
   - Tile → a single synthetic WALK_HERE entry (action 60, args sx/sy), mirroring `addEntries` `:1201`.
   - If `itemTargetMode == 1` (an item is selected via "Use") or `isTargeting` (a spell is selected), the builders already emit the "Use X -> Y" / "Cast X -> Y" entries. No special-casing is needed; this is why the builders are reused instead of reimplemented.
4. **Copy out** the entries: `{ index, op: strip(ops[i]), subject: strip(opBases[i]), action: actions[i] }`.
5. **Restore** the saved state.

### `list_actions(target)`
Returns `[{ op, subject }]` in menu order (top entry = left-click default), plus `cancel_hint` when targeting mode is active.

### `do_action(target, op, subject?)`
1. `build(target)`, then pick the entry whose op equals `op` case-insensitively, and `subject` if given; this disambiguates stacked objs or same-named ops. No match → `ToolException("no op 'X' on <target>; available: [...]")`.
2. Rebuild for real: save state, set `size = 0`, run the same builder, and find the entry index.
3. Set `Mouse.clickX`/`clickY` to the target's projected screen position if it's on screen, else to the viewport centre.
   This is purely cosmetic: `doAction` uses them for the yellow/red `Cross`.
4. Call `MiniMenu.doAction(idx)`, then restore the saved menu state.
5. Cancel any active `NavTask` (MCP-13) unless the call comes from `NavTask` itself.
6. Return the ack: `{ ok: true, op, subject, target, tick }`.
   `ok` means the packet was queued. It doesn't mean the action succeeded server-side; the agent checks with `wait_for` / `get_chat`.

### Selection state
- "Use" on an inventory item and selecting a spell set `itemTargetMode`/`isTargeting` (MCP-09 tool path). The next `do_action` consumes them.
- Add a `cancel_selection` tool that calls `MiniMenu.cancelTargeting()` and resets `itemTargetMode = 0`.

### Gotchas to verify while implementing
- Some `doAction` branches read `MiniMenu` selection fields (`selectedObjSlot`, `selectedObjId`, `MiniMap.selectedComponentId`) set by an earlier "Use".
  Restoring the saved state must **not** clobber those. Only save and restore the entry arrays and `size`.
- `addNpcEntries` may skip "Attack" or reorder it by combat level (`VarpDomain` attack-option settings). That's the correct UI behavior; keep it.
- `doAction` for walk-to-entity actions calls `PathFinder.findPath*`. If no path exists, the UI still sends the packet; mirror that.

## Acceptance criteria
- Packet capture (`-Dmcp.debug` logs the outbound opcodes and args) for `do_action(npc:X, "Talk-to")` is byte-identical to a real right-click → Talk-to on the same NPC.
- Works on an NPC behind the camera (off-screen).
- "Use" a tinderbox (MCP-09), then `list_actions(obj:logs…)` shows "Use Tinderbox -> Logs", and `do_action` lights the fire.
- After `do_action`, an open right-click menu is still intact.

## Tests
- Unit-testing the builders isn't feasible (static game state).
- Unit-test the pure `EntryMatcher`: op and subject matching, case folding, tag stripping, the ambiguity error message.
- The rest is covered by the MCP-15 e2e run.
