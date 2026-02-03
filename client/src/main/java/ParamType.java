import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hn")
public final class ParamType extends Linkable2 {

	@OriginalMember(owner = "client!wd", name = "a", descriptor = "Lclient!gn;")
	public static final LruCache recentUse = new LruCache(64);

	@OriginalMember(owner = "client!d", name = "hb", descriptor = "Lclient!ve;")
	public static Js5 clientConfig;

	@OriginalMember(owner = "client!hn", name = "I", descriptor = "I")
	public int anInt2667;

	@OriginalMember(owner = "client!hn", name = "L", descriptor = "I")
	private int anInt2669;

	@OriginalMember(owner = "client!hn", name = "Q", descriptor = "Lclient!na;")
	public JagString aClass100_544;

    @OriginalMember(owner = "client!ih", name = "a", descriptor = "(II)Lclient!hn;")
    public static ParamType list(@OriginalArg(1) int arg0) {
        @Pc(6) ParamType local6 = (ParamType) recentUse.method1806((long) arg0);
        if (local6 != null) {
            return local6;
        }
        @Pc(30) byte[] local30 = clientConfig.getFile(11, arg0);
        local6 = new ParamType();
        if (local30 != null) {
            local6.decode(new Packet(local30));
        }
        recentUse.method1811(local6, (long) arg0);
        return local6;
    }

	@OriginalMember(owner = "client!sf", name = "a", descriptor = "(BLclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0) {
		clientConfig = arg0;
	}

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(ILclient!wa;I)V")
	private void decode(@OriginalArg(0) int arg0, @OriginalArg(1) Packet arg1) {
		if (arg0 == 1) {
			this.anInt2669 = arg1.g1();
		} else if (arg0 == 2) {
			this.anInt2667 = arg1.g4();
		} else if (arg0 == 5) {
			this.aClass100_544 = arg1.gjstr();
		}
	}

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(13) int local13 = arg0.g1();
			if (local13 == 0) {
				return;
			}
			this.decode(local13, arg0);
		}
	}

	@OriginalMember(owner = "client!hn", name = "f", descriptor = "(I)Z")
	public final boolean method2078() {
		return this.anInt2669 == 115;
	}
}
