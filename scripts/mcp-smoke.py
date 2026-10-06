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

# MCP-27 — long enough that a walk spans several legs and many server ticks.
WALK_DISTANCE = 15
WALK_TIMEOUT_MS = 45000

# The edge a straight wall (loc shape 0) stands on, by rotation, as the step from its tile.
WALL_EDGE_STEP = {0: (-1, 0), 1: (0, 1), 2: (1, 0), 3: (0, -1)}

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
        # Every tools/call result, so step_schema can check them all at the end (MCP-27).
        self.calls = []

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
        if method == "tools/call":
            self.calls.append(((params or {}).get("name"), message["result"]))
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


def schema_problems(result):
    """What makes a tools/call result invalid under the MCP schema (MCP-19), as strings."""
    problems = []
    if not isinstance(result, dict):
        return ["the result is a {}, not an object".format(type(result).__name__)]
    if "structuredContent" in result and not isinstance(result["structuredContent"], dict):
        problems.append("structuredContent is a {}, not an object".format(
            type(result["structuredContent"]).__name__))
    content = result.get("content")
    if not isinstance(content, list):
        problems.append("content is a {}, not a list".format(type(content).__name__))
        return problems
    for index, block in enumerate(content):
        if not isinstance(block, dict) or "type" not in block:
            problems.append("content[{}] has no type".format(index))
        elif block["type"] == "text" and not isinstance(block.get("text"), str):
            problems.append("content[{}] is a text block without text".format(index))
        elif block["type"] == "image" and not (block.get("data") and block.get("mimeType")):
            problems.append("content[{}] is an image block without data and mimeType".format(index))
    if "isError" in result and not isinstance(result["isError"], bool):
        problems.append("isError is not a boolean")
    return problems


def chebyshev(a, b):
    return max(abs(a["x"] - b["x"]), abs(a["y"] - b["y"]))


def walk(client, goal, timeout_ms=WALK_TIMEOUT_MS):
    """walk_to, then wait for that task to finish. Returns the idle nav_status of the walk."""
    started = client.tool("walk_to", goal)
    task = started.get("task")
    outcome = client.tool("wait_for", {
        "conditions": [{"condition": "nav_done", "task": task}],
        "mode": "any",
        "timeout_ms": timeout_ms,
    }, timeout=timeout_ms / 1000.0 + 20)
    nav = client.tool("nav_status")
    if not outcome.get("met"):
        client.tool("nav_cancel")
        raise Failure("walk task {} did not finish in {} ms (nav: {})".format(task, timeout_ms, nav))
    require(nav.get("last_task") == task, "nav_status reports task {}, not {}".format(nav.get("last_task"), task))
    return nav


def walk_refused_by_server(client):
    """True when the chat shows the server refused a walk packet (an interface blocks movement)."""
    deadline = time.time() + 3.0
    while time.time() < deadline:
        transcript = [m["text"] for m in client.tool("get_chat", {"since": 0, "limit": 100})["messages"]]
        if any("WALK ACTION" in text for text in transcript):
            return True
        time.sleep(0.3)
    return False


def settle(client, timeout=20.0):
    """Wait until the scene around the player is loaded enough to act on."""
    deadline = time.time() + timeout
    while time.time() < deadline:
        entities = client.tool("find_entities", {"type": "npc", "radius": 8}).get("entities", [])
        if entities:
            return entities
        time.sleep(0.5)
    return []


