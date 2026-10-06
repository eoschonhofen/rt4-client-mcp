# AIO-07 — Local `accounts.json` store

**Goal:** Every account created on this machine is kept locally (name, token, server, created),
so the agent can fetch it through MCP (AIO-10) and nothing depends on copying it from the screen.

**Repo:** client · **Depends on:** —

## Files
- `client/src/main/java/rt4/aionly/AccountStore.java` (new, pure, no `rt4.*` statics)
- `.gitignore` (`accounts.json`)

## Implementation
1. Location: next to the resolved `config.json` path (`client.java:227–254`). The file is `accounts.json`.
2. Format:
   ```json
   { "accounts": [ { "name": "bob", "token": "…20 chars…", "host": "play.example.org", "created": "2026-10-06T12:00:00Z" } ] }
   ```
   `host` is the server the account was made on, so a dev account and a public account don't get mixed up.
3. API:
   - `static AccountStore open(Path)`
   - `List<Account> list()`
   - `void add(Account)`: replaces an entry with the same `name` + `host`
   - `Optional<Account> find(name, host)`
4. Writes are atomic: temp file + `ATOMIC_MOVE` (same pattern as `McpConfig.writeToken`), and the temp file is deleted on
   failure. On POSIX, set permissions `rw-------`.
5. Thread safety: methods are `synchronized`. AIO-08 calls from the game thread and AIO-10 from MCP threads.
6. A malformed file is never overwritten silently. Rename it to `accounts.json.bad-<ts>`, log a warning,
   then start empty.

## Acceptance criteria
- Two accounts created in a row both appear in the file. Restarting the client keeps them.
- The file mode is 600 and the file is ignored by git.

## Tests (`AccountStoreTest`, temp dir)
- Add/list/find round trip.
- Replace on duplicate.
- Atomic write leaves no temp file.
- A malformed file is renamed and not clobbered.
- Permissions are 600 (POSIX only, otherwise skipped).
