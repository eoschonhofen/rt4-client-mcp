import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static249 {

	@OriginalMember(owner = "client!ud", name = "K", descriptor = "I")
	public static int loginGamePort;

	@OriginalMember(owner = "client!ud", name = "I", descriptor = "Lclient!na;")
	public static final JagString aClass100_1037 = JagString.wrap("Chargement en cours)3)3)3");

	@OriginalMember(owner = "client!ud", name = "L", descriptor = "Lclient!na;")
	public static final JagString aClass100_1038 = JagString.wrap("clignotant1:");

	@OriginalMember(owner = "client!ud", name = "O", descriptor = "I")
	public static int anInt5431 = 0;

	@OriginalMember(owner = "client!ud", name = "Q", descriptor = "Lclient!na;")
	public static final JagString aClass100_1039 = JagString.wrap(" x ");

	@OriginalMember(owner = "client!ud", name = "T", descriptor = "[I")
	public static final int[] anIntArray478 = new int[32];

	@OriginalMember(owner = "client!ud", name = "a", descriptor = "(ILclient!be;)Z")
	public static boolean method4265(@OriginalArg(1) IfType arg0) {
		if (arg0.clientCode == 205) {
			Static267.anInt5775 = 250;
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ud", name = "d", descriptor = "(I)V")
	public static void method4266() {
		VarpType.recentUse.method3104();
	}
}
