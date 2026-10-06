#!/usr/bin/env python3
"""MCP client for the RT4 client's embedded MCP server.

Stdlib only, so run it with:  python3 -I scripts/rt4-mcp.py <command>

It finds the server by itself: the token comes from the mcp_token file beside the client's config.json and the
port is probed from mcp_port up to mcp_port + 9, so nobody has to paste the bearer token.

Commands:

  bridge            stdio <-> HTTP proxy. Register it as a stdio MCP server:
                      claude mcp add rt4 -- python3 -I /path/to/scripts/rt4-mcp.py bridge
                    The proxy re-initializes by itself when the game client restarts.
  ports             List the RT4 clients answering on this machine.
  tools             List the tools (--json prints the full schemas).
  call NAME [ARGS]  Call one tool. ARGS is a JSON object, or key=value pairs.
                    Prints structuredContent as JSON and saves images to --out.

Overrides, in order: --url/--port, --token, --config, then $RT4_MCP_URL, $RT4_MCP_PORT,
$RT4_MCP_TOKEN and $RT4_CONFIG.
"""

import argparse
import base64
import http.client
import json
import os
import sys
import tempfile
import threading
import time
import urllib.parse

DEFAULT_PORT = 43600
# AIO-13 — clients bind the first free port of mcp_port .. mcp_port + 9.
PORT_SPAN = 10
PROTOCOL_VERSION = "2025-06-18"
CLIENT_INFO = {"name": "rt4-mcp", "version": "1.0.0"}
PROBE_TIMEOUT = 1.0
# wait_for may block 60 s; leave headroom for the game-thread hops around it.
CALL_TIMEOUT = 90.0
SESSION_HEADER = "Mcp-Session-Id"
# JSON-RPC server error for "the game client is not reachable".
UNREACHABLE = -32000


class McpError(Exception):
    """The server answered, but not with a usable result."""


class Unreachable(McpError):
    """Nothing usable is listening."""


# ---------------------------------------------------------------- discovery


def script_repo():
    return os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


def config_candidates(explicit):
    if explicit:
        return [explicit]
    paths = []
    if os.environ.get("RT4_CONFIG"):
        paths.append(os.environ["RT4_CONFIG"])
    # gradlew :client:run starts the client in client/client, so that is where config.json lives.
    paths.append(os.path.join(script_repo(), "client", "config.json"))
    paths.append(os.path.join(os.getcwd(), "config.json"))
    paths.append(os.path.join(os.getcwd(), "client", "config.json"))
    paths.append(os.path.join(os.getcwd(), "client", "client", "config.json"))
    return paths


TOKEN_FILE_NAME = "mcp_token"


def read_token_file(directory):
    """The sidecar token written by the client, or "" when there is none."""
    try:
        with open(os.path.join(directory, TOKEN_FILE_NAME), encoding="utf-8") as handle:
            return handle.read().strip()
    except FileNotFoundError:
        return ""
    except OSError as error:
        raise McpError("cannot read the token file: {}".format(error))


def load_token(explicit_config, config_path, config):
    """The bearer token: the sidecar file beside config.json, or a legacy mcp_token key."""
    legacy = (config.get("mcp_token") or "").strip()
    if legacy:
        return legacy
    directories = []
    if config_path:
        directories.append(os.path.dirname(os.path.abspath(config_path)))
    else:
        directories = [os.path.dirname(os.path.abspath(path))
                       for path in config_candidates(explicit_config)]
    for directory in directories:
        token = read_token_file(directory)
        if token:
            return token
    return ""


def load_config(explicit):
    for path in config_candidates(explicit):
        try:
            with open(path, encoding="utf-8") as handle:
                return path, json.load(handle)
        except FileNotFoundError:
            continue
        except (OSError, ValueError) as error:
            raise McpError("cannot read {}: {}".format(path, error))
    if explicit:
        raise McpError("config not found: {}".format(explicit))
    return None, {}


