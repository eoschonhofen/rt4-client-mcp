# MCP-20 — 🐛 `wait_for`: wrong `ticks_waited`, status read off the game thread

**Type:** bug · **Severity:** medium · **Depends on:** MCP-12

**Goal:** `wait_for` outcomes report the real wait length, and never read game state from an HTTP thread.

## Problem
1. **`ticks_waited` is absolute.** `Waiters.evaluate` (`Waiters.java:149,159`) passes `current.tick()`,
   which is ticks since the client started, not since the wait began. Timeouts report `0`.
2. **Off-thread reads.** On timeout or interrupt, `Waiters.await` builds `status()` on the HTTP thread, and
   `WaitTool.java:86` does the same when a waiter was cancelled. `status()` reads `PlayerList.self`,
   `PlayerSkillXpTable` and `Player.plane` without the game thread, which breaks the MCP-04 rule.
3. **`nav_done` with a task id that was replaced** never becomes true. `NavTask.isFinished(task)` only
   matches the *last* finished id, so a task that was cancelled and then followed by another one is lost.

## Files
- `client/src/main/java/rt4/mcp/Waiters.java`
- `client/src/main/java/rt4/mcp/tools/WaitTool.java`
- `client/src/main/java/rt4/mcp/nav/NavTask.java`
- tests: `WaitersTest`, `ConditionsTest`

## Implementation
- Store `startTick` on the `Waiter` at `register` time, and report `current.tick() - startTick` everywhere,
  including timeouts.
- Timeout path: complete the outcome *on the game thread*. Either:
  - (a) `GameThread.call(() -> Waiters.status())` with a short timeout, falling back to no status; or
  - (b) have `evaluate()` also expire waiters whose deadline passed (store `deadlineNanos` on the waiter),
    so the HTTP thread just waits on the future with a little slack.

  Prefer (b): it removes the race between timeout and completion.
- `NavTask`: remember the outcome of every finished task id in a small bounded map (for example the last
  16). `isFinished(id)` is true when the id is in that map.

## Acceptance criteria
- `wait_for([ticks n=5])` reports `ticks_waited` of 5 or 6.
- A timed-out wait reports the elapsed ticks and a status block, without touching game state off-thread.

## Tests
- `WaitersTest`: the fake view's tick starts at 1000, and `ticks_waited` is relative.
- The timeout path produces the status from the fake view on the "game thread" (the test drains it).
- `nav_done(task=1)` is true after task 1 was cancelled and task 2 started.
