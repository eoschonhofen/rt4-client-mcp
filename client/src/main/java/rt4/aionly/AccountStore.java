package rt4.aionly;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.time.Instant;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * AIO-07 — every account created on this machine, kept next to {@code config.json}.
 *
 * <p>The token is shown once on screen; this file is the durable copy the agent
 * reads through MCP (AIO-10). It is deliberately not part of {@code rt4}: the
 * game thread calls {@link #add} and MCP threads call {@link #list} and
 * {@link #find}, so every method is synchronized and nothing here touches a
 * global.
 */
public final class AccountStore {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private final Path file;
	private final List<Account> accounts = new ArrayList<>();

	private AccountStore(Path file) {
		this.file = file;
	}

	/** Opens the store at {@code file}, creating nothing yet. */
	public static AccountStore open(Path file) {
		AccountStore store = new AccountStore(file);
		store.load();
		return store;
	}

	public synchronized List<Account> list() {
		return new ArrayList<>(accounts);
	}

	/** Adds {@code account}, replacing any earlier entry with the same name and host. */
	public synchronized void add(Account account) {
		accounts.removeIf(existing -> existing.sameIdentity(account));
		accounts.add(account);
		save();
	}

	public synchronized Optional<Account> find(String name, String host) {
		return accounts.stream().filter(account -> account.matches(name, host)).findFirst();
	}

	// ----

	private void load() {
		if (!Files.exists(file)) {
			return;
		}
		try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			AccountsFile parsed = GSON.fromJson(reader, AccountsFile.class);
			if (parsed == null) {
				quarantine("the file was empty");
				return;
			}
			if (parsed.accounts != null) {
				accounts.addAll(parsed.accounts);
			}
		} catch (IOException | JsonSyntaxException e) {
			quarantine(e.getMessage());
		}
	}

	/** Never overwrite a malformed file silently: keep it beside the new one. */
	private void quarantine(String reason) {
		Path bad = file.resolveSibling(file.getFileName() + ".bad-" + Instant.now().toEpochMilli());
		try {
			Files.move(file, bad, StandardCopyOption.REPLACE_EXISTING);
			System.err.println("[accounts] " + file + " was malformed (" + reason + "); kept as " + bad);
		} catch (IOException moveFailure) {
			System.err.println("[accounts] " + file + " was malformed (" + reason + ") and could not be moved: " + moveFailure);
		}
		accounts.clear();
	}

	private void save() {
		Path temp = file.resolveSibling(file.getFileName() + ".tmp");
		try {
			Path parent = file.toAbsolutePath().getParent();
			if (parent != null) {
				Files.createDirectories(parent);
			}
			try (Writer writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)) {
				GSON.toJson(new AccountsFile(accounts), writer);
			}
			restrictToOwner(temp);
			try {
				Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
			} catch (AtomicMoveNotSupportedException e) {
				Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING);
			}
			restrictToOwner(file);
		} catch (IOException e) {
			try {
				Files.deleteIfExists(temp);
			} catch (IOException ignored) {
				// The temp file is already the problem; nothing else to do.
			}
			System.err.println("[accounts] could not save " + file + ": " + e);
		}
	}

	private static void restrictToOwner(Path path) {
		try {
			Set<PosixFilePermission> permissions = EnumSet.of(
				PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
			Files.setPosixFilePermissions(path, permissions);
		} catch (UnsupportedOperationException | IOException ignored) {
			// Non-POSIX filesystem; the directory policy applies instead.
		}
	}

	private static final class AccountsFile {
		List<Account> accounts = new ArrayList<>();

		AccountsFile() {
		}

		AccountsFile(List<Account> accounts) {
			this.accounts = accounts;
		}
	}

	/** One saved account. Field names are the JSON keys. */
	public static final class Account {
		public String name;
		public String token;
		public String host;
		public String created;

		Account() {
			// for Gson
		}

		public Account(String name, String token, String host, String created) {
			this.name = name;
			this.token = token;
			this.host = host;
			this.created = created;
		}

		/** A new entry stamped with the current time. */
		public static Account create(String name, String token, String host) {
			return new Account(name, token, host, Instant.now().toString());
		}

		boolean sameIdentity(Account other) {
			return name.equals(other.name) && host.equals(other.host);
		}

		boolean matches(String name, String host) {
			return this.name.equals(name) && this.host.equals(host);
		}

		@Override
		public String toString() {
			return name + "@" + host;
		}
	}
}
