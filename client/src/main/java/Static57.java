import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static57 {

	@OriginalMember(owner = "client!eg", name = "a", descriptor = "I")
	public static int anInt1744;

	@OriginalMember(owner = "client!eg", name = "t", descriptor = "I")
	public static int anInt1757;

	@OriginalMember(owner = "client!eg", name = "d", descriptor = "I")
	public static int anInt1747 = 0;

	@OriginalMember(owner = "client!eg", name = "B", descriptor = "Lclient!na;")
	private static final JagString aClass100_393 = JagString.wrap("slide:");

	@OriginalMember(owner = "client!eg", name = "u", descriptor = "Lclient!na;")
	public static final JagString aClass100_389 = aClass100_393;

	@OriginalMember(owner = "client!eg", name = "v", descriptor = "I")
	public static int anInt1758 = 0;

	@OriginalMember(owner = "client!eg", name = "y", descriptor = "Lclient!na;")
	public static final JagString aClass100_390 = JagString.wrap("Jeter");

	@OriginalMember(owner = "client!eg", name = "z", descriptor = "Lclient!na;")
	public static JagString aClass100_391 = aClass100_393;

	@OriginalMember(owner = "client!eg", name = "A", descriptor = "Lclient!na;")
	public static final JagString aClass100_392 = JagString.wrap("Sprites geladen)3");

	@OriginalMember(owner = "client!eg", name = "a", descriptor = "(IIIIIIII)V")
	public static void method1320(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (IfType.openInterface(arg4)) {
			Static36.method946(IfType.list[arg4], -1, arg5, arg1, arg3, arg6, arg0, arg2);
		}
	}
}
