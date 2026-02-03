import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static36 {

	@OriginalMember(owner = "client!client", name = "W", descriptor = "I")
	public static int anInt1165;

	@OriginalMember(owner = "client!client", name = "kb", descriptor = "[[Lclient!hg;")
	public static GlSquare[][] aClass3_Sub14ArrayArray1;

	@OriginalMember(owner = "client!client", name = "lb", descriptor = "[I")
	public static int[] anIntArray84;

	@OriginalMember(owner = "client!client", name = "U", descriptor = "Lclient!sc;")
	public static final HashTable aClass133_3 = new HashTable(16);

	@OriginalMember(owner = "client!client", name = "eb", descriptor = "Lclient!na;")
	private static final JagString aClass100_235 = JagString.wrap("cyan:");

	@OriginalMember(owner = "client!client", name = "X", descriptor = "Lclient!na;")
	public static JagString aClass100_233 = aClass100_235;

	@OriginalMember(owner = "client!client", name = "cb", descriptor = "Lclient!na;")
	public static final JagString aClass100_234 = aClass100_235;

	@OriginalMember(owner = "client!client", name = "a", descriptor = "(Lclient!be;)Lclient!be;")
	public static IfType method938(@OriginalArg(0) IfType arg0) {
		@Pc(4) int local4 = method940(arg0).method505();
		if (local4 == 0) {
			return null;
		}
		for (@Pc(10) int local10 = 0; local10 < local4; local10++) {
			arg0 = Static5.method32(arg0.layerId);
			if (arg0 == null) {
				return null;
			}
		}
		return arg0;
	}

	@OriginalMember(owner = "client!client", name = "b", descriptor = "(Lclient!be;)Lclient!bf;")
	public static ServerActive method940(@OriginalArg(0) IfType arg0) {
		@Pc(13) ServerActive local13 = (ServerActive) Static210.aClass133_21.find(((long) arg0.parentId << 32) + (long) arg0.anInt457);
		return local13 == null ? arg0.active : local13;
	}

	@OriginalMember(owner = "client!client", name = "a", descriptor = "([Lclient!be;IIIIIII)V")
	public static void method946(@OriginalArg(0) IfType[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		for (@Pc(1) int local1 = 0; local1 < arg0.length; local1++) {
			@Pc(9) IfType local9 = arg0[local1];
			if (local9 != null && local9.layerId == arg1 && (!local9.v3 || local9.type == 0 || local9.aBoolean25 || method940(local9).eventCode != 0 || local9 == Static4.aClass13_1 || local9.clientCode == 1338) && (!local9.v3 || !method947(local9))) {
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
				if (local9 == Static105.aClass13_14) {
					Static44.aBoolean83 = true;
					Static124.anInt3075 = local50;
					Static20.anInt660 = local55;
				}
				if (!local9.v3 || local61 < local65 && local63 < local67) {
					if (local9.type == 0) {
						if (!local9.v3 && method947(local9) && Static180.aClass13_22 != local9) {
							continue;
						}
						if (local9.aBoolean29 && Static215.mouseX >= local61 && Static223.mouseY >= local63 && Static215.mouseX < local65 && Static223.mouseY < local67) {
							for (@Pc(164) HookReq local164 = (HookReq) Static185.aClass69_101.head(); local164 != null; local164 = (HookReq) Static185.aClass69_101.method2288()) {
								if (local164.aBoolean158) {
									local164.unlink();
									local164.aClass13_17.aBoolean19 = false;
								}
							}
							if (Static213.anInt4851 == 0) {
								Static105.aClass13_14 = null;
								Static4.aClass13_1 = null;
							}
							Static137.anInt3337 = 0;
						}
					}
					if (local9.v3) {
						@Pc(207) boolean local207;
						if (Static215.mouseX >= local61 && Static223.mouseY >= local63 && Static215.mouseX < local65 && Static223.mouseY < local67) {
							local207 = true;
						} else {
							local207 = false;
						}
						@Pc(212) boolean local212 = false;
						if (Static22.anInt723 == 1 && local207) {
							local212 = true;
						}
						@Pc(221) boolean local221 = false;
						if (Static150.anInt3585 == 1 && Static7.anInt985 >= local61 && Static60.anInt1893 >= local63 && Static7.anInt985 < local65 && Static60.anInt1893 < local67) {
							local221 = true;
						}
						@Pc(243) int local243;
						@Pc(322) int local322;
						if (local9.aByteArray8 != null) {
							for (local243 = 0; local243 < local9.aByteArray8.length; local243++) {
								if (Static187.aBooleanArray101[local9.aByteArray8[local243]]) {
									if (local9.anIntArray49 == null || Static83.anInt372 >= local9.anIntArray49[local243]) {
										@Pc(279) byte local279 = local9.aByteArray7[local243];
										if (local279 == 0 || ((local279 & 0x2) == 0 || Static187.aBooleanArray101[86]) && ((local279 & 0x1) == 0 || Static187.aBooleanArray101[82]) && ((local279 & 0x4) == 0 || Static187.aBooleanArray101[81])) {
											Static263.method4512(Static186.EMPTY_STRING, -1, local243 + 1, local9.parentId);
											local322 = local9.anIntArray46[local243];
											if (local9.anIntArray49 == null) {
												local9.anIntArray49 = new int[local9.aByteArray8.length];
											}
											if (local322 == 0) {
												local9.anIntArray49[local243] = Integer.MAX_VALUE;
											} else {
												local9.anIntArray49[local243] = Static83.anInt372 + local322;
											}
										}
									}
								} else if (local9.anIntArray49 != null) {
									local9.anIntArray49[local243] = 0;
								}
							}
						}
						if (local221) {
							Static40.method1015(Static60.anInt1893 - local55, Static7.anInt985 - local50, local9);
						}
						if (Static105.aClass13_14 != null && Static105.aClass13_14 != local9 && local207 && method940(local9).method509()) {
							Static56.aClass13_12 = local9;
						}
						if (local9 == Static4.aClass13_1) {
							Static146.aBoolean174 = true;
							Static81.anInt2225 = local50;
							Static228.anInt5103 = local55;
						}
						if (local9.aBoolean25 || local9.clientCode != 0) {
							@Pc(399) HookReq local399;
							if (local207 && client.mouseWheelRotation != 0 && local9.onscrollwheel != null) {
								local399 = new HookReq();
								local399.aBoolean158 = true;
								local399.aClass13_17 = local9;
								local399.anInt3097 = client.mouseWheelRotation;
								local399.anObjectArray31 = local9.onscrollwheel;
								Static185.aClass69_101.method2282(local399);
							}
							if (Static105.aClass13_14 != null || Static118.aClass13_15 != null || Static60.aBoolean108 || local9.clientCode != 1400 && Static137.anInt3337 > 0) {
								local221 = false;
								local212 = false;
								local207 = false;
							}
							@Pc(508) int local508;
							if (local9.clientCode != 0) {
								if (local9.clientCode == 1337) {
									Static280.aClass13_26 = local9;
									Static43.method1143(local9);
									continue;
								}
								if (local9.clientCode == 1338) {
									if (local221) {
										Static1.anInt5 = Static7.anInt985 - local50;
										Static107.anInt2878 = Static60.anInt1893 - local55;
									}
									continue;
								}
								if (local9.clientCode == 1400) {
									Static24.aClass13_8 = local9;
									if (local221) {
										if (Static187.aBooleanArray101[82] && Static191.anInt4502 > 0) {
											local243 = (int) ((double) (Static7.anInt985 - local50 - local9.anInt445 / 2) * 2.0D / (double) Static83.aFloat3);
											local508 = (int) ((double) (Static60.anInt1893 - local55 - local9.anInt459 / 2) * 2.0D / (double) Static83.aFloat3);
											local322 = Static13.anInt435 + local243;
											@Pc(516) int local516 = Static28.anInt919 + local508;
											@Pc(520) int local520 = local322 + Static158.anInt3846;
											@Pc(528) int local528 = Static181.anInt4296 + Static2.anInt13 - local516 - 1;
											Static61.method1454(local520, local528, 0);
											Static153.method2909();
											continue;
										}
										Static137.anInt3337 = 1;
										Static246.anInt5388 = Static215.mouseX;
										Static165.anInt4035 = Static223.mouseY;
										continue;
									}
									if (local212 && Static137.anInt3337 > 0) {
										if (Static137.anInt3337 == 1 && (Static246.anInt5388 != Static215.mouseX || Static165.anInt4035 != Static223.mouseY)) {
											Static197.anInt4620 = Static13.anInt435;
											Static71.anInt1885 = Static28.anInt919;
											Static137.anInt3337 = 2;
										}
										if (Static137.anInt3337 == 2) {
											Static98.method1964(Static197.anInt4620 + (int) ((double) (Static246.anInt5388 - Static215.mouseX) * 2.0D / (double) Static138.aFloat14));
											Static277.method4641(Static71.anInt1885 + (int) ((double) (Static165.anInt4035 - Static223.mouseY) * 2.0D / (double) Static138.aFloat14));
										}
										continue;
									}
									Static137.anInt3337 = 0;
									continue;
								}
								if (local9.clientCode == 1401) {
									if (local212) {
										Static119.method2387(local9.anInt445, Static223.mouseY - local55, Static215.mouseX - local50, local9.anInt459);
									}
									continue;
								}
								if (local9.clientCode == 1402) {
									if (!GameShell.glRenderer) {
										Static43.method1143(local9);
									}
									continue;
								}
							}
							if (!local9.aBoolean24 && local221) {
								local9.aBoolean24 = true;
								if (local9.onclick != null) {
									local399 = new HookReq();
									local399.aBoolean158 = true;
									local399.aClass13_17 = local9;
									local399.anInt3102 = Static7.anInt985 - local50;
									local399.anInt3097 = Static60.anInt1893 - local55;
									local399.anObjectArray31 = local9.onclick;
									Static185.aClass69_101.method2282(local399);
								}
							}
							if (local9.aBoolean24 && local212 && local9.onclickrepeat != null) {
								local399 = new HookReq();
								local399.aBoolean158 = true;
								local399.aClass13_17 = local9;
								local399.anInt3102 = Static215.mouseX - local50;
								local399.anInt3097 = Static223.mouseY - local55;
								local399.anObjectArray31 = local9.onclickrepeat;
								Static185.aClass69_101.method2282(local399);
							}
							if (local9.aBoolean24 && !local212) {
								local9.aBoolean24 = false;
								if (local9.onrelease != null) {
									local399 = new HookReq();
									local399.aBoolean158 = true;
									local399.aClass13_17 = local9;
									local399.anInt3102 = Static215.mouseX - local50;
									local399.anInt3097 = Static223.mouseY - local55;
									local399.anObjectArray31 = local9.onrelease;
									Static115.aClass69_70.method2282(local399);
								}
							}
							if (local212 && local9.onhold != null) {
								local399 = new HookReq();
								local399.aBoolean158 = true;
								local399.aClass13_17 = local9;
								local399.anInt3102 = Static215.mouseX - local50;
								local399.anInt3097 = Static223.mouseY - local55;
								local399.anObjectArray31 = local9.onhold;
								Static185.aClass69_101.method2282(local399);
							}
							if (!local9.aBoolean19 && local207) {
								local9.aBoolean19 = true;
								if (local9.onmouseover != null) {
									local399 = new HookReq();
									local399.aBoolean158 = true;
									local399.aClass13_17 = local9;
									local399.anInt3102 = Static215.mouseX - local50;
									local399.anInt3097 = Static223.mouseY - local55;
									local399.anObjectArray31 = local9.onmouseover;
									Static185.aClass69_101.method2282(local399);
								}
							}
							if (local9.aBoolean19 && local207 && local9.onmouserepeat != null) {
								local399 = new HookReq();
								local399.aBoolean158 = true;
								local399.aClass13_17 = local9;
								local399.anInt3102 = Static215.mouseX - local50;
								local399.anInt3097 = Static223.mouseY - local55;
								local399.anObjectArray31 = local9.onmouserepeat;
								Static185.aClass69_101.method2282(local399);
							}
							if (local9.aBoolean19 && !local207) {
								local9.aBoolean19 = false;
								if (local9.onmouseleave != null) {
									local399 = new HookReq();
									local399.aBoolean158 = true;
									local399.aClass13_17 = local9;
									local399.anInt3102 = Static215.mouseX - local50;
									local399.anInt3097 = Static223.mouseY - local55;
									local399.anObjectArray31 = local9.onmouseleave;
									Static115.aClass69_70.method2282(local399);
								}
							}
							if (local9.ontimer != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.ontimer;
								Static4.aClass69_2.method2282(local399);
							}
							@Pc(966) HookReq local966;
							if (local9.onvarctransmit != null && Static4.anInt37 > local9.anInt511) {
								if (local9.onvarctransmitlist == null || Static4.anInt37 - local9.anInt511 > 32) {
									local399 = new HookReq();
									local399.aClass13_17 = local9;
									local399.anObjectArray31 = local9.onvarctransmit;
									Static185.aClass69_101.method2282(local399);
								} else {
									label563: for (local243 = local9.anInt511; local243 < Static4.anInt37; local243++) {
										local508 = Static138.anIntArray323[local243 & 0x1F];
										for (local322 = 0; local322 < local9.onvarctransmitlist.length; local322++) {
											if (local9.onvarctransmitlist[local322] == local508) {
												local966 = new HookReq();
												local966.aClass13_17 = local9;
												local966.anObjectArray31 = local9.onvarctransmit;
												Static185.aClass69_101.method2282(local966);
												break label563;
											}
										}
									}
								}
								local9.anInt511 = Static4.anInt37;
							}
							if (local9.onvarcstrtransmit != null && Static72.anInt2036 > local9.anInt487) {
								if (local9.onvarcstrtransmitlist == null || Static72.anInt2036 - local9.anInt487 > 32) {
									local399 = new HookReq();
									local399.aClass13_17 = local9;
									local399.anObjectArray31 = local9.onvarcstrtransmit;
									Static185.aClass69_101.method2282(local399);
								} else {
									label539: for (local243 = local9.anInt487; local243 < Static72.anInt2036; local243++) {
										local508 = Static233.anIntArray452[local243 & 0x1F];
										for (local322 = 0; local322 < local9.onvarcstrtransmitlist.length; local322++) {
											if (local9.onvarcstrtransmitlist[local322] == local508) {
												local966 = new HookReq();
												local966.aClass13_17 = local9;
												local966.anObjectArray31 = local9.onvarcstrtransmit;
												Static185.aClass69_101.method2282(local966);
												break label539;
											}
										}
									}
								}
								local9.anInt487 = Static72.anInt2036;
							}
							if (local9.onvartransmit != null && Static70.anInt2015 > local9.anInt471) {
								if (local9.onvartransmitlist == null || Static70.anInt2015 - local9.anInt471 > 32) {
									local399 = new HookReq();
									local399.aClass13_17 = local9;
									local399.anObjectArray31 = local9.onvartransmit;
									Static185.aClass69_101.method2282(local399);
								} else {
									label515: for (local243 = local9.anInt471; local243 < Static70.anInt2015; local243++) {
										local508 = Static83.anIntArray23[local243 & 0x1F];
										for (local322 = 0; local322 < local9.onvartransmitlist.length; local322++) {
											if (local9.onvartransmitlist[local322] == local508) {
												local966 = new HookReq();
												local966.aClass13_17 = local9;
												local966.anObjectArray31 = local9.onvartransmit;
												Static185.aClass69_101.method2282(local966);
												break label515;
											}
										}
									}
								}
								local9.anInt471 = Static70.anInt2015;
							}
							if (local9.oninvtransmit != null && Static111.anInt2901 > local9.anInt493) {
								if (local9.oninvtransmitlist == null || Static111.anInt2901 - local9.anInt493 > 32) {
									local399 = new HookReq();
									local399.aClass13_17 = local9;
									local399.anObjectArray31 = local9.oninvtransmit;
									Static185.aClass69_101.method2282(local399);
								} else {
									label491: for (local243 = local9.anInt493; local243 < Static111.anInt2901; local243++) {
										local508 = Static27.anIntArray70[local243 & 0x1F];
										for (local322 = 0; local322 < local9.oninvtransmitlist.length; local322++) {
											if (local9.oninvtransmitlist[local322] == local508) {
												local966 = new HookReq();
												local966.aClass13_17 = local9;
												local966.anObjectArray31 = local9.oninvtransmit;
												Static185.aClass69_101.method2282(local966);
												break label491;
											}
										}
									}
								}
								local9.anInt493 = Static111.anInt2901;
							}
							if (local9.onstattransmit != null && Static89.anInt2385 > local9.anInt525) {
								if (local9.onstattransmitlist == null || Static89.anInt2385 - local9.anInt525 > 32) {
									local399 = new HookReq();
									local399.aClass13_17 = local9;
									local399.anObjectArray31 = local9.onstattransmit;
									Static185.aClass69_101.method2282(local399);
								} else {
									label467: for (local243 = local9.anInt525; local243 < Static89.anInt2385; local243++) {
										local508 = Static249.anIntArray478[local243 & 0x1F];
										for (local322 = 0; local322 < local9.onstattransmitlist.length; local322++) {
											if (local9.onstattransmitlist[local322] == local508) {
												local966 = new HookReq();
												local966.aClass13_17 = local9;
												local966.anObjectArray31 = local9.onstattransmit;
												Static185.aClass69_101.method2282(local966);
												break label467;
											}
										}
									}
								}
								local9.anInt525 = Static89.anInt2385;
							}
							if (Static49.anInt1464 > local9.anInt482 && local9.anObjectArray20 != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.anObjectArray20;
								Static185.aClass69_101.method2282(local399);
							}
							if (Static185.anInt4369 > local9.anInt482 && local9.anObjectArray1 != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.anObjectArray1;
								Static185.aClass69_101.method2282(local399);
							}
							if (Static278.anInt5867 > local9.anInt482 && local9.anObjectArray28 != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.anObjectArray28;
								Static185.aClass69_101.method2282(local399);
							}
							if (Static207.anInt4778 > local9.anInt482 && local9.anObjectArray21 != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.anObjectArray21;
								Static185.aClass69_101.method2282(local399);
							}
							if (Static209.anInt4808 > local9.anInt482 && local9.anObjectArray30 != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.anObjectArray30;
								Static185.aClass69_101.method2282(local399);
							}
							local9.anInt482 = Static119.anInt3028;
							if (local9.anObjectArray14 != null) {
								for (local243 = 0; local243 < Static182.anInt4313; local243++) {
									@Pc(1430) HookReq local1430 = new HookReq();
									local1430.aClass13_17 = local9;
									local1430.anInt3100 = Static227.anIntArray447[local243];
									local1430.anInt3099 = Static205.anIntArray426[local243];
									local1430.anObjectArray31 = local9.anObjectArray14;
									Static185.aClass69_101.method2282(local1430);
								}
							}
							if (Static13.aBoolean16 && local9.anObjectArray13 != null) {
								local399 = new HookReq();
								local399.aClass13_17 = local9;
								local399.anObjectArray31 = local9.anObjectArray13;
								Static185.aClass69_101.method2282(local399);
							}
						}
					}
					if (!local9.v3 && Static105.aClass13_14 == null && Static118.aClass13_15 == null && !Static60.aBoolean108) {
						if ((local9.overLayerId >= 0 || local9.colourOver != 0) && Static215.mouseX >= local61 && Static223.mouseY >= local63 && Static215.mouseX < local65 && Static223.mouseY < local67) {
							if (local9.overLayerId >= 0) {
								Static180.aClass13_22 = arg0[local9.overLayerId];
							} else {
								Static180.aClass13_22 = local9;
							}
						}
						if (local9.type == 8 && Static215.mouseX >= local61 && Static223.mouseY >= local63 && Static215.mouseX < local65 && Static223.mouseY < local67) {
							Static43.aClass13_11 = local9;
						}
						if (local9.scrollHeight > local9.anInt459) {
							Static236.method4049(Static223.mouseY, local9.anInt459, local9, Static215.mouseX, local50 + local9.anInt445, local55, local9.scrollHeight);
						}
					}
					if (local9.type == 0) {
						method946(arg0, local9.parentId, local61, local63, local65, local67, local50 - local9.anInt489, local55 - local9.anInt468);
						if (local9.aClass13Array3 != null) {
							method946(local9.aClass13Array3, local9.parentId, local61, local63, local65, local67, local50 - local9.anInt489, local55 - local9.anInt468);
						}
						@Pc(1595) SubInterface local1595 = (SubInterface) Static119.aClass133_9.find((long) local9.parentId);
						if (local1595 != null) {
							Static57.method1320(local50, local63, local55, local65, local1595.anInt5878, local61, local67);
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!client", name = "c", descriptor = "(Lclient!be;)Z")
	public static boolean method947(@OriginalArg(0) IfType arg0) {
		if (Static121.aBoolean154) {
			if (method940(arg0).eventCode != 0) {
				return false;
			}
			if (arg0.type == 0) {
				return false;
			}
		}
		return arg0.hide;
	}
}
