import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static264 {

	@OriginalMember(owner = "client!vh", name = "e", descriptor = "I")
	public static int anInt4623;

	@OriginalMember(owner = "client!vh", name = "c", descriptor = "Lclient!na;")
	public static final JagString aClass100_875 = JagString.wrap(":");

	@OriginalMember(owner = "client!vh", name = "p", descriptor = "[I")
	public static final int[] anIntArray412 = new int[] { 1, 0, 0, 0, 1, 0, 2, 1, 1, 1, 0, 2, 0, 0, 1, 0 };

	@OriginalMember(owner = "client!vh", name = "a", descriptor = "(Lclient!th;III)V")
	public static void method3574(@OriginalArg(0) ModelSource arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(12) Square local12;
		if (arg2 < Static152.anInt3594) {
			local12 = World.levelTiles[arg1][arg2 + 1][arg3];
			if (local12 != null && local12.groundDecor != null && local12.groundDecor.aClass8_1.method4543()) {
				arg0.method4544(local12.groundDecor.aClass8_1, 128, 0, 0, true);
			}
		}
		if (arg3 < Static152.anInt3594) {
			local12 = World.levelTiles[arg1][arg2][arg3 + 1];
			if (local12 != null && local12.groundDecor != null && local12.groundDecor.aClass8_1.method4543()) {
				arg0.method4544(local12.groundDecor.aClass8_1, 0, 0, 128, true);
			}
		}
		if (arg2 < Static152.anInt3594 && arg3 < Static99.anInt2550) {
			local12 = World.levelTiles[arg1][arg2 + 1][arg3 + 1];
			if (local12 != null && local12.groundDecor != null && local12.groundDecor.aClass8_1.method4543()) {
				arg0.method4544(local12.groundDecor.aClass8_1, 128, 0, 128, true);
			}
		}
		if (arg2 < Static152.anInt3594 && arg3 > 0) {
			local12 = World.levelTiles[arg1][arg2 + 1][arg3 - 1];
			if (local12 != null && local12.groundDecor != null && local12.groundDecor.aClass8_1.method4543()) {
				arg0.method4544(local12.groundDecor.aClass8_1, 128, 0, -128, true);
			}
		}
	}
}
