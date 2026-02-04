import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static13 {

	@OriginalMember(owner = "client!bc", name = "X", descriptor = "I")
	public static int anInt436;

	@OriginalMember(owner = "client!bc", name = "I", descriptor = "Z")
	public static boolean aBoolean16 = false;

	@OriginalMember(owner = "client!bc", name = "N", descriptor = "Lclient!lb;")
	public static final MonochromeImageCacheEntry aClass3_Sub23_1 = new MonochromeImageCacheEntry(0, 0);

	@OriginalMember(owner = "client!bc", name = "f", descriptor = "(B)Lclient!na;")
	public static JagString method471() {
		@Pc(32) JagString local32;
		if (Static260.anInt5014 == 1 && Client.menuNumEntries < 2) {
			local32 = JagString.join(new JagString[] { Text.aClass100_937, Text.aClass100_901, Static34.aClass100_203, Static225.aClass100_961 });
		} else if (Client.targetMode && Client.menuNumEntries < 2) {
			local32 = JagString.join(new JagString[] { Static102.aClass100_545, Text.aClass100_901, Static78.aClass100_466, Static225.aClass100_961 });
		} else if (Client.aBoolean199 && ClientKeyboardListener.keyHeld[81] && Client.menuNumEntries > 2) {
			local32 = Static269.method2228(Client.menuNumEntries - 2);
		} else {
			local32 = Static269.method2228(Client.menuNumEntries - 1);
		}
		if (Client.menuNumEntries > 2) {
			local32 = JagString.join(new JagString[] { local32, Static1.aClass100_2, JagString.parseInt(Client.menuNumEntries - 2), Text.aClass100_1054 });
		}
		return local32;
	}

}
