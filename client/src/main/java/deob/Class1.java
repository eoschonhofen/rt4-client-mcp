package deob;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!a")
public final class Class1 {

	@OriginalMember(owner = "client!a", name = "b", descriptor = "[I")
	public static final int[] anIntArray1 = new int[4096];

	static {
		for (@Pc(4) int local4 = 0; local4 < 4096; local4++) {
			anIntArray1[local4] = method3211(local4);
		}
	}

    @OriginalMember(owner = "client!we", name = "a", descriptor = "(BI)I")
    public static int method3211(@OriginalArg(1) int arg0) {
        @Pc(13) int local13 = arg0 * (arg0 * arg0 >> 12) >> 12;
        @Pc(26) int local26 = arg0 * 6 - 61440;
        @Pc(34) int local34 = (arg0 * local26 >> 12) + 40960;
        return local13 * local34 >> 12;
    }
}
