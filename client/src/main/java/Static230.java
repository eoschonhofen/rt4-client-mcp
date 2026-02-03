import java.io.UnsupportedEncodingException;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static230 {

	@OriginalMember(owner = "client!sj", name = "D", descriptor = "I")
	public static int anInt5158;

	@OriginalMember(owner = "client!sj", name = "p", descriptor = "I")
	public static int mapPrevLoadCount = 1;

	@OriginalMember(owner = "client!sj", name = "q", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array158 = new JagString[100];

	@OriginalMember(owner = "client!sj", name = "t", descriptor = "I")
	public static int anInt5152 = 0;

	@OriginalMember(owner = "client!sj", name = "u", descriptor = "Z")
	public static boolean aBoolean250 = false;

	@OriginalMember(owner = "client!sj", name = "w", descriptor = "Lclient!na;")
	public static final JagString aClass100_978 = JagString.wrap("<)4col>");

	@OriginalMember(owner = "client!sj", name = "x", descriptor = "Lclient!na;")
	public static final JagString aClass100_979 = JagString.wrap("Connexion au serveur de mise -9 jour en cours");

	@OriginalMember(owner = "client!sj", name = "H", descriptor = "I")
	public static int anInt5161 = 0;

	@OriginalMember(owner = "client!sj", name = "c", descriptor = "(I)V")
	public static void method3947() {
		Static250.aClass99_33.method3104();
		Static139.aClass99_21.method3104();
	}

	@OriginalMember(owner = "client!sj", name = "a", descriptor = "(Z)V")
	public static void method3948() {
		try {
			if (Static14.state == 1) {
				@Pc(16) int local16 = Static172.midiPlayer.method4440();
				if (local16 > 0 && Static172.midiPlayer.method4414()) {
					local16 -= Static57.anInt1757;
					if (local16 < 0) {
						local16 = 0;
					}
					Static172.midiPlayer.method4447(local16);
					return;
				}
				Static172.midiPlayer.stop();
				Static172.midiPlayer.method4426();
				Static144.loadingMidiFile = null;
				Static27.loadingWaveCache = null;
				if (Static172.midis == null) {
					Static14.state = 0;
				} else {
					Static14.state = 2;
				}
			}
		} catch (@Pc(62) Exception local62) {
			local62.printStackTrace();
			Static172.midiPlayer.stop();
			Static172.midis = null;
			Static144.loadingMidiFile = null;
			Static14.state = 0;
			Static27.loadingWaveCache = null;
		}
	}

	@OriginalMember(owner = "client!sj", name = "a", descriptor = "(BII)I")
	public static int method3949(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (arg0 == -2) {
			return 12345678;
		} else if (arg0 == -1) {
			if (arg1 < 2) {
				arg1 = 2;
			} else if (arg1 > 126) {
				arg1 = 126;
			}
			return arg1;
		} else {
			arg1 = (arg0 & 0x7F) * arg1 >> 7;
			if (arg1 < 2) {
				arg1 = 2;
			} else if (arg1 > 126) {
				arg1 = 126;
			}
			return (arg0 & 0xFF80) + arg1;
		}
	}

	@OriginalMember(owner = "client!sj", name = "a", descriptor = "(IIBIII)V")
	public static void method3950(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 >= Static172.anInt4164 && arg3 <= Static224.anInt5063 && Static267.anInt5773 <= arg4 && Static106.anInt2869 >= arg2) {
			Static176.method3308(arg2, arg3, arg4, arg0, arg1);
		} else {
			Static163.method3105(arg1, arg3, arg4, arg0, arg2);
		}
	}

	@OriginalMember(owner = "client!sj", name = "a", descriptor = "(Ljava/lang/String;I)Lclient!na;")
	public static JagString method3952(@OriginalArg(0) String arg0) {
		@Pc(14) byte[] local14;
		try {
			local14 = arg0.getBytes("ISO-8859-1");
		} catch (@Pc(16) UnsupportedEncodingException local16) {
			local14 = arg0.getBytes();
		}
		@Pc(23) JagString local23 = new JagString();
		local23.aByteArray52 = local14;
		local23.anInt4030 = 0;
		for (@Pc(31) int local31 = 0; local31 < local14.length; local31++) {
			if (local14[local31] != 0) {
				local14[local23.anInt4030++] = local14[local31];
			}
		}
		return local23;
	}

	@OriginalMember(owner = "client!sj", name = "a", descriptor = "(ILclient!na;)V")
	public static void method3954(@OriginalArg(1) JagString arg0) {
		Static47.aClass100_991 = arg0;
		if (GameShell.signlink.applet == null) {
			return;
		}
		try {
			@Pc(17) JagString local17 = Static272.aClass100_989.method3153(GameShell.signlink.applet);
			@Pc(23) JagString local23 = Static246.aClass100_1029.method3153(GameShell.signlink.applet);
			@Pc(48) JagString local48 = Static34.concatenate(new JagString[] { local17, Static142.aClass100_667, arg0, Static276.aClass100_1095, local23 });
			if (arg0.length() == 0) {
				local48 = Static34.concatenate(new JagString[] { local48, Static245.aClass100_1018 });
			} else {
				local48 = Static34.concatenate(new JagString[] { local48, Static263.aClass100_1082, Static33.method873(MonotonicTime.currentTime() + 94608000000L), Static64.aClass100_431, Static154.method2929(94608000L) });
			}
			Static34.concatenate(new JagString[] { Static221.aClass100_821, local48, Static223.aClass100_946 }).method3134(GameShell.signlink.applet);
		} catch (@Pc(124) Throwable local124) {
		}
	}

	@OriginalMember(owner = "client!sj", name = "c", descriptor = "(II)V")
	public static void method3956(@OriginalArg(0) int arg0) {
		if (Static14.state == 0) {
			Static172.midiPlayer.method4447(arg0);
		} else {
			Static253.anInt5527 = arg0;
		}
	}
}
