import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static13 {

	@OriginalMember(owner = "client!bc", name = "W", descriptor = "I")
	public static int anInt435;

	@OriginalMember(owner = "client!bc", name = "X", descriptor = "I")
	public static int anInt436;

	@OriginalMember(owner = "client!bc", name = "Z", descriptor = "I")
	public static int anInt437;

	@OriginalMember(owner = "client!bc", name = "I", descriptor = "Z")
	public static boolean aBoolean16 = false;

	@OriginalMember(owner = "client!bc", name = "N", descriptor = "Lclient!lb;")
	public static final MonochromeImageCacheEntry aClass3_Sub23_1 = new MonochromeImageCacheEntry(0, 0);

	@OriginalMember(owner = "client!bc", name = "f", descriptor = "(B)Lclient!na;")
	public static JagString method471() {
		@Pc(32) JagString local32;
		if (Static260.anInt5014 == 1 && Static231.anInt5204 < 2) {
			local32 = Static34.concatenate(new JagString[] { Static222.aClass100_937, Static206.aClass100_901, Static34.aClass100_203, Static225.aClass100_961 });
		} else if (Static241.aBoolean302 && Static231.anInt5204 < 2) {
			local32 = Static34.concatenate(new JagString[] { Static102.aClass100_545, Static206.aClass100_901, Static78.aClass100_466, Static225.aClass100_961 });
		} else if (Static172.aBoolean199 && Static187.aBooleanArray101[81] && Static231.anInt5204 > 2) {
			local32 = Static269.method2228(Static231.anInt5204 - 2);
		} else {
			local32 = Static269.method2228(Static231.anInt5204 - 1);
		}
		if (Static231.anInt5204 > 2) {
			local32 = Static34.concatenate(new JagString[] { local32, Static1.aClass100_2, Static123.method2423(Static231.anInt5204 - 2), Static253.aClass100_1054 });
		}
		return local32;
	}

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "(II)V")
	public static void method472(@OriginalArg(0) int arg0) {
		@Pc(14) ClientInvCache local14 = (ClientInvCache) Static20.aClass133_2.find((long) arg0);
		if (local14 != null) {
			local14.unlink();
		}
	}

}
