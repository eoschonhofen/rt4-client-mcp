# MCP-22 — 🐛 Game-thread hardening: unguarded per-frame hooks, interrupting the game thread

**Type:** bug · **Severity:** medium · **Depends on:** MCP-04

**Goal:** No MCP code path can crash or interrupt the game loop.

## Problem
1. **Unguarded hooks.** `GameThread.drain()` (`GameThread.java:100,104`) runs `navigationStep`,
   `waiterTick` and `InputInjector.tick()` with no try/catch. They run inside `client.mainLoop()`, and
   `GameShell.run` turns any exception there into `error("crash")` and `shutdown`. One NPE in `SceneScan`,
   a condition or a key release kills the client.
2. **Interrupting the game thread.** On timeout, `GameThread.call` (`GameThread.java:65`) calls
   `task.cancel(true)`. If the task is already running on the game thread, that sets the game thread's
   interrupt flag.

## Files
- `client/src/main/java/rt4/mcp/GameThread.java`
- `client/src/main/java/rt4/mcp/nav/NavTask.java`
- `client/src/main/java/rt4/mcp/Waiters.java`
- `client/src/test/java/rt4/mcp/GameThreadTest.java`

## Implementation
- Wrap each hook in its own `try { … } catch (Throwable t)`.
  - Log once per distinct message, rate-limited, with `[MCP]` and the stack trace.
  - On a nav failure: `NavTask.fail("internal error: " + t)` (a new helper that finishes the task as FAILED,
    not CANCELLED), so the agent sees FAILED instead of a hang.
  - On a waiter failure: complete that waiter with reason `"internal error"`. This needs per-waiter
    try/catch inside `Waiters.evaluate`.
- Use `task.cancel(false)` instead of `cancel(true)`. A task already running finishes normally, and only a
  queued one is dropped.
- The timeout message should say whether the task was still queued (not executed) or running (it may have
  executed), so the agent knows if the action might have happened.

## Acceptance criteria
- Forcing an exception in `NavTask.step` (test hook) leaves the client running, and `nav_status` shows
  FAILED with the reason.

## Tests
- `GameThreadTest`:
  - a throwing `navigationStep` doesn't propagate out of `drain()`, and the waiters still run that frame
  - a throwing waiter condition completes only that waiter
  - a timed-out running task doesn't interrupt the draining thread (assert `!Thread.interrupted()` after
    drain)
