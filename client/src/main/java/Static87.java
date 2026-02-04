import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static87 {

	@OriginalMember(owner = "client!gn", name = "d", descriptor = "Z")
	public static boolean aBoolean130 = false;

	@OriginalMember(owner = "client!gn", name = "v", descriptor = "Lclient!na;")
	public static final JagString aClass100_494 = JagString.wrap("null");

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(Z)Z")
	public static boolean method1802() {
		if (Client.js) {
			try {
				Static9.aClass100_35.method3157(GameShell.signlink.applet);
				return true;
			} catch (@Pc(14) Throwable local14) {
			}
		}
		return false;
	}

}
