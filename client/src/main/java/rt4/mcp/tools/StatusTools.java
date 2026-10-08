package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.Component;
import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;
import rt4.Inv;
import rt4.JagString;
import rt4.MapMarker;
import rt4.MiniMap;
import rt4.Npc;
import rt4.NpcList;
import rt4.ObjType;
import rt4.ObjTypeList;
import rt4.Player;
import rt4.PlayerList;
import rt4.PlayerSkillXpTable;
import rt4.VarpDomain;
import rt4.client;
import rt4.mcp.Coords;
import rt4.mcp.Dialogue;
import rt4.mcp.MenuSynth;
import rt4.mcp.nav.AStar;
import rt4.mcp.nav.DoorIndex;
import rt4.mcp.nav.SceneCollision;
import rt4.mcp.Names;
import rt4.mcp.TickTracker;
import rt4.mcp.Targets;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

/**
 * MCP-06 — the focused state reads: {@code get_status}, {@code get_inventory},
 * {@code get_equipment} and {@code get_skills}.
 */
public final class StatusTools {
	public static final int BACKPACK_INVENTORY = 93;
	public static final int EQUIPMENT_INVENTORY = 94;
	public static final int BACKPACK_INTERFACE = 149;
	public static final int BACKPACK_SLOTS = 28;
	/** The run-energy toggle varp, confirmed against {@code Settings.java} on the server. */
	public static final int RUN_VARP = 173;

	private static final int HP_SKILL = 3;
	private static final int PRAYER_SKILL = 5;

