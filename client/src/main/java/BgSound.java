import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fl")
public final class BgSound extends Linkable {

    @OriginalMember(owner = "client!je", name = "T", descriptor = "Lclient!sc;")
    public static HashTable aClass133_9 = new HashTable(8);
    @OriginalMember(owner = "client!fl", name = "p", descriptor = "I")
	public int anInt2028;

	@OriginalMember(owner = "client!fl", name = "q", descriptor = "I")
	public int anInt2029;

	@OriginalMember(owner = "client!fl", name = "t", descriptor = "I")
	public int anInt2032;

	@OriginalMember(owner = "client!fl", name = "v", descriptor = "Lclient!b;")
	public WaveStream aClass3_Sub3_Sub1_1;

	@OriginalMember(owner = "client!fl", name = "x", descriptor = "I")
	public int anInt2033;

	@OriginalMember(owner = "client!fl", name = "y", descriptor = "Lclient!b;")
	public WaveStream aClass3_Sub3_Sub1_2;

	@OriginalMember(owner = "client!fl", name = "z", descriptor = "I")
	public int anInt2034;

	@OriginalMember(owner = "client!fl", name = "E", descriptor = "Lclient!pb;")
	public LocType multiloc;

	@OriginalMember(owner = "client!fl", name = "F", descriptor = "I")
	public int anInt2037;

	@OriginalMember(owner = "client!fl", name = "I", descriptor = "Lclient!km;")
	public ClientNPC aClass8_Sub4_Sub2_1;

	@OriginalMember(owner = "client!fl", name = "K", descriptor = "I")
	public int anInt2040;

	@OriginalMember(owner = "client!fl", name = "L", descriptor = "I")
	public int anInt2041;

	@OriginalMember(owner = "client!fl", name = "M", descriptor = "Lclient!e;")
	public ClientPlayer aClass8_Sub4_Sub1_1;

	@OriginalMember(owner = "client!fl", name = "N", descriptor = "I")
	public int anInt2042;

	@OriginalMember(owner = "client!fl", name = "O", descriptor = "Z")
	public boolean aBoolean117;

	@OriginalMember(owner = "client!fl", name = "R", descriptor = "I")
	public int anInt2044;

	@OriginalMember(owner = "client!fl", name = "T", descriptor = "[I")
	public int[] anIntArray181;

	@OriginalMember(owner = "client!fl", name = "G", descriptor = "I")
	public int anInt2038 = 0;

	@OriginalMember(owner = "client!je", name = "k", descriptor = "(I)V")
	public static void recalculateMultilocs() {
		@Pc(6) BgSound local6;
		for (local6 = (BgSound) Static3.aClass69_135.head(); local6 != null; local6 = (BgSound) Static3.aClass69_135.next()) {
			if (local6.aBoolean117) {
				local6.method1567();
			}
		}
		for (local6 = (BgSound) Static152.aClass69_87.head(); local6 != null; local6 = (BgSound) Static152.aClass69_87.next()) {
			if (local6.aBoolean117) {
				local6.method1567();
			}
		}
	}

