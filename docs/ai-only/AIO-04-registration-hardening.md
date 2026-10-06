# AIO-04 — Registration: token format, rate limit, open switch

**Goal:** In-client registration on the public server only accepts AI tokens, can't be spammed
from one IP, and can be turned off without a restart of the client fleet.

**Repo:** main · **Depends on:** AIO-02

## Files
- `Server/src/main/core/net/registry/AccountRegister.java`
- `Server/src/main/core/net/registry/RegistrationLimiter.kt` (new, pure)
- `Server/src/main/core/auth/AgentToken.kt` (new, pure, shared with AIO-05)
- `Server/src/main/core/ServerConstants.kt`, `ServerConfigParser.kt`

## Implementation
1. `AgentToken`:
   - `const val LENGTH = 20`, alphabet `[a-z0-9]`
   - `fun isValid(s: String): Boolean`
   - `fun generate(): String` (`SecureRandom`)
2. New config keys, read in `ServerConfigParser`:
   - `server.registration_open` (default `true`)
   - `server.registration_per_ip_hour` (default `3`)
   - `server.name_checks_per_ip_minute` (default `30`)
   - `server.agent_tokens_only` (default `false`; `true` in `public.conf`)
3. `RegistrationLimiter`:
   - A sliding window per IP, with an injected clock.
   - `allowCreate(ip)` and `allowNameCheck(ip)`.
   - Entries are pruned when they expire so the map can't grow forever.
4. `AccountRegister.read`, before the existing logic:
   - `!REGISTRATION_OPEN` → `CANNOT_CREATE` for `36`, `SERVER_BUSY` for `186`/`147`.
   - `186` and the limiter refuses → `SERVER_BUSY`.
   - `36` and the limiter refuses → `CANNOT_CREATE`. Only successful creations count against the hourly limit.
5. Opcode `36`, when `AGENT_TOKENS_ONLY`:
   - Replace the 5–20 length check and `PASS_SIMILAR_TO_USER` with `AgentToken.isValid(password)`.
     Failure → `INVALID_PASS` (31).
6. **Bug fix:** `createAccountWith(info)` returns a boolean that is ignored today, so SUCCESS is sent even when
   the insert failed. Send `CANNOT_CREATE` when it returns false.
7. Log one INFO line per creation: name and IP. Never log the password.

## Acceptance criteria
- With `agent_tokens_only=true`, `36` with password `hunter22` → reply 31. A valid token → 2, and the
  `members` row exists with a bcrypt hash.
- The fourth creation from one IP inside an hour → 9. The 31st name check in a minute → 7.
- With `registration_open=false`, every create attempt fails, and existing accounts still log in.
- A duplicate-name race (insert fails) → 9, not 2.
- With `default.conf`, registration behaves as before.

## Tests (`Server/src/test/kotlin`)
- `AgentTokenTest`:
  - Generate 1000 tokens: all valid, all distinct.
  - Rejects 19 and 21 chars, uppercase, and `_`.
- `RegistrationLimiterTest`:
  - Window boundaries with a fake clock.
  - Per-IP isolation.
  - Pruning.
