import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static79 {

	@OriginalMember(owner = "client!ge", name = "q", descriptor = "I")
	public static int anInt2161;

	@OriginalMember(owner = "client!ge", name = "k", descriptor = "[I")
	public static int[] anIntArray205 = new int[2];

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(IIIZIZZ)V")
	public static void method1697(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5) {
		if (arg2 <= arg4) {
			return;
		}
		@Pc(13) int local13 = (arg2 + arg4) / 2;
		@Pc(15) int local15 = arg4;
		@Pc(19) GWCWorld local19 = Static101.aClass10_Sub1Array1[local13];
		Static101.aClass10_Sub1Array1[local13] = Static101.aClass10_Sub1Array1[arg2];
		Static101.aClass10_Sub1Array1[arg2] = local19;
		for (@Pc(31) int local31 = arg4; local31 < arg2; local31++) {
			if (Static164.method3115(local19, Static101.aClass10_Sub1Array1[local31], arg0, arg1, arg3, arg5) <= 0) {
				@Pc(53) GWCWorld local53 = Static101.aClass10_Sub1Array1[local31];
				Static101.aClass10_Sub1Array1[local31] = Static101.aClass10_Sub1Array1[local15];
				Static101.aClass10_Sub1Array1[local15++] = local53;
			}
		}
		Static101.aClass10_Sub1Array1[arg2] = Static101.aClass10_Sub1Array1[local15];
		Static101.aClass10_Sub1Array1[local15] = local19;
		method1697(arg0, arg1, local15 - 1, arg3, arg4, arg5);
		method1697(arg0, arg1, arg2, arg3, local15 + 1, arg5);
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(IJ)Lclient!na;")
	public static JagString toBaseDisplayName(@OriginalArg(1) long arg0) {
		if (arg0 <= 0L || arg0 >= 6582952005840035281L) {
			return null;
		} else if (arg0 % 37L == 0L) {
			return null;
		} else {
			@Pc(32) int local32 = 0;
			@Pc(34) long local34 = arg0;
			while (local34 != 0L) {
				local34 /= 37L;
				local32++;
			}
			@Pc(48) byte[] local48 = new byte[local32];
			while (arg0 != 0L) {
				@Pc(65) long local65 = arg0;
				arg0 /= 37L;
				local32--;
				local48[local32] = Static31.aByteArray12[(int) (local65 - arg0 * 37L)];
			}
			@Pc(88) JagString local88 = new JagString();
			local88.aByteArray52 = local48;
			local88.anInt4030 = local48.length;
			return local88;
		}
	}

}
