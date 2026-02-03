import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static34 {

	@OriginalMember(owner = "client!ck", name = "F", descriptor = "I")
	public static int anInt1049;

	@OriginalMember(owner = "client!ck", name = "J", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray3;

	@OriginalMember(owner = "client!ck", name = "X", descriptor = "I")
	public static int anInt1060;

	@OriginalMember(owner = "client!ck", name = "b", descriptor = "Lclient!na;")
	private static final JagString aClass100_195 = JagString.wrap("Discard");

	@OriginalMember(owner = "client!ck", name = "c", descriptor = "Lclient!na;")
	public static final JagString aClass100_196 = JagString.wrap("Lade Konfiguration )2 ");

	@OriginalMember(owner = "client!ck", name = "d", descriptor = "[I")
	public static final int[] anIntArray80 = new int[] { 1, 0, -1, 0 };

	@OriginalMember(owner = "client!ck", name = "T", descriptor = "Lclient!na;")
	private static final JagString aClass100_207 = JagString.wrap("Jan");

	@OriginalMember(owner = "client!ck", name = "hb", descriptor = "Lclient!na;")
	private static final JagString aClass100_212 = JagString.wrap("Feb");

	@OriginalMember(owner = "client!ck", name = "gb", descriptor = "Lclient!na;")
	private static final JagString aClass100_211 = JagString.wrap("Mar");

	@OriginalMember(owner = "client!ck", name = "db", descriptor = "Lclient!na;")
	private static final JagString aClass100_210 = JagString.wrap("Apr");

	@OriginalMember(owner = "client!ck", name = "n", descriptor = "Lclient!na;")
	private static final JagString aClass100_199 = JagString.wrap("May");

	@OriginalMember(owner = "client!ck", name = "L", descriptor = "Lclient!na;")
	private static final JagString aClass100_204 = JagString.wrap("Jun");

	@OriginalMember(owner = "client!ck", name = "B", descriptor = "Lclient!na;")
	private static final JagString aClass100_202 = JagString.wrap("Jul");

	@OriginalMember(owner = "client!ck", name = "l", descriptor = "Lclient!na;")
	private static final JagString aClass100_198 = JagString.wrap("Aug");

	@OriginalMember(owner = "client!ck", name = "V", descriptor = "Lclient!na;")
	private static final JagString aClass100_208 = JagString.wrap("Sep");

	@OriginalMember(owner = "client!ck", name = "S", descriptor = "Lclient!na;")
	private static final JagString aClass100_206 = JagString.wrap("Oct");

	@OriginalMember(owner = "client!ck", name = "Y", descriptor = "Lclient!na;")
	private static final JagString aClass100_209 = JagString.wrap("Nov");

	@OriginalMember(owner = "client!ck", name = "O", descriptor = "Lclient!na;")
	private static final JagString aClass100_205 = JagString.wrap("Dec");

	@OriginalMember(owner = "client!ck", name = "f", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array40 = new JagString[] { aClass100_207, aClass100_212, aClass100_211, aClass100_210, aClass100_199, aClass100_204, aClass100_202, aClass100_198, aClass100_208, aClass100_206, aClass100_209, aClass100_205 };

	@OriginalMember(owner = "client!ck", name = "i", descriptor = "Lclient!na;")
	public static JagString aClass100_197 = aClass100_195;

	@OriginalMember(owner = "client!ck", name = "o", descriptor = "Lclient!na;")
	private static final JagString aClass100_200 = JagString.wrap("Loaded world list data");

	@OriginalMember(owner = "client!ck", name = "y", descriptor = "Lclient!na;")
	public static JagString aClass100_201 = aClass100_200;

	@OriginalMember(owner = "client!ck", name = "D", descriptor = "Lclient!na;")
	public static JagString aClass100_203 = null;

	@OriginalMember(owner = "client!ck", name = "K", descriptor = "I")
	public static int anInt1053 = 0;

	@OriginalMember(owner = "client!ck", name = "eb", descriptor = "Z")
	public static boolean aBoolean65 = false;

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(ILclient!va;Lclient!ve;Lclient!ve;Lclient!ve;)Z")
	public static boolean init(@OriginalArg(1) MidiPlayer arg0, @OriginalArg(2) Js5 arg1, @OriginalArg(3) Js5 arg2, @OriginalArg(4) Js5 arg3) {
		Static210.aClass153_87 = arg1;
		Static78.aClass153_32 = arg3;
		Static252.aClass153_103 = arg2;
		Static172.midiPlayer = arg0;
		return true;
	}

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(Lclient!fe;I)V")
	public static void method879(@OriginalArg(0) ClientEntity arg0) {
		arg0.aBoolean171 = false;
		@Pc(18) SeqType local18;
		if (arg0.anInt3366 != -1) {
			local18 = SeqType.list(arg0.anInt3366);
			if (local18 == null || local18.frames == null) {
				arg0.anInt3366 = -1;
			} else {
				arg0.anInt3396++;
				if (local18.frames.length > arg0.anInt3407 && arg0.anInt3396 > local18.delay[arg0.anInt3407]) {
					arg0.anInt3396 = 1;
					arg0.anInt3407++;
					arg0.anInt3388++;
					Static152.method2836(arg0.anInt3421, local18, arg0.anInt3412, arg0 == Static173.aClass8_Sub4_Sub1_2, arg0.anInt3407);
				}
				if (arg0.anInt3407 >= local18.frames.length) {
					arg0.anInt3407 = 0;
					arg0.anInt3396 = 0;
					Static152.method2836(arg0.anInt3421, local18, arg0.anInt3412, Static173.aClass8_Sub4_Sub1_2 == arg0, arg0.anInt3407);
				}
				arg0.anInt3388 = arg0.anInt3407 + 1;
				if (arg0.anInt3388 >= local18.frames.length) {
					arg0.anInt3388 = 0;
				}
			}
		}
		@Pc(156) int local156;
		if (arg0.anInt3432 != -1 && Static83.anInt372 >= arg0.anInt3359) {
			local156 = SpotType.list(arg0.anInt3432).anim;
			if (local156 == -1) {
				arg0.anInt3432 = -1;
			} else {
				@Pc(165) SeqType local165 = SeqType.list(local156);
				if (local165 == null || local165.frames == null) {
					arg0.anInt3432 = -1;
				} else {
					if (arg0.anInt3399 < 0) {
						arg0.anInt3399 = 0;
						Static152.method2836(arg0.anInt3421, local165, arg0.anInt3412, Static173.aClass8_Sub4_Sub1_2 == arg0, 0);
					}
					arg0.anInt3361++;
					if (arg0.anInt3399 < local165.frames.length && local165.delay[arg0.anInt3399] < arg0.anInt3361) {
						arg0.anInt3399++;
						arg0.anInt3361 = 1;
						Static152.method2836(arg0.anInt3421, local165, arg0.anInt3412, Static173.aClass8_Sub4_Sub1_2 == arg0, arg0.anInt3399);
					}
					if (arg0.anInt3399 >= local165.frames.length) {
						arg0.anInt3432 = -1;
					}
					arg0.anInt3418 = arg0.anInt3399 + 1;
					if (local165.frames.length <= arg0.anInt3418) {
						arg0.anInt3418 = -1;
					}
				}
			}
		}
		if (arg0.anInt3369 != -1 && arg0.anInt3420 <= 1) {
			local18 = SeqType.list(arg0.anInt3369);
			if (local18.anInt5363 == 1 && arg0.anInt3405 > 0 && Static83.anInt372 >= arg0.anInt3395 && Static83.anInt372 > arg0.anInt3386) {
				arg0.anInt3420 = 1;
				return;
			}
		}
		if (arg0.anInt3369 != -1 && arg0.anInt3420 == 0) {
			local18 = SeqType.list(arg0.anInt3369);
			if (local18 == null || local18.frames == null) {
				arg0.anInt3369 = -1;
			} else {
				arg0.anInt3360++;
				if (arg0.anInt3425 < local18.frames.length && arg0.anInt3360 > local18.delay[arg0.anInt3425]) {
					arg0.anInt3360 = 1;
					arg0.anInt3425++;
					Static152.method2836(arg0.anInt3421, local18, arg0.anInt3412, arg0 == Static173.aClass8_Sub4_Sub1_2, arg0.anInt3425);
				}
				if (local18.frames.length <= arg0.anInt3425) {
					arg0.anInt3425 -= local18.anInt5362;
					arg0.anInt3371++;
					if (arg0.anInt3371 >= local18.anInt5357) {
						arg0.anInt3369 = -1;
					} else if (arg0.anInt3425 >= 0 && local18.frames.length > arg0.anInt3425) {
						Static152.method2836(arg0.anInt3421, local18, arg0.anInt3412, Static173.aClass8_Sub4_Sub1_2 == arg0, arg0.anInt3425);
					} else {
						arg0.anInt3369 = -1;
					}
				}
				arg0.anInt3373 = arg0.anInt3425 + 1;
				if (arg0.anInt3373 >= local18.frames.length) {
					arg0.anInt3373 -= local18.anInt5362;
					if (local18.anInt5357 <= arg0.anInt3371 + 1) {
						arg0.anInt3373 = -1;
					} else if (arg0.anInt3373 < 0 || arg0.anInt3373 >= local18.frames.length) {
						arg0.anInt3373 = -1;
					}
				}
				arg0.aBoolean171 = local18.aBoolean279;
			}
		}
		if (arg0.anInt3420 > 0) {
			arg0.anInt3420--;
		}
		for (local156 = 0; local156 < arg0.aClass147Array3.length; local156++) {
			@Pc(545) Class147 local545 = arg0.aClass147Array3[local156];
			if (local545 != null) {
				if (local545.anInt5408 > 0) {
					local545.anInt5408--;
				} else {
					@Pc(570) SeqType local570 = SeqType.list(local545.anInt5396);
					if (local570 == null || local570.frames == null) {
						arg0.aClass147Array3[local156] = null;
					} else {
						local545.anInt5404++;
						if (local545.anInt5399 < local570.frames.length && local545.anInt5404 > local570.delay[local545.anInt5399]) {
							local545.anInt5399++;
							local545.anInt5404 = 1;
							Static152.method2836(arg0.anInt3421, local570, arg0.anInt3412, arg0 == Static173.aClass8_Sub4_Sub1_2, local545.anInt5399);
						}
						if (local570.frames.length <= local545.anInt5399) {
							local545.anInt5400++;
							local545.anInt5399 -= local570.anInt5362;
							if (local570.anInt5357 <= local545.anInt5400) {
								arg0.aClass147Array3[local156] = null;
							} else if (local545.anInt5399 >= 0 && local545.anInt5399 < local570.frames.length) {
								Static152.method2836(arg0.anInt3421, local570, arg0.anInt3412, Static173.aClass8_Sub4_Sub1_2 == arg0, local545.anInt5399);
							} else {
								arg0.aClass147Array3[local156] = null;
							}
						}
						local545.anInt5398 = local545.anInt5399 + 1;
						if (local570.frames.length <= local545.anInt5398) {
							local545.anInt5398 -= local570.anInt5362;
							if (local545.anInt5400 + 1 >= local570.anInt5357) {
								local545.anInt5398 = -1;
							} else if (local545.anInt5398 < 0 || local570.frames.length <= local545.anInt5398) {
								local545.anInt5398 = -1;
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "([Lclient!na;B)Lclient!na;")
	public static JagString concatenate(@OriginalArg(0) JagString[] arg0) {
		if (arg0.length < 2) {
			throw new IllegalArgumentException();
		}
		return Static118.method2355(0, arg0.length, arg0);
	}
}
