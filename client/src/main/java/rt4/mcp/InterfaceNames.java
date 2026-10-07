package rt4.mcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * MCP-28 — the cache's own name for an interface id.
 *
 * <p>An interface reaches an agent as a bare number, and a number is not something it can
 * reason about: "the panel numbered 192" is the spellbook, but only to somebody who already
 * knows. The names are the cache's, taken from the constant table the server project already
 * depends on, and committed as a resource because the client cannot depend on that jar — see
 * {@code scripts/gen-interface-names.py}.</p>
 *
 * <p>An id that is not in the table has no name, and is reported without one. Nothing is
 * invented here.</p>
 */
public final class InterfaceNames {
	private static final String RESOURCE = "/rt4/mcp/interface-names.tsv";

	private static Map<Integer, String> names;

	private InterfaceNames() {
	}

	/** The cache's name for an interface, or null when the table does not have one. */
	public static String of(int interfaceId) {
		return table().get(Integer.valueOf(interfaceId));
	}

	/** How many names the table holds. For the tests and for a sanity check at startup. */
	public static int size() {
		return table().size();
	}

	private static synchronized Map<Integer, String> table() {
		if (names == null) {
			names = load();
		}
		return names;
	}

	/**
	 * Reads the resource once. A missing or broken resource leaves the table empty rather
	 * than failing the client: a nameless panel is a smaller loss than a client that will
	 * not start.
	 */
	private static Map<Integer, String> load() {
		Map<Integer, String> loaded = new HashMap<Integer, String>();
		InputStream stream = InterfaceNames.class.getResourceAsStream(RESOURCE);
		if (stream == null) {
			return Collections.unmodifiableMap(loaded);
		}
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(stream, StandardCharsets.UTF_8));
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				parse(line, loaded);
			}
		} catch (IOException ignored) {
			// A half-read table is still better than none.
		} finally {
			try {
				stream.close();
			} catch (IOException ignored) {
				// Nothing to do about it.
			}
		}
		return Collections.unmodifiableMap(loaded);
	}

	/** One {@code id\tname} line. Comments, blanks and malformed lines are skipped. */
	static void parse(String line, Map<Integer, String> into) {
		if (line == null) {
			return;
		}
		String trimmed = line.trim();
		if (trimmed.isEmpty() || trimmed.charAt(0) == '#') {
			return;
		}
		int tab = trimmed.indexOf('\t');
		if (tab <= 0 || tab + 1 >= trimmed.length()) {
			return;
		}
		String name = trimmed.substring(tab + 1).trim();
		if (name.isEmpty()) {
			return;
		}
		try {
			into.put(Integer.valueOf(Integer.parseInt(trimmed.substring(0, tab).trim())), name);
		} catch (NumberFormatException ignored) {
			// Not an id; not a row.
		}
	}
}
