import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static248 {

	@OriginalMember(owner = "client!uc", name = "f", descriptor = "I")
	public static int anInt4232;

	@OriginalMember(owner = "client!uc", name = "a", descriptor = "(IB)Z")
	public static boolean method3288(@OriginalArg(0) int arg0) {
		Static189.anInt4443 = arg0 + 1 & 0xFFFF;
		Client.aBoolean65 = true;
		return true;
	}

	@OriginalMember(owner = "client!uc", name = "a", descriptor = "(II)I")
	public static int method3289(@OriginalArg(0) int arg0) {
		@Pc(9) int local9 = (arg0 >>> 1 & 0xD5555555) + (arg0 & 0x55555555);
		@Pc(19) int local19 = (local9 >>> 2 & 0x33333333) + (local9 & 0x33333333);
		@Pc(31) int local31 = (local19 >>> 4) + local19 & 0xF0F0F0F;
		@Pc(37) int local37 = local31 + (local31 >>> 8);
		@Pc(43) int local43 = local37 + (local37 >>> 16);
		return local43 & 0xFF;
	}

}
