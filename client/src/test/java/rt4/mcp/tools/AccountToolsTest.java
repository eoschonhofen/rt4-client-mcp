package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import rt4.aionly.AccountStore;
import rt4.aionly.Accounts;
import rt4.mcp.Tool;
import rt4.mcp.ToolException;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;

import java.nio.file.Path;

/** AIO-10 — get_account: listing, per-host filtering, and the unknown-name error. */
class AccountToolsTest {
	private static final String HOST = "play.example.org";
	private static final String OTHER_HOST = "test.2009scape.org";

	@TempDir
	Path dir;

	private String savedHostname;

	@BeforeEach
	void setUp() {
		savedHostname = rt4.client.hostname;
		rt4.client.hostname = HOST;

		Accounts.init(dir.resolve("config.json").toString());
		AccountStore store = Accounts.store();
		store.add(AccountStore.Account.create("bob", "abcdefghijklmnopqrst", HOST));
		store.add(AccountStore.Account.create("alice", "01234567890123456789", HOST));
		store.add(AccountStore.Account.create("carol", "zzzzzzzzzzzzzzzzzzzz", OTHER_HOST));
	}

	@AfterEach
	void tearDown() {
		rt4.client.hostname = savedHostname;
	}

	@Test
	void listsEveryAccountForTheCurrentHost() throws ToolException {
		JsonObject out = call(null);

		Assertions.assertEquals(HOST, out.get("host").getAsString());
		JsonArray accounts = out.getAsJsonArray("accounts");
		Assertions.assertEquals(2, accounts.size());
		Assertions.assertEquals("bob", accounts.get(0).getAsJsonObject().get("name").getAsString());
		Assertions.assertEquals("abcdefghijklmnopqrst",
			accounts.get(0).getAsJsonObject().get("token").getAsString());
		Assertions.assertEquals("alice", accounts.get(1).getAsJsonObject().get("name").getAsString());
	}

	@Test
	void returnsTheNamedAccount() throws ToolException {
		JsonObject out = call("bob");

		JsonArray accounts = out.getAsJsonArray("accounts");
		Assertions.assertEquals(1, accounts.size());
		Assertions.assertEquals("abcdefghijklmnopqrst",
			accounts.get(0).getAsJsonObject().get("token").getAsString());
		Assertions.assertFalse(accounts.get(0).getAsJsonObject().get("created").isJsonNull());
	}

	@Test
	void neverReturnsAnAccountFromAnotherHost() throws ToolException {
		JsonObject out = call(null);
		Assertions.assertEquals(2, out.getAsJsonArray("accounts").size());

		Assertions.assertThrows(ToolException.class, () -> call("carol"));
	}

	@Test
	void anUnknownNameIsAToolError() {
		ToolException error = Assertions.assertThrows(ToolException.class, () -> call("nobody"));
		Assertions.assertTrue(error.getMessage().contains("nobody"), error.getMessage());
		Assertions.assertTrue(error.getMessage().contains(HOST), error.getMessage());
	}

	@Test
	void isRegistered() {
		ToolRegistry registry = new ToolRegistry();
		AccountTools.register(registry);
		Assertions.assertTrue(registry.contains("get_account"));
	}

	private JsonObject call(String name) throws ToolException {
		ToolRegistry registry = new ToolRegistry();
		AccountTools.register(registry);
		Tool tool = registry.get("get_account");

		JsonObject args = new JsonObject();
		if (name != null) {
			args.addProperty("name", name);
		}
		ToolResult result = tool.call(args);
		return result.toJson().getAsJsonObject("structuredContent");
	}
}
