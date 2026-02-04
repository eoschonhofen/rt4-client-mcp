import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static247 {

	@OriginalMember(owner = "client!ub", name = "h", descriptor = "[Lclient!pe;")
	public static Occlude[] aClass120Array2;

	@OriginalMember(owner = "client!ub", name = "m", descriptor = "I")
	public static int anInt5405;

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(IIIIIII)V")
	public static void method4244(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		if (Static172.anInt4164 <= arg1 && Static224.anInt5063 >= arg3 && Static267.anInt5773 <= arg2 && arg4 <= Static106.anInt2869) {
			if (arg5 == 1) {
				Static134.method2622(arg0, arg3, arg2, arg4, arg1);
			} else {
				Static183.method3334(arg3, arg2, arg0, arg4, arg5, arg1);
			}
		} else if (arg5 == 1) {
			Static173.method3246(arg0, arg1, arg4, arg3, arg2);
		} else {
			Static10.method352(arg4, arg5, arg3, arg1, arg0, arg2);
		}
	}

	@OriginalMember(owner = "client!ub", name = "a", descriptor = "(IB)I")
	public static int method4247(@OriginalArg(0) int arg0) {
		return arg0 & 0xFF;
	}

}
