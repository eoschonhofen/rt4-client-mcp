# MCP-01 — Config keys, token generation, startup log

**Goal:** `config.json` controls the MCP server. A missing token is generated once and
persisted. On startup the client prints a ready-to-paste `claude mcp add` command.

**Depends on:** —

## Files
- `client/src/main/java/rt4/GlobalJsonConfig.java` (add fields)
- `client/src/main/java/rt4/mcp/McpConfig.java` (new)
- `client/config.json` (add keys)

## Implementation
1. Add to `GlobalJsonConfig`. Gson leaves missing keys at their Java defaults, so initialize them inline:
   ```java
   public boolean mcp_enabled = true;
   public int mcp_port = 43600;
   public String mcp_token = "";
   ```
   `GlobalJsonConfig.load()` swallows a missing file and leaves `instance == null`.
   `McpConfig` must treat a null instance as "all defaults".
2. `McpConfig.resolve(String configPath)`:
   - Reads the values from `GlobalJsonConfig.instance`.
   - If `mcp_token` is empty, generates 32 random bytes with `SecureRandom`, hex-encoded (64 chars).
   - Writes the token back: parse the file as `JsonObject`, set `mcp_token`, then pretty-print it with `GsonBuilder().setPrettyPrinting()`.
     Write to `config.json.tmp`, then do an atomic `Files.move(..., ATOMIC_MOVE, REPLACE_EXISTING)`.
     Preserve the other keys. Don't round-trip through `GlobalJsonConfig`, which would drop unknown keys.
   - If the write fails (read-only file or no config file), keep the in-memory token and log a warning.
   - The config path comes from `client.java:254` (`GlobalJsonConfig.load(configPath)`). Pass that same path through.
3. Startup log (stderr, one block):
   ```
   [MCP] listening on http://127.0.0.1:43600/mcp
   [MCP] claude mcp add --transport http rt4 http://127.0.0.1:43600/mcp --header "Authorization: Bearer <token>"
   ```
4. Add the three keys to `client/config.json` with `mcp_token: ""`.
5. Git hygiene (one-time, documented in MCP-15, not code):
   `git update-index --skip-worktree client/config.json`.

## Acceptance criteria
- With no `mcp_*` keys present, defaults apply: enabled, port 43600, and a token generated and written.
- The second start reuses the persisted token.
- Other keys in `config.json` survive the write-back unchanged.
- `mcp_enabled: false` means no listener and no log lines.

## Tests (JUnit, MCP-02)
- `McpConfigTest`:
  - Defaults when the instance is null.
  - Token generated is 64 hex chars.
  - Write-back preserves unknown keys (temp dir).
  - Existing token is not regenerated.