	private StatusTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(status());
		registry.register(inventory());
		registry.register(equipment());
		registry.register(skills());
	}

	// ------------------------------------------------------------------ get_status

	private static Tool status() {
		return Tools.gameTool("get_status",
				"Current player state: login state, world position, hp/prayer, run energy, animation, "
						+ "whether you are idle, the open interfaces and whether a dialogue is open. "
						+ "Works logged out (logged_in=false, no player fields). "
						+ "Call it after acting and after wait_for to see what changed.",
				Tools.obj(),
				args -> {
					JsonObject out = new JsonObject();
					boolean loggedIn = client.gameState == 30 && PlayerList.self != null;

					out.addProperty("logged_in", loggedIn);
					out.addProperty("game_state", client.gameState);
					out.addProperty("tick", TickTracker.tick());
					out.addProperty("ticks_approximate", TickTracker.APPROXIMATE);
					out.add("open_interfaces", openInterfaceIds());
					out.addProperty("dialogue_open", Dialogue.isOpen());

					if (loggedIn) {
						Player self = PlayerList.self;
						out.addProperty("name", Names.plain(self.getName()));
						out.addProperty("combat_level", self.combatLevel);
						out.add("position", position());
						out.add("hp", levels(HP_SKILL));
						out.add("prayer", levels(PRAYER_SKILL));

						JsonObject run = new JsonObject();
						run.addProperty("energy", Player.runEnergy);
						run.addProperty("enabled", VarpDomain.activeVarps[RUN_VARP] == 1);
						out.add("run", run);

						out.addProperty("weight", Player.weight);
						out.addProperty("animation", self.seqId);
						out.addProperty("moving", self.movementQueueSize > 0);
						String interacting = interacting(self.faceEntity);
						if (interacting != null) {
							out.addProperty("interacting", interacting);
						}
						out.addProperty("idle", isIdle(self));

						JsonObject selection = MenuSynth.selection();
						if (selection != null) {
							out.add("selection", selection);
						}
					}
					out.add("nav", rt4.mcp.nav.NavTask.statusJson());

					JsonArray hints = hints();
					if (hints.size() > 0) {
						out.add("hints", hints);
					}

					JsonObject around = around();
					if (around.size() > 0) {
						out.add("around", around);
					}

					return ToolResult.json(out);
				});
	}

	/**
	 * The eight directions around the agent, and whether each is {@code clear},
	 * {@code door} or {@code blocked}.
	 *
	 * "Follow the path" is impossible to act on without this: an agent can see
	 * the ground in front of it is not a wall, but not that every other way is.
	 * This is the compass that turns a corridor into a direction.
	 */
	private static JsonObject around() {
		JsonObject out = new JsonObject();
		SceneCollision collision = new SceneCollision(Player.plane);
		if (!collision.ready()) {
			return out;
		}

		int x = PlayerList.self.movementQueueX[0];
		int y = PlayerList.self.movementQueueY[0];
		java.util.Set<Integer> doors = DoorIndex.doors(12).keySet();

		String[][] directions = {
				{ "north", "0", "1" }, { "north_east", "1", "1" }, { "east", "1", "0" },
				{ "south_east", "1", "-1" }, { "south", "0", "-1" }, { "south_west", "-1", "-1" },
				{ "west", "-1", "0" }, { "north_west", "-1", "1" },
		};
		for (String[] direction : directions) {
			int dx = Integer.parseInt(direction[1]);
			int dy = Integer.parseInt(direction[2]);
			out.addProperty(direction[0],
					AStar.classifyStep(collision, x, y, dx, dy, doors));
		}
		return out;
	}

	/**
	 * The marks the world is pointing with — the flashing arrows on the map.
	 *
	 * Tutorial Island says "go here" with one of these rather than in words, and
	 * a player reads it off the minimap without thinking. An agent cannot: there
	 * is nothing in the interface tree that carries it, so without this it is
	 * told to go somewhere and never told where. {@code targetX}/{@code targetY}
	 * are world coordinates, the same space as {@code position}, because the
	 * minimap subtracts {@code Camera.origin} from them when it draws.
	 *
	 * {@code flashing} mirrors the blink the client draws ({@code loop % 20 < 10}),
	 * which is what makes the mark noticeable to a person at all.
	 */
	private static JsonArray hints() {
		JsonArray out = new JsonArray();
		for (MapMarker marker : MiniMap.hintMapMarkers) {
			if (marker == null || marker.type == 0) {
				continue;
			}

			JsonObject hint = new JsonObject();
			hint.addProperty("flashing", client.loop % 20 < 10);
			hint.addProperty("marker_type", marker.type);

			// Types 1 and 10 both carry an actor index; 2-6 carry a place; anything
			// else points off the edge of the map and is nothing to walk to.
			if (marker.type == 1 || marker.type == 10) {
				// Pointing at somebody. Only drawn when that somebody is loaded —
				// `MiniMap` skips the marker entirely otherwise — so only reported
				// when loaded, or the agent chases a mark no player can see. A
				// hint icon whose NPC has since gone leaves a marker pointing at a
				// stale slot, and that is precisely the case worth not reporting.
				Npc npc = marker.actorTargetId >= 0 && marker.actorTargetId < NpcList.npcs.length
						? NpcList.npcs[marker.actorTargetId] : null;
				if (npc == null || npc.type == null) {
					continue;
				}
				hint.addProperty("kind", "actor");
				hint.addProperty("index", marker.actorTargetId);
				String name = Names.plain(npc.type.name);
				if (name != null) {
					hint.addProperty("name", name);
				}
				// The same conversion the entity scan uses, so a hint and a
				// `find_entities` result name the same tile.
				hint.addProperty("x", Coords.worldX(npc.movementQueueX[0]));
				hint.addProperty("y", Coords.worldY(npc.movementQueueY[0]));
				hint.addProperty("plane", Player.plane);
			} else if (marker.type >= 2 && marker.type <= 6) {
				hint.addProperty("kind", "tile");
				hint.addProperty("x", marker.targetX);
				hint.addProperty("y", marker.targetY);
				hint.addProperty("plane", Player.plane);
				if (marker.targetHeight != 0) {
					hint.addProperty("height", marker.targetHeight);
				}
			} else {
				continue;
			}

			out.add(hint);
		}
		return out;
	}

	/** The MCP-12 {@code idle} predicate, also exposed through {@code get_status.idle}. */
	public static boolean isIdle(Player player) {
		return player.seqId == -1 && player.movementQueueSize == 0 && player.faceEntity == -1;
	}

	private static JsonObject position() {
		JsonObject position = new JsonObject();
		position.addProperty("x", Coords.worldX(PlayerList.self.movementQueueX[0]));
		position.addProperty("y", Coords.worldY(PlayerList.self.movementQueueY[0]));
		position.addProperty("plane", Player.plane);
		return position;
	}

	private static JsonObject levels(int skill) {
		JsonObject levels = new JsonObject();
		levels.addProperty("current", PlayerSkillXpTable.boostedLevels[skill]);
		levels.addProperty("max", PlayerSkillXpTable.baseLevels[skill]);
		return levels;
	}

	/** {@code faceEntity} below 32768 is an NPC index, above it a player index offset by 32768. */
	public static String interacting(int faceEntity) {
		if (faceEntity < 0) {
			return null;
		}
		return faceEntity < 32768 ? Targets.npc(faceEntity) : Targets.player(faceEntity - 32768);
	}

	/** Top-level interface plus every open sub-interface. */
	public static JsonArray openInterfaceIds() {
		JsonArray ids = new JsonArray();
		if (InterfaceList.topLevelInterface != -1) {
			ids.add(InterfaceList.topLevelInterface);
		}
		HashTableIterator iterator = new HashTableIterator(InterfaceList.openInterfaces);
		for (ComponentPointer pointer = (ComponentPointer) iterator.first(); pointer != null; pointer = (ComponentPointer) iterator.next()) {
			if (pointer.interfaceId != -1) {
				ids.add(pointer.interfaceId);
			}
		}
		return ids;
	}

	// ------------------------------------------------------------------ get_inventory

	private static Tool inventory() {
		return Tools.gameTool("get_inventory",
				"Backpack contents (inventory 93), as { items: [...] }. Only non-empty slots are "
						+ "returned. Each entry has slot, id, name, count, the item's ops (exactly the "
						+ "strings do_action accepts) and its inventory-slot target id, e.g. "
						+ "do_action(target='if:149:0:3', op='Wield').",
				Tools.obj(),
				args -> {
					JsonObject out = new JsonObject();
					out.add("items", inventoryContents());
					return ToolResult.json(out);
				});
	}

	/** The backpack as a JSON array, shared with the helper and smoke tools. */
	public static JsonArray inventoryContents() {
		JsonArray items = new JsonArray();
		Component component = backpackComponent();
		Inv inventory = (Inv) Inv.objectContainerCache.get(BACKPACK_INVENTORY);
		if (inventory == null) {
			return items;
		}

		int slots = Math.min(inventory.objectIds.length, BACKPACK_SLOTS);
		for (int slot = 0; slot < slots; slot++) {
			int id = inventory.objectIds[slot];
			if (id <= 0) {
				continue;
			}
			JsonObject item = new JsonObject();
			item.addProperty("slot", slot);
			item.addProperty("id", id);

			ObjType type = ObjTypeList.get(id);
			item.addProperty("name", type == null ? null : Names.plain(type.name));
			item.addProperty("count", inventory.objectStackSizes[slot]);
			item.add("ops", arrayOfOps(type == null ? null : type.iops));
			if (component != null) {
				item.addProperty("target", slotTarget(component, slot));
			}
			items.add(item);
		}
		return items;
	}

	/**
	 * The backpack container. Interface 149 child 0 is the fixed-mode component; in other
	 * window modes it is still loaded, but fall back to scanning the open interfaces for a
	 * 28-slot inventory so the target stays correct if that ever changes.
	 */
	public static Component backpackComponent() {
		Component direct = InterfaceList.getComponent(BACKPACK_INTERFACE, 0);
		if (direct != null && direct.type == 2 && slotCount(direct) == BACKPACK_SLOTS) {
			return direct;
		}

		JsonArray open = openInterfaceIds();
		for (int i = 0; i < open.size(); i++) {
			int interfaceId = open.get(i).getAsInt();
			if (interfaceId < 0 || interfaceId >= InterfaceList.components.length) {
				continue;
			}
			Component[] children = InterfaceList.components[interfaceId];
			if (children == null) {
				continue;
			}
			for (Component child : children) {
				if (child != null && child.type == 2 && slotCount(child) == BACKPACK_SLOTS) {
					return child;
				}
			}
		}
		return direct;
	}

	public static int slotCount(Component component) {
		return component.baseWidth * component.baseHeight;
	}

	/** The MCP-09 inventory-slot target id for a slot in this component. */
	public static String slotTarget(Component component, int slot) {
		return Targets.slot(component.id >>> 16, component.id & 0xFFFF, slot);
	}

	public static JsonArray arrayOfOps(JagString[] ops) {
		JsonArray array = new JsonArray();
		if (ops != null) {
			for (JagString op : ops) {
				if (op != null) {
					array.add(Names.plain(op));
				}
			}
		}
		return array;
	}

	// ------------------------------------------------------------------ get_equipment

	private static Tool equipment() {
		return Tools.gameTool("get_equipment",
				"Worn equipment (inventory 94) as { items: [...] }, by 530 slot name: head, cape, "
						+ "neck, weapon, body, shield, legs, hands, feet, ring, ammo. Empty slots are omitted.",
				Tools.obj(),
				args -> {
					JsonArray items = new JsonArray();
					Inv inventory = (Inv) Inv.objectContainerCache.get(EQUIPMENT_INVENTORY);
					if (inventory != null) {
						int slots = Math.min(inventory.objectIds.length, Names.EQUIPMENT_SLOTS.length);
						for (int slot = 0; slot < slots; slot++) {
							int id = inventory.objectIds[slot];
							if (id <= 0) {
								continue;
							}
							ObjType type = ObjTypeList.get(id);
							JsonObject item = new JsonObject();
							item.addProperty("slot", slot);
							item.addProperty("slot_name", Names.EQUIPMENT_SLOTS[slot]);
							item.addProperty("id", id);
							item.addProperty("name", type == null ? null : Names.plain(type.name));
							item.addProperty("count", inventory.objectStackSizes[slot]);
							items.add(item);
						}
					}
					JsonObject out = new JsonObject();
					out.add("items", items);
					return ToolResult.json(out);
				});
	}

	// ------------------------------------------------------------------ get_skills

	private static Tool skills() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "skill", Tools.string("Optional skill name, case-insensitive, e.g. 'Woodcutting'."));

		return Tools.gameTool("get_skills",
				"All 25 skills as { skills: [{ name, level, base, xp }] }; level is the boosted "
						+ "level and base the real one. Pass 'skill' to get a single entry.",
				schema,
				args -> {
					String wanted = Tools.optString(args, "skill", null);
					int wantedIndex = -1;
					if (wanted != null) {
						wantedIndex = Names.skillIndex(wanted);
						if (wantedIndex < 0) {
							throw new ToolException("unknown skill '" + wanted + "'; expected one of "
									+ String.join(", ", Names.SKILLS));
						}
					}

					JsonArray result = new JsonArray();
					for (int skill = 0; skill < Names.SKILLS.length; skill++) {
						if (wantedIndex >= 0 && skill != wantedIndex) {
							continue;
						}
						JsonObject entry = new JsonObject();
						entry.addProperty("name", Names.SKILLS[skill]);
						entry.addProperty("level", PlayerSkillXpTable.boostedLevels[skill]);
						entry.addProperty("base", PlayerSkillXpTable.baseLevels[skill]);
						entry.addProperty("xp", PlayerSkillXpTable.experience[skill]);
						entry.addProperty("enabled", PlayerSkillXpTable.ENABLED_SKILLS[skill]);
						result.add(entry);
					}
					JsonObject out = new JsonObject();
					out.add("skills", result);
					return ToolResult.json(out);
				});
	}
}
