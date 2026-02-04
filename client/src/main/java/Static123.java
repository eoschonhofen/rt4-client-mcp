import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static123 {

	@OriginalMember(owner = "client!jj", name = "d", descriptor = "Lclient!na;")
	public static final JagString aClass100_592 = JagString.wrap("headicons_pk");

	@OriginalMember(owner = "client!jj", name = "e", descriptor = "I")
	public static int anInt3058 = 0;

	@OriginalMember(owner = "client!jj", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_593 = JagString.wrap(" (X");

	@OriginalMember(owner = "client!jj", name = "m", descriptor = "Lclient!na;")
	public static final JagString aClass100_594 = JagString.wrap("<)4col>");

	@OriginalMember(owner = "client!jj", name = "a", descriptor = "(Z)V")
	public static void method2418() {
		if (!Client.advertsuppressed && Client.modewhere != 2) {
			try {
				Static206.aClass100_900.method3157(Static215.client);
			} catch (@Pc(26) Throwable local26) {
			}
		}
	}

	@OriginalMember(owner = "client!jj", name = "a", descriptor = "(B)[F")
	public static float[] method2422() {
		@Pc(3) float local3 = Static161.method3068() + Static161.method3059();
		@Pc(9) int local9 = Static161.method3064();
		@Pc(18) float local18 = (float) (local9 >> 16 & 0xFF) / 255.0F;
		Static251.aFloatArray28[3] = 1.0F;
		@Pc(37) float local37 = (float) (local9 >> 8 & 0xFF) / 255.0F;
		@Pc(39) float local39 = 0.58823526F;
		@Pc(46) float local46 = (float) (local9 & 0xFF) / 255.0F;
		Static251.aFloatArray28[2] = Static257.aFloatArray2[2] * local46 * local39 * local3;
		Static251.aFloatArray28[0] = Static257.aFloatArray2[0] * local18 * local39 * local3;
		Static251.aFloatArray28[1] = local3 * local39 * local37 * Static257.aFloatArray2[1];
		return Static251.aFloatArray28;
	}

}
