package jagex3.dash3d;

import jagex3.datastruct.DoublyLinkable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!bk")
public final class MapSpotAnimNode extends DoublyLinkable {

	@OriginalMember(owner = "client!bk", name = "M", descriptor = "Lclient!bh;")
	public final MapSpotAnim aClass8_Sub2_1;

	@OriginalMember(owner = "client!bk", name = "<init>", descriptor = "(Lclient!bh;)V")
	public MapSpotAnimNode(@OriginalArg(0) MapSpotAnim arg0) {
		this.aClass8_Sub2_1 = arg0;
	}
}
