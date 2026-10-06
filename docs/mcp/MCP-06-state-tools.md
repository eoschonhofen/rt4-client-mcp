# MCP-06 — State tools: status, inventory, equipment, skills, chat

**Goal:** Small, focused reads the agent pulls on demand.

**Depends on:** MCP-04, MCP-05

## Files
- `rt4/mcp/tools/StatusTools.java`
- `rt4/mcp/tools/ChatTools.java`
- `rt4/mcp/Names.java`: `JagString` → plain `String`, with `<col=…>`/`<img=…>` tags stripped

## Tools

### `get_status` (works logged out too)
```json
{ "logged_in": true, "game_state": 30,
  "name": "Zezima", "combat_level": 3,
  "position": { "x": 3222, "y": 3218, "plane": 0 },
  "hp": { "current": 10, "max": 10 }, "prayer": { "current": 1, "max": 1 },
  "run": { "energy": 100, "enabled": true }, "weight": 0,
  "animation": -1, "moving": false, "interacting": "npc:1423",
  "idle": true, "nav": { "task": 3, "state": "walking" },
  "open_interfaces": [149, 548], "dialogue_open": false,
  "tick": 1834 }
```
- HP and prayer come from `PlayerSkillXpTable.boostedLevels`/`baseLevels`. Indexes: hitpoints 3, prayer 5.
- `run.enabled`: varp 173. Read it through `VarpDomain`; confirm the varp id against the server source while implementing.
- `Player.runEnergy`, `Player.weight`.
- `animation`, `moving`, `interacting`: `PathingEntity` fields (`seqId`, movement queue length, `faceEntity`).
  Map the target index to `npc:`/`player:` ids. Confirm field names in `PathingEntity.java` while implementing.
- `idle` = no animation, not moving and not interacting. This is also the `wait_for(idle)` predicate in MCP-12.
- `dialogue_open`: true if any open interface is in the chatbox-dialogue set (see MCP-14).
- `tick`: the server tick counter from MCP-12.

### `get_inventory`
- Inventory `93` (backpack) via `Inv.objectContainerCache.get(93)`, using `objectIds` and `objectStackSizes`.
- Returns `[{ slot, id, name, count, ops: [...] }]` for the 28 slots, skipping empty ones.
- `ops` comes from `ObjTypeList.get(id).inventoryOps`, with null entries removed. These are the exact strings `do_action` accepts.
- Each entry carries a `target` field: the inventory-slot target id of the backpack component (MCP-09).
  Resolve the component id from interface 149 child 0 in fixed mode; resizable uses a different parent.
  Detect the mode via `API.GetWindowMode()`, or find the visible inventory component dynamically.
  Prefer the dynamic lookup.

### `get_equipment`
- Inventory `94`. Returns `[{ slot_name, id, name, count }]` with 530 slot names: head, cape, neck, weapon, body, shield, legs, hands, feet, ring, ammo.

### `get_skills`
- 25 entries: `{ name, level, base, xp }` from `PlayerSkillXpTable`.
- Optional arg `skill` (name) returns a single entry.
- Hard-coded 530 skill-name table in `Names`.

### `get_chat`
- Args: `since` (int, default 0) and `limit` (default 30).
- Source: the `Chat` ring buffer (`messages`, `names`, `types`, `clans`, 100 entries) and `Chat.messageCounter`.
- **Verify:** whether `Chat.add` shifts the arrays (newest at 0) or writes circularly.
  Derive each message's absolute sequence number from `messageCounter` accordingly.
- Returns `{ next: <seq>, messages: [{ seq, type, type_name, name, text }] }`, oldest first, where `seq > since`.
- Type names: game, public, private_in, private_out, trade, clan, etc. Table in `Names`, filled from the `Chat.add` call sites in `Protocol`.

## Acceptance criteria
- Each tool returns in one game-thread hop (under 30 ms).
- `get_inventory` ops match what right-clicking the item shows in the UI.
- `get_chat(since=next)` polling never misses or repeats a message, even across 100+ messages.

## Tests
- `NamesTest`: tag stripping, skill table length 25.
- The chat sequencing logic is extracted into a pure `ChatCursor` class and unit-tested with a fake ring buffer, including wrap-around.
