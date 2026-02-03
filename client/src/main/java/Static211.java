import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static211 {

	@OriginalMember(owner = "client!rc", name = "R", descriptor = "Z")
	private static boolean aBoolean74;

	@OriginalMember(owner = "client!rc", name = "p", descriptor = "I")
	public static int anInt1142 = 0;

	@OriginalMember(owner = "client!rc", name = "v", descriptor = "Lclient!na;")
	private static final JagString aClass100_228 = JagString.wrap(" from your ignore list first)3");

	@OriginalMember(owner = "client!rc", name = "s", descriptor = "Lclient!na;")
	public static JagString aClass100_227 = aClass100_228;

	@OriginalMember(owner = "client!rc", name = "D", descriptor = "Lclient!na;")
	public static final JagString aClass100_229 = JagString.wrap(" s(West d-Bconnect-B)3");

	@OriginalMember(owner = "client!rc", name = "G", descriptor = "Lclient!na;")
	public static final JagString aClass100_230 = JagString.wrap("");

	@OriginalMember(owner = "client!rc", name = "I", descriptor = "Lclient!na;")
	public static final JagString aClass100_231 = JagString.wrap(")3)3)3");

	@OriginalMember(owner = "client!rc", name = "K", descriptor = "Lclient!na;")
	public static final JagString aClass100_232 = JagString.wrap("::rect_debug");

	@OriginalMember(owner = "client!rc", name = "M", descriptor = "Z")
	public static boolean aBoolean73 = false;

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(Lclient!na;Z)Lclient!na;")
	public static JagString method923(@OriginalArg(0) JagString arg0) {
		@Pc(12) int local12 = Static171.method3218(arg0);
		return local12 == -1 ? Static93.aClass100_517 : Static203.aClass134_1.aClass100Array153[local12].method3140(Static101.aClass100_538, Static197.aClass100_872);
	}

	@OriginalMember(owner = "client!rc", name = "a", descriptor = "(Z)V")
	public static void method924() {
		Static244.aClass99_32.method3104();
	}

	@OriginalMember(owner = "client!rc", name = "d", descriptor = "(I)V")
	public static void method930() {
		if (client.midiPcmPlayer != null) {
			client.midiPcmPlayer.shutdown();
		}
		if (client.soundPcmPlayer != null) {
			client.soundPcmPlayer.shutdown();
		}
		Static41.init(client.lowMem);
		client.midiPcmPlayer = Static107.getPlayer(22050, GameShell.signlink, GameShell.canvas, 0);
		client.midiPcmPlayer.playStream(client.midiPlayer);
		client.soundPcmPlayer = Static107.getPlayer(2048, GameShell.signlink, GameShell.canvas, 1);
		client.soundPcmPlayer.playStream(client.soundMixer);
	}
}
