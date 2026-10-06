package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Every tool from MCP-06 to MCP-14 must actually be registered. */
class ToolRegistryTest {
	private static final List<String> EXPECTED = Arrays.asList(
			"get_status", "get_inventory", "get_equipment", "get_skills", "get_chat",
			"find_entities", "list_actions", "do_action", "cancel_selection", "get_interfaces",
			"type_text", "press_key", "drag_item", "camera", "mouse_click",
			"login", "logout", "get_screenshot", "wait_for",
			"walk_to", "nav_status", "nav_cancel",
			"interact", "continue_dialogue", "choose_option");

	@Test
	void everyToolIsRegisteredInOrder() {
		ToolRegistry registry = new ToolRegistry();
		McpServer.registerTools(registry);

		assertEquals(EXPECTED, names(registry));
	}

	@Test
	void everyToolHasANameDescriptionAndSchema() {
		ToolRegistry registry = new ToolRegistry();
		McpServer.registerTools(registry);

		for (Tool tool : registry.tools()) {
			assertTrue(tool.name() != null && !tool.name().isEmpty(), "a tool has no name");
			assertTrue(tool.description() != null && tool.description().length() > 20,
					tool.name() + " has no useful description");
			assertTrue(tool.inputSchema() != null && tool.inputSchema().has("type"),
					tool.name() + " has no input schema");
			assertTrue(tool.inputSchema().has("properties"), tool.name() + " has no properties");
		}
	}

	private static List<String> names(ToolRegistry registry) {
		List<String> names = new java.util.ArrayList<String>();
		for (Tool tool : registry.tools()) {
			names.add(tool.name());
		}
		return names;
	}
}
