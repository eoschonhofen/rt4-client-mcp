import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static17 {

	@OriginalMember(owner = "client!bg", name = "g", descriptor = "Lclient!i;")
	public static final PacketBit aClass3_Sub15_Sub1_2 = new PacketBit(5000);

	@OriginalMember(owner = "client!bg", name = "z", descriptor = "Lclient!na;")
	public static final JagString aClass100_101 = JagString.wrap("k");

	@OriginalMember(owner = "client!bg", name = "M", descriptor = "I")
	public static int anInt577 = 0;

	@OriginalMember(owner = "client!bg", name = "N", descriptor = "Lclient!na;")
	public static final JagString aClass100_102 = JagString.wrap("mapfunction");

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(B)V")
	public static void method527() {
		Client.in.gBitStart();
		@Pc(11) int local11 = Client.in.method2238(1);
		if (local11 == 0) {
			return;
		}
		@Pc(23) int local23 = Client.in.method2238(2);
		if (local23 == 0) {
			Static44.anIntArray106[Static116.anInt2951++] = 2047;
			return;
		}
		@Pc(54) int local54;
		@Pc(64) int local64;
		if (local23 == 1) {
			local54 = Client.in.method2238(3);
			Static173.aClass8_Sub4_Sub1_2.method2684(1, local54);
			local64 = Client.in.method2238(1);
			if (local64 == 1) {
				Static44.anIntArray106[Static116.anInt2951++] = 2047;
			}
		} else if (local23 == 2) {
			if (Client.in.method2238(1) == 1) {
				local54 = Client.in.method2238(3);
				Static173.aClass8_Sub4_Sub1_2.method2684(2, local54);
				local64 = Client.in.method2238(3);
				Static173.aClass8_Sub4_Sub1_2.method2684(2, local64);
			} else {
				local54 = Client.in.method2238(3);
				Static173.aClass8_Sub4_Sub1_2.method2684(0, local54);
			}
			local54 = Client.in.method2238(1);
			if (local54 == 1) {
				Static44.anIntArray106[Static116.anInt2951++] = 2047;
			}
		} else if (local23 == 3) {
			local54 = Client.in.method2238(7);
			local64 = Client.in.method2238(1);
			Static55.anInt1735 = Client.in.method2238(2);
			@Pc(163) int local163 = Client.in.method2238(1);
			if (local163 == 1) {
				Static44.anIntArray106[Static116.anInt2951++] = 2047;
			}
			@Pc(181) int local181 = Client.in.method2238(7);
			Static173.aClass8_Sub4_Sub1_2.method1265(local181, local64 == 1, local54);
		}
	}

	@OriginalMember(owner = "client!bg", name = "d", descriptor = "(II)Z")
	public static boolean method530(@OriginalArg(0) int arg0) {
		return arg0 == 198 || arg0 == 230 || arg0 == 156 || arg0 == 140 || arg0 == 223;
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(Lclient!be;ZI)V")
	public static void method531(@OriginalArg(0) IfType arg0, @OriginalArg(1) boolean arg1) {
		@Pc(20) int local20 = arg0.scrollWidth == 0 ? arg0.anInt445 : arg0.scrollWidth;
		@Pc(32) int local32 = arg0.scrollHeight == 0 ? arg0.anInt459 : arg0.scrollHeight;
		Static266.method4190(arg0.parentId, arg1, local20, local32, IfType.list[arg0.parentId >> 16]);
		if (arg0.aClass13Array3 != null) {
			Static266.method4190(arg0.parentId, arg1, local20, local32, arg0.aClass13Array3);
		}
		@Pc(66) SubInterface local66 = (SubInterface) Static119.aClass133_9.find((long) arg0.parentId);
		if (local66 != null) {
			Static234.method4017(local32, arg1, local66.anInt5878, local20);
		}
	}
}
