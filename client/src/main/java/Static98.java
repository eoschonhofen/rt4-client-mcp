import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static98 {

	@OriginalMember(owner = "client!hj", name = "e", descriptor = "I")
	public static int anInt2512;

	@OriginalMember(owner = "client!hj", name = "d", descriptor = "Lclient!na;")
	public static final JagString aClass100_524 = JagString.wrap("hint_headicons");

	@OriginalMember(owner = "client!hj", name = "a", descriptor = "(II)V")
	public static void method1964(@OriginalArg(0) int arg0) {
		Static217.anInt4901 = -1;
		Static142.anInt3482 = -1;
		WorldMap.anInt435 = arg0;
		WorldMap.method965();
	}

	@OriginalMember(owner = "client!hj", name = "a", descriptor = "(Lclient!na;B)Z")
	public static boolean method1965(@OriginalArg(0) JagString arg0) {
		if (arg0 == null) {
			return false;
		}
		for (@Pc(12) int local12 = 0; local12 < Static9.anInt178; local12++) {
			if (arg0.method3111(Static122.aClass100Array92[local12])) {
				return true;
			}
		}
		if (arg0.method3111(Client.localPlayer.aClass100_364)) {
			return true;
		} else {
			return false;
		}
	}

}
