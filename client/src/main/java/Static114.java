import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static114 {

	@OriginalMember(owner = "client!j", name = "x", descriptor = "Lclient!rk;")
	public static PixFontGeneric aClass3_Sub2_Sub9_42;

	@OriginalMember(owner = "client!j", name = "v", descriptor = "I")
	public static int anInt5831 = 0;

	@OriginalMember(owner = "client!j", name = "z", descriptor = "Lclient!na;")
	public static final JagString aClass100_1099 = JagString.wrap("::shiftclick");

	@OriginalMember(owner = "client!j", name = "K", descriptor = "I")
	public static int anInt5843 = 0;

	@OriginalMember(owner = "client!j", name = "M", descriptor = "Lclient!na;")
	public static final JagString aClass100_1100 = JagString.wrap("::replacecanvas");

	@OriginalMember(owner = "client!j", name = "a", descriptor = "([BI)Lclient!dd;")
	public static PixFont method4635(@OriginalArg(0) byte[] arg0) {
		if (arg0 == null) {
			return null;
		} else {
			@Pc(22) PixFont local22 = new PixFont(arg0, Static274.anIntArray440, Static269.anIntArray252, Static254.anIntArray488, Static26.anIntArray66, Static7.aByteArrayArray5);
			Static75.method1631();
			return local22;
		}
	}

	@OriginalMember(owner = "client!j", name = "g", descriptor = "(I)V")
	public static void method4637() {
		Static165.aClass3_Sub2_Sub1_8 = null;
		Static39.aClass3_Sub2_Sub1_1 = null;
		Static92.aClass3_Sub2_Sub1_6 = null;
		Static181.aClass3_Sub2_Sub1_9 = null;
		Static204.aClass3_Sub2_Sub1_10 = null;
	}
}
