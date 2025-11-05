package jagex3.client;

import jagex3.datastruct.Linkable;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!wk")
public final class ComponentPointer extends Linkable {

	@OriginalMember(owner = "client!wk", name = "r", descriptor = "I")
	public int anInt5878;

	@OriginalMember(owner = "client!wk", name = "s", descriptor = "I")
	public int anInt5879;
}
