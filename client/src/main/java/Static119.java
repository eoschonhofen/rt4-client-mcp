import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static119 {

	@OriginalMember(owner = "client!je", name = "W", descriptor = "Lclient!ve;")
	public static Js5 aClass153_44;

	@OriginalMember(owner = "client!je", name = "eb", descriptor = "[I")
	public static int[] anIntArray282;

	@OriginalMember(owner = "client!je", name = "R", descriptor = "Z")
	public static boolean aBoolean153 = false;

	@OriginalMember(owner = "client!je", name = "T", descriptor = "Lclient!sc;")
	public static HashTable aClass133_9 = new HashTable(8);

	@OriginalMember(owner = "client!je", name = "U", descriptor = "Lclient!na;")
	public static final JagString aClass100_588 = JagString.wrap("showingVideoAd");

	@OriginalMember(owner = "client!je", name = "db", descriptor = "Lclient!na;")
	public static final JagString aClass100_589 = JagString.wrap(" <col=ffffff>");

	@OriginalMember(owner = "client!je", name = "f", descriptor = "(B)V")
	public static void method2381() {
		if (Static18.aBoolean40) {
			Static243.aClass36_1 = null;
			Static18.aBoolean40 = false;
			Static78.aClass3_Sub2_Sub1_3 = null;
		}
	}

	@OriginalMember(owner = "client!je", name = "j", descriptor = "(I)I")
	public static int method2385() {
		if (Static203.aClass134_1 == null) {
			return -1;
		}
		while (Static232.anInt5212 < Static203.aClass134_1.anInt5074) {
			if (Static203.aClass134_1.method3897(Static232.anInt5212)) {
				return Static232.anInt5212++;
			}
			Static232.anInt5212++;
		}
		return -1;
	}

	@OriginalMember(owner = "client!je", name = "k", descriptor = "(I)V")
	public static void method2386() {
		@Pc(6) BgSound local6;
		for (local6 = (BgSound) Static3.aClass69_135.head(); local6 != null; local6 = (BgSound) Static3.aClass69_135.method2288()) {
			if (local6.aBoolean117) {
				local6.method1567();
			}
		}
		for (local6 = (BgSound) Static152.aClass69_87.head(); local6 != null; local6 = (BgSound) Static152.aClass69_87.method2288()) {
			if (local6.aBoolean117) {
				local6.method1567();
			}
		}
	}

	@OriginalMember(owner = "client!je", name = "a", descriptor = "(IIIII)V")
	public static void method2387(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		Static13.anInt435 = Static48.anInt1449 * arg2 / arg0;
		Static28.anInt919 = Static181.anInt4296 * arg1 / arg3;
		Static142.anInt3482 = -1;
		Static217.anInt4901 = -1;
		Static38.method965();
	}
}
