import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static197 {

	@OriginalMember(owner = "client!pm", name = "cb", descriptor = "[[[Lclient!bj;")
	public static Square[][][] aClass3_Sub5ArrayArrayArray2;

	@OriginalMember(owner = "client!pm", name = "hb", descriptor = "I")
	public static int anInt4620;

	@OriginalMember(owner = "client!pm", name = "Y", descriptor = "Lclient!na;")
	public static final JagString aClass100_872 = JagString.wrap("<br>");

	@OriginalMember(owner = "client!pm", name = "ab", descriptor = "Z")
	public static boolean aBoolean228 = true;

	@OriginalMember(owner = "client!pm", name = "gb", descriptor = "Lclient!na;")
	private static final JagString aClass100_874 = JagString.wrap(" from your friend list first)3");

	@OriginalMember(owner = "client!pm", name = "fb", descriptor = "Lclient!na;")
	public static JagString aClass100_873 = aClass100_874;

	@OriginalMember(owner = "client!pm", name = "a", descriptor = "(ILsignlink!ll;)[Lclient!od;")
	public static DisplayMode[] method3558(@OriginalArg(1) SignLink arg0) {
		if (!arg0.method5111()) {
			return new DisplayMode[0];
		}
		@Pc(17) PrivilegedRequest local17 = arg0.method5132();
		while (local17.status == 0) {
			Static231.sleepPrecise(10L);
		}
		if (local17.status == 2) {
			return new DisplayMode[0];
		}
		@Pc(39) int[] local39 = (int[]) local17.result;
		@Pc(45) DisplayMode[] local45 = new DisplayMode[local39.length >> 2];
		for (@Pc(47) int local47 = 0; local47 < local45.length; local47++) {
			@Pc(59) DisplayMode local59 = new DisplayMode();
			local45[local47] = local59;
			local59.anInt4248 = local39[local47 << 2];
			local59.anInt4250 = local39[(local47 << 2) + 1];
			local59.anInt4251 = local39[(local47 << 2) + 2];
			local59.anInt4249 = local39[(local47 << 2) + 3];
		}
		return local45;
	}

	@OriginalMember(owner = "client!pm", name = "a", descriptor = "(ZIZIZII)V")
	public static void method3560(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		if (arg2) {
			Static239.method4169();
		}
		if (GameShell.aFrame2 != null && (arg1 != 3 || arg4 != Static114.anInt5831 || arg5 != Static22.anInt729)) {
			Static25.method714(GameShell.aFrame2, GameShell.signlink);
			GameShell.aFrame2 = null;
		}
		if (arg1 == 3 && GameShell.aFrame2 == null) {
			GameShell.aFrame2 = Static169.method3176(0, arg5, arg4, GameShell.signlink);
			if (GameShell.aFrame2 != null) {
				Static22.anInt729 = arg5;
				Static114.anInt5831 = arg4;
				Static203.method3663(GameShell.signlink);
			}
		}
		if (arg1 == 3 && GameShell.aFrame2 == null) {
			method3560(true, Static214.anInt5581, true, arg3, -1, -1);
			return;
		}
		@Pc(85) Container local85;
		if (GameShell.aFrame2 != null) {
			local85 = GameShell.aFrame2;
		} else if (GameShell.frame == null) {
			local85 = GameShell.signlink.applet;
		} else {
			local85 = GameShell.frame;
		}
		GameShell.canvasWid = local85.getSize().width;
		GameShell.canvasHei = local85.getSize().height;
		@Pc(109) Insets local109;
		if (GameShell.frame == local85) {
			local109 = GameShell.frame.getInsets();
			GameShell.canvasWid -= local109.right + local109.left;
			GameShell.canvasHei -= local109.bottom + local109.top;
		}
		if (arg1 >= 2) {
			GameShell.anInt1448 = GameShell.canvasWid;
			GameShell.anInt5554 = GameShell.canvasHei;
			GameShell.anInt3497 = 0;
			GameShell.anInt4246 = 0;
		} else {
			GameShell.anInt4246 = 0;
			GameShell.anInt3497 = (GameShell.canvasWid - 765) / 2;
			GameShell.anInt1448 = 765;
			GameShell.anInt5554 = 503;
		}
		if (arg0) {
			Static31.shutdown(GameShell.canvas);
			Static223.shutdown(GameShell.canvas);
			if (client.mouseWheel != null) {
				client.mouseWheel.removeListeners(GameShell.canvas);
			}
			Static215.client.addcanvas();
			Static19.method591(GameShell.canvas);
			Static88.method1833(GameShell.canvas);
			if (client.mouseWheel != null) {
				client.mouseWheel.addListeners(GameShell.canvas);
			}
		} else {
			if (GameShell.glRenderer) {
				Static239.method4181(GameShell.anInt1448, GameShell.anInt5554);
			}
			GameShell.canvas.setSize(GameShell.anInt1448, GameShell.anInt5554);
			if (GameShell.frame == local85) {
				local109 = GameShell.frame.getInsets();
				GameShell.canvas.setLocation(local109.left + GameShell.anInt3497, local109.top + GameShell.anInt4246);
			} else {
				GameShell.canvas.setLocation(GameShell.anInt3497, GameShell.anInt4246);
			}
		}
		if (arg1 == 0 && arg3 > 0) {
			Static239.method4161(GameShell.canvas);
		}
		if (arg2 && arg1 > 0) {
			GameShell.canvas.setIgnoreRepaint(true);
			if (!Static211.aBoolean73) {
				Static65.method1500();
				Static260.drawArea = null;
				Static260.drawArea = Static131.method2579(GameShell.anInt5554, GameShell.anInt1448, GameShell.canvas);
				Static129.method2492();
				if (client.state == 5) {
					Static182.method3359(true, Static280.aClass3_Sub2_Sub9_43);
				} else {
					Static114.messageBox(false, Static170.aClass100_621);
				}
				try {
					@Pc(269) Graphics local269 = GameShell.canvas.getGraphics();
					Static260.drawArea.method4186(local269);
				} catch (@Pc(277) Exception local277) {
				}
				Static139.method2704();
				if (arg3 == 0) {
					Static260.drawArea = Static131.method2579(503, 765, GameShell.canvas);
				} else {
					Static260.drawArea = null;
				}
				@Pc(300) PrivilegedRequest local300 = GameShell.signlink.method5123(Static215.client.getClass());
				while (local300.status == 0) {
					Static231.sleepPrecise(100L);
				}
				if (local300.status == 1) {
					Static211.aBoolean73 = true;
				}
			}
			if (Static211.aBoolean73) {
				Static239.method4180(GameShell.canvas, Static186.anInt4392 * 2);
			}
		}
		if (!GameShell.glRenderer && arg1 > 0) {
			method3560(true, 0, true, arg3, -1, -1);
			return;
		}
		if (arg1 > 0 && arg3 == 0) {
			GameShell.thread.setPriority(5);
			Static260.drawArea = null;
			Static268.method4580();
			((WorldTextureProvider) Static94.anInterface1_2).method3248(200);
			if (Static178.highDetailLighting) {
				Static94.method1911(0.7F);
			}
			Static114.method4637();
		} else if (arg1 == 0 && arg3 > 0) {
			GameShell.thread.setPriority(1);
			Static260.drawArea = Static131.method2579(503, 765, GameShell.canvas);
			Static268.method4583();
			Static76.method1643();
			((WorldTextureProvider) Static94.anInterface1_2).method3248(20);
			if (Static178.highDetailLighting) {
				if (Static113.anInt4609 == 1) {
					Static94.method1911(0.9F);
				}
				if (Static113.anInt4609 == 2) {
					Static94.method1911(0.8F);
				}
				if (Static113.anInt4609 == 3) {
					Static94.method1911(0.7F);
				}
				if (Static113.anInt4609 == 4) {
					Static94.method1911(0.6F);
				}
			}
			Static95.method1939();
			Static114.method4637();
		}
		Static87.aBoolean130 = !Static138.method2697();
		if (arg2) {
			Static141.method2721();
		}
		if (arg1 >= 2) {
			Static124.aBoolean156 = true;
		} else {
			Static124.aBoolean156 = false;
		}
		if (Static154.anInt3711 != -1) {
			Static210.method3712(true);
		}
		if (Static124.loginStream != null && (client.state == 30 || client.state == 25)) {
			Static59.method1373();
		}
		for (@Pc(466) int local466 = 0; local466 < 100; local466++) {
			Static186.aBooleanArray100[local466] = true;
		}
		GameShell.fullredraw = true;
	}
}
