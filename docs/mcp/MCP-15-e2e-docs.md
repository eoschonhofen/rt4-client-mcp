# MCP-15 — E2E smoke script and setup docs

**Goal:** One command proves that the whole stack works against the local server. The docs tell a human how to wire up Claude Code.

**Depends on:** all

## Files
- `client/scripts/mcp-smoke.py`: stdlib-only Python (`urllib`, `json`). Run it with `python3 -I`.
- `docs/mcp/USAGE.md`
- `../SETUP.md` (server repo, untracked): add an "MCP" section

## Smoke script
Usage: `mcp-smoke.py --url http://127.0.0.1:43600/mcp --token <t> [--user smoke --pass x]`

The steps run in order; each prints PASS/FAIL with timing, and any failure exits non-zero.

1. **Auth negatives:**
   - no token → 401
   - bad Origin → 403
   - GET → 405
2. **Handshake:**
   - `initialize` → session header
   - `notifications/initialized` → 202
   - `tools/list` includes every tool from MCP-06 through MCP-14
3. **Login:**
   - `get_status.logged_in` false
   - `login(smoke, x)`
   - `wait_for(logged_in, 30s)`
4. **State:**
   - `get_status` position is in Lumbridge, the noauth spawn
   - `get_skills` length 25
   - `get_inventory` parses
5. **Find:** `find_entities(type=npc, radius=20)` returns ≥1 result, and `list_actions` on the first one is non-empty.
6. **Walk:**
   - `walk_to` 5 tiles east
   - `wait_for(nav_done, 20s)`
   - position within 1 tile
7. **Door:** `find_entities(type=loc, name=door, has_op=Open, radius=20)`; if one is found, `do_action(…, "Open")` and `wait_for(ticks 3)`. SKIP if none.
8. **Interact + dialogue:**
   - `interact("Lumbridge Guide" or nearest talkable NPC, "Talk-to")`
   - `wait_for(dialogue_open, 15s)`
   - `continue_dialogue()` → stops at `options` or `closed`
9. **Admin setup:** use admin `::` commands via `type_text` to spawn test items. The noauth default admin makes the server accept them.
   Then `get_inventory` shows them, and `drag_item(0, 1)` swaps them.
10. **Chat:**
    - `type_text("mcp smoke", enter=true)`
    - `wait_for(chat_matches "mcp smoke")`
11. **Screenshot:** `get_screenshot` decodes as a valid PNG with nonzero size.
12. **Logout:**
    - `logout`
    - `wait_for(logged_out)`
    - `DELETE` the session

Running here (sandbox): start the server (`../start-server.sh`) and the client in the background, then run the script.
If the software-rendered client stalls (see the SETUP.md rendering note), report that and hand the run to the user.

## USAGE.md contents
- Enable and configure: the `config.json` keys and the token write-back.
- `git update-index --skip-worktree client/config.json`, and why.
- `claude mcp add --transport http rt4 http://127.0.0.1:43600/mcp --header "Authorization: Bearer <token>"`.
- Tool catalogue: one line each, generated from `tools/list` by `mcp-smoke.py --print-tools`.
- The agent loop pattern: `find_entities` → `do_action` → `wait_for` → read state.
- Limits: same-plane `walk_to`, no world hopping, no packet-79 drags.
- **Warning:** no server gate. Pointing `ip_address` at a live 2009Scape server allows botting there, which breaks their rules and gets accounts banned.

## Acceptance criteria
- All steps PASS (or documented SKIP) against a fresh local server.
- `./gradlew :client:test` is green.
