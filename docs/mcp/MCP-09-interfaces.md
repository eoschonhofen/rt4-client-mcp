# MCP-09 — Interface reading and component/inventory-slot actions

**Goal:** The agent can see open interfaces (dialogues, bank, shop, tabs, prayer book, spellbook, GE)
and click any component op or inventory slot op the UI offers.

**Depends on:** MCP-08

## Files
- `rt4/mcp/tools/InterfaceTools.java`
- `rt4/mcp/InterfaceWalker.java`
- `rt4/mcp/MenuSynth.java` (add component support)

## Tool: `get_interfaces`
Input: `{ "interface_id": 149?, "include_hidden": false, "max_depth": 6 }`
- No `interface_id` → walk every **open** interface:
  `InterfaceList.topLevelInterface` plus the sub-interfaces in `InterfaceList.openInterfaces` (a `HashTable` of `ComponentPointer`, keyed by parent component id).
- Output per interface:
  `{ id, parent: "if:548:77"?, components: [...] }`. Each component is
  `{ target: "if:149:0", type, text?, ops?, obj?: {id,name,count}, slots?: [...], button?: "continue|ok|toggle|select" }`.
- Only emit components that carry something actionable or readable:
  non-empty `text`, non-null `ops`, `buttonType != 0`, `objId != -1`, or an inventory type with items.
  Skip invisible ones (`hidden` or a hidden parent) unless `include_hidden`. This keeps a bank dump to inventory slots plus buttons.
- Inventory-type components (`type == 2`, `objTypes`/`objCounts`):
  `slots: [{ slot, target: "if:P:C:slot", id, name, count, ops }]`.
  Ops come from the component's `ops` merged with the item's inventory ops. That's exactly how the UI builds them; reuse the builder in step 2 to get them.
- Dynamic children (`createdComponentId`, CS2-created) appear under their parent, with the child index in the target.

## Component actions via `MenuSynth`
1. Add to `MenuSynth.build` for `if:` targets:
   - **Component** → `MiniMenu.addComponentEntries(mouseY, mouseX, component)` (`MiniMenu.java:218`).
     It emits entries based on `buttonType` (continue, ok, toggle) and `ops`/`optionBase`.
     Pass mouse coords inside the component bounds; check what `addComponentEntries` reads them for.
   - **Inventory slot** → the per-slot path in `addComponentEntries`, which handles the `objTypes` grid.
     It decides the slot from the mouse position, so compute the slot's cell centre:
     column = `slot % width` and row = `slot / width`, with cell size from `component.invMarginX/Y` plus 32.
     Set `InterfaceList.mouseOverInventoryObjectIndex` if the builder reads it. Verify which fields drive slot selection.
2. `list_actions` and `do_action` then work unchanged for `if:` targets.
3. Relevant action codes (`MiniMenu.java:186–196`):
   `COMPONENT_ACTION_CLOSE` 28, `OBJ_IN_COMPONENT_ACTION_1` 25, `OBJ_IN_COMPONENT_ACTION_4` 43, `OBJ_EXAMINE_IN_COMPONENT` 1006,
   the `COMPONENT_*_ACTION` targeting codes, and the unnamed `UNKNOWN_*` component/CS2 codes.
   Name any `UNKNOWN_*` codes discovered along the way in a follow-up rename commit.

## Selection flow ("Use", spells)
- `do_action("if:149:0:3", "Use")` sets `itemTargetMode = 1` and `selectedObjSlot`/`selectedObjId` (via the `doAction` branch).
- `do_action("if:192:28", "Cast")` (spellbook) sets `isTargeting`.
- `get_status` gains a `selection: { kind: "item|spell", name }` field.

## Acceptance criteria
- Open the bank: `get_interfaces(interface_id=bank)` lists the bank slots with Withdraw-1 … Withdraw-X ops and the deposit-all button.
  `do_action(slot, "Withdraw-10")` withdraws 10.
- NPC dialogue: the "Click here to continue" component has `button: "continue"`, and `do_action(…, "Continue")` advances.
- A prayer toggles on via its component op.
- Equipping an item from the inventory with its "Wield" op works.
- The output for a fully open bank stays under about 8 KB.

## Tests
- `InterfaceWalker` filtering runs on a fake component tree (plain records): hidden propagation, actionable filter, depth cap, inventory slot extraction.
- The slot → cell-centre math is a pure function with unit tests.
