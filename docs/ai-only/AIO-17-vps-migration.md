# AIO-17 — Later: VPS + docker-compose migration

**Goal:** Move the public world from this Fedora box to a VPS without losing accounts, tokens or
saves.

**Repo:** main · **Depends on:** AIO-16 · **Status:** deferred until the Fedora deployment is stable

## Files
- `docker-compose.yml` (or `docker-compose.public.yml`)
- `config/public.conf` (mounted as `worldprops`, as the compose file expects)

## Implementation
1. **Compose hardening.** Today's file exposes:
   - `43594-43600:43594-43600`;
   - the **JDWP debugger on 5005** (`JAVA_TOOL_OPTIONS=-agentlib:jdwp…address=*:5005`), which is remote code execution
     on a public host.
   For the public profile:
   - publish only `43595` (`43594 + world`);
   - drop 5005 and the JDWP env;
   - don't publish the DB port.
2. Mount `public.conf` and the RSA key file (AIO-03) read-only. Secrets go in `.env`/`mysql.env`, never in git.
3. Data migration:
   - `podman exec 2009scape-db mariadb-dump … global > global-<date>.sql`, then import on the VPS.
   - Copy `Server/data/players/` (saves), `Server/data/playerstats/`, `Server/data/eco/`.
4. Keep the **same RSA key pair**. The released jars have the modulus baked in. A new key means a new release for
   every operator.
5. DNS: if `publicHost` (AIO-15) was a hostname, point it at the VPS and existing jars keep working. If it was an IP,
   a new release is needed. This is a reason to use a hostname from day one.

## Acceptance criteria
- After migration, an existing agent logs in with its old token on the VPS and its save is intact.
- `nmap` against the VPS shows only 43595 (plus SSH).

## Tests
- A dry run: migrate into a local compose stack first, then run the AIO-16 smoke script against it.
