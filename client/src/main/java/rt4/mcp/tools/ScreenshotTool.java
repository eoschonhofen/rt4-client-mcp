package rt4.mcp.tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rt4.mcp.GameThread;
import rt4.mcp.Screenshot;
import rt4.mcp.Tool;
import rt4.mcp.ToolRegistry;
import rt4.mcp.ToolResult;
import rt4.mcp.Tools;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

/**
 * MCP-11 / MCP-23 — {@code get_screenshot}, the visual fallback for puzzles and unknown
 * interfaces.
 *
 * <p>Only the frame copy runs on the game thread (through {@link GameThread#call}); cropping,
 * scaling and PNG encoding happen on the calling HTTP thread, so a screenshot does not stall a
 * frame.</p>
 */
public final class ScreenshotTool {
	private static final Gson COMPACT = new Gson();

	/** Test seam: production reads the live frame through {@link Screenshot#capture()}. */
	static volatile Callable<Screenshot.Frame> captureSource = Screenshot::capture;

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

		return Tools.tool("get_screenshot",
				"A PNG of the last drawn frame, for puzzles, unknown interfaces and sanity checks. "
						+ "Prefer the text tools; images are expensive. The text line reports the size, the "
						+ "scale the image was actually encoded at, the region origin and the source frame "
						+ "size; convert an image pixel to a canvas coordinate with "
						+ "canvas = (region_x, region_y) + pixel / scale.",
				schema,
				args -> {
					final double requested = Screenshot.clampScale(
							Tools.optDouble(args, "scale", Screenshot.DEFAULT_SCALE));
					final JsonObject region = Tools.optObject(args, "region");

					// Only the pixel copy touches the game thread (MCP-23).
					Screenshot.Frame frame = GameThread.call(captureSource);

					int regionX = 0;
					int regionY = 0;
					BufferedImage image;
					if (region != null) {
						regionX = Screenshot.clamp((int) Tools.optDouble(region, "x", 0.0D), 0, Math.max(0, frame.width - 1));
						regionY = Screenshot.clamp((int) Tools.optDouble(region, "y", 0.0D), 0, Math.max(0, frame.height - 1));
						int regionW = Screenshot.clamp((int) Tools.optDouble(region, "w", frame.width), 1, frame.width - regionX);
						int regionH = Screenshot.clamp((int) Tools.optDouble(region, "h", frame.height), 1, frame.height - regionY);
						image = Screenshot.crop(frame.pixels, frame.width, frame.height, regionX, regionY, regionW, regionH);
					} else {
						image = Screenshot.toImage(frame);
					}

					Screenshot.Encoded encoded = Screenshot.encodeToFit(image, requested, Screenshot.MAX_BASE64);

					JsonObject info = new JsonObject();
					info.addProperty("width", encoded.image.getWidth());
					info.addProperty("height", encoded.image.getHeight());
					info.addProperty("scale", encoded.scale);
					info.addProperty("region_x", regionX);
					info.addProperty("region_y", regionY);
					info.addProperty("region_width", image.getWidth());
					info.addProperty("region_height", image.getHeight());
					info.addProperty("frame_width", frame.width);
					info.addProperty("frame_height", frame.height);
					return ToolResult.imageWithText(encoded.png, COMPACT.toJson(info));
				});
	}
}
