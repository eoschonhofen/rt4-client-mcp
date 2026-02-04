import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!be")
public final class IfType {

	@OriginalMember(owner = "client!gn", name = "i", descriptor = "Lclient!n;")
	public static final SoftLruCache fontCache = new SoftLruCache(20);

	@OriginalMember(owner = "client!pf", name = "b", descriptor = "Lclient!n;")
	public static final SoftLruCache spriteCache = new SoftLruCache(200);

	@OriginalMember(owner = "client!sc", name = "m", descriptor = "[Z")
	public static boolean[] open;

	@OriginalMember(owner = "client!qg", name = "ab", descriptor = "Lclient!ve;")
	public static Js5 interfaces;

	@OriginalMember(owner = "client!th", name = "j", descriptor = "[[Lclient!be;")
	public static IfType[][] list;

	@OriginalMember(owner = "client!rc", name = "C", descriptor = "Z")
	public static boolean loadingAsset = false;

	@OriginalMember(owner = "client!bm", name = "f", descriptor = "Lclient!ve;")
	public static Js5 sprites;

	@OriginalMember(owner = "client!nd", name = "v", descriptor = "Lclient!ve;")
	public static Js5 fontMetrics;

	@OriginalMember(owner = "client!qh", name = "g", descriptor = "Lclient!ve;")
	public static Js5 models;

