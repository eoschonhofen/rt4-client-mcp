# Playing 2009Scape with an MCP agent

The client embeds a [Model Context Protocol](https://modelcontextprotocol.io) server, so an
LLM agent can see the game state and act on it: walk around, talk to NPCs, bank, chop trees,
fight, and read the result. This document is for the human setting it up.

## 1. Enable it

The server is on by default. These keys in `client/config.json` control it:

| Key | Default | Meaning |
|---|---|---|
| `mcp_enabled` | `true` | Start the listener (loopback only). |
| `mcp_port` | `43600` | TCP port on `127.0.0.1`. |
| `mcp_token` | `""` | Bearer token. Left empty, one is generated and written back. |

On startup the client prints a ready-to-paste command:

```
[MCP] listening on http://127.0.0.1:43600/mcp
[MCP] claude mcp add --transport http rt4 http://127.0.0.1:43600/mcp --header "Authorization: Bearer <token>"
```

If the port is taken the client logs `[MCP] port 43600 in use, MCP disabled` and the game
starts normally.

## 2. Keep the token out of git

`client/config.json` also holds your local IPs and now the token, so it is marked
`skip-worktree` and git ignores changes to it:

```bash
git update-index --skip-worktree client/config.json
```

The client writes the generated token back into that file, preserving every other key.

## 3. Connect a host

Claude Code, from anywhere:

```bash
claude mcp add --transport http rt4 http://127.0.0.1:43600/mcp \
  --header "Authorization: Bearer <token>"
```

The server speaks plain JSON (no SSE) on `POST /mcp`. Requests must carry the bearer token;
`Origin`, if present, must be loopback; the `Host` header must name `127.0.0.1:<port>` or
`localhost:<port>`. `GET` is a `405`; `DELETE` with the session header ends a session.

## 4. Tool catalogue

Generated with `python3 -I scripts/mcp-smoke.py --print-tools` against a running client:

| Tool | What it does |
|---|---|
| `get_status` | Login state, world position, hp/prayer, run, animation, idle, open interfaces, dialogue, nav state. Works logged out. |
| `get_inventory` | Backpack contents with ops and slot targets. |
| `get_equipment` | Worn equipment by 530 slot name. |
| `get_skills` | All 25 skills, or one. |
| `get_chat` | Chat messages as a monotonic sequence. Poll with `since=<previous next>`. |
| `find_entities` | NPCs, players, locs and ground items by name, op and distance. |
| `list_actions` | The minimenu a right-click on a target would show. |
| `do_action` | Run one of those actions; the packet is byte-identical to a click. |
| `cancel_selection` | Clear a pending "Use item" or spell. |
| `get_interfaces` | Open interfaces, their components, buttons and inventory slots. |
| `type_text` · `press_key` | Chat, "Enter amount", key presses, held arrow keys. |
| `drag_item` | Move an item between two slots (packet 231). |
| `camera` | Read and set yaw, pitch and zoom. |
| `mouse_click` | Raw mouse event at a canvas pixel (escape hatch; pair with `get_screenshot`). |
| `login` · `logout` | Title-screen login and the real logout button. |
| `get_screenshot` | PNG of the last frame, software or HD mode. |
| `wait_for` | Block on up to 16 conditions instead of polling. |
| `walk_to` · `nav_status` · `nav_cancel` | Background A* walking that opens doors. After a walk, `nav_status` keeps `last_state`, `last_reason` and `last_doors_opened`. |
| `interact` | `find_entities` + `do_action` in one call, with a backpack fallback. |
| `continue_dialogue` | Click through a dialogue and collect the transcript. |
| `choose_option` | Pick a dialogue option by text or index. |

## 5. The agent loop

```
find_entities(type="npc", name="Banker", has_op="Bank")   -> target
do_action(target="npc:7", op="Bank")                       -> queued
wait_for([interface_open(548), idle])                      -> what happened
get_interfaces(interface_id=548)                           -> slot targets
do_action(target="if:548:6:0", op="Withdraw-10")
```

`do_action` means "the packet was queued", not "the server agreed". Confirm with `wait_for`
and a state read. Every action cancels an active `walk_to`, and a real mouse click cancels it
too, so you can take over at any time.

## 6. Limits

* `walk_to` works on one plane. Stairs, ladders, agility shortcuts, teleports and ferries
  fail with a reason instead of guessing.
* No world hopping; the `world` argument to `login` is reserved.
* Component-to-component drags (packet 79) are not implemented.
* Ticks in `get_status.tick` and `wait_for(ticks)` come from wall-clock time (600 ms per
  tick), so they are close but not exact. Responses say `ticks_approximate: true`.
* Tools have a 2 second budget per game-thread hop; `wait_for` may block up to 60 s.

## 7. Smoke test

With the server and client running:

```bash
python3 -I scripts/mcp-smoke.py --url http://127.0.0.1:43600/mcp --token <token>
```

It checks auth, the handshake, the tool list, login, state reads, `find_entities`, a 15-tile
`walk_to`, a `walk_to` through a closed door and back, a raw door open, dialogue, admin item
spawning, chat, a screenshot and logout, then checks every tool result it saw against the MCP
result schema. It prints PASS/FAIL/SKIP with timings, and any failure exits non-zero.

A walk must end `ARRIVED`. A walk that fails `stuck`, or a door walk that never opens the door,
is a FAIL. The walk step skips only when the chat shows the server refused the walk packet,
for example on Tutorial Island while a modal blocks movement.

## 8. Warning

There is no target-server gate. Pointing `ip_address` at a live 2009Scape server and letting
an agent play there is botting: it breaks their rules and gets accounts banned. This is built
for a local server you run yourself, or a server that allows automated play. The client logs a
warning at startup when `ip_address` is not this machine.
