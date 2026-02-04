import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static91 {

	@OriginalMember(owner = "client!hc", name = "O", descriptor = "[Lclient!pe;")
	public static Occlude[] aClass120Array1;

	@OriginalMember(owner = "client!hc", name = "P", descriptor = "I")
	public static int anInt2428;

	@OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!km;Z)V")
	public static void method1877(@OriginalArg(0) ClientNPC arg0) {
		for (@Pc(13) BgSound local13 = (BgSound) Static152.aClass69_87.head(); local13 != null; local13 = (BgSound) Static152.aClass69_87.next()) {
			if (arg0 == local13.aClass8_Sub4_Sub2_1) {
				if (local13.aClass3_Sub3_Sub1_1 != null) {
					Client.soundMixer.method1347(local13.aClass3_Sub3_Sub1_1);
					local13.aClass3_Sub3_Sub1_1 = null;
				}
				local13.unlink();
				return;
			}
		}
	}

}
