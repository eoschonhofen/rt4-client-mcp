# AIO-09 — Token display panel on the title screen

**Goal:** After a successful creation, the human sees the name and token clearly, with a note that
the agent can also fetch it through `get_account`.

**Repo:** client · **Depends on:** AIO-08

## Files
- `client/src/main/java/rt4/aionly/TokenPanel.java` (new)
- The title-screen draw path in `client.java`. Find where the title/login interfaces are drawn at
  `gameState == 10`, and draw after them.

## Implementation
1. `TokenPanel.show(name, token)` stores the values. `TokenPanel.dismiss()` clears them.
2. Draw it Java-side over the title screen. It works in both renderers, using the same font and raster helpers the
   plugin API uses (`plugin.api.API.DrawText`, `DrawRect`):
   ```
   Account created: bob
   Token: k3j9q0z8m1x7c4v2b6n5
   Saved to accounts.json — your agent can read it with get_account.
   This token is shown once by the server. [ OK ]
   ```
   The token is rendered in a monospace-looking layout, grouped `k3j9q 0z8m1 x7c4v 2b6n5` for reading.
   Spaces are display only.
3. Dismiss with a click on OK or Enter. These are real inputs, allowed by the title-screen hole (AIO-12).
4. While the panel is open, title-screen clicks go to the panel only, so the human doesn't click through it
   into the login form.
5. The panel never shows again for that account. The token is no longer kept in memory after dismiss.
   It stays only in `accounts.json`.

## Acceptance criteria
- After creation the panel appears in SD and HD, readable at 765×503 and resized.
- OK or Enter dismisses it and the title screen works again.
- If the client restarts while the panel is open, nothing re-shows. The token is still in `accounts.json`.

## Tests
- A pure `TokenFormat.group("k3j9q0z8m1x7c4v2b6n5")` → `"k3j9q 0z8m1 x7c4v 2b6n5"`.
- Manual SD/HD check, with screenshots attached to the PR or commit notes.
