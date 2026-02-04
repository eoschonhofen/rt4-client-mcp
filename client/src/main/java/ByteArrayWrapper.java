import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!u")
public abstract class ByteArrayWrapper {

    @OriginalMember(owner = "client!km", name = "a", descriptor = "(ILjava/lang/Object;Z)[B")
    public static byte[] unwrap(@OriginalArg(1) Object arg0, @OriginalArg(2) boolean arg1) {
        if (arg0 == null) {
            return null;
        } else if (arg0 instanceof byte[]) {
            @Pc(14) byte[] local14 = (byte[]) arg0;
            return arg1 ? Static23.method648(local14) : local14;
        } else if (arg0 instanceof ByteArrayWrapper) {
            @Pc(34) ByteArrayWrapper local34 = (ByteArrayWrapper) arg0;
            return local34.method4236();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @OriginalMember(owner = "client!u", name = "a", descriptor = "(I)[B")
	public abstract byte[] method4236();

	@OriginalMember(owner = "client!u", name = "a", descriptor = "(I[B)V")
	public abstract void method4238(@OriginalArg(1) byte[] arg0);
}
