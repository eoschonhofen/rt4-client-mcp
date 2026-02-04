import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fl")
public final class BgSound extends Linkable {

	@OriginalMember(owner = "client!he", name = "ab", descriptor = "Lclient!sc;")
	public static final HashTable aClass133_7 = new HashTable(16);
	@OriginalMember(owner = "client!ab", name = "n", descriptor = "Lclient!ih;")
	public static final LinkList soundlist = new LinkList();
	@OriginalMember(owner = "client!ma", name = "x", descriptor = "Lclient!ih;")
	public static final LinkList aClass69_87 = new LinkList();
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
		for (local6 = (BgSound) soundlist.head(); local6 != null; local6 = (BgSound) soundlist.next()) {
			if (local6.aBoolean117) {
				local6.recalcSound();
			}
		}
		for (local6 = (BgSound) aClass69_87.head(); local6 != null; local6 = (BgSound) aClass69_87.next()) {
			if (local6.aBoolean117) {
				local6.recalcSound();
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
				local13.recalcSound();
			}
			if (local13.anIntArray181 != null) {
				local13.anInt2034 = local13.anInt2032 + (int) (Math.random() * (double) (local13.anInt2040 - local13.anInt2032));
			}
			soundlist.push(local13);
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
			aClass69_87.push(local13);
		} else if (arg6 != null) {
			local13.aClass8_Sub4_Sub1_1 = arg6;
			local13.anInt2037 = (arg6.method2693() + arg4) * 128;
			local13.anInt2028 = (arg6.method2693() + arg0) * 128;
			local13.anInt2044 = Static140.method2706(arg6);
			local13.anInt2042 = arg6.anInt1664 * 128;
			aClass133_7.put(local13, arg6.name.method3158());
		}
	}

    @OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!km;Z)V")
    public static void method1877(@OriginalArg(0) ClientNPC arg0) {
        for (@Pc(13) BgSound local13 = (BgSound) aClass69_87.head(); local13 != null; local13 = (BgSound) aClass69_87.next()) {
            if (arg0 == local13.aClass8_Sub4_Sub2_1) {
                if (local13.aClass3_Sub3_Sub1_1 != null) {
                    Client.soundMixer.method1347(local13.aClass3_Sub3_Sub1_1);
                    local13.aClass3_Sub3_Sub1_1 = null;
                }
                local13.unlink();
                return;
            }
        }
    }

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(IIIII)V")
	public static void method2281(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(6) BgSound local6;
		for (local6 = (BgSound) soundlist.head(); local6 != null; local6 = (BgSound) soundlist.next()) {
			Static150.method2804(arg1, local6, arg3, arg0, arg2);
		}
		@Pc(37) byte local37;
		@Pc(42) BasType local42;
		@Pc(141) int local141;
		for (local6 = (BgSound) aClass69_87.head(); local6 != null; local6 = (BgSound) aClass69_87.next()) {
			local37 = 1;
			local42 = local6.aClass8_Sub4_Sub2_1.method2681();
			if (local42.anInt1037 == local6.aClass8_Sub4_Sub2_1.anInt3366) {
				local37 = 0;
			} else if (local42.anInt1058 == local6.aClass8_Sub4_Sub2_1.anInt3366 || local42.anInt1054 == local6.aClass8_Sub4_Sub2_1.anInt3366 || local42.anInt1045 == local6.aClass8_Sub4_Sub2_1.anInt3366 || local42.anInt1043 == local6.aClass8_Sub4_Sub2_1.anInt3366) {
				local37 = 2;
			} else if (local42.anInt1062 == local6.aClass8_Sub4_Sub2_1.anInt3366 || local42.anInt1042 == local6.aClass8_Sub4_Sub2_1.anInt3366 || local6.aClass8_Sub4_Sub2_1.anInt3366 == local42.anInt1048 || local42.anInt1066 == local6.aClass8_Sub4_Sub2_1.anInt3366) {
				local37 = 3;
			}
			if (local6.anInt2038 != local37) {
				local141 = Static112.method2299(local6.aClass8_Sub4_Sub2_1);
				if (local141 != local6.anInt2044) {
					if (local6.aClass3_Sub3_Sub1_1 != null) {
						Client.soundMixer.method1347(local6.aClass3_Sub3_Sub1_1);
						local6.aClass3_Sub3_Sub1_1 = null;
					}
					local6.anInt2044 = local141;
				}
				local6.anInt2038 = local37;
			}
			local6.anInt2041 = local6.aClass8_Sub4_Sub2_1.x;
			local6.anInt2037 = local6.aClass8_Sub4_Sub2_1.x + local6.aClass8_Sub4_Sub2_1.method2693() * 64;
			local6.anInt2029 = local6.aClass8_Sub4_Sub2_1.z;
			local6.anInt2028 = local6.aClass8_Sub4_Sub2_1.z + local6.aClass8_Sub4_Sub2_1.method2693() * 64;
			Static150.method2804(arg1, local6, arg3, arg0, arg2);
		}
		for (local6 = (BgSound) aClass133_7.search(); local6 != null; local6 = (BgSound) aClass133_7.findnext()) {
			local37 = 1;
			local42 = local6.aClass8_Sub4_Sub1_1.method2681();
			if (local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1037) {
				local37 = 0;
			} else if (local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1058 || local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1054 || local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1045 || local42.anInt1043 == local6.aClass8_Sub4_Sub1_1.anInt3366) {
				local37 = 2;
			} else if (local42.anInt1062 == local6.aClass8_Sub4_Sub1_1.anInt3366 || local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1042 || local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1048 || local6.aClass8_Sub4_Sub1_1.anInt3366 == local42.anInt1066) {
				local37 = 3;
			}
			if (local6.anInt2038 != local37) {
				local141 = Static140.method2706(local6.aClass8_Sub4_Sub1_1);
				if (local6.anInt2044 != local141) {
					if (local6.aClass3_Sub3_Sub1_1 != null) {
						Client.soundMixer.method1347(local6.aClass3_Sub3_Sub1_1);
						local6.aClass3_Sub3_Sub1_1 = null;
					}
					local6.anInt2044 = local141;
				}
				local6.anInt2038 = local37;
			}
			local6.anInt2041 = local6.aClass8_Sub4_Sub1_1.x;
			local6.anInt2037 = local6.aClass8_Sub4_Sub1_1.x + local6.aClass8_Sub4_Sub1_1.method2693() * 64;
			local6.anInt2029 = local6.aClass8_Sub4_Sub1_1.z;
			local6.anInt2028 = local6.aClass8_Sub4_Sub1_1.z + local6.aClass8_Sub4_Sub1_1.method2693() * 64;
			Static150.method2804(arg1, local6, arg3, arg0, arg2);
		}
	}

	@OriginalMember(owner = "client!ra", name = "a", descriptor = "(ILclient!pb;BII)V")
	public static void method3701(@OriginalArg(0) int arg0, @OriginalArg(1) LocType arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		for (@Pc(10) BgSound local10 = (BgSound) soundlist.head(); local10 != null; local10 = (BgSound) soundlist.next()) {
			if (arg3 == local10.anInt2033 && local10.anInt2041 == arg0 * 128 && local10.anInt2029 == arg2 * 128 && arg1.id == local10.multiloc.id) {
				if (local10.aClass3_Sub3_Sub1_1 != null) {
					Client.soundMixer.method1347(local10.aClass3_Sub3_Sub1_1);
					local10.aClass3_Sub3_Sub1_1 = null;
				}
				if (local10.aClass3_Sub3_Sub1_2 != null) {
					Client.soundMixer.method1347(local10.aClass3_Sub3_Sub1_2);
					local10.aClass3_Sub3_Sub1_2 = null;
				}
				local10.unlink();
				return;
			}
		}
	}

	@OriginalMember(owner = "client!rg", name = "a", descriptor = "(Lclient!e;I)V")
	public static void method4359(@OriginalArg(0) ClientPlayer arg0) {
		@Pc(12) BgSound local12 = (BgSound) aClass133_7.find(arg0.name.method3158());
		if (local12 == null) {
			method2411(arg0.routeZ[0], null, 0, null, arg0.routeX[0], Client.minusedlevel, arg0);
		} else {
			local12.recalcSound();
		}
	}

	@OriginalMember(owner = "client!vd", name = "a", descriptor = "(BZ)V")
	public static void reset(@OriginalArg(1) boolean arg0) {
		@Pc(14) BgSound local14;
		for (local14 = (BgSound) soundlist.head(); local14 != null; local14 = (BgSound) soundlist.next()) {
			if (local14.aClass3_Sub3_Sub1_1 != null) {
				Client.soundMixer.method1347(local14.aClass3_Sub3_Sub1_1);
				local14.aClass3_Sub3_Sub1_1 = null;
			}
			if (local14.aClass3_Sub3_Sub1_2 != null) {
				Client.soundMixer.method1347(local14.aClass3_Sub3_Sub1_2);
				local14.aClass3_Sub3_Sub1_2 = null;
			}
			local14.unlink();
		}
		if (!arg0) {
			return;
		}
		for (local14 = (BgSound) aClass69_87.head(); local14 != null; local14 = (BgSound) aClass69_87.next()) {
			if (local14.aClass3_Sub3_Sub1_1 != null) {
				Client.soundMixer.method1347(local14.aClass3_Sub3_Sub1_1);
				local14.aClass3_Sub3_Sub1_1 = null;
			}
			local14.unlink();
		}
		for (local14 = (BgSound) aClass133_7.search(); local14 != null; local14 = (BgSound) aClass133_7.findnext()) {
			if (local14.aClass3_Sub3_Sub1_1 != null) {
				Client.soundMixer.method1347(local14.aClass3_Sub3_Sub1_1);
				local14.aClass3_Sub3_Sub1_1 = null;
			}
			local14.unlink();
		}
	}

	@OriginalMember(owner = "client!wc", name = "a", descriptor = "(Lclient!e;I)V")
	public static void method4597(@OriginalArg(0) ClientPlayer arg0) {
		@Pc(10) BgSound local10 = (BgSound) aClass133_7.find(arg0.name.method3158());
		if (local10 == null) {
			return;
		}
		if (local10.aClass3_Sub3_Sub1_1 != null) {
			Client.soundMixer.method1347(local10.aClass3_Sub3_Sub1_1);
			local10.aClass3_Sub3_Sub1_1 = null;
		}
		local10.unlink();
	}

	@OriginalMember(owner = "client!fl", name = "c", descriptor = "(I)V")
	public final void recalcSound() {
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
