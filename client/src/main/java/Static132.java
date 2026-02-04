import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static132 {

	@OriginalMember(owner = "client!ke", name = "Y", descriptor = "[I")
	public static final int[] anIntArray309 = new int[] { 1, 4 };

	@OriginalMember(owner = "client!ke", name = "c", descriptor = "(III)V")
	public static void method2606(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(1, arg0);
		local8.method1017();
		local8.anInt1271 = arg1;
	}

	@OriginalMember(owner = "client!ke", name = "a", descriptor = "(IIIBI)V")
	public static void method2607(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(4, arg2);
		local8.method1017();
		local8.anInt1270 = arg3;
		local8.anInt1269 = arg0;
		local8.anInt1271 = arg1;
	}

	@OriginalMember(owner = "client!ke", name = "f", descriptor = "(B)V")
	public static void method2608() {
		@Pc(7) int local7 = 0;
		for (@Pc(23) int local23 = 0; local23 < 104; local23++) {
			for (@Pc(30) int local30 = 0; local30 < 104; local30++) {
				if (Client.method4348(true, local23, local30, World.activeTiles, local7)) {
					local7++;
				}
				if (local7 >= 512) {
					return;
				}
			}
		}
	}

}
