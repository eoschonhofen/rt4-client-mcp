package jagex3.dash3d;

import jagex3.datastruct.Linkable;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!qe")
public final class Inv extends Linkable {

	@OriginalMember(owner = "client!qe", name = "p", descriptor = "[I")
	public int[] anIntArray420 = new int[] { -1 };

	@OriginalMember(owner = "client!qe", name = "u", descriptor = "[I")
	public int[] anIntArray422 = new int[] { 0 };
}
