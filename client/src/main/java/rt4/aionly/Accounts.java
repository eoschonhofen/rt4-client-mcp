package rt4.aionly;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * AIO-07 / AIO-10 — the process-wide account store.
 *
 * <p>The file lives next to the resolved {@code config.json} (see {@code client.main}), so a
 * client started with {@code --config} keeps its own accounts. {@link #init} is called once at
 * startup; {@link #store} is what the MCP tool reads.</p>
 */
public final class Accounts {
	private static volatile AccountStore store;

	private Accounts() {
	}

	/** Opens (without creating) {@code accounts.json} next to {@code configPath}. */
	public static void init(String configPath) {
		String path = configPath == null || configPath.isEmpty() ? "config.json" : configPath;
		Path config = Paths.get(path).toAbsolutePath();
		Path parent = config.getParent();
		store = AccountStore.open((parent == null ? Paths.get(".") : parent).resolve("accounts.json"));
	}

	public static AccountStore store() {
		AccountStore current = store;
		if (current == null) {
			// No explicit init (a test, or an embedding): fall back to the working directory.
			init(null);
			current = store;
		}
		return current;
	}

	/**
	 * The server this client is pointed at: {@code client.hostname} once a world list or
	 * login has been attempted, otherwise the configured address, otherwise the built-in
	 * default. Used to tag new accounts and to filter {@code get_account}.
	 */
	public static String host() {
		String hostname = rt4.client.hostname;
		if (hostname == null || hostname.isEmpty()) {
			rt4.GlobalJsonConfig config = rt4.GlobalJsonConfig.instance;
			hostname = config == null ? null : config.ipAddress();
		}
		if (hostname == null || hostname.isEmpty()) {
			hostname = rt4.GlobalConfig.DEFAULT_HOSTNAME;
		}
		return hostname;
	}
}
