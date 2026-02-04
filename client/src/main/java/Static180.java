import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static180 {

	@OriginalMember(owner = "client!og", name = "b", descriptor = "I")
	public static int anInt4264;

	@OriginalMember(owner = "client!og", name = "a", descriptor = "(II)V")
	public static void method3328(@OriginalArg(0) int arg0) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(10, arg0);
		local12.method1007();
	}

	@OriginalMember(owner = "client!og", name = "a", descriptor = "(I)V")
	public static void method3329() {
		Static110.aClass99_15.clear();
	}

	@OriginalMember(owner = "client!og", name = "a", descriptor = "(III)I")
	public static int method3330(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (arg0 > 22050) {
			arg1 = arg0;
			arg0 = 22050;
		}
		while (arg0 != 0) {
			@Pc(21) int local21 = arg1 % arg0;
			arg1 = arg0;
			arg0 = local21;
		}
		return arg1;
	}
}
