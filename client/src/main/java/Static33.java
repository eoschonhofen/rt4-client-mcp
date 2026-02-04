import java.util.Date;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static33 {

	@OriginalMember(owner = "client!dm", name = "j", descriptor = "Lclient!na;")
	public static final JagString aClass100_351 = JagString.wrap(" ");
	@OriginalMember(owner = "client!cj", name = "h", descriptor = "Z")
	public static boolean aBoolean63;

	@OriginalMember(owner = "client!cj", name = "n", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest aClass212_1;

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(ILclient!pb;ZIIII)Z")
	public static boolean method867(@OriginalArg(0) int arg0, @OriginalArg(1) LocType arg1, @OriginalArg(5) int arg2, @OriginalArg(6) int arg3) {
		@Pc(10) MsiType local10 = MsiType.list(arg1.anInt4415);
		if (local10.anInt12 == -1) {
			return true;
		}
		if (arg1.aBoolean218) {
			@Pc(24) int local24 = arg3 + arg1.anInt4395;
			arg3 = local24 & 0x3;
		} else {
			arg3 = 0;
		}
		@Pc(42) SoftwarePix8 local42 = local10.method9(arg3);
		if (local42 == null) {
			return false;
		}
		@Pc(49) int local49 = arg1.anInt4397;
		@Pc(52) int local52 = arg1.anInt4403;
		if ((arg3 & 0x1) == 1) {
			local49 = arg1.anInt4403;
			local52 = arg1.anInt4397;
		}
		@Pc(66) int local66 = local42.anInt4279;
		@Pc(69) int local69 = local42.anInt4276;
		if (local10.aBoolean2) {
			local69 = local52 * 4;
			local66 = local49 * 4;
		}
		if (local10.anInt11 == 0) {
			local42.method1398(arg0 * 4 + 48, (-local52 + -arg2 + 104) * 4 + 48, local66, local69);
		} else {
			local42.method1390(arg0 * 4 + 48, (-local52 + -arg2 + 104) * 4 + 48, local66, local69, local10.anInt11);
		}
		return true;
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(ZI[B)Ljava/lang/Object;")
	public static Object method869(@OriginalArg(2) byte[] arg0) {
		if (arg0 == null) {
			return null;
		}
		if (arg0.length > 136 && !Static84.aBoolean127) {
			try {
				@Pc(27) ByteArrayWrapper local27 = (ByteArrayWrapper) Class.forName("ByteBufferNode").getDeclaredConstructor().newInstance();
				local27.method4238(arg0);
				return local27;
			} catch (@Pc(34) Throwable local34) {
				Static84.aBoolean127 = true;
			}
		}
		return arg0;
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(I)[Lclient!qf;")
	public static AbstractPix32[] method870() {
		@Pc(6) AbstractPix32[] local6 = new AbstractPix32[Static165.anInt4038];
		for (@Pc(15) int local15 = 0; local15 < Static165.anInt4038; local15++) {
			@Pc(30) int local30 = Static254.anIntArray488[local15] * Static26.anIntArray66[local15];
			@Pc(34) byte[] local34 = Static7.aByteArrayArray5[local15];
			@Pc(37) int[] local37 = new int[local30];
			for (@Pc(39) int local39 = 0; local39 < local30; local39++) {
				local37[local39] = Static259.anIntArray513[local34[local39] & 0xFF];
			}
			if (GameShell.glRenderer) {
				local6[local15] = new GlPix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local15], Static269.anIntArray252[local15], Static254.anIntArray488[local15], Static26.anIntArray66[local15], local37);
			} else {
				local6[local15] = new Pix32(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local15], Static269.anIntArray252[local15], Static254.anIntArray488[local15], Static26.anIntArray66[local15], local37);
			}
		}
		Static75.method1631();
		return local6;
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(IB)I")
	public static int method872(@OriginalArg(0) int arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(JB)Lclient!na;")
	public static JagString method873(@OriginalArg(0) long arg0) {
		Static35.aCalendar1.setTime(new Date(arg0));
		@Pc(13) int local13 = Static35.aCalendar1.get(7);
		@Pc(17) int local17 = Static35.aCalendar1.get(5);
		@Pc(21) int local21 = Static35.aCalendar1.get(2);
		@Pc(32) int local32 = Static35.aCalendar1.get(1);
		@Pc(36) int local36 = Static35.aCalendar1.get(11);
		@Pc(40) int local40 = Static35.aCalendar1.get(12);
		@Pc(44) int local44 = Static35.aCalendar1.get(13);
		return JagString.join(new JagString[] { Static219.aClass100Array149[local13 - 1], Static74.aClass100_461, JagString.parseInt(local17 / 10), JagString.parseInt(local17 % 10), Static270.aClass100_1089, Static138.aClass100Array102[local21], Static270.aClass100_1089, JagString.parseInt(local32), aClass100_351, JagString.parseInt(local36 / 10), JagString.parseInt(local36 % 10), Static264.aClass100_875, JagString.parseInt(local40 / 10), JagString.parseInt(local40 % 10), Static264.aClass100_875, JagString.parseInt(local44 / 10), JagString.parseInt(local44 % 10), Static55.aClass100_376 });
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(ZIIIIIIFB)[[I")
	public static int[][] method874(@OriginalArg(7) float arg0) {
		@Pc(15) int[][] local15 = new int[256][64];
		@Pc(19) TextureOp34 local19 = new TextureOp34();
		local19.anInt648 = (int) (arg0 * 4096.0F);
		local19.anInt642 = 3;
		local19.anInt641 = 4;
		local19.aBoolean44 = false;
		local19.anInt646 = 8;
		local19.method4630();
		Static10.method348(256, 64);
		for (@Pc(46) int local46 = 0; local46 < 256; local46++) {
			local19.method584(local46, local15[local46]);
		}
		return local15;
	}
}
