import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static16 {

	@OriginalMember(owner = "client!bf", name = "C", descriptor = "[I")
	public static final int[] anIntArray51 = new int[] { 2, 2, 4, 2, 1, 8, 4, 1, 4, 4, 2, 1, 1, 1, 4, 1 };

	@OriginalMember(owner = "client!bf", name = "I", descriptor = "[I")
	public static final int[] anIntArray52 = new int[] { 16776960, 16711680, 65280, 65535, 16711935, 16777215 };

	@OriginalMember(owner = "client!bf", name = "N", descriptor = "Lclient!na;")
	public static final JagString aClass100_95 = Text.aClass100_92;

	@OriginalMember(owner = "client!bf", name = "c", descriptor = "(I)V")
	public static void method501() {
		if (!GameShell.glRenderer || Static231.aBoolean252) {
			return;
		}
		@Pc(14) Square[][][] local14 = World.levelTiles;
		for (@Pc(22) int local22 = 0; local22 < local14.length; local22++) {
			@Pc(30) Square[][] local30 = local14[local22];
			for (@Pc(32) int local32 = 0; local32 < local30.length; local32++) {
				for (@Pc(42) int local42 = 0; local42 < local30[local32].length; local42++) {
					@Pc(54) Square local54 = local30[local32][local42];
					if (local54 != null) {
						@Pc(71) GlModelLit local71;
						if (local54.groundDecor != null && local54.groundDecor.aClass8_1 instanceof GlModelLit) {
							local71 = (GlModelLit) local54.groundDecor.aClass8_1;
							if ((local54.groundDecor.aLong26 & Long.MIN_VALUE) == 0L) {
								local71.method4111(false, true, true, false, true, true);
							} else {
								local71.method4111(true, true, true, true, true, true);
							}
						}
						if (local54.decor != null) {
							if (local54.decor.aClass8_3 instanceof GlModelLit) {
								local71 = (GlModelLit) local54.decor.aClass8_3;
								if ((local54.decor.aLong52 & Long.MIN_VALUE) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
							if (local54.decor.aClass8_2 instanceof GlModelLit) {
								local71 = (GlModelLit) local54.decor.aClass8_2;
								if ((Long.MIN_VALUE & local54.decor.aLong52) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
						}
						if (local54.wall != null) {
							if (local54.wall.aClass8_5 instanceof GlModelLit) {
								local71 = (GlModelLit) local54.wall.aClass8_5;
								if ((local54.wall.aLong107 & Long.MIN_VALUE) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
							if (local54.wall.aClass8_6 instanceof GlModelLit) {
								local71 = (GlModelLit) local54.wall.aClass8_6;
								if ((Long.MIN_VALUE & local54.wall.aLong107) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
						}
						for (@Pc(270) int local270 = 0; local270 < local54.spriteCount; local270++) {
							if (local54.sprites[local270].model instanceof GlModelLit) {
								@Pc(293) GlModelLit local293 = (GlModelLit) local54.sprites[local270].model;
								if ((Long.MIN_VALUE & local54.sprites[local270].typecode) == 0L) {
									local293.method4111(false, true, true, false, true, true);
								} else {
									local293.method4111(true, true, true, true, true, true);
								}
							}
						}
					}
				}
			}
		}
		Static231.aBoolean252 = true;
	}
}
