package rt4.mcp;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import rt4.GlobalJsonConfig;

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

	private McpConfig(boolean enabled, int port, String token, String configPath) {
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
		if (configPath == null || configPath.isEmpty()) {
			return false;
		}

		Path target = Paths.get(configPath);
		try {
			if (!Files.isRegularFile(target)) {
				return false;
			}

			String raw = new String(Files.readAllBytes(target), StandardCharsets.UTF_8);
			JsonObject root;
			try {
				root = JsonParser.parseString(raw).getAsJsonObject();
			} catch (RuntimeException malformed) {
				// Not a JSON object we can extend; fall back to a fresh one rather than
				// destroying whatever is there.
				return false;
			}
			root.addProperty("mcp_token", token);

			String pretty = new GsonBuilder().setPrettyPrinting().create().toJson(root) + System.lineSeparator();

			Path parent = target.toAbsolutePath().getParent();
			Path tmp = Files.createTempFile(parent, "config.json.", ".tmp");
			Files.write(tmp, pretty.getBytes(StandardCharsets.UTF_8));
			try {
				Files.move(tmp, target, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception atomicUnsupported) {
				Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING);
			}
			return true;
		} catch (Exception ex) {
			System.err.println("[MCP] token write-back failed: " + ex);
			return false;
		}
	}

	public String url() {
		return "http://" + BIND_ADDRESS + ":" + port + "/mcp";
	}

	/** The one-time stderr block a human copies into their MCP host. */
	public void logStartup() {
		System.err.println("[MCP] listening on " + url());
		System.err.println("[MCP] claude mcp add --transport http rt4 " + url()
				+ " --header \"Authorization: Bearer " + token + "\"");
	}
}
