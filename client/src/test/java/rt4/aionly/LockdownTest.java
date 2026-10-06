package rt4.aionly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/** AIO-11 — the lockdown flag is fail-closed and comes only from the build. */
class LockdownTest {

	@Test
	void aMissingResourceMeansLocked() {
		Assertions.assertTrue(Lockdown.readFlag(null));
	}

	@Test
	void lockdownFalseDisablesIt() {
		Assertions.assertFalse(Lockdown.readFlag(stream("lockdown=false\n")));
	}

	@Test
	void anythingElseStaysLocked() {
		Assertions.assertTrue(Lockdown.readFlag(stream("lockdown=true\n")));
		Assertions.assertTrue(Lockdown.readFlag(stream("other=value\n")));
		Assertions.assertTrue(Lockdown.readFlag(stream("")));
	}

	@Test
	void theBuildWritesTheFlagResource() {
		Assertions.assertNotNull(Lockdown.class.getResourceAsStream(Lockdown.RESOURCE),
			"processResources must write rt4/aionly/build.properties into the jar");
	}

	private static InputStream stream(String text) {
		return new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
	}
}
