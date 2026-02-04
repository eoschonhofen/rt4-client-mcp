import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static4 {

	@OriginalMember(owner = "client!h", name = "S", descriptor = "[I")
	public static final int[] skillxp = new int[99];

	@OriginalMember(owner = "client!h", name = "wb", descriptor = "Lclient!na;")
	static final JagString aClass100_496 = Text.aClass100_499;

	@OriginalMember(owner = "client!ac", name = "e", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray1;

	@OriginalMember(owner = "client!ac", name = "i", descriptor = "Lclient!ih;")
	public static final LinkList aClass69_2 = new LinkList();

	@OriginalMember(owner = "client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;

	@OriginalMember(owner = "client!ac", name = "l", descriptor = "Lclient!ck;")
	public static final BasType aClass20_1 = new BasType();

	@OriginalMember(owner = "client!ac", name = "m", descriptor = "Lclient!na;")
	public static final JagString aClass100_7 = JagString.wrap("overlay");

	@OriginalMember(owner = "client!ac", name = "n", descriptor = "I")
	public static int anInt36 = 0;

	@OriginalMember(owner = "client!ac", name = "o", descriptor = "I")
	public static int anInt37 = 0;

	@OriginalMember(owner = "client!ac", name = "p", descriptor = "Lclient!be;")
	public static IfType aClass13_1 = null;

	static {
		@Pc(4) int local4 = 0;
		for (@Pc(6) int local6 = 0; local6 < 99; local6++) {
			@Pc(13) int local13 = local6 + 1;
			@Pc(26) int local26 = (int) (Math.pow(2.0D, (double) local13 / 7.0D) * 300.0D + (double) local13);
			local4 += local26;
			skillxp[local6] = local4 / 4;
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(IIII)I")
	public static int method22(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		if ((Static12.mapl[arg2][arg1][arg0] & 0x8) == 0) {
			return arg2 <= 0 || (Static12.mapl[1][arg1][arg0] & 0x2) == 0 ? arg2 : arg2 - 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "([J[Ljava/lang/Object;I)V")
	public static void method23(@OriginalArg(0) long[] arg0, @OriginalArg(1) Object[] arg1) {
		Static228.method3909(arg0.length - 1, arg0, 0, arg1);
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(BI)V")
	public static void method24(@OriginalArg(1) int arg0) {
		@Pc(16) DelayedStateChange local16 = Static238.method4143(1, arg0);
		local16.method1007();
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(Lclient!na;I)I")
	public static int method25(@OriginalArg(0) JagString arg0) {
		if (arg0 == null) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static9.anInt178; local20++) {
			if (arg0.method3111(Static122.aClass100Array92[local20])) {
				return local20;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "client!ac", name = "b", descriptor = "(I)V")
	public static void method28() {
		Static43.method1143(Static105.aClass13_14);
		Static213.anInt4851++;
		if (Static44.aBoolean83 && Static146.aBoolean174) {
			@Pc(30) int local30 = Static215.mouseX;
			local30 -= Static246.anInt5388;
			if (Static81.anInt2225 > local30) {
				local30 = Static81.anInt2225;
			}
			@Pc(41) int local41 = Static223.mouseY;
			if (Static81.anInt2225 + aClass13_1.anInt445 < local30 - -Static105.aClass13_14.anInt445) {
				local30 = Static81.anInt2225 + aClass13_1.anInt445 - Static105.aClass13_14.anInt445;
			}
			local41 -= Static165.anInt4035;
			if (local41 < Static228.anInt5103) {
				local41 = Static228.anInt5103;
			}
			if (Static228.anInt5103 + aClass13_1.anInt459 < local41 - -Static105.aClass13_14.anInt459) {
				local41 = Static228.anInt5103 + aClass13_1.anInt459 - Static105.aClass13_14.anInt459;
			}
			@Pc(109) int local109 = local41 - Static20.anInt660;
			@Pc(114) int local114 = local30 - Static124.anInt3075;
			@Pc(122) int local122 = local30 + aClass13_1.anInt489 - Static81.anInt2225;
			@Pc(130) int local130 = aClass13_1.anInt468 + local41 - Static228.anInt5103;
			@Pc(133) int local133 = Static105.aClass13_14.dragdeadzone;
			if (Static213.anInt4851 > Static105.aClass13_14.dragdeadtime && (local133 < local114 || -local133 > local114 || local109 > local133 || local109 < -local133)) {
				Static138.aBoolean172 = true;
			}
			@Pc(176) HookReq local176;
			if (Static105.aClass13_14.ondrag != null && Static138.aBoolean172) {
				local176 = new HookReq();
				local176.component = Static105.aClass13_14;
				local176.onop = Static105.aClass13_14.ondrag;
				local176.mouseX = local122;
				local176.mouseY = local130;
				Static82.method1767(local176);
			}
			if (Static22.anInt723 == 0) {
				if (Static138.aBoolean172) {
					if (Static105.aClass13_14.ondragcomplete != null) {
						local176 = new HookReq();
						local176.mouseY = local130;
						local176.drop = Static56.aClass13_12;
						local176.mouseX = local122;
						local176.onop = Static105.aClass13_14.ondragcomplete;
						local176.component = Static105.aClass13_14;
						Static82.method1767(local176);
					}
					if (Static56.aClass13_12 != null && Static36.method938(Static105.aClass13_14) != null) {
						Static6.aClass3_Sub15_Sub1_1.p1Enc(79);
						Static6.aClass3_Sub15_Sub1_1.method2185(Static105.aClass13_14.parentId);
						Static6.aClass3_Sub15_Sub1_1.method2222(Static56.aClass13_12.subId);
						Static6.aClass3_Sub15_Sub1_1.p4(Static56.aClass13_12.parentId);
						Static6.aClass3_Sub15_Sub1_1.method2222(Static105.aClass13_14.subId);
					}
				} else if ((Static116.anInt2952 == 1 || Static277.method4640(Static231.anInt5204 - 1)) && Static231.anInt5204 > 2) {
					Static226.method3901();
				} else if (Static231.anInt5204 > 0) {
					Static59.method1372();
				}
				Static105.aClass13_14 = null;
			}
		} else if (Static213.anInt4851 > 1) {
			Static105.aClass13_14 = null;
		}
	}
}
