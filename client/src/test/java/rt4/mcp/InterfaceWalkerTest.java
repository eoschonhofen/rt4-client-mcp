package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterfaceWalkerTest {
	/** A fake component with only the fields the filter looks at. */
	private static final class Fake implements InterfaceWalker.ComponentView {
		final String label;
		boolean hidden;
		boolean inventory;
		boolean text;
		boolean ops;
		int buttonType;
		boolean item;
		int itemCount;
		final List<InterfaceWalker.ComponentView> children = new ArrayList<InterfaceWalker.ComponentView>();

		Fake(String label) {
			this.label = label;
		}

		Fake withText() {
			text = true;
			return this;
		}

		Fake withOps() {
			ops = true;
			return this;
		}

		Fake withButton(int type) {
			buttonType = type;
			return this;
		}

		Fake withInventory(int items) {
			inventory = true;
			itemCount = items;
			return this;
		}

		Fake hide() {
			hidden = true;
			return this;
		}

		Fake child(Fake child) {
			children.add(child);
			return this;
		}

		@Override
		public boolean hidden() {
			return hidden;
		}

		@Override
		public boolean inventory() {
			return inventory;
		}

		@Override
		public boolean hasText() {
			return text;
		}

		@Override
		public boolean hasOps() {
			return ops;
		}

		@Override
		public int buttonType() {
			return buttonType;
		}

		@Override
		public boolean hasItem() {
			return item;
		}

		@Override
		public int itemCount() {
			return itemCount;
		}

		@Override
		public List<InterfaceWalker.ComponentView> children() {
			return children;
		}
	}

	private static List<String> labels(List<InterfaceWalker.ComponentView> views) {
		List<String> out = new ArrayList<String>();
		for (InterfaceWalker.ComponentView view : views) {
			out.add(((Fake) view).label);
		}
		return out;
	}

	@Test
	void actionableFilterKeepsOnlyUsefulComponents() {
		Fake plain = new Fake("plain");
		Fake withText = new Fake("text").withText();
		Fake withOps = new Fake("ops").withOps();
		Fake withButton = new Fake("button").withButton(1);
		Fake emptyInventory = new Fake("empty-inv").withInventory(0);
		Fake filledInventory = new Fake("filled-inv").withInventory(3);

		List<InterfaceWalker.ComponentView> kept = InterfaceWalker.filter(
				Arrays.<InterfaceWalker.ComponentView>asList(plain, withText, withOps, withButton, emptyInventory, filledInventory),
				false, 6);

		assertEquals(Arrays.asList("text", "ops", "button", "filled-inv"), labels(kept));
	}

	@Test
	void hiddenComponentsHideTheirWholeSubtree() {
		Fake visible = new Fake("visible").withText();
		Fake hidden = new Fake("hidden").withText().hide();
		hidden.child(new Fake("hidden-child").withText());
		visible.child(new Fake("visible-child").withText());

		List<InterfaceWalker.ComponentView> kept = InterfaceWalker.filter(
				Arrays.<InterfaceWalker.ComponentView>asList(visible, hidden), false, 6);

		assertEquals(Arrays.asList("visible", "visible-child"), labels(kept));
	}

	@Test
	void includeHiddenKeepsTheSubtree() {
		Fake hidden = new Fake("hidden").withText().hide();
		hidden.child(new Fake("hidden-child").withText());

		List<InterfaceWalker.ComponentView> kept = InterfaceWalker.filter(
				Collections.<InterfaceWalker.ComponentView>singletonList(hidden), true, 6);

		assertEquals(Arrays.asList("hidden", "hidden-child"), labels(kept));
	}

	@Test
	void hiddenChildIsSkippedUnderAVisibleParent() {
		Fake visible = new Fake("visible").withText();
		visible.child(new Fake("hidden-child").withText().hide());
		visible.child(new Fake("shown-child").withText());

		assertEquals(Arrays.asList("visible", "shown-child"),
				labels(InterfaceWalker.filter(Collections.<InterfaceWalker.ComponentView>singletonList(visible), false, 6)));
	}

	@Test
	void depthCapStopsTheWalk() {
		Fake root = new Fake("d1").withText();
		Fake d2 = new Fake("d2").withText();
		Fake d3 = new Fake("d3").withText();
		Fake d4 = new Fake("d4").withText();
		root.child(d2);
		d2.child(d3);
		d3.child(d4);

		assertEquals(Arrays.asList("d1", "d2", "d3"),
				labels(InterfaceWalker.filter(Collections.<InterfaceWalker.ComponentView>singletonList(root), false, 3)));
	}

	@Test
	void nullChildrenAreTolerated() {
		Fake root = new Fake("root").withText();
		root.children.add(null);
		assertEquals(Arrays.asList("root"),
				labels(InterfaceWalker.filter(Collections.<InterfaceWalker.ComponentView>singletonList(root), false, 6)));
	}

	@Test
	void inventorySlotsSkipEmptyOnesAndConvertTheId() {
		int[] objTypes = {0, 0, 995 + 1, 0, 0};
		int[] objCounts = {0, 0, 42, 0, 7};

		List<InterfaceWalker.Slot> slots = InterfaceWalker.extractSlots(objTypes, objCounts, 5);

		assertEquals(1, slots.size());
		assertEquals(2, slots.get(0).slot);
		assertEquals(995, slots.get(0).id);
		assertEquals(42, slots.get(0).count);
	}

	@Test
	void inventorySlotsRespectTheSlotCount() {
		int[] objTypes = {1, 2, 3, 4};
		int[] objCounts = {1, 1, 1, 1};

		assertEquals(2, InterfaceWalker.extractSlots(objTypes, objCounts, 2).size());
	}

	@Test
	void nullInventoryArraysAreHandled() {
		assertTrue(InterfaceWalker.extractSlots(null, null, 10).isEmpty());
	}

	@Test
	void slotColumnAndRowFollowTheGridWidth() {
		assertEquals(0, InterfaceWalker.slotColumn(0, 4));
		assertEquals(3, InterfaceWalker.slotColumn(3, 4));
		assertEquals(0, InterfaceWalker.slotColumn(4, 4));
		assertEquals(1, InterfaceWalker.slotRow(5, 4));
		assertEquals(2, InterfaceWalker.slotRow(8, 4));
	}

	@Test
	void slotCentreIsTheCellMiddle() {
		// 32px cells, no margin, no per-slot offset.
		assertEquals(16, InterfaceWalker.slotCentreX(0, 4, 0, 0));
		assertEquals(16, InterfaceWalker.slotCentreY(0, 4, 0, 0));
		assertEquals(48, InterfaceWalker.slotCentreX(1, 4, 0, 0));
		assertEquals(48, InterfaceWalker.slotCentreY(4, 4, 0, 0));
	}

	@Test
	void slotCentreIncludesMarginAndOffset() {
		assertEquals(16 + 36, InterfaceWalker.slotCentreX(1, 4, 4, 0));
		assertEquals(16 + 40, InterfaceWalker.slotCentreY(4, 4, 8, 0));
		assertEquals(17, InterfaceWalker.slotCentreX(0, 4, 0, 1));
		assertEquals(20, InterfaceWalker.slotCentreY(0, 4, 0, 4));
	}

	@Test
	void depthIsClamped() {
		assertEquals(InterfaceWalker.DEFAULT_MAX_DEPTH, InterfaceWalker.clampDepth(0));
		assertEquals(InterfaceWalker.DEFAULT_MAX_DEPTH, InterfaceWalker.clampDepth(-3));
		assertEquals(4, InterfaceWalker.clampDepth(4));
		assertEquals(InterfaceWalker.MAX_DEPTH, InterfaceWalker.clampDepth(999));
	}

	@Test
	void isActionableMatchesTheFilter() {
		assertFalse(InterfaceWalker.isActionable(new Fake("plain")));
		assertTrue(InterfaceWalker.isActionable(new Fake("text").withText()));
		assertTrue(InterfaceWalker.isActionable(new Fake("inv").withInventory(1)));
		assertFalse(InterfaceWalker.isActionable(new Fake("inv").withInventory(0)));
	}
}
