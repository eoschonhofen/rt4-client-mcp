import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static40 {

	@OriginalMember(owner = "client!da", name = "ab", descriptor = "I")
	public static int anInt1275;

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(ILclient!ve;Z)Lclient!ok;")
	public static Pix8 method1010(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1) {
		return PixLoader.method4346(arg1, arg0) ? PixLoader.method4614() : null;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;")
	public static String replace(@OriginalArg(0) String arg0, @OriginalArg(1) String arg1, @OriginalArg(3) String arg2) {
		for (@Pc(5) int local5 = arg2.indexOf(arg0); local5 != -1; local5 = arg2.indexOf(arg0, local5 + arg1.length())) {
			arg2 = arg2.substring(0, local5) + arg1 + arg2.substring(arg0.length() + local5);
		}
		return arg2;
	}

}
