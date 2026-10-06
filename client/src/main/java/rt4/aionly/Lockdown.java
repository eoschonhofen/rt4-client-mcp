package rt4.aionly;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * AIO-11 — compile-time lockdown for release builds.
 *
 * <p>{@code ./gradlew :client:build} locks the client; {@code -PaiOnly=false} builds a
 * development client where human input and plugins work as before. The value is baked into
 * {@code rt4/aionly/build.properties} by {@code processResources} and read once, here.</p>
 *
 * <p>There is deliberately <b>no</b> system property or config override: a runtime switch
 * would turn lockdown into a one-flag bypass for any operator. A missing or unreadable
 * resource means locked, so a packaging mistake fails closed.</p>
 */
public final class Lockdown {
	static final String RESOURCE = "/rt4/aionly/build.properties";
	static final String KEY = "lockdown";

	/** Whether this build is locked. Read once, at class initialization. */
	public static final boolean ENABLED = load();

	private Lockdown() {
	}

	private static boolean load() {
		try (InputStream in = Lockdown.class.getResourceAsStream(RESOURCE)) {
			return readFlag(in);
		} catch (IOException unreadable) {
			return true;
		}
	}

	/** Missing stream, missing key or an unreadable value means locked. */
	static boolean readFlag(InputStream in) {
		if (in == null) {
			return true;
		}
		try {
			Properties properties = new Properties();
			properties.load(in);
			return Boolean.parseBoolean(properties.getProperty(KEY, "true").trim());
		} catch (IOException unreadable) {
			return true;
		}
	}
}
