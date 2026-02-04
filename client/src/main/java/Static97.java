import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static97 {

	@OriginalMember(owner = "client!hi", name = "f", descriptor = "J")
	public static long aLong89 = 0L;

	@OriginalMember(owner = "client!hi", name = "a", descriptor = "(BI)I")
	public static int method1959(@OriginalArg(1) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "client!hi", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method1962(@OriginalArg(0) Packet arg0) {
		@Pc(9) int local9 = arg0.method2204();
		Static203.aClass32Array1 = new WorldInfo[local9];
		@Pc(14) int local14;
		for (local14 = 0; local14 < local9; local14++) {
			Static203.aClass32Array1[local14] = new WorldInfo();
			Static203.aClass32Array1[local14].anInt1739 = arg0.method2204();
			Static203.aClass32Array1[local14].aClass100_378 = arg0.gjstr2();
		}
		Static19.anInt636 = arg0.method2204();
		Static171.anInt4157 = arg0.method2204();
		Static106.anInt2871 = arg0.method2204();
		Static196.aClass10_Sub1Array2 = new GWCWorld[Static171.anInt4157 + 1 - Static19.anInt636];
		for (local14 = 0; local14 < Static106.anInt2871; local14++) {
			@Pc(77) int local77 = arg0.method2204();
			@Pc(85) GWCWorld local85 = Static196.aClass10_Sub1Array2[local77] = new GWCWorld();
			local85.anInt377 = arg0.g1();
			local85.anInt381 = arg0.g4();
			local85.anInt382 = local77 + Static19.anInt636;
			local85.aClass100_69 = arg0.gjstr2();
			local85.aClass100_71 = arg0.gjstr2();
		}
		Static80.anInt4702 = arg0.g4();
		Static61.aBoolean109 = true;
	}

}
