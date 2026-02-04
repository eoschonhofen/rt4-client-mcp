import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static167 {

	@OriginalMember(owner = "client!nd", name = "y", descriptor = "Lclient!mm;")
	public static Pix32 aClass3_Sub2_Sub1_Sub1_3;

	@OriginalMember(owner = "client!nd", name = "r", descriptor = "Lclient!na;")
	public static final JagString aClass100_781 = JagString.wrap("zap");

	@OriginalMember(owner = "client!nd", name = "x", descriptor = "Lclient!na;")
	public static final JagString aClass100_783 = JagString.wrap(")4p=");

	@OriginalMember(owner = "client!nd", name = "a", descriptor = "(ZLclient!qb;)V")
	public static void method3170(@OriginalArg(1) PcmStream arg0) {
		if (arg0.aClass3_Sub16_5 != null) {
			arg0.aClass3_Sub16_5.anInt3313 = 0;
		}
		arg0.aBoolean292 = false;
		for (@Pc(14) PcmStream local14 = arg0.method4406(); local14 != null; local14 = arg0.method4409()) {
			method3170(local14);
		}
	}

}
