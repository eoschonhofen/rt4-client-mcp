package rt4.aionly;

import rt4.GameShell;
import rt4.client;

/**
 * AIO-09 / AIO-12 / AIO-14 — the single draw hook for the Java-side overlays.
 *
 * <p>{@code client.mainRedraw()} calls {@link #draw()} after the interfaces have been drawn
 * and before the frame is presented, which is the one place that works for both the software
 * and the GL renderer. The overlays therefore end up inside {@code get_screenshot}; the
 * top-left corner is documented as the spectator overlay in the tool description.</p>
 */
public final class Overlays {
	private Overlays() {
	}

	public static void draw() {
		int width = GameShell.canvasWidth;
		int height = GameShell.canvasHeight;

		if (client.gameState == 10) {
			TokenPanel.draw(width, height);
			TitleMessage.draw(width, height);
		} else if (client.gameState == 30 && Lockdown.ENABLED) {
			SpectatorOverlay.draw();
		}
	}
}
