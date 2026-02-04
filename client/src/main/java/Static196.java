import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static196 {

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "[Lclient!ba;")
	public static GWCWorld[] aClass10_Sub1Array2;

	@OriginalMember(owner = "client!pl", name = "e", descriptor = "[I")
	public static final int[] anIntArray408 = new int[500];

	@OriginalMember(owner = "client!pl", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_863 = JagString.wrap(":tradereq:");

	@OriginalMember(owner = "client!pl", name = "i", descriptor = "I")
	public static int anInt4587 = 0;

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "(II)V")
	public static void method3534(@OriginalArg(0) int arg0) {
		if (Client.state == arg0) {
			return;
		}
		if (Client.state == 0) {
			Static163.method3097();
		}
		if (arg0 == 40) {
			Static49.method1208();
		}
		@Pc(37) boolean local37 = arg0 == 5 || arg0 == 10 || arg0 == 28;
		if (arg0 != 40 && Static233.aClass95_4 != null) {
			Static233.aClass95_4.close();
			Static233.aClass95_4 = null;
		}
		if (arg0 == 25 || arg0 == 28) {
			Static271.locModelLoadCount = 0;
			Static230.mapPrevLoadCount = 1;
			Static233.mapLoadingStage = 0;
			Static38.locModelLoadPrevCount = 1;
			Static175.mapLoadCount = 0;
			Static116.method2325(true);
		}
		if (arg0 == 25 || arg0 == 10) {
			Static123.method2418();
		}
		if (arg0 == 5) {
			Static181.method3344(Client.sprites);
		} else {
			Static119.method2381();
		}
		@Pc(106) boolean local106 = Client.state == 5 || Client.state == 10 || Client.state == 28;
		if (local106 != local37) {
			if (local37) {
				Static221.anInt4363 = Static250.anInt5441;
				if (Static12.anInt391 == 0) {
					Static29.method801();
				} else {
					Static257.method526(Static250.anInt5441, Client.songs, 255);
				}
				Static107.aClass73_3.sendLoginLogoutPacket(false);
			} else {
				Static29.method801();
				Static107.aClass73_3.sendLoginLogoutPacket(true);
			}
		}
		if (GameShell.glRenderer && (arg0 == 25 || arg0 == 28 || arg0 == 40)) {
			Static239.method4160();
		}
		Client.state = arg0;
	}

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "(ZI)V")
	public static void method3535(@OriginalArg(0) boolean arg0) {
		Static221.huetot = new int[104];
		Static139.sattot = new int[104];
		Static146.anInt3508 = 99;
		Static251.comtot = new int[104];
		@Pc(14) byte local14;
		if (arg0) {
			local14 = 1;
		} else {
			local14 = 4;
		}
		Static163.aByteArrayArrayArray11 = new byte[local14][104][104];
		Static128.tot = new int[104];
		Static60.anIntArrayArrayArray6 = new int[local14][105][105];
		Static118.shadow = new byte[local14][105][105];
		Static240.aByteArrayArrayArray14 = new byte[local14][104][104];
		Static279.ligtot = new int[104];
		Static4.aByteArrayArrayArray1 = new byte[local14][104][104];
		Static253.floort1 = new byte[local14][104][104];
	}

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "(I)Lclient!mm;")
	public static Pix32 method3537() {
		@Pc(13) int local13 = Static254.anIntArray488[0] * Static26.anIntArray66[0];
		@Pc(17) byte[] local17 = Static7.aByteArrayArray5[0];
		@Pc(20) int[] local20 = new int[local13];
		for (@Pc(22) int local22 = 0; local22 < local13; local22++) {
			local20[local22] = Static259.anIntArray513[local17[local22] & 0xFF];
		}
		@Pc(57) Pix32 local57 = new Pix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], local20);
		Static75.method1631();
		return local57;
	}
}
