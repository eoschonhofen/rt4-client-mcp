import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!da")
public final class DelayedStateChange extends Linkable2 {

	@OriginalMember(owner = "client!la", name = "f", descriptor = "Lclient!ce;")
	public static final LinkList2 aClass16_7 = new LinkList2();

	@OriginalMember(owner = "client!rh", name = "e", descriptor = "Lclient!ce;")
	public static final LinkList2 aClass16_9 = new LinkList2();

	@OriginalMember(owner = "client!da", name = "T", descriptor = "I")
	public int anInt1269;

	@OriginalMember(owner = "client!da", name = "U", descriptor = "I")
	public int anInt1270;

	@OriginalMember(owner = "client!da", name = "V", descriptor = "I")
	public int anInt1271;

	@OriginalMember(owner = "client!da", name = "W", descriptor = "Lclient!na;")
	public JagString aClass100_254;

	@OriginalMember(owner = "client!da", name = "<init>", descriptor = "(II)V")
	public DelayedStateChange(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.key = (long) arg0 << 32 | (long) arg1;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Z)V")
	public final void method1007() {
		this.key2 = MonotonicTime.currentTime() + 500L | Long.MIN_VALUE & this.key2;
		aClass16_7.pushFront(this);
	}

	@OriginalMember(owner = "client!da", name = "b", descriptor = "(Z)J")
	public final long method1009() {
		return this.key2 & Long.MAX_VALUE;
	}

	@OriginalMember(owner = "client!da", name = "e", descriptor = "(I)I")
	public final int method1011() {
		return (int) (this.key >>> 32 & 0xFFL);
	}

	@OriginalMember(owner = "client!da", name = "f", descriptor = "(B)I")
	public final int method1012() {
		return (int) this.key;
	}

	@OriginalMember(owner = "client!da", name = "g", descriptor = "(B)V")
	public final void method1017() {
		this.key2 |= Long.MIN_VALUE;
		if (this.method1009() == 0L) {
			aClass16_9.pushFront(this);
		}
	}
}
