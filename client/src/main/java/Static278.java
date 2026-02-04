import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static278 {

	@OriginalMember(owner = "client!wj", name = "b", descriptor = "Lclient!na;")
	public static final JagString aClass100_1101 = JagString.wrap(" <col=ffff00>");

	@OriginalMember(owner = "client!wj", name = "e", descriptor = "Lclient!na;")
	public static JagString aClass100_1102 = null;

	@OriginalMember(owner = "client!wj", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_1103 = JagString.wrap("ul");

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(Z)V")
	public static void method4648(@OriginalArg(0) boolean arg0) {
		if (arg0) {
			World.levelTiles = Static276.aClass3_Sub5ArrayArrayArray3;
			ClientBuild.groundh = Static80.anIntArrayArrayArray19;
			Static182.aClass3_Sub14ArrayArray2 = Static195.aClass3_Sub14ArrayArray3;
		} else {
			World.levelTiles = Static197.aClass3_Sub5ArrayArrayArray2;
			ClientBuild.groundh = Static107.anIntArrayArrayArray10;
			Static182.aClass3_Sub14ArrayArray2 = Static36.aClass3_Sub14ArrayArray1;
		}
		World.anInt3114 = World.levelTiles.length;
	}

	@OriginalMember(owner = "client!wj", name = "b", descriptor = "(I)V")
	public static void method4649() {
		NPCType.aClass99_18.method3104();
	}

}
