package jagex3.dash3d;

import jagex3.datastruct.DoublyLinkable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!pa")
public final class ClientObjNode extends DoublyLinkable {

	@OriginalMember(owner = "client!pa", name = "T", descriptor = "Lclient!uj;")
	public final ClientObj aClass8_Sub7_1;

	@OriginalMember(owner = "client!pa", name = "<init>", descriptor = "(Lclient!uj;)V")
	public ClientObjNode(@OriginalArg(0) ClientObj arg0) {
		this.aClass8_Sub7_1 = arg0;
	}
}
