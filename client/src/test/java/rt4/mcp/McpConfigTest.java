package rt4.mcp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import rt4.GlobalJsonConfig;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class McpConfigTest {
	@TempDir
	Path tempDir;

	@AfterEach
	void resetConfig() {
		GlobalJsonConfig.instance = null;
	}

	private static GlobalJsonConfig config() {
		GlobalJsonConfig instance = new GlobalJsonConfig();
		GlobalJsonConfig.instance = instance;
		return instance;
	}

	@Test
	void defaultsWhenInstanceIsNull() {
		GlobalJsonConfig.instance = null;

		McpConfig resolved = McpConfig.resolve(tempDir.resolve("missing.json").toString());

		assertTrue(resolved.enabled);
		assertEquals(43600, resolved.port);
		assertEquals(64, resolved.token.length());
		assertTrue(resolved.token.matches("[0-9a-f]{64}"));
	}

	@Test
	void generatedTokenIsSixtyFourHexChars() {
		for (int i = 0; i < 20; i++) {
			String token = McpConfig.generateToken();
			assertEquals(64, token.length());
			assertTrue(token.matches("[0-9a-f]{64}"));
		}
		assertNotEquals(McpConfig.generateToken(), McpConfig.generateToken());
	}

	@Test
	void aGeneratedTokenLandsInTheSidecarAndLeavesConfigAlone() throws Exception {
		Path path = tempDir.resolve("config.json");
		String original = "{\n  \"ip_address\": \"127.0.0.1\",\n  \"custom_key\": [1, 2, 3]\n}\n";
		Files.write(path, original.getBytes(StandardCharsets.UTF_8));

		config().mcp_token = "";
		McpConfig resolved = McpConfig.resolve(path.toString());

		Path sidecar = tempDir.resolve(McpConfig.TOKEN_FILE_NAME);
		assertEquals(resolved.token,
				new String(Files.readAllBytes(sidecar), StandardCharsets.UTF_8).trim());
		// config.json is tracked by git, so the secret must never be written into it.
		assertEquals(original, new String(Files.readAllBytes(path), StandardCharsets.UTF_8));
		// Only config.json and the sidecar: no temp file left behind.
		try (java.util.stream.Stream<Path> files = Files.list(tempDir)) {
			assertEquals(2L, files.count());
		}
	}

	@Test
	void theSidecarIsReadableOnlyByItsOwner() throws Exception {
		Path path = tempDir.resolve("config.json");
		Files.write(path, "{}\n".getBytes(StandardCharsets.UTF_8));

		config().mcp_token = "";
		McpConfig.resolve(path.toString());

		Path sidecar = tempDir.resolve(McpConfig.TOKEN_FILE_NAME);
		assumeTrue(sidecar.getFileSystem().supportedFileAttributeViews().contains("posix"));
		assertEquals("rw-------",
				java.nio.file.attribute.PosixFilePermissions.toString(Files.getPosixFilePermissions(sidecar)));
	}

	@Test
	void anExistingSidecarTokenIsReused() throws Exception {
		Path path = tempDir.resolve("config.json");
		Files.write(path, "{}\n".getBytes(StandardCharsets.UTF_8));
		String existing = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
		Files.write(tempDir.resolve(McpConfig.TOKEN_FILE_NAME),
				(existing + "\n").getBytes(StandardCharsets.UTF_8));

		config().mcp_token = "";
		McpConfig resolved = McpConfig.resolve(path.toString());

		assertEquals(existing, resolved.token);
	}

	@Test
	void aJunkSidecarIsIgnoredAndReplaced() throws Exception {
		Path path = tempDir.resolve("config.json");
		Files.write(path, "{}\n".getBytes(StandardCharsets.UTF_8));
		Files.write(tempDir.resolve(McpConfig.TOKEN_FILE_NAME),
				"not a token\n".getBytes(StandardCharsets.UTF_8));

		config().mcp_token = "";
		McpConfig resolved = McpConfig.resolve(path.toString());

		assertTrue(resolved.token.matches("[0-9a-f]{64}"));
		assertEquals(resolved.token, new String(
				Files.readAllBytes(tempDir.resolve(McpConfig.TOKEN_FILE_NAME)), StandardCharsets.UTF_8).trim());
	}

	@Test
	void aFailedWriteLeavesNoTempFile() throws Exception {
		Path path = tempDir.resolve("config.json");
		Files.write(path, "{}\n".getBytes(StandardCharsets.UTF_8));

		boolean written = McpConfig.writeToken(path.toString(), "abcdef", (from, to) -> {
			throw new java.io.IOException("simulated move failure");
		});

		assertFalse(written);
		try (java.util.stream.Stream<Path> files = Files.list(tempDir)) {
			assertEquals(1L, files.count(), "the temp file must be deleted when the write fails");
		}
	}

	/** Runs {@code action} with System.err captured, and returns everything it logged. */
	private static String stderrOf(Runnable action) throws Exception {
		java.io.PrintStream original = System.err;
		java.io.ByteArrayOutputStream captured = new java.io.ByteArrayOutputStream();
		System.setErr(new java.io.PrintStream(captured, true, "UTF-8"));
		try {
			action.run();
		} finally {
			System.setErr(original);
		}
		return captured.toString("UTF-8");
	}

	@Test
	void aRemoteServerIsWarnedAboutAtStartup() throws Exception {
		Path path = tempDir.resolve("config.json");
		String token = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
		Files.write(path, ("{\"ip_address\": \"test.2009scape.org\", \"mcp_enabled\": true,"
				+ " \"mcp_token\": \"" + token + "\"}\n").getBytes(StandardCharsets.UTF_8));
		GlobalJsonConfig.load(path.toString());

		String logged = stderrOf(() -> McpConfig.resolve(path.toString()));

		assertTrue(logged.contains("not loopback"), logged);
	}

	@Test
	void aLoopbackServerIsNotWarnedAbout() throws Exception {
		Path path = tempDir.resolve("config.json");
		String token = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
		Files.write(path, ("{\"ip_address\": \"127.0.0.1\", \"mcp_enabled\": true,"
				+ " \"mcp_token\": \"" + token + "\"}\n").getBytes(StandardCharsets.UTF_8));
		GlobalJsonConfig.load(path.toString());

		String logged = stderrOf(() -> McpConfig.resolve(path.toString()));

		assertFalse(logged.contains("not loopback"), logged);
	}

	@Test
	void loopbackCoversEverySpellingOfThisMachine() {
		for (String local : new String[]{null, "", " ", "localhost", "LOCALHOST", "127.0.0.1", "127.0.1.1",
				"127.255.255.254", "::1", "[::1]", "0:0:0:0:0:0:0:1", "0000:0000:0000:0000:0000:0000:0000:0001"}) {
			assertTrue(McpConfig.isLoopback(local), "should be loopback: " + local);
		}
		for (String remote : new String[]{"test.2009scape.org", "192.168.0.10", "128.0.0.1", "127.0.0",
				"127.0.0.1.example.com", "::2", "[2001:db8::1]"}) {
			assertFalse(McpConfig.isLoopback(remote), "should not be loopback: " + remote);
		}
	}

	@Test
	void aLegacyTokenInConfigIsHonouredAndWarnedAbout() throws Exception {
		Path path = tempDir.resolve("config.json");
		String existing = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
		Files.write(path, ("{\"mcp_token\": \"" + existing + "\"}\n").getBytes(StandardCharsets.UTF_8));

		config().mcp_token = existing;
		String logged = stderrOf(() -> assertEquals(existing, McpConfig.resolve(path.toString()).token));

		assertTrue(logged.contains("a tracked file"), logged);
		assertFalse(Files.exists(tempDir.resolve(McpConfig.TOKEN_FILE_NAME)));
	}

	@Test
	void disabledAndCustomPortAreRead() {
		GlobalJsonConfig instance = config();
		instance.mcp_enabled = false;
		instance.mcp_port = 45678;

		McpConfig resolved = McpConfig.resolve(tempDir.resolve("missing.json").toString());

		assertFalse(resolved.enabled);
		assertEquals(45678, resolved.port);
	}

	@Test
	void invalidPortFallsBackToDefault() {
		config().mcp_port = -1;

		McpConfig resolved = McpConfig.resolve(tempDir.resolve("missing.json").toString());

		assertEquals(43600, resolved.port);
	}

	@Test
	void aMissingConfigFileStillGetsASidecarToken() {
		Path missing = tempDir.resolve("nope.json");
		config().mcp_token = "";

		McpConfig resolved = McpConfig.resolve(missing.toString());

		assertEquals(64, resolved.token.length());
		assertFalse(Files.exists(missing));
		assertTrue(Files.exists(tempDir.resolve(McpConfig.TOKEN_FILE_NAME)));
	}

	@Test
	void anUnusableConfigPathKeepsAnInMemoryToken() {
		config().mcp_token = "";

		McpConfig resolved = McpConfig.resolve("");

		assertEquals(64, resolved.token.length());
		assertFalse(McpConfig.writeToken("", resolved.token));
	}
}
