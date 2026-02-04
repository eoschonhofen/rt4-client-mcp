import java.util.Random;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static171 {

	@OriginalMember(owner = "client!ni", name = "h", descriptor = "I")
	public static int anInt4153;

	@OriginalMember(owner = "client!ni", name = "q", descriptor = "I")
	public static int anInt4157;

	@OriginalMember(owner = "client!ni", name = "n", descriptor = "Lclient!sm;")
	public static Class139 aClass139_1 = new Class139();

	@OriginalMember(owner = "client!ni", name = "a", descriptor = "(BILjava/util/Random;)I")
	public static int method3219(@OriginalArg(1) int arg0, @OriginalArg(2) Random arg1) {
		if (arg0 <= 0) {
			throw new IllegalArgumentException();
		} else if (Static209.method3702(arg0)) {
			return (int) (((long) arg1.nextInt() & 0xFFFFFFFFL) * (long) arg0 >> 32);
		} else {
			@Pc(38) int local38 = Integer.MIN_VALUE - (int) (4294967296L % (long) arg0);
			@Pc(41) int local41;
			do {
				local41 = arg1.nextInt();
			} while (local38 <= local41);
			return Static39.method990(local41, arg0);
		}
	}

}
