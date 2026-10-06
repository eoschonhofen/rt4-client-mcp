# AIO-13 — MCP auto-port and window title

**Goal:** Several agent clients run on one machine. Each binds its own MCP port, and the window
says which, so the operator can wire each agent to the right client.

**Repo:** client · **Depends on:** —

## Files
- `client/src/main/java/rt4/mcp/McpServer.java`
- `client/src/main/java/rt4/mcp/McpHttpServer.java` (expose the bound port)
- `client/src/main/java/rt4/GameShell.java` (title, about line 725)
- `client/src/main/java/rt4/aionly/TitleMessage.java` (from AIO-12)

## Implementation
1. `McpServer.start` tries `config.port` … `config.port + 9` in order. On `BindException` it moves to the next
   port. Other `IOException`s stop the search, as today.
2. Record the bound port in `McpServer.boundPort()` (volatile, `-1` if none).
3. Startup log per instance. The server name has a suffix after the first port, so `claude mcp add` doesn't
   collide:
   ```
   [MCP] listening on http://127.0.0.1:43601/mcp
   [MCP] claude mcp add --transport http rt4-43601 http://127.0.0.1:43601/mcp --header "Authorization: Bearer <token>"
   ```
   Port 43600 keeps the plain name `rt4`, so existing setups don't break.
4. All instances share `mcp_token` from `config.json`. Only the first start generates it, and the write-back
   is already atomic.
5. Window title (`GameShell` `modeString`): append ` — MCP :43601`, or ` — MCP unavailable` when `boundPort() == -1`.
6. Under lockdown with no port bound, the client can't be controlled. Show a persistent
   `TitleMessage`: "MCP unavailable: ports 43600–43609 busy. Close a client and restart this one."

## Acceptance criteria
- Three clients started in a row bind 43600, 43601 and 43602. Their titles and logs show the ports.
- The 11th client shows "MCP unavailable" in the title and on the title screen, and doesn't crash.
- `mcp_enabled=false` → no listener and no title suffix.

## Tests
- `McpHttpServerTest` (exists): occupy port N with a plain `ServerSocket`, start with base N, and assert it binds
  N+1. With all 10 occupied, `start` returns null.
- A pure helper for the server name and the `claude mcp add` line: `serverName(43600) == "rt4"`, `serverName(43601) == "rt4-43601"`.
