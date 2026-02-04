import java.awt.Frame;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static25 {

	@OriginalMember(owner = "client!c", name = "bb", descriptor = "[Lclient!ec;")
	public static Sprite[] aClass31Array2;

	@OriginalMember(owner = "client!c", name = "Y", descriptor = "Lclient!na;")
	public static final JagString aClass100_154 = JagString.wrap("Mem:");

	@OriginalMember(owner = "client!c", name = "a", descriptor = "(Ljava/awt/Frame;ZLsignlink!ll;)V")
	public static void method714(@OriginalArg(0) Frame arg0, @OriginalArg(2) SignLink arg1) {
		while (true) {
			@Pc(16) PrivilegedRequest local16 = arg1.method5115(arg0);
			while (local16.status == 0) {
				ThreadSleep.sleepPrecise(10L);
			}
			if (local16.status == 1) {
				arg0.setVisible(false);
				arg0.dispose();
				return;
			}
			ThreadSleep.sleepPrecise(100L);
		}
	}

}
