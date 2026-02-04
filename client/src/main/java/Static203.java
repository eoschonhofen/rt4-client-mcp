import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static203 {

	@OriginalMember(owner = "client!qh", name = "e", descriptor = "[Lclient!ee;")
	public static WorldInfo[] aClass32Array1;

	@OriginalMember(owner = "client!qh", name = "c", descriptor = "Lclient!na;")
	public static final JagString aClass100_893 = JagString.wrap("Memory before cleanup=");

	@OriginalMember(owner = "client!qh", name = "i", descriptor = "Lclient!na;")
	public static final JagString aClass100_894 = JagString.wrap("Mem:");

	@OriginalMember(owner = "client!qh", name = "a", descriptor = "(Lsignlink!ll;B)V")
	public static void method3663(@OriginalArg(0) SignLink arg0) {
		@Pc(11) FileOnDisk local11 = null;
		try {
			@Pc(16) PrivilegedRequest local16 = arg0.method5112("runescape");
			while (local16.status == 0) {
				ThreadSleep.sleepPrecise(1L);
			}
			if (local16.status == 1) {
				local11 = (FileOnDisk) local16.result;
				@Pc(39) Packet local39 = Static48.method1196();
				local11.method5134(local39.data, local39.pos, 0);
			}
		} catch (@Pc(49) Exception local49) {
		}
		try {
			if (local11 != null) {
				local11.method5136();
			}
		} catch (@Pc(56) Exception local56) {
		}
	}
}
