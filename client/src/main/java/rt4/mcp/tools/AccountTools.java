package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.aionly.AccountStore;
import rt4.aionly.Accounts;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

import java.util.List;

/**
 * AIO-10 — {@code get_account}: the agent fetches its own credentials.
 *
 * <p>This reads {@code accounts.json}, which AIO-08 writes when a human creates the account
 * from the title screen. It touches no game state, so it deliberately does not go through
 * {@code GameThread} and works before login.</p>
 */
public final class AccountTools {
	private AccountTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(getAccount());
	}

	private static Tool getAccount() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "name", Tools.string(
			"Optional account name. Omit to list every account saved for this server."));

		return Tools.tool("get_account",
			"Read the accounts this client has created. The token is the password for login: "
				+ "call login(name, token) and then wait_for(logged_in). Never type a token into chat.",
			schema,
			args -> {
				String host = host();
				String wanted = Tools.optString(args, "name", null);
				if (wanted != null) {
					wanted = wanted.trim();
					if (wanted.isEmpty()) {
						wanted = null;
					}
				}

				JsonArray matches = new JsonArray();
				List<AccountStore.Account> saved = Accounts.store().list();
				for (AccountStore.Account account : saved) {
					if (!host.equals(account.host)) {
						continue;
					}
					if (wanted != null && !wanted.equalsIgnoreCase(account.name)) {
						continue;
					}
					JsonObject entry = new JsonObject();
					entry.addProperty("name", account.name);
					entry.addProperty("token", account.token);
					entry.addProperty("created", account.created);
					matches.add(entry);
				}

				if (wanted != null && matches.size() == 0) {
					throw new ToolException("no saved account '" + wanted + "' for " + host);
				}

				JsonObject out = new JsonObject();
				out.addProperty("host", host);
				out.add("accounts", matches);
				return ToolResult.json(out);
			});
	}

	/**
	 * The server this client is pointed at; see {@link Accounts#host()}.
	 */
	static String host() {
		return Accounts.host();
	}
}
