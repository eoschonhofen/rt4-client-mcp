import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ic")
public final class LightType {

	@OriginalMember(owner = "client!rm", name = "d", descriptor = "Lclient!n;")
	public static final SoftLruCache recentUse = new SoftLruCache(64);

	@OriginalMember(owner = "client!gl", name = "a", descriptor = "Lclient!ve;")
	public static Js5 aClass153_36;

	@OriginalMember(owner = "client!ic", name = "g", descriptor = "I")
	public int anInt2867 = 2048;

	@OriginalMember(owner = "client!ic", name = "c", descriptor = "I")
	public int anInt2865 = 0;

	@OriginalMember(owner = "client!ic", name = "o", descriptor = "I")
	public int anInt2872 = 0;

	@OriginalMember(owner = "client!ic", name = "p", descriptor = "I")
	public int anInt2873 = 2048;

    @OriginalMember(owner = "client!la", name = "a", descriptor = "(II)Lclient!ic;")
    public static LightType list(@OriginalArg(1) int arg0) {
        @Pc(10) LightType local10 = (LightType) recentUse.find((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(26) byte[] local26 = aClass153_36.getFile(31, arg0);
        local10 = new LightType();
        if (local26 != null) {
            local10.decode(new Packet(local26), arg0);
        }
        recentUse.put(local10, (long) arg0);
        return local10;
    }

	@OriginalMember(owner = "client!id", name = "a", descriptor = "(Lclient!ve;B)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		aClass153_36 = arg0;
	}

	@OriginalMember(owner = "client!c", name = "c", descriptor = "(II)V")
	public static void method715() {
		recentUse.method3102(5);
	}

	@OriginalMember(owner = "client!gd", name = "b", descriptor = "(I)V")
	public static void method1695() {
		recentUse.method3104();
	}

	@OriginalMember(owner = "client!hd", name = "a", descriptor = "(I)V")
	public static void method1882() {
		recentUse.clear();
	}

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "(ILclient!wa;I)V")
	public final void decode(@OriginalArg(1) Packet arg0, @OriginalArg(2) int arg1) {
		while (true) {
			@Pc(5) int local5 = arg0.g1();
			if (local5 == 0) {
				return;
			}
			this.method2258(local5, arg0, arg1);
		}
	}

	@OriginalMember(owner = "client!ic", name = "a", descriptor = "(ILclient!wa;IZ)V")
	private void method2258(@OriginalArg(0) int arg0, @OriginalArg(1) Packet arg1, @OriginalArg(2) int arg2) {
		if (arg0 == 1) {
			this.anInt2865 = arg1.g1();
		} else if (arg0 == 2) {
			this.anInt2873 = arg1.g2();
		} else if (arg0 == 3) {
			this.anInt2867 = arg1.g2();
		} else if (arg0 == 4) {
			this.anInt2872 = arg1.g2b();
		}
	}
}
