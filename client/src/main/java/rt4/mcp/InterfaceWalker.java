package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.Component;
import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;
import rt4.JagString;
import rt4.ObjType;
import rt4.ObjTypeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MCP-09 — reads the open interfaces and works out the geometry of inventory slots.
 *
 * <p>The filtering rules ({@link ComponentView}, {@link #filter}, {@link #isActionable}) and
 * the slot geometry are pure so they can be unit-tested against a fake component tree. The
 * traversal of the live {@code Component}s is in {@link #describe}.</p>
 */
public final class InterfaceWalker {
	public static final int DEFAULT_MAX_DEPTH = 6;
	public static final int MAX_DEPTH = 12;

	/** The slice of a component the walker needs, so tests can build a fake tree. */
	public interface ComponentView {
		boolean hidden();

		boolean inventory();

		boolean hasText();

		boolean hasOps();

		int buttonType();

		/** A single-item component ({@code objId != -1}). */
		boolean hasItem();

		/** How many non-empty inventory slots this component has; 0 when not an inventory. */
		int itemCount();

		List<ComponentView> children();

		/** The component's target id, e.g. {@code if:241:1}. */
		default String targetId() {
			return null;
		}

		/** The readable text on this component, tags stripped. */
		default String textValue() {
			return null;
		}

		/** Option ops a player can pick (dialogue options, {@code if3} buttons). 1-based order. */
		default List<String> optionTexts() {
			return Collections.emptyList();
		}

		/** The op that advances the dialogue, or null when this is not a continue button. */
		default String continueOp() {
			return null;
		}
	}

	/** One inventory slot. */
	public static final class Slot {
		public final int slot;
		public final int id;
		public final int count;
		public final List<String> ops;

		public Slot(int slot, int id, int count, List<String> ops) {
			this.slot = slot;
			this.id = id;
			this.count = count;
			this.ops = ops == null ? Collections.<String>emptyList() : ops;
		}
	}

	private InterfaceWalker() {
	}

	// ------------------------------------------------------------------ pure filtering

	/**
	 * Whether a component is worth reporting: some text, ops, a button, an item, or an
	 * inventory that actually holds something.
	 */
	public static boolean isActionable(ComponentView view) {
		return view.hasText() || view.hasOps() || view.buttonType() != 0 || view.hasItem()
				|| (view.inventory() && view.itemCount() > 0);
	}

	/**
	 * Keeps every actionable component reachable within {@code maxDepth}. A hidden component
	 * hides its whole subtree unless {@code includeHidden}; the root list counts as depth 1.
	 */
	public static List<ComponentView> filter(List<ComponentView> roots, boolean includeHidden, int maxDepth) {
		List<ComponentView> kept = new ArrayList<ComponentView>();
		collect(roots, includeHidden, 1, Math.max(1, maxDepth), kept);
		return kept;
	}

	private static void collect(List<ComponentView> nodes, boolean includeHidden, int depth, int maxDepth,
								List<ComponentView> kept) {
		if (nodes == null || depth > maxDepth) {
			return;
		}
		for (ComponentView node : nodes) {
			if (node == null) {
				continue;
			}
			if (!includeHidden && node.hidden()) {
				continue;
			}
			if (isActionable(node)) {
				kept.add(node);
			}
			collect(node.children(), includeHidden, depth + 1, maxDepth, kept);
		}
	}

	/** Every non-empty slot, in slot order. {@code objTypes} stores {@code id + 1}. */
	public static List<Slot> extractSlots(int[] objTypes, int[] objCounts, int slotCount) {
		List<Slot> slots = new ArrayList<Slot>();
		if (objTypes == null) {
			return slots;
		}
		int limit = Math.min(Math.min(objTypes.length, slotCount), objCounts == null ? objTypes.length : objCounts.length);
		for (int slot = 0; slot < limit; slot++) {
			if (objTypes[slot] <= 0) {
				continue;
			}
			int count = objCounts == null ? 1 : objCounts[slot];
			slots.add(new Slot(slot, objTypes[slot] - 1, count, null));
		}
		return slots;
	}

	// ------------------------------------------------------------------ slot geometry

	public static int slotColumn(int slot, int width) {
		return width <= 0 ? 0 : slot % width;
	}

	public static int slotRow(int slot, int width) {
		return width <= 0 ? 0 : slot / width;
	}

	/** Component-relative x of a slot's 32x32 cell centre. */
	public static int slotCentreX(int slot, int width, int marginX, int offsetX) {
		return (marginX + 32) * slotColumn(slot, width) + offsetX + 16;
	}

	/** Component-relative y of a slot's 32x32 cell centre. */
	public static int slotCentreY(int slot, int width, int marginY, int offsetY) {
		return (marginY + 32) * slotRow(slot, width) + offsetY + 16;
	}

	/** The mouse position {@code addComponentEntries} needs to pick this slot, relative to the component. */
	public static int[] slotCentre(Component component, int slot) {
		int width = Math.max(1, component.baseWidth);
		int offsetX = 0;
		int offsetY = 0;
		if (slot < 20 && component.invOffsetX != null && component.invOffsetY != null) {
			offsetX = component.invOffsetX[slot];
			offsetY = component.invOffsetY[slot];
		}
		return new int[]{
				slotCentreX(slot, width, component.invMarginX, offsetX),
				slotCentreY(slot, width, component.invMarginY, offsetY)
		};
	}

	public static int clampDepth(int maxDepth) {
		if (maxDepth <= 0) {
			return DEFAULT_MAX_DEPTH;
		}
		return Math.min(maxDepth, MAX_DEPTH);
	}

	// ------------------------------------------------------------------ live traversal

	/**
	 * Describes the open interfaces. {@code only} restricts the dump to one interface id;
	 * null walks the top-level interface plus every open sub-interface.
	 */
	public static JsonArray describe(Integer only, boolean includeHidden, int maxDepth) {
		int depth = clampDepth(maxDepth);
		JsonArray out = new JsonArray();

		if (only != null) {
			JsonObject entry = describeInterface(only.intValue(), null, includeHidden, depth);
			if (entry != null) {
				out.add(entry);
			}
			return out;
		}

		int top = InterfaceList.topLevelInterface;
		if (top != -1) {
			JsonObject entry = describeInterface(top, null, includeHidden, depth);
			if (entry != null) {
				out.add(entry);
			}
		}

		if (InterfaceList.openInterfaces != null) {
			HashTableIterator iterator = new HashTableIterator(InterfaceList.openInterfaces);
			for (ComponentPointer pointer = (ComponentPointer) iterator.first(); pointer != null; pointer = (ComponentPointer) iterator.next()) {
				long key = pointer.key;
				String parent = Targets.component((int) (key >>> 16) & 0xFFFF, (int) key & 0xFFFF);
				JsonObject entry = describeInterface(pointer.interfaceId, parent, includeHidden, depth);
				if (entry != null) {
					out.add(entry);
				}
			}
		}
		return out;
	}

	private static JsonObject describeInterface(int interfaceId, String parent, boolean includeHidden, int maxDepth) {
		if (InterfaceList.components == null || interfaceId < 0 || interfaceId >= InterfaceList.components.length) {
			return null;
		}
		Component[] children = InterfaceList.components[interfaceId];
		if (children == null) {
			return null;
		}

		JsonArray components = new JsonArray();
		collectComponents(children, -1, includeHidden, maxDepth, 1, components);

		JsonObject entry = new JsonObject();
		entry.addProperty("id", interfaceId);
		if (parent != null) {
			entry.addProperty("parent", parent);
		}
		entry.add("components", components);
		return entry;
	}

	/**
	 * Walks the component tree. An interface's component array is flat, so a component's
	 * children are the entries whose {@code overlayer} is that component's id; roots have
	 * {@code overlayer == -1}.
	 */
	private static void collectComponents(Component[] all, int parentId, boolean includeHidden, int maxDepth,
										  int depth, JsonArray out) {
		if (all == null || depth > maxDepth) {
			return;
		}
		for (Component child : all) {
			if (child == null || child.overlayer != parentId) {
				continue;
			}
			if (!includeHidden && InterfaceList.isHidden(child)) {
				continue;
			}
			JsonObject json = describeComponent(child, includeHidden, maxDepth, depth);
			if (json != null) {
				out.add(json);
			}
			collectComponents(all, child.id, includeHidden, maxDepth, depth + 1, out);
		}
	}

	private static JsonObject describeComponent(Component component, boolean includeHidden, int maxDepth, int depth) {
		JsonObject json = new JsonObject();
		json.addProperty("target", Targets.component(component.id >>> 16, component.id & 0xFFFF));
		json.addProperty("type", component.type);

		String text = Names.plain(component.text);
		boolean hasText = text != null && !text.isEmpty();
		if (hasText) {
			json.addProperty("text", text);
		}

		JsonArray ops = new JsonArray();
		addOps(ops, component.ops);
		if (component.type == 2) {
			addOps(ops, component.invOptions);
		}
		if (component.if3) {
			for (int i = 0; i < 10; i++) {
				String op = Names.plain(InterfaceList.getOp(component, i));
				if (op != null && !op.isEmpty()) {
					ops.add(op);
				}
			}
		}
		if (ops.size() > 0) {
			json.add("ops", ops);
		}

		String button = buttonName(component);
		if (button != null) {
			json.addProperty("button", button);
		}

		if (component.objId != -1) {
			ObjType type = ObjTypeList.get(component.objId);
			JsonObject obj = new JsonObject();
			obj.addProperty("id", component.objId);
			obj.addProperty("name", type == null ? null : Names.plain(type.name));
			json.add("obj", obj);
		}

		if (component.type == 2) {
			JsonArray slots = describeSlots(component);
			if (slots.size() > 0) {
				json.add("slots", slots);
			}
		}

		// CS2-created children appear under their parent, with the child index in the target.
		if (component.createdComponents != null && depth < maxDepth) {
			JsonArray created = new JsonArray();
			for (int i = 0; i < component.createdComponents.length; i++) {
				Component child = component.createdComponents[i];
				if (child == null) {
					continue;
				}
				if (!includeHidden && InterfaceList.isHidden(child)) {
					continue;
				}
				JsonObject childJson = describeComponent(child, includeHidden, maxDepth, depth + 1);
				if (childJson != null) {
					childJson.addProperty("created_index", i);
					created.add(childJson);
				}
			}
			if (created.size() > 0) {
				json.add("children", created);
			}
		}

		boolean useful = json.has("text") || json.has("ops") || json.has("button") || json.has("obj")
				|| json.has("slots") || json.has("children");
		return useful ? json : null;
	}

	private static JsonArray describeSlots(Component component) {
		JsonArray slots = new JsonArray();
		int slotCount = Math.max(0, component.baseWidth * component.baseHeight);
		if (component.objTypes == null) {
			return slots;
		}
		int limit = Math.min(component.objTypes.length, slotCount);
		for (int slot = 0; slot < limit; slot++) {
			int rawId = component.objTypes[slot];
			if (rawId <= 0) {
				continue;
			}
			int id = rawId - 1;
			ObjType type = ObjTypeList.get(id);

			JsonObject entry = new JsonObject();
			entry.addProperty("slot", slot);
			entry.addProperty("target", Targets.slot(component.id >>> 16, component.id & 0xFFFF, slot));
			entry.addProperty("id", id);
			entry.addProperty("name", type == null ? null : Names.plain(type.name));
			entry.addProperty("count", component.objCounts == null ? 1 : component.objCounts[slot]);

			JsonArray ops = new JsonArray();
			addOps(ops, type == null ? null : type.iops);
			addOps(ops, component.invOptions);
			if (ops.size() > 0) {
				entry.add("ops", ops);
			}
			slots.add(entry);
		}
		return slots;
	}

	private static void addOps(JsonArray out, JagString[] ops) {
		if (ops == null) {
			return;
		}
		for (JagString op : ops) {
			if (op == null) {
				continue;
			}
			String text = Names.plain(op);
			if (text != null && !text.isEmpty()) {
				out.add(text);
			}
		}
	}

	/** The button meaning {@code MiniMenu.addComponentEntries} would emit. */
	public static String buttonName(Component component) {
		switch (component.buttonType) {
			case 1:
				return "ok";
			case 2:
				return "select";
			case 3:
				return "close";
			case 4:
				return "toggle";
			case 5:
				return "logout";
			case 6:
				return "continue";
			default:
				break;
		}
		if (InterfaceList.getServerActiveProperties(component).isResumePauseButtonEnabled()) {
			return "continue";
		}
		return null;
	}
}
