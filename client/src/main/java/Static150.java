import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static150 {

	@OriginalMember(owner = "client!lk", name = "J", descriptor = "Lclient!na;")
	public static final JagString aClass100_687 = JagString.wrap(")4p=");

	@OriginalMember(owner = "client!lk", name = "P", descriptor = "Lclient!na;")
	private static final JagString aClass100_688 = JagString.wrap("Loading fonts )2 ");

	@OriginalMember(owner = "client!lk", name = "S", descriptor = "Lclient!na;")
	private static final JagString aClass100_689 = JagString.wrap(" is already on your friend list)3");

	@OriginalMember(owner = "client!lk", name = "V", descriptor = "I")
	public static int anInt3582 = 0;

	@OriginalMember(owner = "client!lk", name = "X", descriptor = "Lclient!na;")
	public static JagString aClass100_690 = aClass100_688;

	@OriginalMember(owner = "client!lk", name = "Z", descriptor = "I")
	public static int anInt3585 = 0;

	@OriginalMember(owner = "client!lk", name = "ab", descriptor = "Lclient!na;")
	public static JagString aClass100_691 = aClass100_689;

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
			local305.anObjectArray31 = arg2.anObjectArray17;
			local305.aClass13_17 = arg2;
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
				client.soundMixer.method1347(arg1.aClass3_Sub3_Sub1_1);
				arg1.aClass3_Sub3_Sub1_1 = null;
			}
			if (arg1.aClass3_Sub3_Sub1_2 != null) {
				client.soundMixer.method1347(arg1.aClass3_Sub3_Sub1_2);
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
			@Pc(150) JagFX local150 = Static292.method3988(client.jagFX, arg1.anInt2044, 0);
			if (local150 != null) {
				@Pc(158) Wave local158 = local150.method3989().method2648(client.soundDecimator);
				@Pc(163) WaveStream local163 = Static284.method404(local158, local134);
				local163.method396(-1);
				client.soundMixer.method1343(local163);
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
			@Pc(227) JagFX local227 = Static292.method3988(client.jagFX, arg1.anIntArray181[local219], 0);
			if (local227 != null) {
				@Pc(236) Wave local236 = local227.method3989().method2648(client.soundDecimator);
				@Pc(241) WaveStream local241 = Static284.method404(local236, local134);
				local241.method396(0);
				client.soundMixer.method1343(local241);
				arg1.anInt2034 = (int) ((double) (arg1.anInt2040 - arg1.anInt2032) * Math.random()) + arg1.anInt2032;
				arg1.aClass3_Sub3_Sub1_2 = local241;
			}
		}
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(Z)V")
	public static void swapEnglish() {
		Static41.aClass100_272 = Static189.aClass100_834;
		Static253.aClass100_1054 = Static190.aClass100_838;
		Static71.aClass100_417 = Static49.aClass100_350;
		Static74.MAINLOAD70 = Static20.aClass100_114;
		Static244.aClass100_1016 = Static253.aClass100_1057;
		Static197.aClass100_873 = Static58.aClass100_395;
		Static51.aClass100_367 = Static58.aClass100_394;
		Static138.aClass100_648 = Static60.aClass100_421;
		Static233.aClass100_995 = Static75.aClass100_462;
		Static165.aClass100_774 = Static193.aClass100_854;
		Static23.MAINLOAD40 = Static28.aClass100_173;
		Static16.aClass100_94 = Static251.aClass100_1044;
		Static41.aClass100_271 = Static149.aClass100_683;
		Static25.aClass100_155 = Static191.aClass100_842;
		Static156.aClass100_746 = Static166.aClass100_778;
		Static35.aClass100_220 = Static162.aClass100_765;
		Static208.aClass100_906 = Static266.aClass100_1011;
		Static206.aClass100_901 = Static189.aClass100_836;
		Static88.aClass100_498 = Static146.aClass100_669;
		Static2.aClass100_4 = Static191.aClass100_844;
		Static171.MAINLOAD110 = Static225.aClass100_963;
		Static141.aClass100_663 = Static38.aClass100_243;
		Static192.OK = Static156.aClass100_742;
		Static53.aClass100_370 = Static252.aClass100_1050;
		Static92.aClass100_508 = Static38.aClass100_241;
		Static113.MAINLOAD10 = Static258.aClass100_1069;
		Static71.aClass100_418 = Static49.aClass100_350;
		aClass100_690 = Static10.aClass100_65;
		Static270.aClass100_1091 = Static167.aClass100_782;
		Static220.aClass100_931 = Static170.aClass100_620;
		Static246.aClass100_1028 = Static106.aClass100_564;
		Static109.CONTINUE = Static229.aClass100_976;
		Static170.aClass100_621 = Static109.aClass100_568;
		Static253.aClass100_1055 = Static195.aClass100_860;
		Static223.MAINLOAD80 = Static57.aClass100_392;
		Static247.aClass100_1032 = Static29.aClass100_175;
		Static36.aClass100_233 = Static82.aClass100_481;
		Static148.aClass100_675 = Static231.aClass100_984;
		Static232.aClass100_986 = Static37.aClass100_237;
		Static56.aClass100_384 = Static176.aClass100_799;
		Static199.aClass100_881 = Static5.aClass100_8;
		Static34.aClass100_201 = Static224.aClass100_958;
		Static211.aClass100_227 = Static88.aClass100_497;
		Static20.aClass100_115 = Static90.aClass100_502;
		Static92.aClass100_507 = Static180.aClass100_808;
		Static38.aClass100_240 = Static156.aClass100_744;
		Static35.aClass100_218 = Static162.aClass100_765;
		Static225.SELECT = Static14.aClass100_81;
		Static262.aClass100_1079 = Static84.aClass100_483;
		Static229.aClass100_973 = Static15.aClass100_90;
		Static82.aClass100_478 = Static272.aClass100_988;
		Static49.aClass100_348 = Static183.aClass100_809;
		Static265.MAINLOAD30 = Static264.aClass100_876;
		Static105.aClass100_560 = Static213.aClass100_910;
		Static224.aClass100_957 = Static223.aClass100_948;
		Static259.aClass100_1073 = Static86.aClass100_491;
		Static69.aClass100_444 = Static90.aClass100_501;
		Static234.aClass100_998 = Static201.aClass100_409;
		Static214.aClass100_1064 = Static126.aClass100_601;
		Static222.aClass100_937 = Static98.aClass100_525;
		Static131.aClass100_626 = Static21.aClass100_125;
		Static262.aClass100_1077 = Static164.aClass100_768;
		Static269.aClass100_555 = Static252.aClass100_1048;
		Static57.aClass100_391 = Static207.aClass100_905;
		Static11.MAINLOAD40B = Static274.aClass100_942;
		Static21.aClass100_123 = Static12.aClass100_75;
		Static245.aClass100_1022 = Static247.aClass100_1031;
		Static157.aClass100_752 = Static47.aClass100_992;
		Static49.aClass100_349 = Static170.aClass100_619;
		Static69.aClass100_440 = Static90.aClass100_501;
		Static21.aClass100_124 = Static34.aClass100_196;
		Static164.aClass100_769 = Static234.aClass100_996;
		Static27.aClass100_166 = Static121.aClass100_590;
		Static7.aClass100_190 = Static179.aClass100_805;
		Static111.aClass100_573 = Static221.aClass100_822;
		Static196.MAINLOAD120B = Static54.aClass100_373;
		Static184.aClass100_820 = Static140.aClass100_660;
		Static178.MAINLOAD30B = Static198.aClass100_261;
		Static34.aClass100_197 = Static30.aClass100_183;
		Static10.aClass100_64 = Static210.aClass100_909;
		Static191.aClass100_846 = Static20.aClass100_117;
		Static17.aClass100_104 = Static65.aClass100_434;
		aClass100_691 = Static243.aClass100_1012;
		Static48.aClass100_347 = Static173.aClass100_792;
		Static39.aClass100_249 = Static175.aClass100_796;
		Static56.aClass100_385 = Static147.aClass100_671;
		Static86.MAINLOAD90 = Static205.aClass100_898;
		Static148.MAINLOAD120 = Static28.aClass100_174;
		Static225.aClass100_964 = Static231.aClass100_985;
		Static56.aClass100_386 = Static11.aClass100_72;
	}
}