class Target:
    """Where the server is and how to authenticate, before any port is picked."""

    def __init__(self, args):
        self.config_path, config = load_config(getattr(args, "config", None))
        self.token = (args.token or os.environ.get("RT4_MCP_TOKEN")
                      or load_token(getattr(args, "config", None), self.config_path, config))
        url = args.url or os.environ.get("RT4_MCP_URL")
        port = args.port or os.environ.get("RT4_MCP_PORT")
        if url:
            parsed = urllib.parse.urlsplit(url)
            self.host = parsed.hostname or "127.0.0.1"
            self.ports = [parsed.port or 80]
            self.path = parsed.path or "/mcp"
        else:
            self.host = "127.0.0.1"
            self.path = "/mcp"
            if port:
                self.ports = [int(port)]
            else:
                base = config.get("mcp_port") or DEFAULT_PORT
                self.ports = list(range(base, base + PORT_SPAN))

    def describe(self):
        if len(self.ports) == 1:
            return "{}:{}".format(self.host, self.ports[0])
        return "{}:{}-{}".format(self.host, self.ports[0], self.ports[-1])

    def require_token(self):
        if not self.token:
            raise McpError(
                "no bearer token: start the client once so it writes the mcp_token file beside config.json, "
                "or pass --token / --config (looked in: {})".format(", ".join(config_candidates(None))))


# ---------------------------------------------------------------- HTTP session


class Session:
    """One MCP session on one port. Thread-safe: requests may overlap (wait_for blocks)."""

    def __init__(self, host, port, path, token):
        self.host = host
        self.port = port
        self.path = path
        self.token = token
        self.session = None
        self.server_info = None

    @property
    def url(self):
        return "http://{}:{}{}".format(self.host, self.port, self.path)

    def post(self, message, timeout=CALL_TIMEOUT):
        """POST one JSON-RPC message; returns (status, parsed body or None, raw body)."""
        connection = http.client.HTTPConnection(self.host, self.port, timeout=timeout)
        headers = {
            "Content-Type": "application/json",
            "Accept": "application/json, text/event-stream",
            "Authorization": "Bearer " + self.token,
        }
        if self.session:
            headers[SESSION_HEADER] = self.session
        try:
            try:
                connection.connect()
            except OSError as error:
                raise Unreachable("{}: {}".format(self.url, error))
            # Past this point the request may have reached the game, so it is not safe to resend.
            try:
                connection.request("POST", self.path, body=json.dumps(message), headers=headers)
                response = connection.getresponse()
                data = response.read()
                session_id = response.getheader(SESSION_HEADER)
            except OSError as error:
                raise McpError("{}: {} failed mid-request: {}".format(self.url, message.get("method"), error))
        finally:
            connection.close()
        if session_id:
            self.session = session_id
        body = None
        if data:
            try:
                body = json.loads(data)
            except ValueError:
                body = None
        return response.status, body, data

    def initialize(self, params=None, timeout=CALL_TIMEOUT):
        params = params or {"protocolVersion": PROTOCOL_VERSION, "capabilities": {}, "clientInfo": CLIENT_INFO}
        self.session = None
        status, body, data = self.post({"jsonrpc": "2.0", "id": 0, "method": "initialize", "params": params},
                                       timeout=timeout)
        if status == 401:
            raise McpError("{} rejected the bearer token (401); check the mcp_token file beside config.json".format(self.url))
        if status != 200 or not body or "result" not in body:
            raise Unreachable("{} is not an RT4 MCP server (HTTP {}: {!r})".format(self.url, status, data[:120]))
        self.server_info = body["result"].get("serverInfo")
        self.post({"jsonrpc": "2.0", "method": "notifications/initialized"}, timeout=timeout)
        return body["result"]

    def close(self):
        if not self.session:
            return
        connection = http.client.HTTPConnection(self.host, self.port, timeout=PROBE_TIMEOUT)
        try:
            connection.request("DELETE", self.path, headers={
                "Authorization": "Bearer " + self.token, SESSION_HEADER: self.session})
            connection.getresponse().read()
        except OSError:
            pass
        finally:
            connection.close()
            self.session = None


