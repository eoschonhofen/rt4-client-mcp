import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static11 {

	@OriginalMember(owner = "client!ba", name = "s", descriptor = "Lclient!na;")
	private static final JagString aClass100_68 = JagString.wrap("Loaded update list");

	@OriginalMember(owner = "client!ba", name = "w", descriptor = "I")
	public static int anInt384 = 0;

	@OriginalMember(owner = "client!ba", name = "x", descriptor = "Z")
	public static boolean aBoolean15 = true;

	@OriginalMember(owner = "client!ba", name = "z", descriptor = "Lclient!na;")
	public static JagString MAINLOAD40B = aClass100_68;

	@OriginalMember(owner = "client!ba", name = "E", descriptor = "Lclient!na;")
	public static final JagString aClass100_72 = JagString.wrap("Stufe: ");

	@OriginalMember(owner = "client!ba", name = "e", descriptor = "(I)V")
	public static void method443() {
		Static262.aClass99_35.method3104();
	}

	@OriginalMember(owner = "client!ba", name = "a", descriptor = "(IB)I")
	public static int method446(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return 0;
		}
		@Pc(17) ClientInvCache local17 = (ClientInvCache) Static20.aClass133_2.find((long) arg0);
		if (local17 == null) {
			return InvType.list(arg0).anInt3706;
		}
		@Pc(31) int local31 = 0;
		for (@Pc(33) int local33 = 0; local33 < local17.anIntArray420.length; local33++) {
			if (local17.anIntArray420[local33] == -1) {
				local31++;
			}
		}
		return local31 + InvType.list(arg0).anInt3706 - local17.anIntArray420.length;
	}
}
