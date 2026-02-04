import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ce")
public final class LinkList2 {

	@OriginalMember(owner = "client!ce", name = "n", descriptor = "Lclient!rg;")
	private Linkable2 cursor;

	@OriginalMember(owner = "client!ce", name = "l", descriptor = "Lclient!rg;")
	private final Linkable2 sentinel = new Linkable2();

	@OriginalMember(owner = "client!ce", name = "<init>", descriptor = "()V")
	public LinkList2() {
		this.sentinel.next2 = this.sentinel;
		this.sentinel.prev2 = this.sentinel;
	}

    @OriginalMember(owner = "client!gk", name = "a", descriptor = "(Lclient!rg;Lclient!rg;B)V")
    public static void insertBefore(@OriginalArg(0) Linkable2 arg0, @OriginalArg(1) Linkable2 arg1) {
        if (arg1.prev2 != null) {
            arg1.unlink2();
        }
        arg1.prev2 = arg0;
        arg1.next2 = arg0.next2;
        arg1.prev2.next2 = arg1;
        arg1.next2.prev2 = arg1;
    }

    @OriginalMember(owner = "client!ce", name = "a", descriptor = "(I)I")
	public final int method793() {
		@Pc(3) int local3 = 0;
		@Pc(7) Linkable2 local7 = this.sentinel.next2;
		while (local7 != this.sentinel) {
			local7 = local7.next2;
			local3++;
		}
		return local3;
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(B)Lclient!rg;")
	public final Linkable2 method795() {
		@Pc(3) Linkable2 local3 = this.sentinel.next2;
		if (this.sentinel == local3) {
			this.cursor = null;
			return null;
		} else {
			this.cursor = local3.next2;
			return local3;
		}
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(I)Lclient!rg;")
	public final Linkable2 search() {
		@Pc(7) Linkable2 local7 = this.sentinel.next2;
		if (local7 == this.sentinel) {
			return null;
		} else {
			local7.unlink2();
			return local7;
		}
	}

	@OriginalMember(owner = "client!ce", name = "c", descriptor = "(I)Lclient!rg;")
	public final Linkable2 method797() {
		@Pc(2) Linkable2 local2 = this.cursor;
		if (local2 == this.sentinel) {
			this.cursor = null;
			return null;
		} else {
			this.cursor = local2.next2;
			return local2;
		}
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(Lclient!rg;B)V")
	public final void pushFront(@OriginalArg(0) Linkable2 arg0) {
		if (arg0.prev2 != null) {
			arg0.unlink2();
		}
		arg0.prev2 = this.sentinel.prev2;
		arg0.next2 = this.sentinel;
		arg0.prev2.next2 = arg0;
		arg0.next2.prev2 = arg0;
	}

	@OriginalMember(owner = "client!ce", name = "d", descriptor = "(I)V")
	public final void method802() {
		while (true) {
			@Pc(15) Linkable2 local15 = this.sentinel.next2;
			if (this.sentinel == local15) {
				this.cursor = null;
				return;
			}
			local15.unlink2();
		}
	}
}
