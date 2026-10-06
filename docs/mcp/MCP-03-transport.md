# MCP-03 — Streamable HTTP transport, JSON-RPC, MCP lifecycle, auth

**Goal:** An MCP host (Claude Code) can connect over Streamable HTTP, initialize,
list tools and call them. Unauthenticated or cross-origin requests are rejected.

**Depends on:** MCP-01, MCP-02

## Files
- `rt4/mcp/McpHttpServer.java`: `com.sun.net.httpserver.HttpServer` wrapper
- `rt4/mcp/JsonRpc.java`: parse, validate and build JSON-RPC 2.0 messages (pure)
- `rt4/mcp/McpProtocol.java`: method dispatch and session state
- `rt4/mcp/Tool.java`, `ToolRegistry.java`, `ToolResult.java`, `ToolException.java`
- `rt4/mcp/Auth.java`: bearer and Origin checks (pure)
- `rt4/client.java`: start the server after config load (near line 254), only if `mcp_enabled`

## Implementation

### HTTP
- `HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), port), 0)`.
  Bind to loopback only, never `0.0.0.0`.
- Executor: `Executors.newFixedThreadPool(4, daemonThreadFactory("mcp-http"))`.
  Use daemon threads so the JVM exits when the game closes.
  Four threads let a blocking `wait_for` coexist with other calls.
- Single context `/mcp`:
  - `POST`: body is one JSON-RPC message. A batch array returns `-32600`; the latest spec dropped batching.
    - Request → `200`, `Content-Type: application/json`, a single JSON-RPC response.
      Never use SSE. The spec allows a plain JSON response, and the server sends no server→client requests.
    - Notification or response → `202`, empty body.
  - `GET` → `405`. We don't offer a server-initiated SSE stream.
  - `DELETE` → terminate the session in `Mcp-Session-Id` → `200`.
  - Anything else → `405`.
- Port in use → log `[MCP] port N in use, MCP disabled` and continue. The game must still start.

### Auth (`Auth`, applied before anything else)
- `Authorization` must equal `Bearer <token>`. Compare with `MessageDigest.isEqual`, which is constant-time.
  Failure → `401` with header `WWW-Authenticate: Bearer`.
- `Origin`: if present, it must parse as a URI with host `localhost`, `127.0.0.1` or `[::1]`. Otherwise → `403`.
  An absent Origin is allowed, since non-browser clients don't send one.
- `Host` header must be `127.0.0.1:<port>` or `localhost:<port>` (DNS-rebinding defense). Otherwise → `403`.

### Sessions
- `initialize` creates a session: a UUID returned in the `Mcp-Session-Id` response header.
- Every later request must carry a known session id. Unknown id → `404`, which makes the client re-initialize.
- A request without a session id other than `initialize` → `400`.
- Keep sessions in a `ConcurrentHashMap`. No expiry is needed; this is a single-user desktop.

### MCP methods (`McpProtocol`)
- `initialize`:
  - Version negotiation: echo the requested `protocolVersion` if it's in `{"2025-11-25", "2025-06-18", "2025-03-26"}`, else reply with `"2025-06-18"`.
  - Result: `capabilities: { tools: { listChanged: false } }`, `serverInfo: { name: "rt4-client", version: "1.0.0" }`, and an `instructions` string.
    The instructions are a short primer: target ID format, the ack + `wait_for` pattern, and that coordinates are world coordinates.
- `notifications/initialized` → 202.
- `ping` → `{}`.
- `tools/list` → every registered tool's `name`, `description` and `inputSchema` (JSON Schema object). Ignore the cursor; there's no pagination.
- `tools/call`:
  - Look up the tool and validate that `arguments` is an object.
  - Call `tool.call(JsonObject args)` → `ToolResult`.
  - A `ToolException` becomes a **tool error result** (`isError: true`, text content), not a JSON-RPC error.
    The agent needs to see "target not found"-style messages.
  - An unknown tool or malformed params → JSON-RPC `-32602`.
- Unknown method → `-32601`. Malformed JSON → `-32700`.

### Tool SPI
```java
interface Tool {
    String name();
    String description();          // written for the LLM: what, when, gotchas
    JsonObject inputSchema();
    ToolResult call(JsonObject args) throws ToolException;
}
```
- `ToolResult.json(JsonElement)`: a single `text` content holding compact JSON, plus a `structuredContent` copy.
- `ToolResult.image(byte[] png)`: `image` content, `mimeType: image/png`, base64.
- `ToolResult.text(String)`.
- `ToolRegistry`: an ordered map; tools are registered in `McpServer.start()`.
  Adding a tool is one class plus one register line.

### Logging
- Log one line per call to stderr when `-Dmcp.debug=true`: method, tool, duration, error flag.
- Never write to stdout.

## Acceptance criteria
- `claude mcp add --transport http rt4 http://127.0.0.1:43600/mcp --header "Authorization: Bearer …"` connects, and `/mcp` shows the server's tools.
- curl without a token → 401. Wrong Origin → 403. `GET` → 405.
- A tool that throws shows up to the agent as `isError: true` with the message.
- The game starts normally when the port is taken.

## Tests
- `JsonRpcTest`:
  - Request, notification and response classification.
  - Error codes -32700, -32600, -32601, -32602.
  - Id echo for string and number ids.
- `AuthTest`:
  - Bearer: missing, wrong, right.
  - Origin: none, `http://localhost:3000`, `http://evil.com`, `null`.
  - Host checks.
- `McpProtocolTest`:
  - Initialize negotiation (supported and unsupported versions).
  - `tools/list` shape.
  - `tools/call` with a fake tool: success, `ToolException` and unknown tool.
- `McpHttpServerTest`: start on an ephemeral port with a fake registry, then hit it with `HttpURLConnection`.
  Cover the full handshake, session header enforcement and DELETE.
