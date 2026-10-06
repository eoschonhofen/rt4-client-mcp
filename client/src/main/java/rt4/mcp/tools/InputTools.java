package rt4.mcp.tools;

import com.google.gson.JsonObject;
import plugin.api.API;
import rt4.Component;
import rt4.InterfaceList;
import rt4.Protocol;
import rt4.ServerActiveProperties;
import rt4.VarpDomain;
import rt4.mcp.DragPackets;
import rt4.mcp.GameThread;
import rt4.mcp.InputInjector;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

/**
 * MCP-10 — the interactions that do not go through the minimenu: typing, keys, dragging,
 * the camera and raw mouse events.
 */
public final class InputTools {
	private InputTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(typeText());
		registry.register(pressKey());
		registry.register(dragItem());
		registry.register(camera());
		registry.register(mouseClick());
	}

	private static Tool typeText() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "text", Tools.string("The text to type, at most 80 characters."));
		Tools.prop(schema, "enter", Tools.withDefault(Tools.bool("Press Enter afterwards to submit."), false));
		Tools.require(schema, "text");

		return Tools.gameTool("type_text",
				"Type text as real keystrokes: public chat, the 'Enter amount' prompt, GE item search, "
						+ "private messages. Set enter=true to submit. The ack only means the keys were "
						+ "injected; confirm with get_chat or a state read.",
				schema,
				args -> {
					String text = Tools.getString(args, "text");
					boolean enter = Tools.optBool(args, "enter", false);
					InputInjector.typeText(text, enter);

					JsonObject out = new JsonObject();
					out.addProperty("typed", text);
					out.addProperty("enter", enter);
					return ToolResult.json(out);
				});
	}

	private static Tool pressKey() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "key", Tools.string(
				"enter, escape, backspace, tab, space, up, down, left, right, f1..f12, shift, ctrl, or a single character."));
		Tools.prop(schema, "hold_ms", Tools.withDefault(Tools.integer(
				"How long to hold the key. Arrow keys rotate the camera while held."), 0));
		Tools.require(schema, "key");

		return Tools.gameTool("press_key",
				"Press one key, optionally holding it for hold_ms (arrow keys then pan the camera, "
						+ "just like a human holding them).",
				schema,
				args -> {
					String key = Tools.getString(args, "key");
					int holdMs = Tools.optInt(args, "hold_ms", 0);
					if (holdMs < 0 || holdMs > 10000) {
						throw new ToolException("hold_ms must be between 0 and 10000");
					}
					InputInjector.pressKey(key, holdMs);

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("key", key);
					out.addProperty("hold_ms", holdMs);
					return ToolResult.json(out);
				});
	}

	private static Tool dragItem() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "from_slot", Tools.integer("The slot the item is dragged out of."));
		Tools.prop(schema, "to_slot", Tools.integer("The slot the item is dropped into."));
		Tools.prop(schema, "interface_id", Tools.integer(
				"Optional interface id holding the inventory; defaults to the backpack (149)."));
		Tools.require(schema, "from_slot", "to_slot");

		return Tools.gameTool("drag_item",
				"Drag an item between two slots of an inventory (backpack, bank, ...). The local update "
						+ "matches a real drag — swap, bank insert mode (the slots in between shift) or the "
						+ "replace mode — and the server is told with packet 231. Component-to-component "
						+ "drags are not supported.",
				schema,
				args -> {
					GameThread.requireLoggedIn();

					int fromSlot = Tools.getInt(args, "from_slot");
					int toSlot = Tools.getInt(args, "to_slot");
					if (fromSlot < 0 || toSlot < 0) {
						throw new ToolException("slots must be non-negative");
					}
					if (fromSlot == toSlot) {
						throw new ToolException("from_slot and to_slot must differ");
					}
					Integer interfaceId = Tools.has(args, "interface_id") ? Tools.getInt(args, "interface_id") : null;

					Component component = inventoryComponent(interfaceId, Math.max(fromSlot, toSlot));
					int slots = StatusTools.slotCount(component);
					if (fromSlot >= slots || toSlot >= slots) {
						throw new ToolException("slot " + Math.max(fromSlot, toSlot) + " is outside this inventory ("
								+ slots + " slots)");
					}
					if (component.objTypes[fromSlot] <= 0) {
						throw new ToolException("slot " + fromSlot + " is empty");
					}

					ServerActiveProperties properties = InterfaceList.getServerActiveProperties(component);
					if (!properties.isObjSwapEnabled() && !properties.isObjReplaceEnabled()) {
						throw new ToolException("this interface does not allow dragging items between slots");
					}

					boolean replace = properties.isObjReplaceEnabled();
					int sourceObjId = component.objTypes[fromSlot] - 1;
					int inserting = DragPackets.insertFlag(VarpDomain.inserting, component.clientCode, sourceObjId);

					DragPackets.applyLocal(component.objTypes, component.objCounts, fromSlot, toSlot,
							replace, inserting == 1);
					Protocol.outboundBuffer.p1isaac(DragPackets.DRAG_OPCODE);
					Protocol.outboundBuffer.pdata(
							DragPackets.encode(toSlot, component.id, fromSlot, inserting), DragPackets.PAYLOAD_LENGTH);

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("from_slot", fromSlot);
					out.addProperty("to_slot", toSlot);
					out.addProperty("component_id", component.id);
					out.addProperty("inserting", inserting);
					out.addProperty("replaced", replace);
					return ToolResult.json(out);
				});
	}

	private static Component inventoryComponent(Integer interfaceId, int maxSlot) throws ToolException {
		if (interfaceId == null) {
			Component backpack = StatusTools.backpackComponent();
			if (backpack == null) {
				throw new ToolException("the backpack is not open");
			}
			if (maxSlot >= StatusTools.slotCount(backpack)) {
				throw new ToolException("slot " + maxSlot + " is outside the backpack ("
						+ StatusTools.slotCount(backpack) + " slots)");
			}
			return backpack;
		}

		if (InterfaceList.components == null || interfaceId < 0 || interfaceId >= InterfaceList.components.length) {
			throw new ToolException("unknown interface id " + interfaceId);
		}
		Component[] children = InterfaceList.components[interfaceId];
		if (children != null) {
			for (Component child : children) {
				if (child != null && child.type == 2 && StatusTools.slotCount(child) > maxSlot) {
					return child;
				}
			}
		}
		throw new ToolException("no inventory with slot " + maxSlot + " in interface " + interfaceId);
	}

	private static Tool camera() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "yaw", Tools.number("Camera yaw in degrees."));
		Tools.prop(schema, "pitch", Tools.number("Camera pitch in degrees."));
		Tools.prop(schema, "zoom", Tools.integer("Camera zoom in raw units (higher is further away)."));

		return Tools.gameTool("camera",
				"Read and/or set the camera. Omit an argument to leave it alone; the result always "
						+ "reports the current yaw, pitch and zoom.",
				schema,
				args -> {
					if (Tools.has(args, "yaw")) {
						API.SetCameraYaw(Tools.optDouble(args, "yaw", 0.0D));
					}
					if (Tools.has(args, "pitch")) {
						API.SetCameraPitch(Tools.optDouble(args, "pitch", 0.0D));
					}
					if (Tools.has(args, "zoom")) {
						API.SetCameraZoom(Tools.optInt(args, "zoom", 0));
					}

					JsonObject out = new JsonObject();
					out.addProperty("yaw", API.GetCameraYaw());
					out.addProperty("pitch", API.GetCameraPitch());
					out.addProperty("zoom", API.GetCameraZoom());
					return ToolResult.json(out);
				});
	}

	private static Tool mouseClick() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "x", Tools.integer("Canvas x in pixels."));
		Tools.prop(schema, "y", Tools.integer("Canvas y in pixels."));
		Tools.prop(schema, "button", Tools.withDefault(Tools.stringEnum(
				"Mouse button.", "left", "right", "middle"), "left"));
		Tools.prop(schema, "move_only", Tools.withDefault(Tools.bool(
				"Only move the pointer, do not click."), false));
		Tools.require(schema, "x", "y");

		return Tools.tool("mouse_click",
				"Escape hatch: post a raw mouse event at canvas pixel (x, y). Prefer do_action, which "
						+ "builds the real minimenu packet. Use get_screenshot to find coordinates. "
						+ "Synthetic clicks do not cancel walk_to.",
				schema,
				args -> {
					int x = Tools.getInt(args, "x");
					int y = Tools.getInt(args, "y");
					String button = Tools.optString(args, "button", "left");
					boolean moveOnly = Tools.optBool(args, "move_only", false);

					InputInjector.click(x, y, button, moveOnly);

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("x", x);
					out.addProperty("y", y);
					out.addProperty("button", button);
					out.addProperty("move_only", moveOnly);
					return ToolResult.json(out);
				});
	}
}
