package rt4.mcp.tools;

import com.google.gson.JsonObject;
import rt4.Component;
import rt4.ComponentPointer;
import rt4.HashTableIterator;
import rt4.InterfaceList;
import rt4.JagString;
import rt4.LoginManager;
import rt4.client;
import rt4.mcp.ComponentTarget;
import rt4.mcp.GameThread;
import rt4.mcp.MenuSynth;
import rt4.mcp.Names;
import rt4.mcp.Targets;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

/**
 * MCP-10 — {@code login} and {@code logout}.
 */
public final class SessionTools {
	/** {@code MiniMenu.LOGOUT_ACTION}: the logout button's action code. */
	private static final int LOGOUT_BUTTON_TYPE = 5;

	private SessionTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(login());
		registry.register(logout());
	}

	private static Tool login() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "username", Tools.string("Account name."));
		Tools.prop(schema, "password", Tools.string("Account password. Never logged."));
		Tools.prop(schema, "world", Tools.integer("Reserved; world switching is not supported yet."));
		Tools.require(schema, "username", "password");

		return Tools.gameTool("login",
				"Log in from the title screen. The local server accepts any credentials when auth is "
						+ "off. This only starts the login; follow it with wait_for(logged_in).",
				schema,
				args -> {
					if (client.gameState != 10) {
						throw new ToolException("login is only possible on the title screen (game_state="
								+ client.gameState + "); logout first");
					}
					String username = Tools.getString(args, "username");
					String password = Tools.getString(args, "password");

					LoginManager.startLogin(JagString.parse(username), JagString.parse(password), 0);

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("username", username);
					out.addProperty("note", "call wait_for(logged_in)");
					return ToolResult.json(out);
				});
	}

	private static Tool logout() {
		return Tools.gameTool("logout",
				"Click the logout button, which sends the logout packet exactly like the UI. The "
						+ "server can refuse it during combat. Follow it with wait_for(logged_out).",
				Tools.obj(),
				args -> {
					GameThread.requireLoggedIn();

					ComponentTarget button = findLogoutButton();
					if (button == null) {
						throw new ToolException("no logout button is open; open the logout tab first");
					}
					Component component = Targets.resolveComponent(button);
					String op = Names.plain(component.option);
					if (op == null || op.isEmpty()) {
						op = "Logout";
					}
					MenuSynth.act(button, op, null);

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("target", button.format());
					out.addProperty("op", op);
					return ToolResult.json(out);
				});
	}

	/** The logout button is the open component with {@code buttonType == 5}. */
	static ComponentTarget findLogoutButton() {
		int top = InterfaceList.topLevelInterface;
		if (top != -1) {
			ComponentTarget found = findLogoutIn(InterfaceList.components == null || top >= InterfaceList.components.length
					? null : InterfaceList.components[top], top);
			if (found != null) {
				return found;
			}
		}
		if (InterfaceList.openInterfaces != null) {
			HashTableIterator iterator = new HashTableIterator(InterfaceList.openInterfaces);
			for (ComponentPointer pointer = (ComponentPointer) iterator.first(); pointer != null; pointer = (ComponentPointer) iterator.next()) {
				int interfaceId = pointer.interfaceId;
				if (InterfaceList.components == null || interfaceId < 0 || interfaceId >= InterfaceList.components.length) {
					continue;
				}
				ComponentTarget found = findLogoutIn(InterfaceList.components[interfaceId], interfaceId);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}

	private static ComponentTarget findLogoutIn(Component[] children, int interfaceId) {
		if (children == null) {
			return null;
		}
		for (Component child : children) {
			if (child != null && child.buttonType == LOGOUT_BUTTON_TYPE) {
				return ComponentTarget.component(interfaceId, child.id & 0xFFFF);
			}
		}
		return null;
	}
}
