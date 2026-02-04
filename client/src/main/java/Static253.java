import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static253 {

	@OriginalMember(owner = "client!ui", name = "Q", descriptor = "I")
	public static int anInt5526;

	@OriginalMember(owner = "client!ui", name = "T", descriptor = "F")
	public static float aFloat36;

	@OriginalMember(owner = "client!ui", name = "mb", descriptor = "F")
	public static float aFloat37;

	@OriginalMember(owner = "client!ui", name = "c", descriptor = "(II)I")
	public static int method4328(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "client!ui", name = "d", descriptor = "(II)V")
	public static void method4332(@OriginalArg(0) int arg0) {
		if (arg0 >= 0 && Static258.aBooleanArray130.length > arg0) {
			Static258.aBooleanArray130[arg0] = !Static258.aBooleanArray130[arg0];
		}
	}

}
