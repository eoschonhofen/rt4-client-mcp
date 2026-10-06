package rt4;

import com.google.gson.Gson;

import java.io.FileReader;
import java.math.BigInteger;

public class GlobalJsonConfig {
	public static GlobalJsonConfig instance = null;

	public static void load(String path) {
		Gson gson = new Gson();

		try {
			instance = gson.fromJson(new FileReader(path), GlobalJsonConfig.class);
		} catch (Exception ex) {
			System.err.println("No config.json file, using defaults");
		}
	}

	/** AIO-03 — parse without touching the filesystem, for tests and the release bootstrap. */
	static GlobalJsonConfig parse(String json) {
		return new Gson().fromJson(json, GlobalJsonConfig.class);
	}

	/**
	 * AIO-03 — a public deployment ships its own RSA modulus, so that the private
	 * half stays private. Call this right after {@link #load(String)}, before any
	 * login or create packet can be built.
	 */
	public static void applyRsaModulus() {
		if (instance == null) {
			return;
		}
		BigInteger modulus = instance.rsaModulus();
		if (modulus != null) {
			GlobalConfig.RSA_MODULUS = modulus;
		}
	}

	// ----

	String ip_management;
	String ip_address;
	int world;
	int server_port;
	int wl_port;
	int js5_port;
	boolean mouseWheelZoom = GlobalConfig.MOUSEWHEEL_ZOOM;
	public String pluginsFolder = "plugins";

	// MCP server (see docs/mcp). Gson leaves missing keys at these Java defaults.
	public boolean mcp_enabled = true;
	public int mcp_port = 43600;
	public String mcp_token = "";

	/** AIO-03 — optional decimal RSA modulus. Missing or blank keeps the built-in default. */
	public String rsa_modulus;

	/** MCP-26 — the game server this client points at, for the non-loopback warning. */
	public String ipAddress() {
		return ip_address;
	}

	/** AIO-03 — the configured modulus override, or null when there is none. */
	public BigInteger rsaModulus() {
		if (rsa_modulus == null) {
			return null;
		}
		String trimmed = rsa_modulus.trim();
		if (trimmed.isEmpty()) {
			return null;
		}
		return new BigInteger(trimmed);
	}
}
