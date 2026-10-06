#!/usr/bin/env python3
"""AIO-16 — end-to-end smoke test for the AI-only world.

Drives one client through the whole agent path with the client's own MCP endpoint:

    create (title screen) -> get_account -> login -> walk -> logout -> bad token fails

Stdlib only. Run it with:

    python3 -I scripts/aionly-smoke.py --url http://127.0.0.1:43600/mcp --token <token>

Registration needs a click on the title screen's "Create Account" button. That button moves
with the window mode, so its canvas coordinates are an argument:

    --create-x 490 --create-y 300

Without them the registration step is SKIPped, and the script expects `get_account` to already
have an account for this host.

Every step prints PASS/FAIL/SKIP with its timing; any FAIL exits non-zero.
"""

import argparse
import http.client
import json
import random
import string
import sys
import time
import urllib.parse

DEFAULT_TIMEOUT = 70.0
# The tile next to the default spawn (3222,3218).
WALK_X, WALK_Y = 3222, 3217


class Skip(Exception):
    """A step that cannot run in this environment."""


class Failure(Exception):
    """A step that ran and did not do what it should."""


class Mcp:
    def __init__(self, url, token):
        parsed = urllib.parse.urlsplit(url)
        self.host = parsed.hostname or "127.0.0.1"
        self.port = parsed.port or 80
        self.path = parsed.path or "/mcp"
        self.token = token
        self.session = None
        self.next_id = 0

    def _post(self, payload, expect_json=True):
        connection = http.client.HTTPConnection(self.host, self.port, timeout=DEFAULT_TIMEOUT)
        headers = {
            "Content-Type": "application/json",
            "Accept": "application/json, text/event-stream",
            "Authorization": "Bearer " + self.token,
        }
        if self.session:
            headers["Mcp-Session-Id"] = self.session
        connection.request("POST", self.path, json.dumps(payload), headers)
        response = connection.getresponse()
        body = response.read().decode("utf-8")
        session = response.getheader("Mcp-Session-Id")
        connection.close()
        if session:
            self.session = session
        if response.status >= 400:
            raise Failure("HTTP %d: %s" % (response.status, body[:200]))
        if not expect_json or not body:
            return None
        return json.loads(body)

    def initialize(self):
        result = self._post({
            "jsonrpc": "2.0", "id": self.next_id, "method": "initialize",
            "params": {"protocolVersion": "2025-06-18", "capabilities": {},
                       "clientInfo": {"name": "aionly-smoke", "version": "1.0"}},
        })
        self.next_id += 1
        return result

    def tool(self, name, **arguments):
        response = self._post({
            "jsonrpc": "2.0", "id": self.next_id, "method": "tools/call",
            "params": {"name": name, "arguments": arguments},
        })
        self.next_id += 1
        if "error" in response:
            raise Failure("%s: %s" % (name, response["error"].get("message")))
        result = response.get("result", {})
        text = ""
        for block in result.get("content", []):
            if block.get("type") == "text":
                text += block.get("text", "")
        if result.get("isError"):
            raise Failure("%s: %s" % (name, text))
        return result.get("structuredContent", {}), text

    def tools(self):
        response = self._post({"jsonrpc": "2.0", "id": self.next_id, "method": "tools/list"})
        self.next_id += 1
        return [tool["name"] for tool in response["result"]["tools"]]


def run_step(name, function):
    started = time.time()
    try:
        detail = function()
        print("PASS  %-34s %5.1fs  %s" % (name, time.time() - started, detail or ""))
        return True
    except Skip as skipped:
        print("SKIP  %-34s %5.1fs  %s" % (name, time.time() - started, skipped))
        return True
    except Failure as failed:
        print("FAIL  %-34s %5.1fs  %s" % (name, time.time() - started, failed))
        return False
    except Exception as error:  # noqa: BLE001 - the script reports anything
        print("FAIL  %-34s %5.1fs  %r" % (name, time.time() - started, error))
        return False


def random_name():
    return "ai" + "".join(random.choice(string.ascii_lowercase + string.digits) for _ in range(8))


