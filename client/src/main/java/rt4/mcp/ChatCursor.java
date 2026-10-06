package rt4.mcp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * MCP-06 — turns the client's chat buffer into a monotonic sequence of messages.
 *
 * <p>{@code Chat.add} shifts the display arrays down, so index 0 is the newest message and
 * there is no absolute counter in the buffer. {@code Chat.size}, however, is a monotonic
 * count of every message ever added, so the number of new messages between two polls is
 * simply the difference. The cursor assigns sequence numbers as it observes them and
 * remembers the last {@code capacity()} of them, oldest first.</p>
 *
 * <p>Pure: the client buffer is behind {@link Source}, which the tests fake.</p>
 */
public final class ChatCursor {
	/** The chat buffer, index 0 = newest. */
	public interface Source {
		/** Total messages ever added, monotonically non-decreasing. */
		int total();

		/** How many messages the buffer can hold. */
		int capacity();

		int type(int index);

		String name(int index);

		String text(int index);
	}

	public static final class Entry {
		public final int seq;
		public final int type;
		public final String typeName;
		public final String name;
		public final String text;

		Entry(int seq, int type, String name, String text) {
			this.seq = seq;
			this.type = type;
			this.typeName = Names.chatTypeName(type);
			this.name = name;
			this.text = text;
		}
	}

	public static final class Result {
		public final int next;
		public final List<Entry> messages;

		Result(int next, List<Entry> messages) {
			this.next = next;
			this.messages = messages;
		}
	}

	private int seenTotal = -1;
	/** Sequence numbers start at 1, so the default {@code since=0} returns the backlog. */
	private int nextSeq = 1;
	private final Deque<Entry> recent = new ArrayDeque<Entry>();

	/** Forgets everything; the next poll re-reads the whole buffer as the initial backlog. */
	public void reset() {
		seenTotal = -1;
		nextSeq = 1;
		recent.clear();
	}

	/**
	 * Observes the buffer and returns every remembered message with {@code seq > since},
	 * oldest first, at most {@code limit} of them. {@code next} is the sequence number to
	 * pass as {@code since} next time — the last one actually returned — so a truncated
	 * reply continues where it stopped and a full one never repeats a message.
	 */
	public Result poll(Source source, int since, int limit) {
		int total = source.total();
		int capacity = Math.max(1, source.capacity());
		int buffered = Math.max(0, Math.min(total, capacity));

		if (seenTotal < 0) {
			// First observation: the whole buffer is the backlog, oldest first.
			append(source, buffered - 1);
		} else {
			int added = total - seenTotal;
			if (added < 0) {
				// The buffer was reset underneath us (relogin or a reload).
				recent.clear();
				append(source, buffered - 1);
			} else if (added > 0) {
				append(source, Math.min(added, buffered) - 1);
			}
		}
		seenTotal = total;

		List<Entry> matches = new ArrayList<Entry>();
		for (Entry entry : recent) {
			if (entry.seq > since) {
				matches.add(entry);
				if (matches.size() >= limit) {
					break;
				}
			}
		}

		// "next" is the seq to pass as "since" next time: the last one returned, so a
		// truncated reply continues where it stopped instead of skipping or repeating.
		int next = matches.isEmpty() ? Math.max(since, nextSeq - 1) : matches.get(matches.size() - 1).seq;
		return new Result(next, Collections.unmodifiableList(matches));
	}

	/** Adds buffer indices {@code from} down to 0, oldest first, assigning sequence numbers. */
	private void append(Source source, int from) {
		int capacity = Math.max(1, source.capacity());
		for (int index = from; index >= 0; index--) {
			recent.addLast(new Entry(nextSeq++, source.type(index), source.name(index), source.text(index)));
			while (recent.size() > capacity) {
				recent.removeFirst();
			}
		}
	}
}
