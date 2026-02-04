import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static75 {

	@OriginalMember(owner = "client!g", name = "d", descriptor = "I")
	public static int anInt2119 = 0;

	@OriginalMember(owner = "client!g", name = "a", descriptor = "(IZ)V")
	public static void method1629(@OriginalArg(1) boolean arg0) {
		Client.regionmode = arg0;
		@Pc(13) int local13;
		@Pc(20) int local20;
		@Pc(26) int local26;
		@Pc(31) int local31;
		@Pc(60) int local60;
		@Pc(64) int local64;
		@Pc(138) int local138;
		@Pc(151) int local151;
		@Pc(169) int local169;
		if (!Client.regionmode) {
			local13 = Client.in.method2184();
			local20 = (Client.psize - Client.in.pos) / 16;
			Client.anIntArrayArray14 = new int[local20][4];
			for (local26 = 0; local26 < local20; local26++) {
				for (local31 = 0; local31 < 4; local31++) {
					Client.anIntArrayArray14[local26][local31] = Client.in.method2224();
				}
			}
			local26 = Client.in.method2180();
			local31 = Client.in.g2();
			local60 = Client.in.method2184();
			local64 = Client.in.method2184();
			Client.mapBuildIndex = new int[local20];
			Client.aByteArrayArray13 = new byte[local20][];
			Client.aByteArrayArray15 = null;
			Client.mapBuildUnderGroundFile = new int[local20];
			Client.aByteArrayArray11 = new byte[local20][];
			Client.aByteArrayArray4 = new byte[local20][];
			Client.mapBuildNpcFile = null;
			Client.mapBuildGroundFile = new int[local20];
			Client.aByteArrayArray14 = new byte[local20][];
			Client.mapBuildLocationFile = new int[local20];
			Client.mapBuildUnderGroundLocationFile = new int[local20];
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
						Client.mapBuildIndex[local20] = local169;
						Client.mapBuildGroundFile[local20] = -1;
						Client.mapBuildLocationFile[local20] = -1;
						Client.mapBuildUnderGroundFile[local20] = -1;
						Client.mapBuildUnderGroundLocationFile[local20] = -1;
					} else {
						Client.mapBuildIndex[local20] = local169;
						Client.mapBuildGroundFile[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_M, JagString.parseInt(local138), Client.AUTO_UNDERSCORE, JagString.parseInt(local151) }));
						Client.mapBuildLocationFile[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_L, JagString.parseInt(local138), Client.AUTO_UNDERSCORE, JagString.parseInt(local151) }));
						Client.mapBuildUnderGroundFile[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_UM, JagString.parseInt(local138), Client.AUTO_UNDERSCORE, JagString.parseInt(local151) }));
						Client.mapBuildUnderGroundLocationFile[local20] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_UL, JagString.parseInt(local138), Client.AUTO_UNDERSCORE, JagString.parseInt(local151) }));
					}
					local20++;
				}
			}
			Client.method2463(local26, local60, local31, local64, false, local13);
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
		Client.anIntArrayArray14 = new int[local60][4];
		for (local64 = 0; local64 < local60; local64++) {
			for (local391 = 0; local391 < 4; local391++) {
				Client.anIntArrayArray14[local64][local391] = Client.in.method2224();
			}
		}
		local64 = Client.in.g2();
		Client.mapBuildUnderGroundLocationFile = new int[local60];
		Client.mapBuildLocationFile = new int[local60];
		Client.mapBuildGroundFile = new int[local60];
		Client.aByteArrayArray4 = new byte[local60][];
		Client.mapBuildNpcFile = null;
		Client.mapBuildUnderGroundFile = new int[local60];
		Client.aByteArrayArray11 = new byte[local60][];
		Client.aByteArrayArray13 = new byte[local60][];
		Client.mapBuildIndex = new int[local60];
		Client.aByteArrayArray15 = null;
		Client.aByteArrayArray14 = new byte[local60][];
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
							if (local571 == Client.mapBuildIndex[local573]) {
								local571 = -1;
								break;
							}
						}
						if (local571 != -1) {
							Client.mapBuildIndex[local60] = local571;
							@Pc(609) int local609 = local571 & 0xFF;
							local573 = local571 >> 8 & 0xFF;
							Client.mapBuildGroundFile[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_M, JagString.parseInt(local573), Client.AUTO_UNDERSCORE, JagString.parseInt(local609) }));
							Client.mapBuildLocationFile[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_L, JagString.parseInt(local573), Client.AUTO_UNDERSCORE, JagString.parseInt(local609) }));
							Client.mapBuildUnderGroundFile[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_UM, JagString.parseInt(local573), Client.AUTO_UNDERSCORE, JagString.parseInt(local609) }));
							Client.mapBuildUnderGroundLocationFile[local60] = Client.maps.getGroupId(JagString.join(new JagString[] { Client.AUTO_UL, JagString.parseInt(local573), Client.AUTO_UNDERSCORE, JagString.parseInt(local609) }));
							local60++;
						}
					}
				}
			}
		}
		Client.method2463(local26, local64, local20, local31, false, local13);
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
		@Pc(7) Square local7 = World.activeTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) Decor local14 = local7.decor;
			local7.decor = null;
			return local14;
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
