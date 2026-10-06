package rt4.mcp.tools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rt4.Component;
import rt4.InterfaceList;
import rt4.JagString;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/** MCP-21 — {@code logout} must only ever click a real logout control. */
class LogoutButtonTest {
	@AfterEach
	void cleanup() {
		InterfaceList.components = null;
		InterfaceList.topLevelInterface = -1;
	}

	private static Component component(int interfaceId, int childId, int buttonType) {
		Component component = new Component();
		component.id = (interfaceId << 16) | childId;
		component.buttonType = buttonType;
		component.overlayer = -1;
		return component;
	}

	private static void open(int interfaceId, Component... children) {
		if (InterfaceList.components == null || InterfaceList.components.length <= interfaceId) {
			InterfaceList.components = new Component[Math.max(interfaceId + 1, 256)][];
		}
		InterfaceList.components[interfaceId] = children;
		InterfaceList.topLevelInterface = interfaceId;
	}

	@Test
	void aSelectButtonIsNotALogoutButton() {
		Component select = component(182, 3, 5);
		select.text = JagString.parse("Select");
		select.option = JagString.parse("Select");
		open(182, select);

		assertNull(SessionTools.findLogoutButton(), "a type-5 select/radio button is not a logout button");
	}

	@Test
	void theInterface182LogoutButtonIsFound() {
		Component button = component(182, 6, 1);
		button.option = JagString.parse("Logout");
		open(182, button);

		SessionTools.LogoutButton found = SessionTools.findLogoutButton();

		assertNotNull(found);
		assertEquals("if:182:6", found.target.format());
		assertEquals("Logout", found.op);
	}

	@Test
	void clientCode205IsFoundInAnyOpenInterface() {
		Component button = component(149, 0, 1);
		button.clientCode = 205;
		button.option = JagString.parse("Ok");
		open(149, button);

		SessionTools.LogoutButton found = SessionTools.findLogoutButton();

		assertNotNull(found);
		assertEquals("if:149:0", found.target.format());
	}

	@Test
	void aButtonLabelledLogoutIsFound() {
		Component button = component(182, 9, 1);
		button.text = JagString.parse("Click here to logout");
		button.option = JagString.parse("");
		open(182, button);

		assertNotNull(SessionTools.findLogoutButton());
	}

	@Test
	void aHiddenOrNestedButtonOutsideTheLogoutTabIsIgnored() {
		Component unrelated = component(149, 4, 1);
		unrelated.text = JagString.parse("Deposit all");
		unrelated.option = JagString.parse("Deposit-All");
		open(149, unrelated);

		assertNull(SessionTools.findLogoutButton());
	}
}
