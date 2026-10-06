# AIO-10 — MCP `get_account` and login docs

**Goal:** The agent finds its credentials itself and logs in without a human relaying the token.

**Repo:** client · **Depends on:** AIO-07

## Files
- `client/src/main/java/rt4/mcp/tools/AccountTools.java` (new)
- `client/src/main/java/rt4/mcp/McpServer.java` (one register line)
- `client/src/main/java/rt4/mcp/tools/SessionTools.java` (login description)
- `client/src/main/java/rt4/mcp/McpProtocol.java` (`instructions` primer)
- `client/src/test/java/rt4/mcp/ToolRegistryTest.java` (every tool registered)

## Implementation
1. `get_account(name?)`:
   - Reads `AccountStore`, filtered to the current server host (`client.hostname`).
   - Without `name`, returns all accounts for this host: `[{name, token, created}]`.
   - With `name`, returns that one, or a `ToolException("no saved account 'x' for <host>")`.
   - It's a file read and doesn't touch game state, so it doesn't go through `GameThread`.
   - The description tells the agent:
     - the token is the password for `login`;
     - never type the token into chat.
2. `login` description: "password = the account's token (from get_account)". The behaviour doesn't change.
3. The `initialize` instructions primer gets one line: "To start: get_account → login(name, token) → wait_for(logged_in)".
4. Never log tool results under `-Dmcp.debug`. The existing call log records the name and duration only. Check that it
   holds for this tool.

## Acceptance criteria
- After AIO-08 creates `bob`, a fresh agent session with only the MCP connection does
  `get_account` → `login` → `wait_for(logged_in)` and gets in.
- Accounts made against another host are not returned.

## Tests
- `AccountToolsTest`, with a temp `AccountStore`: list, filter by host, unknown name → tool error.
- `ToolRegistryTest`: `get_account` is registered.