	@OriginalMember(owner = "client!be", name = "b", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray1;

	@OriginalMember(owner = "client!be", name = "d", descriptor = "[Ljava/lang/Object;")
	public Object[] onstattransmit;

	@OriginalMember(owner = "client!be", name = "e", descriptor = "[Ljava/lang/Object;")
	public Object[] onload;

	@OriginalMember(owner = "client!be", name = "g", descriptor = "[Ljava/lang/Object;")
	public Object[] onvarctransmit;

	@OriginalMember(owner = "client!be", name = "k", descriptor = "[Ljava/lang/Object;")
	public Object[] onclick;

	@OriginalMember(owner = "client!be", name = "p", descriptor = "[Ljava/lang/Object;")
	public Object[] onclickrepeat;

	@OriginalMember(owner = "client!be", name = "q", descriptor = "[Lclient!na;")
	public JagString[] aClass100Array18;

	@OriginalMember(owner = "client!be", name = "s", descriptor = "[Lclient!na;")
	public JagString[] iop;

	@OriginalMember(owner = "client!be", name = "t", descriptor = "[Ljava/lang/Object;")
	public Object[] oninvtransmit;

	@OriginalMember(owner = "client!be", name = "u", descriptor = "[I")
	public int[] oninvtransmitlist;

	@OriginalMember(owner = "client!be", name = "v", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray8;

	@OriginalMember(owner = "client!be", name = "x", descriptor = "Z")
	public boolean vFlip;

	@OriginalMember(owner = "client!be", name = "z", descriptor = "[Ljava/lang/Object;")
	public Object[] onhold;

	@OriginalMember(owner = "client!be", name = "E", descriptor = "[Ljava/lang/Object;")
	public Object[] onscrollwheel;

	@OriginalMember(owner = "client!be", name = "G", descriptor = "[I")
	public int[] onvarcstrtransmitlist;

	@OriginalMember(owner = "client!be", name = "I", descriptor = "I")
	public int type;

	@OriginalMember(owner = "client!be", name = "V", descriptor = "[I")
	public int[] invBackground;

	@OriginalMember(owner = "client!be", name = "X", descriptor = "Z")
	public boolean hFlip;

	@OriginalMember(owner = "client!be", name = "Z", descriptor = "I")
	public int model1Id;

	@OriginalMember(owner = "client!be", name = "bb", descriptor = "[Ljava/lang/Object;")
	public Object[] ontargetenter;

	@OriginalMember(owner = "client!be", name = "fb", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray12;

	@OriginalMember(owner = "client!be", name = "gb", descriptor = "[I")
	public int[] anIntArray37;

	@OriginalMember(owner = "client!be", name = "kb", descriptor = "[I")
	public int[] onvarctransmitlist;

	@OriginalMember(owner = "client!be", name = "qb", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray13;

	@OriginalMember(owner = "client!be", name = "tb", descriptor = "[Ljava/lang/Object;")
	public Object[] onkey;

	@OriginalMember(owner = "client!be", name = "ub", descriptor = "[Ljava/lang/Object;")
	public Object[] onvarcstrtransmit;

	@OriginalMember(owner = "client!be", name = "Db", descriptor = "[Ljava/lang/Object;")
	public Object[] ondragcomplete;

	@OriginalMember(owner = "client!be", name = "Fb", descriptor = "[B")
	public byte[] aByteArray7;

	@OriginalMember(owner = "client!be", name = "Jb", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray17;

	@OriginalMember(owner = "client!be", name = "Nb", descriptor = "[Ljava/lang/Object;")
	public Object[] onrelease;

	@OriginalMember(owner = "client!be", name = "Xb", descriptor = "[Ljava/lang/Object;")
	public Object[] onmouseover;

	@OriginalMember(owner = "client!be", name = "Yb", descriptor = "[I")
	public int[] anIntArray39;

	@OriginalMember(owner = "client!be", name = "dc", descriptor = "[I")
	public int[] linkObjNumber;

	@OriginalMember(owner = "client!be", name = "fc", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray20;

	@OriginalMember(owner = "client!be", name = "lc", descriptor = "[Lclient!be;")
	public IfType[] subcomponents;

	@OriginalMember(owner = "client!be", name = "mc", descriptor = "[B")
	public byte[] aByteArray8;

	@OriginalMember(owner = "client!be", name = "rc", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray21;

	@OriginalMember(owner = "client!be", name = "tc", descriptor = "[Ljava/lang/Object;")
	public Object[] ontimer;

	@OriginalMember(owner = "client!be", name = "yc", descriptor = "[I")
	public int[] invBackgroundX;

	@OriginalMember(owner = "client!be", name = "Ac", descriptor = "[I")
	public int[] onstattransmitlist;

	@OriginalMember(owner = "client!be", name = "Bc", descriptor = "[I")
	public int[] scriptOperand;

	@OriginalMember(owner = "client!be", name = "Cc", descriptor = "[Ljava/lang/Object;")
	public Object[] onmouserepeat;

	@OriginalMember(owner = "client!be", name = "Ic", descriptor = "[Ljava/lang/Object;")
	public Object[] onmouseleave;

	@OriginalMember(owner = "client!be", name = "Jc", descriptor = "[Ljava/lang/Object;")
	public Object[] onvartransmit;

	@OriginalMember(owner = "client!be", name = "Nc", descriptor = "[I")
	public int[] onvartransmitlist;

	@OriginalMember(owner = "client!be", name = "Tc", descriptor = "[I")
	public int[] anIntArray45;

	@OriginalMember(owner = "client!be", name = "Xc", descriptor = "[Ljava/lang/Object;")
	public Object[] ondrag;

	@OriginalMember(owner = "client!be", name = "ad", descriptor = "[[I")
	public int[][] scripts;

	@OriginalMember(owner = "client!be", name = "bd", descriptor = "[I")
	public int[] anIntArray46;

	@OriginalMember(owner = "client!be", name = "cd", descriptor = "[I")
	public int[] invBackgroundY;

	@OriginalMember(owner = "client!be", name = "gd", descriptor = "[Ljava/lang/Object;")
	public Object[] ontargetleave;

	@OriginalMember(owner = "client!be", name = "kd", descriptor = "[I")
	public int[] scriptComparator;

	@OriginalMember(owner = "client!be", name = "nd", descriptor = "[I")
	public int[] anIntArray49;

	@OriginalMember(owner = "client!be", name = "qd", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray28;

	@OriginalMember(owner = "client!be", name = "rd", descriptor = "[Ljava/lang/Object;")
	public Object[] onop;

	@OriginalMember(owner = "client!be", name = "sd", descriptor = "[Ljava/lang/Object;")
	public Object[] anObjectArray30;

	@OriginalMember(owner = "client!be", name = "wd", descriptor = "[I")
	public int[] linkObjType;

	@OriginalMember(owner = "client!be", name = "H", descriptor = "Z")
	public boolean tiling = false;

	@OriginalMember(owner = "client!be", name = "f", descriptor = "I")
	public int anInt442 = 1;

	@OriginalMember(owner = "client!be", name = "R", descriptor = "I")
	public int anInt459 = 0;

	@OriginalMember(owner = "client!be", name = "S", descriptor = "I")
	public int hAlign = 0;

	@OriginalMember(owner = "client!be", name = "j", descriptor = "I")
	public int modelZoom = 100;

	@OriginalMember(owner = "client!be", name = "h", descriptor = "B")
	public byte aByte2 = 0;

	@OriginalMember(owner = "client!be", name = "jb", descriptor = "I")
	public int anInt469 = 0;

	@OriginalMember(owner = "client!be", name = "nb", descriptor = "I")
	public int dragdeadzone = 0;

	@OriginalMember(owner = "client!be", name = "W", descriptor = "I")
	public int modelAnim2 = -1;

	@OriginalMember(owner = "client!be", name = "o", descriptor = "S")
	public short aShort10 = 3000;

	@OriginalMember(owner = "client!be", name = "D", descriptor = "I")
	public int modelXAn = 0;

	@OriginalMember(owner = "client!be", name = "Eb", descriptor = "I")
	public int modelYOf = 0;

	@OriginalMember(owner = "client!be", name = "A", descriptor = "Z")
	public boolean orthog = false;

	@OriginalMember(owner = "client!be", name = "zb", descriptor = "Z")
	public boolean fill = false;

	@OriginalMember(owner = "client!be", name = "y", descriptor = "I")
	public int dragdeadtime = 0;

	@OriginalMember(owner = "client!be", name = "hb", descriptor = "I")
	public int anInt468 = 0;

	@OriginalMember(owner = "client!be", name = "xb", descriptor = "I")
	public int graphic = -1;

	@OriginalMember(owner = "client!be", name = "eb", descriptor = "I")
	public int lineHeight = 0;

	@OriginalMember(owner = "client!be", name = "a", descriptor = "Z")
	public boolean hide = false;

	@OriginalMember(owner = "client!be", name = "Zb", descriptor = "I")
	public int lineWidth = 1;

	@OriginalMember(owner = "client!be", name = "Mb", descriptor = "I")
	public int anInt484 = -1;

	@OriginalMember(owner = "client!be", name = "O", descriptor = "I")
	public int subId = -1;

	@OriginalMember(owner = "client!be", name = "J", descriptor = "Z")
	public boolean clickTrigger = false;

	@OriginalMember(owner = "client!be", name = "Sb", descriptor = "Lclient!na;")
	public JagString targetBase = Static176.aClass100_800;

	@OriginalMember(owner = "client!be", name = "i", descriptor = "Z")
	public boolean aBoolean19 = false;

	@OriginalMember(owner = "client!be", name = "yb", descriptor = "I")
	public int vAlign = 0;

	@OriginalMember(owner = "client!be", name = "lb", descriptor = "I")
	public int overLayerId = -1;

	@OriginalMember(owner = "client!be", name = "m", descriptor = "Z")
	public boolean aBoolean20 = false;

	@OriginalMember(owner = "client!be", name = "pc", descriptor = "I")
	public int anInt499 = -1;

	@OriginalMember(owner = "client!be", name = "Qb", descriptor = "B")
	public byte aByte3 = 0;

	@OriginalMember(owner = "client!be", name = "bc", descriptor = "I")
	public int scrollHeight = 0;

	@OriginalMember(owner = "client!be", name = "Y", descriptor = "Z")
	public boolean draggablebehavior = false;

	@OriginalMember(owner = "client!be", name = "ob", descriptor = "Z")
	public boolean shadow = false;

	@OriginalMember(owner = "client!be", name = "cb", descriptor = "I")
	public int drawTime = -1;

	@OriginalMember(owner = "client!be", name = "jc", descriptor = "I")
	public int anInt496 = 1;

	@OriginalMember(owner = "client!be", name = "Cb", descriptor = "I")
	public int colourOver = 0;

	@OriginalMember(owner = "client!be", name = "Hb", descriptor = "Z")
	public boolean v3 = false;

	@OriginalMember(owner = "client!be", name = "gc", descriptor = "Lclient!bf;")
	public ServerActive active = Static45.aClass3_Sub4_2;

	@OriginalMember(owner = "client!be", name = "cc", descriptor = "I")
	public int colour2 = 0;

	@OriginalMember(owner = "client!be", name = "Gb", descriptor = "Lclient!na;")
	public JagString text = Static176.aClass100_800;

	@OriginalMember(owner = "client!be", name = "n", descriptor = "I")
	public int anInt445 = 0;

	@OriginalMember(owner = "client!be", name = "Wb", descriptor = "I")
	public int anInt489 = 0;

	@OriginalMember(owner = "client!be", name = "sb", descriptor = "Z")
	public boolean aBoolean29 = false;

	@OriginalMember(owner = "client!be", name = "U", descriptor = "I")
	private int model2Id = -1;

	@OriginalMember(owner = "client!be", name = "N", descriptor = "I")
	public int layerId = -1;

	@OriginalMember(owner = "client!be", name = "pb", descriptor = "I")
	public int anInt473 = 1;

	@OriginalMember(owner = "client!be", name = "Q", descriptor = "I")
	public int anInt458 = -1;

	@OriginalMember(owner = "client!be", name = "vb", descriptor = "I")
	public int colour2Over = 0;

	@OriginalMember(owner = "client!be", name = "nc", descriptor = "I")
	public int anInt497 = 0;

	@OriginalMember(owner = "client!be", name = "Dc", descriptor = "Lclient!na;")
	public JagString aClass100_88 = Static176.aClass100_800;

	@OriginalMember(owner = "client!be", name = "Lc", descriptor = "I")
	public int varcTransmitNum = 0;

	@OriginalMember(owner = "client!be", name = "w", descriptor = "I")
	public int width = 0;

	@OriginalMember(owner = "client!be", name = "Mc", descriptor = "I")
	public int marginX = 0;

	@OriginalMember(owner = "client!be", name = "Ib", descriptor = "I")
	public int transmitNum = -1;

	@OriginalMember(owner = "client!be", name = "c", descriptor = "Z")
	public boolean alpha = false;

	@OriginalMember(owner = "client!be", name = "F", descriptor = "I")
	public int anInt451 = 0;

	@OriginalMember(owner = "client!be", name = "wb", descriptor = "I")
	public int trans = 0;

	@OriginalMember(owner = "client!be", name = "hc", descriptor = "I")
	public int anInt494 = 0;

	@OriginalMember(owner = "client!be", name = "Ub", descriptor = "Lclient!na;")
	public JagString targetVerb = Static176.aClass100_800;

	@OriginalMember(owner = "client!be", name = "Lb", descriptor = "I")
	public int anInt483 = 0;

	@OriginalMember(owner = "client!be", name = "r", descriptor = "Lclient!na;")
	public JagString text2 = Static176.aClass100_800;

	@OriginalMember(owner = "client!be", name = "Pc", descriptor = "I")
	public int outline = 0;

	@OriginalMember(owner = "client!be", name = "oc", descriptor = "I")
	public int anInt498 = -1;

	@OriginalMember(owner = "client!be", name = "Rb", descriptor = "I")
	public int varcstrTransmitNum = 0;

	@OriginalMember(owner = "client!be", name = "ic", descriptor = "I")
	public int modelXOf = 0;

	@OriginalMember(owner = "client!be", name = "Sc", descriptor = "I")
	public int marginY = 0;

	@OriginalMember(owner = "client!be", name = "Tb", descriptor = "I")
	public int height = 0;

	@OriginalMember(owner = "client!be", name = "Fc", descriptor = "I")
	public int parentId = -1;

	@OriginalMember(owner = "client!be", name = "Yc", descriptor = "I")
	public int graphic2 = -1;

	@OriginalMember(owner = "client!be", name = "zc", descriptor = "B")
	public byte aByte4 = 0;

	@OriginalMember(owner = "client!be", name = "qc", descriptor = "I")
	public int anInt500 = 0;

	@OriginalMember(owner = "client!be", name = "uc", descriptor = "I")
	public int font = -1;

	@OriginalMember(owner = "client!be", name = "Pb", descriptor = "I")
	public int scrollWidth = 0;

	@OriginalMember(owner = "client!be", name = "ec", descriptor = "I")
	public int invTransmit = 0;

	@OriginalMember(owner = "client!be", name = "Vc", descriptor = "S")
	public short aShort11 = 0;

	@OriginalMember(owner = "client!be", name = "ed", descriptor = "I")
	public int rotate = 0;

	@OriginalMember(owner = "client!be", name = "id", descriptor = "I")
	public int modelAnim = -1;

	@OriginalMember(owner = "client!be", name = "Rc", descriptor = "Lclient!na;")
	public JagString buttonText = Text.OK;

	@OriginalMember(owner = "client!be", name = "Gc", descriptor = "I")
	public int modelZAn = 0;

	@OriginalMember(owner = "client!be", name = "vc", descriptor = "I")
	public int anInt503 = 0;

	@OriginalMember(owner = "client!be", name = "Uc", descriptor = "I")
	public int drawCount = -1;

	@OriginalMember(owner = "client!be", name = "K", descriptor = "I")
	public int clientCode = 0;

	@OriginalMember(owner = "client!be", name = "Oc", descriptor = "I")
	public int shadowColour = 0;

	@OriginalMember(owner = "client!be", name = "fd", descriptor = "Lclient!be;")
	public IfType aClass13_5 = null;

	@OriginalMember(owner = "client!be", name = "od", descriptor = "I")
	public int statTransmit = 0;

	@OriginalMember(owner = "client!be", name = "ab", descriptor = "I")
	public int model1Type = 1;

	@OriginalMember(owner = "client!be", name = "md", descriptor = "Z")
	public boolean aBoolean34 = false;

	@OriginalMember(owner = "client!be", name = "hd", descriptor = "B")
	public byte aByte5 = 0;

	@OriginalMember(owner = "client!be", name = "Wc", descriptor = "I")
	private int anInt518 = 1;

	@OriginalMember(owner = "client!be", name = "pd", descriptor = "I")
	public int anInt526 = 0;

	@OriginalMember(owner = "client!be", name = "ld", descriptor = "I")
	public int modelYAn = 0;

	@OriginalMember(owner = "client!be", name = "T", descriptor = "Z")
	public boolean aBoolean25 = false;

	@OriginalMember(owner = "client!be", name = "vd", descriptor = "I")
	public int dataX = 0;

	@OriginalMember(owner = "client!be", name = "jd", descriptor = "I")
	public int anInt523 = 0;

	@OriginalMember(owner = "client!be", name = "l", descriptor = "I")
	public int dataY = 0;

	@OriginalMember(owner = "client!be", name = "Bb", descriptor = "Z")
	public boolean aBoolean31 = true;

	@OriginalMember(owner = "client!be", name = "Kc", descriptor = "I")
	public int anInt510 = 0;

	@OriginalMember(owner = "client!be", name = "mb", descriptor = "I")
	public int varTransmitNum = 0;

	@OriginalMember(owner = "client!be", name = "rb", descriptor = "I")
	public int colour = 0;

	@OriginalMember(owner = "client!be", name = "xd", descriptor = "I")
	public int buttonType = 0;

	@OriginalMember(owner = "client!tm", name = "b", descriptor = "(II)Z")
	public static boolean openInterface(@OriginalArg(0) int arg0) {
		if (open[arg0]) {
			return true;
		} else if (interfaces.requestGroupDownload(arg0)) {
			@Pc(25) int local25 = interfaces.getFileIdLimit(arg0);
			if (local25 == 0) {
				open[arg0] = true;
				return true;
			}
			if (list[arg0] == null) {
				list[arg0] = new IfType[local25];
			}
			for (@Pc(46) int local46 = 0; local46 < local25; local46++) {
				if (list[arg0][local46] == null) {
					@Pc(62) byte[] local62 = interfaces.getFile(arg0, local46);
					if (local62 != null) {
						@Pc(74) IfType local74 = list[arg0][local46] = new IfType();
						local74.parentId = local46 + (arg0 << 16);
						if (local62[0] == -1) {
							local74.decode3(new Packet(local62));
						} else {
							local74.decode(new Packet(local62));
						}
					}
				}
			}
			open[arg0] = true;
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(ZLclient!ve;Lclient!ve;Lclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 fontMetrics, @OriginalArg(2) Js5 sprites, @OriginalArg(3) Js5 interfaces, @OriginalArg(4) Js5 models) {
		IfType.sprites = sprites;
		IfType.fontMetrics = fontMetrics;
		IfType.interfaces = interfaces;
		IfType.models = models;

		list = new IfType[IfType.interfaces.getGroupCount()][];
		open = new boolean[IfType.interfaces.getGroupCount()];
	}

    @OriginalMember(owner = "client!af", name = "a", descriptor = "(BI)Lclient!be;")
    public static IfType get(@OriginalArg(1) int arg0) {
        try {
            @Pc(7) int local7 = arg0 >> 16;
            @Pc(18) int local18 = arg0 & 0xFFFF;
            if (list.length <= local7 || local7 < 0) {
                // components.length <= parent || parent < 0
                return null;
            }
            if (list[local7] == null || list[local7][local18] == null) {
                @Pc(33) boolean local33 = openInterface(local7);
                if (!local33) {
                    return null;
                }
            }
            if (list[local7].length <= local18) {
                // components[parent].length <= child
                return null;
            }
            return list[local7][local18];
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

	@OriginalMember(owner = "client!lh", name = "d", descriptor = "(B)V")
	public static void method2764() {
		spriteCache.method3104();
		Static124.aClass99_17.method3104();
		fontCache.method3104();
	}

	@OriginalMember(owner = "client!da", name = "h", descriptor = "(B)V")
	public static void method1019() {
		spriteCache.clear();
		Static124.aClass99_17.clear();
		fontCache.clear();
	}

	@OriginalMember(owner = "client!eb", name = "d", descriptor = "(I)V")
	public static void method1287() {
		list = new IfType[interfaces.getGroupCount()][];
		open = new boolean[interfaces.getGroupCount()];
	}

	@OriginalMember(owner = "client!ec", name = "a", descriptor = "(II)V")
	public static void method1289() {
		spriteCache.method3102(50);
		Static124.aClass99_17.method3102(50);
		fontCache.method3102(50);
	}

	@OriginalMember(owner = "client!ig", name = "a", descriptor = "(BI)V")
	public static void closeInterface(@OriginalArg(1) int arg0) {
		if (arg0 == -1 || !open[arg0]) {
			return;
		}
		interfaces.method4490(arg0);
		if (list[arg0] == null) {
			return;
		}
		@Pc(27) boolean local27 = true;
		for (@Pc(29) int local29 = 0; local29 < list[arg0].length; local29++) {
			if (list[arg0][local29] != null) {
				if (list[arg0][local29].type == 2) {
					local27 = false;
				} else {
					list[arg0][local29] = null;
				}
			}
		}
		if (local27) {
			list[arg0] = null;
		}
		open[arg0] = false;
	}

	@OriginalMember(owner = "client!qf", name = "a", descriptor = "(BII)Lclient!be;")
	public static IfType method1418(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(7) IfType local7 = get(arg0);
		if (arg1 == -1) {
			return local7;
		} else if (local7 == null || local7.subcomponents == null || local7.subcomponents.length <= arg1) {
			return null;
		} else {
			return local7.subcomponents[arg1];
		}
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(IIB)V")
	public final void method477(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (this.anIntArray39 == null || this.anIntArray39.length <= arg0) {
			@Pc(18) int[] local18 = new int[arg0 + 1];
			if (this.anIntArray39 != null) {
				@Pc(24) int local24;
				for (local24 = 0; local24 < this.anIntArray39.length; local24++) {
					local18[local24] = this.anIntArray39[local24];
				}
				for (local24 = this.anIntArray39.length; local24 < arg0; local24++) {
					local18[local24] = -1;
				}
			}
			this.anIntArray39 = local18;
		}
		this.anIntArray39[arg0] = arg1;
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(I)Z")
	public final boolean method478() {
		if (this.anIntArray37 != null) {
			return true;
		}
		@Pc(18) SoftwarePix8 local18 = Static164.method3119(this.graphic, sprites);
		if (local18 == null) {
			return false;
		}
		local18.method1396();
		this.anIntArray37 = new int[local18.anInt4278];
		this.anIntArray45 = new int[local18.anInt4278];
		for (@Pc(37) int local37 = 0; local37 < local18.anInt4278; local37++) {
			@Pc(47) int local47 = 0;
			@Pc(50) int local50 = local18.anInt4270;
			@Pc(52) int local52;
			for (local52 = 0; local52 < local18.anInt4270; local52++) {
				if (local18.aByteArray18[local18.anInt4270 * local37 + local52] != 0) {
					local47 = local52;
					break;
				}
			}
			for (local52 = local47; local52 < local18.anInt4270; local52++) {
				if (local18.aByteArray18[local37 * local18.anInt4270 + local52] == 0) {
					local50 = local52;
					break;
				}
			}
			this.anIntArray37[local37] = local47;
			this.anIntArray45[local37] = local50 - local47;
		}
		return true;
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(BLclient!na;I)V")
	public final void method480(@OriginalArg(1) JagString arg0, @OriginalArg(2) int arg1) {
		if (this.aClass100Array18 == null || this.aClass100Array18.length <= arg1) {
			@Pc(23) JagString[] local23 = new JagString[arg1 + 1];
			if (this.aClass100Array18 != null) {
				for (@Pc(30) int local30 = 0; local30 < this.aClass100Array18.length; local30++) {
					local23[local30] = this.aClass100Array18[local30];
				}
			}
			this.aClass100Array18 = local23;
		}
		this.aClass100Array18[arg1] = arg0;
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet arg0) {
		this.v3 = false;
		this.type = arg0.g1();
		this.buttonType = arg0.g1();
		this.clientCode = arg0.g2();
		this.dataX = arg0.g2b();
		this.dataY = arg0.g2b();
		this.width = arg0.g2();
		this.height = arg0.g2();
		this.aByte5 = 0;
		this.aByte3 = 0;
		this.aByte4 = 0;
		this.aByte2 = 0;
		this.trans = arg0.g1();
		this.layerId = arg0.g2();
		if (this.layerId == 65535) {
			this.layerId = -1;
		} else {
			this.layerId += this.parentId & 0xFFFF0000;
		}
		this.overLayerId = arg0.g2();
		if (this.overLayerId == 65535) {
			this.overLayerId = -1;
		}
		@Pc(109) int local109 = arg0.g1();
		@Pc(125) int local125;
		if (local109 > 0) {
			this.scriptComparator = new int[local109];
			this.scriptOperand = new int[local109];
			for (local125 = 0; local125 < local109; local125++) {
				this.scriptOperand[local125] = arg0.g1();
				this.scriptComparator[local125] = arg0.g2();
			}
		}
		local125 = arg0.g1();
		@Pc(164) int local164;
		@Pc(175) int local175;
		@Pc(183) int local183;
		if (local125 > 0) {
			this.scripts = new int[local125][];
			for (local164 = 0; local164 < local125; local164++) {
				local175 = arg0.g2();
				this.scripts[local164] = new int[local175];
				for (local183 = 0; local183 < local175; local183++) {
					this.scripts[local164][local183] = arg0.g2();
					if (this.scripts[local164][local183] == 65535) {
						this.scripts[local164][local183] = -1;
					}
				}
			}
		}
		if (this.type == 0) {
			this.scrollHeight = arg0.g2();
			this.hide = arg0.g1() == 1;
		}
		if (this.type == 1) {
			arg0.g2();
			arg0.g1();
		}
		local164 = 0;
		if (this.type == 2) {
			this.aByte3 = 3;
			this.linkObjType = new int[this.width * this.height];
			this.linkObjNumber = new int[this.height * this.width];
			this.aByte5 = 3;
			local175 = arg0.g1();
			local183 = arg0.g1();
			if (local175 == 1) {
				local164 = 268435456;
			}
			@Pc(312) int local312 = arg0.g1();
			if (local183 == 1) {
				local164 |= 0x40000000;
			}
			if (local312 == 1) {
				local164 |= Integer.MIN_VALUE;
			}
			@Pc(333) int local333 = arg0.g1();
			if (local333 == 1) {
				local164 |= 0x20000000;
			}
			this.marginX = arg0.g1();
			this.marginY = arg0.g1();
			this.invBackgroundY = new int[20];
			this.invBackgroundX = new int[20];
			this.invBackground = new int[20];
			@Pc(364) int local364;
			for (local364 = 0; local364 < 20; local364++) {
				@Pc(371) int local371 = arg0.g1();
				if (local371 == 1) {
					this.invBackgroundX[local364] = arg0.g2b();
					this.invBackgroundY[local364] = arg0.g2b();
					this.invBackground[local364] = arg0.g4();
				} else {
					this.invBackground[local364] = -1;
				}
			}
			this.iop = new JagString[5];
			for (local364 = 0; local364 < 5; local364++) {
				@Pc(418) JagString local418 = arg0.gjstr();
				if (local418.length() > 0) {
					this.iop[local364] = local418;
					local164 |= 0x1 << local364 + 23;
				}
			}
		}
		if (this.type == 3) {
			this.fill = arg0.g1() == 1;
		}
		if (this.type == 4 || this.type == 1) {
			this.hAlign = arg0.g1();
			this.vAlign = arg0.g1();
			this.lineHeight = arg0.g1();
			this.font = arg0.g2();
			if (this.font == 65535) {
				this.font = -1;
			}
			this.shadow = arg0.g1() == 1;
		}
		if (this.type == 4) {
			this.text = arg0.gjstr();
			this.text2 = arg0.gjstr();
		}
		if (this.type == 1 || this.type == 3 || this.type == 4) {
			this.colour = arg0.g4();
		}
		if (this.type == 3 || this.type == 4) {
			this.colour2 = arg0.g4();
			this.colourOver = arg0.g4();
			this.colour2Over = arg0.g4();
		}
		if (this.type == 5) {
			this.graphic = arg0.g4();
			this.graphic2 = arg0.g4();
		}
		if (this.type == 6) {
			this.model1Type = 1;
			this.model1Id = arg0.g2();
			this.anInt518 = 1;
			if (this.model1Id == 65535) {
				this.model1Id = -1;
			}
			this.model2Id = arg0.g2();
			if (this.model2Id == 65535) {
				this.model2Id = -1;
			}
			this.modelAnim = arg0.g2();
			if (this.modelAnim == 65535) {
				this.modelAnim = -1;
			}
			this.modelAnim2 = arg0.g2();
			if (this.modelAnim2 == 65535) {
				this.modelAnim2 = -1;
			}
			this.modelZoom = arg0.g2();
			this.modelXAn = arg0.g2();
			this.modelYAn = arg0.g2();
		}
		if (this.type == 7) {
			this.aByte3 = 3;
			this.aByte5 = 3;
			this.linkObjType = new int[this.height * this.width];
			this.linkObjNumber = new int[this.width * this.height];
			this.hAlign = arg0.g1();
			this.font = arg0.g2();
			if (this.font == 65535) {
				this.font = -1;
			}
			this.shadow = arg0.g1() == 1;
			this.colour = arg0.g4();
			this.marginX = arg0.g2b();
			this.marginY = arg0.g2b();
			local175 = arg0.g1();
			if (local175 == 1) {
				local164 |= 0x40000000;
			}
			this.iop = new JagString[5];
			for (local183 = 0; local183 < 5; local183++) {
				@Pc(756) JagString local756 = arg0.gjstr();
				if (local756.length() > 0) {
					this.iop[local183] = local756;
					local164 |= 0x1 << local183 + 23;
				}
			}
		}
		if (this.type == 8) {
			this.text = arg0.gjstr();
		}
		if (this.buttonType == 2 || this.type == 2) {
			this.targetVerb = arg0.gjstr();
			this.targetBase = arg0.gjstr();
			local175 = arg0.g2() & 0x3F;
			local164 |= local175 << 11;
		}
		if (this.buttonType == 1 || this.buttonType == 4 || this.buttonType == 5 || this.buttonType == 6) {
			this.buttonText = arg0.gjstr();
			if (this.buttonText.length() == 0) {
				if (this.buttonType == 1) {
					this.buttonText = Text.OK;
				}
				if (this.buttonType == 4) {
					this.buttonText = Text.SELECT;
				}
				if (this.buttonType == 5) {
					this.buttonText = Text.SELECT;
				}
				if (this.buttonType == 6) {
					this.buttonText = Text.CONTINUE;
				}
			}
		}
		if (this.buttonType == 1 || this.buttonType == 4 || this.buttonType == 5) {
			local164 |= 0x400000;
		}
		if (this.buttonType == 6) {
			local164 |= 0x1;
		}
		this.active = new ServerActive(local164, -1);
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(ZI)Lclient!qf;")
	public final AbstractPix32 method482(@OriginalArg(1) int arg0) {
		loadingAsset = false;
		if (arg0 < 0 || arg0 >= this.invBackground.length) {
			return null;
		}
		@Pc(29) int local29 = this.invBackground[arg0];
		if (local29 == -1) {
			return null;
		}
		@Pc(43) AbstractPix32 local43 = (AbstractPix32) spriteCache.find((long) local29);
		if (local43 != null) {
			return local43;
		}
		local43 = Static150.method2800(local29, sprites);
		if (local43 == null) {
			loadingAsset = true;
		} else {
			spriteCache.put(local43, (long) local29);
		}
		return local43;
	}

	@OriginalMember(owner = "client!be", name = "b", descriptor = "(ILclient!wa;)[Ljava/lang/Object;")
	private Object[] decodeHook(@OriginalArg(1) Packet arg0) {
		@Pc(11) int local11 = arg0.g1();
		if (local11 == 0) {
			return null;
		}
		@Pc(26) Object[] local26 = new Object[local11];
		for (@Pc(28) int local28 = 0; local28 < local11; local28++) {
			@Pc(35) int local35 = arg0.g1();
			if (local35 == 0) {
				local26[local28] = Integer.valueOf(arg0.g4());
			} else if (local35 == 1) {
				local26[local28] = arg0.gjstr();
			}
		}
		this.aBoolean25 = true;
		return local26;
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(Lclient!wa;Z)[I")
	private int[] decodeTransmitList(@OriginalArg(0) Packet arg0) {
		@Pc(9) int local9 = arg0.g1();
		if (local9 == 0) {
			return null;
		}
		@Pc(19) int[] local19 = new int[local9];
		for (@Pc(26) int local26 = 0; local26 < local9; local26++) {
			local19[local26] = arg0.g4();
		}
		return local19;
	}

	@OriginalMember(owner = "client!be", name = "b", descriptor = "(III)V")
	public final void method487(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(8) int local8 = this.linkObjNumber[arg1];
		this.linkObjNumber[arg1] = this.linkObjNumber[arg0];
		this.linkObjNumber[arg0] = local8;
		@Pc(34) int local34 = this.linkObjType[arg1];
		this.linkObjType[arg1] = this.linkObjType[arg0];
		this.linkObjType[arg0] = local34;
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(ILclient!tk;IIIZLclient!hh;)Lclient!ak;")
	public final ModelLit method488(@OriginalArg(0) int arg0, @OriginalArg(1) SeqType arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) PlayerModel arg5) {
		loadingAsset = false;
		@Pc(10) int local10;
		@Pc(13) int local13;
		if (arg4) {
			local10 = this.anInt518;
			local13 = this.model2Id;
		} else {
			local13 = this.model1Id;
			local10 = this.model1Type;
		}
		if (local10 == 0) {
			return null;
		} else if (local10 == 1 && local13 == -1) {
			return null;
		} else {
			@Pc(61) ModelLit local61;
			if (local10 == 1) {
				local61 = (ModelLit) Static124.aClass99_17.find((long) ((local10 << 16) + local13));
				if (local61 == null) {
					@Pc(69) ModelUnlit local69 = ModelUnlit.method1686(models, local13);
					if (local69 == null) {
						loadingAsset = true;
						return null;
					}
					local61 = local69.method1679(64, 768, -50, -10, -50);
					Static124.aClass99_17.put(local61, (long) (local13 + (local10 << 16)));
				}
				if (arg1 != null) {
					local61 = arg1.method4215(local61, arg0, arg3, arg2);
				}
				return local61;
			} else if (local10 == 2) {
				local61 = NPCType.list(local13).method2943(arg1, arg3, arg0, arg2);
				if (local61 == null) {
					loadingAsset = true;
					return null;
				} else {
					return local61;
				}
			} else if (local10 == 3) {
				if (arg5 == null) {
					return null;
				}
				local61 = arg5.method1956(arg3, arg1, arg2, arg0);
				if (local61 == null) {
					loadingAsset = true;
					return null;
				} else {
					return local61;
				}
			} else if (local10 == 4) {
				@Pc(164) ObjType local164 = ObjType.list(local13);
				@Pc(173) ModelLit local173 = local164.getModelLit(arg0, arg3, arg1, 10, arg2);
				if (local173 == null) {
					loadingAsset = true;
					return null;
				} else {
					return local173;
				}
			} else if (local10 == 6) {
				local61 = NPCType.list(local13).method2937(null, 0, 0, arg0, arg3, arg2, null, 0, arg1);
				if (local61 == null) {
					loadingAsset = true;
					return null;
				} else {
					return local61;
				}
			} else if (local10 != 7) {
				return null;
			} else if (arg5 == null) {
				return null;
			} else {
				@Pc(227) int local227 = this.model1Id >>> 16;
				@Pc(232) int local232 = this.model1Id & 0xFFFF;
				@Pc(235) int local235 = this.anInt498;
				@Pc(246) ModelLit local246 = arg5.method1946(arg0, local235, local227, arg3, arg1, arg2, local232);
				if (local246 == null) {
					loadingAsset = true;
					return null;
				} else {
					return local246;
				}
			}
		}
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(BZ)Lclient!qf;")
	public final AbstractPix32 getGraphic(@OriginalArg(1) boolean arg0) {
		loadingAsset = false;
		@Pc(12) int local12;
		if (arg0) {
			local12 = this.graphic2;
		} else {
			local12 = this.graphic;
		}
		if (local12 == -1) {
			return null;
		}
		@Pc(66) long local66 = ((this.vFlip ? 1L : 0L) << 38) + ((this.alpha ? 1L : 0L) << 35) + (long) local12 + ((long) this.outline << 36) + ((this.hFlip ? 1L : 0L) << 39) + ((long) this.shadowColour << 40);
		@Pc(72) AbstractPix32 local72 = (AbstractPix32) spriteCache.find(local66);
		if (local72 != null) {
			return local72;
		}

		@Pc(85) Pix32 local85;
		if (this.alpha) {
			local85 = Static80.depack(sprites, local12);
		} else {
			local85 = Static78.depack(0, sprites, local12);
		}
		if (local85 == null) {
			loadingAsset = true;
			return null;
		}

		if (this.vFlip) {
			local85.vflip();
		}
		if (this.hFlip) {
			local85.hflip();
		}
		if (this.outline > 0) {
			local85.untrim(this.outline);
		}
		if (this.outline >= 1) {
			local85.addOutline(1);
		}
		if (this.outline >= 2) {
			local85.addOutline(16777215);
		}
		if (this.shadowColour != 0) {
			local85.addShadow(this.shadowColour);
		}

		if (!GameShell.glRenderer) {
			local72 = local85;
		} else if (local85 instanceof SoftwareAlphaPix32) {
			local72 = new GlAlphaPix32(local85);
		} else {
			local72 = new GlPix32(local85);
		}

		spriteCache.put(local72, local66);
		return local72;
	}

	@OriginalMember(owner = "client!be", name = "c", descriptor = "(ILclient!wa;)V")
	public final void decode3(@OriginalArg(1) Packet buf) {
		this.v3 = true;
		buf.pos++;

		this.type = buf.g1();
		if ((this.type & 0x80) != 0) {
			this.type &= 0x7F;
			buf.gjstr();
		}

		this.clientCode = buf.g2();
		this.dataX = buf.g2b();
		this.dataY = buf.g2b();
		this.width = buf.g2();
		this.height = buf.g2();
		this.aByte5 = buf.g1b();
		this.aByte3 = buf.g1b();
		this.aByte4 = buf.g1b();
		this.aByte2 = buf.g1b();

		this.layerId = buf.g2();
		if (this.layerId == 65535) {
			this.layerId = -1;
		} else {
			this.layerId = (this.parentId & 0xFFFF0000) + this.layerId;
		}

		this.hide = buf.g1() == 1;

		if (this.type == 0) {
			this.scrollWidth = buf.g2();
			this.scrollHeight = buf.g2();
			this.aBoolean29 = buf.g1() == 1;
		}

		@Pc(175) int local175;
		if (this.type == 5) {
			this.graphic = buf.g4();
			this.rotate = buf.g2();
			local175 = buf.g1();
			this.alpha = (local175 & 0x2) != 0;
			this.tiling = (local175 & 0x1) != 0;
			this.trans = buf.g1();
			this.outline = buf.g1();
			this.shadowColour = buf.g4();
			this.vFlip = buf.g1() == 1;
			this.hFlip = buf.g1() == 1;
		}

		if (this.type == 6) {
			this.model1Type = 1;
			this.model1Id = buf.g2();
			if (this.model1Id == 65535) {
				this.model1Id = -1;
			}

			this.modelXOf = buf.g2b();
			this.modelYOf = buf.g2b();
			this.modelXAn = buf.g2();
			this.modelYAn = buf.g2();
			this.modelZAn = buf.g2();
			this.modelZoom = buf.g2();

			this.modelAnim = buf.g2();
			if (this.modelAnim == 65535) {
				this.modelAnim = -1;
			}

			this.orthog = buf.g1() == 1;
			this.aShort11 = (short) buf.g2();
			this.aShort10 = (short) buf.g2();
			this.aBoolean34 = buf.g1() == 1;

			if (this.aByte5 != 0) {
				this.anInt451 = buf.g2();
			}

			if (this.aByte3 != 0) {
				this.anInt526 = buf.g2();
			}
		}

		if (this.type == 4) {
			this.font = buf.g2();
			if (this.font == 65535) {
				this.font = -1;
			}

			this.text = buf.gjstr();
			this.lineHeight = buf.g1();
			this.hAlign = buf.g1();
			this.vAlign = buf.g1();
			this.shadow = buf.g1() == 1;
			this.colour = buf.g4();
		}

		if (this.type == 3) {
			this.colour = buf.g4();
			this.fill = buf.g1() == 1;
			this.trans = buf.g1();
		}

		if (this.type == 9) {
			this.lineWidth = buf.g1();
			this.colour = buf.g4();
			this.aBoolean20 = buf.g1() == 1;
		}

		local175 = buf.g3();
		@Pc(471) int local471 = buf.g1();
		@Pc(497) int local497;
		if (local471 != 0) {
			this.anIntArray46 = new int[10];
			this.aByteArray8 = new byte[10];
			this.aByteArray7 = new byte[10];
			while (local471 != 0) {
				local497 = (local471 >> 4) - 1;
				local471 = buf.g1() | local471 << 8;
				local471 &= 0xFFF;
				if (local471 == 4095) {
					this.anIntArray46[local497] = -1;
				} else {
					this.anIntArray46[local497] = local471;
				}
				this.aByteArray8[local497] = buf.g1b();
				this.aByteArray7[local497] = buf.g1b();
				local471 = buf.g1();
			}
		}

		this.aClass100_88 = buf.gjstr();
		local497 = buf.g1();
		@Pc(557) int local557 = local497 & 0xF;
		@Pc(567) int local567;
		if (local557 > 0) {
			this.aClass100Array18 = new JagString[local557];
			for (local567 = 0; local567 < local557; local567++) {
				this.aClass100Array18[local567] = buf.gjstr();
			}
		}
		@Pc(584) int local584 = local497 >> 4;
		if (local584 > 0) {
			local567 = buf.g1();
			this.anIntArray39 = new int[local567 + 1];
			for (@Pc(599) int local599 = 0; local599 < this.anIntArray39.length; local599++) {
				this.anIntArray39[local599] = -1;
			}
			this.anIntArray39[local567] = buf.g2();
		}
		if (local584 > 1) {
			local567 = buf.g1();
			this.anIntArray39[local567] = buf.g2();
		}

		this.dragdeadzone = buf.g1();
		this.dragdeadtime = buf.g1();
		this.draggablebehavior = buf.g1() == 1;

		local567 = -1;

		this.targetVerb = buf.gjstr();

		if (Static199.method3594(local175) != 0) {
			local567 = buf.g2();
			this.anInt499 = buf.g2();
			if (local567 == 65535) {
				local567 = -1;
			}
			if (this.anInt499 == 65535) {
				this.anInt499 = -1;
			}
			this.anInt484 = buf.g2();
			if (this.anInt484 == 65535) {
				this.anInt484 = -1;
			}
		}

		this.active = new ServerActive(local175, local567);
		this.onload = this.decodeHook(buf);
		this.onmouseover = this.decodeHook(buf);
		this.onmouseleave = this.decodeHook(buf);
		this.ontargetleave = this.decodeHook(buf);
		this.ontargetenter = this.decodeHook(buf);
		this.onvartransmit = this.decodeHook(buf);
		this.oninvtransmit = this.decodeHook(buf);
		this.onstattransmit = this.decodeHook(buf);
		this.ontimer = this.decodeHook(buf);
		this.onop = this.decodeHook(buf);
		this.onmouserepeat = this.decodeHook(buf);
		this.onclick = this.decodeHook(buf);
		this.onclickrepeat = this.decodeHook(buf);
		this.onrelease = this.decodeHook(buf);
		this.onhold = this.decodeHook(buf);
		this.ondrag = this.decodeHook(buf);
		this.ondragcomplete = this.decodeHook(buf);
		this.onscrollwheel = this.decodeHook(buf);
		this.onvarctransmit = this.decodeHook(buf);
		this.onvarcstrtransmit = this.decodeHook(buf);
		this.onvartransmitlist = this.decodeTransmitList(buf);
		this.oninvtransmitlist = this.decodeTransmitList(buf);
		this.onstattransmitlist = this.decodeTransmitList(buf);
		this.onvarctransmitlist = this.decodeTransmitList(buf);
		this.onvarcstrtransmitlist = this.decodeTransmitList(buf);
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "([Lclient!ok;I)Lclient!rk;")
	public final PixFontGeneric getFont(@OriginalArg(0) Pix8[] arg0) {
		loadingAsset = false;

		if (this.font == -1) {
			return null;
		}

		@Pc(21) PixFontGeneric local21 = (PixFontGeneric) fontCache.find((long) this.font);
		if (local21 != null) {
			return local21;
		}

		local21 = Static127.method2462(this.font, sprites, fontMetrics);
		if (local21 == null) {
			loadingAsset = true;
		} else {
			local21.method2873(arg0, null);
			fontCache.put(local21, (long) this.font);
		}
		return local21;
	}
}
