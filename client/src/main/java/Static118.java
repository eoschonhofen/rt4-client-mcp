import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static118 {

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(B)I")
	public static int method2352() {
		Static232.anInt5212 = 0;
		return WorldMap.method2385();
	}

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(IB)V")
	public static void method2353(@OriginalArg(0) int arg0) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(12, arg0);
		local12.method1007();
	}

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(II)I")
	public static int method2356(@OriginalArg(1) int arg0) {
		return arg0 & 0x7F;
	}
}
