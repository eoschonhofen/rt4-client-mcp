import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!eg")
public final class SpotType {

	@OriginalMember(owner = "client!wk", name = "t", descriptor = "Lclient!n;")
	public static final SoftLruCache recentUse = new SoftLruCache(64);

	@OriginalMember(owner = "client!he", name = "cb", descriptor = "Lclient!ve;")
	public static Js5 models;

	@OriginalMember(owner = "client!ke", name = "R", descriptor = "Lclient!ve;")
	public static Js5 spotConfig;

	@OriginalMember(owner = "client!eg", name = "f", descriptor = "[S")
	private short[] aShortArray15;

	@OriginalMember(owner = "client!eg", name = "g", descriptor = "[S")
	private short[] aShortArray16;

	@OriginalMember(owner = "client!eg", name = "h", descriptor = "[S")
	private short[] aShortArray17;

	@OriginalMember(owner = "client!eg", name = "m", descriptor = "I")
	public int id;

	@OriginalMember(owner = "client!eg", name = "o", descriptor = "I")
	private int anInt1753;

	@OriginalMember(owner = "client!eg", name = "s", descriptor = "[S")
	private short[] aShortArray18;

	@OriginalMember(owner = "client!eg", name = "i", descriptor = "Z")
	public boolean aBoolean100 = false;

	@OriginalMember(owner = "client!eg", name = "j", descriptor = "I")
	private int anInt1748 = 0;

	@OriginalMember(owner = "client!eg", name = "k", descriptor = "I")
	private int anInt1749 = 0;

	@OriginalMember(owner = "client!eg", name = "p", descriptor = "I")
	public int anim = -1;

	@OriginalMember(owner = "client!eg", name = "n", descriptor = "I")
	private int anInt1752 = 128;

	@OriginalMember(owner = "client!eg", name = "b", descriptor = "I")
	private int anInt1745 = 128;

	@OriginalMember(owner = "client!eg", name = "q", descriptor = "I")
	private int anInt1755 = 0;

	@OriginalMember(owner = "client!vk", name = "a", descriptor = "(Lclient!ve;Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
		models = arg0;
		spotConfig = arg1;
	}

	@OriginalMember(owner = "client!ck", name = "a", descriptor = "(BI)Lclient!eg;")
	public static SpotType list(@OriginalArg(1) int arg0) {
		@Pc(10) SpotType local10 = (SpotType) recentUse.find((long) arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(26) byte[] local26 = spotConfig.getFile(Static206.method3681(arg0), Static133.method4010(arg0));
		local10 = new SpotType();
		local10.id = arg0;
		if (local26 != null) {
			local10.decode(new Packet(local26));
		}
		recentUse.put(local10, (long) arg0);
		return local10;
	}

	@OriginalMember(owner = "client!eg", name = "a", descriptor = "(Lclient!wa;B)V")
	public final void decode(@OriginalArg(0) Packet arg0) {
		while (true) {
			@Pc(17) int local17 = arg0.g1();
			if (local17 == 0) {
				return;
			}
			this.decode(arg0, local17);
		}
	}

	@OriginalMember(owner = "client!eg", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet arg0, @OriginalArg(1) int arg1) {
		if (arg1 == 1) {
			this.anInt1753 = arg0.g2();
		} else if (arg1 == 2) {
			this.anim = arg0.g2();
		} else if (arg1 == 4) {
			this.anInt1745 = arg0.g2();
		} else if (arg1 == 5) {
			this.anInt1752 = arg0.g2();
		} else if (arg1 == 6) {
			this.anInt1755 = arg0.g2();
		} else if (arg1 == 7) {
			this.anInt1749 = arg0.g1();
		} else if (arg1 == 8) {
			this.anInt1748 = arg0.g1();
		} else if (arg1 == 9) {
			this.aBoolean100 = true;
		} else {
			@Pc(78) int local78;
			@Pc(88) int local88;
			if (arg1 == 40) {
				local78 = arg0.g1();
				this.aShortArray15 = new short[local78];
				this.aShortArray18 = new short[local78];
				for (local88 = 0; local88 < local78; local88++) {
					this.aShortArray15[local88] = (short) arg0.g2();
					this.aShortArray18[local88] = (short) arg0.g2();
				}
			} else if (arg1 == 41) {
				local78 = arg0.g1();
				this.aShortArray16 = new short[local78];
				this.aShortArray17 = new short[local78];
				for (local88 = 0; local88 < local78; local88++) {
					this.aShortArray16[local88] = (short) arg0.g2();
					this.aShortArray17[local88] = (short) arg0.g2();
				}
			}
		}
	}

	@OriginalMember(owner = "client!eg", name = "a", descriptor = "(IBII)Lclient!ak;")
	public final ModelLit method1319(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(13) ModelLit local13 = (ModelLit) Static56.aClass99_9.find((long) this.id);
		if (local13 == null) {
			@Pc(28) ModelUnlit local28 = Static77.method1686(models, this.anInt1753);
			if (local28 == null) {
				return null;
			}
			@Pc(40) int local40;
			if (this.aShortArray15 != null) {
				for (local40 = 0; local40 < this.aShortArray15.length; local40++) {
					local28.method1687(this.aShortArray15[local40], this.aShortArray18[local40]);
				}
			}
			if (this.aShortArray16 != null) {
				for (local40 = 0; local40 < this.aShortArray16.length; local40++) {
					local28.method1669(this.aShortArray16[local40], this.aShortArray17[local40]);
				}
			}
			local13 = local28.method1679(this.anInt1749 + 64, this.anInt1748 + 850, -30, -50, -30);
			Static56.aClass99_9.put(local13, (long) this.id);
		}
		@Pc(118) ModelLit local118;
		if (this.anim == -1 || arg1 == -1) {
			local118 = local13.method4560(true, true, true);
		} else {
			local118 = SeqType.list(this.anim).method4219(arg0, arg2, arg1, local13);
		}
		if (this.anInt1745 != 128 || this.anInt1752 != 128) {
			local118.method4559(this.anInt1745, this.anInt1752, this.anInt1745);
		}
		if (this.anInt1755 != 0) {
			if (this.anInt1755 == 90) {
				local118.method4563();
			}
			if (this.anInt1755 == 180) {
				local118.method4552();
			}
			if (this.anInt1755 == 270) {
				local118.method4578();
			}
		}
		return local118;
	}
}
