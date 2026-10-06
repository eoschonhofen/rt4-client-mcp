package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.Component;
import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;
import rt4.Inv;
import rt4.JagString;
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

					return ToolResult.json(out);
				});
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
				"Backpack contents (inventory 93). Only non-empty slots are returned. Each entry has "
						+ "slot, id, name, count, the item's ops (exactly the strings do_action accepts) and "
						+ "its inventory-slot target id, e.g. do_action(target='if:149:0:3', op='Wield').",
				Tools.obj(),
				args -> ToolResult.json(inventoryContents()));
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
				"Worn equipment (inventory 94) by 530 slot name: head, cape, neck, weapon, body, "
						+ "shield, legs, hands, feet, ring, ammo. Empty slots are omitted.",
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
					return ToolResult.json(items);
				});
	}

	// ------------------------------------------------------------------ get_skills

	private static Tool skills() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "skill", Tools.string("Optional skill name, case-insensitive, e.g. 'Woodcutting'."));

		return Tools.gameTool("get_skills",
				"All 25 skills as { name, level, base, xp }; level is the boosted level and base the "
						+ "real one. Pass 'skill' to get a single entry.",
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
					return ToolResult.json(result);
				});
	}
}
