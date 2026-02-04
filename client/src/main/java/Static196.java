import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static196 {

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "[Lclient!ba;")
	public static GWCWorld[] aClass10_Sub1Array2;

	@OriginalMember(owner = "client!pl", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_863 = JagString.wrap(":tradereq:");

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "(I)Lclient!mm;")
	public static Pix32 method3537() {
		@Pc(13) int local13 = Static254.anIntArray488[0] * Static26.anIntArray66[0];
		@Pc(17) byte[] local17 = Static7.aByteArrayArray5[0];
		@Pc(20) int[] local20 = new int[local13];
		for (@Pc(22) int local22 = 0; local22 < local13; local22++) {
			local20[local22] = Static259.anIntArray513[local17[local22] & 0xFF];
		}
		@Pc(57) Pix32 local57 = new Pix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], local20);
		Static75.method1631();
		return local57;
	}
}
