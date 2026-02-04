import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static118 {

	@OriginalMember(owner = "client!jd", name = "d", descriptor = "[[[B")
	public static byte[][][] shadow;

	@OriginalMember(owner = "client!jd", name = "i", descriptor = "Lclient!be;")
	public static IfType aClass13_15;

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(B)I")
	public static int method2352() {
		Static232.anInt5212 = 0;
		return Static119.method2385();
	}

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(IB)V")
	public static void method2353(@OriginalArg(0) int arg0) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(12, arg0);
		local12.method1007();
	}

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(II[Lclient!be;)V")
	public static void method2354(@OriginalArg(1) int arg0, @OriginalArg(2) IfType[] arg1) {
		for (@Pc(7) int local7 = 0; local7 < arg1.length; local7++) {
			@Pc(15) IfType local15 = arg1[local7];
			if (local15 != null && local15.layerId == arg0 && (!local15.v3 || !Static36.method947(local15))) {
				if (local15.type == 0) {
					if (!local15.v3 && Static36.method947(local15) && local15 != Static180.aClass13_22) {
						continue;
					}
					method2354(local15.parentId, arg1);
					if (local15.aClass13Array3 != null) {
						method2354(local15.parentId, local15.aClass13Array3);
					}
					@Pc(73) SubInterface local73 = (SubInterface) Static119.aClass133_9.find((long) local15.parentId);
					if (local73 != null) {
						Static96.method1949(local73.anInt5878);
					}
				}
				if (local15.type == 6) {
					@Pc(105) int local105;
					if (local15.modelAnim != -1 || local15.modelAnim2 != -1) {
						@Pc(100) boolean local100 = Static154.method2926(local15);
						if (local100) {
							local105 = local15.modelAnim2;
						} else {
							local105 = local15.modelAnim;
						}
						if (local105 != -1) {
							@Pc(118) SeqType local118 = SeqType.list(local105);
							if (local118 != null) {
								local15.anInt500 += Static178.anInt4247;
								while (local15.anInt500 > local118.delay[local15.anInt510]) {
									local15.anInt500 -= local118.delay[local15.anInt510];
									local15.anInt510++;
									if (local118.frames.length <= local15.anInt510) {
										local15.anInt510 -= local118.anInt5362;
										if (local15.anInt510 < 0 || local118.frames.length <= local15.anInt510) {
											local15.anInt510 = 0;
										}
									}
									local15.anInt496 = local15.anInt510 + 1;
									if (local118.frames.length <= local15.anInt496) {
										local15.anInt496 -= local118.anInt5362;
										if (local15.anInt496 < 0 || local118.frames.length <= local15.anInt496) {
											local15.anInt496 = -1;
										}
									}
									Static43.method1143(local15);
								}
							}
						}
					}
					if (local15.anInt483 != 0 && !local15.v3) {
						@Pc(239) int local239 = local15.anInt483 >> 16;
						@Pc(243) int local243 = local239 * Static178.anInt4247;
						local105 = local15.anInt483 << 16 >> 16;
						local15.modelXAn = local243 + local15.modelXAn & 0x7FF;
						local105 *= Static178.anInt4247;
						local15.modelYAn = local15.modelYAn + local105 & 0x7FF;
						Static43.method1143(local15);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(II)I")
	public static int method2356(@OriginalArg(1) int arg0) {
		return arg0 & 0x7F;
	}
}
