import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ByteArrayPool {
    @OriginalMember(owner = "client!wi", name = "X", descriptor = "[[B")
    public static final byte[][] aByteArrayArray16 = new byte[1000][];
    @OriginalMember(owner = "client!bb", name = "t", descriptor = "[[B")
    public static final byte[][] aByteArrayArray2 = new byte[250][];
    @OriginalMember(owner = "client!dc", name = "db", descriptor = "[[B")
    public static final byte[][] aByteArrayArray6 = new byte[50][];
    @OriginalMember(owner = "client!ja", name = "j", descriptor = "I")
    public static int anInt2937 = 0;
    @OriginalMember(owner = "client!ug", name = "r", descriptor = "I")
    public static int anInt5459 = 0;
    @OriginalMember(owner = "client!sd", name = "T", descriptor = "I")
    public static int anInt5064 = 0;

    @OriginalMember(owner = "client!sh", name = "a", descriptor = "(II)[B")
    public static synchronized byte[] method3907(@OriginalArg(1) int arg0) {
        @Pc(22) byte[] local22;
        if (arg0 == 100 && anInt2937 > 0) {
            local22 = aByteArrayArray16[--anInt2937];
            aByteArrayArray16[anInt2937] = null;
            return local22;
        } else if (arg0 == 5000 && anInt5459 > 0) {
            local22 = aByteArrayArray2[--anInt5459];
            aByteArrayArray2[anInt5459] = null;
            return local22;
        } else if (arg0 == 30000 && anInt5064 > 0) {
            local22 = aByteArrayArray6[--anInt5064];
            aByteArrayArray6[anInt5064] = null;
            return local22;
        } else {
            return new byte[arg0];
        }
    }
}
