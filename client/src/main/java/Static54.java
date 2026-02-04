import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static54 {

	@OriginalMember(owner = "client!ed", name = "D", descriptor = "Lclient!na;")
	public static final JagString aClass100_374 = JagString.wrap("details");

	@OriginalMember(owner = "client!ed", name = "H", descriptor = "Lclient!na;")
	public static final JagString aClass100_375 = JagString.wrap("<)4col> x");

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(IBIILclient!be;)V")
	public static void method1305(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) IfType arg3) {
		Client.doAudio();
		if (GameShell.glRenderer) {
			Static46.method1187(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		} else {
			Pix2D.method2496(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		}
		if (Client.anInt5795 != 2 && Client.anInt5795 != 5 && Static89.aClass3_Sub2_Sub1_5 != null) {
			@Pc(48) int local48 = Client.anInt1814 + Client.anInt1747 & 0x7FF;
			@Pc(57) int local57 = Client.localPlayer.x / 32 + 48;
			@Pc(67) int local67 = 464 - Client.localPlayer.z / 32;
			if (GameShell.glRenderer) {
				((GlPix32) Static89.aClass3_Sub2_Sub1_5).method1427(arg2, arg1, arg3.anInt445, arg3.anInt459, local57, local67, local48, Client.anInt4130 + 256, (GlPix32) arg3.getGraphic(false));
			} else {
				((Pix32) Static89.aClass3_Sub2_Sub1_5).method310(arg2, arg1, arg3.anInt445, arg3.anInt459, local57, local67, local48, Client.anInt4130 + 256, arg3.anIntArray37, arg3.anIntArray45);
			}
			@Pc(146) int local146;
			@Pc(181) int local181;
			@Pc(150) int local150;
			@Pc(154) int local154;
			@Pc(231) int local231;
			@Pc(200) int local200;
			@Pc(239) int local239;
			@Pc(271) int local271;
			if (Static235.aClass134_2 != null) {
				for (@Pc(117) int local117 = 0; local117 < Static235.aClass134_2.anInt5074; local117++) {
					if (Static235.aClass134_2.method3892(local117)) {
						local146 = (Static235.aClass134_2.aShortArray73[local117] - Client.mapBuildBaseX) * 4 + 2 - Client.localPlayer.x / 32;
						local150 = Pix3D.sinTable[local48];
						local154 = Pix3D.cosTable[local48];
						@Pc(156) PixFontGeneric local156 = Static114.aClass3_Sub2_Sub9_42;
						@Pc(164) int local164 = local150 * 256 / (Client.anInt4130 + 256);
						local181 = (Static235.aClass134_2.aShortArray72[local117] - Client.mapBuildBaseZ) * 4 + 2 - Client.localPlayer.z / 32;
						@Pc(189) int local189 = local154 * 256 / (Client.anInt4130 + 256);
						local200 = local181 * local189 - local146 * local164 >> 16;
						if (Static235.aClass134_2.method3894(local117) == 1) {
							local156 = Static215.aClass3_Sub2_Sub9_32;
						}
						if (Static235.aClass134_2.method3894(local117) == 2) {
							local156 = Static280.aClass3_Sub2_Sub9_43;
						}
						local231 = local164 * local181 + local189 * local146 >> 16;
						local239 = local156.method2856(Static235.aClass134_2.aClass100Array153[local117], 100);
						@Pc(245) int local245 = local231 - local239 / 2;
						if (local245 >= -arg3.anInt445 && local245 <= arg3.anInt445 && local200 >= -arg3.anInt459 && local200 <= arg3.anInt459) {
							local271 = 16777215;
							if (Static235.aClass134_2.anIntArray444[local117] != -1) {
								local271 = Static235.aClass134_2.anIntArray444[local117];
							}
							if (GameShell.glRenderer) {
								Static46.method1188((GlPix32) arg3.getGraphic(false));
							} else {
								Pix2D.method2486(arg3.anIntArray37, arg3.anIntArray45);
							}
							local156.method2869(Static235.aClass134_2.aClass100Array153[local117], arg2 + local245 + arg3.anInt445 / 2, arg1 + arg3.anInt459 / 2 + -local200, local239, 50, local271, 0, 1, 0, 0);
							if (GameShell.glRenderer) {
								Static46.method1173();
							} else {
								Pix2D.method2482();
							}
						}
					}
				}
			}
			for (local146 = 0; local146 < ClientBuild.anInt5454; local146++) {
				local181 = ClientBuild.anIntArray331[local146] * 4 + 2 - Client.localPlayer.x / 32;
				local150 = ClientBuild.anIntArray219[local146] * 4 + 2 - Client.localPlayer.z / 32;
				@Pc(382) LocType local382 = LocType.list(ClientBuild.anIntArray417[local146]);
				if (local382.anIntArray380 != null) {
					local382 = local382.getMultiLoc();
					if (local382 == null || local382.anInt4400 == -1) {
						continue;
					}
				}
				Static60.method1446(arg3, Static67.aClass3_Sub2_Sub1Array4[local382.anInt4400], local150, local181, arg1, arg2);
			}
			for (local146 = 0; local146 < 104; local146++) {
				for (local181 = 0; local181 < 104; local181++) {
					@Pc(439) LinkList local439 = Client.groundObj[Client.minusedlevel][local146][local181];
					if (local439 != null) {
						local154 = local146 * 4 + 2 - Client.localPlayer.x / 32;
						local231 = local181 * 4 + 2 - Client.localPlayer.z / 32;
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[0], local231, local154, arg1, arg2);
					}
				}
			}
			for (local146 = 0; local146 < Client.npcCount; local146++) {
				@Pc(498) ClientNPC local498 = Client.npcs[Client.npcIds[local146]];
				if (local498 != null && local498.method2682()) {
					@Pc(507) NPCType local507 = local498.aClass96_1;
					if (local507 != null && local507.anIntArray357 != null) {
						local507 = local507.method2932();
					}
					if (local507 != null && local507.aBoolean184 && local507.aBoolean183) {
						local154 = local498.x / 32 - Client.localPlayer.x / 32;
						local231 = local498.z / 32 - Client.localPlayer.z / 32;
						if (local507.anInt3739 == -1) {
							Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[1], local231, local154, arg1, arg2);
						} else {
							Static60.method1446(arg3, Static67.aClass3_Sub2_Sub1Array4[local507.anInt3739], local231, local154, arg1, arg2);
						}
					}
				}
			}
			for (local146 = 0; local146 < Client.anInt5774; local146++) {
				@Pc(591) ClientPlayer local591 = Client.players[Client.playerIds[local146]];
				if (local591 != null && local591.method2682()) {
					local154 = local591.z / 32 - Client.localPlayer.z / 32;
					local150 = local591.x / 32 - Client.localPlayer.x / 32;
					@Pc(624) long local624 = local591.aClass100_364.method3158();
					@Pc(626) boolean local626 = false;
					for (local239 = 0; local239 < Static9.anInt178; local239++) {
						if (local624 == Static92.aLongArray3[local239] && Static104.anIntArray255[local239] != 0) {
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
					if (Client.localPlayer.anInt1650 != 0 && local591.anInt1650 != 0 && local591.anInt1650 == Client.localPlayer.anInt1650) {
						local682 = true;
					}
					if (local626) {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[3], local154, local150, arg1, arg2);
					} else if (local660) {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[5], local154, local150, arg1, arg2);
					} else if (local682) {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[4], local154, local150, arg1, arg2);
					} else {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[2], local154, local150, arg1, arg2);
					}
				}
			}
			@Pc(756) MapMarker[] local756 = Static143.aClass102Array1;
			for (local181 = 0; local181 < local756.length; local181++) {
				@Pc(770) MapMarker local770 = local756[local181];
				if (local770 != null && local770.anInt4058 != 0 && Client.loopCycle % 20 < 10) {
					if (local770.anInt4058 == 1 && local770.anInt4057 >= 0 && local770.anInt4057 < Client.npcs.length) {
						@Pc(804) ClientNPC local804 = Client.npcs[local770.anInt4057];
						if (local804 != null) {
							local231 = local804.x / 32 - Client.localPlayer.x / 32;
							local200 = local804.z / 32 - Client.localPlayer.z / 32;
							Static97.method1960(local770.anInt4048, arg1, arg2, local231, local200, arg3);
						}
					}
					if (local770.anInt4058 == 2) {
						local154 = (local770.anInt4053 - Client.mapBuildBaseX) * 4 + 2 - Client.localPlayer.x / 32;
						local231 = (-Client.mapBuildBaseZ + local770.anInt4046) * 4 + 2 - Client.localPlayer.z / 32;
						Static97.method1960(local770.anInt4048, arg1, arg2, local154, local231, arg3);
					}
					if (local770.anInt4058 == 10 && local770.anInt4057 >= 0 && Client.players.length > local770.anInt4057) {
						@Pc(905) ClientPlayer local905 = Client.players[local770.anInt4057];
						if (local905 != null) {
							local200 = local905.z / 32 - Client.localPlayer.z / 32;
							local231 = local905.x / 32 - Client.localPlayer.x / 32;
							Static97.method1960(local770.anInt4048, arg1, arg2, local231, local200, arg3);
						}
					}
				}
			}
			if (Client.anInt2939 != 0) {
				local146 = Client.anInt2939 * 4 + 2 - Client.localPlayer.x / 32;
				local181 = Static84.anInt2255 * 4 + 2 - Client.localPlayer.z / 32;
				Static60.method1446(arg3, Static84.aClass3_Sub2_Sub1_4, local181, local146, arg1, arg2);
			}
			if (GameShell.glRenderer) {
				Static46.method1186(arg2 + arg3.anInt445 / 2 - 1, arg1 + -1 - -(arg3.anInt459 / 2), 3, 3, 16777215);
			} else {
				Pix2D.method2495(arg3.anInt445 / 2 + arg2 - 1, arg3.anInt459 / 2 + -1 + arg1, 3, 3, 16777215);
			}
		} else if (GameShell.glRenderer) {
			@Pc(1041) AbstractPix32 local1041 = arg3.getGraphic(false);
			if (local1041 != null) {
				local1041.method1423(arg2, arg1);
			}
		} else {
			Pix2D.method2504(arg2, arg1, arg3.anIntArray37, arg3.anIntArray45);
		}
		Client.componentRedrawRequested2[arg0] = true;
	}

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static172.anInt4164 && arg3 <= Static224.anInt5063) {
			@Pc(22) int local22 = Static78.method1690(Static106.anInt2869, arg1, Static267.anInt5773);
			@Pc(28) int local28 = Static78.method1690(Static106.anInt2869, arg0, Static267.anInt5773);
			Static101.method2054(local22, arg3, local28, arg2);
		}
	}

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "([SI[Lclient!na;II)V")
	public static void method1307(@OriginalArg(0) short[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JagString[] arg2, @OriginalArg(4) int arg3) {
		if (arg1 <= arg3) {
			return;
		}
		@Pc(14) int local14 = arg3;
		@Pc(21) int local21 = (arg3 + arg1) / 2;
		@Pc(25) JagString local25 = arg2[local21];
		arg2[local21] = arg2[arg1];
		arg2[arg1] = local25;
		@Pc(39) short local39 = arg0[local21];
		arg0[local21] = arg0[arg1];
		arg0[arg1] = local39;
		for (@Pc(51) int local51 = arg3; local51 < arg1; local51++) {
			if (local25 == null || arg2[local51] != null && arg2[local51].method3139(local25) < (local51 & 0x1)) {
				@Pc(80) JagString local80 = arg2[local51];
				arg2[local51] = arg2[local14];
				arg2[local14] = local80;
				@Pc(94) short local94 = arg0[local51];
				arg0[local51] = arg0[local14];
				arg0[local14++] = local94;
			}
		}
		arg2[arg1] = arg2[local14];
		arg2[local14] = local25;
		arg0[arg1] = arg0[local14];
		arg0[local14] = local39;
		method1307(arg0, local14 - 1, arg2, arg3);
		method1307(arg0, arg1, arg2, local14 + 1);
	}

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(IIII)I")
	public static int getTable(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (arg0 > 243) {
			arg1 >>= 0x4;
		} else if (arg0 > 217) {
			arg1 >>= 0x3;
		} else if (arg0 > 192) {
			arg1 >>= 0x2;
		} else if (arg0 > 179) {
			arg1 >>= 0x1;
		}
		return (arg0 >> 1) + (arg1 >> 5 << 7) + (arg2 >> 2 << 10);
	}

	@OriginalMember(owner = "client!ed", name = "b", descriptor = "(II)Lclient!ba;")
	public static GWCWorld method1310(@OriginalArg(1) int arg0) {
		return Static61.aBoolean109 && arg0 >= Static19.anInt636 && arg0 <= Static171.anInt4157 ? Static196.aClass10_Sub1Array2[arg0 - Static19.anInt636] : null;
	}

}
