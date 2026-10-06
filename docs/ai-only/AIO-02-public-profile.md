# AIO-02 — `public.conf` world profile

**Goal:** A separate worldprops profile runs the public AI-only world with real auth and
persistence. `default.conf` stays the no-auth local dev profile.

**Repo:** main · **Depends on:** AIO-01

## Files
- `Server/worldprops/public.conf.example` (new, committed)
- `Server/worldprops/public.conf` (local copy with DB creds, gitignored)
- `.gitignore`
- `start-server.sh` (document the profile argument; it already accepts `<conf-file>`)
- `Server/src/main/core/ServerConstants.kt`, `core/game/system/config/ServerConfigParser.kt` (new keys from AIO-04)

## Implementation
1. Copy `default.conf` to `public.conf.example`, then change:
   ```
   use_auth = true
   persist_accounts = true
   noauth_default_admin = false
   msip = "<public host or 0.0.0.0 as appropriate>"
   [database] database_username / database_password / database_address = "127.0.0.1"
   ```
   Add the registration keys introduced in AIO-04 (`registration_open`, `registration_per_ip_hour`,
   `name_checks_per_ip_minute`) and the RSA key path from AIO-03.
2. Leave `secret_key` unchanged unless the client is changed to match. It's sent by every client and
   isn't a real secret. Add a comment that says so.
3. `Server.kt:64` already takes the config path as `args[0]`, and `start-server.sh` passes it through. Run with
   `./start-server.sh Server/worldprops/public.conf`.
4. Startup sanity log: when `USE_AUTH` is true, log the authenticator, storage provider and DB host,
   but not the password, so a misconfigured profile is obvious.

## Acceptance criteria
- Starting with `public.conf` connects to MariaDB. A login with an unknown name fails with
  invalid credentials, and nobody is admin by default.
- Starting with no argument still uses `default.conf`, and any-name/any-password login still works.
- `public.conf` (with creds) is not tracked by git.

## Tests
- `ServerConfigParser` test (`Server/src/test/kotlin`): parsing `public.conf.example` sets
  `USE_AUTH`, `PERSIST_ACCOUNTS`, `NOAUTH_DEFAULT_ADMIN` and the new registration keys.
