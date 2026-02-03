import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!kk")
public final class VarBitType {

	@OriginalMember(owner = "client!nj", name = "c", descriptor = "Lclient!ve;")
	public static Js5 varbitConfig;
	@OriginalMember(owner = "client!kk", name = "c", descriptor = "I")
	public int anInt3318;

	@OriginalMember(owner = "client!kk", name = "h", descriptor = "I")
	public int anInt3323;

	@OriginalMember(owner = "client!kk", name = "l", descriptor = "I")
	public int anInt3327;

    @OriginalMember(owner = "client!jl", name = "a", descriptor = "(IB)Lclient!kk;")
    public static VarBitType method2449(@OriginalArg(0) int arg0) {
        @Pc(10) VarBitType local10 = (VarBitType) Static125.aClass99_19.find((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(31) byte[] local31 = varbitConfig.getFile(Static254.method4349(arg0), Static274.method3845(arg0));
        local10 = new VarBitType();
        if (local31 != null) {
            local10.method2651(new Packet(local31));
        }
        Static125.aClass99_19.put(local10, (long) arg0);
        return local10;
    }

	@OriginalMember(owner = "client!og", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		varbitConfig = arg0;
	}

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;I)V")
	public final void method2651(@OriginalArg(0) Packet arg0) {
		while (true) {
			@Pc(9) int local9 = arg0.g1();
			if (local9 == 0) {
				return;
			}
			this.method2653(arg0, local9);
		}
	}

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;II)V")
	private void method2653(@OriginalArg(0) Packet arg0, @OriginalArg(2) int arg1) {
		if (arg1 == 1) {
			this.anInt3327 = arg0.g2();
			this.anInt3318 = arg0.g1();
			this.anInt3323 = arg0.g1();
		}
	}
}
