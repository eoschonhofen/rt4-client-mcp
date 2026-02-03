import java.awt.Container;
import java.awt.Insets;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static203 {

	@OriginalMember(owner = "client!qh", name = "a", descriptor = "Lclient!se;")
	public static MapElementList aClass134_1;

	@OriginalMember(owner = "client!qh", name = "d", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_8;

	@OriginalMember(owner = "client!qh", name = "e", descriptor = "[Lclient!ee;")
	public static WorldInfo[] aClass32Array1;

	@OriginalMember(owner = "client!qh", name = "c", descriptor = "Lclient!na;")
	public static final JagString aClass100_893 = JagString.wrap("Memory before cleanup=");

	@OriginalMember(owner = "client!qh", name = "i", descriptor = "Lclient!na;")
	public static final JagString aClass100_894 = JagString.wrap("Mem:");

	@OriginalMember(owner = "client!qh", name = "a", descriptor = "(Z)V")
	public static void method3662() {
		@Pc(8) Container local8;
		if (GameShell.aFrame2 != null) {
			local8 = GameShell.aFrame2;
		} else if (GameShell.frame == null) {
			local8 = GameShell.signlink.applet;
		} else {
			local8 = GameShell.frame;
		}
		GameShell.canvasWid = local8.getSize().width;
		GameShell.canvasHei = local8.getSize().height;
		@Pc(35) Insets local35;
		if (local8 == GameShell.frame) {
			local35 = GameShell.frame.getInsets();
			GameShell.canvasHei -= local35.bottom + local35.top;
			GameShell.canvasWid -= local35.right + local35.left;
		}
		if (Static144.method2736() >= 2) {
			GameShell.anInt1448 = GameShell.canvasWid;
			GameShell.anInt3497 = 0;
			GameShell.anInt4246 = 0;
			GameShell.anInt5554 = GameShell.canvasHei;
		} else {
			GameShell.anInt4246 = 0;
			GameShell.anInt3497 = (GameShell.canvasWid - 765) / 2;
			GameShell.anInt5554 = 503;
			GameShell.anInt1448 = 765;
		}
		if (GameShell.glRenderer) {
			Static239.method4181(GameShell.anInt1448, GameShell.anInt5554);
		}
		GameShell.canvas.setSize(GameShell.anInt1448, GameShell.anInt5554);
		if (local8 == GameShell.frame) {
			local35 = GameShell.frame.getInsets();
			GameShell.canvas.setLocation(local35.left + GameShell.anInt3497, GameShell.anInt4246 + local35.top);
		} else {
			GameShell.canvas.setLocation(GameShell.anInt3497, GameShell.anInt4246);
		}
		if (Static154.anInt3711 != -1) {
			Static210.method3712(true);
		}
		Static139.method2704();
	}

	@OriginalMember(owner = "client!qh", name = "a", descriptor = "(Lsignlink!ll;B)V")
	public static void method3663(@OriginalArg(0) SignLink arg0) {
		@Pc(11) FileOnDisk local11 = null;
		try {
			@Pc(16) PrivilegedRequest local16 = arg0.method5112("runescape");
			while (local16.status == 0) {
				Static231.sleepPrecise(1L);
			}
			if (local16.status == 1) {
				local11 = (FileOnDisk) local16.result;
				@Pc(39) Packet local39 = Static48.method1196();
				local11.method5134(local39.data, local39.pos, 0);
			}
		} catch (@Pc(49) Exception local49) {
		}
		try {
			if (local11 != null) {
				local11.method5136();
			}
		} catch (@Pc(56) Exception local56) {
		}
	}
}
