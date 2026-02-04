import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static93 {

	@OriginalMember(owner = "client!he", name = "ab", descriptor = "Lclient!sc;")
	public static final HashTable aClass133_7 = new HashTable(16);

	@OriginalMember(owner = "client!he", name = "db", descriptor = "Lclient!na;")
	public static final JagString aClass100_517 = JagString.wrap("");

	@OriginalMember(owner = "client!he", name = "eb", descriptor = "[I")
	public static final int[] anIntArray219 = new int[1000];

	@OriginalMember(owner = "client!he", name = "gb", descriptor = "Lclient!na;")
	public static final JagString aClass100_518 = JagString.wrap("www");

	@OriginalMember(owner = "client!he", name = "c", descriptor = "(II)V")
	public static void method1906(@OriginalArg(1) int arg0) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(7, arg0);
		local12.method1007();
	}
}