def connect(target, params=None):
    """Initializes a session on the first port that answers; raises Unreachable if none does."""
    target.require_token()
    problems = []
    for port in target.ports:
        session = Session(target.host, port, target.path, target.token)
        try:
            result = session.initialize(params, timeout=PROBE_TIMEOUT if len(target.ports) > 1 else CALL_TIMEOUT)
            return session, result
        except Unreachable as error:
            problems.append(str(error))
    raise Unreachable("no RT4 client MCP server on {}. Start the client (./start-client.sh) and check that "
                      "mcp_enabled is true. Last error: {}".format(target.describe(), problems[-1] if problems else "-"))


def scan(target):
    """Every port in range that answers an initialize, as (port, serverInfo)."""
    found = []
    for port in target.ports:
        session = Session(target.host, port, target.path, target.token)
        try:
            result = session.initialize(timeout=PROBE_TIMEOUT)
        except McpError:
            continue
        found.append((port, result.get("serverInfo", {})))
        session.close()
    return found


# ---------------------------------------------------------------- bridge


class Bridge:
    """Newline-delimited JSON-RPC on stdio, forwarded to the HTTP server.

    The host's own initialize goes through unchanged. If the game client restarts, the
    session vanishes (404) or the port goes dark; the bridge replays that initialize on
    whichever port answers and retries the request once.
    """

    def __init__(self, target, log):
        self.target = target
        self.log = log
        self.session = None
        self.init_params = None
        self.lock = threading.Lock()
        self.out_lock = threading.Lock()

    def write(self, message):
        line = json.dumps(message, separators=(",", ":"))
        with self.out_lock:
            sys.stdout.write(line + "\n")
            sys.stdout.flush()

    def reconnect(self, stale):
        with self.lock:
            if self.session is not stale and self.session is not None:
                return self.session
            self.session, _ = connect(self.target, self.init_params)
            self.log("connected to {} ({})".format(self.session.url, (self.session.server_info or {}).get("name")))
            return self.session

    def handle(self, message):
        request_id = message.get("id")
        method = message.get("method")
        is_request = method is not None and "id" in message
        try:
            if method == "initialize":
                self.init_params = message.get("params")
                with self.lock:
                    if self.session is not None:
                        self.session.close()
                    self.session = None
                    self.target.require_token()
                    self.session = self.first_port(message)
                return
            session = self.session or self.reconnect(None)
            try:
                status, body, data = self.forward(session, message)
            except Unreachable:
                self.log("{} stopped answering, looking for the client again".format(session.url))
                session = self.reconnect(session)
                status, body, data = self.forward(session, message)
            if status in (400, 404) and self.is_session_error(body):
                self.log("session lost (HTTP {}), re-initializing".format(status))
                session = self.reconnect(session)
                status, body, data = self.forward(session, message)
            self.reply(message, status, body, data)
        except Unreachable as error:
            self.log(str(error))
            with self.lock:
                self.session = None
            if is_request:
                self.write(self.error(request_id, UNREACHABLE, str(error)))
        except McpError as error:
            self.log(str(error))
            if is_request:
                self.write(self.error(request_id, UNREACHABLE, str(error)))

    def first_port(self, message):
        """Sends the host's initialize to each port in turn and relays the first real answer."""
        problems = []
        for port in self.target.ports:
            session = Session(self.target.host, port, self.target.path, self.target.token)
            timeout = PROBE_TIMEOUT if len(self.target.ports) > 1 else CALL_TIMEOUT
            try:
                status, body, data = session.post(message, timeout=timeout)
            except Unreachable as error:
                problems.append(str(error))
                continue
            if status == 401:
                raise McpError("{} rejected the bearer token (401); check the mcp_token file beside config.json".format(session.url))
            if status != 200 or not body or "result" not in body:
                problems.append("{}: HTTP {}".format(session.url, status))
                continue
            session.server_info = body["result"].get("serverInfo")
            self.log("connected to {} ({})".format(session.url, (session.server_info or {}).get("name")))
            self.write(body)
            return session
        raise Unreachable("no RT4 client MCP server on {}. Start the client (./start-client.sh) and reconnect "
                          "(/mcp in Claude Code). Last error: {}".format(
                              self.target.describe(), problems[-1] if problems else "-"))

    @staticmethod
    def forward(session, message):
        return session.post(message)

    @staticmethod
    def is_session_error(body):
        if not isinstance(body, dict):
            return False
        text = str((body.get("error") or {}).get("message", ""))
        return SESSION_HEADER in text

    def reply(self, message, status, body, data):
        if "id" not in message or message.get("method") is None:
            return  # notification or response from the host: nothing to relay
        if status == 200 and isinstance(body, dict):
            self.write(body)
        elif isinstance(body, dict) and "error" in body:
            body["id"] = message["id"]
            self.write(body)
        else:
            text = data.decode("utf-8", "replace").strip() if data else ""
            self.write(self.error(message["id"], UNREACHABLE, "HTTP {}: {}".format(status, text or "no body")))

    @staticmethod
    def error(request_id, code, text):
        return {"jsonrpc": "2.0", "id": request_id, "error": {"code": code, "message": text}}

    def run(self):
        threads = []
        for line in sys.stdin:
            line = line.strip()
            if not line:
                continue
            try:
                message = json.loads(line)
            except ValueError:
                self.write(self.error(None, -32700, "parse error"))
                continue
            if isinstance(message, list):
                self.write(self.error(None, -32600, "batches are not supported"))
                continue
            if message.get("method") == "initialize":
                # Everything after initialize needs its session, so run it inline.
                self.handle(message)
                continue
            thread = threading.Thread(target=self.handle, args=(message,), daemon=True)
            thread.start()
            threads.append(thread)
            threads = [t for t in threads if t.is_alive()]
        for thread in threads:
            thread.join(CALL_TIMEOUT)
        if self.session is not None:
            self.session.close()


