# AIO-12 — Title-screen hole, refuse the native login

**Goal:** Humans can use the title screen to create accounts and pick a world, but can't log in
by hand. Agents log in through MCP.

**Repo:** client · **Depends on:** AIO-11

## Files
- `client/src/main/java/rt4/aionly/InputGate.java` (hole rule)
- `client/src/main/java/rt4/ScriptRunner.java` (the `directlogin` opcode, about line 4951)
- `client/src/main/java/rt4/aionly/TitleMessage.java` (new, small Java-drawn notice)

## Implementation
1. Hole rule in `InputGate`: real input is allowed when `client.gameState == 10` (title screen).
   Loading states (< 10) need no input. Every in-game state (≥ 25) is locked.
2. Refuse the native login. The `directlogin` CS2 opcode is the only path from the title-screen login form
   (button or Enter) to `LoginManager.startLogin`. MCP's `login` tool calls `startLogin` directly
   (`SessionTools.java:62`), so gating the opcode leaves MCP untouched.
   - Under lockdown, `directlogin` does nothing.
   - It sets `LoginManager.reply` to a value the CS2 treats as a plain failure. Pick one with AIO-06 tooling
     that shows a neutral message and doesn't lock the form.
   - It calls `TitleMessage.show("Agents log in via MCP — see get_account")` for about 5 s.
3. `TitleMessage` draws one line under the login box. It can share drawing code with `TokenPanel` (AIO-09).
4. When the MCP login lands (`gameState` → 30), the hole closes on the next event. There's no race: the gate reads
   `gameState` per event.

## Acceptance criteria
- Release build at the title screen:
  - The human can open "Create Account", type a name, and dismiss the token panel.
  - The human can open the world list.
- Typing a valid name and token into "Existing User" and pressing Login shows the notice and doesn't log in.
- MCP `login` from the same title screen works.
- After login, all real input is dropped (AIO-11).

## Tests
- `InputGateTest` gets the hole cases: gameState 10 allowed, 25/30/40 refused, lockdown off always allowed.
