package rt4.mcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/** A settable {@link Conditions.GameView} shared by the MCP-12 tests. */
class FakeGameView implements Conditions.GameView {
	boolean loggedIn = true;
	boolean loggedOut = false;
	boolean idle = true;
	boolean dialogueOpen = false;
	int tick = 0;
	int frame = 0;
	int x = 3222;
	int y = 3218;
	int plane = 0;
	int inventoryHash = 1;
	int hp = 10;
	int animation = -1;
	int chatSize = 0;

	final Map<Integer, Boolean> interfaces = new HashMap<Integer, Boolean>();
	final Map<Integer, Integer> itemCounts = new HashMap<Integer, Integer>();
	final Map<Integer, Integer> xp = new HashMap<Integer, Integer>();
	final Map<String, Boolean> alive = new HashMap<String, Boolean>();
	final Map<String, Integer> types = new HashMap<String, Integer>();
	final Map<Integer, Boolean> navDone = new HashMap<Integer, Boolean>();

	final List<Integer> chatTypesList = new ArrayList<Integer>();
	final List<String> chatTexts = new ArrayList<String>();

	void addChat(int type, String text) {
		chatTypesList.add(0, type);
		chatTexts.add(0, text);
		chatSize++;
	}

	@Override
	public boolean loggedIn() {
		return loggedIn;
	}

	@Override
	public boolean loggedOut() {
		return loggedOut;
	}

	@Override
	public int tick() {
		return tick;
	}

	@Override
	public int frame() {
		return frame;
	}

	@Override
	public boolean idle() {
		return idle;
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public int plane() {
		return plane;
	}

	@Override
	public boolean dialogueOpen() {
		return dialogueOpen;
	}

	@Override
	public boolean interfaceOpen(int interfaceId) {
		Boolean open = interfaces.get(interfaceId);
		return open != null && open;
	}

	@Override
	public int inventoryHash() {
		return inventoryHash;
	}

	@Override
	public int itemCount(int itemId) {
		Integer count = itemCounts.get(itemId);
		return count == null ? 0 : count;
	}

	@Override
	public int totalXp(int skill) {
		if (skill >= 0) {
			Integer value = xp.get(skill);
			return value == null ? 0 : value;
		}
		int total = 0;
		for (int value : xp.values()) {
			total += value;
		}
		return total;
	}

	@Override
	public int hp() {
		return hp;
	}

	@Override
	public boolean entityAlive(String target, int expectedType) {
		Boolean value = alive.get(target);
		if (value != null && !value) {
			return false;
		}
		if (expectedType >= 0) {
			Integer type = types.get(target);
			return type != null && type == expectedType;
		}
		return value == null || value;
	}

	@Override
	public int entityType(String target) {
		Integer type = types.get(target);
		return type == null ? -1 : type;
	}

	@Override
	public boolean navFinished(int task) {
		Boolean done = navDone.get(task);
		return done != null && done;
	}

	@Override
	public int animation() {
		return animation;
	}

	@Override
	public boolean chatMatches(int sinceSize, Pattern regex, Set<Integer> types) {
		int added = Math.min(chatSize - sinceSize, chatTexts.size());
		for (int i = 0; i < added; i++) {
			if (types != null && !types.isEmpty() && !types.contains(chatTypesList.get(i))) {
				continue;
			}
			String text = chatTexts.get(i);
			if (regex != null && text != null && regex.matcher(text).find()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int chatSizeHint() {
		return chatSize;
	}
}