# ---------------------------------------------------------------- one-shot commands


def rpc(session, method, params=None):
    message = {"jsonrpc": "2.0", "id": 1, "method": method}
    if params is not None:
        message["params"] = params
    status, body, data = session.post(message)
    if status != 200 or not isinstance(body, dict):
        raise McpError("{} returned HTTP {}: {!r}".format(method, status, data[:200]))
    if "error" in body:
        raise McpError("{} failed: {}".format(method, body["error"].get("message")))
    return body["result"]


def parse_arguments(raw):
    """A JSON object, or key=value pairs whose values are parsed as JSON when they can be."""
    if not raw:
        return {}
    if len(raw) == 1 and raw[0].lstrip().startswith("{"):
        try:
            value = json.loads(raw[0])
        except ValueError as error:
            raise McpError("arguments are not valid JSON: {}".format(error))
        if not isinstance(value, dict):
            raise McpError("arguments must be a JSON object")
        return value
    arguments = {}
    for pair in raw:
        if "=" not in pair:
            raise McpError("expected key=value, got {!r}".format(pair))
        key, value = pair.split("=", 1)
        try:
            arguments[key] = json.loads(value)
        except ValueError:
            arguments[key] = value
    return arguments


def save_image(block, out_dir, name):
    os.makedirs(out_dir, exist_ok=True)
    extension = {"image/png": ".png", "image/jpeg": ".jpg"}.get(block.get("mimeType"), ".bin")
    handle, path = tempfile.mkstemp(prefix=name + "-", suffix=extension, dir=out_dir)
    with os.fdopen(handle, "wb") as out:
        out.write(base64.b64decode(block.get("data", "")))
    return path


