import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rm")
public final class HashTable2 {

	@OriginalMember(owner = "client!rm", name = "e", descriptor = "[Lclient!rg;")
	private final Linkable2[] buckets;

	@OriginalMember(owner = "client!rm", name = "<init>", descriptor = "(I)V")
	public HashTable2(@OriginalArg(0) int arg0) {
		this.buckets = new Linkable2[arg0];
		for (@Pc(7) int local7 = 0; local7 < arg0; local7++) {
			@Pc(23) Linkable2 local23 = this.buckets[local7] = new Linkable2();
			local23.prev2 = local23;
			local23.next2 = local23;
		}
	}
}
