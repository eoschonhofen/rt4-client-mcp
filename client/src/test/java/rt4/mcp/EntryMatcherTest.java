package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntryMatcherTest {
	private static EntryMatcher.Entry entry(int index, String op, String subject) {
		return new EntryMatcher.Entry(index, op, subject, 0);
	}

	private static List<EntryMatcher.Entry> menu() {
		return Arrays.asList(
				entry(0, "Examine", "Door"),
				entry(1, "Open", "Door"),
				entry(2, "Close", "Door")
		);
	}

	@Test
	void matchesOpCaseInsensitively() throws Exception {
		EntryMatcher.Entry match = EntryMatcher.match(menu(), "open", null, "loc:1@2,3,0");
		assertEquals(1, match.index);
		assertEquals("Open", match.op);
	}

	@Test
	void matchesSubjectWhenGiven() throws Exception {
		EntryMatcher.Entry match = EntryMatcher.match(menu(), "Open", "Door", "loc:1@2,3,0");
		assertEquals(1, match.index);
	}

	@Test
	void subjectMismatchIsNoMatch() {
		ToolException error = assertThrows(ToolException.class,
				() -> EntryMatcher.match(menu(), "Open", "Gate", "loc:1@2,3,0"));
		assertTrue(error.getMessage().contains("no op 'Open'"), error.getMessage());
		assertTrue(error.getMessage().contains("loc:1@2,3,0"), error.getMessage());
		assertTrue(error.getMessage().contains("[Examine, Open, Close]"), error.getMessage());
	}

	@Test
	void unknownOpListsWhatWasAvailable() {
		ToolException error = assertThrows(ToolException.class,
				() -> EntryMatcher.match(menu(), "Bank", null, "npc:7"));
		assertTrue(error.getMessage().contains("no op 'Bank' on npc:7"), error.getMessage());
		assertTrue(error.getMessage().contains("available: [Examine, Open, Close]"), error.getMessage());
	}

	@Test
	void emptyMenuIsReported() {
		ToolException error = assertThrows(ToolException.class,
				() -> EntryMatcher.match(Collections.<EntryMatcher.Entry>emptyList(), "Open", null, "npc:7"));
		assertTrue(error.getMessage().contains("available: []"), error.getMessage());
	}

	@Test
	void blankOpIsRejected() {
		assertThrows(ToolException.class, () -> EntryMatcher.match(menu(), "  ", null, "npc:7"));
		assertThrows(ToolException.class, () -> EntryMatcher.match(menu(), null, null, "npc:7"));
	}

	@Test
	void firstMatchWinsForDuplicatedOps() throws Exception {
		List<EntryMatcher.Entry> stacked = Arrays.asList(
				entry(0, "Take", "Coins"),
				entry(1, "Take", "Bones")
		);
		assertEquals("Coins", EntryMatcher.match(stacked, "Take", null, "tile:1,2").subject);
		assertEquals("Bones", EntryMatcher.match(stacked, "Take", "bones", "tile:1,2").subject);
	}

	@Test
	void matchingTrimsWhitespace() throws Exception {
		assertEquals(1, EntryMatcher.match(menu(), "  Open ", " Door ", "loc:1@2,3,0").index);
	}

	@Test
	void matchesRejectsNullEntriesAndOps() {
		assertFalse(EntryMatcher.matches(null, "Open", null));
		assertFalse(EntryMatcher.matches(entry(0, null, null), "Open", null));
	}

	@Test
	void availableDeduplicatesPreservingOrder() {
		List<EntryMatcher.Entry> entries = Arrays.asList(
				entry(0, "Attack", "Goblin"),
				entry(1, "Attack", "Rat"),
				entry(2, "Examine", "Goblin")
		);
		assertEquals("[Attack, Examine]", EntryMatcher.available(entries));
	}

	@Test
	void hasOpIgnoresCase() {
		assertTrue(EntryMatcher.hasOp(menu(), "open"));
		assertTrue(EntryMatcher.hasOp(menu(), "EXAMINE"));
		assertFalse(EntryMatcher.hasOp(menu(), "Bank"));
		assertFalse(EntryMatcher.hasOp(menu(), null));
	}
}
