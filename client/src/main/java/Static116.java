import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static116 {

	@OriginalMember(owner = "client!jb", name = "k", descriptor = "[Lclient!qf;")
	public static AbstractPix32[] aClass3_Sub2_Sub1Array3;

	@OriginalMember(owner = "client!jb", name = "c", descriptor = "Lclient!na;")
	public static final JagString aClass100_583 = JagString.wrap("(Y<)4col>");

	@OriginalMember(owner = "client!jb", name = "y", descriptor = "I")
	public static int anInt2961 = 0;

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(ILclient!ve;I)Lclient!jk;")
	public static Patch method2320(@OriginalArg(1) Js5 arg0, @OriginalArg(2) int arg1) {
		@Pc(9) byte[] local9 = arg0.method4500(arg1);
		return local9 == null ? null : new Patch(local9);
	}

	@OriginalMember(owner = "client!jb", name = "a", descriptor = "(IZ)V")
	public static void method2325(@OriginalArg(1) boolean arg0) {
		WorldMap.aByteArrayArrayArray8 = null;
		WorldMap.anIntArrayArrayArray3 = null;
		Client.aClass13_8 = null;
		WorldMap.aByteArrayArrayArray3 = null;
		WorldMap.anIntArray330 = null;
		WorldMap.aByteArrayArrayArray10 = null;
		if (arg0 && WorldMap.aClass3_Sub2_Sub4_2 != null) {
			Static153.aClass100_724 = WorldMap.aClass3_Sub2_Sub4_2.aClass100_138;
		} else {
			Static153.aClass100_724 = null;
		}
		WorldMap.aByteArrayArrayArray7 = null;
		WorldMap.aByteArrayArrayArray12 = null;
		WorldMap.anIntArrayArrayArray5 = null;
		WorldMap.anIntArrayArrayArray17 = null;
		WorldMap.stage = 0;
		WorldMap.aClass3_Sub2_Sub4_2 = null;
		Static145.aClass69_84.method2278();
		WorldMap.aClass134_1 = null;
		Static217.anInt4901 = -1;
		WorldMap.f22 = null;
		WorldMap.f30 = null;
		WorldMap.f12 = null;
		WorldMap.f26 = null;
		WorldMap.f11 = null;
		WorldMap.f14 = null;
		WorldMap.f17 = null;
		WorldMap.f19 = null;
		Static70.aClass3_Sub2_Sub1_2 = null;
		Static142.anInt3482 = -1;
		Static153.aClass3_Sub2_Sub1_Sub1_2 = null;
	}
}
