import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Method;
import java.net.URL;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rc")
public abstract class GameShell extends Applet implements Runnable, FocusListener, WindowListener {

	@OriginalMember(owner = "client!sh", name = "l", descriptor = "[J")
	public static final long[] updateTime = new long[32];

	@OriginalMember(owner = "client!ah", name = "k", descriptor = "[J")
	public static final long[] drawTime = new long[32];

	@OriginalMember(owner = "client!fk", name = "l", descriptor = "Lsignlink!ll;")
	public static SignLink signlink;

	@OriginalMember(owner = "client!d", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame frame;

	@OriginalMember(owner = "client!t", name = "m", descriptor = "Z")
	public static volatile boolean focus_in = true;

	@OriginalMember(owner = "client!fh", name = "P", descriptor = "Z")
	public static volatile boolean fullredraw = true;

	@OriginalMember(owner = "client!md", name = "L", descriptor = "Ljava/awt/Canvas;")
	public static Canvas canvas;

	@OriginalMember(owner = "client!fh", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame aFrame2;

	@OriginalMember(owner = "client!ca", name = "ab", descriptor = "Z")
	public static boolean focus;

	@OriginalMember(owner = "client!cl", name = "bb", descriptor = "Z")
	public static volatile boolean canvasReplaceRecommended = false;

	@OriginalMember(owner = "client!tk", name = "c", descriptor = "J")
	public static volatile long lastCanvasReplace = 0L;

	@OriginalMember(owner = "client!sj", name = "F", descriptor = "Lclient!rc;")
	public static GameShell shell = null;

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "Z")
	public static boolean alreadyshutdown = false;

	@OriginalMember(owner = "client!fl", name = "w", descriptor = "J")
	public static long killtime = 0L;

	@OriginalMember(owner = "client!tc", name = "f", descriptor = "Z")
	public static boolean aBoolean256 = false;

	@OriginalMember(owner = "client!tf", name = "w", descriptor = "Z")
	public static boolean glRenderer = false;

	@OriginalMember(owner = "client!fl", name = "U", descriptor = "I")
	public static int canvasWid;

	@OriginalMember(owner = "client!jh", name = "c", descriptor = "I")
	public static int canvasHei;

	@OriginalMember(owner = "client!ve", name = "F", descriptor = "I")
	public static int updatePos;

	@OriginalMember(owner = "client!ii", name = "i", descriptor = "I")
	public static int drawPos;

	@OriginalMember(owner = "client!tk", name = "v", descriptor = "I")
	public static int anInt5359 = 0;

	@OriginalMember(owner = "client!ol", name = "fb", descriptor = "I")
	public static int redrawNum = 500;

	@OriginalMember(owner = "client!dl", name = "d", descriptor = "I")
	public static int anInt1448;

	@OriginalMember(owner = "client!uj", name = "B", descriptor = "I")
	public static int anInt5554;

	@OriginalMember(owner = "client!lf", name = "f", descriptor = "I")
	public static int anInt3497 = 0;

	@OriginalMember(owner = "client!od", name = "e", descriptor = "I")
	public static int anInt4246 = 0;

	@OriginalMember(owner = "client!fi", name = "l", descriptor = "I")
	public static int loaded = 0;

	@OriginalMember(owner = "client!kd", name = "pb", descriptor = "I")
	public static int revision;

	@OriginalMember(owner = "client!da", name = "M", descriptor = "Z")
	public static boolean openwinjs;

	@OriginalMember(owner = "client!cm", name = "b", descriptor = "Ljava/lang/Thread;")
	public static Thread thread;

	@OriginalMember(owner = "client!sf", name = "d", descriptor = "I")
	public static int mindel = 1;

	@OriginalMember(owner = "client!sg", name = "p", descriptor = "I")
	public static int updateCount;

	@OriginalMember(owner = "client!qe", name = "v", descriptor = "Lclient!s;")
	public static Timer timer;

	@OriginalMember(owner = "client!ba", name = "B", descriptor = "I")
	public static int deltime = 20;

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "Z")
	private boolean alreadyerrored = false;

