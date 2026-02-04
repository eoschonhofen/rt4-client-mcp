import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!jj")
public final class GroundObject {

	@OriginalMember(owner = "client!jj", name = "a", descriptor = "Lclient!th;")
	public ModelSource bottomObj;

	@OriginalMember(owner = "client!jj", name = "b", descriptor = "I")
	public int y;

	@OriginalMember(owner = "client!jj", name = "c", descriptor = "Lclient!th;")
	public ModelSource middleObj;

	@OriginalMember(owner = "client!jj", name = "h", descriptor = "Lclient!th;")
	public ModelSource topObj;

	@OriginalMember(owner = "client!jj", name = "k", descriptor = "I")
	public int z;

	@OriginalMember(owner = "client!jj", name = "n", descriptor = "I")
	public int height;

	@OriginalMember(owner = "client!jj", name = "o", descriptor = "I")
	public int x;

	@OriginalMember(owner = "client!jj", name = "r", descriptor = "J")
	public long typecode;
}
