package rt4.aionly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * AIO-07 — the durable account store: round trip, replacement, atomic writes,
 * malformed-file quarantine and POSIX permissions.
 */
public class AccountStoreTest {
	@TempDir
	Path dir;

	private Path file() {
		return dir.resolve("accounts.json");
	}

	@Test
	public void shouldAddListAndFindAcrossReopen() {
		AccountStore store = AccountStore.open(file());
		store.add(AccountStore.Account.create("bob", "abcdefghijklmnopqrst", "play.example.org"));
		store.add(AccountStore.Account.create("alice", "01234567890123456789", "localhost"));

		AccountStore reopened = AccountStore.open(file());
		List<AccountStore.Account> all = reopened.list();
		Assertions.assertEquals(2, all.size());

		Optional<AccountStore.Account> bob = reopened.find("bob", "play.example.org");
		Assertions.assertTrue(bob.isPresent());
		Assertions.assertEquals("abcdefghijklmnopqrst", bob.get().token);
		Assertions.assertFalse(reopened.find("bob", "localhost").isPresent());
		Assertions.assertFalse(reopened.find("nobody", "play.example.org").isPresent());
	}

	@Test
	public void shouldReplaceAnEntryWithTheSameNameAndHost() {
		AccountStore store = AccountStore.open(file());
		store.add(AccountStore.Account.create("bob", "abcdefghijklmnopqrst", "play.example.org"));
		store.add(AccountStore.Account.create("bob", "01234567890123456789", "play.example.org"));

		Assertions.assertEquals(1, store.list().size());
		Assertions.assertEquals("01234567890123456789", store.find("bob", "play.example.org").get().token);
	}

	@Test
	public void shouldKeepTheSameNameOnDifferentHosts() {
		AccountStore store = AccountStore.open(file());
		store.add(AccountStore.Account.create("bob", "abcdefghijklmnopqrst", "play.example.org"));
		store.add(AccountStore.Account.create("bob", "01234567890123456789", "localhost"));

		Assertions.assertEquals(2, store.list().size());
	}

	@Test
	public void shouldLeaveNoTemporaryFileBehind() throws IOException {
		AccountStore store = AccountStore.open(file());
		store.add(AccountStore.Account.create("bob", "abcdefghijklmnopqrst", "localhost"));

		try (Stream<Path> entries = Files.list(dir)) {
			Assertions.assertFalse(entries.anyMatch(path -> path.getFileName().toString().endsWith(".tmp")));
		}
	}

	@Test
	public void shouldQuarantineAMalformedFileAndStartEmpty() throws IOException {
		Files.write(file(), "this is not json".getBytes(StandardCharsets.UTF_8));

		AccountStore store = AccountStore.open(file());

		Assertions.assertTrue(store.list().isEmpty());
		try (Stream<Path> entries = Files.list(dir)) {
			Path quarantined = entries
				.filter(path -> path.getFileName().toString().startsWith("accounts.json.bad-"))
				.findFirst()
				.orElseThrow(() -> new AssertionError("the malformed file was not kept"));
			Assertions.assertEquals("this is not json", new String(Files.readAllBytes(quarantined), StandardCharsets.UTF_8));
		}
	}

	@Test
	public void shouldQuarantineAnEmptyFile() throws IOException {
		Files.write(file(), new byte[0]);

		AccountStore store = AccountStore.open(file());

		Assertions.assertTrue(store.list().isEmpty());
		Assertions.assertFalse(Files.exists(file()));
	}

	@Test
	public void shouldUseOwnerOnlyPermissionsOnPosix() throws IOException {
		Assumptions.assumeTrue(Files.getFileStore(dir).supportsFileAttributeView("posix"));

		AccountStore store = AccountStore.open(file());
		store.add(AccountStore.Account.create("bob", "abcdefghijklmnopqrst", "localhost"));

		Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(file());
		Assertions.assertEquals(
			EnumSet.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE),
			permissions);
	}
}
