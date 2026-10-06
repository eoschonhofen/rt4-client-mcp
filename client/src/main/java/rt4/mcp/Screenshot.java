package rt4.mcp;

import rt4.GlRenderer;
import rt4.SoftwareRaster;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * MCP-11 — captures the last drawn frame.
 *
 * <p>In this client the update and render loops share one thread
 * ({@code GameShell.main} alternating {@code mainLoopWrapper} and
 * {@code mainRedrawWrapper}), so the game thread can copy the pixel buffer without a race —
 * and without waiting for the render pass, which would deadlock. HD frames are refreshed by
 * {@code client.mainRedraw()} calling {@code GlRenderer.swapBuffers()} (which reads the
 * pixels back) at the end of every frame.</p>
 */
public final class Screenshot {
	public static final double MIN_SCALE = 0.25D;
	public static final double MAX_SCALE = 1.0D;
	public static final double DEFAULT_SCALE = 0.5D;
	/** MCP-23 — the last-resort encode scale when even the minimum requested scale is too big. */
	public static final double MIN_ENCODE_SCALE = 0.125D;
	/** MCP tools/call responses get unwieldy above roughly this much base64. */
	public static final int MAX_BASE64 = 1_500_000;

	public static final class Frame {
		public final int[] pixels;
		public final int width;
		public final int height;

		public Frame(int[] pixels, int width, int height) {
			this.pixels = pixels;
			this.width = width;
			this.height = height;
		}
	}

	private Screenshot() {
	}

	/** The last completed frame, top-down ARGB. */
	public static Frame capture() throws ToolException {
		if (GlRenderer.enabled) {
			int width = GlRenderer.canvasWidth;
			int height = GlRenderer.canvasHeight;
			int[] pixels = GlRenderer.pixelData;
			if (pixels == null || width <= 0 || height <= 0 || pixels.length < width * height) {
				throw new ToolException("no HD frame has been rendered yet");
			}
			int[] copy = Arrays.copyOf(pixels, width * height);
			// glReadPixels has its origin at the bottom-left.
			flipVertically(copy, width, height);
			return new Frame(copy, width, height);
		}

		int width = SoftwareRaster.width;
		int height = SoftwareRaster.height;
		int[] pixels = SoftwareRaster.pixels;
		if (pixels == null || width <= 0 || height <= 0 || pixels.length < width * height) {
			throw new ToolException("no frame has been drawn yet");
		}
		return new Frame(Arrays.copyOf(pixels, width * height), width, height);
	}

	/** Flips a top-down/bottom-up pixel array in place, row by row. */
	public static void flipVertically(int[] pixels, int width, int height) {
		for (int y = 0; y < height / 2; y++) {
			int top = y * width;
			int bottom = (height - 1 - y) * width;
			for (int x = 0; x < width; x++) {
				int tmp = pixels[top + x];
				pixels[top + x] = pixels[bottom + x];
				pixels[bottom + x] = tmp;
			}
		}
	}

	/**
	 * Copies a region into a {@code TYPE_INT_RGB} image, clamping the region to the frame.
	 * Alpha is dropped, which is what a screenshot wants.
	 */
	public static BufferedImage crop(int[] pixels, int frameWidth, int frameHeight, int x, int y, int width, int height) {
		int left = clamp(x, 0, Math.max(0, frameWidth - 1));
		int top = clamp(y, 0, Math.max(0, frameHeight - 1));
		int w = clamp(width, 1, frameWidth - left);
		int h = clamp(height, 1, frameHeight - top);

		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				image.setRGB(col, row, pixels[(top + row) * frameWidth + left + col] & 0xFFFFFF);
			}
		}
		return image;
	}

	/** The whole frame as an RGB image. */
	public static BufferedImage toImage(Frame frame) {
		return crop(frame.pixels, frame.width, frame.height, 0, 0, frame.width, frame.height);
	}

	/** Bilinear scale; the result is at least 1x1. */
	public static BufferedImage scale(BufferedImage source, double scale) {
		int width = Math.max(1, (int) Math.round(source.getWidth() * scale));
		int height = Math.max(1, (int) Math.round(source.getHeight() * scale));
		BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = output.createGraphics();
		try {
			graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			graphics.drawImage(source, 0, 0, width, height, null);
		} finally {
			graphics.dispose();
		}
		return output;
	}

	public static byte[] toPng(BufferedImage image) throws ToolException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image, "png", out);
			return out.toByteArray();
		} catch (Exception failure) {
			throw new ToolException("could not encode the screenshot as PNG: " + failure);
		}
	}

	/** MCP-23 — an encoded screenshot plus the scale it was actually encoded at. */
	public static final class Encoded {
		public final BufferedImage image;
		public final byte[] png;
		public final double scale;

		Encoded(BufferedImage image, byte[] png, double scale) {
			this.image = image;
			this.png = png;
			this.scale = scale;
		}
	}

	/**
	 * MCP-23 — scales and encodes, halving the scale until the base64 fits {@code maxBase64} or
	 * the floor {@link #MIN_ENCODE_SCALE} is reached. Reports the scale it really used, so the
	 * agent can convert image pixels back to canvas coordinates.
	 */
	public static Encoded encodeToFit(BufferedImage image, double requestedScale, int maxBase64) throws ToolException {
		double scale = requestedScale;
		BufferedImage scaled = scale(image, scale);
		byte[] png = toPng(scaled);
		while (ToolResult.base64Length(png) > maxBase64 && scale > MIN_ENCODE_SCALE) {
			scale = Math.max(MIN_ENCODE_SCALE, scale / 2.0D);
			scaled = scale(image, scale);
			png = toPng(scaled);
		}
		return new Encoded(scaled, png, scale);
	}

	public static double clampScale(double scale) {
		if (Double.isNaN(scale) || scale <= 0.0D) {
			return DEFAULT_SCALE;
		}
		return Math.max(MIN_SCALE, Math.min(MAX_SCALE, scale));
	}

	public static int clamp(int value, int min, int max) {
		if (max < min) {
			return min;
		}
		return value < min ? min : Math.min(value, max);
	}
}