def on_tutorial_island(client):
    """A fresh noauth account starts the tutorial, where modals block walking and chat."""
    guides = client.tool("find_entities", {"type": "npc", "name": "RuneScape guide", "radius": 20})
    return bool(guides.get("entities"))


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
        skills = client.tool("get_skills").get("skills", [])
        require(len(skills) == 25, "get_skills must return 25 entries, got {}".format(len(skills)))
        inventory = client.tool("get_inventory").get("items", [])
        require(isinstance(inventory, list), "get_inventory must return a list of items")
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
        attempts = []
        for dx, dy in ((WALK_DISTANCE, 0), (-WALK_DISTANCE, 0), (0, WALK_DISTANCE), (0, -WALK_DISTANCE)):
            goal = {"x": before["x"] + dx, "y": before["y"] + dy, "plane": before["plane"]}
            nav = walk(client, goal)
            state, reason = nav.get("last_state"), nav.get("last_reason") or ""
            if state == "ARRIVED":
                after = client.tool("get_status")["position"]
                require(chebyshev(after, goal) <= 1, "ARRIVED at {} but the goal was {}".format(after, goal))
                require(not reason, "an arrived walk must have no last_reason, got '{}'".format(reason))
                return "{} tiles: {} -> {}".format(WALK_DISTANCE, before, after)
            if state == "FAILED" and reason.startswith("stuck"):
                # MCP-16: never excuse a stuck walk as a server refusal.
                raise Failure("walk to {} got stuck: {}".format(goal, reason))
            attempts.append("{}: {} {}".format(goal, state, reason))
            if not (state == "FAILED" and reason.startswith("no path")):
                break  # anything but "no path" will not get better in another direction
        if walk_refused_by_server(client):
            raise Skip("the server refused the walk packet (an interface blocks movement here): {}".format(
                "; ".join(attempts)))
        raise Failure("no {}-tile walk arrived: {}".format(WALK_DISTANCE, "; ".join(attempts)))

    def step_walk_door():
        if on_tutorial_island(client):
            raise Skip("tutorial doors only open as the tutorial progresses")
        me = client.tool("get_status")["position"]
        locs = client.tool("find_entities", {"type": "loc", "has_op": "Open", "radius": 20, "limit": 50})
        doors = [loc for loc in locs.get("entities", [])
                 if loc.get("plane") == me["plane"] and (loc.get("extra") or {}).get("shape") == 0]
        if not doors:
            raise Skip("no closed door or gate (straight wall loc) within 20 tiles")

        tried = []
        for door in doors[:3]:
            step_x, step_y = WALL_EDGE_STEP[door["extra"]["rotation"] & 3]
            inside = {"x": door["x"], "y": door["y"], "plane": me["plane"]}
            outside = {"x": door["x"] + step_x, "y": door["y"] + step_y, "plane": me["plane"]}
            near, far = (inside, outside) if chebyshev(me, inside) <= chebyshev(me, outside) else (outside, inside)

            there = walk(client, far)
            if there.get("last_state") == "FAILED" and (there.get("last_reason") or "").startswith("no path"):
                tried.append("{}: {}".format(door["target"], there.get("last_reason")))
                continue
            require(there.get("last_state") == "ARRIVED",
                    "walking through {} to {} ended {} ({})".format(
                        door["target"], far, there.get("last_state"), there.get("last_reason")))
            require(there.get("last_doors_opened", 0) >= 1,
                    "walk_to reached {} without opening {} (doors_opened 0)".format(far, door["target"]))

            back = walk(client, near)
            require(back.get("last_state") == "ARRIVED",
                    "walking back through {} to {} ended {} ({})".format(
                        door["target"], near, back.get("last_state"), back.get("last_reason")))
            return "through {} and back, {} + {} doors opened".format(
                door["target"], there.get("last_doors_opened"), back.get("last_doors_opened", 0))
        raise Failure("no route through any nearby door: {}".format("; ".join(tried)))

    def step_door():
        doors = client.tool("find_entities", {"type": "loc", "name": "door", "has_op": "Open", "radius": 20}).get("entities", [])
        if not doors:
            raise Skip("no closed door within 20 tiles")
        client.tool("do_action", {"target": doors[0]["target"], "op": "Open"})
        client.tool("wait_for", {"conditions": [{"condition": "ticks", "n": 3}], "timeout_ms": 10000})
        return "opened {}".format(doors[0]["target"])

    def step_dialogue():
        guide = client.tool("find_entities", {"type": "npc", "name": "Lumbridge Guide", "has_op": "Talk-to", "radius": 20}).get("entities", [])
        if not guide:
            guide = client.tool("find_entities", {"type": "npc", "has_op": "Talk-to", "radius": 10}).get("entities", [])
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
        before = len(client.tool("get_inventory").get("items", []))
        client.tool("type_text", {"text": "::item 995 100", "enter": True})
        client.tool("wait_for", {"conditions": [{"condition": "inventory_changed"}], "timeout_ms": 5000})
        after = client.tool("get_inventory").get("items", [])
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

    def step_schema():
        require(client.calls, "no tool was called")
        bad = []
        for name, result in client.calls:
            for problem in schema_problems(result):
                bad.append("{}: {}".format(name, problem))
        require(not bad, "schema violations: {}".format("; ".join(bad)))
        return "{} tool results checked".format(len(client.calls))

    run_step("1. auth negatives", step_auth, results)
    run_step("2a. initialize handshake", step_handshake, results)
    run_step("2b. tools/list", step_tools_list, results)
    run_step("3. login", step_login, results)
    run_step("4. state reads", step_state, results)
    run_step("5. find_entities", step_find, results)
    run_step("6. walk_to", step_walk, results)
    run_step("7a. walk_to through a door", step_walk_door, results)
    run_step("7b. door", step_door, results)
    run_step("8. interact + dialogue", step_dialogue, results)
    run_step("9. admin items + drag", step_admin_items, results)
    run_step("10. chat", step_chat, results)
    run_step("11. screenshot", step_screenshot, results)
    run_step("12. logout", step_logout, results)
    run_step("13. result schema", step_schema, results)

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
