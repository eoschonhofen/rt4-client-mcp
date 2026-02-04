import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static71 {

	@OriginalMember(owner = "client!fk", name = "e", descriptor = "I")
	public static int anInt1885;

	@OriginalMember(owner = "client!fk", name = "j", descriptor = "[[I")
	public static int[][] anIntArrayArray10;

	@OriginalMember(owner = "client!fk", name = "g", descriptor = "Z")
	public static boolean aBoolean107 = true;

	@OriginalMember(owner = "client!fk", name = "k", descriptor = "[I")
	public static final int[] anIntArray147 = new int[14];

	@OriginalMember(owner = "client!fk", name = "a", descriptor = "([BIZ)I")
	public static int method1442(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1) {
		return Packet.getcrc(0, arg1, arg0);
	}

}
