import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cl")
public final class AnimFrameSet extends Linkable2 {

	@OriginalMember(owner = "client!cl", name = "W", descriptor = "[Lclient!ne;")
	public final AnimFrame[] aClass104Array1;

	@OriginalMember(owner = "client!cl", name = "<init>", descriptor = "(Lclient!ve;Lclient!ve;IZ)V")
	public AnimFrameSet(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3) {
		@Pc(5) LinkList local5 = new LinkList();
		@Pc(10) int local10 = arg0.getFileIdLimit(arg2);
		this.aClass104Array1 = new AnimFrame[local10];
		@Pc(19) int[] local19 = arg0.getFileLimit(arg2);
		for (@Pc(21) int local21 = 0; local21 < local19.length; local21++) {
			@Pc(37) byte[] local37 = arg0.getFile(arg2, local19[local21]);
			@Pc(51) int local51 = local37[1] & 0xFF | (local37[0] & 0xFF) << 8;
			@Pc(56) AnimBase local56 = (AnimBase) local5.head();
			@Pc(58) AnimBase local58 = null;
			while (local56 != null) {
				if (local56.anInt3113 == local51) {
					local58 = local56;
					break;
				}
				local56 = (AnimBase) local5.next();
			}
			if (local58 == null) {
				@Pc(85) byte[] local85 = arg1.method4502(0, local51);
				local58 = new AnimBase(local51, local85);
				local5.push(local58);
			}
			this.aClass104Array1[local19[local21]] = new AnimFrame(local37, local58);
		}
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(Lclient!ve;ZLclient!ve;BI)Lclient!cl;")
	public static AnimFrameSet method1803(@OriginalArg(0) Js5 arg0, @OriginalArg(2) Js5 arg1, @OriginalArg(4) int arg2) {
		@Pc(5) boolean local5 = true;
		@Pc(16) int[] local16 = arg0.getFileLimit(arg2);
		for (@Pc(18) int local18 = 0; local18 < local16.length; local18++) {
			@Pc(30) byte[] local30 = arg0.method4502(local16[local18], arg2);
			if (local30 == null) {
				local5 = false;
			} else {
				@Pc(49) int local49 = (local30[0] & 0xFF) << 8 | local30[1] & 0xFF;
				@Pc(57) byte[] local57 = arg1.method4502(0, local49);
				if (local57 == null) {
					local5 = false;
				}
			}
		}
		if (!local5) {
			return null;
		}
		try {
			return new AnimFrameSet(arg0, arg1, arg2, false);
		} catch (@Pc(84) Exception local84) {
			return null;
		}
	}

	@OriginalMember(owner = "client!cl", name = "c", descriptor = "(II)Z")
	public final boolean method901(@OriginalArg(1) int arg0) {
		return this.aClass104Array1[arg0].aBoolean197;
	}

	@OriginalMember(owner = "client!cl", name = "a", descriptor = "(IB)Z")
	public final boolean method903(@OriginalArg(0) int arg0) {
		return this.aClass104Array1[arg0].aBoolean196;
	}
}
