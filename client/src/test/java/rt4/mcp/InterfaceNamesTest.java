package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterfaceNamesTest {
	@Test
	void theTableHoldsThePanelsAnAgentMeets() {
		assertEquals("MAGIC", InterfaceNames.of(192));
		assertEquals("PRAYER", InterfaceNames.of(271));
		assertEquals("SMITHING_NEW", InterfaceNames.of(300));
		assertEquals("INVENTORY", InterfaceNames.of(149));
		assertEquals("WORNITEMS", InterfaceNames.of(387));
		assertEquals("TOPLEVEL", InterfaceNames.of(548));
		assertTrue(InterfaceNames.size() > 400, "the generated table should be the whole cache");
	}

	@Test
	void anIdWithNoNameGetsNoneInvented() {
		assertNull(InterfaceNames.of(65535));
		assertNull(InterfaceNames.of(-1));
	}

	@Test
	void commentsBlanksAndRubbishAreSkipped() {
		Map<Integer, String> into = new HashMap<Integer, String>();
		InterfaceNames.parse("# id\tname", into);
		InterfaceNames.parse("", into);
		InterfaceNames.parse("   ", into);
		InterfaceNames.parse(null, into);
		InterfaceNames.parse("no-tab-here", into);
		InterfaceNames.parse("notanumber\tNAME", into);
		InterfaceNames.parse("7\t", into);
		assertTrue(into.isEmpty(), into.toString());

		InterfaceNames.parse("7\tSOME_PANEL", into);
		assertEquals("SOME_PANEL", into.get(Integer.valueOf(7)));
	}
}