def command_ports(target, args):
    target.require_token()
    found = scan(target)
    if not found:
        print("no RT4 client MCP server on {}".format(target.describe()), file=sys.stderr)
        return 1
    for port, info in found:
        print("{}\thttp://{}:{}{}\t{} {}".format(port, target.host, port, target.path,
                                                 info.get("name", "?"), info.get("version", "")))
    return 0


def command_tools(target, args):
    session, _ = connect(target)
    try:
        tools = rpc(session, "tools/list").get("tools", [])
    finally:
        session.close()
    if args.json:
        print(json.dumps(tools, indent=2))
        return 0
    for tool in tools:
        schema = tool.get("inputSchema", {})
        required = set(schema.get("required", []))
        params = ["{}{}".format(name, "" if name in required else "?") for name in schema.get("properties", {})]
        print("{}({})".format(tool["name"], ", ".join(params)))
        if args.verbose:
            print("    " + tool.get("description", ""))
    return 0


def command_call(target, args):
    arguments = parse_arguments(args.arguments)
    session, _ = connect(target)
    try:
        result = rpc(session, "tools/call", {"name": args.name, "arguments": arguments})
    finally:
        session.close()
    texts = []
    for block in result.get("content", []):
        if block.get("type") == "image":
            path = save_image(block, args.out, args.name)
            print("image saved: {}".format(path), file=sys.stderr)
        elif block.get("type") == "text":
            texts.append(block.get("text", ""))
    if "structuredContent" in result and not args.text:
        print(json.dumps(result["structuredContent"], indent=2 if args.pretty else None))
    else:
        print("\n".join(texts))
    return 2 if result.get("isError") else 0


def build_parser():
    parser = argparse.ArgumentParser(description=__doc__.split("\n\n")[0])
    parser.add_argument("--url", help="Full endpoint, e.g. http://127.0.0.1:43601/mcp. Skips port probing.")
    parser.add_argument("--port", type=int, help="Use this port only instead of probing mcp_port .. +9.")
    parser.add_argument("--token", help="Bearer token. Defaults to the mcp_token file beside config.json.")
    parser.add_argument("--config", help="Path to the client's config.json.")
    commands = parser.add_subparsers(dest="command")

    bridge = commands.add_parser("bridge", help="stdio <-> HTTP proxy for MCP hosts (default)")
    bridge.add_argument("--quiet", action="store_true", help="No log lines on stderr.")

    commands.add_parser("ports", help="list the RT4 clients answering on this machine")

    tools = commands.add_parser("tools", help="list the tools")
    tools.add_argument("--json", action="store_true", help="Print the full tool definitions.")
    tools.add_argument("-v", "--verbose", action="store_true", help="Print descriptions too.")

    call = commands.add_parser("call", help="call one tool")
    call.add_argument("name", help="Tool name, e.g. get_status.")
    call.add_argument("arguments", nargs="*", help="A JSON object, or key=value pairs.")
    call.add_argument("--pretty", action="store_true", help="Indent the JSON output.")
    call.add_argument("--text", action="store_true", help="Print the text content instead of structuredContent.")
    call.add_argument("--out", default=os.path.join(tempfile.gettempdir(), "rt4-mcp"),
                      help="Directory for images (get_screenshot). Default: %(default)s")
    return parser


def main(argv):
    args = build_parser().parse_args(argv)
    command = args.command or "bridge"
    try:
        target = Target(args)
        if command == "bridge":
            quiet = getattr(args, "quiet", False)

            def log(text):
                if not quiet:
                    print("[rt4-mcp] " + text, file=sys.stderr, flush=True)

            log("bridging stdio to {}{} (config: {})".format(target.describe(), target.path, target.config_path))
            Bridge(target, log).run()
            return 0
        if command == "ports":
            return command_ports(target, args)
        if command == "tools":
            return command_tools(target, args)
        return command_call(target, args)
    except McpError as error:
        print("rt4-mcp: {}".format(error), file=sys.stderr)
        return 1
    except KeyboardInterrupt:
        return 130


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
