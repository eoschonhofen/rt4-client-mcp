import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static49 {

	@OriginalMember(owner = "client!dm", name = "m", descriptor = "I")
	public static int anInt1459 = 0;

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(IBIII)V")
	public static void method1206(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		@Pc(15) int local15 = 0;
		Static131.method2576(Static71.anIntArrayArray10[arg3], arg0 - arg1, arg0 - -arg1, arg2);
		@Pc(32) int local32 = -arg1;
		@Pc(34) int local34 = arg1;
		@Pc(36) int local36 = -1;
		while (local15 < local34) {
			local15++;
			local36 += 2;
			local32 += local36;
			if (local32 >= 0) {
				local34--;
				local32 -= local34 << 1;
				@Pc(65) int[] local65 = Static71.anIntArrayArray10[arg3 + local34];
				@Pc(71) int[] local71 = Static71.anIntArrayArray10[arg3 - local34];
				@Pc(76) int local76 = arg0 + local15;
				@Pc(81) int local81 = arg0 - local15;
				Static131.method2576(local65, local81, local76, arg2);
				Static131.method2576(local71, local81, local76, arg2);
			}
			@Pc(97) int local97 = local34 + arg0;
			@Pc(102) int local102 = arg0 - local34;
			@Pc(109) int[] local109 = Static71.anIntArrayArray10[arg3 + local15];
			@Pc(116) int[] local116 = Static71.anIntArrayArray10[arg3 - local15];
			Static131.method2576(local109, local102, local97, arg2);
			Static131.method2576(local116, local102, local97, arg2);
		}
	}

}
