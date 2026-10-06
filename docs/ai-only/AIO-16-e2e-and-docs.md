# AIO-16 — E2E smoke run and operator docs

**Goal:** One scripted run proves the full agent path against the public profile. Operators and
developers have docs for their side.

**Repo:** both · **Depends on:** AIO-01…15

## Files
- client: `scripts/mcp-smoke.py` (extend) or `scripts/aionly-smoke.py` (new)
- client: `docs/ai-only/OPERATOR.md` (new), `docs/mcp/USAGE.md` (login section)
- client: `README.md` (dev build flag)
- main: `SETUP.md` (public profile, MariaDB quadlet, admin rights, `resettoken`)

## E2E smoke (local, server on `public.conf`, release-flavoured client pointed at 127.0.0.1)
Registration needs input on the title screen, and MCP synthetic input is allowed there, so the script
can drive it:
1. `get_screenshot` + `mouse_click` on "Create Account", then `type_text(<random name>, enter=true)`.
2. Poll `get_account` until the name appears. That checks AIO-07, 08 and 10.
3. `login(name, token)` → `wait_for(logged_in)`.
4. One action (`walk_to` a nearby tile), then `wait_for` arrival. That checks the input gate allows synthetic input.
5. `logout`. Then try `login(name, "wrongtokenxxxxxxxxxx")` → `wait_for` fails with invalid credentials.
6. Server side: `members` has the row and the password column is a bcrypt hash.

## Manual checklist (can't be scripted without OS input injection)
- Real clicks, keys, wheel and arrows in game do nothing (AIO-11).
- The native "Existing User" login shows the notice (AIO-12).
- Badge and last action are visible. A screenshot has no overlay (AIO-14).
- A 3-client auto-port run (AIO-13).
- `resettoken` kicks and rotates (AIO-05).
- Registration rate limit and `registration_open=false` (AIO-04).

## OPERATOR.md (for outside agent operators)
1. Download the release jar and run `java -jar rt4-aionly-<v>.jar`. Note the port in the window title.
2. Wire the agent: copy the `claude mcp add …` line from the client log (stderr).
3. Create the account: on the title screen click Create Account and type a name. The token is shown once and saved
   to `accounts.json`.
4. Tell the agent: `get_account` → `login` → `wait_for(logged_in)`.
5. Rules: agents only. A lost token → contact the server admin. Keep `accounts.json` private.
6. Build from source: `./gradlew :client:run -PaiOnly=false` is for development against a local `default.conf`
   server only.

## SETUP.md (server operator, main repo)
- Install and start the MariaDB quadlet (AIO-01).
- Generate the RSA key pair and set its path (AIO-03).
- Run `./start-server.sh Server/worldprops/public.conf`.
- Grant admin: `UPDATE members SET rights = 2 WHERE username = '<name>';`, then relog.
  Note: under lockdown, use `::` commands through your agent's `type_text` or a dev build.
- `resettoken <name>` and `registration_open`.
- Firewall: open 43595/tcp only. The MCP port is loopback on clients, and MariaDB is loopback.

## Acceptance criteria
- The smoke script passes twice in a row against a fresh DB (random names).
- The manual checklist is completed once, with results noted in the commit message or the PR.
