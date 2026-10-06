package rt4.mcp.tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import rt4.Chat;
import rt4.mcp.ChatCursor;
import rt4.mcp.Names;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

/**
 * MCP-06 — {@code get_chat}: the client's chat buffer as a monotonic sequence, so polling
 * with {@code since=next} never misses or repeats a message.
 */
public final class ChatTools {
	private static final ChatCursor CURSOR = new ChatCursor();

	private static final ChatCursor.Source SOURCE = new ChatCursor.Source() {
		@Override
		public int total() {
			return Chat.size;
		}

		@Override
		public int capacity() {
			return Chat.messages.length;
		}

		@Override
		public int type(int index) {
			return Chat.types[index];
		}

		@Override
		public String name(int index) {
			return Names.plain(Chat.names[index]);
		}

		@Override
		public String text(int index) {
			return Names.plain(Chat.messages[index]);
		}
	};

	private ChatTools() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(chat());
	}

	/** Forgets the cursor; used when a new client session starts. */
	public static void resetCursor() {
		CURSOR.reset();
	}

	private static Tool chat() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "since", Tools.withDefault(Tools.integer(
				"Return only messages with seq > since. Pass the 'next' value from the previous call."), 0));
		Tools.prop(schema, "limit", Tools.withDefault(Tools.integer(
				"Maximum messages to return, 1-100."), 30));

		return Tools.gameTool("get_chat",
				"Chat messages (game, public, private, trade, clan, ...), oldest first. Each has a "
						+ "monotonic seq. Poll with since=<previous next> to get only new lines, or use "
						+ "wait_for(chat_matches) to block. Verify what an action did by reading this.",
				schema,
				args -> {
					int since = Tools.optInt(args, "since", 0);
					int limit = Tools.clamp(Tools.optInt(args, "limit", 30), 1, 100);

					ChatCursor.Result result = CURSOR.poll(SOURCE, since, limit);

					JsonObject out = new JsonObject();
					out.addProperty("next", result.next);
					JsonArray messages = new JsonArray();
					for (ChatCursor.Entry entry : result.messages) {
						JsonObject message = new JsonObject();
						message.addProperty("seq", entry.seq);
						message.addProperty("type", entry.type);
						message.addProperty("type_name", entry.typeName);
						message.add("name", Tools.text(entry.name));
						message.addProperty("text", entry.text);
						messages.add(message);
					}
					out.add("messages", messages);
					return ToolResult.json(out);
				});
	}

	/** Exposed so MCP-12's {@code chat_matches} can share the same cursor. */
	public static ChatCursor cursor() {
		return CURSOR;
	}
}
