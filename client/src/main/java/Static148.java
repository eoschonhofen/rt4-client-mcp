import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static148 {

	@OriginalMember(owner = "client!li", name = "l", descriptor = "Lclient!ge;")
	public static DataFile aClass49_4;

	@OriginalMember(owner = "client!li", name = "t", descriptor = "I")
	public static int anInt3534;

	@OriginalMember(owner = "client!li", name = "w", descriptor = "Lclient!sc;")
	public static HashTable aClass133_13;

	@OriginalMember(owner = "client!li", name = "x", descriptor = "I")
	public static int anInt3535;

	@OriginalMember(owner = "client!li", name = "b", descriptor = "Lclient!na;")
	private static final JagString aClass100_673 = JagString.wrap("Loading wordpack )2 ");

	@OriginalMember(owner = "client!li", name = "e", descriptor = "Lclient!na;")
	public static final JagString aClass100_674 = JagString.wrap("Veuillez patienter)3)3)3");

	@OriginalMember(owner = "client!li", name = "u", descriptor = "Lclient!na;")
	private static final JagString aClass100_678 = JagString.wrap("Examine");

	@OriginalMember(owner = "client!li", name = "f", descriptor = "Lclient!na;")
	public static JagString aClass100_675 = aClass100_678;

	@OriginalMember(owner = "client!li", name = "h", descriptor = "[Lclient!mj;")
	public static final CollisionMap[] aClass97Array1 = new CollisionMap[4];

	@OriginalMember(owner = "client!li", name = "i", descriptor = "Lclient!na;")
	public static JagString MAINLOAD120 = aClass100_673;

	@OriginalMember(owner = "client!li", name = "p", descriptor = "Lclient!na;")
	public static final JagString aClass100_677 = JagString.wrap("::rebuild");

	@OriginalMember(owner = "client!li", name = "a", descriptor = "(ZI)V")
	public static void method2765(@OriginalArg(1) int arg0) {
		if (arg0 == -1 && !Static144.aBoolean173) {
			Static241.method4548();
		} else if (arg0 != -1 && (Static221.anInt4363 != arg0 || !Static136.method2655()) && Static12.anInt391 != 0 && !Static144.aBoolean173) {
			Static257.method526(arg0, client.songs, Static12.anInt391);
		}
		Static221.anInt4363 = arg0;
	}

	@OriginalMember(owner = "client!li", name = "a", descriptor = "(III)V")
	public static void method2766(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		Static7.var[arg0] = arg1;
		@Pc(21) LongNode local21 = (LongNode) Static199.aClass133_20.find((long) arg0);
		if (local21 == null) {
			local21 = new LongNode(MonotonicTime.currentTime() + 500L);
			Static199.aClass133_20.put(local21, (long) arg0);
		} else {
			local21.aLong55 = MonotonicTime.currentTime() + 500L;
		}
	}

	@OriginalMember(owner = "client!li", name = "a", descriptor = "(II)Lclient!dd;")
	public static PixFont method2768(@OriginalArg(1) int arg0) {
		@Pc(16) PixFont local16 = (PixFont) Static139.aClass99_22.find((long) arg0);
		if (local16 != null) {
			return local16;
		}
		@Pc(26) byte[] local26 = client.fontMetrics.getFile(arg0, 0);
		local16 = new PixFont(local26);
		local16.method2873(Static159.aClass36Array12, null);
		Static139.aClass99_22.put(local16, (long) arg0);
		return local16;
	}
}
