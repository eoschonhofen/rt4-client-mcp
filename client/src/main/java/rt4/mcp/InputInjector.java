package rt4.mcp;

import rt4.GameShell;
import rt4.Keyboard;
import rt4.Mouse;

import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * MCP-10 — injects synthetic keyboard and mouse input through the client's own listeners,
 * exactly as a human would.
 *
 * <p>Key events go through {@link Keyboard} on the game thread (its handlers are
 * {@code synchronized}). Mouse events must be delivered on the AWT event thread, so they are
 * posted with {@link EventQueue#invokeLater}; while they are being delivered
 * {@link Mouse#syntheticPress} is set so the "a human click cancels walk_to" rule can ignore
 * them.</p>
 */
public final class InputInjector {
	public static final int MAX_TEXT_LENGTH = 80;
	public static final long MILLIS_PER_FRAME = 20L;

	private static final ReleaseQueue PENDING = new ReleaseQueue();

	/** Breaks ties between releases due on the same frame: first held, first released. */
	private static final AtomicLong NEXT_SEQUENCE = new AtomicLong();

	/** One key press waiting for its release deadline. */
	static final class PendingRelease {
		final int virtualKey;
		final long releaseFrame;
		final long sequence = NEXT_SEQUENCE.getAndIncrement();

		PendingRelease(int virtualKey, long releaseFrame) {
			this.virtualKey = virtualKey;
			this.releaseFrame = releaseFrame;
		}
	}

	/**
	 * MCP-26 — releases in deadline order, and in hold order on the same frame. A plain FIFO stopped at the first entry that was not
	 * due yet, so a short hold queued behind a long one waited for the long one to expire.
	 */
	static final class ReleaseQueue {
		private final PriorityQueue<PendingRelease> queue = new PriorityQueue<PendingRelease>(
				new Comparator<PendingRelease>() {
					@Override
					public int compare(PendingRelease left, PendingRelease right) {
						int byFrame = Long.compare(left.releaseFrame, right.releaseFrame);
						return byFrame != 0 ? byFrame : Long.compare(left.sequence, right.sequence);
					}
				});

		synchronized void add(PendingRelease release) {
			queue.add(release);
		}

		synchronized boolean isEmpty() {
			return queue.isEmpty();
		}

		synchronized int size() {
			return queue.size();
		}

		synchronized void clear() {
			queue.clear();
		}

		/** Removes and returns every release due at or before {@code frame}, earliest first. */
		synchronized List<PendingRelease> due(long frame) {
			List<PendingRelease> released = new ArrayList<PendingRelease>();
			while (!queue.isEmpty() && queue.peek().releaseFrame <= frame) {
				released.add(queue.poll());
			}
			return released;
		}
	}

	private InputInjector() {
	}

	public static Canvas canvas() throws ToolException {
		Canvas canvas = GameShell.canvas;
		if (canvas == null) {
			throw new ToolException("the game window is not ready yet");
		}
		return canvas;
	}

	// ------------------------------------------------------------------ keyboard

	/** Types text one character at a time, then optionally presses Enter. */
	public static void typeText(String text, boolean enter) throws ToolException {
		Canvas canvas = canvas();
		if (text == null) {
			text = "";
		}
		if (text.length() > MAX_TEXT_LENGTH) {
			throw new ToolException("text is longer than the client's " + MAX_TEXT_LENGTH + " character limit");
		}
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (!KeyMap.isTypable(c)) {
				throw new ToolException("character '" + c + "' cannot be typed by the client");
			}
			int virtualKey = KeyMap.charKey(c);
			if (virtualKey < 0) {
				throw new ToolException("character '" + c + "' has no key mapping");
			}
			int modifiers = KeyMap.needsShift(c) ? InputEvent.SHIFT_DOWN_MASK : 0;
			Keyboard keyboard = Keyboard.instance;
			if (keyboard == null) {
				throw new ToolException("the client is shutting down");
			}
			keyboard.keyPressed(keyEvent(canvas, KeyEvent.KEY_PRESSED, modifiers, virtualKey, KeyEvent.CHAR_UNDEFINED));
			keyboard.keyTyped(keyEvent(canvas, KeyEvent.KEY_TYPED, modifiers, KeyEvent.VK_UNDEFINED, (char) KeyMap.clientChar(c)));
			keyboard.keyReleased(keyEvent(canvas, KeyEvent.KEY_RELEASED, modifiers, virtualKey, KeyEvent.CHAR_UNDEFINED));
		}
		if (enter) {
			pressKey("enter", 0L);
		}
	}

	/** Presses a named key, releasing it immediately or after {@code holdMs}. */
	public static void pressKey(String name, long holdMs) throws ToolException {
		Canvas canvas = canvas();
		int virtualKey = KeyMap.namedKey(name);
		if (virtualKey < 0) {
			throw new ToolException("unknown key '" + name + "'");
		}
		Keyboard keyboard = Keyboard.instance;
		if (keyboard == null) {
			throw new ToolException("the client is shutting down");
		}
		keyboard.keyPressed(keyEvent(canvas, KeyEvent.KEY_PRESSED, 0, virtualKey, KeyEvent.CHAR_UNDEFINED));
		if (holdMs <= 0L) {
			keyboard.keyReleased(keyEvent(canvas, KeyEvent.KEY_RELEASED, 0, virtualKey, KeyEvent.CHAR_UNDEFINED));
			return;
		}
		long frames = Math.max(1L, holdMs / MILLIS_PER_FRAME);
		PENDING.add(new PendingRelease(virtualKey, GameThread.frame() + frames));
	}

	/**
	 * Runs from {@code GameThread.drain()} every frame and releases keys whose hold expired.
	 * No-op while nothing is held, so it is safe to call from tests.
	 */
	public static void tick() {
		if (PENDING.isEmpty()) {
			return;
		}
		long frame = GameThread.frame();
		Canvas canvas = GameShell.canvas;
		Keyboard keyboard = Keyboard.instance;
		if (canvas == null || keyboard == null) {
			PENDING.clear();
			return;
		}
		for (PendingRelease release : PENDING.due(frame)) {
			keyboard.keyReleased(keyEvent(canvas, KeyEvent.KEY_RELEASED, 0, release.virtualKey, KeyEvent.CHAR_UNDEFINED));
		}
	}

	public static int pendingReleases() {
		return PENDING.size();
	}

	private static KeyEvent keyEvent(Canvas canvas, int id, int modifiers, int virtualKey, char character) {
		return new KeyEvent(canvas, id, System.currentTimeMillis(), modifiers, virtualKey, character);
	}

	// ------------------------------------------------------------------ mouse

	/** Posts a synthetic mouse click (or just a move) to the AWT event thread. */
	public static void click(final int x, final int y, final String button, final boolean moveOnly) throws ToolException {
		final Canvas canvas = canvas();
		final int buttonId = mouseButton(button);
		final int pressModifiers = buttonModifiers(buttonId);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				boolean previous = Mouse.syntheticPress;
				Mouse.syntheticPress = true;
				try {
					canvas.dispatchEvent(mouseEvent(canvas, MouseEvent.MOUSE_MOVED, x, y, 0, MouseEvent.NOBUTTON, 0));
					if (!moveOnly) {
						canvas.dispatchEvent(mouseEvent(canvas, MouseEvent.MOUSE_PRESSED, x, y, pressModifiers, buttonId, 1));
						canvas.dispatchEvent(mouseEvent(canvas, MouseEvent.MOUSE_RELEASED, x, y, 0, buttonId, 1));
						canvas.dispatchEvent(mouseEvent(canvas, MouseEvent.MOUSE_CLICKED, x, y, 0, buttonId, 1));
					}
				} finally {
					Mouse.syntheticPress = previous;
				}
			}
		});
	}

	private static MouseEvent mouseEvent(Canvas canvas, int id, int x, int y, int modifiers, int button, int clicks) {
		return new MouseEvent(canvas, id, System.currentTimeMillis(), modifiers, x, y, clicks, false, button);
	}

	private static int mouseButton(String button) throws ToolException {
		if (button == null || button.isEmpty() || "left".equalsIgnoreCase(button)) {
			return MouseEvent.BUTTON1;
		}
		if ("right".equalsIgnoreCase(button)) {
			return MouseEvent.BUTTON3;
		}
		if ("middle".equalsIgnoreCase(button)) {
			return MouseEvent.BUTTON2;
		}
		throw new ToolException("unknown mouse button '" + button + "'; use left, right or middle");
	}

	private static int buttonModifiers(int buttonId) {
		if (buttonId == MouseEvent.BUTTON3) {
			return InputEvent.BUTTON3_DOWN_MASK;
		}
		if (buttonId == MouseEvent.BUTTON2) {
			return InputEvent.BUTTON2_DOWN_MASK;
		}
		return InputEvent.BUTTON1_DOWN_MASK;
	}
}
