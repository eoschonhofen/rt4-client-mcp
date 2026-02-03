import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static199 {

	@OriginalMember(owner = "client!qc", name = "bb", descriptor = "[Lclient!kl;")
	public static FriendChatUser[] aClass3_Sub22Array1;

	@OriginalMember(owner = "client!qc", name = "cb", descriptor = "I")
	public static int anInt4675;

	@OriginalMember(owner = "client!qc", name = "K", descriptor = "Lclient!sc;")
	public static HashTable aClass133_20 = new HashTable(16);

	@OriginalMember(owner = "client!qc", name = "P", descriptor = "I")
	public static int loadPos = 10;

	@OriginalMember(owner = "client!qc", name = "U", descriptor = "I")
	public static int anInt4672 = 0;

	@OriginalMember(owner = "client!qc", name = "Z", descriptor = "Lclient!na;")
	private static final JagString aClass100_882 = JagString.wrap("Members object");

	@OriginalMember(owner = "client!qc", name = "Y", descriptor = "Lclient!na;")
	public static JagString aClass100_881 = aClass100_882;

	@OriginalMember(owner = "client!qc", name = "ab", descriptor = "[I")
	public static final int[] anIntArray417 = new int[1000];

	@OriginalMember(owner = "client!qc", name = "a", descriptor = "(BI)I")
	public static int method3594(@OriginalArg(1) int arg0) {
		return arg0 >> 11 & 0x7F;
	}

}
