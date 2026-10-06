package rt4.mcp;

import rt4.GlobalJsonConfig;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.security.SecureRandom;
import java.util.EnumSet;
import java.util.Set;

/**
 * MCP-01 — resolves the MCP settings from {@code config.json}.
 *
 * <p>{@code mcp_enabled} and {@code mcp_port} live in {@link GlobalJsonConfig}, so a missing
 * key keeps its Java default.</p>
 *
 * <p>The bearer token does <em>not</em> live in {@code config.json}: that file is tracked by
 * git, so a generated secret written into it is one {@code git add} away from being pushed.
 * The token is kept in a sibling {@link #TOKEN_FILE_NAME} file instead, which is gitignored
 * and created owner-readable. A {@code mcp_token} still present in {@code config.json} is
 * honoured for backwards compatibility, with a warning.</p>
 */
public final class McpConfig {
	public static final String BIND_ADDRESS = "127.0.0.1";
	public static final int DEFAULT_PORT = 43600;
	public static final int TOKEN_BYTES = 32;
	/** Name of the sidecar token file, kept next to {@code config.json}. */
	public static final String TOKEN_FILE_NAME = "mcp_token";

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

		if (!token.isEmpty()) {
			System.err.println("[MCP] warning: mcp_token is set in " + configPath + ", a tracked file."
					+ " Clear that key and the token will move to the gitignored "
					+ TOKEN_FILE_NAME + " file beside it");
		} else {
			token = readToken(configPath);
		}

		if (token.isEmpty()) {
			token = generateToken();
			if (!writeToken(configPath, token)) {
				System.err.println("[MCP] could not write the token to " + tokenFile(configPath)
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

	/** The sidecar token file beside {@code configPath}; null when there is no usable path. */
	static Path tokenFile(String configPath) {
		if (configPath == null || configPath.isEmpty()) {
			return null;
		}
		Path parent = Paths.get(configPath).toAbsolutePath().getParent();
		return parent == null ? null : parent.resolve(TOKEN_FILE_NAME);
	}

	/**
	 * Reads the sidecar token, or {@code ""} when there is none, it is unreadable, or it does
	 * not look like a token this class would have written.
	 */
	static String readToken(String configPath) {
		Path file = tokenFile(configPath);
		if (file == null || !Files.isRegularFile(file)) {
			return "";
		}
		try {
			String token = new String(Files.readAllBytes(file), StandardCharsets.UTF_8).trim();
			if (!token.matches("[0-9a-f]{" + TOKEN_BYTES * 2 + "}")) {
				System.err.println("[MCP] ignoring " + file + ": not a " + TOKEN_BYTES * 2
						+ "-character hex token");
				return "";
			}
			return token;
		} catch (Exception ex) {
			System.err.println("[MCP] could not read " + file + ": " + ex);
			return "";
		}
	}

	/**
	 * Writes {@code token} to the sidecar file beside {@code configPath}, owner-readable only.
	 * Returns false (without throwing) when there is no usable path or the write fails.
	 * {@code config.json} is never modified.
	 */
	static boolean writeToken(String configPath, String token) {
		return writeToken(configPath, token, null);
	}

	/** Test seam: {@code mover} replaces the real move, so a failure can be simulated. */
	static boolean writeToken(String configPath, String token, TokenMove mover) {
		Path target = tokenFile(configPath);
		if (target == null) {
			return false;
		}

		Path tmp = null;
		try {
			Path parent = target.getParent();
			if (!Files.isDirectory(parent)) {
				return false;
			}

			tmp = Files.createTempFile(parent, TOKEN_FILE_NAME + ".", ".tmp");
			restrictToOwner(tmp);
			Files.write(tmp, (token + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
			moveIntoPlace(tmp, target, mover);
			tmp = null; // The move consumed it; there is nothing left to clean up.
			return true;
		} catch (Exception ex) {
			System.err.println("[MCP] token write failed: " + ex);
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

	/** Best-effort {@code 0600}; silently skipped on a filesystem without POSIX permissions. */
	private static void restrictToOwner(Path file) {
		try {
			Set<PosixFilePermission> ownerOnly = EnumSet.of(PosixFilePermission.OWNER_READ,
					PosixFilePermission.OWNER_WRITE);
			Files.setPosixFilePermissions(file, ownerOnly);
		} catch (Exception unsupported) {
			// Windows and other non-POSIX filesystems; the token is still outside git.
		}
	}

	/** Moves the finished temp file over the token file, atomically when the filesystem allows. */
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

	/** How a finished temp file replaces the token file; a test seam for a failed write. */
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
