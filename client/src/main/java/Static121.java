import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static121 {

	@OriginalMember(owner = "client!jg", name = "a", descriptor = "I")
	public static int anInt3038;

	@OriginalMember(owner = "client!jg", name = "b", descriptor = "I")
	public static int anInt3039;

	@OriginalMember(owner = "client!jg", name = "c", descriptor = "Lclient!en;")
	public static BufferedRandomAccessFile aClass38_3;

	@OriginalMember(owner = "client!jg", name = "g", descriptor = "I")
	public static int anInt3041;

	@OriginalMember(owner = "client!jg", name = "e", descriptor = "Z")
	public static boolean aBoolean154 = false;

	@OriginalMember(owner = "client!jg", name = "a", descriptor = "(I)[Lclient!ek;")
	public static SoftwarePix8[] method2406() {
		@Pc(2) SoftwarePix8[] local2 = new SoftwarePix8[Static165.anInt4038];
		for (@Pc(8) int local8 = 0; local8 < Static165.anInt4038; local8++) {
			local2[local8] = new SoftwarePix8(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local8], Static269.anIntArray252[local8], Static254.anIntArray488[local8], Static26.anIntArray66[local8], Static7.aByteArrayArray5[local8], Static259.anIntArray513);
		}
		Static75.method1631();
		return local2;
	}

}
