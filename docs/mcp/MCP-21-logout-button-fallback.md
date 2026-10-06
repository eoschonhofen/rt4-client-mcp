# MCP-21 — 🐛 `logout` fallback can click an unrelated select button

**Type:** bug · **Severity:** medium · **Depends on:** MCP-10

**Goal:** `logout` only ever clicks a real logout control, and errors out otherwise.

## Problem
`SessionTools.findLogoutButton` tries three ranks. Rank 1 (`tools/SessionTools.java:149`) matches any
component with `buttonType == 5`. In `MiniMenu.addComponentEntries`, type 5 is a **select/radio button**
(action 51, which the deobfuscated code names `LOGOUT_ACTION`, misleadingly). It sends packet 10 and sets a
varp. When no `clientCode == 205` component is open, `logout` clicks the first select button in any open
interface, which can toggle a setting.

## Files
- `client/src/main/java/rt4/mcp/tools/SessionTools.java`
- new `client/src/test/java/rt4/mcp/LogoutButtonTest.java` (or extend `InterfaceWalkerTest`)

## Implementation
- Delete rank 1.
- Rank 2 (text contains "logout"): restrict to `buttonType == 1`, or to components whose `option` /
  op list contains "Logout".
- Check what the local server actually exposes for the 530 logout tab (interface 182) and add it as an
  explicit match (interface id + child) ahead of the heuristics.
- When nothing matches, keep the current error, and add a hint about which tab to open.
- Fix the class comment and the `LOGOUT_BUTTON_TYPE` constant name.

## Acceptance criteria
- With the settings tab open and the logout tab closed, `logout` returns an error and changes no varp.
- With the logout tab open, `logout` + `wait_for(logged_out)` succeeds.

## Tests
- A fake component tree containing a type-5 select button and no logout button → `findLogoutButton` returns
  null.
- A tree containing interface 182's logout component → it is found.
