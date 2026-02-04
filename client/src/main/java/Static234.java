import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static234 {

	@OriginalMember(owner = "client!ta", name = "o", descriptor = "[I")
	public static int[] anIntArray454;

	@OriginalMember(owner = "client!ta", name = "p", descriptor = "[I")
	public static int[] anIntArray455;

	@OriginalMember(owner = "client!ta", name = "a", descriptor = "(Lclient!ve;III)Z")
	public static boolean method4016(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(9) byte[] local9 = arg0.getFile(arg2, arg1);
		if (local9 == null) {
			return false;
		} else {
			Static84.method1770(local9);
			return true;
		}
	}

	@OriginalMember(owner = "client!ta", name = "a", descriptor = "([Lclient!mm;ILclient!ve;)V")
	public static void method4018(@OriginalArg(0) Pix32[] arg0, @OriginalArg(2) Js5 arg1) {
		WorldMap.aClass153_44 = arg1;
		Static241.aClass3_Sub2_Sub1_Sub1Array13 = arg0;
		Static258.aBooleanArray130 = new boolean[Static241.aClass3_Sub2_Sub1_Sub1Array13.length];
		Static228.aClass69_120.method2278();
		@Pc(25) int local25 = WorldMap.aClass153_44.getGroupId(Static54.aClass100_374);
		@Pc(30) int[] local30 = WorldMap.aClass153_44.getFileLimit(local25);
		for (@Pc(32) int local32 = 0; local32 < local30.length; local32++) {
			Static228.aClass69_120.push(WorldMap.method3713(new Packet(WorldMap.aClass153_44.getFile(local25, local30[local32]))));
		}
	}

	@OriginalMember(owner = "client!ta", name = "a", descriptor = "(IIZII)V")
	public static void method4019(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static267.anInt5773 && arg3 <= Static106.anInt2869) {
			@Pc(15) int local15 = Static78.method1690(Static224.anInt5063, arg0, Static172.anInt4164);
			@Pc(21) int local21 = Static78.method1690(Static224.anInt5063, arg2, Static172.anInt4164);
			Static222.method3826(arg1, arg3, local21, local15);
		}
	}

}
