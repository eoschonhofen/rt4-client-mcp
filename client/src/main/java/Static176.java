import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static176 {

	@OriginalMember(owner = "client!ob", name = "f", descriptor = "Lclient!ve;")
	public static Js5 aClass153_76;

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "[Z")
	public static final boolean[] aBooleanArray95 = new boolean[5];

	@OriginalMember(owner = "client!ob", name = "e", descriptor = "Lclient!na;")
	public static final JagString aClass100_800 = JagString.wrap("");

	@OriginalMember(owner = "client!ob", name = "o", descriptor = "Lclient!na;")
	private static final JagString aClass100_801 = JagString.wrap(")4a=");

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(IB)Z")
	public static boolean method3303(@OriginalArg(0) int arg0) {
		@Pc(3) GWCWorld local3 = Static54.method1310(arg0);
		if (local3 == null) {
			return false;
		} else if (SignLink.anInt5928 == 1 || SignLink.anInt5928 == 2 || Client.modewhere == 2) {
			@Pc(31) byte[] local31 = local3.aClass100_71.builderToString();
			Client.host = new String(local31, 0, local31.length);
			Client.anInt3103 = local3.anInt382;
			if (Client.modewhere != 0) {
				Client.gamePort = Client.anInt3103 + 40000;
				Client.loginPort = Client.gamePort;
				Client.js5Port = Client.anInt3103 + 50000;
			}
			return true;
		} else {
			@Pc(62) JagString local62 = Static211.aClass100_230;
			if (Client.modewhere != 0) {
				local62 = JagString.join(new JagString[] { Static31.aClass100_193, JagString.parseInt(local3.anInt382 + 7000) });
			}
			@Pc(89) JagString local89 = Static211.aClass100_230;
			if (Static47.aClass100_991 != null) {
				local89 = JagString.join(new JagString[] { Static167.aClass100_783, Static47.aClass100_991 });
			}
			@Pc(182) JagString local182 = JagString.join(new JagString[] { Static115.aClass100_582, local3.aClass100_71, local62, Static279.aClass100_1107, JagString.parseInt(Client.lang), aClass100_801, JagString.parseInt(Client.affid), local89, Static139.aClass100_659, Client.js ? Static30.aClass100_184 : Static260.aClass100_945, Static60.aClass100_420, Client.objecttag ? Static30.aClass100_184 : Static260.aClass100_945, Static198.aClass100_260, Client.advertsuppressed ? Static30.aClass100_184 : Static260.aClass100_945 });
			try {
				Static215.client.getAppletContext().showDocument(local182.method3107(), "_self");
				return true;
			} catch (@Pc(191) Exception local191) {
				return false;
			}
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(IIIIIIB)V")
	public static void method3304(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(15) int local15;
		@Pc(47) int local47;
		if (Static260.anInt5014 == 0) {
			@Pc(13) int local13 = Static148.anInt3535;
			local15 = Static1.anInt4;
			@Pc(17) int local17 = Static247.anInt5405;
			@Pc(19) int local19 = Static240.anInt5334;
			@Pc(33) int local33 = (arg5 - arg3) * (local17 - local19) / arg1 + local19;
			local47 = local15 + (local13 - local15) * (arg4 - arg0) / arg2;
			if (Client.targetMode && (Static274.anInt4999 & 0x40) != 0) {
				@Pc(61) IfType local61 = IfType.method1418(Static98.anInt2512, Static15.anInt506);
				if (local61 == null) {
					Client.method1294();
				} else {
					Client.addMenuOption(Static246.anInt5393, 0L, Static225.aClass100_961, local33, (short) 11, Static102.aClass100_545, local47);
				}
			} else {
				if (Client.game == 1) {
					Client.addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, local33, (short) 36, Text.aClass100_957, local47);
				}
				Client.addMenuOption(-1, 0L, TitleScreen.AUTO_EMPTY, local33, (short) 60, Static195.aClass100_859, local47);
			}
		}
		@Pc(112) long local112 = -1L;
		for (local15 = 0; local15 < Static2.anInt7; local15++) {
			@Pc(121) long local121 = Static259.aLongArray11[local15];
			local47 = (int) local121 & 0x7F;
			@Pc(133) int local133 = (int) local121 >> 29 & 0x3;
			@Pc(140) int local140 = (int) (local121 >>> 32) & Integer.MAX_VALUE;
			@Pc(147) int local147 = (int) local121 >> 7 & 0x7F;
			if (local121 != local112) {
				local112 = local121;
				@Pc(240) int local240;
				if (local133 == 2 && World.method523(Client.minusedlevel, local47, local147, local121)) {
					@Pc(172) LocType local172 = LocType.list(local140);
					if (local172.anIntArray380 != null) {
						local172 = local172.getMultiLoc();
					}
					if (local172 == null) {
						continue;
					}
					if (Static260.anInt5014 == 1) {
						Client.addMenuOption(Static169.anInt4075, local121, JagString.join(new JagString[] { Static34.aClass100_203, Static27.aClass100_164, local172.aClass100_830 }), local47, (short) 14, Text.aClass100_937, local147);
					} else if (Client.targetMode) {
						@Pc(363) ParamType local363 = Static121.anInt3039 == -1 ? null : ParamType.list(Static121.anInt3039);
						if ((Static274.anInt4999 & 0x4) != 0 && (local363 == null || local172.method3423(local363.anInt2667, Static121.anInt3039) != local363.anInt2667)) {
							Client.addMenuOption(Static246.anInt5393, local121, JagString.join(new JagString[] { Static78.aClass100_466, Static27.aClass100_164, local172.aClass100_830 }), local47, (short) 38, Static102.aClass100_545, local147);
						}
					} else {
						@Pc(228) JagString[] local228 = local172.aClass100Array130;
						if (Static208.aBoolean237) {
							local228 = Static279.method4664(local228);
						}
						if (local228 != null) {
							for (local240 = 4; local240 >= 0; local240--) {
								if (local228[local240] != null) {
									@Pc(254) short local254 = 0;
									if (local240 == 0) {
										local254 = 42;
									}
									if (local240 == 1) {
										local254 = 50;
									}
									@Pc(268) int local268 = -1;
									if (local240 == 2) {
										local254 = 49;
									}
									if (local172.anInt4406 == local240) {
										local268 = local172.anInt4416;
									}
									if (local240 == 3) {
										local254 = 46;
									}
									if (local240 == local172.anInt4420) {
										local268 = local172.anInt4423;
									}
									if (local240 == 4) {
										local254 = 1001;
									}
									Client.addMenuOption(local268, local121, JagString.join(new JagString[] { Static240.aClass100_1008, local172.aClass100_830 }), local47, local254, local228[local240], local147);
								}
							}
						}
						Client.addMenuOption(Static225.anInt5073, (long) local172.id, JagString.join(new JagString[] { Static240.aClass100_1008, local172.aClass100_830 }), local47, (short) 1004, Text.aClass100_675, local147);
					}
				}
				@Pc(514) int local514;
				@Pc(526) int local526;
				@Pc(479) int local479;
				@Pc(493) int local493;
				@Pc(502) ClientNPC local502;
				@Pc(597) ClientPlayer local597;
				if (local133 == 1) {
					@Pc(421) ClientNPC local421 = Client.npcs[local140];
					if ((local421.aClass96_1.anInt3713 & 0x1) == 0 && (local421.x & 0x7F) == 0 && (local421.z & 0x7F) == 0 || (local421.aClass96_1.anInt3713 & 0x1) == 1 && (local421.x & 0x7F) == 64 && (local421.z & 0x7F) == 64) {
						local479 = local421.x + 64 - local421.aClass96_1.anInt3713 * 64;
						local240 = local421.z - (local421.aClass96_1.anInt3713 - 1) * 64;
						for (local493 = 0; local493 < Client.npcCount; local493++) {
							local502 = Client.npcs[Client.npcIds[local493]];
							local514 = local502.x + 64 - local502.aClass96_1.anInt3713 * 64;
							local526 = local502.z + 64 - local502.aClass96_1.anInt3713 * 64;
							if (local502 != null && local421 != local502 && local514 >= local479 && local421.aClass96_1.anInt3713 - (local514 - local479 >> 7) >= local502.aClass96_1.anInt3713 && local240 <= local526 && local502.aClass96_1.anInt3713 <= local421.aClass96_1.anInt3713 - (local526 - local240 >> 7)) {
								Client.method4240(local502.aClass96_1, local47, Client.npcIds[local493], local147);
							}
						}
						for (local493 = 0; local493 < Client.playerCount; local493++) {
							local597 = Client.players[Client.playerIds[local493]];
							local514 = local597.x + 64 - local597.method2693() * 64;
							local526 = local597.z + 64 - local597.method2693() * 64;
							if (local597 != null && local514 >= local479 && local597.method2693() <= local421.aClass96_1.anInt3713 - (local514 - local479 >> 7) && local526 >= local240 && local597.method2693() <= local421.aClass96_1.anInt3713 - (local526 - local240 >> 7)) {
								Client.method3767(Client.playerIds[local493], local147, local597, local47);
							}
						}
					}
					Client.method4240(local421.aClass96_1, local47, local140, local147);
				}
				if (local133 == 0) {
					@Pc(688) ClientPlayer local688 = Client.players[local140];
					if ((local688.x & 0x7F) == 64 && (local688.z & 0x7F) == 64) {
						local479 = local688.x - (local688.method2693() - 1) * 64;
						local240 = local688.z + 64 - local688.method2693() * 64;
						for (local493 = 0; local493 < Client.npcCount; local493++) {
							local502 = Client.npcs[Client.npcIds[local493]];
							local514 = local502.x + 64 - local502.aClass96_1.anInt3713 * 64;
							local526 = local502.z + 64 - local502.aClass96_1.anInt3713 * 64;
							if (local502 != null && local514 >= local479 && local502.aClass96_1.anInt3713 <= local688.method2693() - (local514 - local479 >> 7) && local526 >= local240 && local502.aClass96_1.anInt3713 <= local688.method2693() - (local526 - local240 >> 7)) {
								Client.method4240(local502.aClass96_1, local47, Client.npcIds[local493], local147);
							}
						}
						for (local493 = 0; local493 < Client.playerCount; local493++) {
							local597 = Client.players[Client.playerIds[local493]];
							local514 = local597.x - (local597.method2693() - 1) * 64;
							local526 = local597.z + 64 - local597.method2693() * 64;
							if (local597 != null && local597 != local688 && local479 <= local514 && local597.method2693() <= local688.method2693() - (local514 - local479 >> 7) && local526 >= local240 && local597.method2693() <= local688.method2693() - (local526 - local240 >> 7)) {
								Client.method3767(Client.playerIds[local493], local147, local597, local47);
							}
						}
					}
					Client.method3767(local140, local147, local688, local47);
				}
				if (local133 == 3) {
					@Pc(931) LinkList local931 = Client.groundObj[Client.minusedlevel][local47][local147];
					if (local931 != null) {
						for (@Pc(940) ClientObjNode local940 = (ClientObjNode) local931.method2279(); local940 != null; local940 = (ClientObjNode) local931.method2286()) {
							local240 = local940.aClass8_Sub7_1.id;
							@Pc(951) ObjType local951 = ObjType.list(local240);
							if (Static260.anInt5014 == 1) {
								Client.addMenuOption(Static169.anInt4075, (long) local240, JagString.join(new JagString[] { Static34.aClass100_203, Static223.aClass100_947, local951.name}), local47, (short) 33, Text.aClass100_937, local147);
							} else if (Client.targetMode) {
								@Pc(1142) ParamType local1142 = Static121.anInt3039 == -1 ? null : ParamType.list(Static121.anInt3039);
								if ((Static274.anInt4999 & 0x1) != 0 && (local1142 == null || local951.method1829(local1142.anInt2667, Static121.anInt3039) != local1142.anInt2667)) {
									Client.addMenuOption(Static246.anInt5393, (long) local240, JagString.join(new JagString[] { Static78.aClass100_466, Static223.aClass100_947, local951.name}), local47, (short) 39, Static102.aClass100_545, local147);
								}
							} else {
								@Pc(997) JagString[] local997 = local951.aClass100Array72;
								if (Static208.aBoolean237) {
									local997 = Static279.method4664(local997);
								}
								for (local514 = 4; local514 >= 0; local514--) {
									if (local997 != null && local997[local514] != null) {
										@Pc(1025) byte local1025 = 0;
										if (local514 == 0) {
											local1025 = 21;
										}
										if (local514 == 1) {
											local1025 = 34;
										}
										@Pc(1041) int local1041 = -1;
										if (local514 == local951.anInt2338) {
											local1041 = local951.anInt2327;
										}
										if (local514 == 2) {
											local1025 = 18;
										}
										if (local951.anInt2355 == local514) {
											local1041 = local951.anInt2321;
										}
										if (local514 == 3) {
											local1025 = 20;
										}
										if (local514 == 4) {
											local1025 = 24;
										}
										Client.addMenuOption(local1041, (long) local240, JagString.join(new JagString[] { Static8.aClass100_32, local951.name}), local47, local1025, local997[local514], local147);
									}
								}
								Client.addMenuOption(Static225.anInt5073, (long) local240, JagString.join(new JagString[] { Static8.aClass100_32, local951.name}), local47, (short) 1002, Text.aClass100_675, local147);
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ob", name = "a", descriptor = "(IIIIII)V")
	public static void method3308(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		for (@Pc(8) int local8 = arg2; local8 <= arg0; local8++) {
			Static131.method2576(Static71.anIntArrayArray10[local8], arg3, arg1, arg4);
		}
	}
}
