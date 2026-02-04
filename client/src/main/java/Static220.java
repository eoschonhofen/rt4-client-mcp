import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static220 {

	@OriginalMember(owner = "client!rm", name = "g", descriptor = "Z")
	public static boolean aBoolean244 = true;

	@OriginalMember(owner = "client!rm", name = "i", descriptor = "Lclient!na;")
	public static final JagString aClass100_930 = JagString.wrap("(Z");

	@OriginalMember(owner = "client!rm", name = "k", descriptor = "Lclient!na;")
	public static final JagString aClass100_932 = Text.aClass100_929;

	@OriginalMember(owner = "client!rm", name = "a", descriptor = "(ZIIIILclient!ak;I)Lclient!ak;")
	public static ModelLit method3800(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) ModelLit arg4, @OriginalArg(6) int arg5) {
		@Pc(4) long local4 = (long) arg2;
		@Pc(10) ModelLit local10 = (ModelLit) Static110.aClass99_15.find(local4);
		if (local10 == null) {
			@Pc(22) ModelUnlit local22 = Static77.method1686(Client.models, arg2);
			if (local22 == null) {
				return null;
			}
			local10 = local22.method1679(64, 768, -50, -10, -50);
			Static110.aClass99_15.put(local10, local4);
		}
		@Pc(42) int local42 = arg4.method4562();
		@Pc(45) int local45 = arg4.method4561();
		@Pc(48) int local48 = arg4.method4576();
		@Pc(51) int local51 = arg4.method4550();
		local10 = local10.method4560(true, true, true);
		if (arg0 != 0) {
			local10.method4554(arg0);
		}
		@Pc(94) int local94;
		if (GameShell.glRenderer) {
			@Pc(68) GlModelLit local68 = (GlModelLit) local10;
			if (arg5 != Client.getAvH(Client.minusedlevel, arg3 + local42, arg1 + local48) || arg5 != Client.getAvH(Client.minusedlevel, arg3 + local45, local51 + arg1)) {
				for (local94 = 0; local94 < local68.anInt5295; local94++) {
					local68.anIntArray465[local94] += Client.getAvH(Client.minusedlevel, local68.anIntArray461[local94] + arg3, local68.anIntArray466[local94] + arg1) - arg5;
				}
				local68.aClass127_4.aBoolean235 = false;
				local68.aClass5_1.aBoolean3 = false;
			}
		} else {
			@Pc(142) SoftwareModelLit local142 = (SoftwareModelLit) local10;
			if (arg5 != Client.getAvH(Client.minusedlevel, local42 + arg3, local48 + arg1) || arg5 != Client.getAvH(Client.minusedlevel, arg3 + local45, local51 + arg1)) {
				for (local94 = 0; local94 < local142.anInt5788; local94++) {
					local142.anIntArray527[local94] += Client.getAvH(Client.minusedlevel, arg3 + local142.anIntArray528[local94], local142.anIntArray531[local94] + arg1) - arg5;
				}
				local142.aBoolean305 = false;
			}
		}
		return local10;
	}

	@OriginalMember(owner = "client!rm", name = "a", descriptor = "(III)V")
	public static void method3801() {
		for (@Pc(1) int local1 = 0; local1 < World.anInt3114; local1++) {
			for (@Pc(6) int local6 = 0; local6 < Static152.anInt3594; local6++) {
				for (@Pc(11) int local11 = 0; local11 < Static99.anInt2550; local11++) {
					@Pc(22) Square local22 = World.levelTiles[local1][local6][local11];
					if (local22 != null) {
						@Pc(27) Wall local27 = local22.wall;
						if (local27 != null && local27.aClass8_5.method4543()) {
							Static69.method1544(local27.aClass8_5, local1, local6, local11, 1, 1);
							if (local27.aClass8_6 != null && local27.aClass8_6.method4543()) {
								Static69.method1544(local27.aClass8_6, local1, local6, local11, 1, 1);
								local27.aClass8_5.method4544(local27.aClass8_6, 0, 0, 0, false);
								local27.aClass8_6 = local27.aClass8_6.method4539();
							}
							local27.aClass8_5 = local27.aClass8_5.method4539();
						}
						for (@Pc(83) int local83 = 0; local83 < local22.spriteCount; local83++) {
							@Pc(92) Sprite local92 = local22.sprites[local83];
							if (local92 != null && local92.model.method4543()) {
								Static69.method1544(local92.model, local1, local6, local11, local92.anInt1713 + 1 - local92.anInt1701, local92.anInt1698 - local92.anInt1696 + 1);
								local92.model = local92.model.method4539();
							}
						}
						@Pc(131) GroundDecor local131 = local22.groundDecor;
						if (local131 != null && local131.aClass8_1.method4543()) {
							Static264.method3574(local131.aClass8_1, local1, local6, local11);
							local131.aClass8_1 = local131.aClass8_1.method4539();
						}
					}
				}
			}
		}
	}
}
