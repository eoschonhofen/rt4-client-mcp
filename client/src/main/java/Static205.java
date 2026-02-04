import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static205 {

	@OriginalMember(owner = "client!qj", name = "a", descriptor = "(Lclient!be;BI)Lclient!na;")
	public static JagString method3677(@OriginalArg(0) IfType arg0, @OriginalArg(2) int arg1) {
		if (!Client.method940(arg0).method503(arg1) && arg0.onop == null) {
			return null;
		} else if (arg0.aClass100Array18 == null || arg0.aClass100Array18.length <= arg1 || arg0.aClass100Array18[arg1] == null || arg0.aClass100Array18[arg1].method3144().length() == 0) {
			return Static121.aBoolean154 ? JagString.join(new JagString[] { Static207.aClass100_903, JagString.parseInt(arg1) }) : null;
		} else {
			return arg0.aClass100Array18[arg1];
		}
	}
}