def wait_for_state(mcp, predicate, timeout=20.0):
    deadline = time.time() + timeout
    last = None
    while time.time() < deadline:
        state, _ = mcp.tool("get_status")
        last = state
        if predicate(state):
            return state
        time.sleep(0.5)
    raise Failure("timed out waiting for state; last was %s" % json.dumps(last)[:200])


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--url", required=True)
    parser.add_argument("--token", required=True)
    parser.add_argument("--create-x", type=int, default=None)
    parser.add_argument("--create-y", type=int, default=None)
    parser.add_argument("--name", default=None, help="account name; random when omitted")
    parser.add_argument("--host", default=None, help="expected server host in accounts.json")
    args = parser.parse_args()

    mcp = Mcp(args.url, args.token)
    name = args.name or random_name()
    account = {}

    def step_tools():
        names = mcp.tools()
        missing = [tool for tool in ("get_account", "login", "wait_for", "walk_to") if tool not in names]
        if missing:
            raise Failure("missing tools: %s" % ", ".join(missing))
        return "%d tools" % len(names)

    def step_initialize():
        result = mcp.initialize()
        if "result" not in result:
            raise Failure("initialize failed: %s" % json.dumps(result)[:200])
        return result["result"]["serverInfo"]["name"]

    def step_create():
        if args.create_x is None or args.create_y is None:
            raise Skip("pass --create-x/--create-y to drive registration")
        mcp.tool("mouse_click", x=args.create_x, y=args.create_y)
        mcp.tool("type_text", text=name, enter=True)
        deadline = time.time() + 30.0
        while time.time() < deadline:
            stored, _ = mcp.tool("get_account")
            for entry in stored.get("accounts", []):
                if entry.get("name") == name:
                    account.update(entry)
                    return "created %s" % name
            time.sleep(1.0)
        raise Failure("the account never appeared in accounts.json")

    def step_account():
        stored, _ = mcp.tool("get_account")
        accounts = stored.get("accounts", [])
        if not accounts:
            raise Skip("no saved account; run with --create-x/--create-y first")
        chosen = accounts[-1]
        if args.name and chosen.get("name") != args.name:
            for entry in accounts:
                if entry.get("name") == args.name:
                    chosen = entry
                    break
        account.update(chosen)
        if args.host and stored.get("host") != args.host:
            raise Failure("host is %s, expected %s" % (stored.get("host"), args.host))
        return "%s (%d saved)" % (chosen.get("name"), len(accounts))

    def step_login():
        account_name = account.get("name")
        token = account.get("token")
        if not account_name or not token:
            raise Skip("no account to log in with")
        mcp.tool("login", username=account_name, password=token)
        wait_for_state(mcp, lambda state: state.get("logged_in") is True)
        return "logged in as %s" % account_name

    def step_walk():
        mcp.tool("walk_to", x=WALK_X, y=WALK_Y)
        state = wait_for_state(mcp, lambda s: s.get("x") == WALK_X and s.get("y") == WALK_Y, timeout=60.0)
        return "arrived at %s,%s" % (state.get("x"), state.get("y"))

    def step_logout():
        mcp.tool("logout")
        wait_for_state(mcp, lambda state: state.get("logged_in") is False)
        return "logged out"

    def step_bad_token():
        account_name = account.get("name")
        if not account_name:
            raise Skip("no account to test the token against")
        mcp.tool("login", username=account_name, password="wrongtokenxxxxxxxxxx")
        try:
            wait_for_state(mcp, lambda state: state.get("logged_in") is True, timeout=8.0)
        except Failure:
            return "the wrong token was refused"
        raise Failure("the wrong token was accepted")

    steps = [
        ("initialize", step_initialize),
        ("tools/list", step_tools),
        ("create account (title screen)", step_create),
        ("get_account", step_account),
        ("login(name, token)", step_login),
        ("walk_to %d,%d" % (WALK_X, WALK_Y), step_walk),
        ("logout", step_logout),
        ("login with a wrong token fails", step_bad_token),
    ]

    print("AI-only smoke run against %s (account name %s)\n" % (args.url, name))
    failures = 0
    for name_of_step, function in steps:
        if not run_step(name_of_step, function):
            failures += 1

    print()
    if failures:
        print("%d step(s) FAILED" % failures)
        return 1
    print("all steps passed")
    return 0


if __name__ == "__main__":
    sys.exit(main())
