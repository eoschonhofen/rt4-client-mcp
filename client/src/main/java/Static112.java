import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static112 {

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "Lclient!na;")
	public static final JagString aClass100_574 = JagString.wrap(":duelfriend:");

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "(Lclient!km;I)I")
	public static int method2299(@OriginalArg(0) ClientNPC arg0) {
		@Pc(13) NPCType local13 = arg0.aClass96_1;
		if (local13.anIntArray357 != null) {
			local13 = local13.method2932();
			if (local13 == null) {
				return -1;
			}
		}
		@Pc(29) int local29 = local13.anInt3747;
		@Pc(33) BasType local33 = arg0.method2681();
		if (local33.anInt1037 == arg0.anInt3366) {
			local29 = local13.anInt3728;
		} else if (arg0.anInt3366 == local33.anInt1058 || local33.anInt1054 == arg0.anInt3366 || arg0.anInt3366 == local33.anInt1045 || arg0.anInt3366 == local33.anInt1043) {
			local29 = local13.anInt3734;
		} else if (local33.anInt1062 == arg0.anInt3366 || arg0.anInt3366 == local33.anInt1042 || arg0.anInt3366 == local33.anInt1048 || arg0.anInt3366 == local33.anInt1066) {
			local29 = local13.anInt3745;
		}
		return local29;
	}
}
