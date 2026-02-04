import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static154 {

	@OriginalMember(owner = "client!md", name = "S", descriptor = "I")
	public static int anInt3709;

	@OriginalMember(owner = "client!md", name = "Q", descriptor = "Lclient!na;")
	public static final JagString aClass100_735 = JagString.wrap("::mm");

	@OriginalMember(owner = "client!md", name = "T", descriptor = "Lclient!na;")
	public static final JagString aClass100_736 = JagString.wrap("Shift)2click disabled)3");

	@OriginalMember(owner = "client!md", name = "V", descriptor = "[[S")
	public static final short[][] recol1s = new short[][] { { 6798, 107, 10283, 16, 4797, 7744, 5799, 4634, -31839, 22433, 2983, -11343, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010 }, { 8741, 12, -1506, -22374, 7735, 8404, 1701, -27106, 24094, 10153, -8915, 4783, 1341, 16578, -30533, 25239, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010 }, { 25238, 8742, 12, -1506, -22374, 7735, 8404, 1701, -27106, 24094, 10153, -8915, 4783, 1341, 16578, -30533, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010 }, { 4626, 11146, 6439, 12, 4758, 10270 }, { 4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574 } };

	@OriginalMember(owner = "client!md", name = "W", descriptor = "I")
	public static int anInt3711 = -1;

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!be;I)Z")
	public static boolean method2926(@OriginalArg(0) IfType arg0) {
		if (arg0.scriptOperand == null) {
			return false;
		}
		for (@Pc(14) int local14 = 0; local14 < arg0.scriptOperand.length; local14++) {
			@Pc(34) int local34 = Static273.method3212(local14, arg0);
			@Pc(39) int local39 = arg0.scriptComparator[local14];
			if (arg0.scriptOperand[local14] == 2) {
				if (local39 <= local34) {
					return false;
				}
			} else if (arg0.scriptOperand[local14] == 3) {
				if (local34 <= local39) {
					return false;
				}
			} else if (arg0.scriptOperand[local14] == 4) {
				if (local34 == local39) {
					return false;
				}
			} else if (local39 != local34) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(JB)Lclient!na;")
	public static JagString method2929(@OriginalArg(0) long arg0) {
		return Static59.method1376(arg0);
	}
}
