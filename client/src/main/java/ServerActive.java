import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!bf")
public final class ServerActive extends Linkable {

	@OriginalMember(owner = "client!bf", name = "q", descriptor = "I")
	public final int anInt540;

	@OriginalMember(owner = "client!bf", name = "x", descriptor = "I")
	public final int eventCode;

	@OriginalMember(owner = "client!bf", name = "<init>", descriptor = "(II)V")
	public ServerActive(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.anInt540 = arg1;
		this.eventCode = arg0;
	}

	@OriginalMember(owner = "client!bf", name = "a", descriptor = "(IB)Z")
	public final boolean method503(@OriginalArg(0) int arg0) {
		return (this.eventCode >> arg0 + 1 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "d", descriptor = "(I)Z")
	public final boolean method504() {
		return (this.eventCode >> 29 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "e", descriptor = "(B)I")
	public final int method505() {
		return this.eventCode >> 18 & 0x7;
	}

	@OriginalMember(owner = "client!bf", name = "e", descriptor = "(I)Z")
	public final boolean method506() {
		return (this.eventCode & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "f", descriptor = "(I)Z")
	public final boolean method507() {
		return (this.eventCode >> 31 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "g", descriptor = "(I)Z")
	public final boolean method508() {
		return (this.eventCode >> 22 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "a", descriptor = "(Z)Z")
	public final boolean method509() {
		return (this.eventCode >> 21 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "h", descriptor = "(I)Z")
	public final boolean method510() {
		return (this.eventCode >> 30 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "f", descriptor = "(B)Z")
	public final boolean method511() {
		return (this.eventCode >> 28 & 0x1) != 0;
	}

	@OriginalMember(owner = "client!bf", name = "i", descriptor = "(I)I")
	public final int method512() {
		return Static199.method3594(this.eventCode);
	}
}
