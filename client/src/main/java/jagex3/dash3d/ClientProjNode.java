package jagex3.dash3d;

import jagex3.datastruct.DoublyLinkable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ud")
public final class ClientProjNode extends DoublyLinkable {

	@OriginalMember(owner = "client!ud", name = "R", descriptor = "Lclient!ra;")
	public final ClientProj aClass8_Sub6_1;

	@OriginalMember(owner = "client!ud", name = "<init>", descriptor = "(Lclient!ra;)V")
	public ClientProjNode(@OriginalArg(0) ClientProj arg0) {
		this.aClass8_Sub6_1 = arg0;
	}
}
