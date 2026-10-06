package rt4.mcp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * MCP-08 — matching a synthesized menu entry by op and optional subject. Pure, so the
 * matching rules and the error message are unit-tested.
 */
public final class EntryMatcher {
	/** One synthesized minimenu entry. {@code index} is the slot {@code MiniMenu.doAction} needs. */
	public static final class Entry {
		public final int index;
		public final String op;
		public final String subject;
		public final int action;

		public Entry(int index, String op, String subject, int action) {
			this.index = index;
			this.op = op;
			this.subject = subject;
			this.action = action;
		}
	}

	private EntryMatcher() {
	}

	public static boolean matches(Entry entry, String op, String subject) {
		if (entry == null || op == null || entry.op == null) {
			return false;
		}
		if (!entry.op.equalsIgnoreCase(op.trim())) {
			return false;
		}
		return subject == null || subject.trim().isEmpty()
				|| (entry.subject != null && entry.subject.equalsIgnoreCase(subject.trim()));
	}

	/**
	 * The first entry whose op matches (case-insensitively) and, when given, whose subject
	 * matches. Throws a message listing what was actually available.
	 */
	public static Entry match(List<Entry> entries, String op, String subject, String target) throws ToolException {
		if (op == null || op.trim().isEmpty()) {
			throw new ToolException("an op is required; available on " + target + ": " + available(entries));
		}
		for (Entry entry : entries) {
			if (matches(entry, op, subject)) {
				return entry;
			}
		}
		throw new ToolException("no op '" + op + "' on " + target + "; available: " + available(entries));
	}

	/** The distinct ops, in menu order, for an error message or for the agent to correct itself. */
	public static String available(List<Entry> entries) {
		List<String> ops = new ArrayList<String>();
		for (Entry entry : entries) {
			if (entry.op != null && !ops.contains(entry.op)) {
				ops.add(entry.op);
			}
		}
		return ops.isEmpty() ? "[]" : ops.toString();
	}

	/** Case-insensitive op lookup, used by the helper tools. */
	public static boolean hasOp(List<Entry> entries, String op) {
		if (op == null) {
			return false;
		}
		String wanted = op.trim().toLowerCase(Locale.ROOT);
		for (Entry entry : entries) {
			if (entry.op != null && entry.op.toLowerCase(Locale.ROOT).equals(wanted)) {
				return true;
			}
		}
		return false;
	}
}
