import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!ad")
public final class MonotonicTime {

	@OriginalMember(owner = "signlink!ad", name = "a", descriptor = "J")
	private static long previous;

	@OriginalMember(owner = "signlink!ad", name = "b", descriptor = "J")
	private static long leapMillis;

	@OriginalMember(owner = "signlink!ad", name = "a", descriptor = "(B)J")
	public static synchronized long currentTime() {
		@Pc(1) long local1 = System.currentTimeMillis();
		if (leapMillis > local1) {
			previous += leapMillis - local1;
		}
		leapMillis = local1;
		return previous + local1;
	}
}
