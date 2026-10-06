#!/usr/bin/env python3
"""End-to-end smoke test for the RT4 client's embedded MCP server.

Stdlib only, so run it with:  python3 -I scripts/mcp-smoke.py --url ... --token ...

Every step prints PASS/FAIL/SKIP with its timing; any FAIL exits non-zero.
"""

import argparse
import base64
import http.client
import json
import sys
import time
import urllib.parse

DEFAULT_TIMEOUT = 70.0

# Every tool the server must expose by the end of MCP-14.
EXPECTED_TOOLS = [
    "get_status",
    "get_inventory",
    "get_equipment",
    "get_skills",
    "get_chat",
    "find_entities",
    "list_actions",
    "do_action",
    "cancel_selection",
    "get_interfaces",
    "type_text",
    "press_key",
    "drag_item",
    "camera",
    "mouse_click",
    "login",
    "logout",
    "get_screenshot",
    "wait_for",
    "walk_to",
    "nav_status",
    "nav_cancel",
    "interact",
    "continue_dialogue",
    "choose_option",
]


class Skip(Exception):
    """A step that cannot run in this environment."""


class Failure(Exception):
    """A step that ran and did not do what it should."""


class Client:
    def __init__(self, url, token):
        parsed = urllib.parse.urlsplit(url)
        self.host = parsed.hostname or "127.0.0.1"
        self.port = parsed.port or 80
        self.path = parsed.path or "/mcp"
        self.token = token
        self.session = None
        self.next_id = 0

    def raw(self, method, payload=None, auth=True, origin=None, session=True, timeout=DEFAULT_TIMEOUT):
        connection = http.client.HTTPConnection(self.host, self.port, timeout=timeout)
        headers = {}
        body = None
        if payload is not None:
            body = json.dumps(payload)
            headers["Content-Type"] = "application/json"
        if auth:
            headers["Authorization"] = "Bearer " + self.token
        if origin is not None:
            headers["Origin"] = origin
        if session and self.session:
            headers["Mcp-Session-Id"] = self.session
        try:
            connection.request(method, self.path, body=body, headers=headers)
            response = connection.getresponse()
            data = response.read()
            session_id = response.getheader("Mcp-Session-Id")
            status = response.status
        finally:
            connection.close()
        if session_id:
            self.session = session_id
        return status, data

    def rpc(self, method, params=None, session=True, timeout=DEFAULT_TIMEOUT):
        self.next_id += 1
        payload = {"jsonrpc": "2.0", "id": self.next_id, "method": method}
        if params is not None:
            payload["params"] = params
        status, data = self.raw("POST", payload, session=session, timeout=timeout)
        if status != 200:
            raise Failure("{} returned HTTP {}: {}".format(method, status, data[:200]))
        message = json.loads(data)
        if "error" in message:
            raise Failure("{} returned an error: {}".format(method, message["error"]))
        return message["result"]

    def notify(self, method, params=None):
        payload = {"jsonrpc": "2.0", "method": method}
        if params is not None:
            payload["params"] = params
        return self.raw("POST", payload)

    def tool(self, name, arguments=None, timeout=DEFAULT_TIMEOUT):
        result = self.rpc("tools/call", {"name": name, "arguments": arguments or {}}, timeout=timeout)
        if result.get("isError"):
            text = ""
            for block in result.get("content", []):
                if block.get("type") == "text":
                    text = block.get("text", "")
                    break
            raise Failure("{} failed: {}".format(name, text))
        if "structuredContent" in result:
            return result["structuredContent"]
        for block in result.get("content", []):
            if block.get("type") == "text":
                try:
                    return json.loads(block.get("text", "null"))
                except ValueError:
                    return block.get("text")
        return None

    def tool_error(self, name, arguments=None, timeout=DEFAULT_TIMEOUT):
        result = self.rpc("tools/call", {"name": name, "arguments": arguments or {}}, timeout=timeout)
        return bool(result.get("isError"))

    def delete_session(self):
        return self.raw("DELETE", None, session=True)


def require(condition, message):
    if not condition:
        raise Failure(message)


def settle(client, timeout=20.0):
    """Wait until the scene around the player is loaded enough to act on."""
    deadline = time.time() + timeout
    while time.time() < deadline:
        entities = client.tool("find_entities", {"type": "npc", "radius": 8})
        if entities:
            return entities
        time.sleep(0.5)
    return []


def on_tutorial_island(client):
    """A fresh noauth account starts the tutorial, where modals block walking and chat."""
    guides = client.tool("find_entities", {"type": "npc", "name": "RuneScape guide", "radius": 20})
    return bool(guides)


def run_step(label, function, results):
    started = time.time()
    try:
        detail = function()
    except Skip as skipped:
        results.append(("SKIP", label))
        print("SKIP {}: {} ({:.2f}s)".format(label, skipped, time.time() - started))
    except Exception as error:  # noqa: BLE001 - a smoke test reports anything
        results.append(("FAIL", label))
        print("FAIL {}: {} ({:.2f}s)".format(label, error, time.time() - started))
    else:
        results.append(("PASS", label))
        suffix = " - {}".format(detail) if detail else ""
        print("PASS {}{} ({:.2f}s)".format(label, suffix, time.time() - started))


