import java.awt.Point;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static81 {

	@OriginalMember(owner = "client!gg", name = "Z", descriptor = "I")
	public static int anInt2222;

	@OriginalMember(owner = "client!gg", name = "bb", descriptor = "I")
	public static int anInt2223;

	@OriginalMember(owner = "client!gg", name = "W", descriptor = "Lclient!na;")
	public static final JagString aClass100_475 = JagString.wrap("null");

	@OriginalMember(owner = "client!gg", name = "Y", descriptor = "Lclient!na;")
	public static final JagString aClass100_476 = JagString.wrap("::gc");

	@OriginalMember(owner = "client!gg", name = "c", descriptor = "(II)V")
	public static void method1750(@OriginalArg(0) int arg0) {
		if (!Static64.aBoolean111) {
			arg0 = -1;
		}
		if (arg0 == Static115.anInt2941) {
			return;
		}
		if (arg0 != -1) {
			@Pc(24) CursorType local24 = CursorType.method3660(arg0);
			@Pc(28) Pix32 local28 = local24.method2246();
			if (local28 == null) {
				arg0 = -1;
			} else {
				GameShell.signlink.method5113(local28.method301(), local28.anInt1860, GameShell.canvas, new Point(local24.anInt2852, local24.anInt2850), local28.anInt1866);
				Static115.anInt2941 = arg0;
			}
		}
		if (arg0 == -1 && Static115.anInt2941 != -1) {
			GameShell.signlink.method5113(null, -1, GameShell.canvas, new Point(), -1);
			Static115.anInt2941 = -1;
		}
	}

	@OriginalMember(owner = "client!gg", name = "a", descriptor = "([[IZ)V")
	public static void method1751(@OriginalArg(0) int[][] arg0) {
		Static71.anIntArrayArray10 = arg0;
	}

	@OriginalMember(owner = "client!gg", name = "a", descriptor = "(ILclient!ve;)V")
	public static void method1754(@OriginalArg(1) Js5 arg0) {
		Static138.anInt3443 = arg0.getGroupId(Static12.aClass100_73);
	}

}
