# MCP server in the RT4 client — ticket index

Embed a Model Context Protocol server in the client so an LLM agent can play the
game autonomously, doing everything the UI can do.

## Decisions (from design interview, 2026-10-06)

| Area | Decision |
|---|---|
| Purpose | An LLM agent plays the game autonomously |
| Action model | Hybrid: synthesized minimenu actions are the core. Dedicated tools cover non-menu input, with raw mouse/key as an escape hatch |
| Targeting | Any entity loaded in the 104×104 scene, on screen or not. Menu entries are built from scene data, not from hover |
| State | Focused pull tools plus an optional screenshot tool |
| Transport | Streamable HTTP on `127.0.0.1`, using JDK `com.sun.net.httpserver` and gson. No new runtime dependencies. Java 8 source level |
| Threading | HTTP thread → command queue → executed on the game thread inside `client.mainLoop()` |
| Return model | Actions return an ack once their packet is sent. `wait_for(condition, timeout)` blocks separately |
| Helpers | `walk_to` (scene A*, opens doors, same plane, runs as a background task), dialogue helpers, name-based `interact` |
| Safety | No target-server gate (accepted risk). Loopback bind, `Origin` check, bearer token |
| Config | `config.json` keys `mcp_enabled`, `mcp_port` (marked `skip-worktree`). The bearer token is auto-generated into the gitignored `client/mcp_token` file beside it, never into the tracked config |
| Human input | Stays live. A real mouse click cancels an active `walk_to` |
| Tests | JUnit 5 for pure logic, plus an e2e smoke run against the local server |
| Git | Branch `mcp-server` in `client/`. Atomic gitmoji commits, no attribution, no push |

## Tickets

| ID | Title | Depends on |
|---|---|---|
| [MCP-01](MCP-01-config.md) | Config keys, token generation, startup log | — |
| [MCP-02](MCP-02-test-infra.md) | JUnit 5 test setup for `:client` | — |
| [MCP-03](MCP-03-transport.md) | Streamable HTTP transport, JSON-RPC, MCP lifecycle, auth | 01, 02 |
| [MCP-04](MCP-04-game-thread-queue.md) | Game-thread command queue and frame hook | 03 |
| [MCP-05](MCP-05-target-ids.md) | Target ID scheme and coordinate helpers | 04 |
| [MCP-06](MCP-06-state-tools.md) | State tools: status, inventory, equipment, skills, chat | 04, 05 |
| [MCP-07](MCP-07-find-entities.md) | `find_entities`: NPCs, players, locs, ground items | 05 |
| [MCP-08](MCP-08-menu-synth.md) | Menu synthesizer: `list_actions` / `do_action` for world targets | 05, 07 |
| [MCP-09](MCP-09-interfaces.md) | Interface reading and component/inventory-slot actions | 08 |
| [MCP-10](MCP-10-input-tools.md) | Non-menu input: text, keys, drag, camera, raw mouse, login/logout | 04 |
| [MCP-11](MCP-11-screenshot.md) | `get_screenshot` (software and GL) | 04 |
| [MCP-12](MCP-12-wait-for.md) | `wait_for` conditions and the tick/event tracker | 04, 06 |
| [MCP-13](MCP-13-walk-to.md) | `walk_to`: scene A*, doors, background nav task | 08, 12 |
| [MCP-14](MCP-14-helpers.md) | `interact`, `continue_dialogue`, `choose_option` | 08, 09 |
| [MCP-15](MCP-15-e2e-docs.md) | E2E smoke script and setup docs | all |

```
01 ─┐
02 ─┼─ 03 ── 04 ─┬─ 05 ─┬─ 07 ── 08 ─┬─ 09 ── 14
    │            │      └─ 06        │
    │            ├─ 10               └─ 13 (needs 12)
    │            ├─ 11
    │            └─ 12 (needs 06)
    └─────────────────────────────────────── 15
```

## Follow-up tickets (review, 2026-10-06)

Bugs and refactors found reviewing MCP-01…15. Order: 16 → 17 → 18 first (`walk_to` is unreliable until
all three land), then 19 (client interop), then the rest.

| ID | Type | Severity | Title | Depends on |
|---|---|---|---|---|
| [MCP-16](MCP-16-nav-tick-timing.md) | 🐛 bug | high | `walk_to` stuck/door timers count frames, not ticks | 13 |
| [MCP-17](MCP-17-astar-collision-masks.md) | 🐛 bug | high | A* uses large-NPC masks and skips the diagonal tile | 13 |
| [MCP-18](MCP-18-door-crossing.md) | 🐛 bug | high | Doors: "opened" never detected, crossing is one-way | 17 |
| [MCP-19](MCP-19-structured-content-object.md) | 🐛 bug | high | `structuredContent` must be a JSON object | 03 |
| [MCP-20](MCP-20-wait-for-fixes.md) | 🐛 bug | medium | `wait_for`: wrong `ticks_waited`, off-thread status | 12 |
| [MCP-21](MCP-21-logout-button-fallback.md) | 🐛 bug | medium | `logout` fallback clicks select buttons | 10 |
| [MCP-22](MCP-22-game-thread-hardening.md) | 🐛 bug | medium | Unguarded per-frame hooks, `cancel(true)` on game thread | 04 |
| [MCP-23](MCP-23-screenshot-scale-and-threading.md) | 🐛 bug | medium | Screenshot reports wrong scale, encodes on game thread | 11 |
| [MCP-24](MCP-24-drag-item-parity.md) | 🐛 bug | low | `drag_item` ignores insert/replace modes | 10 |
| [MCP-25](MCP-25-transport-capacity.md) | ♻️ refactor | medium | HTTP thread starvation, unbounded sessions | 03 |
| [MCP-26](MCP-26-cleanup.md) | ♻️ refactor | low | Dead code, naming, key-release order, plane checks | — |
| [MCP-27](MCP-27-e2e-coverage-docs.md) | ✅ test/docs | low | Smoke test catches these; update SETUP.md | 16–19 |

## Code layout

New package `client/src/main/java/rt4/mcp/`:

| Class | Ticket | Role |
|---|---|---|
| `McpConfig` | 01 | Reads and defaults the config keys, generates the token |
| `McpHttpServer` | 03 | HTTP endpoint, auth, sessions |
| `JsonRpc` | 03 | JSON-RPC 2.0 parse/dispatch (pure, unit-tested) |
| `McpProtocol` | 03 | `initialize`, `tools/list`, `tools/call`, `ping` |
| `Tool`, `ToolRegistry`, `ToolResult` | 03 | Tool SPI and JSON schema metadata |
| `GameThread` | 04 | Command queue plus `runOnGameThread(fn, timeout)` |
| `Targets` | 05 | Target ID parse/format, scene↔world coordinates |
| `tools/*` | 06–14 | One class per tool group |
| `MenuSynth` | 08 | Builds MiniMenu entries for a target without hover |
| `nav/AStar`, `nav/NavTask` | 13 | Pathfinding (pure) plus the per-frame nav driver |
| `Waiters` | 12 | Condition registry evaluated each frame |

Touch points in existing code are kept small and listed per ticket:
`client.java`, `GlobalJsonConfig.java`, `MiniMenu.java` (one extraction refactor), `Mouse.java`.

## Ticket template

Each ticket has: **Goal**, **Depends on**, **Files**, **Implementation**,
**Acceptance criteria**, **Tests**.
