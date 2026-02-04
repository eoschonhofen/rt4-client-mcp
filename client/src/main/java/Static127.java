import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static127 {

	@OriginalMember(owner = "client!wh", name = "o", descriptor = "Lclient!na;")
	public static final JagString aClass100_1096 = JagString.wrap("rect_debug=");

	@OriginalMember(owner = "client!k", name = "l", descriptor = "[I")
	public static int[] anIntArray292;

	@OriginalMember(owner = "client!k", name = "c", descriptor = "Z")
	public static boolean aBoolean159 = false;

	@OriginalMember(owner = "client!k", name = "i", descriptor = "I")
	public static int anInt3125 = 0;

	@OriginalMember(owner = "client!k", name = "m", descriptor = "Z")
	public static boolean aBoolean160 = false;

	@OriginalMember(owner = "client!k", name = "t", descriptor = "I")
	public static int anInt3132 = 0;

	@OriginalMember(owner = "client!k", name = "a", descriptor = "(IIBLclient!ve;Lclient!ve;)Lclient!rk;")
	public static PixFontGeneric method2462(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1, @OriginalArg(4) Js5 arg2) {
		return Static234.method4016(arg1, 0, arg0) ? Static29.method799(arg2.getFile(arg0, 0)) : null;
	}

	@OriginalMember(owner = "client!k", name = "a", descriptor = "(B)Lclient!da;")
	public static DelayedStateChange method2464() {
		@Pc(10) DelayedStateChange local10 = (DelayedStateChange) DelayedStateChange.aClass16_9.method795();
		if (local10 != null) {
			local10.unlink();
			local10.unlink2();
			return local10;
		}
		do {
			local10 = (DelayedStateChange) DelayedStateChange.aClass16_7.method795();
			if (local10 == null) {
				return null;
			}
			if (local10.method1009() > MonotonicTime.currentTime()) {
				return null;
			}
			local10.unlink();
			local10.unlink2();
		} while ((Long.MIN_VALUE & local10.key2) == 0L);
		return local10;
	}

}
