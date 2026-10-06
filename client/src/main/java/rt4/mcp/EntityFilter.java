package rt4.mcp;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * MCP-07 — the filtering, distance sorting and limiting behind {@code find_entities}.
 *
 * <p>Pure: it works on plain {@link Entity} records that {@link SceneScan} produces on the
 * game thread, so the matching rules are unit-tested directly.</p>
 */
public final class EntityFilter {
	public static final int DEFAULT_RADIUS = 15;
	public static final int MAX_RADIUS = 52;
	public static final int DEFAULT_LIMIT = 20;
	public static final int MAX_LIMIT = 100;

	/** One discoverable thing, with everything the tool reports. */
	public static final class Entity {
		public final String target;
		public final String type;
		public final int id;
		public final String name;
		public final int x;
		public final int y;
		public final int plane;
		public final int distance;
		public final List<String> ops;
		public final JsonObject extra;

		public Entity(String target, String type, int id, String name, int x, int y, int plane, int distance,
					  List<String> ops, JsonObject extra) {
			this.target = target;
			this.type = type;
			this.id = id;
			this.name = name;
			this.x = x;
			this.y = y;
			this.plane = plane;
			this.distance = distance;
			this.ops = ops == null ? Collections.<String>emptyList() : ops;
			this.extra = extra;
		}

		public boolean hasOp(String op) {
			for (String candidate : ops) {
				if (candidate != null && candidate.equalsIgnoreCase(op)) {
					return true;
				}
			}
			return false;
		}

		/** Short keys; null fields are dropped to keep the agent's payload small. */
		public JsonObject toJson() {
			JsonObject out = new JsonObject();
			out.addProperty("target", target);
			out.addProperty("type", type);
			out.addProperty("id", id);
			out.addProperty("name", name);
			out.addProperty("x", x);
			out.addProperty("y", y);
			out.addProperty("plane", plane);
			out.addProperty("distance", distance);
			if (!ops.isEmpty()) {
				JsonArray array = new JsonArray();
				for (String op : ops) {
					array.add(op);
				}
				out.add("ops", array);
			}
			if (extra != null && extra.entrySet().size() > 0) {
				out.add("extra", extra);
			}
			return out;
		}
	}

	private EntityFilter() {
	}

	public static int clampRadius(int radius) {
		if (radius < 0) {
			return DEFAULT_RADIUS;
		}
		return Math.min(radius, MAX_RADIUS);
	}

	public static int clampLimit(int limit) {
		if (limit <= 0) {
			return DEFAULT_LIMIT;
		}
		return Math.min(limit, MAX_LIMIT);
	}

	/** Case-insensitive name match. */
	public static boolean nameMatches(String name, String wanted, boolean exact) {
		if (wanted == null || wanted.isEmpty()) {
			return true;
		}
		if (name == null) {
			return false;
		}
		String haystack = name.toLowerCase(Locale.ROOT);
		String needle = wanted.toLowerCase(Locale.ROOT);
		return exact ? haystack.equals(needle) : haystack.contains(needle);
	}

	/**
	 * Applies the type, name, radius and {@code has_op} filters, sorts by distance
	 * (nearest first, original order kept for ties) and truncates to {@code limit}.
	 */
	public static List<Entity> select(List<Entity> candidates, String type, String name, String match,
									  int radius, String hasOp, int limit) {
		boolean exact = "exact".equalsIgnoreCase(match);
		boolean anyType = type == null || type.isEmpty() || "any".equalsIgnoreCase(type);
		int maxDistance = clampRadius(radius);
		int maxResults = clampLimit(limit);

		List<Entity> matches = new ArrayList<Entity>();
		for (Entity entity : candidates) {
			if (!anyType && !entity.type.equalsIgnoreCase(type)) {
				continue;
			}
			if (entity.distance > maxDistance) {
				continue;
			}
			if (!nameMatches(entity.name, name, exact)) {
				continue;
			}
			if (hasOp != null && !hasOp.isEmpty() && !entity.hasOp(hasOp)) {
				continue;
			}
			matches.add(entity);
		}

		Collections.sort(matches, new Comparator<Entity>() {
			@Override
			public int compare(Entity left, Entity right) {
				return Integer.compare(left.distance, right.distance);
			}
		});

		if (matches.size() > maxResults) {
			return new ArrayList<Entity>(matches.subList(0, maxResults));
		}
		return matches;
	}
}
