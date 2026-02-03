import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ih")
public final class LinkList {

	@OriginalMember(owner = "client!ih", name = "p", descriptor = "Lclient!ab;")
	private Linkable cursor;

	@OriginalMember(owner = "client!ih", name = "m", descriptor = "Lclient!ab;")
	public final Linkable sentinel = new Linkable();

	@OriginalMember(owner = "client!ih", name = "<init>", descriptor = "()V")
	public LinkList() {
		this.sentinel.prev = this.sentinel;
		this.sentinel.next = this.sentinel;
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(I)V")
	public final void method2278() {
		while (true) {
			@Pc(5) Linkable local5 = this.sentinel.next;
			if (local5 == this.sentinel) {
				this.cursor = null;
				return;
			}
			local5.unlink();
		}
	}

	@OriginalMember(owner = "client!ih", name = "b", descriptor = "(I)Lclient!ab;")
	public final Linkable method2279() {
		@Pc(7) Linkable local7 = this.sentinel.prev;
		if (this.sentinel == local7) {
			this.cursor = null;
			return null;
		} else {
			this.cursor = local7.prev;
			return local7;
		}
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(ZLclient!ab;)V")
	public final void method2282(@OriginalArg(1) Linkable arg0) {
		if (arg0.prev != null) {
			arg0.unlink();
		}
		arg0.next = this.sentinel;
		arg0.prev = this.sentinel.prev;
		arg0.prev.next = arg0;
		arg0.next.prev = arg0;
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(ILclient!ab;)V")
	public final void method2283(@OriginalArg(1) Linkable arg0) {
		if (arg0.prev != null) {
			arg0.unlink();
		}
		arg0.next = this.sentinel.next;
		arg0.prev = this.sentinel;
		arg0.prev.next = arg0;
		arg0.next.prev = arg0;
	}

	@OriginalMember(owner = "client!ih", name = "d", descriptor = "(I)Lclient!ab;")
	public final Linkable method2286() {
		@Pc(13) Linkable local13 = this.cursor;
		if (this.sentinel == local13) {
			this.cursor = null;
			return null;
		} else {
			this.cursor = local13.prev;
			return local13;
		}
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(B)Lclient!ab;")
	public final Linkable method2287() {
		@Pc(3) Linkable local3 = this.sentinel.next;
		if (this.sentinel == local3) {
			return null;
		} else {
			local3.unlink();
			return local3;
		}
	}

	@OriginalMember(owner = "client!ih", name = "e", descriptor = "(I)Lclient!ab;")
	public final Linkable method2288() {
		@Pc(12) Linkable local12 = this.cursor;
		if (local12 == this.sentinel) {
			this.cursor = null;
			return null;
		} else {
			this.cursor = local12.next;
			return local12;
		}
	}

	@OriginalMember(owner = "client!ih", name = "f", descriptor = "(I)Lclient!ab;")
	public final Linkable head() {
		@Pc(3) Linkable local3 = this.sentinel.next;
		if (this.sentinel == local3) {
			this.cursor = null;
			return null;
		} else {
			this.cursor = local3.next;
			return local3;
		}
	}
}
