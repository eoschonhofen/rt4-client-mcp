package rt4.aionly;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * AIO-15 — first-run config bootstrap for the release jar.
 *
 * <p>The release jar bundles {@code rt4/aionly/default-config.json} with the public host and
 * RSA modulus baked in. On first start there is no {@code config.json} yet, and
 * {@code McpConfig.writeToken} needs a real file to write the generated token back into, so
 * the bundled default is copied into place before the config is loaded. An existing file is
 * never overwritten.</p>
 */
public final class ConfigBootstrap {
	public static final String RESOURCE = "/rt4/aionly/default-config.json";

	private ConfigBootstrap() {
	}

	/**
	 * Writes the bundled default to {@code target} when it does not exist.
	 *
	 * @return true when a config file was written.
	 */
	public static boolean ensureConfig(Path target) throws IOException {
		return ensureConfig(target, bundledDefault());
	}

	/** The bundled default, or null in a development build where none was generated. */
	static InputStream bundledDefault() {
		return ConfigBootstrap.class.getResourceAsStream(RESOURCE);
	}

	/** Test seam: {@code bundled} replaces the resource lookup. */
	static boolean ensureConfig(Path target, InputStream bundled) throws IOException {
		if (Files.exists(target) || bundled == null) {
			return false;
		}
		Path parent = target.toAbsolutePath().getParent();
		if (parent != null) {
			Files.createDirectories(parent);
		}
		try (InputStream in = bundled) {
			Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
		}
		return true;
	}
}
