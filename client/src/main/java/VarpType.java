import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!eh")
public final class VarpType {

	@OriginalMember(owner = "client!sm", name = "c", descriptor = "Lclient!n;")
	public static final SoftLruCache recentUse = new SoftLruCache(64);

	@OriginalMember(owner = "client!gg", name = "ab", descriptor = "Lclient!ve;")
	public static Js5 clientConfig;

	@OriginalMember(owner = "client!nb", name = "p", descriptor = "I")
	public static int numDefinitions;

	@OriginalMember(owner = "client!eh", name = "e", descriptor = "I")
	public int clientcode = 0;

    @OriginalMember(owner = "client!ub", name = "a", descriptor = "(II)Lclient!eh;")
    public static VarpType list(@OriginalArg(1) int arg0) {
        @Pc(10) VarpType local10 = (VarpType) recentUse.find((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(20) byte[] local20 = clientConfig.getFile(16, arg0);
        local10 = new VarpType();
        if (local20 != null) {
            local10.decode(new Packet(local20));
        }
        recentUse.put(local10, (long) arg0);
        return local10;
    }

	@OriginalMember(owner = "client!sj", name = "a", descriptor = "(Lclient!ve;B)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		clientConfig = arg0;
		numDefinitions = clientConfig.getFileIdLimit(16);
	}

	@OriginalMember(owner = "client!bn", name = "c", descriptor = "(II)V")
	public static void method666() {
		recentUse.method3102(5);
	}

	@OriginalMember(owner = "client!ab", name = "b", descriptor = "(B)V")
	public static void method4657() {
		recentUse.clear();
	}

	@OriginalMember(owner = "client!ud", name = "d", descriptor = "(I)V")
	public static void method4266() {
		recentUse.method3104();
	}

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet buf) {
		while (true) {
			@Pc(5) int code = buf.g1();
			if (code == 0) {
				return;
			}

			this.decode(buf, code);
		}
	}

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(Lclient!wa;BI)V")
	private void decode(@OriginalArg(0) Packet buf, @OriginalArg(2) int code) {
		if (code == 5) {
			this.clientcode = buf.g2();
		}
	}
}
