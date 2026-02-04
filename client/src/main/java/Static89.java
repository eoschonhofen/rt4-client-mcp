import java.util.Random;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static89 {

	@OriginalMember(owner = "client!ha", name = "i", descriptor = "Lclient!qf;")
	public static AbstractPix32 aClass3_Sub2_Sub1_5;

	@OriginalMember(owner = "client!ha", name = "o", descriptor = "I")
	public static int anInt2387;

	@OriginalMember(owner = "client!ha", name = "q", descriptor = "I")
	public static int anInt2388 = 0;

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(II)[B")
	public static byte[] method1837(@OriginalArg(1) int arg0) {
		@Pc(10) ByteArrayNode2 local10 = (ByteArrayNode2) Static53.aClass54_5.method1806((long) arg0);
		if (local10 == null) {
			@Pc(24) Random local24 = new Random((long) arg0);
			@Pc(27) byte[] local27 = new byte[512];
			@Pc(29) int local29;
			for (local29 = 0; local29 < 255; local29++) {
				local27[local29] = (byte) local29;
			}
			for (local29 = 0; local29 < 255; local29++) {
				@Pc(53) int local53 = 255 - local29;
				@Pc(58) int local58 = Static171.method3219(local53, local24);
				@Pc(62) byte local62 = local27[local58];
				local27[local58] = local27[local53];
				local27[local53] = local27[511 - local29] = local62;
			}
			local10 = new ByteArrayNode2(local27);
			Static53.aClass54_5.method1811(local10, (long) arg0);
		}
		return local10.aByteArray37;
	}

	@OriginalMember(owner = "client!ha", name = "b", descriptor = "(II)V")
	public static void method1840(@OriginalArg(1) int arg0) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(2, arg0);
		local8.method1007();
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(IIIII)V")
	public static void method1843(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(7) int local7 = 0;
		@Pc(9) int local9 = arg2;
		@Pc(12) int local12 = -arg2;
		@Pc(14) int local14 = -1;
		@Pc(22) int local22 = Static78.method1690(Static224.anInt5063, arg2 + arg3, Static172.anInt4164);
		@Pc(30) int local30 = Static78.method1690(Static224.anInt5063, arg3 - arg2, Static172.anInt4164);
		Static131.method2576(Static71.anIntArrayArray10[arg1], local30, local22, arg0);
		while (local7 < local9) {
			local14 += 2;
			local12 += local14;
			@Pc(58) int local58;
			@Pc(68) int local68;
			@Pc(84) int local84;
			@Pc(93) int local93;
			if (local12 > 0) {
				local9--;
				local58 = arg1 - local9;
				local12 -= local9 << 1;
				local68 = arg1 + local9;
				if (local68 >= Static267.anInt5773 && local58 <= Static106.anInt2869) {
					local84 = Static78.method1690(Static224.anInt5063, arg3 + local7, Static172.anInt4164);
					local93 = Static78.method1690(Static224.anInt5063, arg3 - local7, Static172.anInt4164);
					if (Static106.anInt2869 >= local68) {
						Static131.method2576(Static71.anIntArrayArray10[local68], local93, local84, arg0);
					}
					if (Static267.anInt5773 <= local58) {
						Static131.method2576(Static71.anIntArrayArray10[local58], local93, local84, arg0);
					}
				}
			}
			local7++;
			local58 = arg1 - local7;
			local68 = local7 + arg1;
			if (local68 >= Static267.anInt5773 && Static106.anInt2869 >= local58) {
				local84 = Static78.method1690(Static224.anInt5063, arg3 + local9, Static172.anInt4164);
				local93 = Static78.method1690(Static224.anInt5063, arg3 - local9, Static172.anInt4164);
				if (local68 <= Static106.anInt2869) {
					Static131.method2576(Static71.anIntArrayArray10[local68], local93, local84, arg0);
				}
				if (local58 >= Static267.anInt5773) {
					Static131.method2576(Static71.anIntArrayArray10[local58], local93, local84, arg0);
				}
			}
		}
	}
}
