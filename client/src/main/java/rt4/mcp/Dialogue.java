package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.Component;
import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;
import rt4.JagString;
import rt4.LocalizedText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * MCP-06/MCP-14 — chatbox dialogue detection and classification.
 *
 * <p>530 mounts dialogue interfaces as sub-interfaces of the chatbox, so a dialogue is open
 * when the top-level interface, or any open sub-interface, is one of the chatbox dialogue
 * ids. {@link #classify} is pure so every kind can be tested against a fake component tree;
 * {@link #current} feeds it the live components.</p>
 */
public final class Dialogue {
	/** NPC chat heads (1–4 lines). */
	public static final Set<Integer> NPC_CHAT = new HashSet<Integer>(Arrays.asList(241, 242, 243, 244));
	/** Player chat heads (1–4 lines). */
	public static final Set<Integer> PLAYER_CHAT = new HashSet<Integer>(Arrays.asList(64, 65, 66, 67));
	/** Option menus (2–5 options). */
	public static final Set<Integer> OPTIONS = new HashSet<Integer>(Arrays.asList(228, 230, 232, 234));
	/** Plain message / "click here to continue". */
	public static final Set<Integer> MESSAGE = new HashSet<Integer>(Arrays.asList(210, 211, 212, 213, 214, 519));

	private static final Set<Integer> ALL = new HashSet<Integer>();

	static {
		ALL.addAll(NPC_CHAT);
		ALL.addAll(PLAYER_CHAT);
		ALL.addAll(OPTIONS);
		ALL.addAll(MESSAGE);
	}

	private Dialogue() {
	}

	/** Every interface id known to be a chatbox dialogue. */
	public static Set<Integer> dialogueInterfaces() {
		return ALL;
	}

	/** The dialogue interface currently mounted in the chatbox, or -1. */
	public static int openInterfaceId() {
		int top = InterfaceList.topLevelInterface;
		if (top != -1 && ALL.contains(top)) {
			return top;
		}
		HashTableIterator iterator = new HashTableIterator(InterfaceList.openInterfaces);
		for (ComponentPointer pointer = (ComponentPointer) iterator.first(); pointer != null; pointer = (ComponentPointer) iterator.next()) {
			if (ALL.contains(pointer.interfaceId)) {
				return pointer.interfaceId;
			}
		}
		return -1;
	}

	/** Whether a chatbox dialogue is on screen. */
	public static boolean isOpen() {
		return openInterfaceId() != -1;
	}

	// ------------------------------------------------------------------ pure classification

	/** The kind of dialogue for an interface id. */
	public static String kindOf(int interfaceId) {
		if (NPC_CHAT.contains(interfaceId)) {
			return "npc";
		}
		if (PLAYER_CHAT.contains(interfaceId)) {
			return "player";
		}
		if (OPTIONS.contains(interfaceId)) {
			return "options";
		}
		if (MESSAGE.contains(interfaceId)) {
			return "message";
		}
		return "dialogue";
	}

	/**
	 * Describes an open dialogue from a flat list of its components. Returns null when
	 * there is nothing to say.
	 */
	public static JsonObject classify(int interfaceId, List<InterfaceWalker.ComponentView> components) {
		JsonObject out = new JsonObject();
		out.addProperty("kind", kindOf(interfaceId));
		out.addProperty("interface", interfaceId);

		boolean optionsMenu = OPTIONS.contains(interfaceId);
		JsonArray lines = new JsonArray();
		JsonArray options = new JsonArray();
		JsonObject continueTarget = null;

		for (InterfaceWalker.ComponentView view : components) {
			if (view == null) {
				continue;
			}
			String text = view.textValue();
			if (text != null) {
				text = text.trim();
			}
			String advanceOp = view.continueOp();

			// In an option menu the choice is the clickable text itself, and the op that
			// fires it is the component's continue action (not its label).
			if (optionsMenu && advanceOp != null && text != null && !text.isEmpty()) {
				addOption(options, text, view.targetId(), advanceOp);
				continue;
			}

			if (text != null && !text.isEmpty()) {
				lines.add(text);
			}
			List<String> optionTexts = view.optionTexts();
			if (optionTexts != null) {
				for (String option : optionTexts) {
					if (option != null && !option.trim().isEmpty()) {
						addOption(options, option.trim(), view.targetId(), option.trim());
					}
				}
			}
			if (continueTarget == null && advanceOp != null && !advanceOp.trim().isEmpty()) {
				continueTarget = new JsonObject();
				continueTarget.add("target", Tools.text(view.targetId()));
				continueTarget.addProperty("op", advanceOp.trim());
			}
		}

		if (lines.size() > 0) {
			out.add("lines", lines);
		}
		if (options.size() > 0) {
			out.add("options", options);
			out.addProperty("kind", "options");
		}
		if (continueTarget != null) {
			out.add("continue", continueTarget);
		}
		return out;
	}

	private static void addOption(JsonArray options, String text, String target, String op) {
		JsonObject entry = new JsonObject();
		entry.addProperty("index", options.size() + 1);
		entry.addProperty("text", text);
		entry.add("target", Tools.text(target));
		entry.addProperty("op", op);
		options.add(entry);
	}

	/** Case-insensitive substring match, or a 1-based index. */
	public static JsonObject findOption(JsonArray options, String text, Integer index) throws ToolException {
		if (options == null || options.size() == 0) {
			throw new ToolException("no option menu is open");
		}
		List<JsonObject> matches = new ArrayList<JsonObject>();
		if (index != null) {
			if (index < 1 || index > options.size()) {
				throw new ToolException("option index " + index + " is out of range; there are "
						+ options.size() + " options: " + optionTexts(options));
			}
			return options.get(index - 1).getAsJsonObject();
		}
		if (text == null || text.trim().isEmpty()) {
			throw new ToolException("give an option text or an index; the options are: " + optionTexts(options));
		}
		String needle = text.trim().toLowerCase(Locale.ROOT);
		for (int i = 0; i < options.size(); i++) {
			JsonObject option = options.get(i).getAsJsonObject();
			String candidate = option.get("text").getAsString().toLowerCase(Locale.ROOT);
			if (candidate.contains(needle)) {
				matches.add(option);
			}
		}
		if (matches.isEmpty()) {
			throw new ToolException("no option matches '" + text + "'; the options are: " + optionTexts(options));
		}
		if (matches.size() > 1) {
			throw new ToolException("'" + text + "' matches " + matches.size() + " options: " + optionTexts(options)
					+ "; pass an index instead");
		}
		return matches.get(0);
	}

	public static String optionTexts(JsonArray options) {
		List<String> texts = new ArrayList<String>();
		if (options != null) {
			for (int i = 0; i < options.size(); i++) {
				texts.add((i + 1) + "=" + options.get(i).getAsJsonObject().get("text").getAsString());
			}
		}
		return texts.toString();
	}

	// ------------------------------------------------------------------ live reading

	/** The open dialogue, or null. Game thread only. */
	public static JsonObject current() {
		int interfaceId = openInterfaceId();
		if (interfaceId == -1) {
			return null;
		}
		return classify(interfaceId, views(interfaceId));
	}

	private static List<InterfaceWalker.ComponentView> views(int interfaceId) {
		List<InterfaceWalker.ComponentView> views = new ArrayList<InterfaceWalker.ComponentView>();
		if (InterfaceList.components == null || interfaceId < 0 || interfaceId >= InterfaceList.components.length) {
			return views;
		}
		collectViews(InterfaceList.components[interfaceId], -1, views, 1);
		return views;
	}

	/**
	 * Flattens the component tree. The interface's array is flat, so children are the
	 * entries whose {@code overlayer} is the parent's id; roots have {@code overlayer == -1}.
	 */
	private static void collectViews(Component[] all, int parentId, List<InterfaceWalker.ComponentView> out, int depth) {
		if (all == null || depth > 12) {
			return;
		}
		for (Component child : all) {
			if (child == null || child.overlayer != parentId) {
				continue;
			}
			out.add(new LiveView(child));
			collectViews(all, child.id, out, depth + 1);
			if (child.createdComponents != null) {
				for (Component created : child.createdComponents) {
					if (created != null) {
						out.add(new LiveView(created));
					}
				}
			}
		}
	}

	/** Adapts a live {@link Component} to the walker view the classifier reads. */
	private static final class LiveView implements InterfaceWalker.ComponentView {
		private final Component component;

		LiveView(Component component) {
			this.component = component;
		}

		@Override
		public boolean hidden() {
			return InterfaceList.isHidden(component);
		}

		@Override
		public boolean inventory() {
			return component.type == 2;
		}

		@Override
		public boolean hasText() {
			String text = textValue();
			return text != null && !text.isEmpty();
		}

		@Override
		public boolean hasOps() {
			return optionTexts().size() > 0;
		}

		@Override
		public int buttonType() {
			return component.buttonType;
		}

		@Override
		public boolean hasItem() {
			return component.objId != -1;
		}

		@Override
		public int itemCount() {
			if (component.objTypes == null) {
				return 0;
			}
			int count = 0;
			for (int id : component.objTypes) {
				if (id > 0) {
					count++;
				}
			}
			return count;
		}

		@Override
		public List<InterfaceWalker.ComponentView> children() {
			return java.util.Collections.emptyList();
		}

		@Override
		public String targetId() {
			return Targets.component(component.id >>> 16, component.id & 0xFFFF);
		}

		@Override
		public String textValue() {
			return Names.plain(component.text);
		}

		@Override
		public List<String> optionTexts() {
			List<String> options = new ArrayList<String>();
			if (component.if3) {
				for (int i = 0; i < 5; i++) {
					JagString op = InterfaceList.getOp(component, i);
					if (op != null) {
						String text = Names.plain(op);
						if (text != null && !text.isEmpty()) {
							options.add(text);
						}
					}
				}
			}
			return options;
		}

		@Override
		public String continueOp() {
			if (component.buttonType == 6) {
				return Names.plain(component.option);
			}
			if (InterfaceList.getServerActiveProperties(component).isResumePauseButtonEnabled()) {
				return Names.plain(LocalizedText.CONTINUE);
			}
			return null;
		}
	}
}
