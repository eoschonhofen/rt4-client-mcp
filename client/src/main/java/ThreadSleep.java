import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ThreadSleep {
    @OriginalMember(owner = "client!rm", name = "a", descriptor = "(JB)V")
    public static void sleep(@OriginalArg(0) long arg0) {
        try {
            Thread.sleep(arg0);
        } catch (@Pc(11) InterruptedException local11) {
        }
    }

    @OriginalMember(owner = "client!sk", name = "a", descriptor = "(JI)V")
    public static void sleepPrecise(@OriginalArg(0) long arg0) {
        if (arg0 <= 0L) {
            return;
        }
        if (arg0 % 10L == 0L) {
            sleep(arg0 - 1L);
            sleep(1L);
        } else {
            sleep(arg0);
        }
    }
}
