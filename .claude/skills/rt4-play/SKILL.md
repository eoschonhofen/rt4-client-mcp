---
name: rt4-play
description: Play 2009Scape through the RT4 client's embedded MCP server - log in, read game state, walk, talk to NPCs, bank, skill, fight. Use when asked to play the game, control or drive the client, "log in as X", do an in-game task (chop logs, bank, finish a dialogue, walk somewhere), check what the character is doing, or connect an agent to the client's MCP server. Covers connecting with scripts/rt4-mcp.py when no rt4 MCP tools are loaded.
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

Paths above are relative to the client repo (`client/` in the monorepo). If nothing answers, the
game client isn't running. Ask the user to start `./start-client.sh` from their desktop terminal.
Don't launch it from a sandboxed shell, because it needs a display and `/dev/dri`.

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
- **Read the chat cursor.** Keep `next` from `get_chat` and pass it back as `since`, so you only
  see new lines. Chat is where most refusals and results show up.
- **Prefer text tools to screenshots.** Use `get_screenshot` (scale 0.25–0.5) only for unknown
  interfaces, puzzles or to sanity-check. Use `mouse_click` only when no `if:` target or
  minimenu op exists.
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

**Walk**
```
walk_to(x, y, radius=1)             -> { task }
wait_for([nav_done(task)], timeout_ms=60000)
nav_status                          -> last_state ARRIVED, or FAILED with last_reason
```
`walk_to` opens doors and gates, re-plans as regions load, and accepts far destinations on the
same plane. Stairs, ladders, shortcuts and teleports fail with a reason. Handle those yourself
with `interact(name="Staircase", op="Climb-up")` and then `wait_for(position|idle)`. When a walk
fails `stuck`, try a nearby tile, open the blocking door by hand (`find_entities(type=loc,
name=door, has_op=Open)`), or walk a shorter leg.

**Talk to an NPC and get through the dialogue**
```
interact(name="Hans", op="Talk-to")
wait_for([dialogue_open], timeout_ms=10000)
continue_dialogue(max_steps=20)     -> { transcript, stopped: options|closed|no_continue|no_change|max_steps|deadline }
choose_option(text="...")           -> then continue_dialogue again
```

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

**Items**: Use the inventory slot targets from `get_inventory` with `do_action(target=
"if:149:0:<slot>", op="Wield"|"Drop"|"Eat"|"Use")`. Move items with `drag_item(from_slot, to_slot)`.
For "Enter amount" prompts, use `type_text("10", enter=true)`.

**Log out**: `logout`, then `wait_for([logged_out])`. The server refuses it for about 10 s
after combat.

## 4. Working style

- State the goal, act in small steps, and verify each one. Don't chain several actions without a
  read in between, because a refused first action makes the rest meaningless.
- After two failed attempts of the same kind, change approach: a different target, a different
  tile, `list_actions`, or a screenshot. Don't loop.
- Report progress in game terms: position, items gained, XP, and what blocked you (quote the
  chat line).
- A real mouse click by the human cancels `walk_to`. If a walk ends `CANCELLED`, assume the
  human took over and ask before continuing.

## 5. Limits and safety

- One plane per `walk_to`. There's no world hopping, and component-to-component drags aren't
  supported.
- Ticks are wall-clock estimates (`ticks_approximate: true`).
- Each game-thread hop has a 2 s budget, and `wait_for` blocks for at most 60 s. Loop
  `wait_for` for longer jobs.
- Only play on the local server or the project's own AI-only world. Driving an account on a live
  2009Scape server this way is botting. It breaks their rules and gets accounts banned. If
  `ip_address` in `config.json` isn't this machine or the project's world, stop and ask.
