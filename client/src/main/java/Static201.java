import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static201 {

	@OriginalMember(owner = "client!qf", name = "S", descriptor = "I")
	public static int anInt1864;

	@OriginalMember(owner = "client!qf", name = "N", descriptor = "Lclient!na;")
	public static final JagString aClass100_406 = JagString.wrap("::cardmem");

	@OriginalMember(owner = "client!qf", name = "Q", descriptor = "Lclient!na;")
	public static final JagString aClass100_407 = JagString.wrap(" )2> <col=ffff00>");

	@OriginalMember(owner = "client!qf", name = "R", descriptor = "Lclient!na;")
	public static final JagString aClass100_408 = JagString.wrap(" )2> ");

	@OriginalMember(owner = "client!qf", name = "X", descriptor = "Lclient!be;")
	public static IfType aClass13_13 = null;

	@OriginalMember(owner = "client!qf", name = "a", descriptor = "(BII)Lclient!be;")
	public static IfType method1418(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(7) IfType local7 = IfType.get(arg0);
		if (arg1 == -1) {
			return local7;
		} else if (local7 == null || local7.subcomponents == null || local7.subcomponents.length <= arg1) {
			return null;
		} else {
			return local7.subcomponents[arg1];
		}
	}
}
