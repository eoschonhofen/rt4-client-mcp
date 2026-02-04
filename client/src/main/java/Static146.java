import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static146 {

	@OriginalMember(owner = "client!lg", name = "d", descriptor = "F")
	public static float aFloat15;

	@OriginalMember(owner = "client!lg", name = "c", descriptor = "[Lclient!tk;")
	public static final SeqType[] aClass144Array1 = new SeqType[14];

	@OriginalMember(owner = "client!lg", name = "a", descriptor = "(ZLclient!wa;Lclient!na;)I")
	public static int method2748(@OriginalArg(1) Packet arg0, @OriginalArg(2) JagString arg1) {
		@Pc(6) int local6 = arg0.pos;
		@Pc(14) byte[] local14 = arg1.builderToString();
		arg0.method2194(local14.length);
		arg0.pos += Static62.aClass44_1.method1550(local14.length, arg0.data, local14, 0, arg0.pos);
		return arg0.pos - local6;
	}

	@OriginalMember(owner = "client!lg", name = "a", descriptor = "(Lclient!ve;BII)[Lclient!ok;")
	public static Pix8[] method2749(@OriginalArg(0) Js5 arg0, @OriginalArg(3) int arg1) {
		return Static234.method4016(arg0, 0, arg1) ? PixLoader.method4331() : null;
	}

}
