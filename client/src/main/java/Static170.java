import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static170 {

	@OriginalMember(owner = "client!nh", name = "Z", descriptor = "I")
	public static int anInt3241 = 128;

	@OriginalMember(owner = "client!nh", name = "fb", descriptor = "[I")
	public static final int[] anIntArray306 = new int[] { 4, 4, 1, 2, 6, 4, 2, 49, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	@OriginalMember(owner = "client!nh", name = "hb", descriptor = "Lclient!na;")
	public static final JagString aClass100_623 = JagString.wrap("::fps ");

	@OriginalMember(owner = "client!nh", name = "a", descriptor = "(I[B)Z")
	public static boolean method2572(@OriginalArg(1) byte[] arg0) {
		@Pc(13) Packet local13 = new Packet(arg0);
		@Pc(17) int local17 = local13.g1();
		if (local17 != 1) {
			return false;
		}
		@Pc(33) boolean local33 = local13.g1() == 1;
		if (local33) {
			Static97.method1962(local13);
		}
		Static69.method1546(local13);
		return true;
	}

}
