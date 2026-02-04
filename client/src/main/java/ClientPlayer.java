import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!e")
public final class ClientPlayer extends ClientEntity {

	@OriginalMember(owner = "client!e", name = "Bc", descriptor = "Lclient!hh;")
	public PlayerModel aClass59_1;

	@OriginalMember(owner = "client!e", name = "Mc", descriptor = "Lclient!na;")
	public JagString name;

	@OriginalMember(owner = "client!e", name = "tc", descriptor = "I")
	public int anInt1649 = -1;

	@OriginalMember(owner = "client!e", name = "wc", descriptor = "I")
	private int anInt1651 = 0;

	@OriginalMember(owner = "client!e", name = "uc", descriptor = "I")
	public int anInt1650 = 0;

	@OriginalMember(owner = "client!e", name = "yc", descriptor = "I")
	public int combatLevel = 0;

	@OriginalMember(owner = "client!e", name = "Fc", descriptor = "I")
	public int anInt1656 = 0;

	@OriginalMember(owner = "client!e", name = "Cc", descriptor = "I")
	public int anInt1654 = -1;

	@OriginalMember(owner = "client!e", name = "qc", descriptor = "I")
	public int anInt1648 = -1;

	@OriginalMember(owner = "client!e", name = "Pc", descriptor = "I")
	public int anInt1664 = 0;

	@OriginalMember(owner = "client!e", name = "Hc", descriptor = "I")
	public int anInt1658 = -1;

	@OriginalMember(owner = "client!e", name = "Sc", descriptor = "I")
	public int anInt1667 = -1;

	@OriginalMember(owner = "client!e", name = "Oc", descriptor = "Z")
	public boolean aBoolean98 = false;

	@OriginalMember(owner = "client!e", name = "Xc", descriptor = "I")
	public int anInt1671 = 0;

	@OriginalMember(owner = "client!e", name = "Vc", descriptor = "I")
	public int anInt1669 = -1;

	@OriginalMember(owner = "client!e", name = "Wc", descriptor = "I")
	public int anInt1670 = -1;

