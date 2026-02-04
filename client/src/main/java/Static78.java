import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static78 {

	@OriginalMember(owner = "client!gd", name = "l", descriptor = "I")
	public static int anInt2147;

	@OriginalMember(owner = "client!gd", name = "n", descriptor = "Lclient!qf;")
	public static AbstractPix32 aClass3_Sub2_Sub1_3;

	@OriginalMember(owner = "client!gd", name = "b", descriptor = "Lclient!na;")
	public static final JagString aClass100_464 = JagString.wrap("null");

	@OriginalMember(owner = "client!gd", name = "c", descriptor = "Lclient!na;")
	public static final JagString aClass100_465 = JagString.wrap(")0");

	@OriginalMember(owner = "client!gd", name = "h", descriptor = "[I")
	public static final int[] anIntArray204 = new int[] { 16, 32, 64, 128 };

	@OriginalMember(owner = "client!gd", name = "i", descriptor = "Lclient!na;")
	public static JagString aClass100_466 = null;

	@OriginalMember(owner = "client!gd", name = "a", descriptor = "(IIBI)I")
	public static int method1690(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		return arg2 > arg1 ? arg2 : arg1 > arg0 ? arg0 : arg1;
	}

	@OriginalMember(owner = "client!gd", name = "a", descriptor = "(ILclient!ve;II)Lclient!mm;")
	public static Pix32 depack(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1, @OriginalArg(3) int arg2) {
		return Static234.method4016(arg1, arg0, arg2) ? Static196.method3537() : null;
	}

}
