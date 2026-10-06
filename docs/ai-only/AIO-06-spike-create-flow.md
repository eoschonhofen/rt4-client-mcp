# AIO-06 — Spike: map the native create-account CS2 flow

**Goal:** Learn exactly how the cache's create-account interface drives `CreateManager`, so
AIO-08 can take over after the name step without editing the cache.

**Repo:** client · **Depends on:** — · **Output:** `docs/ai-only/notes/create-flow.md`

## Known so far
- The CS2 opcodes in `ScriptRunner.java:4973–5016` are:
  - `sendRequestAccount` → `CreateManager.checkName` (`186`)
  - `5603` → `checkInfo` (`147`)
  - `sendCreateAccount` → `createAccount` (`36`)
  - `getAccountCreateRC` reads `CreateManager.reply`
  - `resetAccountCreateRC` resets it
  - `5610` reads suggested names
- `CreateManager.reply`:
  - `-3` = busy, `-2` = idle
  - `-4`/`-5` = connection errors
  - otherwise the server byte: 2 = success, 20 = name taken (with suggestions), 22 = invalid name,
    9 = cannot create, 30–33 = password errors
- The server accepts any DOB and country on `147` and doesn't validate them on `36` (`AccountRegister.java`).

## Questions to answer
1. Which interface ids and components make up each step: name, DOB, country, password, terms, done?
   Use the existing `InterfaceDebugPlugin` (dev build) or log `InterfaceList.openInterfaces` changes.
2. Which CS2 script polls `getAccountCreateRC` after the name check, and what does it do for each reply
   value? In particular:
   - Does it keep polling while `reply == -3`?
   - Which value returns it to the name screen with a message?
   - Which value, if any, closes the flow back to the title screen?
3. Can Java close the create-account interface and return to the title screen cleanly? Candidates:
   - the same path the "Back"/"Cancel" button uses;
   - setting a top-level interface;
   - calling the CS2 that the cancel button runs.
4. Valid `day/month/year/country` values that pass the client-side scripts if they're ever checked.
   Pick defaults, e.g. 1/1/1990, country from the native list.
5. Does a 20-char `[a-z0-9]` password survive:
   - `JagString.parse` → `pjstr` on create;
   - `LoginManager.startLogin` on login?
   The UI caps the field at 20, and the RSA block must stay under 128 bytes with
   `LOGIN_USE_STRINGS = true`. Do the byte math.

## Method
- Add temporary `-Dcreate.debug=true` logging in the `ScriptRunner` create opcodes and in
  `CreateManager.loop` reply changes. Click through a full native registration against `default.conf`.
- Remove the logging, or keep it behind the flag, before AIO-08.

## Done when
- `notes/create-flow.md` documents the screens, reply handling and the chosen "return to title"
  mechanism, and gives a concrete takeover plan for AIO-08.
