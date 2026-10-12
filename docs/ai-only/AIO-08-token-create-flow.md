# AIO-08 — Token-driven create flow in `CreateManager`

**Goal:** A human types only a name in the native create-account screen. The client then creates
the account with a generated token and no further screens.

**Repo:** client · **Depends on:** AIO-04, AIO-06, AIO-07

## Files
- `client/src/main/java/rt4/CreateManager.java`
- `client/src/main/java/rt4/aionly/AgentToken.java` (new, pure: `generate()`, `isValid()`, same rules as the server)
- `client/src/main/java/rt4/aionly/TokenCreateFlow.java` (new state machine, pure core)
- `client/src/main/java/rt4/aionly/Lockdown.java` (from AIO-11; the flow is active only when `Lockdown.ENABLED`)

## Implementation
Follow the takeover plan in `notes/create-flow.md` (AIO-06). The intended shape:

1. `TokenCreateFlow` has the states `IDLE → NAME_CHECK → INFO → CREATE → DONE | FAILED`.
2. When the native CS2 calls `CreateManager.checkName(name)` and lockdown is on, record the name and enter
   `NAME_CHECK`.
3. In `CreateManager.loop()`, when a request finishes (`step` returns to 0 with a server reply):
   - **NAME_CHECK, reply 2:**
     - Keep `CreateManager.reply = -3`, so the CS2 still sees busy and doesn't advance to the DOB screen.
     - Generate the token and call `checkInfo(defaults)`.
   - **INFO, reply 2:** call `createAccount(defaults…, JagString.parse(token), name)`.
   - **CREATE, reply 2:**
     - Save `{name, token, host}` to `AccountStore`.
     - Hand `{name, token}` to the token panel (AIO-09).
     - Leave the create interface the way AIO-06 found works.
   - **Any other reply at any state:**
     - Expose that reply to the CS2 unchanged (20 name taken, 22 invalid name, 9 cannot create, 7 busy),
       so the native screen shows its message.
     - Clear the token from memory.
4. Don't change the flow's own packets. It reuses `checkInfo`/`createAccount` as they are. The token travels only
   inside the RSA block (AIO-03).
5. Timeouts and connection errors (`-4`/`-5`) → `FAILED`, and the reply is exposed so the native screen shows
   "could not connect".
6. Never log the token, including in debug logging.

## Acceptance criteria
- Against `public.conf`: type a free name, then the account exists in `members`, `accounts.json` has the entry,
  and the panel shows the token. The DOB, country and password screens never appear.
- A taken name shows the native "not available" message plus suggestions, and creates nothing.
- If the server is down, the native error shows and nothing is saved.
- With lockdown off (dev build), the native multi-step flow is unchanged.

## Tests
- `TokenCreateFlowTest`, driven with fake replies:
  - The happy path issues the three requests in order and keeps the reply busy until DONE.
  - Each failure reply at each state is surfaced and stops the flow.
  - The token is cleared after DONE or FAILED.
- `AgentTokenTest`, client side: format and distinctness, mirroring the server tests.
