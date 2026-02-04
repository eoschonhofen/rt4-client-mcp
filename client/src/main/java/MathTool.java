import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MathTool {
    @OriginalMember(owner = "client!qi", name = "b", descriptor = "(II)I")
    public static int bitsRequired(@OriginalArg(0) int arg0) {
        @Pc(5) int local5 = 0;
        if (arg0 < 0 || arg0 >= 65536) {
            local5 += 16;
            arg0 >>>= 0x10;
        }
        if (arg0 >= 256) {
            local5 += 8;
            arg0 >>>= 0x8;
        }
        if (arg0 >= 16) {
            local5 += 4;
            arg0 >>>= 0x4;
        }
        if (arg0 >= 4) {
            arg0 >>>= 0x2;
            local5 += 2;
        }
        if (arg0 >= 1) {
            arg0 >>>= 0x1;
            local5++;
        }
        return arg0 + local5;
    }
}
