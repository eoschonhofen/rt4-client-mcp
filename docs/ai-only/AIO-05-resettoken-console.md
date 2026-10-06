# AIO-05 — Console `resettoken <name>`

**Goal:** When an agent's token is lost or leaked, the operator issues a new one from the server
console. The old token stops working immediately.

**Repo:** main · **Depends on:** AIO-02, AIO-04

## Files
- `Server/src/main/core/Server.kt` (stdin loop, `printCommands`)
- `Server/src/main/core/auth/AgentToken.kt` (from AIO-04)

## Implementation
1. The stdin loop (`Server.kt:99`) matches whole lines with `when(command)`. Split on whitespace and
   match on the first word. Existing commands behave the same.
2. `resettoken <name>`:
   - Requires `ServerConstants.USE_AUTH`. Otherwise print "auth disabled, tokens unused".
   - Normalize the name the way `AccountRegister` does (lowercase, spaces → `_`).
   - Unknown name (`Auth.storageProvider.checkUsernameTaken` false) → print "no such account".
   - `val token = AgentToken.generate()`, then `GameWorld.getAuthenticator().updatePassword(name, token)`.
   - If the player is online, kick them, so a leaked-token session ends now.
   - Print `token for <name>: <token>` with `println` to the console only. **Not** through `log()`,
     because `write_logs = true` persists log lines to disk.
3. Add the command to `printCommands()`.

## Acceptance criteria
- `resettoken bob` prints a valid 20-char token. The old token fails login and the new one works.
- If `bob` is online, they are disconnected.
- The token doesn't appear in `logs/` or the server log files.
- `stop`, `update`, `help` and `restartworker` still work.

## Tests
- Extract the parsing into a pure `ConsoleCommand.parse(line)` and test it: name normalization,
  missing argument, unknown command.