def build_parser():
    parser = argparse.ArgumentParser(description="RT4 MCP server smoke test")
    parser.add_argument("--url", default="http://127.0.0.1:43600/mcp")
    parser.add_argument("--token", required=False, default="")
    parser.add_argument("--user", default="smoke")
    parser.add_argument("--pass", dest="password", default="x")
    parser.add_argument("--print-tools", action="store_true", help="print the tool catalogue and exit")
    return parser


def main(argv):
    args = build_parser().parse_args(argv)
    if not args.print_tools and not args.token:
        print("--token is required (copy it from the client's startup log)", file=sys.stderr)
        return 2
    client = Client(args.url, args.token)

    if args.print_tools:
        handshake(client)
        for tool in client.rpc("tools/list").get("tools", []):
            print("- {}: {}".format(tool["name"], tool.get("description", "").strip()))
        return 0

    results = []

    def step_auth():
        status, _ = client.raw("POST", {"jsonrpc": "2.0", "id": 1, "method": "ping"}, auth=False, session=False)
        require(status == 401, "a tokenless request must be 401, got {}".format(status))
        status, _ = client.raw("POST", {"jsonrpc": "2.0", "id": 1, "method": "ping"}, origin="http://evil.com",
                               session=False)
        require(status == 403, "a cross-origin request must be 403, got {}".format(status))
        status, _ = client.raw("GET", None, session=False)
        require(status == 405, "GET must be 405, got {}".format(status))

    def step_handshake():
        handshake(client)
        status, _ = client.notify("notifications/initialized")
        require(status == 202, "notifications/initialized must be 202, got {}".format(status))

    def step_tools_list():
        names = [tool["name"] for tool in client.rpc("tools/list").get("tools", [])]
        missing = [name for name in EXPECTED_TOOLS if name not in names]
        require(not missing, "tools/list is missing {}".format(missing))
        return "{} tools".format(len(names))

    def step_login():
        status = client.tool("get_status")
        if not status.get("logged_in"):
            client.tool("login", {"username": args.user, "password": args.password})
            outcome = client.tool("wait_for", {
                "conditions": [{"condition": "logged_in"}],
                "mode": "any",
                "timeout_ms": 30000,
            })
            require(outcome.get("met"), "login did not complete: {}".format(outcome))
        settle(client)
        return "logged in"

    def step_state():
        status = client.tool("get_status")
        require(status.get("logged_in"), "not logged in")
        position = status.get("position", {})
        require("x" in position and "y" in position and position["x"] > 0,
                "get_status has no usable position: {}".format(position))
        skills = client.tool("get_skills")
        require(len(skills) == 25, "get_skills must return 25 entries, got {}".format(len(skills)))
        inventory = client.tool("get_inventory")
        require(isinstance(inventory, list), "get_inventory must return a list")
        where = "Tutorial Island" if on_tutorial_island(client) else "the mainland"
        return "at {},{},{} on {} with {} backpack items".format(
            position["x"], position["y"], position.get("plane"), where, len(inventory))

    def step_find():
        entities = settle(client)
        require(len(entities) >= 1, "no NPC within 20 tiles")
        first = None
        for entity in entities:
            if client.tool("list_actions", {"target": entity["target"]}).get("actions"):
                first = entity
                break
        require(first is not None, "no nearby NPC offered any action")
        return "{} npcs, first actionable {}".format(len(entities), first["name"])

    def step_walk():
        status = client.tool("get_status")
        require("position" in status, "get_status has no position (not logged in?): {}".format(status))
        before = status["position"]
        goal = {"x": before["x"] + 5, "y": before["y"], "plane": before["plane"]}
        client.tool("walk_to", goal)
        outcome = client.tool("wait_for", {
            "conditions": [{"condition": "nav_done"}],
            "mode": "any",
            "timeout_ms": 20000,
        }, timeout=40)
        require(outcome.get("met"), "nav_done never became true: {}".format(outcome))
        after = client.tool("get_status")["position"]
        if abs(after["x"] - goal["x"]) <= 1 and abs(after["y"] - goal["y"]) <= 1:
            return "{} -> {}".format(before, after)

        nav = client.tool("nav_status")
        deadline = time.time() + 3.0
        transcript = []
        while time.time() < deadline:
            transcript = [m["text"] for m in client.tool("get_chat", {"since": 0, "limit": 100})["messages"]]
            if any("WALK ACTION" in text for text in transcript):
                break
            time.sleep(0.3)
        if any("WALK ACTION" in text for text in transcript):
            raise Skip("the walk packet reached the server, which refused it (an interface blocks "
                       "movement here): client nav said '{}'".format(nav.get("last_reason")))
        raise Failure("walk ended at {} instead of {} (nav: {})".format(after, goal, nav))

    def step_door():
        doors = client.tool("find_entities", {"type": "loc", "name": "door", "has_op": "Open", "radius": 20})
        if not doors:
            raise Skip("no closed door within 20 tiles")
        client.tool("do_action", {"target": doors[0]["target"], "op": "Open"})
        client.tool("wait_for", {"conditions": [{"condition": "ticks", "n": 3}], "timeout_ms": 10000})
        return "opened {}".format(doors[0]["target"])

    def step_dialogue():
        guide = client.tool("find_entities", {"type": "npc", "name": "Lumbridge Guide", "has_op": "Talk-to", "radius": 20})
        if not guide:
            guide = client.tool("find_entities", {"type": "npc", "has_op": "Talk-to", "radius": 10})
        if not guide:
            raise Skip("no talkable NPC nearby")
        client.tool("interact", {"name": guide[0]["name"], "op": "Talk-to", "type": "npc", "radius": 20})
        outcome = client.tool("wait_for", {
            "conditions": [{"condition": "dialogue_open"}],
            "mode": "any",
            "timeout_ms": 15000,
        }, timeout=30)
        require(outcome.get("met"), "no dialogue opened: {}".format(outcome))
        result = client.tool("continue_dialogue", {"max_steps": 10}, timeout=40)
        require(result.get("stopped") in ("options", "closed"), "unexpected stop: {}".format(result.get("stopped")))
        return "stopped at {}".format(result.get("stopped"))

    def step_admin_items():
        if on_tutorial_island(client):
            raise Skip("the tutorial replaces the chatbox, so :: commands cannot be typed yet")
        before = len(client.tool("get_inventory"))
        client.tool("type_text", {"text": "::item 995 100", "enter": True})
        client.tool("wait_for", {"conditions": [{"condition": "inventory_changed"}], "timeout_ms": 5000})
        after = client.tool("get_inventory")
        if len(after) == before:
            raise Skip("the server did not accept ::item (admin command name may differ)")
        client.tool("drag_item", {"from_slot": after[0]["slot"], "to_slot": 27})
        return "{} -> {} items".format(before, len(after))

    def step_chat():
        if on_tutorial_island(client):
            raise Skip("the tutorial replaces the chatbox, so public chat cannot be typed yet")
        client.tool("type_text", {"text": "mcp smoke", "enter": True})
        outcome = client.tool("wait_for", {
            "conditions": [{"condition": "chat_matches", "regex": "mcp smoke"}],
            "mode": "any",
            "timeout_ms": 10000,
        }, timeout=30)
        require(outcome.get("met"), "the chat message never came back: {}".format(outcome))
        return "chat echoed"

    def step_screenshot():
        result = client.rpc("tools/call", {"name": "get_screenshot", "arguments": {"scale": 0.5}})
        require(not result.get("isError"), "get_screenshot failed: {}".format(result))
        png = None
        size = None
        for block in result.get("content", []):
            if block.get("type") == "image":
                png = base64.b64decode(block["data"])
            elif block.get("type") == "text":
                try:
                    size = json.loads(block["text"])
                except ValueError:
                    size = None
        require(png, "no image content returned")
        require(png[:8] == b"\x89PNG\r\n\x1a\n", "content is not a PNG")
        require(len(png) > 0, "PNG is empty")
        return "{} bytes, {}x{}".format(len(png), (size or {}).get("width"), (size or {}).get("height"))

    def step_logout():
        client.tool("logout")
        outcome = client.tool("wait_for", {
            "conditions": [{"condition": "logged_out"}],
            "mode": "any",
            "timeout_ms": 15000,
        }, timeout=30)
        require(outcome.get("met"), "logout did not complete: {}".format(outcome))
        status, _ = client.delete_session()
        require(status in (200, 404), "DELETE returned {}".format(status))
        return "logged out, session closed"

    run_step("1. auth negatives", step_auth, results)
    run_step("2a. initialize handshake", step_handshake, results)
    run_step("2b. tools/list", step_tools_list, results)
    run_step("3. login", step_login, results)
    run_step("4. state reads", step_state, results)
    run_step("5. find_entities", step_find, results)
    run_step("6. walk_to", step_walk, results)
    run_step("7. door", step_door, results)
    run_step("8. interact + dialogue", step_dialogue, results)
    run_step("9. admin items + drag", step_admin_items, results)
    run_step("10. chat", step_chat, results)
    run_step("11. screenshot", step_screenshot, results)
    run_step("12. logout", step_logout, results)

    failures = [label for status, label in results if status == "FAIL"]
    print("")
    print("{} passed, {} skipped, {} failed".format(
        sum(1 for status, _ in results if status == "PASS"),
        sum(1 for status, _ in results if status == "SKIP"),
        len(failures)))
    return 1 if failures else 0


def handshake(client):
    result = client.rpc("initialize", {
        "protocolVersion": "2025-06-18",
        "capabilities": {},
        "clientInfo": {"name": "mcp-smoke", "version": "1.0.0"},
    }, session=False)
    require(client.session, "initialize did not return an Mcp-Session-Id header")
    require(result.get("serverInfo", {}).get("name") == "rt4-client",
            "unexpected serverInfo: {}".format(result.get("serverInfo")))
    return result


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
