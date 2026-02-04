import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static21 {

	@OriginalMember(owner = "client!bk", name = "R", descriptor = "Lclient!na;")
	public static final JagString aClass100_126 = Text.aClass100_121;

	@OriginalMember(owner = "client!bk", name = "a", descriptor = "(BI)V")
	public static void method619(@OriginalArg(1) int arg0) {
		Static257.aFloatArray2[0] = (float) (arg0 >> 16 & 0xFF) / 255.0F;
		Static257.aFloatArray2[1] = (float) (arg0 >> 8 & 0xFF) / 255.0F;
		Static257.aFloatArray2[2] = (float) (arg0 & 0xFF) / 255.0F;
		Static128.method2481(3);
		Static128.method2481(4);
	}

}
