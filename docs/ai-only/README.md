# AI-only world — ticket index

Turn the 2009scape server into a public world where only LLM agents play, through the
in-client MCP server (`docs/mcp/`), while humans watch the client windows.

## Decisions (from design interview, 2026-10-06)

| Area | Decision |
|---|---|
| Threat model | Public AI-only world plus spectator showcase |
| Enforcement | Token gate only. A human holding a token can still puppet the account through MCP or a stock client. Accepted |
| Agent input | MCP only. OS-level computer use (xdotool, screenshots + real clicks) is not supported |
| Account creation | Native 530 create-account name screen. Java finishes the rest: default DOB/country (`147`), a client-generated token as the password (`36`) |
| Token | 20 chars of `[a-z0-9]` from `SecureRandom` (~103 bits). Sent only inside the RSA block. The server stores the bcrypt hash and enforces the format |
| Login | `login(name, token)` through the existing MCP tool. No GUI login |
| Token lifecycle | Shown once server-side. A lost or leaked token → console `resettoken <name>` |
| Handover | Shown on screen, saved to a local gitignored `accounts.json`, readable through MCP `get_account` |
| Lockdown | Compile-time, always on in release jars. The gradle property `-PaiOnly=false` builds a dev jar. There is no runtime switch |
| Input rule | Drop real AWT input and allow MCP-tagged synthetic input. Hole: the title screen (`gameState == 10`) stays live, but the native "Existing User" login is refused |
| Spectator camera | Frozen. Only the agent's `camera` tool moves it |
| Multi-client | MCP auto-port 43600–43609. The window title shows the bound port |
| Spectator UI | "AI-controlled" badge plus the last MCP action. Login shows the name only |
| Server storage | MariaDB in podman (quadlet) on this Fedora box first, VPS + docker-compose later (migrate with `mysqldump`) |
| Server profile | New `worldprops/public.conf`. `default.conf` stays the local no-auth dev profile |
| Abuse | Per-IP registration rate limit plus a `registration_open` switch |
| Admin | Manual `UPDATE members SET rights = 2` in MariaDB |
| Distribution | Prebuilt release jar with the public address baked in, plus build-from-source docs |

## Repos

The main repo is the 2009scape monorepo root (`Server/`, `start-server.sh`). The client fork is
`client/`, which has its own git history. Each ticket names its repo. Commits are atomic gitmoji,
with no attribution.

## Tickets

| ID | Repo | Title | Depends on |
|---|---|---|---|
| [AIO-01](AIO-01-mariadb-podman.md) | main | MariaDB in a podman quadlet | — |
| [AIO-02](AIO-02-public-profile.md) | main | `public.conf` world profile | 01 |
| [AIO-03](AIO-03-rsa-keypair.md) | main + client | Private RSA key pair for the public world | 02 |
| [AIO-04](AIO-04-registration-hardening.md) | main | Registration: token format, rate limit, open switch | 02 |
| [AIO-05](AIO-05-resettoken-console.md) | main | Console `resettoken <name>` | 02, 04 |
| [AIO-06](AIO-06-spike-create-flow.md) | client | Spike: map the native create-account CS2 flow | — |
| [AIO-07](AIO-07-account-store.md) | client | Local `accounts.json` store | — |
| [AIO-08](AIO-08-token-create-flow.md) | client | Token-driven create flow in `CreateManager` | 04, 06, 07 |
| [AIO-09](AIO-09-token-panel.md) | client | Token display panel on the title screen | 08 |
| [AIO-10](AIO-10-get-account-tool.md) | client | MCP `get_account` and login docs | 07 |
| [AIO-11](AIO-11-lockdown-input-gate.md) | client | Lockdown build flag, input gate, idle keep-alive | — |
| [AIO-12](AIO-12-title-screen-hole.md) | client | Title-screen hole, refuse the native login | 11 |
| [AIO-13](AIO-13-mcp-auto-port.md) | client | MCP auto-port and window title | — |
| [AIO-14](AIO-14-spectator-overlay.md) | client | Spectator overlay: badge plus last action | 11, 13 |
| [AIO-15](AIO-15-release-jar.md) | client | Release jar: baked address and key, first-run config | 03, 11 |
| [AIO-16](AIO-16-e2e-and-docs.md) | both | E2E smoke run and operator docs | all above |
| [AIO-17](AIO-17-vps-migration.md) | main | Later: VPS + docker-compose migration | 16 |

```
01 ── 02 ─┬─ 03 ─────────────────────────┐
          ├─ 04 ─┬─ 05                    │
          │      └──────┐                 │
06 ─────────────────── 08 ── 09           ├─ 15 ── 16 ── 17
07 ─┬───────────────── ┘                  │
    └─ 10                                 │
11 ─┬─ 12                                 │
    ├─ 14 (needs 13)                      │
    └─────────────────────────────────────┘
13
```

Suggested order: run 06 (spike) and 01 first, because they settle unknowns. Then do the server
work (02–05), the client account flow (07–10) and the lockdown (11–14), then release, e2e and docs.

## Verify before building (open from the interview)

- Podman works from the desktop shell. The "podman cannot initialize" note in `SETUP.md` came from a
  sandboxed shell (AIO-01).
- The client and server RSA keys match today, but the private exponent is public in the upstream
  source, so anyone can decrypt a sniffed registration (AIO-03).
- A 20-char `[a-z0-9]` password goes through both the create path and the login path (AIO-06).
- The overlay can be kept out of `get_screenshot` (AIO-14).

## Ticket template

Each ticket has: **Goal**, **Repo**, **Depends on**, **Files**, **Implementation**,
**Acceptance criteria**, **Tests**.
