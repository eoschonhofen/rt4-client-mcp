import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

	@OriginalMember(owner = "client!fc", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_435 = JagString.wrap("<img=0>");
	@OriginalMember(owner = "client!mi", name = "Y", descriptor = "[[[Lclient!ih;")
	public static final LinkList[][][] groundObj = new LinkList[4][104][104];
	@OriginalMember(owner = "client!ag", name = "P", descriptor = "Lclient!i;")
	public static final PacketBit out = new PacketBit(5000);
	@OriginalMember(owner = "client!p", name = "c", descriptor = "Lclient!ih;")
	public static final LinkList hookRequests = new LinkList();
	@OriginalMember(owner = "client!ja", name = "f", descriptor = "Lclient!ih;")
	public static final LinkList hookRequestsMouseStop = new LinkList();
	@OriginalMember(owner = "client!ac", name = "i", descriptor = "Lclient!ih;")
	public static final LinkList hookRequestsTimer = new LinkList();
	@OriginalMember(owner = "client!km", name = "Bc", descriptor = "[I")
	public static final int[] varcTransmit = new int[32];
	@OriginalMember(owner = "client!t", name = "E", descriptor = "[I")
	public static final int[] varcstrTransmit = new int[32];
	@OriginalMember(owner = "client!gj", name = "q", descriptor = "[I")
	public static final int[] varTransmit = new int[32];
	@OriginalMember(owner = "client!cb", name = "I", descriptor = "[I")
	public static final int[] invTransmit = new int[32];
	@OriginalMember(owner = "client!ud", name = "T", descriptor = "[I")
	public static final int[] statTransmit = new int[32];
	@OriginalMember(owner = "client!sg", name = "q", descriptor = "[I")
	public static final int[] keypressKeycodes = new int[128];
	@OriginalMember(owner = "client!qj", name = "i", descriptor = "[I")
	public static final int[] keypressKeychars = new int[128];
	@OriginalMember(owner = "client!ch", name = "y", descriptor = "[Z")
	public static final boolean[] componentRedrawRequested2 = new boolean[100];
	@OriginalMember(owner = "client!bg", name = "g", descriptor = "Lclient!i;")
	public static final PacketBit login = new PacketBit(5000);
	@OriginalMember(owner = "client!ud", name = "Q", descriptor = "Lclient!na;")
	public static final JagString aClass100_1039 = JagString.wrap(" x ");
	@OriginalMember(owner = "client!na", name = "mb", descriptor = "[I")
	public static final int[] waveDelay = new int[50];
	@OriginalMember(owner = "client!nk", name = "n", descriptor = "[Lclient!sl;")
	public static final JagFX[] waveSounds = new JagFX[50];
	@OriginalMember(owner = "client!qe", name = "t", descriptor = "[I")
	public static final int[] waveSoundIds = new int[50];
	@OriginalMember(owner = "client!ca", name = "fb", descriptor = "[I")
	public static final int[] waveAmbient = new int[50];
	@OriginalMember(owner = "client!wh", name = "g", descriptor = "[I")
	public static final int[] anIntArray563 = new int[50];
	@OriginalMember(owner = "client!eb", name = "p", descriptor = "[I")
	public static final int[] entityRemovalIds = new int[1000];
	@OriginalMember(owner = "client!ib", name = "f", descriptor = "[I")
	public static final int[] playerIds = new int[2048];
	@OriginalMember(owner = "client!mi", name = "S", descriptor = "[Lclient!e;")
	public static final ClientPlayer[] players = new ClientPlayer[2048];
	@OriginalMember(owner = "client!dh", name = "d", descriptor = "[I")
	public static final int[] entityUpdateIds = new int[2048];
	@OriginalMember(owner = "client!pg", name = "V", descriptor = "I")
	public static final int tooltipRedraw = 50;
	@OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
	public static final int[] camShakeCycle = new int[5];
	@OriginalMember(owner = "client!sd", name = "V", descriptor = "[I")
	public static final int[] componentDrawWidth = new int[100];
	@OriginalMember(owner = "client!vh", name = "b", descriptor = "[I")
	public static final int[] componentDrawX = new int[100];
	@OriginalMember(owner = "client!fe", name = "lb", descriptor = "[I")
	public static final int[] componentDrawHeight = new int[100];
	@OriginalMember(owner = "client!e", name = "sc", descriptor = "[I")
	public static final int[] componentDrawY = new int[100];
	@OriginalMember(owner = "client!pa", name = "R", descriptor = "[Z")
	public static final boolean[] componentRedrawRequested1 = new boolean[100];
	@OriginalMember(owner = "client!nm", name = "S", descriptor = "[Lclient!km;")
	public static final ClientNPC[] npcs = new ClientNPC[32768];
	@OriginalMember(owner = "client!me", name = "P", descriptor = "[I")
	public static final int[] varcInt = new int[2000];
	@OriginalMember(owner = "client!lb", name = "p", descriptor = "[I")
	public static final int[] statBaseLevel = new int[25];
	@OriginalMember(owner = "client!sg", name = "b", descriptor = "[I")
	public static final int[] statXP = new int[25];
	@OriginalMember(owner = "client!hk", name = "fb", descriptor = "[I")
	public static final int[] statEffectiveLevel = new int[25];
	@OriginalMember(owner = "client!cj", name = "i", descriptor = "[I")
	public static final int[] npcIds = new int[32768];
	@OriginalMember(owner = "client!lf", name = "a", descriptor = "[[I")
	public static final int[][] anIntArrayArray25 = new int[104][104];
	@OriginalMember(owner = "client!nd", name = "q", descriptor = "[[I")
	public static final int[][] anIntArrayArray31 = new int[104][104];
	@OriginalMember(owner = "client!vc", name = "eb", descriptor = "[I")
	public static final int[] anIntArray514 = new int[4096];
	@OriginalMember(owner = "client!gk", name = "c", descriptor = "[I")
	public static final int[] anIntArray209 = new int[4096];
	@OriginalMember(owner = "client!uj", name = "C", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array168 = new JagString[500];
	@OriginalMember(owner = "client!t", name = "v", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array160 = new JagString[500];
	@OriginalMember(owner = "client!pf", name = "r", descriptor = "[I")
	public static final int[] anIntArray382 = new int[500];
	@OriginalMember(owner = "client!d", name = "eb", descriptor = "[S")
	public static final short[] menuAction = new short[500];
	@OriginalMember(owner = "client!mi", name = "U", descriptor = "[J")
	public static final long[] aLongArray5 = new long[500];
	@OriginalMember(owner = "client!pl", name = "e", descriptor = "[I")
	public static final int[] anIntArray408 = new int[500];
	@OriginalMember(owner = "client!ef", name = "c", descriptor = "[I")
	public static final int[] anIntArray142 = new int[500];
	@OriginalMember(owner = "client!rb", name = "b", descriptor = "Lclient!sc;")
	public static final HashTable serverActive = new HashTable(512);
	@OriginalMember(owner = "client!ka", name = "q", descriptor = "Lclient!ih;")
	public static final LinkList aClass69_77 = new LinkList();
	@OriginalMember(owner = "client!kh", name = "g", descriptor = "Lclient!hh;")
	public static final PlayerModel aClass59_2 = new PlayerModel();
	@OriginalMember(owner = "client!hd", name = "g", descriptor = "[J")
	public static final long[] aLongArray3 = new long[200];
	@OriginalMember(owner = "client!nm", name = "bb", descriptor = "Lclient!na;")
	public static final JagString aClass100_798 = JagString.wrap("<col=ff0000>");
	@OriginalMember(owner = "client!fb", name = "p", descriptor = "Lclient!na;")
	public static final JagString aClass100_433 = JagString.wrap("<col=ff3000>");
	@OriginalMember(owner = "client!sc", name = "D", descriptor = "Lclient!na;")
	public static final JagString aClass100_951 = JagString.wrap("<col=ff7000>");
	@OriginalMember(owner = "client!si", name = "Z", descriptor = "Lclient!na;")
	public static final JagString aClass100_972 = JagString.wrap("<col=ffb000>");
	@OriginalMember(owner = "client!df", name = "l", descriptor = "Lclient!na;")
	public static final JagString aClass100_332 = JagString.wrap("<col=00ff00>");
	@OriginalMember(owner = "client!ag", name = "bb", descriptor = "Lclient!na;")
	public static final JagString aClass100_18 = JagString.wrap("<col=40ff00>");
	@OriginalMember(owner = "client!vg", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_1081 = JagString.wrap("<col=80ff00>");
	@OriginalMember(owner = "client!dc", name = "v", descriptor = "Lclient!na;")
	public static final JagString aClass100_266 = JagString.wrap("<col=c0ff00>");
	@OriginalMember(owner = "client!sf", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_965 = JagString.wrap("<col=ffff00>");
	@OriginalMember(owner = "client!wh", name = "u", descriptor = "Lclient!na;")
	public static final JagString aClass100_1097 = JagString.wrap("(U2");
	@OriginalMember(owner = "client!bm", name = "p", descriptor = "Lclient!na;")
	public static final JagString aClass100_133 = JagString.wrap("(U1");
	@OriginalMember(owner = "client!mj", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_761 = JagString.wrap("(U3");
	@OriginalMember(owner = "client!hh", name = "a", descriptor = "Lclient!na;")
	public static final JagString aClass100_520 = JagString.wrap("(U4");
	@OriginalMember(owner = "client!tb", name = "P", descriptor = "Lclient!na;")
	public static final JagString aClass100_1002 = JagString.wrap("(U5");
	@OriginalMember(owner = "client!jh", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_591 = JagString.wrap("(Udns");
	@OriginalMember(owner = "client!gm", name = "W", descriptor = "Lclient!na;")
	public static final JagString AUTO_UNDERSCORE = JagString.wrap("_");
	@OriginalMember(owner = "client!wb", name = "e", descriptor = "Lclient!na;")
	public static final JagString AUTO_L = JagString.wrap("l");
	@OriginalMember(owner = "client!i", name = "ic", descriptor = "Lclient!na;")
	public static final JagString AUTO_M = JagString.wrap("m");
	@OriginalMember(owner = "client!oe", name = "o", descriptor = "Lclient!na;")
	public static final JagString AUTO_N = JagString.wrap("n");
	@OriginalMember(owner = "client!nb", name = "a", descriptor = "Lclient!na;")
	public static final JagString AUTO_UM = JagString.wrap("um");
	@OriginalMember(owner = "client!wj", name = "f", descriptor = "Lclient!na;")
	public static final JagString AUTO_UL = JagString.wrap("ul");
	@OriginalMember(owner = "client!wa", name = "Eb", descriptor = "[Lclient!bg;")
	public static final Js5CachedResourceProvider[] js5Providers = new Js5CachedResourceProvider[28];
	@OriginalMember(owner = "client!jh", name = "b", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array92 = new JagString[200];

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
	public static int chatTransmitNum = 0;

	@OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
	public static int transmitNum = 1;

	@OriginalMember(owner = "client!v", name = "f", descriptor = "Lclient!al;")
	public static Js5Loader js5Loader;

	@OriginalMember(owner = "client!ld", name = "k", descriptor = "Ljava/lang/String;")
	public static String worldListHost;

	@OriginalMember(owner = "client!hi", name = "g", descriptor = "I")
	public static int worldListJs5Port;

	@OriginalMember(owner = "client!ud", name = "K", descriptor = "I")
	public static int worldListGamePort;

	@OriginalMember(owner = "client!ee", name = "g", descriptor = "I")
	public static int js5Port;

	@OriginalMember(owner = "client!wc", name = "c", descriptor = "I")
	public static int gamePort;

	@OriginalMember(owner = "client!em", name = "v", descriptor = "Ljava/lang/String;")
	public static String host;

	@OriginalMember(owner = "client!r", name = "f", descriptor = "I")
	public static int worldListPort;

	@OriginalMember(owner = "client!vc", name = "db", descriptor = "[S")
	public static short[] aShortArray88;

	@OriginalMember(owner = "client!ra", name = "s", descriptor = "I")
	public static int loginPort;

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
	@OriginalMember(owner = "client!ee", name = "b", descriptor = "I")
	public static int minusedlevel;
	@OriginalMember(owner = "client!ge", name = "m", descriptor = "I")
	public static int chatEffects = 0;
	@OriginalMember(owner = "client!jb", name = "n", descriptor = "I")
	public static int oneMouseButton = 0;
	@OriginalMember(owner = "client!oe", name = "b", descriptor = "I")
	public static int bankArrangeMode = 0;
	@OriginalMember(owner = "client!ac", name = "o", descriptor = "I")
	public static int varcTransmitNum = 0;
	@OriginalMember(owner = "client!fl", name = "B", descriptor = "I")
	public static int varcstrTransmitNum = 0;
	@OriginalMember(owner = "client!fi", name = "n", descriptor = "I")
	public static int varTransmitNum = 0;
	@OriginalMember(owner = "client!ii", name = "c", descriptor = "I")
	public static int invTransmitNum = 0;
	@OriginalMember(owner = "client!ha", name = "m", descriptor = "I")
	public static int statTransmitNum = 0;
	@OriginalMember(owner = "client!p", name = "d", descriptor = "I")
	public static int friendTransmitNum = 0;
	@OriginalMember(owner = "client!wj", name = "l", descriptor = "I")
	public static int clanTransmitNum = 0;
	@OriginalMember(owner = "client!ql", name = "d", descriptor = "I")
	public static int miscTransmitNum = 0;
	@OriginalMember(owner = "client!ra", name = "J", descriptor = "I")
	public static int anInt4808 = 0;
	@OriginalMember(owner = "client!oj", name = "y", descriptor = "I")
	public static int keypresses = 0;
	@OriginalMember(owner = "client!dg", name = "h", descriptor = "Lclient!be;")
	public static IfType tooltipCom;
	@OriginalMember(owner = "client!oe", name = "l", descriptor = "I")
	public static int accountCreateStep = 0;
	@OriginalMember(owner = "client!sf", name = "a", descriptor = "I")
	public static int accountCreateWaitingTime = 0;
	@OriginalMember(owner = "client!eg", name = "v", descriptor = "I")
	public static int accountCreateFailCount = 0;
	@OriginalMember(owner = "client!sc", name = "y", descriptor = "I")
	public static int accountCreateError = -2;
	@OriginalMember(owner = "client!fl", name = "C", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest socketReq;
	@OriginalMember(owner = "client!rl", name = "X", descriptor = "I")
	public static int worldHopStep = 0;
	@OriginalMember(owner = "client!bj", name = "Y", descriptor = "I")
	public static int worldListWaitingTime = 0;
	@OriginalMember(owner = "client!vk", name = "b", descriptor = "I")
	public static int worldHopError = -2;
	@OriginalMember(owner = "client!pl", name = "i", descriptor = "I")
	public static int worldHopFailCount = 0;
	@OriginalMember(owner = "client!vl", name = "k", descriptor = "I")
	public static int logoutTimer = 0;
	@OriginalMember(owner = "client!em", name = "B", descriptor = "I")
	public static int rebootTimer = 0;
	@OriginalMember(owner = "client!sd", name = "X", descriptor = "Z")
	public static boolean networkError = false;
	@OriginalMember(owner = "client!ol", name = "V", descriptor = "I")
	public static int loginStep = 0;
	@OriginalMember(owner = "client!hd", name = "a", descriptor = "I")
	public static int loginWaitingTime = 0;
	@OriginalMember(owner = "client!wh", name = "s", descriptor = "I")
	public static int loginFailCount = 0;
	@OriginalMember(owner = "client!pi", name = "P", descriptor = "J")
	public static long serverSeed = 0L;
	@OriginalMember(owner = "client!pg", name = "S", descriptor = "I")
	public static int staffmodlevel = 0;
	@OriginalMember(owner = "client!ud", name = "O", descriptor = "I")
	public static int anInt5431 = 0;
	@OriginalMember(owner = "client!jk", name = "G", descriptor = "Z")
	public static boolean aBoolean157 = false;
	@OriginalMember(owner = "client!ql", name = "c", descriptor = "Z")
	public static boolean aBoolean236 = false;
	@OriginalMember(owner = "client!c", name = "hb", descriptor = "Z")
	public static boolean aBoolean57 = false;
	@OriginalMember(owner = "client!gm", name = "bb", descriptor = "Z")
	public static boolean aBoolean129 = false;
	@OriginalMember(owner = "client!tm", name = "e", descriptor = "Z")
	public static boolean mouseTracked = false;
	@OriginalMember(owner = "client!bf", name = "E", descriptor = "I")
	public static int anInt549 = -1;
	@OriginalMember(owner = "client!qg", name = "W", descriptor = "Z")
	public static boolean aBoolean233 = false;
	@OriginalMember(owner = "client!aa", name = "l", descriptor = "Z")
	public static boolean memServer = false;
	@OriginalMember(owner = "client!vh", name = "s", descriptor = "I")
	public static int lastWriteX = 0;
	@OriginalMember(owner = "client!oe", name = "a", descriptor = "I")
	public static int lastWriteY = 0;
	@OriginalMember(owner = "client!qi", name = "t", descriptor = "I")
	public static int mouseTrackedDelta = 0;
	@OriginalMember(owner = "client!ok", name = "f", descriptor = "J")
	public static long aLong139 = 0L;
	@OriginalMember(owner = "client!fe", name = "R", descriptor = "Z")
	public static boolean focusIn = true;
	@OriginalMember(owner = "client!pm", name = "ab", descriptor = "Z")
	public static boolean sendCamera = true;
	@OriginalMember(owner = "client!bf", name = "G", descriptor = "I")
	public static int sendCameraDelay = 0;
	@OriginalMember(owner = "client!fl", name = "s", descriptor = "I")
	public static int anInt2031 = 128;
	@OriginalMember(owner = "client!eg", name = "d", descriptor = "I")
	public static int anInt1747 = 0;
	@OriginalMember(owner = "client!pe", name = "j", descriptor = "I")
	public static int waveCount = 0;
	@OriginalMember(owner = "client!nk", name = "O", descriptor = "Lclient!e;")
	public static ClientPlayer localPlayer;
	@OriginalMember(owner = "client!cg", name = "f", descriptor = "I")
	public static int ambientVolume = 127;
	@OriginalMember(owner = "client!le", name = "k", descriptor = "Z")
	public static boolean aBoolean173 = false;
	@OriginalMember(owner = "client!s", name = "c", descriptor = "I")
	public static int anInt4363 = -1;
	@OriginalMember(owner = "client!bb", name = "n", descriptor = "I")
	public static int midiVolume = 255;
	@OriginalMember(owner = "client!vl", name = "j", descriptor = "I")
	public static int playerCount = 0;
	@OriginalMember(owner = "client!tg", name = "h", descriptor = "I")
	public static int entityRemovalCount = 0;
	@OriginalMember(owner = "client!gj", name = "d", descriptor = "I")
	public static int loopCycle = 0;
	@OriginalMember(owner = "client!jb", name = "m", descriptor = "I")
	public static int entityUpdateCount = 0;
	@OriginalMember(owner = "client!fi", name = "k", descriptor = "I")
	public static int crossMode = 0;
	@OriginalMember(owner = "client!bg", name = "M", descriptor = "I")
	public static int crossCycle = 0;
	@OriginalMember(owner = "client!v", name = "b", descriptor = "Lclient!be;")
	public static IfType selectedArea;
	@OriginalMember(owner = "client!fl", name = "P", descriptor = "I")
	public static int selectedCycle = 0;
	@OriginalMember(owner = "client!od", name = "i", descriptor = "I")
	public static int anInt4247 = 0;
	@OriginalMember(owner = "client!lj", name = "w", descriptor = "I")
	public static int objGrabX = 0;
	@OriginalMember(owner = "client!qk", name = "f", descriptor = "I")
	public static int objGrabY = 0;
	@OriginalMember(owner = "client!jj", name = "j", descriptor = "Z")
	public static boolean objGrabThreshold = false;
	@OriginalMember(owner = "client!gd", name = "j", descriptor = "I")
	public static int objDragCycles = 0;
	@OriginalMember(owner = "client!jd", name = "i", descriptor = "Lclient!be;")
	public static IfType objDragInterface;
	@OriginalMember(owner = "client!nf", name = "h", descriptor = "Lclient!be;")
	public static IfType hoveredSlotParent;
	@OriginalMember(owner = "client!ac", name = "n", descriptor = "I")
	public static int anInt36 = 0;
	@OriginalMember(owner = "client!bh", name = "t", descriptor = "I")
	public static int anInt588 = 0;
	@OriginalMember(owner = "client!md", name = "W", descriptor = "I")
	public static int toplevelinterface = -1;
	@OriginalMember(owner = "client!lg", name = "b", descriptor = "Z")
	public static boolean aBoolean174 = false;
	@OriginalMember(owner = "client!ef", name = "r", descriptor = "Lclient!be;")
	public static IfType aClass13_12 = null;
	@OriginalMember(owner = "client!dh", name = "a", descriptor = "Z")
	public static boolean aBoolean83 = false;
	@OriginalMember(owner = "client!og", name = "e", descriptor = "Lclient!be;")
	public static IfType hoveredCom;
	@OriginalMember(owner = "client!bn", name = "N", descriptor = "Lclient!be;")
	public static IfType aClass13_8;
	@OriginalMember(owner = "client!bn", name = "V", descriptor = "I")
	public static int componentDrawCount = 0;
	@OriginalMember(owner = "client!ib", name = "e", descriptor = "Lclient!be;")
	public static IfType dragComponent = null;
	@OriginalMember(owner = "client!c", name = "eb", descriptor = "I")
	public static int crossY = 0;
	@OriginalMember(owner = "client!jh", name = "j", descriptor = "I")
	public static int crossX = 0;
	@OriginalMember(owner = "client!kf", name = "l", descriptor = "I")
	public static int tooltipNum = 0;
	@OriginalMember(owner = "client!ld", name = "i", descriptor = "I")
	public static int macroCameraCycle = 0;
	@OriginalMember(owner = "client!sj", name = "H", descriptor = "I")
	public static int macroCameraX = 0;
	@OriginalMember(owner = "client!qk", name = "h", descriptor = "I")
	public static int macroCameraZ = 0;
	@OriginalMember(owner = "client!ke", name = "U", descriptor = "I")
	public static int macroCameraAngle = 0;
	@OriginalMember(owner = "client!rm", name = "c", descriptor = "I")
	public static int macroCameraXModifier = 1;
	@OriginalMember(owner = "client!bj", name = "r", descriptor = "I")
	public static int macroCameraZModifier = 2;
	@OriginalMember(owner = "client!uc", name = "a", descriptor = "I")
	public static int macroCameraAngleModifier = 2;
	@OriginalMember(owner = "client!ej", name = "W", descriptor = "I")
	public static int macroMinimapAngle = 0;
	@OriginalMember(owner = "client!vg", name = "d", descriptor = "I")
	public static int macroMinimapAngleModifier = 2;
	@OriginalMember(owner = "client!gi", name = "H", descriptor = "I")
	public static int macroMinimapCycle = 0;
	@OriginalMember(owner = "client!we", name = "w", descriptor = "I")
	public static int macroMinimapZoom = 0;
	@OriginalMember(owner = "client!oe", name = "n", descriptor = "I")
	public static int macroMinimapZoomModifier = 1;
	@OriginalMember(owner = "client!kd", name = "ob", descriptor = "I")
	public static int noTimeoutCycle = 0;
	@OriginalMember(owner = "client!ck", name = "eb", descriptor = "Z")
	public static boolean aBoolean65 = false;
	@OriginalMember(owner = "client!t", name = "F", descriptor = "I")
	public static int anInt5226 = 0;
	@OriginalMember(owner = "client!id", name = "l", descriptor = "Lclient!jb;")
	public static Js5Net js5Net;
	@OriginalMember(owner = "client!kh", name = "f", descriptor = "I")
	public static int anInt3302;
	@OriginalMember(owner = "client!af", name = "d", descriptor = "I")
	public static int anInt40;
	@OriginalMember(owner = "client!km", name = "Pc", descriptor = "I")
	public static int anInt3439;
	@OriginalMember(owner = "client!tg", name = "b", descriptor = "I")
	public static int anInt5333;
	@OriginalMember(owner = "client!ol", name = "ib", descriptor = "I")
	public static int anInt4358;
	@OriginalMember(owner = "client!bf", name = "B", descriptor = "I")
	public static int projectX = -1;
	@OriginalMember(owner = "client!fc", name = "a", descriptor = "I")
	public static int projectY = -1;
	@OriginalMember(owner = "client!em", name = "z", descriptor = "Z")
	public static boolean isMenuOpen = false;
	@OriginalMember(owner = "client!ja", name = "n", descriptor = "I")
	public static int anInt2939 = 0;
	@OriginalMember(owner = "client!sk", name = "kb", descriptor = "I")
	public static int menuNumEntries = 0;
	@OriginalMember(owner = "client!wb", name = "d", descriptor = "I")
	public static int anInt5795 = 0;
	@OriginalMember(owner = "client!sg", name = "o", descriptor = "I")
	public static int anInt5096;
	@OriginalMember(owner = "client!t", name = "l", descriptor = "Lclient!ma;")
	public static ClientStream lastStream;
	@OriginalMember(owner = "client!se", name = "a", descriptor = "I")
	public static int mapBuildBaseX;
	@OriginalMember(owner = "client!lc", name = "n", descriptor = "I")
	public static int mapBuildBaseZ;
	@OriginalMember(owner = "client!bb", name = "E", descriptor = "I")
	public static int runEnergy = 0;
	@OriginalMember(owner = "client!ug", name = "o", descriptor = "I")
	public static int runWeight = 0;
	@OriginalMember(owner = "client!wd", name = "g", descriptor = "I")
	public static int npcCount = 0;
	@OriginalMember(owner = "client!cn", name = "B", descriptor = "I")
	public static int locModelLoadPrevCount = 1;
	@OriginalMember(owner = "client!wc", name = "g", descriptor = "I")
	public static int locModelLoadCount = 0;
	@OriginalMember(owner = "client!th", name = "n", descriptor = "Z")
	public static boolean targetMode = false;
	@OriginalMember(owner = "client!jl", name = "J", descriptor = "I")
	public static int waveVolume = 127;
	@OriginalMember(owner = "client!jl", name = "v", descriptor = "I")
	public static int anInt3096 = 0;
	@OriginalMember(owner = "client!ca", name = "X", descriptor = "Lclient!ih;")
	public static LinkList aClass69_27 = new LinkList();
	@OriginalMember(owner = "client!mh", name = "X", descriptor = "I")
	public static int anInt3851 = -1;
	@OriginalMember(owner = "client!lf", name = "k", descriptor = "I")
	public static int anInt3502 = -1;
	@OriginalMember(owner = "client!jg", name = "e", descriptor = "Z")
	public static boolean aBoolean154 = false;
	@OriginalMember(owner = "client!tg", name = "d", descriptor = "[Lclient!qf;")
	public static AbstractPix32[] cross;
	@OriginalMember(owner = "client!ba", name = "w", descriptor = "I")
	public static int overrideChat = 0;
	@OriginalMember(owner = "client!je", name = "T", descriptor = "Lclient!sc;")
    public static HashTable aClass133_9 = new HashTable(8);
	@OriginalMember(owner = "client!nm", name = "U", descriptor = "I")
	public static int mapLoadCount = 0;
	@OriginalMember(owner = "client!sm", name = "k", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest lastAddress;
	@OriginalMember(owner = "client!sj", name = "u", descriptor = "Z")
	public static boolean regionmode = false;
	@OriginalMember(owner = "client!eb", name = "u", descriptor = "I")
	public static int mapBuildCenterZoneX;
	@OriginalMember(owner = "client!gf", name = "R", descriptor = "I")
	public static int mapBuildCenterZoneZ;
	@OriginalMember(owner = "client!we", name = "H", descriptor = "[[B")
	public static byte[][] aByteArrayArray13;
	@OriginalMember(owner = "client!client", name = "lb", descriptor = "[I")
	public static int[] mapBuildGroundFile;
	@OriginalMember(owner = "client!nj", name = "j", descriptor = "[I")
	public static int[] mapBuildLocationFile;
	@OriginalMember(owner = "client!mf", name = "O", descriptor = "[[B")
	public static byte[][] aByteArrayArray11;
	@OriginalMember(owner = "client!hk", name = "bb", descriptor = "[I")
	public static int[] mapBuildUnderGroundFile;
	@OriginalMember(owner = "client!pa", name = "L", descriptor = "[[B")
	public static byte[][] aByteArrayArray14;
	@OriginalMember(owner = "client!cl", name = "V", descriptor = "[I")
	public static int[] mapBuildUnderGroundLocationFile;
	@OriginalMember(owner = "client!bi", name = "Y", descriptor = "[[B")
	public static byte[][] aByteArrayArray4;
	@OriginalMember(owner = "client!nm", name = "P", descriptor = "[I")
	public static int[] mapBuildNpcFile;
	@OriginalMember(owner = "client!pg", name = "jb", descriptor = "[[B")
	public static byte[][] aByteArrayArray15;
	@OriginalMember(owner = "client!t", name = "y", descriptor = "I")
	public static int mapLoadingStage = 0;
	@OriginalMember(owner = "client!te", name = "H", descriptor = "[I")
	public static int[] mapBuildIndex;
	@OriginalMember(owner = "client!fl", name = "D", descriptor = "[[I")
	public static int[][] anIntArrayArray14;
	@OriginalMember(owner = "client!pb", name = "ab", descriptor = "I")
	public static int anInt4422 = 0;
	@OriginalMember(owner = "client!kl", name = "s", descriptor = "I")
	public static int anInt3337 = 0;
	@OriginalMember(owner = "client!cj", name = "h", descriptor = "Z")
	public static boolean aBoolean63;
	@OriginalMember(owner = "client!gg", name = "db", descriptor = "I")
	public static int anInt2225 = -1;
	@OriginalMember(owner = "client!jk", name = "p", descriptor = "I")
	public static int anInt3075 = -1;
	@OriginalMember(owner = "client!bj", name = "s", descriptor = "I")
	public static int anInt660 = -1;
	@OriginalMember(owner = "client!th", name = "f", descriptor = "[Lclient!ok;")
	public static Pix8[] scrollbar;
	@OriginalMember(owner = "client!re", name = "y", descriptor = "I")
	public static int anInt4851;
	@OriginalMember(owner = "client!ac", name = "p", descriptor = "Lclient!be;")
	public static IfType aClass13_1 = null;
	@OriginalMember(owner = "client!sh", name = "f", descriptor = "I")
	public static int anInt5103 = -1;
	@OriginalMember(owner = "client!hi", name = "a", descriptor = "I")
	public static int minimenuMouseOverX = -1;
	@OriginalMember(owner = "client!gf", name = "K", descriptor = "I")
	public static int anInt4696;
	@OriginalMember(owner = "client!k", name = "j", descriptor = "I")
	public static int anInt3126;
	@OriginalMember(owner = "client!th", name = "m", descriptor = "[Lclient!be;")
	public static IfType[] aClass13Array13;
	@OriginalMember(owner = "client!km", name = "pc", descriptor = "Z")
	public static boolean aBoolean172 = false;
	@OriginalMember(owner = "client!nb", name = "d", descriptor = "I")
	public static int anInt4035 = 0;
	@OriginalMember(owner = "client!rg", name = "s", descriptor = "I")
	public static int minimenuMouseOverY = -1;
	@OriginalMember(owner = "client!wl", name = "h", descriptor = "Lclient!be;")
	public static IfType aClass13_26 = null;
	@OriginalMember(owner = "client!sj", name = "p", descriptor = "I")
	public static int mapPrevLoadCount = 1;
	@OriginalMember(owner = "client!ml", name = "Q", descriptor = "I")
	public static int anInt3953 = 0;
	@OriginalMember(owner = "client!me", name = "nb", descriptor = "I")
	public static int anInt3751;
	@OriginalMember(owner = "client!wi", name = "W", descriptor = "I")
	public static int anInt5850 = 0;
	@OriginalMember(owner = "client!em", name = "w", descriptor = "I")
	public static int anInt1892;
	@OriginalMember(owner = "client!wl", name = "u", descriptor = "I")
	public static int anInt5895 = 0;
	@OriginalMember(owner = "client!nj", name = "a", descriptor = "Z")
	public static boolean aBoolean199 = false;
	@OriginalMember(owner = "client!wi", name = "bb", descriptor = "I")
	public static int anInt5854 = 0;

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
				method2577("modewhat");
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
				method2577("language");
			}
			setLang(Client.lang);

			objecttag = false;
			js = false;

			if (args[3].equals("game0")) {
				game = 0;
			} else if (args[3].equals("game1")) {
				game = 1;
			} else {
				method2577("game");
			}

			country = 0;
			haveie6 = false;
			affid = 0;

			Static47.aClass100_991 = TitleScreen.AUTO_EMPTY;

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

		js5Providers[arg3] = js5Loader.method180(arg3, Static148.aClass49_4, local7);

		if (arg1) {
			js5Providers[arg3].method528();
		}

		return new Js5(js5Providers[arg3], arg0, arg2);
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
			psize = Protocol.SERVERPROT_SIZES[ptype];
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
		@Pc(171) int local171;
		@Pc(156) JagString local156;
		@Pc(275) long local275;
		@Pc(262) boolean local262;
		@Pc(277) int local277;
		@Pc(506) JagString local506;
		@Pc(786) int local786;
		@Pc(790) JagString local790;
		@Pc(864) int local864;
		@Pc(884) long local884;
		@Pc(908) int local908;
		@Pc(916) int local916;
		@Pc(899) long local899;
		@Pc(904) long local904;
		@Pc(1146) int local1146;
		@Pc(1160) int local1160;
		@Pc(1245) boolean local1245;
		@Pc(1409) JagString local1409;
		@Pc(1814) ServerActive local1814;
		@Pc(1804) ServerActive local1804;
		@Pc(1986) int local1986;
		@Pc(3002) int local3002;
		@Pc(3038) JagString local3038;
		@Pc(3020) JagString local3020;
		@Pc(3456) SubInterface local3456;
		@Pc(4956) IfType local4956;

		if (ptype == 60) {
			local133 = in.method2184();
			@Pc(137) byte local137 = in.method2189();
			VarCache.method2575(local137, local133);
			ptype = -1;
			return true;
		}

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
				ScriptRunner.executeScript(local226);
			}
			ptype = -1;
			return true;
		}
		if (ptype == 70) {
			@Pc(245) JagString local245 = in.gjstr();
			if (local245.method3130(Static196.aClass100_863)) {
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				local262 = false;
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					addChat(local156, 4, Text.aClass100_367);
				}
			} else if (local245.method3130(Static61.aClass100_423)) {
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				local262 = false;
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					local506 = local245.method3137(local245.length() - 9, local245.indexOf(Static264.aClass100_875) + 1);
					addChat(local156, 8, local506);
				}
			} else if (local245.method3130(Static191.aClass100_845)) {
				local262 = false;
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (local275 == Static190.aLongArray6[local277]) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					addChat(local156, 10, TitleScreen.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static141.aClass100_664)) {
				local156 = local245.method3137(local245.indexOf(Static141.aClass100_664), 0);
				addChat(TitleScreen.AUTO_EMPTY, 11, local156);
			} else if (local245.method3130(Static138.aClass100_654)) {
				local156 = local245.method3137(local245.indexOf(Static138.aClass100_654), 0);
				if (overrideChat == 0) {
					addChat(TitleScreen.AUTO_EMPTY, 12, local156);
				}
			} else if (local245.method3130(Static244.aClass100_1014)) {
				local156 = local245.method3137(local245.indexOf(Static244.aClass100_1014), 0);
				if (overrideChat == 0) {
					addChat(TitleScreen.AUTO_EMPTY, 13, local156);
				}
			} else if (local245.method3130(Static56.aClass100_379)) {
				local262 = false;
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (local275 == Static190.aLongArray6[local277]) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					addChat(local156, 14, TitleScreen.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static112.aClass100_574)) {
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local262 = false;
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					addChat(local156, 15, TitleScreen.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static217.aClass100_916)) {
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local275 = local156.method3158();
				local262 = false;
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (local275 == Static190.aLongArray6[local277]) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					addChat(local156, 16, TitleScreen.AUTO_EMPTY);
				}
			} else if (local245.method3130(Static164.aClass100_770)) {
				local156 = local245.method3137(local245.indexOf(Static264.aClass100_875), 0);
				local262 = false;
				local275 = local156.method3158();
				for (local277 = 0; local277 < Static35.anInt1093; local277++) {
					if (Static190.aLongArray6[local277] == local275) {
						local262 = true;
						break;
					}
				}
				if (!local262 && overrideChat == 0) {
					local506 = local245.method3137(local245.length() - 9, local245.indexOf(Static264.aClass100_875) + 1);
					addChat(local156, 21, local506);
				}
			} else {
				addChat(TitleScreen.AUTO_EMPTY, 0, local245);
			}
			ptype = -1;
			return true;
		}
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
				zonePacket();
			}
			ptype = -1;
			return true;
		} else if (ptype == 153) {
			ptype = -1;
			anInt2939 = 0;
			return true;
		}
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
            if (!local910 && overrideChat == 0) {
                Static233.aLongArray9[Static251.anInt5447] = local922;
                Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
                @Pc(999) JagString local999 = QuickChatPhraseType.list(local916).method770(in);
                if (local908 == 2 || local908 == 3) {
                    method2928(local916, 20, local999, Static79.toBaseDisplayName(local275).method3125(), JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }));
                } else if (local908 == 1) {
                    method2928(local916, 20, local999, Static79.toBaseDisplayName(local275).method3125(), JagString.join(new JagString[] { aClass100_435, Static79.toBaseDisplayName(local884).method3125() }));
                } else {
                    method2928(local916, 20, local999, Static79.toBaseDisplayName(local275).method3125(), Static79.toBaseDisplayName(local884).method3125());
                }
            }
            ptype = -1;
            return true;
        }
        if (ptype == 55) {
            clanTransmitNum = transmitNum;
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
            lastAddress = signlink.method5128(local133);
            ptype = -1;
            return true;
        } else if (ptype == 225) {
            // PLAYER_INFO
            getPlayerPos();
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
        }
        if (ptype == 44) {
            local133 = in.method2207();
            if (local133 == 65535) {
                local133 = -1;
            }
            local786 = in.g1();
            local864 = in.g1();
            local1409 = in.gjstr();
            if (local864 >= 1 && local864 <= 8) {
                if (local1409.equalsIgnoreCase(Static92.aClass100_510)) {
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
            VarCache.method2575(local133, local786);
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
                toplevelinterface = local133;
                ifAnimReset(local133);
                method3712(false);
                ScriptRunner.method1626(toplevelinterface);
                for (local171 = 0; local171 < 100; local171++) {
                    componentRedrawRequested1[local171] = true;
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
            legacyUpdated();
            varTransmitNum += 32;
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
                method3849(local171, local864, local1146, local786, local277);
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
        }
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
                    local1804 = (ServerActive) serverActive.find(local904);
                    if (local1804 != null) {
                        local1814 = new ServerActive(local1804.eventCode, local133);
                        local1804.unlink();
                    } else if (local277 == -1) {
                        local1814 = new ServerActive(IfType.get(local786).active.eventCode, local133);
                    } else {
                        local1814 = new ServerActive(0, local133);
                    }
                    serverActive.put(local1814, local904);
                }
            }
            ptype = -1;
            return true;
        }
        if (ptype == 56) {
            local133 = in.g2();
            local786 = in.method2192();
            local864 = in.method2206();
            local171 = in.method2207();
            if (local864 >> 30 == 0) {
                @Pc(1994) SeqType local1994;
                if (local864 >> 29 != 0) {
                    local1146 = local864 & 0xFFFF;
                    @Pc(1894) ClientNPC local1894 = npcs[local1146];
                    if (local1894 != null) {
                        if (local171 == 65535) {
                            local171 = -1;
                        }
                        local1245 = true;
                        if (local171 != -1 && local1894.anInt3432 != -1 && SeqType.list(SpotType.list(local171).anim).priority < SeqType.list(SpotType.list(local1894.anInt3432).anim).priority) {
                            local1245 = false;
                        }
                        if (local1245) {
                            local1894.anInt3361 = 0;
                            local1894.anInt3432 = local171;
                            local1894.anInt3359 = loopCycle + local133;
                            local1894.anInt3399 = 0;
                            if (local1894.anInt3359 > loopCycle) {
                                local1894.anInt3399 = -1;
                            }
                            local1894.anInt3394 = local786;
                            local1894.anInt3418 = 1;
                            if (local1894.anInt3432 != -1 && loopCycle == local1894.anInt3359) {
                                local1986 = SpotType.list(local1894.anInt3432).anim;
                                if (local1986 != -1) {
                                    local1994 = SeqType.list(local1986);
                                    if (local1994 != null && local1994.frames != null) {
                                        method2836(local1894.z, local1994, local1894.x, false, 0);
                                    }
                                }
                            }
                        }
                    }
                } else if (local864 >> 28 != 0) {
                    local1146 = local864 & 0xFFFF;
                    @Pc(2033) ClientPlayer local2033;
                    if (anInt549 == local1146) {
                        local2033 = localPlayer;
                    } else {
                        local2033 = players[local1146];
                    }
                    if (local2033 != null) {
                        if (local171 == 65535) {
                            local171 = -1;
                        }
                        local1245 = true;
                        if (local171 != -1 && local2033.anInt3432 != -1 && SeqType.list(SpotType.list(local171).anim).priority < SeqType.list(SpotType.list(local2033.anInt3432).anim).priority) {
                            local1245 = false;
                        }
                        if (local1245) {
                            local2033.anInt3359 = local133 + loopCycle;
                            local2033.anInt3394 = local786;
                            local2033.anInt3432 = local171;
                            if (local2033.anInt3432 == 65535) {
                                local2033.anInt3432 = -1;
                            }
                            local2033.anInt3418 = 1;
                            local2033.anInt3361 = 0;
                            local2033.anInt3399 = 0;
                            if (local2033.anInt3359 > loopCycle) {
                                local2033.anInt3399 = -1;
                            }
                            if (local2033.anInt3432 != -1 && local2033.anInt3359 == loopCycle) {
                                local1986 = SpotType.list(local2033.anInt3432).anim;
                                if (local1986 != -1) {
                                    local1994 = SeqType.list(local1986);
                                    if (local1994 != null && local1994.frames != null) {
                                        method2836(local2033.z, local1994, local2033.x, local2033 == localPlayer, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                local1146 = local864 >> 28 & 0x3;
                local277 = (local864 >> 14 & 0x3FFF) - mapBuildBaseX;
                local1160 = (local864 & 0x3FFF) - mapBuildBaseZ;
                if (local277 >= 0 && local1160 >= 0 && local277 < 104 && local1160 < 104) {
                    local1160 = local1160 * 128 + 64;
                    local277 = local277 * 128 + 64;
                    @Pc(2241) MapSpotAnim local2241 = new MapSpotAnim(local171, local1146, local277, local1160, getAvH(local1146, local277, local1160) - local786, local133, loopCycle);
                    Static99.aClass69_64.push(new MapSpotAnimNode(local2241));
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
            legacyUpdated();
            local133 = in.method2177();
            local786 = in.method2206();
            local864 = in.g1();
            statXP[local864] = local786;
            statEffectiveLevel[local864] = local133;
            statBaseLevel[local864] = 1;
            for (local171 = 0; local171 < 98; local171++) {
                if (Skills.skillxp[local171] <= local786) {
                    statBaseLevel[local864] = local171 + 2;
                }
            }
            statTransmit[statTransmitNum++ & 0x1F] = local864;
            ptype = -1;
            return true;
        } else if (ptype == 104 || ptype == 121 || ptype == 97 || ptype == 14 || ptype == 202 || ptype == 135 || ptype == 17 || ptype == 16 || ptype == 240 || ptype == 33 || ptype == 20 || ptype == 195 || ptype == 179) {
            zonePacket();
            ptype = -1;
            return true;
        } else if (ptype == 149) {
            local133 = in.g2();
            local786 = in.g4();
            if (Static248.method3288(local133)) {
                @Pc(2441) SubInterface local2441 = (SubInterface) aClass133_9.find((long) local786);
                if (local2441 != null) {
                    closeSubInterface(true, local2441);
                }
                if (Static39.aClass13_10 != null) {
                    componentUpdated(Static39.aClass13_10);
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
                anInt1747 = local133;
                anInt2031 = local864;
                if (anInt5096 == 2) {
                    anInt5333 = anInt2031;
                    anInt4358 = anInt1747;
                }
                followCamera();
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
                    if (groundObj[minusedlevel][local133][local786] != null) {
                        groundObj[minusedlevel][local133][local786] = null;
                        showObject(local786, local133);
                    }
                }
            }
            for (@Pc(2604) LocChange local2604 = (LocChange) aClass69_27.head(); local2604 != null; local2604 = (LocChange) aClass69_27.next()) {
                if (local2604.anInt928 >= Static115.anInt2940 && Static115.anInt2940 + 8 > local2604.anInt928 && local2604.anInt916 >= Static180.anInt4264 && local2604.anInt916 < Static180.anInt4264 + 8 && local2604.anInt918 == minusedlevel) {
                    local2604.endTime = 0;
                }
            }
            ptype = -1;
            return true;
        } else if (ptype == 144) {
            local133 = in.method2224();
            @Pc(2666) IfType local2666 = IfType.get(local133);
            for (local864 = 0; local864 < local2666.linkObjNumber.length; local864++) {
                local2666.linkObjNumber[local864] = -1;
                local2666.linkObjNumber[local864] = 0;
            }
            componentUpdated(local2666);
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
            anInt5795 = in.g1();
            ptype = -1;
            return true;
        } else if (ptype == 13) {
            local133 = in.method2180();
            local786 = in.method2177();
            local864 = in.g1();
            minusedlevel = local786 >> 1;
            localPlayer.method1265(local133, (local786 & 0x1) == 1, local864);
            ptype = -1;
            return true;
        }
        if (ptype == 62) {
            local884 = in.g8();
            local864 = in.g2();
            local171 = in.g1();
            local262 = true;
            if (local884 < 0L) {
                local884 &= Long.MAX_VALUE;
                local262 = false;
            }
            local506 = TitleScreen.AUTO_EMPTY;
            if (local864 > 0) {
                local506 = in.gjstr();
            }
            @Pc(2834) JagString local2834 = Static79.toBaseDisplayName(local884).method3125();
            for (local1986 = 0; local1986 < Static9.anInt178; local1986++) {
                if (local884 == aLongArray3[local1986]) {
                    if (local864 != Static104.anIntArray255[local1986]) {
                        Static104.anIntArray255[local1986] = local864;
                        if (local864 > 0) {
                            addChat(TitleScreen.AUTO_EMPTY, 5, JagString.join(new JagString[] { local2834, Text.aClass100_155 }));
                        }
                        if (local864 == 0) {
                            addChat(TitleScreen.AUTO_EMPTY, 5, JagString.join(new JagString[] { local2834, Text.aClass100_507 }));
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
                aLongArray3[Static9.anInt178] = local884;
                aClass100Array92[Static9.anInt178] = local2834;
                Static104.anIntArray255[Static9.anInt178] = local864;
                Static214.aClass100Array170[Static9.anInt178] = local506;
                Static106.anIntArray258[Static9.anInt178] = local171;
                Static3.aBooleanArray135[Static9.anInt178] = local262;
                Static9.anInt178++;
            }
            friendTransmitNum = transmitNum;
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
                        local3038 = aClass100Array92[local916];
                        aClass100Array92[local916] = aClass100Array92[local916 + 1];
                        aClass100Array92[local916 + 1] = local3038;
                        @Pc(3056) long local3056 = aLongArray3[local916];
                        aLongArray3[local916] = aLongArray3[local916 + 1];
                        aLongArray3[local916 + 1] = local3056;
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
            for (local133 = 0; local133 < VarCache.var.length; local133++) {
                if (VarCache.varServ[local133] != VarCache.var[local133]) {
                    VarCache.var[local133] = VarCache.varServ[local133];
                    clientVar(local133);
                    varTransmit[varTransmitNum++ & 0x1F] = local133;
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
                method2722(true, local1146, local171, local277, local864, local786);
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
            if (!local3263 && overrideChat == 0) {
                Static233.aLongArray9[Static251.anInt5447] = local3270;
                Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
                local3020 = QuickChatPhraseType.list(local1986).method770(in);
                if (local1160 == 2) {
                    method2928(local1986, 18, local3020, null, JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }));
                } else if (local1160 == 1) {
                    method2928(local1986, 18, local3020, null, JagString.join(new JagString[] { aClass100_435, Static79.toBaseDisplayName(local884).method3125() }));
                } else {
                    method2928(local1986, 18, local3020, null, Static79.toBaseDisplayName(local884).method3125());
                }
            }
            ptype = -1;
            return true;
        }
        if (ptype == 176) {
            local133 = in.method2206();
            local786 = in.method2184();
            local864 = in.method2206();
            if (Static248.method3288(local786)) {
                @Pc(3449) SubInterface local3449 = (SubInterface) aClass133_9.find((long) local133);
                local3456 = (SubInterface) aClass133_9.find((long) local864);
                if (local3456 != null) {
                    closeSubInterface(local3449 == null || local3456.id != local3449.id, local3456);
                }
                if (local3449 != null) {
                    local3449.unlink();
                    aClass133_9.put(local3449, (long) local864);
                }
                @Pc(3490) IfType local3490 = IfType.get(local133);
                if (local3490 != null) {
                    componentUpdated(local3490);
                }
                local3490 = IfType.get(local864);
                if (local3490 != null) {
                    componentUpdated(local3490);
                    method531(local3490, true);
                }
                if (toplevelinterface != -1) {
                    runHookImmediate(1, toplevelinterface);
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
                camShakeCycle[local786] = local277;
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
            rebootTimer = in.g2() * 30;
            ptype = -1;
            anInt4808 = transmitNum;
            return true;
        } else if (ptype == 114) {
            ReflectionChecker.method3654(signlink, in, psize);
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
            legacyUpdated();
            runEnergy = in.g1();
            anInt4808 = transmitNum;
            ptype = -1;
            return true;
        } else if (ptype == 209) {
            if (toplevelinterface != -1) {
                runHookImmediate(0, toplevelinterface);
            }
            ptype = -1;
            return true;
        } else if (ptype == 191) {
            local133 = in.method2192();
            ClientInvCache.delete(local133);
            invTransmit[invTransmitNum++ & 0x1F] = local133 & 0x7FFF;
            ptype = -1;
            return true;
        } else if (ptype == 102) {
            local133 = in.method2192();
            local786 = in.method2180();
            local864 = in.g2();
            @Pc(3766) ClientNPC local3766 = npcs[local133];
            if (local3766 != null) {
                method3855(local786, local864, local3766);
            }
            ptype = -1;
            return true;
        } else if (ptype == 159) {
            legacyUpdated();
            runWeight = in.g2b();
            anInt4808 = transmitNum;
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
                method4540(false, Static214.anInt5581, -1, -1);
            }
            @Pc(3848) byte[] local3848 = new byte[psize];
            in.method2237(local3848, psize);
            local156 = Static10.method346(local3848, psize, 0);
            if (frame == null && (SignLink.anInt5928 == 3 || !SignLink.osNameLower.startsWith("win") || haveie6)) {
                GameShell.method3175(local156, true);
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
            VarCache.method3995(local133, local786);
            ptype = -1;
            return true;
        } else if (ptype == 155) {
            local133 = in.g1();
            local786 = in.method2224();
            local864 = in.method2184();
            local171 = in.g2();
            if (Static248.method3288(local864)) {
                local3456 = (SubInterface) aClass133_9.find((long) local786);
                if (local3456 != null) {
                    closeSubInterface(local3456.id != local171, local3456);
                }
                method1148(local171, local786, local133);
            }
            ptype = -1;
            return true;
        } else if (ptype == 131) {
            for (local133 = 0; local133 < players.length; local133++) {
                if (players[local133] != null) {
                    players[local133].anInt3369 = -1;
                }
            }
            for (local133 = 0; local133 < npcs.length; local133++) {
                if (npcs[local133] != null) {
                    npcs[local133].anInt3369 = -1;
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
            friendTransmitNum = transmitNum;
            ptype = -1;
            return true;
        } else if (ptype == 32) {
            // NPC_INFO
            getNpcPos();
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
            local1986 -= mapBuildBaseX;
            local908 -= mapBuildBaseZ;
            local1160 = local277 >> 28 & 0x3;
            method1881(local1160, local864, local786, local908, local171, local1986, local1146);
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
                        if (aBoolean157 && !aBoolean236 || aBoolean129) {
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
            if (!local4425 && overrideChat == 0) {
                Static233.aLongArray9[Static251.anInt5447] = local4431;
                Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
                @Pc(4518) JagString local4518 = Static218.method2862(Static65.method1497(in).method3116());
                if (local1160 == 2 || local1160 == 3) {
                    addChat(JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }), 7, local4518);
                } else if (local1160 == 1) {
                    addChat(JagString.join(new JagString[] { aClass100_435, Static79.toBaseDisplayName(local884).method3125() }), 7, local4518);
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
                        if (aBoolean157 && !aBoolean236 || aBoolean129) {
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
            if (!local4632 && overrideChat == 0) {
                Static233.aLongArray9[Static251.anInt5447] = local4626;
                Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
                local3038 = Static218.method2862(Static65.method1497(in).method3116());
                if (local908 == 2 || local908 == 3) {
                    method1598(local3038, JagString.join(new JagString[] { Static44.aClass100_336, Static79.toBaseDisplayName(local884).method3125() }), Static79.toBaseDisplayName(local275).method3125());
                } else if (local908 == 1) {
                    method1598(local3038, JagString.join(new JagString[] { aClass100_435, Static79.toBaseDisplayName(local884).method3125() }), Static79.toBaseDisplayName(local275).method3125());
                } else {
                    method1598(local3038, Static79.toBaseDisplayName(local884).method3125(), Static79.toBaseDisplayName(local275).method3125());
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
            method744(local786, local133, local864);
            ptype = -1;
            return true;
        } else if (ptype == 66) {
            local133 = in.method2207();
            local786 = in.method2206();
            if (Static248.method3288(local133)) {
                local864 = 0;
                if (localPlayer.aClass59_1 != null) {
                    local864 = localPlayer.aClass59_1.method1952();
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
            VarCache.method3995(local133, local786);
            ptype = -1;
            return true;
        }
        if (ptype == 22) {
            local133 = in.g4();
            local786 = in.g2();
            if (local133 < -70000) {
                local786 += 32768;
            }
            if (local133 < 0) {
                local4956 = null;
            } else {
                local4956 = IfType.get(local133);
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
                componentUpdated(local4956);
            }
            legacyUpdated();
            invTransmit[invTransmitNum++ & 0x1F] = local786 & 0x7FFF;
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
            logout();
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
            miscTransmitNum = transmitNum;
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
                    local1804 = (ServerActive) serverActive.find(local904);
                    if (local1804 != null) {
                        local1814 = new ServerActive(local1146, local1804.anInt540);
                        local1804.unlink();
                    } else if (local277 == -1) {
                        local1814 = new ServerActive(local1146, IfType.get(local864).active.anInt540);
                    } else {
                        local1814 = new ServerActive(local1146, -1);
                    }
                    serverActive.put(local1814, local904);
                }
            }
            ptype = -1;
            return true;
        } else if (ptype == 197) {
            Static166.anInt4054 = in.g1();
            friendTransmitNum = transmitNum;
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
                        clanTransmitNum = transmitNum;
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
            clanTransmitNum = transmitNum;
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
                @Pc(5603) IfType local5603 = IfType.get(local786);
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
                    componentUpdated(local5603);
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
                local4956 = IfType.get(local133);
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
                componentUpdated(local4956);
            }
            legacyUpdated();
            invTransmit[invTransmitNum++ & 0x1F] = local786 & 0x7FFF;
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
            method4650(local133, local786);
            ptype = -1;
            return true;
        } else {
            JagException.report("T1 - " + ptype + "," + ptype1 + "," + ptype2 + " - " + psize, null);
            logout();
            return true;
        }
    }

	@OriginalMember(owner = "client!an", name = "h", descriptor = "(I)Z")
	public static boolean method349() {
		try {
			return tcpIn();
		} catch (@Pc(14) IOException ex) {
			lostCon();
			return true;
		} catch (@Pc(19) Exception ex) {
			@Pc(61) String str = "T2 - " + ptype + "," + ptype1 + "," + ptype2 + " - " + psize + "," + (mapBuildBaseX + localPlayer.routeX[0]) + "," + (localPlayer.routeZ[0] + mapBuildBaseZ) + " - ";
			for (@Pc(63) int i = 0; i < psize && i < 50; i++) {
				str = str + in.data[i] + ",";
			}
			JagException.report(str, ex);
			logout();
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
		chatTransmitNum = transmitNum;
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
		IfType.method1019();
		Static180.method3329();
		Static251.method4276();
		Static73.aClass99_10.clear();
		Static139.aClass99_22.clear();
	}

	@OriginalMember(owner = "client!dc", name = "b", descriptor = "(Z)V")
	public static void getPlayerPosOldVis() {
		@Pc(6) int local6 = in.method2238(8);
		@Pc(20) int local20;
		if (playerCount > local6) {
			for (local20 = local6; local20 < playerCount; local20++) {
				entityRemovalIds[entityRemovalCount++] = playerIds[local20];
			}
		}
		if (local6 > playerCount) {
			throw new RuntimeException("gppov1");
		}
		playerCount = 0;
		for (local20 = 0; local20 < local6; local20++) {
			@Pc(75) int local75 = playerIds[local20];
			@Pc(79) ClientPlayer local79 = players[local75];
			@Pc(84) int local84 = in.method2238(1);
			if (local84 == 0) {
				playerIds[playerCount++] = local75;
				local79.cycle = loopCycle;
			} else {
				@Pc(107) int local107 = in.method2238(2);
				if (local107 == 0) {
					playerIds[playerCount++] = local75;
					local79.cycle = loopCycle;
					entityUpdateIds[entityUpdateCount++] = local75;
				} else {
					@Pc(153) int local153;
					@Pc(163) int local163;
					if (local107 == 1) {
						playerIds[playerCount++] = local75;
						local79.cycle = loopCycle;
						local153 = in.method2238(3);
						local79.method2684(1, local153);
						local163 = in.method2238(1);
						if (local163 == 1) {
							entityUpdateIds[entityUpdateCount++] = local75;
						}
					} else if (local107 == 2) {
						playerIds[playerCount++] = local75;
						local79.cycle = loopCycle;
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
							entityUpdateIds[entityUpdateCount++] = local75;
						}
					} else if (local107 == 3) {
						entityRemovalIds[entityRemovalCount++] = local75;
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
		IfType.method1289();
		Static158.method3010();
		Static134.method2621();
		Static73.aClass99_10.method3102(5);
		Static139.aClass99_22.method3102(5);
	}

	@OriginalMember(owner = "client!eg", name = "a", descriptor = "(IIIIIIII)V")
	public static void loopInterface(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (IfType.openInterface(arg4)) {
			loopLayer(IfType.list[arg4], -1, arg5, arg1, arg3, arg6, arg0, arg2);
		}
	}

	@OriginalMember(owner = "client!gg", name = "e", descriptor = "(II)V")
	public static void ifAnimReset(@OriginalArg(0) int arg0) {
		if (!IfType.openInterface(arg0)) {
			return;
		}
		@Pc(15) IfType[] local15 = IfType.list[arg0];
		for (@Pc(17) int local17 = 0; local17 < local15.length; local17++) {
			@Pc(29) IfType local29 = local15[local17];
			if (local29 != null) {
				local29.anInt496 = 1;
				local29.anInt510 = 0;
				local29.anInt500 = 0;
			}
		}
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(II)V")
	public static void animateInterface(@OriginalArg(1) int arg0) {
		if (IfType.openInterface(arg0)) {
			animateLayer(-1, IfType.list[arg0]);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "([Lclient!be;IIIIIII)V")
	public static void loopLayer(@OriginalArg(0) IfType[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		for (@Pc(1) int local1 = 0; local1 < arg0.length; local1++) {
			@Pc(9) IfType local9 = arg0[local1];
            if (local9 == null || local9.layerId != arg1 || (local9.v3 && local9.type != 0 && !local9.aBoolean25 && getActive(local9).eventCode == 0 && local9 != aClass13_1 && local9.clientCode != 1338) || (local9.v3 && hide(local9))) {
                continue;
            }

            @Pc(50) int local50 = local9.anInt523 + arg6;
            @Pc(55) int local55 = local9.anInt469 + arg7;
            @Pc(61) int local61;
            @Pc(63) int local63;
            @Pc(65) int local65;
            @Pc(67) int local67;
            if (local9.type == 2) {
                local61 = arg2;
                local63 = arg3;
                local65 = arg4;
                local67 = arg5;
            } else {
                @Pc(73) int local73 = local50 + local9.anInt445;
                @Pc(78) int local78 = local55 + local9.anInt459;
                if (local9.type == 9) {
                    local73++;
                    local78++;
                }
                local61 = local50 > arg2 ? local50 : arg2;
                local63 = local55 > arg3 ? local55 : arg3;
                local65 = local73 < arg4 ? local73 : arg4;
                local67 = local78 < arg5 ? local78 : arg5;
            }
            if (local9 == dragComponent) {
                aBoolean83 = true;
                anInt3075 = local50;
                anInt660 = local55;
            }

            if (local9.v3 && (local61 >= local65 || local63 >= local67)) {
                continue;
            }

            if (local9.type == 0) {
                if (!local9.v3 && hide(local9) && hoveredCom != local9) {
                    continue;
                }

                if (local9.aBoolean29 && ClientMouseListener.mouseX >= local61 && ClientMouseListener.mouseY >= local63 && ClientMouseListener.mouseX < local65 && ClientMouseListener.mouseY < local67) {
                    for (@Pc(164) HookReq local164 = (HookReq) hookRequests.head(); local164 != null; local164 = (HookReq) hookRequests.next()) {
                        if (local164.aBoolean158) {
                            local164.unlink();
                            local164.component.aBoolean19 = false;
                        }
                    }

                    if (anInt4851 == 0) {
                        dragComponent = null;
                        aClass13_1 = null;
                    }

                    anInt3337 = 0;
                }
            }

            if (local9.v3) {
                @Pc(207) boolean local207;
                if (ClientMouseListener.mouseX >= local61 && ClientMouseListener.mouseY >= local63 && ClientMouseListener.mouseX < local65 && ClientMouseListener.mouseY < local67) {
                    local207 = true;
                } else {
                    local207 = false;
                }

                @Pc(212) boolean local212 = false;
                if (ClientMouseListener.mouseButton == 1 && local207) {
                    local212 = true;
                }

                @Pc(221) boolean local221 = false;
                if (ClientMouseListener.mouseClickButton == 1 && ClientMouseListener.mouseClickX >= local61 && ClientMouseListener.mouseClickY >= local63 && ClientMouseListener.mouseClickX < local65 && ClientMouseListener.mouseClickY < local67) {
                    local221 = true;
                }

                @Pc(243) int local243;
                @Pc(322) int local322;
                if (local9.aByteArray8 != null) {
                    for (local243 = 0; local243 < local9.aByteArray8.length; local243++) {
                        if (ClientKeyboardListener.keyHeld[local9.aByteArray8[local243]]) {
                            if (local9.anIntArray49 != null && loopCycle < local9.anIntArray49[local243]) {
                                continue;
                            }
                            @Pc(279) byte local279 = local9.aByteArray7[local243];
                            if (local279 == 0 || ((local279 & 0x2) == 0 || ClientKeyboardListener.keyHeld[86]) && ((local279 & 0x1) == 0 || ClientKeyboardListener.keyHeld[82]) && ((local279 & 0x4) == 0 || ClientKeyboardListener.keyHeld[81])) {
                                method4512(TitleScreen.AUTO_EMPTY, -1, local243 + 1, local9.parentId);
                                local322 = local9.anIntArray46[local243];
                                if (local9.anIntArray49 == null) {
                                    local9.anIntArray49 = new int[local9.aByteArray8.length];
                                }
                                if (local322 == 0) {
                                    local9.anIntArray49[local243] = Integer.MAX_VALUE;
                                } else {
                                    local9.anIntArray49[local243] = loopCycle + local322;
                                }
                            }
                        } else if (local9.anIntArray49 != null) {
                            local9.anIntArray49[local243] = 0;
                        }
                    }
                }

                if (local221) {
                    method1015(ClientMouseListener.mouseClickY - local55, ClientMouseListener.mouseClickX - local50, local9);
                }

                if (dragComponent != null && dragComponent != local9 && local207 && getActive(local9).method509()) {
                    aClass13_12 = local9;
                }

                if (local9 == aClass13_1) {
                    aBoolean174 = true;
                    anInt2225 = local50;
                    anInt5103 = local55;
                }

                if (local9.aBoolean25 || local9.clientCode != 0) {
                    @Pc(399) HookReq req;
                    if (local207 && mouseWheelRotation != 0 && local9.onscrollwheel != null) {
                        req = new HookReq();
                        req.aBoolean158 = true;
                        req.component = local9;
                        req.mouseY = mouseWheelRotation;
                        req.onop = local9.onscrollwheel;
                        hookRequests.push(req);
                    }

                    if (dragComponent != null || objDragInterface != null || isMenuOpen || local9.clientCode != 1400 && anInt3337 > 0) {
                        local221 = false;
                        local212 = false;
                        local207 = false;
                    }

                    @Pc(508) int local508;
                    if (local9.clientCode != 0) {
                        if (local9.clientCode == 1337) {
                            aClass13_26 = local9;
                            componentUpdated(local9);
                            continue;
                        }

                        if (local9.clientCode == 1338) {
                            if (local221) {
                                Static1.anInt5 = ClientMouseListener.mouseClickX - local50;
                                Static107.anInt2878 = ClientMouseListener.mouseClickY - local55;
                            }
                            continue;
                        }

                        if (local9.clientCode == 1400) {
                            aClass13_8 = local9;
                            if (local221) {
                                if (ClientKeyboardListener.keyHeld[82] && staffmodlevel > 0) {
                                    local243 = (int) ((double) (ClientMouseListener.mouseClickX - local50 - local9.anInt445 / 2) * 2.0D / (double) WorldMap.aFloat3);
                                    local508 = (int) ((double) (ClientMouseListener.mouseClickY - local55 - local9.anInt459 / 2) * 2.0D / (double) WorldMap.aFloat3);
                                    local322 = WorldMap.anInt435 + local243;
                                    @Pc(516) int local516 = WorldMap.anInt919 + local508;
                                    @Pc(520) int local520 = local322 + WorldMap.anInt3846;
                                    @Pc(528) int local528 = WorldMap.anInt4296 + WorldMap.anInt13 - local516 - 1;
                                    tele(local520, local528, 0);
                                    method2909();
                                    continue;
                                }
                                anInt3337 = 1;
                                Static246.anInt5388 = ClientMouseListener.mouseX;
                                anInt4035 = ClientMouseListener.mouseY;
                                continue;
                            }

                            if (local212 && anInt3337 > 0) {
                                if (anInt3337 == 1 && (Static246.anInt5388 != ClientMouseListener.mouseX || anInt4035 != ClientMouseListener.mouseY)) {
                                    Static197.anInt4620 = WorldMap.anInt435;
                                    Static71.anInt1885 = WorldMap.anInt919;
                                    anInt3337 = 2;
                                }
                                if (anInt3337 == 2) {
                                    WorldMap.method1964(Static197.anInt4620 + (int) ((double) (Static246.anInt5388 - ClientMouseListener.mouseX) * 2.0D / (double) WorldMap.aFloat14));
                                    WorldMap.method4641(Static71.anInt1885 + (int) ((double) (anInt4035 - ClientMouseListener.mouseY) * 2.0D / (double) WorldMap.aFloat14));
                                }
                                continue;
                            }

                            anInt3337 = 0;
                            continue;
                        }

                        if (local9.clientCode == 1401) {
                            if (local212) {
                                WorldMap.method2387(local9.anInt445, ClientMouseListener.mouseY - local55, ClientMouseListener.mouseX - local50, local9.anInt459);
                            }
                            continue;
                        }

                        if (local9.clientCode == 1402) {
                            if (!glRenderer) {
                                componentUpdated(local9);
                            }
                            continue;
                        }
                    }

                    if (!local9.clickTrigger && local221) {
                        local9.clickTrigger = true;
                        if (local9.onclick != null) {
                            req = new HookReq();
                            req.aBoolean158 = true;
                            req.component = local9;
                            req.mouseX = ClientMouseListener.mouseClickX - local50;
                            req.mouseY = ClientMouseListener.mouseClickY - local55;
                            req.onop = local9.onclick;
                            hookRequests.push(req);
                        }
                    }

                    if (local9.clickTrigger && local212 && local9.onclickrepeat != null) {
                        req = new HookReq();
                        req.aBoolean158 = true;
                        req.component = local9;
                        req.mouseX = ClientMouseListener.mouseX - local50;
                        req.mouseY = ClientMouseListener.mouseY - local55;
                        req.onop = local9.onclickrepeat;
                        hookRequests.push(req);
                    }

                    if (local9.clickTrigger && !local212) {
                        local9.clickTrigger = false;
                        if (local9.onrelease != null) {
                            req = new HookReq();
                            req.aBoolean158 = true;
                            req.component = local9;
                            req.mouseX = ClientMouseListener.mouseX - local50;
                            req.mouseY = ClientMouseListener.mouseY - local55;
                            req.onop = local9.onrelease;
                            hookRequestsMouseStop.push(req);
                        }
                    }

                    if (local212 && local9.onhold != null) {
                        req = new HookReq();
                        req.aBoolean158 = true;
                        req.component = local9;
                        req.mouseX = ClientMouseListener.mouseX - local50;
                        req.mouseY = ClientMouseListener.mouseY - local55;
                        req.onop = local9.onhold;
                        hookRequests.push(req);
                    }

                    if (!local9.aBoolean19 && local207) {
                        local9.aBoolean19 = true;
                        if (local9.onmouseover != null) {
                            req = new HookReq();
                            req.aBoolean158 = true;
                            req.component = local9;
                            req.mouseX = ClientMouseListener.mouseX - local50;
                            req.mouseY = ClientMouseListener.mouseY - local55;
                            req.onop = local9.onmouseover;
                            hookRequests.push(req);
                        }
                    }

                    if (local9.aBoolean19 && local207 && local9.onmouserepeat != null) {
                        req = new HookReq();
                        req.aBoolean158 = true;
                        req.component = local9;
                        req.mouseX = ClientMouseListener.mouseX - local50;
                        req.mouseY = ClientMouseListener.mouseY - local55;
                        req.onop = local9.onmouserepeat;
                        hookRequests.push(req);
                    }

                    if (local9.aBoolean19 && !local207) {
                        local9.aBoolean19 = false;
                        if (local9.onmouseleave != null) {
                            req = new HookReq();
                            req.aBoolean158 = true;
                            req.component = local9;
                            req.mouseX = ClientMouseListener.mouseX - local50;
                            req.mouseY = ClientMouseListener.mouseY - local55;
                            req.onop = local9.onmouseleave;
                            hookRequestsMouseStop.push(req);
                        }
                    }

                    if (local9.ontimer != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.ontimer;
                        hookRequestsTimer.push(req);
                    }

                    @Pc(966) HookReq local966;
                    if (local9.onvarctransmit != null && varcTransmitNum > local9.varcTransmitNum) {
                        if (local9.onvarctransmitlist == null || varcTransmitNum - local9.varcTransmitNum > 32) {
                            req = new HookReq();
                            req.component = local9;
                            req.onop = local9.onvarctransmit;
                            hookRequests.push(req);
                        } else {
                            label563: for (local243 = local9.varcTransmitNum; local243 < varcTransmitNum; local243++) {
                                local508 = varcTransmit[local243 & 0x1F];
                                for (local322 = 0; local322 < local9.onvarctransmitlist.length; local322++) {
                                    if (local9.onvarctransmitlist[local322] == local508) {
                                        local966 = new HookReq();
                                        local966.component = local9;
                                        local966.onop = local9.onvarctransmit;
                                        hookRequests.push(local966);
                                        break label563;
                                    }
                                }
                            }
                        }
                        local9.varcTransmitNum = varcTransmitNum;
                    }

                    if (local9.onvarcstrtransmit != null && varcstrTransmitNum > local9.varcstrTransmitNum) {
                        if (local9.onvarcstrtransmitlist == null || varcstrTransmitNum - local9.varcstrTransmitNum > 32) {
                            req = new HookReq();
                            req.component = local9;
                            req.onop = local9.onvarcstrtransmit;
                            hookRequests.push(req);
                        } else {
                            label539: for (local243 = local9.varcstrTransmitNum; local243 < varcstrTransmitNum; local243++) {
                                local508 = varcstrTransmit[local243 & 0x1F];
                                for (local322 = 0; local322 < local9.onvarcstrtransmitlist.length; local322++) {
                                    if (local9.onvarcstrtransmitlist[local322] == local508) {
                                        local966 = new HookReq();
                                        local966.component = local9;
                                        local966.onop = local9.onvarcstrtransmit;
                                        hookRequests.push(local966);
                                        break label539;
                                    }
                                }
                            }
                        }
                        local9.varcstrTransmitNum = varcstrTransmitNum;
                    }

                    if (local9.onvartransmit != null && varTransmitNum > local9.varTransmitNum) {
                        if (local9.onvartransmitlist == null || varTransmitNum - local9.varTransmitNum > 32) {
                            req = new HookReq();
                            req.component = local9;
                            req.onop = local9.onvartransmit;
                            hookRequests.push(req);
                        } else {
                            label515: for (local243 = local9.varTransmitNum; local243 < varTransmitNum; local243++) {
                                local508 = varTransmit[local243 & 0x1F];
                                for (local322 = 0; local322 < local9.onvartransmitlist.length; local322++) {
                                    if (local9.onvartransmitlist[local322] == local508) {
                                        local966 = new HookReq();
                                        local966.component = local9;
                                        local966.onop = local9.onvartransmit;
                                        hookRequests.push(local966);
                                        break label515;
                                    }
                                }
                            }
                        }
                        local9.varTransmitNum = varTransmitNum;
                    }

                    if (local9.oninvtransmit != null && invTransmitNum > local9.invTransmit) {
                        if (local9.oninvtransmitlist == null || invTransmitNum - local9.invTransmit > 32) {
                            req = new HookReq();
                            req.component = local9;
                            req.onop = local9.oninvtransmit;
                            hookRequests.push(req);
                        } else {
                            label491: for (local243 = local9.invTransmit; local243 < invTransmitNum; local243++) {
                                local508 = invTransmit[local243 & 0x1F];
                                for (local322 = 0; local322 < local9.oninvtransmitlist.length; local322++) {
                                    if (local9.oninvtransmitlist[local322] == local508) {
                                        local966 = new HookReq();
                                        local966.component = local9;
                                        local966.onop = local9.oninvtransmit;
                                        hookRequests.push(local966);
                                        break label491;
                                    }
                                }
                            }
                        }
                        local9.invTransmit = invTransmitNum;
                    }

                    if (local9.onstattransmit != null && statTransmitNum > local9.statTransmit) {
                        if (local9.onstattransmitlist == null || statTransmitNum - local9.statTransmit > 32) {
                            req = new HookReq();
                            req.component = local9;
                            req.onop = local9.onstattransmit;
                            hookRequests.push(req);
                        } else {
                            label467: for (local243 = local9.statTransmit; local243 < statTransmitNum; local243++) {
                                local508 = statTransmit[local243 & 0x1F];
                                for (local322 = 0; local322 < local9.onstattransmitlist.length; local322++) {
                                    if (local9.onstattransmitlist[local322] == local508) {
                                        local966 = new HookReq();
                                        local966.component = local9;
                                        local966.onop = local9.onstattransmit;
                                        hookRequests.push(local966);
                                        break label467;
                                    }
                                }
                            }
                        }
                        local9.statTransmit = statTransmitNum;
                    }

                    if (chatTransmitNum > local9.transmitNum && local9.anObjectArray20 != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.anObjectArray20;
                        hookRequests.push(req);
                    }

                    if (friendTransmitNum > local9.transmitNum && local9.anObjectArray1 != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.anObjectArray1;
                        hookRequests.push(req);
                    }

                    if (clanTransmitNum > local9.transmitNum && local9.anObjectArray28 != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.anObjectArray28;
                        hookRequests.push(req);
                    }

                    if (miscTransmitNum > local9.transmitNum && local9.anObjectArray21 != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.anObjectArray21;
                        hookRequests.push(req);
                    }

                    if (anInt4808 > local9.transmitNum && local9.anObjectArray30 != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.anObjectArray30;
                        hookRequests.push(req);
                    }

                    local9.transmitNum = transmitNum;

                    if (local9.onkey != null) {
                        for (local243 = 0; local243 < keypresses; local243++) {
                            @Pc(1430) HookReq local1430 = new HookReq();
                            local1430.component = local9;
                            local1430.keyCode = keypressKeycodes[local243];
                            local1430.keyChar = keypressKeychars[local243];
                            local1430.onop = local9.onkey;
                            hookRequests.push(local1430);
                        }
                    }

                    if (Static13.aBoolean16 && local9.anObjectArray13 != null) {
                        req = new HookReq();
                        req.component = local9;
                        req.onop = local9.anObjectArray13;
                        hookRequests.push(req);
                    }
                }
            }

            if (!local9.v3 && dragComponent == null && objDragInterface == null && !isMenuOpen) {
                if ((local9.overLayerId >= 0 || local9.colourOver != 0) && ClientMouseListener.mouseX >= local61 && ClientMouseListener.mouseY >= local63 && ClientMouseListener.mouseX < local65 && ClientMouseListener.mouseY < local67) {
                    if (local9.overLayerId >= 0) {
                        hoveredCom = arg0[local9.overLayerId];
                    } else {
                        hoveredCom = local9;
                    }
                }

                if (local9.type == 8 && ClientMouseListener.mouseX >= local61 && ClientMouseListener.mouseY >= local63 && ClientMouseListener.mouseX < local65 && ClientMouseListener.mouseY < local67) {
                    tooltipCom = local9;
                }

                if (local9.scrollHeight > local9.anInt459) {
                    doScrollbar(ClientMouseListener.mouseY, local9.anInt459, local9, ClientMouseListener.mouseX, local50 + local9.anInt445, local55, local9.scrollHeight);
                }
            }

            if (local9.type == 0) {
                loopLayer(arg0, local9.parentId, local61, local63, local65, local67, local50 - local9.anInt489, local55 - local9.anInt468);

                if (local9.subcomponents != null) {
                    loopLayer(local9.subcomponents, local9.parentId, local61, local63, local65, local67, local50 - local9.anInt489, local55 - local9.anInt468);
                }

                @Pc(1595) SubInterface local1595 = (SubInterface) aClass133_9.find((long) local9.parentId);
                if (local1595 != null) {
                    loopInterface(local50, local63, local55, local65, local1595.id, local61, local67);
                }
            }
        }
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Lclient!be;)Lclient!bf;")
	public static ServerActive getActive(@OriginalArg(0) IfType arg0) {
		@Pc(13) ServerActive local13 = (ServerActive) serverActive.find(((long) arg0.parentId << 32) + (long) arg0.subId);
		return local13 == null ? arg0.active : local13;
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(Lclient!be;)Z")
	public static boolean hide(@OriginalArg(0) IfType arg0) {
		if (aBoolean154) {
			if (getActive(arg0).eventCode != 0) {
				return false;
			}
			if (arg0.type == 0) {
				return false;
			}
		}
		return arg0.hide;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!be;)Lclient!be;")
	public static IfType method938(@OriginalArg(0) IfType arg0) {
		@Pc(4) int local4 = getActive(arg0).method505();
		if (local4 == 0) {
			return null;
		}
		for (@Pc(10) int local10 = 0; local10 < local4; local10++) {
			arg0 = IfType.get(arg0.layerId);
			if (arg0 == null) {
				return null;
			}
		}
		return arg0;
	}

	@OriginalMember(owner = "client!jd", name = "a", descriptor = "(II[Lclient!be;)V")
	public static void animateLayer(@OriginalArg(1) int arg0, @OriginalArg(2) IfType[] arg1) {
		for (@Pc(7) int local7 = 0; local7 < arg1.length; local7++) {
			@Pc(15) IfType local15 = arg1[local7];
			if (local15 != null && local15.layerId == arg0 && (!local15.v3 || !hide(local15))) {
				if (local15.type == 0) {
					if (!local15.v3 && hide(local15) && local15 != hoveredCom) {
						continue;
					}
					animateLayer(local15.parentId, arg1);
					if (local15.subcomponents != null) {
						animateLayer(local15.parentId, local15.subcomponents);
					}
					@Pc(73) SubInterface local73 = (SubInterface) aClass133_9.find((long) local15.parentId);
					if (local73 != null) {
						animateInterface(local73.id);
					}
				}
				if (local15.type == 6) {
					@Pc(105) int local105;
					if (local15.modelAnim != -1 || local15.modelAnim2 != -1) {
						@Pc(100) boolean local100 = getIfActive(local15);
						if (local100) {
							local105 = local15.modelAnim2;
						} else {
							local105 = local15.modelAnim;
						}
						if (local105 != -1) {
							@Pc(118) SeqType local118 = SeqType.list(local105);
							if (local118 != null) {
								local15.anInt500 += anInt4247;
								while (local15.anInt500 > local118.delay[local15.anInt510]) {
									local15.anInt500 -= local118.delay[local15.anInt510];
									local15.anInt510++;
									if (local118.frames.length <= local15.anInt510) {
										local15.anInt510 -= local118.anInt5362;
										if (local15.anInt510 < 0 || local118.frames.length <= local15.anInt510) {
											local15.anInt510 = 0;
										}
									}
									local15.anInt496 = local15.anInt510 + 1;
									if (local118.frames.length <= local15.anInt496) {
										local15.anInt496 -= local118.anInt5362;
										if (local15.anInt496 < 0 || local118.frames.length <= local15.anInt496) {
											local15.anInt496 = -1;
										}
									}
									componentUpdated(local15);
								}
							}
						}
					}
					if (local15.anInt483 != 0 && !local15.v3) {
						@Pc(239) int local239 = local15.anInt483 >> 16;
						@Pc(243) int local243 = local239 * anInt4247;
						local105 = local15.anInt483 << 16 >> 16;
						local15.modelXAn = local243 + local15.modelXAn & 0x7FF;
						local105 *= anInt4247;
						local15.modelYAn = local15.modelYAn + local105 & 0x7FF;
						componentUpdated(local15);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(III)V")
	public static void runHookImmediate(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (IfType.openInterface(arg1)) {
			runHookLayer(IfType.list[arg1], arg0);
		}
	}

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "([Lclient!be;ZI)V")
	public static void runHookLayer(@OriginalArg(0) IfType[] arg0, @OriginalArg(2) int arg1) {
		for (@Pc(11) int local11 = 0; local11 < arg0.length; local11++) {
			@Pc(23) IfType local23 = arg0[local11];
			if (local23 != null) {
				if (local23.type == 0) {
					if (local23.subcomponents != null) {
						runHookLayer(local23.subcomponents, arg1);
					}
					@Pc(49) SubInterface local49 = (SubInterface) aClass133_9.find((long) local23.parentId);
					if (local49 != null) {
						runHookImmediate(arg1, local49.id);
					}
				}
				@Pc(72) HookReq local72;
				if (arg1 == 0 && local23.anObjectArray12 != null) {
					local72 = new HookReq();
					local72.onop = local23.anObjectArray12;
					local72.component = local23;
					ScriptRunner.executeScript(local72);
				}
				if (arg1 == 1 && local23.anObjectArray8 != null) {
					if (local23.subId >= 0) {
						@Pc(103) IfType local103 = IfType.get(local23.parentId);
						if (local103 == null || local103.subcomponents == null || local23.subId >= local103.subcomponents.length || local103.subcomponents[local23.subId] != local23) {
							continue;
						}
					}
					local72 = new HookReq();
					local72.onop = local23.anObjectArray8;
					local72.component = local23;
					ScriptRunner.executeScript(local72);
				}
			}
		}
	}

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(JI)V")
	public static void method1542(@OriginalArg(0) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		for (@Pc(12) int local12 = 0; local12 < Static35.anInt1093; local12++) {
			if (Static190.aLongArray6[local12] == arg0) {
				Static35.anInt1093--;
				for (@Pc(36) int local36 = local12; local36 < Static35.anInt1093; local36++) {
					Static190.aLongArray6[local36] = Static190.aLongArray6[local36 + 1];
					Static193.aClass100Array134[local36] = Static193.aClass100Array134[local36 + 1];
				}
				friendTransmitNum = transmitNum;
				out.p1Enc(213);
				out.p8(arg0);
				break;
			}
		}
	}

	@OriginalMember(owner = "client!fk", name = "c", descriptor = "(I)V")
	public static void method1445() {
		out.p1Enc(177);
		out.p2(Static189.anInt4443);
	}

	@OriginalMember(owner = "client!gg", name = "a", descriptor = "(Z)V")
	public static void gameLoop() {
		if (logoutTimer > 0) {
			logoutTimer--;
		}

		if (rebootTimer > 1) {
			rebootTimer--;
			anInt4808 = transmitNum;
		}

		if (networkError) {
			networkError = false;
			lostCon();
			return;
		}

		for (int i = 0; i < 100 && method349(); i++) {
		}

		if (state != 30) {
			return;
		}

		ReflectionChecker.performCheck(out);

		@Pc(34) int local34;
		@Pc(60) Object local60 = mouseTracking.lock;
		@Pc(86) int local86;
		@Pc(79) int local79;
		@Pc(88) int local88;
		@Pc(106) int local106;
		@Pc(111) int local111;
		@Pc(182) int local182;
		@Pc(189) int local189;
		synchronized (local60) {
			if (!mouseTracked) {
				mouseTracking.length = 0;
			} else if (ClientMouseListener.mouseClickButton != 0 || mouseTracking.length >= 40) {
				// EVENT_MOUSE_MOVE
				out.p1Enc(123);
				local79 = 0;
				out.p1(0);
				local86 = out.pos;
				for (local88 = 0; mouseTracking.length > local88 && out.pos - local86 < 240; local88++) {
					local79++;
					local106 = mouseTracking.y[local88];
					local111 = mouseTracking.x[local88];
					if (local106 < 0) {
						local106 = 0;
					} else if (local106 > 65534) {
						local106 = 65534;
					}
					if (local111 < 0) {
						local111 = 0;
					} else if (local111 > 65534) {
						local111 = 65534;
					}
					@Pc(142) boolean local142 = false;
					if (mouseTracking.y[local88] == -1 && mouseTracking.x[local88] == -1) {
						local142 = true;
						local106 = -1;
						local111 = -1;
					}
					if (lastWriteX != local111 || local106 != lastWriteY) {
						local182 = local111 - lastWriteX;
						lastWriteX = local111;
						local189 = local106 - lastWriteY;
						lastWriteY = local106;
						if (mouseTrackedDelta < 8 && local182 >= -32 && local182 <= 31 && local189 >= -32 && local189 <= 31) {
							local189 += 32;
							local182 += 32;
							out.p2(local189 + (mouseTrackedDelta << 12) + (local182 << 6));
							mouseTrackedDelta = 0;
						} else if (mouseTrackedDelta < 32 && local182 >= -128 && local182 <= 127 && local189 >= -128 && local189 <= 127) {
							out.p1(mouseTrackedDelta + 128);
							local189 += 128;
							local182 += 128;
							out.p2((local182 << 8) + local189);
							mouseTrackedDelta = 0;
						} else if (mouseTrackedDelta < 32) {
							out.p1(mouseTrackedDelta + 192);
							if (local142) {
								out.p4(Integer.MIN_VALUE);
							} else {
								out.p4(local111 | local106 << 16);
							}
							mouseTrackedDelta = 0;
						} else {
							out.p2(mouseTrackedDelta + 57344);
							if (local142) {
								out.p4(Integer.MIN_VALUE);
							} else {
								out.p4(local111 | local106 << 16);
							}
							mouseTrackedDelta = 0;
						}
					} else if (mouseTrackedDelta < 2047) {
						mouseTrackedDelta++;
					}
				}
				out.psize1(out.pos - local86);
				if (mouseTracking.length > local79) {
					mouseTracking.length -= local79;
					for (local88 = 0; local88 < mouseTracking.length; local88++) {
						mouseTracking.x[local88] = mouseTracking.x[local79 + local88];
						mouseTracking.y[local88] = mouseTracking.y[local88 + local79];
					}
				} else {
					mouseTracking.length = 0;
				}
			}
		}

		if (ClientMouseListener.mouseClickButton != 0) {
			@Pc(411) long local411 = (ClientMouseListener.aLong175 - aLong139) / 50L;
			local79 = ClientMouseListener.mouseClickY;
			if (local79 < 0) {
				local79 = 0;
			} else if (local79 > 65535) {
				local79 = 65535;
			}
			if (local411 > 32767L) {
				local411 = 32767L;
			}
			local88 = ClientMouseListener.mouseClickX;
			aLong139 = ClientMouseListener.aLong175;
			@Pc(437) byte local437 = 0;
			if (local88 < 0) {
				local88 = 0;
			} else if (local88 > 65535) {
				local88 = 65535;
			}
			local111 = (int) local411;
			if (ClientMouseListener.mouseClickButton == 2) {
				local437 = 1;
			}
			// EVENT_MOUSE_CLICK
			out.p1Enc(75);
			out.method2191(local437 << 15 | local111);
			out.method2185(local88 | local79 << 16);
		}

		if (sendCameraDelay > 0) {
			sendCameraDelay--;
		}

		if (aBoolean63) {
			for (local34 = 0; local34 < keypresses; local34++) {
				local86 = keypressKeycodes[local34];
				if (local86 == 98 || local86 == 99 || local86 == 96 || local86 == 97) {
					sendCamera = true;
					break;
				}
			}
		} else if (ClientKeyboardListener.keyHeld[96] || ClientKeyboardListener.keyHeld[97] || ClientKeyboardListener.keyHeld[98] || ClientKeyboardListener.keyHeld[99]) {
			sendCamera = true;
		}

		if (sendCamera && sendCameraDelay <= 0) {
			sendCameraDelay = 20;
			sendCamera = false;
			// EVENT_CAMERA_POSITION
			out.p1Enc(21);
			out.method2209(anInt2031);
			out.method2222(anInt1747);
		}

		if (focus && !focusIn) {
			focusIn = true;
			// EVENT_APPLET_FOCUS
			out.p1Enc(22);
			out.p1(1);
		}
		if (!focus && focusIn) {
			focusIn = false;
			// EVENT_APPLET_FOCUS
			out.p1Enc(22);
			out.p1(0);
		}

		if (!Static18.aBoolean39) {
			out.p1Enc(98);
			out.p4(Static145.method2746());
			Static18.aBoolean39 = true;
		}

		checkMinimap();

		if (state != 30) {
			return;
		}

		locChangeDoQueue();
		method3473();
		soundsDoQueue();

		timeoutTimer++;
		if (timeoutTimer > 750) {
			lostCon();
			return;
		}

		movePlayers();
		moveNpcs();
		timeoutChat();

		if (aClass13_8 != null) {
			WorldMap.method447();
		}

		for (local34 = Static38.method963(true); local34 != -1; local34 = Static38.method963(false)) {
			clientVar(local34);
			varTransmit[varTransmitNum++ & 0x1F] = local34;
		}

		@Pc(782) int local782;
		for (@Pc(709) DelayedStateChange local709 = Static127.method2464(); local709 != null; local709 = Static127.method2464()) {
			local79 = local709.method1011();
			local88 = local709.method1012();
			if (local79 == 1) {
				varcInt[local88] = local709.anInt1271;
				varcTransmit[varcTransmitNum++ & 0x1F] = local88;
			} else if (local79 == 2) {
				Static226.varcStr[local88] = local709.aClass100_254;
				varcstrTransmit[varcstrTransmitNum++ & 0x1F] = local88;
			} else {
				@Pc(773) IfType local773;
				if (local79 == 3) {
					local773 = IfType.get(local88);
					if (!local709.aClass100_254.equalsInner(local773.text)) {
						local773.text = local709.aClass100_254;
						componentUpdated(local773);
					}
				} else if (local79 == 4) {
					local773 = IfType.get(local88);
					local111 = local709.anInt1271;
					local182 = local709.anInt1269;
					local782 = local709.anInt1270;
					if (local773.model1Type != local111 || local773.model1Id != local782 || local182 != local773.anInt498) {
						local773.model1Id = local782;
						local773.anInt498 = local182;
						local773.model1Type = local111;
						componentUpdated(local773);
					}
				} else if (local79 == 5) {
					local773 = IfType.get(local88);
					if (local773.modelAnim != local709.anInt1271 || local709.anInt1271 == -1) {
						local773.anInt496 = 1;
						local773.anInt500 = 0;
						local773.modelAnim = local709.anInt1271;
						local773.anInt510 = 0;
						componentUpdated(local773);
					}
				} else if (local79 == 6) {
					local106 = local709.anInt1271;
					local111 = local106 >> 10 & 0x1F;
					local182 = local106 & 0x1F;
					local782 = local106 >> 5 & 0x1F;
					@Pc(1189) IfType local1189 = IfType.get(local88);
					local189 = (local182 << 3) + (local782 << 11) + (local111 << 19);
					if (local189 != local1189.colour) {
						local1189.colour = local189;
						componentUpdated(local1189);
					}
				} else if (local79 == 7) {
					local773 = IfType.get(local88);
					@Pc(1145) boolean local1145 = local709.anInt1271 == 1;
					if (local773 != null && local1145 != local773.hide) {
						// component != null && hidden != component.hidden
						local773.hide = local1145;
						componentUpdated(local773);
					}
				} else if (local79 == 8) {
					local773 = IfType.get(local88);
					if (local709.anInt1271 != local773.modelXAn || local773.modelYAn != local709.anInt1270 || local709.anInt1269 != local773.modelZoom) {
						local773.modelXAn = local709.anInt1271;
						local773.modelZoom = local709.anInt1269;
						local773.modelYAn = local709.anInt1270;
						if (local773.anInt458 != -1) {
							if (local773.anInt451 > 0) {
								local773.modelZoom = local773.modelZoom * 32 / local773.anInt451;
							} else if (local773.width > 0) {
								local773.modelZoom = local773.modelZoom * 32 / local773.width;
							}
						}
						componentUpdated(local773);
					}
				} else if (local79 == 9) {
					local773 = IfType.get(local88);
					if (local709.anInt1271 != local773.anInt458 || local773.anInt503 != local709.anInt1270) {
						local773.anInt458 = local709.anInt1271;
						local773.anInt503 = local709.anInt1270;
						componentUpdated(local773);
					}
				} else if (local79 == 10) {
					local773 = IfType.get(local88);
					if (local773.anInt494 != local709.anInt1271 || local709.anInt1270 != local773.anInt497 || local773.modelZAn != local709.anInt1269) {
						local773.anInt497 = local709.anInt1270;
						local773.modelZAn = local709.anInt1269;
						local773.anInt494 = local709.anInt1271;
						componentUpdated(local773);
					}
				} else if (local79 == 11) {
					local773 = IfType.get(local88);
					local773.anInt523 = local773.dataX = local709.anInt1271;
					local773.aByte4 = 0;
					local773.aByte2 = 0;
					local773.anInt469 = local773.dataY = local709.anInt1270;
					componentUpdated(local773);
				} else if (local79 == 12) {
					local773 = IfType.get(local88);
					local111 = local709.anInt1271;
					if (local773 != null && local773.type == 0) {
						if (local111 > local773.scrollHeight - local773.anInt459) {
							local111 = local773.scrollHeight - local773.anInt459;
						}
						if (local111 < 0) {
							local111 = 0;
						}
						if (local111 != local773.anInt468) {
							local773.anInt468 = local111;
							componentUpdated(local773);
						}
					}
				} else if (local79 == 13) {
					local773 = IfType.get(local88);
					local773.anInt483 = local709.anInt1271;
				}
			}
		}

		if (crossMode != 0) {
			crossCycle += 20;

			if (crossCycle >= 400) {
				crossMode = 0;
			}
		}

		anInt4247++;

		if (selectedArea != null) {
			selectedCycle++;

			if (selectedCycle >= 15) {
				componentUpdated(selectedArea);
				selectedArea = null;
			}
		}

		@Pc(1361) IfType local1361;
		if (objDragInterface != null) {
			componentUpdated(objDragInterface);

			if (objGrabX + 5 < ClientMouseListener.mouseX || ClientMouseListener.mouseX < objGrabX - 5 || objGrabY + 5 < ClientMouseListener.mouseY || objGrabY - 5 > ClientMouseListener.mouseY) {
				objGrabThreshold = true;
			}

			objDragCycles++;

			if (ClientMouseListener.mouseButton == 0) {
				if (objGrabThreshold && objDragCycles >= 5) {
					if (objDragInterface == hoveredSlotParent && anInt36 != anInt588) {
						local1361 = objDragInterface;
						@Pc(1363) byte local1363 = 0;
						if (bankArrangeMode == 1 && local1361.clientCode == 206) {
							local1363 = 1;
						}
						if (local1361.linkObjNumber[anInt588] <= 0) {
							local1363 = 0;
						}
						if (getActive(local1361).method504()) {
							local106 = anInt36;
							local111 = anInt588;
							local1361.linkObjNumber[local111] = local1361.linkObjNumber[local106];
							local1361.linkObjType[local111] = local1361.linkObjType[local106];
							local1361.linkObjNumber[local106] = -1;
							local1361.linkObjType[local106] = 0;
						} else if (local1363 == 1) {
							local111 = anInt588;
							local106 = anInt36;
							while (local111 != local106) {
								if (local106 > local111) {
									local1361.method487(local106 - 1, local106);
									local106--;
								} else if (local111 > local106) {
									local1361.method487(local106 + 1, local106);
									local106++;
								}
							}
						} else {
							local1361.method487(anInt588, anInt36);
						}
						out.p1Enc(231);
						out.p2(anInt36);
						out.method2211(objDragInterface.parentId);
						out.method2209(anInt588);
						out.method2169(local1363);
					}
				} else if ((oneMouseButton == 1 || method4640(menuNumEntries - 1)) && menuNumEntries > 2) {
					method3901();
				} else if (menuNumEntries > 0) {
					method1372();
				}

				ClientMouseListener.mouseClickButton = 0;
				selectedCycle = 10;
				objDragInterface = null;
			}
		}

		IfType hovered = hoveredCom;
		@Pc(1508) IfType tooltip = tooltipCom;
		hoveredCom = null;
		tooltipCom = null;
		aBoolean174 = false;
		aClass13_12 = null;
		aBoolean83 = false;
		aClass13_8 = null;

		keypresses = 0;
		while (ClientKeyboardListener.pollKey() && keypresses < 128) {
			keypressKeycodes[keypresses] = ClientKeyboardListener.code;
			keypressKeychars[keypresses] = ClientKeyboardListener.ch;
			keypresses++;
		}

		if (toplevelinterface != -1) {
			loopInterface(0, 0, 0, anInt1448, toplevelinterface, 0, anInt5554);
		}

		transmitNum++;

		while (true) {
			@Pc(1569) IfType com;
			@Pc(1560) IfType child;
			@Pc(1555) HookReq req;

			do {
				req = (HookReq) hookRequestsTimer.popFront();
				if (req == null) {
					break;
				}

				child = req.component;
				if (child.subId < 0) {
					break;
				}

				com = IfType.get(child.layerId);
			} while (com == null || com.subcomponents == null || child.subId >= com.subcomponents.length || com.subcomponents[child.subId] != child);
			if (req != null) {
				ScriptRunner.executeScript(req);
				continue;
			}

			do {
				req = (HookReq) hookRequestsMouseStop.popFront();
				if (req == null) {
					break;
				}

				child = req.component;
				if (child.subId < 0) {
					break;
				}

				com = IfType.get(child.layerId);
			} while (com == null || com.subcomponents == null || com.subcomponents.length <= child.subId || com.subcomponents[child.subId] != child);
			if (req != null) {
				ScriptRunner.executeScript(req);
				continue;
			}

			do {
				req = (HookReq) hookRequests.popFront();
				if (req == null) {
					break;
				}

				child = req.component;
				if (child.subId < 0) {
					break;
				}

				com = IfType.get(child.layerId);
			} while (com == null || com.subcomponents == null || child.subId >= com.subcomponents.length || child != com.subcomponents[child.subId]);
			if (req != null) {
				ScriptRunner.executeScript(req);
				continue;
			}

			break;
		}

		if (aClass13_8 == null) {
			anInt3337 = 0;
		}

		if (dragComponent != null) {
			loopIf3Drag();
		}

		if (staffmodlevel > 0 && ClientKeyboardListener.keyHeld[82] && ClientKeyboardListener.keyHeld[81] && mouseWheelRotation != 0) {
			// ctrl + shift + mouse wheel = change level
			int level = minusedlevel - mouseWheelRotation;
			if (level < 0) {
				level = 0;
			} else if (level > 3) {
				level = 3;
			}

			tele(localPlayer.routeX[0] + mapBuildBaseX, localPlayer.routeZ[0] + mapBuildBaseZ, level);
		}

		if (staffmodlevel > 0 && ClientKeyboardListener.keyHeld[82] && ClientKeyboardListener.keyHeld[81]) {
			// ctrl + shift + mouse click = teleport
			if (World.groundX != -1) {
				System.out.println(mapBuildBaseX + World.groundX);
				System.out.println(mapBuildBaseZ + World.groundZ);
				tele(mapBuildBaseX + World.groundX, mapBuildBaseZ - -World.groundZ, minusedlevel);
			}

			anInt4422 = 0;
			anInt3096 = 0;
		} else if (anInt3096 == 2) {
			if (World.groundX != -1) {
				out.p1Enc(131);
				out.method2185(Static98.anInt2512);
				out.method2209(mapBuildBaseX + World.groundX);
				out.method2191(Static15.anInt506);
				out.method2209(World.groundZ + mapBuildBaseZ);
				crossMode = 1;
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				crossX = ClientMouseListener.mouseClickX;
			}

			anInt3096 = 0;
		} else if (anInt4422 == 2) {
			if (World.groundX != -1) {
				out.p1Enc(179);
				out.p2(mapBuildBaseZ + World.groundZ);
				out.p2(World.groundX + mapBuildBaseX);
				crossCycle = 0;
				crossMode = 1;
				crossX = ClientMouseListener.mouseClickX;
				crossY = ClientMouseListener.mouseClickY;
			}

			anInt4422 = 0;
		} else if (World.groundX != -1 && anInt3096 == 0 && anInt4422 == 0) {
			@Pc(1871) boolean local1871 = tryMove(localPlayer.routeZ[0], 0, 0, true, 0, World.groundX, 0, 0, 0, World.groundZ, localPlayer.routeX[0]);
			if (local1871) {
				crossY = ClientMouseListener.mouseClickY;
				crossCycle = 0;
				crossX = ClientMouseListener.mouseClickX;
				crossMode = 1;
			}
		}
		World.groundX = -1;

		mouseLoop();

		if (hoveredCom != hovered) {
			if (hovered != null) {
				componentUpdated(hovered);
			}
			if (hoveredCom != null) {
				componentUpdated(hoveredCom);
			}
		}

		if (tooltip != tooltipCom && tooltipRedraw == tooltipNum) {
			if (tooltip != null) {
				componentUpdated(tooltip);
			}
			if (tooltipCom != null) {
				componentUpdated(tooltipCom);
			}
		}

		if (tooltipCom == null) {
			if (tooltipNum > 0) {
				tooltipNum--;
			}
		} else if (tooltipNum < tooltipRedraw) {
			tooltipNum++;
			if (tooltipRedraw == tooltipNum) {
				componentUpdated(tooltipCom);
			}
		}

		if (anInt5096 == 1) {
			method4273();
		} else if (anInt5096 == 2) {
			method2450();
		} else {
			method1008();
		}

		for (int i = 0; i < 5; i++) {
			camShakeCycle[i]++;
		}

		int mouseIdle = ClientMouseListener.getIdleTimer();
		int keyIdle = ClientKeyboardListener.getIdleTimer();
		if (mouseIdle > 15000 && keyIdle > 15000) {
			logoutTimer = 250;
			ClientMouseListener.setIdleTimer(14500);

			// IDLE_TIMER
			out.p1Enc(245);
		}

		if (Static33.aClass212_1 != null && Static33.aClass212_1.status == 1) {
			if (Static33.aClass212_1.result != null) {
				GameShell.method3175(Static175.aClass100_797, Static164.aBoolean194);
			}

			Static175.aClass100_797 = null;
			Static33.aClass212_1 = null;
			Static164.aBoolean194 = false;
		}

		noTimeoutCycle++;

		macroCameraCycle++;
		if (macroCameraCycle > 500) {
			macroCameraCycle = 0;

			local782 = (int) (Math.random() * 8.0D);
			if ((local782 & 0x4) == 4) {
				macroCameraX += macroCameraXModifier;
			}
			if ((local782 & 0x2) == 2) {
				macroCameraZ += macroCameraZModifier;
			}
			if ((local782 & 0x1) == 1) {
				macroCameraAngle += macroCameraAngleModifier;
			}
		}

		if (macroCameraX < -40) {
			macroCameraXModifier = 1;
		}
		if (macroCameraX > 40) {
			macroCameraXModifier = -1;
		}

		if (macroCameraZ < -55) {
			macroCameraZModifier = 2;
		}
		if (macroCameraZ > 55) {
			macroCameraZModifier = -2;
		}

		if (macroCameraAngle < -50) {
			macroCameraAngleModifier = 2;
		}
		if (macroCameraAngle > 50) {
			macroCameraAngleModifier = -2;
		}

		macroMinimapCycle++;
		if (macroMinimapCycle > 500) {
			macroMinimapCycle = 0;

			local782 = (int) (Math.random() * 8.0D);
			if ((local782 & 0x1) == 1) {
				macroMinimapAngle += macroMinimapAngleModifier;
			}
			if ((local782 & 0x2) == 2) {
				macroMinimapZoom += macroMinimapZoomModifier;
			}
		}

		if (macroMinimapAngle < -60) {
			macroMinimapAngleModifier = 2;
		}
		if (macroMinimapAngle > 60) {
			macroMinimapAngleModifier = -2;
		}

		if (macroMinimapZoom < -20) {
			macroMinimapZoomModifier = 1;
		}
		if (macroMinimapZoom > 10) {
			macroMinimapZoomModifier = -1;
		}

		if (noTimeoutCycle > 50) {
			// NO_TIMEOUT
			out.p1Enc(93);
		}

		if (aBoolean65) {
			method1445();
			aBoolean65 = false;
		}

		try {
			if (stream != null && out.pos > 0) {
				stream.write(out.data, out.pos);
				noTimeoutCycle = 0;
				out.pos = 0;
			}
		} catch (@Pc(2266) IOException ex) {
			lostCon();
		}
	}

	@OriginalMember(owner = "client!fb", name = "a", descriptor = "(JB)V")
	public static void method1496(@OriginalArg(0) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		if (Static9.anInt178 >= 100 && !aBoolean233 || Static9.anInt178 >= 200) {
			addChat(TitleScreen.AUTO_EMPTY, 0, Text.aClass100_995);
			return;
		}
		@Pc(35) JagString local35 = Static79.toBaseDisplayName(arg0).method3125();
		@Pc(42) int local42;
		for (local42 = 0; local42 < Static9.anInt178; local42++) {
			if (aLongArray3[local42] == arg0) {
				addChat(TitleScreen.AUTO_EMPTY, 0, JagString.join(new JagString[] { local35, Text.aClass100_691 }));
				return;
			}
		}
		for (local42 = 0; local42 < Static35.anInt1093; local42++) {
			if (arg0 == Static190.aLongArray6[local42]) {
				addChat(TitleScreen.AUTO_EMPTY, 0, JagString.join(new JagString[] { Text.aClass100_417, local35, Text.aClass100_227 }));
				return;
			}
		}
		if (local35.equalsInner(localPlayer.name)) {
			addChat(TitleScreen.AUTO_EMPTY, 0, Text.aClass100_573);
			return;
		}
		aClass100Array92[Static9.anInt178] = local35;
		aLongArray3[Static9.anInt178] = arg0;
		Static104.anIntArray255[Static9.anInt178] = 0;
		Static214.aClass100Array170[Static9.anInt178] = TitleScreen.AUTO_EMPTY;
		Static106.anIntArray258[Static9.anInt178] = 0;
		Static3.aBooleanArray135[Static9.anInt178] = false;
		Static9.anInt178++;
		friendTransmitNum = transmitNum;
		out.p1Enc(120);
		out.p8(arg0);
	}

	@OriginalMember(owner = "client!ri", name = "a", descriptor = "(B)V")
	public static void loginPoll() {
		if (loginStep == 0 || loginStep == 5) {
			return;
		}

		try {
			if (++loginWaitingTime > 2000) {
				if (stream != null) {
					stream.close();
					stream = null;
				}

				if (loginFailCount >= 1) {
					worldHopError = -5;
					loginStep = 0;
					return;
				}

				loginWaitingTime = 0;

				if (loginPort == gamePort) {
					loginPort = js5Port;
				} else {
					loginPort = gamePort;
				}

				loginStep = 1;
				loginFailCount++;
			}

			if (loginStep == 1) {
				socketReq = signlink.socketreq(host, loginPort);
				loginStep = 2;
			}

			if (loginStep == 2) {
				if (socketReq.status == 2) {
					throw new IOException();
				}

				if (socketReq.status != 1) {
					return;
				}

				stream = new ClientStream((Socket) socketReq.result, signlink);
				socketReq = null;

				@Pc(106) long local106 = Static101.aLong98 = TitleScreen.loginUser.method3158();
				out.pos = 0;
				out.p1(14);
				@Pc(120) int local120 = (int) (local106 >> 16 & 0x1FL);
				out.p1(local120);
				stream.write(out.data, 2);

				if (midiPcmPlayer != null) {
					midiPcmPlayer.skipNextAcceptedCheck();
				}
				if (soundPcmPlayer != null) {
					soundPcmPlayer.skipNextAcceptedCheck();
				}

				@Pc(150) int local150 = stream.read();

				if (midiPcmPlayer != null) {
					midiPcmPlayer.skipNextAcceptedCheck();
				}
				if (soundPcmPlayer != null) {
					soundPcmPlayer.skipNextAcceptedCheck();
				}

				if (local150 != 0) {
					worldHopError = local150;
					loginStep = 0;
					stream.close();
					stream = null;
					return;
				}

				loginStep = 3;
			}

			if (loginStep == 3) {
				if (stream.available() < 8) {
					return;
				}

				stream.read(0, 8, in.data);
				in.pos = 0;
				serverSeed = in.g8();

				@Pc(210) int[] seed = new int[4];
				seed[2] = (int) (serverSeed >> 32);
				seed[3] = (int) serverSeed;
				seed[1] = (int) (Math.random() * 9.9999999E7D);
				seed[0] = (int) (Math.random() * 9.9999999E7D);

				out.pos = 0;
				out.p1(10);
				out.p4(seed[0]);
				out.p4(seed[1]);
				out.p4(seed[2]);
				out.p4(seed[3]);
				out.p8(TitleScreen.loginUser.method3158());
				out.pjstr(TitleScreen.loginPass);
				if (GlobalConfig.ARIOS_LOGIN_EXTRA) {
					out.pjstr(JagString.wrap(""));
					out.pjstr(JagString.wrap(""));
					out.pjstr(JagString.wrap(""));
				}
				out.rsaenc(Static86.aBigInteger1, Static256.aBigInteger2);

				login.pos = 0;
				if (state == 40) {
					login.p1(18);
				} else {
					login.p1(16);
				}

				int offset = 0;
				if (GlobalConfig.ARIOS_LOGIN_IDX28) {
					// pretend that we're loading the archive so we don't throw the packet size off
					offset = 4;
				}
				login.p2(out.pos + Static229.method3937(Static47.aClass100_991) + (159 + offset));
				login.p4(530);
				login.p1(Static5.anInt39);
				login.p1(advertsuppressed ? 1 : 0);
				login.p1(1);
				login.p1(Static144.method2736());
				login.p2(anInt1448);
				login.p2(anInt5554);
				login.p1(Static186.anInt4392);
				Static140.method2705(login);
				login.pjstr(Static47.aClass100_991);
				login.p4(affid);
				login.p4(Static145.method2746());
				Static18.aBoolean39 = true;
				login.p2(Static189.anInt4443);
				login.p4(anims.getCrc());
				login.p4(bases.getCrc());
				login.p4(config.getCrc());
				login.p4(interfaces.getCrc());
				login.p4(jagFX.getCrc());
				login.p4(maps.getCrc());
				login.p4(songs.getCrc());
				login.p4(models.getCrc());
				login.p4(sprites.getCrc());
				login.p4(textures.getCrc());
				login.p4(binary.getCrc());
				login.p4(jingles.getCrc());
				login.p4(scripts.getCrc());
				login.p4(fontMetrics.getCrc());
				login.p4(vorbis.getCrc());
				login.p4(patches.getCrc());
				login.p4(locConfig.getCrc());
				login.p4(enumConfig.getCrc());
				login.p4(npcConfig.getCrc());
				login.p4(objConfig.getCrc());
				login.p4(seqConfig.getCrc());
				login.p4(spotConfig.getCrc());
				login.p4(varbitConfig.getCrc());
				login.p4(worldmap.getCrc());
				login.p4(quickchat.getCrc());
				login.p4(quickchatGlobal.getCrc());
				login.p4(materials.getCrc());
				login.p4(particleConfig.getCrc());
				if (GlobalConfig.ARIOS_LOGIN_IDX28) {
					login.p4(0);
				}
				login.tinyenc(out.data, out.pos);

				stream.write(login.data, login.pos);

				out.seed(seed);
				for (@Pc(583) int i = 0; i < 4; i++) {
					seed[i] += 50;
				}
				in.seed(seed);
				loginStep = 4;
			}

			if (loginStep == 4) {
				if (stream.available() < 1) {
					return;
				}

				@Pc(623) int local623 = stream.read();
				if (local623 == 21) {
					loginStep = 7;
				} else if (local623 == 29) {
					loginStep = 10;
				} else if (local623 == 1) {
					loginStep = 5;
					worldHopError = local623;
					return;
				} else if (local623 == 2) {
					loginStep = 8;
				} else if (local623 == 15) {
					loginStep = 0;
					worldHopError = local623;
					return;
				} else if (local623 == 23 && loginFailCount < 1) {
					loginStep = 1;
					loginFailCount++;
					loginWaitingTime = 0;
					stream.close();
					stream = null;
					return;
				} else {
					worldHopError = local623;
					loginStep = 0;
					stream.close();
					stream = null;
					return;
				}
			}

			if (loginStep == 6) {
				out.pos = 0;
				out.p1Enc(17);
				stream.write(out.data, out.pos);
				loginStep = 4;
				return;
			}

			if (loginStep == 7) {
				if (stream.available() >= 1) {
					Static231.anInt5202 = (stream.read() + 3) * 60;
					loginStep = 0;
					worldHopError = 21;
					stream.close();
					stream = null;
					return;
				}
				return;
			}

			if (loginStep == 10) {
				if (stream.available() >= 1) {
					Static204.anInt4765 = stream.read();
					loginStep = 0;
					worldHopError = 29;
					stream.close();
					stream = null;
					return;
				}
				return;
			}

			if (loginStep == 8) {
				if (stream.available() < 14) {
					return;
				}

				stream.read(0, 14, in.data);
				in.pos = 0;
				staffmodlevel = in.g1();
				anInt5431 = in.g1();
				aBoolean157 = in.g1() == 1;
				aBoolean236 = in.g1() == 1;
				aBoolean57 = in.g1() == 1;
				aBoolean129 = in.g1() == 1;
				mouseTracked = in.g1() == 1;
				anInt549 = in.g2();
				aBoolean233 = in.g1() == 1;
				memServer = in.g1() == 1;

				LocType.method3438(memServer);
				ObjType.method186(memServer);

				if (!advertsuppressed) {
					if (aBoolean157 && !aBoolean57 || aBoolean233) {
						try {
							Static167.aClass100_781.method3157(signlink.applet);
						} catch (@Pc(910) Throwable ignore) {
						}
					} else {
						try {
							Static56.aClass100_380.method3157(signlink.applet);
						} catch (@Pc(920) Throwable ignore) {
						}
					}
				}

				ptype = in.g1Enc();
				psize = in.g2();
				loginStep = 9;
			}

			if (loginStep == 9) {
				if (stream.available() < psize) {
					return;
				}
				in.pos = 0;
				stream.read(0, psize, in.data);
				worldHopError = 2;
				loginStep = 0;
				method4221();
				mapBuildCenterZoneZ = -1;
				Static75.method1629(false);
				ptype = -1;
				return;
			}
		} catch (@Pc(977) IOException ex) {
			if (stream != null) {
				stream.close();
				stream = null;
			}

			if (loginFailCount >= 1) {
				loginStep = 0;
				worldHopError = -4;
			} else {
				loginStep = 1;
				loginWaitingTime = 0;
				loginFailCount++;

				if (gamePort == loginPort) {
					loginPort = js5Port;
				} else {
					loginPort = gamePort;
				}
			}
		}
	}

	@OriginalMember(owner = "client!rm", name = "a", descriptor = "(IBI)V")
	public static void showObject(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(9) LinkList local9 = groundObj[minusedlevel][arg1][arg0];
		if (local9 == null) {
			World.delObj(minusedlevel, arg1, arg0);
			return;
		}
		@Pc(28) int local28 = -99999999;
		@Pc(30) ClientObjNode local30 = null;
		@Pc(35) ClientObjNode local35;
		for (local35 = (ClientObjNode) local9.head(); local35 != null; local35 = (ClientObjNode) local9.next()) {
			@Pc(44) ObjType local44 = ObjType.list(local35.aClass8_Sub7_1.id);
			@Pc(47) int local47 = local44.anInt2325;
			if (local44.anInt2336 == 1) {
				local47 *= local35.aClass8_Sub7_1.anInt5550 + 1;
			}
			if (local28 < local47) {
				local28 = local47;
				local30 = local35;
			}
		}
		if (local30 == null) {
			World.delObj(minusedlevel, arg1, arg0);
			return;
		}
		local9.pushFront(local30);
		@Pc(89) ClientObj local89 = null;
		@Pc(91) ClientObj local91 = null;
		for (local35 = (ClientObjNode) local9.head(); local35 != null; local35 = (ClientObjNode) local9.next()) {
			@Pc(103) ClientObj local103 = local35.aClass8_Sub7_1;
			if (local103.id != local30.aClass8_Sub7_1.id) {
				if (local89 == null) {
					local89 = local103;
				}
				if (local103.id != local89.id && local91 == null) {
					local91 = local103;
				}
			}
		}
		@Pc(152) long local152 = (long) ((arg0 << 7) + arg1 + 1610612736);
		World.setObj(minusedlevel, arg1, arg0, getAvH(minusedlevel, arg1 * 128 + 64, arg0 * 128 + 64), local30.aClass8_Sub7_1, local152, local89, local91);
	}

	@OriginalMember(owner = "client!ug", name = "a", descriptor = "(B)V")
	public static void locChangeDoQueue() {
		for (@Pc(10) LocChange local10 = (LocChange) aClass69_27.head(); local10 != null; local10 = (LocChange) aClass69_27.next()) {
			if (local10.endTime > 0) {
				local10.endTime--;
			}
			if (local10.endTime != 0) {
				if (local10.startTime > 0) {
					local10.startTime--;
				}
				if (local10.startTime == 0 && local10.anInt928 >= 1 && local10.anInt916 >= 1 && local10.anInt928 <= 102 && local10.anInt916 <= 102 && (local10.oldType < 0 || ClientBuild.changeLocAvailable(local10.oldType, local10.anInt926))) {
					locChangeUnchecked(local10.oldType, local10.anInt928, local10.anInt918, local10.anInt922, local10.anInt916, local10.anInt926, local10.anInt927);
					local10.startTime = -1;
					if (local10.newType == local10.oldType && local10.newType == -1) {
						local10.unlink();
					} else if (local10.oldType == local10.newType && local10.anInt922 == local10.anInt923 && local10.anInt920 == local10.anInt926) {
						local10.unlink();
					}
				}
			} else if (local10.newType < 0 || ClientBuild.changeLocAvailable(local10.newType, local10.anInt920)) {
				locChangeUnchecked(local10.newType, local10.anInt928, local10.anInt918, local10.anInt923, local10.anInt916, local10.anInt920, local10.anInt927);
				local10.unlink();
			}
		}
	}

	@OriginalMember(owner = "client!di", name = "a", descriptor = "(IIIIIIIIZIII)Z")
	public static boolean method1171(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) boolean arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
		@Pc(3) int local3;
		@Pc(10) int local10;
		for (local3 = 0; local3 < 104; local3++) {
			for (local10 = 0; local10 < 104; local10++) {
				anIntArrayArray25[local3][local10] = 0;
				anIntArrayArray31[local3][local10] = 99999999;
			}
		}
		local3 = arg2;
		anIntArrayArray25[arg2][arg9] = 99;
		local10 = arg9;
		anIntArrayArray31[arg2][arg9] = 0;
		@Pc(51) byte local51 = 0;
		@Pc(53) boolean local53 = false;
		@Pc(64) int local64 = 0;
		anIntArray514[0] = arg2;
		@Pc(71) int local71 = local51 + 1;
		anIntArray209[0] = arg9;
		@Pc(78) int[][] local78 = levelCollisionMap[minusedlevel].flags;
		@Pc(198) int local198;
		while (local71 != local64) {
			local10 = anIntArray209[local64];
			local3 = anIntArray514[local64];
			local64 = local64 + 1 & 0xFFF;
			if (local3 == arg0 && local10 == arg3) {
				local53 = true;
				break;
			}
			if (arg8 != 0) {
				if ((arg8 < 5 || arg8 == 10) && levelCollisionMap[minusedlevel].method3042(arg3, local3, local10, arg0, arg8 - 1, 1, arg6)) {
					local53 = true;
					break;
				}
				if (arg8 < 10 && levelCollisionMap[minusedlevel].method3046(arg3, arg8 - 1, arg0, local10, 1, arg6, local3)) {
					local53 = true;
					break;
				}
			}
			if (arg10 != 0 && arg5 != 0 && levelCollisionMap[minusedlevel].method3052(arg0, local10, local3, 1, arg10, arg1, arg3, arg5)) {
				local53 = true;
				break;
			}
			local198 = anIntArrayArray31[local3][local10] + 1;
			if (local3 > 0 && anIntArrayArray25[local3 - 1][local10] == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0) {
				anIntArray514[local71] = local3 - 1;
				anIntArray209[local71] = local10;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray25[local3 - 1][local10] = 2;
				anIntArrayArray31[local3 - 1][local10] = local198;
			}
			if (local3 < 103 && anIntArrayArray25[local3 + 1][local10] == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0) {
				anIntArray514[local71] = local3 + 1;
				anIntArray209[local71] = local10;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray25[local3 + 1][local10] = 8;
				anIntArrayArray31[local3 + 1][local10] = local198;
			}
			if (local10 > 0 && anIntArrayArray25[local3][local10 - 1] == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
				anIntArray514[local71] = local3;
				anIntArray209[local71] = local10 - 1;
				anIntArrayArray25[local3][local10 - 1] = 1;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray31[local3][local10 - 1] = local198;
			}
			if (local10 < 103 && anIntArrayArray25[local3][local10 + 1] == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
				anIntArray514[local71] = local3;
				anIntArray209[local71] = local10 + 1;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray25[local3][local10 + 1] = 4;
				anIntArrayArray31[local3][local10 + 1] = local198;
			}
			if (local3 > 0 && local10 > 0 && anIntArrayArray25[local3 - 1][local10 - 1] == 0 && (local78[local3 - 1][local10 - 1] & 0x12C010E) == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
				anIntArray514[local71] = local3 - 1;
				anIntArray209[local71] = local10 - 1;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray25[local3 - 1][local10 - 1] = 3;
				anIntArrayArray31[local3 - 1][local10 - 1] = local198;
			}
			if (local3 < 103 && local10 > 0 && anIntArrayArray25[local3 + 1][local10 - 1] == 0 && (local78[local3 + 1][local10 - 1] & 0x12C0183) == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
				anIntArray514[local71] = local3 + 1;
				anIntArray209[local71] = local10 - 1;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray25[local3 + 1][local10 - 1] = 9;
				anIntArrayArray31[local3 + 1][local10 - 1] = local198;
			}
			if (local3 > 0 && local10 < 103 && anIntArrayArray25[local3 - 1][local10 + 1] == 0 && (local78[local3 - 1][local10 + 1] & 0x12C0138) == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
				anIntArray514[local71] = local3 - 1;
				anIntArray209[local71] = local10 + 1;
				anIntArrayArray25[local3 - 1][local10 + 1] = 6;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray31[local3 - 1][local10 + 1] = local198;
			}
			if (local3 < 103 && local10 < 103 && anIntArrayArray25[local3 + 1][local10 + 1] == 0 && (local78[local3 + 1][local10 + 1] & 0x12C01E0) == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
				anIntArray514[local71] = local3 + 1;
				anIntArray209[local71] = local10 + 1;
				anIntArrayArray25[local3 + 1][local10 + 1] = 12;
				local71 = local71 + 1 & 0xFFF;
				anIntArrayArray31[local3 + 1][local10 + 1] = local198;
			}
		}
		Static221.anInt4364 = 0;
		@Pc(839) int local839;
		if (!local53) {
			if (!arg7) {
				return false;
			}
			local198 = 1000;
			local839 = 100;
			for (@Pc(846) int local846 = arg0 - 10; local846 <= arg0 + 10; local846++) {
				for (@Pc(856) int local856 = arg3 - 10; local856 <= arg3 + 10; local856++) {
					if (local846 >= 0 && local856 >= 0 && local846 < 104 && local856 < 104 && anIntArrayArray31[local846][local856] < 100) {
						@Pc(894) int local894 = 0;
						if (local856 < arg3) {
							local894 = arg3 - local856;
						} else if (arg5 + arg3 - 1 < local856) {
							local894 = local856 + 1 - arg3 - arg5;
						}
						@Pc(927) int local927 = 0;
						if (local846 < arg0) {
							local927 = arg0 - local846;
						} else if (local846 > arg10 + arg0 - 1) {
							local927 = local846 + 1 - arg10 - arg0;
						}
						@Pc(968) int local968 = local894 * local894 + local927 * local927;
						if (local968 < local198 || local968 == local198 && anIntArrayArray31[local846][local856] < local839) {
							local10 = local856;
							local198 = local968;
							local3 = local846;
							local839 = anIntArrayArray31[local846][local856];
						}
					}
				}
			}
			if (local198 == 1000) {
				return false;
			}
			if (arg2 == local3 && local10 == arg9) {
				return false;
			}
			Static221.anInt4364 = 1;
		}
		@Pc(1032) byte local1032 = 0;
		anIntArray514[0] = local3;
		local64 = local1032 + 1;
		anIntArray209[0] = local10;
		local198 = local839 = anIntArrayArray25[local3][local10];
		while (arg2 != local3 || local10 != arg9) {
			if (local839 != local198) {
				local839 = local198;
				anIntArray514[local64] = local3;
				anIntArray209[local64++] = local10;
			}
			if ((local198 & 0x2) != 0) {
				local3++;
			} else if ((local198 & 0x8) != 0) {
				local3--;
			}
			if ((local198 & 0x1) != 0) {
				local10++;
			} else if ((local198 & 0x4) != 0) {
				local10--;
			}
			local198 = anIntArrayArray25[local3][local10];
		}
		if (local64 > 0) {
			method3502(local64, arg4);
			return true;
		} else if (arg4 == 1) {
			return false;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!ah", name = "a", descriptor = "(BZ)V")
	public static void preventTimeout(@OriginalArg(1) boolean arg0) {
		doAudio();

		if (state != 30 && state != 25) {
			return;
		}

		noTimeoutCycle++;
		if (noTimeoutCycle < 50 && !arg0) {
			return;
		}

		noTimeoutCycle = 0;

		if (!networkError && stream != null) {
			out.p1Enc(93);

			try {
				stream.write(out.data, out.pos);
				out.pos = 0;
			} catch (@Pc(53) IOException ex) {
				networkError = true;
			}
		}

		doAudio();
	}

	@OriginalMember(owner = "client!wj", name = "b", descriptor = "(B)V")
	public static void logout() {
		if (stream != null) {
			stream.close();
			stream = null;
		}
		clearCaches();
		World.method1500();
		@Pc(19) int local19;
		for (local19 = 0; local19 < 4; local19++) {
			levelCollisionMap[local19].reset();
		}
		WorldMap.method2325(false);
		System.gc();
		MidiManager.method801();
		aBoolean173 = false;
		anInt4363 = -1;
		BgSound.reset(true);
		regionmode = false;
		mapBuildBaseZ = 0;
		mapBuildCenterZoneZ = 0;
		mapBuildCenterZoneX = 0;
		mapBuildBaseX = 0;
		for (local19 = 0; local19 < Static143.aClass102Array1.length; local19++) {
			Static143.aClass102Array1[local19] = null;
		}
		playerCount = 0;
		npcCount = 0;
		for (local19 = 0; local19 < 2048; local19++) {
			players[local19] = null;
			Static115.aClass3_Sub15Array1[local19] = null;
		}
		for (local19 = 0; local19 < 32768; local19++) {
			npcs[local19] = null;
		}
		for (local19 = 0; local19 < 4; local19++) {
			for (@Pc(115) int local115 = 0; local115 < 104; local115++) {
				for (@Pc(122) int local122 = 0; local122 < 104; local122++) {
					groundObj[local19][local115][local122] = null;
				}
			}
		}
		Static35.method902();
		Static189.anInt4443 = 0;
		Static8.method121();
		method1596(true);
	}

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(I)V")
	public static void getNpcPosNewVis() {
		while (true) {
			if (in.bitsLeft(psize) >= 27) {
				@Pc(14) int local14 = in.method2238(15);
				if (local14 != 32767) {
					@Pc(19) boolean local19 = false;
					if (npcs[local14] == null) {
						local19 = true;
						npcs[local14] = new ClientNPC();
					}
					@Pc(37) ClientNPC local37 = npcs[local14];
					npcIds[npcCount++] = local14;
					local37.cycle = loopCycle;
					if (local37.aClass96_1 != null && local37.aClass96_1.method2935()) {
						BgSound.method1877(local37);
					}
					@Pc(66) int local66 = in.method2238(1);
					@Pc(73) int local73 = Static56.anIntArray141[in.method2238(3)];
					if (local19) {
						local37.anInt3400 = local37.anInt3381 = local73;
					}
					@Pc(86) int local86 = in.method2238(1);
					if (local86 == 1) {
						entityUpdateIds[entityUpdateCount++] = local14;
					}
					@Pc(105) int local105 = in.method2238(5);
					local37.method2698(NPCType.list(in.method2238(14)));
					if (local105 > 15) {
						local105 -= 32;
					}
					@Pc(124) int local124 = in.method2238(5);
					if (local124 > 15) {
						local124 -= 32;
					}
					local37.method2692(local37.aClass96_1.anInt3713);
					local37.anInt3365 = local37.aClass96_1.anInt3737;
					local37.anInt3376 = local37.aClass96_1.anInt3733;
					if (local37.anInt3376 == 0) {
						local37.anInt3381 = 0;
					}
					local37.method2683(local37.method2693(), localPlayer.routeX[0] + local124, local105 + localPlayer.routeZ[0], local66 == 1);
					if (local37.aClass96_1.method2935()) {
						BgSound.method2411(local37.routeZ[0], null, 0, local37, local37.routeX[0], minusedlevel, null);
					}
					continue;
				}
			}
			in.gBitEnd();
			return;
		}
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(III[Lclient!be;IIIIBI)V")
	public static void drawLayer(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) IfType[] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(9) int arg8) {
		if (glRenderer) {
			Static46.method1187(arg0, arg6, arg4, arg7);
		} else {
			Pix2D.method2496(arg0, arg6, arg4, arg7);
			Pix3D.method1908();
		}

		for (@Pc(18) int local18 = 0; local18 < arg3.length; local18++) {
			@Pc(30) IfType com = arg3[local18];
            if (com == null || (com.layerId != arg5 && (arg5 != 0xabcdabcd || com != dragComponent))) {
                continue;
            }

            @Pc(57) int drawCount;
            if (arg8 == -1) {
				componentDrawX[componentDrawCount] = arg2 + com.anInt523;
				componentDrawY[componentDrawCount] = com.anInt469 + arg1;
				componentDrawWidth[componentDrawCount] = com.anInt445;
				componentDrawHeight[componentDrawCount] = com.anInt459;
                drawCount = componentDrawCount++;
            } else {
                drawCount = arg8;
            }

            com.drawTime = loopCycle;
            com.drawCount = drawCount;

            if (com.v3 && hide(com)) {
                continue;
            }

            if (com.clientCode > 0) {
                clientComponent(com);
            }

            @Pc(114) int local114 = arg1 + com.anInt469;
            @Pc(123) int local123 = com.anInt523 + arg2;

			@Pc(117) int local117 = com.trans;
            if (aBoolean154 && (getActive(com).eventCode != 0 || com.type == 0) && local117 > 127) {
                local117 = 127;
            }

            @Pc(166) int local166;
            @Pc(164) int local164;
            if (com == dragComponent) {
                if (arg5 != 0xabcdabcd && !com.draggablebehavior) {
                    anInt4696 = arg2;
                    anInt3126 = arg1;
                    aClass13Array13 = arg3;
                    continue;
                }
                if (aBoolean172 && aBoolean174) {
                    local164 = ClientMouseListener.mouseY;
                    local166 = ClientMouseListener.mouseX;

                    local164 -= anInt4035;
                    if (local164 < anInt5103) {
                        local164 = anInt5103;
                    }

                    if (local164 + com.anInt459 > aClass13_1.anInt459 + anInt5103) {
                        local164 = aClass13_1.anInt459 + anInt5103 - com.anInt459;
                    }

                    local114 = local164;
                    local166 -= Static246.anInt5388;
                    if (anInt2225 > local166) {
                        local166 = anInt2225;
                    }
                    if (aClass13_1.anInt445 + anInt2225 < com.anInt445 + local166) {
                        local166 = aClass13_1.anInt445 + anInt2225 - com.anInt445;
                    }
                    local123 = local166;
                }

                if (!com.draggablebehavior) {
                    local117 = 128;
                }
            }

            @Pc(302) int local302;
            @Pc(291) int local291;
            @Pc(270) int local270;
            @Pc(276) int local276;
            if (com.type == 2) {
                local291 = arg7;
                local302 = arg4;
                local164 = arg6;
                local166 = arg0;
            } else {
                local164 = local114 > arg6 ? local114 : arg6;
                local166 = arg0 < local123 ? local123 : arg0;
                local270 = com.anInt445 + local123;
                local276 = local114 + com.anInt459;
                if (com.type == 9) {
                    local276++;
                    local270++;
                }
                local291 = arg7 <= local276 ? arg7 : local276;
                local302 = local270 >= arg4 ? arg4 : local270;
            }

            if (com.v3 && (local302 <= local166 || local164 >= local291)) {
                continue;
            }

            @Pc(468) int local468;
            @Pc(503) int local503;
            @Pc(514) int local514;
            @Pc(518) int local518;
            @Pc(556) int local556;
            @Pc(563) int local563;
            @Pc(571) int local571;
            @Pc(545) int local545;

            if (com.clientCode != 0) {
                if (com.clientCode == 1337 || com.clientCode == 1403 && glRenderer) {
                    aClass13_26 = com;
                    minimenuMouseOverY = local114;
                    minimenuMouseOverX = local123;

                    gameDrawMain(com.anInt459, com.clientCode == 1403, local123, com.anInt445, local114);

                    if (glRenderer) {
                        Static46.method1187(arg0, arg6, arg4, arg7);
                    } else {
                        Pix2D.method2496(arg0, arg6, arg4, arg7);
                    }
                    continue;
                }

                if (com.clientCode == 1338) {
                    if (!com.method478()) {
                        continue;
                    }

                    method1305(drawCount, local114, local123, com);
                    if (glRenderer) {
                        Static46.method1187(arg0, arg6, arg4, arg7);
                    } else {
                        Pix2D.method2496(arg0, arg6, arg4, arg7);
                    }
                    if (anInt5795 != 0 && anInt5795 != 3 || isMenuOpen || local166 > anInt3751 || anInt1892 < local164 || anInt3751 >= local302 || local291 <= anInt1892) {
                        continue;
                    }
                    local270 = anInt3751 - local123;
                    local276 = anInt1892 - local114;
                    local468 = com.anIntArray37[local276];
                    if (local270 < local468 || local270 > local468 + com.anIntArray45[local276]) {
                        continue;
                    }
                    local276 -= com.anInt459 / 2;
                    local503 = anInt1747 + macroMinimapAngle & 0x7FF;
                    local270 -= com.anInt445 / 2;
                    local514 = Pix3D.sinTable[local503];
                    local518 = Pix3D.cosTable[local503];
                    local514 = (macroMinimapZoom + 256) * local514 >> 8;
                    local518 = (macroMinimapZoom + 256) * local518 >> 8;
                    local545 = local518 * local276 - local514 * local270 >> 11;
                    local556 = local276 * local514 + local270 * local518 >> 11;
                    local563 = localPlayer.x + local556 >> 7;
                    local571 = localPlayer.z - local545 >> 7;
                    if (targetMode && (Static274.anInt4999 & 0x40) != 0) {
                        @Pc(583) IfType local583 = IfType.method1418(Static98.anInt2512, Static15.anInt506);
                        if (local583 == null) {
                            method1294();
                        } else {
                            addMenuOption(Static246.anInt5393, 1L, Static225.aClass100_961, local563, (short) 11, Static102.aClass100_545, local571);
                        }
                        continue;
                    }
                    if (game == 1) {
                        addMenuOption(-1, 1L, TitleScreen.AUTO_EMPTY, local563, (short) 36, Text.aClass100_957, local571);
                    }
                    addMenuOption(-1, 1L, TitleScreen.AUTO_EMPTY, local563, (short) 60, Static195.aClass100_859, local571);
                    continue;
                }

                if (com.clientCode == 1339) {
                    if (com.method478()) {
                        method3047(local123, local114, com, drawCount);
                        if (glRenderer) {
                            Static46.method1187(arg0, arg6, arg4, arg7);
                        } else {
                            Pix2D.method2496(arg0, arg6, arg4, arg7);
                        }
                    }
                    continue;
                }

                if (com.clientCode == 1400) {
                    WorldMap.method2225(local123, local114, com.anInt459, com.anInt445);
                    componentRedrawRequested1[drawCount] = true;
                    componentRedrawRequested2[drawCount] = true;
                    if (glRenderer) {
                        Static46.method1187(arg0, arg6, arg4, arg7);
                    } else {
                        Pix2D.method2496(arg0, arg6, arg4, arg7);
                    }
                    continue;
                }

                if (com.clientCode == 1401) {
                    Static1.method4(local123, com.anInt459, com.anInt445, local114);
                    componentRedrawRequested1[drawCount] = true;
                    componentRedrawRequested2[drawCount] = true;
                    if (glRenderer) {
                        Static46.method1187(arg0, arg6, arg4, arg7);
                    } else {
                        Pix2D.method2496(arg0, arg6, arg4, arg7);
                    }
                    continue;
                }

                if (com.clientCode == 1402) {
                    if (!glRenderer) {
                        Static221.method3392(local123, local114);
                        componentRedrawRequested1[drawCount] = true;
                        componentRedrawRequested2[drawCount] = true;
                    }
                    continue;
                }

                if (com.clientCode == 1405) {
                    if (!Static43.aBoolean82) {
                        continue;
                    }
                    local270 = com.anInt445 + local123;
                    local276 = local114 + 15;
                    Static215.aClass3_Sub2_Sub9_32.method2864(JagString.join(new JagString[] { Static101.aClass100_539, JagString.parseInt(anInt5359) }), local270, local276, 16776960, -1);
                    local276 += 15;
                    @Pc(795) Runtime local795 = Runtime.getRuntime();
                    local503 = (int) ((local795.totalMemory() - local795.freeMemory()) / 1024L);
                    local514 = 16776960;
                    if (local503 > 65536) {
                        local514 = 16711680;
                    }
                    Static215.aClass3_Sub2_Sub9_32.method2864(JagString.join(new JagString[] { Static25.aClass100_154, JagString.parseInt(local503), Static17.aClass100_101 }), local270, local276, local514, -1);
                    local276 += 15;
                    if (glRenderer) {
                        local514 = 16776960;
                        local518 = (Static63.anInt1942 + Static63.anInt1945 + Static63.anInt1944) / 1024;
                        if (local518 > 65536) {
                            local514 = 16711680;
                        }
                        Static215.aClass3_Sub2_Sub9_32.method2864(JagString.join(new JagString[] { Static198.aClass100_264, JagString.parseInt(local518), Static17.aClass100_101 }), local270, local276, local514, -1);
                        local276 += 15;
                    }
                    local518 = 0;
                    local545 = 0;
                    local556 = 0;
                    for (local563 = 0; local563 < 28; local563++) {
                        local518 += js5Providers[local563].method535();
                        local556 += js5Providers[local563].method529();
                        local545 += js5Providers[local563].method533();
                    }
                    local571 = local556 * 10000 / local518;
                    local563 = local545 * 100 / local518;
                    @Pc(968) JagString local968 = JagString.join(new JagString[] { Static43.aClass100_334, Static182.method3360(0, true, 2, (long) local571), Static147.aClass100_672, JagString.parseInt(local563), Static14.aClass100_80 });
                    Static114.aClass3_Sub2_Sub9_42.method2864(local968, local270, local276, 16776960, -1);
                    local276 += 12;
                    componentRedrawRequested1[drawCount] = true;
                    componentRedrawRequested2[drawCount] = true;
                    continue;
                }

                if (com.clientCode == 1406) {
                    Static143.anInt3484 = local114;
                    Static201.aClass13_13 = com;
                    Static131.anInt3260 = local123;
                    continue;
                }
            }

            if (!isMenuOpen) {
                if (com.type == 0 && com.aBoolean29 && anInt3751 >= local166 && anInt1892 >= local164 && anInt3751 < local302 && local291 > anInt1892 && !aBoolean154) {
                    menuNumEntries = 1;
                    anIntArray382[0] = Static35.anInt1092;
                    aClass100Array168[0] = Text.aClass100_1091;
                    aClass100Array160[0] = TitleScreen.AUTO_EMPTY;
                    menuAction[0] = 1005;
                }
                if (local166 <= anInt3751 && local164 <= anInt1892 && local302 > anInt3751 && local291 > anInt1892) {
                    addComponentOptions(anInt1892 - local114, -local123 + anInt3751, com);
                }
            }

            if (com.type == 0) {
                if (!com.v3 && hide(com) && hoveredCom != com) {
                    continue;
                }
                if (!com.v3) {
                    if (com.scrollHeight - com.anInt459 < com.anInt468) {
                        com.anInt468 = com.scrollHeight - com.anInt459;
                    }
                    if (com.anInt468 < 0) {
                        com.anInt468 = 0;
                    }
                }

                drawLayer(local166, local114 - com.anInt468, -com.anInt489 + local123, arg3, local302, com.parentId, local164, local291, drawCount);

                if (com.subcomponents != null) {
                    drawLayer(local166, local114 - com.anInt468, -com.anInt489 + local123, com.subcomponents, local302, com.parentId, local164, local291, drawCount);
                }

                @Pc(1186) SubInterface local1186 = (SubInterface) aClass133_9.find((long) com.parentId);
                if (local1186 != null) {
                    if (local1186.anInt5879 == 0 && !isMenuOpen && anInt3751 >= local166 && local164 <= anInt1892 && local302 > anInt3751 && anInt1892 < local291 && !aBoolean154) {
                        aClass100Array168[0] = Text.aClass100_1091;
                        menuNumEntries = 1;
                        anIntArray382[0] = Static35.anInt1092;
                        menuAction[0] = 1005;
                        aClass100Array160[0] = TitleScreen.AUTO_EMPTY;
                    }

                    method86(local1186.id, local166, local302, local123, drawCount, local291, local164, local114);
                }

                if (glRenderer) {
                    Static46.method1187(arg0, arg6, arg4, arg7);
                } else {
                    Pix2D.method2496(arg0, arg6, arg4, arg7);
                    Pix3D.method1908();
                }
            }

            if (!Static223.aBooleanArray116[drawCount] && Static199.anInt4672 <= 1) {
                continue;
            }

			@Pc(1921) PixFontGeneric local1921;
			@Pc(2611) ObjType local2611;

            if (com.type == 0 && !com.v3 && com.scrollHeight > com.anInt459) {
                drawScrollbar(com.anInt468, com.scrollHeight, com.anInt445 + local123, local114, com.anInt459);
            } else if (com.type == 1) {
				// empty
            } else if (com.type == 2) {
                local270 = 0;
                for (local276 = 0; local276 < com.height; local276++) {
                    for (local468 = 0; local468 < com.width; local468++) {
                        local514 = local114 + local276 * (com.marginY + 32);
                        local503 = (com.marginX + 32) * local468 + local123;
                        if (local270 < 20) {
                            local514 += com.invBackgroundY[local270];
                            local503 += com.invBackgroundX[local270];
                        }
                        if (com.linkObjNumber[local270] > 0) {
                            local545 = com.linkObjNumber[local270] - 1;
                            if (arg0 < local503 + 32 && local503 < arg4 && arg6 < local514 + 32 && local514 < arg7 || com == objDragInterface && anInt36 == local270) {
                                @Pc(1476) AbstractPix32 local1476;
                                if (Static260.anInt5014 == 1 && Static185.anInt4370 == local270 && com.parentId == Static224.anInt5062) {
                                    local1476 = Static190.method3443(2, local545, com.aBoolean31, com.linkObjType[local270], 0);
                                } else {
                                    local1476 = Static190.method3443(1, local545, com.aBoolean31, com.linkObjType[local270], 3153952);
                                }
                                if (Pix3D.aBoolean134) {
                                    componentRedrawRequested1[drawCount] = true;
                                }
                                if (local1476 == null) {
                                    componentUpdated(com);
                                } else if (objDragInterface == com && local270 == anInt36) {
                                    local518 = ClientMouseListener.mouseX - objGrabX;
                                    local556 = ClientMouseListener.mouseY - objGrabY;
                                    if (local556 < 5 && local556 > -5) {
                                        local556 = 0;
                                    }
                                    if (local518 < 5 && local518 > -5) {
                                        local518 = 0;
                                    }
                                    if (objDragCycles < 5) {
                                        local518 = 0;
                                        local556 = 0;
                                    }
                                    local1476.method1417(local503 + local518, local514 - -local556, 128);
                                    if (arg5 != -1) {
                                        @Pc(1571) IfType local1571 = arg3[arg5 & 0xFFFF];
                                        @Pc(1577) int local1577;
                                        @Pc(1575) int local1575;
                                        if (glRenderer) {
                                            local1575 = Static46.anInt1441;
                                            local1577 = Static46.anInt1438;
                                        } else {
                                            local1577 = Pix2D.anInt3147;
                                            local1575 = Pix2D.anInt3149;
                                        }
                                        @Pc(1611) int local1611;
                                        if (local1577 > local556 + local514 && local1571.anInt468 > 0) {
                                            local1611 = anInt4247 * (local1577 - local556 - local514) / 3;
                                            if (local1611 > anInt4247 * 10) {
                                                local1611 = anInt4247 * 10;
                                            }
                                            if (local1611 > local1571.anInt468) {
                                                local1611 = local1571.anInt468;
                                            }
                                            local1571.anInt468 -= local1611;
                                            objGrabY += local1611;
                                            componentUpdated(local1571);
                                        }
                                        if (local1575 < local556 + local514 + 32 && local1571.anInt468 < local1571.scrollHeight - local1571.anInt459) {
                                            local1611 = (local514 + local556 + 32 - local1575) * anInt4247 / 3;
                                            if (local1611 > anInt4247 * 10) {
                                                local1611 = anInt4247 * 10;
                                            }
                                            if (local1571.scrollHeight - local1571.anInt468 - local1571.anInt459 < local1611) {
                                                local1611 = local1571.scrollHeight - local1571.anInt459 - local1571.anInt468;
                                            }
                                            local1571.anInt468 += local1611;
                                            objGrabY -= local1611;
                                            componentUpdated(local1571);
                                        }
                                    }
                                } else if (com == selectedArea && local270 == Static250.anInt5444) {
                                    local1476.method1417(local503, local514, 128);
                                } else {
                                    local1476.plotSprite(local503, local514);
                                }
                            }
                        } else if (com.invBackground != null && local270 < 20) {
                            @Pc(1381) AbstractPix32 local1381 = com.method482(local270);
                            if (local1381 != null) {
                                local1381.plotSprite(local503, local514);
                            } else if (IfType.loadingAsset) {
                                componentUpdated(com);
                            }
                        }
                        local270++;
                    }
                }
            } else if (com.type == 3) {
                if (getIfActive(com)) {
                    local270 = com.colour2;
                    if (hoveredCom == com && com.colour2Over != 0) {
                        local270 = com.colour2Over;
                    }
                } else {
                    local270 = com.colour;
                    if (com == hoveredCom && com.colourOver != 0) {
                        local270 = com.colourOver;
                    }
                }
                if (local117 == 0) {
                    if (com.fill) {
                        if (glRenderer) {
                            Static46.method1186(local123, local114, com.anInt445, com.anInt459, local270);
                        } else {
                            Pix2D.method2495(local123, local114, com.anInt445, com.anInt459, local270);
                        }
                    } else if (glRenderer) {
                        Static46.method1179(local123, local114, com.anInt445, com.anInt459, local270);
                    } else {
                        Pix2D.method2483(local123, local114, com.anInt445, com.anInt459, local270);
                    }
                } else if (com.fill) {
                    if (glRenderer) {
                        Static46.method1182(local123, local114, com.anInt445, com.anInt459, local270, 256 - (local117 & 0xFF));
                    } else {
                        Pix2D.method2484(local123, local114, com.anInt445, com.anInt459, local270, 256 - (local117 & 0xFF));
                    }
                } else if (glRenderer) {
                    Static46.method1180(local123, local114, com.anInt445, com.anInt459, local270, 256 - (local117 & 0xFF));
                } else {
                    Pix2D.method2487(local123, local114, com.anInt445, com.anInt459, local270, 256 - (local117 & 0xFF));
                }
            } else if (com.type == 4) {
                local1921 = com.getFont(Static159.aClass36Array12);
                if (local1921 != null) {
                    @Pc(1934) JagString local1934 = com.text;
                    if (getIfActive(com)) {
                        local276 = com.colour2;
                        if (hoveredCom == com && com.colour2Over != 0) {
                            local276 = com.colour2Over;
                        }
                        if (com.text2.length() > 0) {
                            local1934 = com.text2;
                        }
                    } else {
                        local276 = com.colour;
                        if (hoveredCom == com && com.colourOver != 0) {
                            local276 = com.colourOver;
                        }
                    }
                    if (com.v3 && com.anInt458 != -1) {
                        @Pc(1989) ObjType local1989 = ObjType.list(com.anInt458);
                        local1934 = local1989.name;
                        if (local1934 == null) {
                            local1934 = Static92.aClass100_510;
                        }
                        if ((local1989.anInt2336 == 1 || com.anInt503 != 1) && com.anInt503 != -1) {
                            local1934 = JagString.join(new JagString[]{Static8.aClass100_32, local1934, Static54.aClass100_375, JagString.method1548(com.anInt503)});
                        }
                    }
                    if (Static39.aClass13_10 == com) {
                        local276 = com.colour;
                        local1934 = Text.aClass100_115;
                    }
                    if (!com.v3) {
                        local1934 = substituteVars(com, local1934);
                    }
                    local1921.method2852(local1934, local123, local114, com.anInt445, com.anInt459, local276, com.shadow ? 0 : -1, com.hAlign, com.vAlign, com.lineHeight);
                } else if (IfType.loadingAsset) {
                    componentUpdated(com);
                }
            } else if (com.type == 5) {
                @Pc(2094) AbstractPix32 local2094;
                if (com.v3) {
                    if (com.anInt458 == -1) {
                        local2094 = com.getGraphic(false);
                    } else {
                        local2094 = Static190.method3443(com.outline, com.anInt458, com.aBoolean31, com.anInt503, com.shadowColour);
                    }
                    if (local2094 != null) {
                        local276 = local2094.anInt1860;
                        local468 = local2094.anInt1866;
                        if (com.tiling) {
                            local503 = (local276 + com.anInt445 - 1) / local276;
                            local514 = (com.anInt459 + local468 - 1) / local468;
                            if (glRenderer) {
                                Static46.method1183(local123, local114, com.anInt445 + local123, com.anInt459 + local114);
                                @Pc(2274) boolean local2274 = Static209.method3702(local2094.anInt1867);
                                @Pc(2279) boolean local2279 = Static209.method3702(local2094.anInt1859);
                                @Pc(2282) GlPix32 local2282 = (GlPix32) local2094;
                                if (local2274 && local2279) {
                                    if (local117 == 0) {
                                        local2282.method1429(local123, local114, local503, local514);
                                    } else {
                                        local2282.method1426(local123, local114, 256 - (local117 & 0xFF), local503, local514);
                                    }
                                } else if (local2274) {
                                    for (local563 = 0; local563 < local514; local563++) {
                                        if (local117 == 0) {
                                            local2282.method1429(local123, local563 * local468 + local114, local503, 1);
                                        } else {
                                            local2282.method1426(local123, local114 + local563 * local468, -(local117 & 0xFF) + 256, local503, 1);
                                        }
                                    }
                                } else if (local2279) {
                                    for (local563 = 0; local563 < local503; local563++) {
                                        if (local117 == 0) {
                                            local2282.method1429(local276 * local563 + local123, local114, 1, local514);
                                        } else {
                                            local2282.method1426(local276 * local563 + local123, local114, 256 - (local117 & 0xFF), 1, local514);
                                        }
                                    }
                                } else {
                                    for (local563 = 0; local563 < local503; local563++) {
                                        for (local571 = 0; local571 < local514; local571++) {
                                            if (local117 == 0) {
                                                local2094.plotSprite(local123 + local276 * local563, local468 * local571 + local114);
                                            } else {
                                                local2094.method1417(local563 * local276 + local123, local468 * local571 + local114, 256 - (local117 & 0xFF));
                                            }
                                        }
                                    }
                                }
                                Static46.method1187(arg0, arg6, arg4, arg7);
                            } else {
                                Pix2D.method2498(local123, local114, local123 + com.anInt445, local114 - -com.anInt459);
                                for (local518 = 0; local518 < local503; local518++) {
                                    for (local556 = 0; local556 < local514; local556++) {
                                        if (com.rotate != 0) {
                                            local2094.method1420(local114 + local468 * local556 + local468 / 2, com.rotate, 4096, local518 * local276 + local123 + local276 / 2);
                                        } else if (local117 == 0) {
                                            local2094.plotSprite(local518 * local276 + local123, local468 * local556 + local114);
                                        } else {
                                            local2094.method1417(local518 * local276 + local123, local114 + local556 * local468, 256 - (local117 & 0xFF));
                                        }
                                    }
                                }
                                Pix2D.method2496(arg0, arg6, arg4, arg7);
                            }
                        } else {
                            local503 = com.anInt445 * 4096 / local276;
                            if (com.rotate != 0) {
                                local2094.method1420(local114 + com.anInt459 / 2, com.rotate, local503, local123 + com.anInt445 / 2);
                            } else if (local117 != 0) {
                                local2094.method1422(local123, local114, com.anInt445, com.anInt459, 256 - (local117 & 0xFF));
                            } else if (local276 == com.anInt445 && local468 == com.anInt459) {
                                local2094.plotSprite(local123, local114);
                            } else {
                                local2094.method1419(local123, local114, com.anInt445, com.anInt459);
                            }
                        }
                    } else if (IfType.loadingAsset) {
                        componentUpdated(com);
                    }
                } else {
                    local2094 = com.getGraphic(getIfActive(com));
                    if (local2094 != null) {
                        local2094.plotSprite(local123, local114);
                    } else if (IfType.loadingAsset) {
                        componentUpdated(com);
                    }
                }
            } else if (com.type == 6) {
                @Pc(2587) boolean local2587 = getIfActive(com);
                @Pc(2589) ModelLit local2589 = null;
                if (local2587) {
                    local276 = com.modelAnim2;
                } else {
                    local276 = com.modelAnim;
                }
                local503 = 0;
                if (com.anInt458 != -1) {
                    local2611 = ObjType.list(com.anInt458);
                    if (local2611 != null) {
                        local2611 = local2611.method1820(com.anInt503);
                        @Pc(2630) SeqType local2630 = local276 == -1 ? null : SeqType.list(local276);
                        local2589 = local2611.getModelLit(com.anInt496, com.anInt500, local2630, 1, com.anInt510);
                        if (local2589 == null) {
                            componentUpdated(com);
                        } else {
                            local503 = -local2589.calcBoundingCylinder() / 2;
                        }
                    }
                } else if (com.model1Type == 5) {
                    if (com.model1Id == -1) {
                        local2589 = aClass59_2.method1954(null, -1, null, null, 0, -1, 0, -1, -1);
                    } else {
                        local514 = com.model1Id & 0x7FF;
                        if (local514 == anInt549) {
                            local514 = 2047;
                        }
                        @Pc(2751) ClientPlayer local2751 = players[local514];
                        @Pc(2760) SeqType local2760 = local276 == -1 ? null : SeqType.list(local276);
                        if (local2751 != null && (int) local2751.name.method3158() << 11 == (com.model1Id & 0xFFFFF800)) {
                            local2589 = local2751.aClass59_1.method1954(null, -1, null, local2760, 0, -1, 0, com.anInt510, 0);
                        }
                    }
                } else if (local276 == -1) {
                    local2589 = com.method488(-1, null, -1, 0, local2587, localPlayer.aClass59_1);
                    if (local2589 == null && IfType.loadingAsset) {
                        componentUpdated(com);
                    }
                } else {
                    @Pc(2689) SeqType local2689 = SeqType.list(local276);
                    local2589 = com.method488(com.anInt496, local2689, com.anInt510, com.anInt500, local2587, localPlayer.aClass59_1);
                    if (local2589 == null && IfType.loadingAsset) {
                        componentUpdated(com);
                    }
                }
                if (local2589 != null) {
                    if (com.anInt451 > 0) {
                        local514 = (com.anInt445 << 8) / com.anInt451;
                    } else {
                        local514 = 256;
                    }
                    if (com.anInt526 <= 0) {
                        local518 = 256;
                    } else {
                        local518 = (com.anInt459 << 8) / com.anInt526;
                    }
                    local556 = local123 + com.anInt445 / 2 + (local514 * com.modelXOf >> 8);
                    local545 = com.anInt459 / 2 + local114 + (local518 * com.modelYOf >> 8);
                    if (glRenderer) {
                        if (com.orthog) {
                            Static239.method4182(local556, local545, com.modelZoom, com.aShort11, local514, local518);
                        } else {
                            Static239.method4148(local556, local545, local514, local518);
                            Static239.method4152((float) com.aShort10, (float) com.aShort11 * 1.5F);
                        }
                        Static239.method4173();
                        Static239.method4158(true);
                        Static239.method4154(false);
                        Static229.method3935(Static113.anInt4609);
                        if (Static263.aBoolean299) {
                            Static46.method1177();
                            Static239.method4168();
                            Static46.method1187(arg0, arg6, arg4, arg7);
                            Static263.aBoolean299 = false;
                        }
                        if (com.aBoolean34) {
                            Static239.method4178();
                        }
                        local563 = Pix3D.sinTable[com.modelXAn] * com.modelZoom >> 16;
                        local571 = com.modelZoom * Pix3D.cosTable[com.modelXAn] >> 16;
                        if (com.v3) {
                            local2589.method4571(com.modelYAn, com.modelZAn, com.modelXAn, com.anInt494, com.anInt497 + local563 + local503, com.anInt497 + local571, -1L);
                        } else {
                            local2589.method4571(com.modelYAn, 0, com.modelXAn, 0, local563, local571, -1L);
                        }
                        if (com.aBoolean34) {
                            Static239.method4157();
                        }
                    } else {
                        Pix3D.method1919(local556, local545);
                        local563 = Pix3D.sinTable[com.modelXAn] * com.modelZoom >> 16;
                        local571 = com.modelZoom * Pix3D.cosTable[com.modelXAn] >> 16;
                        if (!com.v3) {
                            local2589.method4571(com.modelYAn, 0, com.modelXAn, 0, local563, local571, -1L);
                        } else if (com.orthog) {
                            ((SoftwareModelLit) local2589).method4591(com.modelYAn, com.modelZAn, com.modelXAn, com.anInt494, com.anInt497 + local503 + local563, local571 + com.anInt497, com.modelZoom);
                        } else {
                            local2589.method4571(com.modelYAn, com.modelZAn, com.modelXAn, com.anInt494, com.anInt497 + local563 + local503, local571 + com.anInt497, -1L);
                        }
                        Pix3D.method1915();
                    }
                }
            } else if (com.type == 7) {
				local1921 = com.getFont(Static159.aClass36Array12);
				if (local1921 == null) {
					if (IfType.loadingAsset) {
						componentUpdated(com);
					}
					continue;
				}
				local276 = 0;
				for (local468 = 0; local468 < com.height; local468++) {
					for (local503 = 0; local503 < com.width; local503++) {
						if (com.linkObjNumber[local276] > 0) {
							local2611 = ObjType.list(com.linkObjNumber[local276] - 1);
							@Pc(3159) JagString local3159;
							if (local2611.anInt2336 != 1 && com.linkObjType[local276] == 1) {
								local3159 = JagString.join(new JagString[]{Static8.aClass100_32, local2611.name, Static230.aClass100_978});
							} else {
								local3159 = JagString.join(new JagString[]{Static8.aClass100_32, local2611.name, Static54.aClass100_375, JagString.method1548(com.linkObjType[local276])});
							}
							local556 = local123 + local503 * (com.marginX + 115);
							local545 = (com.marginY + 12) * local468 + local114;
							if (com.hAlign == 0) {
								local1921.method2857(local3159, local556, local545, com.colour, com.shadow ? 0 : -1);
							} else if (com.hAlign == 1) {
								local1921.method2875(local3159, local556 + 57, local545, com.colour, com.shadow ? 0 : -1);
							} else {
								local1921.method2864(local3159, local556 + 115 - 1, local545, com.colour, com.shadow ? 0 : -1);
							}
						}
						local276++;
					}
				}
			} else if (com.type == 8 && tooltipCom == com && tooltipNum == tooltipRedraw) {
				local276 = 0;
				local270 = 0;
				@Pc(3297) JagString local3297 = com.text;
				@Pc(3299) PixFontGeneric local3299 = Static215.aClass3_Sub2_Sub9_32;
				local3297 = substituteVars(com, local3297);
				@Pc(3325) JagString local3325;
				while (local3297.length() > 0) {
					local518 = local3297.indexOf(Static269.aClass100_556);
					if (local518 == -1) {
						local3325 = local3297;
						local3297 = TitleScreen.AUTO_EMPTY;
					} else {
						local3325 = local3297.method3137(local518, 0);
						local3297 = local3297.method3136(local518 + 4);
					}
					local556 = local3299.method2858(local3325);
					local276 += local3299.anInt3626 + 1;
					if (local270 < local556) {
						local270 = local556;
					}
				}
				local556 = local114 + com.anInt459 + 5;
				local270 += 6;
				local276 += 7;
				if (local556 + local276 > arg7) {
					local556 = arg7 - local276;
				}
				local518 = local123 + com.anInt445 - local270 - 5;
				if (local518 < local123 + 5) {
					local518 = local123 + 5;
				}
				if (local270 + local518 > arg4) {
					local518 = arg4 - local270;
				}
				if (glRenderer) {
					Static46.method1186(local518, local556, local270, local276, 16777120);
					Static46.method1179(local518, local556, local270, local276, 0);
				} else {
					Pix2D.method2495(local518, local556, local270, local276, 16777120);
					Pix2D.method2483(local518, local556, local270, local276, 0);
				}
				local3297 = com.text;
				local545 = local556 + local3299.anInt3626 + 2;
				local3297 = substituteVars(com, local3297);
				while (local3297.length() > 0) {
					local563 = local3297.indexOf(Static269.aClass100_556);
					if (local563 == -1) {
						local3325 = local3297;
						local3297 = TitleScreen.AUTO_EMPTY;
					} else {
						local3325 = local3297.method3137(local563, 0);
						local3297 = local3297.method3136(local563 + 4);
					}
					local3299.method2857(local3325, local518 + 3, local545, 0, -1);
					local545 += local3299.anInt3626 + 1;
				}
			} else if (com.type == 9) {
				if (com.aBoolean20) {
					local468 = local123 + com.anInt445;
					local276 = local114 + com.anInt459;
					local503 = local114;
				} else {
					local276 = local114;
					local503 = local114 + com.anInt459;
					local468 = local123 + com.anInt445;
				}
				if (com.lineWidth == 1) {
					if (glRenderer) {
						Static46.method1185(local123, local276, local468, local503, com.colour);
					} else {
						Pix2D.method2500(local123, local276, local468, local503, com.colour);
					}
				} else if (glRenderer) {
					Static46.method1181(local123, local276, local468, local503, com.colour, com.lineWidth);
				} else {
					Pix2D.method2494(local123, local276, local468, local503, com.colour, com.lineWidth);
				}
			}
        }
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(Z)V")
	public static void method1807() {
		for (@Pc(11) int local11 = 0; local11 < 100; local11++) {
			componentRedrawRequested1[local11] = true;
		}
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(ZI)V")
	public static void method1805(@OriginalArg(0) boolean arg0) {
		@Pc(7) byte local7;
		@Pc(9) byte[][] local9;
		if (glRenderer && arg0) {
			local7 = 1;
			local9 = aByteArrayArray14;
		} else {
			local7 = 4;
			local9 = aByteArrayArray13;
		}
		@Pc(18) int local18 = local9.length;
		@Pc(20) int local20;
		@Pc(38) int local38;
		@Pc(49) int local49;
		@Pc(53) byte[] local53;
		for (local20 = 0; local20 < local18; local20++) {
			local38 = (mapBuildIndex[local20] >> 8) * 64 - mapBuildBaseX;
			local49 = (mapBuildIndex[local20] & 0xFF) * 64 - mapBuildBaseZ;
			local53 = local9[local20];
			if (local53 != null) {
				doAudio();
				ClientBuild.method2203(levelCollisionMap, arg0, mapBuildCenterZoneZ * 8 - 48, local49, local38, (mapBuildCenterZoneX - 6) * 8, local53);
			}
		}
		for (local20 = 0; local20 < local18; local20++) {
			local38 = (mapBuildIndex[local20] >> 8) * 64 - mapBuildBaseX;
			local49 = (mapBuildIndex[local20] & 0xFF) * 64 - mapBuildBaseZ;
			local53 = local9[local20];
			if (local53 == null && mapBuildCenterZoneX < 800) {
				doAudio();
				for (@Pc(130) int local130 = 0; local130 < local7; local130++) {
					Static23.method645(local130, local49, local38, 64, 64);
				}
			}
		}
	}

	@OriginalMember(owner = "client!ta", name = "a", descriptor = "(I)V")
	public static void getNpcPosExtended() {
		for (@Pc(3) int local3 = 0; local3 < entityUpdateCount; local3++) {
			@Pc(10) int local10 = entityUpdateIds[local3];
			@Pc(14) ClientNPC local14 = npcs[local10];
			@Pc(18) int local18 = in.g1();
			if ((local18 & 0x8) != 0) {
				local18 += in.g1() << 8;
			}
			@Pc(43) int local43;
			@Pc(47) int local47;
			if ((local18 & 0x40) != 0) {
				local43 = in.g1();
				local47 = in.method2212();
				local14.method2686(local47, loopCycle, local43);
				local14.anInt3378 = loopCycle + 300;
				local14.anInt3372 = in.method2180();
			}
			if ((local18 & 0x2) != 0) {
				local43 = in.method2212();
				local47 = in.method2180();
				local14.method2686(local47, loopCycle, local43);
			}
			if ((local18 & 0x10) != 0) {
				local43 = in.g2();
				local47 = in.g1();
				if (local43 == 65535) {
					local43 = -1;
				}
				method3855(local47, local43, local14);
			}
			if ((local18 & 0x4) != 0) {
				local14.targetId = in.method2184();
				if (local14.targetId == 65535) {
					local14.targetId = -1;
				}
			}
			if ((local18 & 0x80) != 0) {
				local43 = in.method2184();
				if (local43 == 65535) {
					local43 = -1;
				}
				local47 = in.method2208();
				@Pc(147) boolean local147 = true;
				if (local43 != -1 && local14.anInt3432 != -1 && SeqType.list(SpotType.list(local43).anim).priority < SeqType.list(SpotType.list(local14.anInt3432).anim).priority) {
					local147 = false;
				}
				if (local147) {
					local14.anInt3432 = local43;
					local14.anInt3359 = (local47 & 0xFFFF) + loopCycle;
					local14.anInt3361 = 0;
					local14.anInt3399 = 0;
					local14.anInt3394 = local47 >> 16;
					local14.anInt3418 = 1;
					if (local14.anInt3359 > loopCycle) {
						local14.anInt3399 = -1;
					}
					if (local14.anInt3432 != -1 && local14.anInt3359 == loopCycle) {
						@Pc(227) int local227 = SpotType.list(local14.anInt3432).anim;
						if (local227 != -1) {
							@Pc(236) SeqType local236 = SeqType.list(local227);
							if (local236 != null && local236.frames != null) {
								method2836(local14.z, local236, local14.x, false, 0);
							}
						}
					}
				}
			}
			if ((local18 & 0x1) != 0) {
				if (local14.aClass96_1.method2935()) {
					BgSound.method1877(local14);
				}
				local14.method2698(NPCType.list(in.method2192()));
				local14.method2692(local14.aClass96_1.anInt3713);
				local14.anInt3365 = local14.aClass96_1.anInt3737;
				if (local14.aClass96_1.method2935()) {
					BgSound.method2411(local14.routeZ[0], null, 0, local14, local14.routeX[0], minusedlevel, null);
				}
			}
			if ((local18 & 0x20) != 0) {
				local14.aClass100_640 = in.gjstr();
				local14.anInt3408 = 100;
			}
			if ((local18 & 0x100) != 0) {
				local43 = in.method2212();
				@Pc(331) int[] local331 = new int[local43];
				@Pc(334) int[] local334 = new int[local43];
				@Pc(337) int[] local337 = new int[local43];
				for (@Pc(339) int local339 = 0; local339 < local43; local339++) {
					@Pc(350) int local350 = in.method2192();
					if (local350 == 65535) {
						local350 = -1;
					}
					local331[local339] = local350;
					local334[local339] = in.method2180();
					local337[local339] = in.g2();
				}
				method3037(local337, local14, local334, local331);
			}
			if ((local18 & 0x200) != 0) {
				local14.anInt3382 = in.method2184();
				local14.anInt3363 = in.g2();
			}
		}
	}

	@OriginalMember(owner = "client!ta", name = "a", descriptor = "(IZIII)V")
	public static void method4017(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (IfType.openInterface(arg2)) {
			method4190(-1, arg1, arg3, arg0, IfType.list[arg2]);
		}
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(B)V")
	public static void followCamera() {
		if (anInt2031 < 128) {
			anInt2031 = 128;
		}
		if (anInt2031 > 383) {
			anInt2031 = 383;
		}
		anInt1747 &= 0x7FF;
		@Pc(33) int local33 = Static81.anInt2223 >> 7;
		@Pc(37) int local37 = Static111.anInt2900 >> 7;
		@Pc(43) int local43 = getAvH(minusedlevel, Static81.anInt2223, Static111.anInt2900);
		@Pc(45) int local45 = 0;
		@Pc(64) int local64;
		if (local33 > 3 && local37 > 3 && local33 < 100 && local37 < 100) {
			for (local64 = local33 - 4; local64 <= local33 + 4; local64++) {
				for (@Pc(73) int local73 = local37 - 4; local73 <= local37 + 4; local73++) {
					@Pc(80) int local80 = minusedlevel;
					if (local80 < 3 && (ClientBuild.mapl[1][local64][local73] & 0x2) == 2) {
						local80++;
					}
					@Pc(117) int local117 = (World.aByteArrayArrayArray13[local80][local64][local73] & 0xFF) * 8 + local43 - World.groundh[local80][local64][local73];
					if (local117 > local45) {
						local45 = local117;
					}
				}
			}
		}
		local64 = local45 * 192;
		if (local64 > 98048) {
			local64 = 98048;
		}
		if (local64 < 32768) {
			local64 = 32768;
		}
		if (ClientBuild.anInt5245 < local64) {
			ClientBuild.anInt5245 += (local64 - ClientBuild.anInt5245) / 24;
		} else if (local64 < ClientBuild.anInt5245) {
			ClientBuild.anInt5245 += (local64 - ClientBuild.anInt5245) / 80;
		}
	}

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(IIIIIIII)V")
	public static void locChangeUnchecked(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (arg1 < 1 || arg4 < 1 || arg1 > 102 || arg4 > 102) {
			return;
		}
		@Pc(39) int local39;
		if (!highDetail() && (ClientBuild.mapl[0][arg1][arg4] & 0x2) == 0) {
			local39 = arg2;
			if ((ClientBuild.mapl[arg2][arg1][arg4] & 0x8) != 0) {
				local39 = 0;
			}
			if (local39 != Static41.anInt1316) {
				return;
			}
		}
		local39 = arg2;
		if (arg2 < 3 && (ClientBuild.mapl[1][arg1][arg4] & 0x2) == 2) {
			local39 = arg2 + 1;
		}
		Static43.method1144(arg4, arg1, arg2, arg6, local39, levelCollisionMap[arg2]);
		if (arg0 >= 0) {
			@Pc(92) boolean local92 = Static250.aBoolean283;
			Static250.aBoolean283 = true;
			ClientBuild.method3397(local39, false, arg2, false, levelCollisionMap[arg2], arg0, arg5, arg1, arg4, arg3);
			Static250.aBoolean283 = local92;
		}
	}

	@OriginalMember(owner = "client!ql", name = "a", descriptor = "(IIII)I")
	public static int getAvH(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (World.groundh == null) {
			return 0;
		}
		@Pc(12) int local12 = arg1 >> 7;
		@Pc(16) int local16 = arg2 >> 7;
		if (local12 < 0 || local16 < 0 || local12 > 103 || local16 > 103) {
			return 0;
		}
		@Pc(36) int local36 = arg1 & 0x7F;
		@Pc(40) int local40 = arg2 & 0x7F;
		@Pc(42) int local42 = arg0;
		if (arg0 < 3 && (ClientBuild.mapl[1][local12][local16] & 0x2) == 2) {
			local42 = arg0 + 1;
		}
		@Pc(91) int local91 = local36 * World.groundh[local42][local12 + 1][local16 + 1] + World.groundh[local42][local12][local16 + 1] * (128 - local36) >> 7;
		@Pc(118) int local118 = local36 * World.groundh[local42][local12 + 1][local16] + (128 - local36) * World.groundh[local42][local12][local16] >> 7;
		return local40 * local91 + (128 - local40) * local118 >> 7;
	}

	@OriginalMember(owner = "client!i", name = "i", descriptor = "(Z)V")
	public static void legacyUpdated() {
		for (@Pc(6) SubInterface local6 = (SubInterface) aClass133_9.search(); local6 != null; local6 = (SubInterface) aClass133_9.findnext()) {
			@Pc(14) int local14 = local6.id;
			if (IfType.openInterface(local14)) {
				@Pc(21) boolean local21 = true;
				@Pc(25) IfType[] local25 = IfType.list[local14];
				@Pc(27) int local27;
				for (local27 = 0; local27 < local25.length; local27++) {
					if (local25[local27] != null) {
						local21 = local25[local27].v3;
						break;
					}
				}
				if (!local21) {
					local27 = (int) local6.key;
					@Pc(60) IfType local60 = IfType.get(local27);
					if (local60 != null) {
						componentUpdated(local60);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!gl", name = "a", descriptor = "(II)V")
	public static void clientVar(@OriginalArg(1) int varp) {
		legacyUpdated();
		BgSound.recalculateMultilocs();

		@Pc(17) int clientcode = VarpType.list(varp).clientcode;
		if (clientcode == 0) {
			return;
		}

		@Pc(25) int state = VarCache.var[varp];
		if (clientcode == 6) {
			chatEffects = state;
		}
		if (clientcode == 5) {
			oneMouseButton = state;
		}
		if (clientcode == 9) {
			bankArrangeMode = state;
		}
	}

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(IIIZIIIIIIII)Z")
	public static boolean tryMove(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
		if (localPlayer.method2693() == 2) {
			return method8(arg6, arg7, arg4, arg0, arg9, arg3, arg2, arg1, arg5, arg8, arg10);
		} else if (localPlayer.method2693() <= 2) {
			return method1171(arg5, arg4, arg10, arg9, arg8, arg2, arg1, arg3, arg7, arg0, arg6);
		} else {
			return method1955(arg9, arg6, arg8, arg1, localPlayer.method2693(), arg5, arg7, arg4, arg10, arg2, arg3, arg0);
		}
	}

	@OriginalMember(owner = "client!i", name = "p", descriptor = "(II)V")
	public static void doAction(@OriginalArg(1) int arg0) {
		if (arg0 < 0) {
			return;
		}
		@Pc(15) int local15 = anIntArray408[arg0];
		@Pc(19) int local19 = anIntArray142[arg0];
		@Pc(23) int local23 = menuAction[arg0];
		if (local23 >= 2000) {
			local23 -= 2000;
		}
		@Pc(31) long local31 = aLongArray5[arg0];
		@Pc(36) int local36 = (int) aLongArray5[arg0];
		@Pc(43) ClientPlayer local43;
		if (local23 == 31) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossMode = 2;
				crossCycle = 0;
				crossX = ClientMouseListener.mouseClickX;
				crossY = ClientMouseListener.mouseClickY;
				out.p1Enc(71);
				out.method2191(local36);
			}
		}
		if (local23 == 46) {
			method4003(local31, local19, local15);
			out.p1Enc(247);
			out.method2222(mapBuildBaseZ + local19);
			out.method2191(local15 + mapBuildBaseX);
			out.p2(Integer.MAX_VALUE & (int) (local31 >>> 32));
		}
		if (local23 == 40) {
			out.p1Enc(27);
			out.p2(Static185.anInt4370);
			out.method2211(local19);
			out.method2222(local15);
			out.method2211(Static224.anInt5062);
			out.method2191(Static274.anInt4997);
			out.method2191(local36);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		@Pc(192) ClientNPC local192;
		if (local23 == 19) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossX = ClientMouseListener.mouseClickX;
				crossMode = 2;
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				out.p1Enc(30);
				out.p2(local36);
			}
		}
		if (local23 == 17) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossX = ClientMouseListener.mouseClickX;
				crossCycle = 0;
				crossMode = 2;
				crossY = ClientMouseListener.mouseClickY;
				out.p1Enc(78);
				out.method2222(local36);
			}
		}
		if (local23 == 44) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossX = ClientMouseListener.mouseClickX;
				crossMode = 2;
				crossY = ClientMouseListener.mouseClickY;
				crossCycle = 0;
				out.p1Enc(133);
				out.method2222(local36);
			}
		}
		if (local23 == 58) {
			out.p1Enc(135);
			out.method2209(local36);
			out.method2209(local15);
			out.method2185(local19);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 42) {
			method4003(local31, local19, local15);
			out.p1Enc(254);
			out.method2222(local15 + mapBuildBaseX);
			out.method2209((int) (local31 >>> 32) & Integer.MAX_VALUE);
			out.p2(local19 + mapBuildBaseZ);
		}
		if (local23 == 28) {
			method2909();
		}
		if (local23 == 45) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossX = ClientMouseListener.mouseClickX;
				crossMode = 2;
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				out.p1Enc(239);
				out.method2211(Static98.anInt2512);
				out.method2209(Static15.anInt506);
				out.method2191(local36);
			}
		}
		@Pc(560) boolean local560;
		if (local23 == 18) {
			if (game == 1) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			} else {
				local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
				if (!local560) {
					tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
				}
			}
			crossX = ClientMouseListener.mouseClickX;
			crossCycle = 0;
			crossMode = 2;
			crossY = ClientMouseListener.mouseClickY;
			out.p1Enc(66);
			out.method2222(mapBuildBaseX + local15);
			out.p2(local36);
			out.method2191(local19 + mapBuildBaseZ);
		}
		if (local23 == 1001) {
			method4003(local31, local19, local15);
			out.p1Enc(170);
			out.method2191(Integer.MAX_VALUE & (int) (local31 >>> 32));
			out.method2191(local15 + mapBuildBaseX);
			out.method2191(local19 + mapBuildBaseZ);
		}
		if (local23 == 1002) {
			crossMode = 2;
			crossX = ClientMouseListener.mouseClickX;
			crossY = ClientMouseListener.mouseClickY;
			crossCycle = 0;
			out.p1Enc(92);
			out.method2191(local36);
		}
		@Pc(693) IfType local693;
		if (local23 == 1006) {
			local693 = IfType.get(local19);
			if (local693 == null || local693.linkObjType[local15] < 100000) {
				out.p1Enc(92);
				out.method2191(local36);
			} else {
				addChat(TitleScreen.AUTO_EMPTY, 0, JagString.join(new JagString[] { JagString.parseInt(local693.linkObjType[local15]), aClass100_1039, ObjType.list(local36).name}));
			}
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 60) {
			if (local36 == 0) {
				World.method3556(minusedlevel, local15, local19);
			} else if (local36 == 1) {
				if (staffmodlevel > 0 && ClientKeyboardListener.keyHeld[82] && ClientKeyboardListener.keyHeld[81]) {
					tele(mapBuildBaseX + local15, mapBuildBaseZ + local19, minusedlevel);
				} else if (tryMove(localPlayer.routeZ[0], 0, 0, true, 0, local15, 0, 0, 1, local19, localPlayer.routeX[0])) {
					out.p1(Static1.anInt5);
					out.p1(Static107.anInt2878);
					out.p2(anInt1747);
					out.p1(57);
					out.p1(macroMinimapAngle);
					out.p1(macroMinimapZoom);
					out.p1(89);
					out.p2(localPlayer.x);
					out.p2(localPlayer.z);
					out.p1(Static221.anInt4364);
					out.p1(63);
				}
			}
		}
		if (local23 == 1007) {
			crossCycle = 0;
			crossMode = 2;
			crossY = ClientMouseListener.mouseClickY;
			crossX = ClientMouseListener.mouseClickX;
			local192 = npcs[local36];
			if (local192 != null) {
				@Pc(884) NPCType local884 = local192.aClass96_1;
				if (local884.anIntArray357 != null) {
					local884 = local884.method2932();
				}
				if (local884 != null) {
					out.p1Enc(72);
					out.p2(local884.id);
				}
			}
		}
		if (local23 == 47) {
			out.p1Enc(156);
			out.method2191(local15);
			out.method2209(local36);
			out.method2211(local19);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 3) {
			out.p1Enc(253);
			out.method2211(Static98.anInt2512);
			out.method2191(local15);
			out.method2211(local19);
			out.method2209(local36);
			out.method2222(Static15.anInt506);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 10) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossMode = 2;
				crossY = ClientMouseListener.mouseClickY;
				crossX = ClientMouseListener.mouseClickX;
				crossCycle = 0;
				out.p1Enc(4);
				out.method2222(local36);
			}
		}
		if (local23 == 41 && Static39.aClass13_10 == null) {
			method10(local15, local19);
			Static39.aClass13_10 = IfType.method1418(local19, local15);
			componentUpdated(Static39.aClass13_10);
		}
		if (local23 == 49) {
			method4003(local31, local19, local15);
			out.p1Enc(84);
			out.method2191(Integer.MAX_VALUE & (int) (local31 >>> 32));
			out.method2191(mapBuildBaseZ + local19);
			out.method2222(local15 + mapBuildBaseX);
		}
		if (local23 == 23) {
			out.p1Enc(206);
			out.method2209(local36);
			out.method2222(local15);
			out.method2211(local19);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 14 && method4003(local31, local19, local15)) {
			out.p1Enc(134);
			out.method2209(mapBuildBaseX + local15);
			out.p2(Static274.anInt4997);
			out.method2222(local19 + mapBuildBaseZ);
			out.p2(Static185.anInt4370);
			out.method2185(Static224.anInt5062);
			out.method2209((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 37) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossCycle = 0;
				crossMode = 2;
				crossY = ClientMouseListener.mouseClickY;
				crossX = ClientMouseListener.mouseClickX;
				out.p1Enc(114);
				out.method2191(local36);
			}
		}
		if (local23 == 9 || local23 == 1003) {
			method4512(aClass100Array160[arg0], local15, local36, local19);
		}
		if (local23 == 5) {
			out.p1Enc(55);
			out.method2222(local36);
			out.method2209(local15);
			out.method2201(local19);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 21) {
			if (game == 1) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			} else {
				local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
				if (!local560) {
					tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
				}
			}
			crossMode = 2;
			crossX = ClientMouseListener.mouseClickX;
			crossCycle = 0;
			crossY = ClientMouseListener.mouseClickY;
			out.p1Enc(228);
			out.p2(local36);
			out.method2222(mapBuildBaseX + local15);
			out.method2191(mapBuildBaseZ + local19);
		}
		if (local23 == 4) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossMode = 2;
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				crossX = ClientMouseListener.mouseClickX;
				out.p1Enc(148);
				out.method2209(local36);
			}
		}
		if (local23 == 32) {
			local693 = IfType.method1418(local19, local15);
			if (local693 != null) {
				method1294();
				@Pc(1493) ServerActive local1493 = getActive(local693);
				method4246(local19, local15, local1493.method512(), local1493.anInt540, local693.anInt499, local693.anInt484);
				Static260.anInt5014 = 0;
				Static102.aClass100_545 = method1963(local693);
				if (Static102.aClass100_545 == null) {
					Static102.aClass100_545 = Static250.aClass100_1042;
				}
				if (local693.v3) {
					Static78.aClass100_466 = JagString.join(new JagString[] { local693.aClass100_88, Static204.aClass100_896 });
				} else {
					Static78.aClass100_466 = JagString.join(new JagString[] { aClass100_332, local693.targetBase, Static204.aClass100_896 });
				}
			}
			return;
		}
		if (local23 == 29) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossY = ClientMouseListener.mouseClickY;
				crossCycle = 0;
				crossMode = 2;
				crossX = ClientMouseListener.mouseClickX;
				out.p1Enc(180);
				out.method2191(local36);
			}
		}
		if (local23 == 35) {
			out.p1Enc(161);
			out.method2211(local19);
			out.method2191(local36);
			out.method2191(local15);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 15) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossCycle = 0;
				crossMode = 2;
				crossX = ClientMouseListener.mouseClickX;
				crossY = ClientMouseListener.mouseClickY;
				out.p1Enc(195);
				out.method2209(Static15.anInt506);
				out.method2211(Static98.anInt2512);
				out.method2191(local36);
			}
		}
		if (local23 == 34) {
			if (game == 1) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			} else {
				local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
				if (!local560) {
					tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
				}
			}
			crossX = ClientMouseListener.mouseClickX;
			crossMode = 2;
			crossY = ClientMouseListener.mouseClickY;
			crossCycle = 0;
			out.p1Enc(109);
			out.method2222(local19 + mapBuildBaseZ);
			out.p2(local15 + mapBuildBaseX);
			out.method2191(local36);
		}
		if (local23 == 25) {
			out.p1Enc(81);
			out.method2209(local15);
			out.p2(local36);
			out.method2201(local19);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 2) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossY = ClientMouseListener.mouseClickY;
				crossMode = 2;
				crossX = ClientMouseListener.mouseClickX;
				crossCycle = 0;
				out.p1Enc(218);
				out.method2222(local36);
			}
		}
		@Pc(1955) int local1955;
		if (local23 == 51) {
			out.p1Enc(10);
			out.p4(local19);
			local693 = IfType.get(local19);
			if (local693.scripts != null && local693.scripts[0][0] == 5) {
				local1955 = local693.scripts[0][1];
				if (VarCache.var[local1955] != local693.scriptComparator[0]) {
					VarCache.var[local1955] = local693.scriptComparator[0];
					clientVar(local1955);
				}
			}
		}
		if (local23 == 26) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossMode = 2;
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				crossX = ClientMouseListener.mouseClickX;
				out.p1Enc(115);
				out.method2185(Static224.anInt5062);
				out.method2222(Static185.anInt4370);
				out.method2222(local36);
				out.method2191(Static274.anInt4997);
			}
		}
		if (local23 == 59) {
			out.p1Enc(10);
			out.p4(local19);
			local693 = IfType.get(local19);
			if (local693.scripts != null && local693.scripts[0][0] == 5) {
				local1955 = local693.scripts[0][1];
				VarCache.var[local1955] = 1 - VarCache.var[local1955];
				clientVar(local1955);
			}
		}
		if (local23 == 33) {
			local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
			if (!local560) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			}
			crossX = ClientMouseListener.mouseClickX;
			crossCycle = 0;
			crossY = ClientMouseListener.mouseClickY;
			crossMode = 2;
			out.p1Enc(101);
			out.method2191(local15 + mapBuildBaseX);
			out.method2222(Static185.anInt4370);
			out.method2222(Static274.anInt4997);
			out.method2222(local36);
			out.method2191(mapBuildBaseZ + local19);
			out.method2185(Static224.anInt5062);
		}
		if (local23 == 1004) {
			crossCycle = 0;
			crossX = ClientMouseListener.mouseClickX;
			crossMode = 2;
			crossY = ClientMouseListener.mouseClickY;
			out.p1Enc(94);
			out.method2191(local36);
		}
		if (local23 == 11) {
			if (local36 == 0) {
				anInt3096 = 1;
				World.method3556(minusedlevel, local15, local19);
			} else if (local36 == 1) {
				out.p1Enc(131);
				out.method2185(Static98.anInt2512);
				out.method2209(mapBuildBaseX + local15);
				out.method2191(Static15.anInt506);
				out.method2209(local19 + mapBuildBaseZ);
			}
		}
		if (local23 == 8) {
			local693 = IfType.get(local19);
			@Pc(2287) boolean local2287 = true;
			if (local693.clientCode > 0) {
				local2287 = clientButton(local693);
			}
			if (local2287) {
				out.p1Enc(10);
				out.p4(local19);
			}
		}
		if (local23 == 1) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				crossMode = 2;
				crossX = ClientMouseListener.mouseClickX;
				out.p1Enc(248);
				out.method2191(local36);
				out.p2(Static274.anInt4997);
				out.p2(Static185.anInt4370);
				out.method2185(Static224.anInt5062);
			}
		}
		if (local23 == 7) {
			out.p1Enc(85);
			out.method2201(local19);
			out.p2(local15);
			out.method2209(local36);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 24) {
			if (game == 1) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			} else {
				local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
				if (!local560) {
					tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
				}
			}
			crossMode = 2;
			crossY = ClientMouseListener.mouseClickY;
			crossX = ClientMouseListener.mouseClickX;
			crossCycle = 0;
			out.p1Enc(48);
			out.method2209(local15 + mapBuildBaseX);
			out.method2191(local36);
			out.method2222(mapBuildBaseZ + local19);
		}
		if (local23 == 38 && method4003(local31, local19, local15)) {
			out.p1Enc(233);
			out.method2191(local19 + mapBuildBaseZ);
			out.method2209(mapBuildBaseX + local15);
			out.method2191(Static15.anInt506);
			out.method2201(Static98.anInt2512);
			out.method2209((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 13) {
			out.p1Enc(6);
			out.p4(local19);
			out.method2209(local15);
			out.method2222(local36);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 57) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossMode = 2;
				crossY = ClientMouseListener.mouseClickY;
				crossX = ClientMouseListener.mouseClickX;
				crossCycle = 0;
				out.p1Enc(175);
				out.method2209(local36);
			}
		}
		if (local23 == 22) {
			method1294();
			local693 = IfType.get(local19);
			Static224.anInt5062 = local19;
			Static185.anInt4370 = local15;
			Static260.anInt5014 = 1;
			Static274.anInt4997 = local36;
			componentUpdated(local693);
			Static34.aClass100_203 = JagString.join(new JagString[] { Static8.aClass100_32, ObjType.list(local36).name, Static204.aClass100_896 });
			if (Static34.aClass100_203 == null) {
				Static34.aClass100_203 = Static92.aClass100_510;
			}
			return;
		}
		if (local23 == 50) {
			method4003(local31, local19, local15);
			out.p1Enc(194);
			out.method2191(local19 + mapBuildBaseZ);
			out.method2222(mapBuildBaseX + local15);
			out.p2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 48) {
			out.p1Enc(154);
			out.method2222(local15);
			out.method2201(local19);
			out.method2191(local36);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 30) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossCycle = 0;
				crossX = ClientMouseListener.mouseClickX;
				crossY = ClientMouseListener.mouseClickY;
				crossMode = 2;
				out.p1Enc(68);
				out.method2191(local36);
			}
		}
		if (local23 == 43) {
			out.p1Enc(153);
			out.method2211(local19);
			out.method2222(local15);
			out.method2222(local36);
			selectedCycle = 0;
			selectedArea = IfType.get(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 39) {
			local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
			if (!local560) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			}
			crossY = ClientMouseListener.mouseClickY;
			crossX = ClientMouseListener.mouseClickX;
			crossMode = 2;
			crossCycle = 0;
			out.p1Enc(73);
			out.method2201(Static98.anInt2512);
			out.p2(mapBuildBaseZ + local19);
			out.method2191(local36);
			out.method2191(local15 + mapBuildBaseX);
			out.method2222(Static15.anInt506);
		}
		if (local23 == 12) {
			out.p1Enc(82);
			out.p2(Static15.anInt506);
			out.method2201(local19);
			out.p4(Static98.anInt2512);
			out.method2191(local15);
		}
		if (local23 == 36) {
			if (local36 == 0) {
				anInt4422 = 1;
				World.method3556(minusedlevel, local15, local19);
			} else if (staffmodlevel > 0 && ClientKeyboardListener.keyHeld[82] && ClientKeyboardListener.keyHeld[81]) {
				tele(local15 + mapBuildBaseX, mapBuildBaseZ - -local19, minusedlevel);
			} else {
				out.p1Enc(179);
				out.p2(local19 + mapBuildBaseZ);
				out.p2(local15 + mapBuildBaseX);
			}
		}
		if (local23 == 6) {
			local43 = players[local36];
			if (local43 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local43.routeX[0], 1, 0, 2, local43.routeZ[0], localPlayer.routeX[0]);
				crossY = ClientMouseListener.mouseClickY;
				crossCycle = 0;
				crossMode = 2;
				crossX = ClientMouseListener.mouseClickX;
				out.p1Enc(106);
				out.p2(local36);
			}
		}
		if (local23 == 20) {
			if (game == 1) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
			} else {
				local560 = tryMove(localPlayer.routeZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, localPlayer.routeX[0]);
				if (!local560) {
					tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, localPlayer.routeX[0]);
				}
			}
			crossY = ClientMouseListener.mouseClickY;
			crossCycle = 0;
			crossX = ClientMouseListener.mouseClickX;
			crossMode = 2;
			out.p1Enc(33);
			out.p2(local36);
			out.p2(mapBuildBaseX + local15);
			out.method2222(mapBuildBaseZ + local19);
		}
		if (local23 == 16) {
			local192 = npcs[local36];
			if (local192 != null) {
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local192.routeX[0], 1, 0, 2, local192.routeZ[0], localPlayer.routeX[0]);
				crossX = ClientMouseListener.mouseClickX;
				crossCycle = 0;
				crossY = ClientMouseListener.mouseClickY;
				crossMode = 2;
				out.p1Enc(3);
				out.method2191(local36);
			}
		}
		if (Static260.anInt5014 != 0) {
			Static260.anInt5014 = 0;
			componentUpdated(IfType.get(Static224.anInt5062));
		}
		if (targetMode) {
			method1294();
		}
		if (selectedArea != null && selectedCycle == 0) {
			componentUpdated(selectedArea);
		}
	}

	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(IIIIIIIII)V")
	public static void method86(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		if (IfType.openInterface(arg0)) {
			drawLayer(arg1, arg7, arg3, IfType.list[arg0], arg2, -1, arg6, arg5, arg4);
		} else if (arg4 == -1) {
			for (@Pc(27) int local27 = 0; local27 < 100; local27++) {
				componentRedrawRequested1[local27] = true;
			}
		} else {
			componentRedrawRequested1[arg4] = true;
		}
	}

	@OriginalMember(owner = "client!ag", name = "j", descriptor = "(I)V")
	public static void method84() {
		World.method1500();
		for (@Pc(9) int local9 = 0; local9 < 4; local9++) {
			levelCollisionMap[local9].reset();
		}
		System.gc();
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(BJII)Z")
	public static boolean method4003(@OriginalArg(1) long arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(12) int local12 = (int) arg0 >> 14 & 0x1F;
		@Pc(24) int local24 = (int) arg0 >> 20 & 0x3;
		@Pc(31) int local31 = (int) (arg0 >>> 32) & Integer.MAX_VALUE;
		if (local12 == 10 || local12 == 11 || local12 == 22) {
			@Pc(46) LocType local46 = LocType.list(local31);
			@Pc(62) int local62;
			@Pc(59) int local59;
			if (local24 == 0 || local24 == 2) {
				local59 = local46.anInt4403;
				local62 = local46.anInt4397;
			} else {
				local59 = local46.anInt4397;
				local62 = local46.anInt4403;
			}
			@Pc(73) int local73 = local46.anInt4433;
			if (local24 != 0) {
				local73 = (local73 << local24 & 0xF) + (local73 >> 4 - local24);
			}
			tryMove(localPlayer.routeZ[0], 0, local59, true, local73, arg2, local62, 0, 2, arg1, localPlayer.routeX[0]);
		} else {
			tryMove(localPlayer.routeZ[0], local24, 0, true, 0, arg2, 0, local12 + 1, 2, arg1, localPlayer.routeX[0]);
		}
		crossY = ClientMouseListener.mouseClickY;
		crossCycle = 0;
		crossMode = 2;
		crossX = ClientMouseListener.mouseClickX;
		return true;
	}

	@OriginalMember(owner = "client!dg", name = "a", descriptor = "(ILclient!be;)V")
	public static void componentUpdated(@OriginalArg(1) IfType arg0) {
		if (Static182.anInt4311 == arg0.drawTime) {
			componentRedrawRequested1[arg0.drawCount] = true;
		}
	}

	@OriginalMember(owner = "client!tc", name = "a", descriptor = "(IILclient!be;BIIII)V")
	public static void doScrollbar(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) IfType arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (Static45.aBoolean84) {
			Static42.anInt1396 = 32;
		} else {
			Static42.anInt1396 = 0;
		}
		Static45.aBoolean84 = false;
		@Pc(139) int local139;
		if (ClientMouseListener.mouseButton != 0) {
			if (arg4 <= arg3 && arg4 + 16 > arg3 && arg0 >= arg5 && arg5 + 16 > arg0) {
				arg2.anInt468 -= 4;
				componentUpdated(arg2);
			} else if (arg3 >= arg4 && arg3 < arg4 + 16 && arg0 >= arg1 + arg5 - 16 && arg1 + arg5 > arg0) {
				arg2.anInt468 += 4;
				componentUpdated(arg2);
			} else if (arg3 >= arg4 - Static42.anInt1396 && arg3 < arg4 + Static42.anInt1396 + 16 && arg0 >= arg5 + 16 && arg1 + arg5 - 16 > arg0) {
				local139 = arg1 * (arg1 - 32) / arg6;
				if (local139 < 8) {
					local139 = 8;
				}
				@Pc(150) int local150 = arg1 - local139 - 32;
				@Pc(162) int local162 = arg0 - local139 / 2 - arg5 - 16;
				arg2.anInt468 = (arg6 - arg1) * local162 / local150;
				componentUpdated(arg2);
				Static45.aBoolean84 = true;
			}
		}
        if (mouseWheelRotation != 0) {
            local139 = arg2.anInt445;
            if (arg4 - local139 <= arg3 && arg5 <= arg0 && arg3 < arg4 + 16 && arg1 + arg5 >= arg0) {
                arg2.anInt468 += mouseWheelRotation * 45;
                componentUpdated(arg2);
            }
        }
    }

	@OriginalMember(owner = "client!uh", name = "f", descriptor = "(I)V")
	public static void method4302() {
		if (Static236.method4047() != 2) {
			return;
		}
		@Pc(27) byte local27 = (byte) (Static136.anInt3325 - 4 & 0xFF);
		@Pc(31) int local31 = Static136.anInt3325 % 104;
		@Pc(33) int local33;
		@Pc(40) int local40;
		for (local33 = 0; local33 < 4; local33++) {
			for (local40 = 0; local40 < 104; local40++) {
				Static266.aByteArrayArrayArray15[local33][local31][local40] = local27;
			}
		}
		if (minusedlevel == 3) {
			return;
		}
		for (local33 = 0; local33 < 2; local33++) {
			Static79.anIntArray205[local33] = -1000000;
			Static149.anIntArray338[local33] = 1000000;
			Static267.anIntArray518[local33] = 0;
			Static243.anIntArray476[local33] = 1000000;
			Static50.anIntArray134[local33] = 0;
		}
		if (anInt5096 != 1) {
			local33 = getAvH(minusedlevel, anInt3439, anInt3302);
			if (local33 - anInt40 < 800 && (ClientBuild.mapl[minusedlevel][anInt3439 >> 7][anInt3302 >> 7] & 0x4) != 0) {
				method4348(false, anInt3439 >> 7, anInt3302 >> 7, World.activeTiles, 1);
			}
			return;
		}
		if ((ClientBuild.mapl[minusedlevel][localPlayer.x >> 7][localPlayer.z >> 7] & 0x4) != 0) {
			method4348(false, localPlayer.x >> 7, localPlayer.z >> 7, World.activeTiles, 0);
		}
		if (anInt5333 >= 310) {
			return;
		}
		@Pc(135) int local135 = localPlayer.z >> 7;
		local40 = anInt3302 >> 7;
		@Pc(146) int local146;
		if (local40 < local135) {
			local146 = local135 - local40;
		} else {
			local146 = local40 - local135;
		}
		local33 = anInt3439 >> 7;
		@Pc(162) int local162 = localPlayer.x >> 7;
		@Pc(174) int local174;
		if (local162 > local33) {
			local174 = local162 - local33;
		} else {
			local174 = local33 - local162;
		}
		@Pc(192) int local192;
		@Pc(186) int local186;
		if (local174 <= local146) {
			local186 = 32768;
			local192 = local174 * 65536 / local146;
			while (local40 != local135) {
				if (local40 < local135) {
					local40++;
				} else if (local40 > local135) {
					local40--;
				}
				if ((ClientBuild.mapl[minusedlevel][local33][local40] & 0x4) != 0) {
					method4348(false, local33, local40, World.activeTiles, 1);
					break;
				}
				local186 += local192;
				if (local186 >= 65536) {
					if (local162 > local33) {
						local33++;
					} else if (local162 < local33) {
						local33--;
					}
					local186 -= 65536;
					if ((ClientBuild.mapl[minusedlevel][local33][local40] & 0x4) != 0) {
						method4348(false, local33, local40, World.activeTiles, 1);
						break;
					}
				}
			}
			return;
		}
		local186 = 32768;
		local192 = local146 * 65536 / local174;
		while (local162 != local33) {
			if (local162 > local33) {
				local33++;
			} else if (local33 > local162) {
				local33--;
			}
			if ((ClientBuild.mapl[minusedlevel][local33][local40] & 0x4) != 0) {
				method4348(false, local33, local40, World.activeTiles, 1);
				break;
			}
			local186 += local192;
			if (local186 >= 65536) {
				if (local40 < local135) {
					local40++;
				} else if (local135 < local40) {
					local40--;
				}
				local186 -= 65536;
				if ((ClientBuild.mapl[minusedlevel][local33][local40] & 0x4) != 0) {
					method4348(false, local33, local40, World.activeTiles, 1);
					break;
				}
			}
		}
	}

	@OriginalMember(owner = "client!uj", name = "a", descriptor = "(BZII[[[Lclient!bj;I)Z")
	public static boolean method4348(@OriginalArg(1) boolean arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Square[][][] arg3, @OriginalArg(5) int arg4) {
		@Pc(14) byte local14 = arg0 ? 1 : (byte) (Static136.anInt3325 & 0xFF);
		if (local14 == Static266.aByteArrayArrayArray15[minusedlevel][arg1][arg2]) {
			return false;
		} else if ((ClientBuild.mapl[minusedlevel][arg1][arg2] & 0x4) == 0) {
			return false;
		} else {
			@Pc(47) int local47 = 0;
			@Pc(49) byte local49 = 0;
			anIntArray514[0] = arg1;
			@Pc(69) int local69 = local49 + 1;
			anIntArray209[0] = arg2;
			Static266.aByteArrayArrayArray15[minusedlevel][arg1][arg2] = local14;
			while (local47 != local69) {
				@Pc(94) int local94 = anIntArray514[local47] >> 16 & 0xFF;
				@Pc(102) int local102 = anIntArray514[local47] >> 24 & 0xFF;
				@Pc(108) int local108 = anIntArray514[local47] & 0xFFFF;
				@Pc(116) int local116 = anIntArray209[local47] >> 16 & 0xFF;
				@Pc(122) int local122 = anIntArray209[local47] & 0xFFFF;
				local47 = local47 + 1 & 0xFFF;
				@Pc(130) boolean local130 = false;
				@Pc(132) boolean local132 = false;
				if ((ClientBuild.mapl[minusedlevel][local108][local122] & 0x4) == 0) {
					local130 = true;
				}
				@Pc(150) int local150;
				@Pc(191) int local191;
				label238: for (local150 = minusedlevel + 1; local150 <= 3; local150++) {
					if ((ClientBuild.mapl[local150][local108][local122] & 0x8) == 0) {
						@Pc(227) int local227;
						@Pc(358) int local358;
						if (local130 && arg3[local150][local108][local122] != null) {
							if (arg3[local150][local108][local122].wall != null) {
								local191 = Static104.method2251(local94);
								if (arg3[local150][local108][local122].wall.anInt3049 == local191 || arg3[local150][local108][local122].wall.anInt3052 == local191) {
									continue;
								}
								if (local102 != 0) {
									local227 = Static104.method2251(local102);
									if (local227 == arg3[local150][local108][local122].wall.anInt3049 || arg3[local150][local108][local122].wall.anInt3052 == local227) {
										continue;
									}
								}
								if (local116 != 0) {
									local227 = Static104.method2251(local116);
									if (local227 == arg3[local150][local108][local122].wall.anInt3049 || local227 == arg3[local150][local108][local122].wall.anInt3052) {
										continue;
									}
								}
							}
							if (arg3[local150][local108][local122].sprites != null) {
								for (local191 = 0; local191 < arg3[local150][local108][local122].spriteCount; local191++) {
									local227 = (int) (arg3[local150][local108][local122].sprites[local191].typecode >> 14 & 0x3FL);
									if (local227 == 21) {
										local227 = 19;
									}
									@Pc(352) int local352 = (int) (arg3[local150][local108][local122].sprites[local191].typecode >> 20 & 0x3L);
									local358 = local227 | local352 << 6;
									if (local358 == local94 || local102 != 0 && local358 == local102 || local116 != 0 && local116 == local358) {
										continue label238;
									}
								}
							}
						}
						local132 = true;
						@Pc(395) Square local395 = arg3[local150][local108][local122];
						if (local395 != null && local395.spriteCount > 0) {
							for (local227 = 0; local227 < local395.spriteCount; local227++) {
								@Pc(418) Sprite local418 = local395.sprites[local227];
								if (local418.anInt1713 != local418.anInt1701 || local418.anInt1698 != local418.anInt1696) {
									for (local358 = local418.anInt1701; local358 <= local418.anInt1713; local358++) {
										for (@Pc(450) int local450 = local418.anInt1696; local450 <= local418.anInt1698; local450++) {
											Static266.aByteArrayArrayArray15[local150][local358][local450] = local14;
										}
									}
								}
							}
						}
						Static266.aByteArrayArrayArray15[local150][local108][local122] = local14;
					}
				}
				if (local132) {
					if (World.groundh[minusedlevel + 1][local108][local122] > Static79.anIntArray205[arg4]) {
						Static79.anIntArray205[arg4] = World.groundh[minusedlevel + 1][local108][local122];
					}
					local150 = local108 << 7;
					if (local150 < Static149.anIntArray338[arg4]) {
						Static149.anIntArray338[arg4] = local150;
					} else if (Static267.anIntArray518[arg4] < local150) {
						Static267.anIntArray518[arg4] = local150;
					}
					local191 = local122 << 7;
					if (Static243.anIntArray476[arg4] > local191) {
						Static243.anIntArray476[arg4] = local191;
					} else if (Static50.anIntArray134[arg4] < local191) {
						Static50.anIntArray134[arg4] = local191;
					}
				}
				if (!local130) {
					if (local108 >= 1 && Static266.aByteArrayArrayArray15[minusedlevel][local108 - 1][local122] != local14) {
						anIntArray514[local69] = local108 - 1 | 0x120000 | 0xD3000000;
						anIntArray209[local69] = local122 | 0x130000;
						local69 = local69 + 1 & 0xFFF;
						Static266.aByteArrayArrayArray15[minusedlevel][local108 - 1][local122] = local14;
					}
					local122++;
					if (local122 < 104) {
						if (local108 - 1 >= 0 && local14 != Static266.aByteArrayArrayArray15[minusedlevel][local108 - 1][local122] && (ClientBuild.mapl[minusedlevel][local108][local122] & 0x4) == 0 && (ClientBuild.mapl[minusedlevel][local108 - 1][local122 - 1] & 0x4) == 0) {
							anIntArray514[local69] = 0x52000000 | 0x120000 | local108 - 1;
							anIntArray209[local69] = local122 | 0x130000;
							Static266.aByteArrayArrayArray15[minusedlevel][local108 - 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
						if (local14 != Static266.aByteArrayArrayArray15[minusedlevel][local108][local122]) {
							anIntArray514[local69] = local108 | 0x13000000 | 0x520000;
							anIntArray209[local69] = local122 | 0x530000;
							local69 = local69 + 1 & 0xFFF;
							Static266.aByteArrayArrayArray15[minusedlevel][local108][local122] = local14;
						}
						if (local108 + 1 < 104 && Static266.aByteArrayArrayArray15[minusedlevel][local108 + 1][local122] != local14 && (ClientBuild.mapl[minusedlevel][local108][local122] & 0x4) == 0 && (ClientBuild.mapl[minusedlevel][local108 + 1][local122 - 1] & 0x4) == 0) {
							anIntArray514[local69] = 0x92000000 | 0x520000 | local108 + 1;
							anIntArray209[local69] = local122 | 0x530000;
							Static266.aByteArrayArrayArray15[minusedlevel][local108 + 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
					}
					local122--;
					if (local108 + 1 < 104 && local14 != Static266.aByteArrayArrayArray15[minusedlevel][local108 + 1][local122]) {
						anIntArray514[local69] = local108 + 1 | 0x920000 | 0x53000000;
						anIntArray209[local69] = local122 | 0x930000;
						Static266.aByteArrayArrayArray15[minusedlevel][local108 + 1][local122] = local14;
						local69 = local69 + 1 & 0xFFF;
					}
					local122--;
					if (local122 >= 0) {
						if (local108 - 1 >= 0 && Static266.aByteArrayArrayArray15[minusedlevel][local108 - 1][local122] != local14 && (ClientBuild.mapl[minusedlevel][local108][local122] & 0x4) == 0 && (ClientBuild.mapl[minusedlevel][local108 - 1][local122 + 1] & 0x4) == 0) {
							anIntArray514[local69] = local108 - 1 | 0xD20000 | 0x12000000;
							anIntArray209[local69] = local122 | 0xD30000;
							Static266.aByteArrayArrayArray15[minusedlevel][local108 - 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
						if (local14 != Static266.aByteArrayArrayArray15[minusedlevel][local108][local122]) {
							anIntArray514[local69] = local108 | 0xD20000 | 0x93000000;
							anIntArray209[local69] = local122 | 0xD30000;
							local69 = local69 + 1 & 0xFFF;
							Static266.aByteArrayArrayArray15[minusedlevel][local108][local122] = local14;
						}
						if (local108 + 1 < 104 && Static266.aByteArrayArrayArray15[minusedlevel][local108 + 1][local122] != local14 && (ClientBuild.mapl[minusedlevel][local108][local122] & 0x4) == 0 && (ClientBuild.mapl[minusedlevel][local108 + 1][local122 + 1] & 0x4) == 0) {
							anIntArray514[local69] = local108 + 1 | 0xD2000000 | 0x920000;
							anIntArray209[local69] = local122 | 0x930000;
							Static266.aByteArrayArrayArray15[minusedlevel][local108 + 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
					}
				}
			}
			if (Static79.anIntArray205[arg4] != -1000000) {
				Static79.anIntArray205[arg4] += 10;
				Static149.anIntArray338[arg4] -= 50;
				Static267.anIntArray518[arg4] += 50;
				Static50.anIntArray134[arg4] += 50;
				Static243.anIntArray476[arg4] -= 50;
			}
			return true;
		}
	}

	@OriginalMember(owner = "client!mh", name = "f", descriptor = "(B)V")
	public static void accountCreatePoll() {
		if (accountCreateStep == 0) {
			return;
		}

		try {
			if (++accountCreateWaitingTime > 2000) {
				if (stream != null) {
					stream.close();
					stream = null;
				}

				if (accountCreateFailCount >= 1) {
					accountCreateError = -5;
					accountCreateStep = 0;
					return;
				}

				accountCreateStep = 1;
				accountCreateWaitingTime = 0;
				accountCreateFailCount++;

				if (loginPort == gamePort) {
					loginPort = js5Port;
				} else {
					loginPort = gamePort;
				}
			}

			if (accountCreateStep == 1) {
				socketReq = signlink.socketreq(host, loginPort);
				accountCreateStep = 2;
			}

			@Pc(120) int local120;
			if (accountCreateStep == 2) {
				if (socketReq.status == 2) {
					throw new IOException();
				}

				if (socketReq.status != 1) {
					return;
				}

				stream = new ClientStream((Socket) socketReq.result, signlink);
				socketReq = null;
				stream.write(out.data, out.pos);

				if (midiPcmPlayer != null) {
					midiPcmPlayer.skipNextAcceptedCheck();
				}
				if (soundPcmPlayer != null) {
					soundPcmPlayer.skipNextAcceptedCheck();
				}

				local120 = stream.read();

				if (midiPcmPlayer != null) {
					midiPcmPlayer.skipNextAcceptedCheck();
				}
				if (soundPcmPlayer != null) {
					soundPcmPlayer.skipNextAcceptedCheck();
				}

				if (local120 != 21) {
					accountCreateError = local120;
					accountCreateStep = 0;
					stream.close();
					stream = null;
					return;
				}

				accountCreateStep = 3;
			}

			if (accountCreateStep == 3) {
				if (stream.available() < 1) {
					return;
				}

				Static229.aClass100Array156 = new JagString[stream.read()];
				accountCreateStep = 4;
			}

			if (accountCreateStep == 4) {
				if (stream.available() < Static229.aClass100Array156.length * 8) {
					return;
				}

				in.pos = 0;
				stream.read(0, Static229.aClass100Array156.length * 8, in.data);
				for (local120 = 0; local120 < Static229.aClass100Array156.length; local120++) {
					Static229.aClass100Array156[local120] = Static79.toBaseDisplayName(in.g8());
				}
				accountCreateError = 21;
				accountCreateStep = 0;
				stream.close();
				stream = null;
				return;
			}
		} catch (@Pc(238) IOException ex) {
			if (stream != null) {
				stream.close();
				stream = null;
			}

			if (accountCreateFailCount < 1) {
				accountCreateFailCount++;

				if (gamePort == loginPort) {
					loginPort = js5Port;
				} else {
					loginPort = gamePort;
				}

				accountCreateWaitingTime = 0;
				accountCreateStep = 1;
			} else {
				accountCreateError = -4;
				accountCreateStep = 0;
			}
		}
	}

	@OriginalMember(owner = "client!ch", name = "b", descriptor = "(B)V")
	public static void worldListPoll() {
		if (worldHopStep == 0) {
			return;
		}

		try {
			if (++worldListWaitingTime > 1500) {
				if (stream != null) {
					stream.close();
					stream = null;
				}

				if (worldHopFailCount >= 1) {
					worldHopError = -5;
					worldHopStep = 0;
					return;
				}

				worldListWaitingTime = 0;
				worldHopFailCount++;

				worldHopStep = 1;

				if (worldListPort == worldListGamePort) {
					worldListPort = worldListJs5Port;
				} else {
					worldListPort = worldListGamePort;
				}
			}

			if (worldHopStep == 1) {
				socketReq = signlink.socketreq(worldListHost, worldListPort);
				worldHopStep = 2;
			}

			@Pc(126) int local126;
			if (worldHopStep == 2) {
				if (socketReq.status == 2) {
					throw new IOException();
				}

				if (socketReq.status != 1) {
					return;
				}

				stream = new ClientStream((Socket) socketReq.result, signlink);
				socketReq = null;
				stream.write(out.data, out.pos);

				if (midiPcmPlayer != null) {
					midiPcmPlayer.skipNextAcceptedCheck();
				}
				if (soundPcmPlayer != null) {
					soundPcmPlayer.skipNextAcceptedCheck();
				}

				local126 = stream.read();

				if (midiPcmPlayer != null) {
					midiPcmPlayer.skipNextAcceptedCheck();
				}
				if (soundPcmPlayer != null) {
					soundPcmPlayer.skipNextAcceptedCheck();
				}

				if (local126 != 101) {
					worldHopError = local126;
					worldHopStep = 0;
					stream.close();
					stream = null;
					return;
				}

				worldHopStep = 3;
			}

			if (worldHopStep == 3) {
				if (stream.available() < 2) {
					return;
				}

				local126 = stream.read() << 8 | stream.read();
				Static176.method3303(local126);
				if (anInt3103 == -1) {
					worldHopStep = 0;
					worldHopError = 6;
					stream.close();
					stream = null;
					return;
				}
				worldHopStep = 0;
				stream.close();
				stream = null;
				method1208();
				return;
			}
		} catch (@Pc(210) IOException ex) {
			if (stream != null) {
				stream.close();
				stream = null;
			}

			if (worldHopFailCount < 1) {
				if (worldListPort == worldListGamePort) {
					worldListPort = worldListJs5Port;
				} else {
					worldListPort = worldListGamePort;
				}

				worldHopStep = 1;
				worldListWaitingTime = 0;
				worldHopFailCount++;
			} else {
				worldHopError = -4;
				worldHopStep = 0;
			}
		}
	}

	@OriginalMember(owner = "client!nm", name = "a", descriptor = "(Z)V")
	public static void lostCon() {
		if (logoutTimer > 0) {
			logout();
			return;
		}

        lastStream = stream;
        stream = null;
        setMainState(40);
    }

	@OriginalMember(owner = "client!dh", name = "a", descriptor = "(Z)V")
	public static void reconnectDone() {
		out.pos = 0;
		in.pos = 0;
		ptype = -1;
		ptype0 = -1;
		ptype1 = -1;
		ptype2 = -1;
		psize = 0;

		anInt2939 = 0;
		menuNumEntries = 0;
		anInt5795 = 0;
		isMenuOpen = false;

		rebootTimer = 0;
		timeoutTimer = 0;

		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				players[i].targetId = -1;
			}
		}

		for (int i = 0; i < npcs.length; i++) {
			if (npcs[i] != null) {
				npcs[i].targetId = -1;
			}
		}

		ClientInvCache.deleteAll();

		anInt5096 = 1;
		setMainState(30);

		for (int i = 0; i < 100; i++) {
			componentRedrawRequested1[i] = true;
		}

		method1373();
	}

	@OriginalMember(owner = "client!dh", name = "a", descriptor = "(IIII)Lclient!wk;")
	public static SubInterface method1148(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(9) SubInterface local9 = new SubInterface();
		local9.anInt5879 = arg2;
		local9.id = arg0;
		aClass133_9.put(local9, (long) arg1);
		ifAnimReset(arg0);
		@Pc(28) IfType local28 = IfType.get(arg1);
		if (local28 != null) {
			componentUpdated(local28);
		}
		if (Static39.aClass13_10 != null) {
			componentUpdated(Static39.aClass13_10);
			Static39.aClass13_10 = null;
		}
		@Pc(45) int local45 = menuNumEntries;
		@Pc(53) int local53;
		for (local53 = 0; local53 < local45; local53++) {
			if (Static2.method5(menuAction[local53])) {
				method3628(local53);
			}
		}
		if (menuNumEntries == 1) {
			isMenuOpen = false;
			dirtyArea(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
		} else {
			dirtyArea(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
			local53 = Static280.aClass3_Sub2_Sub9_43.method2858(Text.aClass100_998);
			for (@Pc(95) int local95 = 0; local95 < menuNumEntries; local95++) {
				@Pc(104) int local104 = Static280.aClass3_Sub2_Sub9_43.method2858(Static269.method2228(local95));
				if (local104 > local53) {
					local53 = local104;
				}
			}
			Static24.anInt761 = local53 + 8;
			Static13.anInt436 = menuNumEntries * 15 + (Static261.aBoolean298 ? 26 : 22);
		}
		if (local28 != null) {
			method531(local28, false);
		}
		ScriptRunner.method1626(arg0);
		if (toplevelinterface != -1) {
			runHookImmediate(1, toplevelinterface);
		}
		return local9;
	}

	@OriginalMember(owner = "client!gd", name = "c", descriptor = "(I)V")
	public static void mapBuildLoop() {
		preventTimeout(false);
		mapLoadCount = 0;

		@Pc(12) boolean local12 = true;
		@Pc(14) int local14;
		for (local14 = 0; local14 < aByteArrayArray13.length; local14++) {
			if (mapBuildGroundFile[local14] != -1 && aByteArrayArray13[local14] == null) {
				aByteArrayArray13[local14] = maps.getFile(mapBuildGroundFile[local14], 0);
				if (aByteArrayArray13[local14] == null) {
					mapLoadCount++;
					local12 = false;
				}
			}
			if (mapBuildLocationFile[local14] != -1 && aByteArrayArray11[local14] == null) {
				aByteArrayArray11[local14] = maps.method4488(mapBuildLocationFile[local14], anIntArrayArray14[local14], 0);
				if (aByteArrayArray11[local14] == null) {
					local12 = false;
					mapLoadCount++;
				}
			}
			if (glRenderer) {
				if (mapBuildUnderGroundFile[local14] != -1 && aByteArrayArray14[local14] == null) {
					aByteArrayArray14[local14] = maps.getFile(mapBuildUnderGroundFile[local14], 0);
					if (aByteArrayArray14[local14] == null) {
						local12 = false;
						mapLoadCount++;
					}
				}
				if (mapBuildUnderGroundLocationFile[local14] != -1 && aByteArrayArray4[local14] == null) {
					aByteArrayArray4[local14] = maps.getFile(mapBuildUnderGroundLocationFile[local14], 0);
					if (aByteArrayArray4[local14] == null) {
						mapLoadCount++;
						local12 = false;
					}
				}
			}
			if (mapBuildNpcFile != null && aByteArrayArray15[local14] == null && mapBuildNpcFile[local14] != -1) {
				aByteArrayArray15[local14] = maps.method4488(mapBuildNpcFile[local14], anIntArrayArray14[local14], 0);
				if (aByteArrayArray15[local14] == null) {
					mapLoadCount++;
					local12 = false;
				}
			}
		}
		if (WorldMap.aClass134_2 == null) {
			if (WorldMap.aClass3_Sub2_Sub4_3 == null || !worldmap.method4497(JagString.join(new JagString[] { WorldMap.aClass3_Sub2_Sub4_3.aClass100_138, Static50.aClass100_363 }))) {
				WorldMap.aClass134_2 = new MapElementList(0);
			} else if (worldmap.method4489(JagString.join(new JagString[] { WorldMap.aClass3_Sub2_Sub4_3.aClass100_138, Static50.aClass100_363 }))) {
				WorldMap.aClass134_2 = Static140.method2711(JagString.join(new JagString[] { WorldMap.aClass3_Sub2_Sub4_3.aClass100_138, Static50.aClass100_363 }), worldmap);
			} else {
				local12 = false;
				mapLoadCount++;
			}
		}
		if (!local12) {
			mapLoadingStage = 1;
			return;
		}

		locModelLoadCount = 0;
		local12 = true;
		@Pc(320) int local320;
		@Pc(309) int local309;
		for (local14 = 0; local14 < aByteArrayArray13.length; local14++) {
			@Pc(294) byte[] local294 = aByteArrayArray11[local14];
			if (local294 != null) {
				local309 = (mapBuildIndex[local14] & 0xFF) * 64 - mapBuildBaseZ;
				local320 = (mapBuildIndex[local14] >> 8) * 64 - mapBuildBaseX;
				if (regionmode) {
					local309 = 10;
					local320 = 10;
				}
				local12 &= ClientBuild.checkLocations(local320, local309, local294);
			}
			if (glRenderer) {
				local294 = aByteArrayArray4[local14];
				if (local294 != null) {
					local320 = (mapBuildIndex[local14] >> 8) * 64 - mapBuildBaseX;
					local309 = (mapBuildIndex[local14] & 0xFF) * 64 - mapBuildBaseZ;
					if (regionmode) {
						local309 = 10;
						local320 = 10;
					}
					local12 &= ClientBuild.checkLocations(local320, local309, local294);
				}
			}
		}
		if (!local12) {
			mapLoadingStage = 2;
			return;
		}

		if (mapLoadingStage != 0) {
			messageBox(true, JagString.join(new JagString[] { Text.aClass100_621, Static18.aClass100_108 }));
		}

		doAudio();
		clearCaches();

		@Pc(420) boolean underwater = false;
		@Pc(427) int local427;
		if (glRenderer && Static220.aBoolean244) {
			for (local427 = 0; local427 < aByteArrayArray13.length; local427++) {
				if (aByteArrayArray4[local427] != null || aByteArrayArray14[local427] != null) {
					underwater = true;
					break;
				}
			}
		}
		World.resetMap(glRenderer ? 28 : 25, underwater);

		for (local427 = 0; local427 < 4; local427++) {
			levelCollisionMap[local427].reset();
		}

		for (local427 = 0; local427 < 4; local427++) {
			for (local320 = 0; local320 < 104; local320++) {
				for (local309 = 0; local309 < 104; local309++) {
					ClientBuild.mapl[local427][local320][local309] = 0;
				}
			}
		}

		BgSound.reset(false);

		if (glRenderer) {
			Static242.aClass36_Sub1_4.method1392();
			for (local427 = 0; local427 < 13; local427++) {
				for (local320 = 0; local320 < 13; local320++) {
					Static242.aClass165ArrayArray1[local427][local320].aBoolean313 = true;
				}
			}
		}

		if (glRenderer) {
			World.method2404();
		}

		if (glRenderer) {
			Static75.method1632();
		}

		doAudio();
		System.gc();

		preventTimeout(true);
		ClientBuild.init(false);

		if (!regionmode) {
			method1805(false);

			preventTimeout(true);
			if (glRenderer) {
				local427 = localPlayer.routeX[0] >> 3;
				local320 = localPlayer.routeZ[0] >> 3;
				ClientBuild.loadGround(local320, local427);
			}

			method743(false);

			if (aByteArrayArray15 != null) {
				method3013();
			}
		}

		if (regionmode) {
			method1835(false);

			preventTimeout(true);
			if (glRenderer) {
				local427 = localPlayer.routeX[0] >> 3;
				local320 = localPlayer.routeZ[0] >> 3;
				ClientBuild.loadGround(local320, local427);
			}

			method4002(false);
		}

		clearCaches();

		preventTimeout(true);
		ClientBuild.finishBuild(levelCollisionMap, false);

		if (glRenderer) {
			World.method2395();
		}

		preventTimeout(true);
		local427 = ClientBuild.minusedlevel;
		if (local427 > minusedlevel) {
			local427 = minusedlevel;
		}
		if (local427 < minusedlevel - 1) {
		}

		if (highDetail()) {
			World.fillBaseLevel(0);
		} else {
			World.fillBaseLevel(ClientBuild.minusedlevel);
		}

		ClientBuild.quit();

		if (glRenderer && underwater) {
			World.setActiveTiles(true);
			ClientBuild.init(true);

			if (!regionmode) {
				method1805(true);
				preventTimeout(true);
				method743(true);
			}

			if (regionmode) {
				method1835(true);
				preventTimeout(true);
				method4002(true);
			}

			clearCaches();

			preventTimeout(true);
			ClientBuild.finishBuild(levelCollisionMap, true);

			preventTimeout(true);
			ClientBuild.quit();

			World.setActiveTiles(false);
		}

		if (glRenderer) {
			for (local320 = 0; local320 < 13; local320++) {
				for (local309 = 0; local309 < 13; local309++) {
					Static242.aClass165ArrayArray1[local320][local309].method4676(World.groundh[0], local320 * 8, local309 * 8);
				}
			}
		}

		for (int x = 0; x < 104; x++) {
			for (int z = 0; z < 104; z++) {
				showObject(z, x);
			}
		}

		Static269.method2218();

		doAudio();
		locChangePostBuildCorrect();

		clearCaches();
		Static231.aBoolean252 = false;

		if (frame != null && stream != null && state == 25) {
			out.p1Enc(20);
			out.p4(1057001181);
		}

		if (!regionmode) {
			@Pc(815) int local815 = (mapBuildCenterZoneX + 6) / 8;
			@Pc(821) int local821 = (mapBuildCenterZoneX - 6) / 8;
			local320 = (mapBuildCenterZoneZ - 6) / 8;
			local309 = (mapBuildCenterZoneZ + 6) / 8;

			for (@Pc(837) int local837 = local320 - 1; local837 <= local309 + 1; local837++) {
				for (@Pc(850) int local850 = local821 - 1; local850 <= local815 + 1; local850++) {
					if (local837 < local320 || local837 > local309 || local850 < local821 || local850 > local815) {
						maps.updateCacheHint(JagString.join(new JagString[] { AUTO_M, JagString.parseInt(local837), AUTO_UNDERSCORE, JagString.parseInt(local850) }));
						maps.updateCacheHint(JagString.join(new JagString[] { AUTO_L, JagString.parseInt(local837), AUTO_UNDERSCORE, JagString.parseInt(local850) }));
					}
				}
			}
		}

		if (state == 28) {
			setMainState(10);
		} else {
			setMainState(30);

			if (stream != null) {
				// MAP_BUILD_COMPLETE
				out.p1Enc(110);
			}
		}

		Static141.method2720();

		doAudio();
		doneslowupdate();
	}

	@OriginalMember(owner = "client!gd", name = "a", descriptor = "(JI)V")
	public static void method1691(@OriginalArg(0) long arg0) {
		out.pos = 0;
		out.p1(186);
		if (GlobalConfig.ARIOS_LOGIN_STRINGS) {
			out.pjstr(Static79.toBaseDisplayName(arg0));
		} else {
			out.p8(arg0);
		}
		accountCreateStep = 1;
		accountCreateWaitingTime = 0;
		accountCreateFailCount = 0;
		accountCreateError = -3;
	}

	@OriginalMember(owner = "client!ud", name = "a", descriptor = "(ILclient!be;)Z")
	public static boolean clientButton(@OriginalArg(1) IfType com) {
		if (com.clientCode == 205) {
			logoutTimer = 250;
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ch", name = "c", descriptor = "(I)V")
	public static void checkMinimap() {
		if (!highDetail() && Static41.anInt1316 != minusedlevel) {
			method2463(minusedlevel, mapBuildCenterZoneX, mapBuildCenterZoneZ, localPlayer.routeZ[0], false, localPlayer.routeX[0]);
		} else if (minusedlevel != Static107.anInt2875 && ClientBuild.method2665(minusedlevel)) {
			Static107.anInt2875 = minusedlevel;
			Static269.method2218();
		}
	}

	@OriginalMember(owner = "client!ed", name = "d", descriptor = "(B)V")
	public static void soundsDoQueue() {
		for (@Pc(5) int local5 = 0; local5 < waveCount; local5++) {
			@Pc(12) int local12 = waveDelay[local5]--;
			if (waveDelay[local5] >= -10) {
				@Pc(79) JagFX local79 = waveSounds[local5];
				if (local79 == null) {
					local79 = JagFX.load(jagFX, waveSoundIds[local5], 0);
					if (local79 == null) {
						continue;
					}
					waveDelay[local5] += local79.optimiseStart();
					waveSounds[local5] = local79;
				}
				if (waveDelay[local5] < 0) {
					@Pc(209) int local209;
					if (waveAmbient[local5] == 0) {
						local209 = waveVolume;
					} else {
						@Pc(125) int local125 = (waveAmbient[local5] & 0xFF) * 128;
						@Pc(133) int local133 = waveAmbient[local5] >> 8 & 0xFF;
						@Pc(141) int local141 = waveAmbient[local5] >> 16 & 0xFF;
						@Pc(151) int local151 = local133 * 128 + 64 - localPlayer.z;
						if (local151 < 0) {
							local151 = -local151;
						}
						@Pc(167) int local167 = local141 * 128 + 64 - localPlayer.x;
						if (local167 < 0) {
							local167 = -local167;
						}
						@Pc(180) int local180 = local167 + local151 - 128;
						if (local125 < local180) {
							waveDelay[local5] = -100;
							continue;
						}
						if (local180 < 0) {
							local180 = 0;
						}
						local209 = ambientVolume * (local125 - local180) / local125;
					}
					if (local209 > 0) {
						@Pc(223) Wave local223 = local79.toWave().decimate(soundDecimator);
						@Pc(228) WaveStream local228 = WaveStream.newRatePercent(local223, local209);
						local228.setLoopCount(anIntArray563[local5] - 1);
						soundMixer.playStream(local228);
					}
					waveDelay[local5] = -100;
				}
			} else {
				waveCount--;
				for (@Pc(28) int local28 = local5; local28 < waveCount; local28++) {
					waveSoundIds[local28] = waveSoundIds[local28 + 1];
					waveSounds[local28] = waveSounds[local28 + 1];
					anIntArray563[local28] = anIntArray563[local28 + 1];
					waveDelay[local28] = waveDelay[local28 + 1];
					waveAmbient[local28] = waveAmbient[local28 + 1];
				}
				local5--;
			}
		}
		if (aBoolean173 && !MidiManager.isInitialised()) {
			if (midiVolume != 0 && anInt4363 != -1) {
				MidiManager.play(songs, anInt4363, midiVolume);
			}
			aBoolean173 = false;
		} else if (midiVolume != 0 && anInt4363 != -1 && !MidiManager.isInitialised()) {
			out.p1Enc(137);
			out.p4(anInt4363);
			anInt4363 = -1;
		}
	}

	@OriginalMember(owner = "client!ca", name = "h", descriptor = "(I)V")
	public static void method740() {
		@Pc(10) int zx = (anInt3439 >> 10) + (mapBuildBaseX >> 3);
		@Pc(23) int zz = (anInt3302 >> 10) + (mapBuildBaseZ >> 3);

		aByteArrayArray11 = new byte[18][];
		mapBuildUnderGroundLocationFile = new int[18];
		aByteArrayArray15 = new byte[18][];
		mapBuildGroundFile = new int[18];
		anIntArrayArray14 = new int[18][4];
		aByteArrayArray14 = new byte[18][];
		mapBuildIndex = new int[18];
		aByteArrayArray13 = new byte[18][];
		mapBuildNpcFile = new int[18];
		mapBuildUnderGroundFile = new int[18];
		mapBuildLocationFile = new int[18];
		aByteArrayArray4 = new byte[18][];

		@Pc(74) int mapCount = 0;
		for (int mx = (zx - 6) / 8; mx <= (zx + 6) / 8; mx++) {
			for (@Pc(97) int mz = (zz - 6) / 8; mz <= (zz + 6) / 8; mz++) {
				@Pc(115) int index = (mx << 8) + mz;
				mapBuildIndex[mapCount] = index;

				mapBuildGroundFile[mapCount] = maps.getGroupId(JagString.join(new JagString[] { AUTO_M, JagString.parseInt(mx), AUTO_UNDERSCORE, JagString.parseInt(mz) }));
				mapBuildLocationFile[mapCount] = maps.getGroupId(JagString.join(new JagString[] { AUTO_L, JagString.parseInt(mx), AUTO_UNDERSCORE, JagString.parseInt(mz) }));
				mapBuildNpcFile[mapCount] = maps.getGroupId(JagString.join(new JagString[] { AUTO_N, JagString.parseInt(mx), AUTO_UNDERSCORE, JagString.parseInt(mz) }));
				mapBuildUnderGroundFile[mapCount] = maps.getGroupId(JagString.join(new JagString[] { AUTO_UM, JagString.parseInt(mx), AUTO_UNDERSCORE, JagString.parseInt(mz) }));
				mapBuildUnderGroundLocationFile[mapCount] = maps.getGroupId(JagString.join(new JagString[] { AUTO_UL, JagString.parseInt(mx), AUTO_UNDERSCORE, JagString.parseInt(mz) }));

				if (mapBuildNpcFile[mapCount] == -1) {
					mapBuildGroundFile[mapCount] = -1;
					mapBuildLocationFile[mapCount] = -1;
					mapBuildUnderGroundFile[mapCount] = -1;
					mapBuildUnderGroundLocationFile[mapCount] = -1;
				}

				mapCount++;
			}
		}

		for (int i = mapCount; i < mapBuildNpcFile.length; i++) {
			mapBuildNpcFile[i] = -1;
			mapBuildGroundFile[i] = -1;
			mapBuildLocationFile[i] = -1;
			mapBuildUnderGroundFile[i] = -1;
			mapBuildUnderGroundLocationFile[i] = -1;
		}

		method2463(0, zz, zx, 8, true, 8);
	}

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(ZI)V")
	public static void method743(@OriginalArg(0) boolean arg0) {
		@Pc(13) int local13 = aByteArrayArray13.length;
		@Pc(19) byte[][] local19;
		if (glRenderer && arg0) {
			local19 = aByteArrayArray4;
		} else {
			local19 = aByteArrayArray11;
		}
		for (@Pc(25) int local25 = 0; local25 < local13; local25++) {
			@Pc(32) byte[] local32 = local19[local25];
			if (local32 != null) {
				@Pc(45) int local45 = (mapBuildIndex[local25] >> 8) * 64 - mapBuildBaseX;
				@Pc(56) int local56 = (mapBuildIndex[local25] & 0xFF) * 64 - mapBuildBaseZ;
				doAudio();
				ClientBuild.method2437(local45, arg0, local32, local56, levelCollisionMap);
			}
		}
	}

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(IIII)V")
	public static void method744(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (waveVolume == 0 || arg0 == 0 || waveCount >= 50 || arg1 == -1) {
			return;
		}
		waveSoundIds[waveCount] = arg1;
		anIntArray563[waveCount] = arg0;
		waveDelay[waveCount] = arg2;
		waveSounds[waveCount] = null;
		waveAmbient[waveCount] = 0;
		waveCount++;
	}

	@OriginalMember(owner = "client!ph", name = "a", descriptor = "(B)V")
	public static void method3473() {
		while (true) {
			@Pc(17) LocMerge local17 = (LocMerge) aClass69_77.popFront();
			if (local17 == null) {
				return;
			}
			@Pc(40) ClientEntity local40;
			@Pc(29) int local29;
			if (local17.anInt1192 < 0) {
				local29 = -local17.anInt1192 - 1;
				if (local29 == anInt549) {
					local40 = localPlayer;
				} else {
					local40 = players[local29];
				}
			} else {
				local29 = local17.anInt1192 - 1;
				local40 = npcs[local29];
			}
			if (local40 != null) {
				@Pc(63) LocType local63 = LocType.list(local17.anInt1189);
				if (minusedlevel < 3) {
				}
				@Pc(86) int local86;
				@Pc(83) int local83;
				if (local17.anInt1206 == 1 || local17.anInt1206 == 3) {
					local83 = local63.anInt4397;
					local86 = local63.anInt4403;
				} else {
					local86 = local63.anInt4397;
					local83 = local63.anInt4403;
				}
				@Pc(103) int local103 = (local86 + 1 >> 1) + local17.anInt1190;
				@Pc(110) int local110 = (local86 >> 1) + local17.anInt1190;
				@Pc(117) int local117 = (local83 >> 1) + local17.anInt1204;
				@Pc(126) int local126 = (local83 + 1 >> 1) + local17.anInt1204;
				@Pc(130) int[][] local130 = World.groundh[minusedlevel];
				@Pc(157) int local157 = local130[local103][local126] + local130[local110][local126] + local130[local110][local117] + local130[local103][local117] >> 2;
				@Pc(159) ModelSource local159 = null;
				@Pc(164) int local164 = Static133.anIntArray453[local17.anInt1198];
				if (local164 == 0) {
					@Pc(176) Wall local176 = World.method4509(minusedlevel, local17.anInt1190, local17.anInt1204);
					if (local176 != null) {
						local159 = local176.aClass8_5;
					}
				} else if (local164 == 1) {
					@Pc(231) Decor local231 = World.getDecor(minusedlevel, local17.anInt1190, local17.anInt1204);
					if (local231 != null) {
						local159 = local231.aClass8_3;
					}
				} else if (local164 == 2) {
					@Pc(198) Sprite local198 = World.getScene(minusedlevel, local17.anInt1190, local17.anInt1204);
					if (local198 != null) {
						local159 = local198.model;
					}
				} else if (local164 == 3) {
					@Pc(216) GroundDecor local216 = World.method2210(minusedlevel, local17.anInt1190, local17.anInt1204);
					if (local216 != null) {
						local159 = local216.aClass8_1;
					}
				}
				if (local159 != null) {
					locChangeCreate(minusedlevel, local17.anInt1204, 0, local17.anInt1190, local17.anInt1205 + 1, -1, local164, 0, local17.anInt1187 + 1);
					local40.anInt3375 = local17.anInt1205 + loopCycle;
					local40.anInt3426 = local83 * 64 + local17.anInt1204 * 128;
					local40.anInt3379 = local86 * 64 + local17.anInt1190 * 128;
					local40.anObject5 = local159;
					@Pc(292) int local292 = local17.anInt1188;
					local40.anInt3406 = local157;
					local40.anInt3390 = loopCycle + local17.anInt1187;
					@Pc(304) int local304 = local17.anInt1191;
					@Pc(307) int local307 = local17.anInt1197;
					@Pc(310) int local310 = local17.anInt1200;
					@Pc(316) int local316;
					if (local292 > local304) {
						local316 = local292;
						local292 = local304;
						local304 = local316;
					}
					local40.anInt3411 = local17.anInt1190 + local304;
					if (local307 > local310) {
						local316 = local307;
						local307 = local310;
						local310 = local316;
					}
					local40.anInt3374 = local17.anInt1204 + local307;
					local40.anInt3410 = local310 + local17.anInt1204;
					local40.anInt3384 = local17.anInt1190 + local292;
				}
			}
		}
	}

	@OriginalMember(owner = "client!fb", name = "b", descriptor = "(B)V")
	public static void getPlayerPos() {
		entityUpdateCount = 0;
		entityRemovalCount = 0;

		getPlayerPosLocal();
		getPlayerPosOldVis();
		getPlayerPosNewVis();
		getPlayerPosExtended();

		for (int i = 0; i < entityRemovalCount; i++) {
			@Pc(30) int local30 = entityRemovalIds[i];
			if (loopCycle != players[local30].cycle) {
				if (players[local30].anInt1664 > 0) {
					BgSound.method4597(players[local30]);
				}

				players[local30] = null;
			}
		}

		if (psize != in.pos) {
			throw new RuntimeException("gpp1 pos:" + in.pos + " psize:" + psize);
		}

		for (int i = 0; i < playerCount; i++) {
			if (players[playerIds[i]] == null) {
				throw new RuntimeException("gpp2 pos:" + i + " size:" + playerCount);
			}
		}
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(B)V")
	public static void getPlayerPosLocal() {
		in.gBitStart();
		@Pc(11) int local11 = in.method2238(1);
		if (local11 == 0) {
			return;
		}
		@Pc(23) int local23 = in.method2238(2);
		if (local23 == 0) {
			entityUpdateIds[entityUpdateCount++] = 2047;
			return;
		}
		@Pc(54) int local54;
		@Pc(64) int local64;
		if (local23 == 1) {
			local54 = in.method2238(3);
			localPlayer.method2684(1, local54);
			local64 = in.method2238(1);
			if (local64 == 1) {
				entityUpdateIds[entityUpdateCount++] = 2047;
			}
		} else if (local23 == 2) {
			if (in.method2238(1) == 1) {
				local54 = in.method2238(3);
				localPlayer.method2684(2, local54);
				local64 = in.method2238(3);
				localPlayer.method2684(2, local64);
			} else {
				local54 = in.method2238(3);
				localPlayer.method2684(0, local54);
			}
			local54 = in.method2238(1);
			if (local54 == 1) {
				entityUpdateIds[entityUpdateCount++] = 2047;
			}
		} else if (local23 == 3) {
			local54 = in.method2238(7);
			local64 = in.method2238(1);
			minusedlevel = in.method2238(2);
			@Pc(163) int local163 = in.method2238(1);
			if (local163 == 1) {
				entityUpdateIds[entityUpdateCount++] = 2047;
			}
			@Pc(181) int local181 = in.method2238(7);
			localPlayer.method1265(local181, local64 == 1, local54);
		}
	}

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(I)V")
	public static void getPlayerPosNewVis() {
		while (true) {
			if (in.bitsLeft(psize) >= 11) {
				@Pc(20) int local20 = in.method2238(11);
				if (local20 != 2047) {
					@Pc(27) boolean local27 = false;
					if (players[local20] == null) {
						players[local20] = new ClientPlayer();
						local27 = true;
						if (Static115.aClass3_Sub15Array1[local20] != null) {
							players[local20].method1262(Static115.aClass3_Sub15Array1[local20]);
						}
					}
					playerIds[playerCount++] = local20;
					@Pc(65) ClientPlayer local65 = players[local20];
					local65.cycle = loopCycle;
					@Pc(73) int local73 = in.method2238(1);
					if (local73 == 1) {
						entityUpdateIds[entityUpdateCount++] = local20;
					}
					@Pc(92) int local92 = in.method2238(5);
					@Pc(99) int local99 = Static56.anIntArray141[in.method2238(3)];
					if (local92 > 15) {
						local92 -= 32;
					}
					if (local27) {
						local65.anInt3400 = local65.anInt3381 = local99;
					}
					@Pc(116) int local116 = in.method2238(1);
					@Pc(121) int local121 = in.method2238(5);
					if (local121 > 15) {
						local121 -= 32;
					}
					local65.method1265(local92 + localPlayer.routeX[0], local116 == 1, localPlayer.routeZ[0] + local121);
					continue;
				}
			}
			in.gBitEnd();
			return;
		}
	}

	@OriginalMember(owner = "client!tm", name = "a", descriptor = "(I)V")
	public static void getPlayerPosExtended() {
		for (@Pc(7) int local7 = 0; local7 < entityUpdateCount; local7++) {
			@Pc(31) int local31 = entityUpdateIds[local7];
			@Pc(35) ClientPlayer local35 = players[local31];
			@Pc(39) int local39 = in.g1();
			if ((local39 & 0x10) != 0) {
				local39 += in.g1() << 8;
			}
			getPlayerPosExtended(local39, local31, local35);
		}
	}

	@OriginalMember(owner = "client!gk", name = "a", descriptor = "(IIBLclient!e;)V")
	public static void getPlayerPosExtended(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) ClientPlayer arg2) {
		@Pc(13) int local13;
		@Pc(17) int local17;
		@Pc(24) int local24;
		if ((arg0 & 0x80) != 0) {
			local13 = in.method2192();
			local17 = in.g1();
			@Pc(21) int local21 = in.g1();
			local24 = in.pos;
			@Pc(35) boolean local35 = (local13 & 0x8000) != 0;
			if (arg2.name != null && arg2.aClass59_1 != null) {
				@Pc(48) long local48 = arg2.name.method3158();
				@Pc(50) boolean local50 = false;
				if (local17 <= 1) {
					if (!local35 && (aBoolean157 && !aBoolean236 || aBoolean129)) {
						local50 = true;
					} else {
						for (@Pc(69) int local69 = 0; local69 < Static35.anInt1093; local69++) {
							if (Static190.aLongArray6[local69] == local48) {
								local50 = true;
								break;
							}
						}
					}
				}
				if (!local50 && overrideChat == 0) {
					Static270.aClass3_Sub15_9.pos = 0;
					in.method2200(local21, Static270.aClass3_Sub15_9.data);
					Static270.aClass3_Sub15_9.pos = 0;
					@Pc(106) int local106 = -1;
					@Pc(127) JagString local127;
					if (local35) {
						@Pc(112) QuickChatPhrase local112 = QuickChatPhrase.method3568(Static270.aClass3_Sub15_9);
						local13 &= 0x7FFF;
						local106 = local112.anInt439;
						local127 = local112.aClass3_Sub2_Sub6_1.method770(Static270.aClass3_Sub15_9);
					} else {
						local127 = Static218.method2862(Static65.method1497(Static270.aClass3_Sub15_9).method3116());
					}
					arg2.aClass100_640 = local127.method3144();
					arg2.anInt3352 = local13 & 0xFF;
					arg2.anInt3408 = 150;
					arg2.anInt3429 = local13 >> 8;
					if (local17 == 2) {
						method2928(local106, local35 ? 17 : 1, local127, null, JagString.join(new JagString[] { Static44.aClass100_336, arg2.method1264() }));
					} else if (local17 == 1) {
						method2928(local106, local35 ? 17 : 1, local127, null, JagString.join(new JagString[] {aClass100_435, arg2.method1264() }));
					} else {
						method2928(local106, local35 ? 17 : 2, local127, null, arg2.method1264());
					}
				}
			}
			in.pos = local24 + local21;
		}
		if ((arg0 & 0x1) != 0) {
			local13 = in.method2204();
			local17 = in.method2177();
			arg2.method2686(local17, loopCycle, local13);
			arg2.anInt3378 = loopCycle + 300;
			arg2.anInt3372 = in.method2180();
		}
		if ((arg0 & 0x8) != 0) {
			local13 = in.g2();
			if (local13 == 65535) {
				local13 = -1;
			}
			local17 = in.g1();
			triggerPlayerAnim(local17, local13, arg2);
		}
		if ((arg0 & 0x4) != 0) {
			local13 = in.method2177();
			@Pc(309) byte[] local309 = new byte[local13];
			@Pc(314) Packet local314 = new Packet(local309);
			in.gdata(local13, local309);
			Static115.aClass3_Sub15Array1[arg1] = local314;
			arg2.method1262(local314);
		}
		if ((arg0 & 0x2) != 0) {
			arg2.targetId = in.method2184();
			if (arg2.targetId == 65535) {
				arg2.targetId = -1;
			}
		}
		if ((arg0 & 0x400) != 0) {
			arg2.anInt3380 = in.method2212();
			arg2.anInt3428 = in.g1();
			arg2.anInt3416 = in.method2177();
			arg2.anInt3392 = in.g1();
			arg2.anInt3395 = in.method2192() + loopCycle;
			arg2.anInt3386 = in.method2192() + loopCycle;
			arg2.anInt3431 = in.method2212();
			arg2.anInt3409 = 1;
			arg2.anInt3405 = 0;
		}
		if ((arg0 & 0x20) != 0) {
			arg2.aClass100_640 = in.gjstr();
			if (arg2.aClass100_640.method3149(0) == 126) {
				arg2.aClass100_640 = arg2.aClass100_640.method3136(1);
				addChat(arg2.method1264(), 2, arg2.aClass100_640);
			} else if (arg2 == localPlayer) {
				addChat(arg2.method1264(), 2, arg2.aClass100_640);
			}
			arg2.anInt3352 = 0;
			arg2.anInt3429 = 0;
			arg2.anInt3408 = 150;
		}
		if ((arg0 & 0x200) != 0) {
			local13 = in.method2204();
			local17 = in.method2180();
			arg2.method2686(local17, loopCycle, local13);
		}
		if ((arg0 & 0x800) != 0) {
			local13 = in.method2212();
			@Pc(502) int[] local502 = new int[local13];
			@Pc(505) int[] local505 = new int[local13];
			@Pc(508) int[] local508 = new int[local13];
			for (@Pc(510) int local510 = 0; local510 < local13; local510++) {
				@Pc(521) int local521 = in.method2192();
				if (local521 == 65535) {
					local521 = -1;
				}
				local502[local510] = local521;
				local505[local510] = in.method2177();
				local508[local510] = in.g2();
			}
			Static32.method865(local505, local502, arg2, local508);
		}
		if ((arg0 & 0x100) != 0) {
			local13 = in.method2192();
			if (local13 == 65535) {
				local13 = -1;
			}
			local17 = in.method2224();
			@Pc(573) boolean local573 = true;
			if (local13 != -1 && arg2.anInt3432 != -1 && SeqType.list(SpotType.list(local13).anim).priority < SeqType.list(SpotType.list(arg2.anInt3432).anim).priority) {
				local573 = false;
			}
			if (local573) {
				arg2.anInt3359 = (local17 & 0xFFFF) + loopCycle;
				arg2.anInt3361 = 0;
				arg2.anInt3399 = 0;
				arg2.anInt3432 = local13;
				if (arg2.anInt3359 > loopCycle) {
					arg2.anInt3399 = -1;
				}
				arg2.anInt3394 = local17 >> 16;
				arg2.anInt3418 = 1;
				if (arg2.anInt3432 != -1 && loopCycle == arg2.anInt3359) {
					local24 = SpotType.list(arg2.anInt3432).anim;
					if (local24 != -1) {
						@Pc(663) SeqType local663 = SeqType.list(local24);
						if (local663 != null && local663.frames != null) {
							method2836(arg2.z, local663, arg2.x, arg2 == localPlayer, 0);
						}
					}
				}
			}
		}
		if ((arg0 & 0x40) != 0) {
			arg2.anInt3382 = in.g2();
			arg2.anInt3363 = in.method2207();
		}
	}

	@OriginalMember(owner = "client!fk", name = "b", descriptor = "(I)V")
	public static void movePlayers() {
		for (@Pc(7) int local7 = -1; local7 < playerCount; local7++) {
			@Pc(21) int local21;
			if (local7 == -1) {
				local21 = 2047;
			} else {
				local21 = playerIds[local7];
			}
			@Pc(31) ClientPlayer local31 = players[local21];
			if (local31 != null) {
				method4514(local31.method2693(), local31);
			}
		}
	}

	@OriginalMember(owner = "client!ig", name = "a", descriptor = "(I)V")
	public static void moveNpcs() {
		for (@Pc(7) int local7 = 0; local7 < npcCount; local7++) {
			@Pc(18) int local18 = npcIds[local7];
			@Pc(22) ClientNPC local22 = npcs[local18];
			if (local22 != null) {
				method4514(local22.aClass96_1.anInt3713, local22);
			}
		}
	}

	@OriginalMember(owner = "client!bi", name = "f", descriptor = "(B)V")
	public static void timeoutChat() {
		@Pc(11) int local11;
		for (local11 = -1; local11 < playerCount; local11++) {
			@Pc(22) int local22;
			if (local11 == -1) {
				local22 = 2047;
			} else {
				local22 = playerIds[local11];
			}
			@Pc(30) ClientPlayer local30 = players[local22];
			if (local30 != null && local30.anInt3408 > 0) {
				local30.anInt3408--;
				if (local30.anInt3408 == 0) {
					local30.aClass100_640 = null;
				}
			}
		}
		for (local11 = 0; local11 < npcCount; local11++) {
			@Pc(68) int local68 = npcIds[local11];
			@Pc(72) ClientNPC local72 = npcs[local68];
			if (local72 != null && local72.anInt3408 > 0) {
				local72.anInt3408--;
				if (local72.anInt3408 == 0) {
					local72.aClass100_640 = null;
				}
			}
		}
	}

	@OriginalMember(owner = "client!bg", name = "a", descriptor = "(Lclient!be;ZI)V")
	public static void method531(@OriginalArg(0) IfType arg0, @OriginalArg(1) boolean arg1) {
		@Pc(20) int local20 = arg0.scrollWidth == 0 ? arg0.anInt445 : arg0.scrollWidth;
		@Pc(32) int local32 = arg0.scrollHeight == 0 ? arg0.anInt459 : arg0.scrollHeight;
		method4190(arg0.parentId, arg1, local20, local32, IfType.list[arg0.parentId >> 16]);
		if (arg0.subcomponents != null) {
			method4190(arg0.parentId, arg1, local20, local32, arg0.subcomponents);
		}
		@Pc(66) SubInterface local66 = (SubInterface) aClass133_9.find((long) arg0.parentId);
		if (local66 != null) {
			method4017(local32, arg1, local66.id, local20);
		}
	}

	@OriginalMember(owner = "client!od", name = "a", descriptor = "(ILclient!na;)V")
	public static void method3318(@OriginalArg(1) JagString arg0) {
		if (Static199.aClass3_Sub22Array1 == null) {
			return;
		}
		@Pc(22) long local22 = arg0.method3158();
		@Pc(24) int local24 = 0;
		if (local22 == 0L) {
			return;
		}
		while (Static199.aClass3_Sub22Array1.length > local24 && Static199.aClass3_Sub22Array1[local24].key != local22) {
			local24++;
		}
		if (local24 < Static199.aClass3_Sub22Array1.length && Static199.aClass3_Sub22Array1[local24] != null) {
			out.p1Enc(162);
			out.p8(Static199.aClass3_Sub22Array1[local24].key);
		}
	}

	@OriginalMember(owner = "client!ac", name = "b", descriptor = "(I)V")
	public static void loopIf3Drag() {
		componentUpdated(dragComponent);
		anInt4851++;
		if (aBoolean83 && aBoolean174) {
			@Pc(30) int local30 = ClientMouseListener.mouseX;
			local30 -= Static246.anInt5388;
			if (anInt2225 > local30) {
				local30 = anInt2225;
			}
			@Pc(41) int local41 = ClientMouseListener.mouseY;
			if (anInt2225 + aClass13_1.anInt445 < local30 - -dragComponent.anInt445) {
				local30 = anInt2225 + aClass13_1.anInt445 - dragComponent.anInt445;
			}
			local41 -= anInt4035;
			if (local41 < anInt5103) {
				local41 = anInt5103;
			}
			if (anInt5103 + aClass13_1.anInt459 < local41 - -dragComponent.anInt459) {
				local41 = anInt5103 + aClass13_1.anInt459 - dragComponent.anInt459;
			}
			@Pc(109) int local109 = local41 - anInt660;
			@Pc(114) int local114 = local30 - anInt3075;
			@Pc(122) int local122 = local30 + aClass13_1.anInt489 - anInt2225;
			@Pc(130) int local130 = aClass13_1.anInt468 + local41 - anInt5103;
			@Pc(133) int local133 = dragComponent.dragdeadzone;
			if (anInt4851 > dragComponent.dragdeadtime && (local133 < local114 || -local133 > local114 || local109 > local133 || local109 < -local133)) {
				aBoolean172 = true;
			}
			@Pc(176) HookReq local176;
			if (dragComponent.ondrag != null && aBoolean172) {
				local176 = new HookReq();
				local176.component = dragComponent;
				local176.onop = dragComponent.ondrag;
				local176.mouseX = local122;
				local176.mouseY = local130;
				ScriptRunner.executeScript(local176);
			}
			if (ClientMouseListener.mouseButton == 0) {
				if (aBoolean172) {
					if (dragComponent.ondragcomplete != null) {
						local176 = new HookReq();
						local176.mouseY = local130;
						local176.drop = aClass13_12;
						local176.mouseX = local122;
						local176.onop = dragComponent.ondragcomplete;
						local176.component = dragComponent;
						ScriptRunner.executeScript(local176);
					}
					if (aClass13_12 != null && method938(dragComponent) != null) {
						out.p1Enc(79);
						out.method2185(dragComponent.parentId);
						out.method2222(aClass13_12.subId);
						out.p4(aClass13_12.parentId);
						out.method2222(dragComponent.subId);
					}
				} else if ((oneMouseButton == 1 || method4640(menuNumEntries - 1)) && menuNumEntries > 2) {
					method3901();
				} else if (menuNumEntries > 0) {
					method1372();
				}
				dragComponent = null;
			}
		} else if (anInt4851 > 1) {
			dragComponent = null;
		}
	}

	@OriginalMember(owner = "client!ah", name = "b", descriptor = "(I)V")
	public static void mouseLoop() {
		if (objDragInterface != null || dragComponent != null) {
			return;
		}
		@Pc(20) int local20 = ClientMouseListener.mouseClickButton;
		@Pc(93) int local93;
		@Pc(99) int local99;
		if (!isMenuOpen) {
			if (local20 == 1 && menuNumEntries > 0) {
				@Pc(37) short local37 = menuAction[menuNumEntries - 1];
				if (local37 == 25 || local37 == 23 || local37 == 48 || local37 == 7 || local37 == 13 || local37 == 47 || local37 == 5 || local37 == 43 || local37 == 35 || local37 == 58 || local37 == 22 || local37 == 1006) {
					local93 = anIntArray408[menuNumEntries - 1];
					local99 = anIntArray142[menuNumEntries - 1];
					@Pc(103) IfType local103 = IfType.get(local99);
					@Pc(106) ServerActive local106 = getActive(local103);
					if (local106.method511() || local106.method504()) {
						objDragCycles = 0;
						objGrabThreshold = false;
						if (objDragInterface != null) {
							componentUpdated(objDragInterface);
						}
						objDragInterface = IfType.get(local99);
						objGrabX = ClientMouseListener.mouseClickX;
						objGrabY = ClientMouseListener.mouseClickY;
						anInt36 = local93;
						componentUpdated(objDragInterface);
						return;
					}
				}
			}
			if (local20 == 1 && (oneMouseButton == 1 && menuNumEntries > 2 || method4640(menuNumEntries - 1))) {
				local20 = 2;
			}
			if (local20 == 2 && menuNumEntries > 0 || anInt3953 == 1) {
				method3901();
			}
			if (local20 == 1 && menuNumEntries > 0 || anInt3953 == 2) {
				method1372();
			}
			return;
		}
		@Pc(204) int local204;
		if (local20 != 1) {
			local93 = ClientMouseListener.mouseY;
			local204 = ClientMouseListener.mouseX;
			if (local204 < Static183.anInt4271 - 10 || local204 > Static24.anInt761 + Static183.anInt4271 + 10 || Static229.anInt5138 - 10 > local93 || local93 > Static13.anInt436 + Static229.anInt5138 + 10) {
				isMenuOpen = false;
				dirtyArea(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
			}
		}
		if (local20 != 1) {
			return;
		}
		local204 = Static183.anInt4271;
		local93 = Static229.anInt5138;
		local99 = Static24.anInt761;
		@Pc(265) int local265 = ClientMouseListener.mouseClickX;
		@Pc(267) int local267 = ClientMouseListener.mouseClickY;
		@Pc(269) int local269 = -1;
		for (@Pc(271) int local271 = 0; local271 < menuNumEntries; local271++) {
			@Pc(289) int local289;
			if (Static261.aBoolean298) {
				local289 = (menuNumEntries - local271 - 1) * 15 + local93 + 35;
			} else {
				local289 = (menuNumEntries - local271 - 1) * 15 + local93 + 31;
			}
			if (local265 > local204 && local204 + local99 > local265 && local289 - 13 < local267 && local289 + 3 > local267) {
				local269 = local271;
			}
		}
		if (local269 != -1) {
			doAction(local269);
		}
		isMenuOpen = false;
		dirtyArea(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
	}

	@OriginalMember(owner = "client!kf", name = "a", descriptor = "(IIBII)V")
	public static void dirtyArea(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		for (@Pc(12) int local12 = 0; local12 < componentDrawCount; local12++) {
			if (componentDrawWidth[local12] + componentDrawX[local12] > arg0 && arg1 + arg0 > componentDrawX[local12] && arg2 < componentDrawHeight[local12] + componentDrawY[local12] && componentDrawY[local12] < arg2 + arg3) {
				componentRedrawRequested1[local12] = true;
			}
		}
	}

	@OriginalMember(owner = "client!ke", name = "a", descriptor = "(ZLclient!wk;Z)V")
	public static void closeSubInterface(@OriginalArg(0) boolean arg0, @OriginalArg(1) SubInterface arg1) {
		@Pc(9) int local9 = (int) arg1.key;
		@Pc(16) int local16 = arg1.id;
		arg1.unlink();

		if (arg0) {
			IfType.closeInterface(local16);
		}

		purgeServerActive(local16);

		@Pc(32) IfType local32 = IfType.get(local9);
		if (local32 != null) {
			componentUpdated(local32);
		}

		@Pc(41) int local41 = menuNumEntries;
		@Pc(43) int local43;
		for (local43 = 0; local43 < local41; local43++) {
			if (Static2.method5(menuAction[local43])) {
				method3628(local43);
			}
		}

		if (menuNumEntries == 1) {
			isMenuOpen = false;
			dirtyArea(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
		} else {
			dirtyArea(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
			local43 = Static280.aClass3_Sub2_Sub9_43.method2858(Text.aClass100_998);
			for (@Pc(75) int local75 = 0; local75 < menuNumEntries; local75++) {
				@Pc(88) int local88 = Static280.aClass3_Sub2_Sub9_43.method2858(Static269.method2228(local75));
				if (local43 < local88) {
					local43 = local88;
				}
			}
			Static13.anInt436 = menuNumEntries * 15 + (Static261.aBoolean298 ? 26 : 22);
			Static24.anInt761 = local43 + 8;
		}

		if (toplevelinterface != -1) {
			runHookImmediate(1, toplevelinterface);
		}
	}

	@OriginalMember(owner = "client!we", name = "a", descriptor = "(BILclient!be;)I")
	public static int getIfVar(@OriginalArg(1) int arg0, @OriginalArg(2) IfType arg1) {
		if (arg1.scripts == null || arg0 >= arg1.scripts.length) {
			return -2;
		}
		try {
			@Pc(33) int[] local33 = arg1.scripts[arg0];
			@Pc(35) byte local35 = 0;
			@Pc(37) int local37 = 0;
			@Pc(39) int local39 = 0;
			while (true) {
				@Pc(41) int local41 = 0;
				@Pc(46) int local46 = local33[local39++];
				@Pc(48) byte local48 = 0;
				if (local46 == 0) {
					return local37;
				}
				if (local46 == 1) {
					local41 = statEffectiveLevel[local33[local39++]];
				}
				if (local46 == 2) {
					local41 = statBaseLevel[local33[local39++]];
				}
				if (local46 == 3) {
					local41 = statXP[local33[local39++]];
				}
				@Pc(124) int local124;
				@Pc(135) IfType local135;
				@Pc(140) int local140;
				@Pc(152) int local152;
				if (local46 == 4) {
					local124 = local33[local39++] << 16;
					@Pc(131) int local131 = local124 + local33[local39++];
					local135 = IfType.get(local131);
					local140 = local33[local39++];
					if (local140 != -1 && (!ObjType.list(local140).aBoolean131 || memServer)) {
						for (local152 = 0; local152 < local135.linkObjNumber.length; local152++) {
							if (local140 + 1 == local135.linkObjNumber[local152]) {
								local41 += local135.linkObjType[local152];
							}
						}
					}
				}
				if (local46 == 5) {
					local41 = VarCache.var[local33[local39++]];
				}
				if (local46 == 6) {
					local41 = Skills.skillxp[statBaseLevel[local33[local39++]] - 1];
				}
				if (local46 == 7) {
					local41 = VarCache.var[local33[local39++]] * 100 / 46875;
				}
				if (local46 == 8) {
					local41 = localPlayer.combatLevel;
				}
				if (local46 == 9) {
					for (local124 = 0; local124 < 25; local124++) {
						if (Skills.used[local124]) {
							local41 += statBaseLevel[local124];
						}
					}
				}
				if (local46 == 10) {
					local124 = local33[local39++] << 16;
					local124 += local33[local39++];
					local135 = IfType.get(local124);
					local140 = local33[local39++];
					if (local140 != -1 && (!ObjType.list(local140).aBoolean131 || memServer)) {
						for (local152 = 0; local152 < local135.linkObjNumber.length; local152++) {
							if (local135.linkObjNumber[local152] == local140 + 1) {
								local41 = 999999999;
								break;
							}
						}
					}
				}
				if (local46 == 11) {
					local41 = runEnergy;
				}
				if (local46 == 12) {
					local41 = runWeight;
				}
				if (local46 == 13) {
					local124 = VarCache.var[local33[local39++]];
					@Pc(353) int local353 = local33[local39++];
					local41 = (0x1 << local353 & local124) == 0 ? 0 : 1;
				}
				if (local46 == 14) {
					local124 = local33[local39++];
					local41 = VarCache.getVarbit(local124);
				}
				if (local46 == 15) {
					local48 = 1;
				}
				if (local46 == 16) {
					local48 = 2;
				}
				if (local46 == 17) {
					local48 = 3;
				}
				if (local46 == 18) {
					local41 = (localPlayer.x >> 7) + mapBuildBaseX;
				}
				if (local46 == 19) {
					local41 = (localPlayer.z >> 7) + mapBuildBaseZ;
				}
				if (local46 == 20) {
					local41 = local33[local39++];
				}
				if (local48 == 0) {
					if (local35 == 0) {
						local37 += local41;
					}
					if (local35 == 1) {
						local37 -= local41;
					}
					if (local35 == 2 && local41 != 0) {
						local37 /= local41;
					}
					if (local35 == 3) {
						local37 *= local41;
					}
					local35 = 0;
				} else {
					local35 = local48;
				}
			}
		} catch (@Pc(464) Exception local464) {
			return -1;
		}
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!be;I)Z")
	public static boolean getIfActive(@OriginalArg(0) IfType arg0) {
		if (arg0.scriptOperand == null) {
			return false;
		}
		for (@Pc(14) int local14 = 0; local14 < arg0.scriptOperand.length; local14++) {
			@Pc(34) int local34 = getIfVar(local14, arg0);
			@Pc(39) int local39 = arg0.scriptComparator[local14];
			if (arg0.scriptOperand[local14] == 2) {
				if (local39 <= local34) {
					return false;
				}
			} else if (arg0.scriptOperand[local14] == 3) {
				if (local34 <= local39) {
					return false;
				}
			} else if (arg0.scriptOperand[local14] == 4) {
				if (local34 == local39) {
					return false;
				}
			} else if (local39 != local34) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!q", name = "a", descriptor = "(IIIIIIBI)V")
	public static void getOverlayPos(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6) {
		if (arg5 < 128 || arg2 < 128 || arg5 > 13056 || arg2 > 13056) {
			projectX = -1;
			projectY = -1;
			return;
		}
		@Pc(38) int local38 = getAvH(minusedlevel, arg5, arg2) - arg3;
		@Pc(42) int local42 = arg2 - anInt3302;
		@Pc(46) int local46 = local38 - anInt40;
		@Pc(50) int local50 = arg5 - anInt3439;
		@Pc(54) int local54 = Pix3D.sinTable[anInt5333];
		@Pc(58) int local58 = Pix3D.cosTable[anInt5333];
		@Pc(62) int local62 = Pix3D.sinTable[anInt4358];
		@Pc(66) int local66 = Pix3D.cosTable[anInt4358];
		@Pc(76) int local76 = local50 * local66 + local62 * local42 >> 16;
		@Pc(87) int local87 = local42 * local66 - local62 * local50 >> 16;
		@Pc(89) int local89 = local76;
		@Pc(99) int local99 = local58 * local46 - local87 * local54 >> 16;
		@Pc(113) int local113 = local87 * local58 + local46 * local54 >> 16;
		if (local113 < 50) {
			projectX = -1;
			projectY = -1;
		} else if (glRenderer) {
			@Pc(150) int local150 = arg1 * 512 >> 8;
			projectY = local150 * local89 / local113 + arg0;
			@Pc(164) int local164 = arg6 * 512 >> 8;
			projectX = local164 * local99 / local113 + arg4;
		} else {
			projectY = (local89 << 9) / local113 + arg0;
			projectX = (local99 << 9) / local113 + arg4;
		}
	}

	@OriginalMember(owner = "client!pa", name = "a", descriptor = "(IIILclient!e;)V")
	public static void triggerPlayerAnim(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) ClientPlayer arg2) {
		if (arg1 == arg2.anInt3369 && arg1 != -1) {
			@Pc(89) SeqType local89 = SeqType.list(arg1);
			@Pc(92) int local92 = local89.duplicatebehavior;

			if (local92 == 1) {
				arg2.anInt3420 = arg0;
				arg2.anInt3360 = 0;
				arg2.anInt3373 = 1;
				arg2.anInt3425 = 0;
				arg2.anInt3371 = 0;
				method2836(arg2.z, local89, arg2.x, localPlayer == arg2, arg2.anInt3425);
			} else if (local92 == 2) {
				arg2.anInt3371 = 0;
			}
		} else if (arg1 == -1 || arg2.anInt3369 == -1 || SeqType.list(arg1).priority >= SeqType.list(arg2.anInt3369).priority) {
			arg2.anInt3373 = 1;
			arg2.anInt3425 = 0;
			arg2.anInt3420 = arg0;
			arg2.anInt3405 = arg2.anInt3409;
			arg2.anInt3371 = 0;
			arg2.anInt3360 = 0;
			arg2.anInt3369 = arg1;
			if (arg2.anInt3369 != -1) {
				method2836(arg2.z, SeqType.list(arg2.anInt3369), arg2.x, arg2 == localPlayer, arg2.anInt3425);
			}
		}
	}

	@OriginalMember(owner = "client!pl", name = "a", descriptor = "(II)V")
	public static void setMainState(@OriginalArg(0) int arg0) {
		if (state == arg0) {
			return;
		}

		if (state == 0) {
			GameShell.resetProgress();
		}

		if (arg0 == 40) {
			method1208();
		}

		@Pc(37) boolean local37 = arg0 == 5 || arg0 == 10 || arg0 == 28;
		if (arg0 != 40 && lastStream != null) {
			lastStream.close();
			lastStream = null;
		}

		if (arg0 == 25 || arg0 == 28) {
			locModelLoadCount = 0;
			mapPrevLoadCount = 1;
			mapLoadingStage = 0;
			locModelLoadPrevCount = 1;
			mapLoadCount = 0;
			WorldMap.method2325(true);
		}

		if (arg0 == 25 || arg0 == 10) {
			Static123.method2418();
		}

		if (arg0 == 5) {
			Static181.method3344(sprites);
		} else {
			Static119.method2381();
		}

		@Pc(106) boolean local106 = state == 5 || state == 10 || state == 28;
		if (local106 != local37) {
			if (local37) {
				anInt4363 = Static250.anInt5441;
				if (midiVolume == 0) {
					MidiManager.method801();
				} else {
					MidiManager.method526(Static250.anInt5441, songs, 255);
				}
				js5Net.sendLoginLogoutPacket(false);
			} else {
				MidiManager.method801();
				js5Net.sendLoginLogoutPacket(true);
			}
		}

		if (glRenderer && (arg0 == 25 || arg0 == 28 || arg0 == 40)) {
			Static239.method4160();
		}

		state = arg0;
	}

	@OriginalMember(owner = "client!ej", name = "i", descriptor = "(I)V")
	public static void method1373() {
		out.p1Enc(243);
		out.p1(Static144.method2736());
		out.p2(anInt1448);
		out.p2(anInt5554);
		out.p1(Static186.anInt4392);
	}

	@OriginalMember(owner = "client!ej", name = "h", descriptor = "(I)V")
	public static void method1372() {
		if (anInt3953 == 2) {
			if (anInt3751 == anInt5850 && anInt1892 == anInt5895) {
				anInt3953 = 0;
				if (aBoolean199 && ClientKeyboardListener.keyHeld[81] && menuNumEntries > 2) {
					doAction(menuNumEntries - 2);
				} else {
					doAction(menuNumEntries - 1);
				}
			}
		} else if (anInt3751 == ClientMouseListener.mouseClickX && anInt1892 == ClientMouseListener.mouseClickY) {
			anInt3953 = 0;
			if (aBoolean199 && ClientKeyboardListener.keyHeld[81] && menuNumEntries > 2) {
				doAction(menuNumEntries - 2);
			} else {
				doAction(menuNumEntries - 1);
			}
		} else {
			anInt5895 = ClientMouseListener.mouseClickY;
			anInt3953 = 2;
			anInt5850 = ClientMouseListener.mouseClickX;
		}
	}

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(IIIIIII)V")
	public static void method2726(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		anInt5854 = 0;
		@Pc(5) int local5;
		@Pc(642) int local642;
		@Pc(74) int local74;
		@Pc(265) int local265;
		@Pc(310) int local310;
		@Pc(359) int local359;
		@Pc(639) int local639;
		for (local5 = -1; local5 < playerCount + npcCount; local5++) {
			@Pc(17) ClientEntity local17;
			if (local5 == -1) {
				local17 = localPlayer;
			} else if (playerCount > local5) {
				local17 = players[playerIds[local5]];
			} else {
				local17 = npcs[npcIds[local5 - playerCount]];
			}
			if (local17 != null && local17.method2682()) {
				@Pc(58) NPCType local58;
				if (local17 instanceof ClientNPC) {
					local58 = ((ClientNPC) local17).aClass96_1;
					if (local58.anIntArray357 != null) {
						local58 = local58.method2932();
					}
					if (local58 == null) {
						continue;
					}
				}
				@Pc(161) int local161;
				if (local5 >= playerCount) {
					local58 = ((ClientNPC) local17).aClass96_1;
					if (local58.anIntArray357 != null) {
						local58 = local58.method2932();
					}
					if (local58.anInt3732 >= 0 && Static138.aClass3_Sub2_Sub1Array5.length > local58.anInt3732) {
						if (local58.anInt3730 == -1) {
							local265 = local17.method2691() + 15;
						} else {
							local265 = local58.anInt3730 + 15;
						}
						method3326(arg4 >> 1, arg3, local17, arg5, local265, arg1 >> 1);
						if (projectY > -1) {
							Static138.aClass3_Sub2_Sub1Array5[local58.anInt3732].plotSprite(arg2 + projectY - 12, arg0 + -30 - -projectX);
						}
					}
					@Pc(308) MapMarker[] local308 = Static143.aClass102Array1;
					for (local310 = 0; local310 < local308.length; local310++) {
						@Pc(322) MapMarker local322 = local308[local310];
						if (local322 != null && local322.anInt4058 == 1 && local322.anInt4057 == npcIds[local5 - playerCount] && loopCycle % 20 < 10) {
							if (local58.anInt3730 == -1) {
								local359 = local17.method2691() + 15;
							} else {
								local359 = local58.anInt3730 + 15;
							}
							method3326(arg4 >> 1, arg3, local17, arg5, local359, arg1 >> 1);
							if (projectY > -1) {
								Static276.aClass3_Sub2_Sub1Array11[local322.anInt4048].plotSprite(arg2 + projectY - 12, projectX + -28 + arg0);
							}
						}
					}
				} else {
					local74 = 30;
					@Pc(77) ClientPlayer local77 = (ClientPlayer) local17;
					if (local77.anInt1669 != -1 || local77.anInt1649 != -1) {
						method3326(arg4 >> 1, arg3, local17, arg5, local17.method2691() + 15, arg1 >> 1);
						if (projectY > -1) {
							if (local77.anInt1669 != -1) {
								Static219.aClass3_Sub2_Sub1Array9[local77.anInt1669].plotSprite(projectY + arg2 - 12, arg0 + -30 + projectX);
								local74 += 25;
							}
							if (local77.anInt1649 != -1) {
								Static138.aClass3_Sub2_Sub1Array5[local77.anInt1649].plotSprite(arg2 + projectY - 12, arg0 - (-projectX + local74));
								local74 += 25;
							}
						}
					}
					if (local5 >= 0) {
						@Pc(159) MapMarker[] local159 = Static143.aClass102Array1;
						for (local161 = 0; local161 < local159.length; local161++) {
							@Pc(173) MapMarker local173 = local159[local161];
							if (local173 != null && local173.anInt4058 == 10 && playerIds[local5] == local173.anInt4057) {
								method3326(arg4 >> 1, arg3, local17, arg5, local17.method2691() + 15, arg1 >> 1);
								if (projectY > -1) {
									Static276.aClass3_Sub2_Sub1Array11[local173.anInt4048].plotSprite(arg2 + projectY - 12, arg0 + (projectX - local74));
								}
							}
						}
					}
				}
				if (local17.aClass100_640 != null && (local5 >= playerCount || Static59.anInt1812 == 0 || Static59.anInt1812 == 3 || Static59.anInt1812 == 1 && method1965(((ClientPlayer) local17).name))) {
					method3326(arg4 >> 1, arg3, local17, arg5, local17.method2691(), arg1 >> 1);
					if (projectY > -1 && anInt5854 < Static191.anInt4506) {
						Static191.anIntArray389[anInt5854] = Static280.aClass3_Sub2_Sub9_43.method2858(local17.aClass100_640) / 2;
						Static191.anIntArray387[anInt5854] = Static280.aClass3_Sub2_Sub9_43.anInt3626;
						Static191.anIntArray385[anInt5854] = projectY;
						Static191.anIntArray392[anInt5854] = projectX;
						Static191.anIntArray390[anInt5854] = local17.anInt3429;
						Static191.anIntArray391[anInt5854] = local17.anInt3352;
						Static191.anIntArray384[anInt5854] = local17.anInt3408;
						Static191.aClass100Array132[anInt5854] = local17.aClass100_640;
						anInt5854++;
					}
				}
				if (local17.anInt3378 > loopCycle) {
					@Pc(508) AbstractPix32 local508 = Static116.aClass3_Sub2_Sub1Array3[0];
					@Pc(512) AbstractPix32 local512 = Static116.aClass3_Sub2_Sub1Array3[1];
					if (local17 instanceof ClientNPC) {
						@Pc(518) ClientNPC local518 = (ClientNPC) local17;
						@Pc(528) AbstractPix32[] local528 = (AbstractPix32[]) Static73.aClass99_10.find((long) local518.aClass96_1.anInt3736);
						if (local528 == null) {
							local528 = Static209.method3708(local518.aClass96_1.anInt3736, sprites);
							if (local528 != null) {
								Static73.aClass99_10.put(local528, (long) local518.aClass96_1.anInt3736);
							}
						}
						if (local528 != null && local528.length == 2) {
							local512 = local528[1];
							local508 = local528[0];
						}
						@Pc(571) NPCType local571 = local518.aClass96_1;
						if (local571.anInt3730 == -1) {
							local310 = local17.method2691();
						} else {
							local310 = local571.anInt3730;
						}
					} else {
						local310 = local17.method2691();
					}
					method3326(arg4 >> 1, arg3, local17, arg5, local508.anInt1859 + local310 + 10, arg1 >> 1);
					if (projectY > -1) {
						local161 = projectY + arg2 - (local508.anInt1867 >> 1);
						local359 = projectX + arg0 - 3;
						local508.plotSprite(local161, local359);
						local639 = local508.anInt1867 * local17.anInt3372 / 255;
						local642 = local508.anInt1859;
						if (glRenderer) {
							Static46.method1183(local161, local359, local161 + local639, local359 + local642);
						} else {
							Pix2D.method2498(local161, local359, local161 + local639, local642 + local359);
						}
						local512.plotSprite(local161, local359);
						if (glRenderer) {
							Static46.method1187(arg2, arg0, arg1 + arg2, arg0 - -arg4);
						} else {
							Pix2D.method2496(arg2, arg0, arg1 + arg2, arg4 + arg0);
						}
					}
				}
				for (local74 = 0; local74 < 4; local74++) {
					if (local17.anIntArray319[local74] > loopCycle) {
						if (local17 instanceof ClientNPC) {
							@Pc(725) ClientNPC local725 = (ClientNPC) local17;
							@Pc(728) NPCType local728 = local725.aClass96_1;
							if (local728.anInt3730 == -1) {
								local265 = local17.method2691() / 2;
							} else {
								local265 = local728.anInt3730 / 2;
							}
						} else {
							local265 = local17.method2691() / 2;
						}
						method3326(arg4 >> 1, arg3, local17, arg5, local265, arg1 >> 1);
						if (projectY > -1) {
							if (local74 == 1) {
								projectX -= 20;
							}
							if (local74 == 2) {
								projectX -= 10;
								projectY -= 15;
							}
							if (local74 == 3) {
								projectX -= 10;
								projectY += 15;
							}
							Static213.aClass3_Sub2_Sub1Array8[local17.anIntArray321[local74]].plotSprite(arg2 + projectY - 12, arg0 + projectX - 12);
							Static114.aClass3_Sub2_Sub9_42.method2875(JagString.parseInt(local17.anIntArray322[local74]), projectY + arg2 - 1, projectX + 3 + arg0, 16777215, 0);
						}
					}
				}
			}
		}
		for (local5 = 0; local5 < anInt5854; local5++) {
			local74 = Static191.anIntArray392[local5];
			@Pc(859) int local859 = Static191.anIntArray385[local5];
			local310 = Static191.anIntArray387[local5];
			local265 = Static191.anIntArray389[local5];
			@Pc(869) boolean local869 = true;
			while (local869) {
				local869 = false;
				for (local359 = 0; local359 < local5; local359++) {
					if (Static191.anIntArray392[local359] - Static191.anIntArray387[local359] < local74 + 2 && local74 - local310 < Static191.anIntArray392[local359] - -2 && local859 - local265 < Static191.anIntArray385[local359] + Static191.anIntArray389[local359] && Static191.anIntArray385[local359] - Static191.anIntArray389[local359] < local265 + local859 && Static191.anIntArray392[local359] - Static191.anIntArray387[local359] < local74) {
						local74 = Static191.anIntArray392[local359] - Static191.anIntArray387[local359];
						local869 = true;
					}
				}
			}
			projectY = Static191.anIntArray385[local5];
			projectX = Static191.anIntArray392[local5] = local74;
			@Pc(962) JagString local962 = Static191.aClass100Array132[local5];
			if (chatEffects == 0) {
				local639 = 16776960;
				if (Static191.anIntArray390[local5] < 6) {
					local639 = Static16.anIntArray52[Static191.anIntArray390[local5]];
				}
				if (Static191.anIntArray390[local5] == 6) {
					local639 = Static136.anInt3325 % 20 >= 10 ? 16776960 : 16711680;
				}
				if (Static191.anIntArray390[local5] == 7) {
					local639 = Static136.anInt3325 % 20 < 10 ? 255 : 65535;
				}
				if (Static191.anIntArray390[local5] == 8) {
					local639 = Static136.anInt3325 % 20 >= 10 ? 8454016 : 45056;
				}
				if (Static191.anIntArray390[local5] == 9) {
					local642 = 150 - Static191.anIntArray384[local5];
					if (local642 < 50) {
						local639 = local642 * 1280 + 16711680;
					} else if (local642 < 100) {
						local639 = 16776960 + 16384000 - local642 * 327680;
					} else if (local642 < 150) {
						local639 = local642 * 5 + 65280 - 500;
					}
				}
				if (Static191.anIntArray390[local5] == 10) {
					local642 = 150 - Static191.anIntArray384[local5];
					if (local642 < 50) {
						local639 = local642 * 5 + 16711680;
					} else if (local642 < 100) {
						local639 = 16711935 - (local642 - 50) * 327680;
					} else if (local642 < 150) {
						local639 = local642 * 327680 + 255 + 500 - local642 * 5 - 32768000;
					}
				}
				if (Static191.anIntArray390[local5] == 11) {
					local642 = 150 - Static191.anIntArray384[local5];
					if (local642 < 50) {
						local639 = 16777215 - local642 * 327685;
					} else if (local642 < 100) {
						local639 = local642 * 327685 + 65280 - 16384250;
					} else if (local642 < 150) {
						local639 = 16777215 + 32768000 - local642 * 327680;
					}
				}
				if (Static191.anIntArray391[local5] == 0) {
					Static280.aClass3_Sub2_Sub9_43.method2875(local962, projectY + arg2, arg0 + projectX, local639, 0);
				}
				if (Static191.anIntArray391[local5] == 1) {
					Static280.aClass3_Sub2_Sub9_43.method2872(local962, arg2 + projectY, projectX + arg0, local639, Static136.anInt3325);
				}
				if (Static191.anIntArray391[local5] == 2) {
					Static280.aClass3_Sub2_Sub9_43.method2871(local962, arg2 + projectY, arg0 - -projectX, local639, Static136.anInt3325);
				}
				if (Static191.anIntArray391[local5] == 3) {
					Static280.aClass3_Sub2_Sub9_43.method2868(local962, arg2 + projectY, projectX + arg0, local639, Static136.anInt3325, 150 - Static191.anIntArray384[local5]);
				}
				if (Static191.anIntArray391[local5] == 4) {
					local642 = (150 - Static191.anIntArray384[local5]) * (Static280.aClass3_Sub2_Sub9_43.method2858(local962) + 100) / 150;
					if (glRenderer) {
						Static46.method1183(projectY + arg2 - 50, arg0, projectY + arg2 + 50, arg4 + arg0);
					} else {
						Pix2D.method2498(arg2 + projectY - 50, arg0, projectY + arg2 + 50, arg4 + arg0);
					}
					Static280.aClass3_Sub2_Sub9_43.method2857(local962, arg2 + projectY + 50 - local642, arg0 + projectX, local639, 0);
					if (glRenderer) {
						Static46.method1187(arg2, arg0, arg1 + arg2, arg4 + arg0);
					} else {
						Pix2D.method2496(arg2, arg0, arg2 + arg1, arg0 + arg4);
					}
				}
				if (Static191.anIntArray391[local5] == 5) {
					@Pc(1372) int local1372 = 0;
					local642 = 150 - Static191.anIntArray384[local5];
					if (glRenderer) {
						Static46.method1183(arg2, projectX + arg0 - Static280.aClass3_Sub2_Sub9_43.anInt3626 - 1, arg1 + arg2, arg0 + projectX + 5);
					} else {
						Pix2D.method2498(arg2, projectX + arg0 - Static280.aClass3_Sub2_Sub9_43.anInt3626 - 1, arg2 + arg1, projectX + arg0 + 5);
					}
					if (local642 < 25) {
						local1372 = local642 - 25;
					} else if (local642 > 125) {
						local1372 = local642 - 125;
					}
					Static280.aClass3_Sub2_Sub9_43.method2875(local962, projectY + arg2, local1372 + arg0 + projectX, local639, 0);
					if (glRenderer) {
						Static46.method1187(arg2, arg0, arg2 + arg1, arg0 + arg4);
					} else {
						Pix2D.method2496(arg2, arg0, arg2 + arg1, arg0 + arg4);
					}
				}
			} else {
				Static280.aClass3_Sub2_Sub9_43.method2875(local962, arg2 + projectY, arg0 + projectX, 16776960, 0);
			}
		}
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(B)V")
	public static void getNpcPosOldVis() {
		in.gBitStart();
		@Pc(13) int local13 = in.method2238(8);
		@Pc(22) int local22;
		if (npcCount > local13) {
			for (local22 = local13; local22 < npcCount; local22++) {
				entityRemovalIds[entityRemovalCount++] = npcIds[local22];
			}
		}
		if (npcCount < local13) {
			throw new RuntimeException("gnpov1");
		}
		npcCount = 0;
		for (local22 = 0; local22 < local13; local22++) {
			@Pc(61) int local61 = npcIds[local22];
			@Pc(65) ClientNPC local65 = npcs[local61];
			@Pc(70) int local70 = in.method2238(1);
			if (local70 == 0) {
				npcIds[npcCount++] = local61;
				local65.cycle = loopCycle;
			} else {
				@Pc(92) int local92 = in.method2238(2);
				if (local92 == 0) {
					npcIds[npcCount++] = local61;
					local65.cycle = loopCycle;
					entityUpdateIds[entityUpdateCount++] = local61;
				} else {
					@Pc(139) int local139;
					@Pc(149) int local149;
					if (local92 == 1) {
						npcIds[npcCount++] = local61;
						local65.cycle = loopCycle;
						local139 = in.method2238(3);
						local65.method2684(1, local139);
						local149 = in.method2238(1);
						if (local149 == 1) {
							entityUpdateIds[entityUpdateCount++] = local61;
						}
					} else if (local92 == 2) {
						npcIds[npcCount++] = local61;
						local65.cycle = loopCycle;
						if (in.method2238(1) == 1) {
							local139 = in.method2238(3);
							local65.method2684(2, local139);
							local149 = in.method2238(3);
							local65.method2684(2, local149);
						} else {
							local139 = in.method2238(3);
							local65.method2684(0, local139);
						}
						local139 = in.method2238(1);
						if (local139 == 1) {
							entityUpdateIds[entityUpdateCount++] = local61;
						}
					} else if (local92 == 3) {
						entityRemovalIds[entityRemovalCount++] = local61;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!gm", name = "h", descriptor = "(I)V")
	public static void getNpcPos() {
		entityUpdateCount = 0;
		entityRemovalCount = 0;

		getNpcPosOldVis();
		getNpcPosNewVis();
		getNpcPosExtended();

		for (int i = 0; i < entityRemovalCount; i++) {
			@Pc(30) int local30 = entityRemovalIds[i];
			if (npcs[local30].cycle != loopCycle) {
				if (npcs[local30].aClass96_1.method2935()) {
					BgSound.method1877(npcs[local30]);
				}
				npcs[local30].method2698(null);
				npcs[local30] = null;
			}
		}

		if (psize != in.pos) {
			throw new RuntimeException("gnp1 pos:" + in.pos + " psize:" + psize);
		}

		for (int i = 0; i < npcCount; i++) {
			if (npcs[npcIds[i]] == null) {
				throw new RuntimeException("gnp2 pos:" + i + " size:" + npcCount);
			}
		}
	}

	@OriginalMember(owner = "client!cn", name = "b", descriptor = "(ZI)V")
	public static void method964(@OriginalArg(0) boolean arg0) {
		@Pc(3) int local3 = playerCount;
		if (anInt2939 == localPlayer.x >> 7 && localPlayer.z >> 7 == Static84.anInt2255) {
			anInt2939 = 0;
		}
		if (arg0) {
			local3 = 1;
		}
		@Pc(28) int local28;
		@Pc(39) ClientPlayer local39;
		@Pc(82) int local82;
		@Pc(182) int local182;
		@Pc(200) int local200;
		@Pc(214) int local214;
		@Pc(223) int local223;
		@Pc(106) int local106;
		for (local28 = 0; local28 < local3; local28++) {
			if (arg0) {
				local39 = localPlayer;
			} else {
				local39 = players[playerIds[local28]];
			}
			if (local39 != null && local39.method2682()) {
				@Pc(55) int local55 = local39.method2693();
				@Pc(77) int local77;
				if (local55 == 1) {
					if ((local39.x & 0x7F) == 64 && (local39.z & 0x7F) == 64) {
						local77 = local39.x >> 7;
						local82 = local39.z >> 7;
						if (local77 >= 0 && local77 < 104 && local82 >= 0 && local82 < 104) {
							local106 = Static31.anIntArrayArray6[local77][local82]++;
						}
					}
				} else if (((local55 & 0x1) != 0 || (local39.x & 0x7F) == 0 && (local39.z & 0x7F) == 0) && ((local55 & 0x1) != 1 || (local39.x & 0x7F) == 64 && (local39.z & 0x7F) == 64)) {
					local77 = local39.x - local55 * 64 >> 7;
					local82 = local39.z - local55 * 64 >> 7;
					local182 = local39.method2693() + local77;
					if (local182 > 104) {
						local182 = 104;
					}
					if (local77 < 0) {
						local77 = 0;
					}
					local200 = local82 + local39.method2693();
					if (local82 < 0) {
						local82 = 0;
					}
					if (local200 > 104) {
						local200 = 104;
					}
					for (local214 = local77; local214 < local182; local214++) {
						for (local223 = local82; local223 < local200; local223++) {
							local106 = Static31.anIntArrayArray6[local214][local223]++;
						}
					}
				}
			}
		}
		label220: for (local28 = 0; local28 < local3; local28++) {
			@Pc(272) long local272;
			if (arg0) {
				local39 = localPlayer;
				local272 = 8791798054912L;
			} else {
				local39 = players[playerIds[local28]];
				local272 = (long) playerIds[local28] << 32;
			}
			if (local39 != null && local39.method2682()) {
				local39.aBoolean98 = false;
				if ((Static15.aBoolean33 && playerCount > 200 || playerCount > 50) && !arg0 && local39.anInt3366 == local39.method2681().anInt1037) {
					local39.aBoolean98 = true;
				}
				local82 = local39.method2693();
				if (local82 == 1) {
					if ((local39.x & 0x7F) == 64 && (local39.z & 0x7F) == 64) {
						local182 = local39.x >> 7;
						local200 = local39.z >> 7;
						if (local182 < 0 || local182 >= 104 || local200 < 0 || local200 >= 104) {
							continue;
						}
						if (Static31.anIntArrayArray6[local182][local200] > 1) {
							local106 = Static31.anIntArrayArray6[local182][local200]--;
							continue;
						}
					}
				} else if ((local82 & 0x1) == 0 && (local39.x & 0x7F) == 0 && (local39.z & 0x7F) == 0 || (local82 & 0x1) == 1 && (local39.x & 0x7F) == 64 && (local39.z & 0x7F) == 0) {
					local182 = local39.x - local82 * 64 >> 7;
					local214 = local82 + local182;
					local200 = local39.z - local82 * 64 >> 7;
					if (local214 > 104) {
						local214 = 104;
					}
					if (local182 < 0) {
						local182 = 0;
					}
					local223 = local82 + local200;
					if (local200 < 0) {
						local200 = 0;
					}
					@Pc(468) boolean local468 = true;
					if (local223 > 104) {
						local223 = 104;
					}
					@Pc(476) int local476;
					@Pc(485) int local485;
					for (local476 = local182; local476 < local214; local476++) {
						for (local485 = local200; local485 < local223; local485++) {
							if (Static31.anIntArrayArray6[local476][local485] <= 1) {
								local468 = false;
								break;
							}
						}
					}
					if (local468) {
						local476 = local182;
						while (true) {
							if (local476 >= local214) {
								continue label220;
							}
							for (local485 = local200; local485 < local223; local485++) {
								local106 = Static31.anIntArrayArray6[local476][local485]--;
							}
							local476++;
						}
					}
				}
				if (local39.anObject5 == null || loopCycle < local39.anInt3390 || local39.anInt3375 <= loopCycle) {
					local39.anInt3424 = getAvH(minusedlevel, local39.x, local39.z);
					World.addDynamic(minusedlevel, local39.x, local39.z, local39.anInt3424, (local82 - 1) * 64 + 60, local39, local39.anInt3381, local272, local39.aBoolean171);
				} else {
					local39.aBoolean98 = false;
					local39.anInt3424 = getAvH(minusedlevel, local39.x, local39.z);
					World.method3387(minusedlevel, local39.x, local39.z, local39.anInt3424, local39, local39.anInt3381, local272, local39.anInt3384, local39.anInt3374, local39.anInt3411, local39.anInt3410);
				}
			}
		}
	}

	@OriginalMember(owner = "client!hd", name = "a", descriptor = "(IIIIIIII)V")
	public static void method1881(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (arg5 < 0 || arg3 < 0 || arg5 >= 103 || arg3 >= 103) {
			return;
		}
		@Pc(38) int local38;
		if (arg4 == 0) {
			@Pc(28) Wall local28 = World.method4509(arg0, arg5, arg3);
			if (local28 != null) {
				local38 = Integer.MAX_VALUE & (int) (local28.aLong107 >>> 32);
				if (arg2 == 2) {
					local28.aClass8_5 = new ClientLocAnim(local38, 2, arg1 + 4, arg0, arg5, arg3, arg6, false, local28.aClass8_5);
					local28.aClass8_6 = new ClientLocAnim(local38, 2, arg1 + 1 & 0x3, arg0, arg5, arg3, arg6, false, local28.aClass8_6);
				} else {
					local28.aClass8_5 = new ClientLocAnim(local38, arg2, arg1, arg0, arg5, arg3, arg6, false, local28.aClass8_5);
				}
			}
		}
		if (arg4 == 1) {
			@Pc(106) Decor local106 = World.getDecor(arg0, arg5, arg3);
			if (local106 != null) {
				local38 = (int) (local106.aLong52 >>> 32) & Integer.MAX_VALUE;
				if (arg2 == 4 || arg2 == 5) {
					local106.aClass8_3 = new ClientLocAnim(local38, 4, arg1, arg0, arg5, arg3, arg6, false, local106.aClass8_3);
				} else if (arg2 == 6) {
					local106.aClass8_3 = new ClientLocAnim(local38, 4, arg1 + 4, arg0, arg5, arg3, arg6, false, local106.aClass8_3);
				} else if (arg2 == 7) {
					local106.aClass8_3 = new ClientLocAnim(local38, 4, (arg1 + 2 & 0x3) + 4, arg0, arg5, arg3, arg6, false, local106.aClass8_3);
				} else if (arg2 == 8) {
					local106.aClass8_3 = new ClientLocAnim(local38, 4, arg1 + 4, arg0, arg5, arg3, arg6, false, local106.aClass8_3);
					local106.aClass8_2 = new ClientLocAnim(local38, 4, (arg1 + 2 & 0x3) + 4, arg0, arg5, arg3, arg6, false, local106.aClass8_2);
				}
			}
		}
		if (arg4 == 2) {
			if (arg2 == 11) {
				arg2 = 10;
			}
			@Pc(255) Sprite local255 = World.getScene(arg0, arg5, arg3);
			if (local255 != null) {
				local255.model = new ClientLocAnim((int) (local255.typecode >>> 32) & Integer.MAX_VALUE, arg2, arg1, arg0, arg5, arg3, arg6, false, local255.model);
			}
		}
		if (arg4 == 3) {
			@Pc(290) GroundDecor local290 = World.method2210(arg0, arg5, arg3);
			if (local290 != null) {
				local290.aClass8_1 = new ClientLocAnim(Integer.MAX_VALUE & (int) (local290.aLong26 >>> 32), 22, arg1, arg0, arg5, arg3, arg6, false, local290.aClass8_1);
			}
		}
	}

	@OriginalMember(owner = "client!lf", name = "a", descriptor = "(I)V")
	public static void method2742() {
		if (state == 10 && glRenderer) {
			setMainState(28);
		}
		if (state == 30) {
			setMainState(25);
		}
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(IBIIIIIIIIIZI)Z")
	public static boolean method1955(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) boolean arg10, @OriginalArg(12) int arg11) {
		@Pc(3) int local3;
		@Pc(10) int local10;
		for (local3 = 0; local3 < 104; local3++) {
			for (local10 = 0; local10 < 104; local10++) {
				anIntArrayArray25[local3][local10] = 0;
				anIntArrayArray31[local3][local10] = 99999999;
			}
		}
		local3 = arg8;
		local10 = arg11;
		anIntArrayArray25[arg8][arg11] = 99;
		anIntArrayArray31[arg8][arg11] = 0;
		@Pc(53) byte local53 = 0;
		anIntArray514[0] = arg8;
		@Pc(65) int local65 = local53 + 1;
		anIntArray209[0] = arg11;
		@Pc(69) int local69 = 0;
		@Pc(71) boolean local71 = false;
		@Pc(76) int[][] local76 = levelCollisionMap[minusedlevel].flags;
		@Pc(201) int local201;
		@Pc(242) int local242;
		label397: while (local69 != local65) {
			local3 = anIntArray514[local69];
			local10 = anIntArray209[local69];
			local69 = local69 + 1 & 0xFFF;
			if (arg5 == local3 && local10 == arg0) {
				local71 = true;
				break;
			}
			if (arg6 != 0) {
				if ((arg6 < 5 || arg6 == 10) && levelCollisionMap[minusedlevel].method3042(arg0, local3, local10, arg5, arg6 - 1, arg4, arg3)) {
					local71 = true;
					break;
				}
				if (arg6 < 10 && levelCollisionMap[minusedlevel].method3046(arg0, arg6 - 1, arg5, local10, arg4, arg3, local3)) {
					local71 = true;
					break;
				}
			}
			if (arg1 != 0 && arg9 != 0 && levelCollisionMap[minusedlevel].method3052(arg5, local10, local3, arg4, arg1, arg7, arg0, arg9)) {
				local71 = true;
				break;
			}
			local201 = anIntArrayArray31[local3][local10] + 1;
			if (local3 > 0 && anIntArrayArray25[local3 - 1][local10] == 0 && (local76[local3 - 1][local10] & 0x12C010E) == 0 && (local76[local3 - 1][arg4 + local10 - 1] & 0x12C0138) == 0) {
				local242 = 1;
				while (true) {
					if (arg4 - 1 <= local242) {
						anIntArray514[local65] = local3 - 1;
						anIntArray209[local65] = local10;
						anIntArrayArray25[local3 - 1][local10] = 2;
						local65 = local65 + 1 & 0xFFF;
						anIntArrayArray31[local3 - 1][local10] = local201;
						break;
					}
					if ((local76[local3 - 1][local10 + local242] & 0x12C013E) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local3 < 102 && anIntArrayArray25[local3 + 1][local10] == 0 && (local76[local3 + arg4][local10] & 0x12C0183) == 0 && (local76[arg4 + local3][local10 + arg4 - 1] & 0x12C01E0) == 0) {
				local242 = 1;
				while (true) {
					if (local242 >= arg4 - 1) {
						anIntArray514[local65] = local3 + 1;
						anIntArray209[local65] = local10;
						anIntArrayArray25[local3 + 1][local10] = 8;
						anIntArrayArray31[local3 + 1][local10] = local201;
						local65 = local65 + 1 & 0xFFF;
						break;
					}
					if ((local76[arg4 + local3][local10 + local242] & 0x12C01E3) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local10 > 0 && anIntArrayArray25[local3][local10 - 1] == 0 && (local76[local3][local10 - 1] & 0x12C010E) == 0 && (local76[arg4 + local3 - 1][local10 - 1] & 0x12C0183) == 0) {
				local242 = 1;
				while (true) {
					if (arg4 - 1 <= local242) {
						anIntArray514[local65] = local3;
						anIntArray209[local65] = local10 - 1;
						anIntArrayArray25[local3][local10 - 1] = 1;
						local65 = local65 + 1 & 0xFFF;
						anIntArrayArray31[local3][local10 - 1] = local201;
						break;
					}
					if ((local76[local3 + local242][local10 - 1] & 0x12C018F) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local10 < 102 && anIntArrayArray25[local3][local10 + 1] == 0 && (local76[local3][local10 + arg4] & 0x12C0138) == 0 && (local76[local3 + arg4 - 1][arg4 + local10] & 0x12C01E0) == 0) {
				local242 = 1;
				while (true) {
					if (local242 >= arg4 - 1) {
						anIntArray514[local65] = local3;
						anIntArray209[local65] = local10 + 1;
						anIntArrayArray25[local3][local10 + 1] = 4;
						anIntArrayArray31[local3][local10 + 1] = local201;
						local65 = local65 + 1 & 0xFFF;
						break;
					}
					if ((local76[local3 + local242][arg4 + local10] & 0x12C01F8) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local3 > 0 && local10 > 0 && anIntArrayArray25[local3 - 1][local10 - 1] == 0 && (local76[local3 - 1][arg4 + local10 - 1 - 1] & 0x12C0138) == 0 && (local76[local3 - 1][local10 - 1] & 0x12C010E) == 0 && (local76[arg4 + local3 - 1 - 1][local10 - 1] & 0x12C0183) == 0) {
				local242 = 1;
				while (true) {
					if (arg4 - 1 <= local242) {
						anIntArray514[local65] = local3 - 1;
						anIntArray209[local65] = local10 - 1;
						local65 = local65 + 1 & 0xFFF;
						anIntArrayArray25[local3 - 1][local10 - 1] = 3;
						anIntArrayArray31[local3 - 1][local10 - 1] = local201;
						break;
					}
					if ((local76[local3 - 1][local10 + local242 - 1] & 0x12C013E) != 0 || (local76[local242 + local3 - 1][local10 - 1] & 0x12C018F) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local3 < 102 && local10 > 0 && anIntArrayArray25[local3 + 1][local10 - 1] == 0 && (local76[local3 + 1][local10 - 1] & 0x12C010E) == 0 && (local76[arg4 + local3][local10 - 1] & 0x12C0183) == 0 && (local76[local3 + arg4][local10 + arg4 - 1 - 1] & 0x12C01E0) == 0) {
				local242 = 1;
				while (true) {
					if (local242 >= arg4 - 1) {
						anIntArray514[local65] = local3 + 1;
						anIntArray209[local65] = local10 - 1;
						local65 = local65 + 1 & 0xFFF;
						anIntArrayArray25[local3 + 1][local10 - 1] = 9;
						anIntArrayArray31[local3 + 1][local10 - 1] = local201;
						break;
					}
					if ((local76[local3 + arg4][local10 + local242 - 1] & 0x12C01E3) != 0 || (local76[local242 + local3 + 1][local10 - 1] & 0x12C018F) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local3 > 0 && local10 < 102 && anIntArrayArray25[local3 - 1][local10 + 1] == 0 && (local76[local3 - 1][local10 + 1] & 0x12C010E) == 0 && (local76[local3 - 1][local10 + arg4] & 0x12C0138) == 0 && (local76[local3][local10 + arg4] & 0x12C01E0) == 0) {
				local242 = 1;
				while (true) {
					if (arg4 - 1 <= local242) {
						anIntArray514[local65] = local3 - 1;
						anIntArray209[local65] = local10 + 1;
						local65 = local65 + 1 & 0xFFF;
						anIntArrayArray25[local3 - 1][local10 + 1] = 6;
						anIntArrayArray31[local3 - 1][local10 + 1] = local201;
						break;
					}
					if ((local76[local3 - 1][local10 + local242 + 1] & 0x12C013E) != 0 || (local76[local242 + local3 - 1][arg4 + local10] & 0x12C01F8) != 0) {
						break;
					}
					local242++;
				}
			}
			if (local3 < 102 && local10 < 102 && anIntArrayArray25[local3 + 1][local10 + 1] == 0 && (local76[local3 + 1][local10 + arg4] & 0x12C0138) == 0 && (local76[local3 + arg4][local10 + arg4] & 0x12C01E0) == 0 && (local76[arg4 + local3][local10 + 1] & 0x12C0183) == 0) {
				for (local242 = 1; local242 < arg4 - 1; local242++) {
					if ((local76[local242 + local3 + 1][local10 + arg4] & 0x12C01F8) != 0 || (local76[arg4 + local3][local242 + local10 + 1] & 0x12C01E3) != 0) {
						continue label397;
					}
				}
				anIntArray514[local65] = local3 + 1;
				anIntArray209[local65] = local10 + 1;
				anIntArrayArray25[local3 + 1][local10 + 1] = 12;
				anIntArrayArray31[local3 + 1][local10 + 1] = local201;
				local65 = local65 + 1 & 0xFFF;
			}
		}
		Static221.anInt4364 = 0;
		if (!local71) {
			if (!arg10) {
				return false;
			}
			local201 = 1000;
			local242 = 100;
			for (@Pc(1247) int local1247 = arg5 - 10; local1247 <= arg5 + 10; local1247++) {
				for (@Pc(1257) int local1257 = arg0 - 10; local1257 <= arg0 + 10; local1257++) {
					if (local1247 >= 0 && local1257 >= 0 && local1247 < 104 && local1257 < 104 && anIntArrayArray31[local1247][local1257] < 100) {
						@Pc(1295) int local1295 = 0;
						if (arg5 > local1247) {
							local1295 = arg5 - local1247;
						} else if (arg5 + arg1 - 1 < local1247) {
							local1295 = local1247 + 1 - arg1 - arg5;
						}
						@Pc(1334) int local1334 = 0;
						if (local1257 < arg0) {
							local1334 = arg0 - local1257;
						} else if (arg0 + arg9 - 1 < local1257) {
							local1334 = local1257 + 1 - arg0 - arg9;
						}
						@Pc(1377) int local1377 = local1295 * local1295 + local1334 * local1334;
						if (local1377 < local201 || local1377 == local201 && local242 > anIntArrayArray31[local1247][local1257]) {
							local242 = anIntArrayArray31[local1247][local1257];
							local3 = local1247;
							local201 = local1377;
							local10 = local1257;
						}
					}
				}
			}
			if (local201 == 1000) {
				return false;
			}
			if (local3 == arg8 && arg11 == local10) {
				return false;
			}
			Static221.anInt4364 = 1;
		}
		@Pc(1438) byte local1438 = 0;
		anIntArray514[0] = local3;
		local69 = local1438 + 1;
		anIntArray209[0] = local10;
		local201 = local242 = anIntArrayArray25[local3][local10];
		while (local3 != arg8 || arg11 != local10) {
			if (local242 != local201) {
				anIntArray514[local69] = local3;
				local242 = local201;
				anIntArray209[local69++] = local10;
			}
			if ((local201 & 0x2) != 0) {
				local3++;
			} else if ((local201 & 0x8) != 0) {
				local3--;
			}
			if ((local201 & 0x1) != 0) {
				local10++;
			} else if ((local201 & 0x4) != 0) {
				local10--;
			}
			local201 = anIntArrayArray25[local3][local10];
		}
		if (local69 > 0) {
			method3502(local69, arg2);
			return true;
		} else if (arg2 == 1) {
			return false;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
	public static void addMenuOption(@OriginalArg(0) int arg0, @OriginalArg(1) long arg1, @OriginalArg(3) JagString arg2, @OriginalArg(4) int arg3, @OriginalArg(5) short arg4, @OriginalArg(6) JagString arg5, @OriginalArg(7) int arg6) {
		if (isMenuOpen || menuNumEntries >= 500) {
			return;
		}

		aClass100Array168[menuNumEntries] = arg5;
		aClass100Array160[menuNumEntries] = arg2;
		anIntArray382[menuNumEntries] = arg0 == -1 ? Static35.anInt1092 : arg0;
		menuAction[menuNumEntries] = arg4;
		aLongArray5[menuNumEntries] = arg1;
		anIntArray408[menuNumEntries] = arg3;
		anIntArray142[menuNumEntries] = arg6;
		menuNumEntries++;
	}

	@OriginalMember(owner = "client!tk", name = "a", descriptor = "(Z)V")
	public static void method4221() {
		mouseTrackedDelta = 0;
		focusIn = true;
		aLong139 = 0L;
		mouseTracking.length = 0;
		focus = true;
		ReflectionChecker.method4625();
		ptype2 = -1;
		ptype1 = -1;
		ptype = -1;
		logoutTimer = 0;
		rebootTimer = 0;
		out.pos = 0;
		ptype0 = -1;
		timeoutTimer = 0;
		in.pos = 0;
		@Pc(3506) int local3506;
		for (local3506 = 0; local3506 < Static143.aClass102Array1.length; local3506++) {
			Static143.aClass102Array1[local3506] = null;
		}
		menuNumEntries = 0;
		isMenuOpen = false;
		ClientMouseListener.setIdleTimer(0);
		for (local3506 = 0; local3506 < 100; local3506++) {
			aClass100Array158[local3506] = null;
		}
		Static260.anInt5014 = 0;
		macroCameraAngle = (int) (Math.random() * 100.0D) - 50;
		Static84.anInt2255 = 0;
		anInt1747 = (int) (Math.random() * 20.0D) - 10 & 0x7FF;
		Static107.anInt2875 = -1;
		playerCount = 0;
		anInt5795 = 0;
		macroCameraZ = (int) (Math.random() * 110.0D) - 55;
		targetMode = false;
		macroMinimapZoom = (int) (Math.random() * 30.0D) - 20;
		waveCount = 0;
		anInt2939 = 0;
		macroMinimapAngle = (int) (Math.random() * 120.0D) - 60;
		anInt1941 = 0;
		macroCameraX = (int) (Math.random() * 80.0D) - 40;
		npcCount = 0;
		for (local3506 = 0; local3506 < 2048; local3506++) {
			players[local3506] = null;
			Static115.aClass3_Sub15Array1[local3506] = null;
		}
		for (local3506 = 0; local3506 < 32768; local3506++) {
			npcs[local3506] = null;
		}
		localPlayer = players[2047] = new ClientPlayer();
		Static217.aClass69_116.method2278();
		Static99.aClass69_64.method2278();
		if (groundObj != null) {
			for (local3506 = 0; local3506 < 4; local3506++) {
				for (@Pc(3663) int local3663 = 0; local3663 < 104; local3663++) {
					for (@Pc(3670) int local3670 = 0; local3670 < 104; local3670++) {
						groundObj[local3506][local3663][local3670] = null;
					}
				}
			}
		}
		aClass69_27 = new LinkList();
		Static166.anInt4054 = 0;
		Static9.anInt178 = 0;
		Static8.method121();
		Static20.method601();
		Static133.anInt5230 = 0;
		Static233.anInt5217 = 0;
		Static265.anInt5765 = 0;
		Static231.anInt5203 = 0;
		Static248.anInt4232 = 0;
		Static245.anInt5375 = 0;
		Static57.anInt1744 = 0;
		Static251.anInt5449 = 0;
		Static113.anInt4612 = 0;
		Static233.anInt5225 = 0;
		for (local3506 = 0; local3506 < varcInt.length; local3506++) {
			varcInt[local3506] = -1;
		}
		if (toplevelinterface != -1) {
			IfType.closeInterface(toplevelinterface);
		}
		for (@Pc(3755) SubInterface local3755 = (SubInterface) aClass133_9.search(); local3755 != null; local3755 = (SubInterface) aClass133_9.findnext()) {
			closeSubInterface(true, local3755);
		}
		toplevelinterface = -1;
		aClass133_9 = new HashTable(8);
		IfType.method1287();
		Static39.aClass13_10 = null;
		isMenuOpen = false;
		menuNumEntries = 0;
		aClass59_2.method1950(new int[] { 0, 0, 0, 0, 0 }, -1, false, null, -1);
		for (local3506 = 0; local3506 < 8; local3506++) {
			Static160.aClass100Array121[local3506] = null;
			Static1.aBooleanArray1[local3506] = false;
			Static191.anIntArray388[local3506] = -1;
		}
		ClientInvCache.deleteAll();
		Static19.aBoolean43 = true;
		for (local3506 = 0; local3506 < 100; local3506++) {
			componentRedrawRequested1[local3506] = true;
		}
		Static214.anInt5577 = 0;
		Static199.aClass3_Sub22Array1 = null;
		Static15.aClass100_87 = null;
		for (local3506 = 0; local3506 < 6; local3506++) {
			Static229.aClass136Array1[local3506] = new StockMarketOffer();
		}
		for (local3506 = 0; local3506 < 25; local3506++) {
			statEffectiveLevel[local3506] = 0;
			statBaseLevel[local3506] = 0;
			statXP[local3506] = 0;
		}
		if (glRenderer) {
			Static86.method1799();
		}
		sendCamera = true;
		Static189.anInt4443 = 0;
		Static195.aClass100_859 = Text.aClass100_560;
		Static127.aBoolean160 = false;
		aShortArray88 = Static62.aShortArray19 = Static232.aShortArray74 = Static259.aShortArray87 = new short[256];
		Static114.method4637();
		Static261.aBoolean298 = false;
		method1373();
	}

	@OriginalMember(owner = "client!jm", name = "a", descriptor = "(Z)V")
	public static void method2460() {
		if (toplevelinterface != -1) {
			animateInterface(toplevelinterface);
		}
		for (@Pc(15) int local15 = 0; local15 < componentDrawCount; local15++) {
			if (componentRedrawRequested1[local15]) {
				componentRedrawRequested2[local15] = true;
			}
			Static223.aBooleanArray116[local15] = componentRedrawRequested1[local15];
			componentRedrawRequested1[local15] = false;
		}
		minimenuMouseOverX = -1;
		hoveredSlotParent = null;
		Static182.anInt4311 = loopCycle;
		if (glRenderer) {
			Static263.aBoolean299 = true;
		}
		minimenuMouseOverY = -1;
		if (toplevelinterface != -1) {
			componentDrawCount = 0;
			method182();
		}
		if (glRenderer) {
			Static46.method1177();
		} else {
			Pix2D.method2503();
		}
		anInt4247 = 0;
	}

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(IIB)V")
	public static void method4650(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (midiVolume != 0 && arg1 != -1) {
			MidiManager.play(jingles, arg1, midiVolume);
			aBoolean173 = true;
		}
	}

	@OriginalMember(owner = "client!wi", name = "c", descriptor = "(II)Z")
	public static boolean method4640(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return false;
		}
		@Pc(12) int local12 = menuAction[arg0];
		if (local12 >= 2000) {
			local12 -= 2000;
		}
		return local12 == 1003;
	}

	@OriginalMember(owner = "client!we", name = "b", descriptor = "(BI)V")
	public static void purgeServerActive(@OriginalArg(1) int arg0) {
		for (@Pc(11) Linkable active = serverActive.search(); active != null; active = serverActive.findnext()) {
			if ((active.key >> 48 & 0xFFFFL) == (long) arg0) {
				active.unlink();
			}
		}
	}

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "(ZB)V")
	public static void method3712(@OriginalArg(0) boolean arg0) {
		method4017(anInt5554, arg0, toplevelinterface, anInt1448);
	}

	@OriginalMember(owner = "client!va", name = "a", descriptor = "(IZILclient!be;)V")
	public static void addComponentOptions(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) IfType arg2) {
		if (arg2.buttonType == 1) {
			addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, 0, (short) 8, arg2.buttonText, arg2.parentId);
		}
		@Pc(47) JagString local47;
		if (arg2.buttonType == 2 && !targetMode) {
			local47 = method1963(arg2);
			if (local47 != null) {
				addMenuOption(-1, 0L, JagString.join(new JagString[] { aClass100_332, arg2.targetBase}), -1, (short) 32, local47, arg2.parentId);
			}
		}
		if (arg2.buttonType == 3) {
			addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, 0, (short) 28, Text.aClass100_249, arg2.parentId);
		}
		if (arg2.buttonType == 4) {
			addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, 0, (short) 59, arg2.buttonText, arg2.parentId);
		}
		if (arg2.buttonType == 5) {
			addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, 0, (short) 51, arg2.buttonText, arg2.parentId);
		}
		if (arg2.buttonType == 6 && Static39.aClass13_10 == null) {
			addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, -1, (short) 41, arg2.buttonText, arg2.parentId);
		}
		@Pc(173) int local173;
		@Pc(171) int local171;
		if (arg2.type == 2) {
			local171 = 0;
			for (local173 = 0; local173 < arg2.height; local173++) {
				for (@Pc(183) int local183 = 0; local183 < arg2.width; local183++) {
					@Pc(195) int local195 = (arg2.marginX + 32) * local183;
					@Pc(202) int local202 = (arg2.marginY + 32) * local173;
					if (local171 < 20) {
						local202 += arg2.invBackgroundY[local171];
						local195 += arg2.invBackgroundX[local171];
					}
					if (arg1 >= local195 && local202 <= arg0 && local195 + 32 > arg1 && local202 + 32 > arg0) {
						hoveredSlotParent = arg2;
						anInt588 = local171;
						if (arg2.linkObjNumber[local171] > 0) {
							@Pc(267) ServerActive local267 = getActive(arg2);
							@Pc(276) ObjType local276 = ObjType.list(arg2.linkObjNumber[local171] - 1);
							if (Static260.anInt5014 == 1 && local267.method510()) {
								if (Static224.anInt5062 != arg2.parentId || Static185.anInt4370 != local171) {
									addMenuOption(-1, (long) local276.id, JagString.join(new JagString[] { Static34.aClass100_203, Static223.aClass100_947, local276.name}), local171, (short) 40, Text.aClass100_937, arg2.parentId);
								}
							} else if (targetMode && local267.method510()) {
								@Pc(596) ParamType local596 = Static121.anInt3039 == -1 ? null : ParamType.list(Static121.anInt3039);
								if ((Static274.anInt4999 & 0x10) != 0 && (local596 == null || local276.method1829(local596.anInt2667, Static121.anInt3039) != local596.anInt2667)) {
									addMenuOption(Static246.anInt5393, (long) local276.id, JagString.join(new JagString[] { Static78.aClass100_466, Static223.aClass100_947, local276.name}), local171, (short) 3, Static102.aClass100_545, arg2.parentId);
								}
							} else {
								@Pc(296) JagString[] local296 = local276.aClass100Array71;
								if (Static208.aBoolean237) {
									local296 = Static279.method4664(local296);
								}
								@Pc(309) int local309;
								@Pc(334) byte local334;
								if (local267.method510()) {
									for (local309 = 4; local309 >= 3; local309--) {
										if (local296 != null && local296[local309] != null) {
											if (local309 == 3) {
												local334 = 35;
											} else {
												local334 = 58;
											}
											addMenuOption(-1, (long) local276.id, JagString.join(new JagString[] { Static8.aClass100_32, local276.name}), local171, local334, local296[local309], arg2.parentId);
										}
									}
								}
								if (local267.method507()) {
									addMenuOption(Static169.anInt4075, (long) local276.id, JagString.join(new JagString[] { Static8.aClass100_32, local276.name}), local171, (short) 22, Text.aClass100_937, arg2.parentId);
								}
								if (local267.method510() && local296 != null) {
									for (local309 = 2; local309 >= 0; local309--) {
										if (local296[local309] != null) {
											local334 = 0;
											if (local309 == 0) {
												local334 = 47;
											}
											if (local309 == 1) {
												local334 = 5;
											}
											if (local309 == 2) {
												local334 = 43;
											}
											addMenuOption(-1, (long) local276.id, JagString.join(new JagString[] { Static8.aClass100_32, local276.name}), local171, local334, local296[local309], arg2.parentId);
										}
									}
								}
								local296 = arg2.iop;
								if (Static208.aBoolean237) {
									local296 = Static279.method4664(local296);
								}
								if (local296 != null) {
									for (local309 = 4; local309 >= 0; local309--) {
										if (local296[local309] != null) {
											local334 = 0;
											if (local309 == 0) {
												local334 = 25;
											}
											if (local309 == 1) {
												local334 = 23;
											}
											if (local309 == 2) {
												local334 = 48;
											}
											if (local309 == 3) {
												local334 = 7;
											}
											if (local309 == 4) {
												local334 = 13;
											}
											addMenuOption(-1, (long) local276.id, JagString.join(new JagString[] { Static8.aClass100_32, local276.name}), local171, local334, local296[local309], arg2.parentId);
										}
									}
								}
								addMenuOption(Static225.anInt5073, (long) local276.id, JagString.join(new JagString[] { Static8.aClass100_32, local276.name}), local171, (short) 1006, Text.aClass100_675, arg2.parentId);
							}
						}
					}
					local171++;
				}
			}
		}
		if (!arg2.v3) {
			return;
		}
		if (!targetMode) {
			for (local171 = 9; local171 >= 5; local171--) {
				@Pc(765) JagString local765 = method3677(arg2, local171);
				if (local765 != null) {
					addMenuOption(method118(local171, arg2), (long) (local171 + 1), arg2.aClass100_88, arg2.subId, (short) 1003, local765, arg2.parentId);
				}
			}
			local47 = method1963(arg2);
			if (local47 != null) {
				addMenuOption(-1, 0L, arg2.aClass100_88, arg2.subId, (short) 32, local47, arg2.parentId);
			}
			for (local173 = 4; local173 >= 0; local173--) {
				@Pc(828) JagString local828 = method3677(arg2, local173);
				if (local828 != null) {
					addMenuOption(method118(local173, arg2), (long) (local173 + 1), arg2.aClass100_88, arg2.subId, (short) 9, local828, arg2.parentId);
				}
			}
			if (getActive(arg2).method506()) {
				addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, arg2.subId, (short) 41, Text.CONTINUE, arg2.parentId);
			}
		} else if (getActive(arg2).method508() && (Static274.anInt4999 & 0x20) != 0) {
			addMenuOption(Static246.anInt5393, 0L, JagString.join(new JagString[] { Static78.aClass100_466, Static201.aClass100_408, arg2.aClass100_88 }), arg2.subId, (short) 12, Static102.aClass100_545, arg2.parentId);
		}
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(IIIZIII)V")
	public static void method4000(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(3) int local3 = 0;
		@Pc(5) MapMarker[] local5 = Static143.aClass102Array1;
		while (local5.length > local3) {
			@Pc(17) MapMarker local17 = local5[local3];
			if (local17 != null && local17.anInt4058 == 2) {
				getOverlayPos(arg0 >> 1, arg4, (local17.anInt4046 - mapBuildBaseZ << 7) + local17.anInt4047, local17.anInt4050 * 2, arg2 >> 1, local17.anInt4045 + (local17.anInt4053 - mapBuildBaseX << 7), arg3);
				if (projectY > -1 && loopCycle % 20 < 10) {
					Static276.aClass3_Sub2_Sub1Array11[local17.anInt4048].plotSprite(arg1 + projectY - 12, arg5 + -28 - -projectX);
				}
			}
			local3++;
		}
	}

	@OriginalMember(owner = "client!t", name = "a", descriptor = "(ZB)V")
	public static void method4002(@OriginalArg(0) boolean arg0) {
		@Pc(19) byte local19;
		@Pc(21) byte[][] local21;
		if (glRenderer && arg0) {
			local21 = aByteArrayArray4;
			local19 = 1;
		} else {
			local19 = 4;
			local21 = aByteArrayArray11;
		}
		for (@Pc(29) int local29 = 0; local29 < local19; local29++) {
			doAudio();
			for (@Pc(36) int local36 = 0; local36 < 13; local36++) {
				for (@Pc(43) int local43 = 0; local43 < 13; local43++) {
					@Pc(56) int local56 = Static187.anIntArrayArrayArray18[local29][local36][local43];
					if (local56 != -1) {
						@Pc(67) int local67 = local56 >> 24 & 0x3;
						if (!arg0 || local67 == 0) {
							@Pc(77) int local77 = local56 >> 1 & 0x3;
							@Pc(83) int local83 = local56 >> 14 & 0x3FF;
							@Pc(89) int local89 = local56 >> 3 & 0x7FF;
							@Pc(99) int local99 = local89 / 8 + (local83 / 8 << 8);
							for (@Pc(101) int local101 = 0; local101 < mapBuildIndex.length; local101++) {
								if (mapBuildIndex[local101] == local99 && local21[local101] != null) {
									ClientBuild.method3771(levelCollisionMap, local29, local21[local101], local67, local77, local36 * 8, local43 * 8, arg0, (local83 & 0x7) * 8, (local89 & 0x7) * 8);
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!jl", name = "a", descriptor = "(IIIII)V")
	public static void method2448(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		out.pos = 0;
		out.p1(147);
		out.p1(arg2);
		out.p1(arg3);
		out.p2(arg0);
		out.p2(arg1);
		accountCreateWaitingTime = 0;
		accountCreateFailCount = 0;
		accountCreateStep = 1;
		accountCreateError = -3;
	}

	@OriginalMember(owner = "client!jl", name = "c", descriptor = "(I)V")
	public static void method2450() {
		@Pc(9) int local9 = Static248.anInt4232 * 128 + 64;
		@Pc(15) int local15 = Static245.anInt5375 * 128 + 64;
		@Pc(23) int local23 = getAvH(minusedlevel, local15, local9) - Static231.anInt5203;
		if (Static113.anInt4612 >= 100) {
			anInt3439 = Static245.anInt5375 * 128 + 64;
			anInt3302 = Static248.anInt4232 * 128 + 64;
			anInt40 = getAvH(minusedlevel, anInt3439, anInt3302) - Static231.anInt5203;
		} else {
			if (anInt3439 < local15) {
				anInt3439 += Static233.anInt5225 + Static113.anInt4612 * (local15 - anInt3439) / 1000;
				if (anInt3439 > local15) {
					anInt3439 = local15;
				}
			}
			if (anInt40 < local23) {
				anInt40 += (local23 - anInt40) * Static113.anInt4612 / 1000 + Static233.anInt5225;
				if (anInt40 > local23) {
					anInt40 = local23;
				}
			}
			if (anInt3439 > local15) {
				anInt3439 -= Static233.anInt5225 + (anInt3439 - local15) * Static113.anInt4612 / 1000;
				if (anInt3439 < local15) {
					anInt3439 = local15;
				}
			}
			if (anInt3302 < local9) {
				anInt3302 += Static233.anInt5225 + Static113.anInt4612 * (local9 - anInt3302) / 1000;
				if (local9 < anInt3302) {
					anInt3302 = local9;
				}
			}
			if (local23 < anInt40) {
				anInt40 -= (anInt40 - local23) * Static113.anInt4612 / 1000 + Static233.anInt5225;
				if (local23 > anInt40) {
					anInt40 = local23;
				}
			}
			if (anInt3302 > local9) {
				anInt3302 -= Static233.anInt5225 + (anInt3302 - local9) * Static113.anInt4612 / 1000;
				if (local9 > anInt3302) {
					anInt3302 = local9;
				}
			}
		}
		local9 = Static265.anInt5765 * 128 + 64;
		local15 = Static251.anInt5449 * 128 + 64;
		local23 = getAvH(minusedlevel, local15, local9) - Static57.anInt1744;
		@Pc(236) int local236 = local23 - anInt40;
		@Pc(241) int local241 = local9 - anInt3302;
		@Pc(246) int local246 = local15 - anInt3439;
		@Pc(257) int local257 = (int) Math.sqrt((double) (local246 * local246 + local241 * local241));
		@Pc(268) int local268 = (int) (Math.atan2((double) local236, (double) local257) * 325.949D) & 0x7FF;
		if (local268 < 128) {
			local268 = 128;
		}
		if (local268 > 383) {
			local268 = 383;
		}
		@Pc(292) int local292 = (int) (-325.949D * Math.atan2((double) local246, (double) local241)) & 0x7FF;
		if (anInt5333 < local268) {
			anInt5333 += Static133.anInt5230 + Static233.anInt5217 * (local268 - anInt5333) / 1000;
			if (anInt5333 > local268) {
				anInt5333 = local268;
			}
		}
		if (anInt5333 > local268) {
			anInt5333 -= (anInt5333 - local268) * Static233.anInt5217 / 1000 + Static133.anInt5230;
			if (anInt5333 < local268) {
				anInt5333 = local268;
			}
		}
		@Pc(350) int local350 = local292 - anInt4358;
		if (local350 > 1024) {
			local350 -= 2048;
		}
		if (local350 < -1024) {
			local350 += 2048;
		}
		if (local350 > 0) {
			anInt4358 += local350 * Static233.anInt5217 / 1000 + Static133.anInt5230;
			anInt4358 &= 0x7FF;
		}
		if (local350 < 0) {
			anInt4358 -= Static233.anInt5217 * -local350 / 1000 + Static133.anInt5230;
			anInt4358 &= 0x7FF;
		}
		@Pc(404) int local404 = local292 - anInt4358;
		if (local404 > 1024) {
			local404 -= 2048;
		}
		if (local404 < -1024) {
			local404 += 2048;
		}
		if (local404 < 0 && local350 > 0 || local404 > 0 && local350 < 0) {
			anInt4358 = local292;
		}
	}

	@OriginalMember(owner = "client!kd", name = "a", descriptor = "(Ljava/lang/String;B)V")
	public static void method2577(@OriginalArg(0) String arg0) {
		System.out.println("Bad " + arg0 + ", Usage: worldid, <live/rc/wip>, <english/german>, <game0/game1>");
		System.exit(1);
	}

	@OriginalMember(owner = "client!j", name = "a", descriptor = "(BZLclient!na;)V")
	public static void messageBox(@OriginalArg(1) boolean arg0, @OriginalArg(2) JagString arg1) {
		@Pc(24) int local24 = Static215.aClass3_Sub2_Sub9_32.method2856(arg1, 250);
		@Pc(31) int local31 = Static215.aClass3_Sub2_Sub9_32.method2860(arg1, 250) * 13;
		if (glRenderer) {
			Static46.method1186(6, 6, local24 + 4 + 4, local31 + 8, 0);
			Static46.method1179(6, 6, local24 + 4 + 4, local31 + 4 + 4, 16777215);
		} else {
			Pix2D.method2495(6, 6, local24 + 4 + 4, local31 + 8, 0);
			Pix2D.method2483(6, 6, local24 + 8, 4 + 4 + local31, 16777215);
		}
		Static215.aClass3_Sub2_Sub9_32.method2852(arg1, 10, 10, local24, local31, 16777215, -1, 1, 1, 0);
		dirtyArea(6, local24 + 8, 6, local31 + 4 + 4);
		if (!arg0) {
			method2407(10, 10, local31, local24);
		} else if (glRenderer) {
			Static239.method4153();
		} else {
			try {
				@Pc(159) Graphics local159 = canvas.getGraphics();
				GameShell.drawArea.method4186(local159);
			} catch (@Pc(167) Exception local167) {
				canvas.repaint();
			}
		}
	}

	@OriginalMember(owner = "client!jg", name = "a", descriptor = "(IBIII)V")
	public static void method2407(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		for (@Pc(3) int local3 = 0; local3 < componentDrawCount; local3++) {
			if (arg0 < componentDrawX[local3] + componentDrawWidth[local3] && arg0 + arg3 > componentDrawX[local3] && componentDrawY[local3] + componentDrawHeight[local3] > arg1 && componentDrawY[local3] < arg2 + arg1) {
				componentRedrawRequested2[local3] = true;
			}
		}
	}

	@OriginalMember(owner = "client!mh", name = "h", descriptor = "(B)V")
	public static void method3013() {
		@Pc(10) int local10 = aByteArrayArray15.length;
		for (@Pc(16) int local16 = 0; local16 < local10; local16++) {
			if (aByteArrayArray15[local16] != null) {
				@Pc(25) int local25 = -1;
				for (@Pc(27) int local27 = 0; local27 < Static157.anInt3811; local27++) {
					if (Static217.anIntArray434[local27] == mapBuildIndex[local16]) {
						local25 = local27;
						break;
					}
				}
				if (local25 == -1) {
					Static217.anIntArray434[Static157.anInt3811] = mapBuildIndex[local16];
					local25 = Static157.anInt3811++;
				}
				@Pc(67) int local67 = 0;
				@Pc(74) Packet local74 = new Packet(aByteArrayArray15[local16]);
				while (local74.pos < aByteArrayArray15[local16].length && local67 < 511) {
					@Pc(97) int local97 = local67++ << 6 | local25;
					@Pc(103) int local103 = local74.g2();
					@Pc(107) int local107 = local103 >> 14;
					@Pc(113) int local113 = local103 >> 7 & 0x3F;
					@Pc(125) int local125 = local113 + (mapBuildIndex[local16] >> 8) * 64 - mapBuildBaseX;
					@Pc(129) int local129 = local103 & 0x3F;
					@Pc(142) int local142 = local129 + (mapBuildIndex[local16] & 0xFF) * 64 - mapBuildBaseZ;
					@Pc(148) NPCType local148 = NPCType.list(local74.g2());
					if (npcs[local97] == null && (local148.aByte10 & 0x1) > 0 && local107 == Static41.anInt1316 && local125 >= 0 && local148.anInt3713 + local125 < 104 && local142 >= 0 && local142 + local148.anInt3713 < 104) {
						npcs[local97] = new ClientNPC();
						@Pc(198) ClientNPC local198 = npcs[local97];
						npcIds[npcCount++] = local97;
						local198.cycle = loopCycle;
						local198.method2698(local148);
						local198.method2692(local198.aClass96_1.anInt3713);
						local198.anInt3400 = local198.anInt3381 = Static56.anIntArray141[local198.aClass96_1.aByte11];
						local198.anInt3376 = local198.aClass96_1.anInt3733;
						if (local198.anInt3376 == 0) {
							local198.anInt3381 = 0;
						}
						local198.anInt3365 = local198.aClass96_1.anInt3737;
						local198.method2683(local198.method2693(), local125, local142, true);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void locChangeCreate(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		@Pc(9) LocChange local9 = null;
		for (@Pc(14) LocChange local14 = (LocChange) aClass69_27.head(); local14 != null; local14 = (LocChange) aClass69_27.next()) {
			if (local14.anInt918 == arg0 && arg3 == local14.anInt928 && local14.anInt916 == arg1 && arg6 == local14.anInt927) {
				local9 = local14;
				break;
			}
		}
		if (local9 == null) {
			local9 = new LocChange();
			local9.anInt928 = arg3;
			local9.anInt916 = arg1;
			local9.anInt918 = arg0;
			local9.anInt927 = arg6;
			locChangeSetOld(local9);
			aClass69_27.push(local9);
		}
		local9.anInt926 = arg7;
		local9.startTime = arg8;
		local9.endTime = arg4;
		local9.oldType = arg5;
		local9.anInt922 = arg2;
	}

	@OriginalMember(owner = "client!sf", name = "a", descriptor = "(ILclient!cd;)V")
	public static void locChangeSetOld(@OriginalArg(1) LocChange arg0) {
		@Pc(5) long local5 = 0L;
		@Pc(7) int local7 = -1;
		@Pc(14) int local14 = 0;
		if (arg0.anInt927 == 0) {
			local5 = World.method4521(arg0.anInt918, arg0.anInt928, arg0.anInt916);
		}
		@Pc(31) int local31 = 0;
		if (arg0.anInt927 == 1) {
			local5 = World.method2703(arg0.anInt918, arg0.anInt928, arg0.anInt916);
		}
		if (arg0.anInt927 == 2) {
			local5 = World.method899(arg0.anInt918, arg0.anInt928, arg0.anInt916);
		}
		if (arg0.anInt927 == 3) {
			local5 = World.method602(arg0.anInt918, arg0.anInt928, arg0.anInt916);
		}
		if (local5 != 0L) {
			local7 = Integer.MAX_VALUE & (int) (local5 >>> 32);
			local31 = (int) local5 >> 20 & 0x3;
			local14 = (int) local5 >> 14 & 0x1F;
		}
		arg0.newType = local7;
		arg0.anInt920 = local14;
		arg0.anInt923 = local31;
	}

	@OriginalMember(owner = "client!ni", name = "a", descriptor = "(ILclient!na;I)V")
	public static void method3221(@OriginalArg(1) JagString arg0, @OriginalArg(2) int arg1) {
		out.p1Enc(188);
		out.method2216(arg1);
		out.p8(arg0.method3158());
	}

	@OriginalMember(owner = "client!nh", name = "a", descriptor = "(IIIIIIIIIIIII)V")
	public static void method2574(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11) {
		@Pc(7) LocMerge local7 = new LocMerge();
		local7.anInt1206 = arg6;
		local7.anInt1205 = arg3;
		local7.anInt1187 = arg1;
		local7.anInt1200 = arg5;
		local7.anInt1192 = arg2;
		local7.anInt1190 = arg8;
		local7.anInt1197 = arg10;
		local7.anInt1204 = arg4;
		local7.anInt1189 = arg11;
		local7.anInt1188 = arg7;
		local7.anInt1191 = arg0;
		local7.anInt1198 = arg9;
		aClass69_77.push(local7);
	}

	@OriginalMember(owner = "client!og", name = "a", descriptor = "(BIILclient!fe;III)V")
	public static void method3326(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) ClientEntity arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		getOverlayPos(arg5, arg1, arg2.z, arg4, arg0, arg2.x, arg3);
	}

	@OriginalMember(owner = "client!pk", name = "i", descriptor = "(I)V")
	public static void method3532() {
		for (@Pc(16) ClientProjNode local16 = (ClientProjNode) Static217.aClass69_116.head(); local16 != null; local16 = (ClientProjNode) Static217.aClass69_116.next()) {
			@Pc(21) ClientProj local21 = local16.aClass8_Sub6_1;
			if (minusedlevel != local21.anInt4810 || local21.anInt4800 < loopCycle) {
				local16.unlink();
			} else if (loopCycle >= local21.anInt4822) {
				if (local21.anInt4819 > 0) {
					@Pc(54) ClientNPC local54 = npcs[local21.anInt4819 - 1];
					if (local54 != null && local54.x >= 0 && local54.x < 13312 && local54.z >= 0 && local54.z < 13312) {
						local21.method3705(local54.z, loopCycle, getAvH(local21.anInt4810, local54.x, local54.z) - local21.anInt4805, local54.x);
					}
				}
				if (local21.anInt4819 < 0) {
					@Pc(102) int local102 = -local21.anInt4819 - 1;
					@Pc(107) ClientPlayer local107;
					if (anInt549 == local102) {
						local107 = localPlayer;
					} else {
						local107 = players[local102];
					}
					if (local107 != null && local107.x >= 0 && local107.x < 13312 && local107.z >= 0 && local107.z < 13312) {
						local21.method3705(local107.z, loopCycle, getAvH(local21.anInt4810, local107.x, local107.z) - local21.anInt4805, local107.x);
					}
				}
				local21.method3704(anInt4247);
				World.addDynamic(minusedlevel, (int) local21.aDouble8, (int) local21.aDouble3, (int) local21.aDouble6, 60, local21, local21.anInt4821, -1L, false);
			}
		}
	}

	@OriginalMember(owner = "client!rj", name = "a", descriptor = "(IIILclient!e;I)V")
	public static void method3767(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) ClientPlayer arg2, @OriginalArg(4) int arg3) {
		if (localPlayer == arg2 || menuNumEntries >= 400) {
			return;
		}
		@Pc(158) JagString local158;
		if (arg2.anInt1671 == 0) {
			@Pc(22) boolean local22 = true;
			if (localPlayer.anInt1667 != -1 && arg2.anInt1667 != -1) {
				@Pc(43) int local43 = arg2.combatLevel < localPlayer.combatLevel ? localPlayer.combatLevel : arg2.combatLevel;
				@Pc(58) int local58 = localPlayer.anInt1667 < arg2.anInt1667 ? localPlayer.anInt1667 : arg2.anInt1667;
				@Pc(69) int local69 = local43 * 10 / 100 + local58 + 5;
				@Pc(76) int local76 = localPlayer.combatLevel - arg2.combatLevel;
				if (local76 < 0) {
					local76 = -local76;
				}
				if (local69 < local76) {
					local22 = false;
				}
			}
			@Pc(95) JagString local95 = game == 1 ? Text.aClass100_746 : Text.aClass100_386;
			if (arg2.combatLevel < arg2.anInt1656) {
				local158 = JagString.join(new JagString[] { arg2.method1264(), local22 ? method2420(arg2.combatLevel, localPlayer.combatLevel) : Static204.aClass100_896, Static123.aClass100_593, local95, JagString.parseInt(arg2.combatLevel), Static78.aClass100_465, JagString.parseInt(arg2.anInt1656 - arg2.combatLevel), Static72.aClass100_448 });
			} else {
				local158 = JagString.join(new JagString[] { arg2.method1264(), local22 ? method2420(arg2.combatLevel, localPlayer.combatLevel) : Static204.aClass100_896, Static123.aClass100_593, local95, JagString.parseInt(arg2.combatLevel), Static72.aClass100_448 });
			}
		} else {
			local158 = JagString.join(new JagString[] { arg2.method1264(), Static123.aClass100_593, Text.aClass100_384, JagString.parseInt(arg2.anInt1671), Static72.aClass100_448 });
		}
		@Pc(275) int local275;
		if (Static260.anInt5014 == 1) {
			addMenuOption(Static169.anInt4075, (long) arg0, JagString.join(new JagString[] { Static34.aClass100_203, Static105.aClass100_561, local158 }), arg3, (short) 1, Text.aClass100_937, arg1);
		} else if (!targetMode) {
			for (local275 = 7; local275 >= 0; local275--) {
				if (Static160.aClass100Array121[local275] != null) {
					@Pc(291) short local291 = 0;
					if (game == 0 && Static160.aClass100Array121[local275].equalsIgnoreCase(Text.aClass100_1055)) {
						if (arg2.combatLevel > localPlayer.combatLevel) {
							local291 = 2000;
						}
						if (localPlayer.anInt1650 != 0 && arg2.anInt1650 != 0) {
							if (localPlayer.anInt1650 == arg2.anInt1650) {
								local291 = 2000;
							} else {
								local291 = 0;
							}
						}
					} else if (Static1.aBooleanArray1[local275]) {
						local291 = 2000;
					}
					@Pc(353) short local353 = Static5.aShortArray2[local275];
					@Pc(358) short local358 = (short) (local353 + local291);
					addMenuOption(Static191.anIntArray388[local275], (long) arg0, JagString.join(new JagString[] { Static204.aClass100_896, local158 }), arg3, local358, Static160.aClass100Array121[local275], arg1);
				}
			}
		} else if ((Static274.anInt4999 & 0x8) != 0) {
			addMenuOption(Static246.anInt5393, (long) arg0, JagString.join(new JagString[] { Static78.aClass100_466, Static105.aClass100_561, local158 }), arg3, (short) 15, Static102.aClass100_545, arg1);
		}
		for (local275 = 0; local275 < menuNumEntries; local275++) {
			if (menuAction[local275] == 60) {
				aClass100Array160[local275] = JagString.join(new JagString[] { Static204.aClass100_896, local158 });
				break;
			}
		}
	}

	@OriginalMember(owner = "client!rj", name = "f", descriptor = "(B)V")
	public static void clearCaches() {
		FloType.method4612();
		FluType.method1308();
		IdkType.method3999();
		LocType.method1854();
		NPCType.method3673();
		ObjType.method3302();
		SeqType.method350();
		SpotType.method4249();
		VarBitType.method1295();
		VarpType.method4266();
		BasType.method2433();
		MsiType.method3653();
		LightType.method1695();
		CursorType.method351();
		Static230.method3947();
		IfType.method2764();
		if (modewhat != 0) {
			for (@Pc(54) int local54 = 0; local54 < Static51.aByteArrayArray8.length; local54++) {
				Static51.aByteArrayArray8[local54] = null;
			}
			Static105.anInt2863 = 0;
		}
		Static181.method3347();
		Static90.method1857();
		Static139.aClass99_22.method3104();
		if (!glRenderer) {
			((WorldTextureProvider) Pix3D.anInterface1_2).method3247();
		}
		Static105.aClass54_9.method1815();
		anims.method4499();
		bases.method4499();
		interfaces.method4499();
		jagFX.method4499();
		maps.method4499();
		songs.method4499();
		models.method4499();
		sprites.method4499();
		binary.method4499();
		jingles.method4499();
		scripts.method4499();
		Static73.aClass99_10.method3104();
	}

	@OriginalMember(owner = "client!ub", name = "b", descriptor = "(IIIIIII)V")
	public static void method4246(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
		@Pc(8) IfType local8 = IfType.method1418(arg0, arg1);
		if (local8 != null && local8.ontargetenter != null) {
			@Pc(19) HookReq local19 = new HookReq();
			local19.component = local8;
			local19.onop = local8.ontargetenter;
			ScriptRunner.executeScript(local19);
		}
		Static15.anInt506 = arg1;
		Static121.anInt3039 = arg3;
		Static98.anInt2512 = arg0;
		Static274.anInt4999 = arg2;
		targetMode = true;
		Static246.anInt5393 = arg4;
		Static35.anInt1092 = arg5;
		componentUpdated(local8);
	}

	@OriginalMember(owner = "client!ui", name = "a", descriptor = "(IIZIII)V")
	public static void gameDrawMain(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		Static136.anInt3325++;
		method3711();
		if (!arg1) {
			method964(true);
			method3240(true);
			method964(false);
		}
		method3240(false);
		if (!arg1) {
			method3532();
		}
		addMapAnim();
		if (glRenderer) {
			Static115.method2314(arg3, arg4, arg0, arg2, true);
			arg2 = Static7.anInt983;
			arg4 = Static24.anInt773;
			arg3 = Static166.anInt4055;
			arg0 = Static245.anInt5377;
		}
		@Pc(59) int local59;
		@Pc(57) int local57;
		if (anInt5096 == 1) {
			local57 = macroCameraX + anInt1747 & 0x7FF;
			local59 = anInt2031;
			if (local59 < ClientBuild.anInt5245 / 256) {
				local59 = ClientBuild.anInt5245 / 256;
			}
			if (Static176.aBooleanArray95[4] && Static276.anIntArray564[4] + 128 > local59) {
				local59 = Static276.anIntArray564[4] + 128;
			}
			Static18.method555(Static81.anInt2223, arg0, getAvH(minusedlevel, localPlayer.x, localPlayer.z) - 50, 600 - -(local59 * 3), local57, Static111.anInt2900, local59);
		}
		local57 = anInt40;
		local59 = anInt3439;
		@Pc(121) int local121 = anInt3302;
		@Pc(123) int local123 = anInt5333;
		@Pc(125) int local125 = anInt4358;
		@Pc(127) int local127;
		@Pc(171) int local171;
		for (local127 = 0; local127 < 5; local127++) {
			if (Static176.aBooleanArray95[local127]) {
				local171 = (int) ((double) -Static222.anIntArray437[local127] + (double) (Static222.anIntArray437[local127] * 2 + 1) * Math.random() + Math.sin((double) camShakeCycle[local127] * ((double) Static202.anIntArray424[local127] / 100.0D)) * (double) Static276.anIntArray564[local127]);
				if (local127 == 3) {
					anInt4358 = local171 + anInt4358 & 0x7FF;
				}
				if (local127 == 4) {
					anInt5333 += local171;
					if (anInt5333 < 128) {
						anInt5333 = 128;
					}
					if (anInt5333 > 383) {
						anInt5333 = 383;
					}
				}
				if (local127 == 2) {
					anInt3302 += local171;
				}
				if (local127 == 1) {
					anInt40 += local171;
				}
				if (local127 == 0) {
					anInt3439 += local171;
				}
			}
		}
		method4302();
		if (glRenderer) {
			Static46.method1187(arg2, arg4, arg2 + arg3, arg4 - -arg0);
			@Pc(248) float local248 = (float) anInt5333 * 0.17578125F;
			@Pc(253) float local253 = (float) anInt4358 * 0.17578125F;
			if (anInt5096 == 3) {
				local248 = Static146.aFloat15 * 360.0F / 6.2831855F;
				local253 = Static84.aFloat10 * 360.0F / 6.2831855F;
			}
			Static239.method4171(arg2, arg4, arg3, arg0, arg3 / 2 + arg2, arg4 - -(arg0 / 2), local248, local253, Static223.anInt5029, Static223.anInt5029);
		} else {
			Pix2D.method2496(arg2, arg4, arg3 + arg2, arg0 + arg4);
			Pix3D.method1908();
		}
		if (isMenuOpen || anInt3751 < arg2 || anInt3751 >= arg3 + arg2 || arg4 > anInt1892 || arg0 + arg4 <= anInt1892) {
			Static39.aBoolean77 = false;
			Static2.anInt7 = 0;
		} else {
			Static39.aBoolean77 = true;
			Static2.anInt7 = 0;
			local171 = Static247.anInt5405;
			@Pc(344) int local344 = Static1.anInt4;
			local127 = Static240.anInt5334;
			Static150.anInt3582 = local127 + (local171 - local127) * (-arg2 + anInt3751) / arg3;
			@Pc(361) int local361 = Static148.anInt3535;
			Static34.anInt1053 = (local361 - local344) * (anInt1892 - arg4) / arg0 + local344;
		}
		doAudio();
		@Pc(387) byte local387 = Static236.method4047() == 2 ? (byte) Static136.anInt3325 : 1;
		if (glRenderer) {
			Static239.method4173();
			Static239.method4158(true);
			Static239.method4154(true);
			if (state == 10) {
				local171 = Static103.method2235(anInt4247, anInt3302 >> 10, Static113.anInt4609, anInt3439 >> 10);
			} else {
				local171 = Static103.method2235(anInt4247, localPlayer.routeZ[0] >> 3, Static113.anInt4609, localPlayer.routeX[0] >> 3);
			}
			World.method2394(loopCycle, !Static11.aBoolean15);
			Static239.method4176(local171);
			Static143.method2731(anInt5333, anInt3302, anInt40, anInt3439, anInt4358);
			Static239.anInt5323 = loopCycle;
			World.method2954(anInt3439, anInt40, anInt3302, anInt5333, anInt4358, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, minusedlevel + 1, local387, localPlayer.x >> 7, localPlayer.z >> 7);
			Static263.aBoolean299 = true;
			World.method2390();
			Static143.method2731(0, 0, 0, 0, 0);
			doAudio();
			World.method3858();
			method2726(arg4, arg3, arg2, Static223.anInt5029, arg0, Static223.anInt5029);
			method4000(arg3, arg2, arg0, Static223.anInt5029, Static223.anInt5029, arg4);
		} else {
			Pix2D.method2495(arg2, arg4, arg3, arg0, 0);
			World.method2954(anInt3439, anInt40, anInt3302, anInt5333, anInt4358, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, minusedlevel + 1, local387, localPlayer.x >> 7, localPlayer.z >> 7);
			doAudio();
			World.method3858();
			method2726(arg4, arg3, arg2, 256, arg0, 256);
			method4000(arg3, arg2, arg0, 256, 256, arg4);
		}
		((WorldTextureProvider) Pix3D.anInterface1_2).method3239(anInt4247);
		otherOverlays(arg3, arg4, arg0, arg2);
		anInt5333 = local123;
		anInt3302 = local121;
		anInt40 = local57;
		anInt3439 = local59;
		anInt4358 = local125;
		if (Static19.aBoolean43 && js5Net.method2328() == 0) {
			Static19.aBoolean43 = false;
		}
		if (Static19.aBoolean43) {
			if (glRenderer) {
				Static46.method1186(arg2, arg4, arg3, arg0, 0);
			} else {
				Pix2D.method2495(arg2, arg4, arg3, arg0, 0);
			}
			messageBox(false, Text.aClass100_621);
		}
		if (!arg1 && !Static19.aBoolean43 && !isMenuOpen && arg2 <= anInt3751 && arg3 + arg2 > anInt3751 && arg4 <= anInt1892 && arg0 + arg4 > anInt1892) {
			Static176.method3304(arg4, arg3, arg0, arg2, anInt1892, anInt3751);
		}
	}

	@OriginalMember(owner = "client!rb", name = "a", descriptor = "(I)V")
	public static void method3711() {
		for (@Pc(7) int local7 = 0; local7 < 104; local7++) {
			for (@Pc(14) int local14 = 0; local14 < 104; local14++) {
				Static31.anIntArrayArray6[local7][local14] = 0;
			}
		}
	}

	@OriginalMember(owner = "client!vg", name = "a", descriptor = "(Lclient!na;IIBI)V")
	public static void method4512(@OriginalArg(0) JagString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) IfType local8 = IfType.method1418(arg3, arg1);
		if (local8 == null) {
			return;
		}
		if (local8.onop != null) {
			@Pc(19) HookReq local19 = new HookReq();
			local19.onop = local8.onop;
			local19.component = local8;
			local19.opbase = arg0;
			local19.opindex = arg2;
			ScriptRunner.executeScript(local19);
		}
		@Pc(37) boolean local37 = true;
		if (local8.clientCode > 0) {
			local37 = clientButton(local8);
		}
		if (!local37 || !getActive(local8).method503(arg2 - 1)) {
			return;
		}
		if (arg2 == 1) {
			out.p1Enc(155);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 2) {
			out.p1Enc(196);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 3) {
			out.p1Enc(124);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 4) {
			out.p1Enc(199);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 5) {
			out.p1Enc(234);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 6) {
			out.p1Enc(168);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 7) {
			out.p1Enc(166);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 8) {
			out.p1Enc(64);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 9) {
			out.p1Enc(53);
			out.p4(arg3);
			out.p2(arg1);
		}
		if (arg2 == 10) {
			out.p1Enc(9);
			out.p4(arg3);
			out.p2(arg1);
		}
	}

	@OriginalMember(owner = "client!vg", name = "a", descriptor = "(IILclient!fe;)V")
	public static void method4514(@OriginalArg(0) int arg0, @OriginalArg(2) ClientEntity arg1) {
		if (loopCycle < arg1.anInt3395) {
			method553(arg1);
		} else if (arg1.anInt3386 >= loopCycle) {
			method4665(arg1);
		} else {
			method2247(arg1);
		}
		if (arg1.x < 128 || arg1.z < 128 || arg1.x >= 13184 || arg1.z >= 13184) {
			arg1.anInt3369 = -1;
			arg1.anInt3432 = -1;
			arg1.anInt3395 = 0;
			arg1.anInt3386 = 0;
			arg1.x = arg1.routeX[0] * 128 + arg1.method2693() * 64;
			arg1.z = arg1.routeZ[0] * 128 + arg1.method2693() * 64;
			arg1.method2689();
		}
		if (arg1 == localPlayer && (arg1.x < 1536 || arg1.z < 1536 || arg1.x >= 11776 || arg1.z >= 11776)) {
			arg1.anInt3432 = -1;
			arg1.anInt3395 = 0;
			arg1.anInt3386 = 0;
			arg1.anInt3369 = -1;
			arg1.x = arg1.routeX[0] * 128 + arg1.method2693() * 64;
			arg1.z = arg1.routeZ[0] * 128 + arg1.method2693() * 64;
			arg1.method2689();
		}
		method949(arg1);
		method879(arg1);
	}

	@OriginalMember(owner = "client!bh", name = "a", descriptor = "(Lclient!fe;Z)V")
	public static void method553(@OriginalArg(0) ClientEntity arg0) {
		@Pc(8) int local8 = arg0.anInt3395 - loopCycle;
		@Pc(20) int local20 = arg0.anInt3380 * 128 + arg0.method2693() * 64;
		@Pc(36) int local36 = arg0.anInt3428 * 128 + arg0.method2693() * 64;
		if (arg0.anInt3431 == 0) {
			arg0.anInt3400 = 1024;
		}
		arg0.x += (local20 - arg0.x) / local8;
		arg0.z += (local36 - arg0.z) / local8;
		if (arg0.anInt3431 == 1) {
			arg0.anInt3400 = 1536;
		}
		arg0.anInt3417 = 0;
		if (arg0.anInt3431 == 2) {
			arg0.anInt3400 = 0;
		}
		if (arg0.anInt3431 == 3) {
			arg0.anInt3400 = 512;
		}
	}

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(Lclient!fe;B)V")
	public static void method4665(@OriginalArg(0) ClientEntity arg0) {
		if (loopCycle == arg0.anInt3386 || arg0.anInt3369 == -1 || arg0.anInt3420 != 0 || arg0.anInt3360 + 1 > SeqType.list(arg0.anInt3369).delay[arg0.anInt3425]) {
			@Pc(35) int local35 = arg0.anInt3386 - arg0.anInt3395;
			@Pc(41) int local41 = loopCycle - arg0.anInt3395;
			@Pc(52) int local52 = arg0.anInt3380 * 128 + arg0.method2693() * 64;
			@Pc(64) int local64 = arg0.anInt3428 * 128 + arg0.method2693() * 64;
			@Pc(75) int local75 = arg0.anInt3416 * 128 + arg0.method2693() * 64;
			@Pc(86) int local86 = arg0.anInt3392 * 128 + arg0.method2693() * 64;
			arg0.x = (local41 * local75 + local52 * (local35 - local41)) / local35;
			arg0.z = (local86 * local41 + local64 * (local35 - local41)) / local35;
		}
		arg0.anInt3417 = 0;
		if (arg0.anInt3431 == 0) {
			arg0.anInt3400 = 1024;
		}
		if (arg0.anInt3431 == 1) {
			arg0.anInt3400 = 1536;
		}
		if (arg0.anInt3431 == 2) {
			arg0.anInt3400 = 0;
		}
		if (arg0.anInt3431 == 3) {
			arg0.anInt3400 = 512;
		}
		arg0.anInt3381 = arg0.anInt3400;
	}

	@OriginalMember(owner = "client!ia", name = "a", descriptor = "(BLclient!fe;)V")
	public static void method2247(@OriginalArg(1) ClientEntity arg0) {
		@Pc(9) BasType local9 = arg0.method2681();
		arg0.anInt3366 = local9.anInt1037;
		if (arg0.anInt3409 == 0) {
			arg0.anInt3417 = 0;
			return;
		}
		if (arg0.anInt3369 != -1 && arg0.anInt3420 == 0) {
			@Pc(40) SeqType local40 = SeqType.list(arg0.anInt3369);
			if (arg0.anInt3405 > 0 && local40.anInt5363 == 0) {
				arg0.anInt3417++;
				return;
			}
			if (arg0.anInt3405 <= 0 && local40.anInt5349 == 0) {
				arg0.anInt3417++;
				return;
			}
		}
		@Pc(79) int local79 = arg0.x;
		@Pc(82) int local82 = arg0.z;
		@Pc(99) int local99 = arg0.routeX[arg0.anInt3409 - 1] * 128 + arg0.method2693() * 64;
		@Pc(116) int local116 = arg0.routeZ[arg0.anInt3409 - 1] * 128 + arg0.method2693() * 64;
		if (local99 - local79 > 256 || local99 - local79 < -256 || local116 - local82 > 256 || local116 - local82 < -256) {
			arg0.x = local99;
			arg0.z = local116;
			return;
		}
		if (local99 <= local79) {
			if (local79 <= local99) {
				if (local116 > local82) {
					arg0.anInt3400 = 1024;
				} else if (local82 > local116) {
					arg0.anInt3400 = 0;
				}
			} else if (local116 > local82) {
				arg0.anInt3400 = 768;
			} else if (local116 < local82) {
				arg0.anInt3400 = 256;
			} else {
				arg0.anInt3400 = 512;
			}
		} else if (local116 > local82) {
			arg0.anInt3400 = 1280;
		} else if (local82 > local116) {
			arg0.anInt3400 = 1792;
		} else {
			arg0.anInt3400 = 1536;
		}
		@Pc(224) int local224 = arg0.anInt3400 - arg0.anInt3381 & 0x7FF;
		@Pc(227) int local227 = local9.anInt1056;
		if (local224 > 1024) {
			local224 -= 2048;
		}
		@Pc(233) boolean local233 = true;
		@Pc(235) byte local235 = 1;
		if (local224 >= -256 && local224 <= 256) {
			local227 = local9.anInt1051;
		} else if (local224 >= 256 && local224 < 768) {
			local227 = local9.anInt1035;
		} else if (local224 >= -768 && local224 <= -256) {
			local227 = local9.anInt1057;
		}
		@Pc(273) int local273 = 4;
		if (local227 == -1) {
			local227 = local9.anInt1051;
		}
		arg0.anInt3366 = local227;
		if (arg0 instanceof ClientNPC) {
			local233 = ((ClientNPC) arg0).aClass96_1.aBoolean181;
		}
		if (local233) {
			if (arg0.anInt3381 != arg0.anInt3400 && arg0.targetId == -1 && arg0.anInt3376 != 0) {
				local273 = 2;
			}
			if (arg0.anInt3409 > 2) {
				local273 = 6;
			}
			if (arg0.anInt3409 > 3) {
				local273 = 8;
			}
			if (arg0.anInt3417 > 0 && arg0.anInt3409 > 1) {
				local273 = 8;
				arg0.anInt3417--;
			}
		} else {
			if (arg0.anInt3409 > 1) {
				local273 = 6;
			}
			if (arg0.anInt3409 > 2) {
				local273 = 8;
			}
			if (arg0.anInt3417 > 0 && arg0.anInt3409 > 1) {
				arg0.anInt3417--;
				local273 = 8;
			}
		}
		if (arg0.aByteArray48[arg0.anInt3409 - 1] == 2) {
			local273 <<= 0x1;
			local235 = 2;
		} else if (arg0.aByteArray48[arg0.anInt3409 - 1] == 0) {
			local235 = 0;
			local273 >>= 0x1;
		}
		if (local273 < 8 || local9.anInt1058 == -1) {
			if (local9.anInt1062 != -1 && local235 == 0) {
				if (local9.anInt1056 == arg0.anInt3366 && local9.anInt1042 != -1) {
					arg0.anInt3366 = local9.anInt1042;
				} else if (local9.anInt1057 == arg0.anInt3366 && local9.anInt1066 != -1) {
					arg0.anInt3366 = local9.anInt1066;
				} else if (local9.anInt1035 == arg0.anInt3366 && local9.anInt1048 != -1) {
					arg0.anInt3366 = local9.anInt1048;
				} else {
					arg0.anInt3366 = local9.anInt1062;
				}
			}
		} else if (local9.anInt1056 == arg0.anInt3366 && local9.anInt1054 != -1) {
			arg0.anInt3366 = local9.anInt1054;
		} else if (arg0.anInt3366 == local9.anInt1057 && local9.anInt1043 != -1) {
			arg0.anInt3366 = local9.anInt1043;
		} else if (arg0.anInt3366 == local9.anInt1035 && local9.anInt1045 != -1) {
			arg0.anInt3366 = local9.anInt1045;
		} else {
			arg0.anInt3366 = local9.anInt1058;
		}
		if (local9.anInt1032 != -1) {
			local273 <<= 0x7;
			if (arg0.anInt3409 == 1) {
				@Pc(594) int local594 = (local99 >= arg0.x ? local99 - arg0.x : -local99 + arg0.x) << 7;
				@Pc(600) int local600 = arg0.anInt3358 * arg0.anInt3358;
				@Pc(622) int local622 = (local116 < arg0.z ? arg0.z - local116 : -arg0.z + local116) << 7;
				@Pc(629) int local629 = local594 > local622 ? local594 : local622;
				@Pc(636) int local636 = local9.anInt1032 * 2 * local629;
				if (local636 < local600) {
					arg0.anInt3358 /= 2;
				} else if (local629 < local600 / 2) {
					arg0.anInt3358 -= local9.anInt1032;
					if (arg0.anInt3358 < 0) {
						arg0.anInt3358 = 0;
					}
				} else if (arg0.anInt3358 < local273) {
					arg0.anInt3358 += local9.anInt1032;
					if (arg0.anInt3358 > local273) {
						arg0.anInt3358 = local273;
					}
				}
			} else if (local273 > arg0.anInt3358) {
				arg0.anInt3358 += local9.anInt1032;
				if (local273 < arg0.anInt3358) {
					arg0.anInt3358 = local273;
				}
			} else if (arg0.anInt3358 > 0) {
				arg0.anInt3358 -= local9.anInt1032;
				if (arg0.anInt3358 < 0) {
					arg0.anInt3358 = 0;
				}
			}
			local273 = arg0.anInt3358 >> 7;
			if (local273 < 1) {
				local273 = 1;
			}
		}
		if (local79 < local99) {
			arg0.x += local273;
			if (local99 < arg0.x) {
				arg0.x = local99;
			}
		} else if (local79 > local99) {
			arg0.x -= local273;
			if (local99 > arg0.x) {
				arg0.x = local99;
			}
		}
		if (local82 < local116) {
			arg0.z += local273;
			if (arg0.z > local116) {
				arg0.z = local116;
			}
		} else if (local116 < local82) {
			arg0.z -= local273;
			if (local116 > arg0.z) {
				arg0.z = local116;
			}
		}
		if (arg0.x == local99 && local116 == arg0.z) {
			arg0.anInt3409--;
			if (arg0.anInt3405 > 0) {
				arg0.anInt3405--;
			}
		}
	}

	@OriginalMember(owner = "client!cm", name = "a", descriptor = "(ILclient!fe;)V")
	public static void method949(@OriginalArg(1) ClientEntity arg0) {
		if (arg0.anInt3376 == 0) {
			return;
		}
		@Pc(13) BasType local13 = arg0.method2681();
		@Pc(43) int local43;
		@Pc(36) int local36;
		if (arg0.targetId != -1 && arg0.targetId < 32768) {
			@Pc(26) ClientNPC local26 = npcs[arg0.targetId];
			if (local26 != null) {
				local36 = arg0.z - local26.z;
				local43 = arg0.x - local26.x;
				if (local43 != 0 || local36 != 0) {
					arg0.anInt3400 = (int) (Math.atan2((double) local43, (double) local36) * 325.949D) & 0x7FF;
				}
			}
		}
		@Pc(94) int local94;
		@Pc(70) int local70;
		if (arg0.targetId >= 32768) {
			local70 = arg0.targetId - 32768;
			if (local70 == anInt549) {
				local70 = 2047;
			}
			@Pc(83) ClientPlayer local83 = players[local70];
			if (local83 != null) {
				local94 = arg0.z - local83.z;
				local36 = arg0.x - local83.x;
				if (local36 != 0 || local94 != 0) {
					arg0.anInt3400 = (int) (Math.atan2((double) local36, (double) local94) * 325.949D) & 0x7FF;
				}
			}
		}
		if ((arg0.anInt3382 != 0 || arg0.anInt3363 != 0) && (arg0.anInt3409 == 0 || arg0.anInt3417 > 0)) {
			local70 = arg0.x - (arg0.anInt3382 - mapBuildBaseX - mapBuildBaseX) * 64;
			local43 = arg0.z - (arg0.anInt3363 - mapBuildBaseZ - mapBuildBaseZ) * 64;
			if (local70 != 0 || local43 != 0) {
				arg0.anInt3400 = (int) (Math.atan2((double) local70, (double) local43) * 325.949D) & 0x7FF;
			}
			arg0.anInt3363 = 0;
			arg0.anInt3382 = 0;
		}
		local70 = arg0.anInt3400 - arg0.anInt3381 & 0x7FF;
		if (local70 == 0) {
			arg0.anInt3385 = 0;
			arg0.anInt3414 = 0;
		} else if (local13.anInt1038 == 0) {
			arg0.anInt3385++;
			@Pc(226) boolean local226;
			if (local70 > 1024) {
				arg0.anInt3381 -= arg0.anInt3376;
				local226 = true;
				if (local70 < arg0.anInt3376 || local70 > 2048 - arg0.anInt3376) {
					arg0.anInt3381 = arg0.anInt3400;
					local226 = false;
				}
				if (local13.anInt1037 == arg0.anInt3366 && (arg0.anInt3385 > 25 || local226)) {
					if (local13.anInt1036 == -1) {
						arg0.anInt3366 = local13.anInt1051;
					} else {
						arg0.anInt3366 = local13.anInt1036;
					}
				}
			} else {
				local226 = true;
				arg0.anInt3381 += arg0.anInt3376;
				if (arg0.anInt3376 > local70 || local70 > 2048 - arg0.anInt3376) {
					local226 = false;
					arg0.anInt3381 = arg0.anInt3400;
				}
				if (local13.anInt1037 == arg0.anInt3366 && (arg0.anInt3385 > 25 || local226)) {
					if (local13.anInt1067 == -1) {
						arg0.anInt3366 = local13.anInt1051;
					} else {
						arg0.anInt3366 = local13.anInt1067;
					}
				}
			}
			arg0.anInt3381 &= 0x7FF;
		} else {
			if (local13.anInt1037 == arg0.anInt3366 && arg0.anInt3385 > 25) {
				if (local13.anInt1067 == -1) {
					arg0.anInt3366 = local13.anInt1051;
				} else {
					arg0.anInt3366 = local13.anInt1067;
				}
			}
			local43 = arg0.anInt3400 << 5;
			if (local43 != arg0.anInt3402) {
				arg0.anInt3387 = 0;
				arg0.anInt3402 = local43;
				local36 = local43 - arg0.anInt3377 & 0xFFFF;
				local94 = arg0.anInt3414 * arg0.anInt3414 / (local13.anInt1038 * 2);
				@Pc(471) int local471;
				if (arg0.anInt3414 > 0 && local36 >= local94 && local36 - local94 < 32768) {
					arg0.anInt3397 = local36 / 2;
					arg0.aBoolean167 = true;
					local471 = local13.anInt1031 * local13.anInt1031 / (local13.anInt1038 * 2);
					if (local471 > 32767) {
						local471 = 32767;
					}
					if (local471 < arg0.anInt3397) {
						arg0.anInt3397 = local36 - local471;
					}
				} else if (arg0.anInt3414 < 0 && local94 <= 65536 - local36 && 65536 - local36 - local94 < 32768) {
					arg0.anInt3397 = (65536 - local36) / 2;
					arg0.aBoolean167 = true;
					local471 = local13.anInt1031 * local13.anInt1031 / (local13.anInt1038 * 2);
					if (local471 > 32767) {
						local471 = 32767;
					}
					if (local471 < arg0.anInt3397) {
						arg0.anInt3397 = 65536 - local36 - local471;
					}
				} else {
					arg0.aBoolean167 = false;
				}
			}
			if (arg0.anInt3414 == 0) {
				local36 = arg0.anInt3402 - arg0.anInt3377 & 0xFFFF;
				if (local36 < local13.anInt1038) {
					arg0.anInt3377 = arg0.anInt3402;
				} else {
					arg0.anInt3387 = 0;
					local94 = local13.anInt1031 * local13.anInt1031 / (local13.anInt1038 * 2);
					arg0.aBoolean167 = true;
					if (local94 > 32767) {
						local94 = 32767;
					}
					if (local36 >= 32768) {
						arg0.anInt3414 = -local13.anInt1038;
						arg0.anInt3397 = (65536 - local36) / 2;
						if (local94 < arg0.anInt3397) {
							arg0.anInt3397 = 65536 - local36 - local94;
						}
					} else {
						arg0.anInt3414 = local13.anInt1038;
						arg0.anInt3397 = local36 / 2;
						if (local94 < arg0.anInt3397) {
							arg0.anInt3397 = local36 - local94;
						}
					}
				}
			} else if (arg0.anInt3414 <= 0) {
				if (arg0.anInt3387 >= arg0.anInt3397) {
					arg0.aBoolean167 = false;
				}
				if (!arg0.aBoolean167) {
					arg0.anInt3414 += local13.anInt1038;
					if (arg0.anInt3414 > 0) {
						arg0.anInt3414 = 0;
					}
				} else if (-local13.anInt1031 < arg0.anInt3414) {
					arg0.anInt3414 -= local13.anInt1038;
				}
			} else {
				if (arg0.anInt3397 <= arg0.anInt3387) {
					arg0.aBoolean167 = false;
				}
				if (!arg0.aBoolean167) {
					arg0.anInt3414 -= local13.anInt1038;
					if (arg0.anInt3414 < 0) {
						arg0.anInt3414 = 0;
					}
				} else if (arg0.anInt3414 < local13.anInt1031) {
					arg0.anInt3414 += local13.anInt1038;
				}
			}
			arg0.anInt3377 += arg0.anInt3414;
			arg0.anInt3377 &= 0xFFFF;
			if (arg0.anInt3414 <= 0) {
				arg0.anInt3387 -= arg0.anInt3414;
			} else {
				arg0.anInt3387 += arg0.anInt3414;
			}
			arg0.anInt3381 = arg0.anInt3377 >> 5;
		}
	}

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(Lclient!fe;I)V")
	public static void method879(@OriginalArg(0) ClientEntity arg0) {
		arg0.aBoolean171 = false;
		@Pc(18) SeqType local18;
		if (arg0.anInt3366 != -1) {
			local18 = SeqType.list(arg0.anInt3366);
			if (local18 == null || local18.frames == null) {
				arg0.anInt3366 = -1;
			} else {
				arg0.anInt3396++;
				if (local18.frames.length > arg0.anInt3407 && arg0.anInt3396 > local18.delay[arg0.anInt3407]) {
					arg0.anInt3396 = 1;
					arg0.anInt3407++;
					arg0.anInt3388++;
					method2836(arg0.z, local18, arg0.x, arg0 == localPlayer, arg0.anInt3407);
				}
				if (arg0.anInt3407 >= local18.frames.length) {
					arg0.anInt3407 = 0;
					arg0.anInt3396 = 0;
					method2836(arg0.z, local18, arg0.x, localPlayer == arg0, arg0.anInt3407);
				}
				arg0.anInt3388 = arg0.anInt3407 + 1;
				if (arg0.anInt3388 >= local18.frames.length) {
					arg0.anInt3388 = 0;
				}
			}
		}
		@Pc(156) int local156;
		if (arg0.anInt3432 != -1 && loopCycle >= arg0.anInt3359) {
			local156 = SpotType.list(arg0.anInt3432).anim;
			if (local156 == -1) {
				arg0.anInt3432 = -1;
			} else {
				@Pc(165) SeqType local165 = SeqType.list(local156);
				if (local165 == null || local165.frames == null) {
					arg0.anInt3432 = -1;
				} else {
					if (arg0.anInt3399 < 0) {
						arg0.anInt3399 = 0;
						method2836(arg0.z, local165, arg0.x, localPlayer == arg0, 0);
					}
					arg0.anInt3361++;
					if (arg0.anInt3399 < local165.frames.length && local165.delay[arg0.anInt3399] < arg0.anInt3361) {
						arg0.anInt3399++;
						arg0.anInt3361 = 1;
						method2836(arg0.z, local165, arg0.x, localPlayer == arg0, arg0.anInt3399);
					}
					if (arg0.anInt3399 >= local165.frames.length) {
						arg0.anInt3432 = -1;
					}
					arg0.anInt3418 = arg0.anInt3399 + 1;
					if (local165.frames.length <= arg0.anInt3418) {
						arg0.anInt3418 = -1;
					}
				}
			}
		}
		if (arg0.anInt3369 != -1 && arg0.anInt3420 <= 1) {
			local18 = SeqType.list(arg0.anInt3369);
			if (local18.anInt5363 == 1 && arg0.anInt3405 > 0 && loopCycle >= arg0.anInt3395 && loopCycle > arg0.anInt3386) {
				arg0.anInt3420 = 1;
				return;
			}
		}
		if (arg0.anInt3369 != -1 && arg0.anInt3420 == 0) {
			local18 = SeqType.list(arg0.anInt3369);
			if (local18 == null || local18.frames == null) {
				arg0.anInt3369 = -1;
			} else {
				arg0.anInt3360++;
				if (arg0.anInt3425 < local18.frames.length && arg0.anInt3360 > local18.delay[arg0.anInt3425]) {
					arg0.anInt3360 = 1;
					arg0.anInt3425++;
					method2836(arg0.z, local18, arg0.x, arg0 == localPlayer, arg0.anInt3425);
				}
				if (local18.frames.length <= arg0.anInt3425) {
					arg0.anInt3425 -= local18.anInt5362;
					arg0.anInt3371++;
					if (arg0.anInt3371 >= local18.anInt5357) {
						arg0.anInt3369 = -1;
					} else if (arg0.anInt3425 >= 0 && local18.frames.length > arg0.anInt3425) {
						method2836(arg0.z, local18, arg0.x, localPlayer == arg0, arg0.anInt3425);
					} else {
						arg0.anInt3369 = -1;
					}
				}
				arg0.anInt3373 = arg0.anInt3425 + 1;
				if (arg0.anInt3373 >= local18.frames.length) {
					arg0.anInt3373 -= local18.anInt5362;
					if (local18.anInt5357 <= arg0.anInt3371 + 1) {
						arg0.anInt3373 = -1;
					} else if (arg0.anInt3373 < 0 || arg0.anInt3373 >= local18.frames.length) {
						arg0.anInt3373 = -1;
					}
				}
				arg0.aBoolean171 = local18.aBoolean279;
			}
		}
		if (arg0.anInt3420 > 0) {
			arg0.anInt3420--;
		}
		for (local156 = 0; local156 < arg0.aClass147Array3.length; local156++) {
			@Pc(545) Class147 local545 = arg0.aClass147Array3[local156];
			if (local545 != null) {
				if (local545.anInt5408 > 0) {
					local545.anInt5408--;
				} else {
					@Pc(570) SeqType local570 = SeqType.list(local545.anInt5396);
					if (local570 == null || local570.frames == null) {
						arg0.aClass147Array3[local156] = null;
					} else {
						local545.anInt5404++;
						if (local545.anInt5399 < local570.frames.length && local545.anInt5404 > local570.delay[local545.anInt5399]) {
							local545.anInt5399++;
							local545.anInt5404 = 1;
							method2836(arg0.z, local570, arg0.x, arg0 == localPlayer, local545.anInt5399);
						}
						if (local570.frames.length <= local545.anInt5399) {
							local545.anInt5400++;
							local545.anInt5399 -= local570.anInt5362;
							if (local570.anInt5357 <= local545.anInt5400) {
								arg0.aClass147Array3[local156] = null;
							} else if (local545.anInt5399 >= 0 && local545.anInt5399 < local570.frames.length) {
								method2836(arg0.z, local570, arg0.x, localPlayer == arg0, local545.anInt5399);
							} else {
								arg0.aClass147Array3[local156] = null;
							}
						}
						local545.anInt5398 = local545.anInt5399 + 1;
						if (local570.frames.length <= local545.anInt5398) {
							local545.anInt5398 -= local570.anInt5362;
							if (local545.anInt5400 + 1 >= local570.anInt5357) {
								local545.anInt5398 = -1;
							} else if (local545.anInt5398 < 0 || local570.frames.length <= local545.anInt5398) {
								local545.anInt5398 = -1;
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(IIIIIZIIIIII)Z")
	public static boolean method8(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(11) int arg10) {
		@Pc(3) int local3;
		@Pc(8) int local8;
		for (local3 = 0; local3 < 104; local3++) {
			for (local8 = 0; local8 < 104; local8++) {
				anIntArrayArray25[local3][local8] = 0;
				anIntArrayArray31[local3][local8] = 99999999;
			}
		}
		anIntArrayArray25[arg10][arg3] = 99;
		anIntArrayArray31[arg10][arg3] = 0;
		local8 = arg3;
		local3 = arg10;
		@Pc(53) byte local53 = 0;
		anIntArray514[0] = arg10;
		@Pc(59) boolean local59 = false;
		@Pc(61) int local61 = 0;
		@Pc(64) int local64 = local53 + 1;
		anIntArray209[0] = arg3;
		@Pc(71) int[][] local71 = levelCollisionMap[minusedlevel].flags;
		@Pc(193) int local193;
		while (local61 != local64) {
			local3 = anIntArray514[local61];
			local8 = anIntArray209[local61];
			local61 = local61 + 1 & 0xFFF;
			if (arg8 == local3 && arg4 == local8) {
				local59 = true;
				break;
			}
			if (arg1 != 0) {
				if ((arg1 < 5 || arg1 == 10) && levelCollisionMap[minusedlevel].method3042(arg4, local3, local8, arg8, arg1 - 1, 2, arg7)) {
					local59 = true;
					break;
				}
				if (arg1 < 10 && levelCollisionMap[minusedlevel].method3046(arg4, arg1 - 1, arg8, local8, 2, arg7, local3)) {
					local59 = true;
					break;
				}
			}
			if (arg0 != 0 && arg6 != 0 && levelCollisionMap[minusedlevel].method3052(arg8, local8, local3, 2, arg0, arg2, arg4, arg6)) {
				local59 = true;
				break;
			}
			local193 = anIntArrayArray31[local3][local8] + 1;
			if (local3 > 0 && anIntArrayArray25[local3 - 1][local8] == 0 && (local71[local3 - 1][local8] & 0x12C010E) == 0 && (local71[local3 - 1][local8 + 1] & 0x12C0138) == 0) {
				anIntArray514[local64] = local3 - 1;
				anIntArray209[local64] = local8;
				local64 = local64 + 1 & 0xFFF;
				anIntArrayArray25[local3 - 1][local8] = 2;
				anIntArrayArray31[local3 - 1][local8] = local193;
			}
			if (local3 < 102 && anIntArrayArray25[local3 + 1][local8] == 0 && (local71[local3 + 2][local8] & 0x12C0183) == 0 && (local71[local3 + 2][local8 + 1] & 0x12C01E0) == 0) {
				anIntArray514[local64] = local3 + 1;
				anIntArray209[local64] = local8;
				local64 = local64 + 1 & 0xFFF;
				anIntArrayArray25[local3 + 1][local8] = 8;
				anIntArrayArray31[local3 + 1][local8] = local193;
			}
			if (local8 > 0 && anIntArrayArray25[local3][local8 - 1] == 0 && (local71[local3][local8 - 1] & 0x12C010E) == 0 && (local71[local3 + 1][local8 - 1] & 0x12C0183) == 0) {
				anIntArray514[local64] = local3;
				anIntArray209[local64] = local8 - 1;
				anIntArrayArray25[local3][local8 - 1] = 1;
				anIntArrayArray31[local3][local8 - 1] = local193;
				local64 = local64 + 1 & 0xFFF;
			}
			if (local8 < 102 && anIntArrayArray25[local3][local8 + 1] == 0 && (local71[local3][local8 + 2] & 0x12C0138) == 0 && (local71[local3 + 1][local8 + 2] & 0x12C01E0) == 0) {
				anIntArray514[local64] = local3;
				anIntArray209[local64] = local8 + 1;
				anIntArrayArray25[local3][local8 + 1] = 4;
				local64 = local64 + 1 & 0xFFF;
				anIntArrayArray31[local3][local8 + 1] = local193;
			}
			if (local3 > 0 && local8 > 0 && anIntArrayArray25[local3 - 1][local8 - 1] == 0 && (local71[local3 - 1][local8] & 0x12C0138) == 0 && (local71[local3 - 1][local8 - 1] & 0x12C010E) == 0 && (local71[local3][local8 - 1] & 0x12C0183) == 0) {
				anIntArray514[local64] = local3 - 1;
				anIntArray209[local64] = local8 - 1;
				anIntArrayArray25[local3 - 1][local8 - 1] = 3;
				anIntArrayArray31[local3 - 1][local8 - 1] = local193;
				local64 = local64 + 1 & 0xFFF;
			}
			if (local3 < 102 && local8 > 0 && anIntArrayArray25[local3 + 1][local8 - 1] == 0 && (local71[local3 + 1][local8 - 1] & 0x12C010E) == 0 && (local71[local3 + 2][local8 - 1] & 0x12C0183) == 0 && (local71[local3 + 2][local8] & 0x12C01E0) == 0) {
				anIntArray514[local64] = local3 + 1;
				anIntArray209[local64] = local8 - 1;
				local64 = local64 + 1 & 0xFFF;
				anIntArrayArray25[local3 + 1][local8 - 1] = 9;
				anIntArrayArray31[local3 + 1][local8 - 1] = local193;
			}
			if (local3 > 0 && local8 < 102 && anIntArrayArray25[local3 - 1][local8 + 1] == 0 && (local71[local3 - 1][local8 + 1] & 0x12C010E) == 0 && (local71[local3 - 1][local8 + 2] & 0x12C0138) == 0 && (local71[local3][local8 + 2] & 0x12C01E0) == 0) {
				anIntArray514[local64] = local3 - 1;
				anIntArray209[local64] = local8 + 1;
				anIntArrayArray25[local3 - 1][local8 + 1] = 6;
				anIntArrayArray31[local3 - 1][local8 + 1] = local193;
				local64 = local64 + 1 & 0xFFF;
			}
			if (local3 < 102 && local8 < 102 && anIntArrayArray25[local3 + 1][local8 + 1] == 0 && (local71[local3 + 1][local8 + 2] & 0x12C0138) == 0 && (local71[local3 + 2][local8 + 2] & 0x12C01E0) == 0 && (local71[local3 + 2][local8 + 1] & 0x12C0183) == 0) {
				anIntArray514[local64] = local3 + 1;
				anIntArray209[local64] = local8 + 1;
				local64 = local64 + 1 & 0xFFF;
				anIntArrayArray25[local3 + 1][local8 + 1] = 12;
				anIntArrayArray31[local3 + 1][local8 + 1] = local193;
			}
		}
		Static221.anInt4364 = 0;
		@Pc(921) int local921;
		if (!local59) {
			if (!arg5) {
				return false;
			}
			local193 = 1000;
			local921 = 100;
			for (@Pc(928) int local928 = arg8 - 10; local928 <= arg8 + 10; local928++) {
				for (@Pc(942) int local942 = arg4 - 10; local942 <= arg4 + 10; local942++) {
					if (local928 >= 0 && local942 >= 0 && local928 < 104 && local942 < 104 && anIntArrayArray31[local928][local942] < 100) {
						@Pc(978) int local978 = 0;
						@Pc(980) int local980 = 0;
						if (local928 < arg8) {
							local978 = arg8 - local928;
						} else if (local928 > arg0 + arg8 - 1) {
							local978 = local928 + 1 - arg0 - arg8;
						}
						if (arg4 > local942) {
							local980 = arg4 - local942;
						} else if (local942 > arg4 + arg6 - 1) {
							local980 = local942 + 1 - arg4 - arg6;
						}
						@Pc(1057) int local1057 = local978 * local978 + local980 * local980;
						if (local1057 < local193 || local193 == local1057 && anIntArrayArray31[local928][local942] < local921) {
							local8 = local942;
							local921 = anIntArrayArray31[local928][local942];
							local193 = local1057;
							local3 = local928;
						}
					}
				}
			}
			if (local193 == 1000) {
				return false;
			}
			if (arg10 == local3 && local8 == arg3) {
				return false;
			}
			Static221.anInt4364 = 1;
		}
		@Pc(1121) byte local1121 = 0;
		anIntArray514[0] = local3;
		local61 = local1121 + 1;
		anIntArray209[0] = local8;
		local193 = local921 = anIntArrayArray25[local3][local8];
		while (arg10 != local3 || arg3 != local8) {
			if (local921 != local193) {
				anIntArray514[local61] = local3;
				anIntArray209[local61++] = local8;
				local921 = local193;
			}
			if ((local193 & 0x2) != 0) {
				local3++;
			} else if ((local193 & 0x8) != 0) {
				local3--;
			}
			if ((local193 & 0x1) != 0) {
				local8++;
			} else if ((local193 & 0x4) != 0) {
				local8--;
			}
			local193 = anIntArrayArray25[local3][local8];
		}
		if (local61 > 0) {
			method3502(local61, arg9);
			return true;
		} else if (arg9 == 1) {
			return false;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(IZI)V")
	public static void method10(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		out.p1Enc(132);
		out.method2201(arg1);
		out.method2222(arg0);
	}

	@OriginalMember(owner = "client!aa", name = "a", descriptor = "(BLclient!be;)V")
	public static void clientComponent(@OriginalArg(1) IfType arg0) {
		@Pc(16) int local16 = arg0.clientCode;
		if (local16 == 324) {
			if (anInt3851 == -1) {
				anInt3851 = arg0.graphic;
				anInt3502 = arg0.graphic2;
			}
			if (aClass59_2.aBoolean141) {
				arg0.graphic = anInt3851;
			} else {
				arg0.graphic = anInt3502;
			}
		} else if (local16 == 325) {
			if (anInt3851 == -1) {
				anInt3502 = arg0.graphic2;
				anInt3851 = arg0.graphic;
			}
			if (aClass59_2.aBoolean141) {
				arg0.graphic = anInt3502;
			} else {
				arg0.graphic = anInt3851;
			}
		} else if (local16 == 327) {
			arg0.modelXAn = 150;
			arg0.modelYAn = (int) (Math.sin((double) loopCycle / 40.0D) * 256.0D) & 0x7FF;
			arg0.model1Type = 5;
			arg0.model1Id = -1;
		} else if (local16 == 328) {
			if (localPlayer.name == null) {
				arg0.model1Id = 0;
			} else {
				arg0.modelXAn = 150;
				arg0.modelYAn = (int) (Math.sin((double) loopCycle / 40.0D) * 256.0D) & 0x7FF;
				arg0.model1Type = 5;
				arg0.model1Id = ((int) localPlayer.name.method3158() << 11) + 2047;
				arg0.anInt496 = localPlayer.anInt3388;
				arg0.anInt500 = 0;
				arg0.modelAnim = localPlayer.anInt3366;
				arg0.anInt510 = localPlayer.anInt3407;
			}
		}
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIILclient!be;)V")
	public static void method1015(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) IfType arg2) {
		if (dragComponent != null || isMenuOpen || (arg2 == null || method1836(arg2) == null)) {
			return;
		}
		dragComponent = arg2;
		aClass13_1 = method1836(arg2);
		Static246.anInt5388 = arg1;
		aBoolean172 = false;
		anInt4851 = 0;
		anInt4035 = arg0;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIIILclient!na;JI)V")
	public static void method1016(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) JagString arg3, @OriginalArg(5) long arg4, @OriginalArg(6) int arg5) {
		@Pc(8) Packet local8 = new Packet(128);
		local8.p1(10);
		local8.p2((int) (Math.random() * 99999.0D));
		local8.p2(530);
		if (GlobalConfig.ARIOS_LOGIN_STRINGS) {
			local8.pjstr(Static79.toBaseDisplayName(arg4));
		} else {
			local8.p8(arg4);
		}
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.pjstr(arg3);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.p2(affid);
		local8.p1(arg0);
		local8.p1(arg2);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.p2(arg5);
		local8.p2(arg1);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.rsaenc(Static86.aBigInteger1, Static256.aBigInteger2);
		out.pos = 0;
		out.p1(36);
		out.p1(local8.pos);
		out.tinyenc(local8.data, local8.pos);
		accountCreateError = -3;
		accountCreateStep = 1;
		accountCreateWaitingTime = 0;
		accountCreateFailCount = 0;
	}

	@OriginalMember(owner = "client!da", name = "d", descriptor = "(I)V")
	public static void method1008() {
		if (Static155.anInt3718 == -1 || Static52.anInt1694 == -1) {
			return;
		}
		@Pc(27) int local27 = (Static233.anInt5224 * (Static114.anInt5843 - Static228.anInt5101) >> 16) + Static228.anInt5101;
		@Pc(30) float[] local30 = new float[3];
		Static233.anInt5224 += local27;
		if (Static233.anInt5224 >= 65535) {
			Static233.anInt5224 = 65535;
			if (Static186.aBoolean205) {
				Static13.aBoolean16 = false;
			} else {
				Static13.aBoolean16 = true;
			}
			Static186.aBoolean205 = true;
		} else {
			Static186.aBoolean205 = false;
			Static13.aBoolean16 = false;
		}
		@Pc(66) float local66 = (float) Static233.anInt5224 / 65535.0F;
		@Pc(70) int local70 = Static127.anInt3125 * 2;
		@Pc(141) int local141;
		@Pc(131) int local131;
		@Pc(111) int local111;
		@Pc(119) int local119;
		@Pc(146) int local146;
		@Pc(155) int local155;
		@Pc(173) int local173;
		for (@Pc(72) int local72 = 0; local72 < 3; local72++) {
			local111 = (Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 2][local72] + Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 2][local72] - Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 3][local72]) * 3;
			local119 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70][local72];
			local131 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 1][local72] * 3;
			local141 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70][local72] * 3;
			local146 = local131 - local141;
			local155 = local111 + local141 - local131 * 2;
			local173 = Static107.anIntArrayArrayArray9[Static155.anInt3718][local70 + 2][local72] + local131 - local119 - local111;
			local30[local72] = (float) local119 + (((float) local173 * local66 + (float) local155) * local66 + (float) local146) * local66;
		}
		anInt40 = (int) local30[1] * -1;
		anInt3439 = (int) local30[0] - mapBuildBaseX * 128;
		anInt3302 = (int) local30[2] - mapBuildBaseZ * 128;
		@Pc(226) float[] local226 = new float[3];
		local141 = Static75.anInt2119 * 2;
		for (local131 = 0; local131 < 3; local131++) {
			local111 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141][local131] * 3;
			local146 = (Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 2][local131] + Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 2][local131] - Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 3][local131]) * 3;
			local155 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141][local131];
			local119 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 1][local131] * 3;
			local173 = local119 - local111;
			@Pc(313) int local313 = local146 + local111 - local119 * 2;
			@Pc(331) int local331 = Static107.anIntArrayArrayArray9[Static52.anInt1694][local141 + 2][local131] + local119 - local146 - local155;
			local226[local131] = (float) local155 + local66 * (local66 * (local66 * (float) local331 + (float) local313) + (float) local173);
		}
		@Pc(363) float local363 = local226[0] - local30[0];
		@Pc(371) float local371 = local226[2] - local30[2];
		@Pc(382) float local382 = (local226[1] - local30[1]) * -1.0F;
		@Pc(392) double local392 = Math.sqrt((double) (local371 * local371 + local363 * local363));
		Static146.aFloat15 = (float) Math.atan2((double) local382, local392);
		Static84.aFloat10 = -((float) Math.atan2((double) local363, (double) local371));
		anInt5333 = (int) ((double) Static146.aFloat15 * 325.949D) & 0x7FF;
		anInt4358 = (int) ((double) Static84.aFloat10 * 325.949D) & 0x7FF;
	}

	@OriginalMember(owner = "client!dm", name = "d", descriptor = "(I)V")
	public static void method1208() {
		networkError = false;
		Static231.anInt5202 = 0;
		worldHopError = -3;
		loginWaitingTime = 0;
		loginStep = 1;
		loginFailCount = 0;
		Static204.anInt4765 = -1;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(Lclient!be;III)V")
	public static void drawFeedback(@OriginalArg(0) IfType arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (menuNumEntries < 2 && Static260.anInt5014 == 0 && !targetMode) {
			return;
		}
		@Pc(24) JagString local24 = Static13.method471();
		if (arg0 == null) {
			@Pc(40) int local40 = Static280.aClass3_Sub2_Sub9_43.method2859(local24, arg2 + 4, arg1 - -15, Static39.aRandom1, Static60.anInt1895);
			dirtyArea(arg2 + 4, Static280.aClass3_Sub2_Sub9_43.method2858(local24) + local40, arg1, 15);
			return;
		}
		@Pc(59) PixFontGeneric local59 = arg0.getFont(Static159.aClass36Array12);
		if (local59 == null) {
			local59 = Static280.aClass3_Sub2_Sub9_43;
		}
		local59.method2878(local24, arg2, arg1, arg0.anInt445, arg0.anInt459, arg0.colour, arg0.shadowColour, arg0.hAlign, arg0.vAlign, Static39.aRandom1, Static60.anInt1895, Static50.anIntArray132);
		dirtyArea(Static50.anIntArray132[0], Static50.anIntArray132[2], Static50.anIntArray132[1], Static50.anIntArray132[3]);
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(B)V")
	public static void method1294() {
		if (!targetMode) {
			return;
		}
		@Pc(19) IfType local19 = IfType.method1418(Static98.anInt2512, Static15.anInt506);
		if (local19 != null && local19.ontargetleave != null) {
			@Pc(29) HookReq local29 = new HookReq();
			local29.onop = local19.ontargetleave;
			local29.component = local19;
			ScriptRunner.executeScript(local29);
		}
		targetMode = false;
		Static35.anInt1092 = -1;
		componentUpdated(local19);
	}

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(IBIILclient!be;)V")
	public static void method1305(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) IfType arg3) {
		doAudio();
		if (glRenderer) {
			Static46.method1187(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		} else {
			Pix2D.method2496(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		}
		if (anInt5795 != 2 && anInt5795 != 5 && Static89.aClass3_Sub2_Sub1_5 != null) {
			@Pc(48) int local48 = macroMinimapAngle + anInt1747 & 0x7FF;
			@Pc(57) int local57 = localPlayer.x / 32 + 48;
			@Pc(67) int local67 = 464 - localPlayer.z / 32;
			if (glRenderer) {
				((GlPix32) Static89.aClass3_Sub2_Sub1_5).method1427(arg2, arg1, arg3.anInt445, arg3.anInt459, local57, local67, local48, macroMinimapZoom + 256, (GlPix32) arg3.getGraphic(false));
			} else {
				((Pix32) Static89.aClass3_Sub2_Sub1_5).method310(arg2, arg1, arg3.anInt445, arg3.anInt459, local57, local67, local48, macroMinimapZoom + 256, arg3.anIntArray37, arg3.anIntArray45);
			}
			@Pc(146) int local146;
			@Pc(181) int local181;
			@Pc(150) int local150;
			@Pc(154) int local154;
			@Pc(231) int local231;
			@Pc(200) int local200;
			@Pc(239) int local239;
			@Pc(271) int local271;
			if (WorldMap.aClass134_2 != null) {
				for (@Pc(117) int local117 = 0; local117 < WorldMap.aClass134_2.anInt5074; local117++) {
					if (WorldMap.aClass134_2.method3892(local117)) {
						local146 = (WorldMap.aClass134_2.aShortArray73[local117] - mapBuildBaseX) * 4 + 2 - localPlayer.x / 32;
						local150 = Pix3D.sinTable[local48];
						local154 = Pix3D.cosTable[local48];
						@Pc(156) PixFontGeneric local156 = Static114.aClass3_Sub2_Sub9_42;
						@Pc(164) int local164 = local150 * 256 / (macroMinimapZoom + 256);
						local181 = (WorldMap.aClass134_2.aShortArray72[local117] - mapBuildBaseZ) * 4 + 2 - localPlayer.z / 32;
						@Pc(189) int local189 = local154 * 256 / (macroMinimapZoom + 256);
						local200 = local181 * local189 - local146 * local164 >> 16;
						if (WorldMap.aClass134_2.method3894(local117) == 1) {
							local156 = Static215.aClass3_Sub2_Sub9_32;
						}
						if (WorldMap.aClass134_2.method3894(local117) == 2) {
							local156 = Static280.aClass3_Sub2_Sub9_43;
						}
						local231 = local164 * local181 + local189 * local146 >> 16;
						local239 = local156.method2856(WorldMap.aClass134_2.aClass100Array153[local117], 100);
						@Pc(245) int local245 = local231 - local239 / 2;
						if (local245 >= -arg3.anInt445 && local245 <= arg3.anInt445 && local200 >= -arg3.anInt459 && local200 <= arg3.anInt459) {
							local271 = 16777215;
							if (WorldMap.aClass134_2.anIntArray444[local117] != -1) {
								local271 = WorldMap.aClass134_2.anIntArray444[local117];
							}
							if (glRenderer) {
								Static46.method1188((GlPix32) arg3.getGraphic(false));
							} else {
								Pix2D.method2486(arg3.anIntArray37, arg3.anIntArray45);
							}
							local156.method2869(WorldMap.aClass134_2.aClass100Array153[local117], arg2 + local245 + arg3.anInt445 / 2, arg1 + arg3.anInt459 / 2 + -local200, local239, 50, local271, 0, 1, 0, 0);
							if (glRenderer) {
								Static46.method1173();
							} else {
								Pix2D.method2482();
							}
						}
					}
				}
			}
			for (local146 = 0; local146 < ClientBuild.anInt5454; local146++) {
				local181 = ClientBuild.anIntArray331[local146] * 4 + 2 - localPlayer.x / 32;
				local150 = ClientBuild.anIntArray219[local146] * 4 + 2 - localPlayer.z / 32;
				@Pc(382) LocType local382 = LocType.list(ClientBuild.anIntArray417[local146]);
				if (local382.anIntArray380 != null) {
					local382 = local382.getMultiLoc();
					if (local382 == null || local382.anInt4400 == -1) {
						continue;
					}
				}
				method1446(arg3, Static67.aClass3_Sub2_Sub1Array4[local382.anInt4400], local150, local181, arg1, arg2);
			}
			for (local146 = 0; local146 < 104; local146++) {
				for (local181 = 0; local181 < 104; local181++) {
					@Pc(439) LinkList local439 = groundObj[minusedlevel][local146][local181];
					if (local439 != null) {
						local154 = local146 * 4 + 2 - localPlayer.x / 32;
						local231 = local181 * 4 + 2 - localPlayer.z / 32;
						method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[0], local231, local154, arg1, arg2);
					}
				}
			}
			for (local146 = 0; local146 < npcCount; local146++) {
				@Pc(498) ClientNPC local498 = npcs[npcIds[local146]];
				if (local498 != null && local498.method2682()) {
					@Pc(507) NPCType local507 = local498.aClass96_1;
					if (local507 != null && local507.anIntArray357 != null) {
						local507 = local507.method2932();
					}
					if (local507 != null && local507.aBoolean184 && local507.aBoolean183) {
						local154 = local498.x / 32 - localPlayer.x / 32;
						local231 = local498.z / 32 - localPlayer.z / 32;
						if (local507.anInt3739 == -1) {
							method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[1], local231, local154, arg1, arg2);
						} else {
							method1446(arg3, Static67.aClass3_Sub2_Sub1Array4[local507.anInt3739], local231, local154, arg1, arg2);
						}
					}
				}
			}
			for (local146 = 0; local146 < playerCount; local146++) {
				@Pc(591) ClientPlayer local591 = players[playerIds[local146]];
				if (local591 != null && local591.method2682()) {
					local154 = local591.z / 32 - localPlayer.z / 32;
					local150 = local591.x / 32 - localPlayer.x / 32;
					@Pc(624) long local624 = local591.name.method3158();
					@Pc(626) boolean local626 = false;
					for (local239 = 0; local239 < Static9.anInt178; local239++) {
						if (local624 == aLongArray3[local239] && Static104.anIntArray255[local239] != 0) {
							local626 = true;
							break;
						}
					}
					@Pc(660) boolean local660 = false;
					for (local271 = 0; local271 < Static214.anInt5577; local271++) {
						if (local624 == Static199.aClass3_Sub22Array1[local271].key) {
							local660 = true;
							break;
						}
					}
					@Pc(682) boolean local682 = false;
					if (localPlayer.anInt1650 != 0 && local591.anInt1650 != 0 && local591.anInt1650 == localPlayer.anInt1650) {
						local682 = true;
					}
					if (local626) {
						method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[3], local154, local150, arg1, arg2);
					} else if (local660) {
						method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[5], local154, local150, arg1, arg2);
					} else if (local682) {
						method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[4], local154, local150, arg1, arg2);
					} else {
						method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[2], local154, local150, arg1, arg2);
					}
				}
			}
			@Pc(756) MapMarker[] local756 = Static143.aClass102Array1;
			for (local181 = 0; local181 < local756.length; local181++) {
				@Pc(770) MapMarker local770 = local756[local181];
				if (local770 != null && local770.anInt4058 != 0 && loopCycle % 20 < 10) {
					if (local770.anInt4058 == 1 && local770.anInt4057 >= 0 && local770.anInt4057 < npcs.length) {
						@Pc(804) ClientNPC local804 = npcs[local770.anInt4057];
						if (local804 != null) {
							local231 = local804.x / 32 - localPlayer.x / 32;
							local200 = local804.z / 32 - localPlayer.z / 32;
							method1960(local770.anInt4048, arg1, arg2, local231, local200, arg3);
						}
					}
					if (local770.anInt4058 == 2) {
						local154 = (local770.anInt4053 - mapBuildBaseX) * 4 + 2 - localPlayer.x / 32;
						local231 = (-mapBuildBaseZ + local770.anInt4046) * 4 + 2 - localPlayer.z / 32;
						method1960(local770.anInt4048, arg1, arg2, local154, local231, arg3);
					}
					if (local770.anInt4058 == 10 && local770.anInt4057 >= 0 && players.length > local770.anInt4057) {
						@Pc(905) ClientPlayer local905 = players[local770.anInt4057];
						if (local905 != null) {
							local200 = local905.z / 32 - localPlayer.z / 32;
							local231 = local905.x / 32 - localPlayer.x / 32;
							method1960(local770.anInt4048, arg1, arg2, local231, local200, arg3);
						}
					}
				}
			}
			if (anInt2939 != 0) {
				local146 = anInt2939 * 4 + 2 - localPlayer.x / 32;
				local181 = Static84.anInt2255 * 4 + 2 - localPlayer.z / 32;
				method1446(arg3, Static84.aClass3_Sub2_Sub1_4, local181, local146, arg1, arg2);
			}
			if (glRenderer) {
				Static46.method1186(arg2 + arg3.anInt445 / 2 - 1, arg1 + -1 - -(arg3.anInt459 / 2), 3, 3, 16777215);
			} else {
				Pix2D.method2495(arg3.anInt445 / 2 + arg2 - 1, arg3.anInt459 / 2 + -1 + arg1, 3, 3, 16777215);
			}
		} else if (glRenderer) {
			@Pc(1041) AbstractPix32 local1041 = arg3.getGraphic(false);
			if (local1041 != null) {
				local1041.plotSprite(arg2, arg1);
			}
		} else {
			Pix2D.method2504(arg2, arg1, arg3.anIntArray37, arg3.anIntArray45);
		}
		componentRedrawRequested2[arg0] = true;
	}

	@OriginalMember(owner = "client!fm", name = "a", descriptor = "(ZI)V")
	public static void method1596(@OriginalArg(0) boolean arg0) {
		if (arg0) {
			if (toplevelinterface != -1) {
				IfType.closeInterface(toplevelinterface);
			}
			for (@Pc(18) SubInterface local18 = (SubInterface) aClass133_9.search(); local18 != null; local18 = (SubInterface) aClass133_9.findnext()) {
				closeSubInterface(true, local18);
			}
			toplevelinterface = -1;
			aClass133_9 = new HashTable(8);
			IfType.method1287();
			toplevelinterface = Static156.anInt3783;
			method3712(false);
			method1807();
			ScriptRunner.method1626(toplevelinterface);
		}
		Static35.anInt1092 = -1;
		Static81.method1750(Static270.anInt5794);
		localPlayer = new ClientPlayer();
		localPlayer.z = 3000;
		localPlayer.x = 3000;
		if (!glRenderer) {
			Static145.method2743(sprites);
			setMainState(10);
			return;
		}
		if (anInt5096 == 2) {
			anInt3439 = Static245.anInt5375 << 7;
			anInt3302 = Static248.anInt4232 << 7;
		} else {
			method1008();
		}
		Static86.method1799();
		method740();
		setMainState(28);
	}

	@OriginalMember(owner = "client!fm", name = "a", descriptor = "(ILclient!na;Lclient!na;Lclient!na;I)V")
	public static void method1598(@OriginalArg(1) JagString arg0, @OriginalArg(2) JagString arg1, @OriginalArg(3) JagString arg2) {
		method2928(-1, 9, arg0, arg2, arg1);
	}

	@OriginalMember(owner = "client!g", name = "b", descriptor = "(B)V")
	public static void zonePacket() {
		@Pc(15) int local15;
		@Pc(23) int local23;
		@Pc(19) int local19;
		@Pc(27) int local27;
		@Pc(31) int local31;
		@Pc(39) int local39;
		@Pc(45) int local45;
		if (ptype == 195) {
			local15 = in.method2212();
			local19 = local15 & 0x3;
			local23 = local15 >> 2;
			local27 = Static133.anIntArray453[local23];
			local31 = in.g1();
			local39 = (local31 >> 4 & 0x7) + Static115.anInt2940;
			local45 = (local31 & 0x7) + Static180.anInt4264;
			if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
				locChangeCreate(minusedlevel, local45, local19, local39, -1, -1, local27, local23, 0);
			}
		} else if (ptype == 33) {
			local15 = in.method2192();
			local23 = in.g1();
			local27 = (local23 & 0x7) + Static180.anInt4264;
			local19 = (local23 >> 4 & 0x7) + Static115.anInt2940;
			local31 = in.method2184();
			if (local19 >= 0 && local27 >= 0 && local19 < 104 && local27 < 104) {
				@Pc(122) ClientObj local122 = new ClientObj();
				local122.anInt5550 = local31;
				local122.id = local15;
				if (groundObj[minusedlevel][local19][local27] == null) {
					groundObj[minusedlevel][local19][local27] = new LinkList();
				}
				groundObj[minusedlevel][local19][local27].push(new ClientObjNode(local122));
				showObject(local27, local19);
			}
		} else {
			@Pc(218) int local218;
			@Pc(228) int local228;
			@Pc(232) int local232;
			@Pc(247) int local247;
			@Pc(224) int local224;
			@Pc(236) int local236;
			@Pc(317) ClientProj local317;
			if (ptype == 121) {
				local15 = in.g1();
				local23 = Static115.anInt2940 * 2 + (local15 >> 4 & 0xF);
				local19 = (local15 & 0xF) + Static180.anInt4264 * 2;
				local27 = local23 + in.g1b();
				local31 = in.g1b() + local19;
				local39 = in.g2b();
				local45 = in.g2();
				local218 = in.g1() * 4;
				local224 = in.g1() * 4;
				local228 = in.g2();
				local232 = in.g2();
				local236 = in.g1();
				if (local236 == 255) {
					local236 = -1;
				}
				local247 = in.g1();
				if (local23 >= 0 && local19 >= 0 && local23 < 208 && local19 < 208 && local27 >= 0 && local31 >= 0 && local27 < 208 && local31 < 208 && local45 != 65535) {
					local31 *= 64;
					local27 = local27 * 64;
					local19 = local19 * 64;
					local23 = local23 * 64;
					local317 = new ClientProj(local45, minusedlevel, local23, local19, getAvH(minusedlevel, local23, local19) - local218, loopCycle + local228, local232 + loopCycle, local236, local247, local39, local224);
					local317.method3705(local31, loopCycle + local228, -local224 + getAvH(minusedlevel, local27, local31), local27);
					Static217.aClass69_116.push(new ClientProjNode(local317));
				}
			} else if (ptype == 17) {
				local15 = in.g1();
				local23 = Static115.anInt2940 + (local15 >> 4 & 0x7);
				local19 = Static180.anInt4264 + (local15 & 0x7);
				local27 = in.g2();
				local31 = in.g1();
				local39 = in.g2();
				if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
					local23 = local23 * 128 + 64;
					local19 = local19 * 128 + 64;
					@Pc(427) MapSpotAnim local427 = new MapSpotAnim(local27, minusedlevel, local23, local19, getAvH(minusedlevel, local23, local19) - local31, local39, loopCycle);
					Static99.aClass69_64.push(new MapSpotAnimNode(local427));
				}
			} else if (ptype == 179) {
				local15 = in.method2177();
				local23 = local15 >> 2;
				local19 = local15 & 0x3;
				local27 = Static133.anIntArray453[local23];
				local31 = in.g1();
				local39 = Static115.anInt2940 + (local31 >> 4 & 0x7);
				local45 = (local31 & 0x7) + Static180.anInt4264;
				local218 = in.method2184();
				if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
					locChangeCreate(minusedlevel, local45, local19, local39, -1, local218, local27, local23, 0);
				}
			} else if (ptype == 20) {
				local15 = in.method2180();
				local23 = (local15 >> 4 & 0x7) + Static115.anInt2940;
				local19 = Static180.anInt4264 + (local15 & 0x7);
				local27 = in.method2180();
				local31 = local27 >> 2;
				local39 = local27 & 0x3;
				local45 = Static133.anIntArray453[local31];
				local218 = in.method2192();
				if (local218 == 65535) {
					local218 = -1;
				}
				method1881(minusedlevel, local39, local31, local19, local45, local23, local218);
			} else {
				@Pc(633) int local633;
				if (ptype == 202) {
					local15 = in.g1();
					local23 = local15 >> 2;
					local19 = local15 & 0x3;
					local27 = in.g1();
					local31 = (local27 >> 4 & 0x7) + Static115.anInt2940;
					local39 = (local27 & 0x7) + Static180.anInt4264;
					@Pc(605) byte local605 = in.method2215();
					@Pc(609) byte local609 = in.method2215();
					@Pc(613) byte local613 = in.method2175();
					local228 = in.method2184();
					local232 = in.method2192();
					@Pc(625) byte local625 = in.g1b();
					local247 = in.g2();
					local633 = in.method2214();
					if (!glRenderer) {
						method2574(local625, local247, local633, local232, local39, local613, local19, local605, local31, local23, local609, local228);
					}
				}
				if (ptype == 14) {
					local15 = in.g1();
					local19 = Static180.anInt4264 + (local15 & 0x7);
					local23 = (local15 >> 4 & 0x7) + Static115.anInt2940;
					local27 = in.g2();
					local31 = in.g2();
					local39 = in.g2();
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
						@Pc(710) LinkList local710 = groundObj[minusedlevel][local23][local19];
						if (local710 != null) {
							for (@Pc(718) ClientObjNode local718 = (ClientObjNode) local710.head(); local718 != null; local718 = (ClientObjNode) local710.next()) {
								@Pc(723) ClientObj local723 = local718.aClass8_Sub7_1;
								if ((local27 & 0x7FFF) == local723.id && local31 == local723.anInt5550) {
									local723.anInt5550 = local39;
									break;
								}
							}
							showObject(local19, local23);
						}
					}
				} else if (ptype == 135) {
					local15 = in.method2207();
					local23 = in.method2212();
					local27 = Static180.anInt4264 + (local23 & 0x7);
					local19 = (local23 >> 4 & 0x7) + Static115.anInt2940;
					local31 = in.method2192();
					local39 = in.method2192();
					if (local19 >= 0 && local27 >= 0 && local19 < 104 && local27 < 104 && anInt549 != local15) {
						@Pc(812) ClientObj local812 = new ClientObj();
						local812.anInt5550 = local31;
						local812.id = local39;
						if (groundObj[minusedlevel][local19][local27] == null) {
							groundObj[minusedlevel][local19][local27] = new LinkList();
						}
						groundObj[minusedlevel][local19][local27].push(new ClientObjNode(local812));
						showObject(local27, local19);
					}
				} else if (ptype == 16) {
					local15 = in.g1();
					local23 = Static115.anInt2940 + (local15 >> 4 & 0x7);
					local19 = (local15 & 0x7) + Static180.anInt4264;
					local27 = local23 + in.g1b();
					local31 = in.g1b() + local19;
					local39 = in.g2b();
					local45 = in.g2();
					local218 = in.g1() * 4;
					local224 = in.g1() * 4;
					local228 = in.g2();
					local232 = in.g2();
					local236 = in.g1();
					local247 = in.g1();
					if (local236 == 255) {
						local236 = -1;
					}
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104 && local27 >= 0 && local31 >= 0 && local27 < 104 && local31 < 104 && local45 != 65535) {
						local31 = local31 * 128 + 64;
						local19 = local19 * 128 + 64;
						local23 = local23 * 128 + 64;
						local27 = local27 * 128 + 64;
						local317 = new ClientProj(local45, minusedlevel, local23, local19, getAvH(minusedlevel, local23, local19) - local218, local228 + loopCycle, local232 + loopCycle, local236, local247, local39, local224);
						local317.method3705(local31, loopCycle + local228, getAvH(minusedlevel, local27, local31) - local224, local27);
						Static217.aClass69_116.push(new ClientProjNode(local317));
					}
				} else if (ptype == 104) {
					local15 = in.g1();
					local19 = Static180.anInt4264 * 2 + (local15 & 0xF);
					local23 = Static115.anInt2940 * 2 + (local15 >> 4 & 0xF);
					local27 = in.g1b() + local23;
					local31 = in.g1b() + local19;
					local39 = in.g2b();
					local45 = in.g2b();
					local218 = in.g2();
					local224 = in.g1b();
					local228 = in.g1() * 4;
					local232 = in.g2();
					local236 = in.g2();
					local247 = in.g1();
					local633 = in.g1();
					if (local247 == 255) {
						local247 = -1;
					}
					if (local23 >= 0 && local19 >= 0 && local23 < 208 && local19 < 208 && local27 >= 0 && local31 >= 0 && local27 < 208 && local31 < 208 && local218 != 65535) {
						local27 = local27 * 64;
						local23 *= 64;
						local31 *= 64;
						local19 *= 64;
						if (local39 != 0) {
							@Pc(1194) int local1194;
							@Pc(1198) ClientEntity local1198;
							@Pc(1184) int local1184;
							@Pc(1188) int local1188;
							if (local39 >= 0) {
								local1184 = local39 - 1;
								local1188 = local1184 & 0x7FF;
								local1194 = local1184 >> 11 & 0xF;
								local1198 = npcs[local1188];
							} else {
								local1184 = -local39 - 1;
								local1194 = local1184 >> 11 & 0xF;
								local1188 = local1184 & 0x7FF;
								if (anInt549 == local1188) {
									local1198 = localPlayer;
								} else {
									local1198 = players[local1188];
								}
							}
							if (local1198 != null) {
								@Pc(1232) BasType local1232 = local1198.method2681();
								if (local1232.anIntArrayArray7 != null && local1232.anIntArrayArray7[local1194] != null) {
									local1188 = local1232.anIntArrayArray7[local1194][0];
									local224 -= local1232.anIntArrayArray7[local1194][1];
									@Pc(1264) int local1264 = local1232.anIntArrayArray7[local1194][2];
									@Pc(1269) int local1269 = Pix3D.sinTable[local1198.anInt3381];
									@Pc(1274) int local1274 = Pix3D.cosTable[local1198.anInt3381];
									@Pc(1284) int local1284 = local1188 * local1274 + local1264 * local1269 >> 16;
									@Pc(1295) int local1295 = local1274 * local1264 - local1188 * local1269 >> 16;
									local19 += local1295;
									local23 += local1284;
								}
							}
						}
						@Pc(1331) ClientProj local1331 = new ClientProj(local218, minusedlevel, local23, local19, getAvH(minusedlevel, local23, local19) - local224, local232 + loopCycle, local236 + loopCycle, local247, local633, local45, local228);
						local1331.method3705(local31, local232 + loopCycle, -local228 + getAvH(minusedlevel, local27, local31), local27);
						Static217.aClass69_116.push(new ClientProjNode(local1331));
					}
				} else if (ptype == 97) {
					local15 = in.g1();
					local23 = Static115.anInt2940 + (local15 >> 4 & 0x7);
					local19 = Static180.anInt4264 + (local15 & 0x7);
					local27 = in.g2();
					if (local27 == 65535) {
						local27 = -1;
					}
					local31 = in.g1();
					local39 = local31 >> 4 & 0xF;
					local218 = in.g1();
					local45 = local31 & 0x7;
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
						local224 = local39 + 1;
						if (localPlayer.routeX[0] >= local23 - local224 && local224 + local23 >= localPlayer.routeX[0] && localPlayer.routeZ[0] >= local19 - local224 && localPlayer.routeZ[0] <= local224 + local19 && ambientVolume != 0 && local45 > 0 && waveCount < 50 && local27 != -1) {
							waveSoundIds[waveCount] = local27;
							anIntArray563[waveCount] = local45;
							waveDelay[waveCount] = local218;
							waveSounds[waveCount] = null;
							waveAmbient[waveCount] = local39 + (local23 << 16) + (local19 << 8);
							waveCount++;
						}
					}
				} else if (ptype == 240) {
					local15 = in.method2180();
					local19 = Static180.anInt4264 + (local15 & 0x7);
					local23 = (local15 >> 4 & 0x7) + Static115.anInt2940;
					local27 = in.g2();
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
						@Pc(1565) LinkList local1565 = groundObj[minusedlevel][local23][local19];
						if (local1565 != null) {
							for (@Pc(1572) ClientObjNode local1572 = (ClientObjNode) local1565.head(); local1572 != null; local1572 = (ClientObjNode) local1565.next()) {
								if (local1572.aClass8_Sub7_1.id == (local27 & 0x7FFF)) {
									local1572.unlink();
									break;
								}
							}
							if (local1565.head() == null) {
								groundObj[minusedlevel][local23][local19] = null;
							}
							showObject(local19, local23);
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(I)V")
	public static void gameDraw() {
		if (!isMenuOpen) {
			if (anInt3953 != 0) {
				anInt3751 = anInt5850;
				anInt1892 = anInt5895;
			} else if (ClientMouseListener.mouseClickButton == 0) {
				anInt3751 = ClientMouseListener.mouseX;
				anInt1892 = ClientMouseListener.mouseY;
			} else {
				anInt3751 = ClientMouseListener.mouseClickX;
				anInt1892 = ClientMouseListener.mouseClickY;
			}
			menuNumEntries = 1;
			aClass100Array168[0] = Text.aClass100_1091;
			aClass100Array160[0] = TitleScreen.AUTO_EMPTY;
			menuAction[0] = 1005;
			anIntArray382[0] = Static35.anInt1092;
		}
		if (toplevelinterface != -1) {
			animateInterface(toplevelinterface);
		}
		@Pc(60) int local60;
		for (local60 = 0; local60 < componentDrawCount; local60++) {
			if (componentRedrawRequested1[local60]) {
				componentRedrawRequested2[local60] = true;
			}
			Static223.aBooleanArray116[local60] = componentRedrawRequested1[local60];
			componentRedrawRequested1[local60] = false;
		}
		Static201.aClass13_13 = null;
		minimenuMouseOverX = -1;
		minimenuMouseOverY = -1;
		hoveredSlotParent = null;
		if (glRenderer) {
			Static263.aBoolean299 = true;
		}
		Static182.anInt4311 = loopCycle;
		if (toplevelinterface != -1) {
			componentDrawCount = 0;
			method182();
		}
		if (glRenderer) {
			Static46.method1177();
		} else {
			Pix2D.method2503();
		}

		sortMinimenu();
		if (isMenuOpen) {
			if (Static261.aBoolean298) {
				method2297();
			} else {
				Static145.method2744();
			}
		} else if (Static201.aClass13_13 != null) {
			drawFeedback(Static201.aClass13_13, Static143.anInt3484, Static131.anInt3260);
		} else if (minimenuMouseOverX != -1) {
			drawFeedback(null, minimenuMouseOverY, minimenuMouseOverX);
		}

		local60 = isMenuOpen ? -1 : Static235.method4044();
		if (local60 == -1) {
			local60 = Static270.anInt5794;
		}
		Static81.method1750(local60);
		if (anInt3096 == 1) {
			anInt3096 = 2;
		}
		if (anInt4422 == 1) {
			anInt4422 = 2;
		}
		if (Static199.anInt4672 == 3) {
			for (@Pc(189) int local189 = 0; local189 < componentDrawCount; local189++) {
				if (Static223.aBooleanArray116[local189]) {
					if (glRenderer) {
						Static46.method1182(componentDrawX[local189], componentDrawY[local189], componentDrawWidth[local189], componentDrawHeight[local189], 16711935, 128);
					} else {
						Pix2D.method2484(componentDrawX[local189], componentDrawY[local189], componentDrawWidth[local189], componentDrawHeight[local189], 16711935, 128);
					}
				} else if (componentRedrawRequested2[local189]) {
					if (glRenderer) {
						Static46.method1182(componentDrawX[local189], componentDrawY[local189], componentDrawWidth[local189], componentDrawHeight[local189], 16711680, 128);
					} else {
						Pix2D.method2484(componentDrawX[local189], componentDrawY[local189], componentDrawWidth[local189], componentDrawHeight[local189], 16711680, 128);
					}
				}
			}
		}
		BgSound.method2281(anInt4247, localPlayer.x, localPlayer.z, minusedlevel);
		anInt4247 = 0;
	}

	@OriginalMember(owner = "client!pm", name = "a", descriptor = "(ZIZIZII)V")
	public static void method3560(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		if (arg2) {
			Static239.method4169();
		}
		if (aFrame2 != null && (arg1 != 3 || arg4 != Static114.anInt5831 || arg5 != Static22.anInt729)) {
			Static25.method714(aFrame2, signlink);
			aFrame2 = null;
		}
		if (arg1 == 3 && aFrame2 == null) {
			aFrame2 = DisplayMode.method3176(0, arg5, arg4, signlink);
			if (aFrame2 != null) {
				Static22.anInt729 = arg5;
				Static114.anInt5831 = arg4;
				Static203.method3663(signlink);
			}
		}
		if (arg1 == 3 && aFrame2 == null) {
			method3560(true, Static214.anInt5581, true, arg3, -1, -1);
			return;
		}
		@Pc(85) Container local85;
		if (aFrame2 != null) {
			local85 = aFrame2;
		} else if (frame == null) {
			local85 = signlink.applet;
		} else {
			local85 = frame;
		}
		canvasWid = local85.getSize().width;
		canvasHei = local85.getSize().height;
		@Pc(109) Insets local109;
		if (frame == local85) {
			local109 = frame.getInsets();
			canvasWid -= local109.right + local109.left;
			canvasHei -= local109.bottom + local109.top;
		}
		if (arg1 >= 2) {
			anInt1448 = canvasWid;
			anInt5554 = canvasHei;
			anInt3497 = 0;
			anInt4246 = 0;
		} else {
			anInt4246 = 0;
			anInt3497 = (canvasWid - 765) / 2;
			anInt1448 = 765;
			anInt5554 = 503;
		}
		if (arg0) {
			ClientKeyboardListener.shutdown(canvas);
			ClientMouseListener.shutdown(canvas);
			if (mouseWheel != null) {
				mouseWheel.removeListeners(canvas);
			}
			Static215.client.addcanvas();
			ClientKeyboardListener.addListeners(canvas);
			ClientMouseListener.addListeners(canvas);
			if (mouseWheel != null) {
				mouseWheel.addListeners(canvas);
			}
		} else {
			if (glRenderer) {
				Static239.method4181(anInt1448, anInt5554);
			}
			canvas.setSize(anInt1448, anInt5554);
			if (frame == local85) {
				local109 = frame.getInsets();
				canvas.setLocation(local109.left + anInt3497, local109.top + anInt4246);
			} else {
				canvas.setLocation(anInt3497, anInt4246);
			}
		}
		if (arg1 == 0 && arg3 > 0) {
			Static239.method4161(canvas);
		}
		if (arg2 && arg1 > 0) {
			canvas.setIgnoreRepaint(true);
			if (!Static211.aBoolean73) {
				World.method1500();
				GameShell.drawArea = null;
				GameShell.drawArea = PixMap.method2579(anInt5554, anInt1448, canvas);
				Pix2D.method2492();
				if (state == 5) {
					Static182.method3359(true, Static280.aClass3_Sub2_Sub9_43);
				} else {
					messageBox(false, Text.aClass100_621);
				}
				try {
					@Pc(269) Graphics local269 = canvas.getGraphics();
					GameShell.drawArea.method4186(local269);
				} catch (@Pc(277) Exception local277) {
				}
				method2704();
				if (arg3 == 0) {
					GameShell.drawArea = PixMap.method2579(503, 765, canvas);
				} else {
					GameShell.drawArea = null;
				}
				@Pc(300) PrivilegedRequest local300 = signlink.method5123(Static215.client.getClass());
				while (local300.status == 0) {
					ThreadSleep.sleepPrecise(100L);
				}
				if (local300.status == 1) {
					Static211.aBoolean73 = true;
				}
			}
			if (Static211.aBoolean73) {
				Static239.method4180(canvas, Static186.anInt4392 * 2);
			}
		}
		if (!glRenderer && arg1 > 0) {
			method3560(true, 0, true, arg3, -1, -1);
			return;
		}
		if (arg1 > 0 && arg3 == 0) {
			thread.setPriority(5);
			GameShell.drawArea = null;
			SoftwareModelLit.method4580();
			((WorldTextureProvider) Pix3D.anInterface1_2).method3248(200);
			if (Static178.highDetailLighting) {
				Pix3D.method1911(0.7F);
			}
			Static114.method4637();
		} else if (arg1 == 0 && arg3 > 0) {
			thread.setPriority(1);
			GameShell.drawArea = PixMap.method2579(503, 765, canvas);
			SoftwareModelLit.method4583();
			Static76.method1643();
			((WorldTextureProvider) Pix3D.anInterface1_2).method3248(20);
			if (Static178.highDetailLighting) {
				if (Static113.anInt4609 == 1) {
					Pix3D.method1911(0.9F);
				}
				if (Static113.anInt4609 == 2) {
					Pix3D.method1911(0.8F);
				}
				if (Static113.anInt4609 == 3) {
					Pix3D.method1911(0.7F);
				}
				if (Static113.anInt4609 == 4) {
					Pix3D.method1911(0.6F);
				}
			}
			Static95.method1939();
			Static114.method4637();
		}
		Static87.aBoolean130 = !highDetail();
		if (arg2) {
			method2721();
		}
		if (arg1 >= 2) {
			Static124.aBoolean156 = true;
		} else {
			Static124.aBoolean156 = false;
		}
		if (toplevelinterface != -1) {
			method3712(true);
		}
		if (stream != null && (state == 30 || state == 25)) {
			method1373();
		}
		for (@Pc(466) int local466 = 0; local466 < 100; local466++) {
			componentRedrawRequested1[local466] = true;
		}
		fullredraw = true;
	}

	@OriginalMember(owner = "client!hj", name = "a", descriptor = "(Lclient!na;B)Z")
	public static boolean method1965(@OriginalArg(0) JagString arg0) {
		if (arg0 == null) {
			return false;
		}
		for (@Pc(12) int local12 = 0; local12 < Static9.anInt178; local12++) {
			if (arg0.equalsIgnoreCase(aClass100Array92[local12])) {
				return true;
			}
		}
		if (arg0.equalsIgnoreCase(localPlayer.name)) {
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(II)V")
	public static void setLang(@OriginalArg(1) int lang) {
		if (lang == 0) {
			return;
		}

		if (lang == 1) {
			TextGerman.swapGerman();
		} else if (lang == 2) {
			TextFrench.swapFrench();
		} else {
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "client!ij", name = "a", descriptor = "(B)V")
	public static void method2297() {
		@Pc(3) int local3 = Static183.anInt4271;
		@Pc(9) int local9 = Static229.anInt5138;
		@Pc(11) int local11 = Static13.anInt436;
		@Pc(13) int local13 = Static24.anInt761;
		if (Static39.aClass3_Sub2_Sub1_1 == null || Static181.aClass3_Sub2_Sub1_9 == null) {
			if (sprites.method4506(Static55.anInt1736) && sprites.method4506(Static169.anInt4073)) {
				Static39.aClass3_Sub2_Sub1_1 = Static80.depack(sprites, Static55.anInt1736);
				Static181.aClass3_Sub2_Sub1_9 = Static80.depack(sprites, Static169.anInt4073);
				if (glRenderer) {
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
			} else if (glRenderer) {
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
				Static39.aClass3_Sub2_Sub1_1.plotSprite(local114 * Static39.aClass3_Sub2_Sub1_1.anInt1867 + local3, local9);
			}
			Static181.aClass3_Sub2_Sub1_9.plotSprite(local3, local9);
			Static181.aClass3_Sub2_Sub1_9.method1421(local3 + local13 - Static181.aClass3_Sub2_Sub1_9.anInt1867, local9);
		}
		Static280.aClass3_Sub2_Sub9_43.method2857(Text.aClass100_998, local3 + 3, local9 + 14, Static195.anInt4581, -1);
		if (glRenderer) {
			Static46.method1182(local3, local9 + 20, local13, local11 - 20, Static40.anInt1275, 256 - Static111.anInt2910);
		} else {
			Pix2D.method2484(local3, local9 + 20, local13, local11 - 20, Static40.anInt1275, 256 - Static111.anInt2910);
		}
		local114 = ClientMouseListener.mouseY;
		local112 = ClientMouseListener.mouseX;
		@Pc(203) int local203;
		@Pc(219) int local219;
		for (local203 = 0; local203 < menuNumEntries; local203++) {
			local219 = (menuNumEntries - local203 - 1) * 15 + local9 + 35;
			if (local3 < local112 && local112 < local3 + local13 && local114 > local219 - 13 && local114 < local219 + 3) {
				if (glRenderer) {
					Static46.method1182(local3, local219 - 13, local13, 16, Static251.anInt5457, 256 - Static232.anInt5208);
				} else {
					Pix2D.method2484(local3, local219 - 13, local13, 16, Static251.anInt5457, 256 - Static232.anInt5208);
				}
			}
		}
		if ((Static165.aClass3_Sub2_Sub1_8 == null || Static92.aClass3_Sub2_Sub1_6 == null || Static204.aClass3_Sub2_Sub1_10 == null) && sprites.method4506(Static85.anInt2261) && sprites.method4506(Static136.anInt3324) && sprites.method4506(Static254.anInt5556)) {
			Static165.aClass3_Sub2_Sub1_8 = Static80.depack(sprites, Static85.anInt2261);
			Static92.aClass3_Sub2_Sub1_6 = Static80.depack(sprites, Static136.anInt3324);
			Static204.aClass3_Sub2_Sub1_10 = Static80.depack(sprites, Static254.anInt5556);
			if (glRenderer) {
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
				Static165.aClass3_Sub2_Sub1_8.plotSprite(local3 + Static165.aClass3_Sub2_Sub1_8.anInt1867 * local219, local11 + local9 + -Static165.aClass3_Sub2_Sub1_8.anInt1859);
			}
			local219 = (local11 - 20) / Static92.aClass3_Sub2_Sub1_6.anInt1859;
			for (local418 = 0; local418 < local219; local418++) {
				Static92.aClass3_Sub2_Sub1_6.plotSprite(local3, local9 + local418 * Static92.aClass3_Sub2_Sub1_6.anInt1859 + 20);
				Static92.aClass3_Sub2_Sub1_6.method1421(local3 + local13 - Static92.aClass3_Sub2_Sub1_6.anInt1867, local9 + 20 + local418 * Static92.aClass3_Sub2_Sub1_6.anInt1859);
			}
			Static204.aClass3_Sub2_Sub1_10.plotSprite(local3, local11 + local9 - Static204.aClass3_Sub2_Sub1_10.anInt1859);
			Static204.aClass3_Sub2_Sub1_10.method1421(local3 + local13 - Static204.aClass3_Sub2_Sub1_10.anInt1867, local9 - -local11 + -Static204.aClass3_Sub2_Sub1_10.anInt1859);
		}
		for (local203 = 0; local203 < menuNumEntries; local203++) {
			local219 = (menuNumEntries - local203 - 1) * 15 + local9 + 35;
			local418 = Static195.anInt4581;
			if (local3 < local112 && local13 + local3 > local112 && local219 - 13 < local114 && local114 < local219 + 3) {
				local418 = Static262.anInt5752;
			}
			Static280.aClass3_Sub2_Sub9_43.method2857(Static269.method2228(local203), local3 + 3, local219, local418, 0);
		}
		method2407(Static183.anInt4271, Static229.anInt5138, Static13.anInt436, Static24.anInt761);
	}

	@OriginalMember(owner = "client!um", name = "a", descriptor = "(Z)V")
	public static void method4392() {
		overrideChat = 0;
		@Pc(17) int local17 = mapBuildBaseX + (localPlayer.x >> 7);
		@Pc(25) int local25 = (localPlayer.z >> 7) + mapBuildBaseZ;
		if (local17 >= 3053 && local17 <= 3156 && local25 >= 3056 && local25 <= 3136) {
			overrideChat = 1;
		}
		if (local17 >= 3072 && local17 <= 3118 && local25 >= 9492 && local25 <= 9535) {
			overrideChat = 1;
		}
		if (overrideChat == 1 && local17 >= 3139 && local17 <= 3199 && local25 >= 3008 && local25 <= 3062) {
			overrideChat = 0;
		}
	}

	@OriginalMember(owner = "client!ja", name = "a", descriptor = "(IIIIB)V")
	public static void otherOverlays(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (crossMode == 1) {
			cross[crossCycle / 100].plotSprite(crossX - 8, crossY + -8);
		}
		if (crossMode == 2) {
			cross[crossCycle / 100 + 4].plotSprite(crossX - 8, crossY + -8);
		}
		method4392();
	}

	@OriginalMember(owner = "client!jj", name = "a", descriptor = "(IBI)Lclient!na;")
	public static JagString method2420(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(4) int local4 = arg1 - arg0;
		if (local4 < -9) {
			return aClass100_798;
		} else if (local4 < -6) {
			return aClass100_433;
		} else if (local4 < -3) {
			return aClass100_951;
		} else if (local4 < 0) {
			return aClass100_972;
		} else if (local4 > 9) {
			return aClass100_332;
		} else if (local4 > 6) {
			return aClass100_18;
		} else if (local4 > 3) {
			return aClass100_1081;
		} else {
			return local4 > 0 ? aClass100_266 : aClass100_965;
		}
    }

	@OriginalMember(owner = "client!k", name = "a", descriptor = "(IIIIZIZ)V")
	public static void method2463(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) boolean arg4, @OriginalArg(5) int arg5) {
		if (mapBuildCenterZoneZ == arg2 && arg1 == mapBuildCenterZoneX && (Static41.anInt1316 == arg0 || highDetail())) {
			return;
		}
		mapBuildCenterZoneZ = arg2;
		mapBuildCenterZoneX = arg1;
		Static41.anInt1316 = arg0;
		if (highDetail()) {
			Static41.anInt1316 = 0;
		}
		if (arg4) {
			setMainState(28);
		} else {
			setMainState(25);
		}
		messageBox(true, Text.aClass100_621);
		@Pc(53) int local53 = mapBuildBaseZ;
		@Pc(55) int local55 = mapBuildBaseX;
		mapBuildBaseZ = arg1 * 8 - 48;
		mapBuildBaseX = (arg2 - 6) * 8;
		WorldMap.aClass3_Sub2_Sub4_3 = Static29.method803(mapBuildCenterZoneZ * 8, mapBuildCenterZoneX * 8);
		@Pc(81) int local81 = mapBuildBaseZ - local53;
		@Pc(86) int local86 = mapBuildBaseX - local55;
		WorldMap.aClass134_2 = null;
		@Pc(96) int local96;
		@Pc(103) ClientNPC local103;
		@Pc(109) int local109;
		if (arg4) {
			npcCount = 0;
			for (local96 = 0; local96 < 32768; local96++) {
				local103 = npcs[local96];
				if (local103 != null) {
					local103.x -= local86 * 128;
					local103.z -= local81 * 128;
					if (local103.x >= 0 && local103.x <= 13184 && local103.z >= 0 && local103.z <= 13184) {
						for (local109 = 0; local109 < 10; local109++) {
							local103.routeX[local109] -= local86;
							local103.routeZ[local109] -= local81;
						}
						npcIds[npcCount++] = local96;
					} else {
						npcs[local96].method2698(null);
						npcs[local96] = null;
					}
				}
			}
		} else {
			for (local96 = 0; local96 < 32768; local96++) {
				local103 = npcs[local96];
				if (local103 != null) {
					for (local109 = 0; local109 < 10; local109++) {
						local103.routeX[local109] -= local86;
						local103.routeZ[local109] -= local81;
					}
					local103.x -= local86 * 128;
					local103.z -= local81 * 128;
				}
			}
		}
		for (local96 = 0; local96 < 2048; local96++) {
			@Pc(265) ClientPlayer local265 = players[local96];
			if (local265 != null) {
				for (local109 = 0; local109 < 10; local109++) {
					local265.routeX[local109] -= local86;
					local265.routeZ[local109] -= local81;
				}
				local265.x -= local86 * 128;
				local265.z -= local81 * 128;
			}
		}
		minusedlevel = arg0;
		localPlayer.method1265(arg5, false, arg3);
		@Pc(322) byte local322 = 104;
		@Pc(324) byte local324 = 0;
		@Pc(326) byte local326 = 0;
		@Pc(328) byte local328 = 1;
		@Pc(330) byte local330 = 104;
		@Pc(332) byte local332 = 1;
		if (local81 < 0) {
			local328 = -1;
			local330 = -1;
			local326 = 103;
		}
		if (local86 < 0) {
			local332 = -1;
			local324 = 103;
			local322 = -1;
		}
		for (@Pc(358) int local358 = local324; local358 != local322; local358 += local332) {
			for (@Pc(367) int local367 = local326; local367 != local330; local367 += local328) {
				@Pc(378) int local378 = local86 + local358;
				@Pc(382) int local382 = local367 + local81;
				for (@Pc(384) int local384 = 0; local384 < 4; local384++) {
					if (local378 >= 0 && local382 >= 0 && local378 < 104 && local382 < 104) {
						groundObj[local384][local358][local367] = groundObj[local384][local378][local382];
					} else {
						groundObj[local384][local358][local367] = null;
					}
				}
			}
		}
		for (@Pc(451) LocChange local451 = (LocChange) aClass69_27.head(); local451 != null; local451 = (LocChange) aClass69_27.next()) {
			local451.anInt916 -= local81;
			local451.anInt928 -= local86;
			if (local451.anInt928 < 0 || local451.anInt916 < 0 || local451.anInt928 >= 104 || local451.anInt916 >= 104) {
				local451.unlink();
			}
		}
		if (arg4) {
			anInt3439 -= local86 * 128;
			anInt3302 -= local81 * 128;
			Static248.anInt4232 -= local81;
			Static251.anInt5449 -= local86;
			Static265.anInt5765 -= local81;
			Static245.anInt5375 -= local86;
		} else {
			anInt5096 = 1;
		}
		waveCount = 0;
		if (anInt2939 != 0) {
			Static84.anInt2255 -= local81;
			anInt2939 -= local86;
		}
		if (glRenderer && arg4 && (Math.abs(local86) > 104 || Math.abs(local81) > 104)) {
			Static86.method1799();
		}
		Static107.anInt2875 = -1;
		Static99.aClass69_64.method2278();
		Static217.aClass69_116.method2278();
	}

	@OriginalMember(owner = "client!k", name = "a", descriptor = "(Lclient!be;Lclient!na;I)Lclient!na;")
	public static JagString substituteVars(@OriginalArg(0) IfType arg0, @OriginalArg(1) JagString arg1) {
		if (arg1.indexOf(AUTO_PERCENT) == -1) {
			return arg1;
		}

		while (true) {
			@Pc(14) int local14 = arg1.indexOf(aClass100_133);
			if (local14 == -1) {
				while (true) {
					local14 = arg1.indexOf(aClass100_1097);
					if (local14 == -1) {
						while (true) {
							local14 = arg1.indexOf(aClass100_761);
							if (local14 == -1) {
								while (true) {
									local14 = arg1.indexOf(aClass100_520);
									if (local14 == -1) {
										while (true) {
											local14 = arg1.indexOf(aClass100_1002);
											if (local14 == -1) {
												while (true) {
													local14 = arg1.indexOf(aClass100_591);
													if (local14 == -1) {
														return arg1;
													}
													@Pc(246) JagString local246 = TitleScreen.AUTO_EMPTY;
													if (lastAddress != null) {
														local246 = Static181.formatIPv4(lastAddress.intArg);
														try {
															if (lastAddress.result != null) {
																@Pc(265) byte[] local265 = ((String) lastAddress.result).getBytes("ISO-8859-1");
																local246 = Static10.method346(local265, local265.length, 0);
															}
														} catch (@Pc(274) UnsupportedEncodingException local274) {
														}
													}
													arg1 = JagString.join(new JagString[] { arg1.method3137(local14, 0), local246, arg1.method3136(local14 + 4) });
												}
											}
											arg1 = JagString.join(new JagString[] { arg1.method3137(local14, 0), niceNumber(getIfVar(4, arg0)), arg1.method3136(local14 + 2) });
										}
									}
									arg1 = JagString.join(new JagString[] { arg1.method3137(local14, 0), niceNumber(getIfVar(3, arg0)), arg1.method3136(local14 + 2) });
								}
							}
							arg1 = JagString.join(new JagString[] { arg1.method3137(local14, 0), niceNumber(getIfVar(2, arg0)), arg1.method3136(local14 + 2) });
						}
					}
					arg1 = JagString.join(new JagString[] { arg1.method3137(local14, 0), niceNumber(getIfVar(1, arg0)), arg1.method3136(local14 + 2) });
				}
			}
			arg1 = JagString.join(new JagString[] { arg1.method3137(local14, 0), niceNumber(getIfVar(0, arg0)), arg1.method3136(local14 + 2) });
		}
	}

	@OriginalMember(owner = "client!k", name = "a", descriptor = "(Lclient!na;Z)V")
	public static void doCheat(@OriginalArg(0) JagString arg0) {
		if (staffmodlevel >= 2) {
			@Pc(18) int local18;
			@Pc(38) int local38;
			@Pc(29) Runtime local29;
			if (arg0.equalsIgnoreCase(Static81.aClass100_476)) {
				method2380();
				for (local18 = 0; local18 < 10; local18++) {
					System.gc();
				}
				local29 = Runtime.getRuntime();
				local38 = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
				addChat(null, 0, JagString.join(new JagString[] { Static202.aClass100_892, JagString.parseInt(local38), Static17.aClass100_101 }));
			}
			@Pc(117) int local117;
			if (arg0.equalsIgnoreCase(Static154.aClass100_735)) {
				method2380();
				for (local18 = 0; local18 < 10; local18++) {
					System.gc();
				}
				local29 = Runtime.getRuntime();
				local38 = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
				addChat(null, 0, JagString.join(new JagString[] { Static203.aClass100_893, JagString.parseInt(local38), Static17.aClass100_101 }));
				Static16.method501();
				method2380();
				for (local117 = 0; local117 < 10; local117++) {
					System.gc();
				}
				local38 = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
				addChat(null, 0, JagString.join(new JagString[] { Static270.aClass100_1093, JagString.parseInt(local38), Static17.aClass100_101 }));
			}
			if (arg0.equalsIgnoreCase(Static240.aClass100_1009)) {
				addChat(null, 0, JagString.join(new JagString[] { Static44.aClass100_335, JagString.parseInt(Static198.method1029()) }));
			}
			if (glRenderer && arg0.equalsIgnoreCase(Static201.aClass100_406)) {
				System.out.println("oncard_geometry:" + Static63.anInt1945);
				System.out.println("oncard_2d:" + Static63.anInt1944);
				System.out.println("oncard_texture:" + Static63.anInt1942);
			}
			if (arg0.equalsIgnoreCase(Static257.aClass100_99)) {
				lostCon();
			}
			if (arg0.equalsIgnoreCase(Static279.aClass100_1106)) {
				js5Net.shutdown();
			}
			if (arg0.equalsIgnoreCase(Static185.aClass100_823)) {
				js5Net.method2319();
			}
			if (arg0.equalsIgnoreCase(Static165.aClass100_775)) {
				signlink.method5110();
				stream.method2833();
				js5Net.method2323();
			}
			if (arg0.equalsIgnoreCase(Static114.aClass100_1100)) {
				canvasReplaceRecommended = true;
			}
			if (arg0.equalsIgnoreCase(Static148.aClass100_677)) {
				setMainState(25);
			}
			if (arg0.equalsIgnoreCase(Static107.aClass100_566)) {
				Static43.aBoolean82 = true;
			}
			if (arg0.equalsIgnoreCase(Static61.aClass100_422)) {
				Static43.aBoolean82 = false;
			}
			if (arg0.equalsIgnoreCase(Static96.aClass100_522)) {
				method4540(false, 0, -1, -1);
			}
			if (arg0.equalsIgnoreCase(Static181.aClass100_811)) {
				method4540(false, 1, -1, -1);
			}
			if (arg0.equalsIgnoreCase(Static207.aClass100_904)) {
				method4540(false, 2, -1, -1);
			}
			if (arg0.equalsIgnoreCase(Static99.aClass100_529)) {
				method4540(false, 3, 1024, 768);
			}
			if (arg0.equalsIgnoreCase(Static69.aClass100_443)) {
				for (local18 = 0; local18 < 4; local18++) {
					for (local38 = 1; local38 < 103; local38++) {
						for (local117 = 1; local117 < 103; local117++) {
							levelCollisionMap[local18].flags[local38][local117] = 0;
						}
					}
				}
			}
			if (arg0.method3138(Static241.aClass100_1088)) {
				Static76.method1645(arg0.method3136(15).method3132());
				Static203.method3663(signlink);
				Static18.aBoolean39 = false;
			}
			if (arg0.method3138(Static170.aClass100_623) && modewhere != 0) {
				Static115.method2312(arg0.method3136(6).method3132());
			}
			if (arg0.equalsIgnoreCase(Static272.aClass100_990)) {
				throw new RuntimeException();
			}
			if (arg0.method3138(Static211.aClass100_232)) {
				Static199.anInt4672 = arg0.method3136(12).method3144().method3132();
				addChat(null, 0, JagString.join(new JagString[] { Static127.aClass100_1096, JagString.parseInt(Static199.anInt4672) }));
			}
			if (arg0.equalsIgnoreCase(Static181.aClass100_810)) {
				aBoolean154 = true;
			}
			if (arg0.equalsIgnoreCase(Static124.aClass100_596)) {
				if (Static204.aBoolean234) {
					Static204.aBoolean234 = false;
					addChat(null, 0, Static274.aClass100_943);
				} else {
					Static204.aBoolean234 = true;
					addChat(null, 0, Static50.aClass100_362);
				}
			}
			if (arg0.equalsIgnoreCase(Static114.aClass100_1099)) {
				if (aBoolean199) {
					Static154.aClass100_736.method3129();
					aBoolean199 = false;
				} else {
					Static43.aClass100_333.method3129();
					aBoolean199 = true;
				}
			}
		}
		out.p1Enc(44);
		out.p1(arg0.length() - 1);
		out.pjstr(arg0.method3136(2));
	}

	@OriginalMember(owner = "client!la", name = "a", descriptor = "(IJ)V")
	public static void method2707(@OriginalArg(1) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		if (Static35.anInt1093 >= 100) {
			addChat(TitleScreen.AUTO_EMPTY, 0, Text.aClass100_1028);
			return;
		}
		@Pc(34) JagString local34 = Static79.toBaseDisplayName(arg0).method3125();
		@Pc(36) int local36;
		for (local36 = 0; local36 < Static35.anInt1093; local36++) {
			if (Static190.aLongArray6[local36] == arg0) {
				addChat(TitleScreen.AUTO_EMPTY, 0, JagString.join(new JagString[] { local34, Text.aClass100_820 }));
				return;
			}
		}
		for (local36 = 0; local36 < Static9.anInt178; local36++) {
			if (aLongArray3[local36] == arg0) {
				addChat(TitleScreen.AUTO_EMPTY, 0, JagString.join(new JagString[] { Text.aClass100_418, local34, Text.aClass100_873 }));
				return;
			}
		}
		if (local34.equalsInner(localPlayer.name)) {
			addChat(TitleScreen.AUTO_EMPTY, 0, Text.aClass100_774);
			return;
		}
		Static190.aLongArray6[Static35.anInt1093] = arg0;
		Static193.aClass100Array134[Static35.anInt1093++] = Static79.toBaseDisplayName(arg0);
		friendTransmitNum = transmitNum;
		out.p1Enc(34);
		out.p8(arg0);
	}

	@OriginalMember(owner = "client!lb", name = "a", descriptor = "(Z)V")
	public static void method2721() {
		World.method1500();
		Static89.aClass3_Sub2_Sub1_5 = null;
		Static107.anInt2875 = -1;
		clearCaches();
		Static255.aClass54_16.method1815();
		Static171.aClass139_1 = new Class139();
		((WorldTextureProvider) Pix3D.anInterface1_2).method3247();
		World.anInt3034 = 0;
		World.aClass51Array1 = new Light[255];
		Static237.method4120();
		Static242.method4203();
		Static115.method2315();
		WorldMap.method2325(false);
		Static119.method2381();
		for (@Pc(39) int local39 = 0; local39 < 2048; local39++) {
			@Pc(46) ClientPlayer local46 = players[local39];
			if (local46 != null) {
				local46.anObject5 = null;
			}
		}
		if (glRenderer) {
			Static242.method4201();
			Static76.method1642();
		}
		Static102.method2074(fontMetrics, sprites);
		Static30.method839(sprites);
		Static204.aClass3_Sub2_Sub1_10 = null;
		Static39.aClass3_Sub2_Sub1_1 = null;
		Static92.aClass3_Sub2_Sub1_6 = null;
		Static165.aClass3_Sub2_Sub1_8 = null;
		Static181.aClass3_Sub2_Sub1_9 = null;
		if (state == 5) {
			Static181.method3344(sprites);
		}
		if (state == 10) {
			method1596(false);
		}
		if (state == 30) {
			setMainState(25);
		}
	}

	@OriginalMember(owner = "client!lb", name = "a", descriptor = "(ZIIIBII)V")
	public static void method2722(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		Static113.anInt4612 = arg3;
		Static231.anInt5203 = arg2;
		Static245.anInt5375 = arg5;
		Static233.anInt5225 = arg1;
		Static248.anInt4232 = arg4;
		if (arg0 && Static113.anInt4612 >= 100) {
			anInt3439 = Static245.anInt5375 * 128 + 64;
			anInt3302 = Static248.anInt4232 * 128 + 64;
			anInt40 = getAvH(minusedlevel, anInt3439, anInt3302) - Static231.anInt5203;
		}
		anInt5096 = 2;
	}

	@OriginalMember(owner = "client!ma", name = "a", descriptor = "(ILclient!tk;IIZI)V")
	public static void method2836(@OriginalArg(0) int arg0, @OriginalArg(1) SeqType arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3, @OriginalArg(5) int arg4) {
		if (waveCount >= 50 || (arg1.anIntArrayArray38 == null || arg4 >= arg1.anIntArrayArray38.length || arg1.anIntArrayArray38[arg4] == null)) {
			return;
		}
		@Pc(36) int local36 = arg1.anIntArrayArray38[arg4][0];
		@Pc(40) int local40 = local36 >> 8;
		@Pc(57) int local57;
		if (arg1.anIntArrayArray38[arg4].length > 1) {
			local57 = (int) ((double) arg1.anIntArrayArray38[arg4].length * Math.random());
			if (local57 > 0) {
				local40 = arg1.anIntArrayArray38[arg4][local57];
			}
		}
		@Pc(73) int local73 = local36 >> 5 & 0x7;
		@Pc(77) int local77 = local36 & 0x1F;
		if (local77 == 0) {
			if (arg3) {
				method744(local73, local40, 0);
			}
		} else if (ambientVolume != 0) {
			waveSoundIds[waveCount] = local40;
			anIntArray563[waveCount] = local73;
			@Pc(111) int local111 = (arg0 - 64) / 128;
			local57 = (arg2 - 64) / 128;
			waveDelay[waveCount] = 0;
			waveSounds[waveCount] = null;
			waveAmbient[waveCount] = local77 + (local57 << 16) + (local111 << 8);
			waveCount++;
		}
	}

	@OriginalMember(owner = "client!mc", name = "f", descriptor = "(B)V")
	public static void method2909() {
		out.p1Enc(184);
		for (@Pc(18) SubInterface local18 = (SubInterface) aClass133_9.search(); local18 != null; local18 = (SubInterface) aClass133_9.findnext()) {
			if (local18.anInt5879 == 0) {
				closeSubInterface(true, local18);
			}
		}
		if (Static39.aClass13_10 != null) {
			componentUpdated(Static39.aClass13_10);
			Static39.aClass13_10 = null;
		}
	}

	@OriginalMember(owner = "client!mf", name = "a", descriptor = "(JI)V")
	public static void method2956(@OriginalArg(0) long arg0) {
		if ((long) 0 != arg0) {
			out.p1Enc(104);
			out.p8(arg0);
		}
	}

	@OriginalMember(owner = "client!mi", name = "a", descriptor = "([IBLclient!km;[I[I)V")
	public static void method3037(@OriginalArg(0) int[] arg0, @OriginalArg(2) ClientNPC arg1, @OriginalArg(3) int[] arg2, @OriginalArg(4) int[] arg3) {
		for (@Pc(3) int local3 = 0; local3 < arg3.length; local3++) {
			@Pc(15) int local15 = arg3[local3];
			@Pc(19) int local19 = arg0[local3];
			@Pc(23) int local23 = arg2[local3];
			for (@Pc(25) int local25 = 0; local19 != 0 && arg1.aClass147Array3.length > local25; local25++) {
				if ((local19 & 0x1) != 0) {
					if (local15 == -1) {
						arg1.aClass147Array3[local25] = null;
					} else {
						@Pc(60) SeqType local60 = SeqType.list(local15);
						@Pc(65) Class147 local65 = arg1.aClass147Array3[local25];
						@Pc(68) int local68 = local60.duplicatebehavior;
						if (local65 != null) {
							if (local15 == local65.anInt5396) {
								if (local68 == 0) {
									local65 = arg1.aClass147Array3[local25] = null;
								} else if (local68 == 1) {
									local65.anInt5399 = 0;
									local65.anInt5400 = 0;
									local65.anInt5398 = 1;
									local65.anInt5404 = 0;
									local65.anInt5408 = local23;
									method2836(arg1.z, local60, arg1.x, false, 0);
								} else if (local68 == 2) {
									local65.anInt5400 = 0;
								}
							} else if (local60.priority >= SeqType.list(local65.anInt5396).priority) {
								local65 = arg1.aClass147Array3[local25] = null;
							}
						}
						if (local65 == null) {
							local65 = arg1.aClass147Array3[local25] = new Class147();
							local65.anInt5398 = 1;
							local65.anInt5404 = 0;
							local65.anInt5408 = local23;
							local65.anInt5396 = local15;
							local65.anInt5400 = 0;
							local65.anInt5399 = 0;
							method2836(arg1.z, local60, arg1.x, false, 0);
						}
					}
				}
				local19 >>>= 0x1;
			}
		}
	}

	@OriginalMember(owner = "client!mj", name = "a", descriptor = "(IILclient!be;IB)V")
	public static void method3047(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) IfType arg2, @OriginalArg(3) int arg3) {
		if (glRenderer) {
			Static46.method1187(arg0, arg1, arg2.anInt445 + arg0, arg2.anInt459 + arg1);
		}
		if (anInt5795 >= 3) {
			if (glRenderer) {
				@Pc(44) AbstractPix32 local44 = arg2.getGraphic(false);
				if (local44 != null) {
					local44.plotSprite(arg0, arg1);
				}
			} else {
				Pix2D.method2504(arg0, arg1, arg2.anIntArray37, arg2.anIntArray45);
			}
		} else if (glRenderer) {
			((GlPix32) Static106.aClass3_Sub2_Sub1_7).method1427(arg0, arg1, arg2.anInt445, arg2.anInt459, Static106.aClass3_Sub2_Sub1_7.anInt1867 / 2, Static106.aClass3_Sub2_Sub1_7.anInt1859 / 2, anInt1747, 256, (GlPix32) arg2.getGraphic(false));
		} else {
			((Pix32) Static106.aClass3_Sub2_Sub1_7).method313(arg0, arg1, arg2.anInt445, arg2.anInt459, Static106.aClass3_Sub2_Sub1_7.anInt1867 / 2, Static106.aClass3_Sub2_Sub1_7.anInt1859 / 2, anInt1747, arg2.anIntArray37, arg2.anIntArray45);
		}
		componentRedrawRequested2[arg3] = true;
	}

	@OriginalMember(owner = "client!nk", name = "c", descriptor = "(IZ)V")
	public static void method3240(@OriginalArg(1) boolean arg0) {
		@Pc(7) int local7;
		@Pc(16) ClientNPC local16;
		@Pc(107) int local107;
		@Pc(113) int local113;
		@Pc(133) int local133;
		@Pc(149) int local149;
		@Pc(158) int local158;
		@Pc(171) int local171;
		for (local7 = 0; local7 < npcCount; local7++) {
			local16 = npcs[npcIds[local7]];
			if (local16 != null && local16.method2682() && local16.aClass96_1.aBoolean182 == arg0 && local16.aClass96_1.method2933()) {
				@Pc(42) int local42 = local16.method2693();
				@Pc(97) int local97;
				if (local42 == 1) {
					if ((local16.x & 0x7F) == 64 && (local16.z & 0x7F) == 64) {
						local97 = local16.x >> 7;
						local107 = local16.z >> 7;
						if (local97 >= 0 && local97 < 104 && local107 >= 0 && local107 < 104) {
							local171 = Static31.anIntArrayArray6[local97][local107]++;
						}
					}
				} else if (((local42 & 0x1) != 0 || (local16.x & 0x7F) == 0 && (local16.z & 0x7F) == 0) && ((local42 & 0x1) != 1 || (local16.x & 0x7F) == 64 && (local16.z & 0x7F) == 64)) {
					local97 = local16.x - local42 * 64 >> 7;
					local107 = local16.z - local42 * 64 >> 7;
					local113 = local16.method2693() + local97;
					if (local97 < 0) {
						local97 = 0;
					}
					if (local113 > 104) {
						local113 = 104;
					}
					local133 = local107 + local16.method2693();
					if (local107 < 0) {
						local107 = 0;
					}
					if (local133 > 104) {
						local133 = 104;
					}
					for (local149 = local97; local149 < local113; local149++) {
						for (local158 = local107; local158 < local133; local158++) {
							local171 = Static31.anIntArrayArray6[local149][local158]++;
						}
					}
				}
			}
		}
		label200: for (local7 = 0; local7 < npcCount; local7++) {
			local16 = npcs[npcIds[local7]];
			@Pc(262) long local262 = (long) npcIds[local7] << 32 | 0x20000000L;
			if (local16 != null && local16.method2682() && local16.aClass96_1.aBoolean182 == arg0 && local16.aClass96_1.method2933()) {
				local107 = local16.method2693();
				if (local107 == 1) {
					if ((local16.x & 0x7F) == 64 && (local16.z & 0x7F) == 64) {
						local113 = local16.x >> 7;
						local133 = local16.z >> 7;
						if (local113 < 0 || local113 >= 104 || local133 < 0 || local133 >= 104) {
							continue;
						}
						if (Static31.anIntArrayArray6[local113][local133] > 1) {
							local171 = Static31.anIntArrayArray6[local113][local133]--;
							continue;
						}
					}
				} else if ((local107 & 0x1) == 0 && (local16.x & 0x7F) == 0 && (local16.z & 0x7F) == 0 || (local107 & 0x1) == 1 && (local16.x & 0x7F) == 64 && (local16.z & 0x7F) == 64) {
					local113 = local16.x - local107 * 64 >> 7;
					local133 = local16.z - local107 * 64 >> 7;
					local158 = local133 + local107;
					if (local133 < 0) {
						local133 = 0;
					}
					@Pc(368) boolean local368 = true;
					local149 = local113 + local107;
					if (local158 > 104) {
						local158 = 104;
					}
					if (local113 < 0) {
						local113 = 0;
					}
					if (local149 > 104) {
						local149 = 104;
					}
					@Pc(396) int local396;
					@Pc(401) int local401;
					for (local396 = local113; local396 < local149; local396++) {
						for (local401 = local133; local401 < local158; local401++) {
							if (Static31.anIntArrayArray6[local396][local401] <= 1) {
								local368 = false;
								break;
							}
						}
					}
					if (local368) {
						local396 = local113;
						while (true) {
							if (local396 >= local149) {
								continue label200;
							}
							for (local401 = local133; local401 < local158; local401++) {
								local171 = Static31.anIntArrayArray6[local396][local401]--;
							}
							local396++;
						}
					}
				}
				if (!local16.aClass96_1.aBoolean183) {
					local262 |= Long.MIN_VALUE;
				}
				local16.anInt3424 = getAvH(minusedlevel, local16.x, local16.z);
				World.addDynamic(minusedlevel, local16.x, local16.z, local16.anInt3424, local107 * 64 + 60 - 64, local16, local16.anInt3381, local262, local16.aBoolean171);
			}
		}
	}

	@OriginalMember(owner = "client!p", name = "a", descriptor = "(I)V")
	public static void method3395() {
		if (loginStep == 5) {
			loginStep = 6;
		}
	}

	@OriginalMember(owner = "client!pi", name = "a", descriptor = "(JI)V")
	public static void method3500(@OriginalArg(0) long arg0) {
		if (arg0 == 0L) {
			return;
		}
		for (@Pc(13) int local13 = 0; local13 < Static9.anInt178; local13++) {
			if (aLongArray3[local13] == arg0) {
				Static9.anInt178--;
				for (@Pc(41) int local41 = local13; local41 < Static9.anInt178; local41++) {
					aClass100Array92[local41] = aClass100Array92[local41 + 1];
					Static104.anIntArray255[local41] = Static104.anIntArray255[local41 + 1];
					Static214.aClass100Array170[local41] = Static214.aClass100Array170[local41 + 1];
					aLongArray3[local41] = aLongArray3[local41 + 1];
					Static106.anIntArray258[local41] = Static106.anIntArray258[local41 + 1];
					Static3.aBooleanArray135[local41] = Static3.aBooleanArray135[local41 + 1];
				}
				friendTransmitNum = transmitNum;
				out.p1Enc(57);
				out.p8(arg0);
				break;
			}
		}
	}

	@OriginalMember(owner = "client!pi", name = "c", descriptor = "(III)V")
	public static void method3502(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(13) int local13 = arg0;
		if (arg0 > 25) {
			local13 = 25;
		}
		arg0--;
		@Pc(23) int local23 = anIntArray514[arg0];
		@Pc(27) int local27 = anIntArray209[arg0];
		if (arg1 == 0) {
			out.p1Enc(215);
			out.p1(local13 + local13 + 3);
		}
		if (arg1 == 1) {
			out.p1Enc(39);
			out.p1(local13 + local13 + 3 + 14);
		}
		if (arg1 == 2) {
			out.p1Enc(77);
			out.p1(local13 + local13 + 3);
		}
		out.method2216(ClientKeyboardListener.keyHeld[82] ? 1 : 0);
		out.p2(mapBuildBaseX + local23);
		out.method2209(mapBuildBaseZ + local27);
		Static84.anInt2255 = anIntArray209[0];
		anInt2939 = anIntArray514[0];
		for (@Pc(126) int local126 = 1; local126 < local13; local126++) {
			arg0--;
			out.method2216(anIntArray514[arg0] - local23);
			out.method2169(anIntArray209[arg0] - local27);
		}
	}

	@OriginalMember(owner = "client!qe", name = "b", descriptor = "(II)V")
	public static void method3628(@OriginalArg(1) int arg0) {
		menuNumEntries--;
		if (menuNumEntries == arg0) {
			return;
		}
		Static289.method2617(aClass100Array168, arg0 + 1, aClass100Array168, arg0, menuNumEntries - arg0);
		Static289.method2617(aClass100Array160, arg0 + 1, aClass100Array160, arg0, menuNumEntries - arg0);
		Static289.method2613(anIntArray382, arg0 + 1, anIntArray382, arg0, menuNumEntries - arg0);
		Static289.method2616(menuAction, arg0 + 1, menuAction, arg0, menuNumEntries - arg0);
		Static289.method2611(aLongArray5, arg0 + 1, aLongArray5, arg0, menuNumEntries - arg0);
		Static289.method2613(anIntArray408, arg0 + 1, anIntArray408, arg0, menuNumEntries - arg0);
		Static289.method2613(anIntArray142, arg0 + 1, anIntArray142, arg0, menuNumEntries - arg0);
	}

	@OriginalMember(owner = "client!aj", name = "a", descriptor = "(BILclient!be;)I")
	public static int method118(@OriginalArg(1) int arg0, @OriginalArg(2) IfType arg1) {
		if (!getActive(arg1).method503(arg0) && arg1.onop == null) {
			return -1;
		} else if (arg1.anIntArray39 == null || arg0 >= arg1.anIntArray39.length) {
			return -1;
		} else {
			return arg1.anIntArray39[arg0];
		}
	}

	@OriginalMember(owner = "client!qj", name = "a", descriptor = "(Lclient!be;BI)Lclient!na;")
	public static JagString method3677(@OriginalArg(0) IfType arg0, @OriginalArg(2) int arg1) {
		if (!getActive(arg0).method503(arg1) && arg0.onop == null) {
			return null;
		} else if (arg0.aClass100Array18 == null || arg0.aClass100Array18.length <= arg1 || arg0.aClass100Array18[arg1] == null || arg0.aClass100Array18[arg1].method3144().length() == 0) {
			return aBoolean154 ? JagString.join(new JagString[] { Static207.aClass100_903, JagString.parseInt(arg1) }) : null;
		} else {
			return arg0.aClass100Array18[arg1];
		}
	}

	@OriginalMember(owner = "client!rc", name = "d", descriptor = "(I)V")
	public static void method930() {
		if (midiPcmPlayer != null) {
			midiPcmPlayer.shutdown();
		}
		if (soundPcmPlayer != null) {
			soundPcmPlayer.shutdown();
		}
		Static41.init(lowMem);
		midiPcmPlayer = PcmPlayer.getPlayer(22050, signlink, canvas, 0);
		midiPcmPlayer.playStream(midiPlayer);
		soundPcmPlayer = PcmPlayer.getPlayer(2048, signlink, canvas, 1);
		soundPcmPlayer.playStream(soundMixer);
	}

	@OriginalMember(owner = "client!rl", name = "i", descriptor = "(I)V")
	public static void locChangePostBuildCorrect() {
		for (@Pc(10) LocChange local10 = (LocChange) aClass69_27.head(); local10 != null; local10 = (LocChange) aClass69_27.next()) {
			if (local10.endTime == -1) {
				local10.startTime = 0;
				locChangeSetOld(local10);
			} else {
				local10.unlink();
			}
		}
	}

	@OriginalMember(owner = "client!rm", name = "a", descriptor = "(ZIIIILclient!ak;I)Lclient!ak;")
	public static ModelLit method3800(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) ModelLit arg4, @OriginalArg(6) int arg5) {
		@Pc(4) long local4 = (long) arg2;
		@Pc(10) ModelLit local10 = (ModelLit) Static110.aClass99_15.find(local4);
		if (local10 == null) {
			@Pc(22) ModelUnlit local22 = ModelUnlit.method1686(models, arg2);
			if (local22 == null) {
				return null;
			}
			local10 = local22.method1679(64, 768, -50, -10, -50);
			Static110.aClass99_15.put(local10, local4);
		}
		@Pc(42) int local42 = arg4.method4562();
		@Pc(45) int local45 = arg4.method4561();
		@Pc(48) int local48 = arg4.method4576();
		@Pc(51) int local51 = arg4.method4550();
		local10 = local10.method4560(true, true, true);
		if (arg0 != 0) {
			local10.method4554(arg0);
		}
		@Pc(94) int local94;
		if (glRenderer) {
			@Pc(68) GlModelLit local68 = (GlModelLit) local10;
			if (arg5 != getAvH(minusedlevel, arg3 + local42, arg1 + local48) || arg5 != getAvH(minusedlevel, arg3 + local45, local51 + arg1)) {
				for (local94 = 0; local94 < local68.anInt5295; local94++) {
					local68.anIntArray465[local94] += getAvH(minusedlevel, local68.anIntArray461[local94] + arg3, local68.anIntArray466[local94] + arg1) - arg5;
				}
				local68.aClass127_4.aBoolean235 = false;
				local68.aClass5_1.aBoolean3 = false;
			}
		} else {
			@Pc(142) SoftwareModelLit local142 = (SoftwareModelLit) local10;
			if (arg5 != getAvH(minusedlevel, local42 + arg3, local48 + arg1) || arg5 != getAvH(minusedlevel, arg3 + local45, local51 + arg1)) {
				for (local94 = 0; local94 < local142.anInt5788; local94++) {
					local142.anIntArray527[local94] += getAvH(minusedlevel, arg3 + local142.anIntArray528[local94], local142.anIntArray531[local94] + arg1) - arg5;
				}
				local142.aBoolean305 = false;
			}
		}
		return local10;
	}

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "(IIILclient!km;)V")
	public static void method3855(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) ClientNPC arg2) {
		if (arg2.anInt3369 == arg1 && arg1 != -1) {
			@Pc(10) SeqType local10 = SeqType.list(arg1);
			@Pc(13) int local13 = local10.duplicatebehavior;
			if (local13 == 1) {
				arg2.anInt3373 = 1;
				arg2.anInt3425 = 0;
				arg2.anInt3360 = 0;
				arg2.anInt3371 = 0;
				arg2.anInt3420 = arg0;
				method2836(arg2.z, local10, arg2.x, false, arg2.anInt3425);
			}
			if (local13 == 2) {
				arg2.anInt3371 = 0;
			}
		} else if (arg1 == -1 || arg2.anInt3369 == -1 || SeqType.list(arg1).priority >= SeqType.list(arg2.anInt3369).priority) {
			arg2.anInt3360 = 0;
			arg2.anInt3369 = arg1;
			arg2.anInt3373 = 1;
			arg2.anInt3371 = 0;
			arg2.anInt3420 = arg0;
			arg2.anInt3405 = arg2.anInt3409;
			arg2.anInt3425 = 0;
			if (arg2.anInt3369 != -1) {
				method2836(arg2.z, SeqType.list(arg2.anInt3369), arg2.x, false, arg2.anInt3425);
			}
		}
	}

	@OriginalMember(owner = "client!sf", name = "b", descriptor = "(B)V")
	public static void method3901() {
		@Pc(16) int local16 = Static280.aClass3_Sub2_Sub9_43.method2858(Text.aClass100_998);
		@Pc(18) int local18;
		@Pc(27) int local27;
		for (local18 = 0; local18 < menuNumEntries; local18++) {
			local27 = Static280.aClass3_Sub2_Sub9_43.method2858(Static269.method2228(local18));
			if (local27 > local16) {
				local16 = local27;
			}
		}
		local18 = menuNumEntries * 15 + 21;
		@Pc(43) int local43 = anInt1892;
		local16 += 8;
		local27 = anInt3751 - local16 / 2;
		if (local43 + local18 > anInt5554) {
			local43 = anInt5554 - local18;
		}
		if (anInt1448 < local27 + local16) {
			local27 = anInt1448 - local16;
		}
		if (local27 < 0) {
			local27 = 0;
		}
		if (local43 < 0) {
			local43 = 0;
		}
		if (anInt3953 == 1) {
			if (anInt3751 == anInt5850 && anInt5895 == anInt1892) {
				Static13.anInt436 = menuNumEntries * 15 + (Static261.aBoolean298 ? 26 : 22);
				anInt3953 = 0;
				Static229.anInt5138 = local43;
				Static183.anInt4271 = local27;
				isMenuOpen = true;
				Static24.anInt761 = local16;
			}
		} else if (anInt3751 == ClientMouseListener.mouseClickX && anInt1892 == ClientMouseListener.mouseClickY) {
			Static183.anInt4271 = local27;
			anInt3953 = 0;
			Static24.anInt761 = local16;
			Static229.anInt5138 = local43;
			Static13.anInt436 = (Static261.aBoolean298 ? 26 : 22) + menuNumEntries * 15;
			isMenuOpen = true;
		} else {
			anInt5895 = ClientMouseListener.mouseClickY;
			anInt5850 = ClientMouseListener.mouseClickX;
			anInt3953 = 1;
		}
	}

	@OriginalMember(owner = "client!th", name = "a", descriptor = "(ZIIII)V")
	public static void method4540(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		Static97.aLong89 = 0L;
		@Pc(4) int local4 = Static144.method2736();
		if (arg1 == 3 || local4 == 3) {
			arg0 = true;
		}
		@Pc(44) boolean local44 = false;
		if (local4 > 0 != arg1 > 0) {
			local44 = true;
		}
		if (arg0 && arg1 > 0) {
			local44 = true;
		}
		method3560(arg0, arg1, local44, local4, arg2, arg3);
	}

	@OriginalMember(owner = "client!u", name = "a", descriptor = "(Z)V")
	public static void addMapAnim() {
		for (@Pc(9) MapSpotAnimNode local9 = (MapSpotAnimNode) Static99.aClass69_64.head(); local9 != null; local9 = (MapSpotAnimNode) Static99.aClass69_64.next()) {
			@Pc(15) MapSpotAnim local15 = local9.aClass8_Sub2_1;
			if (local15.anInt606 != minusedlevel || local15.aBoolean41) {
				local9.unlink();
			} else if (local15.anInt590 <= loopCycle) {
				local15.method558(anInt4247);
				if (local15.aBoolean41) {
					local9.unlink();
				} else {
					World.addDynamic(local15.anInt606, local15.anInt604, local15.anInt598, local15.anInt599, 60, local15, 0, -1L, false);
				}
			}
		}
	}

	@OriginalMember(owner = "client!u", name = "a", descriptor = "(Lclient!me;IIII)V")
	public static void method4240(@OriginalArg(0) NPCType arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (menuNumEntries >= 400) {
			return;
		}
		if (arg0.anIntArray357 != null) {
			arg0 = arg0.method2932();
		}
		if (arg0 == null || !arg0.aBoolean183) {
			return;
		}
		@Pc(35) JagString local35 = arg0.aClass100_738;
		if (arg0.anInt3726 != 0) {
			@Pc(47) JagString local47 = game == 1 ? Text.aClass100_746 : Text.aClass100_386;
			local35 = JagString.join(new JagString[] { local35, method2420(arg0.anInt3726, localPlayer.combatLevel), Static123.aClass100_593, local47, JagString.parseInt(arg0.anInt3726), Static72.aClass100_448 });
		}
		if (Static260.anInt5014 == 1) {
			addMenuOption(Static169.anInt4075, (long) arg2, JagString.join(new JagString[] { Static34.aClass100_203, Static201.aClass100_407, local35 }), arg1, (short) 26, Text.aClass100_937, arg3);
		} else if (targetMode) {
			@Pc(378) ParamType local378 = Static121.anInt3039 == -1 ? null : ParamType.list(Static121.anInt3039);
			if ((Static274.anInt4999 & 0x2) != 0 && (local378 == null || arg0.method2936(Static121.anInt3039, local378.anInt2667) != local378.anInt2667)) {
				addMenuOption(Static246.anInt5393, (long) arg2, JagString.join(new JagString[] { Static78.aClass100_466, Static201.aClass100_407, local35 }), arg1, (short) 45, Static102.aClass100_545, arg3);
			}
		} else {
			@Pc(129) JagString[] local129 = arg0.aClass100Array116;
			if (Static208.aBoolean237) {
				local129 = Static279.method4664(local129);
			}
			@Pc(140) int local140;
			if (local129 != null) {
				for (local140 = 4; local140 >= 0; local140--) {
					if (local129[local140] != null && (game != 0 || !local129[local140].equalsIgnoreCase(Text.aClass100_1055))) {
						@Pc(161) byte local161 = 0;
						if (local140 == 0) {
							local161 = 17;
						}
						if (local140 == 1) {
							local161 = 16;
						}
						@Pc(176) int local176 = -1;
						if (local140 == 2) {
							local161 = 4;
						}
						if (local140 == 3) {
							local161 = 19;
						}
						if (arg0.anInt3750 == local140) {
							local176 = arg0.anInt3719;
						}
						if (local140 == arg0.anInt3743) {
							local176 = arg0.anInt3735;
						}
						if (local140 == 4) {
							local161 = 2;
						}
						addMenuOption(local176, (long) arg2, JagString.join(new JagString[] {aClass100_965, local35 }), arg1, local161, local129[local140], arg3);
					}
				}
			}
			if (game == 0 && local129 != null) {
				for (local140 = 4; local140 >= 0; local140--) {
					if (local129[local140] != null && local129[local140].equalsIgnoreCase(Text.aClass100_1055)) {
						@Pc(271) short local271 = 0;
						if (arg0.anInt3726 > localPlayer.combatLevel) {
							local271 = 2000;
						}
						@Pc(281) short local281 = 0;
						if (local140 == 0) {
							local281 = 17;
						}
						if (local140 == 1) {
							local281 = 16;
						}
						if (local140 == 2) {
							local281 = 4;
						}
						if (local140 == 3) {
							local281 = 19;
						}
						if (local140 == 4) {
							local281 = 2;
						}
						if (local281 != 0) {
							local281 += local271;
						}
						addMenuOption(arg0.anInt3752, (long) arg2, JagString.join(new JagString[] {aClass100_965, local35 }), arg1, local281, local129[local140], arg3);
					}
				}
			}
			addMenuOption(Static225.anInt5073, (long) arg2, JagString.join(new JagString[] {aClass100_965, local35 }), arg1, (short) 1007, Text.aClass100_675, arg3);
		}
	}

	@OriginalMember(owner = "client!uf", name = "a", descriptor = "(B)V")
	public static void method4273() {
		@Pc(14) int local14 = localPlayer.x + macroCameraAngle;
		@Pc(20) int local20 = localPlayer.z + macroCameraZ;
		if (Static81.anInt2223 - local14 < -500 || Static81.anInt2223 - local14 > 500 || Static111.anInt2900 - local20 < -500 || Static111.anInt2900 - local20 > 500) {
			Static81.anInt2223 = local14;
			Static111.anInt2900 = local20;
		}
		if (Static111.anInt2900 != local20) {
			Static111.anInt2900 += (local20 - Static111.anInt2900) / 16;
		}
		if (Static81.anInt2223 != local14) {
			Static81.anInt2223 += (local14 - Static81.anInt2223) / 16;
		}
		if (aBoolean63) {
			for (@Pc(93) int local93 = 0; local93 < keypresses; local93++) {
				@Pc(104) int local104 = keypressKeycodes[local93];
				if (local104 == 98) {
					anInt2031 = anInt2031 + 47 & 0xFFFFFFF0;
				} else if (local104 == 99) {
					anInt2031 = anInt2031 - 17 & 0xFFFFFFF0;
				} else if (local104 == 96) {
					anInt1747 = anInt1747 - 65 & 0xFFFFFF80;
				} else if (local104 == 97) {
					anInt1747 = anInt1747 + 191 & 0xFFFFFF80;
				}
			}
		} else {
			if (ClientKeyboardListener.keyHeld[98]) {
				Static56.anInt1743 += (12 - Static56.anInt1743) / 2;
			} else if (ClientKeyboardListener.keyHeld[99]) {
				Static56.anInt1743 += (-Static56.anInt1743 - 12) / 2;
			} else {
				Static56.anInt1743 /= 2;
			}
			if (ClientKeyboardListener.keyHeld[96]) {
				Static38.anInt1203 += (-Static38.anInt1203 - 24) / 2;
			} else if (ClientKeyboardListener.keyHeld[97]) {
				Static38.anInt1203 += (24 - Static38.anInt1203) / 2;
			} else {
				Static38.anInt1203 /= 2;
			}
			anInt2031 += Static56.anInt1743 / 2;
			anInt1747 += Static38.anInt1203 / 2;
		}
		followCamera();
	}

	@OriginalMember(owner = "client!vd", name = "a", descriptor = "(IIIIBI)V")
	public static void method3849(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
		Static133.anInt5230 = arg2;
		Static265.anInt5765 = arg1;
		Static233.anInt5217 = arg4;
		Static251.anInt5449 = arg3;
		Static57.anInt1744 = arg0;
		if (Static233.anInt5217 >= 100) {
			@Pc(30) int local30 = Static251.anInt5449 * 128 + 64;
			@Pc(36) int local36 = Static265.anInt5765 * 128 + 64;
			@Pc(44) int local44 = getAvH(minusedlevel, local30, local36) - Static57.anInt1744;
			@Pc(49) int local49 = local44 - anInt40;
			@Pc(54) int local54 = local30 - anInt3439;
			@Pc(59) int local59 = local36 - anInt3302;
			@Pc(70) int local70 = (int) Math.sqrt((double) (local59 * local59 + local54 * local54));
			anInt5333 = (int) (Math.atan2((double) local49, (double) local70) * 325.949D) & 0x7FF;
			anInt4358 = (int) (Math.atan2((double) local54, (double) local59) * -325.949D) & 0x7FF;
			if (anInt5333 < 128) {
				anInt5333 = 128;
			}
			if (anInt5333 > 383) {
				anInt5333 = 383;
			}
		}
		anInt5096 = 2;
	}

	@OriginalMember(owner = "client!vf", name = "a", descriptor = "(IB)Lclient!na;")
	public static JagString niceNumber(@OriginalArg(0) int arg0) {
		return arg0 >= 999999999 ? Static220.aClass100_930 : JagString.parseInt(arg0);
	}

	@OriginalMember(owner = "client!vk", name = "a", descriptor = "(IZIII[Lclient!be;)V")
	public static void method4190(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) IfType[] arg4) {
		for (@Pc(3) int local3 = 0; local3 < arg4.length; local3++) {
			@Pc(19) IfType local19 = arg4[local3];
			if (local19 != null && local19.layerId == arg0) {
				Static150.method2801(arg3, arg2, local19, arg1);
				Static111.method2291(local19, arg3, arg2);
				if (local19.scrollWidth - local19.anInt445 < local19.anInt489) {
					local19.anInt489 = local19.scrollWidth - local19.anInt445;
				}
				if (local19.anInt468 > local19.scrollHeight - local19.anInt459) {
					local19.anInt468 = local19.scrollHeight - local19.anInt459;
				}
				if (local19.anInt468 < 0) {
					local19.anInt468 = 0;
				}
				if (local19.anInt489 < 0) {
					local19.anInt489 = 0;
				}
				if (local19.type == 0) {
					method531(local19, arg1);
				}
			}
		}
	}

	@OriginalMember(owner = "client!wh", name = "a", descriptor = "(IILclient!na;)V")
	public static void opPlayer(@OriginalArg(0) int arg0, @OriginalArg(2) JagString arg1) {
		@Pc(7) JagString local7 = arg1.method3159().method3125();
		@Pc(13) boolean local13 = false;
		for (@Pc(15) int local15 = 0; local15 < playerCount; local15++) {
			@Pc(28) ClientPlayer local28 = players[playerIds[local15]];
			if (local28 != null && local28.name != null && local28.name.equalsIgnoreCase(local7)) {
				local13 = true;
				tryMove(localPlayer.routeZ[0], 0, 1, false, 0, local28.routeX[0], 1, 0, 2, local28.routeZ[0], localPlayer.routeX[0]);
				if (arg0 == 1) {
					out.p1Enc(68);
					out.method2191(playerIds[local15]);
				} else if (arg0 == 4) {
					out.p1Enc(180);
					out.method2191(playerIds[local15]);
				} else if (arg0 == 5) {
					out.p1Enc(4);
					out.method2222(playerIds[local15]);
				} else if (arg0 == 6) {
					out.p1Enc(133);
					out.method2222(playerIds[local15]);
				} else if (arg0 == 7) {
					out.p1Enc(114);
					out.method2191(playerIds[local15]);
				}
				break;
			}
		}
		if (!local13) {
			addChat(TitleScreen.AUTO_EMPTY, 0, JagString.join(new JagString[] { Text.aClass100_478, local7 }));
		}
	}

	@OriginalMember(owner = "client!wl", name = "b", descriptor = "(I)V")
	public static void sortMinimenu() {
		@Pc(3) boolean local3 = false;
		while (!local3) {
			local3 = true;
			for (@Pc(13) int local13 = 0; local13 < menuNumEntries - 1; local13++) {
				if (menuAction[local13] < 1000 && menuAction[local13 + 1] > 1000) {
					@Pc(41) JagString local41 = aClass100Array160[local13];
					local3 = false;
					aClass100Array160[local13] = aClass100Array160[local13 + 1];
					aClass100Array160[local13 + 1] = local41;
					@Pc(61) JagString local61 = aClass100Array168[local13];
					aClass100Array168[local13] = aClass100Array168[local13 + 1];
					aClass100Array168[local13 + 1] = local61;
					@Pc(79) int local79 = anIntArray408[local13];
					anIntArray408[local13] = anIntArray408[local13 + 1];
					anIntArray408[local13 + 1] = local79;
					@Pc(97) int local97 = anIntArray142[local13];
					anIntArray142[local13] = anIntArray142[local13 + 1];
					anIntArray142[local13 + 1] = local97;
					@Pc(115) int local115 = anIntArray382[local13];
					anIntArray382[local13] = anIntArray382[local13 + 1];
					anIntArray382[local13 + 1] = local115;
					@Pc(133) short local133 = menuAction[local13];
					menuAction[local13] = menuAction[local13 + 1];
					menuAction[local13 + 1] = local133;
					@Pc(151) long local151 = aLongArray5[local13];
					aLongArray5[local13] = aLongArray5[local13 + 1];
					aLongArray5[local13 + 1] = local151;
				}
			}
		}
	}

	@OriginalMember(owner = "client!km", name = "f", descriptor = "(I)Z")
	public static boolean highDetail() {
		return glRenderer ? true : Static162.aBoolean190;
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(ZB)V")
	public static void method1835(@OriginalArg(0) boolean arg0) {
		@Pc(11) byte local11;
		@Pc(13) byte[][] local13;
		if (glRenderer && arg0) {
			local11 = 1;
			local13 = aByteArrayArray14;
		} else {
			local13 = aByteArrayArray13;
			local11 = 4;
		}
		for (@Pc(21) int local21 = 0; local21 < local11; local21++) {
			doAudio();
			for (@Pc(32) int local32 = 0; local32 < 13; local32++) {
				for (@Pc(39) int local39 = 0; local39 < 13; local39++) {
					@Pc(52) int local52 = Static187.anIntArrayArrayArray18[local21][local32][local39];
					@Pc(54) boolean local54 = false;
					if (local52 != -1) {
						@Pc(65) int local65 = local52 >> 24 & 0x3;
						if (!arg0 || local65 == 0) {
							@Pc(76) int local76 = local52 >> 3 & 0x7FF;
							@Pc(82) int local82 = local52 >> 1 & 0x3;
							@Pc(88) int local88 = local52 >> 14 & 0x3FF;
							@Pc(98) int local98 = (local88 / 8 << 8) + local76 / 8;
							for (@Pc(100) int local100 = 0; local100 < mapBuildIndex.length; local100++) {
								if (mapBuildIndex[local100] == local98 && local13[local100] != null) {
									ClientBuild.method4228(local82, local32 * 8, local21, levelCollisionMap, local39 * 8, local13[local100], local65, (local76 & 0x7) * 8, (local88 & 0x7) * 8, arg0);
									local54 = true;
									break;
								}
							}
						}
					}
					if (!local54) {
						Static23.method645(local21, local39 * 8, local32 * 8, 8, 8);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(ILclient!be;)Lclient!be;")
	public static IfType method1836(@OriginalArg(1) IfType arg0) {
		@Pc(12) IfType local12 = method938(arg0);
		if (local12 == null) {
			local12 = arg0.aClass13_5;
		}
		return local12;
	}

	@OriginalMember(owner = "client!wa", name = "a", descriptor = "(Z)V")
	public static void method2170() {
		Static250.anInt5434++;
	}

	@OriginalMember(owner = "client!en", name = "a", descriptor = "(IIIB)V")
	public static void tele(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(66) JagString local66 = JagString.join(new JagString[] { Static96.aClass100_521, JagString.parseInt(arg2), Static159.aClass100_760, JagString.parseInt(arg0 >> 6), Static159.aClass100_760, JagString.parseInt(arg1 >> 6), Static159.aClass100_760, JagString.parseInt(arg0 & 0x3F), Static159.aClass100_760, JagString.parseInt(arg1 & 0x3F) });
		local66.method3129();
		doCheat(local66);
	}

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(BIIIII)V")
	public static void drawScrollbar(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		scrollbar[0].plotSprite(arg2, arg3);
		scrollbar[1].plotSprite(arg2, arg4 + arg3 - 16);
		@Pc(35) int local35 = arg4 * (arg4 - 32) / arg1;
		if (local35 < 8) {
			local35 = 8;
		}
		@Pc(54) int local54 = arg0 * (arg4 - local35 - 32) / (arg1 - arg4);
        if (glRenderer) {
            Static46.method1186(arg2, arg3 + 16, 16, arg4 - 32, Static182.anInt4306);
            Static46.method1186(arg2, arg3 + local54 + 16, 16, local35, Static53.anInt1704);
            Static46.method1176(arg2, local54 + arg3 + 16, local35, Static219.anInt4938);
            Static46.method1176(arg2 + 1, local54 + 16 + arg3, local35, Static219.anInt4938);
            Static46.method1174(arg2, local54 + arg3 + 16, 16, Static219.anInt4938);
            Static46.method1174(arg2, local54 + arg3 + 17, 16, Static219.anInt4938);
            Static46.method1176(arg2 + 15, arg3 + (16 - -local54), local35, Static20.anInt671);
            Static46.method1176(arg2 + 14, arg3 - -local54 + 17, local35 - 1, Static20.anInt671);
            Static46.method1174(arg2, local35 + arg3 + local54 + 15, 16, Static20.anInt671);
            Static46.method1174(arg2 + 1, arg3 + 14 - -local54 + local35, 15, Static20.anInt671);
        } else {
            Pix2D.method2495(arg2, arg3 + 16, 16, arg4 - 32, Static182.anInt4306);
            Pix2D.method2495(arg2, local54 + arg3 + 16, 16, local35, Static53.anInt1704);
            Pix2D.method2490(arg2, local54 + arg3 + 16, local35, Static219.anInt4938);
            Pix2D.method2490(arg2 + 1, local54 + 16 + arg3, local35, Static219.anInt4938);
            Pix2D.method2489(arg2, arg3 + local54 + 16, 16, Static219.anInt4938);
            Pix2D.method2489(arg2, arg3 + local54 + 17, 16, Static219.anInt4938);
            Pix2D.method2490(arg2 + 15, local54 + 16 + arg3, local35, Static20.anInt671);
            Pix2D.method2490(arg2 + 14, arg3 - -17 - -local54, local35 - 1, Static20.anInt671);
            Pix2D.method2489(arg2, local35 + arg3 + local54 + 15, 16, Static20.anInt671);
            Pix2D.method2489(arg2 + 1, local35 + arg3 - (-local54 + -14), 15, Static20.anInt671);
        }
    }

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Z)V")
	public static void method182() {
		aClass13Array13 = null;
		method86(toplevelinterface, 0, anInt1448, 0, -1, anInt5554, 0, 0);
		if (aClass13Array13 != null) {
			drawLayer(0, anInt3126, anInt4696, aClass13Array13, anInt1448, -1412584499, 0, anInt5554, aClass13_1.drawCount);
			aClass13Array13 = null;
		}
	}

	@OriginalMember(owner = "client!em", name = "a", descriptor = "(Lclient!be;Lclient!qf;IIIBI)V")
	public static void method1446(@OriginalArg(0) IfType arg0, @OriginalArg(1) AbstractPix32 arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
		if (arg1 == null) {
			return;
		}
		@Pc(21) int local21 = arg3 * arg3 + arg2 * arg2;
		@Pc(27) int local27 = macroMinimapAngle + anInt1747 & 0x7FF;
		@Pc(39) int local39 = Math.max(arg0.anInt445 / 2, arg0.anInt459 / 2) + 10;
		if (local39 * local39 < local21) {
			return;
		}
		@Pc(50) int local50 = Pix3D.sinTable[local27];
		@Pc(58) int local58 = local50 * 256 / (macroMinimapZoom + 256);
		@Pc(62) int local62 = Pix3D.cosTable[local27];
		@Pc(70) int local70 = local62 * 256 / (macroMinimapZoom + 256);
		@Pc(81) int local81 = local58 * arg2 + arg3 * local70 >> 16;
		@Pc(92) int local92 = local70 * arg2 - arg3 * local58 >> 16;
		if (glRenderer) {
			((GlPix32) arg1).method1425(arg0.anInt445 / 2 + arg5 + local81 - arg1.anInt1860 / 2, arg0.anInt459 / 2 + arg4 - (local92 + arg1.anInt1866 / 2), (GlPix32) arg0.getGraphic(false));
		} else {
			((Pix32) arg1).method312(arg0.anInt445 / 2 + arg5 + local81 - arg1.anInt1860 / 2, -(arg1.anInt1866 / 2) + arg0.anInt459 / 2 + arg4 + -local92, arg0.anIntArray37, arg0.anIntArray45);
		}
	}

	@OriginalMember(owner = "client!hi", name = "a", descriptor = "(IIIIILclient!be;Z)V")
	public static void method1960(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) IfType arg5) {
		@Pc(13) int local13 = arg3 * arg3 + arg4 * arg4;
		if (local13 > 360000) {
			return;
		}
		@Pc(30) int local30 = Math.min(arg5.anInt445 / 2, arg5.anInt459 / 2);
		if (local30 * local30 >= local13) {
			method1446(arg5, Static149.aClass3_Sub2_Sub1Array7[arg0], arg4, arg3, arg1, arg2);
			return;
		}
		local30 -= 10;
		@Pc(58) int local58 = macroMinimapAngle + anInt1747 & 0x7FF;
		@Pc(62) int local62 = Pix3D.cosTable[local58];
		@Pc(66) int local66 = Pix3D.sinTable[local58];
		@Pc(74) int local74 = local66 * 256 / (macroMinimapZoom + 256);
		@Pc(82) int local82 = local62 * 256 / (macroMinimapZoom + 256);
		@Pc(93) int local93 = arg4 * local74 + local82 * arg3 >> 16;
		@Pc(104) int local104 = arg4 * local82 - local74 * arg3 >> 16;
		@Pc(110) double local110 = Math.atan2((double) local93, (double) local104);
		@Pc(117) int local117 = (int) (Math.sin(local110) * (double) local30);
		@Pc(124) int local124 = (int) (Math.cos(local110) * (double) local30);
		if (glRenderer) {
			((GlPix32) Static277.aClass3_Sub2_Sub1Array12[arg0]).method1428((arg5.anInt445 / 2 + arg2 + local117) * 16, (arg5.anInt459 / 2 + arg1 - local124) * 16, (int) (local110 * 10430.378D));
		} else {
			((Pix32) Static277.aClass3_Sub2_Sub1Array12[arg0]).method306(local117 + arg5.anInt445 / 2 + arg2 - 10, arg5.anInt459 / 2 + -10 + arg1 + -local124, local110);
		}
	}

	@OriginalMember(owner = "client!hi", name = "a", descriptor = "(Lclient!be;B)Lclient!na;")
	public static JagString method1963(@OriginalArg(0) IfType arg0) {
		if (getActive(arg0).method512() == 0) {
			return null;
		} else if (arg0.targetVerb == null || arg0.targetVerb.method3144().length() == 0) {
			return aBoolean154 ? Static143.aClass100_668 : null;
		} else {
			return arg0.targetVerb;
		}
	}

	@OriginalMember(owner = "client!client", name = "f", descriptor = "(I)V")
	@Override
	protected final void mainredraw() {
		if (state == 1000) {
			return;
		}

		@Pc(15) boolean local15 = MidiManager.updateLoading();
		if (local15 && aBoolean173 && midiPcmPlayer != null) {
			midiPcmPlayer.method3570();
		}

		if ((state == 30 || state == 10) && (GameShell.canvasReplaceRecommended || Static97.aLong89 != 0L && Static97.aLong89 < MonotonicTime.currentTime())) {
			method4540(GameShell.canvasReplaceRecommended, Static144.method2736(), Static114.anInt5831, Static22.anInt729);
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
				GameShell.method3662();
				Static97.aLong89 = MonotonicTime.currentTime() + 500L;
			}
		}

		if (GameShell.aFrame2 != null && !GameShell.focus && (state == 30 || state == 10)) {
			method4540(false, Static214.anInt5581, -1, -1);
		}

		@Pc(158) boolean local158 = false;
		if (GameShell.fullredraw) {
			local158 = true;
			GameShell.fullredraw = false;
		}
		if (local158) {
			GameShell.method2704();
		}

		if (GameShell.glRenderer) {
			for (local80 = 0; local80 < 100; local80++) {
				componentRedrawRequested1[local80] = true;
			}
		}

		if (state == 0) {
			GameShell.drawProgress(null, local158, TitleScreen.loadString, TitleScreen.loadPos);
		} else if (state == 5) {
			Static182.method3359(false, Static280.aClass3_Sub2_Sub9_43);
		} else if (state == 10) {
			method2460();
		} else if (state == 25 || state == 28) {
			if (mapLoadingStage == 1) {
				if (mapPrevLoadCount < mapLoadCount) {
					mapPrevLoadCount = mapLoadCount;
				}

				local80 = (mapPrevLoadCount - mapLoadCount) * 50 / mapPrevLoadCount;
				messageBox(false, JagString.join(new JagString[] { Text.aClass100_621, Static229.aClass100_974, JagString.parseInt(local80), Static14.aClass100_80 }));
			} else if (mapLoadingStage == 2) {
				if (locModelLoadPrevCount < locModelLoadCount) {
					locModelLoadPrevCount = locModelLoadCount;
				}

				local80 = (locModelLoadPrevCount - locModelLoadCount) * 50 / locModelLoadPrevCount + 50;
				messageBox(false, JagString.join(new JagString[] { Text.aClass100_621, Static229.aClass100_974, JagString.parseInt(local80), Static14.aClass100_80 }));
			} else {
				messageBox(false, Text.aClass100_621);
			}
		} else if (state == 30) {
			gameDraw();
		} else if (state == 40) {
			messageBox(false, JagString.join(new JagString[] { Text.aClass100_986, Static269.aClass100_556, Text.aClass100_1077 }));
		}

		if (GameShell.glRenderer && state != 0) {
			Static239.method4153();

			for (local80 = 0; local80 < componentDrawCount; local80++) {
				componentRedrawRequested2[local80] = false;
			}
		} else {
			@Pc(388) Graphics local388;
			if ((state == 30 || state == 10) && Static199.anInt4672 == 0 && !local158) {
				try {
					local388 = GameShell.canvas.getGraphics();
					for (local84 = 0; local84 < componentDrawCount; local84++) {
						if (componentRedrawRequested2[local84]) {
							GameShell.drawArea.draw(componentDrawWidth[local84], componentDrawX[local84], componentDrawHeight[local84], local388, componentDrawY[local84]);
							componentRedrawRequested2[local84] = false;
						}
					}
				} catch (@Pc(423) Exception ex) {
					GameShell.canvas.repaint();
				}
			} else if (state != 0) {
				try {
					local388 = GameShell.canvas.getGraphics();
					GameShell.drawArea.method4186(local388);
					for (local84 = 0; local84 < componentDrawCount; local84++) {
						componentRedrawRequested2[local84] = false;
					}
				} catch (@Pc(453) Exception ex) {
					GameShell.canvas.repaint();
				}
			}
		}

		if (Static107.aBoolean147) {
			method3729();
		}

		if (Static164.aBoolean191 && state == 10 && toplevelinterface != -1) {
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

		ClientKeyboardListener.shutdown(GameShell.canvas);
		ClientMouseListener.shutdown(GameShell.canvas);
		if (mouseWheel != null) {
			mouseWheel.removeListeners(GameShell.canvas);
		}
		ClientKeyboardListener.method82();
		ClientMouseListener.method4277();
		mouseWheel = null;

		if (midiPcmPlayer != null) {
			midiPcmPlayer.shutdown();
		}

		if (soundPcmPlayer != null) {
			soundPcmPlayer.shutdown();
		}

		js5Net.shutdown();
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
		setLang(lang);

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
			Static47.aClass100_991 = TitleScreen.AUTO_EMPTY;
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
		GameShell.method3662();
		Static86.aClass80_3 = new Js5CacheQueue();
		js5Net = new Js5Net();
		if (modewhat != 0) {
			Static51.aByteArrayArray8 = new byte[50][];
		}
		Static80.method3615(GameShell.signlink);

		if (modewhere == 0) {
			worldListHost = GlobalConfig.DEFAULT_HOSTNAME; // this.getCodeBase().getHost();
			worldListJs5Port = GlobalConfig.ALTERNATE_PORT + 1; // 443;
			worldListGamePort = GlobalConfig.DEFAULT_PORT + 1; // 43594;
		} else if (modewhere == 1) {
			worldListHost = GlobalConfig.DEFAULT_HOSTNAME; // this.getCodeBase().getHost();
			worldListJs5Port = GlobalConfig.ALTERNATE_PORT + worldid; // Static187.anInt4413 + 50000;
			worldListGamePort = GlobalConfig.DEFAULT_PORT + worldid; // Static187.anInt4413 + 40000;
		} else if (modewhere == 2) {
			worldListHost = GlobalConfig.DEFAULT_HOSTNAME; // "127.0.0.1";
			worldListJs5Port = GlobalConfig.ALTERNATE_PORT + worldid; // Static187.anInt4413 + 50000;
			worldListGamePort = GlobalConfig.DEFAULT_PORT + worldid; // Static187.anInt4413 + 40000;
		}

		if (game == 1) {
			aBoolean199 = true;
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

		js5Port = worldListJs5Port;
		gamePort = worldListGamePort;
		host = worldListHost;
		worldListPort = worldListGamePort;
		aShortArray88 = Static62.aShortArray19 = Static232.aShortArray74 = Static259.aShortArray87 = new short[256];
		loginPort = worldListPort;

		if ((SignLink.anInt5928 == 3 && modewhere != 2) || GlobalConfig.SELECT_DEFAULT_WORLD) {
			anInt3103 = worldid;
		}

		ClientKeyboardListener.setupKeyCodeMap();
		ClientKeyboardListener.addListeners(GameShell.canvas);

		ClientMouseListener.addListeners(GameShell.canvas);

		mouseWheel = MouseWheelInterface.method1150();
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
		js5Net.js5Errors++;
		js5SocketReq = null;
		js5Net.anInt2963 = arg0;
		js5Stream = null;
		js5ConnectState = 0;
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(B)V")
	private void titleScreenLoop() {
		for (keypresses = 0; ClientKeyboardListener.pollKey() && keypresses < 128; keypresses++) {
			keypressKeycodes[keypresses] = ClientKeyboardListener.code;
			keypressKeychars[keypresses] = ClientKeyboardListener.ch;
		}
		anInt4247++;
		if (toplevelinterface != -1) {
			loopInterface(0, 0, 0, GameShell.anInt1448, toplevelinterface, 0, GameShell.anInt5554);
		}
		transmitNum++;
		if (GameShell.glRenderer) {
			label191: for (@Pc(57) int local57 = 0; local57 < 32768; local57++) {
				@Pc(66) ClientNPC local66 = npcs[local57];
				if (local66 != null) {
					@Pc(73) byte local73 = local66.aClass96_1.aByte10;
					if ((local73 & 0x2) > 0 && local66.anInt3409 == 0 && Math.random() * 1000.0D < 10.0D) {
						@Pc(98) int local98 = (int) Math.round(Math.random() * 2.0D - 1.0D);
						@Pc(106) int local106 = (int) Math.round(Math.random() * 2.0D - 1.0D);
						if (local98 != 0 || local106 != 0) {
							local66.aByteArray48[0] = 1;
							local66.routeX[0] = local98 + (local66.x >> 7);
							local66.routeZ[0] = local106 + (local66.z >> 7);
							levelCollisionMap[minusedlevel].method3056(local66.x >> 7, local66.method2693(), false, 0, local66.method2693(), local66.z >> 7);
							if (local66.routeX[0] >= 0 && local66.routeX[0] <= 104 - local66.method2693() && local66.routeZ[0] >= 0 && local66.routeZ[0] <= 104 - local66.method2693() && levelCollisionMap[minusedlevel].method3054(local66.z >> 7, local66.routeZ[0], local66.routeX[0], local66.x >> 7)) {
								if (local66.method2693() > 1) {
									for (@Pc(226) int local226 = local66.routeX[0]; local66.routeX[0] + local66.method2693() > local226; local226++) {
										for (@Pc(246) int local246 = local66.routeZ[0]; local66.routeZ[0] + local66.method2693() > local246; local246++) {
											if ((levelCollisionMap[minusedlevel].flags[local226][local246] & 0x12401FF) != 0) {
												continue label191;
											}
										}
									}
								}
								local66.anInt3409 = 1;
							}
						}
					}
					method2247(local66);
					method949(local66);
					method879(local66);
					levelCollisionMap[minusedlevel].method3043(local66.x >> 7, false, local66.z >> 7, local66.method2693(), local66.method2693());
				}
			}
		}

		if (!GameShell.glRenderer) {
			method2170();
		} else if (loginStep == 0 && accountCreateStep == 0) {
			if (anInt5096 == 2) {
				method2450();
			} else {
				method1008();
			}

			if (anInt3439 >> 7 < 14 || anInt3439 >> 7 >= 90 || anInt3302 >> 7 < 14 || anInt3302 >> 7 >= 90) {
				method740();
			}
		}

		while (true) {
			@Pc(374) HookReq local374;
			@Pc(379) IfType local379;
			@Pc(387) IfType local387;
			do {
				local374 = (HookReq) hookRequestsTimer.popFront();
				if (local374 == null) {
					while (true) {
						do {
							local374 = (HookReq) hookRequestsMouseStop.popFront();
							if (local374 == null) {
								while (true) {
									do {
										local374 = (HookReq) hookRequests.popFront();
										if (local374 == null) {
											if (dragComponent != null) {
												loopIf3Drag();
											}
											if (Static33.aClass212_1 != null && Static33.aClass212_1.status == 1) {
												if (Static33.aClass212_1.result != null) {
													GameShell.method3175(Static175.aClass100_797, Static164.aBoolean194);
												}
												Static164.aBoolean194 = false;
												Static175.aClass100_797 = null;
												Static33.aClass212_1 = null;
											}
											if (loopCycle % 1500 == 0) {
												Static123.method2418();
											}
											return;
										}
										local379 = local374.component;
										if (local379.subId < 0) {
											break;
										}
										local387 = IfType.get(local379.layerId);
									} while (local387 == null || local387.subcomponents == null || local387.subcomponents.length <= local379.subId || local379 != local387.subcomponents[local379.subId]);
									ScriptRunner.executeScript(local374);
								}
							}
							local379 = local374.component;
							if (local379.subId < 0) {
								break;
							}
							local387 = IfType.get(local379.layerId);
						} while (local387 == null || local387.subcomponents == null || local379.subId >= local387.subcomponents.length || local379 != local387.subcomponents[local379.subId]);
						ScriptRunner.executeScript(local374);
					}
				}
				local379 = local374.component;
				if (local379.subId < 0) {
					break;
				}
				local387 = IfType.get(local379.layerId);
			} while (local387 == null || local387.subcomponents == null || local387.subcomponents.length <= local379.subId || local379 != local387.subcomponents[local379.subId]);
			ScriptRunner.executeScript(local374);
		}
	}

	@OriginalMember(owner = "client!client", name = "d", descriptor = "(Z)V")
	private void serviceNetClient() {
		@Pc(3) boolean local3 = js5Net.loop();
		if (!local3) {
			this.js5connect();
		}
	}

	@OriginalMember(owner = "client!client", name = "h", descriptor = "(I)V")
	private void js5connect() {
		if (anInt5226 < js5Net.js5Errors) {
			js5ConnectCooldown = 5 * 50 * (js5Net.js5Errors - 1);

			if (gamePort == loginPort) {
				loginPort = js5Port;
			} else {
				loginPort = gamePort;
			}

			if (js5ConnectCooldown > 3000) {
				js5ConnectCooldown = 3000;
			}

			if (js5Net.js5Errors >= 2 && js5Net.anInt2963 == 6) {
				this.error("js5connect_outofdate");
				state = 1000;
				return;
			}

			if (js5Net.js5Errors >= 4 && js5Net.anInt2963 == -1) {
				this.error("js5crc");
				state = 1000;
				return;
			}

			if (js5Net.js5Errors >= 4 && (state == 0 || state == 5)) {
				if (js5Net.anInt2963 == 7 || js5Net.anInt2963 == 9) {
					this.error("js5connect_full");
				} else if (js5Net.anInt2963 > 0) {
					this.error("js5connect");
				} else {
					this.error("js5io");
				}

				state = 1000;
				return;
			}
		}

		anInt5226 = js5Net.js5Errors;

		if (js5ConnectCooldown > 0) {
			js5ConnectCooldown--;
			return;
		}

		try {
			if (js5ConnectState == 0) {
				js5SocketReq = GameShell.signlink.socketreq(host, loginPort);
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
					@Pc(258) int local258 = js5Stream.read();
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
				js5Net.init(!local296, js5Stream);
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
					if (!ClientKeyboardListener.pollKey()) {
						break label252;
					}
				} while (ClientKeyboardListener.ch != 115 && ClientKeyboardListener.ch != 83);
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
				js5Loader = new Js5Loader(js5Net, Static86.aClass80_3);
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
				total += js5Providers[i].method538() * Static170.anIntArray306[i] / 100;
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
                TitleScreen.method3172(sprites);
                Static81.method1754(sprites);
                loadingStep = 45;
            }
        } else if (loadingStep == 45) {
			Static41.init(lowMem);

			midiPlayer = new MidiPlayer();
			midiPlayer.setChannelDefaultPatch();

			midiPcmPlayer = PcmPlayer.getPlayer(22050, GameShell.signlink, GameShell.canvas, 0);
			midiPcmPlayer.playStream(midiPlayer);

			MidiManager.init(midiPlayer, patches, vorbis, jagFX);

			soundPcmPlayer = PcmPlayer.getPlayer(2048, GameShell.signlink, GameShell.canvas, 1);
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
			setMainState(5);
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
				Pix3D.method1914(local951);
				if (Static113.anInt4609 == 1) {
					Pix3D.method1911(0.9F);
				}
				if (Static113.anInt4609 == 2) {
					Pix3D.method1911(0.8F);
				}
				if (Static113.anInt4609 == 3) {
					Pix3D.method1911(0.7F);
				}
				if (Static113.anInt4609 == 4) {
					Pix3D.method1911(0.6F);
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
			if (binary.requestDownload(TitleScreen.AUTO_EMPTY, Static252.AUTO_HUFFMAN)) {
				@Pc(1060) Huffman local1060 = new Huffman(binary.getFile(TitleScreen.AUTO_EMPTY, Static252.AUTO_HUFFMAN));
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
				WorldMap.method4018(Static173.aClass3_Sub2_Sub1_Sub1Array9, worldmap);
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
				setMainState(1000);
			} else if (!Static61.aBoolean109) {
				this.error("worldlistio_" + local98);
				setMainState(1000);
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
			method4540(false, Static214.anInt5581, -1, -1);
			TitleScreen.loadPos = 100;
			loadingStep = 160;
			TitleScreen.loadString = Text.aClass100_1064;
		} else if (loadingStep == 160) {
			method1596(true);
		}
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(B)V")
	@Override
	protected final void mainloop() {
		if (state == 1000) {
			return;
		}

		loopCycle++;
		if (loopCycle % 1000 == 1) {
			@Pc(24) GregorianCalendar local24 = new GregorianCalendar();
			Static60.anInt1895 = local24.get(11) * 600 + local24.get(12) * 10 + local24.get(13) / 6;
			Static39.aRandom1.setSeed((long) Static60.anInt1895);
		}

		this.serviceNetClient();

		if (js5Loader != null) {
			js5Loader.method179();
		}

		MidiManager.updateFadeOut();
		doAudio();
		ClientKeyboardListener.loop();
		ClientMouseListener.loop();

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
			mapBuildLoop();
		}

		if (state == 10) {
			this.titleScreenLoop();
			accountCreatePoll();
			worldListPoll();
			loginPoll();
		} else if (state == 30) {
			gameLoop();
		} else if (state == 40) {
			loginPoll();

			if (worldHopError != -3) {
				if (worldHopError == 15) {
					reconnectDone();
				} else if (worldHopError != 2) {
					logout();
				}
			}
		}
	}
}
