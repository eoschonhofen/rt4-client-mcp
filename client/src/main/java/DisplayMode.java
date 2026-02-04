import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

@OriginalClass("client!od")
public final class DisplayMode {

	@OriginalMember(owner = "client!od", name = "j", descriptor = "I")
	public int anInt4248;

	@OriginalMember(owner = "client!od", name = "k", descriptor = "I")
	public int anInt4249;

	@OriginalMember(owner = "client!od", name = "l", descriptor = "I")
	public int anInt4250;

	@OriginalMember(owner = "client!od", name = "m", descriptor = "I")
	public int anInt4251;

    @OriginalMember(owner = "client!pm", name = "a", descriptor = "(ILsignlink!ll;)[Lclient!od;")
    public static DisplayMode[] method3558(@OriginalArg(1) SignLink arg0) {
        if (!arg0.method5111()) {
            return new DisplayMode[0];
        }
        @Pc(17) PrivilegedRequest local17 = arg0.method5132();
        while (local17.status == 0) {
            ThreadSleep.sleepPrecise(10L);
        }
        if (local17.status == 2) {
            return new DisplayMode[0];
        }
        @Pc(39) int[] local39 = (int[]) local17.result;
        @Pc(45) DisplayMode[] local45 = new DisplayMode[local39.length >> 2];
        for (@Pc(47) int local47 = 0; local47 < local45.length; local47++) {
            @Pc(59) DisplayMode local59 = new DisplayMode();
            local45[local47] = local59;
            local59.anInt4248 = local39[local47 << 2];
            local59.anInt4250 = local39[(local47 << 2) + 1];
            local59.anInt4251 = local39[(local47 << 2) + 2];
            local59.anInt4249 = local39[(local47 << 2) + 3];
        }
        return local45;
    }

    @OriginalMember(owner = "client!ab", name = "c", descriptor = "(B)[Lclient!od;")
    public static DisplayMode[] method4660() {
        if (Static105.aClass114Array1 == null) {
            @Pc(16) DisplayMode[] local16 = method3558(GameShell.signlink);
            @Pc(20) DisplayMode[] local20 = new DisplayMode[local16.length];
            @Pc(22) int local22 = 0;
            label52: for (@Pc(24) int local24 = 0; local24 < local16.length; local24++) {
                @Pc(32) DisplayMode local32 = local16[local24];
                if ((local32.anInt4251 <= 0 || local32.anInt4251 >= 24) && local32.anInt4248 >= 800 && local32.anInt4250 >= 600) {
                    for (@Pc(52) int local52 = 0; local52 < local22; local52++) {
                        @Pc(59) DisplayMode local59 = local20[local52];
                        if (local32.anInt4248 == local59.anInt4248 && local59.anInt4250 == local32.anInt4250) {
                            if (local32.anInt4251 > local59.anInt4251) {
                                local20[local52] = local32;
                            }
                            continue label52;
                        }
                    }
                    local20[local22] = local32;
                    local22++;
                }
            }
            Static105.aClass114Array1 = new DisplayMode[local22];
            Static289.method2617(local20, 0, Static105.aClass114Array1, 0, local22);
            @Pc(112) int[] local112 = new int[Static105.aClass114Array1.length];
            for (@Pc(114) int local114 = 0; local114 < Static105.aClass114Array1.length; local114++) {
                @Pc(122) DisplayMode local122 = Static105.aClass114Array1[local114];
                local112[local114] = local122.anInt4250 * local122.anInt4248;
            }
            Static181.method3346(local112, Static105.aClass114Array1);
        }
        return Static105.aClass114Array1;
    }

    @OriginalMember(owner = "client!nf", name = "a", descriptor = "(IIIIILsignlink!ll;)Ljava/awt/Frame;")
    public static Frame method3176(@OriginalArg(2) int arg0, @OriginalArg(3) int arg1, @OriginalArg(4) int arg2, @OriginalArg(5) SignLink arg3) {
        if (!arg3.method5111()) {
            return null;
        }
        @Pc(20) DisplayMode[] local20 = method3558(arg3);
        if (local20 == null) {
            return null;
        }
        @Pc(27) boolean local27 = false;
        for (@Pc(29) int local29 = 0; local29 < local20.length; local29++) {
            if (arg2 == local20[local29].anInt4248 && arg1 == local20[local29].anInt4250 && (!local27 || local20[local29].anInt4251 > arg0)) {
                arg0 = local20[local29].anInt4251;
                local27 = true;
            }
        }
        if (!local27) {
            return null;
        }
        @Pc(90) PrivilegedRequest local90 = arg3.method5129(arg0, arg1, arg2);
        while (local90.status == 0) {
            ThreadSleep.sleepPrecise(10L);
        }
        @Pc(103) Frame local103 = (Frame) local90.result;
        if (local103 == null) {
            return null;
        } else if (local90.status == 2) {
            Static25.method714(local103, arg3);
            return null;
        } else {
            return local103;
        }
    }
}
