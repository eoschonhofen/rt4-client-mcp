import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static119 {

	@OriginalMember(owner = "client!je", name = "eb", descriptor = "[I")
	public static int[] anIntArray282;

	@OriginalMember(owner = "client!je", name = "R", descriptor = "Z")
	public static boolean aBoolean153 = false;

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
		if (WorldMap.aClass134_1 == null) {
			return -1;
		}
		while (Static232.anInt5212 < WorldMap.aClass134_1.anInt5074) {
			if (WorldMap.aClass134_1.method3897(Static232.anInt5212)) {
				return Static232.anInt5212++;
			}
			Static232.anInt5212++;
		}
		return -1;
	}

	@OriginalMember(owner = "client!je", name = "a", descriptor = "(IIIII)V")
	public static void method2387(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		WorldMap.anInt435 = WorldMap.anInt1449 * arg2 / arg0;
		WorldMap.anInt919 = WorldMap.anInt4296 * arg1 / arg3;
		Static142.anInt3482 = -1;
		Static217.anInt4901 = -1;
		WorldMap.method965();
	}
}
