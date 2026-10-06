package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyMapTest {
	@Test
	void namedKeysMapToTheirVirtualKey() {
		assertEquals(KeyEvent.VK_ENTER, KeyMap.namedKey("enter"));
		assertEquals(KeyEvent.VK_ENTER, KeyMap.namedKey("ENTER"));
		assertEquals(KeyEvent.VK_ESCAPE, KeyMap.namedKey("escape"));
		assertEquals(KeyEvent.VK_ESCAPE, KeyMap.namedKey("esc"));
		assertEquals(KeyEvent.VK_BACK_SPACE, KeyMap.namedKey("backspace"));
		assertEquals(KeyEvent.VK_TAB, KeyMap.namedKey("tab"));
		assertEquals(KeyEvent.VK_SPACE, KeyMap.namedKey("space"));
		assertEquals(KeyEvent.VK_UP, KeyMap.namedKey("up"));
		assertEquals(KeyEvent.VK_DOWN, KeyMap.namedKey("down"));
		assertEquals(KeyEvent.VK_LEFT, KeyMap.namedKey("left"));
		assertEquals(KeyEvent.VK_RIGHT, KeyMap.namedKey("right"));
		assertEquals(KeyEvent.VK_SHIFT, KeyMap.namedKey("shift"));
		assertEquals(KeyEvent.VK_CONTROL, KeyMap.namedKey("ctrl"));
	}

	@Test
	void functionKeysAreMapped() {
		assertEquals(KeyEvent.VK_F1, KeyMap.namedKey("f1"));
		assertEquals(KeyEvent.VK_F12, KeyMap.namedKey("f12"));
		assertEquals(KeyEvent.VK_F5, KeyMap.namedKey("F5"));
		assertEquals(-1, KeyMap.namedKey("f13"));
		assertEquals(-1, KeyMap.namedKey("f0"));
	}

	@Test
	void unknownNamedKeysAreRejected() {
		assertEquals(-1, KeyMap.namedKey("banana"));
		assertEquals(-1, KeyMap.namedKey(""));
		assertEquals(-1, KeyMap.namedKey(null));
	}

	@Test
	void singleCharactersFallBackToCharMapping() {
		assertEquals(KeyEvent.VK_A, KeyMap.namedKey("a"));
		assertEquals(KeyEvent.VK_1, KeyMap.namedKey("1"));
		assertEquals(KeyEvent.VK_SPACE, KeyMap.namedKey(" "));
	}

	@Test
	void lettersAndDigitsUseContiguousBlocks() {
		assertEquals(KeyEvent.VK_A, KeyMap.charKey('a'));
		assertEquals(KeyEvent.VK_Z, KeyMap.charKey('z'));
		assertEquals(KeyEvent.VK_A, KeyMap.charKey('A'));
		assertEquals(KeyEvent.VK_Z, KeyMap.charKey('Z'));
		assertEquals(KeyEvent.VK_0, KeyMap.charKey('0'));
		assertEquals(KeyEvent.VK_9, KeyMap.charKey('9'));
	}

	@Test
	void shiftedPunctuationMapsToTheUnshiftedKey() {
		assertEquals(KeyEvent.VK_1, KeyMap.charKey('!'));
		assertEquals(KeyEvent.VK_0, KeyMap.charKey(')'));
		assertEquals(KeyEvent.VK_MINUS, KeyMap.charKey('_'));
		assertEquals(KeyEvent.VK_PERIOD, KeyMap.charKey('>'));
		assertEquals(KeyEvent.VK_SLASH, KeyMap.charKey('/'));
		assertEquals(KeyEvent.VK_SLASH, KeyMap.charKey('?'));
	}

	@Test
	void shiftIsRequiredForUpperAndShiftedChars() {
		assertTrue(KeyMap.needsShift('A'));
		assertTrue(KeyMap.needsShift('!'));
		assertTrue(KeyMap.needsShift('_'));
		assertFalse(KeyMap.needsShift('a'));
		assertFalse(KeyMap.needsShift('1'));
		assertFalse(KeyMap.needsShift(' '));
	}

	@Test
	void unmappableCharactersReturnMinusOne() {
		assertEquals(-1, KeyMap.charKey('\u00e9'));
		assertEquals(-1, KeyMap.charKey('\u20ac'));
	}

	@Test
	void typableRangeMatchesTheClientCharset() {
		assertTrue(KeyMap.isTypable('a'));
		assertTrue(KeyMap.isTypable('\u00ff'));
		assertTrue(KeyMap.isTypable('\u20ac'));
		assertFalse(KeyMap.isTypable('\u0100'));
		assertFalse(KeyMap.isTypable((char) 0));
	}

	@Test
	void euroIsTranslatedToTheClientsByte() {
		assertEquals(128, KeyMap.clientChar('\u20ac'));
		assertEquals('a', KeyMap.clientChar('a'));
	}
}
