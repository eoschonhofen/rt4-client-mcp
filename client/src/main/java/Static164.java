import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static164 {

	@OriginalMember(owner = "client!na", name = "w", descriptor = "Z")
	public static boolean aBoolean192;

	@OriginalMember(owner = "client!na", name = "W", descriptor = "Z")
	public static boolean aBoolean194;

	@OriginalMember(owner = "client!na", name = "h", descriptor = "Z")
	public static boolean aBoolean191 = false;

	@OriginalMember(owner = "client!na", name = "o", descriptor = "I")
	public static int anInt3988 = 0;

	@OriginalMember(owner = "client!na", name = "cb", descriptor = "Lclient!na;")
	public static final JagString aClass100_770 = JagString.wrap(":allyreq:");

	@OriginalMember(owner = "client!na", name = "a", descriptor = "(Lclient!ba;Lclient!ba;IIIZZ)I")
	public static int method3115(@OriginalArg(0) GWCWorld arg0, @OriginalArg(1) GWCWorld arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) boolean arg5) {
		@Pc(8) int local8 = Static270.method4595(arg1, arg3, arg0, arg5);
		if (local8 != 0) {
			return arg5 ? -local8 : local8;
		} else if (arg2 == -1) {
			return 0;
		} else {
			@Pc(42) int local42 = Static270.method4595(arg1, arg2, arg0, arg4);
			return arg4 ? -local42 : local42;
		}
	}

	@OriginalMember(owner = "client!na", name = "a", descriptor = "(Lclient!ve;IZ)Lclient!mm;")
	public static Pix32 method3117(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1) {
		return PixLoader.method4346(arg0, arg1) ? Static196.method3537() : null;
	}

	@OriginalMember(owner = "client!na", name = "a", descriptor = "(IZILclient!ve;)Lclient!ek;")
	public static SoftwarePix8 method3119(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return Static234.method4016(arg1, 0, arg0) ? Static134.method2619() : null;
	}

}
