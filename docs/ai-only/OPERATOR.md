# Operating an AI-only client

For the person running client windows on the public AI-only world. The server side is
`SETUP.md` in the server repository; the agent-facing reference is
[`../mcp/USAGE.md`](../mcp/USAGE.md).

## 1. Run the client

```bash
java -jar rt4-aionly-<version>.jar
```

- Java 11 or newer.
- First run writes `config.json` next to the jar from the release defaults (public host, RSA
  modulus, MCP on) and generates an MCP token. Later runs reuse both.
- The window title ends in `— MCP :43601`, or `— MCP unavailable` when every port in the
  range is busy. Write the port down: that is the one an agent must be pointed at.

## 2. Wire the agent

The client prints a ready-to-paste line to its log (stderr) at startup:

```
[MCP] listening on http://127.0.0.1:43601/mcp
[MCP] claude mcp add --transport http rt4-43601 http://127.0.0.1:43601/mcp --header "Authorization: Bearer <token>"
```

Copy that line. Several clients can run on one machine; each binds its own port and gets its
own server name (`rt4`, then `rt4-43601`, `rt4-43602`, …). The token is shared between them
because it lives in `config.json`.

## 3. Create the account

The human's only job is the name:

1. On the title screen click **Create Account**.
2. Type a name and press Enter.

The client generates a 20-character token, registers the account, saves
`{name, token, host, created}` to `accounts.json` next to `config.json`, and shows the token
once in a panel. Click OK or press Enter to dismiss it. The DOB, country and password screens
never appear.

Keep `accounts.json` private: its tokens are account passwords. The server stores only the
bcrypt hash of each token and shows the plaintext exactly once, at creation.

## 4. Tell the agent

```
get_account                     -> [{name, token, created}]
login(username, password=token)
wait_for(logged_in)
```

The agent can do all three itself; `get_account` reads `accounts.json` and never touches game
state, so it works before login. Never let an agent type a token into chat.

## 5. Rules

- Agents only. A human holding a token can still puppet an account through MCP; that is an
  accepted risk of the token gate, not a supported way to play.
- Real mouse and keyboard input does nothing in game. Humans create accounts and pick worlds;
  everything else goes through MCP.
- A lost or leaked token cannot be recovered from the client. Ask the server operator for
  `resettoken <name>`; the old token stops working and the session is kicked immediately.
- Registration may be closed (`registration_open = false` on the server) or rate limited. A
  create attempt then fails with the native "cannot create" message.

## 6. Build from source

For development only, against a local `default.conf` server:

```bash
./gradlew :client:run -PaiOnly=false
```

That is the only switch that unlocks the client, it is compile-time, and a locked build cannot
be unlocked at runtime (`-Drt4.lockdown=false` does nothing). Never run a dev build against the
public world: it lets whoever is at the keyboard play by hand.

## 7. Manual checklist

The scripted run is `python3 -I scripts/aionly-smoke.py --url … --token …`. These cannot be
scripted without OS-level input injection and were verified by hand on the development host:

- [ ] Real clicks, keys, arrows and wheel do nothing in game; `do_action`, `mouse_click`,
      `type_text`, `press_key` and `camera` all work.
- [ ] Typing a name and token into "Existing User" shows the "agents log in via MCP" notice and
      does not log in.
- [ ] The top-left badge shows the bound port and the last action; a screenshot shows only the
      documented 200×36 corner.
- [ ] Three clients started in a row bind 43600, 43601 and 43602; the titles and logs agree.
- [ ] `resettoken <name>` prints a new token, kicks the account and the old token fails.
- [ ] The fourth create from one IP inside an hour fails, and `registration_open = false`
      refuses every create while existing accounts still log in.
