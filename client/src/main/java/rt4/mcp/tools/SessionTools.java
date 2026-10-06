package rt4.mcp.tools;

import com.google.gson.JsonObject;
import rt4.Component;
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
					rt4.mcp.nav.NavTask.cancel("cancelled by login");

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

					LogoutButton button = findLogoutButton();
					if (button == null) {
						throw new ToolException("no logout button is open; open the logout tab first");
					}
					MenuSynth.act(button.target, button.op, null);
					rt4.mcp.nav.NavTask.cancel("cancelled by logout");

					JsonObject out = new JsonObject();
					out.addProperty("ok", true);
					out.addProperty("target", button.target.format());
					out.addProperty("op", button.op);
					return ToolResult.json(out);
				});
	}

	/** The logout button and the op that clicks it. */
	static final class LogoutButton {
		final ComponentTarget target;
		final String op;

		LogoutButton(ComponentTarget target, String op) {
			this.target = target;
			this.op = op;
		}
	}

	/**
	 * Finds the logout trigger in any open interface. The client marks it with
	 * {@code clientCode == 205} (see {@code MiniMenu.handleSpecialButtonAction}); the 530 logout
	 * tab also uses {@code buttonType == 5} ("Click here to logout" is a plain button whose text
	 * says what it does).
	 */
	static LogoutButton findLogoutButton() {
		for (int rank = 0; rank < 3; rank++) {
			com.google.gson.JsonArray open = StatusTools.openInterfaceIds();
			for (int i = 0; i < open.size(); i++) {
				int interfaceId = open.get(i).getAsInt();
				if (InterfaceList.components == null || interfaceId < 0 || interfaceId >= InterfaceList.components.length) {
					continue;
				}
				LogoutButton found = findLogoutIn(InterfaceList.components[interfaceId], -1, interfaceId, 1, rank);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}

	private static LogoutButton findLogoutIn(Component[] all, int parentId, int interfaceId, int depth, int rank) {
		if (all == null || depth > 12) {
			return null;
		}
		for (Component child : all) {
			if (child == null || child.overlayer != parentId) {
				continue;
			}
			if (matchesRank(child, rank)) {
				String op = Names.plain(child.option);
				if (op == null || op.isEmpty()) {
					op = "Ok";
				}
				return new LogoutButton(ComponentTarget.component(interfaceId, child.id & 0xFFFF), op);
			}
			LogoutButton nested = findLogoutIn(all, child.id, interfaceId, depth + 1, rank);
			if (nested != null) {
				return nested;
			}
		}
		return null;
	}

	private static boolean matchesRank(Component component, int rank) {
		if (rank == 0) {
			return component.clientCode == 205;
		}
		if (rank == 1) {
			return component.buttonType == LOGOUT_BUTTON_TYPE;
		}
		String text = Names.plain(component.text);
		return component.buttonType != 0 && text != null
				&& text.toLowerCase(java.util.Locale.ROOT).contains("logout");
	}

}
