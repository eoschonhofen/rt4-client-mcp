import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static69 {

	@OriginalMember(owner = "client!fh", name = "U", descriptor = "[[Z")
	public static final boolean[][] aBooleanArrayArray2 = new boolean[][] { new boolean[0], { true, false, true }, { true, false, false, true }, { false, false, true, true }, { true, true, false }, { false, true, true }, { true, false, false, true }, { false, false, false, true, true }, { false, true, true }, { true, false, true, true, true }, { false, true, true, true, true }, { false, true, true, true, true, false } };

	@OriginalMember(owner = "client!fh", name = "ab", descriptor = "Lclient!na;")
	public static final JagString aClass100_443 = JagString.wrap("::noclip");

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(IIII)I")
	public static int method1540(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (arg1 == arg2) {
			return arg1;
		} else {
			@Pc(17) int local17 = 128 - arg0;
			@Pc(50) int local50 = arg0 * (arg2 >>> 7 & 0x1FE01FE) + local17 * (arg1 >>> 7 & 0x1FE01FE) & 0xFF00FF00;
			@Pc(65) int local65 = local17 * (arg1 & 0xFF00FF) + (arg2 & 0xFF00FF) * arg0 & 0xFF00FF00;
			return local50 + (local65 >> 7);
		}
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(BLclient!ek;)V")
	public static void method1545(@OriginalArg(1) SoftwarePix8 arg0) {
		@Pc(5) int local5;
		for (local5 = 0; local5 < Static270.anIntArray562.length; local5++) {
			Static270.anIntArray562[local5] = 0;
		}
		@Pc(36) int local36;
		for (local5 = 0; local5 < 5000; local5++) {
			local36 = (int) ((double) 256 * Math.random() * 128.0D);
			Static270.anIntArray562[local36] = (int) (Math.random() * 284.0D);
		}
		@Pc(66) int local66;
		@Pc(76) int local76;
		for (local5 = 0; local5 < 20; local5++) {
			for (local36 = 1; local36 < 255; local36++) {
				for (local66 = 1; local66 < 127; local66++) {
					local76 = local66 + (local36 << 7);
					Static263.anIntArray516[local76] = (Static270.anIntArray562[local76 + 128] + Static270.anIntArray562[local76 - 1] + Static270.anIntArray562[local76 + 1] + Static270.anIntArray562[local76 + -128]) / 4;
				}
			}
			@Pc(113) int[] local113 = Static270.anIntArray562;
			Static270.anIntArray562 = Static263.anIntArray516;
			Static263.anIntArray516 = local113;
		}
		if (arg0 == null) {
			return;
		}
		local5 = 0;
		for (local36 = 0; local36 < arg0.anInt4278; local36++) {
			for (local66 = 0; local66 < arg0.anInt4270; local66++) {
				if (arg0.aByteArray18[local5++] != 0) {
					local76 = arg0.anInt4280 + local66 + 16;
					@Pc(162) int local162 = arg0.anInt4273 + local36 + 16;
					@Pc(169) int local169 = local76 + (local162 << 7);
					Static270.anIntArray562[local169] = 0;
				}
			}
		}
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method1546(@OriginalArg(0) Packet arg0) {
		for (@Pc(7) int local7 = 0; local7 < Static106.anInt2871; local7++) {
			@Pc(18) int local18 = arg0.method2204();
			@Pc(22) int local22 = arg0.g2();
			if (local22 == 65535) {
				local22 = -1;
			}
			if (Static196.aClass10_Sub1Array2[local18] != null) {
				Static196.aClass10_Sub1Array2[local18].anInt379 = local22;
			}
		}
	}
}
