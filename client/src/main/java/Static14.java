import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static14 {

	@OriginalMember(owner = "client!bd", name = "b", descriptor = "Lclient!na;")
	public static final JagString aClass100_80 = JagString.wrap("(U(Y");

	@OriginalMember(owner = "client!bd", name = "i", descriptor = "I")
	public static int state = 0;

	@OriginalMember(owner = "client!bd", name = "a", descriptor = "(Z)[Lclient!mm;")
	public static Pix32[] method474() {
		@Pc(4) Pix32[] local4 = new Pix32[Static165.anInt4038];
		for (@Pc(12) int local12 = 0; local12 < Static165.anInt4038; local12++) {
			@Pc(27) int local27 = Static26.anIntArray66[local12] * Static254.anIntArray488[local12];
			@Pc(31) byte[] local31 = Static7.aByteArrayArray5[local12];
			@Pc(34) int[] local34 = new int[local27];
			for (@Pc(36) int local36 = 0; local36 < local27; local36++) {
				local34[local36] = Static259.anIntArray513[local31[local36] & 0xFF];
			}
			local4[local12] = new Pix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local12], Static269.anIntArray252[local12], Static254.anIntArray488[local12], Static26.anIntArray66[local12], local34);
		}
		Static75.method1631();
		return local4;
	}

}
