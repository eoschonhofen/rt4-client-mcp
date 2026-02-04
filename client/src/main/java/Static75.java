import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static75 {

	@OriginalMember(owner = "client!g", name = "d", descriptor = "I")
	public static int anInt2119 = 0;

	@OriginalMember(owner = "client!g", name = "a", descriptor = "(IZ)V")
	public static void method1629(@OriginalArg(1) boolean arg0) {
		Static230.aBoolean250 = arg0;
		@Pc(13) int local13;
		@Pc(20) int local20;
		@Pc(26) int local26;
		@Pc(31) int local31;
		@Pc(60) int local60;
		@Pc(64) int local64;
		@Pc(138) int local138;
		@Pc(151) int local151;
		@Pc(169) int local169;
		if (!Static230.aBoolean250) {
			local13 = Client.in.method2184();
			local20 = (Client.psize - Client.in.pos) / 16;
			Static72.anIntArrayArray14 = new int[local20][4];
			for (local26 = 0; local26 < local20; local26++) {
				for (local31 = 0; local31 < 4; local31++) {
					Static72.anIntArrayArray14[local26][local31] = Client.in.method2224();
				}
			}
			local26 = Client.in.method2180();
			local31 = Client.in.g2();
			local60 = Client.in.method2184();
			local64 = Client.in.method2184();
			Static238.anIntArray470 = new int[local20];
			Static273.aByteArrayArray13 = new byte[local20][];
			Static191.aByteArrayArray15 = null;
			Static99.anIntArray239 = new int[local20];
			Static156.aByteArrayArray11 = new byte[local20][];
			Static19.aByteArrayArray4 = new byte[local20][];
			Static175.anIntArray371 = null;
			Static36.anIntArray84 = new int[local20];
			Static186.aByteArrayArray14 = new byte[local20][];
			Static172.anIntArray366 = new int[local20];
			Static35.anIntArray82 = new int[local20];
			local20 = 0;
			@Pc(100) boolean local100 = false;
			if ((local31 / 8 == 48 || local31 / 8 == 49) && local60 / 8 == 48) {
				local100 = true;
			}
			if (local31 / 8 == 48 && local60 / 8 == 148) {
				local100 = true;
			}
			for (local138 = (local31 - 6) / 8; local138 <= (local31 + 6) / 8; local138++) {
				for (local151 = (local60 - 6) / 8; local151 <= (local60 + 6) / 8; local151++) {
					local169 = (local138 << 8) + local151;
					if (local100 && (local151 == 49 || local151 == 149 || local151 == 147 || local138 == 50 || local138 == 49 && local151 == 47)) {
						Static238.anIntArray470[local20] = local169;
						Static36.anIntArray84[local20] = -1;
						Static172.anIntArray366[local20] = -1;
						Static99.anIntArray239[local20] = -1;
						Static35.anIntArray82[local20] = -1;
					} else {
						Static238.anIntArray470[local20] = local169;
						Static36.anIntArray84[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Static103.aClass100_558, JagString.parseInt(local138), Static86.aClass100_488, JagString.parseInt(local151) }));
						Static172.anIntArray366[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Static270.aClass100_1090, JagString.parseInt(local138), Static86.aClass100_488, JagString.parseInt(local151) }));
						Static99.anIntArray239[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Static165.aClass100_772, JagString.parseInt(local138), Static86.aClass100_488, JagString.parseInt(local151) }));
						Static35.anIntArray82[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Static278.aClass100_1103, JagString.parseInt(local138), Static86.aClass100_488, JagString.parseInt(local151) }));
					}
					local20++;
				}
			}
			Static127.method2463(local26, local60, local31, local64, false, local13);
			return;
		}
		local13 = Client.in.method2207();
		local20 = Client.in.method2207();
		local26 = Client.in.method2180();
		local31 = Client.in.method2207();
		Client.in.gBitStart();
		@Pc(391) int local391;
		for (local60 = 0; local60 < 4; local60++) {
			for (local64 = 0; local64 < 13; local64++) {
				for (local391 = 0; local391 < 13; local391++) {
					local138 = Client.in.method2238(1);
					if (local138 == 1) {
						Static187.anIntArrayArrayArray18[local60][local64][local391] = Client.in.method2238(26);
					} else {
						Static187.anIntArrayArrayArray18[local60][local64][local391] = -1;
					}
				}
			}
		}
		Client.in.gBitEnd();
		local60 = (Client.psize - Client.in.pos) / 16;
		Static72.anIntArrayArray14 = new int[local60][4];
		for (local64 = 0; local64 < local60; local64++) {
			for (local391 = 0; local391 < 4; local391++) {
				Static72.anIntArrayArray14[local64][local391] = Client.in.method2224();
			}
		}
		local64 = Client.in.g2();
		Static35.anIntArray82 = new int[local60];
		Static172.anIntArray366 = new int[local60];
		Static36.anIntArray84 = new int[local60];
		Static19.aByteArrayArray4 = new byte[local60][];
		Static175.anIntArray371 = null;
		Static99.anIntArray239 = new int[local60];
		Static156.aByteArrayArray11 = new byte[local60][];
		Static273.aByteArrayArray13 = new byte[local60][];
		Static238.anIntArray470 = new int[local60];
		Static191.aByteArrayArray15 = null;
		Static186.aByteArrayArray14 = new byte[local60][];
		local60 = 0;
		for (local391 = 0; local391 < 4; local391++) {
			for (local138 = 0; local138 < 13; local138++) {
				for (local151 = 0; local151 < 13; local151++) {
					local169 = Static187.anIntArrayArrayArray18[local391][local138][local151];
					if (local169 != -1) {
						@Pc(555) int local555 = local169 >> 14 & 0x3FF;
						@Pc(561) int local561 = local169 >> 3 & 0x7FF;
						@Pc(571) int local571 = local561 / 8 + (local555 / 8 << 8);
						@Pc(573) int local573;
						for (local573 = 0; local573 < local60; local573++) {
							if (local571 == Static238.anIntArray470[local573]) {
								local571 = -1;
								break;
							}
						}
						if (local571 != -1) {
							Static238.anIntArray470[local60] = local571;
							@Pc(609) int local609 = local571 & 0xFF;
							local573 = local571 >> 8 & 0xFF;
							Static36.anIntArray84[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Static103.aClass100_558, JagString.parseInt(local573), Static86.aClass100_488, JagString.parseInt(local609) }));
							Static172.anIntArray366[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Static270.aClass100_1090, JagString.parseInt(local573), Static86.aClass100_488, JagString.parseInt(local609) }));
							Static99.anIntArray239[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Static165.aClass100_772, JagString.parseInt(local573), Static86.aClass100_488, JagString.parseInt(local609) }));
							Static35.anIntArray82[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Static278.aClass100_1103, JagString.parseInt(local573), Static86.aClass100_488, JagString.parseInt(local609) }));
							local60++;
						}
					}
				}
			}
		}
		Static127.method2463(local26, local64, local20, local31, false, local13);
	}

	@OriginalMember(owner = "client!g", name = "a", descriptor = "(B)V")
	public static void method1631() {
		Static254.anIntArray488 = null;
		Static269.anIntArray252 = null;
		Static26.anIntArray66 = null;
		Static7.aByteArrayArray5 = null;
		Static274.anIntArray440 = null;
		Static259.anIntArray513 = null;
	}

	@OriginalMember(owner = "client!g", name = "b", descriptor = "(I)V")
	public static void method1632() {
		@Pc(9) Environment local9 = new Environment();
		for (@Pc(18) int local18 = 0; local18 < 13; local18++) {
			for (@Pc(25) int local25 = 0; local25 < 13; local25++) {
				ClientBuild.aClass92ArrayArray1[local18][local25] = local9;
			}
		}
	}

	@OriginalMember(owner = "client!g", name = "a", descriptor = "(III)Lclient!df;")
	public static Decor method1633(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Square local7 = World.levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) Decor local14 = local7.decor;
			local7.decor = null;
			return local14;
		}
	}

	@OriginalMember(owner = "client!g", name = "b", descriptor = "(B)V")
	public static void method1634() {
		@Pc(15) int local15;
		@Pc(23) int local23;
		@Pc(19) int local19;
		@Pc(27) int local27;
		@Pc(31) int local31;
		@Pc(39) int local39;
		@Pc(45) int local45;
		if (Client.ptype == 195) {
			local15 = Client.in.method2212();
			local19 = local15 & 0x3;
			local23 = local15 >> 2;
			local27 = Static133.anIntArray453[local23];
			local31 = Client.in.g1();
			local39 = (local31 >> 4 & 0x7) + Static115.anInt2940;
			local45 = (local31 & 0x7) + Static180.anInt4264;
			if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
				Client.locChangeCreate(Client.minusedlevel, local45, local19, local39, -1, -1, local27, local23, 0);
			}
		} else if (Client.ptype == 33) {
			local15 = Client.in.method2192();
			local23 = Client.in.g1();
			local27 = (local23 & 0x7) + Static180.anInt4264;
			local19 = (local23 >> 4 & 0x7) + Static115.anInt2940;
			local31 = Client.in.method2184();
			if (local19 >= 0 && local27 >= 0 && local19 < 104 && local27 < 104) {
				@Pc(122) ClientObj local122 = new ClientObj();
				local122.anInt5550 = local31;
				local122.id = local15;
				if (Client.groundObj[Client.minusedlevel][local19][local27] == null) {
					Client.groundObj[Client.minusedlevel][local19][local27] = new LinkList();
				}
				Client.groundObj[Client.minusedlevel][local19][local27].push(new ClientObjNode(local122));
				Client.showObject(local27, local19);
			}
		} else {
			@Pc(218) int local218;
			@Pc(228) int local228;
			@Pc(232) int local232;
			@Pc(247) int local247;
			@Pc(224) int local224;
			@Pc(236) int local236;
			@Pc(317) ClientProj local317;
			if (Client.ptype == 121) {
				local15 = Client.in.g1();
				local23 = Static115.anInt2940 * 2 + (local15 >> 4 & 0xF);
				local19 = (local15 & 0xF) + Static180.anInt4264 * 2;
				local27 = local23 + Client.in.g1b();
				local31 = Client.in.g1b() + local19;
				local39 = Client.in.g2b();
				local45 = Client.in.g2();
				local218 = Client.in.g1() * 4;
				local224 = Client.in.g1() * 4;
				local228 = Client.in.g2();
				local232 = Client.in.g2();
				local236 = Client.in.g1();
				if (local236 == 255) {
					local236 = -1;
				}
				local247 = Client.in.g1();
				if (local23 >= 0 && local19 >= 0 && local23 < 208 && local19 < 208 && local27 >= 0 && local31 >= 0 && local27 < 208 && local31 < 208 && local45 != 65535) {
					local31 *= 64;
					local27 = local27 * 64;
					local19 = local19 * 64;
					local23 = local23 * 64;
					local317 = new ClientProj(local45, Client.minusedlevel, local23, local19, Client.getAvH(Client.minusedlevel, local23, local19) - local218, Client.loopCycle + local228, local232 + Client.loopCycle, local236, local247, local39, local224);
					local317.method3705(local31, Client.loopCycle + local228, -local224 + Client.getAvH(Client.minusedlevel, local27, local31), local27);
					Static217.aClass69_116.push(new ClientProjNode(local317));
				}
			} else if (Client.ptype == 17) {
				local15 = Client.in.g1();
				local23 = Static115.anInt2940 + (local15 >> 4 & 0x7);
				local19 = Static180.anInt4264 + (local15 & 0x7);
				local27 = Client.in.g2();
				local31 = Client.in.g1();
				local39 = Client.in.g2();
				if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
					local23 = local23 * 128 + 64;
					local19 = local19 * 128 + 64;
					@Pc(427) MapSpotAnim local427 = new MapSpotAnim(local27, Client.minusedlevel, local23, local19, Client.getAvH(Client.minusedlevel, local23, local19) - local31, local39, Client.loopCycle);
					Static99.aClass69_64.push(new MapSpotAnimNode(local427));
				}
			} else if (Client.ptype == 179) {
				local15 = Client.in.method2177();
				local23 = local15 >> 2;
				local19 = local15 & 0x3;
				local27 = Static133.anIntArray453[local23];
				local31 = Client.in.g1();
				local39 = Static115.anInt2940 + (local31 >> 4 & 0x7);
				local45 = (local31 & 0x7) + Static180.anInt4264;
				local218 = Client.in.method2184();
				if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
					Client.locChangeCreate(Client.minusedlevel, local45, local19, local39, -1, local218, local27, local23, 0);
				}
			} else if (Client.ptype == 20) {
				local15 = Client.in.method2180();
				local23 = (local15 >> 4 & 0x7) + Static115.anInt2940;
				local19 = Static180.anInt4264 + (local15 & 0x7);
				local27 = Client.in.method2180();
				local31 = local27 >> 2;
				local39 = local27 & 0x3;
				local45 = Static133.anIntArray453[local31];
				local218 = Client.in.method2192();
				if (local218 == 65535) {
					local218 = -1;
				}
				Client.method1881(Client.minusedlevel, local39, local31, local19, local45, local23, local218);
			} else {
				@Pc(633) int local633;
				if (Client.ptype == 202) {
					local15 = Client.in.g1();
					local23 = local15 >> 2;
					local19 = local15 & 0x3;
					local27 = Client.in.g1();
					local31 = (local27 >> 4 & 0x7) + Static115.anInt2940;
					local39 = (local27 & 0x7) + Static180.anInt4264;
					@Pc(605) byte local605 = Client.in.method2215();
					@Pc(609) byte local609 = Client.in.method2215();
					@Pc(613) byte local613 = Client.in.method2175();
					local228 = Client.in.method2184();
					local232 = Client.in.method2192();
					@Pc(625) byte local625 = Client.in.g1b();
					local247 = Client.in.g2();
					local633 = Client.in.method2214();
					if (!GameShell.glRenderer) {
						Client.method2574(local625, local247, local633, local232, local39, local613, local19, local605, local31, local23, local609, local228);
					}
				}
				if (Client.ptype == 14) {
					local15 = Client.in.g1();
					local19 = Static180.anInt4264 + (local15 & 0x7);
					local23 = (local15 >> 4 & 0x7) + Static115.anInt2940;
					local27 = Client.in.g2();
					local31 = Client.in.g2();
					local39 = Client.in.g2();
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
						@Pc(710) LinkList local710 = Client.groundObj[Client.minusedlevel][local23][local19];
						if (local710 != null) {
							for (@Pc(718) ClientObjNode local718 = (ClientObjNode) local710.head(); local718 != null; local718 = (ClientObjNode) local710.next()) {
								@Pc(723) ClientObj local723 = local718.aClass8_Sub7_1;
								if ((local27 & 0x7FFF) == local723.id && local31 == local723.anInt5550) {
									local723.anInt5550 = local39;
									break;
								}
							}
							Client.showObject(local19, local23);
						}
					}
				} else if (Client.ptype == 135) {
					local15 = Client.in.method2207();
					local23 = Client.in.method2212();
					local27 = Static180.anInt4264 + (local23 & 0x7);
					local19 = (local23 >> 4 & 0x7) + Static115.anInt2940;
					local31 = Client.in.method2192();
					local39 = Client.in.method2192();
					if (local19 >= 0 && local27 >= 0 && local19 < 104 && local27 < 104 && Client.anInt549 != local15) {
						@Pc(812) ClientObj local812 = new ClientObj();
						local812.anInt5550 = local31;
						local812.id = local39;
						if (Client.groundObj[Client.minusedlevel][local19][local27] == null) {
							Client.groundObj[Client.minusedlevel][local19][local27] = new LinkList();
						}
						Client.groundObj[Client.minusedlevel][local19][local27].push(new ClientObjNode(local812));
						Client.showObject(local27, local19);
					}
				} else if (Client.ptype == 16) {
					local15 = Client.in.g1();
					local23 = Static115.anInt2940 + (local15 >> 4 & 0x7);
					local19 = (local15 & 0x7) + Static180.anInt4264;
					local27 = local23 + Client.in.g1b();
					local31 = Client.in.g1b() + local19;
					local39 = Client.in.g2b();
					local45 = Client.in.g2();
					local218 = Client.in.g1() * 4;
					local224 = Client.in.g1() * 4;
					local228 = Client.in.g2();
					local232 = Client.in.g2();
					local236 = Client.in.g1();
					local247 = Client.in.g1();
					if (local236 == 255) {
						local236 = -1;
					}
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104 && local27 >= 0 && local31 >= 0 && local27 < 104 && local31 < 104 && local45 != 65535) {
						local31 = local31 * 128 + 64;
						local19 = local19 * 128 + 64;
						local23 = local23 * 128 + 64;
						local27 = local27 * 128 + 64;
						local317 = new ClientProj(local45, Client.minusedlevel, local23, local19, Client.getAvH(Client.minusedlevel, local23, local19) - local218, local228 + Client.loopCycle, local232 + Client.loopCycle, local236, local247, local39, local224);
						local317.method3705(local31, Client.loopCycle + local228, Client.getAvH(Client.minusedlevel, local27, local31) - local224, local27);
						Static217.aClass69_116.push(new ClientProjNode(local317));
					}
				} else if (Client.ptype == 104) {
					local15 = Client.in.g1();
					local19 = Static180.anInt4264 * 2 + (local15 & 0xF);
					local23 = Static115.anInt2940 * 2 + (local15 >> 4 & 0xF);
					local27 = Client.in.g1b() + local23;
					local31 = Client.in.g1b() + local19;
					local39 = Client.in.g2b();
					local45 = Client.in.g2b();
					local218 = Client.in.g2();
					local224 = Client.in.g1b();
					local228 = Client.in.g1() * 4;
					local232 = Client.in.g2();
					local236 = Client.in.g2();
					local247 = Client.in.g1();
					local633 = Client.in.g1();
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
								local1198 = Client.npcs[local1188];
							} else {
								local1184 = -local39 - 1;
								local1194 = local1184 >> 11 & 0xF;
								local1188 = local1184 & 0x7FF;
								if (Client.anInt549 == local1188) {
									local1198 = Client.localPlayer;
								} else {
									local1198 = Client.players[local1188];
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
						@Pc(1331) ClientProj local1331 = new ClientProj(local218, Client.minusedlevel, local23, local19, Client.getAvH(Client.minusedlevel, local23, local19) - local224, local232 + Client.loopCycle, local236 + Client.loopCycle, local247, local633, local45, local228);
						local1331.method3705(local31, local232 + Client.loopCycle, -local228 + Client.getAvH(Client.minusedlevel, local27, local31), local27);
						Static217.aClass69_116.push(new ClientProjNode(local1331));
					}
				} else if (Client.ptype == 97) {
					local15 = Client.in.g1();
					local23 = Static115.anInt2940 + (local15 >> 4 & 0x7);
					local19 = Static180.anInt4264 + (local15 & 0x7);
					local27 = Client.in.g2();
					if (local27 == 65535) {
						local27 = -1;
					}
					local31 = Client.in.g1();
					local39 = local31 >> 4 & 0xF;
					local218 = Client.in.g1();
					local45 = local31 & 0x7;
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
						local224 = local39 + 1;
						if (Client.localPlayer.anIntArray318[0] >= local23 - local224 && local224 + local23 >= Client.localPlayer.anIntArray318[0] && Client.localPlayer.anIntArray317[0] >= local19 - local224 && Client.localPlayer.anIntArray317[0] <= local224 + local19 && Client.ambientVolume != 0 && local45 > 0 && Client.waveCount < 50 && local27 != -1) {
							Client.waveSoundIds[Client.waveCount] = local27;
							Client.anIntArray563[Client.waveCount] = local45;
							Client.waveDelay[Client.waveCount] = local218;
							Client.waveSounds[Client.waveCount] = null;
							Client.waveAmbient[Client.waveCount] = local39 + (local23 << 16) + (local19 << 8);
							Client.waveCount++;
						}
					}
				} else if (Client.ptype == 240) {
					local15 = Client.in.method2180();
					local19 = Static180.anInt4264 + (local15 & 0x7);
					local23 = (local15 >> 4 & 0x7) + Static115.anInt2940;
					local27 = Client.in.g2();
					if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
						@Pc(1565) LinkList local1565 = Client.groundObj[Client.minusedlevel][local23][local19];
						if (local1565 != null) {
							for (@Pc(1572) ClientObjNode local1572 = (ClientObjNode) local1565.head(); local1572 != null; local1572 = (ClientObjNode) local1565.next()) {
								if (local1572.aClass8_Sub7_1.id == (local27 & 0x7FFF)) {
									local1572.unlink();
									break;
								}
							}
							if (local1565.head() == null) {
								Client.groundObj[Client.minusedlevel][local23][local19] = null;
							}
							Client.showObject(local19, local23);
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!g", name = "a", descriptor = "(ILclient!ve;)V")
	public static void method1635(@OriginalArg(1) Js5 arg0) {
		Static166.anInt4049 = arg0.getGroupId(Static18.aClass100_106);
		Static130.anInt3161 = arg0.getGroupId(Static55.aClass100_377);
		Static73.anInt2077 = arg0.getGroupId(Static73.aClass100_454);
		Static280.anInt5900 = arg0.getGroupId(Static17.aClass100_102);
		Static131.anInt3261 = arg0.getGroupId(Static219.aClass100_920);
		Static36.anInt1165 = arg0.getGroupId(Static260.aClass100_944);
		Static214.anInt5579 = arg0.getGroupId(Static123.aClass100_592);
		Static34.anInt1049 = arg0.getGroupId(Static228.aClass100_968);
		Static202.anInt4741 = arg0.getGroupId(Static98.aClass100_524);
		Static149.anInt3551 = arg0.getGroupId(Static189.aClass100_835);
		Static19.anInt647 = arg0.getGroupId(Static259.aClass100_1075);
		Static32.anInt1016 = arg0.getGroupId(Static86.aClass100_490);
		Static78.anInt2147 = arg0.getGroupId(Static189.aClass100_837);
		Static124.anInt3083 = arg0.getGroupId(Static250.aClass100_1041);
		Static224.anInt5057 = arg0.getGroupId(Static27.aClass100_167);
		Static84.anInt2257 = arg0.getGroupId(Static18.aClass100_107);
		Static163.anInt3962 = arg0.getGroupId(Static280.aClass100_1108);
		Static128.anInt3143 = arg0.getGroupId(Static5.aClass100_9);
	}
}
