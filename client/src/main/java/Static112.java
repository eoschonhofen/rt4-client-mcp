import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static112 {

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "Lclient!na;")
	public static final JagString aClass100_574 = JagString.wrap(":duelfriend:");

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "(B)V")
	public static void method2297() {
		@Pc(3) int local3 = Static183.anInt4271;
		@Pc(9) int local9 = Static229.anInt5138;
		@Pc(11) int local11 = Static13.anInt436;
		@Pc(13) int local13 = Static24.anInt761;
		if (Static39.aClass3_Sub2_Sub1_1 == null || Static181.aClass3_Sub2_Sub1_9 == null) {
			if (Client.sprites.method4506(Static55.anInt1736) && Client.sprites.method4506(Static169.anInt4073)) {
				Static39.aClass3_Sub2_Sub1_1 = Static80.depack(Client.sprites, Static55.anInt1736);
				Static181.aClass3_Sub2_Sub1_9 = Static80.depack(Client.sprites, Static169.anInt4073);
				if (GameShell.glRenderer) {
					if (Static39.aClass3_Sub2_Sub1_1 instanceof SoftwareAlphaPix32) {
						Static39.aClass3_Sub2_Sub1_1 = new GlAlphaPix32((Pix32) Static39.aClass3_Sub2_Sub1_1);
					} else {
						Static39.aClass3_Sub2_Sub1_1 = new GlPix32((Pix32) Static39.aClass3_Sub2_Sub1_1);
					}
					if (Static181.aClass3_Sub2_Sub1_9 instanceof SoftwareAlphaPix32) {
						Static181.aClass3_Sub2_Sub1_9 = new GlAlphaPix32((Pix32) Static181.aClass3_Sub2_Sub1_9);
					} else {
						Static181.aClass3_Sub2_Sub1_9 = new GlPix32((Pix32) Static181.aClass3_Sub2_Sub1_9);
					}
				}
			} else if (GameShell.glRenderer) {
				Static46.method1182(local3, local9, local13, 20, Static40.anInt1275, 256 - Static111.anInt2910);
			} else {
				Pix2D.method2484(local3, local9, local13, 20, Static40.anInt1275, 256 - Static111.anInt2910);
			}
		}
		@Pc(112) int local112;
		@Pc(114) int local114;
		if (Static39.aClass3_Sub2_Sub1_1 != null && Static181.aClass3_Sub2_Sub1_9 != null) {
			local112 = local13 / Static39.aClass3_Sub2_Sub1_1.anInt1867;
			for (local114 = 0; local114 < local112; local114++) {
				Static39.aClass3_Sub2_Sub1_1.method1423(local114 * Static39.aClass3_Sub2_Sub1_1.anInt1867 + local3, local9);
			}
			Static181.aClass3_Sub2_Sub1_9.method1423(local3, local9);
			Static181.aClass3_Sub2_Sub1_9.method1421(local3 + local13 - Static181.aClass3_Sub2_Sub1_9.anInt1867, local9);
		}
		Static280.aClass3_Sub2_Sub9_43.method2857(Text.aClass100_998, local3 + 3, local9 + 14, Static195.anInt4581, -1);
		if (GameShell.glRenderer) {
			Static46.method1182(local3, local9 + 20, local13, local11 - 20, Static40.anInt1275, 256 - Static111.anInt2910);
		} else {
			Pix2D.method2484(local3, local9 + 20, local13, local11 - 20, Static40.anInt1275, 256 - Static111.anInt2910);
		}
		local114 = ClientMouseListener.mouseY;
		local112 = ClientMouseListener.mouseX;
		@Pc(203) int local203;
		@Pc(219) int local219;
		for (local203 = 0; local203 < Client.menuNumEntries; local203++) {
			local219 = (Client.menuNumEntries - local203 - 1) * 15 + local9 + 35;
			if (local3 < local112 && local112 < local3 + local13 && local114 > local219 - 13 && local114 < local219 + 3) {
				if (GameShell.glRenderer) {
					Static46.method1182(local3, local219 - 13, local13, 16, Static251.anInt5457, 256 - Static232.anInt5208);
				} else {
					Pix2D.method2484(local3, local219 - 13, local13, 16, Static251.anInt5457, 256 - Static232.anInt5208);
				}
			}
		}
		if ((Static165.aClass3_Sub2_Sub1_8 == null || Static92.aClass3_Sub2_Sub1_6 == null || Static204.aClass3_Sub2_Sub1_10 == null) && Client.sprites.method4506(Static85.anInt2261) && Client.sprites.method4506(Static136.anInt3324) && Client.sprites.method4506(Static254.anInt5556)) {
			Static165.aClass3_Sub2_Sub1_8 = Static80.depack(Client.sprites, Static85.anInt2261);
			Static92.aClass3_Sub2_Sub1_6 = Static80.depack(Client.sprites, Static136.anInt3324);
			Static204.aClass3_Sub2_Sub1_10 = Static80.depack(Client.sprites, Static254.anInt5556);
			if (GameShell.glRenderer) {
				if (Static165.aClass3_Sub2_Sub1_8 instanceof SoftwareAlphaPix32) {
					Static165.aClass3_Sub2_Sub1_8 = new GlAlphaPix32((Pix32) Static165.aClass3_Sub2_Sub1_8);
				} else {
					Static165.aClass3_Sub2_Sub1_8 = new GlPix32((Pix32) Static165.aClass3_Sub2_Sub1_8);
				}
				if (Static92.aClass3_Sub2_Sub1_6 instanceof SoftwareAlphaPix32) {
					Static92.aClass3_Sub2_Sub1_6 = new GlAlphaPix32((Pix32) Static92.aClass3_Sub2_Sub1_6);
				} else {
					Static92.aClass3_Sub2_Sub1_6 = new GlPix32((Pix32) Static92.aClass3_Sub2_Sub1_6);
				}
				if (Static204.aClass3_Sub2_Sub1_10 instanceof SoftwareAlphaPix32) {
					Static204.aClass3_Sub2_Sub1_10 = new GlAlphaPix32((Pix32) Static204.aClass3_Sub2_Sub1_10);
				} else {
					Static204.aClass3_Sub2_Sub1_10 = new GlPix32((Pix32) Static204.aClass3_Sub2_Sub1_10);
				}
			}
		}
		@Pc(418) int local418;
		if (Static165.aClass3_Sub2_Sub1_8 != null && Static92.aClass3_Sub2_Sub1_6 != null && Static204.aClass3_Sub2_Sub1_10 != null) {
			local203 = local13 / Static165.aClass3_Sub2_Sub1_8.anInt1867;
			for (local219 = 0; local219 < local203; local219++) {
				Static165.aClass3_Sub2_Sub1_8.method1423(local3 + Static165.aClass3_Sub2_Sub1_8.anInt1867 * local219, local11 + local9 + -Static165.aClass3_Sub2_Sub1_8.anInt1859);
			}
			local219 = (local11 - 20) / Static92.aClass3_Sub2_Sub1_6.anInt1859;
			for (local418 = 0; local418 < local219; local418++) {
				Static92.aClass3_Sub2_Sub1_6.method1423(local3, local9 + local418 * Static92.aClass3_Sub2_Sub1_6.anInt1859 + 20);
				Static92.aClass3_Sub2_Sub1_6.method1421(local3 + local13 - Static92.aClass3_Sub2_Sub1_6.anInt1867, local9 + 20 + local418 * Static92.aClass3_Sub2_Sub1_6.anInt1859);
			}
			Static204.aClass3_Sub2_Sub1_10.method1423(local3, local11 + local9 - Static204.aClass3_Sub2_Sub1_10.anInt1859);
			Static204.aClass3_Sub2_Sub1_10.method1421(local3 + local13 - Static204.aClass3_Sub2_Sub1_10.anInt1867, local9 - -local11 + -Static204.aClass3_Sub2_Sub1_10.anInt1859);
		}
		for (local203 = 0; local203 < Client.menuNumEntries; local203++) {
			local219 = (Client.menuNumEntries - local203 - 1) * 15 + local9 + 35;
			local418 = Static195.anInt4581;
			if (local3 < local112 && local13 + local3 > local112 && local219 - 13 < local114 && local114 < local219 + 3) {
				local418 = Static262.anInt5752;
			}
			Static280.aClass3_Sub2_Sub9_43.method2857(Static269.method2228(local203), local3 + 3, local219, local418, 0);
		}
		Client.method2407(Static183.anInt4271, Static229.anInt5138, Static13.anInt436, Static24.anInt761);
	}

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "(Lclient!km;I)I")
	public static int method2299(@OriginalArg(0) ClientNPC arg0) {
		@Pc(13) NPCType local13 = arg0.aClass96_1;
		if (local13.anIntArray357 != null) {
			local13 = local13.method2932();
			if (local13 == null) {
				return -1;
			}
		}
		@Pc(29) int local29 = local13.anInt3747;
		@Pc(33) BasType local33 = arg0.method2681();
		if (local33.anInt1037 == arg0.anInt3366) {
			local29 = local13.anInt3728;
		} else if (arg0.anInt3366 == local33.anInt1058 || local33.anInt1054 == arg0.anInt3366 || arg0.anInt3366 == local33.anInt1045 || arg0.anInt3366 == local33.anInt1043) {
			local29 = local13.anInt3734;
		} else if (local33.anInt1062 == arg0.anInt3366 || arg0.anInt3366 == local33.anInt1042 || arg0.anInt3366 == local33.anInt1048 || arg0.anInt3366 == local33.anInt1066) {
			local29 = local13.anInt3745;
		}
		return local29;
	}
}
