# MCP-19 — 🐛 `structuredContent` must be a JSON object

**Type:** bug · **Severity:** high · **Depends on:** MCP-03

**Goal:** Every `tools/call` result is valid under the MCP schema, so strict clients (Claude Code, the
TypeScript SDK) accept it.

## Problem
`ToolResult.json(JsonElement)` (`ToolResult.java:36`) copies any value into `structuredContent`. The MCP
schema defines `structuredContent` as an object (`{ [key: string]: unknown }`), and the TypeScript SDK checks
it as an object. These tools return a bare array:

| Tool | Source |
|---|---|
| `get_inventory` | `tools/StatusTools.java:159` (`inventoryContents()`) |
| `get_equipment` | `tools/StatusTools.java:271` |
| `get_skills` | `tools/StatusTools.java:309` |
| `find_entities` | `tools/FindEntities.java:61` |
| `get_interfaces` | `tools/InterfaceTools.java:49` (`InterfaceWalker.describe`) |

The Python smoke test reads raw JSON, so it doesn't catch this. Confirm first by calling `get_inventory`
through `claude mcp` and checking whether the SDK rejects the result.

## Files
- `client/src/main/java/rt4/mcp/ToolResult.java`
- the five tool classes above
- `scripts/mcp-smoke.py` (it currently treats these results as lists)
- `client/src/test/java/rt4/mcp/McpProtocolTest.java`

## Implementation
- Change the signature to `ToolResult.json(JsonObject)` only. Delete the `JsonElement` overload so this
  can't come back.
- Wrap arrays with a descriptive key:
  - `get_inventory` → `{ "items": [...] }`
  - `get_equipment` → `{ "items": [...] }`
  - `get_skills` → `{ "skills": [...] }`
  - `find_entities` → `{ "entities": [...], "count": n }`
  - `get_interfaces` → `{ "interfaces": [...] }`
- Update the tool descriptions to show the new shape.
- Update the smoke script to unwrap the new keys.
- `HelperTools.interactInventory` reads `inventoryContents()` directly. Keep that helper returning
  `JsonArray`, and only wrap it at the tool boundary.

## Acceptance criteria
- Each of the five tools, called from Claude Code, returns without a schema error.
- The smoke test passes.

## Tests
- `McpProtocolTest`: for every registered tool that can run without the game (or with a stub), assert that
  `structuredContent` is absent or a JSON object.
- A unit test: `ToolResult.json` no longer compiles with a `JsonArray`. Document this as the guard; it needs
  no runtime test.
