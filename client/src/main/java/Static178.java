import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static178 {

	@OriginalMember(owner = "client!od", name = "c", descriptor = "Z")
	public static boolean highDetailLighting = true;

	@OriginalMember(owner = "client!od", name = "g", descriptor = "S")
	public static short aShort25 = 256;

	@OriginalMember(owner = "client!od", name = "i", descriptor = "I")
	public static int anInt4247 = 0;

	@OriginalMember(owner = "client!od", name = "a", descriptor = "(ILclient!na;)V")
	public static void method3318(@OriginalArg(1) JagString arg0) {
		if (Static199.aClass3_Sub22Array1 == null) {
			return;
		}
		@Pc(22) long local22 = arg0.method3158();
		@Pc(24) int local24 = 0;
		if (local22 == 0L) {
			return;
		}
		while (Static199.aClass3_Sub22Array1.length > local24 && Static199.aClass3_Sub22Array1[local24].key != local22) {
			local24++;
		}
		if (local24 < Static199.aClass3_Sub22Array1.length && Static199.aClass3_Sub22Array1[local24] != null) {
			Static6.aClass3_Sub15_Sub1_1.p1Enc(162);
			Static6.aClass3_Sub15_Sub1_1.p8(Static199.aClass3_Sub22Array1[local24].key);
		}
	}

}
