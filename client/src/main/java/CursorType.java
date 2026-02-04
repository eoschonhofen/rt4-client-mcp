import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ia")
public final class CursorType {

	@OriginalMember(owner = "client!ge", name = "i", descriptor = "Lclient!n;")
	public static final SoftLruCache recentUse = new SoftLruCache(64);
	@OriginalMember(owner = "client!ah", name = "i", descriptor = "Lclient!n;")
	public static final SoftLruCache aClass99_5 = new SoftLruCache(2);

	@OriginalMember(owner = "client!mc", name = "Z", descriptor = "Lclient!ve;")
	public static Js5 aClass153_57;

	@OriginalMember(owner = "client!tk", name = "j", descriptor = "Lclient!ve;")
	public static Js5 aClass153_97;

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "I")
	public int anInt2850;

	@OriginalMember(owner = "client!ia", name = "c", descriptor = "I")
	public int anInt2852;

	@OriginalMember(owner = "client!ia", name = "i", descriptor = "I")
	private int anInt2857;

    @OriginalMember(owner = "client!qg", name = "d", descriptor = "(II)Lclient!ia;")
    public static CursorType method3660(@OriginalArg(0) int arg0) {
        @Pc(10) CursorType local10 = (CursorType) recentUse.find((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(20) byte[] local20 = aClass153_57.getFile(33, arg0);
        local10 = new CursorType();
        if (local20 != null) {
            local10.decode(new Packet(local20), arg0);
        }
        recentUse.put(local10, (long) arg0);
        return local10;
    }

	@OriginalMember(owner = "client!u", name = "a", descriptor = "(BLclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		aClass153_57 = arg0;
		aClass153_97 = arg1;
	}

    @OriginalMember(owner = "client!an", name = "i", descriptor = "(I)V")
    public static void method351() {
        recentUse.method3104();
        aClass99_5.method3104();
    }

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(II)V")
	public static void method716() {
		recentUse.method3102(5);
		aClass99_5.method3102(5);
	}

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(Z)V")
	public static void method741() {
		recentUse.clear();
		aClass99_5.clear();
	}

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(B)Lclient!mm;")
	public final Pix32 method2246() {
		@Pc(7) Pix32 local7 = (Pix32) aClass99_5.find((long) this.anInt2857);
		if (local7 != null) {
			return local7;
		}
		local7 = Static80.depack(aClass153_97, this.anInt2857);
		if (local7 != null) {
			aClass99_5.put(local7, (long) this.anInt2857);
		}
		return local7;
	}

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(Lclient!wa;IB)V")
	public final void decode(@OriginalArg(0) Packet arg0, @OriginalArg(1) int arg1) {
		while (true) {
			@Pc(18) int local18 = arg0.g1();
			if (local18 == 0) {
				return;
			}
			this.decode(arg1, local18, arg0);
		}
	}

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(IIILclient!wa;)V")
	private void decode(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Packet arg2) {
		if (arg1 == 1) {
			this.anInt2857 = arg2.g2();
		} else if (arg1 == 2) {
			this.anInt2852 = arg2.g1();
			this.anInt2850 = arg2.g1();
		}
	}
}
