package rt4.aionly;

import plugin.api.API;
import plugin.api.FontColor;
import plugin.api.FontType;
import plugin.api.TextModifier;

/**
 * AIO-09 / AIO-12 / AIO-14 — the small drawing helpers the Java-side overlays share.
 *
 * <p>Everything goes through {@code plugin.api.API}, which picks the software raster or the
 * GL raster for the current renderer, so the same call works in SD and HD.</p>
 */
public final class Overlay {
	public static final FontColor WHITE = new FontColor(0xFFFFFF);
	public static final FontColor YELLOW = new FontColor(0xFFFF00);
	public static final FontColor GREEN = new FontColor(0x00FF00);
	public static final FontColor GREY = new FontColor(0xC0C0C0);

	private Overlay() {
	}

	/** A translucent filled box; {@code alpha} is 0 (opaque) to 255. */
	public static void box(int x, int y, int width, int height, int rgb, int alpha) {
		API.FillRect(x, y, width, height, rgb, alpha);
		API.DrawRect(x, y, width, height, 0x000000);
	}

	public static void textLeft(String text, int x, int y, FontColor color) {
		API.DrawText(FontType.SMALL, color, TextModifier.LEFT, text, x, y);
	}

	public static void textCenter(String text, int x, int y, FontColor color) {
		API.DrawText(FontType.SMALL, color, TextModifier.CENTER, text, x, y);
	}
}
