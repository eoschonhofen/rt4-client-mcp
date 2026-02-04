import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.IOException;
import java.net.Socket;
import java.util.GregorianCalendar;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!client")
public final class Client extends GameShell {

	@OriginalMember(owner = "client!ca", name = "eb", descriptor = "[I")
	public static final int[] anIntArray67 = new int[100];

	@OriginalMember(owner = "client!mc", name = "Y", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array112 = new JagString[100];

	@OriginalMember(owner = "client!sj", name = "q", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array158 = new JagString[100];

	@OriginalMember(owner = "client!fb", name = "l", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array62 = new JagString[100];

	@OriginalMember(owner = "client!th", name = "l", descriptor = "[I")
	public static final int[] anIntArray521 = new int[100];

	@OriginalMember(owner = "client!li", name = "h", descriptor = "[Lclient!mj;")
	public static final CollisionMap[] levelCollisionMap = new CollisionMap[4];

	@OriginalMember(owner = "client!eg", name = "e", descriptor = "Lclient!i;")
	public static final PacketBit in = new PacketBit(65536);

	@OriginalMember(owner = "client!dm", name = "n", descriptor = "Lclient!na;")
	public static final JagString AUTO_PERCENT = JagString.wrap("(U");

	@OriginalMember(owner = "client!tl", name = "d", descriptor = "I")
	public static int state = 0;

	@OriginalMember(owner = "client!nc", name = "j", descriptor = "I")
	public static int loadingStep = 0;

	@OriginalMember(owner = "client!fk", name = "q", descriptor = "Lclient!uc;")
	public static MouseWheelInterface mouseWheel;

	@OriginalMember(owner = "client!eh", name = "j", descriptor = "I")
	public static int mouseWheelRotation = 0;

	@OriginalMember(owner = "client!pb", name = "Q", descriptor = "I")
	public static int worldid = 1;

	@OriginalMember(owner = "client!gj", name = "b", descriptor = "I")
	public static int modewhere = 0;

	@OriginalMember(owner = "client!gg", name = "U", descriptor = "I")
	public static int modewhat = 0;

	@OriginalMember(owner = "client!ud", name = "S", descriptor = "Z")
	public static boolean advertsuppressed = false;

	@OriginalMember(owner = "client!lb", name = "v", descriptor = "I")
	public static int lang = 0;

	@OriginalMember(owner = "client!t", name = "x", descriptor = "Z")
	public static boolean objecttag = false;

	@OriginalMember(owner = "client!lk", name = "U", descriptor = "Z")
	public static boolean js = false;

	@OriginalMember(owner = "client!vk", name = "n", descriptor = "I")
	public static int game = 0;

	@OriginalMember(owner = "client!wk", name = "w", descriptor = "I")
	public static int country;

	@OriginalMember(owner = "client!od", name = "n", descriptor = "Z")
	public static boolean haveie6 = false;

	@OriginalMember(owner = "client!qi", name = "r", descriptor = "I")
	public static int affid = 0;

	@OriginalMember(owner = "client!re", name = "B", descriptor = "Lclient!ve;")
	public static Js5 anims;

	@OriginalMember(owner = "client!ud", name = "J", descriptor = "Lclient!ve;")
	public static Js5 bases;

	@OriginalMember(owner = "client!wf", name = "g", descriptor = "Lclient!ve;")
	public static Js5 config;

	@OriginalMember(owner = "client!dc", name = "z", descriptor = "Lclient!ve;")
	public static Js5 interfaces;

	@OriginalMember(owner = "client!uc", name = "c", descriptor = "Lclient!ve;")
	public static Js5 jagFX;

	@OriginalMember(owner = "client!ca", name = "Z", descriptor = "Lclient!ve;")
	public static Js5 maps;

	@OriginalMember(owner = "client!kc", name = "w", descriptor = "Lclient!ve;")
	public static Js5 songs;

	@OriginalMember(owner = "client!vl", name = "g", descriptor = "Lclient!ve;")
	public static Js5 models;

	@OriginalMember(owner = "client!ra", name = "K", descriptor = "Lclient!ve;")
	public static Js5 sprites;

	@OriginalMember(owner = "client!pk", name = "Z", descriptor = "Lclient!ve;")
	public static Js5 textures;

	@OriginalMember(owner = "client!ol", name = "U", descriptor = "Lclient!ve;")
	public static Js5 binary;

	@OriginalMember(owner = "client!rg", name = "z", descriptor = "Lclient!ve;")
	public static Js5 jingles;

	@OriginalMember(owner = "client!bf", name = "s", descriptor = "Lclient!ve;")
	public static Js5 scripts;

	@OriginalMember(owner = "client!ve", name = "l", descriptor = "Lclient!ve;")
	public static Js5 fontMetrics;

	@OriginalMember(owner = "client!kl", name = "r", descriptor = "Lclient!ve;")
	public static Js5 vorbis;

	@OriginalMember(owner = "client!km", name = "Oc", descriptor = "Lclient!ve;")
	public static Js5 patches;

	@OriginalMember(owner = "client!wl", name = "s", descriptor = "Lclient!ve;")
	public static Js5 locConfig;

	@OriginalMember(owner = "client!km", name = "Nc", descriptor = "Lclient!ve;")
	public static Js5 enumConfig;

	@OriginalMember(owner = "client!nj", name = "l", descriptor = "Lclient!ve;")
	public static Js5 npcConfig;

	@OriginalMember(owner = "client!ni", name = "k", descriptor = "Lclient!ve;")
	public static Js5 objConfig;

	@OriginalMember(owner = "client!ui", name = "cb", descriptor = "Lclient!ve;")
	public static Js5 seqConfig;

	@OriginalMember(owner = "client!jh", name = "p", descriptor = "Lclient!ve;")
	public static Js5 spotConfig;

	@OriginalMember(owner = "client!mf", name = "W", descriptor = "Lclient!ve;")
	public static Js5 varbitConfig;

	@OriginalMember(owner = "client!sg", name = "k", descriptor = "Lclient!ve;")
	public static Js5 worldmap;

	@OriginalMember(owner = "client!uj", name = "J", descriptor = "Lclient!ve;")
	public static Js5 quickchat;

	@OriginalMember(owner = "client!cd", name = "B", descriptor = "Lclient!ve;")
	public static Js5 quickchatGlobal;

	@OriginalMember(owner = "client!nd", name = "t", descriptor = "Lclient!ve;")
	public static Js5 materials;

	@OriginalMember(owner = "client!sf", name = "b", descriptor = "Lclient!ve;")
	public static Js5 particleConfig;

	@OriginalMember(owner = "client!od", name = "f", descriptor = "Lclient!jd;")
	public static MouseTracking mouseTracking;

	@OriginalMember(owner = "client!hk", name = "eb", descriptor = "Z")
	public static boolean lowMem = true;

	@OriginalMember(owner = "client!li", name = "v", descriptor = "Lclient!va;")
	public static MidiPlayer midiPlayer;

	@OriginalMember(owner = "client!ba", name = "D", descriptor = "Lclient!vh;")
	public static PcmPlayer midiPcmPlayer;

	@OriginalMember(owner = "client!lh", name = "s", descriptor = "Lclient!vh;")
	public static PcmPlayer soundPcmPlayer;

	@OriginalMember(owner = "client!qi", name = "C", descriptor = "Lclient!ei;")
	public static Mixer soundMixer;

	@OriginalMember(owner = "client!ef", name = "p", descriptor = "Lclient!vj;")
	public static Decimator soundDecimator;

	@OriginalMember(owner = "client!ac", name = "c", descriptor = "I")
	public static int js5ConnectState = 0;

	@OriginalMember(owner = "client!cm", name = "f", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest js5SocketReq;

	@OriginalMember(owner = "client!qk", name = "g", descriptor = "Lclient!ma;")
	public static ClientStream js5Stream;

	@OriginalMember(owner = "client!rj", name = "Y", descriptor = "J")
	public static long js5ConnectTime;

	@OriginalMember(owner = "client!bl", name = "P", descriptor = "I")
	public static int js5ConnectCooldown = 0;

	@OriginalMember(owner = "client!f", name = "Z", descriptor = "I")
	public static int anInt1941 = 0;

	@OriginalMember(owner = "client!dm", name = "u", descriptor = "I")
	public static int anInt1464 = 0;

	@OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
	public static int anInt3028 = 1;

	@OriginalMember(owner = "client!v", name = "f", descriptor = "Lclient!al;")
	public static Js5Loader js5Loader;

	@OriginalMember(owner = "client!ld", name = "k", descriptor = "Ljava/lang/String;")
	public static String loginHost;

	@OriginalMember(owner = "client!hi", name = "g", descriptor = "I")
	public static int loginJs5Port;

	@OriginalMember(owner = "client!ud", name = "K", descriptor = "I")
	public static int loginGamePort;

	@OriginalMember(owner = "client!ee", name = "g", descriptor = "I")
	public static int anInt1738;

	@OriginalMember(owner = "client!wc", name = "c", descriptor = "I")
	public static int anInt5800;

	@OriginalMember(owner = "client!em", name = "v", descriptor = "Ljava/lang/String;")
	public static String aString1;

	@OriginalMember(owner = "client!r", name = "f", descriptor = "I")
	public static int anInt4784;

	@OriginalMember(owner = "client!vc", name = "db", descriptor = "[S")
	public static short[] aShortArray88;

	@OriginalMember(owner = "client!ra", name = "s", descriptor = "I")
	public static int anInt4794;

	@OriginalMember(owner = "client!jl", name = "H", descriptor = "I")
	public static int anInt3103 = -1;

	@OriginalMember(owner = "client!jk", name = "B", descriptor = "Lclient!ma;")
	public static ClientStream stream;

	@OriginalMember(owner = "client!na", name = "l", descriptor = "I")
	public static int ptype = 0;

	@OriginalMember(owner = "client!sc", name = "o", descriptor = "I")
	public static int psize = 0;

	@OriginalMember(owner = "client!dm", name = "q", descriptor = "I")
	public static int ptype2 = 0;

	@OriginalMember(owner = "client!af", name = "k", descriptor = "I")
	public static int ptype1 = 0;

	@OriginalMember(owner = "client!sj", name = "t", descriptor = "I")
	public static int ptype0 = 0;

	@OriginalMember(owner = "client!qf", name = "M", descriptor = "I")
	public static int timeoutTimer = 0;

	@OriginalMember(owner = "client!client", name = "main", descriptor = "([Ljava/lang/String;)V")
	public static void main(@OriginalArg(0) String[] args) {
		try {
			if (args.length != 4) {
				args = new String[] { "1", "live", "english", "game0" };
				// Static131.method2577("argument count");
			}

			@Pc(15) int lang = -1;

			worldid = Integer.parseInt(args[0]);
			modewhere = 2;

			if (args[1].equals("live")) {
				modewhat = 0;
			} else if (args[1].equals("rc")) {
				modewhat = 1;
			} else if (args[1].equals("wip")) {
				modewhat = 2;
			} else {
				Static131.method2577("modewhat");
			}

			advertsuppressed = false;

			try {
				@Pc(63) byte[] local63 = args[2].getBytes("ISO-8859-1");
				lang = Static101.method2053(Static10.method346(local63, local63.length, 0));
			} catch (@Pc(74) Exception local74) {
			}

			if (lang != -1) {
				Client.lang = lang;
			} else if (args[2].equals("english")) {
				Client.lang = 0;
			} else if (args[2].equals("german")) {
				Client.lang = 1;
			} else {
				Static131.method2577("language");
			}
			Static3.setLang(Client.lang);

			objecttag = false;
			js = false;

			if (args[3].equals("game0")) {
				game = 0;
			} else if (args[3].equals("game1")) {
				game = 1;
			} else {
				Static131.method2577("game");
			}

			country = 0;
			haveie6 = false;
			affid = 0;

			Static47.aClass100_991 = Static186.AUTO_EMPTY;

			@Pc(146) Client app = new Client();
			Static215.client = app;
			app.method936(modewhat + 32, "runescape");
			GameShell.frame.setLocation(40, 40);
		} catch (@Pc(167) Exception local167) {
			JagException.report(null, local167);
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(ZZZIZ)Lclient!ve;")
	public static Js5 openJs5(@OriginalArg(0) boolean arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3) {
		@Pc(7) DataFile local7 = null;
		if (cacheDat != null) {
			local7 = new DataFile(arg3, cacheDat, GameShell.cacheIndex[arg3], 1000000);
		}

		Static269.js5Providers[arg3] = js5Loader.method180(arg3, Static148.aClass49_4, local7);

		if (arg1) {
			Static269.js5Providers[arg3].method528();
		}

		return new Js5(Static269.js5Providers[arg3], arg0, arg2);
	}

	@OriginalMember(owner = "client!id", name = "b", descriptor = "(I)V")
	public static void doAudio() {
		if (soundPcmPlayer != null) {
			soundPcmPlayer.cycle();
		}

		if (midiPcmPlayer != null) {
			midiPcmPlayer.cycle();
		}
	}

	@OriginalMember(owner = "client!ac", name = "a", descriptor = "(B)Z")
	public static boolean tcpIn() throws IOException {
		if (stream == null) {
			return false;
		}

		@Pc(14) int local14 = stream.available();
		if (local14 == 0) {
			return false;
		}

		if (ptype == -1) {
			local14--;
			stream.read(0, 1, in.data);
			in.pos = 0;
			ptype = in.g1Enc();
			psize = Static234.SERVERPROT_SIZES[ptype];
		}

		if (psize == -1) {
			if (local14 <= 0) {
				return false;
			}

			stream.read(0, 1, in.data);
			local14--;
			psize = in.data[0] & 0xFF;
		}

		if (psize == -2) {
			if (local14 <= 1) {
				return false;
			}

			local14 -= 2;
			stream.read(0, 2, in.data);
			in.pos = 0;
			psize = in.g2();
		}

		if (psize > local14) {
			return false;
		}

		in.pos = 0;
		stream.read(0, psize, in.data);

		ptype2 = ptype1;
		ptype1 = ptype0;
		ptype0 = ptype;
		timeoutTimer = 0;

		@Pc(133) int local133;
		if (ptype == 60) {
			local133 = in.method2184();
			@Pc(137) byte local137 = in.method2189();
			Static170.method2575(local137, local133);
			ptype = -1;
			return true;
		}
		@Pc(171) int local171;
		@Pc(156) JagString local156;
		if (ptype == 115) {
			local133 = in.g2();
			local156 = in.gjstr();
			@Pc(163) Object[] local163 = new Object[local156.length() + 1];
			for (local171 = local156.length() - 1; local171 >= 0; local171--) {
				if (local156.method3149(local171) == 115) {
					local163[local171 + 1] = in.gjstr();
				} else {
					local163[local171 + 1] = Integer.valueOf(in.g4());
				}
			}
			local163[0] = Integer.valueOf(in.g4());
			if (Static248.method3288(local133)) {
				@Pc(226) HookReq local226 = new HookReq();
				local226.onop = local163;
				Static82.method1767(local226);
			}
			ptype = -1;
			return true;
		}
		@Pc(275) long local275;
		@Pc(262) boolean local262;
		@Pc(277) int local277;
		@Pc(506) JagString local506;
		if (ptype == 70) {
			@Pc(245) JagString local245 = in.gjstr();
			if (local245.method3130(Static196.aClass100_863)) {
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				local262 = false;
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					addChat(local156, 4, Text.aClass100_367);
				}
			} else if (local245.method3130(Static61.aClass100_423)) {
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				local262 = false;
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					local506 = local245.method3137(local245.length() - 9, local245.method3131(Static264.aClass100_875) + 1);
					addChat(local156, 8, local506);
				}
			} else if (local245.method3130(Static191.aClass100_845)) {
				local262 = false;
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (local275 == Static190.aLongArray6[local277]) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					addChat(local156, 10, Static186.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static141.aClass100_664)) {
				local156 = local245.method3137(local245.method3131(Static141.aClass100_664), 0);
				addChat(Static186.AUTO_EMPTY, 11, local156);
			} else if (local245.method3130(Static138.aClass100_654)) {
				local156 = local245.method3137(local245.method3131(Static138.aClass100_654), 0);
				if (Static11.anInt384 == 0) {
					addChat(Static186.AUTO_EMPTY, 12, local156);
				}
			} else if (local245.method3130(Static244.aClass100_1014)) {
				local156 = local245.method3137(local245.method3131(Static244.aClass100_1014), 0);
				if (Static11.anInt384 == 0) {
					addChat(Static186.AUTO_EMPTY, 13, local156);
				}
			} else if (local245.method3130(Static56.aClass100_379)) {
				local262 = false;
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (local275 == Static190.aLongArray6[local277]) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					addChat(local156, 14, Static186.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static112.aClass100_574)) {
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local262 = false;
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					addChat(local156, 15, Static186.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static217.aClass100_916)) {
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				local262 = false;
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (local275 == Static190.aLongArray6[local277]) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					addChat(local156, 16, Static186.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static164.aClass100_770)) {
				local156 = local245.method3137(local245.method3131(Static264.aClass100_875), 0);
				local262 = false;
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && Static11.anInt384 == 0) {
					local506 = local245.method3137(local245.length() - 9, local245.method3131(Static264.aClass100_875) + 1);
					addChat(local156, 21, local506);
				}
			} else {
				addChat(Static186.AUTO_EMPTY, 0, local245);
			}
			ptype = -1;
			return true;
		}
		@Pc(786) int local786;
		@Pc(790) JagString local790;
		if (ptype == 123) {
			local133 = in.method2192();
			local786 = in.method2184();
			local790 = in.gjstr();
			if (Static248.method3288(local786)) {
				Static193.method3498(local790, local133);
			}
			ptype = -1;
			return true;
		} else if (ptype == 230) {
			Static180.anInt4264 = in.method2177();
			Static115.anInt2940 = in.method2180();
			while (psize > in.pos) {
				ptype = in.g1();
				Static75.method1634();
			}
			ptype = -1;
			return true;
		} else if (ptype == 153) {
			ptype = -1;
			Static115.anInt2939 = 0;
			return true;
		} else {
			@Pc(864) int local864;
			if (ptype == 220) {
				local133 = in.method2224();
				local786 = in.method2192();
				local864 = in.g2();
				if (Static248.method3288(local864)) {
					Static229.method3938(local786, local133);
				}
				ptype = -1;
				return true;
			}
			@Pc(884) long local884;
			@Pc(908) int local908;
			@Pc(916) int local916;
			@Pc(899) long local899;
			@Pc(904) long local904;
			if (ptype == 81) {
				local884 = in.g8();
				in.g1b();
				local275 = in.g8();
				local899 = in.g2();
				local904 = in.g3();
				local908 = in.g1();
				@Pc(910) boolean local910 = false;
				local916 = in.g2();
				@Pc(922) long local922 = (local899 << 32) + local904;
				@Pc(924) int local924 = 0;
				label1320: while (true) {
					if (local924 < 100) {
						if (local922 != Static233.aLongArray9[local924]) {
							local924++;
							continue;
						}
						local910 = true;
						break;
					}
					if (local908 <= 1) {
						for (local924 = 0; local924 < Static35.anInt1093; local924++) {
							if (Static190.aLongArray6[local924] == local884) {
								local910 = true;
								break label1320;
							}
						}
					}
					break;
				}
				if (!local910 && Static11.anInt384 == 0) {
					Static233.aLongArray9[Static251.anInt5447] = local922;
					Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
					@Pc(999) JagString local999 = QuickChatPhraseType.list(local916).method770(in);
					if (local908 == 2 || local908 == 3) {
						method2928(local916, 20, local999, Static79.toBaseDisplayName(local275).method3125(), JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }));
					} else if (local908 == 1) {
						method2928(local916, 20, local999, Static79.toBaseDisplayName(local275).method3125(), JagString.join(new JagString[] { Static65.aClass100_435, Static79.toBaseDisplayName(local884).method3125() }));
					} else {
						method2928(local916, 20, local999, Static79.toBaseDisplayName(local275).method3125(), Static79.toBaseDisplayName(local884).method3125());
					}
				}
				ptype = -1;
				return true;
			}
			@Pc(1146) int local1146;
			@Pc(1160) int local1160;
			@Pc(1245) boolean local1245;
			if (ptype == 55) {
				Static278.anInt5867 = anInt3028;
				local884 = in.g8();
				if (local884 == 0L) {
					Static270.aClass100_1094 = null;
					ptype = -1;
					Static15.aClass100_87 = null;
					Static199.aClass3_Sub22Array1 = null;
					Static214.anInt5577 = 0;
					return true;
				}
				local275 = in.g8();
				Static15.aClass100_87 = Static79.toBaseDisplayName(local275);
				Static270.aClass100_1094 = Static79.toBaseDisplayName(local884);
				Static50.aByte6 = in.g1b();
				local1146 = in.g1();
				if (local1146 == 255) {
					ptype = -1;
					return true;
				}
				Static214.anInt5577 = local1146;
				@Pc(1158) FriendChatUser[] local1158 = new FriendChatUser[100];
				for (local1160 = 0; local1160 < Static214.anInt5577; local1160++) {
					local1158[local1160] = new FriendChatUser();
					local1158[local1160].key = in.g8();
					local1158[local1160].displayName = Static79.toBaseDisplayName(local1158[local1160].key);
					local1158[local1160].world = in.g2();
					local1158[local1160].rank = in.g1b();
					local1158[local1160].aClass100_635 = in.gjstr();
					if (Static101.aLong98 == local1158[local1160].key) {
						Static160.aByte14 = local1158[local1160].rank;
					}
				}
				local908 = Static214.anInt5577;
				while (local908 > 0) {
					local1245 = true;
					local908--;
					for (local916 = 0; local916 < local908; local916++) {
						if (local1158[local916].displayName.method3139(local1158[local916 + 1].displayName) > 0) {
							local1245 = false;
							@Pc(1279) FriendChatUser local1279 = local1158[local916];
							local1158[local916] = local1158[local916 + 1];
							local1158[local916 + 1] = local1279;
						}
					}
					if (local1245) {
						break;
					}
				}
				Static199.aClass3_Sub22Array1 = local1158;
				ptype = -1;
				return true;
			} else if (ptype == 164) {
				local133 = in.method2206();
				Static232.aClass212_5 = signlink.method5128(local133);
				ptype = -1;
				return true;
			} else if (ptype == 225) {
				Static64.method1495();
				ptype = -1;
				return true;
			} else if (ptype == 48) {
				local133 = in.g2();
				local156 = in.gjstr();
				local864 = in.method2207();
				if (Static248.method3288(local133)) {
					Static193.method3498(local156, local864);
				}
				ptype = -1;
				return true;
			} else if (ptype == 232) {
				Static59.anInt1812 = in.g1();
				Static49.anInt1459 = in.g1();
				Static84.anInt2256 = in.g1();
				ptype = -1;
				return true;
			} else {
				@Pc(1409) JagString local1409;
				if (ptype == 44) {
					local133 = in.method2207();
					if (local133 == 65535) {
						local133 = -1;
					}
					local786 = in.g1();
					local864 = in.g1();
					local1409 = in.gjstr();
					if (local864 >= 1 && local864 <= 8) {
						if (local1409.method3111(Static92.aClass100_510)) {
							local1409 = null;
						}
						Static160.aClass100Array121[local864 - 1] = local1409;
						Static191.anIntArray388[local864 - 1] = local133;
						Static1.aBooleanArray1[local864 - 1] = local786 == 0;
					}
					ptype = -1;
					return true;
				} else if (ptype == 226) {
					local133 = in.g4();
					local786 = in.method2184();
					Static170.method2575(local133, local786);
					ptype = -1;
					return true;
				} else if (ptype == 21) {
					local133 = in.method2212();
					local786 = in.g2();
					local864 = in.method2208();
					if (Static248.method3288(local786)) {
						Static153.method2905(local864, local133);
					}
					ptype = -1;
					return true;
				} else if (ptype == 145) {
					local133 = in.method2207();
					local786 = in.method2177();
					local864 = in.method2207();
					if (Static248.method3288(local864)) {
						if (local786 == 2) {
							Static5.method34();
						}
						Static154.anInt3711 = local133;
						Static81.method1753(local133);
						Static210.method3712(false);
						Static74.method1626(Static154.anInt3711);
						for (local171 = 0; local171 < 100; local171++) {
							Static186.aBooleanArray100[local171] = true;
						}
					}
					ptype = -1;
					return true;
				} else if (ptype == 69) {
					local133 = in.method2207();
					local786 = in.g4();
					local864 = in.method2184();
					if (Static248.method3288(local133)) {
						Static132.method2606(local864, local786);
					}
					ptype = -1;
					return true;
				} else if (ptype == 141) {
					local884 = in.g8();
					local864 = in.g2();
					local1409 = QuickChatPhraseType.list(local864).method770(in);
					method2928(local864, 19, local1409, null, Static79.toBaseDisplayName(local884).method3125());
					ptype = -1;
					return true;
				} else if (ptype == 169) {
					Static271.method4598(in);
					ptype = -1;
					return true;
				} else if (ptype == 89) {
					Static8.method121();
					Static103.method2245();
					Static70.anInt2015 += 32;
					ptype = -1;
					return true;
				} else if (ptype == 125) {
					local133 = in.g2();
					local786 = in.g1();
					local864 = in.g1();
					local171 = in.g2();
					local1146 = in.g1();
					local277 = in.g1();
					if (Static248.method3288(local133)) {
						Static260.method3849(local171, local864, local1146, local786, local277);
					}
					ptype = -1;
					return true;
				} else if (ptype == 36) {
					local133 = in.method2224();
					local786 = in.method2217();
					local864 = in.method2184();
					if (Static248.method3288(local864)) {
						Static225.method3893(local133, local786);
					}
					ptype = -1;
					return true;
				} else {
					@Pc(1814) ServerActive local1814;
					@Pc(1804) ServerActive local1804;
					if (ptype == 9) {
						local133 = in.method2207();
						local786 = in.method2208();
						local864 = in.method2184();
						local171 = in.method2192();
						if (local171 == 65535) {
							local171 = -1;
						}
						local1146 = in.method2184();
						if (local1146 == 65535) {
							local1146 = -1;
						}
						if (Static248.method3288(local864)) {
							for (local277 = local1146; local277 <= local171; local277++) {
								local904 = (long) local277 + ((long) local786 << 32);
								local1804 = (ServerActive) Static210.aClass133_21.find(local904);
								if (local1804 != null) {
									local1814 = new ServerActive(local1804.eventCode, local133);
									local1804.unlink();
								} else if (local277 == -1) {
									local1814 = new ServerActive(Static5.method32(local786).active.eventCode, local133);
								} else {
									local1814 = new ServerActive(0, local133);
								}
								Static210.aClass133_21.put(local1814, local904);
							}
						}
						ptype = -1;
						return true;
					}
					@Pc(1986) int local1986;
					if (ptype == 56) {
						local133 = in.g2();
						local786 = in.method2192();
						local864 = in.method2206();
						local171 = in.method2207();
						if (local864 >> 30 == 0) {
							@Pc(1994) SeqType local1994;
							if (local864 >> 29 != 0) {
								local1146 = local864 & 0xFFFF;
								@Pc(1894) ClientNPC local1894 = Static175.aClass8_Sub4_Sub2Array1[local1146];
								if (local1894 != null) {
									if (local171 == 65535) {
										local171 = -1;
									}
									local1245 = true;
									if (local171 != -1 && local1894.anInt3432 != -1 && SeqType.list(SpotType.list(local171).anim).anInt5355 < SeqType.list(SpotType.list(local1894.anInt3432).anim).anInt5355) {
										local1245 = false;
									}
									if (local1245) {
										local1894.anInt3361 = 0;
										local1894.anInt3432 = local171;
										local1894.anInt3359 = Static83.anInt372 + local133;
										local1894.anInt3399 = 0;
										if (local1894.anInt3359 > Static83.anInt372) {
											local1894.anInt3399 = -1;
										}
										local1894.anInt3394 = local786;
										local1894.anInt3418 = 1;
										if (local1894.anInt3432 != -1 && Static83.anInt372 == local1894.anInt3359) {
											local1986 = SpotType.list(local1894.anInt3432).anim;
											if (local1986 != -1) {
												local1994 = SeqType.list(local1986);
												if (local1994 != null && local1994.frames != null) {
													Static152.method2836(local1894.anInt3421, local1994, local1894.anInt3412, false, 0);
												}
											}
										}
									}
								}
							} else if (local864 >> 28 != 0) {
								local1146 = local864 & 0xFFFF;
								@Pc(2033) ClientPlayer local2033;
								if (Static16.anInt549 == local1146) {
									local2033 = Static173.aClass8_Sub4_Sub1_2;
								} else {
									local2033 = Static159.aClass8_Sub4_Sub1Array1[local1146];
								}
								if (local2033 != null) {
									if (local171 == 65535) {
										local171 = -1;
									}
									local1245 = true;
									if (local171 != -1 && local2033.anInt3432 != -1 && SeqType.list(SpotType.list(local171).anim).anInt5355 < SeqType.list(SpotType.list(local2033.anInt3432).anim).anInt5355) {
										local1245 = false;
									}
									if (local1245) {
										local2033.anInt3359 = local133 + Static83.anInt372;
										local2033.anInt3394 = local786;
										local2033.anInt3432 = local171;
										if (local2033.anInt3432 == 65535) {
											local2033.anInt3432 = -1;
										}
										local2033.anInt3418 = 1;
										local2033.anInt3361 = 0;
										local2033.anInt3399 = 0;
										if (local2033.anInt3359 > Static83.anInt372) {
											local2033.anInt3399 = -1;
										}
										if (local2033.anInt3432 != -1 && local2033.anInt3359 == Static83.anInt372) {
											local1986 = SpotType.list(local2033.anInt3432).anim;
											if (local1986 != -1) {
												local1994 = SeqType.list(local1986);
												if (local1994 != null && local1994.frames != null) {
													Static152.method2836(local2033.anInt3421, local1994, local2033.anInt3412, local2033 == Static173.aClass8_Sub4_Sub1_2, 0);
												}
											}
										}
									}
								}
							}
						} else {
							local1146 = local864 >> 28 & 0x3;
							local277 = (local864 >> 14 & 0x3FFF) - Static225.anInt5068;
							local1160 = (local864 & 0x3FFF) - Static142.anInt3483;
							if (local277 >= 0 && local1160 >= 0 && local277 < 104 && local1160 < 104) {
								local1160 = local1160 * 128 + 64;
								local277 = local277 * 128 + 64;
								@Pc(2241) MapSpotAnim local2241 = new MapSpotAnim(local171, local1146, local277, local1160, Static207.method3685(local1146, local277, local1160) - local786, local133, Static83.anInt372);
								Static99.aClass69_64.method2282(new MapSpotAnimNode(local2241));
							}
						}
						ptype = -1;
						return true;
					} else if (ptype == 207) {
						local133 = in.method2224();
						local786 = in.method2184();
						local864 = in.g2();
						local171 = in.method2184();
						if (Static248.method3288(local786)) {
							Static190.method3444(local171 + (local864 << 16), local133);
						}
						ptype = -1;
						return true;
					} else if (ptype == 38) {
						Static103.method2245();
						local133 = in.method2177();
						local786 = in.method2206();
						local864 = in.g1();
						Static227.anIntArray446[local864] = local786;
						Static99.anIntArray240[local864] = local133;
						Static141.anIntArray326[local864] = 1;
						for (local171 = 0; local171 < 98; local171++) {
							if (Static4.skillxp[local171] <= local786) {
								Static141.anIntArray326[local864] = local171 + 2;
							}
						}
						Static249.anIntArray478[Static89.anInt2385++ & 0x1F] = local864;
						ptype = -1;
						return true;
					} else if (ptype == 104 || ptype == 121 || ptype == 97 || ptype == 14 || ptype == 202 || ptype == 135 || ptype == 17 || ptype == 16 || ptype == 240 || ptype == 33 || ptype == 20 || ptype == 195 || ptype == 179) {
						Static75.method1634();
						ptype = -1;
						return true;
					} else if (ptype == 149) {
						local133 = in.g2();
						local786 = in.g4();
						if (Static248.method3288(local133)) {
							@Pc(2441) SubInterface local2441 = (SubInterface) Static119.aClass133_9.find((long) local786);
							if (local2441 != null) {
								Static132.method2605(true, local2441);
							}
							if (Static39.aClass13_10 != null) {
								Static43.method1143(Static39.aClass13_10);
								Static39.aClass13_10 = null;
							}
						}
						ptype = -1;
						return true;
					} else if (ptype == 187) {
						local133 = in.method2192();
						local786 = in.g2();
						local864 = in.g2();
						if (Static248.method3288(local786)) {
							Static57.anInt1747 = local133;
							Static72.anInt2031 = local864;
							if (Static227.anInt5096 == 2) {
								Static240.anInt5333 = Static72.anInt2031;
								Static184.anInt4358 = Static57.anInt1747;
							}
							Static87.method1812();
						}
						ptype = -1;
						return true;
					} else if (ptype == 132) {
						local133 = in.g2();
						local786 = in.method2184();
						local864 = in.method2207();
						local171 = in.method2207();
						local1146 = in.g4();
						if (Static248.method3288(local786)) {
							Static261.method4505(local864, local1146, local171, local133);
						}
						ptype = -1;
						return true;
					} else if (ptype == 112) {
						Static115.anInt2940 = in.g1();
						Static180.anInt4264 = in.method2212();
						for (local133 = Static115.anInt2940; local133 < Static115.anInt2940 + 8; local133++) {
							for (local786 = Static180.anInt4264; local786 < Static180.anInt4264 + 8; local786++) {
								if (Static159.aClass69ArrayArrayArray1[Static55.anInt1735][local133][local786] != null) {
									Static159.aClass69ArrayArrayArray1[Static55.anInt1735][local133][local786] = null;
									Static220.method3797(local786, local133);
								}
							}
						}
						for (@Pc(2604) LocChange local2604 = (LocChange) Static26.aClass69_27.head(); local2604 != null; local2604 = (LocChange) Static26.aClass69_27.method2288()) {
							if (local2604.anInt928 >= Static115.anInt2940 && Static115.anInt2940 + 8 > local2604.anInt928 && local2604.anInt916 >= Static180.anInt4264 && local2604.anInt916 < Static180.anInt4264 + 8 && local2604.anInt918 == Static55.anInt1735) {
								local2604.anInt924 = 0;
							}
						}
						ptype = -1;
						return true;
					} else if (ptype == 144) {
						local133 = in.method2224();
						@Pc(2666) IfType local2666 = Static5.method32(local133);
						for (local864 = 0; local864 < local2666.linkObjNumber.length; local864++) {
							local2666.linkObjNumber[local864] = -1;
							local2666.linkObjNumber[local864] = 0;
						}
						Static43.method1143(local2666);
						ptype = -1;
						return true;
					} else if (ptype == 130) {
						local133 = in.method2208();
						local786 = in.method2207();
						local864 = in.method2184();
						if (local864 == 65535) {
							local864 = -1;
						}
						if (Static248.method3288(local786)) {
							Static132.method2607(-1, 1, local133, local864);
						}
						ptype = -1;
						return true;
					} else if (ptype == 192) {
						Static270.anInt5795 = in.g1();
						ptype = -1;
						return true;
					} else if (ptype == 13) {
						local133 = in.method2180();
						local786 = in.method2177();
						local864 = in.g1();
						Static55.anInt1735 = local786 >> 1;
						Static173.aClass8_Sub4_Sub1_2.method1265(local133, (local786 & 0x1) == 1, local864);
						ptype = -1;
						return true;
					} else {
						@Pc(3002) int local3002;
						@Pc(3038) JagString local3038;
						@Pc(3020) JagString local3020;
						if (ptype == 62) {
							local884 = in.g8();
							local864 = in.g2();
							local171 = in.g1();
							local262 = true;
							if (local884 < 0L) {
								local884 &= Long.MAX_VALUE;
								local262 = false;
							}
							local506 = Static186.AUTO_EMPTY;
							if (local864 > 0) {
								local506 = in.gjstr();
							}
							@Pc(2834) JagString local2834 = Static79.toBaseDisplayName(local884).method3125();
							for (local1986 = 0; local1986 < Static9.anInt178; local1986++) {
								if (local884 == Static92.aLongArray3[local1986]) {
									if (local864 != Static104.anIntArray255[local1986]) {
										Static104.anIntArray255[local1986] = local864;
										if (local864 > 0) {
											addChat(Static186.AUTO_EMPTY, 5, JagString.join(new JagString[] { local2834, Text.aClass100_155 }));
										}
										if (local864 == 0) {
											addChat(Static186.AUTO_EMPTY, 5, JagString.join(new JagString[] { local2834, Text.aClass100_507 }));
										}
									}
									Static214.aClass100Array170[local1986] = local506;
									Static106.anIntArray258[local1986] = local171;
									local2834 = null;
									Static3.aBooleanArray135[local1986] = local262;
									break;
								}
							}
							if (local2834 != null && Static9.anInt178 < 200) {
								Static92.aLongArray3[Static9.anInt178] = local884;
								Static122.aClass100Array92[Static9.anInt178] = local2834;
								Static104.anIntArray255[Static9.anInt178] = local864;
								Static214.aClass100Array170[Static9.anInt178] = local506;
								Static106.anIntArray258[Static9.anInt178] = local171;
								Static3.aBooleanArray135[Static9.anInt178] = local262;
								Static9.anInt178++;
							}
							Static185.anInt4369 = anInt3028;
							local908 = Static9.anInt178;
							while (local908 > 0) {
								local908--;
								@Pc(2961) boolean local2961 = true;
								for (local916 = 0; local916 < local908; local916++) {
									if (Static104.anIntArray255[local916] != anInt3103 && anInt3103 == Static104.anIntArray255[local916 + 1] || Static104.anIntArray255[local916] == 0 && Static104.anIntArray255[local916 + 1] != 0) {
										local2961 = false;
										local3002 = Static104.anIntArray255[local916];
										Static104.anIntArray255[local916] = Static104.anIntArray255[local916 + 1];
										Static104.anIntArray255[local916 + 1] = local3002;
										local3020 = Static214.aClass100Array170[local916];
										Static214.aClass100Array170[local916] = Static214.aClass100Array170[local916 + 1];
										Static214.aClass100Array170[local916 + 1] = local3020;
										local3038 = Static122.aClass100Array92[local916];
										Static122.aClass100Array92[local916] = Static122.aClass100Array92[local916 + 1];
										Static122.aClass100Array92[local916 + 1] = local3038;
										@Pc(3056) long local3056 = Static92.aLongArray3[local916];
										Static92.aLongArray3[local916] = Static92.aLongArray3[local916 + 1];
										Static92.aLongArray3[local916 + 1] = local3056;
										@Pc(3074) int local3074 = Static106.anIntArray258[local916];
										Static106.anIntArray258[local916] = Static106.anIntArray258[local916 + 1];
										Static106.anIntArray258[local916 + 1] = local3074;
										@Pc(3092) boolean local3092 = Static3.aBooleanArray135[local916];
										Static3.aBooleanArray135[local916] = Static3.aBooleanArray135[local916 + 1];
										Static3.aBooleanArray135[local916 + 1] = local3092;
									}
								}
								if (local2961) {
									break;
								}
							}
							ptype = -1;
							return true;
						} else if (ptype == 160) {
							if (psize == 0) {
								Static195.aClass100_859 = Text.aClass100_560;
							} else {
								Static195.aClass100_859 = in.gjstr();
							}
							ptype = -1;
							return true;
						} else if (ptype == 128) {
							for (local133 = 0; local133 < Static7.var.length; local133++) {
								if (Static106.varServ[local133] != Static7.var[local133]) {
									Static7.var[local133] = Static106.varServ[local133];
									Static85.method1775(local133);
									Static83.anIntArray23[Static70.anInt2015++ & 0x1F] = local133;
								}
							}
							ptype = -1;
							return true;
						} else if (ptype == 154) {
							local133 = in.g2();
							local786 = in.g1();
							local864 = in.g1();
							local171 = in.g2();
							local1146 = in.g1();
							local277 = in.g1();
							if (Static248.method3288(local133)) {
								Static141.method2722(true, local1146, local171, local277, local864, local786);
							}
							ptype = -1;
							return true;
						} else if (ptype == 247) {
							local884 = in.g8();
							local275 = in.g2();
							local899 = in.g3();
							local1160 = in.g1();
							local1986 = in.g2();
							@Pc(3263) boolean local3263 = false;
							@Pc(3270) long local3270 = (local275 << 32) + local899;
							@Pc(3272) int local3272 = 0;
							label1402: while (true) {
								if (local3272 < 100) {
									if (local3270 != Static233.aLongArray9[local3272]) {
										local3272++;
										continue;
									}
									local3263 = true;
									break;
								}
								if (local1160 <= 1) {
									for (local3272 = 0; local3272 < Static35.anInt1093; local3272++) {
										if (local884 == Static190.aLongArray6[local3272]) {
											local3263 = true;
											break label1402;
										}
									}
								}
								break;
							}
							if (!local3263 && Static11.anInt384 == 0) {
								Static233.aLongArray9[Static251.anInt5447] = local3270;
								Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
								local3020 = QuickChatPhraseType.list(local1986).method770(in);
								if (local1160 == 2) {
									method2928(local1986, 18, local3020, null, JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }));
								} else if (local1160 == 1) {
									method2928(local1986, 18, local3020, null, JagString.join(new JagString[] { Static65.aClass100_435, Static79.toBaseDisplayName(local884).method3125() }));
								} else {
									method2928(local1986, 18, local3020, null, Static79.toBaseDisplayName(local884).method3125());
								}
							}
							ptype = -1;
							return true;
						} else {
							@Pc(3456) SubInterface local3456;
							if (ptype == 176) {
								local133 = in.method2206();
								local786 = in.method2184();
								local864 = in.method2206();
								if (Static248.method3288(local786)) {
									@Pc(3449) SubInterface local3449 = (SubInterface) Static119.aClass133_9.find((long) local133);
									local3456 = (SubInterface) Static119.aClass133_9.find((long) local864);
									if (local3456 != null) {
										Static132.method2605(local3449 == null || local3456.anInt5878 != local3449.anInt5878, local3456);
									}
									if (local3449 != null) {
										local3449.unlink();
										Static119.aClass133_9.put(local3449, (long) local864);
									}
									@Pc(3490) IfType local3490 = Static5.method32(local133);
									if (local3490 != null) {
										Static43.method1143(local3490);
									}
									local3490 = Static5.method32(local864);
									if (local3490 != null) {
										Static43.method1143(local3490);
										Static17.method531(local3490, true);
									}
									if (Static154.anInt3711 != -1) {
										Static54.method1304(1, Static154.anInt3711);
									}
								}
								ptype = -1;
								return true;
							} else if (ptype == 27) {
								local133 = in.g2();
								local786 = in.g1();
								local864 = in.g1();
								local171 = in.g1();
								local1146 = in.g1();
								local277 = in.g2();
								if (Static248.method3288(local133)) {
									Static176.aBooleanArray95[local786] = true;
									Static222.anIntArray437[local786] = local864;
									Static276.anIntArray564[local786] = local171;
									Static202.anIntArray424[local786] = local1146;
									Static31.anIntArray76[local786] = local277;
								}
								ptype = -1;
								return true;
							} else if (ptype == 2) {
								local133 = in.method2206();
								local786 = in.method2184();
								local864 = in.method2207();
								if (Static248.method3288(local786)) {
									Static136.method2649(local864, local133);
								}
								ptype = -1;
								return true;
							} else if (ptype == 85) {
								Static60.anInt1894 = in.g2() * 30;
								ptype = -1;
								Static209.anInt4808 = anInt3028;
								return true;
							} else if (ptype == 114) {
								Static202.method3654(signlink, in, psize);
								ptype = -1;
								return true;
							} else if (ptype == 65) {
								local133 = in.method2192();
								local786 = in.method2212();
								local864 = in.method2207();
								if (Static248.method3288(local133)) {
									Static132.method2606(local864, local786);
								}
								ptype = -1;
								return true;
							} else if (ptype == 234) {
								Static103.method2245();
								Static12.anInt400 = in.g1();
								Static209.anInt4808 = anInt3028;
								ptype = -1;
								return true;
							} else if (ptype == 209) {
								if (Static154.anInt3711 != -1) {
									Static54.method1304(0, Static154.anInt3711);
								}
								ptype = -1;
								return true;
							} else if (ptype == 191) {
								local133 = in.method2192();
								ClientInvCache.delete(local133);
								Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = local133 & 0x7FFF;
								ptype = -1;
								return true;
							} else if (ptype == 102) {
								local133 = in.method2192();
								local786 = in.method2180();
								local864 = in.g2();
								@Pc(3766) ClientNPC local3766 = Static175.aClass8_Sub4_Sub2Array1[local133];
								if (local3766 != null) {
									Static223.method3855(local786, local864, local3766);
								}
								ptype = -1;
								return true;
							} else if (ptype == 159) {
								Static103.method2245();
								Static251.anInt5456 = in.g2b();
								Static209.anInt4808 = anInt3028;
								ptype = -1;
								return true;
							} else if (ptype == 71) {
								local884 = in.g8();
								local790 = Static218.method2862(Static65.method1497(in).method3116());
								addChat(Static79.toBaseDisplayName(local884).method3125(), 6, local790);
								ptype = -1;
								return true;
							} else if (ptype == 42) {
								if (aFrame2 != null) {
									Static241.method4540(false, Static214.anInt5581, -1, -1);
								}
								@Pc(3848) byte[] local3848 = new byte[psize];
								in.method2237(local3848, psize);
								local156 = Static10.method346(local3848, psize, 0);
								if (frame == null && (SignLink.anInt5928 == 3 || !SignLink.osNameLower.startsWith("win") || haveie6)) {
									Static169.method3175(local156, true);
								} else {
									Static175.aClass100_797 = local156;
									Static164.aBoolean194 = true;
									Static33.aClass212_1 = signlink.method5131(new String(local156.builderToString(), "ISO-8859-1"));
								}
								ptype = -1;
								return true;
							} else if (ptype == 111) {
								local133 = in.method2184();
								local786 = in.method2224();
								local864 = in.method2207();
								local171 = in.method2192();
								local1146 = in.method2207();
								if (Static248.method3288(local133)) {
									Static132.method2607(local864, 7, local786, local171 << 16 | local1146);
								}
								ptype = -1;
								return true;
							} else if (ptype == 37) {
								local133 = in.method2177();
								local786 = in.method2192();
								Static272.method3995(local133, local786);
								ptype = -1;
								return true;
							} else if (ptype == 155) {
								local133 = in.g1();
								local786 = in.method2224();
								local864 = in.method2184();
								local171 = in.g2();
								if (Static248.method3288(local864)) {
									local3456 = (SubInterface) Static119.aClass133_9.find((long) local786);
									if (local3456 != null) {
										Static132.method2605(local3456.anInt5878 != local171, local3456);
									}
									Static44.method1148(local171, local786, local133);
								}
								ptype = -1;
								return true;
							} else if (ptype == 131) {
								for (local133 = 0; local133 < Static159.aClass8_Sub4_Sub1Array1.length; local133++) {
									if (Static159.aClass8_Sub4_Sub1Array1[local133] != null) {
										Static159.aClass8_Sub4_Sub1Array1[local133].anInt3369 = -1;
									}
								}
								for (local133 = 0; local133 < Static175.aClass8_Sub4_Sub2Array1.length; local133++) {
									if (Static175.aClass8_Sub4_Sub2Array1[local133] != null) {
										Static175.aClass8_Sub4_Sub2Array1[local133].anInt3369 = -1;
									}
								}
								ptype = -1;
								return true;
							} else if (ptype == 217) {
								local133 = in.g1();
								@Pc(4084) MapMarker local4084 = new MapMarker();
								local786 = local133 >> 6;
								local4084.anInt4058 = local133 & 0x3F;
								local4084.anInt4048 = in.g1();
								if (local4084.anInt4048 >= 0 && local4084.anInt4048 < Static276.aClass3_Sub2_Sub1Array11.length) {
									if (local4084.anInt4058 == 1 || local4084.anInt4058 == 10) {
										local4084.anInt4057 = in.g2();
										in.pos += 3;
									} else if (local4084.anInt4058 >= 2 && local4084.anInt4058 <= 6) {
										if (local4084.anInt4058 == 2) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 64;
										}
										if (local4084.anInt4058 == 3) {
											local4084.anInt4045 = 0;
											local4084.anInt4047 = 64;
										}
										if (local4084.anInt4058 == 4) {
											local4084.anInt4045 = 128;
											local4084.anInt4047 = 64;
										}
										if (local4084.anInt4058 == 5) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 0;
										}
										if (local4084.anInt4058 == 6) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 128;
										}
										local4084.anInt4058 = 2;
										local4084.anInt4053 = in.g2();
										local4084.anInt4046 = in.g2();
										local4084.anInt4050 = in.g1();
									}
									local4084.anInt4052 = in.g2();
									if (local4084.anInt4052 == 65535) {
										local4084.anInt4052 = -1;
									}
									Static143.aClass102Array1[local786] = local4084;
								}
								ptype = -1;
								return true;
							} else if (ptype == 126) {
								Static35.anInt1093 = psize / 8;
								for (local133 = 0; local133 < Static35.anInt1093; local133++) {
									Static190.aLongArray6[local133] = in.g8();
									Static193.aClass100Array134[local133] = Static79.toBaseDisplayName(Static190.aLongArray6[local133]);
								}
								Static185.anInt4369 = anInt3028;
								ptype = -1;
								return true;
							} else if (ptype == 32) {
								Static86.method1800();
								ptype = -1;
								return true;
							} else if (ptype == 119) {
								local133 = in.method2184();
								local786 = in.method2208();
								local864 = in.g2b();
								local171 = in.method2173();
								if (Static248.method3288(local133)) {
									Static280.method4666(local864, local786, local171);
								}
								ptype = -1;
								return true;
							} else if (ptype == 235) {
								local133 = in.method2180();
								local786 = local133 >> 2;
								local864 = local133 & 0x3;
								local171 = Static133.anIntArray453[local786];
								local1146 = in.g2();
								local277 = in.g4();
								if (local1146 == 65535) {
									local1146 = -1;
								}
								local908 = local277 & 0x3FFF;
								local1986 = local277 >> 14 & 0x3FFF;
								local1986 -= Static225.anInt5068;
								local908 -= Static142.anInt3483;
								local1160 = local277 >> 28 & 0x3;
								Static92.method1881(local1160, local864, local786, local908, local171, local1986, local1146);
								ptype = -1;
								return true;
							} else if (ptype == 0) {
								local884 = in.g8();
								local275 = in.g2();
								local899 = in.g3();
								local1160 = in.g1();
								@Pc(4425) boolean local4425 = false;
								@Pc(4431) long local4431 = local899 + (local275 << 32);
								local3002 = 0;
								label1450: while (true) {
									if (local3002 >= 100) {
										if (local1160 <= 1) {
											if (Static124.aBoolean157 && !Static207.aBoolean236 || Static86.aBoolean129) {
												local4425 = true;
											} else {
												for (local3002 = 0; local3002 < Static35.anInt1093; local3002++) {
													if (local884 == Static190.aLongArray6[local3002]) {
														local4425 = true;
														break label1450;
													}
												}
											}
										}
										break;
									}
									if (local4431 == Static233.aLongArray9[local3002]) {
										local4425 = true;
										break;
									}
									local3002++;
								}
								if (!local4425 && Static11.anInt384 == 0) {
									Static233.aLongArray9[Static251.anInt5447] = local4431;
									Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
									@Pc(4518) JagString local4518 = Static218.method2862(Static65.method1497(in).method3116());
									if (local1160 == 2 || local1160 == 3) {
										addChat(JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }), 7, local4518);
									} else if (local1160 == 1) {
										addChat(JagString.join(new JagString[] { Static65.aClass100_435, Static79.toBaseDisplayName(local884).method3125() }), 7, local4518);
									} else {
										addChat(Static79.toBaseDisplayName(local884).method3125(), 3, local4518);
									}
								}
								ptype = -1;
								return true;
							} else if (ptype == 54) {
								local884 = in.g8();
								in.g1b();
								local275 = in.g8();
								local899 = in.g2();
								local904 = in.g3();
								@Pc(4626) long local4626 = (local899 << 32) + local904;
								local908 = in.g1();
								@Pc(4632) boolean local4632 = false;
								@Pc(4634) int local4634 = 0;
								label1575: while (true) {
									if (local4634 >= 100) {
										if (local908 <= 1) {
											if (Static124.aBoolean157 && !Static207.aBoolean236 || Static86.aBoolean129) {
												local4632 = true;
											} else {
												for (local4634 = 0; local4634 < Static35.anInt1093; local4634++) {
													if (Static190.aLongArray6[local4634] == local884) {
														local4632 = true;
														break label1575;
													}
												}
											}
										}
										break;
									}
									if (Static233.aLongArray9[local4634] == local4626) {
										local4632 = true;
										break;
									}
									local4634++;
								}
								if (!local4632 && Static11.anInt384 == 0) {
									Static233.aLongArray9[Static251.anInt5447] = local4626;
									Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
									local3038 = Static218.method2862(Static65.method1497(in).method3116());
									if (local908 == 2 || local908 == 3) {
										Static73.method1598(local3038, JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }), Static79.toBaseDisplayName(local275).method3125());
									} else if (local908 == 1) {
										Static73.method1598(local3038, JagString.join(new JagString[] { Static65.aClass100_435, Static79.toBaseDisplayName(local884).method3125() }), Static79.toBaseDisplayName(local275).method3125());
									} else {
										Static73.method1598(local3038, Static79.toBaseDisplayName(local884).method3125(), Static79.toBaseDisplayName(local275).method3125());
									}
								}
								ptype = -1;
								return true;
							} else if (ptype == 214) {
								Static75.method1629(true);
								ptype = -1;
								return true;
							} else if (ptype == 172) {
								local133 = in.g2();
								local786 = in.g1();
								if (local133 == 65535) {
									local133 = -1;
								}
								local864 = in.g2();
								Static26.method744(local786, local133, local864);
								ptype = -1;
								return true;
							} else if (ptype == 66) {
								local133 = in.method2207();
								local786 = in.method2206();
								if (Static248.method3288(local133)) {
									local864 = 0;
									if (Static173.aClass8_Sub4_Sub1_2.aClass59_1 != null) {
										local864 = Static173.aClass8_Sub4_Sub1_2.aClass59_1.method1952();
									}
									Static132.method2607(-1, 3, local786, local864);
								}
								ptype = -1;
								return true;
							} else if (ptype == 171) {
								local133 = in.method2224();
								local156 = in.gjstr();
								local864 = in.method2184();
								if (Static248.method3288(local864)) {
									Static80.method3617(local156, local133);
								}
								ptype = -1;
								return true;
							} else if (ptype == 84) {
								local133 = in.method2208();
								local786 = in.method2207();
								Static272.method3995(local133, local786);
								ptype = -1;
								return true;
							} else {
								@Pc(4956) IfType local4956;
								if (ptype == 22) {
									local133 = in.g4();
									local786 = in.g2();
									if (local133 < -70000) {
										local786 += 32768;
									}
									if (local133 < 0) {
										local4956 = null;
									} else {
										local4956 = Static5.method32(local133);
									}
									while (in.pos < psize) {
										local171 = in.method2204();
										local1146 = in.g2();
										local277 = 0;
										if (local1146 != 0) {
											local277 = in.g1();
											if (local277 == 255) {
												local277 = in.g4();
											}
										}
										if (local4956 != null && local171 >= 0 && local4956.linkObjNumber.length > local171) {
											local4956.linkObjNumber[local171] = local1146;
											local4956.linkObjType[local171] = local277;
										}
										ClientInvCache.set(local1146 - 1, local171, local277, local786);
									}
									if (local4956 != null) {
										Static43.method1143(local4956);
									}
									Static103.method2245();
									Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = local786 & 0x7FFF;
									ptype = -1;
									return true;
								} else if (ptype == 24) {
									local133 = in.g2();
									if (Static248.method3288(local133)) {
										Static35.method902();
									}
									ptype = -1;
									return true;
								} else if (ptype == 86) {
									Static278.method4653();
									ptype = -1;
									return false;
								} else if (ptype == 116) {
									local133 = in.g1();
									if (in.g1() == 0) {
										Static229.aClass136Array1[local133] = new StockMarketOffer();
									} else {
										in.pos--;
										Static229.aClass136Array1[local133] = new StockMarketOffer(in);
									}
									ptype = -1;
									Static207.anInt4778 = anInt3028;
									return true;
								} else if (ptype == 73) {
									local133 = in.method2184();
									local786 = in.method2208();
									if (local133 == 65535) {
										local133 = -1;
									}
									local864 = in.method2192();
									if (Static248.method3288(local864)) {
										Static132.method2607(-1, 2, local786, local133);
									}
									ptype = -1;
									return true;
								} else if (ptype == 162) {
									Static75.method1629(false);
									ptype = -1;
									return true;
								} else if (ptype == 165) {
									local133 = in.method2192();
									local786 = in.method2192();
									if (local786 == 65535) {
										local786 = -1;
									}
									local864 = in.g4();
									local171 = in.method2184();
									local1146 = in.method2206();
									if (local171 == 65535) {
										local171 = -1;
									}
									if (Static248.method3288(local133)) {
										for (local277 = local171; local277 <= local786; local277++) {
											local904 = ((long) local864 << 32) + ((long) local277);
											local1804 = (ServerActive) Static210.aClass133_21.find(local904);
											if (local1804 != null) {
												local1814 = new ServerActive(local1146, local1804.anInt540);
												local1804.unlink();
											} else if (local277 == -1) {
												local1814 = new ServerActive(local1146, Static5.method32(local864).active.anInt540);
											} else {
												local1814 = new ServerActive(local1146, -1);
											}
											Static210.aClass133_21.put(local1814, local904);
										}
									}
									ptype = -1;
									return true;
								} else if (ptype == 197) {
									Static166.anInt4054 = in.g1();
									Static185.anInt4369 = anInt3028;
									ptype = -1;
									return true;
								} else if (ptype == 196) {
									local884 = in.g8();
									local864 = in.g2();
									@Pc(5325) byte local5325 = in.g1b();
									local262 = false;
									if ((Long.MIN_VALUE & local884) != 0L) {
										local262 = true;
									}
									if (local262) {
										if (Static214.anInt5577 == 0) {
											ptype = -1;
											return true;
										}
										local884 &= Long.MAX_VALUE;
										for (local277 = 0; Static214.anInt5577 > local277 && (local884 != Static199.aClass3_Sub22Array1[local277].key || local864 != Static199.aClass3_Sub22Array1[local277].world); local277++) {
										}
										if (local277 < Static214.anInt5577) {
											while (Static214.anInt5577 - 1 > local277) {
												Static199.aClass3_Sub22Array1[local277] = Static199.aClass3_Sub22Array1[local277 + 1];
												local277++;
											}
											Static214.anInt5577--;
											Static199.aClass3_Sub22Array1[Static214.anInt5577] = null;
										}
									} else {
										local506 = in.gjstr();
										@Pc(5347) FriendChatUser local5347 = new FriendChatUser();
										local5347.key = local884;
										local5347.displayName = Static79.toBaseDisplayName(local5347.key);
										local5347.rank = local5325;
										local5347.aClass100_635 = local506;
										local5347.world = local864;
										for (local1986 = Static214.anInt5577 - 1; local1986 >= 0; local1986--) {
											local908 = Static199.aClass3_Sub22Array1[local1986].displayName.method3139(local5347.displayName);
											if (local908 == 0) {
												Static199.aClass3_Sub22Array1[local1986].world = local864;
												Static199.aClass3_Sub22Array1[local1986].rank = local5325;
												Static199.aClass3_Sub22Array1[local1986].aClass100_635 = local506;
												if (local884 == Static101.aLong98) {
													Static160.aByte14 = local5325;
												}
												Static278.anInt5867 = anInt3028;
												ptype = -1;
												return true;
											}
											if (local908 < 0) {
												break;
											}
										}
										if (Static199.aClass3_Sub22Array1.length <= Static214.anInt5577) {
											ptype = -1;
											return true;
										}
										for (local908 = Static214.anInt5577 - 1; local908 > local1986; local908--) {
											Static199.aClass3_Sub22Array1[local908 + 1] = Static199.aClass3_Sub22Array1[local908];
										}
										if (Static214.anInt5577 == 0) {
											Static199.aClass3_Sub22Array1 = new FriendChatUser[100];
										}
										Static199.aClass3_Sub22Array1[local1986 + 1] = local5347;
										if (Static101.aLong98 == local884) {
											Static160.aByte14 = local5325;
										}
										Static214.anInt5577++;
									}
									ptype = -1;
									Static278.anInt5867 = anInt3028;
									return true;
								} else if (ptype == 50) {
									local133 = in.g4();
									local786 = in.method2224();
									local864 = in.method2207();
									if (local864 == 65535) {
										local864 = -1;
									}
									local171 = in.method2192();
									if (Static248.method3288(local171)) {
										@Pc(5603) IfType local5603 = Static5.method32(local786);
										@Pc(5615) ObjType local5615;
										if (local5603.v3) {
											Static209.method3707(local786, local133, local864);
											local5615 = ObjType.list(local864);
											Static261.method4505(local5615.anInt2375, local786, local5615.anInt2369, local5615.anInt2353);
											Static145.method2745(local786, local5615.anInt2339, local5615.anInt2319, local5615.anInt2359);
										} else if (local864 == -1) {
											local5603.model1Type = 0;
											ptype = -1;
											return true;
										} else {
											local5615 = ObjType.list(local864);
											local5603.modelXAn = local5615.anInt2353;
											local5603.modelZoom = local5615.anInt2375 * 100 / local133;
											local5603.model1Type = 4;
											local5603.model1Id = local864;
											local5603.modelYAn = local5615.anInt2369;
											Static43.method1143(local5603);
										}
									}
									ptype = -1;
									return true;
								} else if (ptype == 105) {
									local133 = in.g4();
									local786 = in.g2();
									if (local133 < -70000) {
										local786 += 32768;
									}
									if (local133 >= 0) {
										local4956 = Static5.method32(local133);
									} else {
										local4956 = null;
									}
									if (local4956 != null) {
										for (local171 = 0; local171 < local4956.linkObjNumber.length; local171++) {
											local4956.linkObjNumber[local171] = 0;
											local4956.linkObjType[local171] = 0;
										}
									}
									ClientInvCache.method475(local786);
									local171 = in.g2();
									for (local1146 = 0; local1146 < local171; local1146++) {
										local277 = in.method2180();
										if (local277 == 255) {
											local277 = in.g4();
										}
										local1160 = in.g2();
										if (local4956 != null && local1146 < local4956.linkObjNumber.length) {
											local4956.linkObjNumber[local1146] = local1160;
											local4956.linkObjType[local1146] = local277;
										}
										ClientInvCache.set(local1160 - 1, local1146, local277, local786);
									}
									if (local4956 != null) {
										Static43.method1143(local4956);
									}
									Static103.method2245();
									Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = local786 & 0x7FFF;
									ptype = -1;
									return true;
								} else if (ptype == 142) {
									Static230.method3954(in.gjstr());
									ptype = -1;
									return true;
								} else if (ptype == 26) {
									Static115.anInt2940 = in.method2212();
									Static180.anInt4264 = in.g1();
									ptype = -1;
									return true;
								} else if (ptype == 4) {
									local133 = in.method2207();
									if (local133 == 65535) {
										local133 = -1;
									}
									Static148.method2765(local133);
									ptype = -1;
									return true;
								} else if (ptype == 208) {
									local133 = in.method2181();
									local786 = in.method2192();
									if (local786 == 65535) {
										local786 = -1;
									}
									Static278.method4650(local133, local786);
									ptype = -1;
									return true;
								} else {
									JagException.report("T1 - " + ptype + "," + ptype1 + "," + ptype2 + " - " + psize, null);
									Static278.method4653();
									return true;
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!an", name = "h", descriptor = "(I)Z")
	public static boolean method349() {
		try {
			return tcpIn();
		} catch (@Pc(14) IOException local14) {
			Static175.method3279();
			return true;
		} catch (@Pc(19) Exception local19) {
			@Pc(61) String local61 = "T2 - " + ptype + "," + ptype1 + "," + ptype2 + " - " + psize + "," + (Static225.anInt5068 + Static173.aClass8_Sub4_Sub1_2.anIntArray318[0]) + "," + (Static173.aClass8_Sub4_Sub1_2.anIntArray317[0] + Static142.anInt3483) + " - ";
			for (@Pc(63) int local63 = 0; local63 < psize && local63 < 50; local63++) {
				local61 = local61 + in.data[local63] + ",";
			}
			JagException.report(local61, local19);
			Static278.method4653();
			return true;
		}
	}

	@OriginalMember(owner = "client!i", name = "a", descriptor = "(Lclient!na;ILclient!na;I)V")
	public static void addChat(@OriginalArg(0) JagString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JagString arg2) {
		method2928(-1, arg1, arg2, null, arg0);
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(IILclient!na;Lclient!na;BLclient!na;)V")
	public static void method2928(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JagString arg2, @OriginalArg(3) JagString arg3, @OriginalArg(5) JagString arg4) {
		for (@Pc(14) int local14 = 99; local14 > 0; local14--) {
			anIntArray67[local14] = anIntArray67[local14 - 1];
			aClass100Array112[local14] = aClass100Array112[local14 - 1];
			aClass100Array158[local14] = aClass100Array158[local14 - 1];
			aClass100Array62[local14] = aClass100Array62[local14 - 1];
			anIntArray521[local14] = anIntArray521[local14 - 1];
		}
		anInt1941++;
		anIntArray67[0] = arg1;
		aClass100Array112[0] = arg4;
		anInt1464 = anInt3028;
		anIntArray521[0] = arg0;
		aClass100Array158[0] = arg2;
		aClass100Array62[0] = arg3;
	}

	@OriginalMember(owner = "client!je", name = "h", descriptor = "(I)V")
	public static void method2380() {
		FloType.method4301();
		FluType.method3885();
		IdkType.method3342();
		LocType.method3323();
		NPCType.method4001();
		ObjType.method2239();
		SeqType.method3903();
		SpotType.method1441();
		VarBitType.method1694();
		VarpType.method4657();
		BasType.method1172();
		MsiType.method4529();
		LightType.method1882();
		CursorType.method741();
		Static192.method3474();
		Static40.method1019();
		Static180.method3329();
		Static251.method4276();
		Static73.aClass99_10.clear();
		Static139.aClass99_22.clear();
	}

	@OriginalMember(owner = "client!dc", name = "b", descriptor = "(Z)V")
	public static void method1050() {
		@Pc(6) int local6 = in.method2238(8);
		@Pc(20) int local20;
		if (Static267.anInt5774 > local6) {
			for (local20 = local6; local20 < Static267.anInt5774; local20++) {
				Static52.anIntArray136[Static240.anInt5335++] = Static105.anIntArray256[local20];
			}
		}
		if (local6 > Static267.anInt5774) {
			throw new RuntimeException("gppov1");
		}
		Static267.anInt5774 = 0;
		for (local20 = 0; local20 < local6; local20++) {
			@Pc(75) int local75 = Static105.anIntArray256[local20];
			@Pc(79) ClientPlayer local79 = Static159.aClass8_Sub4_Sub1Array1[local75];
			@Pc(84) int local84 = in.method2238(1);
			if (local84 == 0) {
				Static105.anIntArray256[Static267.anInt5774++] = local75;
				local79.anInt3430 = Static83.anInt372;
			} else {
				@Pc(107) int local107 = in.method2238(2);
				if (local107 == 0) {
					Static105.anIntArray256[Static267.anInt5774++] = local75;
					local79.anInt3430 = Static83.anInt372;
					Static44.anIntArray106[Static116.anInt2951++] = local75;
				} else {
					@Pc(153) int local153;
					@Pc(163) int local163;
					if (local107 == 1) {
						Static105.anIntArray256[Static267.anInt5774++] = local75;
						local79.anInt3430 = Static83.anInt372;
						local153 = in.method2238(3);
						local79.method2684(1, local153);
						local163 = in.method2238(1);
						if (local163 == 1) {
							Static44.anIntArray106[Static116.anInt2951++] = local75;
						}
					} else if (local107 == 2) {
						Static105.anIntArray256[Static267.anInt5774++] = local75;
						local79.anInt3430 = Static83.anInt372;
						if (in.method2238(1) == 1) {
							local153 = in.method2238(3);
							local79.method2684(2, local153);
							local163 = in.method2238(3);
							local79.method2684(2, local163);
						} else {
							local153 = in.method2238(3);
							local79.method2684(0, local153);
						}
						local153 = in.method2238(1);
						if (local153 == 1) {
							Static44.anIntArray106[Static116.anInt2951++] = local75;
						}
					} else if (local107 == 3) {
						Static52.anIntArray136[Static240.anInt5335++] = local75;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!re", name = "a", descriptor = "(I)V")
	public static void method3729() {
		FloType.method119();
		FluType.method1443();
		IdkType.method4142();
		LocType.method4415();
		NPCType.method3706();
		ObjType.method3447();
		SeqType.method1570();
		SpotType.method2666();
		VarBitType.method2221();
		VarpType.method666();
		BasType.method586();
		MsiType.method4615();
		LightType.method715();
		CursorType.method716();
		Static279.method4662();
		Static53.method1289();
		Static158.method3010();
		Static134.method2621();
		Static73.aClass99_10.method3102(5);
		Static139.aClass99_22.method3102(5);
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(I)V")
	@Override
	protected final void mainredraw() {
		if (state == 1000) {
			return;
		}

		@Pc(15) boolean local15 = Static138.updateLoading();
		if (local15 && Static144.aBoolean173 && midiPcmPlayer != null) {
			midiPcmPlayer.method3570();
		}

		if ((state == 30 || state == 10) && (GameShell.canvasReplaceRecommended || Static97.aLong89 != 0L && Static97.aLong89 < MonotonicTime.currentTime())) {
			Static241.method4540(GameShell.canvasReplaceRecommended, Static144.method2736(), Static114.anInt5831, Static22.anInt729);
		}

		@Pc(80) int local80;
		@Pc(84) int local84;
		if (GameShell.aFrame2 == null) {
			@Pc(65) Container local65;
			if (GameShell.aFrame2 != null) {
				local65 = GameShell.aFrame2;
			} else if (GameShell.frame == null) {
				local65 = GameShell.signlink.applet;
			} else {
				local65 = GameShell.frame;
			}
			local80 = local65.getSize().width;
			local84 = local65.getSize().height;
			if (local65 == GameShell.frame) {
				@Pc(90) Insets local90 = GameShell.frame.getInsets();
				local80 -= local90.right + local90.left;
				local84 -= local90.top + local90.bottom;
			}
			if (local80 != GameShell.canvasWid || local84 != GameShell.canvasHei) {
				Static203.method3662();
				Static97.aLong89 = MonotonicTime.currentTime() + 500L;
			}
		}

		if (GameShell.aFrame2 != null && !GameShell.focus && (state == 30 || state == 10)) {
			Static241.method4540(false, Static214.anInt5581, -1, -1);
		}

		@Pc(158) boolean local158 = false;
		if (GameShell.fullredraw) {
			local158 = true;
			GameShell.fullredraw = false;
		}
		if (local158) {
			Static139.method2704();
		}

		if (GameShell.glRenderer) {
			for (local80 = 0; local80 < 100; local80++) {
				Static186.aBooleanArray100[local80] = true;
			}
		}

		if (state == 0) {
			GameShell.drawProgress(null, local158, TitleScreen.loadString, TitleScreen.loadPos);
		} else if (state == 5) {
			Static182.method3359(false, Static280.aClass3_Sub2_Sub9_43);
		} else if (state == 10) {
			Static126.method2460();
		} else if (state == 25 || state == 28) {
			if (Static233.mapLoadingStage == 1) {
				if (Static230.mapPrevLoadCount < Static175.mapLoadCount) {
					Static230.mapPrevLoadCount = Static175.mapLoadCount;
				}

				local80 = (Static230.mapPrevLoadCount - Static175.mapLoadCount) * 50 / Static230.mapPrevLoadCount;
				Static114.messageBox(false, JagString.join(new JagString[] { Text.aClass100_621, Static229.aClass100_974, JagString.parseInt(local80), Static14.aClass100_80 }));
			} else if (Static233.mapLoadingStage == 2) {
				if (Static38.locModelLoadPrevCount < Static271.locModelLoadCount) {
					Static38.locModelLoadPrevCount = Static271.locModelLoadCount;
				}

				local80 = (Static38.locModelLoadPrevCount - Static271.locModelLoadCount) * 50 / Static38.locModelLoadPrevCount + 50;
				Static114.messageBox(false, JagString.join(new JagString[] { Text.aClass100_621, Static229.aClass100_974, JagString.parseInt(local80), Static14.aClass100_80 }));
			} else {
				Static114.messageBox(false, Text.aClass100_621);
			}
		} else if (state == 30) {
			Static89.gameDraw();
		} else if (state == 40) {
			Static114.messageBox(false, JagString.join(new JagString[] { Text.aClass100_986, Static269.aClass100_556, Text.aClass100_1077 }));
		}

		if (GameShell.glRenderer && state != 0) {
			Static239.method4153();

			for (local80 = 0; local80 < Static24.componentDrawCount; local80++) {
				Static31.componentRedrawRequested2[local80] = false;
			}
		} else {
			@Pc(388) Graphics local388;
			if ((state == 30 || state == 10) && Static199.anInt4672 == 0 && !local158) {
				try {
					local388 = GameShell.canvas.getGraphics();
					for (local84 = 0; local84 < Static24.componentDrawCount; local84++) {
						if (Static31.componentRedrawRequested2[local84]) {
							Static260.drawArea.draw(Static224.anIntArray443[local84], Static264.anIntArray410[local84], Static67.anIntArray320[local84], local388, Static50.anIntArray133[local84]);
							Static31.componentRedrawRequested2[local84] = false;
						}
					}
				} catch (@Pc(423) Exception ex) {
					GameShell.canvas.repaint();
				}
			} else if (state != 0) {
				try {
					local388 = GameShell.canvas.getGraphics();
					Static260.drawArea.method4186(local388);
					for (local84 = 0; local84 < Static24.componentDrawCount; local84++) {
						Static31.componentRedrawRequested2[local84] = false;
					}
				} catch (@Pc(453) Exception ex) {
					GameShell.canvas.repaint();
				}
			}
		}

		if (Static107.aBoolean147) {
			method3729();
		}

		if (Static164.aBoolean191 && state == 10 && Static154.anInt3711 != -1) {
			Static164.aBoolean191 = false;
			Static203.method3663(GameShell.signlink);
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(B)V")
	@Override
	protected final void mainquit() {
		if (GameShell.glRenderer) {
			Static239.method4169();
		}

		if (GameShell.aFrame2 != null) {
			Static25.method714(GameShell.aFrame2, GameShell.signlink);
			GameShell.aFrame2 = null;
		}

		if (GameShell.signlink != null) {
			GameShell.signlink.method5121(this.getClass());
		}

		if (mouseTracking != null) {
			mouseTracking.active = false;
		}
		mouseTracking = null;

		if (stream != null) {
			stream.close();
			stream = null;
		}

		Static31.shutdown(GameShell.canvas);
		Static223.shutdown(GameShell.canvas);
		if (mouseWheel != null) {
			mouseWheel.removeListeners(GameShell.canvas);
		}
		Static6.method82();
		Static251.method4277();
		mouseWheel = null;

		if (midiPcmPlayer != null) {
			midiPcmPlayer.shutdown();
		}

		if (soundPcmPlayer != null) {
			soundPcmPlayer.shutdown();
		}

		Static107.aClass73_3.shutdown();
		Static86.aClass80_3.shutdown();

		try {
			if (cacheDat != null) {
				cacheDat.method1455();
			}

			if (GameShell.cacheIndex != null) {
				for (@Pc(95) int local95 = 0; local95 < GameShell.cacheIndex.length; local95++) {
					if (GameShell.cacheIndex[local95] != null) {
						GameShell.cacheIndex[local95].method1455();
					}
				}
			}

			if (Static190.aClass38_5 != null) {
				Static190.aClass38_5.method1455();
			}

			if (Static121.aClass38_3 != null) {
				Static121.aClass38_3.method1455();
			}
		} catch (@Pc(129) IOException ignore) {
		}
	}

	@OriginalMember(owner = "client!client", name = "init", descriptor = "()V")
	@Override
	public final void init() {
		if (!this.method925()) {
			return;
		}

		worldid = Integer.parseInt(this.getParameter("worldid"));

		modewhere = Integer.parseInt(this.getParameter("modewhere"));
		if (modewhere < 0 || modewhere > 1) {
			modewhere = 0;
		}

		modewhat = Integer.parseInt(this.getParameter("modewhat"));
		if (modewhat < 0 || modewhat > 2) {
			modewhat = 0;
		}

		@Pc(50) String local50 = this.getParameter("advertsuppressed");
		if (local50 != null && local50.equals("1")) {
			advertsuppressed = true;
		} else {
			advertsuppressed = false;
		}

		try {
			lang = Integer.parseInt(this.getParameter("lang"));
		} catch (@Pc(69) Exception local69) {
			lang = 0;
		}
		Static3.setLang(lang);

		@Pc(78) String local78 = this.getParameter("objecttag");
		if (local78 != null && local78.equals("1")) {
			objecttag = true;
		} else {
			objecttag = false;
		}

		@Pc(94) String local94 = this.getParameter("js");
		if (local94 != null && local94.equals("1")) {
			js = true;
		} else {
			js = false;
		}

		@Pc(111) String local111 = this.getParameter("game");
		if (local111 != null && local111.equals("1")) {
			game = 1;
		} else {
			game = 0;
		}

		try {
			affid = Integer.parseInt(this.getParameter("affid"));
		} catch (@Pc(130) Exception local130) {
			affid = 0;
		}

		Static47.aClass100_991 = Static227.aClass100_966.method3153(this);
		if (Static47.aClass100_991 == null) {
			Static47.aClass100_991 = Static186.AUTO_EMPTY;
		}

		@Pc(146) String local146 = this.getParameter("country");
		if (local146 != null) {
			try {
				country = Integer.parseInt(local146);
			} catch (@Pc(153) Exception local153) {
				country = 0;
			}
		}

		@Pc(159) String local159 = this.getParameter("haveie6");
		if (local159 != null && local159.equals("1")) {
			haveie6 = true;
		} else {
			haveie6 = false;
		}

		Static215.client = this;
		this.startCommon(modewhat + 32);
	}

	@OriginalMember(owner = "client!client", name = "g", descriptor = "(I)V")
	@Override
	protected final void maininit() {
		Static203.method3662();
		Static86.aClass80_3 = new Js5CacheQueue();
		Static107.aClass73_3 = new Js5Net();
		if (modewhat != 0) {
			Static51.aByteArrayArray8 = new byte[50][];
		}
		Static80.method3615(GameShell.signlink);

		if (modewhere == 0) {
			loginHost = GlobalConfig.DEFAULT_HOSTNAME; // this.getCodeBase().getHost();
			loginJs5Port = GlobalConfig.ALTERNATE_PORT + 1; // 443;
			loginGamePort = GlobalConfig.DEFAULT_PORT + 1; // 43594;
		} else if (modewhere == 1) {
			loginHost = GlobalConfig.DEFAULT_HOSTNAME; // this.getCodeBase().getHost();
			loginJs5Port = GlobalConfig.ALTERNATE_PORT + worldid; // Static187.anInt4413 + 50000;
			loginGamePort = GlobalConfig.DEFAULT_PORT + worldid; // Static187.anInt4413 + 40000;
		} else if (modewhere == 2) {
			loginHost = GlobalConfig.DEFAULT_HOSTNAME; // "127.0.0.1";
			loginJs5Port = GlobalConfig.ALTERNATE_PORT + worldid; // Static187.anInt4413 + 50000;
			loginGamePort = GlobalConfig.DEFAULT_PORT + worldid; // Static187.anInt4413 + 40000;
		}

		if (game == 1) {
			Static172.aBoolean199 = true;
			Static161.anInt3923 = 16777215;
			Static161.anInt3922 = 0;

			PlayerModel.recol1s = RecolsRunescape.recol1s;
			PlayerModel.recol1d = RecolsRunescape.recol1d;
			PlayerModel.recol2s = RecolsRunescape.recol2s;
			PlayerModel.recol2d = RecolsRunescape.recol2d;
		} else {
			PlayerModel.recol1s = Static154.recol1s;
			PlayerModel.recol2d = Static195.recol1d;
			PlayerModel.recol1d = Static43.recol2s;
			PlayerModel.recol2s = Static260.recol2d;
		}

		anInt1738 = loginJs5Port;
		anInt5800 = loginGamePort;
		aString1 = loginHost;
		anInt4784 = loginGamePort;

		aShortArray88 = Static62.aShortArray19 = Static232.aShortArray74 = Static259.aShortArray87 = new short[256];
		anInt4794 = anInt4784;

		if ((SignLink.anInt5928 == 3 && modewhere != 2) || GlobalConfig.SELECT_DEFAULT_WORLD) {
			anInt3103 = worldid;
		}

		ClientKeyboardListener.setupKeyCodeMap();
		ClientKeyboardListener.addListeners(GameShell.canvas);

		ClientMouseListener.addListeners(GameShell.canvas);

		mouseWheel = Static44.method1150();
		if (mouseWheel != null) {
			mouseWheel.addListeners(GameShell.canvas);
		}

		Static7.anInt986 = SignLink.anInt5928;

		try {
			if (GameShell.signlink.cacheDat != null) {
				cacheDat = new BufferedRandomAccessFile(GameShell.signlink.cacheDat, 5200, 0);
				for (@Pc(162) int local162 = 0; local162 < 28; local162++) {
					GameShell.cacheIndex[local162] = new BufferedRandomAccessFile(GameShell.signlink.cacheIndex[local162], 6000, 0);
				}
				Static190.aClass38_5 = new BufferedRandomAccessFile(GameShell.signlink.masterIndex, 6000, 0);
				Static148.aClass49_4 = new DataFile(255, cacheDat, Static190.aClass38_5, 500000);
				Static121.aClass38_3 = new BufferedRandomAccessFile(GameShell.signlink.uidDat, 24, 0);

				GameShell.signlink.cacheIndex = null;
				GameShell.signlink.masterIndex = null;
				GameShell.signlink.uidDat = null;
				GameShell.signlink.cacheDat = null;
			}
		} catch (@Pc(220) IOException ex) {
			Static121.aClass38_3 = null;
			cacheDat = null;
			Static190.aClass38_5 = null;
			Static148.aClass49_4 = null;
		}

		Static278.aClass100_1102 = Text.aClass100_370;

		if (modewhere != 0) {
			Static43.aBoolean82 = true;
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(I)V")
	@Override
	protected final void onKilled() {
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(ZI)V")
	private void js5error(@OriginalArg(1) int arg0) {
		Static107.aClass73_3.js5Errors++;
		js5SocketReq = null;
		Static107.aClass73_3.anInt2963 = arg0;
		js5Stream = null;
		js5ConnectState = 0;
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(B)V")
	private void method943() {
		for (Static182.anInt4313 = 0; Static25.method712() && Static182.anInt4313 < 128; Static182.anInt4313++) {
			Static227.anIntArray447[Static182.anInt4313] = Static102.anInt2681;
			Static205.anIntArray426[Static182.anInt4313] = Static193.anInt4542;
		}
		Static178.anInt4247++;
		if (Static154.anInt3711 != -1) {
			Static57.method1320(0, 0, 0, GameShell.anInt1448, Static154.anInt3711, 0, GameShell.anInt5554);
		}
		anInt3028++;
		if (GameShell.glRenderer) {
			label191: for (@Pc(57) int local57 = 0; local57 < 32768; local57++) {
				@Pc(66) ClientNPC local66 = Static175.aClass8_Sub4_Sub2Array1[local57];
				if (local66 != null) {
					@Pc(73) byte local73 = local66.aClass96_1.aByte10;
					if ((local73 & 0x2) > 0 && local66.anInt3409 == 0 && Math.random() * 1000.0D < 10.0D) {
						@Pc(98) int local98 = (int) Math.round(Math.random() * 2.0D - 1.0D);
						@Pc(106) int local106 = (int) Math.round(Math.random() * 2.0D - 1.0D);
						if (local98 != 0 || local106 != 0) {
							local66.aByteArray48[0] = 1;
							local66.anIntArray318[0] = local98 + (local66.anInt3412 >> 7);
							local66.anIntArray317[0] = local106 + (local66.anInt3421 >> 7);
							levelCollisionMap[Static55.anInt1735].method3056(local66.anInt3412 >> 7, local66.method2693(), false, 0, local66.method2693(), local66.anInt3421 >> 7);
							if (local66.anIntArray318[0] >= 0 && local66.anIntArray318[0] <= 104 - local66.method2693() && local66.anIntArray317[0] >= 0 && local66.anIntArray317[0] <= 104 - local66.method2693() && levelCollisionMap[Static55.anInt1735].method3054(local66.anInt3421 >> 7, local66.anIntArray317[0], local66.anIntArray318[0], local66.anInt3412 >> 7)) {
								if (local66.method2693() > 1) {
									for (@Pc(226) int local226 = local66.anIntArray318[0]; local66.anIntArray318[0] + local66.method2693() > local226; local226++) {
										for (@Pc(246) int local246 = local66.anIntArray317[0]; local66.anIntArray317[0] + local66.method2693() > local246; local246++) {
											if ((levelCollisionMap[Static55.anInt1735].anIntArrayArray30[local226][local246] & 0x12401FF) != 0) {
												continue label191;
											}
										}
									}
								}
								local66.anInt3409 = 1;
							}
						}
					}
					Static104.method2247(local66);
					Static37.method949(local66);
					Static34.method879(local66);
					levelCollisionMap[Static55.anInt1735].method3043(local66.anInt3412 >> 7, false, local66.anInt3421 >> 7, local66.method2693(), local66.method2693());
				}
			}
		}
		if (!GameShell.glRenderer) {
			Static269.method2170();
		} else if (Static184.anInt4348 == 0 && Static179.anInt4261 == 0) {
			if (Static227.anInt5096 == 2) {
				Static125.method2450();
			} else {
				Static40.method1008();
			}
			if (Static138.anInt3439 >> 7 < 14 || Static138.anInt3439 >> 7 >= 90 || Static134.anInt3302 >> 7 < 14 || Static134.anInt3302 >> 7 >= 90) {
				Static26.method740();
			}
		}
		while (true) {
			@Pc(374) HookReq local374;
			@Pc(379) IfType local379;
			@Pc(387) IfType local387;
			do {
				local374 = (HookReq) Static4.aClass69_2.method2287();
				if (local374 == null) {
					while (true) {
						do {
							local374 = (HookReq) Static115.aClass69_70.method2287();
							if (local374 == null) {
								while (true) {
									do {
										local374 = (HookReq) Static185.aClass69_101.method2287();
										if (local374 == null) {
											if (Static105.aClass13_14 != null) {
												Static4.method28();
											}
											if (Static33.aClass212_1 != null && Static33.aClass212_1.status == 1) {
												if (Static33.aClass212_1.result != null) {
													Static169.method3175(Static175.aClass100_797, Static164.aBoolean194);
												}
												Static164.aBoolean194 = false;
												Static175.aClass100_797 = null;
												Static33.aClass212_1 = null;
											}
											if (Static83.anInt372 % 1500 == 0) {
												Static123.method2418();
											}
											return;
										}
										local379 = local374.component;
										if (local379.subId < 0) {
											break;
										}
										local387 = Static5.method32(local379.layerId);
									} while (local387 == null || local387.aClass13Array3 == null || local387.aClass13Array3.length <= local379.subId || local379 != local387.aClass13Array3[local379.subId]);
									Static82.method1767(local374);
								}
							}
							local379 = local374.component;
							if (local379.subId < 0) {
								break;
							}
							local387 = Static5.method32(local379.layerId);
						} while (local387 == null || local387.aClass13Array3 == null || local379.subId >= local387.aClass13Array3.length || local379 != local387.aClass13Array3[local379.subId]);
						Static82.method1767(local374);
					}
				}
				local379 = local374.component;
				if (local379.subId < 0) {
					break;
				}
				local387 = Static5.method32(local379.layerId);
			} while (local387 == null || local387.aClass13Array3 == null || local387.aClass13Array3.length <= local379.subId || local379 != local387.aClass13Array3[local379.subId]);
			Static82.method1767(local374);
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(Z)V")
	private void method944() {
		@Pc(3) boolean local3 = Static107.aClass73_3.loop();
		if (!local3) {
			this.js5connect();
		}
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(I)V")
	private void js5connect() {
		if (Static233.anInt5226 < Static107.aClass73_3.js5Errors) {
			js5ConnectCooldown = 5 * 50 * (Static107.aClass73_3.js5Errors - 1);

			if (anInt5800 == anInt4794) {
				anInt4794 = anInt1738;
			} else {
				anInt4794 = anInt5800;
			}

			if (js5ConnectCooldown > 3000) {
				js5ConnectCooldown = 3000;
			}

			if (Static107.aClass73_3.js5Errors >= 2 && Static107.aClass73_3.anInt2963 == 6) {
				this.error("js5connect_outofdate");
				state = 1000;
				return;
			}

			if (Static107.aClass73_3.js5Errors >= 4 && Static107.aClass73_3.anInt2963 == -1) {
				this.error("js5crc");
				state = 1000;
				return;
			}

			if (Static107.aClass73_3.js5Errors >= 4 && (state == 0 || state == 5)) {
				if (Static107.aClass73_3.anInt2963 == 7 || Static107.aClass73_3.anInt2963 == 9) {
					this.error("js5connect_full");
				} else if (Static107.aClass73_3.anInt2963 > 0) {
					this.error("js5connect");
				} else {
					this.error("js5io");
				}

				state = 1000;
				return;
			}
		}

		Static233.anInt5226 = Static107.aClass73_3.js5Errors;

		if (js5ConnectCooldown > 0) {
			js5ConnectCooldown--;
			return;
		}

		try {
			if (js5ConnectState == 0) {
				js5SocketReq = GameShell.signlink.socketreq(aString1, anInt4794);
				js5ConnectState++;
			}

			if (js5ConnectState == 1) {
				if (js5SocketReq.status == 2) {
					this.js5error(1000);
					return;
				}

				if (js5SocketReq.status == 1) {
					js5ConnectState++;
				}
			}

			if (js5ConnectState == 2) {
				js5Stream = new ClientStream((Socket) js5SocketReq.result, GameShell.signlink);
				@Pc(194) Packet local194 = new Packet(5);
				local194.p1(15);
				local194.p4(530);
				js5Stream.write(local194.data, 5);
				js5ConnectState++;
				js5ConnectTime = MonotonicTime.currentTime();
			}

			if (js5ConnectState == 3) {
				if (state == 0 || state == 5 || js5Stream.available() > 0) {
					@Pc(258) int local258 = js5Stream.method2828();
					if (local258 != 0) {
						this.js5error(local258);
						return;
					}
					js5ConnectState++;
				} else if (MonotonicTime.currentTime() - js5ConnectTime > 30000L) {
					this.js5error(1001);
					return;
				}
			}

			if (js5ConnectState == 4) {
				@Pc(296) boolean local296 = state == 5 || state == 10 || state == 28;
				Static107.aClass73_3.init(!local296, js5Stream);
				js5Stream = null;
				js5SocketReq = null;
				js5ConnectState = 0;
			}
		} catch (@Pc(315) IOException ex) {
			this.js5error(1002);
		}
	}

	@OriginalMember(owner = "client!client", name = "i", descriptor = "(I)V")
	private void mainLoad() {
		if (!Static164.aBoolean191) {
			label252: while (true) {
				do {
					if (!Static25.method712()) {
						break label252;
					}
				} while (Static193.anInt4542 != 115 && Static193.anInt4542 != 83);
				Static164.aBoolean191 = true;
			}
		}

		@Pc(43) int local43;
		if (loadingStep == 0) {
			@Pc(34) Runtime local34 = Runtime.getRuntime();
			local43 = (int) (0L / 1024L);
			@Pc(46) long local46 = MonotonicTime.currentTime();
			if (Static175.aLong138 == 0L) {
				Static175.aLong138 = local46;
			}
			if (local43 > 16384 && local46 - Static175.aLong138 < 5000L) {
				if (local46 - Static160.aLong134 > 1000L) {
					System.gc();
					Static160.aLong134 = local46;
				}
				TitleScreen.loadPos = 5;
				TitleScreen.loadString = Text.aClass100_626;
			} else {
				TitleScreen.loadString = Text.aClass100_769;
				loadingStep = 10;
				TitleScreen.loadPos = 5;
			}
			return;
		}

		@Pc(98) int local98;
		if (loadingStep == 10) {
			World.create();

			for (int i = 0; i < 4; i++) {
				levelCollisionMap[i] = new CollisionMap(104, 104);
			}

			TitleScreen.loadPos = 10;
			loadingStep = 30;
			TitleScreen.loadString = Text.MAINLOAD10;
		} else if (loadingStep == 30) {
			if (js5Loader == null) {
				js5Loader = new Js5Loader(Static107.aClass73_3, Static86.aClass80_3);
			}

            if (!js5Loader.method178()) {
                TitleScreen.loadString = Text.MAINLOAD30;
                TitleScreen.loadPos = 12;
            } else {
                anims = openJs5(false, true, true, 0);
                bases = openJs5(false, true, true, 1);
                config = openJs5(true, true, false, 2);
                interfaces = openJs5(false, true, true, 3);
                jagFX = openJs5(false, true, true, 4);
                maps = openJs5(true, true, true, 5);
                songs = openJs5(true, false, true, 6);
                models = openJs5(false, true, true, 7);
                sprites = openJs5(false, true, true, 8);
                textures = openJs5(false, true, true, 9);
                binary = openJs5(false, true, true, 10);
                jingles = openJs5(false, true, true, 11);
                scripts = openJs5(false, true, true, 12);
                fontMetrics = openJs5(false, true, true, 13);
                vorbis = openJs5(false, false, true, 14);
                patches = openJs5(false, true, true, 15);
                locConfig = openJs5(false, true, true, 16);
                enumConfig = openJs5(false, true, true, 17);
                npcConfig = openJs5(false, true, true, 18);
                objConfig = openJs5(false, true, true, 19);
                seqConfig = openJs5(false, true, true, 20);
                spotConfig = openJs5(false, true, true, 21);
                varbitConfig = openJs5(false, true, true, 22);
                worldmap = openJs5(true, true, true, 23);
                quickchat = openJs5(false, true, true, 24);
                quickchatGlobal = openJs5(false, true, true, 25);
                materials = openJs5(true, true, true, 26);
                particleConfig = openJs5(false, true, true, 27);

                TitleScreen.loadPos = 15;
                TitleScreen.loadString = Text.MAINLOAD30B;
                loadingStep = 40;
            }
        } else if (loadingStep == 40) {
			int total = 0;
			for (int i = 0; i < 28; i++) {
				total += Static269.js5Providers[i].method538() * Static170.anIntArray306[i] / 100;
			}

            if (total != 100) {
                if (total != 0) {
                    TitleScreen.loadString = JagString.join(new JagString[] { Text.MAINLOAD40, JagString.parseInt(total), AUTO_PERCENT});
                }
                TitleScreen.loadPos = 20;
            } else {
                TitleScreen.loadPos = 20;
                TitleScreen.loadString = Text.MAINLOAD40B;
                Static75.method1635(sprites);
                Static167.method3172(sprites);
                Static81.method1754(sprites);
                loadingStep = 45;
            }
        } else if (loadingStep == 45) {
			Static41.init(lowMem);

			midiPlayer = new MidiPlayer();
			midiPlayer.setChannelDefaultPatch();

			midiPcmPlayer = Static107.getPlayer(22050, GameShell.signlink, GameShell.canvas, 0);
			midiPcmPlayer.playStream(midiPlayer);

			Static34.init(midiPlayer, patches, vorbis, jagFX);

			soundPcmPlayer = Static107.getPlayer(2048, GameShell.signlink, GameShell.canvas, 1);
			soundMixer = new Mixer();
			soundPcmPlayer.playStream(soundMixer);
			soundDecimator = new Decimator(22050, Static44.frequency);

			Static250.anInt5441 = songs.getGroupId(Static1.TITLESONG);

			TitleScreen.loadPos = 30;
			loadingStep = 50;
			TitleScreen.loadString = Text.MAINLOAD45;
		} else if (loadingStep == 50) {
			local98 = Static74.method1628(sprites, fontMetrics);
			local43 = Static143.method2732();

			if (local98 >= local43) {
				TitleScreen.loadString = Text.aClass100_1016;
				TitleScreen.loadPos = 35;
				loadingStep = 60;
			} else {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_690, JagString.parseInt(local98 * 100 / local43), AUTO_PERCENT});
				TitleScreen.loadPos = 35;
			}
		} else if (loadingStep == 60) {
			local98 = Static150.method2797(sprites);
			local43 = Static104.method2252();

			if (local43 <= local98) {
				TitleScreen.loadString = Text.aClass100_166;
				loadingStep = 65;
				TitleScreen.loadPos = 40;
			} else {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_348, JagString.parseInt(local98 * 100 / local43), AUTO_PERCENT});
				TitleScreen.loadPos = 40;
			}
		} else if (loadingStep == 65) {
			Static102.method2074(fontMetrics, sprites);

			TitleScreen.loadPos = 45;
			TitleScreen.loadString = Text.aClass100_347;
			Static196.method3534(5);
			loadingStep = 70;
		} else if (loadingStep == 70) {
			config.method4475();
			local98 = config.method4498();
			locConfig.method4475();
			local98 += locConfig.method4498();
			enumConfig.method4475();
			local98 += enumConfig.method4498();
			npcConfig.method4475();
			local98 += npcConfig.method4498();
			objConfig.method4475();
			local98 += objConfig.method4498();
			seqConfig.method4475();
			local98 += seqConfig.method4498();
			spotConfig.method4475();
			local98 += spotConfig.method4498();
			varbitConfig.method4475();
			local98 += varbitConfig.method4498();
			quickchat.method4475();
			local98 += quickchat.method4498();
			quickchatGlobal.method4475();
			local98 += quickchatGlobal.method4498();
			particleConfig.method4475();
			local98 += particleConfig.method4498();

			if (local98 >= 1100) {
				ParamType.init(config);
				FloType.init(config);
				FluType.init(config);
				IdkType.init(models, config);
				LocType.init(locConfig, models);
				NPCType.init(models, npcConfig);
				ObjType.init(objConfig, Static265.aClass3_Sub2_Sub9_Sub1_2, models);
				StructType.init(config);
				SeqType.init(bases, seqConfig, anims);
				BasType.init(config);
				SpotType.init(models, spotConfig);
				VarBitType.init(varbitConfig);
				VarpType.init(config);
				IfType.init(fontMetrics, sprites, interfaces, models);
				InvType.init(config);
				EnumType.init(enumConfig);
				QuickChatPhraseType.init(quickchatGlobal, quickchat, new Js5QuickChatCommandDecoder());
				QuickChatCatType.init(quickchatGlobal, quickchat);
				LightType.init(config);
				CursorType.init(config, sprites);
				MsiType.init(config, sprites);

				TitleScreen.loadPos = 50;
				TitleScreen.loadString = Text.MAINLOAD70;
				Static58.method1321();
				loadingStep = 80;
			} else {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_124, JagString.parseInt(local98 / 11), AUTO_PERCENT});
				TitleScreen.loadPos = 50;
			}
		} else if (loadingStep == 80) {
			local98 = Static28.method789(sprites);
			local43 = Static62.method1483();

			if (local43 > local98) {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_1073, JagString.parseInt(local98 * 100 / local43), AUTO_PERCENT});
				TitleScreen.loadPos = 60;
			} else {
				Static30.method839(sprites);
				loadingStep = 90;
				TitleScreen.loadPos = 60;
				TitleScreen.loadString = Text.MAINLOAD80;
			}
		} else if (loadingStep == 90) {
			if (materials.method4475()) {
				@Pc(951) WorldTextureProvider local951 = new WorldTextureProvider(textures, materials, sprites, 20, !Static53.aBoolean99);
				Static94.method1914(local951);
				if (Static113.anInt4609 == 1) {
					Static94.method1911(0.9F);
				}
				if (Static113.anInt4609 == 2) {
					Static94.method1911(0.8F);
				}
				if (Static113.anInt4609 == 3) {
					Static94.method1911(0.7F);
				}
				if (Static113.anInt4609 == 4) {
					Static94.method1911(0.6F);
				}

				TitleScreen.loadString = Text.MAINLOAD90;
				loadingStep = 100;
				TitleScreen.loadPos = 70;
			} else {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_1032, JagString.parseInt(materials.method4498()), AUTO_PERCENT});
				TitleScreen.loadPos = 70;
			}
		} else if (loadingStep == 100) {
			if (Static231.method3986(sprites)) {
				loadingStep = 110;
			}
		} else if (loadingStep == 110) {
			mouseTracking = new MouseTracking();
			GameShell.signlink.threadreq(10, mouseTracking);

			TitleScreen.loadString = Text.MAINLOAD110;
			TitleScreen.loadPos = 75;
			loadingStep = 120;
		} else if (loadingStep == 120) {
			if (binary.requestDownload(Static186.AUTO_EMPTY, Static252.AUTO_HUFFMAN)) {
				@Pc(1060) Huffman local1060 = new Huffman(binary.getFile(Static186.AUTO_EMPTY, Static252.AUTO_HUFFMAN));
				Static1.setHuffman(local1060);

				TitleScreen.loadString = Text.MAINLOAD120B;
				loadingStep = 130;
				TitleScreen.loadPos = 80;
			} else {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.MAINLOAD120, Static206.PERCENT});
				TitleScreen.loadPos = 80;
			}
		} else if (loadingStep == 130) {
			if (!interfaces.method4475()) {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_104, JagString.parseInt(interfaces.method4498() * 3 / 4), AUTO_PERCENT});
				TitleScreen.loadPos = 85;
			} else if (!scripts.method4475()) {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_104, JagString.parseInt(scripts.method4498() / 10 + 75), AUTO_PERCENT});
				TitleScreen.loadPos = 85;
			} else if (!fontMetrics.method4475()) {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_104, JagString.parseInt(fontMetrics.method4498() / 20 + 85), AUTO_PERCENT});
				TitleScreen.loadPos = 85;
			} else if (!worldmap.method4489(Static165.aClass100_777)) {
				TitleScreen.loadString = JagString.join(new JagString[] { Text.aClass100_104, JagString.parseInt(worldmap.method4478(Static165.aClass100_777) / 10 + 90), AUTO_PERCENT});
				TitleScreen.loadPos = 85;
			} else {
				Static234.method4018(Static173.aClass3_Sub2_Sub1_Sub1Array9, worldmap);
				TitleScreen.loadPos = 95;
				TitleScreen.loadString = Text.aClass100_555;
				loadingStep = 135;
			}
        } else if (loadingStep == 135) {
			local98 = Static207.method3684();
			if (local98 == -1) {
				TitleScreen.loadPos = 95;
				TitleScreen.loadString = Text.aClass100_906;
			} else if (local98 == 7 || local98 == 9) {
				this.error("worldlistfull");
				Static196.method3534(1000);
			} else if (!Static61.aBoolean109) {
				this.error("worldlistio_" + local98);
				Static196.method3534(1000);
			} else {
				TitleScreen.loadString = Text.aClass100_201;
				loadingStep = 140;
				TitleScreen.loadPos = 96;
			}
        } else if (loadingStep == 140) {
			Static156.anInt3783 = interfaces.getGroupId(Static138.aClass100_652);
			maps.method4477(false);
			songs.method4477(true);
			sprites.method4477(true);
			fontMetrics.method4477(true);
			binary.method4477(true);
			interfaces.method4477(true);
			TitleScreen.loadPos = 97;
			TitleScreen.loadString = Text.aClass100_240;
			loadingStep = 150;
			Static107.aBoolean147 = true;
		} else if (loadingStep == 150) {
			Static151.method2807();
			if (Static164.aBoolean191) {
				Static102.anInt2679 = 0;
				Static186.anInt4392 = 0;
				Static214.anInt5581 = 0;
				Static141.anInt3474 = 0;
			}
			Static164.aBoolean191 = true;
			Static203.method3663(GameShell.signlink);
			Static241.method4540(false, Static214.anInt5581, -1, -1);
			TitleScreen.loadPos = 100;
			loadingStep = 160;
			TitleScreen.loadString = Text.aClass100_1064;
		} else if (loadingStep == 160) {
			Static73.method1596(true);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(B)V")
	@Override
	protected final void mainloop() {
		if (state == 1000) {
			return;
		}

		Static83.anInt372++;
		if (Static83.anInt372 % 1000 == 1) {
			@Pc(24) GregorianCalendar local24 = new GregorianCalendar();
			Static60.anInt1895 = local24.get(11) * 600 + local24.get(12) * 10 + local24.get(13) / 6;
			Static39.aRandom1.setSeed((long) Static60.anInt1895);
		}

		this.method944();

		if (js5Loader != null) {
			js5Loader.method179();
		}

		Static230.method3948();
		doAudio();
		Static65.method1501();
		Static111.method2292();

		if (GameShell.glRenderer) {
			Static63.method1490();
		}

		if (mouseWheel != null) {
			@Pc(75) int rotation = mouseWheel.getRotation();
			mouseWheelRotation = rotation;
		}

		if (state == 0) {
			this.mainLoad();
			GameShell.doneslowupdate();
		} else if (state == 5) {
			this.mainLoad();
			GameShell.doneslowupdate();
		} else if (state == 25 || state == 28) {
			Static78.method1696();
		}

		if (state == 10) {
			this.method943();
			Static158.method3008();
			Static31.method848();
			Static216.method1639();
		} else if (state == 30) {
			Static81.method1756();
		} else if (state == 40) {
			Static216.method1639();

			if (Static266.anInt5336 != -3) {
				if (Static266.anInt5336 == 15) {
					Static44.method1146();
				} else if (Static266.anInt5336 != 2) {
					Static278.method4653();
				}
			}
		}
	}
}
