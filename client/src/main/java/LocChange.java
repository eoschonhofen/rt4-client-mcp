import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!cd")
public final class LocChange extends Linkable {

	@OriginalMember(owner = "client!cd", name = "r", descriptor = "I")
	public int anInt916;

	@OriginalMember(owner = "client!cd", name = "t", descriptor = "I")
	public int anInt918;

	@OriginalMember(owner = "client!cd", name = "w", descriptor = "I")
	public int anInt920;

	@OriginalMember(owner = "client!cd", name = "x", descriptor = "I")
	public int newType;

	@OriginalMember(owner = "client!cd", name = "z", descriptor = "I")
	public int anInt922;

	@OriginalMember(owner = "client!cd", name = "A", descriptor = "I")
	public int anInt923;

	@OriginalMember(owner = "client!cd", name = "F", descriptor = "I")
	public int anInt926;

	@OriginalMember(owner = "client!cd", name = "G", descriptor = "I")
	public int anInt927;

	@OriginalMember(owner = "client!cd", name = "H", descriptor = "I")
	public int anInt928;

	@OriginalMember(owner = "client!cd", name = "I", descriptor = "I")
	public int oldType;

	@OriginalMember(owner = "client!cd", name = "C", descriptor = "I")
	public int endTime = -1;

	@OriginalMember(owner = "client!cd", name = "E", descriptor = "I")
	public int startTime = 0;
}
