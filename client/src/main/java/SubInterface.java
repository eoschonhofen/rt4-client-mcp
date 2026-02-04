import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!wk")
public final class SubInterface extends Linkable {

	@OriginalMember(owner = "client!wk", name = "r", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!wk", name = "s", descriptor = "I")
	public int anInt5879;
}
