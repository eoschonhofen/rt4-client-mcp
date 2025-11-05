package jagex3.graphics;

import jagex3.datastruct.Linkable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!lb")
public final class MonochromeImageCacheEntry extends Linkable {

	@OriginalMember(owner = "client!lb", name = "t", descriptor = "I")
	public final int anInt3468;

	@OriginalMember(owner = "client!lb", name = "C", descriptor = "I")
	public final int anInt3476;

	@OriginalMember(owner = "client!lb", name = "<init>", descriptor = "(II)V")
	public MonochromeImageCacheEntry(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.anInt3468 = arg1;
		this.anInt3476 = arg0;
	}
}
