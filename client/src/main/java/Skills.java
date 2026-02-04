import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Skills {
    @OriginalMember(owner = "client!h", name = "S", descriptor = "[I")
    public static final int[] skillxp = new int[99];
    @OriginalMember(owner = "client!oj", name = "z", descriptor = "[Z")
    public static final boolean[] used = new boolean[] { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false };

    static {
        @Pc(4) int local4 = 0;
        for (@Pc(6) int local6 = 0; local6 < 99; local6++) {
            @Pc(13) int local13 = local6 + 1;
            @Pc(26) int local26 = (int) (Math.pow(2.0D, (double) local13 / 7.0D) * 300.0D + (double) local13);
            local4 += local26;
            skillxp[local6] = local4 / 4;
        }
    }
}
