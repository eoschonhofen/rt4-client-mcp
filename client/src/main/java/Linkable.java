import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ab")
public class Linkable {

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "J")
	public long key;

	@OriginalMember(owner = "client!ab", name = "d", descriptor = "Lclient!ab;")
	public Linkable next;

	@OriginalMember(owner = "client!ab", name = "l", descriptor = "Lclient!ab;")
	public Linkable prev;

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(I)Z")
	public final boolean isLinked() {
		return this.prev != null;
	}

	@OriginalMember(owner = "client!ab", name = "b", descriptor = "(I)V")
	public final void unlink() {
		if (this.prev != null) {
			this.prev.next = this.next;
			this.next.prev = this.prev;
			this.prev = null;
			this.next = null;
		}
	}
}
