import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static18 {

	@OriginalMember(owner = "client!bh", name = "M", descriptor = "Z")
	public static boolean aBoolean40;

	@OriginalMember(owner = "client!bh", name = "s", descriptor = "Lclient!na;")
	public static final JagString aClass100_106 = JagString.wrap("p11_full");

	@OriginalMember(owner = "client!bh", name = "v", descriptor = "Lclient!na;")
	public static final JagString aClass100_107 = JagString.wrap("floorshadows");

	@OriginalMember(owner = "client!bh", name = "z", descriptor = "Z")
	public static boolean aBoolean39 = true;

	@OriginalMember(owner = "client!bh", name = "C", descriptor = "Lclient!na;")
	public static final JagString aClass100_108 = JagString.wrap("<br>(X100(U(Y");

	@OriginalMember(owner = "client!bh", name = "G", descriptor = "[I")
	public static final int[] anIntArray57 = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IB)I")
	public static int method554(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIIIIIII)V")
	public static void method555(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		@Pc(5) int local5;
		@Pc(29) int local29;
		if (GameShell.glRenderer) {
			local5 = arg1 - 334;
			if (local5 < 0) {
				local5 = 0;
			} else if (local5 > 100) {
				local5 = 100;
			}
			local29 = local5 * (Static187.aShort27 - Static263.aShort30) / 100 + Static263.aShort30;
			arg3 = local29 * arg3 >> 8;
		}
		local5 = 2048 - arg6 & 0x7FF;
		local29 = 2048 - arg4 & 0x7FF;
		@Pc(55) int local55 = 0;
		@Pc(57) int local57 = arg3;
		@Pc(59) int local59 = 0;
		@Pc(72) int local72;
		@Pc(68) int local68;
		if (local5 != 0) {
			local68 = Pix3D.cosTable[local5];
			local72 = Pix3D.sinTable[local5];
			local59 = local72 * -arg3 >> 16;
			local57 = local68 * arg3 >> 16;
		}
		if (local29 != 0) {
			local72 = Pix3D.sinTable[local29];
			local68 = Pix3D.cosTable[local29];
			local55 = local72 * local57 >> 16;
			local57 = local57 * local68 >> 16;
		}
		Client.anInt5333 = arg6;
		Client.anInt4358 = arg4;
		Client.anInt3302 = arg5 - local57;
		Client.anInt3439 = arg0 - local55;
		Client.anInt40 = arg2 - local59;
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(B)Lclient!ba;")
	public static GWCWorld method556() {
		Static51.anInt1682 = 0;
		return GWCWorld.method1821();
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIII)V")
	public static void method559(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(7) Square local7 = World.activeTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return;
		}
		@Pc(13) Decor local13 = local7.decor;
		if (local13 != null) {
			local13.anInt1394 = local13.anInt1394 * arg3 / 16;
			local13.anInt1392 = local13.anInt1392 * arg3 / 16;
		}
	}
}
