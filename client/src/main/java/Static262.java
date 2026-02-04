import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static262 {

	@OriginalMember(owner = "client!vf", name = "c", descriptor = "I")
	public static int anInt5752;

	@OriginalMember(owner = "client!vf", name = "g", descriptor = "[I")
	public static final int[] anIntArray515 = new int[14];

	@OriginalMember(owner = "client!vf", name = "h", descriptor = "Lclient!na;")
	public static final JagString aClass100_1078 = Text.aClass100_1080;

	@OriginalMember(owner = "client!vf", name = "m", descriptor = "I")
	public static int anInt5754 = -1;

	@OriginalMember(owner = "client!vf", name = "a", descriptor = "(IB)Lclient!na;")
	public static JagString method4510(@OriginalArg(0) int arg0) {
		return arg0 >= 999999999 ? Static220.aClass100_930 : JagString.parseInt(arg0);
	}
}
