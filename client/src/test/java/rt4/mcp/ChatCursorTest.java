package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChatCursorTest {
	/** A shifting chat buffer, newest at index 0, like {@code rt4.Chat}. */
	private static final class FakeChat implements ChatCursor.Source {
		private final int capacity;
		private final List<int[]> types = new ArrayList<int[]>();
		private final List<String[]> payloads = new ArrayList<String[]>();
		private int total;

		FakeChat(int capacity) {
			this.capacity = capacity;
		}

		void add(int type, String name, String text) {
			types.add(0, new int[]{type});
			payloads.add(0, new String[]{name, text});
			while (types.size() > capacity) {
				types.remove(types.size() - 1);
				payloads.remove(payloads.size() - 1);
			}
			total++;
		}

		@Override
		public int total() {
			return total;
		}

		@Override
		public int capacity() {
			return capacity;
		}

		@Override
		public int type(int index) {
			return types.get(index)[0];
		}

		@Override
		public String name(int index) {
			return payloads.get(index)[0];
		}

		@Override
		public String text(int index) {
			return payloads.get(index)[1];
		}
	}

	private static List<String> texts(ChatCursor.Result result) {
		List<String> out = new ArrayList<String>();
		for (ChatCursor.Entry entry : result.messages) {
			out.add(entry.text);
		}
		return out;
	}

	@Test
	void firstPollReturnsTheBufferedBacklogOldestFirst() {
		FakeChat chat = new FakeChat(5);
		chat.add(0, null, "a");
		chat.add(2, "Bob", "b");
		chat.add(0, null, "c");

		ChatCursor.Result result = new ChatCursor().poll(chat, 0, 10);

		assertEquals(Arrays.asList("a", "b", "c"), texts(result));
		assertEquals(1, result.messages.get(0).seq);
		assertEquals(3, result.messages.get(2).seq);
		assertEquals(3, result.next);
	}

	@Test
	void onlyMessagesAfterSinceAreReturned() {
		FakeChat chat = new FakeChat(10);
		chat.add(0, null, "a");
		chat.add(0, null, "b");
		ChatCursor cursor = new ChatCursor();

		ChatCursor.Result first = cursor.poll(chat, 0, 10);
		assertEquals(2, first.next);

		chat.add(0, null, "c");
		ChatCursor.Result second = cursor.poll(chat, first.next, 10);

		assertEquals(Arrays.asList("c"), texts(second));
		assertEquals(3, second.next);

		ChatCursor.Result third = cursor.poll(chat, second.next, 10);
		assertTrue(third.messages.isEmpty());
		assertEquals(3, third.next);
	}

	@Test
	void wrapsAroundAfterTheBufferIsFull() {
		FakeChat chat = new FakeChat(3);
		for (int i = 0; i < 5; i++) {
			chat.add(0, null, "m" + i);
		}

		ChatCursor.Result result = new ChatCursor().poll(chat, 0, 10);

		// Only the newest three survive the buffer, still oldest first.
		assertEquals(Arrays.asList("m2", "m3", "m4"), texts(result));
		assertEquals(1, result.messages.get(0).seq);
		assertEquals(3, result.messages.get(2).seq);
	}

	@Test
	void pollingNeverMissesOrRepeatsAcrossManyMessages() {
		FakeChat chat = new FakeChat(100);
		ChatCursor cursor = new ChatCursor();
		List<String> seen = new ArrayList<String>();
		int since = 0;

		for (int i = 0; i < 250; i++) {
			chat.add(0, null, "m" + i);
			if (i % 7 == 0) {
				ChatCursor.Result result = cursor.poll(chat, since, 100);
				for (ChatCursor.Entry entry : result.messages) {
					seen.add(entry.text);
				}
				since = result.next;
			}
		}
		ChatCursor.Result tail = cursor.poll(chat, since, 100);
		for (ChatCursor.Entry entry : tail.messages) {
			seen.add(entry.text);
		}

		assertEquals(250, seen.size());
		for (int i = 0; i < 250; i++) {
			assertEquals("m" + i, seen.get(i));
		}
	}

	@Test
	void respectsTheLimitButKeepsNextAhead() {
		FakeChat chat = new FakeChat(50);
		for (int i = 0; i < 10; i++) {
			chat.add(0, null, "m" + i);
		}
		ChatCursor cursor = new ChatCursor();

		ChatCursor.Result result = cursor.poll(chat, 0, 3);

		assertEquals(Arrays.asList("m0", "m1", "m2"), texts(result));
		assertEquals(3, result.next, "next is the last seq returned, so a truncated reply continues");

		ChatCursor.Result rest = cursor.poll(chat, 3, 100);
		assertEquals(Arrays.asList("m3", "m4", "m5", "m6", "m7", "m8", "m9"), texts(rest));
	}

	@Test
	void carriesTypeAndNameAndStripsNothing() {
		FakeChat chat = new FakeChat(10);
		chat.add(2, "Bob", "hello");
		ChatCursor.Result result = new ChatCursor().poll(chat, 0, 10);

		ChatCursor.Entry entry = result.messages.get(0);
		assertEquals(2, entry.type);
		assertEquals("public", entry.typeName);
		assertEquals("Bob", entry.name);
		assertEquals("hello", entry.text);
	}

	@Test
	void resetReReadsTheWholeBuffer() {
		FakeChat chat = new FakeChat(10);
		chat.add(0, null, "a");
		ChatCursor cursor = new ChatCursor();
		cursor.poll(chat, 0, 10);
		chat.add(0, null, "b");
		ChatCursor.Result after = cursor.poll(chat, 0, 10);
		assertEquals(2, after.messages.size());

		cursor.reset();
		ChatCursor.Result reset = cursor.poll(chat, 0, 10);

		assertEquals(Arrays.asList("a", "b"), texts(reset));
		assertEquals(1, reset.messages.get(0).seq);
	}

	@Test
	void handlesAnEmptyBuffer() {
		ChatCursor.Result result = new ChatCursor().poll(new FakeChat(10), 0, 10);
		assertTrue(result.messages.isEmpty());
		assertEquals(0, result.next);
	}
}
