import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarCache {
    @OriginalMember(owner = "client!ah", name = "j", descriptor = "[I")
    public static final int[] var = new int[3500];
    @OriginalMember(owner = "client!ic", name = "e", descriptor = "[I")
    public static final int[] varServ = new int[3500];
    @OriginalMember(owner = "client!ea", name = "s", descriptor = "[I")
    public static final int[] mask = new int[32];
    @OriginalMember(owner = "client!qc", name = "K", descriptor = "Lclient!sc;")
    public static HashTable aClass133_20 = new HashTable(16);

    static {
        @Pc(10) int local10 = 2;
        for (@Pc(12) int local12 = 0; local12 < 32; local12++) {
            mask[local12] = local10 - 1;
            local10 += local10;
        }
    }

    @OriginalMember(owner = "client!me", name = "a", descriptor = "(II)I")
    public static int getVarbit(@OriginalArg(1) int arg0) {
        @Pc(13) VarBitType local13 = VarBitType.method2449(arg0);
        @Pc(16) int local16 = local13.anInt3327;
        @Pc(19) int local19 = local13.anInt3323;
        @Pc(22) int local22 = local13.anInt3318;
        @Pc(29) int local29 = mask[local19 - local22];
        return var[local16] >> local22 & local29;
    }

    @OriginalMember(owner = "client!nh", name = "a", descriptor = "(BII)V")
    public static void method2575(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        varServ[arg1] = arg0;
        @Pc(20) LongNode local20 = (LongNode) aClass133_20.find((long) arg1);
        if (local20 == null) {
            local20 = new LongNode(4611686018427387905L);
            aClass133_20.put(local20, (long) arg1);
        } else if (local20.aLong55 != 4611686018427387905L) {
            local20.aLong55 = MonotonicTime.currentTime() + 500L | 0x4000000000000000L;
        }
    }

    @OriginalMember(owner = "client!wd", name = "a", descriptor = "(BII)V")
    public static void method3995(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        @Pc(14) VarBitType local14 = VarBitType.method2449(arg1);
        @Pc(17) int local17 = local14.anInt3327;
        @Pc(20) int local20 = local14.anInt3323;
        @Pc(23) int local23 = local14.anInt3318;
        @Pc(29) int local29 = mask[local20 - local23];
        if (arg0 < 0 || local29 < arg0) {
            arg0 = 0;
        }
        local29 <<= local23;
        method2575(arg0 << local23 & local29 | ~local29 & varServ[local17], local17);
    }

    @OriginalMember(owner = "client!qg", name = "a", descriptor = "(IZI)V")
    public static void method3655(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        @Pc(7) VarBitType local7 = VarBitType.method2449(arg0);
        @Pc(10) int local10 = local7.anInt3323;
        @Pc(16) int local16 = local7.anInt3318;
        @Pc(19) int local19 = local7.anInt3327;
        @Pc(25) int local25 = mask[local10 - local16];
        if (arg1 < 0 || arg1 > local25) {
            arg1 = 0;
        }
        local25 <<= local16;
        Static148.method2766(local19, local25 & arg1 << local16 | var[local19] & ~local25);
    }
}