	@OriginalMember(owner = "client!rc", name = "providesignlink", descriptor = "(Lsignlink!ll;)V")
	public static void providesignlink(@OriginalArg(0) SignLink sign) {
		signlink = sign;
		JagException.signlink = sign;
	}

	@OriginalMember(owner = "client!ta", name = "a", descriptor = "(Z)V")
	public static void doneslowupdate() {
		timer.reset();

		for (int i = 0; i < 32; i++) {
			drawTime[i] = 0L;
		}

		for (int i = 0; i < 32; i++) {
			updateTime[i] = 0L;
		}

		updateCount = 0;
	}

	@OriginalMember(owner = "client!bc", name = "a", descriptor = "(Ljava/awt/Color;ZZLclient!na;I)V")
	public static void drawProgress(@OriginalArg(0) Color arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) JagString arg2, @OriginalArg(4) int arg3) {
		try {
			@Pc(6) Graphics local6 = canvas.getGraphics();
			if (Static222.aFont1 == null) {
				Static222.aFont1 = new Font("Helvetica", 1, 13);
				Static240.aFontMetrics1 = canvas.getFontMetrics(Static222.aFont1);
			}
			if (arg1) {
				local6.setColor(Color.black);
				local6.fillRect(0, 0, anInt1448, anInt5554);
			}
			if (arg0 == null) {
				arg0 = new Color(140, 17, 17);
			}
			try {
				if (Static149.anImage3 == null) {
					Static149.anImage3 = canvas.createImage(304, 34);
				}
				@Pc(56) Graphics local56 = Static149.anImage3.getGraphics();
				local56.setColor(arg0);
				local56.drawRect(0, 0, 303, 33);
				local56.fillRect(2, 2, arg3 * 3, 30);
				local56.setColor(Color.black);
				local56.drawRect(1, 1, 301, 31);
				local56.fillRect(arg3 * 3 + 2, 2, 300 - arg3 * 3, 30);
				local56.setFont(Static222.aFont1);
				local56.setColor(Color.white);
				arg2.method3112(22, (304 - arg2.method3155(Static240.aFontMetrics1)) / 2, local56);
				local6.drawImage(Static149.anImage3, anInt1448 / 2 - 152, anInt5554 / 2 + -18, null);
			} catch (@Pc(134) Exception local134) {
				@Pc(140) int local140 = anInt1448 / 2 - 152;
				@Pc(146) int local146 = anInt5554 / 2 - 18;
				local6.setColor(arg0);
				local6.drawRect(local140, local146, 303, 33);
				local6.fillRect(local140 + 2, local146 + 2, arg3 * 3, 30);
				local6.setColor(Color.black);
				local6.drawRect(local140 + 1, local146 - -1, 301, 31);
				local6.fillRect(arg3 * 3 + local140 + 2, local146 + 2, 300 - arg3 * 3, 30);
				local6.setFont(Static222.aFont1);
				local6.setColor(Color.white);
				arg2.method3112(local146 + 22, local140 + (-arg2.method3155(Static240.aFontMetrics1) + 304) / 2, local6);
			}
			if (Static278.aClass100_1102 != null) {
				local6.setFont(Static222.aFont1);
				local6.setColor(Color.white);
				Static278.aClass100_1102.method3112(anInt5554 / 2 - 26, anInt1448 / 2 - Static278.aClass100_1102.method3155(Static240.aFontMetrics1) / 2, local6);
			}
		} catch (@Pc(252) Exception local252) {
			canvas.repaint();
		}
	}

	@OriginalMember(owner = "client!rc", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusLost(@OriginalArg(0) FocusEvent e) {
		focus_in = false;
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(B)V")
	protected abstract void mainloop();

	@OriginalMember(owner = "client!rc", name = "windowClosing", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosing(@OriginalArg(0) WindowEvent e) {
		this.destroy();
	}

	@OriginalMember(owner = "client!rc", name = "windowIconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowIconified(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!rc", name = "windowDeactivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeactivated(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!rc", name = "getAppletContext", descriptor = "()Ljava/applet/AppletContext;")
	@Override
	public final AppletContext getAppletContext() {
        if (frame != null) {
            return null;
        }

        if (signlink != null && signlink.applet != this) {
            return signlink.applet.getAppletContext();
        }

        return super.getAppletContext();
    }

	@OriginalMember(owner = "client!rc", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent e) {
		focus_in = true;
		fullredraw = true;
	}

	@OriginalMember(owner = "client!rc", name = "windowClosed", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowClosed(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(I)Z")
	protected final boolean method925() {
		return true;
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(B)V")
	public final synchronized void addcanvas() {
		if (canvas != null) {
			canvas.removeFocusListener(this);
			canvas.getParent().remove(canvas);
		}

		@Pc(19) Container container;
		if (aFrame2 != null) {
			container = aFrame2;
		} else if (frame == null) {
			container = signlink.applet;
		} else {
			container = frame;
		}
		container.setLayout(null);

		canvas = new GameCanvas(this);
		container.add(canvas);

		canvas.setSize(anInt1448, anInt5554);
		canvas.setVisible(true);

		if (container == frame) {
			@Pc(66) Insets insets = frame.getInsets();
			canvas.setLocation(anInt3497 + insets.left, insets.top + anInt4246);
		} else {
			canvas.setLocation(anInt3497, anInt4246);
		}

		canvas.addFocusListener(this);
		canvas.requestFocus();

		focus_in = true;
		fullredraw = true;
		focus = true;
		canvasReplaceRecommended = false;
		lastCanvasReplace = MonotonicTime.currentTime();
	}

	@OriginalMember(owner = "client!rc", name = "destroy", descriptor = "()V")
	@Override
	public final void destroy() {
		if (shell == this && !alreadyshutdown) {
			killtime = MonotonicTime.currentTime();
			Static231.sleepPrecise(5000L);
			JagException.signlink = null;
			this.shutdown(false);
		}
	}

	@OriginalMember(owner = "client!rc", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final void update(@OriginalArg(0) Graphics g) {
		this.paint(g);
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(Ljava/lang/String;I)V")
	protected final void error(@OriginalArg(0) String err) {
		if (this.alreadyerrored) {
			return;
		}

		this.alreadyerrored = true;
		System.out.println("error_game_" + err);

		try {
			this.getAppletContext().showDocument(new URL(this.getCodeBase(), "error_game_" + err + ".ws"), "_top");
		} catch (@Pc(47) Exception ignore) {
		}
	}

	@OriginalMember(owner = "client!rc", name = "c", descriptor = "(B)V")
	protected abstract void mainquit();

	@OriginalMember(owner = "client!rc", name = "c", descriptor = "(I)V")
	protected abstract void onKilled();

	@OriginalMember(owner = "client!rc", name = "getDocumentBase", descriptor = "()Ljava/net/URL;")
	@Override
	public final URL getDocumentBase() {
        if (frame != null) {
			return null;
		}

        if (signlink != null && signlink.applet != this) {
            return signlink.applet.getDocumentBase();
        }

        return super.getDocumentBase();
    }

	@OriginalMember(owner = "client!rc", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public final synchronized void paint(@OriginalArg(0) Graphics g) {
		if (shell != this || alreadyshutdown) {
			return;
		}

		fullredraw = true;

		if (aBoolean256 && !glRenderer && MonotonicTime.currentTime() - lastCanvasReplace > 1000L) {
			@Pc(29) Rectangle bounds = g.getClipBounds();
			if (bounds == null || bounds.width >= canvasWid && canvasHei <= bounds.height) {
				canvasReplaceRecommended = true;
			}
		}
	}

	@OriginalMember(owner = "client!rc", name = "windowDeiconified", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowDeiconified(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(IZ)V")
	private void shutdown(@OriginalArg(1) boolean clean) {
		synchronized (this) {
			if (alreadyshutdown) {
				return;
			}

			alreadyshutdown = true;
		}

		if (signlink.applet != null) {
			signlink.applet.destroy();
		}

		try {
			this.mainquit();
		} catch (@Pc(34) Exception ignore) {
		}

		if (canvas != null) {
			try {
				canvas.removeFocusListener(this);
				canvas.getParent().remove(canvas);
			} catch (@Pc(45) Exception ignore) {
			}
		}

		if (signlink != null) {
			try {
				signlink.close();
			} catch (@Pc(53) Exception ignore) {
			}
		}

		this.onKilled();

		if (frame != null) {
			try {
				System.exit(0);
			} catch (@Pc(77) Throwable ignore) {
			}
		}

		System.out.println("Shutdown complete - clean:" + clean);
	}

	@OriginalMember(owner = "client!rc", name = "windowActivated", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowActivated(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!rc", name = "b", descriptor = "(Z)V")
	private void mainloopwrapper() {
		@Pc(6) long local6 = MonotonicTime.currentTime();
		@Pc(10) long local10 = updateTime[updatePos];
		updateTime[updatePos] = local6;
		updatePos = updatePos + 1 & 0x1F;

		synchronized (this) {
			focus = focus_in;
		}

		this.mainloop();

		if (local10 != 0L && local6 <= local10) {
			// lps
		}
	}

	@OriginalMember(owner = "client!rc", name = "e", descriptor = "(I)V")
	private void mainredrawwrapper() {
		@Pc(2) long local2 = MonotonicTime.currentTime();
		@Pc(6) long local6 = drawTime[drawPos];
		drawTime[drawPos] = local2;
		drawPos = drawPos + 1 & 0x1F;

		if (local6 != 0L && local2 > local6) {
			@Pc(41) int local41 = (int) (local2 - local6);
			anInt5359 = ((local41 >> 1) + 32000) / local41;
		}

		if (redrawNum++ > 50) {
			fullredraw = true;
			redrawNum -= 50;

			canvas.setSize(anInt1448, anInt5554);
			canvas.setVisible(true);

			if (frame != null && aFrame2 == null) {
				@Pc(84) Insets insets = frame.getInsets();
				canvas.setLocation(insets.left + anInt3497, anInt4246 + insets.top);
			} else {
				canvas.setLocation(anInt3497, anInt4246);
			}
		}

		this.mainredraw();
	}

	@OriginalMember(owner = "client!rc", name = "f", descriptor = "(I)V")
	protected abstract void mainredraw();

	@OriginalMember(owner = "client!rc", name = "getCodeBase", descriptor = "()Ljava/net/URL;")
	@Override
	public final URL getCodeBase() {
        if (frame != null) {
			return null;
		}

        if (signlink != null && signlink.applet != this) {
            return signlink.applet.getCodeBase();
        }

        return super.getCodeBase();
    }

	@OriginalMember(owner = "client!rc", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		try {
			if (SignLink.javaVendor != null) {
				@Pc(12) String vendor = SignLink.javaVendor.toLowerCase();
				if (vendor.indexOf("sun") != -1 || vendor.indexOf("apple") != -1) {
					@Pc(24) String version = SignLink.javaVersion;

					if (version.equals("1.1") || version.startsWith("1.1.") || version.equals("1.2") || version.startsWith("1.2.")) {
						this.error("wrongjava");
						return;
					}

					mindel = 5;
				} else if (vendor.indexOf("ibm") != -1 && (SignLink.javaVersion == null || SignLink.javaVersion.equals("1.4.2"))) {
					this.error("wrongjava");
					return;
				}
			}

			@Pc(76) int local76;
			if (SignLink.javaVersion != null && SignLink.javaVersion.startsWith("1.")) {
				local76 = 2;
				@Pc(78) int local78 = 0;
				while (local76 < SignLink.javaVersion.length()) {
					@Pc(90) char local90 = SignLink.javaVersion.charAt(local76);
					if (local90 < '0' || local90 > '9') {
						break;
					}
					local78 = local78 * 10 + local90 - 48;
					local76++;
				}
				if (local78 >= 5) {
					aBoolean256 = true;
				}
			}

			if (signlink.applet != null) {
				@Pc(125) Method local125 = SignLink.setFocusCycleRoot;
				if (local125 != null) {
					try {
						local125.invoke(signlink.applet, Boolean.TRUE);
					} catch (@Pc(142) Throwable ignore) {
					}
				}
			}

			Static224.method3888();
			this.addcanvas();
			Static260.drawArea = Static131.method2579(anInt5554, anInt1448, canvas);
			this.maininit();
			timer = Static70.method1547();

			while (killtime == 0L || killtime > MonotonicTime.currentTime()) {
				updateCount = timer.count(mindel, deltime);

				for (int i = 0; i < updateCount; i++) {
					this.mainloopwrapper();
				}

				this.mainredrawwrapper();

				Static140.flushEvents(signlink, canvas);
			}
		} catch (@Pc(198) Exception ex) {
			JagException.report(null, ex);
			this.error("crash");
		}

		this.shutdown(true);
	}

	@OriginalMember(owner = "client!rc", name = "getParameter", descriptor = "(Ljava/lang/String;)Ljava/lang/String;")
	@Override
	public final String getParameter(@OriginalArg(0) String name) {
        if (frame != null) {
			return null;
		}

        if (signlink != null && signlink.applet != this) {
            return signlink.applet.getParameter(name);
        }

        return super.getParameter(name);
    }

	@OriginalMember(owner = "client!rc", name = "g", descriptor = "(I)V")
	protected abstract void maininit();

	@OriginalMember(owner = "client!rc", name = "stop", descriptor = "()V")
	@Override
	public final void stop() {
		if (shell == this && !alreadyshutdown) {
			killtime = MonotonicTime.currentTime() + 4000L;
		}
	}

	@OriginalMember(owner = "client!rc", name = "init", descriptor = "()V")
	public abstract void init();

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(IIZILjava/lang/String;III)V")
	protected final void method936(@OriginalArg(0) int filestore, @OriginalArg(4) String game) {
		try {
			anInt5554 = 768;
			canvasHei = 768;
			anInt3497 = 0;
			revision = 530;
			anInt1448 = 1024;
			canvasWid = 1024;
			anInt4246 = 0;

			shell = this;
			frame = new Frame();
			frame.setTitle("Jagex");
			frame.setResizable(true);
			frame.addWindowListener(this);
			frame.setVisible(true);
			frame.toFront();

			@Pc(44) Insets insets = frame.getInsets();
			frame.setSize(insets.left + canvasWid + insets.right, insets.top + canvasHei + insets.bottom);

			JagException.signlink = signlink = new SignLink(null, filestore, game, 28);

			@Pc(76) PrivilegedRequest req = signlink.threadreq(1, this);
			while (req.status == 0) {
				Static231.sleepPrecise(10L);
			}
			thread = (Thread) req.result;
		} catch (@Pc(91) Exception ex) {
			JagException.report(null, ex);
		}
	}

	@OriginalMember(owner = "client!rc", name = "windowOpened", descriptor = "(Ljava/awt/event/WindowEvent;)V")
	@Override
	public final void windowOpened(@OriginalArg(0) WindowEvent e) {
	}

	@OriginalMember(owner = "client!rc", name = "start", descriptor = "()V")
	@Override
	public final void start() {
		if (shell == this && !alreadyshutdown) {
			killtime = 0L;
		}
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(BIIII)V")
	protected final void startCommon(@OriginalArg(2) int arg0) {
		try {
			if (shell != null) {
				loaded++;

				if (loaded >= 3) {
					this.error("alreadyloaded");
					return;
				}

				this.getAppletContext().showDocument(this.getDocumentBase(), "_self");
				return;
			}

			shell = this;
			anInt4246 = 0;
			revision = 1530;
			anInt1448 = 765;
			canvasWid = 765;
			anInt3497 = 0;
			anInt5554 = 503;
			canvasHei = 503;

			@Pc(54) String local54 = this.getParameter("openwinjs");
			if (local54 != null && local54.equals("1")) {
				openwinjs = true;
			} else {
				openwinjs = false;
			}

			if (signlink == null) {
				JagException.signlink = signlink = new SignLink(this, arg0, null, 0);
			}

			@Pc(86) PrivilegedRequest local86 = signlink.threadreq(1, this);
			while (local86.status == 0) {
				Static231.sleepPrecise(10L);
			}

			thread = (Thread) local86.result;
		} catch (@Pc(103) Exception ex) {
			JagException.report(null, ex);
			this.error("crash");
		}
	}
}
