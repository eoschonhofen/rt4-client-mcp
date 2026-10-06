# MCP-04 — Game-thread command queue and frame hook

**Goal:** Every tool that reads or mutates game state runs on the game thread.
HTTP threads block on a future with a timeout.

**Depends on:** MCP-03

## Files
- `rt4/mcp/GameThread.java` (new)
- `rt4/client.java`: one call in `mainLoop()`

## Implementation
1. `GameThread`:
   ```java
   static final ConcurrentLinkedQueue<FutureTask<?>> queue;
   static <T> T call(Callable<T> fn, long timeoutMs) throws ToolException;
   static void drain();          // called from mainLoop only
   static volatile Thread owner; // set on first drain(), for assertions
   static volatile long frame;   // incremented per drain()
   ```
   - `call()` wraps `fn` in a `FutureTask`, enqueues it and waits with `future.get(timeoutMs)`.
     On timeout it cancels the task and throws `ToolException("game thread did not respond in Nms (loading or frozen?)")`.
     On `ExecutionException` it unwraps: a `ToolException` is rethrown as is, anything else becomes `ToolException(e.toString())`.
   - If called from the game thread itself (`Thread.currentThread() == owner`), run inline. This prevents deadlock.
   - Default timeout 2000 ms. Frames run at about 50 Hz, so this is generous.
2. Hook in `client.mainLoop()` (`client.java:1693`). Insert `rt4.mcp.GameThread.drain();` immediately after `Mouse.loop();`.
   Placement matters: the work then runs after input is polled and before the game-state logic,
   so packets written to `Protocol.outboundBuffer` go out in this frame's flush, exactly like real input.
   - The `gameState == 1000` early return above it is fine, because the queue isn't drained while shutting down.
   - `drain()` caps itself at about 50 tasks or about 8 ms per frame so it can't stall rendering. Leftovers run next frame.
3. Also from `drain()`:
   - Tick `Waiters.evaluate()` (MCP-12).
   - Tick `NavTask.step()` (MCP-13). Both are no-ops until those tickets land.
   Order: tasks → nav → waiters, so waiters see the effects of this frame's actions.
4. Guard helper `GameThread.requireLoggedIn()`. Throws `ToolException("not logged in")` unless `client.gameState == 30`
   (30 = in game; 10 = login screen, per `client.java:622/625`) and `PlayerList.self != null`.
   All world tools call it; `login`, `get_status` and `get_screenshot` don't.

## Acceptance criteria
- A tool that reads `PlayerList.self` from the HTTP thread is impossible by construction: code review checks every tool body runs inside `GameThread.call`.
- With the game window minimized or unfocused, calls still complete; the main loop keeps running.
- 100 concurrent `get_status` calls complete without frame-time spikes over about 10 ms.

## Tests
- `GameThreadTest`, with `drain()` driven manually by a test thread acting as owner:
  - Result propagation.
  - Exception unwrapping.
  - Timeout when `drain()` is never called.
  - Inline execution when called from the owner thread.
  - Per-frame cap respected.
