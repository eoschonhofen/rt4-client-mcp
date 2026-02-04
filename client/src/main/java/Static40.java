import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static40 {

	@OriginalMember(owner = "client!da", name = "ab", descriptor = "I")
	public static int anInt1275;

	@OriginalMember(owner = "client!da", name = "d", descriptor = "(I)V")
	public static void method1008() {
		if (Static155.anInt3718 == -1 || Static52.anInt1694 == -1) {
			return;
		}
		@Pc(27) int local27 = (Static233.anInt5224 * (Static114.anInt5843 - Static228.anInt5101) >> 16) + Static228.anInt5101;
		@Pc(30) float[] local30 = new float[3];
		Static233.anInt5224 += local27;
		if (Static233.anInt5224 >= 65535) {
			Static233.anInt5224 = 65535;
			if (Static186.aBoolean205) {
				Static13.aBoolean16 = false;
			} else {
				Static13.aBoolean16 = true;
			}
			Static186.aBoolean205 = true;
		} else {
			Static186.aBoolean205 = false;
			Static13.aBoolean16 = false;
		}
		@Pc(66) float local66 = (float) Static233.anInt5224 / 65535.0F;
		@Pc(70) int local70 = Static127.anInt3125 * 2;
		@Pc(141) int local141;
		@Pc(131) int local131;
		@Pc(111) int local111;
		@Pc(119) int local119;
		@Pc(146) int local146;
		@Pc(155) int local155;
		@Pc(173) int local173;
		for (@Pc(72) int local72 = 0; local72 < 3; local72++) {
			local111 = (Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 2][local72] + Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 2][local72] - Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 3][local72]) * 3;
			local119 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70][local72];
			local131 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 1][local72] * 3;
			local141 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70][local72] * 3;
			local146 = local131 - local141;
			local155 = local111 + local141 - local131 * 2;
			local173 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 2][local72] + local131 - local119 - local111;
			local30[local72] = (float) local119 + (((float) local173 * local66 + (float) local155) * local66 + (float) local146) * local66;
		}
		Client.anInt40 = (int) local30[1] * -1;
		Client.anInt3439 = (int) local30[0] - Client.mapBuildBaseX * 128;
		Client.anInt3302 = (int) local30[2] - Client.mapBuildBaseZ * 128;
		@Pc(226) float[] local226 = new float[3];
		local141 = Static75.anInt2119 * 2;
		for (local131 = 0; local131 < 3; local131++) {
			local111 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141][local131] * 3;
			local146 = (Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 2][local131] + Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 2][local131] - Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 3][local131]) * 3;
			local155 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141][local131];
			local119 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 1][local131] * 3;
			local173 = local119 - local111;
			@Pc(313) int local313 = local146 + local111 - local119 * 2;
			@Pc(331) int local331 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 2][local131] + local119 - local146 - local155;
			local226[local131] = (float) local155 + local66 * (local66 * (local66 * (float) local331 + (float) local313) + (float) local173);
		}
		@Pc(363) float local363 = local226[0] - local30[0];
		@Pc(371) float local371 = local226[2] - local30[2];
		@Pc(382) float local382 = (local226[1] - local30[1]) * -1.0F;
		@Pc(392) double local392 = Math.sqrt((double) (local371 * local371 + local363 * local363));
		Static146.aFloat15 = (float) Math.atan2((double) local382, local392);
		Static84.aFloat10 = -((float) Math.atan2((double) local363, (double) local371));
		Client.anInt5333 = (int) ((double) Static146.aFloat15 * 325.949D) & 0x7FF;
		Client.anInt4358 = (int) ((double) Static84.aFloat10 * 325.949D) & 0x7FF;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(ILclient!ve;Z)Lclient!ok;")
	public static Pix8 method1010(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1) {
		return Static254.method4346(arg1, arg0) ? Static276.method4614() : null;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;")
	public static String replace(@OriginalArg(0) String arg0, @OriginalArg(1) String arg1, @OriginalArg(3) String arg2) {
		for (@Pc(5) int local5 = arg2.indexOf(arg0); local5 != -1; local5 = arg2.indexOf(arg0, local5 + arg1.length())) {
			arg2 = arg2.substring(0, local5) + arg1 + arg2.substring(arg0.length() + local5);
		}
		return arg2;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIILclient!be;)V")
	public static void method1015(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) IfType arg2) {
		if (Client.dragComponent != null || Client.isMenuOpen || (arg2 == null || Static89.method1836(arg2) == null)) {
			return;
		}
		Client.dragComponent = arg2;
		Static4.aClass13_1 = Static89.method1836(arg2);
		Static246.anInt5388 = arg1;
		Static138.aBoolean172 = false;
		Static213.anInt4851 = 0;
		Static165.anInt4035 = arg0;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIIILclient!na;JI)V")
	public static void method1016(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) JagString arg3, @OriginalArg(5) long arg4, @OriginalArg(6) int arg5) {
		@Pc(8) Packet local8 = new Packet(128);
		local8.p1(10);
		local8.p2((int) (Math.random() * 99999.0D));
		local8.p2(530);
		if (GlobalConfig.ARIOS_LOGIN_STRINGS) {
			local8.pjstr(Static79.toBaseDisplayName(arg4));
		} else {
			local8.p8(arg4);
		}
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.pjstr(arg3);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.p2(Client.affid);
		local8.p1(arg0);
		local8.p1(arg2);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.p2(arg5);
		local8.p2(arg1);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.rsaenc(Static86.aBigInteger1, Static256.aBigInteger2);
		Client.out.pos = 0;
		Client.out.p1(36);
		Client.out.p1(local8.pos);
		Client.out.tinyenc(local8.data, local8.pos);
		Client.accountCreateError = -3;
		Client.accountCreateStep = 1;
		Client.accountCreateWaitingTime = 0;
		Client.accountCreateFailCount = 0;
	}

	@OriginalMember(owner = "client!da", name = "h", descriptor = "(B)V")
	public static void method1019() {
		IfType.spriteCache.clear();
		Static124.aClass99_17.clear();
		IfType.fontCache.clear();
	}
}
