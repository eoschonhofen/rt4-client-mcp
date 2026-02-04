import java.util.Calendar;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static102 {

	@OriginalMember(owner = "client!hn", name = "K", descriptor = "Ljava/util/Calendar;")
	public static final Calendar aCalendar2 = Calendar.getInstance();

	@OriginalMember(owner = "client!hn", name = "W", descriptor = "Lclient!na;")
	public static JagString aClass100_545 = null;

	@OriginalMember(owner = "client!hn", name = "X", descriptor = "I")
	public static int anInt2679 = 0;

	@OriginalMember(owner = "client!hn", name = "Y", descriptor = "I")
	public static int anInt2680 = 0;

	@OriginalMember(owner = "client!hn", name = "d", descriptor = "(I)Lclient!mm;")
	public static Pix32 method2071() {
		@Pc(13) int local13 = Static26.anIntArray66[0] * Static254.anIntArray488[0];
		@Pc(17) byte[] local17 = Static7.aByteArrayArray5[0];
		@Pc(78) Pix32 local78;
		if (Static159.aBooleanArray87[0]) {
			@Pc(30) byte[] local30 = Static64.aByteArrayArray9[0];
			@Pc(33) int[] local33 = new int[local13];
			for (@Pc(35) int local35 = 0; local35 < local13; local35++) {
				local33[local35] = (local30[local35] & 0xFF) << 24 | Static259.anIntArray513[local17[local35] & 0xFF];
			}
			local78 = new SoftwareAlphaPix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], local33);
		} else {
			@Pc(83) int[] local83 = new int[local13];
			for (@Pc(85) int local85 = 0; local85 < local13; local85++) {
				local83[local85] = Static259.anIntArray513[local17[local85] & 0xFF];
			}
			local78 = new Pix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], local83);
		}
		Static75.method1631();
		return local78;
	}

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(Lclient!ve;ILclient!ve;)V")
	public static void method2074(@OriginalArg(0) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		Static114.aClass3_Sub2_Sub9_42 = Static127.method2462(Static166.anInt4049, arg1, arg0);
		if (GameShell.glRenderer) {
			Static265.aClass3_Sub2_Sub9_Sub1_2 = Static122.method2412(Static166.anInt4049, arg0, arg1);
		} else {
			Static265.aClass3_Sub2_Sub9_Sub1_2 = (PixFont) Static114.aClass3_Sub2_Sub9_42;
		}
		Static215.aClass3_Sub2_Sub9_32 = Static127.method2462(Static130.anInt3161, arg1, arg0);
		Static280.aClass3_Sub2_Sub9_43 = Static127.method2462(Static73.anInt2077, arg1, arg0);
	}

}
