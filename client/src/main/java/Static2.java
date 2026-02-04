import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static2 {

	@OriginalMember(owner = "client!aa", name = "t", descriptor = "[Lclient!pc;")
	public static MaterialRenderer[] anInterface4Array1;

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "I")
	public static int anInt7 = 0;

	@OriginalMember(owner = "client!aa", name = "d", descriptor = "Lclient!na;")
	public static final JagString aClass100_3 = Text.aClass100_5;

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(SI)Z")
	public static boolean method5(@OriginalArg(0) short arg0) {
		if (arg0 == 47 || arg0 == 5 || arg0 == 43 || arg0 == 35 || arg0 == 58 || arg0 == 22 || arg0 == 40 || arg0 == 3) {
			return true;
		} else if (arg0 == 9 || arg0 == 12 || arg0 == 1006 || arg0 == 1003) {
			return true;
		} else if (arg0 == 25 || arg0 == 23 || arg0 == 48 || arg0 == 7 || arg0 == 13) {
			return true;
		} else {
			return arg0 == 8 || arg0 == 32 || arg0 == 28 || arg0 == 59 || arg0 == 51 || arg0 == 41;
		}
	}

}
