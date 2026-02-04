import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static219 {

	@OriginalMember(owner = "client!rl", name = "V", descriptor = "[Lclient!qf;")
	public static AbstractPix32[] aClass3_Sub2_Sub1Array9;

	@OriginalMember(owner = "client!rl", name = "S", descriptor = "Lclient!na;")
	public static final JagString aClass100_920 = JagString.wrap("hitmarks");

	@OriginalMember(owner = "client!rl", name = "T", descriptor = "Lclient!na;")
	private static final JagString aClass100_921 = JagString.wrap("Sat");

	@OriginalMember(owner = "client!rl", name = "U", descriptor = "Lclient!na;")
	private static final JagString aClass100_922 = JagString.wrap("Mon");

	@OriginalMember(owner = "client!rl", name = "W", descriptor = "Lclient!na;")
	private static final JagString aClass100_923 = JagString.wrap("Fri");

	@OriginalMember(owner = "client!rl", name = "db", descriptor = "Lclient!na;")
	private static final JagString aClass100_927 = JagString.wrap("Sun");

	@OriginalMember(owner = "client!rl", name = "cb", descriptor = "Lclient!na;")
	private static final JagString aClass100_926 = JagString.wrap("Tue");

	@OriginalMember(owner = "client!rl", name = "ab", descriptor = "Lclient!na;")
	private static final JagString aClass100_924 = JagString.wrap("Wed");

	@OriginalMember(owner = "client!rl", name = "bb", descriptor = "Lclient!na;")
	private static final JagString aClass100_925 = JagString.wrap("Thu");

	@OriginalMember(owner = "client!rl", name = "Y", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array149 = new JagString[] { aClass100_927, aClass100_922, aClass100_926, aClass100_924, aClass100_925, aClass100_923, aClass100_921 };

	@OriginalMember(owner = "client!rl", name = "Z", descriptor = "I")
	public static final int anInt4938 = 7759444;

	@OriginalMember(owner = "client!rl", name = "i", descriptor = "(I)V")
	public static void method3796() {
		for (@Pc(10) LocChange local10 = (LocChange) Client.aClass69_27.head(); local10 != null; local10 = (LocChange) Client.aClass69_27.next()) {
			if (local10.endTime == -1) {
				local10.startTime = 0;
				Client.locChangeSetOld(local10);
			} else {
				local10.unlink();
			}
		}
	}
}
