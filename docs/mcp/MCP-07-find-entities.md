# MCP-07 — `find_entities`: NPCs, players, locs, ground items

**Goal:** The agent can discover anything in the loaded scene by name, type and distance, and gets target ids back.

**Depends on:** MCP-05

## Files
- `rt4/mcp/tools/FindEntities.java`
- `rt4/mcp/SceneScan.java`: iterators over each entity source (game thread)

## Tool: `find_entities`
Input:
```json
{ "type": "npc|player|loc|obj|any", "name": "Banker", "match": "contains|exact",
  "radius": 15, "has_op": "Bank", "limit": 20 }
```
- `name` is case-insensitive. `match` defaults to `contains`.
- `radius` is Chebyshev distance from the player, default 15, max 52.
- `has_op` filters to entities that have that op.
- Results are sorted by distance. `limit` defaults to 20, max 100.

Output: `[{ target, type, id, name, x, y, plane, distance, ops: [...], extra }]`
- NPC `extra`: `{ combat_level, hp_ratio?, animation, interacting, says? }`.
- Player `extra`: `{ combat_level, says? }`.
- `says` is the overhead chat text (forced NPC chat, player public chat) while it is still on screen; omitted when there is none. Forced NPC chat never reaches the chatbox, so this is the only way to read it.
- Obj `extra`: `{ count }`.
- Loc `extra`: `{ shape, rotation, size_x, size_y }`.

## Sources (game thread)
- **NPCs:** `for i < NpcList.size: idx = NpcList.ids[i]; npc = NpcList.npcs[idx]`. The name comes from `npc.type`.
  Apply the multinpc/varbit transform that `addNpcEntries` uses, so names match the UI. Ops come from `type.ops`.
  Skip NPCs with null names and those that can't be clicked (`type.interactive` or the equivalent check used before `addNpcEntries`).
- **Players:** `PlayerList.ids`/`PlayerList.players`, excluding self. Ops come from the server-set player options, the same array `addPlayerEntries` reads.
- **Ground items:** `SceneGraph.objStacks[plane][x][y]` is a `LinkedList` of `ObjStackNode`, one per stack.
  Ops come from `ObjType.ops` (ground ops), which differ from inventory ops.
- **Locs:** iterate the tiles within the radius on the current plane.
  For each `Tile`, collect its `wall`, `wallDecor`, `groundDecor` and `scenery[]` keys, and decode the id as `key >>> 32 & 0x7fffffff`.
  Load `LocTypeList.get(id)` and resolve multilocs with `getMultiLoc()`. Skip locs with a null name or no ops, unless `has_op` is unset and the agent asked for `type: loc` with an exact name.
  Dedupe multi-tile scenery by key; it occupies several tiles.
  - Report the loc's **origin** tile (south-west corner), decoded from the key's x/y bits (`key & 0x7f`, `key >> 7 & 0x7f`). This must match the loc packet in `doAction`.
- Distance comes from the player's scene tile. For multi-tile entities, use the distance to the nearest occupied tile.

## Token budget
- Keep fields short and drop null fields; this payload is the agent's eyes.
- Ops whose label is `"Examine"` are included. The agent may want them.

## Acceptance criteria
- Standing in the Lumbridge castle courtyard, `find_entities(type=npc, name=Hans)` returns one result, and its target works in `list_actions`.
- `find_entities(type=loc, name=door, has_op=Open)` returns the closed doors nearby with correct world coords.
- Ground item drop → `find_entities(type=obj)` shows it at the right tile.

## Tests
- Distance, filter and sort logic lives in a pure `EntityFilter` with a list of plain records. Unit-test it for name matching, radius, `has_op`, limit and sort stability.