	@OriginalMember(owner = "client!jh", name = "a", descriptor = "(IZLclient!pb;ILclient!km;IILclient!e;)V")
	public static void method2411(@OriginalArg(0) int arg0, @OriginalArg(2) LocType arg1, @OriginalArg(3) int arg2, @OriginalArg(4) ClientNPC arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) ClientPlayer arg6) {
		@Pc(13) BgSound local13 = new BgSound();
		local13.anInt2029 = arg0 * 128;
		local13.anInt2041 = arg4 * 128;
		local13.anInt2033 = arg5;
		if (arg1 != null) {
			local13.anIntArray181 = arg1.anIntArray381;
			local13.anInt2042 = arg1.anInt4402 * 128;
			local13.anInt2040 = arg1.anInt4414;
			local13.multiloc = arg1;
			local13.anInt2044 = arg1.anInt4412;
			local13.anInt2032 = arg1.anInt4419;
			@Pc(57) int local57 = arg1.anInt4397;
			@Pc(60) int local60 = arg1.anInt4403;
			if (arg2 == 1 || arg2 == 3) {
				local57 = arg1.anInt4403;
				local60 = arg1.anInt4397;
			}
			local13.anInt2028 = (local60 + arg0) * 128;
			local13.anInt2037 = (arg4 + local57) * 128;
			if (arg1.anIntArray380 != null) {
				local13.aBoolean117 = true;
				local13.method1567();
			}
			if (local13.anIntArray181 != null) {
				local13.anInt2034 = local13.anInt2032 + (int) (Math.random() * (double) (local13.anInt2040 - local13.anInt2032));
			}
			Static3.aClass69_135.push(local13);
		} else if (arg3 != null) {
			local13.aClass8_Sub4_Sub2_1 = arg3;
			@Pc(138) NPCType local138 = arg3.aClass96_1;
			if (local138.anIntArray357 != null) {
				local13.aBoolean117 = true;
				local138 = local138.method2932();
			}
			if (local138 != null) {
				local13.anInt2028 = (local138.anInt3713 + arg0) * 128;
				local13.anInt2037 = (arg4 + local138.anInt3713) * 128;
				local13.anInt2044 = Static112.method2299(arg3);
				local13.anInt2042 = local138.anInt3746 * 128;
			}
			Static152.aClass69_87.push(local13);
		} else if (arg6 != null) {
			local13.aClass8_Sub4_Sub1_1 = arg6;
			local13.anInt2037 = (arg6.method2693() + arg4) * 128;
			local13.anInt2028 = (arg6.method2693() + arg0) * 128;
			local13.anInt2044 = Static140.method2706(arg6);
			local13.anInt2042 = arg6.anInt1664 * 128;
			Static93.aClass133_7.put(local13, arg6.aClass100_364.method3158());
		}
	}

	@OriginalMember(owner = "client!fl", name = "c", descriptor = "(I)V")
	public final void method1567() {
		@Pc(8) int local8 = this.anInt2044;
		if (this.multiloc != null) {
			@Pc(17) LocType local17 = this.multiloc.getMultiLoc();
			if (local17 == null) {
				this.anInt2044 = -1;
				this.anIntArray181 = null;
				this.anInt2040 = 0;
				this.anInt2042 = 0;
				this.anInt2032 = 0;
			} else {
				this.anInt2040 = local17.anInt4414;
				this.anInt2044 = local17.anInt4412;
				this.anInt2032 = local17.anInt4419;
				this.anInt2042 = local17.anInt4402 * 128;
				this.anIntArray181 = local17.anIntArray381;
			}
		} else if (this.aClass8_Sub4_Sub2_1 != null) {
			@Pc(92) int local92 = Static112.method2299(this.aClass8_Sub4_Sub2_1);
			if (local8 != local92) {
				@Pc(100) NPCType local100 = this.aClass8_Sub4_Sub2_1.aClass96_1;
				this.anInt2044 = local92;
				if (local100.anIntArray357 != null) {
					local100 = local100.method2932();
				}
				if (local100 == null) {
					this.anInt2042 = 0;
				} else {
					this.anInt2042 = local100.anInt3746 * 128;
				}
			}
		} else if (this.aClass8_Sub4_Sub1_1 != null) {
			this.anInt2044 = Static140.method2706(this.aClass8_Sub4_Sub1_1);
			this.anInt2042 = this.aClass8_Sub4_Sub1_1.anInt1664 * 128;
		}
		if (this.anInt2044 != local8 && this.aClass3_Sub3_Sub1_1 != null) {
			Client.soundMixer.method1347(this.aClass3_Sub3_Sub1_1);
			this.aClass3_Sub3_Sub1_1 = null;
		}
	}
}
