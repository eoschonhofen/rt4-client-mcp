import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static271 {

	@OriginalMember(owner = "client!wc", name = "h", descriptor = "[[I")
	public static final int[][] anIntArrayArray46 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };

	@OriginalMember(owner = "client!wc", name = "i", descriptor = "[B")
	public static final byte[] aByteArray79 = new byte[520];

	@OriginalMember(owner = "client!wc", name = "a", descriptor = "(Lclient!e;I)V")
	public static void method4597(@OriginalArg(0) ClientPlayer arg0) {
		@Pc(10) BgSound local10 = (BgSound) Static93.aClass133_7.find(arg0.aClass100_364.method3158());
		if (local10 == null) {
			return;
		}
		if (local10.aClass3_Sub3_Sub1_1 != null) {
			Client.soundMixer.method1347(local10.aClass3_Sub3_Sub1_1);
			local10.aClass3_Sub3_Sub1_1 = null;
		}
		local10.unlink();
	}

	@OriginalMember(owner = "client!wc", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method4598(@OriginalArg(0) Packet arg0) {
		if (Static121.aClass38_3 != null) {
			try {
				Static121.aClass38_3.method1459(0L);
				Static121.aClass38_3.method1458(arg0.data, arg0.pos, 24);
			} catch (@Pc(16) Exception local16) {
			}
		}
		arg0.pos += 24;
	}

	@OriginalMember(owner = "client!wc", name = "a", descriptor = "(ZI)V")
	public static void method4600(@OriginalArg(1) int arg0) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(4, arg0);
		local8.method1007();
	}

}
