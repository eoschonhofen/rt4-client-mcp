import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!md")
public final class InvType extends Linkable2 {

	@OriginalMember(owner = "client!ha", name = "p", descriptor = "Lclient!gn;")
	public static final LruCache recentUse = new LruCache(64);

	@OriginalMember(owner = "client!al", name = "q", descriptor = "Lclient!ve;")
	public static Js5 clientConfig;

	@OriginalMember(owner = "client!md", name = "K", descriptor = "I")
	public int size = 0;

    @OriginalMember(owner = "client!u", name = "a", descriptor = "(II)Lclient!md;")
    public static InvType list(@OriginalArg(0) int arg0) {
        @Pc(16) InvType local16 = (InvType) recentUse.method1806((long) arg0);
        if (local16 != null) {
            return local16;
        }
        @Pc(27) byte[] local27 = clientConfig.getFile(5, arg0);
        local16 = new InvType();
        if (local27 != null) {
            local16.decode(new Packet(local27));
        }
        recentUse.method1811(local16, (long) arg0);
        return local16;
    }

	@OriginalMember(owner = "client!je", name = "a", descriptor = "(ILclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0) {
		clientConfig = arg0;
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!wa;I)V")
	public final void decode(@OriginalArg(0) Packet arg0) {
		while (true) {
			@Pc(10) int local10 = arg0.g1();
			if (local10 == 0) {
				return;
			}
			this.decode(arg0, local10);
		}
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!wa;IZ)V")
	private void decode(@OriginalArg(0) Packet arg0, @OriginalArg(1) int arg1) {
		if (arg1 == 2) {
			this.size = arg0.g2();
		}
	}
}
