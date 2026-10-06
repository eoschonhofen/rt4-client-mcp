package rt4.mcp.tools;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import rt4.mcp.GameThread;
import rt4.mcp.Screenshot;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** MCP-23 — only the frame copy may touch the game thread. */
class ScreenshotToolTest {
	@AfterEach
	void cleanup() {
		GameThread.reset();
		ScreenshotTool.captureSource = Screenshot::capture;
	}

	private static Tool screenshotTool() {
		ToolRegistry registry = new ToolRegistry();
		ScreenshotTool.register(registry);
		return registry.get("get_screenshot");
	}

	@Test
	void onlyTheCaptureRunsOnTheGameThread() throws Exception {
		Tool tool = screenshotTool();
		assertNotNull(tool);

		AtomicReference<Thread> captureThread = new AtomicReference<Thread>();
		ScreenshotTool.captureSource = () -> {
			captureThread.set(Thread.currentThread());
			return new Screenshot.Frame(new int[8 * 8], 8, 8);
		};

		AtomicBoolean running = new AtomicBoolean(true);
		Thread game = new Thread(() -> {
			while (running.get()) {
				GameThread.drain();
				try {
					Thread.sleep(1L);
				} catch (InterruptedException stopped) {
					return;
				}
			}
		}, "fake-game-thread");
		game.start();

		long deadline = System.currentTimeMillis() + 3000L;
		while (GameThread.owner() == null && System.currentTimeMillis() < deadline) {
			Thread.sleep(1L);
		}

		try {
			ToolResult result = tool.call(new JsonObject());

			assertEquals(game, captureThread.get(), "the frame copy must run on the game thread");
			assertNotEquals(Thread.currentThread(), captureThread.get(), "encoding must not run on the game thread");
			JsonObject json = result.toJson();
			assertEquals("image", json.getAsJsonArray("content").get(0).getAsJsonObject().get("type").getAsString(),
					"the result must still be a PNG image block");
		} finally {
			running.set(false);
			game.join(3000L);
		}
	}

	@Test
	void regionOriginAndSizeAreReported() throws Exception {
		Tool tool = screenshotTool();
		ScreenshotTool.captureSource = () -> new Screenshot.Frame(new int[20 * 10], 20, 10);
		GameThread.drain(); // this thread becomes the owner, so capture runs inline

		JsonObject args = JsonParser.parseString("{\"region\":{\"x\":3,\"y\":2,\"w\":5,\"h\":4}}").getAsJsonObject();
		ToolResult result = tool.call(args);

		JsonObject content = result.toJson().getAsJsonArray("content").get(1).getAsJsonObject();
		JsonObject info = JsonParser.parseString(content.get("text").getAsString()).getAsJsonObject();
		assertEquals(3, info.get("region_x").getAsInt());
		assertEquals(2, info.get("region_y").getAsInt());
		assertEquals(5, info.get("region_width").getAsInt());
		assertEquals(4, info.get("region_height").getAsInt());
		assertEquals(20, info.get("frame_width").getAsInt());
		assertEquals(10, info.get("frame_height").getAsInt());
	}
}
