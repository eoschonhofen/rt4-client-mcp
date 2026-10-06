# MCP-25 — ♻️ Transport: HTTP thread starvation and sessions that never expire

**Type:** refactor · **Severity:** medium · **Depends on:** MCP-03

**Goal:** Long-blocking tools can't lock out other calls, and the session table stays bounded.

## Problem
1. **Thread starvation.** `McpHttpServer.java:58` uses `newFixedThreadPool(4)`.
   - `wait_for` blocks a thread for up to 60 s.
   - `continue_dialogue` can block for up to 50 steps × 3 s = 150 s.
   - `choose_option` blocks for up to 3 s.

   Four concurrent waits make the server unresponsive, including `nav_cancel` and `ping`.
2. **Unbounded sessions.** Every `initialize` adds to `sessions`, and only a client `DELETE` removes one.
   Clients that reconnect without DELETE leak entries for the life of the process.
3. **`continue_dialogue` can run longer than the client waits.** 150 s is longer than typical MCP client
   timeouts. The agent gets a transport error while the clicks keep going.

## Files
- `client/src/main/java/rt4/mcp/McpHttpServer.java`
- `client/src/main/java/rt4/mcp/tools/HelperTools.java`
- `client/src/test/java/rt4/mcp/McpHttpServerTest.java`

## Implementation
- Choose one of:
  - a bounded but larger pool (for example 16) with a small queue that rejects with HTTP 503 when full; or
  - a cached daemon pool with a concurrency cap enforced by a `Semaphore`.

  Either way, make `ping`, `tools/list`, `nav_cancel` and `get_status` usable while waits are pending.
- Sessions:
  - store `lastSeenNanos`, update it on every request, and evict entries idle for more than 30 min on
    access (no extra thread)
  - cap the table at about 64 entries, evicting the oldest
- `continue_dialogue`: add an overall deadline (for example 30 s) on top of `max_steps`, and return
  `stopped: "deadline"`.

## Acceptance criteria
- Five concurrent `wait_for(timeout 30 s)` calls plus a `ping` → the ping answers within 100 ms.

## Tests
- `McpHttpServerTest`:
  - N blocking tool calls in parallel, then a ping answers
  - an idle session is evicted after the TTL (injectable clock)
  - the session cap holds
