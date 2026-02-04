import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static124 {

	@OriginalMember(owner = "client!jk", name = "x", descriptor = "I")
	public static int anInt3080;

	@OriginalMember(owner = "client!jk", name = "J", descriptor = "I")
	public static int anInt3083;

	@OriginalMember(owner = "client!jk", name = "y", descriptor = "Z")
	public static boolean aBoolean156 = false;

	@OriginalMember(owner = "client!jk", name = "z", descriptor = "Lclient!n;")
	public static final SoftLruCache aClass99_17 = new SoftLruCache(50);

	@OriginalMember(owner = "client!jk", name = "F", descriptor = "Lclient!na;")
	public static final JagString aClass100_596 = JagString.wrap("::tween");

	@OriginalMember(owner = "client!jk", name = "a", descriptor = "(ILclient!na;)Lclient!bn;")
	public static Map method2434(@OriginalArg(1) JagString arg0) {
		for (@Pc(15) Map local15 = (Map) Static228.aClass69_120.head(); local15 != null; local15 = (Map) Static228.aClass69_120.next()) {
			if (local15.aClass100_138.equalsInner(arg0)) {
				return local15;
			}
		}
		return null;
	}

}
