package rt4.aionly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** AIO-15 — first-run config bootstrap: copy when missing, never overwrite. */
class ConfigBootstrapTest {
	@TempDir
	Path dir;

	@Test
	void copiesTheBundledDefaultWhenThereIsNoConfig() throws IOException {
		Path target = dir.resolve("config.json");

		boolean written = ConfigBootstrap.ensureConfig(target, stream("{\"ip_address\":\"play.example.org\"}"));

		Assertions.assertTrue(written);
		Assertions.assertEquals("{\"ip_address\":\"play.example.org\"}", new String(
			Files.readAllBytes(target), StandardCharsets.UTF_8));
	}

	@Test
	void neverOverwritesAnExistingConfig() throws IOException {
		Path target = dir.resolve("config.json");
		Files.write(target, "{\"ip_address\":\"localhost\"}".getBytes(StandardCharsets.UTF_8));

		boolean written = ConfigBootstrap.ensureConfig(target, stream("{\"ip_address\":\"play.example.org\"}"));

		Assertions.assertFalse(written);
		Assertions.assertEquals("{\"ip_address\":\"localhost\"}", new String(
			Files.readAllBytes(target), StandardCharsets.UTF_8));
	}

	@Test
	void doesNothingWhenNoDefaultIsBundled() throws IOException {
		Path target = dir.resolve("config.json");

		Assertions.assertFalse(ConfigBootstrap.ensureConfig(target, null));
		Assertions.assertFalse(Files.exists(target));
	}

	@Test
	void createsTheParentDirectory() throws IOException {
		Path target = dir.resolve("nested/deeper/config.json");

		Assertions.assertTrue(ConfigBootstrap.ensureConfig(target, stream("{}")));
		Assertions.assertTrue(Files.exists(target));
	}

	private static ByteArrayInputStream stream(String text) {
		return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
	}
}
