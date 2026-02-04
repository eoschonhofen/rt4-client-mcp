import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static141 {

	@OriginalMember(owner = "client!lb", name = "z", descriptor = "I")
	public static int anInt3473;

	@OriginalMember(owner = "client!lb", name = "s", descriptor = "Lclient!na;")
	public static final JagString aClass100_664 = JagString.wrap(":clan:");

	@OriginalMember(owner = "client!lb", name = "u", descriptor = "I")
	public static int anInt3469 = 0;

	@OriginalMember(owner = "client!lb", name = "A", descriptor = "I")
	public static int anInt3474 = 0;

	@OriginalMember(owner = "client!lb", name = "D", descriptor = "Lclient!na;")
	public static final JagString aClass100_666 = Text.aClass100_665;

	@OriginalMember(owner = "client!lb", name = "d", descriptor = "(B)V")
	public static void method2720() {
		if (Static153.aClass100_724 != null) {
			Static90.method1853(Static153.aClass100_724);
			Static153.aClass100_724 = null;
		}
	}

	@OriginalMember(owner = "client!lb", name = "a", descriptor = "(Z)V")
	public static void method2721() {
		World.method1500();
		Static89.aClass3_Sub2_Sub1_5 = null;
		Static107.anInt2875 = -1;
		Client.method3768();
		Static255.aClass54_16.method1815();
		Static171.aClass139_1 = new Class139();
		((WorldTextureProvider) Pix3D.anInterface1_2).method3247();
		World.anInt3034 = 0;
		World.aClass51Array1 = new Light[255];
		Static237.method4120();
		Static242.method4203();
		Static115.method2315();
		Static116.method2325(false);
		Static119.method2381();
		for (@Pc(39) int local39 = 0; local39 < 2048; local39++) {
			@Pc(46) ClientPlayer local46 = Client.players[local39];
			if (local46 != null) {
				local46.anObject5 = null;
			}
		}
		if (GameShell.glRenderer) {
			Static242.method4201();
			Static76.method1642();
		}
		Static102.method2074(Client.fontMetrics, Client.sprites);
		Static30.method839(Client.sprites);
		Static204.aClass3_Sub2_Sub1_10 = null;
		Static39.aClass3_Sub2_Sub1_1 = null;
		Static92.aClass3_Sub2_Sub1_6 = null;
		Static165.aClass3_Sub2_Sub1_8 = null;
		Static181.aClass3_Sub2_Sub1_9 = null;
		if (Client.state == 5) {
			Static181.method3344(Client.sprites);
		}
		if (Client.state == 10) {
			Static73.method1596(false);
		}
		if (Client.state == 30) {
			Client.setMainState(25);
		}
	}

	@OriginalMember(owner = "client!lb", name = "a", descriptor = "(ZIIIBII)V")
	public static void method2722(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		Static113.anInt4612 = arg3;
		Static231.anInt5203 = arg2;
		Static245.anInt5375 = arg5;
		Static233.anInt5225 = arg1;
		Static248.anInt4232 = arg4;
		if (arg0 && Static113.anInt4612 >= 100) {
			Client.anInt3439 = Static245.anInt5375 * 128 + 64;
			Client.anInt3302 = Static248.anInt4232 * 128 + 64;
			Client.anInt40 = Client.getAvH(Client.minusedlevel, Client.anInt3439, Client.anInt3302) - Static231.anInt5203;
		}
		Client.anInt5096 = 2;
	}

}
