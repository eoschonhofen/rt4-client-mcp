import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ed")
public final class ReflectionCheck extends Linkable {

	@OriginalMember(owner = "client!ed", name = "p", descriptor = "I")
	public int size;

	@OriginalMember(owner = "client!ed", name = "u", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] method;

	@OriginalMember(owner = "client!ed", name = "v", descriptor = "[I")
	public int[] fieldValue;

	@OriginalMember(owner = "client!ed", name = "w", descriptor = "[I")
	public int[] error;

	@OriginalMember(owner = "client!ed", name = "y", descriptor = "[I")
	public int[] type;

	@OriginalMember(owner = "client!ed", name = "B", descriptor = "[[[B")
	public byte[][][] methodArgs;

	@OriginalMember(owner = "client!ed", name = "C", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] field;

	@OriginalMember(owner = "client!ed", name = "F", descriptor = "I")
	public int id;
}
