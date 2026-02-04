import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static150 {

	@OriginalMember(owner = "client!lk", name = "J", descriptor = "Lclient!na;")
	public static final JagString aClass100_687 = JagString.wrap(")4p=");

	@OriginalMember(owner = "client!lk", name = "V", descriptor = "I")
	public static int anInt3582 = 0;

	@OriginalMember(owner = "client!lk", name = "Z", descriptor = "I")
	public static int anInt3585 = 0;

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(ILclient!ve;)I")
	public static int method2797(@OriginalArg(1) Js5 arg0) {
		@Pc(1) int local1 = 0;
		if (arg0.method4506(Static262.anInt5754)) {
			local1++;
		}
		if (arg0.method4506(Static136.anInt3322)) {
			local1++;
		}
		return local1;
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(IIBLclient!ve;)Lclient!qf;")
	public static AbstractPix32 method2800(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1) {
		return Static234.method4016(arg1, 0, arg0) ? Static82.method1764() : null;
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(IIILclient!be;Z)V")
	public static void method2801(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) IfType arg2, @OriginalArg(4) boolean arg3) {
		@Pc(4) int local4 = arg2.anInt445;
		@Pc(7) int local7 = arg2.anInt459;
		if (arg2.aByte5 == 0) {
			arg2.anInt445 = arg2.width;
		} else if (arg2.aByte5 == 1) {
			arg2.anInt445 = arg1 - arg2.width;
		} else if (arg2.aByte5 == 2) {
			arg2.anInt445 = arg2.width * arg1 >> 14;
		} else if (arg2.aByte5 == 3) {
			if (arg2.type == 2) {
				arg2.anInt445 = arg2.width * 32 + (arg2.width - 1) * arg2.marginX;
			} else if (arg2.type == 7) {
				arg2.anInt445 = arg2.width * 115 + arg2.marginX * (arg2.width - 1);
			}
		}
		if (arg2.aByte3 == 0) {
			arg2.anInt459 = arg2.height;
		} else if (arg2.aByte3 == 1) {
			arg2.anInt459 = arg0 - arg2.height;
		} else if (arg2.aByte3 == 2) {
			arg2.anInt459 = arg0 * arg2.height >> 14;
		} else if (arg2.aByte3 == 3) {
			if (arg2.type == 2) {
				arg2.anInt459 = (arg2.height - 1) * arg2.marginY + arg2.height * 32;
			} else if (arg2.type == 7) {
				arg2.anInt459 = arg2.height * 12 + (arg2.height - 1) * arg2.marginY;
			}
		}
		if (arg2.aByte5 == 4) {
			arg2.anInt445 = arg2.anInt473 * arg2.anInt459 / arg2.anInt442;
		}
		if (arg2.aByte3 == 4) {
			arg2.anInt459 = arg2.anInt442 * arg2.anInt445 / arg2.anInt473;
		}
		if (Static121.aBoolean154 && (Static36.method940(arg2).eventCode != 0 || arg2.type == 0)) {
			if (arg2.anInt459 < 5 && arg2.anInt445 < 5) {
				arg2.anInt459 = 5;
				arg2.anInt445 = 5;
			} else {
				if (arg2.anInt445 <= 0) {
					arg2.anInt445 = 5;
				}
				if (arg2.anInt459 <= 0) {
					arg2.anInt459 = 5;
				}
			}
		}
		if (arg2.clientCode == 1337) {
			Static280.aClass13_26 = arg2;
		}
		if (arg3 && arg2.anObjectArray17 != null && (local4 != arg2.anInt445 || arg2.anInt459 != local7)) {
			@Pc(305) HookReq local305 = new HookReq();
			local305.onop = arg2.anObjectArray17;
			local305.component = arg2;
			Static185.aClass69_101.method2282(local305);
		}
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(ILclient!fl;IIII)V")
	public static void method2804(@OriginalArg(0) int arg0, @OriginalArg(1) BgSound arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg1.anInt2044 == -1 && arg1.anIntArray181 == null) {
			return;
		}
		@Pc(20) int local20 = 0;
		if (arg1.anInt2037 < arg0) {
			local20 = arg0 - arg1.anInt2037;
		} else if (arg1.anInt2041 > arg0) {
			local20 = arg1.anInt2041 - arg0;
		}
		if (arg1.anInt2028 < arg4) {
			local20 += arg4 - arg1.anInt2028;
		} else if (arg4 < arg1.anInt2029) {
			local20 += arg1.anInt2029 - arg4;
		}
		if (arg1.anInt2042 == 0 || arg1.anInt2042 < local20 - 64 || Static30.anInt978 == 0 || arg2 != arg1.anInt2033) {
			if (arg1.aClass3_Sub3_Sub1_1 != null) {
				Client.soundMixer.method1347(arg1.aClass3_Sub3_Sub1_1);
				arg1.aClass3_Sub3_Sub1_1 = null;
			}
			if (arg1.aClass3_Sub3_Sub1_2 != null) {
				Client.soundMixer.method1347(arg1.aClass3_Sub3_Sub1_2);
				arg1.aClass3_Sub3_Sub1_2 = null;
			}
			return;
		}
		local20 -= 64;
		if (local20 < 0) {
			local20 = 0;
		}
		@Pc(134) int local134 = (arg1.anInt2042 - local20) * Static30.anInt978 / arg1.anInt2042;
		if (arg1.aClass3_Sub3_Sub1_1 != null) {
			arg1.aClass3_Sub3_Sub1_1.method386(local134);
		} else if (arg1.anInt2044 >= 0) {
			@Pc(150) JagFX local150 = Static292.method3988(Client.jagFX, arg1.anInt2044, 0);
			if (local150 != null) {
				@Pc(158) Wave local158 = local150.method3989().method2648(Client.soundDecimator);
				@Pc(163) WaveStream local163 = Static284.method404(local158, local134);
				local163.method396(-1);
				Client.soundMixer.method1343(local163);
				arg1.aClass3_Sub3_Sub1_1 = local163;
			}
		}
		if (arg1.aClass3_Sub3_Sub1_2 != null) {
			arg1.aClass3_Sub3_Sub1_2.method386(local134);
			if (!arg1.aClass3_Sub3_Sub1_2.isLinked()) {
				arg1.aClass3_Sub3_Sub1_2 = null;
			}
		} else if (arg1.anIntArray181 != null && (arg1.anInt2034 -= arg3) <= 0) {
			@Pc(219) int local219 = (int) ((double) arg1.anIntArray181.length * Math.random());
			@Pc(227) JagFX local227 = Static292.method3988(Client.jagFX, arg1.anIntArray181[local219], 0);
			if (local227 != null) {
				@Pc(236) Wave local236 = local227.method3989().method2648(Client.soundDecimator);
				@Pc(241) WaveStream local241 = Static284.method404(local236, local134);
				local241.method396(0);
				Client.soundMixer.method1343(local241);
				arg1.anInt2034 = (int) ((double) (arg1.anInt2040 - arg1.anInt2032) * Math.random()) + arg1.anInt2032;
				arg1.aClass3_Sub3_Sub1_2 = local241;
			}
		}
	}

}
