package rt4.mcp;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import rt4.GlobalJsonConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;

/**
 * MCP-01 — resolves the MCP settings from {@code config.json}.
 *
 * <p>The three keys ({@code mcp_enabled}, {@code mcp_port}, {@code mcp_token}) live in
 * {@link GlobalJsonConfig}, so a missing key keeps its Java default. When no token is
 * present one is generated with {@link SecureRandom} and written back into the raw JSON
 * object, which preserves any keys this class does not know about.</p>
 */
public final class McpConfig {
	public static final String BIND_ADDRESS = "127.0.0.1";
	public static final int DEFAULT_PORT = 43600;
	public static final int TOKEN_BYTES = 32;

	public final boolean enabled;
	public final int port;
	public final String token;
	public final String configPath;

	McpConfig(boolean enabled, int port, String token, String configPath) {
		this.enabled = enabled;
		this.port = port;
		this.token = token;
		this.configPath = configPath;
	}

	/**
	 * Reads the settings, generating and persisting a token if there is none yet.
	 * A missing {@link GlobalJsonConfig#instance} means "all defaults".
	 */
	public static McpConfig resolve(String configPath) {
		boolean enabled = true;
		int port = DEFAULT_PORT;
		String token = "";

		GlobalJsonConfig config = GlobalJsonConfig.instance;
		if (config != null) {
			enabled = config.mcp_enabled;
			if (config.mcp_port > 0 && config.mcp_port <= 65535) {
				port = config.mcp_port;
			} else if (config.mcp_port != 0) {
				System.err.println("[MCP] mcp_port " + config.mcp_port + " out of range, using " + DEFAULT_PORT);
			}
			if (config.mcp_token != null) {
				token = config.mcp_token.trim();
			}
			if (enabled && !isLoopback(config.ipAddress())) {
				System.err.println("[MCP] warning: ip_address is " + config.ipAddress()
						+ ", not loopback; an MCP agent can play on that server. Only connect to servers"
						+ " that allow automated play, such as an AI-only world");
			}
		}

		if (token.isEmpty()) {
			token = generateToken();
			if (!writeToken(configPath, token)) {
				System.err.println("[MCP] could not write the token to " + configPath
						+ "; using an in-memory token for this run");
			}
		}

		return new McpConfig(enabled, port, token, configPath);
	}

	/** 32 random bytes, hex encoded — always 64 lowercase hex characters. */
	static String generateToken() {
		byte[] bytes = new byte[TOKEN_BYTES];
		new SecureRandom().nextBytes(bytes);

		StringBuilder hex = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			hex.append(Character.forDigit(b >> 4 & 0xF, 16));
			hex.append(Character.forDigit(b & 0xF, 16));
		}
		return hex.toString();
	}

	/**
	 * Rewrites {@code configPath} with {@code mcp_token} set. Parses the file as a raw
	 * {@link JsonObject} so unknown keys survive. Returns false (without throwing) when
	 * there is no file, it cannot be parsed as an object, or the write fails.
	 */
	static boolean writeToken(String configPath, String token) {
		return writeToken(configPath, token, null);
	}

	/** Test seam: {@code mover} replaces the real move, so a failure can be simulated. */
	static boolean writeToken(String configPath, String token, TokenMove mover) {
		if (configPath == null || configPath.isEmpty()) {
			return false;
		}

		Path target = Paths.get(configPath);
		Path tmp = null;
		try {
			if (!Files.isRegularFile(target)) {
				return false;
			}

			String raw = new String(Files.readAllBytes(target), StandardCharsets.UTF_8);
			JsonObject root;
			try {
				root = JsonParser.parseString(raw).getAsJsonObject();
			} catch (RuntimeException malformed) {
				// Not a JSON object we can extend; leave the file alone rather than rewriting it
				// and losing the keys we cannot see.
				return false;
			}
			root.addProperty("mcp_token", token);

			String pretty = new GsonBuilder().setPrettyPrinting().create().toJson(root) + System.lineSeparator();

			Path parent = target.toAbsolutePath().getParent();
			tmp = Files.createTempFile(parent, "config.json.", ".tmp");
			Files.write(tmp, pretty.getBytes(StandardCharsets.UTF_8));
			moveIntoPlace(tmp, target, mover);
			tmp = null; // The move consumed it; there is nothing left to clean up.
			return true;
		} catch (Exception ex) {
			System.err.println("[MCP] token write-back failed: " + ex);
			return false;
		} finally {
			if (tmp != null) {
				try {
					Files.deleteIfExists(tmp);
				} catch (Exception ignored) {
					// Already gone, or the directory refuses deletes.
				}
			}
		}
	}

	/** Moves the finished temp file over the config, atomically when the filesystem allows. */
	private static void moveIntoPlace(Path tmp, Path target, TokenMove mover) throws Exception {
		if (mover != null) {
			mover.move(tmp, target);
			return;
		}
		try {
			Files.move(tmp, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception atomicUnsupported) {
			Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/** How a finished temp file replaces the config; a test seam for a failed write. */
	interface TokenMove {
		void move(Path from, Path to) throws Exception;
	}

	/**
	 * MCP-26 — whether the client is pointed at this machine: {@code localhost}, anything in
	 * {@code 127.0.0.0/8}, or {@code ::1} in any spelling, bracketed or not. A blank address says
	 * nothing. Host names are not resolved, so this never touches DNS.
	 */
	static boolean isLoopback(String address) {
		if (address == null) {
			return true;
		}
		String host = address.trim();
		if (host.startsWith("[") && host.endsWith("]")) {
			host = host.substring(1, host.length() - 1);
		}
		if (host.isEmpty() || "localhost".equalsIgnoreCase(host)) {
			return true;
		}
		if (host.matches("127(\\.\\d{1,3}){3}")) {
			return true;
		}
		if (host.indexOf(':') >= 0 && host.matches("[0-9A-Fa-f:]+")) {
			try {
				return InetAddress.getByName(host).isLoopbackAddress(); // a literal: no lookup
			} catch (UnknownHostException malformed) {
				return false;
			}
		}
		return false;
	}
}
