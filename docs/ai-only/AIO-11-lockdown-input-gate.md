# AIO-11 — Lockdown build flag, input gate, idle keep-alive

**Goal:** In a release build, real mouse and keyboard input does nothing in-game, while MCP's
synthetic input still works. The agent isn't idle-kicked for having no real input.

**Repo:** client · **Depends on:** —

## Files
- `client/build.gradle` (property → generated resource)
- `client/src/main/java/rt4/aionly/Lockdown.java` (new)
- `client/src/main/java/rt4/aionly/InputGate.java` (new, pure decision logic)
- `client/src/main/java/rt4/Mouse.java`, `Keyboard.java`, `JavaMouseWheel.java` (gate checks at the listener entry points)
- `client/src/main/java/rt4/mcp/InputInjector.java` (tag synthetic keys)
- `client/src/main/java/rt4/mcp/McpProtocol.java` (idle reset)
- `client/src/main/java/plugin/PluginRepository.java` (skip plugins under lockdown)

## Implementation
### Build flag (compile-time only)
1. `gradle -PaiOnly=false` builds a dev jar. The default is `true`. `processResources` writes
   `rt4/aionly/build.properties` with `lockdown=<value>`.
2. `Lockdown.ENABLED` is read once from that resource. A missing resource means `true` (fail closed).
3. **No system property or config key overrides it.** A runtime switch would make lockdown a one-flag bypass
   for any operator. Dev builds come from the gradle property only.
4. `./gradlew run` for local development passes `-PaiOnly=false` in the docs (AIO-16).

### Input gate
1. `InputGate.allowMouse(boolean synthetic, int gameState)` and `allowKey(boolean synthetic, int gameState)` are pure.
   - They return true when lockdown is off, the event is synthetic, or the title-screen hole applies (AIO-12).
2. Mouse:
   - Synthetic mouse events already set `Mouse.syntheticPress` around `canvas.dispatchEvent` on the EDT.
     The EDT is single-threaded, so the flag is reliable there.
   - Gate every `Mouse` listener method: pressed, released, clicked, moved, dragged, entered/exited.
   - Gate `JavaMouseWheel`.
   - Real moves are dropped too, so a spectator's hover can't change tooltips or the minimenu.
3. Keyboard:
   - Synthetic keys call `Keyboard.instance.keyPressed/keyTyped/keyReleased` directly on the **game thread**.
   - Add `InputInjector.injecting` (set and cleared around those calls in `typeText`, `pressKey` and `tick`).
   - A key event counts as synthetic only if `injecting` is set **and** `Thread.currentThread() == GameThread.owner`.
     Real events arrive on the EDT and are dropped even when they race an injection.
4. Focus events stay ungated, so focus loss still clears held keys.
5. Camera stays frozen for humans because arrows, middle-drag and wheel are all gated. The MCP `camera` tool
   uses `plugin.api.API.SetCamera*` and is unaffected.

### Idle keep-alive
- `Protocol.java:2905` sends packet `245` (idle logout) when both `Mouse.idleLoops` and `Keyboard.idleLoops` exceed 15000.
  Agents acting through `do_action`/`walk_to` never touch those counters, so they get logged out after
  about 5 minutes, with or without lockdown.
- In `McpProtocol`, on every `tools/call` of a non-read tool, set `Mouse.setIdleLoops(0)` and `Keyboard.idleLoops = 0`.
  Do it on the game thread, inside the tool's `GameThread.call`, or as a queued task.
- A pure read (`get_status`, `get_screenshot`, `wait_for`) does **not** reset the counters. An agent that only
  watches still idles out, the same as a human.

### Plugins
- Under lockdown, `PluginRepository` loads no plugins. Some plugins (KondoKit) add Swing panels to the frame that
  take real input outside the canvas, and others hook key events. Dev builds load plugins as today.

## Acceptance criteria
- Release build, in game:
  - Real clicks, key presses, arrows, wheel and middle-drag do nothing.
  - `do_action`, `mouse_click`, `type_text`, `press_key` and `camera` all work.
- An agent issuing `walk_to` every minute for 10 minutes isn't logged out. An agent issuing only `get_status`
  is logged out after the normal idle time.
- Dev build (`-PaiOnly=false`): human input and plugins work as today.
- `java -Drt4.lockdown=false -jar release.jar` stays locked.

## Tests
- `InputGateTest`: the truth table over lockdown × synthetic × gameState.
- `LockdownTest`: a missing resource → enabled. `lockdown=false` → disabled.
- `InputInjector`: `injecting` is set only during the keyboard calls. Exceptions clear it (`finally`).
- Manual checklist for real-input rejection, added to the AIO-16 smoke doc.
