import org.openrs2.deob.annotation.OriginalMember;

public final class Static185 {

	@OriginalMember(owner = "client!p", name = "e", descriptor = "I")
	public static int anInt4370;

	@OriginalMember(owner = "client!p", name = "g", descriptor = "F")
	public static float aFloat23;

	@OriginalMember(owner = "client!p", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_823 = JagString.wrap("::serverjs5drop");

	@OriginalMember(owner = "client!p", name = "a", descriptor = "(I)V")
	public static void method3395() {
		if (Client.loginStep == 5) {
			Client.loginStep = 6;
		}
	}

}
