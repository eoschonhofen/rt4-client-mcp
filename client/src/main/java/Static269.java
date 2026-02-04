import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static269 {

	@OriginalMember(owner = "client!wa", name = "z", descriptor = "[I")
	public static int[] anIntArray252;

	@OriginalMember(owner = "client!wa", name = "pb", descriptor = "Lclient!na;")
	public static final JagString aClass100_556 = JagString.wrap("<br>");

	@OriginalMember(owner = "client!wa", name = "e", descriptor = "(B)V")
	public static void method2172() {
		ObjType.aClass99_16.method3104();
	}

	@OriginalMember(owner = "client!wa", name = "o", descriptor = "(I)V")
	public static void method2218() {
		@Pc(8) int local8 = Static236.method4047();
		if (local8 == 0) {
			Static266.aByteArrayArrayArray15 = null;
			Static232.method3993(0);
		} else if (local8 == 1) {
			Static38.method960((byte) 0);
			Static232.method3993(512);
			Static132.method2608();
		} else {
			Static38.method960((byte) (Static136.anInt3325 - 4 & 0xFF));
			Static232.method3993(2);
		}
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
	public static JagString method2228(@OriginalArg(0) int arg0) {
		return Client.aClass100Array160[arg0].length() > 0 ? JagString.join(new JagString[] { Client.aClass100Array168[arg0], Text.aClass100_901, Client.aClass100Array160[arg0] }) : Client.aClass100Array168[arg0];
	}
}
