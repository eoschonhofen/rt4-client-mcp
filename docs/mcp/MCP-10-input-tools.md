# MCP-10 — Non-menu input: text, keys, drag, camera, raw mouse, login/logout

**Goal:** Cover the UI interactions that don't go through the minimenu.

**Depends on:** MCP-04

## Files
- `rt4/mcp/tools/InputTools.java`
- `rt4/mcp/tools/SessionTools.java`
- `rt4/mcp/InputInjector.java`

## Tools

### `type_text(text, enter=false)`
Covers chat, "Enter amount", GE item search and private messages.
- Inject through the same path as real keys. `Keyboard.keyPressed`/`keyTyped` (`Keyboard.java:301`) translate AWT events into `eventQueue` and the typed-char queue.
- Preferred: build a synthetic `KeyEvent` for each char and call `Keyboard.instance.keyPressed(e)`, `keyTyped(e)` and `keyReleased(e)` on the **game thread**.
  These methods are `synchronized`, so this is safe.
  The source component is `GameShell.canvas`.
- Limit to 80 chars per call (the chatbox limit). Chars outside the client charset → `ToolException`.
- Ack includes `{ typed, enter }`. The agent verifies through `get_chat`.

### `press_key(key, hold_ms=0)`
- Named keys: `enter, escape, backspace, tab, space, up, down, left, right, f1..f12, shift, ctrl`, plus single characters.
  Map them to AWT `VK_*`, then through `Keyboard.CODE_MAP`.
- `hold_ms > 0`: send the press now and schedule the release on a later frame through `GameThread`. Arrow keys rotate the camera this way, just like a human.

### `drag_item(from_slot, to_slot, interface?)`
Rearranges inventory or bank slots.
- Mirror `Protocol.java:2755–2767`: swap locally with `component.swapObjs(from, to)`, then send packet 231:
  ```
  p1isaac(231); p2(toSlot); ip4(component.id); p2add(fromSlot); p1sub(inserting)
  ```
  `inserting` is 1 only for the bank in insert mode (varp-driven); read it the way the UI does in the lines above `:2755`.
- Component-to-component drags (packet 79, `Cs1ScriptRunner.java:1397`) are rare in 530 content. Defer them and log a follow-up.

### `camera(yaw?, pitch?, zoom?)`
- Delegate to `plugin.api.API.SetCameraYaw`, `SetCameraPitch` and `SetCameraZoom` (degrees and raw zoom units).
- Returns the current values.

### `mouse_click(x, y, button="left", move_only=false)` (escape hatch)
- Coordinates are canvas pixels. Dispatch synthetic `MouseEvent`s (moved, pressed, released, clicked) to `GameShell.canvas` on the AWT EDT via `EventQueue.invokeLater`.
  These listeners expect EDT delivery; don't call them from the game thread.
- Tag these events, e.g. by setting `InputInjector.syntheticClickPending`, so the MCP-13 "human click cancels walk_to" rule ignores them.
- Description warns the agent: prefer `do_action`, and pair this with `get_screenshot`.

### `login(username, password, world?)`
- Only valid when `client.gameState == 10` (title screen).
- Calls `LoginManager.startLogin(JagString.parse(username), JagString.parse(password), 0)`, the same entry the title screen uses (`ScriptRunner.java:4958`). Check the auth-type arg.
- World switching (`world` arg) is out of scope for v1; the arg is reserved.
- The ack doesn't wait for login. The agent calls `wait_for(logged_in)`.
- **Never** log the password, including under `-Dmcp.debug`.

### `logout()`
- The UI path is the logout tab button, which sends the logout packet; the server can refuse it during combat.
  Find the packet the logout button sends (`LOGOUT_ACTION` 51 / `LOGOUT_ACTION_2` 59 in `doAction`) and fire it the same way.
- Don't call `LoginManager.processLogout()` directly. That's the client-side teardown, used after the server confirms.

## Acceptance criteria
- `type_text("hello", enter=true)` shows "hello" in public chat, as seen by a second client or the server log.
- The bank "Withdraw-X" prompt accepts `type_text("50", enter=true)`.
- `drag_item(0, 27)` swaps the first and last backpack slots, and the swap persists after relog.
- `login` → `wait_for(logged_in)` succeeds against the local server with any credentials.
- `logout` returns to the title screen.

## Tests
- `KeyMapTest`: named-key → VK mapping and charset validation.
- Packet-building for 231 is extracted into a pure function returning a byte array, tested for byte layout.
  It needs the `Buffer` method semantics. If `Buffer` can't load in tests, re-implement the four encodings in the test as the oracle.