	@OriginalMember(owner = "client!e", name = "c", descriptor = "(B)I")
	@Override
	public final int method2693() {
		return this.aClass59_1 == null || this.aClass59_1.anInt2492 == -1 ? super.method2693() : NPCType.list(this.aClass59_1.anInt2492).anInt3713;
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "(I)I")
	@Override
	protected final int method2688() {
		return this.anInt3365;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(ILclient!wa;)V")
	public final void method1262(@OriginalArg(1) Packet arg0) {
		arg0.pos = 0;
		@Pc(20) int local20 = arg0.g1();
		@Pc(22) int local22 = -1;
		@Pc(26) int local26 = local20 & 0x1;
		@Pc(37) boolean local37 = (local20 & 0x4) != 0;
		@Pc(41) int local41 = super.method2693();
		@Pc(44) int[] local44 = new int[12];
		this.method2692((local20 >> 3 & 0x7) + 1);
		this.anInt1651 = local20 >> 6 & 0x3;
		this.x += (this.method2693() - local41) * 64;
		this.z += (this.method2693() - local41) * 64;
		this.anInt1669 = arg0.g1b();
		this.anInt1649 = arg0.g1b();
		this.anInt1650 = 0;
		@Pc(111) int local111;
		@Pc(127) int local127;
		@Pc(134) int local134;
		@Pc(175) int local175;
		for (@Pc(102) int local102 = 0; local102 < 12; local102++) {
			local111 = arg0.g1();
			if (local111 == 0) {
				local44[local102] = 0;
			} else {
				local127 = arg0.g1();
				local134 = (local111 << 8) + local127;
				if (local102 == 0 && local134 == 65535) {
					local22 = arg0.g2();
					this.anInt1650 = arg0.g1();
					break;
				}
				if (local134 >= 32768) {
					local134 = Static234.anIntArray455[local134 - 32768];
					local44[local102] = local134 | 0x40000000;
					local175 = ObjType.list(local134).anInt2351;
					if (local175 != 0) {
						this.anInt1650 = local175;
					}
				} else {
					local44[local102] = local134 - 256 | Integer.MIN_VALUE;
				}
			}
		}
		@Pc(197) int[] local197 = new int[5];
		for (local111 = 0; local111 < 5; local111++) {
			local127 = arg0.g1();
			if (local127 < 0 || local127 >= PlayerModel.recol1s[local111].length) {
				local127 = 0;
			}
			local197[local111] = local127;
		}
		this.anInt3365 = arg0.g2();
		@Pc(236) long local236 = arg0.g8();
		this.name = Static79.toBaseDisplayName(local236).method3125();
		this.combatLevel = arg0.g1();
		if (local37) {
			this.anInt1671 = arg0.g2();
			this.anInt1656 = this.combatLevel;
			this.anInt1667 = -1;
		} else {
			this.anInt1671 = 0;
			this.anInt1656 = arg0.g1();
			this.anInt1667 = arg0.g1();
			if (this.anInt1667 == 255) {
				this.anInt1667 = -1;
			}
		}
		local134 = this.anInt1664;
		this.anInt1664 = arg0.g1();
		if (this.anInt1664 == 0) {
			BgSound.method4597(this);
		} else {
			@Pc(309) int local309 = this.anInt1658;
			@Pc(312) int local312 = this.anInt1654;
			@Pc(315) int local315 = this.anInt1670;
			local175 = this.anInt1648;
			this.anInt1648 = arg0.g2();
			this.anInt1658 = arg0.g2();
			this.anInt1654 = arg0.g2();
			this.anInt1670 = arg0.g2();
			if (this.anInt1664 != local134 || this.anInt1648 != local175 || this.anInt1658 != local309 || local312 != this.anInt1654 || this.anInt1670 != local315) {
				BgSound.method4359(this);
			}
		}
		if (this.aClass59_1 == null) {
			this.aClass59_1 = new PlayerModel();
		}
		local175 = this.aClass59_1.anInt2492;
		this.aClass59_1.method1950(local197, local22, local26 == 1, local44, this.anInt3365);
		if (local175 != local22) {
			this.x = this.routeX[0] * 128 + this.method2693() * 64;
			this.z = this.routeZ[0] * 128 + this.method2693() * 64;
		}
		if (this.aClass47_Sub1_5 != null) {
			this.aClass47_Sub1_5.method1646();
		}
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIIIIIIJILclient!ga;)V")
	@Override
	public final void method4546(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8, @OriginalArg(9) int arg9, @OriginalArg(10) ParticleSystem arg10) {
		if (this.aClass59_1 == null) {
			return;
		}
		@Pc(25) SeqType local25 = this.anInt3369 != -1 && this.anInt3420 == 0 ? SeqType.list(this.anInt3369) : null;
		@Pc(54) SeqType local54 = this.anInt3366 == -1 || this.aBoolean98 || this.anInt3366 == this.method2681().anInt1037 && local25 != null ? null : SeqType.list(this.anInt3366);
		@Pc(76) ModelLit local76 = this.aClass59_1.method1954(this.aClass147Array3, this.anInt3373, local54, local25, this.anInt3396, this.anInt3388, this.anInt3360, this.anInt3425, this.anInt3407);
		@Pc(79) int local79 = Static198.method1029();
		if (GameShell.glRenderer && Static238.anInt5316 < 96 && local79 > 50) {
			Static16.method501();
		}
		@Pc(102) int local102;
		if (Client.modewhat != 0 && local79 < 50) {
			local102 = 50 - local79;
			while (Static105.anInt2863 < local102) {
				Static51.aByteArrayArray8[Static105.anInt2863] = new byte[102400];
				Static105.anInt2863++;
			}
			while (Static105.anInt2863 > local102) {
				Static105.anInt2863--;
				Static51.aByteArrayArray8[Static105.anInt2863] = null;
			}
		}
		if (local76 == null) {
			return;
		}
		this.anInt3413 = local76.calcBoundingCylinder();
		@Pc(184) ModelLit local184;
		if (Static209.aBoolean240 && (this.aClass59_1.anInt2492 == -1 || NPCType.list(this.aClass59_1.anInt2492).aBoolean180)) {
			local184 = Static41.method1043(160, this.aBoolean171, local54 == null ? local25 : local54, this.x, 0, this.z, 0, 1, local76, arg0, local54 == null ? this.anInt3425 : this.anInt3407, this.anInt3424, 240);
			if (GameShell.glRenderer) {
				@Pc(188) float local188 = Static239.method4179();
				@Pc(190) float local190 = Static239.method4166();
				Static239.method4178();
				Static239.method4152(local188, local190 - 150.0F);
				local184.method4546(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, null);
				Static239.method4157();
				Static239.method4152(local188, local190);
			} else {
				local184.method4546(0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, -1L, arg9, null);
			}
		}
		if (Client.localPlayer == this) {
			for (local102 = Static143.aClass102Array1.length - 1; local102 >= 0; local102--) {
				@Pc(245) MapMarker local245 = Static143.aClass102Array1[local102];
				if (local245 != null && local245.anInt4052 != -1) {
					@Pc(291) int local291;
					@Pc(302) int local302;
					if (local245.anInt4058 == 1 && local245.anInt4057 >= 0 && Client.npcs.length > local245.anInt4057) {
						@Pc(278) ClientNPC local278 = Client.npcs[local245.anInt4057];
						if (local278 != null) {
							local291 = local278.x / 32 - Client.localPlayer.x / 32;
							local302 = local278.z / 32 - Client.localPlayer.z / 32;
							this.method1263(null, local302, local76, local291, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
						}
					}
					if (local245.anInt4058 == 2) {
						@Pc(340) int local340 = (local245.anInt4053 - Client.mapBuildBaseX) * 4 + 2 - Client.localPlayer.x / 32;
						local291 = (local245.anInt4046 - Client.mapBuildBaseZ) * 4 + 2 - Client.localPlayer.z / 32;
						this.method1263(null, local291, local76, local340, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
					}
					if (local245.anInt4058 == 10 && local245.anInt4057 >= 0 && Client.players.length > local245.anInt4057) {
						@Pc(395) ClientPlayer local395 = Client.players[local245.anInt4057];
						if (local395 != null) {
							local291 = local395.x / 32 - Client.localPlayer.x / 32;
							local302 = local395.z / 32 - Client.localPlayer.z / 32;
							this.method1263(null, local302, local76, local291, arg5, arg9, arg0, arg7, arg4, arg3, arg1, local245.anInt4052, arg2, arg6);
						}
					}
				}
			}
		}
		this.method2687(local76);
		this.method2685(local76, arg0);
		local184 = null;
		if (!this.aBoolean98 && this.anInt3432 != -1 && this.anInt3399 != -1) {
			@Pc(471) SpotType local471 = SpotType.list(this.anInt3432);
			local184 = local471.method1319(this.anInt3418, this.anInt3399, this.anInt3361);
			if (local184 != null) {
				local184.method4575(0, -this.anInt3394, 0);
				if (local471.aBoolean100) {
					if (Static101.anInt2640 != 0) {
						local184.method4574(Static101.anInt2640);
					}
					if (Static102.anInt2680 != 0) {
						local184.method4564(Static102.anInt2680);
					}
					if (Static62.anInt1938 != 0) {
						local184.method4575(0, Static62.anInt1938, 0);
					}
				}
			}
		}
		@Pc(515) ModelLit local515 = null;
		if (!this.aBoolean98 && this.anObject5 != null) {
			if (Client.loopCycle >= this.anInt3375) {
				this.anObject5 = null;
			}
			if (this.anInt3390 <= Client.loopCycle && this.anInt3375 > Client.loopCycle) {
				if (this.anObject5 instanceof ClientLocAnim) {
					local515 = (ModelLit) ((ClientLocAnim) this.anObject5).method1049();
				} else {
					local515 = (ModelLit) this.anObject5;
				}
				local515.method4575(this.anInt3379 - this.x, this.anInt3406 + -this.anInt3424, this.anInt3426 - this.z);
				if (this.anInt3400 == 512) {
					local515.method4578();
				} else if (this.anInt3400 == 1024) {
					local515.method4552();
				} else if (this.anInt3400 == 1536) {
					local515.method4563();
				}
			}
		}
		if (GameShell.glRenderer) {
			local76.aBoolean303 = true;
			local76.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			if (local184 != null) {
				local184.aBoolean303 = true;
				local184.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
			}
		} else {
			if (local184 != null) {
				local76 = ((SoftwareModelLit) local76).method4588(local184);
			}
			if (local515 != null) {
				local76 = ((SoftwareModelLit) local76).method4588(local515);
			}
			local76.aBoolean303 = true;
			local76.method4546(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, this.aClass47_Sub1_5);
		}
		if (local515 == null) {
			return;
		}
		if (this.anInt3400 == 512) {
			local515.method4563();
		} else if (this.anInt3400 == 1024) {
			local515.method4552();
		} else if (this.anInt3400 == 1536) {
			local515.method4578();
		}
		local515.method4575(this.x - this.anInt3379, -this.anInt3406 + this.anInt3424, this.z - this.anInt3426);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(Lclient!ga;ILclient!ak;IIIIIIIIIIII)V")
	private void method1263(@OriginalArg(0) ParticleSystem arg0, @OriginalArg(1) int arg1, @OriginalArg(2) ModelLit arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10, @OriginalArg(12) int arg11, @OriginalArg(13) int arg12, @OriginalArg(14) int arg13) {
		@Pc(12) int local12 = arg3 * arg3 + arg1 * arg1;
		if (local12 < 16 || local12 > 360000) {
			return;
		}
		@Pc(34) int local34 = (int) (Math.atan2((double) arg3, (double) arg1) * 325.949D) & 0x7FF;
		@Pc(46) ModelLit local46 = Client.method3800(local34, this.z, arg11, this.x, arg2, this.anInt3424);
		if (local46 == null) {
			return;
		}
		if (!GameShell.glRenderer) {
			local46.method4546(0, arg10, arg12, arg9, arg8, arg4, arg13, arg7, -1L, arg5, arg0);
			return;
		}
		@Pc(52) float local52 = Static239.method4179();
		@Pc(54) float local54 = Static239.method4166();
		Static239.method4178();
		Static239.method4152(local52, local54 - 150.0F);
		local46.method4546(0, arg10, arg12, arg9, arg8, arg4, arg13, arg7, -1L, arg5, arg0);
		Static239.method4157();
		Static239.method4152(local52, local54);
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(B)Z")
	@Override
	public final boolean method2682() {
		return this.aClass59_1 != null;
	}

	@OriginalMember(owner = "client!e", name = "e", descriptor = "(I)Lclient!na;")
	public final JagString method1264() {
		@Pc(2) JagString local2 = this.name;
		if (Static103.aClass100Array88 != null) {
			local2 = JagString.join(new JagString[] { Static103.aClass100Array88[this.anInt1651], local2 });
		}
		if (Static263.aClass100Array174 != null) {
			local2 = JagString.join(new JagString[] { local2, Static263.aClass100Array174[this.anInt1651] });
		}
		return local2;
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(IIIII)V")
	@Override
	public final void method4545(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
	}

	@OriginalMember(owner = "client!e", name = "a", descriptor = "(BIZI)V")
	public final void method1265(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2) {
		super.method2683(this.method2693(), arg0, arg2, arg1);
	}

	@OriginalMember(owner = "client!e", name = "finalize", descriptor = "()V")
	@Override
	public final void finalize() {
	}

	@OriginalMember(owner = "client!e", name = "b", descriptor = "()I")
	@Override
	public final int calcBoundingCylinder() {
		return this.anInt3413;
	}
}
