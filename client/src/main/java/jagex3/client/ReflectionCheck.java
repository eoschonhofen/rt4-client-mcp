package jagex3.client;

import jagex3.datastruct.Linkable;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import sign.PrivilegedRequest;

@OriginalClass("client!ed")
public final class ReflectionCheck extends Linkable {

	@OriginalMember(owner = "client!ed", name = "p", descriptor = "I")
	public int anInt1725;

	@OriginalMember(owner = "client!ed", name = "u", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] aClass212Array1;

	@OriginalMember(owner = "client!ed", name = "v", descriptor = "[I")
	public int[] anIntArray137;

	@OriginalMember(owner = "client!ed", name = "w", descriptor = "[I")
	public int[] anIntArray138;

	@OriginalMember(owner = "client!ed", name = "y", descriptor = "[I")
	public int[] anIntArray139;

	@OriginalMember(owner = "client!ed", name = "B", descriptor = "[[[B")
	public byte[][][] aByteArrayArrayArray6;

	@OriginalMember(owner = "client!ed", name = "C", descriptor = "[Lsignlink!im;")
	public PrivilegedRequest[] aClass212Array2;

	@OriginalMember(owner = "client!ed", name = "F", descriptor = "I")
	public int anInt1732;
}
