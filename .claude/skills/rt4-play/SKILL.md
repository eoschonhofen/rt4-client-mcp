---
name: rt4-play
description: Play 2009Scape through the RT4 client's embedded MCP server - log in, read game state, walk, talk to NPCs, bank, skill, fight. Use when asked to play the game, control or drive the client, "log in as X", do an in-game task (chop logs, bank, finish a dialogue, walk somewhere, create a character, finish or skip Tutorial Island), check what the character is doing, or connect an agent to the client's MCP server. Covers connecting with scripts/rt4-mcp.py when no rt4 MCP tools are loaded.
---

# Play 2009Scape through the client's MCP server

The RT4 client embeds an MCP server (`rt4/mcp/`, streamable HTTP on `127.0.0.1:43600`, bearer
token from `client/config.json`). Every UI action is reachable through its 25 tools.
`scripts/rt4-mcp.py` (stdlib Python, run with `python3 -I`) is the client for it. It finds the port
and the token by itself.

## 1. Connect

Pick the first option that works:

1. **The MCP tools are loaded** (`mcp__rt4__get_status` and the rest). Use them directly.
2. **They are not loaded.** Drive the game through Bash with the same tool names:
   ```bash
   python3 -I scripts/rt4-mcp.py ports                       # which clients are running
   python3 -I scripts/rt4-mcp.py tools -v                    # tool list with descriptions
   python3 -I scripts/rt4-mcp.py call get_status --pretty
   python3 -I scripts/rt4-mcp.py call find_entities type=npc name=Banker has_op=Bank
   python3 -I scripts/rt4-mcp.py call wait_for '{"conditions":[{"condition":"idle","for_ticks":3}],"timeout_ms":15000}'
   python3 -I scripts/rt4-mcp.py call get_screenshot scale=0.5   # PNG path on stderr; view it with Read
   ```
   Values in `key=value` are parsed as JSON when they can be (`limit=5` is a number, `enter=true`
   is a boolean). Pass a JSON object for anything nested. Exit code 2 means the tool returned
   `isError`, and its message is printed.
3. **To make the tools permanent**, suggest that the user register the stdio bridge once. It
   reads the token itself and survives client restarts:
   ```bash
   claude mcp add rt4 -s user -- python3 -I <abs path>/client/scripts/rt4-mcp.py bridge
   ```
   With several clients open (ports 43600–43609), pin one with `--port 43601` before `bridge`.

Paths above are relative to the client repo (`client/` in the monorepo). The client's own config
is `client/client/config.json`.

If nothing answers, the game client isn't running. The launcher is `start-client.sh` in the
**monorepo root** (one level above the client repo), not inside `client/`. It needs a display and
`/dev/dri`. When the user asks you to start it and your shell has their display (not a sandbox),
run it with Bash `run_in_background: true`. Then retry `rt4-mcp.py ports` until a port answers,
which takes a few seconds. Otherwise ask the user to run it from their desktop terminal. If the
`rt4` MCP server failed to connect at session start because the client was down, the tools stay
unloaded until the user runs `/mcp` to reconnect, so use the script meanwhile.

## 2. Ground rules

