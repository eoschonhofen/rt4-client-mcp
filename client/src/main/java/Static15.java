import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static15 {

	@OriginalMember(owner = "client!be", name = "Vb", descriptor = "F")
	public static float aFloat4;

	@OriginalMember(owner = "client!be", name = "Kb", descriptor = "Z")
	public static boolean aBoolean33 = true;

	@OriginalMember(owner = "client!be", name = "ac", descriptor = "Lclient!na;")
	public static JagString aClass100_87 = null;

	@OriginalMember(owner = "client!be", name = "kc", descriptor = "J")
	public static long aLong18 = 0L;

	@OriginalMember(owner = "client!be", name = "Ec", descriptor = "I")
	public static int anInt506 = -1;

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(Z)Lclient!na;")
	public static JagString method479() {
		@Pc(8) JagString local8 = Static93.aClass100_518;
		@Pc(10) JagString local10 = Static186.AUTO_EMPTY;
		if (Client.modewhere != 0) {
			local8 = Static50.aClass100_365;
		}
		if (Static47.aClass100_991 != null) {
			local10 = JagString.join(new JagString[] { Static150.aClass100_687, Static47.aClass100_991 });
		}
		return JagString.join(new JagString[] { Static61.aClass100_424, local8, Static80.aClass100_886, JagString.parseInt(Client.lang), Static257.aClass100_98, JagString.parseInt(Client.affid), local10, Static41.aClass100_268 });
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(II)I")
	public static int method492(@OriginalArg(1) int arg0) {
		return arg0 == 16711935 ? -1 : Static105.method2253(arg0);
	}
}
