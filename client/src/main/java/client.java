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
public final class client extends GameShell {

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
				client.lang = lang;
			} else if (args[2].equals("english")) {
				client.lang = 0;
			} else if (args[2].equals("german")) {
				client.lang = 1;
			} else {
				Static131.method2577("language");
			}
			Static3.setLang(client.lang);

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

			Static47.aClass100_991 = Static186.EMPTY_STRING;

			@Pc(146) client app = new client();
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
		if (Static172.aClass38_4 != null) {
			local7 = new DataFile(arg3, Static172.aClass38_4, Static47.aClass38Array2[arg3], 1000000);
		}
		Static269.aClass14_Sub1Array3[arg3] = Static257.aClass9_2.method180(arg3, Static148.aClass49_4, local7);
		if (arg1) {
			Static269.aClass14_Sub1Array3[arg3].method528();
		}
		return new Js5(Static269.aClass14_Sub1Array3[arg3], arg0, arg2);
	}

	@OriginalMember(owner = "client!id", name = "b", descriptor = "(I)V")
	public static void method2261() {
		if (soundPcmPlayer != null) {
			soundPcmPlayer.method3565();
		}
		if (midiPcmPlayer != null) {
			midiPcmPlayer.method3565();
		}
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
			GameShell.drawProgress(null, local158, Static126.loadString, Static199.loadPos);
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
				Static114.messageBox(false, Static34.concatenate(new JagString[] { Static170.aClass100_621, Static229.aClass100_974, Static123.method2423(local80), Static14.aClass100_80 }));
			} else if (Static233.mapLoadingStage == 2) {
				if (Static38.locModelLoadPrevCount < Static271.locModelLoadCount) {
					Static38.locModelLoadPrevCount = Static271.locModelLoadCount;
				}

				local80 = (Static38.locModelLoadPrevCount - Static271.locModelLoadCount) * 50 / Static38.locModelLoadPrevCount + 50;
				Static114.messageBox(false, Static34.concatenate(new JagString[] { Static170.aClass100_621, Static229.aClass100_974, Static123.method2423(local80), Static14.aClass100_80 }));
			} else {
				Static114.messageBox(false, Static170.aClass100_621);
			}
		} else if (state == 30) {
			Static89.gameDraw();
		} else if (state == 40) {
			Static114.messageBox(false, Static34.concatenate(new JagString[] { Static232.aClass100_986, Static269.aClass100_556, Static262.aClass100_1077 }));
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
			Static213.method3729();
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

		if (Static124.loginStream != null) {
			Static124.loginStream.close();
			Static124.loginStream = null;
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
			if (Static172.aClass38_4 != null) {
				Static172.aClass38_4.method1455();
			}

			if (Static47.aClass38Array2 != null) {
				for (@Pc(95) int local95 = 0; local95 < Static47.aClass38Array2.length; local95++) {
					if (Static47.aClass38Array2[local95] != null) {
						Static47.aClass38Array2[local95].method1455();
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
			Static47.aClass100_991 = Static186.EMPTY_STRING;
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
			Static143.loginHost = GlobalConfig.DEFAULT_HOSTNAME; // this.getCodeBase().getHost();
			Static97.loginJs5Port = GlobalConfig.ALTERNATE_PORT + 1; // 443;
			Static249.loginGamePort = GlobalConfig.DEFAULT_PORT + 1; // 43594;
		} else if (modewhere == 1) {
			Static143.loginHost = GlobalConfig.DEFAULT_HOSTNAME; // this.getCodeBase().getHost();
			Static97.loginJs5Port = GlobalConfig.ALTERNATE_PORT + worldid; // Static187.anInt4413 + 50000;
			Static249.loginGamePort = GlobalConfig.DEFAULT_PORT + worldid; // Static187.anInt4413 + 40000;
		} else if (modewhere == 2) {
			Static143.loginHost = GlobalConfig.DEFAULT_HOSTNAME; // "127.0.0.1";
			Static97.loginJs5Port = GlobalConfig.ALTERNATE_PORT + worldid; // Static187.anInt4413 + 50000;
			Static249.loginGamePort = GlobalConfig.DEFAULT_PORT + worldid; // Static187.anInt4413 + 40000;
		}

		if (game == 1) {
			Static172.aBoolean199 = true;
			Static161.anInt3923 = 16777215;
			Static161.anInt3922 = 0;
			Static33.aShortArrayArray2 = Static198.aShortArrayArray4;
			Static172.aShortArrayArray7 = Static32.aShortArrayArray1;
			Static200.aShortArray65 = Static2.aShortArray1;
			Static160.aShortArray41 = Static20.aShortArray5;
		} else {
			Static33.aShortArrayArray2 = Static154.aShortArrayArray6;
			Static160.aShortArray41 = Static195.aShortArray64;
			Static172.aShortArrayArray7 = Static43.aShortArrayArray5;
			Static200.aShortArray65 = Static260.aShortArray71;
		}

		Static55.anInt1738 = Static97.loginJs5Port;
		Static271.anInt5800 = Static249.loginGamePort;
		Static60.aString1 = Static143.loginHost;
		Static208.anInt4784 = Static249.loginGamePort;

		Static259.aShortArray88 = Static62.aShortArray19 = Static232.aShortArray74 = Static259.aShortArray87 = new short[256];
		Static209.anInt4794 = Static208.anInt4784;
		if ((SignLink.anInt5928 == 3 && modewhere != 2) || GlobalConfig.SELECT_DEFAULT_WORLD) {
			Static125.anInt3103 = worldid;
		}
		Static156.method2958();
		Static19.method591(GameShell.canvas);
		Static88.method1833(GameShell.canvas);
		mouseWheel = Static44.method1150();
		if (mouseWheel != null) {
			mouseWheel.addListeners(GameShell.canvas);
		}
		Static7.anInt986 = SignLink.anInt5928;

		try {
			if (GameShell.signlink.cacheDat != null) {
				Static172.aClass38_4 = new BufferedRandomAccessFile(GameShell.signlink.cacheDat, 5200, 0);
				for (@Pc(162) int local162 = 0; local162 < 28; local162++) {
					Static47.aClass38Array2[local162] = new BufferedRandomAccessFile(GameShell.signlink.cacheIndex[local162], 6000, 0);
				}
				Static190.aClass38_5 = new BufferedRandomAccessFile(GameShell.signlink.masterIndex, 6000, 0);
				Static148.aClass49_4 = new DataFile(255, Static172.aClass38_4, Static190.aClass38_5, 500000);
				Static121.aClass38_3 = new BufferedRandomAccessFile(GameShell.signlink.uidDat, 24, 0);

				GameShell.signlink.cacheIndex = null;
				GameShell.signlink.masterIndex = null;
				GameShell.signlink.uidDat = null;
				GameShell.signlink.cacheDat = null;
			}
		} catch (@Pc(220) IOException ex) {
			Static121.aClass38_3 = null;
			Static172.aClass38_4 = null;
			Static190.aClass38_5 = null;
			Static148.aClass49_4 = null;
		}

		Static278.aClass100_1102 = Static53.aClass100_370;

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
		Static119.anInt3028++;
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
							Static148.aClass97Array1[Static55.anInt1735].method3056(local66.anInt3412 >> 7, local66.method2693(), false, 0, local66.method2693(), local66.anInt3421 >> 7);
							if (local66.anIntArray318[0] >= 0 && local66.anIntArray318[0] <= 104 - local66.method2693() && local66.anIntArray317[0] >= 0 && local66.anIntArray317[0] <= 104 - local66.method2693() && Static148.aClass97Array1[Static55.anInt1735].method3054(local66.anInt3421 >> 7, local66.anIntArray317[0], local66.anIntArray318[0], local66.anInt3412 >> 7)) {
								if (local66.method2693() > 1) {
									for (@Pc(226) int local226 = local66.anIntArray318[0]; local66.anIntArray318[0] + local66.method2693() > local226; local226++) {
										for (@Pc(246) int local246 = local66.anIntArray317[0]; local66.anIntArray317[0] + local66.method2693() > local246; local246++) {
											if ((Static148.aClass97Array1[Static55.anInt1735].anIntArrayArray30[local226][local246] & 0x12401FF) != 0) {
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
					Static148.aClass97Array1[Static55.anInt1735].method3043(local66.anInt3412 >> 7, false, local66.anInt3421 >> 7, local66.method2693(), local66.method2693());
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
										local379 = local374.aClass13_17;
										if (local379.anInt457 < 0) {
											break;
										}
										local387 = Static5.method32(local379.layerId);
									} while (local387 == null || local387.aClass13Array3 == null || local387.aClass13Array3.length <= local379.anInt457 || local379 != local387.aClass13Array3[local379.anInt457]);
									Static82.method1767(local374);
								}
							}
							local379 = local374.aClass13_17;
							if (local379.anInt457 < 0) {
								break;
							}
							local387 = Static5.method32(local379.layerId);
						} while (local387 == null || local387.aClass13Array3 == null || local379.anInt457 >= local387.aClass13Array3.length || local379 != local387.aClass13Array3[local379.anInt457]);
						Static82.method1767(local374);
					}
				}
				local379 = local374.aClass13_17;
				if (local379.anInt457 < 0) {
					break;
				}
				local387 = Static5.method32(local379.layerId);
			} while (local387 == null || local387.aClass13Array3 == null || local387.aClass13Array3.length <= local379.anInt457 || local379 != local387.aClass13Array3[local379.anInt457]);
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

			if (Static271.anInt5800 == Static209.anInt4794) {
				Static209.anInt4794 = Static55.anInt1738;
			} else {
				Static209.anInt4794 = Static271.anInt5800;
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
				js5SocketReq = GameShell.signlink.socketreq(Static60.aString1, Static209.anInt4794);
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
				Static199.loadPos = 5;
				Static126.loadString = Static131.aClass100_626;
			} else {
				Static126.loadString = Static164.aClass100_769;
				loadingStep = 10;
				Static199.loadPos = 5;
			}
			return;
		}

		@Pc(98) int local98;
		if (loadingStep == 10) {
			Static120.method2392();

			for (int i = 0; i < 4; i++) {
				Static148.aClass97Array1[i] = new CollisionMap(104, 104);
			}

			Static199.loadPos = 10;
			loadingStep = 30;
			Static126.loadString = Static113.MAINLOAD10;
		} else if (loadingStep == 30) {
			if (Static257.aClass9_2 == null) {
				Static257.aClass9_2 = new Js5Loader(Static107.aClass73_3, Static86.aClass80_3);
			}

			if (Static257.aClass9_2.method178()) {
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

				Static199.loadPos = 15;
				Static126.loadString = Static178.MAINLOAD30B;
				loadingStep = 40;
			} else {
				Static126.loadString = Static265.MAINLOAD30;
				Static199.loadPos = 12;
			}
		} else if (loadingStep == 40) {
			local98 = 0;
			for (local43 = 0; local43 < 28; local43++) {
				local98 += Static269.aClass14_Sub1Array3[local43].method538() * Static170.anIntArray306[local43] / 100;
			}

			if (local98 == 100) {
				Static199.loadPos = 20;
				Static126.loadString = Static11.MAINLOAD40B;
				Static75.method1635(sprites);
				Static167.method3172(sprites);
				Static81.method1754(sprites);
				loadingStep = 45;
			} else {
				if (local98 != 0) {
					Static126.loadString = Static34.concatenate(new JagString[] { Static23.MAINLOAD40, Static123.method2423(local98), Static49.aClass100_352 });
				}
				Static199.loadPos = 20;
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

			Static199.loadPos = 30;
			loadingStep = 50;
			Static126.loadString = Static225.aClass100_964;
		} else if (loadingStep == 50) {
			local98 = Static74.method1628(sprites, fontMetrics);
			local43 = Static143.method2732();

			if (local98 >= local43) {
				Static126.loadString = Static244.aClass100_1016;
				Static199.loadPos = 35;
				loadingStep = 60;
			} else {
				Static126.loadString = Static34.concatenate(new JagString[] { Static150.aClass100_690, Static123.method2423(local98 * 100 / local43), Static49.aClass100_352 });
				Static199.loadPos = 35;
			}
		} else if (loadingStep == 60) {
			local98 = Static150.method2797(sprites);
			local43 = Static104.method2252();

			if (local43 <= local98) {
				Static126.loadString = Static27.aClass100_166;
				loadingStep = 65;
				Static199.loadPos = 40;
			} else {
				Static126.loadString = Static34.concatenate(new JagString[] { Static49.aClass100_348, Static123.method2423(local98 * 100 / local43), Static49.aClass100_352 });
				Static199.loadPos = 40;
			}
		} else if (loadingStep == 65) {
			Static102.method2074(fontMetrics, sprites);

			Static199.loadPos = 45;
			Static126.loadString = Static48.aClass100_347;
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

				Static199.loadPos = 50;
				Static126.loadString = Static74.MAINLOAD70;
				Static58.method1321();
				loadingStep = 80;
			} else {
				Static126.loadString = Static34.concatenate(new JagString[] { Static21.aClass100_124, Static123.method2423(local98 / 11), Static49.aClass100_352 });
				Static199.loadPos = 50;
			}
		} else if (loadingStep == 80) {
			local98 = Static28.method789(sprites);
			local43 = Static62.method1483();

			if (local43 > local98) {
				Static126.loadString = Static34.concatenate(new JagString[] { Static259.aClass100_1073, Static123.method2423(local98 * 100 / local43), Static49.aClass100_352 });
				Static199.loadPos = 60;
			} else {
				Static30.method839(sprites);
				loadingStep = 90;
				Static199.loadPos = 60;
				Static126.loadString = Static223.MAINLOAD80;
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

				Static126.loadString = Static86.MAINLOAD90;
				loadingStep = 100;
				Static199.loadPos = 70;
			} else {
				Static126.loadString = Static34.concatenate(new JagString[] { Static247.aClass100_1032, Static123.method2423(materials.method4498()), Static49.aClass100_352 });
				Static199.loadPos = 70;
			}
		} else if (loadingStep == 100) {
			if (Static231.method3986(sprites)) {
				loadingStep = 110;
			}
		} else if (loadingStep == 110) {
			mouseTracking = new MouseTracking();
			GameShell.signlink.threadreq(10, mouseTracking);

			Static126.loadString = Static171.MAINLOAD110;
			Static199.loadPos = 75;
			loadingStep = 120;
		} else if (loadingStep == 120) {
			if (binary.requestDownload(Static186.EMPTY_STRING, Static252.HUFFMAN)) {
				@Pc(1060) Huffman local1060 = new Huffman(binary.getFile(Static186.EMPTY_STRING, Static252.HUFFMAN));
				Static1.setHuffman(local1060);

				Static126.loadString = Static196.MAINLOAD120B;
				loadingStep = 130;
				Static199.loadPos = 80;
			} else {
				Static126.loadString = Static34.concatenate(new JagString[] { Static148.MAINLOAD120, Static206.PERCENT});
				Static199.loadPos = 80;
			}
		} else if (loadingStep == 130) {
			if (!interfaces.method4475()) {
				Static126.loadString = Static34.concatenate(new JagString[] { Static17.aClass100_104, Static123.method2423(interfaces.method4498() * 3 / 4), Static49.aClass100_352 });
				Static199.loadPos = 85;
			} else if (!scripts.method4475()) {
				Static126.loadString = Static34.concatenate(new JagString[] { Static17.aClass100_104, Static123.method2423(scripts.method4498() / 10 + 75), Static49.aClass100_352 });
				Static199.loadPos = 85;
			} else if (!fontMetrics.method4475()) {
				Static126.loadString = Static34.concatenate(new JagString[] { Static17.aClass100_104, Static123.method2423(fontMetrics.method4498() / 20 + 85), Static49.aClass100_352 });
				Static199.loadPos = 85;
			} else if (worldmap.method4489(Static165.aClass100_777)) {
				Static234.method4018(Static173.aClass3_Sub2_Sub1_Sub1Array9, worldmap);
				Static199.loadPos = 95;
				Static126.loadString = Static269.aClass100_555;
				loadingStep = 135;
			} else {
				Static126.loadString = Static34.concatenate(new JagString[] { Static17.aClass100_104, Static123.method2423(worldmap.method4478(Static165.aClass100_777) / 10 + 90), Static49.aClass100_352 });
				Static199.loadPos = 85;
			}
		} else if (loadingStep == 135) {
			local98 = Static207.method3684();
			if (local98 == -1) {
				Static199.loadPos = 95;
				Static126.loadString = Static208.aClass100_906;
			} else if (local98 == 7 || local98 == 9) {
				this.error("worldlistfull");
				Static196.method3534(1000);
			} else if (Static61.aBoolean109) {
				Static126.loadString = Static34.aClass100_201;
				loadingStep = 140;
				Static199.loadPos = 96;
			} else {
				this.error("worldlistio_" + local98);
				Static196.method3534(1000);
			}
		} else if (loadingStep == 140) {
			Static156.anInt3783 = interfaces.getGroupId(Static138.aClass100_652);
			maps.method4477(false);
			songs.method4477(true);
			sprites.method4477(true);
			fontMetrics.method4477(true);
			binary.method4477(true);
			interfaces.method4477(true);
			Static199.loadPos = 97;
			Static126.loadString = Static38.aClass100_240;
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
			Static199.loadPos = 100;
			loadingStep = 160;
			Static126.loadString = Static214.aClass100_1064;
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

		if (Static257.aClass9_2 != null) {
			Static257.aClass9_2.method179();
		}

		Static230.method3948();
		method2261();
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
