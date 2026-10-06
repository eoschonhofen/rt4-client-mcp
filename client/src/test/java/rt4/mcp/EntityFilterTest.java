package rt4.mcp;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntityFilterTest {
	private static EntityFilter.Entity entity(String type, String name, int distance, String... ops) {
		JsonObject extra = new JsonObject();
		extra.addProperty("combat_level", 1);
		return new EntityFilter.Entity(type + ":" + name, type, 1, name, 3222, 3218, 0, distance,
				Arrays.asList(ops), extra);
	}

	private static List<EntityFilter.Entity> sample() {
		List<EntityFilter.Entity> all = new ArrayList<EntityFilter.Entity>();
		all.add(entity("npc", "Banker", 3, "Bank", "Talk-to"));
		all.add(entity("npc", "Hans", 1, "Talk-to"));
		all.add(entity("loc", "Door", 2, "Open", "Examine"));
		all.add(entity("loc", "Gate", 20, "Open"));
		all.add(entity("obj", "Coins", 1, "Take"));
		all.add(entity("player", "Zezima", 4));
		return all;
	}

	@Test
	void filtersByType() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "npc", null, "contains", 52, null, 100);
		assertEquals(2, result.size());
		assertEquals("Hans", result.get(0).name);
		assertEquals("Banker", result.get(1).name);
	}

	@Test
	void anyTypeReturnsEverything() {
		assertEquals(6, EntityFilter.select(sample(), "any", null, "contains", 52, null, 100).size());
	}

	@Test
	void nameContainsIsCaseInsensitive() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "any", "an", "contains", 52, null, 100);
		assertEquals(2, result.size());
		assertEquals("Hans", result.get(0).name);
		assertEquals("Banker", result.get(1).name);
	}

	@Test
	void nameExactMatchesTheWholeName() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "any", "Hans", "exact", 52, null, 100);
		assertEquals(1, result.size());
		assertEquals("Hans", result.get(0).name);
		assertTrue(EntityFilter.select(sample(), "any", "Han", "exact", 52, null, 100).isEmpty());
	}

	@Test
	void radiusUsesChebyshevDistance() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "any", null, "contains", 2, null, 100);
		assertEquals(3, result.size());
		assertFalse(result.stream().anyMatch(e -> "Gate".equals(e.name)));
	}

	@Test
	void hasOpIsCaseInsensitive() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "any", null, "contains", 52, "open", 100);
		assertEquals(2, result.size());
		assertEquals("Door", result.get(0).name);
		assertEquals("Gate", result.get(1).name);
	}

	@Test
	void hasOpCombinesWithName() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "loc", "door", "contains", 52, "Open", 100);
		assertEquals(1, result.size());
		assertEquals("Door", result.get(0).name);
	}

	@Test
	void resultsAreSortedByDistanceAndStableForTies() {
		List<EntityFilter.Entity> all = new ArrayList<EntityFilter.Entity>();
		all.add(entity("npc", "Far", 5));
		all.add(entity("npc", "NearFirst", 1));
		all.add(entity("npc", "NearSecond", 1));
		all.add(entity("npc", "Middle", 3));

		List<EntityFilter.Entity> result = EntityFilter.select(all, "any", null, "contains", 52, null, 100);

		assertEquals("NearFirst", result.get(0).name);
		assertEquals("NearSecond", result.get(1).name);
		assertEquals("Middle", result.get(2).name);
		assertEquals("Far", result.get(3).name);
	}

	@Test
	void limitTruncatesAfterSorting() {
		List<EntityFilter.Entity> result = EntityFilter.select(sample(), "any", null, "contains", 52, null, 2);
		assertEquals(2, result.size());
		assertEquals("Hans", result.get(0).name);
		assertEquals(1, result.get(0).distance);
	}

	@Test
	void radiusIsClampedToTheScene() {
		assertEquals(EntityFilter.DEFAULT_RADIUS, EntityFilter.clampRadius(-1));
		assertEquals(EntityFilter.MAX_RADIUS, EntityFilter.clampRadius(999));
		assertEquals(20, EntityFilter.clampRadius(20));
	}

	@Test
	void limitIsClamped() {
		assertEquals(EntityFilter.DEFAULT_LIMIT, EntityFilter.clampLimit(0));
		assertEquals(EntityFilter.MAX_LIMIT, EntityFilter.clampLimit(9999));
		assertEquals(5, EntityFilter.clampLimit(5));
	}

	@Test
	void emptyNameFilterMatchesEverything() {
		assertEquals(6, EntityFilter.select(sample(), "any", "", "contains", 52, null, 100).size());
		assertEquals(6, EntityFilter.select(sample(), "any", null, "contains", 52, null, 100).size());
	}

	@Test
	void toJsonDropsEmptyOpsAndExtra() {
		EntityFilter.Entity bare = new EntityFilter.Entity("npc:1", "npc", 7, "Hans", 3222, 3218, 0, 1,
				java.util.Collections.<String>emptyList(), null);
		JsonObject json = bare.toJson();

		assertEquals("npc:1", json.get("target").getAsString());
		assertEquals("npc", json.get("type").getAsString());
		assertEquals(7, json.get("id").getAsInt());
		assertEquals("Hans", json.get("name").getAsString());
		assertEquals(3222, json.get("x").getAsInt());
		assertEquals(1, json.get("distance").getAsInt());
		assertFalse(json.has("ops"));
		assertFalse(json.has("extra"));
	}

	@Test
	void toJsonKeepsOpsAndExtra() {
		EntityFilter.Entity rich = entity("npc", "Banker", 3, "Bank", "Talk-to");
		JsonObject json = rich.toJson();

		assertTrue(json.has("ops"));
		assertEquals(2, json.getAsJsonArray("ops").size());
		assertTrue(json.has("extra"));
	}

	@Test
	void distanceGeometry() {
		assertEquals(0, SceneScan.boxDistance(5, 5, 5, 5, 5, 5));
		assertEquals(0, SceneScan.boxDistance(6, 6, 5, 5, 7, 7));
		assertEquals(2, SceneScan.boxDistance(9, 5, 5, 5, 7, 7));
		assertEquals(3, SceneScan.boxDistance(2, 2, 5, 5, 7, 7));
		assertEquals(3, SceneScan.chebyshev(0, 0, 3, 1));
	}

	@Test
	void nullNamesAreRecognised() {
		assertTrue(SceneScan.isNullName(null));
		assertTrue(SceneScan.isNullName(""));
		assertTrue(SceneScan.isNullName("null"));
		assertTrue(SceneScan.isNullName("Null"));
		assertFalse(SceneScan.isNullName("Door"));
	}
}
