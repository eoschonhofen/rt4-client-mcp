package rt4.mcp.tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rt4.mcp.Screenshot;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

import java.awt.image.BufferedImage;

/**
 * MCP-11 — {@code get_screenshot}, the visual fallback for puzzles and unknown interfaces.
 */
public final class ScreenshotTool {
	private static final Gson COMPACT = new Gson();

	private ScreenshotTool() {
	}

	public static void register(ToolRegistry registry) {
		registry.register(screenshot());
	}

	private static Tool screenshot() {
		JsonObject schema = Tools.obj();
		Tools.prop(schema, "scale", Tools.withDefault(Tools.number(
				"Output scale, 0.25 - 1.0. Smaller means fewer image tokens."), Screenshot.DEFAULT_SCALE));

		JsonObject regionSchema = Tools.obj();
		Tools.prop(regionSchema, "x", Tools.integer("Left edge in canvas pixels."));
		Tools.prop(regionSchema, "y", Tools.integer("Top edge in canvas pixels."));
		Tools.prop(regionSchema, "w", Tools.integer("Width in canvas pixels."));
		Tools.prop(regionSchema, "h", Tools.integer("Height in canvas pixels."));
		Tools.prop(schema, "region", regionSchema);

		return Tools.gameTool("get_screenshot",
				"A PNG of the last drawn frame, for puzzles, unknown interfaces and sanity checks. "
						+ "Prefer the text tools; images are expensive. A text line reports the image size, "
						+ "the scale and the source frame size, so canvas coordinates for mouse_click are "
						+ "image pixel divided by scale.",
				schema,
				args -> {
					double scale = Screenshot.clampScale(Tools.optDouble(args, "scale", Screenshot.DEFAULT_SCALE));
					JsonObject region = Tools.optObject(args, "region");
					Screenshot.Frame frame = Screenshot.capture();

					BufferedImage image;
					if (region != null) {
						int x = (int) Tools.optDouble(region, "x", 0.0D);
						int y = (int) Tools.optDouble(region, "y", 0.0D);
						int width = (int) Tools.optDouble(region, "w", frame.width);
						int height = (int) Tools.optDouble(region, "h", frame.height);
						image = Screenshot.crop(frame.pixels, frame.width, frame.height, x, y, width, height);
					} else {
						image = Screenshot.toImage(frame);
					}

					BufferedImage scaled = Screenshot.scale(image, scale);
					byte[] png = Screenshot.toPng(scaled);
					if (ToolResult.base64Length(png) > Screenshot.MAX_BASE64) {
						scaled = Screenshot.scale(image, scale / 2.0D);
						png = Screenshot.toPng(scaled);
					}

					JsonObject info = new JsonObject();
					info.addProperty("width", scaled.getWidth());
					info.addProperty("height", scaled.getHeight());
					info.addProperty("scale", scale);
					info.addProperty("region_width", image.getWidth());
					info.addProperty("region_height", image.getHeight());
					info.addProperty("frame_width", frame.width);
					info.addProperty("frame_height", frame.height);
					return ToolResult.imageWithText(png, COMPACT.toJson(info));
				});
	}
}
