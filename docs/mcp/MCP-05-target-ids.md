# MCP-05 — Target ID scheme and coordinate helpers

**Goal:** One stable, human-readable string format the agent uses to name anything it can act on.
All coordinates the agent sees are **world** coordinates.

**Depends on:** MCP-04

## Files
- `rt4/mcp/Targets.java`: parse and format (pure), plus resolvers (game thread)
- `rt4/mcp/Coords.java`: scene↔world conversion

## Target ID formats

| Kind | Format | Example | Resolves to |
|---|---|---|---|
| NPC | `npc:<index>` | `npc:1423` | `NpcList.npcs[index]` (null → stale) |
| Player | `player:<index>` | `player:7` | `PlayerList.players[index]` |
| Loc (object) | `loc:<locId>@<x>,<y>,<plane>` | `loc:1530@3222,3218,0` | Scenery/wall/decor at that tile with that id |
| Ground item | `obj:<objId>@<x>,<y>,<plane>` | `obj:995@3221,3219,0` | Entry in `SceneGraph.objStacks[plane][sx][sy]` |
| Tile | `tile:<x>,<y>[,<plane>]` | `tile:3222,3218` | Walk target (plane defaults to the current one) |
| Component | `if:<interfaceId>:<childId>` | `if:149:0` | `InterfaceList.getComponent(parent, child)` |
| Inventory slot | `if:<interfaceId>:<childId>:<slot>` | `if:149:0:3` | Slot in an inventory-type component |

- NPC and player indexes are what the server uses, and they're stable while the entity stays in view.
  If one goes stale (null, or a different type id than last reported), the tool throws `target gone: npc:1423`.
- Loc ids are the **base** `LocType` id. The agent sees the resolved multiloc name, but the id is what the packet needs.
  Packets use `key >>> 32`; see `MiniMenu.doAction` LOC branches.

## Coordinates
- Scene→world: `worldX = Camera.originX + sceneX` and `worldY = Camera.originY + sceneY`.
  This matches how `MiniMenu.doAction` builds loc packets (`Camera.originX + menuArg1`).
- Player scene tile: `PlayerList.self.movementQueueX[0]` / `movementQueueY[0]`. Plane: `Player.plane`.
- `Coords.toScene(worldX, worldY)` returns null if outside `0..103`. Tools then throw `out of loaded scene`.
- **Verify during implementation:** check `Camera.originX`/`originY` against `SceneGraph.centralZoneX*8 - 48`.
  Use whichever is authoritative (they should agree), and add a debug-only assertion.

## Implementation
- `Targets.parse(String) → Target` (sealed-style class hierarchy: `NpcTarget`, `LocTarget`, …). Pure, unit-tested.
- `Targets.format(...)` overloads, used by every state tool so ids round-trip exactly.
- `Targets.resolve(Target)` runs on the game thread and returns the live object or throws `ToolException`.
- Loc resolution: scan `SceneGraph.tiles[plane][sx][sy]` and match the base id derived from each candidate's key.
  Candidates are the wall, wall decor, ground decor and scenery entries.
  Reuse `SceneGraph.isLocValid(plane, x, y, key)` where possible. Keep the 64-bit `key` because `MenuSynth` needs it.

## Acceptance criteria
- Every id emitted by MCP-06/07/09 parses back and resolves to the same entity in the same frame.

## Tests
- `TargetsTest`:
  - Round-trip of every kind.
  - Malformed inputs (`npc:`, `loc:1@1,2`, negative slot) → clear error messages.
  - Default plane on `tile:`.
