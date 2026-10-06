# MCP-12 — `wait_for` conditions and the tick/event tracker

**Goal:** The agent can block until something happens instead of burning turns polling.

**Depends on:** MCP-04, MCP-06

## Files
- `rt4/mcp/Waiters.java`: registry evaluated each frame from `GameThread.drain()`
- `rt4/mcp/Conditions.java`: condition parsing (pure) and predicates
- `rt4/mcp/TickTracker.java`
- `rt4/mcp/tools/WaitTool.java`

## Tick tracking
- The server sends the player update every 600 ms tick. Hook the end of player-info processing in `Protocol`
  (the `PLAYER_INFO` / `ServerProt` handler for the self update) and increment `TickTracker.tick`.
  If finding that spot is unreliable, fall back to wall-clock `ticks = elapsed / 600`. Mark it approximate in the output.

## Tool: `wait_for(conditions, mode="any", timeout_ms=10000)`
`conditions` is a list of objects. `mode` is `any` or `all`. `timeout_ms` is capped at 60000, to stay under typical host tool timeouts.

| Condition | Args | True when |
|---|---|---|
| `idle` | `for_ticks=1` | Player not animating, not moving and not interacting for N consecutive ticks |
| `ticks` | `n` | N server ticks elapsed since the call |
| `logged_in` | — | `gameState == 30` and `self != null` |
| `logged_out` | — | `gameState == 10` |
| `position` | `x,y[,plane][,radius=0]` | Player within radius of the tile |
| `dialogue_open` | — | Chatbox dialogue interface open (set defined in MCP-14) |
| `interface_open` | `id` | Interface id open |
| `interface_closed` | `id` | Interface id not open |
| `inventory_changed` | — | Inv 93 contents hash differs from the call-time snapshot |
| `item_count` | `id, op(>=,<=,==), n` | Backpack count of the item matches |
| `chat_matches` | `regex, types?` | A new chat message since the call matches the regex |
| `skill_xp_changed` | `skill?` | XP changed (any skill, or the named one) |
| `hp_below` | `n` | Current HP < n |
| `npc_gone` | `target` | Target resolves to null or a different type |
| `nav_done` | `task?` | The nav task finished (success or fail) |
| `animation` | `id?` | Player starts the animation (any non -1 if id omitted) |

## Mechanics
- The HTTP thread registers a `Waiter { conditions, mode, snapshot, CompletableFuture }`.
  The snapshot is captured on the game thread (inventory hash, chat seq, xp, tick).
- `Waiters.evaluate()` runs every frame on the game thread. It checks each waiter and completes it with the result.
- The HTTP thread waits with `future.get(timeout)`. On timeout it removes the waiter and returns `{ met: false, timed_out: true }`.
  A timeout isn't an error; it's normal flow for the agent.
- Result:
  `{ met: true, which: ["inventory_changed"], ticks_waited: 4, status: <get_status subset: position, hp, animation, idle> }`.
  Embedding the status saves the agent a follow-up call.
- Condition args are validated at registration: bad regex or unknown condition → `ToolException` immediately.
- Logout or disconnect while waiting completes all waiters with `{ met: false, reason: "logged_out" }`, unless one of them is waiting for `logged_out`.

## Acceptance criteria
- `do_action(tree, "Chop down")`, then `wait_for([inventory_changed, idle(for_ticks=3)], any)`, returns when a log is received or chopping stops.
- `wait_for([ticks n=5])` returns after about 3 s.
- Ten concurrent waiters cost under 1 ms per frame.

## Tests
- `ConditionsTest`: parse every condition, validation errors, `any`/`all` combination.
  Predicates run against a fake `GameView` interface; the real implementation reads statics.
- `idle(for_ticks)` streak logic, the `chat_matches` cursor and the inventory hash all run against the fakes.
