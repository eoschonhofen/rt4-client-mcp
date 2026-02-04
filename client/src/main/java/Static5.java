import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static5 {

	@OriginalMember(owner = "client!af", name = "c", descriptor = "I")
	public static int anInt39 = -1;

	@OriginalMember(owner = "client!af", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_9 = JagString.wrap("hint_mapedge");

	@OriginalMember(owner = "client!af", name = "l", descriptor = "[S")
	public static final short[] aShortArray2 = new short[] { 30, 6, 31, 29, 10, 44, 37, 57 };

	@OriginalMember(owner = "client!af", name = "m", descriptor = "Lclient!na;")
	public static final JagString aClass100_10 = JagString.wrap("<br>");

	@OriginalMember(owner = "client!af", name = "a", descriptor = "(ILjava/lang/String;)V")
	public static void method31(@OriginalArg(1) String arg0) {
		System.out.println("Error: " + Static40.replace("%0a", "\n", arg0));
	}

	@OriginalMember(owner = "client!af", name = "b", descriptor = "(B)V")
	public static void method34() {
		WorldMap.method2325(false);
		System.gc();
		Client.setMainState(25);
	}

}
