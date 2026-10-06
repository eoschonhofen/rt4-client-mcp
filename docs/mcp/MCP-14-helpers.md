# MCP-14 — `interact`, `continue_dialogue`, `choose_option`

**Goal:** Thin convenience tools that cut agent round trips. Each is a composition of MCP-07/08/09 primitives, with no new game hooks.

**Depends on:** MCP-08, MCP-09

## Files
- `rt4/mcp/tools/HelperTools.java`
- `rt4/mcp/Dialogue.java`: dialogue interface detection (partly pure)

## `interact(name, op, type="any", radius=15)`
1. `find_entities(type, name, has_op=op, radius, limit=1)`, which gives the nearest match.
2. `do_action(target, op)`.
3. Returns `{ ok, target, name, x, y, distance }`.
   No match → `ToolException("no '<name>' with op '<op>' within <radius>; nearest names: [...]")`.
   The nearest names come from a relaxed search, to help the agent correct typos.
- Inventory fallback: if `type` is `inv`, or no world match is found and `type == any`, search the backpack slots by item name and run the op on the slot. Example: `interact("Bones", "Bury")`.

## Dialogue detection (`Dialogue`)
- 530 chatbox dialogues use a small set of interface ids, mounted under the chatbox:
  - NPC chat heads, 1–4 lines: 241–244
  - Player chat heads: 64–67
  - Option menus, 2–5 options: 228, 230, 232, 234
  - Plain message / "click to continue": 210–214 and 519
  - Item/sprite dialogues
- **Verify** the ids against the server source (`Server/src/main/content/…/dialogue`, `DialogueInterpreter`) and record the final table in `Dialogue.java`.
- `Dialogue.current()` returns one of:
  - `{ kind: npc|player|options|message|item, speaker?, lines: [...], options?: [...], continue_target? }`
  - `null` (no dialogue open)
- The `continue_target` is the component with `buttonType` continue, or the "Click here to continue" text component. Find it generically through `InterfaceWalker` rather than hard-coded child ids, which vary per interface.
- `get_status.dialogue_open` and `wait_for(dialogue_open)` use `Dialogue.current() != null`.

## `continue_dialogue(max_steps=10)`
- Loop up to `max_steps` times:
  1. Read `Dialogue.current()`.
     - null → stop with reason `closed`.
     - `kind == options` → stop with reason `options`.
  2. Record the dialogue's lines.
  3. `do_action(continue_target, "Continue")`.
  4. `wait_for([interface_changed | dialogue closed], timeout 3000)`. Don't use fixed sleeps.
- Returns the transcript plus the final state: `{ transcript: [{speaker, text}], stopped: "options|closed|max_steps", options?: [...] }`.
- Runs on the HTTP thread, composing `GameThread.call` hops plus waiters. It must never block the game thread.

## `choose_option(text | index)`
- Requires `Dialogue.current().kind == options`, else `ToolException("no option menu open")`.
- Matching: case-insensitive substring of `text`, or a 1-based `index`. Several matches → error listing them.
- Clicks the option component's op through `do_action`. Then waits for the dialogue to change (up to 3 s) and returns the new `Dialogue.current()`.

## Acceptance criteria
- `interact("Banker", "Bank")` then `wait_for(interface_open bank)` opens the bank in Lumbridge/Draynor.
- Talking to the Lumbridge Guide, `continue_dialogue()` stops at the options and returns a readable transcript.
  `choose_option("Where can I find a quest")` continues the conversation.
- `interact("Bones", "Bury")` buries bones from the backpack.

## Tests
- `DialogueTest`: classify fake component trees for each kind, continue-target detection, option extraction and matching (substring, index, ambiguity).
- The `continue_dialogue` loop runs against a fake dialogue sequence: stop conditions and max_steps.
