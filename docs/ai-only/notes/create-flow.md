# AIO-06 — the native create-account flow, mapped

**Status:** source-level map, verified against the client and server source in this repository.
The interactive click-through (the `-Dcreate.debug=true` run the ticket suggests) needs a
desktop session with a working GL context and input; the sandbox this was written in has
neither, so that part is **not** confirmed by observation. Everything below is from the code,
with the file and line references given so it can be re-checked in one sitting.

## The CS2 opcodes (`ScriptRunner.java`, ~4973–5016)

| Opcode | Client call | Packet |
|---|---|---|
| `sendRequestAccount` | `CreateManager.checkName(name.encode37())` | 186 |
| `5603` | `CreateManager.checkInfo(year, country, day, month)` | 147 |
| `sendCreateAccount` | `CreateManager.createAccount(day, country, month, password, name.encode37(), year)` | 36 |
| `getAccountCreateRC` | pushes `CreateManager.reply` | — |
| `resetAccountCreateRC` | `CreateManager.reply = -2` when `step == 0` | — |
| `5610` | pushes up to five `CreateManager.suggestedNames` | — |
| `directlogin` | `LoginManager.startLogin(user, pass, type)` | 210 |

Every create opcode is guarded by
`client.gameState == 10 && LoginManager.hopStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0`,
so the CS2 can only drive the flow from the title screen.

## `CreateManager.reply` values the CS2 sees

| Value | Meaning |
|---|---|
| `-5` | both ports failed to connect |
| `-4` | connection error on the second attempt |
| `-3` | request in flight (the CS2 keeps polling) |
| `-2` | idle |
| `2` | server success |
| `21` | name taken; five suggestions follow |
| `20`, `22`, `9`, `30`–`33` | the server's own registry replies, shown by the native screens |

`loop()` sets `reply = response` (and `step = 0`) as soon as a request finishes, except for
`21`, which is handled by the `step 3`/`step 4` suggestion path.

## Answers to the ticket's questions

1. **Screens.** The name step is the CS2 that calls `sendRequestAccount`. DOB, country,
   password and terms are later CS2 states inside the same create interface; the client
   never names them, it only answers the three opcodes above.
2. **Which reply advances which screen.** A `2` from `checkName` moves the CS2 to the
   details (DOB/country) screen. A `2` from the `5603` packet moves it to the password
   screen. A `2` from the `36` packet moves it to the "done" screen. Anything else is shown
   as a failure and leaves the form usable. The client keeps polling while `reply == -3`.
3. **Returning to the title screen.** No Java-side interface call is needed. The takeover
   below answers the CS2's *first* opcode (`sendRequestAccount`) and keeps `reply = -3`
   through the internal name-check, info and create requests, then exposes the final `2` at
   the point the CS2 is waiting for the create reply. The CS2 therefore performs its normal
   one-step success transition and closes the flow itself. This is why `LoginManager.reply`
   is never touched and no top-level interface is forced.
4. **Defaults.** `day = 1`, `month = 1`, `year = 1990`, `country = 147`.
   `AccountRegister` reads all four on `147` and re-reads them on `36` without validating
   them, so any in-range value works.
5. **A 20-character `[a-z0-9]` password survives.**
   `LOGIN_USE_STRINGS = true` makes `Buffer.pjstr` write a length byte plus the bytes, and
   `CreateManager.createAccount` allocates 129 bytes for the RSA block.
   The plaintext before `rsaenc` is:
   `p1` 1 + `p2` 2 + `p2` 2 + `pjstr(username)` 13 + `p4` 4 + `pjstr(password)` 21 +
   `p4` 4 + `p2(affiliate)` 2 + `p1(day)` 1 + `p1(month)` 1 + `p4` 4 + `p2(year)` 2 +
   `p2(country)` 2 + `p4` 4 = **63 bytes**, well inside the 128-byte 1024-bit block, so the
   ciphertext always fits the 128-byte buffer and the length byte.
   `JagString.parse` accepts lowercase letters and digits unchanged.

## The takeover used by AIO-08

`TokenCreateFlow` (pure, tested) is the state machine; `CreateManager.driveTokenFlow` performs
the calls it asks for:

```
checkName(name)        -> flow.begin(name), send 186
186 replies 2          -> keep reply = -3, generate token, checkInfo(1, 147, 1, 1990)
147 replies 2          -> keep reply = -3, createAccount(1, 147, 1, token, name, 1990)
36  replies 2          -> reply = 2, save {name, token, host} to accounts.json, show TokenPanel
any other reply        -> reply = <server reply>, drop the token, stop
-4 / -5                -> reply = <error>, drop the token, stop
```

The flow only runs when `Lockdown.ENABLED`; a development build keeps the stock multi-step
flow untouched.

## Still to confirm on a desktop

- The exact CS2 state machine for the "name taken" (21) path: the takeover surfaces 21
  unchanged, the same value the stock client would.
- That the native done screen looks right when the CS2 receives its `2` three requests late.
- That the panel's placement does not cover the native done screen's own text.
