import javax.media.opengl.GL;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class World {

	@OriginalMember(owner = "client!ah", name = "p", descriptor = "Lclient!ih;")
	public static final LinkList aClass69_32 = new LinkList();
    @OriginalMember(owner = "client!ke", name = "T", descriptor = "[[I")
    public static final int[][] anIntArrayArray24 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };

    @OriginalMember(owner = "client!jf", name = "a", descriptor = "[Lclient!gi;")
	public static Light[] aClass51Array1;

	@OriginalMember(owner = "client!kc", name = "o", descriptor = "[[[Lclient!bj;")
	public static Square[][][] levelTiles;

	@OriginalMember(owner = "client!f", name = "ab", descriptor = "[[I")
	public static int[][] anIntArrayArray11;

	@OriginalMember(owner = "client!tk", name = "D", descriptor = "[Lclient!ec;")
	public static Sprite[] aClass31Array3;

	@OriginalMember(owner = "client!bl", name = "T", descriptor = "I")
	public static int anInt726 = 0;
	@OriginalMember(owner = "client!ef", name = "g", descriptor = "I")
	public static int groundX = -1;
	@OriginalMember(owner = "client!jb", name = "p", descriptor = "I")
	public static int groundZ = -1;
	@OriginalMember(owner = "client!bc", name = "Z", descriptor = "I")
	public static int cycleNo;
	@OriginalMember(owner = "client!rc", name = "p", descriptor = "I")
	public static int anInt1142 = 0;
	@OriginalMember(owner = "client!tb", name = "Q", descriptor = "I")
	public static int anInt5276 = 0;
	@OriginalMember(owner = "client!jm", name = "r", descriptor = "I")
	public static int anInt3114;
	@OriginalMember(owner = "client!ch", name = "w", descriptor = "I")
	public static int anInt987;
	@OriginalMember(owner = "client!aa", name = "m", descriptor = "I")
	public static int anInt15;
	@OriginalMember(owner = "client!gf", name = "M", descriptor = "I")
	public static int anInt4698;
	@OriginalMember(owner = "client!rh", name = "d", descriptor = "I")
	public static int anInt4866;
	@OriginalMember(owner = "client!dl", name = "h", descriptor = "[[Z")
	public static boolean[][] aBooleanArrayArray1;
	@OriginalMember(owner = "client!wi", name = "db", descriptor = "I")
	public static int anInt5855;
	@OriginalMember(owner = "client!nd", name = "s", descriptor = "I")
	public static int anInt4069;
	@OriginalMember(owner = "client!pi", name = "U", descriptor = "I")
	public static int anInt4539;

	@OriginalMember(owner = "client!jf", name = "c", descriptor = "[I")
	private static int[] anIntArray283;

	@OriginalMember(owner = "client!jf", name = "d", descriptor = "I")
	private static int anInt3029;

	@OriginalMember(owner = "client!jf", name = "e", descriptor = "I")
	private static int anInt3030;

	@OriginalMember(owner = "client!jf", name = "f", descriptor = "[Z")
	private static boolean[] aBooleanArray65;

	@OriginalMember(owner = "client!jf", name = "g", descriptor = "[[[I")
	private static int[][][] anIntArrayArrayArray11;

	@OriginalMember(owner = "client!jf", name = "h", descriptor = "[I")
	private static int[] anIntArray284;

	@OriginalMember(owner = "client!jf", name = "i", descriptor = "I")
	private static int anInt3031;

	@OriginalMember(owner = "client!jf", name = "j", descriptor = "I")
	private static int anInt3032;

	@OriginalMember(owner = "client!jf", name = "k", descriptor = "I")
	private static int anInt3033;

	@OriginalMember(owner = "client!jf", name = "m", descriptor = "[Z")
	private static boolean[] aBooleanArray66;

	@OriginalMember(owner = "client!jf", name = "n", descriptor = "I")
	private static int anInt3035;

	@OriginalMember(owner = "client!jf", name = "o", descriptor = "I")
	private static int anInt3036;

	@OriginalMember(owner = "client!jf", name = "p", descriptor = "I")
	private static int anInt3037;

	@OriginalMember(owner = "client!jf", name = "b", descriptor = "[F")
	private static final float[] aFloatArray17 = new float[] { 0.0F, 0.0F, 0.0F, 1.0F };

	@OriginalMember(owner = "client!jf", name = "l", descriptor = "I")
	public static int anInt3034 = 0;

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(IIIIIII)V")
	public static void method2388(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		if (!Static178.highDetailLighting) {
			return;
		}
		if (arg0 == 1 && arg5 > 0) {
			method2393(arg1, arg2, arg3, arg4, arg5 - 1, arg6);
		} else if (arg0 == 4 && arg5 < anInt3037 - 1) {
			method2393(arg1, arg2, arg3, arg4, arg5 + 1, arg6);
		} else if (arg0 == 8 && arg6 > 0) {
			method2393(arg1, arg2, arg3, arg4, arg5, arg6 - 1);
		} else if (arg0 == 2 && arg6 < anInt3036 - 1) {
			method2393(arg1, arg2, arg3, arg4, arg5, arg6 + 1);
		} else if (arg0 == 16 && arg5 > 0 && arg6 < anInt3036 - 1) {
			method2393(arg1, arg2, arg3, arg4, arg5 - 1, arg6 + 1);
		} else if (arg0 == 32 && arg5 < anInt3037 - 1 && arg6 < anInt3036 - 1) {
			method2393(arg1, arg2, arg3, arg4, arg5 + 1, arg6 + 1);
		} else if (arg0 == 128 && arg5 > 0 && arg6 > 0) {
			method2393(arg1, arg2, arg3, arg4, arg5 - 1, arg6 - 1);
		} else if (arg0 == 64 && arg5 < anInt3037 - 1 && arg6 > 0) {
			method2393(arg1, arg2, arg3, arg4, arg5 + 1, arg6 - 1);
		}
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(Lclient!gi;)V")
	public static void method2389(@OriginalArg(0) Light arg0) {
		if (anInt3034 >= 255) {
			System.out.println("Number of lights added exceeds maximum!");
		} else {
			aClass51Array1[anInt3034++] = arg0;
		}
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "()V")
	public static void method2390() {
		for (@Pc(1) int local1 = 0; local1 < 4; local1++) {
			anIntArray284[local1] = -1;
			method2396(local1);
		}
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(IIIIIIII)V")
	public static void method2391(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		if (!Static178.highDetailLighting || anInt3031 == arg3 && anInt3033 == arg4 && anInt3029 == arg5 && anInt3035 == arg6 && anInt3030 == arg7) {
			return;
		}
		@Pc(20) int local20;
		for (local20 = 0; local20 < 4; local20++) {
			aBooleanArray66[local20] = false;
		}
		local20 = 0;
		@Pc(33) int local33 = 0;
		@Pc(35) int local35;
		@Pc(40) int local40;
		label112: for (local35 = arg4; local35 <= arg6; local35++) {
			label110: for (local40 = arg5; local40 <= arg7; local40++) {
				@Pc(51) int local51 = anIntArrayArrayArray11[arg3][local35][local40];
				while (true) {
					while (true) {
						label96: while (true) {
							if (local51 == 0) {
								continue label110;
							}
							@Pc(59) int local59 = (local51 & 0xFF) - 1;
							local51 >>>= 0x8;
							@Pc(65) int local65;
							for (local65 = 0; local65 < local33; local65++) {
								if (local59 == anIntArray283[local65]) {
									continue label96;
								}
							}
							for (local65 = 0; local65 < 4; local65++) {
								if (local59 == anIntArray284[local65]) {
									if (!aBooleanArray66[local65]) {
										aBooleanArray66[local65] = true;
										local20++;
										if (local20 == 4) {
											break label112;
										}
									}
									continue label96;
								}
							}
							anIntArray283[local33++] = local59;
							local20++;
							if (local20 == 4) {
								break label112;
							}
						}
					}
				}
			}
		}
		for (local35 = 0; local35 < local33; local35++) {
			for (local40 = 0; local40 < 4; local40++) {
				if (!aBooleanArray66[local40]) {
					anIntArray284[local40] = anIntArray283[local35];
					aBooleanArray66[local40] = true;
					method2403(local40, aClass51Array1[anIntArray283[local35]], arg0, arg1, arg2);
					break;
				}
			}
		}
		for (local35 = 0; local35 < 4; local35++) {
			if (!aBooleanArray66[local35]) {
				anIntArray284[local35] = -1;
				method2396(local35);
			}
		}
		anInt3031 = arg3;
		anInt3033 = arg4;
		anInt3029 = arg5;
		anInt3035 = arg6;
		anInt3030 = arg7;
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(III)V")
	public static void create() {
		anInt3032 = 4;
		anInt3037 = 104;
		anInt3036 = 104;
		anIntArrayArrayArray11 = new int[anInt3032][anInt3037][anInt3036];
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(IIIIII)V")
	public static void method2393(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		if (!Static178.highDetailLighting || anInt3031 == arg3 && anInt3033 == arg4 && anInt3029 == arg5 && anInt3035 == arg4 && anInt3030 == arg5) {
			return;
		}
		@Pc(20) int local20;
		for (local20 = 0; local20 < 4; local20++) {
			aBooleanArray66[local20] = false;
		}
		local20 = 0;
		@Pc(39) int local39 = anIntArrayArrayArray11[arg3][arg4][arg5];
		while (true) {
			@Pc(47) int local47;
			@Pc(53) int local53;
			label72: while (local39 != 0) {
				local47 = (local39 & 0xFF) - 1;
				local39 >>>= 0x8;
				for (local53 = 0; local53 < 4; local53++) {
					if (local47 == anIntArray284[local53]) {
						aBooleanArray66[local53] = true;
						continue label72;
					}
				}
				anIntArray283[local20++] = local47;
			}
			for (local47 = 0; local47 < local20; local47++) {
				for (local53 = 0; local53 < 4; local53++) {
					if (!aBooleanArray66[local53]) {
						anIntArray284[local53] = anIntArray283[local47];
						aBooleanArray66[local53] = true;
						method2403(local53, aClass51Array1[anIntArray283[local47]], arg0, arg1, arg2);
						break;
					}
				}
			}
			for (local47 = 0; local47 < 4; local47++) {
				if (!aBooleanArray66[local47]) {
					anIntArray284[local47] = -1;
					method2396(local47);
				}
			}
			anInt3031 = arg3;
			anInt3033 = arg4;
			anInt3029 = arg5;
			anInt3035 = arg4;
			anInt3030 = arg5;
			return;
		}
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(IZ)V")
	public static void method2394(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1) {
		for (@Pc(1) int local1 = 0; local1 < anInt3034; local1++) {
			aClass51Array1[local1].method1765(arg1, arg0);
		}
		anInt3031 = -1;
		anInt3033 = -1;
		anInt3029 = -1;
		anInt3035 = -1;
		anInt3030 = -1;
	}

	@OriginalMember(owner = "client!jf", name = "b", descriptor = "()V")
	public static void method2395() {
		for (@Pc(1) int local1 = 0; local1 < anInt3034; local1++) {
			@Pc(8) Light local8 = aClass51Array1[local1];
			@Pc(11) int local11 = local8.anInt2241;
			if (local8.aBoolean124) {
				local11 = 0;
			}
			@Pc(19) int local19 = local8.anInt2241;
			if (local8.aBoolean126) {
				local19 = 3;
			}
			for (@Pc(26) int local26 = local11; local26 <= local19; local26++) {
				@Pc(31) int local31 = 0;
				@Pc(39) int local39 = (local8.anInt2245 >> 7) - local8.anInt2236;
				if (local39 < 0) {
					local31 = -local39;
					local39 = 0;
				}
				@Pc(55) int local55 = (local8.anInt2245 >> 7) + local8.anInt2236;
				if (local55 > anInt3036 - 1) {
					local55 = anInt3036 - 1;
				}
				for (@Pc(66) int local66 = local39; local66 <= local55; local66++) {
					@Pc(75) short local75 = local8.aShortArray30[local31++];
					@Pc(87) int local87 = (local8.anInt2240 >> 7) + (local75 >> 8) - local8.anInt2236;
					@Pc(95) int local95 = local87 + (local75 & 0xFF) - 1;
					if (local87 < 0) {
						local87 = 0;
					}
					if (local95 > anInt3037 - 1) {
						local95 = anInt3037 - 1;
					}
					for (@Pc(110) int local110 = local87; local110 <= local95; local110++) {
						@Pc(121) int local121 = anIntArrayArrayArray11[local26][local110][local66];
						if ((local121 & 0xFF) == 0) {
							anIntArrayArrayArray11[local26][local110][local66] = local121 | local1 + 1;
						} else if ((local121 & 0xFF00) == 0) {
							anIntArrayArrayArray11[local26][local110][local66] = local121 | local1 + 1 << 8;
						} else if ((local121 & 0xFF0000) == 0) {
							anIntArrayArrayArray11[local26][local110][local66] = local121 | local1 + 1 << 16;
						} else if ((local121 & 0xFF000000) == 0) {
							anIntArrayArrayArray11[local26][local110][local66] = local121 | local1 + 1 << 24;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(I)V")
	private static void method2396(@OriginalArg(0) int arg0) {
		if (aBooleanArray65[arg0]) {
			aBooleanArray65[arg0] = false;
			@Pc(14) int local14 = arg0 + 16384 + 4;
			@Pc(16) GL local16 = Static239.aGL1;
			local16.glDisable(local14);
		}
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(IIIII)V")
	public static void method2397(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (!Static178.highDetailLighting) {
			return;
		}
		label43: for (@Pc(4) int local4 = 0; local4 < 4; local4++) {
			if (anIntArray284[local4] != -1) {
				@Pc(20) int local20 = anIntArrayArrayArray11[arg0][arg1][arg2];
				@Pc(28) int local28;
				while (local20 != 0) {
					local28 = (local20 & 0xFF) - 1;
					local20 >>>= 0x8;
					if (local28 == anIntArray284[local4]) {
						continue label43;
					}
				}
				local20 = anIntArrayArrayArray11[arg0][arg3][arg4];
				while (local20 != 0) {
					local28 = (local20 & 0xFF) - 1;
					local20 >>>= 0x8;
					if (local28 == anIntArray284[local4]) {
						continue label43;
					}
				}
			}
			anIntArray284[local4] = -1;
			method2396(local4);
		}
	}

	@OriginalMember(owner = "client!jf", name = "c", descriptor = "()V")
	public static void method2398() {
		aClass51Array1 = null;
		anIntArray284 = null;
		aBooleanArray65 = null;
		anIntArray283 = null;
		aBooleanArray66 = null;
		anIntArrayArrayArray11 = null;
	}

	@OriginalMember(owner = "client!jf", name = "e", descriptor = "()V")
	public static void method2400() {
		@Pc(1) GL local1 = Static239.aGL1;
		@Pc(3) int local3;
		for (local3 = 0; local3 < 4; local3++) {
			@Pc(10) int local10 = local3 + 16388;
			local1.glLightfv(local10, GL.GL_AMBIENT, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
			local1.glLightf(local10, GL.GL_LINEAR_ATTENUATION, 0.0F);
			local1.glLightf(local10, GL.GL_CONSTANT_ATTENUATION, 0.0F);
		}
		for (local3 = 0; local3 < 4; local3++) {
			anIntArray284[local3] = -1;
			method2396(local3);
		}
	}

	@OriginalMember(owner = "client!jf", name = "f", descriptor = "()V")
	public static void method2401() {
		aClass51Array1 = new Light[255];
		anIntArray284 = new int[4];
		aBooleanArray65 = new boolean[4];
		anIntArray283 = new int[4];
		aBooleanArray66 = new boolean[4];
		anIntArrayArrayArray11 = new int[anInt3032][anInt3037][anInt3036];
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(II[[[Lclient!bj;)V")
	public static void method2402(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Square[][][] arg2) {
		if (!Static178.highDetailLighting) {
			return;
		}
		@Pc(4) GL local4 = Static239.aGL1;
		Static27.method766(0, 0);
		Static239.method4183(0);
		Static239.method4150();
		Static239.method4177(Static239.anInt5328);
		local4.glDepthMask(false);
		Static239.setLightingEnabled(false);
		local4.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ONE);
		local4.glFogfv(GL.GL_FOG_COLOR, new float[] { 0.0F, 0.0F, 0.0F, 0.0F }, 0);
		local4.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_SRC0_RGB, GL.GL_CONSTANT);
		local4.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_OPERAND0_RGB, GL.GL_SRC_ALPHA);
		label71: for (@Pc(56) int local56 = 0; local56 < anInt3034; local56++) {
			@Pc(63) Light local63 = aClass51Array1[local56];
			@Pc(66) int local66 = local63.anInt2241;
			if (local63.aBoolean125) {
				local66--;
			}
			if (local63.aClass45_1 != null) {
				@Pc(76) int local76 = 0;
				@Pc(84) int local84 = (local63.anInt2245 >> 7) - local63.anInt2236;
				@Pc(92) int local92 = (local63.anInt2245 >> 7) + local63.anInt2236;
				if (local92 >= anInt4866) {
					local92 = anInt4866 - 1;
				}
				if (local84 < anInt4698) {
					local76 = anInt4698 - local84;
					local84 = anInt4698;
				}
				for (@Pc(112) int local112 = local84; local112 <= local92; local112++) {
					@Pc(121) short local121 = local63.aShortArray30[local76++];
					@Pc(133) int local133 = (local63.anInt2240 >> 7) + (local121 >> 8) - local63.anInt2236;
					@Pc(141) int local141 = local133 + (local121 & 0xFF) - 1;
					if (local133 < anInt987) {
						local133 = anInt987;
					}
					if (local141 >= anInt15) {
						local141 = anInt15 - 1;
					}
					for (@Pc(155) int local155 = local133; local155 <= local141; local155++) {
						@Pc(160) Square local160 = null;
						if (local66 >= 0) {
							local160 = arg2[local66][local155][local112];
						}
						if (local66 < 0 || local160 != null && local160.aBoolean45) {
							Static239.method4159(201.5F - (float) local63.anInt2241 * 50.0F - 1.5F);
							local4.glTexEnvfv(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_COLOR, new float[] { 0.0F, 0.0F, 0.0F, local63.aFloat8 }, 0);
							local63.aClass45_1.method1556();
							continue label71;
						}
					}
				}
			}
		}
		local4.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_SRC0_RGB, GL.GL_TEXTURE);
		local4.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_OPERAND0_RGB, GL.GL_SRC_COLOR);
		local4.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		local4.glDepthMask(true);
		local4.glFogfv(GL.GL_FOG_COLOR, Static161.aFloatArray19, 0);
		local4.glEnableClientState(GL.GL_TEXTURE_COORD_ARRAY);
		Static239.method4173();
	}

	@OriginalMember(owner = "client!jf", name = "a", descriptor = "(ILclient!gi;III)V")
	private static void method2403(@OriginalArg(0) int arg0, @OriginalArg(1) Light arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		@Pc(5) int local5 = arg0 + 16384 + 4;
		@Pc(7) GL local7 = Static239.aGL1;
		if (!aBooleanArray65[arg0]) {
			local7.glEnable(local5);
			aBooleanArray65[arg0] = true;
		}
		local7.glLightf(local5, GL.GL_QUADRATIC_ATTENUATION, arg1.aFloat9);
		local7.glLightfv(local5, GL.GL_DIFFUSE, arg1.aFloatArray3, 0);
		aFloatArray17[0] = arg1.anInt2240 - arg2;
		aFloatArray17[1] = arg1.anInt2235 - arg3;
		aFloatArray17[2] = arg1.anInt2245 - arg4;
		local7.glLightfv(local5, GL.GL_POSITION, aFloatArray17, 0);
	}

	@OriginalMember(owner = "client!jf", name = "g", descriptor = "()V")
	public static void method2404() {
		anInt3034 = 0;
		for (@Pc(3) int local3 = 0; local3 < anInt3032; local3++) {
			for (@Pc(8) int local8 = 0; local8 < anInt3037; local8++) {
				for (@Pc(13) int local13 = 0; local13 < anInt3036; local13++) {
					anIntArrayArrayArray11[local3][local8][local13] = 0;
				}
			}
		}
	}

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(IIIILclient!th;JLclient!th;Lclient!th;)V")
    public static void setObj(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) ModelSource arg4, @OriginalArg(5) long arg5, @OriginalArg(6) ModelSource arg6, @OriginalArg(7) ModelSource arg7) {
        @Pc(3) GroundObject local3 = new GroundObject();
        local3.topObj = arg4;
        local3.x = arg1 * 128 + 64;
        local3.z = arg2 * 128 + 64;
        local3.y = arg3;
        local3.typecode = arg5;
        local3.bottomObj = arg6;
        local3.middleObj = arg7;
        @Pc(34) int local34 = 0;
        @Pc(42) Square local42 = levelTiles[arg0][arg1][arg2];
        if (local42 != null) {
            for (@Pc(46) int local46 = 0; local46 < local42.spriteCount; local46++) {
                @Pc(55) Sprite local55 = local42.sprites[local46];
                if ((local55.typecode & 0x400000L) == 0x400000L) {
                    @Pc(66) int local66 = local55.model.calcBoundingCylinder();
                    if (local66 != -32768 && local66 < local34) {
                        local34 = local66;
                    }
                }
            }
        }
        local3.height = -local34;
        if (levelTiles[arg0][arg1][arg2] == null) {
            levelTiles[arg0][arg1][arg2] = new Square(arg0, arg1, arg2);
        }
        levelTiles[arg0][arg1][arg2].groundObject = local3;
    }

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIIIJ)V")
	public static void method1880(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) ModelSource arg4, @OriginalArg(5) ModelSource arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) long arg10) {
		if (arg4 == null) {
			return;
		}
		@Pc(6) Decor local6 = new Decor();
		local6.aLong52 = arg10;
		local6.anInt1390 = arg1 * 128 + 64;
		local6.anInt1393 = arg2 * 128 + 64;
		local6.anInt1391 = arg3;
		local6.aClass8_3 = arg4;
		local6.aClass8_2 = arg5;
		local6.anInt1395 = arg6;
		local6.anInt1388 = arg7;
		local6.anInt1394 = arg8;
		local6.anInt1392 = arg9;
		for (@Pc(46) int local46 = arg0; local46 >= 0; local46--) {
			if (levelTiles[local46][arg1][arg2] == null) {
				levelTiles[local46][arg1][arg2] = new Square(local46, arg1, arg2);
			}
		}
		levelTiles[arg0][arg1][arg2].decor = local6;
	}

	@OriginalMember(owner = "client!vf", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIJ)V")
	public static void method4508(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) ModelSource arg4, @OriginalArg(5) ModelSource arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8) {
		if (arg4 == null && arg5 == null) {
			return;
		}
		@Pc(8) Wall local8 = new Wall();
		local8.aLong107 = arg8;
		local8.anInt3048 = arg1 * 128 + 64;
		local8.anInt3044 = arg2 * 128 + 64;
		local8.anInt3051 = arg3;
		local8.aClass8_5 = arg4;
		local8.aClass8_6 = arg5;
		local8.anInt3049 = arg6;
		local8.anInt3052 = arg7;
		for (@Pc(42) int local42 = arg0; local42 >= 0; local42--) {
			if (levelTiles[local42][arg1][arg2] == null) {
				levelTiles[local42][arg1][arg2] = new Square(local42, arg1, arg2);
			}
		}
		levelTiles[arg0][arg1][arg2].wall = local8;
	}

	@OriginalMember(owner = "client!dg", name = "a", descriptor = "(IIIIILclient!th;IJZ)Z")
	public static boolean addDynamic(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) ModelSource arg5, @OriginalArg(6) int arg6, @OriginalArg(7) long arg7, @OriginalArg(8) boolean arg8) {
		if (arg5 == null) {
			return true;
		}
		@Pc(7) int local7 = arg1 - arg4;
		@Pc(11) int local11 = arg2 - arg4;
		@Pc(15) int local15 = arg1 + arg4;
		@Pc(19) int local19 = arg2 + arg4;
		if (arg8) {
			if (arg6 > 640 && arg6 < 1408) {
				local19 += 128;
			}
			if (arg6 > 1152 && arg6 < 1920) {
				local15 += 128;
			}
			if (arg6 > 1664 || arg6 < 384) {
				local11 -= 128;
			}
			if (arg6 > 128 && arg6 < 896) {
				local7 -= 128;
			}
		}
		local7 /= 128;
		local11 /= 128;
		local15 /= 128;
		local19 /= 128;
		return setSprite(arg0, local7, local11, local15 + 1 - local7, local19 - local11 + 1, arg1, arg2, arg3, arg5, arg6, true, arg7);
	}

	@OriginalMember(owner = "client!ib", name = "a", descriptor = "(IIIIIIIILclient!th;IZJ)Z")
	public static boolean setSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) ModelSource arg8, @OriginalArg(9) int arg9, @OriginalArg(10) boolean arg10, @OriginalArg(11) long arg11) {
		@Pc(6) boolean local6 = ClientBuild.groundh == Static80.anIntArrayArrayArray19;
		@Pc(8) int local8 = 0;
		@Pc(17) int local17;
		for (@Pc(10) int local10 = arg1; local10 < arg1 + arg3; local10++) {
			for (local17 = arg2; local17 < arg2 + arg4; local17++) {
				if (local10 < 0 || local17 < 0 || local10 >= Static152.anInt3594 || local17 >= Static99.anInt2550) {
					return false;
				}
				@Pc(42) Square local42 = levelTiles[arg0][local10][local17];
				if (local42 != null && local42.spriteCount >= 5) {
					return false;
				}
			}
		}
		@Pc(58) Sprite local58 = new Sprite();
		local58.typecode = arg11;
		local58.anInt1709 = arg0;
		local58.anInt1699 = arg5;
		local58.anInt1703 = arg6;
		local58.anInt1706 = arg7;
		local58.model = arg8;
		local58.anInt1714 = arg9;
		local58.anInt1701 = arg1;
		local58.anInt1696 = arg2;
		local58.anInt1713 = arg1 + arg3 - 1;
		local58.anInt1698 = arg2 + arg4 - 1;
		@Pc(108) int local108;
		for (local17 = arg1; local17 < arg1 + arg3; local17++) {
			for (local108 = arg2; local108 < arg2 + arg4; local108++) {
				@Pc(115) int local115 = 0;
				if (local17 > arg1) {
					local115++;
				}
				if (local17 < arg1 + arg3 - 1) {
					local115 += 4;
				}
				if (local108 > arg2) {
					local115 += 8;
				}
				if (local108 < arg2 + arg4 - 1) {
					local115 += 2;
				}
				for (@Pc(141) int local141 = arg0; local141 >= 0; local141--) {
					if (levelTiles[local141][local17][local108] == null) {
						levelTiles[local141][local17][local108] = new Square(local141, local17, local108);
					}
				}
				@Pc(174) Square local174 = levelTiles[arg0][local17][local108];
				local174.sprites[local174.spriteCount] = local58;
				local174.anIntArray59[local174.spriteCount] = local115;
				local174.anInt664 |= local115;
				local174.spriteCount++;
				if (local6 && anIntArrayArray11[local17][local108] != 0) {
					local8 = anIntArrayArray11[local17][local108];
				}
			}
		}
		if (local6 && local8 != 0) {
			for (local17 = arg1; local17 < arg1 + arg3; local17++) {
				for (local108 = arg2; local108 < arg2 + arg4; local108++) {
					if (anIntArrayArray11[local17][local108] == 0) {
						anIntArrayArray11[local17][local108] = local8;
					}
				}
			}
		}
		if (arg10) {
			aClass31Array3[anInt726++] = local58;
		}
		return true;
	}

	@OriginalMember(owner = "client!af", name = "a", descriptor = "(IIIIIILclient!th;IJ)Z")
	public static boolean method35(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) ModelSource arg6, @OriginalArg(8) long arg7) {
		if (arg6 == null) {
			return true;
		} else {
			@Pc(11) int local11 = arg1 * 128 + arg4 * 64;
			@Pc(19) int local19 = arg2 * 128 + arg5 * 64;
			return setSprite(arg0, arg1, arg2, arg4, arg5, local11, local19, arg3, arg6, 0, false, arg7);
		}
	}

	@OriginalMember(owner = "client!ol", name = "a", descriptor = "(IIIILclient!th;IJIIII)Z")
	public static boolean method3387(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) ModelSource arg4, @OriginalArg(5) int arg5, @OriginalArg(6) long arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10) {
		return arg4 == null ? true : setSprite(arg0, arg7, arg8, arg9 + 1 - arg7, arg10 - arg8 + 1, arg1, arg2, arg3, arg4, arg5, true, arg6);
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(Lclient!bj;Z)V")
	public static void method4245(@OriginalArg(0) Square arg0, @OriginalArg(1) boolean arg1) {
		aClass69_32.push(arg0);
		while (true) {
			@Pc(8) Square local8;
			@Pc(18) int local18;
			@Pc(21) int local21;
			@Pc(24) int local24;
			@Pc(27) int local27;
			@Pc(31) Square[][] local31;
			@Pc(65) int local65;
			@Pc(115) int local115;
			@Pc(894) int local894;
			@Pc(899) int local899;
			@Pc(904) int local904;
			@Pc(153) Square local153;
			@Pc(1332) int local1332;
			do {
				do {
					do {
						do {
							do {
								do {
									while (true) {
										@Pc(44) int var9;
										@Pc(48) int var10;
										@Pc(907) int var17;
										@Pc(916) int var18;
										@Pc(363) Wall var22;
										@Pc(469) boolean var24;
										@Pc(425) Sprite var25;
										@Pc(1179) Square var32;
										while (true) {
											do {
												local8 = (Square) aClass69_32.popFront();
												if (local8 == null) {
													return;
												}
											} while (!local8.aBoolean46);
											local18 = local8.anInt669;
											local21 = local8.anInt666;
											local24 = local8.anInt672;
											local27 = local8.anInt668;
											local31 = levelTiles[local24];
											@Pc(33) float local33 = 0.0F;
											if (GameShell.glRenderer) {
												if (Static80.anIntArrayArrayArray19 == ClientBuild.groundh) {
													var9 = anIntArrayArray11[local18][local21];
													var10 = var9 & 0xFFFFFF;
													if (var10 != Static152.anInt3604) {
														Static152.anInt3604 = var10;
														Static21.method619(var10);
														Static161.method3066(Static123.method2422());
													}
													local65 = var9 >>> 24 << 3;
													if (local65 != Static22.anInt730) {
														Static22.anInt730 = local65;
														Static147.method2761(local65);
													}
													local115 = Static107.anIntArrayArrayArray10[0][local18][local21] + Static107.anIntArrayArrayArray10[0][local18 + 1][local21] + Static107.anIntArrayArrayArray10[0][local18][local21 + 1] + Static107.anIntArrayArrayArray10[0][local18 + 1][local21 + 1] >> 2;
													Static27.method766(-local115, 3);
													local33 = 201.5F;
													Static239.method4159(local33);
												} else {
													local33 = 201.5F - (float) (local27 + 1) * 50.0F;
													Static239.method4159(local33);
												}
											}
											if (!local8.aBoolean45) {
												break;
											}
											if (arg1) {
												if (local24 > 0) {
													local153 = levelTiles[local24 - 1][local18][local21];
													if (local153 != null && local153.aBoolean46) {
														continue;
													}
												}
												if (local18 <= anInt4069 && local18 > anInt987) {
													local153 = local31[local18 - 1][local21];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (local8.anInt664 & 0x1) == 0)) {
														continue;
													}
												}
												if (local18 >= anInt4069 && local18 < anInt15 - 1) {
													local153 = local31[local18 + 1][local21];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (local8.anInt664 & 0x4) == 0)) {
														continue;
													}
												}
												if (local21 <= anInt4539 && local21 > anInt4698) {
													local153 = local31[local18][local21 - 1];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (local8.anInt664 & 0x8) == 0)) {
														continue;
													}
												}
												if (local21 >= anInt4539 && local21 < anInt4866 - 1) {
													local153 = local31[local18][local21 + 1];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (local8.anInt664 & 0x2) == 0)) {
														continue;
													}
												}
											} else {
												arg1 = true;
											}
											local8.aBoolean45 = false;
											if (local8.aClass3_Sub5_1 != null) {
												local153 = local8.aClass3_Sub5_1;
												if (GameShell.glRenderer) {
													Static239.method4159(201.5F - (float) (local153.anInt668 + 1) * 50.0F);
												}
												if (local153.aClass131_1 == null) {
													if (local153.aClass43_1 != null) {
														if (Static9.method187(0, local18, local21)) {
															Static147.method2762(local153.aClass43_1, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, true);
														} else {
															Static147.method2762(local153.aClass43_1, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, false);
														}
													}
												} else if (Static9.method187(0, local18, local21)) {
													method2610(local153.aClass131_1, 0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, true);
												} else {
													method2610(local153.aClass131_1, 0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, false);
												}
												var22 = local153.wall;
												if (var22 != null) {
													if (GameShell.glRenderer) {
														if ((var22.anInt3049 & local8.anInt670) == 0) {
															method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
														} else {
															method2388(var22.anInt3049, Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local27, local18, local21);
														}
													}
													var22.aClass8_5.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, var22.anInt3048 - Static149.anInt3555, var22.anInt3051 - Static162.anInt3947, var22.anInt3044 - Static217.anInt4903, var22.aLong107, local24, null);
												}
												for (local65 = 0; local65 < local153.spriteCount; local65++) {
													var25 = local153.sprites[local65];
													if (var25 != null) {
														if (GameShell.glRenderer) {
															method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
														}
														var25.model.method4546(var25.anInt1714, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, var25.anInt1699 - Static149.anInt3555, var25.anInt1706 - Static162.anInt3947, var25.anInt1703 - Static217.anInt4903, var25.typecode, local24, null);
													}
												}
												if (GameShell.glRenderer) {
													Static239.method4159(local33);
												}
											}
											var24 = false;
											if (local8.aClass131_1 == null) {
												if (local8.aClass43_1 != null) {
													if (Static9.method187(local27, local18, local21)) {
														Static147.method2762(local8.aClass43_1, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, true);
													} else {
														var24 = true;
														Static147.method2762(local8.aClass43_1, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, false);
													}
												}
											} else if (Static9.method187(local27, local18, local21)) {
												method2610(local8.aClass131_1, local27, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, true);
											} else {
												var24 = true;
												if (local8.aClass131_1.anInt4865 != 12345678 || Static158.aBoolean187 && local24 <= Static160.anInt3902) {
													method2610(local8.aClass131_1, local27, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local18, local21, false);
												}
											}
											if (var24) {
												@Pc(549) GroundDecor local549 = local8.groundDecor;
												if (local549 != null && (local549.aLong26 & 0x80000000L) != 0L) {
													if (GameShell.glRenderer && local549.aBoolean49) {
														Static239.method4159(local33 + 50.0F - 1.5F);
													}
													if (GameShell.glRenderer) {
														method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
													}
													local549.aClass8_1.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local549.anInt732 - Static149.anInt3555, local549.anInt733 - Static162.anInt3947, local549.anInt736 - Static217.anInt4903, local549.aLong26, local24, null);
													if (GameShell.glRenderer && local549.aBoolean49) {
														Static239.method4159(local33);
													}
												}
											}
											var10 = 0;
											local65 = 0;
											@Pc(616) Wall local616 = local8.wall;
											@Pc(619) Decor local619 = local8.decor;
											if (local616 != null || local619 != null) {
												if (anInt4069 == local18) {
													var10++;
												} else if (anInt4069 < local18) {
													var10 += 2;
												}
												if (anInt4539 == local21) {
													var10 += 3;
												} else if (anInt4539 > local21) {
													var10 += 6;
												}
												local65 = Static138.anIntArray324[var10];
												local8.anInt670 = Static191.anIntArray386[var10];
											}
											if (local616 != null) {
												if ((local616.anInt3049 & Static90.anIntArray215[var10]) == 0) {
													local8.anInt663 = 0;
												} else if (local616.anInt3049 == 16) {
													local8.anInt663 = 3;
													local8.anInt665 = Static128.anIntArray294[var10];
													local8.anInt667 = 3 - local8.anInt665;
												} else if (local616.anInt3049 == 32) {
													local8.anInt663 = 6;
													local8.anInt665 = Static254.anIntArray489[var10];
													local8.anInt667 = 6 - local8.anInt665;
												} else if (local616.anInt3049 == 64) {
													local8.anInt663 = 12;
													local8.anInt665 = Static86.anIntArray211[var10];
													local8.anInt667 = 12 - local8.anInt665;
												} else {
													local8.anInt663 = 9;
													local8.anInt665 = Static131.anIntArray307[var10];
													local8.anInt667 = 9 - local8.anInt665;
												}
												if ((local616.anInt3049 & local65) != 0 && !Static260.method3850(local27, local18, local21, local616.anInt3049)) {
													if (GameShell.glRenderer) {
														method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
													}
													local616.aClass8_5.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local616.anInt3048 - Static149.anInt3555, local616.anInt3051 - Static162.anInt3947, local616.anInt3044 - Static217.anInt4903, local616.aLong107, local24, null);
												}
												if ((local616.anInt3052 & local65) != 0 && !Static260.method3850(local27, local18, local21, local616.anInt3052)) {
													if (GameShell.glRenderer) {
														method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
													}
													local616.aClass8_6.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local616.anInt3048 - Static149.anInt3555, local616.anInt3051 - Static162.anInt3947, local616.anInt3044 - Static217.anInt4903, local616.aLong107, local24, null);
												}
											}
											if (local619 != null && !Static276.method4611(local27, local18, local21, local619.aClass8_3.calcBoundingCylinder())) {
												if (GameShell.glRenderer) {
													Static239.method4159(local33 - 0.5F);
												}
												if ((local619.anInt1395 & local65) != 0) {
													if (GameShell.glRenderer) {
														method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
													}
													local619.aClass8_3.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local619.anInt1390 + local619.anInt1394 - Static149.anInt3555, local619.anInt1391 - Static162.anInt3947, local619.anInt1393 + local619.anInt1392 - Static217.anInt4903, local619.aLong52, local24, null);
												} else if (local619.anInt1395 == 256) {
													local894 = local619.anInt1390 - Static149.anInt3555;
													local899 = local619.anInt1391 - Static162.anInt3947;
													local904 = local619.anInt1393 - Static217.anInt4903;
													var17 = local619.anInt1388;
													if (var17 == 1 || var17 == 2) {
														var18 = -local894;
													} else {
														var18 = local894;
													}
													@Pc(928) int local928;
													if (var17 == 2 || var17 == 3) {
														local928 = -local904;
													} else {
														local928 = local904;
													}
													if (local928 < var18) {
														if (GameShell.glRenderer) {
															method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
														}
														local619.aClass8_3.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local894 + local619.anInt1394, local899, local904 + local619.anInt1392, local619.aLong52, local24, null);
													} else if (local619.aClass8_2 != null) {
														if (GameShell.glRenderer) {
															method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
														}
														local619.aClass8_2.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local894, local899, local904, local619.aLong52, local24, null);
													}
												}
												if (GameShell.glRenderer) {
													Static239.method4159(local33);
												}
											}
											if (var24) {
												@Pc(1001) GroundDecor local1001 = local8.groundDecor;
												if (local1001 != null && (local1001.aLong26 & 0x80000000L) == 0L) {
													if (GameShell.glRenderer && local1001.aBoolean49) {
														Static239.method4159(local33 + 50.0F - 1.5F);
													}
													if (GameShell.glRenderer) {
														method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
													}
													local1001.aClass8_1.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1001.anInt732 - Static149.anInt3555, local1001.anInt733 - Static162.anInt3947, local1001.anInt736 - Static217.anInt4903, local1001.aLong26, local24, null);
													if (GameShell.glRenderer && local1001.aBoolean49) {
														Static239.method4159(local33);
													}
												}
												@Pc(1064) GroundObject local1064 = local8.groundObject;
												if (local1064 != null && local1064.height == 0) {
													if (GameShell.glRenderer) {
														method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
													}
													if (local1064.bottomObj != null) {
														local1064.bottomObj.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1064.x - Static149.anInt3555, local1064.y - Static162.anInt3947, local1064.z - Static217.anInt4903, local1064.typecode, local24, null);
													}
													if (local1064.middleObj != null) {
														local1064.middleObj.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1064.x - Static149.anInt3555, local1064.y - Static162.anInt3947, local1064.z - Static217.anInt4903, local1064.typecode, local24, null);
													}
													if (local1064.topObj != null) {
														local1064.topObj.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1064.x - Static149.anInt3555, local1064.y - Static162.anInt3947, local1064.z - Static217.anInt4903, local1064.typecode, local24, null);
													}
												}
											}
											local894 = local8.anInt664;
											if (local894 != 0) {
												if (local18 < anInt4069 && (local894 & 0x4) != 0) {
													var32 = local31[local18 + 1][local21];
													if (var32 != null && var32.aBoolean46) {
														aClass69_32.push(var32);
													}
												}
												if (local21 < anInt4539 && (local894 & 0x2) != 0) {
													var32 = local31[local18][local21 + 1];
													if (var32 != null && var32.aBoolean46) {
														aClass69_32.push(var32);
													}
												}
												if (local18 > anInt4069 && (local894 & 0x1) != 0) {
													var32 = local31[local18 - 1][local21];
													if (var32 != null && var32.aBoolean46) {
														aClass69_32.push(var32);
													}
												}
												if (local21 > anInt4539 && (local894 & 0x8) != 0) {
													var32 = local31[local18][local21 - 1];
													if (var32 != null && var32.aBoolean46) {
														aClass69_32.push(var32);
													}
												}
											}
											break;
										}
										if (local8.anInt663 != 0) {
											var24 = true;
											for (var10 = 0; var10 < local8.spriteCount; var10++) {
												if (local8.sprites[var10].anInt1707 != cycleNo && (local8.anIntArray59[var10] & local8.anInt663) == local8.anInt665) {
													var24 = false;
													break;
												}
											}
											if (var24) {
												var22 = local8.wall;
												if (!Static260.method3850(local27, local18, local21, var22.anInt3049)) {
													if (GameShell.glRenderer) {
														label882: {
															if ((var22.aLong107 & 0xFC000L) == 16384L) {
																local65 = var22.anInt3048 - Static149.anInt3555;
																local115 = var22.anInt3044 - Static217.anInt4903;
																local1332 = (int) (var22.aLong107 >> 20 & 0x3L);
																if (local1332 == 0) {
																	local65 -= 64;
																	local115 += 64;
																	if (local115 < local65 && local18 > 0 && local21 < Static99.anInt2550 - 1) {
																		method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18 - 1, local21 + 1);
																		break label882;
																	}
																} else if (local1332 == 1) {
																	local65 += 64;
																	local115 += 64;
																	if (local115 < -local65 && local18 < Static152.anInt3594 - 1 && local21 < Static99.anInt2550 - 1) {
																		method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18 + 1, local21 + 1);
																		break label882;
																	}
																} else if (local1332 == 2) {
																	local65 += 64;
																	local115 -= 64;
																	if (local115 > local65 && local18 < Static152.anInt3594 - 1 && local21 > 0) {
																		method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18 + 1, local21 - 1);
																		break label882;
																	}
																} else if (local1332 == 3) {
																	local65 -= 64;
																	local115 -= 64;
																	if (local115 > -local65 && local18 > 0 && local21 > 0) {
																		method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18 - 1, local21 - 1);
																		break label882;
																	}
																}
															}
															method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
														}
													}
													var22.aClass8_5.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, var22.anInt3048 - Static149.anInt3555, var22.anInt3051 - Static162.anInt3947, var22.anInt3044 - Static217.anInt4903, var22.aLong107, local24, null);
												}
												local8.anInt663 = 0;
											}
										}
										if (!local8.aBoolean47) {
											break;
										}
										try {
											var9 = local8.spriteCount;
											local8.aBoolean47 = false;
											var10 = 0;
											label767: for (local65 = 0; local65 < var9; local65++) {
												var25 = local8.sprites[local65];
												if (var25.anInt1707 != cycleNo) {
													for (local1332 = var25.anInt1701; local1332 <= var25.anInt1713; local1332++) {
														for (local894 = var25.anInt1696; local894 <= var25.anInt1698; local894++) {
															var32 = local31[local1332][local894];
															if (var32.aBoolean45) {
																local8.aBoolean47 = true;
																continue label767;
															}
															if (var32.anInt663 != 0) {
																local904 = 0;
																if (local1332 > var25.anInt1701) {
																	local904++;
																}
																if (local1332 < var25.anInt1713) {
																	local904 += 4;
																}
																if (local894 > var25.anInt1696) {
																	local904 += 8;
																}
																if (local894 < var25.anInt1698) {
																	local904 += 2;
																}
																if ((local904 & var32.anInt663) == local8.anInt667) {
																	local8.aBoolean47 = true;
																	continue label767;
																}
															}
														}
													}
													Static25.aClass31Array2[var10++] = var25;
													local1332 = anInt4069 - var25.anInt1701;
													local894 = var25.anInt1713 - anInt4069;
													if (local894 > local1332) {
														local1332 = local894;
													}
													local899 = anInt4539 - var25.anInt1696;
													local904 = var25.anInt1698 - anInt4539;
													if (local904 > local899) {
														var25.anInt1705 = local1332 + local904;
													} else {
														var25.anInt1705 = local1332 + local899;
													}
												}
											}
											while (var10 > 0) {
												local65 = -50;
												local115 = -1;
												for (local1332 = 0; local1332 < var10; local1332++) {
													@Pc(1628) Sprite local1628 = Static25.aClass31Array2[local1332];
													if (local1628.anInt1707 != cycleNo) {
														if (local1628.anInt1705 > local65) {
															local65 = local1628.anInt1705;
															local115 = local1332;
														} else if (local1628.anInt1705 == local65) {
															local899 = local1628.anInt1699 - Static149.anInt3555;
															local904 = local1628.anInt1703 - Static217.anInt4903;
															var17 = Static25.aClass31Array2[local115].anInt1699 - Static149.anInt3555;
															var18 = Static25.aClass31Array2[local115].anInt1703 - Static217.anInt4903;
															if (local899 * local899 + local904 * local904 > var17 * var17 + var18 * var18) {
																local115 = local1332;
															}
														}
													}
												}
												if (local115 == -1) {
													break;
												}
												@Pc(1697) Sprite local1697 = Static25.aClass31Array2[local115];
												local1697.anInt1707 = cycleNo;
												if (!Static73.method1599(local27, local1697.anInt1701, local1697.anInt1713, local1697.anInt1696, local1697.anInt1698, local1697.model.calcBoundingCylinder())) {
													if (GameShell.glRenderer) {
														if ((local1697.typecode & 0xFC000L) == 147456L) {
															method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
															local894 = local1697.anInt1699 - Static149.anInt3555;
															local899 = local1697.anInt1703 - Static217.anInt4903;
															local904 = (int) (local1697.typecode >> 20 & 0x3L);
															if (local904 == 1 || local904 == 3) {
																if (local899 > -local894) {
																	method2397(local24, local18, local21 - 1, local18 - 1, local21);
																} else {
																	method2397(local24, local18, local21 + 1, local18 + 1, local21);
																}
															} else if (local899 > local894) {
																method2397(local24, local18, local21 - 1, local18 + 1, local21);
															} else {
																method2397(local24, local18, local21 + 1, local18 - 1, local21);
															}
														} else {
															method2391(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local1697.anInt1701, local1697.anInt1696, local1697.anInt1713, local1697.anInt1698);
														}
													}
													local1697.model.method4546(local1697.anInt1714, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1697.anInt1699 - Static149.anInt3555, local1697.anInt1706 - Static162.anInt3947, local1697.anInt1703 - Static217.anInt4903, local1697.typecode, local24, null);
												}
												for (local894 = local1697.anInt1701; local894 <= local1697.anInt1713; local894++) {
													for (local899 = local1697.anInt1696; local899 <= local1697.anInt1698; local899++) {
														@Pc(1863) Square local1863 = local31[local894][local899];
														if (local1863.anInt663 != 0) {
															aClass69_32.push(local1863);
														} else if ((local894 != local18 || local899 != local21) && local1863.aBoolean46) {
															aClass69_32.push(local1863);
														}
													}
												}
											}
											if (!local8.aBoolean47) {
												break;
											}
										} catch (@Pc(1895) Exception local1895) {
											local8.aBoolean47 = false;
											break;
										}
									}
								} while (!local8.aBoolean46);
							} while (local8.anInt663 != 0);
							if (local18 > anInt4069 || local18 <= anInt987) {
								break;
							}
							local153 = local31[local18 - 1][local21];
						} while (local153 != null && local153.aBoolean46);
						if (local18 < anInt4069 || local18 >= anInt15 - 1) {
							break;
						}
						local153 = local31[local18 + 1][local21];
					} while (local153 != null && local153.aBoolean46);
					if (local21 > anInt4539 || local21 <= anInt4698) {
						break;
					}
					local153 = local31[local18][local21 - 1];
				} while (local153 != null && local153.aBoolean46);
				if (local21 < anInt4539 || local21 >= anInt4866 - 1) {
					break;
				}
				local153 = local31[local18][local21 + 1];
			} while (local153 != null && local153.aBoolean46);
			local8.aBoolean46 = false;
			anInt1142--;
			@Pc(1999) GroundObject local1999 = local8.groundObject;
			if (local1999 != null && local1999.height != 0) {
				if (GameShell.glRenderer) {
					method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
				}
				if (local1999.bottomObj != null) {
					local1999.bottomObj.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1999.x - Static149.anInt3555, local1999.y - Static162.anInt3947 - local1999.height, local1999.z - Static217.anInt4903, local1999.typecode, local24, null);
				}
				if (local1999.middleObj != null) {
					local1999.middleObj.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1999.x - Static149.anInt3555, local1999.y - Static162.anInt3947 - local1999.height, local1999.z - Static217.anInt4903, local1999.typecode, local24, null);
				}
				if (local1999.topObj != null) {
					local1999.topObj.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local1999.x - Static149.anInt3555, local1999.y - Static162.anInt3947 - local1999.height, local1999.z - Static217.anInt4903, local1999.typecode, local24, null);
				}
			}
			if (local8.anInt670 != 0) {
				@Pc(2109) Decor local2109 = local8.decor;
				if (local2109 != null && !Static276.method4611(local27, local18, local21, local2109.aClass8_3.calcBoundingCylinder())) {
					if ((local2109.anInt1395 & local8.anInt670) != 0) {
						if (GameShell.glRenderer) {
							method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
						}
						local2109.aClass8_3.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local2109.anInt1390 + local2109.anInt1394 - Static149.anInt3555, local2109.anInt1391 - Static162.anInt3947, local2109.anInt1393 + local2109.anInt1392 - Static217.anInt4903, local2109.aLong52, local24, null);
					} else if (local2109.anInt1395 == 256) {
						local65 = local2109.anInt1390 - Static149.anInt3555;
						local115 = local2109.anInt1391 - Static162.anInt3947;
						local1332 = local2109.anInt1393 - Static217.anInt4903;
						local894 = local2109.anInt1388;
						if (local894 == 1 || local894 == 2) {
							local899 = -local65;
						} else {
							local899 = local65;
						}
						if (local894 == 2 || local894 == 3) {
							local904 = -local1332;
						} else {
							local904 = local1332;
						}
						if (local904 >= local899) {
							if (GameShell.glRenderer) {
								method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
							}
							local2109.aClass8_3.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local65 + local2109.anInt1394, local115, local1332 + local2109.anInt1392, local2109.aLong52, local24, null);
						} else if (local2109.aClass8_2 != null) {
							if (GameShell.glRenderer) {
								method2393(Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local24, local18, local21);
							}
							local2109.aClass8_2.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local65, local115, local1332, local2109.aLong52, local24, null);
						}
					}
				}
				@Pc(2275) Wall local2275 = local8.wall;
				if (local2275 != null) {
					if ((local2275.anInt3052 & local8.anInt670) != 0 && !Static260.method3850(local27, local18, local21, local2275.anInt3052)) {
						if (GameShell.glRenderer) {
							method2388(local2275.anInt3052, Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local27, local18, local21);
						}
						local2275.aClass8_6.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local2275.anInt3048 - Static149.anInt3555, local2275.anInt3051 - Static162.anInt3947, local2275.anInt3044 - Static217.anInt4903, local2275.aLong107, local24, null);
					}
					if ((local2275.anInt3049 & local8.anInt670) != 0 && !Static260.method3850(local27, local18, local21, local2275.anInt3049)) {
						if (GameShell.glRenderer) {
							method2388(local2275.anInt3049, Static149.anInt3555, Static162.anInt3947, Static217.anInt4903, local27, local18, local21);
						}
						local2275.aClass8_5.method4546(0, Static109.anInt2886, Static121.anInt3038, Static231.anInt5205, Static81.anInt2222, local2275.anInt3048 - Static149.anInt3555, local2275.anInt3051 - Static162.anInt3947, local2275.anInt3044 - Static217.anInt4903, local2275.aLong107, local24, null);
					}
				}
			}
			@Pc(2388) Square local2388;
			if (local24 < anInt3114 - 1) {
				local2388 = levelTiles[local24 + 1][local18][local21];
				if (local2388 != null && local2388.aBoolean46) {
					aClass69_32.push(local2388);
				}
			}
			if (local18 < anInt4069) {
				local2388 = local31[local18 + 1][local21];
				if (local2388 != null && local2388.aBoolean46) {
					aClass69_32.push(local2388);
				}
			}
			if (local21 < anInt4539) {
				local2388 = local31[local18][local21 + 1];
				if (local2388 != null && local2388.aBoolean46) {
					aClass69_32.push(local2388);
				}
			}
			if (local18 > anInt4069) {
				local2388 = local31[local18 - 1][local21];
				if (local2388 != null && local2388.aBoolean46) {
					aClass69_32.push(local2388);
				}
			}
			if (local21 > anInt4539) {
				local2388 = local31[local18][local21 - 1];
				if (local2388 != null && local2388.aBoolean46) {
					aClass69_32.push(local2388);
				}
			}
		}
	}

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(IIIIIII)V")
	public static void method4647(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) Occlude local3 = new Occlude();
		local3.anInt4452 = arg1 / 128;
		local3.anInt4446 = arg2 / 128;
		local3.anInt4461 = arg3 / 128;
		local3.anInt4464 = arg4 / 128;
		local3.anInt4453 = arg0;
		local3.anInt4460 = arg1;
		local3.anInt4445 = arg2;
		local3.anInt4458 = arg3;
		local3.anInt4449 = arg4;
		local3.anInt4444 = arg5;
		local3.anInt4447 = arg6;
		Static91.aClass120Array1[Static28.anInt917++] = local3;
	}

	@OriginalMember(owner = "client!pb", name = "b", descriptor = "(III)Lclient!jj;")
	public static GroundObject delObj(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) GroundObject local14 = local7.groundObject;
			local7.groundObject = null;
			return local14;
		}
	}

	@OriginalMember(owner = "client!sd", name = "c", descriptor = "(II)V")
	public static void method3884(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(7) Square local7 = levelTiles[0][arg0][arg1];
		for (@Pc(9) int local9 = 0; local9 < 3; local9++) {
			@Pc(30) Square local30 = levelTiles[local9][arg0][arg1] = levelTiles[local9 + 1][arg0][arg1];
			if (local30 != null) {
				local30.anInt672--;
				for (@Pc(40) int local40 = 0; local40 < local30.spriteCount; local40++) {
					@Pc(49) Sprite local49 = local30.sprites[local40];
					if ((local49.typecode >> 29 & 0x3L) == 2L && local49.anInt1701 == arg0 && local49.anInt1696 == arg1) {
						local49.anInt1709--;
					}
				}
			}
		}
		if (levelTiles[0][arg0][arg1] == null) {
			levelTiles[0][arg0][arg1] = new Square(0, arg0, arg1);
		}
		levelTiles[0][arg0][arg1].aClass3_Sub5_1 = local7;
		levelTiles[3][arg0][arg1] = null;
	}

	@OriginalMember(owner = "client!gj", name = "a", descriptor = "(III)Lclient!df;")
	public static Decor getDecor(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		return local7 == null ? null : local7.decor;
	}

	@OriginalMember(owner = "client!v", name = "a", descriptor = "(IIIJ)Z")
	public static boolean method523(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) long arg3) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return false;
		} else if (local7.wall != null && local7.wall.aLong107 == arg3) {
			return true;
		} else if (local7.decor != null && local7.decor.aLong52 == arg3) {
			return true;
		} else if (local7.groundDecor != null && local7.groundDecor.aLong26 == arg3) {
			return true;
		} else {
			for (@Pc(46) int local46 = 0; local46 < local7.spriteCount; local46++) {
				if (local7.sprites[local46].typecode == arg3) {
					return true;
				}
			}
			return false;
		}
	}

	@OriginalMember(owner = "client!kf", name = "b", descriptor = "(III)Lclient!ec;")
	public static Sprite getScene(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		}
		for (@Pc(13) int local13 = 0; local13 < local7.spriteCount; local13++) {
			@Pc(22) Sprite local22 = local7.sprites[local13];
			if ((local22.typecode >> 29 & 0x3L) == 2L && local22.anInt1701 == arg1 && local22.anInt1696 == arg2) {
				return local22;
			}
		}
		return null;
	}

	@OriginalMember(owner = "client!ke", name = "a", descriptor = "(Lclient!rh;IIIIIIIZ)V")
	public static void method2610(@OriginalArg(0) QuickGround arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) boolean arg8) {
		@Pc(6) int local6;
		@Pc(7) int local7 = local6 = (arg6 << 7) - Static149.anInt3555;
		@Pc(14) int local14;
		@Pc(15) int local15 = local14 = (arg7 << 7) - Static217.anInt4903;
		@Pc(20) int local20;
		@Pc(21) int local21 = local20 = local7 + 128;
		@Pc(26) int local26;
		@Pc(27) int local27 = local26 = local15 + 128;
		@Pc(37) int local37 = ClientBuild.groundh[arg1][arg6][arg7] - Static162.anInt3947;
		@Pc(49) int local49 = ClientBuild.groundh[arg1][arg6 + 1][arg7] - Static162.anInt3947;
		@Pc(63) int local63 = ClientBuild.groundh[arg1][arg6 + 1][arg7 + 1] - Static162.anInt3947;
		@Pc(75) int local75 = ClientBuild.groundh[arg1][arg6][arg7 + 1] - Static162.anInt3947;
		@Pc(85) int local85 = local15 * arg4 + local7 * arg5 >> 16;
		@Pc(95) int local95 = local15 * arg5 - local7 * arg4 >> 16;
		@Pc(97) int local97 = local85;
		@Pc(107) int local107 = local37 * arg3 - local95 * arg2 >> 16;
		@Pc(117) int local117 = local37 * arg2 + local95 * arg3 >> 16;
		@Pc(119) int local119 = local107;
		if (local117 < 50) {
			return;
		}
		local85 = local14 * arg4 + local21 * arg5 >> 16;
		@Pc(143) int local143 = local14 * arg5 - local21 * arg4 >> 16;
		local21 = local85;
		local85 = local49 * arg3 - local143 * arg2 >> 16;
		@Pc(165) int local165 = local49 * arg2 + local143 * arg3 >> 16;
		local49 = local85;
		if (local165 < 50) {
			return;
		}
		local85 = local27 * arg4 + local20 * arg5 >> 16;
		local27 = local27 * arg5 - local20 * arg4 >> 16;
		@Pc(193) int local193 = local85;
		local85 = local63 * arg3 - local27 * arg2 >> 16;
		local27 = local63 * arg2 + local27 * arg3 >> 16;
		local63 = local85;
		if (local27 < 50) {
			return;
		}
		local85 = local26 * arg4 + local6 * arg5 >> 16;
		@Pc(239) int local239 = local26 * arg5 - local6 * arg4 >> 16;
		@Pc(241) int local241 = local85;
		local85 = local75 * arg3 - local239 * arg2 >> 16;
		@Pc(261) int local261 = local75 * arg2 + local239 * arg3 >> 16;
		if (local261 < 50) {
			return;
		}
		@Pc(275) int local275 = Pix3D.anInt2471 + (local97 << 9) / local117;
		@Pc(283) int local283 = Pix3D.anInt2469 + (local119 << 9) / local117;
		@Pc(291) int local291 = Pix3D.anInt2471 + (local21 << 9) / local165;
		@Pc(299) int local299 = Pix3D.anInt2469 + (local49 << 9) / local165;
		@Pc(307) int local307 = Pix3D.anInt2471 + (local193 << 9) / local27;
		@Pc(315) int local315 = Pix3D.anInt2469 + (local63 << 9) / local27;
		@Pc(323) int local323 = Pix3D.anInt2471 + (local241 << 9) / local261;
		@Pc(331) int local331 = Pix3D.anInt2469 + (local85 << 9) / local261;
		Pix3D.anInt2473 = 0;
		@Pc(475) int local475;
		if ((local307 - local323) * (local299 - local331) - (local315 - local331) * (local291 - local323) > 0) {
			if (Static158.aBoolean187 && Static19.method583(Static89.anInt2388 + Pix3D.anInt2471, Static131.anInt3259 + Pix3D.anInt2469, local315, local331, local299, local307, local323, local291)) {
				groundX = arg6;
				groundZ = arg7;
			}
			if (!GameShell.glRenderer && !arg8) {
				Pix3D.aBoolean138 = false;
				if (local307 < 0 || local323 < 0 || local291 < 0 || local307 > Pix3D.anInt2472 || local323 > Pix3D.anInt2472 || local291 > Pix3D.anInt2472) {
					Pix3D.aBoolean138 = true;
				}
				if (arg0.anInt4869 == -1) {
					if (arg0.anInt4865 != 12345678) {
						Pix3D.method1928(local315, local331, local299, local307, local323, local291, arg0.anInt4865, arg0.anInt4864, arg0.anInt4867);
					}
				} else if (!Static159.aBoolean189) {
					local475 = Pix3D.anInterface1_2.method3234(arg0.anInt4869);
					Pix3D.method1928(local315, local331, local299, local307, local323, local291, Static216.method1640(local475, arg0.anInt4865), Static216.method1640(local475, arg0.anInt4864), Static216.method1640(local475, arg0.anInt4867));
				} else if (arg0.aBoolean241) {
					Pix3D.method1909(local315, local331, local299, local307, local323, local291, arg0.anInt4865, arg0.anInt4864, arg0.anInt4867, local97, local21, local241, local119, local49, local85, local117, local165, local261, arg0.anInt4869);
				} else {
					Pix3D.method1909(local315, local331, local299, local307, local323, local291, arg0.anInt4865, arg0.anInt4864, arg0.anInt4867, local193, local241, local21, local63, local85, local49, local27, local261, local165, arg0.anInt4869);
				}
			}
		}
		if ((local275 - local291) * (local331 - local299) - (local283 - local299) * (local323 - local291) <= 0) {
			return;
		}
		if (Static158.aBoolean187 && Static19.method583(Static89.anInt2388 + Pix3D.anInt2471, Static131.anInt3259 + Pix3D.anInt2469, local283, local299, local331, local275, local291, local323)) {
			groundX = arg6;
			groundZ = arg7;
		}
		if (GameShell.glRenderer || arg8) {
			return;
		}
		Pix3D.aBoolean138 = false;
		if (local275 < 0 || local291 < 0 || local323 < 0 || local275 > Pix3D.anInt2472 || local291 > Pix3D.anInt2472 || local323 > Pix3D.anInt2472) {
			Pix3D.aBoolean138 = true;
		}
		if (arg0.anInt4869 == -1) {
			if (arg0.anInt4872 != 12345678) {
				Pix3D.method1928(local283, local299, local331, local275, local291, local323, arg0.anInt4872, arg0.anInt4867, arg0.anInt4864);
			}
		} else if (Static159.aBoolean189) {
			Pix3D.method1909(local283, local299, local331, local275, local291, local323, arg0.anInt4872, arg0.anInt4867, arg0.anInt4864, local97, local21, local241, local119, local49, local85, local117, local165, local261, arg0.anInt4869);
		} else {
			local475 = Pix3D.anInterface1_2.method3234(arg0.anInt4869);
			Pix3D.method1928(local283, local299, local331, local275, local291, local323, Static216.method1640(local475, arg0.anInt4872), Static216.method1640(local475, arg0.anInt4867), Static216.method1640(local475, arg0.anInt4864));
		}
	}

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "()V")
	public static void method1500() {
		@Pc(3) int local3;
		@Pc(9) int local9;
		@Pc(14) int local14;
		if (Static197.aClass3_Sub5ArrayArrayArray2 != null) {
			for (local3 = 0; local3 < Static197.aClass3_Sub5ArrayArrayArray2.length; local3++) {
				for (local9 = 0; local9 < Static152.anInt3594; local9++) {
					for (local14 = 0; local14 < Static99.anInt2550; local14++) {
						Static197.aClass3_Sub5ArrayArrayArray2[local3][local9][local14] = null;
					}
				}
			}
		}
		Static36.aClass3_Sub14ArrayArray1 = null;
		if (Static276.aClass3_Sub5ArrayArrayArray3 != null) {
			for (local3 = 0; local3 < Static276.aClass3_Sub5ArrayArrayArray3.length; local3++) {
				for (local9 = 0; local9 < Static152.anInt3594; local9++) {
					for (local14 = 0; local14 < Static99.anInt2550; local14++) {
						Static276.aClass3_Sub5ArrayArrayArray3[local3][local9][local14] = null;
					}
				}
			}
		}
		Static195.aClass3_Sub14ArrayArray3 = null;
		Static28.anInt917 = 0;
		if (Static91.aClass120Array1 != null) {
			for (local3 = 0; local3 < Static28.anInt917; local3++) {
				Static91.aClass120Array1[local3] = null;
			}
		}
		if (aClass31Array3 != null) {
			for (local3 = 0; local3 < anInt726; local3++) {
				aClass31Array3[local3] = null;
			}
			anInt726 = 0;
		}
		if (Static25.aClass31Array2 != null) {
			for (local3 = 0; local3 < Static25.aClass31Array2.length; local3++) {
				Static25.aClass31Array2[local3] = null;
			}
		}
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(III)Lclient!bm;")
	public static GroundDecor method2210(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		return local7 == null || local7.groundDecor == null ? null : local7.groundDecor;
	}

	@OriginalMember(owner = "client!vf", name = "a", descriptor = "(III)Lclient!jh;")
	public static Wall method4509(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		return local7 == null ? null : local7.wall;
	}

	@OriginalMember(owner = "client!uc", name = "a", descriptor = "(III[[[BIBII)V")
	public static void renderAll(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) byte[][][] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) byte arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		cycleNo++;
		anInt1142 = 0;
		@Pc(9) int local9 = arg6 - 16;
		@Pc(13) int local13 = arg6 + 16;
		@Pc(17) int local17 = arg7 - 16;
		@Pc(21) int local21 = arg7 + 16;
		@Pc(32) int local32;
		@Pc(37) int local37;
		@Pc(183) int local183;
		for (@Pc(23) int local23 = anInt5276; local23 < anInt3114; local23++) {
			@Pc(30) Square[][] local30 = levelTiles[local23];
			for (local32 = anInt987; local32 < anInt15; local32++) {
				for (local37 = anInt4698; local37 < anInt4866; local37++) {
					@Pc(46) Square local46 = local30[local32][local37];
					if (local46 != null) {
						if (aBooleanArrayArray1[local32 + anInt5855 - anInt4069][local37 + anInt5855 - anInt4539] && (arg3 == null || local23 < arg4 || arg3[local23][local32][local37] != arg5)) {
							local46.aBoolean45 = true;
							local46.aBoolean46 = true;
							if (local46.spriteCount > 0) {
								local46.aBoolean47 = true;
							} else {
								local46.aBoolean47 = false;
							}
							anInt1142++;
						} else {
							local46.aBoolean45 = false;
							local46.aBoolean46 = false;
							local46.anInt663 = 0;
							if (local32 >= local9 && local32 <= local13 && local37 >= local17 && local37 <= local21) {
								if (local46.wall != null) {
									@Pc(103) Wall local103 = local46.wall;
									local103.aClass8_5.method4545(0, local23, local103.anInt3051, local103.anInt3048, local103.anInt3044);
									if (local103.aClass8_6 != null) {
										local103.aClass8_6.method4545(0, local23, local103.anInt3051, local103.anInt3048, local103.anInt3044);
									}
								}
								if (local46.decor != null) {
									@Pc(134) Decor local134 = local46.decor;
									local134.aClass8_3.method4545(local134.anInt1388, local23, local134.anInt1391, local134.anInt1390, local134.anInt1393);
									if (local134.aClass8_2 != null) {
										local134.aClass8_2.method4545(local134.anInt1388, local23, local134.anInt1391, local134.anInt1390, local134.anInt1393);
									}
								}
								if (local46.groundDecor != null) {
									@Pc(167) GroundDecor local167 = local46.groundDecor;
									local167.aClass8_1.method4545(0, local23, local167.anInt733, local167.anInt732, local167.anInt736);
								}
								if (local46.sprites != null) {
									for (local183 = 0; local183 < local46.spriteCount; local183++) {
										@Pc(192) Sprite local192 = local46.sprites[local183];
										local192.model.method4545(local192.anInt1714, local23, local192.anInt1706, local192.anInt1699, local192.anInt1703);
									}
								}
							}
						}
					}
				}
			}
		}
		@Pc(240) boolean local240 = ClientBuild.groundh == Static80.anIntArrayArrayArray19;
		if (GameShell.glRenderer) {
			@Pc(244) GL local244 = Static239.aGL1;
			local244.glPushMatrix();
			local244.glTranslatef((float) -arg0, (float) -arg1, (float) -arg2);
			if (local240) {
				Static156.method2959();
				Static27.method766(-1, 3);
				Static119.aBoolean153 = true;
				Static275.method4609();
				Static152.anInt3604 = -1;
				Static22.anInt730 = -1;
				for (local32 = 0; local32 < Static182.aClass3_Sub14ArrayArray2[0].length; local32++) {
					@Pc(285) GlSquare local285 = Static182.aClass3_Sub14ArrayArray2[0][local32];
					@Pc(294) float local294 = 251.5F - (local285.aBoolean140 ? 1.0F : 0.5F);
					if (local285.anInt2486 != Static152.anInt3604) {
						Static152.anInt3604 = local285.anInt2486;
						Static21.method619(local285.anInt2486);
						Static161.method3066(Static123.method2422());
					}
					local285.method1944(levelTiles, local294, false);
				}
				Static275.method4608();
			} else {
				local32 = anInt5276;
				while (true) {
					if (local32 >= anInt3114) {
						method2402(anInt4069, anInt4539, levelTiles);
						break;
					}
					for (local37 = 0; local37 < Static182.aClass3_Sub14ArrayArray2[local32].length; local37++) {
						@Pc(336) GlSquare local336 = Static182.aClass3_Sub14ArrayArray2[local32][local37];
						@Pc(350) float local350 = 201.5F - (float) local32 * 50.0F - (local336.aBoolean140 ? 1.0F : 0.5F);
						if (local336.anInt2485 != -1 && Pix3D.anInterface1_2.method3237(local336.anInt2485) == 4 && Static220.aBoolean244) {
							Static21.method619(local336.anInt2486);
						}
						local336.method1944(levelTiles, local350, false);
					}
					if (local32 == 0 && Static139.anInt3451 > 0) {
						Static239.method4159(101.5F);
						Static242.method4198(anInt4069, anInt4539, anInt5855, arg1, aBooleanArrayArray1, ClientBuild.groundh[0]);
					}
					local32++;
				}
			}
			local244.glPopMatrix();
		}
		@Pc(434) int local434;
		@Pc(438) int local438;
		@Pc(450) Square local450;
		@Pc(399) int local399;
		@Pc(406) Square[][] local406;
		@Pc(415) int local415;
		@Pc(428) int local428;
		for (local399 = anInt5276; local399 < anInt3114; local399++) {
			local406 = levelTiles[local399];
			for (local37 = -anInt5855; local37 <= 0; local37++) {
				local415 = anInt4069 + local37;
				local183 = anInt4069 - local37;
				if (local415 >= anInt987 || local183 < anInt15) {
					for (local428 = -anInt5855; local428 <= 0; local428++) {
						local434 = anInt4539 + local428;
						local438 = anInt4539 - local428;
						if (local415 >= anInt987) {
							if (local434 >= anInt4698) {
								local450 = local406[local415][local434];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, true);
								}
							}
							if (local438 < anInt4866) {
								local450 = local406[local415][local438];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, true);
								}
							}
						}
						if (local183 < anInt15) {
							if (local434 >= anInt4698) {
								local450 = local406[local183][local434];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, true);
								}
							}
							if (local438 < anInt4866) {
								local450 = local406[local183][local438];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, true);
								}
							}
						}
						if (anInt1142 == 0) {
							if (!local240) {
								Static158.aBoolean187 = false;
							}
							return;
						}
					}
				}
			}
		}
		for (local399 = anInt5276; local399 < anInt3114; local399++) {
			local406 = levelTiles[local399];
			for (local37 = -anInt5855; local37 <= 0; local37++) {
				local415 = anInt4069 + local37;
				local183 = anInt4069 - local37;
				if (local415 >= anInt987 || local183 < anInt15) {
					for (local428 = -anInt5855; local428 <= 0; local428++) {
						local434 = anInt4539 + local428;
						local438 = anInt4539 - local428;
						if (local415 >= anInt987) {
							if (local434 >= anInt4698) {
								local450 = local406[local415][local434];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, false);
								}
							}
							if (local438 < anInt4866) {
								local450 = local406[local415][local438];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, false);
								}
							}
						}
						if (local183 < anInt15) {
							if (local434 >= anInt4698) {
								local450 = local406[local183][local434];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, false);
								}
							}
							if (local438 < anInt4866) {
								local450 = local406[local183][local438];
								if (local450 != null && local450.aBoolean45) {
									method4245(local450, false);
								}
							}
						}
						if (anInt1142 == 0) {
							if (!local240) {
								Static158.aBoolean187 = false;
							}
							return;
						}
					}
				}
			}
		}
		Static158.aBoolean187 = false;
	}

	@OriginalMember(owner = "client!vl", name = "a", descriptor = "(III)Lclient!bm;")
	public static GroundDecor method4526(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) GroundDecor local14 = local7.groundDecor;
			local7.groundDecor = null;
			return local14;
		}
	}

	@OriginalMember(owner = "client!cd", name = "a", descriptor = "(IIIIZ)V")
	public static void method792(@OriginalArg(3) int arg0, @OriginalArg(4) boolean arg1) {
		Static152.anInt3594 = 104;
		Static99.anInt2550 = 104;
		anInt5855 = arg0;
		Static197.aClass3_Sub5ArrayArrayArray2 = new Square[4][Static152.anInt3594][Static99.anInt2550];
		Static107.anIntArrayArrayArray10 = new int[4][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
		if (GameShell.glRenderer) {
			Static36.aClass3_Sub14ArrayArray1 = new GlSquare[4][];
		}
		if (arg1) {
			Static276.aClass3_Sub5ArrayArrayArray3 = new Square[1][Static152.anInt3594][Static99.anInt2550];
			anIntArrayArray11 = new int[Static152.anInt3594][Static99.anInt2550];
			Static80.anIntArrayArrayArray19 = new int[1][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
			if (GameShell.glRenderer) {
				Static195.aClass3_Sub14ArrayArray3 = new GlSquare[1][];
			}
		} else {
			Static276.aClass3_Sub5ArrayArrayArray3 = null;
			anIntArrayArray11 = null;
			Static80.anIntArrayArrayArray19 = null;
			Static195.aClass3_Sub14ArrayArray3 = null;
		}
		Static278.method4648(false);
		Static91.aClass120Array1 = new Occlude[500];
		Static28.anInt917 = 0;
		Static247.aClass120Array2 = new Occlude[500];
		Static215.anInt4870 = 0;
		Static140.anIntArrayArrayArray12 = new int[4][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
		aClass31Array3 = new Sprite[5000];
		anInt726 = 0;
		Static25.aClass31Array2 = new Sprite[100];
		aBooleanArrayArray1 = new boolean[anInt5855 + anInt5855 + 1][anInt5855 + anInt5855 + 1];
		Static89.aBooleanArrayArray3 = new boolean[anInt5855 + anInt5855 + 2][anInt5855 + anInt5855 + 2];
		Static232.aByteArrayArrayArray13 = new byte[4][Static152.anInt3594][Static99.anInt2550];
	}

	@OriginalMember(owner = "client!l", name = "a", descriptor = "(III)J")
	public static long method2703(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		return local7 == null || local7.decor == null ? 0L : local7.decor.aLong52;
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(III)Lclient!jh;")
	public static Wall method2276(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) Wall local14 = local7.wall;
			local7.wall = null;
			return local14;
		}
	}

	@OriginalMember(owner = "client!nh", name = "a", descriptor = "(IIIILclient!th;JZ)V")
	public static void method2570(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) ModelSource arg4, @OriginalArg(5) long arg5, @OriginalArg(6) boolean arg6) {
		if (arg4 == null) {
			return;
		}
		@Pc(6) GroundDecor local6 = new GroundDecor();
		local6.aClass8_1 = arg4;
		local6.anInt732 = arg1 * 128 + 64;
		local6.anInt736 = arg2 * 128 + 64;
		local6.anInt733 = arg3;
		local6.aLong26 = arg5;
		local6.aBoolean49 = arg6;
		if (levelTiles[arg0][arg1][arg2] == null) {
			levelTiles[arg0][arg1][arg2] = new Square(arg0, arg1, arg2);
		}
		levelTiles[arg0][arg1][arg2].groundDecor = local6;
	}

	@OriginalMember(owner = "client!vj", name = "a", descriptor = "(III)J")
	public static long method4521(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		return local7 == null || local7.wall == null ? 0L : local7.wall.aLong107;
	}

	@OriginalMember(owner = "client!cl", name = "a", descriptor = "(III)J")
	public static long method899(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return 0L;
		}
		for (@Pc(13) int local13 = 0; local13 < local7.spriteCount; local13++) {
			@Pc(22) Sprite local22 = local7.sprites[local13];
			if ((local22.typecode >> 29 & 0x3L) == 2L && local22.anInt1701 == arg1 && local22.anInt1696 == arg2) {
				return local22.typecode;
			}
		}
		return 0L;
	}

	@OriginalMember(owner = "client!bj", name = "a", descriptor = "(III)J")
	public static long method602(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = levelTiles[arg0][arg1][arg2];
		return local7 == null || local7.groundDecor == null ? 0L : local7.groundDecor.aLong26;
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(IIIIIIIIIIIIIIIIIIII)V")
	public static void method3305(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13, @OriginalArg(14) int arg14, @OriginalArg(15) int arg15, @OriginalArg(16) int arg16, @OriginalArg(17) int arg17, @OriginalArg(18) int arg18, @OriginalArg(19) int arg19) {
		@Pc(12) QuickGround local12;
		@Pc(14) int local14;
		if (arg3 == 0) {
			local12 = new QuickGround(arg10, arg11, arg12, arg13, -1, arg18, false);
			for (local14 = arg0; local14 >= 0; local14--) {
				if (levelTiles[local14][arg1][arg2] == null) {
					levelTiles[local14][arg1][arg2] = new Square(local14, arg1, arg2);
				}
			}
			levelTiles[arg0][arg1][arg2].aClass131_1 = local12;
		} else if (arg3 == 1) {
			local12 = new QuickGround(arg14, arg15, arg16, arg17, arg5, arg19, arg6 == arg7 && arg6 == arg8 && arg6 == arg9);
			for (local14 = arg0; local14 >= 0; local14--) {
				if (levelTiles[local14][arg1][arg2] == null) {
					levelTiles[local14][arg1][arg2] = new Square(local14, arg1, arg2);
				}
			}
			levelTiles[arg0][arg1][arg2].aClass131_1 = local12;
		} else {
			@Pc(134) Ground local134 = new Ground(arg3, arg4, arg5, arg1, arg2, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
			for (local14 = arg0; local14 >= 0; local14--) {
				if (levelTiles[local14][arg1][arg2] == null) {
					levelTiles[local14][arg1][arg2] = new Square(local14, arg1, arg2);
				}
			}
			levelTiles[arg0][arg1][arg2].aClass43_1 = local134;
		}
	}
}
