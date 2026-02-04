import java.lang.reflect.Method;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static224 {

	@OriginalMember(owner = "client!sd", name = "I", descriptor = "I")
	public static int anInt5057;

	@OriginalMember(owner = "client!sd", name = "R", descriptor = "I")
	public static int anInt5062;

	@OriginalMember(owner = "client!sd", name = "S", descriptor = "I")
	public static int anInt5063 = 100;

	@OriginalMember(owner = "client!sd", name = "e", descriptor = "(I)V")
	public static void method3888() {
		try {
			@Pc(12) Method local12 = Runtime.class.getMethod("maxMemory");
			if (local12 != null) {
				try {
					@Pc(17) Runtime local17 = Runtime.getRuntime();
					@Pc(24) Long local24 = (Long) local12.invoke(local17, (Object[]) null);
					Static238.anInt5316 = (int) (local24 / 1048576L) + 1;
				} catch (@Pc(34) Throwable local34) {
				}
			}
		} catch (@Pc(36) Exception local36) {
		}
	}
}