- **An action is only an ack.** `do_action`, `interact`, `type_text` and `login` return once
  the packet is queued. The server may still refuse it ("You can't reach that", "I can't do
  that", a level requirement). Always follow an action with `wait_for` plus a state read
  (`get_status`, `get_inventory`, `get_chat`).
- **Use `wait_for` instead of sleeping or polling.** Combine conditions with `mode: "any"` so you
  wake on success or failure, e.g. `[inventory_changed, chat_matches("can't|cannot|need"), idle(for_ticks=5)]`.
  A timeout returns `met=false`, not an error.
- **Targets come from discovery.** Get target ids from `find_entities`, `get_inventory` or
  `get_interfaces`, then check `list_actions(target)` when the op name is unclear. Never guess
  `npc:<index>`, because indexes change when NPCs leave and come back into view.
- **`find_entities` types are `npc`, `loc`, `obj` (ground items) and `player`.** An unknown
  `type` returns `{entities: [], count: 0}` instead of erroring, so a typo looks exactly like
  "nothing is there". `type=item` is the common wrong guess; ground items are `obj`. When unsure,
  drop `type` and filter by `name`.
- **Read the chat cursor.** Keep `next` from `get_chat` and pass it back as `since`, so you only
  see new lines. Chat is where most refusals and results show up.
- **Prefer text tools to screenshots.** Use `get_screenshot` (scale 0.25–0.5) only for unknown
  interfaces, puzzles or to sanity-check. Never screenshot to read dialogue or instructions:
  `continue_dialogue` returns the full transcript and options, and `get_interfaces` returns
  every text component (tutorial hints, prompts, progress bars).
- **Avoid `mouse_click`.** Use it only when no `if:` target or minimenu op exists, and convert
  coordinates first: `canvas = (region_x, region_y) + pixel / scale`. A pixel read off a 0.5-scale
  screenshot is half the canvas coordinate, so clicking it raw misses.
- **`wait_for` conditions and their required arguments.** Conditions are validated before the
  wait, so a wrong name fails fast and lists the valid ones:

  | condition | required | condition | required |
  |---|---|---|---|
  | `nav_done` | `task` | `chat_matches` | `regex` (not `pattern`) |
  | `interface_open` | `id` | `interface_closed` | `id` |
  | `hp_below` | `n` | `npc_gone` | `target` |
  | `item_count` | `id` | `ticks` | `n` |
  | `position` | `x` | `idle` | `for_ticks` (optional) |
  | `inventory_changed` | — | `skill_xp_changed` | — |
  | `dialogue_open` | — | `animation` | — |
  | `logged_in` / `logged_out` | — | | |

  `skill_xp_changed` is the best single condition for gathering and combat loops, because it
  fires on the xp drop whether or not an item appears.
- **`interact` finds by `name` only.** For a known target id, use `do_action(target, op)`. For
  option menus, use `choose_option(text=<substring>)` or `choose_option(index=<1-based>)`.
- **Filter the debug chat.** With debug mode on, every action appends `[IF ACTION]`,
  `[SCENERY INTERACT]`, `Iface:`, `Child:`, `Slot:`, `ID:`, `Loc:`, `RCM`,
  `Received interaction request` and `Dialogue opening` lines. The last 10 messages are then
  all noise and you will miss the refusal. Always filter:
  ```bash
  python3 -I scripts/rt4-mcp.py call get_chat since=0 limit=500 | python3 -c '
  import json,sys,re
  bad=re.compile(r"^(\[|-+|Iface|Child|Slot|ID:|Loc:|RCM|ItemID|Received|Dialogue)")
  print([t for t in (m["text"] for m in json.load(sys.stdin)["messages"]) if not bad.match(t)][-8:])'
  ```
  The noise is still useful for one thing: it echoes the component the server actually received.
- **Clear selections.** A leftover "Use item" or selected spell gets used by the next
  `do_action`. `get_status` doesn't report it, so call `cancel_selection` after any "Use" or
  spell flow that didn't finish.
- **Check before risky actions.** That means `hp` before fights, the open interfaces before
  typing, and `logged_in` before anything else.

## 3. Common flows

**Log in**
```
get_status                          -> logged_in=false, game_state=10 (title screen)
login(username, password)           -> queued
wait_for([logged_in], timeout_ms=30000)
get_status                          -> position, hp, open_interfaces
```
On a local dev server with auth off, any credentials work. On the public AI-only world the
password is the account token (`get_account`, if the client has it). Never echo a password or
token back into chat or logs.

**Walk** — read this whole block before any cross-map trip. Navigation is where most time gets
lost.
```
walk_to(x, y, radius=1)             -> { task }
wait_for([nav_done(task)], timeout_ms=60000)
nav_status                          -> last_state ARRIVED, or FAILED with last_reason
```
`walk_to` opens doors and gates on the way and re-plans as regions load. A gate between you and
the destination is handled for you: aim at a tile *past* it and check `last_doors_opened` in
`nav_status`. Stairs, ladders, shortcuts and teleports are out of scope and fail with a reason.

Four things will bite you:

1. **The destination must be inside the loaded scene.** Outside it the call errors with
   `out of loaded scene: tile:X,Y is not in the 104x104 scene around you`, which is roughly 50
   tiles in each direction. Cross-map travel is several `walk_to` legs, re-checking position
   between them, not one call.
2. **`FAILED` does not mean the character stopped.** `no path on plane 0` and `stuck at x,y` are
   both common, and the character often keeps walking for several seconds afterwards. Position in
   `get_status` lags behind too. After a failure, `nav_cancel`, then sleep a few seconds and read
   `get_status` twice before deciding where you are. Chasing a stale position sent me from
   Lumbridge to the Champions' Guild.
3. **A long leg that fails is not proof the route is blocked.** The same trip usually succeeds as
   three short legs along the road. Probe with a few candidate tiles and keep the ones that
   return `ARRIVED`.
4. **When lost, teleport instead of pathfinding home.** Lumbridge Home Teleport is free and
   resets you to a known tile. See "Teleport" below. This is almost always faster than walking
   back.

A tiny wrapper saves a lot of typing; put it in your scratchpad directory:
```bash
#!/bin/bash   # walk.sh x y [radius]
cd /path/to/client
T=$(python3 -I scripts/rt4-mcp.py call walk_to x=$1 y=$2 radius=${3:-1} | python3 -c 'import json,sys;print(json.load(sys.stdin)["task"])')
python3 -I scripts/rt4-mcp.py call wait_for "{\"conditions\":[{\"condition\":\"nav_done\",\"task\":$T}],\"timeout_ms\":60000}" >/dev/null
python3 -I scripts/rt4-mcp.py call nav_status | python3 -c 'import json,sys;d=json.load(sys.stdin);print(d.get("last_state"),d.get("last_reason",""))'
python3 -I scripts/rt4-mcp.py call get_status | python3 -c 'import json,sys;print(json.load(sys.stdin)["position"])'
```

**Teleport**
Spells live in interface 192 and their components carry `Cast` but no text, so you cannot find a
spell by name in `get_interfaces`. Get the names from `list_actions`, whose `subject` is the
spell:
```bash
for c in 0 3 9 15 18 21 23 26; do python3 -I scripts/rt4-mcp.py call list_actions target=if:192:$c \
  | python3 -c 'import json,sys;d=json.load(sys.stdin);print(d["target"],[a["subject"] for a in d["actions"]][:1])'; done
```
`if:192:0` was Lumbridge Home Teleport on this server (free, no runes). Cast it with
`do_action(target="if:192:0", op="Cast")`, then allow about 20 seconds: the animation is long and
`wait_for` has no teleport condition, so wait on `position(x=...)` or just sleep and read
`get_status`. Component numbers are per-server; list them rather than trusting `if:192:0`.

**Talk to an NPC and get through the dialogue**
```
interact(name="Hans", op="Talk-to")
wait_for([dialogue_open], timeout_ms=10000)
continue_dialogue(max_steps=20)     -> { transcript, stopped: options|closed|no_continue|no_change|max_steps|deadline }
choose_option(text="...")           -> then continue_dialogue again
```

**"I can't reach that."**
The most common refusal, and it is about position, not the target. `find_entities` happily
returns things you cannot act on, and `interact`/`do_action` only walk you part of the way.
- Ground items (`obj:`) often need you standing **on** their exact tile: `walk_to(x, y,
  radius=0)` using the entity's own coordinates, then `Take`.
- Fenced areas (wheat fields, farm pens) block reaching a loc from outside even at distance 1.
  `walk_to` a tile **inside** the enclosure first and let pathfinding open the gate.
- Otherwise walk to the target's tile with `radius=1` and retry once.
Check the filtered chat after each attempt; the refusal is the only signal, as `do_action`
still returns `ok: true`.

**Use an item on a loc (hopper, furnace, altar, …)**
```
do_action(target="if:149:0:<slot>", op="Use")     -> selects the item
list_actions(target=<loc>)                        -> "Use" with subject "Grain -> Hopper",
                                                     plus a cancel_hint confirming the selection
do_action(target=<loc>, op="Use")                 -> performs it
```
`list_actions` is the way to confirm a selection is live: it reports `cancel_hint` while one is
pending. Call `cancel_selection` if the flow does not complete.

**Climb between floors**
Ladder ops vary by object: some only have `Climb-up`, some only `Climb-down`, some have `Climb`
plus both. Always `list_actions` first. After climbing, the `plane` in `get_status` changes and
every cached target id from the old floor is dead, so re-run `find_entities` on the new floor.
`walk_to` is single-plane, so each floor is its own navigation problem.

**Gather (trees, rocks, fishing spots)**
```
interact(name="Tree", op="Chop down", type="loc")
wait_for([inventory_changed, chat_matches("inventory is too full|need a|do not have"), idle(for_ticks=5)], timeout_ms=30000)
get_inventory
```
Repeat until the backpack is full (28 slots), then bank or drop.

**Bank**
```
interact(name="Banker", op="Bank")   (or a "Bank booth" loc with op "Bank")
wait_for([interface_open(<bank id>), idle(for_ticks=3)])
get_interfaces                      -> find the bank and backpack slot targets and their ops
do_action(target="if:<iface>:<child>:<slot>", op="Deposit-All")
```
Read the interface ids from `get_status.open_interfaces` and `get_interfaces`. Don't hard-code
ids that you haven't seen in this session.

**Fight**
```
find_entities(type=npc, name="Goblin", has_op="Attack")
do_action(target, op="Attack")
wait_for([npc_gone(target), hp_below(n), idle(for_ticks=8)], timeout_ms=60000)
```
Set `hp_below` well above zero, and eat (`interact(name=<food>, op="Eat", type="inv")`) when it
fires.

**Find an interface button.** Buttons are usually two components: a text label with no ops
(e.g. `"Next"`) and a sibling with the op (e.g. `"Select next"`). `list_actions` on the label
returns nothing. List only the components that have ops, and pick the button by its op string:
```bash
python3 -I scripts/rt4-mcp.py call get_interfaces interface_id=771 | python3 -c '
import json,sys
for c in json.load(sys.stdin)["interfaces"][0]["components"]:
    if c.get("ops"): print(c["target"], c["ops"][0])'
```
Component ids change from page to page of the same interface. The same "Next" was `if:771:58`,
then `if:771:167`, then `if:771:319` in the character designer. Re-list after every page
change, and don't reuse an id from the previous page.

**Tutorial Island (new account)**
A fresh account starts at about (3094, 3107) in the guide's house. `walk_to` fails `stuck`
because the tutorial locks doors until each step is done.
1. Character designer (interface 771) opens with the mouse-options page. Tab components switch
   pages (`Select mouse/gender/head/body options`). On each page, press its own `Select next`.
   The body page's button leads to a "Do you want to accept..." page, where `Confirm changes`
   finishes the design. Progress is the `if:371:1` text ("1% Done").
2. To skip the tutorial (one chance only), talk to **Skippy** (in the starting house), then
   `choose_option("Yes, I'd like to skip")`. This sets progress to 100% and moves you next to the
   Magic instructor at about (3141, 3089). **You are still on the island.**
3. Talk to the **Magic instructor** and pick `choose_option("Yes, I'm ready")` at "Leave Tutorial
   Island?". Then `continue_dialogue` until "Welcome to Lumbridge!". The starter kit shows up in
   the backpack.
4. Verify with `get_status` (the position changed to Lumbridge) and `get_inventory` (non-empty)
   before you report done. "100% Done" alone doesn't mean you've left the island.

Without skipping, follow the hint text in interface 372 (`get_interfaces interface_id=372`)
and talk to each instructor in turn. The RuneScape guide only repeats "follow the onscreen
instructions" until the designer is confirmed.

**Quests**
Ask the **Lumbridge Guide** (`Talk-to`, then the "Where can I find a quest to go on?" option) for
a pointer. Quest dialogue is a loop of `continue_dialogue` and `choose_option`; read `stopped` to
know which comes next. Check progress with the filtered chat (`Congratulations! Quest complete!`)
and with `get_skills skill=<name>` for the reward xp. Don't trust an NPC's directions over the
wiki: WebSearch for the quest guide, because the in-game hints are vague and the F2P world
layout matches the wiki maps.

Worked example, **Cook's Assistant** (the F2P starter, ~15 min over MCP):
1. Cook in Lumbridge Castle kitchen, about (3207, 3214). Accept, then ask for the ingredients.
2. **Milk**: `Dairy cow` loc (op `Milk`) in the pen north-east of the castle, with an empty
   bucket. The gate is a `Gate` loc with `Open`.
3. **Egg**: `obj` named Egg at the chicken farm north of the castle. Stand on its tile.
4. **Flour**: pick `Wheat` (op `Pick`) from inside a field, then Mill Lane Mill, north of
   Lumbridge and west off the road. Open the mill door, climb the ladder **two** floors, use the
   grain on the `Hopper`, `Operate` the `Hopper controls`, climb back down and `Empty` the
   `Flour bin` into an empty pot.
5. Return to the Cook. He takes all three ingredients in one dialogue.

**Items**: Use the inventory slot targets from `get_inventory` with `do_action(target=
"if:149:0:<slot>", op="Wield"|"Drop"|"Eat"|"Use")`. Move items with `drag_item(from_slot, to_slot)`.
For "Enter amount" prompts, use `type_text("10", enter=true)`. After Tutorial Island the starter
kit is in the backpack but nothing is worn: wield the sword and shield before fighting and
confirm with `get_equipment`.

**Log out**: `logout`, then `wait_for([logged_out])`. The server refuses it for about 10 s
after combat.

## 4. Working style

- State the goal, act in small steps, and verify each one. Don't chain several actions without a
  read in between, because a refused first action makes the rest meaningless.
- After two failed attempts of the same kind, change approach: a different target, a different
  tile, `list_actions`, or a screenshot. Don't loop, and never spam the same action in a
  batch. If `ok: true` keeps coming back but nothing changes, you're pressing the wrong
  component. Re-list the ops (see "Find an interface button").
- Don't declare a blocker a "bug" until you've listed every component with ops on the open
  interface and talked to every nearby NPC. The way forward is usually there.
- Claim a milestone only after a state read proves it (position, inventory, chat line). Quote
  the evidence.
- Report progress in game terms: position, items gained, XP, and what blocked you (quote the
  chat line).
- Look up quest and location details with WebSearch rather than wandering. One search for a
  wiki walkthrough is cheaper than ten failed walks, and the F2P map matches the wiki.
- For a repeated loop (combat, gathering, banking), write a small Python driver in the
  scratchpad that calls the script and checks state each iteration, instead of issuing the same
  calls by hand. Keep a stop condition on hp and on "no target found".
- A real mouse click by the human cancels `walk_to`. If a walk ends `CANCELLED`, assume the
  human took over and ask before continuing.

## 5. Limits and safety

- One plane per `walk_to`, and the destination must be inside the loaded 104x104 scene. There's
  no world hopping, and component-to-component drags aren't supported.
- Ticks are wall-clock estimates (`ticks_approximate: true`).
- Each game-thread hop has a 2 s budget, and `wait_for` blocks for at most 60 s. Loop
  `wait_for` for longer jobs.
- Only play on the local server or the project's own AI-only world. Driving an account on a live
  2009Scape server this way is botting. It breaks their rules and gets accounts banned. If
  `ip_address` in `config.json` isn't this machine or the project's world, stop and ask.
