import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static269 {

	@OriginalMember(owner = "client!wa", name = "z", descriptor = "[I")
	public static int[] anIntArray252;

	@OriginalMember(owner = "client!wa", name = "pb", descriptor = "Lclient!na;")
	public static final JagString aClass100_556 = JagString.wrap("<br>");

	@OriginalMember(owner = "client!wa", name = "Eb", descriptor = "[Lclient!bg;")
	public static final Js5CachedResourceProvider[] js5Providers = new Js5CachedResourceProvider[28];

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(Z)V")
	public static void method2170() {
		Static250.anInt5434++;
	}

	@OriginalMember(owner = "client!wa", name = "e", descriptor = "(B)V")
	public static void method2172() {
		ObjType.aClass99_16.method3104();
	}

	@OriginalMember(owner = "client!wa", name = "o", descriptor = "(I)V")
	public static void method2218() {
		@Pc(8) int local8 = Static236.method4047();
		if (local8 == 0) {
			Static266.aByteArrayArrayArray15 = null;
			Static232.method3993(0);
		} else if (local8 == 1) {
			Static38.method960((byte) 0);
			Static232.method3993(512);
			Static132.method2608();
		} else {
			Static38.method960((byte) (Static136.anInt3325 - 4 & 0xFF));
			Static232.method3993(2);
		}
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(IIIII)V")
	public static void method2225(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (WorldMap.stage < 100) {
			WorldMap.method3413();
		}
		if (GameShell.glRenderer) {
			Static46.method1187(arg0, arg1, arg0 + arg3, arg2 + arg1);
		} else {
			Pix2D.method2496(arg0, arg1, arg0 + arg3, arg2 + arg1);
		}
		@Pc(50) int local50;
		@Pc(61) int local61;
		if (WorldMap.stage < 100) {
			local50 = arg0 + arg3 / 2;
			local61 = arg2 / 2 + arg1 - 18 - 20;
			if (GameShell.glRenderer) {
				Static46.method1186(arg0, arg1, arg3, arg2, 0);
				Static46.method1179(local50 - 152, local61, 304, 34, 9179409);
				Static46.method1179(local50 - 151, local61 + 1, 302, 32, 0);
				Static46.method1186(local50 - 150, local61 + 2, WorldMap.stage * 3, 30, 9179409);
				Static46.method1186(local50 + WorldMap.stage * 3 - 150, local61 - -2, 300 - WorldMap.stage * 3, 30, 0);
			} else {
				Pix2D.method2495(arg0, arg1, arg3, arg2, 0);
				Pix2D.method2483(local50 - 152, local61, 304, 34, 9179409);
				Pix2D.method2483(local50 - 151, local61 + 1, 302, 32, 0);
				Pix2D.method2495(local50 - 150, local61 + 2, WorldMap.stage * 3, 30, 9179409);
				Pix2D.method2495(WorldMap.stage * 3 + local50 - 150, local61 - -2, 300 - WorldMap.stage * 3, 30, 0);
			}
			Static280.aClass3_Sub2_Sub9_43.method2875(Text.aClass100_349, local50, local61 + 20, 16777215, -1);
			return;
		}
		Static37.anInt1176 = (int) ((float) (arg2 * 2) / WorldMap.aFloat3);
		Static109.anInt2882 = WorldMap.anInt435 - (int) ((float) arg3 / WorldMap.aFloat3);
		@Pc(211) int local211 = WorldMap.anInt435 - (int) ((float) arg3 / WorldMap.aFloat3);
		local50 = WorldMap.anInt919 - (int) ((float) arg2 / WorldMap.aFloat3);
		Static109.anInt2884 = WorldMap.anInt919 - (int) ((float) arg2 / WorldMap.aFloat3);
		@Pc(236) int local236 = WorldMap.anInt919 + (int) ((float) arg2 / WorldMap.aFloat3);
		local61 = (int) ((float) arg3 / WorldMap.aFloat3) + WorldMap.anInt435;
		Static89.anInt2387 = (int) ((float) (arg3 * 2) / WorldMap.aFloat3);
		if (GameShell.glRenderer) {
			if (Static153.aClass3_Sub2_Sub1_Sub1_2 == null || Static153.aClass3_Sub2_Sub1_Sub1_2.anInt1867 != arg3 || Static153.aClass3_Sub2_Sub1_Sub1_2.anInt1859 != arg2) {
				Static153.aClass3_Sub2_Sub1_Sub1_2 = null;
				Static153.aClass3_Sub2_Sub1_Sub1_2 = new Pix32(arg3, arg2);
			}
			Pix2D.method2491(Static153.aClass3_Sub2_Sub1_Sub1_2.anIntArray20, arg3, arg2);
			Static214.method4364(arg3, 0, local61, local50, 0, local236, arg2, local211);
			Static48.method1195(arg3, 0, local61, local236, arg2, 0, local211, local50);
			WorldMap.method959(0, 0, local211, arg3, local236, local50, local61, arg2);
			Static46.method1178(Static153.aClass3_Sub2_Sub1_Sub1_2.anIntArray20, arg0, arg1, arg3, arg2);
			Pix2D.anIntArray297 = null;
		} else {
			Static214.method4364(arg3 + arg0, arg1, local61, local50, arg0, local236, arg1 + arg2, local211);
			Static48.method1195(arg0 + arg3, arg0, local61, local236, arg2 + arg1, arg1, local211, local50);
			WorldMap.method959(arg0, arg1, local211, arg0 + arg3, local236, local50, local61, arg2 + arg1);
		}
		if (Static201.anInt1864 > 0) {
			Static91.anInt2428--;
			if (Static91.anInt2428 == 0) {
				Static91.anInt2428 = 20;
				Static201.anInt1864--;
			}
		}
		if (!Static43.aBoolean82) {
			return;
		}
		@Pc(405) int local405 = arg1 + arg2 - 8;
		@Pc(412) int local412 = arg0 + arg3 - 5;
		Static215.aClass3_Sub2_Sub9_32.method2864(JagString.join(new JagString[] { Static115.aClass100_579, JagString.parseInt(GameShell.anInt5359) }), local412, local405, 16776960, -1);
		@Pc(434) Runtime local434 = Runtime.getRuntime();
		@Pc(443) int local443 = (int) ((local434.totalMemory() - local434.freeMemory()) / 1024L);
		@Pc(445) int local445 = 16776960;
		@Pc(446) int local446 = local405 - 15;
		if (local443 > 65536) {
			local445 = 16711680;
		}
		Static215.aClass3_Sub2_Sub9_32.method2864(JagString.join(new JagString[] { Static203.aClass100_894, JagString.parseInt(local443), Static19.aClass100_112 }), local412, local446, local445, -1);
		local405 = local446 - 15;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
	public static JagString method2228(@OriginalArg(0) int arg0) {
		return Client.aClass100Array160[arg0].length() > 0 ? JagString.join(new JagString[] { Client.aClass100Array168[arg0], Text.aClass100_901, Client.aClass100Array160[arg0] }) : Client.aClass100Array168[arg0];
	}
}
