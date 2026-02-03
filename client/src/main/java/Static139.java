import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static139 {

	@OriginalMember(owner = "client!l", name = "j", descriptor = "[Lclient!qf;")
	public static AbstractPix32[] aClass3_Sub2_Sub1Array6;

	@OriginalMember(owner = "client!l", name = "l", descriptor = "[I")
	public static int[] sattot;

	@OriginalMember(owner = "client!l", name = "b", descriptor = "Lclient!n;")
	public static final SoftLruCache aClass99_21 = new SoftLruCache(5);

	@OriginalMember(owner = "client!l", name = "d", descriptor = "Lclient!na;")
	public static final JagString aClass100_659 = JagString.wrap(")4j");

	@OriginalMember(owner = "client!l", name = "f", descriptor = "Lclient!n;")
	public static final SoftLruCache aClass99_22 = new SoftLruCache(4);

	@OriginalMember(owner = "client!l", name = "k", descriptor = "I")
	public static int anInt3451 = 2;

	@OriginalMember(owner = "client!l", name = "a", descriptor = "(III)J")
	public static long method2703(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = Static130.aClass3_Sub5ArrayArrayArray1[arg0][arg1][arg2];
		return local7 == null || local7.aClass24_1 == null ? 0L : local7.aClass24_1.aLong52;
	}

	@OriginalMember(owner = "client!l", name = "b", descriptor = "(I)V")
	public static void method2704() {
		@Pc(7) int local7 = GameShell.anInt4246;
		@Pc(9) int local9 = GameShell.anInt3497;
		@Pc(16) int local16 = GameShell.canvasHei - GameShell.anInt5554 - local7;
		@Pc(23) int local23 = GameShell.canvasWid - local9 - GameShell.anInt1448;
		if (local9 <= 0 && local23 <= 0 && local7 <= 0 && local16 <= 0) {
			return;
		}
		try {
			@Pc(46) Container local46;
			if (GameShell.aFrame2 != null) {
				local46 = GameShell.aFrame2;
			} else if (GameShell.frame == null) {
				local46 = GameShell.signlink.applet;
			} else {
				local46 = GameShell.frame;
			}
			@Pc(59) int local59 = 0;
			@Pc(61) int local61 = 0;
			if (GameShell.frame == local46) {
				@Pc(68) Insets local68 = GameShell.frame.getInsets();
				local61 = local68.left;
				local59 = local68.top;
			}
			@Pc(77) Graphics local77 = local46.getGraphics();
			local77.setColor(Color.black);
			if (local9 > 0) {
				local77.fillRect(local61, local59, local9, GameShell.canvasHei);
			}
			if (local7 > 0) {
				local77.fillRect(local61, local59, GameShell.canvasWid, local7);
			}
			if (local23 > 0) {
				local77.fillRect(local61 + GameShell.canvasWid - local23, local59, local23, GameShell.canvasHei);
			}
			if (local16 > 0) {
				local77.fillRect(local61, local59 + GameShell.canvasHei - local16, GameShell.canvasWid, local16);
			}
		} catch (@Pc(132) Exception local132) {
		}
	}
}
