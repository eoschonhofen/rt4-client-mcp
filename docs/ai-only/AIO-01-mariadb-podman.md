# AIO-01 — MariaDB in a podman quadlet

**Goal:** A persistent MariaDB with the `global` schema runs as a user service on this Fedora box,
bound to loopback, so the server can use `ProductionAuthenticator` + `SQLStorageProvider`.

**Repo:** main · **Depends on:** —

## Files
- `deploy/podman/2009scape-db.container` (new, quadlet template)
- `deploy/podman/README.md` (new, install steps)
- `mysql.env` (local, from `mysql.env.example`, gitignored)
- `.gitignore` (add `mysql.env`, `deploy/podman/*.local`)
- `SETUP.md` (correct the "Docker/podman unusable" section)

## Implementation
1. **Verify podman first**, from a desktop terminal and not a sandboxed shell. Run `podman info` and `systemctl --user status`.
   The `SETUP.md` claim (read-only `/run/user/1000/libpod`) came from a sandbox; Hermes/SearXNG already
   run as quadlets on this box. If podman really fails, stop and re-plan storage.
2. Quadlet, mirroring the `db` service in `docker-compose.yml`:
   ```ini
   [Container]
   ContainerName=2009scape-db
   Image=docker.io/library/mariadb:11.4-noble
   PublishPort=127.0.0.1:3306:3306
   Volume=%h/.local/share/2009scape/db:/var/lib/mysql:Z
   Volume=<repo>/Server/db_exports/global.sql:/docker-entrypoint-initdb.d/global.sql:ro,Z
   EnvironmentFile=<repo>/mysql.env
   HealthCmd=healthcheck.sh --connect --innodb_initialized

   [Service]
   Restart=always

   [Install]
   WantedBy=default.target
   ```
   - Bind to loopback only. The game server runs natively on the same host.
   - `<repo>` is an absolute path, so the README tells the user to copy the template to
     `~/.config/containers/systemd/` and edit the paths.
3. Credentials: create a dedicated user and don't use root.
   - Set `MYSQL_USER`/`MYSQL_PASSWORD` and `MYSQL_RANDOM_ROOT_PASSWORD=yes` in `mysql.env`.
   - `global.sql` has no grants for a non-root user. The README gives a one-time
     `GRANT ALL ON global.* TO '<user>'@'%'` through `podman exec -it 2009scape-db mariadb -uroot -p`.
     Alternatively, add a second init file `deploy/podman/grants.sql` mounted next to `global.sql`.
4. Install steps in the README:
   ```
   cp deploy/podman/2009scape-db.container ~/.config/containers/systemd/
   systemctl --user daemon-reload
   systemctl --user start 2009scape-db
   podman exec 2009scape-db mariadb -u<user> -p<pass> global -e 'SHOW TABLES'
   ```
5. Back up the volume dir: note `mysqldump` in the README, since AIO-17 reuses it.

## Acceptance criteria
- `systemctl --user start 2009scape-db` brings MariaDB up. `SHOW TABLES` lists `members`.
- The port is reachable only on `127.0.0.1:3306`: `ss -ltn` shows no `0.0.0.0:3306`.
- Data survives `systemctl --user restart 2009scape-db` and a reboot.
- No credentials are committed.

## Tests
Manual. Record the verification commands and their output in the README.
