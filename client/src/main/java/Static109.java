import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static109 {

	@OriginalMember(owner = "client!ig", name = "b", descriptor = "I")
	public static int anInt2882;

	@OriginalMember(owner = "client!ig", name = "d", descriptor = "I")
	public static int anInt2883;

	@OriginalMember(owner = "client!ig", name = "f", descriptor = "I")
	public static int anInt2884;

	@OriginalMember(owner = "client!ig", name = "i", descriptor = "I")
	public static int anInt2886;

	@OriginalMember(owner = "client!ig", name = "a", descriptor = "(BI)V")
	public static void method2275(@OriginalArg(1) int arg0) {
		if (arg0 == -1 || !IfType.open[arg0]) {
			return;
		}
		IfType.interfaces.method4490(arg0);
		if (IfType.list[arg0] == null) {
			return;
		}
		@Pc(27) boolean local27 = true;
		for (@Pc(29) int local29 = 0; local29 < IfType.list[arg0].length; local29++) {
			if (IfType.list[arg0][local29] != null) {
				if (IfType.list[arg0][local29].type == 2) {
					local27 = false;
				} else {
					IfType.list[arg0][local29] = null;
				}
			}
		}
		if (local27) {
			IfType.list[arg0] = null;
		}
		IfType.open[arg0] = false;
	}
}
