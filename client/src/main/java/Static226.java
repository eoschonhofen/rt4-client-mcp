import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static226 {

	@OriginalMember(owner = "client!sf", name = "c", descriptor = "I")
	public static int anInt5080;

	@OriginalMember(owner = "client!sf", name = "h", descriptor = "[Lclient!na;")
	public static final JagString[] varcStr = new JagString[1000];

	@OriginalMember(owner = "client!sf", name = "i", descriptor = "I")
	public static int anInt5084 = 0;

	@OriginalMember(owner = "client!sf", name = "b", descriptor = "(B)V")
	public static void method3901() {
		@Pc(16) int local16 = Static280.aClass3_Sub2_Sub9_43.method2858(Text.aClass100_998);
		@Pc(18) int local18;
		@Pc(27) int local27;
		for (local18 = 0; local18 < Client.menuNumEntries; local18++) {
			local27 = Static280.aClass3_Sub2_Sub9_43.method2858(Static269.method2228(local18));
			if (local27 > local16) {
				local16 = local27;
			}
		}
		local18 = Client.menuNumEntries * 15 + 21;
		@Pc(43) int local43 = Static60.anInt1892;
		local16 += 8;
		local27 = Static155.anInt3751 - local16 / 2;
		if (local43 + local18 > GameShell.anInt5554) {
			local43 = GameShell.anInt5554 - local18;
		}
		if (GameShell.anInt1448 < local27 + local16) {
			local27 = GameShell.anInt1448 - local16;
		}
		if (local27 < 0) {
			local27 = 0;
		}
		if (local43 < 0) {
			local43 = 0;
		}
		if (Static162.anInt3953 == 1) {
			if (Static155.anInt3751 == Static277.anInt5850 && Static280.anInt5895 == Static60.anInt1892) {
				Static13.anInt436 = Client.menuNumEntries * 15 + (Static261.aBoolean298 ? 26 : 22);
				Static162.anInt3953 = 0;
				Static229.anInt5138 = local43;
				Static183.anInt4271 = local27;
				Client.isMenuOpen = true;
				Static24.anInt761 = local16;
			}
		} else if (Static155.anInt3751 == ClientMouseListener.mouseClickX && Static60.anInt1892 == ClientMouseListener.mouseClickY) {
			Static183.anInt4271 = local27;
			Static162.anInt3953 = 0;
			Static24.anInt761 = local16;
			Static229.anInt5138 = local43;
			Static13.anInt436 = (Static261.aBoolean298 ? 26 : 22) + Client.menuNumEntries * 15;
			Client.isMenuOpen = true;
		} else {
			Static280.anInt5895 = ClientMouseListener.mouseClickY;
			Static277.anInt5850 = ClientMouseListener.mouseClickX;
			Static162.anInt3953 = 1;
		}
	}
}
