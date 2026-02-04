import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class ScriptRunner {

	@OriginalMember(owner = "client!be", name = "ib", descriptor = "Lclient!na;")
	public static final JagString EVENT_OPBASE = JagString.wrap("event_opbase");

	@OriginalMember(owner = "client!uj", name = "t", descriptor = "[I")
	public static final int[] intStack = new int[1000];

	@OriginalMember(owner = "client!ab", name = "j", descriptor = "[Lclient!na;")
	public static final JagString[] stringStack = new JagString[1000];

	@OriginalMember(owner = "client!fe", name = "nc", descriptor = "[Lclient!hj;")
	public static final ClientGosubFrame[] frames = new ClientGosubFrame[50];

	@OriginalMember(owner = "client!oe", name = "i", descriptor = "[[I")
	public static final int[][] arrays = new int[5][5000];

	@OriginalMember(owner = "client!ee", name = "j", descriptor = "[I")
	public static final int[] arrayLengths = new int[5];

	@OriginalMember(owner = "client!ob", name = "p", descriptor = "Lclient!na;")
	public static final JagString AUTO_NON_EXISTANT = JagString.wrap("(U0a )2 non)2existant gosub script)2num: ");

	@OriginalMember(owner = "client!da", name = "O", descriptor = "Lclient!na;")
	public static final JagString AUTO_CS_VIA = JagString.wrap("(U0a )2 via: ");

	@OriginalMember(owner = "client!rl", name = "eb", descriptor = "Lclient!na;")
	public static final JagString AUTO_CS_IN = JagString.wrap("(U0a )2 in: ");

	@OriginalMember(owner = "client!fl", name = "Q", descriptor = "Lclient!na;")
	public static final JagString AUTO_EMPTY = JagString.wrap("");

	@OriginalMember(owner = "client!kk", name = "m", descriptor = "Lclient!na;")
	public static final JagString AUTO_CS_ERROR_LIVE = JagString.wrap("Clientscript error )2 check log for details");

	@OriginalMember(owner = "client!nd", name = "b", descriptor = "Lclient!na;")
	public static final JagString AUTO_CS_ERROR = JagString.wrap("Clientscript error in: ");
	@OriginalMember(owner = "client!ah", name = "r", descriptor = "Lclient!na;")
	public static final JagString aClass100_191 = Text.aClass100_189;

	@OriginalMember(owner = "client!ck", name = "T", descriptor = "Lclient!na;")
	private static final JagString AUTO_JAN = JagString.wrap("Jan");

	@OriginalMember(owner = "client!ck", name = "hb", descriptor = "Lclient!na;")
	private static final JagString AUTO_FEB = JagString.wrap("Feb");

	@OriginalMember(owner = "client!ck", name = "gb", descriptor = "Lclient!na;")
	private static final JagString AUTO_MAR = JagString.wrap("Mar");

	@OriginalMember(owner = "client!ck", name = "db", descriptor = "Lclient!na;")
	private static final JagString AUTO_APR = JagString.wrap("Apr");

	@OriginalMember(owner = "client!ck", name = "n", descriptor = "Lclient!na;")
	private static final JagString AUTO_MAY = JagString.wrap("May");

	@OriginalMember(owner = "client!ck", name = "L", descriptor = "Lclient!na;")
	private static final JagString AUTO_JUN = JagString.wrap("Jun");

	@OriginalMember(owner = "client!ck", name = "B", descriptor = "Lclient!na;")
	private static final JagString AUTO_JUL = JagString.wrap("Jul");

	@OriginalMember(owner = "client!ck", name = "l", descriptor = "Lclient!na;")
	private static final JagString AUTO_AUG = JagString.wrap("Aug");

	@OriginalMember(owner = "client!ck", name = "V", descriptor = "Lclient!na;")
	private static final JagString AUTO_SEP = JagString.wrap("Sep");

	@OriginalMember(owner = "client!ck", name = "S", descriptor = "Lclient!na;")
	private static final JagString AUTO_OCT = JagString.wrap("Oct");

	@OriginalMember(owner = "client!ck", name = "Y", descriptor = "Lclient!na;")
	private static final JagString AUTO_NOV = JagString.wrap("Nov");

	@OriginalMember(owner = "client!ck", name = "O", descriptor = "Lclient!na;")
	private static final JagString AUTO_DEC = JagString.wrap("Dec");

	@OriginalMember(owner = "client!ck", name = "f", descriptor = "[Lclient!na;")
	public static final JagString[] months = new JagString[] {
		AUTO_JAN, AUTO_FEB, AUTO_MAR,
		AUTO_APR, AUTO_MAY, AUTO_JUN,
		AUTO_JUL, AUTO_AUG, AUTO_SEP,
		AUTO_OCT, AUTO_NOV, AUTO_DEC
	};

	@OriginalMember(owner = "client!km", name = "ad", descriptor = "I")
	public static int fp = 0;

	@OriginalMember(owner = "client!rh", name = "a", descriptor = "[I")
	public static int[] intLocals;

	@OriginalMember(owner = "client!og", name = "g", descriptor = "[Lclient!na;")
	public static JagString[] stringLocals;

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BILclient!jl;)V")
	public static void executeScript(@OriginalArg(1) int arg0, @OriginalArg(2) HookReq req) {
		@Pc(4) Object[] onop = req.onop;
		@Pc(10) int local10 = (Integer) onop[0];
		@Pc(14) ClientScript script = ClientScript.get(local10);
		if (script == null) {
			return;
		}

		fp = 0;

		@Pc(26) int ssp = 0;
		@Pc(28) int isp = 0;
		@Pc(30) int pc = -1;
		@Pc(33) int[] intOperands = script.intOperands;
		@Pc(36) int[] instructions = script.instructions;
		@Pc(44) byte lastOp = -1;
		@Pc(58) int opcount;

		try {
			intLocals = new int[script.intLocalCount];
			@Pc(50) int intCount = 0;

			stringLocals = new JagString[script.stringLocalCount];
			@Pc(56) int stringCount = 0;

			@Pc(77) int local77;
			@Pc(194) JagString local194;
			for (opcount = 1; opcount < onop.length; opcount++) {
				if (onop[opcount] instanceof Integer) {
					local77 = (Integer) onop[opcount];
					if (local77 == 0x80000001) {
						local77 = req.mouseX;
					}
					if (local77 == 0x80000002) {
						local77 = req.mouseY;
					}
					if (local77 == 0x80000003) {
						local77 = req.component == null ? -1 : req.component.parentId;
					}
					if (local77 == 0x80000004) {
						local77 = req.opindex;
					}
					if (local77 == 0x80000005) {
						local77 = req.component == null ? -1 : req.component.subId;
					}
					if (local77 == 0x80000006) {
						local77 = req.drop == null ? -1 : req.drop.parentId;
					}
					if (local77 == 0x80000007) {
						local77 = req.drop == null ? -1 : req.drop.subId;
					}
					if (local77 == 0x80000008) {
						local77 = req.keyCode;
					}
					if (local77 == 0x80000009) {
						local77 = req.keyChar;
					}

					intLocals[intCount++] = local77;
				} else if (onop[opcount] instanceof JagString) {
					local194 = (JagString) onop[opcount];
					if (local194.equalsInner(EVENT_OPBASE)) {
						local194 = req.opbase;
					}

					stringLocals[stringCount++] = local194;
				}
			}

			opcount = 0;
			label4266: while (true) {
				opcount++;
				if (arg0 < opcount) {
					throw new RuntimeException("slow");
				}

				pc++;
				@Pc(226) int local226 = instructions[pc];
				@Pc(803) int local803;
				@Pc(652) int local652;
				@Pc(809) int local809;
				@Pc(609) JagString local609;
				@Pc(5294) ParamType local5294;
				@Pc(7566) boolean local7566;
				@Pc(2522) JagString local2522;

				if (local226 < 100) {
					if (local226 == 0) {
						// push_constant_int
						intStack[isp++] = intOperands[pc];
						continue;
					}
					if (local226 == 1) {
						// push_varp
						local77 = intOperands[pc];
						intStack[isp++] = VarCache.var[local77];
						continue;
					}
					if (local226 == 2) {
						// pop_varp
						local77 = intOperands[pc];
						isp--;
						Static148.method2766(local77, intStack[isp]);
						continue;
					}
					if (local226 == 3) {
						// push_constant_string
						stringStack[ssp++] = script.stringOperands[pc];
						continue;
					}
					if (local226 == 6) {
						// branch
						pc += intOperands[pc];
						continue;
					}
					if (local226 == 7) {
						// branch_not
						isp -= 2;
						if (intStack[isp] != intStack[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (local226 == 8) {
						// branch_equals
						isp -= 2;
						if (intStack[isp + 1] == intStack[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (local226 == 9) {
						// branch_less_than
						isp -= 2;
						if (intStack[isp] < intStack[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (local226 == 10) {
						// branch_greater_than
						isp -= 2;
						if (intStack[isp + 1] < intStack[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (local226 == 21) {
						// return
						if (fp == 0) {
							return;
						}
						@Pc(423) ClientGosubFrame local423 = frames[--fp];
						script = local423.script;
						intLocals = local423.intLocals;
						instructions = script.instructions;
						pc = local423.pc;
						stringLocals = local423.stringLocals;
						intOperands = script.intOperands;
						continue;
					}
					if (local226 == 25) {
						// push_varbit
						local77 = intOperands[pc];
						intStack[isp++] = VarCache.getVarbit(local77);
						continue;
					}
					if (local226 == 27) {
						// pop_varbit
						local77 = intOperands[pc];
						isp--;
						Static202.method3655(local77, intStack[isp]);
						continue;
					}
					if (local226 == 31) {
						// branch_less_than_or_equals
						isp -= 2;
						if (intStack[isp + 1] >= intStack[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (local226 == 32) {
						// branch_greater_than_or_equals
						isp -= 2;
						if (intStack[isp] >= intStack[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (local226 == 33) {
						// push_int_local
						intStack[isp++] = intLocals[intOperands[pc]];
						continue;
					}
					@Pc(555) int local555;
					if (local226 == 34) {
						// pop_int_local
						local555 = intOperands[pc];
						isp--;
						intLocals[local555] = intStack[isp];
						continue;
					}
					if (local226 == 35) {
						// push_string_local
						stringStack[ssp++] = stringLocals[intOperands[pc]];
						continue;
					}
					if (local226 == 36) {
						// pop_string_local
						local555 = intOperands[pc];
						ssp--;
						stringLocals[local555] = stringStack[ssp];
						continue;
					}
					if (local226 == 37) {
						// join_string
						local77 = intOperands[pc];
						ssp -= local77;
						local609 = JagString.join(ssp, local77, stringStack);
						stringStack[ssp++] = local609;
						continue;
					}
					if (local226 == 38) {
						// pop_int_discard
						isp--;
						continue;
					}
					if (local226 == 39) {
						// pop_string_discard
						ssp--;
						continue;
					}
					if (local226 == 40) {
						// gosub_with_params
						local77 = intOperands[pc];
						@Pc(642) ClientScript proc = ClientScript.get(local77);
						@Pc(646) int[] local646 = new int[proc.intLocalCount];
						@Pc(650) JagString[] local650 = new JagString[proc.stringLocalCount];
						for (local652 = 0; local652 < proc.intArgCount; local652++) {
							local646[local652] = intStack[local652 + isp - proc.intArgCount];
						}
						for (local652 = 0; local652 < proc.stringArgCount; local652++) {
							local650[local652] = stringStack[local652 + ssp - proc.stringArgCount];
						}
						isp -= proc.intArgCount;
						ssp -= proc.stringArgCount;
						@Pc(705) ClientGosubFrame local705 = new ClientGosubFrame();
						local705.stringLocals = stringLocals;
						local705.intLocals = intLocals;
						local705.pc = pc;
						local705.script = script;
						if (fp >= frames.length) {
							throw new RuntimeException();
						}
						script = proc;
						pc = -1;
						frames[fp++] = local705;
						intLocals = local646;
						intOperands = proc.intOperands;
						instructions = proc.instructions;
						stringLocals = local650;
						continue;
					}
					if (local226 == 42) {
						// push_varc_int
						intStack[isp++] = Client.varcInt[intOperands[pc]];
						continue;
					}
					if (local226 == 43) {
						// pop_varc_int
						local77 = intOperands[pc];
						isp--;
						Client.varcInt[local77] = intStack[isp];
						Static4.method24(local77);
						continue;
					}
					if (local226 == 44) {
						// define_array
						local77 = intOperands[pc] >> 16;
						isp--;
						local803 = intStack[isp];
						local809 = intOperands[pc] & 0xFFFF;
                        if (local803 < 0 || local803 > 5000) {
                            throw new RuntimeException();
                        }

                        arrayLengths[local77] = local803;

                        @Pc(828) byte local828 = -1;
                        if (local809 == 105) {
                            local828 = 0;
                        }

						for (int i = 0; i < local803; i++) {
							arrays[local77][i] = local828;
						}

						continue;
                    }
					if (local226 == 45) {
						// push_array_int
						local77 = intOperands[pc];
						isp--;
						local809 = intStack[isp];
                        if (local809 < 0 || local809 >= arrayLengths[local77]) {
                            throw new RuntimeException();
                        }
                        intStack[isp++] = arrays[local77][local809];
                        continue;
                    }
					if (local226 == 46) {
						// pop_array_int
						local77 = intOperands[pc];
						isp -= 2;
						local809 = intStack[isp];
                        if (local809 < 0 || local809 >= arrayLengths[local77]) {
                            throw new RuntimeException();
                        }
                        arrays[local77][local809] = intStack[isp + 1];
                        continue;
                    }
					if (local226 == 47) {
						// push_varc_str
						local194 = Static226.varcStr[intOperands[pc]];
						if (local194 == null) {
							local194 = Static254.aClass100_1061;
						}
						stringStack[ssp++] = local194;
						continue;
					}
					if (local226 == 48) {
						// pop_varc_str
						local77 = intOperands[pc];
						ssp--;
						Static226.varcStr[local77] = stringStack[ssp];
						Static89.method1840(local77);
						continue;
					}
					if (local226 == 51) {
						@Pc(992) HashTable local992 = script.aClass133Array1[intOperands[pc]];
						isp--;
						@Pc(1002) IntNode local1002 = (IntNode) local992.find((long) intStack[isp]);
						if (local1002 != null) {
							pc += local1002.anInt3141;
						}
						continue;
					}
				}

				@Pc(1020) boolean secondary;
				if (intOperands[pc] == 1) {
					secondary = true;
				} else {
					secondary = false;
				}

				@Pc(1182) IfType local1182;
				@Pc(1052) int local1052;
				@Pc(1063) IfType local1063;
				@Pc(1087) int local1087;
				@Pc(1256) IfType local1256;

				@Pc(1204) IfType local1204;
				@Pc(12388) boolean local12388;
				@Pc(1552) boolean local1552;
				@Pc(4859) int local4859;

				if (local226 < 300) {
					if (local226 == 100) {
						isp -= 3;
						local809 = intStack[isp];
						local803 = intStack[isp + 1];
						local1052 = intStack[isp + 2];
						if (local803 != 0) {
							local1063 = IfType.get(local809);
							if (local1063.subcomponents == null) {
								local1063.subcomponents = new IfType[local1052 + 1];
							}
							if (local1052 >= local1063.subcomponents.length) {
								@Pc(1085) IfType[] local1085 = new IfType[local1052 + 1];
								for (local1087 = 0; local1087 < local1063.subcomponents.length; local1087++) {
									local1085[local1087] = local1063.subcomponents[local1087];
								}
								local1063.subcomponents = local1085;
							}
							if (local1052 > 0 && local1063.subcomponents[local1052 - 1] == null) {
								throw new RuntimeException("Gap at:" + (local1052 - 1));
							}
							@Pc(1137) IfType local1137 = new IfType();
							local1137.v3 = true;
							local1137.subId = local1052;
							local1137.layerId = local1137.parentId = local1063.parentId;
							local1137.type = local803;
							local1063.subcomponents[local1052] = local1137;
							if (secondary) {
								Static274.aClass13_24 = local1137;
							} else {
								Static227.aClass13_25 = local1137;
							}
							Client.componentUpdated(local1063);
							continue;
						}
						throw new RuntimeException();
					}
					if (local226 == 101) {
						local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
						if (local1182.subId == -1) {
							if (!secondary) {
								throw new RuntimeException("Tried to cc_delete static active-component!");
							}
							throw new RuntimeException("Tried to .cc_delete static .active-component!");
						}
						local1204 = IfType.get(local1182.parentId);
						local1204.subcomponents[local1182.subId] = null;
						Client.componentUpdated(local1204);
						continue;
					}
					if (local226 == 102) {
						isp--;
						local1182 = IfType.get(intStack[isp]);
						local1182.subcomponents = null;
						Client.componentUpdated(local1182);
						continue;
					}
					if (local226 == 200) {
						isp -= 2;
						local809 = intStack[isp];
						local803 = intStack[isp + 1];
						local1256 = Static201.method1418(local809, local803);
						if (local1256 != null && local803 != -1) {
							intStack[isp++] = 1;
							if (secondary) {
								Static274.aClass13_24 = local1256;
							} else {
								Static227.aClass13_25 = local1256;
							}
							continue;
						}
						intStack[isp++] = 0;
						continue;
					}
					if (local226 == 201) {
						isp--;
						local809 = intStack[isp];
						local1204 = IfType.get(local809);
						if (local1204 == null) {
							intStack[isp++] = 0;
						} else {
							intStack[isp++] = 1;
							if (secondary) {
								Static274.aClass13_24 = local1204;
							} else {
								Static227.aClass13_25 = local1204;
							}
						}
						continue;
					}
				} else if (local226 < 500) {
                    if (local226 == 403) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        for (local1052 = 0; local1052 < Static204.anIntArray425.length; local1052++) {
                            if (local809 == Static204.anIntArray425[local1052]) {
                                Client.localPlayer.aClass59_1.method1953(local1052, local803);
                                continue label4266;
                            }
                        }
                        local1052 = 0;
                        while (true) {
                            if (local1052 >= Static153.anIntArray351.length) {
                                continue label4266;
                            }
                            if (local809 == Static153.anIntArray351[local1052]) {
                                Client.localPlayer.aClass59_1.method1953(local1052, local803);
                                continue label4266;
                            }
                            local1052++;
                        }
                    }
                    if (local226 == 404) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        Client.localPlayer.aClass59_1.method1951(local809, local803);
                        continue;
                    }
                    if (local226 == 410) {
                        isp--;
                        local12388 = intStack[isp] != 0;
                        Client.localPlayer.aClass59_1.method1948(local12388);
                        continue;
                    }
                } else if ((local226 >= 1000 && local226 < 1100) || (local226 >= 2000 && local226 < 2100)) {
                    if (local226 < 2000) {
                        local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    } else {
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                        local226 -= 1000;
                    }
                    if (local226 == 1000) {
                        isp -= 4;
                        local1182.dataX = intStack[isp];
                        local1182.dataY = intStack[isp + 1];
                        local1052 = intStack[isp + 3];
                        if (local1052 < 0) {
                            local1052 = 0;
                        } else if (local1052 > 5) {
                            local1052 = 5;
                        }
                        local803 = intStack[isp + 2];
                        if (local803 < 0) {
                            local803 = 0;
                        } else if (local803 > 5) {
                            local803 = 5;
                        }
                        local1182.aByte2 = (byte) local1052;
                        local1182.aByte4 = (byte) local803;
                        Client.componentUpdated(local1182);
                        Static74.method1625(local1182);
                        if (local1182.subId == -1) {
                            Static280.method4675(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1001) {
                        isp -= 4;
                        local1182.width = intStack[isp];
                        local1182.height = intStack[isp + 1];
                        local1182.anInt451 = 0;
                        local1182.anInt526 = 0;
                        local803 = intStack[isp + 2];
                        local1052 = intStack[isp + 3];
                        if (local1052 < 0) {
                            local1052 = 0;
                        } else if (local1052 > 4) {
                            local1052 = 4;
                        }
                        local1182.aByte3 = (byte) local1052;
                        if (local803 < 0) {
                            local803 = 0;
                        } else if (local803 > 4) {
                            local803 = 4;
                        }
                        local1182.aByte5 = (byte) local803;
                        Client.componentUpdated(local1182);
                        Static74.method1625(local1182);
                        if (local1182.type == 0) {
                            Client.method531(local1182, false);
                        }
                        continue;
                    }
                    if (local226 == 1003) {
                        isp--;
                        local1552 = intStack[isp] == 1;
                        if (local1552 != local1182.hide) {
                            local1182.hide = local1552;
                            Client.componentUpdated(local1182);
                        }
                        if (local1182.subId == -1) {
                            Static93.method1906(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1004) {
                        isp -= 2;
                        local1182.anInt473 = intStack[isp];
                        local1182.anInt442 = intStack[isp + 1];
                        Client.componentUpdated(local1182);
                        Static74.method1625(local1182);
                        if (local1182.type == 0) {
                            Client.method531(local1182, false);
                        }
                        continue;
                    }
                    if (local226 == 1005) {
                        isp--;
                        local1182.aBoolean29 = intStack[isp] == 1;
                        continue;
                    }
                } else if (local226 >= 1100 && local226 < 1200 || !(local226 < 2100 || local226 >= 2200)) {
                    if (local226 < 2000) {
                        local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    } else {
                        local226 -= 1000;
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                    }
                    if (local226 == 1100) {
                        isp -= 2;
                        local1182.anInt489 = intStack[isp];
                        if (local1182.anInt489 > local1182.scrollWidth - local1182.anInt445) {
                            local1182.anInt489 = local1182.scrollWidth - local1182.anInt445;
                        }
                        if (local1182.anInt489 < 0) {
                            local1182.anInt489 = 0;
                        }
                        local1182.anInt468 = intStack[isp + 1];
                        if (local1182.anInt468 > local1182.scrollHeight - local1182.anInt459) {
                            local1182.anInt468 = local1182.scrollHeight - local1182.anInt459;
                        }
                        if (local1182.anInt468 < 0) {
                            local1182.anInt468 = 0;
                        }
                        Client.componentUpdated(local1182);
                        if (local1182.subId == -1) {
                            Static118.method2353(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1101) {
                        isp--;
                        local1182.colour = intStack[isp];
                        Client.componentUpdated(local1182);
                        if (local1182.subId == -1) {
                            Static245.method4224(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1102) {
                        isp--;
                        local1182.fill = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1103) {
                        isp--;
                        local1182.trans = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1104) {
                        isp--;
                        local1182.lineWidth = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1105) {
                        isp--;
                        local1182.graphic = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1106) {
                        isp--;
                        local1182.rotate = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1107) {
                        isp--;
                        local1182.tiling = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1108) {
                        local1182.model1Type = 1;
                        isp--;
                        local1182.model1Id = intStack[isp];
                        Client.componentUpdated(local1182);
                        if (local1182.subId == -1) {
                            Static271.method4600(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1109) {
                        isp -= 6;
                        local1182.anInt494 = intStack[isp];
                        local1182.anInt497 = intStack[isp + 1];
                        local1182.modelXAn = intStack[isp + 2];
                        local1182.modelYAn = intStack[isp + 3];
                        local1182.modelZAn = intStack[isp + 4];
                        local1182.modelZoom = intStack[isp + 5];
                        Client.componentUpdated(local1182);
                        if (local1182.subId == -1) {
                            Static153.method2910(local1182.parentId);
                            Static180.method3328(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1110) {
                        isp--;
                        local803 = intStack[isp];
                        if (local1182.modelAnim != local803) {
                            local1182.modelAnim = local803;
                            local1182.anInt510 = 0;
                            local1182.anInt500 = 0;
                            local1182.anInt496 = 1;
                            Client.componentUpdated(local1182);
                        }
                        if (local1182.subId == -1) {
                            Static181.method3345(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1111) {
                        isp--;
                        local1182.orthog = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1112) {
                        ssp--;
                        local2522 = stringStack[ssp];
                        if (!local2522.equalsInner(local1182.text)) {
                            local1182.text = local2522;
                            Client.componentUpdated(local1182);
                        }
                        if (local1182.subId == -1) {
                            Static163.method3096(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1113) {
                        isp--;
                        local1182.font = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1114) {
                        isp -= 3;
                        local1182.hAlign = intStack[isp];
                        local1182.vAlign = intStack[isp + 1];
                        local1182.lineHeight = intStack[isp + 2];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1115) {
                        isp--;
                        local1182.shadow = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1116) {
                        isp--;
                        local1182.outline = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1117) {
                        isp--;
                        local1182.shadowColour = intStack[isp];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1118) {
                        isp--;
                        local1182.vFlip = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1119) {
                        isp--;
                        local1182.hFlip = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1120) {
                        isp -= 2;
                        local1182.scrollWidth = intStack[isp];
                        local1182.scrollHeight = intStack[isp + 1];
                        Client.componentUpdated(local1182);
                        if (local1182.type == 0) {
                            Client.method531(local1182, false);
                        }
                        continue;
                    }
                    if (local226 == 1121) {
                        isp -= 2;
                        local1182.aShort11 = (short) intStack[isp];
                        local1182.aShort10 = (short) intStack[isp + 1];
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1122) {
                        isp--;
                        local1182.alpha = intStack[isp] == 1;
                        Client.componentUpdated(local1182);
                        continue;
                    }
                    if (local226 == 1123) {
                        isp--;
                        local1182.modelZoom = intStack[isp];
                        Client.componentUpdated(local1182);
                        if (local1182.subId == -1) {
                            Static153.method2910(local1182.parentId);
                        }
                        continue;
                    }
                } else if (local226 >= 1200 && local226 < 1300 || !(local226 < 2200 || local226 >= 2300)) {
                    if (local226 < 2000) {
                        local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    } else {
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                        local226 -= 1000;
                    }
                    Client.componentUpdated(local1182);
                    if (local226 == 1200 || local226 == 1205) {
                        isp -= 2;
                        local1052 = intStack[isp + 1];
                        local803 = intStack[isp];
                        if (local1182.subId == -1) {
                            Static251.method4279(local1182.parentId);
                            Static153.method2910(local1182.parentId);
                            Static180.method3328(local1182.parentId);
                        }
                        if (local803 == -1) {
                            local1182.model1Id = -1;
                            local1182.model1Type = 1;
                            local1182.anInt458 = -1;
                        } else {
                            local1182.anInt458 = local803;
                            local1182.anInt503 = local1052;
                            @Pc(13416) ObjType local13416 = ObjType.list(local803);
                            local1182.modelZAn = local13416.anInt2339;
                            local1182.anInt494 = local13416.anInt2359;
                            local1182.modelXAn = local13416.anInt2353;
                            local1182.anInt497 = local13416.anInt2319;
                            local1182.modelYAn = local13416.anInt2369;
                            local1182.modelZoom = local13416.anInt2375;
                            if (local1182.anInt451 > 0) {
                                local1182.modelZoom = local1182.modelZoom * 32 / local1182.anInt451;
                            } else if (local1182.width > 0) {
                                local1182.modelZoom = local1182.modelZoom * 32 / local1182.width;
                            }
                            if (local226 == 1205) {
                                local1182.aBoolean31 = false;
                            } else {
                                local1182.aBoolean31 = true;
                            }
                        }
                        continue;
                    }
                    if (local226 == 1201) {
                        local1182.model1Type = 2;
                        isp--;
                        local1182.model1Id = intStack[isp];
                        if (local1182.subId == -1) {
                            Static271.method4600(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1202) {
                        local1182.model1Type = 3;
                        local1182.model1Id = Client.localPlayer.aClass59_1.method1952();
                        if (local1182.subId == -1) {
                            Static271.method4600(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1203) {
                        local1182.model1Type = 6;
                        isp--;
                        local1182.model1Id = intStack[isp];
                        if (local1182.subId == -1) {
                            Static271.method4600(local1182.parentId);
                        }
                        continue;
                    }
                    if (local226 == 1204) {
                        local1182.model1Type = 5;
                        isp--;
                        local1182.model1Id = intStack[isp];
                        if (local1182.subId == -1) {
                            Static271.method4600(local1182.parentId);
                        }
                        continue;
                    }
                } else if (local226 >= 1300 && local226 < 1400 || local226 >= 2300 && local226 < 2400) {
                    if (local226 >= 2000) {
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                        local226 -= 1000;
                    } else {
                        local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    }
                    if (local226 == 1300) {
                        isp--;
                        local803 = intStack[isp] - 1;
                        if (local803 >= 0 && local803 <= 9) {
                            ssp--;
                            local1182.method480(stringStack[ssp], local803);
                            continue;
                        }
                        ssp--;
                        continue;
                    }
                    if (local226 == 1301) {
                        isp -= 2;
                        local1052 = intStack[isp + 1];
                        local803 = intStack[isp];
                        local1182.aClass13_5 = Static201.method1418(local803, local1052);
                        continue;
                    }
                    if (local226 == 1302) {
                        isp--;
                        local1182.draggablebehavior = intStack[isp] == 1;
                        continue;
                    }
                    if (local226 == 1303) {
                        isp--;
                        local1182.dragdeadzone = intStack[isp];
                        continue;
                    }
                    if (local226 == 1304) {
                        isp--;
                        local1182.dragdeadtime = intStack[isp];
                        continue;
                    }
                    if (local226 == 1305) {
                        ssp--;
                        local1182.aClass100_88 = stringStack[ssp];
                        continue;
                    }
                    if (local226 == 1306) {
                        ssp--;
                        local1182.targetVerb = stringStack[ssp];
                        continue;
                    }
                    if (local226 == 1307) {
                        local1182.aClass100Array18 = null;
                        continue;
                    }
                    if (local226 == 1308) {
                        isp--;
                        local1182.anInt484 = intStack[isp];
                        isp--;
                        local1182.anInt499 = intStack[isp];
                        continue;
                    }
                    if (local226 == 1309) {
                        isp--;
                        local803 = intStack[isp];
                        isp--;
                        local1052 = intStack[isp];
                        if (local1052 >= 1 && local1052 <= 10) {
                            local1182.method477(local1052 - 1, local803);
                        }
                        continue;
                    }
                } else if (local226 >= 1400 && local226 < 1500 || local226 >= 2400 && local226 < 2500) {
                    if (local226 < 2000) {
                        local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    } else {
                        local226 -= 1000;
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                    }
                    @Pc(12937) int[] local12937 = null;
                    ssp--;
                    local2522 = stringStack[ssp];
                    if (local2522.length() > 0 && local2522.method3149(local2522.length() - 1) == 89) {
                        isp--;
                        local652 = intStack[isp];
                        if (local652 > 0) {
                            local12937 = new int[local652];
                            while (local652-- > 0) {
                                isp--;
                                local12937[local652] = intStack[isp];
                            }
                        }
                        local2522 = local2522.method3137(local2522.length() - 1, 0);
                    }
                    @Pc(13000) Object[] local13000 = new Object[local2522.length() + 1];
                    for (local4859 = local13000.length - 1; local4859 >= 1; local4859--) {
                        if (local2522.method3149(local4859 - 1) == 115) {
                            ssp--;
                            local13000[local4859] = stringStack[ssp];
                        } else {
                            isp--;
                            local13000[local4859] = Integer.valueOf(intStack[isp]);
                        }
                    }
                    isp--;
                    local4859 = intStack[isp];
                    if (local4859 == -1) {
                        local13000 = null;
                    } else {
                        local13000[0] = Integer.valueOf(local4859);
                    }
                    local1182.aBoolean25 = true;
                    if (local226 == 1400) {
                        local1182.onclick = local13000;
                    } else if (local226 == 1401) {
                        local1182.onhold = local13000;
                    } else if (local226 == 1402) {
                        local1182.onrelease = local13000;
                    } else if (local226 == 1403) {
                        local1182.onmouseover = local13000;
                    } else if (local226 == 1404) {
                        local1182.onmouseleave = local13000;
                    } else if (local226 == 1405) {
                        local1182.ondrag = local13000;
                    } else if (local226 == 1406) {
                        local1182.ontargetleave = local13000;
                    } else if (local226 == 1407) {
                        local1182.onvartransmitlist = local12937;
                        local1182.onvartransmit = local13000;
                    } else if (local226 == 1408) {
                        local1182.ontimer = local13000;
                    } else if (local226 == 1409) {
                        local1182.onop = local13000;
                    } else if (local226 == 1410) {
                        local1182.ondragcomplete = local13000;
                    } else if (local226 == 1411) {
                        local1182.onclickrepeat = local13000;
                    } else if (local226 == 1412) {
                        local1182.onmouserepeat = local13000;
                    } else if (local226 == 1414) {
                        local1182.oninvtransmitlist = local12937;
                        local1182.oninvtransmit = local13000;
                    } else if (local226 == 1415) {
                        local1182.onstattransmitlist = local12937;
                        local1182.onstattransmit = local13000;
                    } else if (local226 == 1416) {
                        local1182.ontargetenter = local13000;
                    } else if (local226 == 1417) {
                        local1182.onscrollwheel = local13000;
                    } else if (local226 == 1418) {
                        local1182.anObjectArray20 = local13000;
                    } else if (local226 == 1419) {
                        local1182.onkey = local13000;
                    } else if (local226 == 1420) {
                        local1182.anObjectArray1 = local13000;
                    } else if (local226 == 1421) {
                        local1182.anObjectArray28 = local13000;
                    } else if (local226 == 1422) {
                        local1182.anObjectArray30 = local13000;
                    } else if (local226 == 1423) {
                        local1182.anObjectArray12 = local13000;
                    } else if (local226 == 1424) {
                        local1182.anObjectArray8 = local13000;
                    } else if (local226 == 1425) {
                        local1182.anObjectArray21 = local13000;
                    } else if (local226 == 1426) {
                        local1182.anObjectArray13 = local13000;
                    } else if (local226 == 1427) {
                        local1182.anObjectArray17 = local13000;
                    } else if (local226 == 1428) {
                        local1182.onvarctransmit = local13000;
                        local1182.onvarctransmitlist = local12937;
                    } else if (local226 == 1429) {
                        local1182.onvarcstrtransmitlist = local12937;
                        local1182.onvarcstrtransmit = local13000;
                    }
                    continue;
                } else if (local226 < 1600) {
                    local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    if (local226 == 1500) {
                        intStack[isp++] = local1182.anInt523;
                        continue;
                    }
                    if (local226 == 1501) {
                        intStack[isp++] = local1182.anInt469;
                        continue;
                    }
                    if (local226 == 1502) {
                        intStack[isp++] = local1182.anInt445;
                        continue;
                    }
                    if (local226 == 1503) {
                        intStack[isp++] = local1182.anInt459;
                        continue;
                    }
                    if (local226 == 1504) {
                        intStack[isp++] = local1182.hide ? 1 : 0;
                        continue;
                    }
                    if (local226 == 1505) {
                        intStack[isp++] = local1182.layerId;
                        continue;
                    }
                } else if (local226 < 1700) {
                    local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    if (local226 == 1600) {
                        intStack[isp++] = local1182.anInt489;
                        continue;
                    }
                    if (local226 == 1601) {
                        intStack[isp++] = local1182.anInt468;
                        continue;
                    }
                    if (local226 == 1602) {
                        stringStack[ssp++] = local1182.text;
                        continue;
                    }
                    if (local226 == 1603) {
                        intStack[isp++] = local1182.scrollWidth;
                        continue;
                    }
                    if (local226 == 1604) {
                        intStack[isp++] = local1182.scrollHeight;
                        continue;
                    }
                    if (local226 == 1605) {
                        intStack[isp++] = local1182.modelZoom;
                        continue;
                    }
                    if (local226 == 1606) {
                        intStack[isp++] = local1182.modelXAn;
                        continue;
                    }
                    if (local226 == 1607) {
                        intStack[isp++] = local1182.modelZAn;
                        continue;
                    }
                    if (local226 == 1608) {
                        intStack[isp++] = local1182.modelYAn;
                        continue;
                    }
                    if (local226 == 1609) {
                        intStack[isp++] = local1182.trans;
                        continue;
                    }
                    if (local226 == 1610) {
                        intStack[isp++] = local1182.anInt494;
                        continue;
                    }
                    if (local226 == 1611) {
                        intStack[isp++] = local1182.anInt497;
                        continue;
                    }
                    if (local226 == 1612) {
                        intStack[isp++] = local1182.graphic;
                        continue;
                    }
                } else if (local226 < 1800) {
                    local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    if (local226 == 1700) {
                        intStack[isp++] = local1182.anInt458;
                        continue;
                    }
                    if (local226 == 1701) {
                        if (local1182.anInt458 == -1) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local1182.anInt503;
                        }
                        continue;
                    }
                    if (local226 == 1702) {
                        intStack[isp++] = local1182.subId;
                        continue;
                    }
                } else if (local226 < 1900) {
                    local1182 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                    if (local226 == 1800) {
                        intStack[isp++] = Client.method940(local1182).method512();
                        continue;
                    }
                    if (local226 == 1801) {
                        isp--;
                        local803 = intStack[isp];
                        local803--;
                        if (local1182.aClass100Array18 != null && local803 < local1182.aClass100Array18.length && local1182.aClass100Array18[local803] != null) {
                            stringStack[ssp++] = local1182.aClass100Array18[local803];
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 1802) {
                        if (local1182.aClass100_88 == null) {
                            stringStack[ssp++] = AUTO_EMPTY;
                        } else {
                            stringStack[ssp++] = local1182.aClass100_88;
                        }
                        continue;
                    }
                } else if (local226 < 2600) {
                    isp--;
                    local1182 = IfType.get(intStack[isp]);
                    if (local226 == 2500) {
                        intStack[isp++] = local1182.anInt523;
                        continue;
                    }
                    if (local226 == 2501) {
                        intStack[isp++] = local1182.anInt469;
                        continue;
                    }
                    if (local226 == 2502) {
                        intStack[isp++] = local1182.anInt445;
                        continue;
                    }
                    if (local226 == 2503) {
                        intStack[isp++] = local1182.anInt459;
                        continue;
                    }
                    if (local226 == 2504) {
                        intStack[isp++] = local1182.hide ? 1 : 0;
                        continue;
                    }
                    if (local226 == 2505) {
                        intStack[isp++] = local1182.layerId;
                        continue;
                    }
                } else if (local226 < 2700) {
                    isp--;
                    local1182 = IfType.get(intStack[isp]);
                    if (local226 == 2600) {
                        intStack[isp++] = local1182.anInt489;
                        continue;
                    }
                    if (local226 == 2601) {
                        intStack[isp++] = local1182.anInt468;
                        continue;
                    }
                    if (local226 == 2602) {
                        stringStack[ssp++] = local1182.text;
                        continue;
                    }
                    if (local226 == 2603) {
                        intStack[isp++] = local1182.scrollWidth;
                        continue;
                    }
                    if (local226 == 2604) {
                        intStack[isp++] = local1182.scrollHeight;
                        continue;
                    }
                    if (local226 == 2605) {
                        intStack[isp++] = local1182.modelZoom;
                        continue;
                    }
                    if (local226 == 2606) {
                        intStack[isp++] = local1182.modelXAn;
                        continue;
                    }
                    if (local226 == 2607) {
                        intStack[isp++] = local1182.modelZAn;
                        continue;
                    }
                    if (local226 == 2608) {
                        intStack[isp++] = local1182.modelYAn;
                        continue;
                    }
                    if (local226 == 2609) {
                        intStack[isp++] = local1182.trans;
                        continue;
                    }
                    if (local226 == 2610) {
                        intStack[isp++] = local1182.anInt494;
                        continue;
                    }
                    if (local226 == 2611) {
                        intStack[isp++] = local1182.anInt497;
                        continue;
                    }
                    if (local226 == 2612) {
                        intStack[isp++] = local1182.graphic;
                        continue;
                    }
                } else if (local226 < 2800) {
                    if (local226 == 2700) {
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                        intStack[isp++] = local1182.anInt458;
                        continue;
                    }
                    if (local226 == 2701) {
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                        if (local1182.anInt458 == -1) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local1182.anInt503;
                        }
                        continue;
                    }
                    if (local226 == 2702) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(12566) SubInterface local12566 = (SubInterface) BgSound.aClass133_9.find((long) local809);
                        if (local12566 == null) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = 1;
                        }
                        continue;
                    }
                    if (local226 == 2703) {
                        isp--;
                        local1182 = IfType.get(intStack[isp]);
                        if (local1182.subcomponents == null) {
                            intStack[isp++] = 0;
                            continue;
                        }
                        local803 = local1182.subcomponents.length;
                        for (local1052 = 0; local1052 < local1182.subcomponents.length; local1052++) {
                            if (local1182.subcomponents[local1052] == null) {
                                local803 = local1052;
                                break;
                            }
                        }
                        intStack[isp++] = local803;
                        continue;
                    }
                    if (local226 == 2704 || local226 == 2705) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        @Pc(12663) SubInterface local12663 = (SubInterface) BgSound.aClass133_9.find((long) local809);
                        if (local12663 != null && local12663.anInt5878 == local803) {
                            intStack[isp++] = 1;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                } else if (local226 < 2900) {
                    isp--;
                    local1182 = IfType.get(intStack[isp]);
                    if (local226 == 2800) {
                        intStack[isp++] = Client.method940(local1182).method512();
                        continue;
                    }
                    if (local226 == 2801) {
                        isp--;
                        local803 = intStack[isp];
                        local803--;
                        if (local1182.aClass100Array18 != null && local1182.aClass100Array18.length > local803 && local1182.aClass100Array18[local803] != null) {
                            stringStack[ssp++] = local1182.aClass100Array18[local803];
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 2802) {
                        if (local1182.aClass100_88 == null) {
                            stringStack[ssp++] = AUTO_EMPTY;
                        } else {
                            stringStack[ssp++] = local1182.aClass100_88;
                        }
                        continue;
                    }
                } else if (local226 < 3200) {
                    if (local226 == 3100) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.addChat(AUTO_EMPTY, 0, local609);
                        continue;
                    }
                    if (local226 == 3101) {
                        isp -= 2;
                        Client.triggerPlayerAnim(intStack[isp + 1], intStack[isp], Client.localPlayer);
                        continue;
                    }
                    if (local226 == 3103) {
                        Static153.method2909();
                        continue;
                    }
                    if (local226 == 3104) {
                        ssp--;
                        local609 = stringStack[ssp];
                        local803 = 0;
                        if (local609.method3123()) {
                            local803 = local609.method3132();
                        }
                        Client.out.p1Enc(23);
                        Client.out.p4(local803);
                        continue;
                    }
                    if (local226 == 3105) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.out.p1Enc(244);
                        Client.out.p8(local609.method3158());
                        continue;
                    }
                    if (local226 == 3106) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.out.p1Enc(65);
                        Client.out.p1(local609.length() + 1);
                        Client.out.pjstr(local609);
                        continue;
                    }
                    if (local226 == 3107) {
                        isp--;
                        local809 = intStack[isp];
                        ssp--;
                        local2522 = stringStack[ssp];
                        Static276.method4613(local809, local2522);
                        continue;
                    }
                    if (local226 == 3108) {
                        isp -= 3;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        local1052 = intStack[isp + 2];
                        local1063 = IfType.get(local1052);
                        Static40.method1015(local803, local809, local1063);
                        continue;
                    }
                    if (local226 == 3109) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local1256 = secondary ? Static274.aClass13_24 : Static227.aClass13_25;
                        local803 = intStack[isp + 1];
                        Static40.method1015(local803, local809, local1256);
                        continue;
                    }
                    if (local226 == 3110) {
                        isp--;
                        local809 = intStack[isp];
                        Client.out.p1Enc(111);
                        Client.out.p2(local809);
                        continue;
                    }
                } else if (local226 < 3300) {
                    if (local226 == 3200) {
                        isp -= 3;
                        Client.method744(intStack[isp + 1], intStack[isp], intStack[isp + 2]);
                        continue;
                    }
                    if (local226 == 3201) {
                        isp--;
                        Static148.method2765(intStack[isp]);
                        continue;
                    }
                    if (local226 == 3202) {
                        isp -= 2;
                        Client.method4650(intStack[isp + 1], intStack[isp]);
                        continue;
                    }
                } else if (local226 < 3400) {
                    if (local226 == 3300) {
                        // clientclock
                        intStack[isp++] = Client.loopCycle;
                        continue;
                    }
                    if (local226 == 3301) {
                        // inv_getobj
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = ClientInvCache.getType(local809, local803);
                        continue;
                    }
                    if (local226 == 3302) {
                        // inv_getnum
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = ClientInvCache.getCount(local809, local803);
                        continue;
                    }
                    if (local226 == 3303) {
                        // inv_total
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = ClientInvCache.invTotal(local809, local803);
                        continue;
                    }
                    if (local226 == 3304) {
                        // inv_size
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = InvType.list(local809).size;
                        continue;
                    }
                    if (local226 == 3305) {
                        // stat
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Client.statEffectiveLevel[local809];
                        continue;
                    }
                    if (local226 == 3306) {
                        // stat_base
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Client.statBaseLevel[local809];
                        continue;
                    }
                    if (local226 == 3307) {
                        // stat_xp
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Client.statXP[local809];
                        continue;
                    }
                    if (local226 == 3308) {
                        // coord
                        local809 = Client.minusedlevel;
                        local803 = Client.mapBuildBaseX + (Client.localPlayer.x >> 7);
                        local1052 = (Client.localPlayer.z >> 7) + Client.mapBuildBaseZ;
                        intStack[isp++] = (local809 << 28) - (-(local803 << 14) - local1052);
                        continue;
                    }
                    if (local226 == 3309) {
                        // coordx
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = local809 >> 14 & 0x3FFF;
                        continue;
                    }
                    if (local226 == 3310) {
                        // coordy
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = local809 >> 28;
                        continue;
                    }
                    if (local226 == 3311) {
                        // coordz
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = local809 & 0x3FFF;
                        continue;
                    }
                    if (local226 == 3312) {
                        // map_members
                        intStack[isp++] = Client.memServer ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3313) {
                        // invother_getobj
                        isp -= 2;
                        local809 = intStack[isp] + 32768;
                        local803 = intStack[isp + 1];
                        intStack[isp++] = ClientInvCache.getType(local809, local803);
                        continue;
                    }
                    if (local226 == 3314) {
                        // invother_getnum
                        isp -= 2;
                        local809 = intStack[isp] + 32768;
                        local803 = intStack[isp + 1];
                        intStack[isp++] = ClientInvCache.getCount(local809, local803);
                        continue;
                    }
                    if (local226 == 3315) {
                        // invother_total
                        isp -= 2;
                        local809 = intStack[isp] + 32768;
                        local803 = intStack[isp + 1];
                        intStack[isp++] = ClientInvCache.invTotal(local809, local803);
                        continue;
                    }
                    if (local226 == 3316) {
                        // staffmodlevel
                        if (Client.anInt4502 < 2) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = Client.anInt4502;
                        }
                        continue;
                    }
                    if (local226 == 3317) {
                        // reboottimer
                        intStack[isp++] = Client.rebootTimer;
                        continue;
                    }
                    if (local226 == 3318) {
                        // map_world
                        intStack[isp++] = Client.anInt3103;
                        continue;
                    }
                    if (local226 == 3321) {
                        // runenergy_visible
                        intStack[isp++] = Client.runEnergy;
                        continue;
                    }
                    if (local226 == 3322) {
                        // runweight_visible
                        intStack[isp++] = Client.runWeight;
                        continue;
                    }
                    if (local226 == 3323) {
                        // playermod
                        if (Client.anInt5431 >= 5 && Client.anInt5431 <= 9) {
                            intStack[isp++] = 1;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3324) {
                        if (Client.anInt5431 >= 5 && Client.anInt5431 <= 9) {
                            intStack[isp++] = Client.anInt5431;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3325) {
                        intStack[isp++] = Client.aBoolean233 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3326) {
                        intStack[isp++] = Client.localPlayer.combatLevel;
                        continue;
                    }
                    if (local226 == 3327) {
                        intStack[isp++] = Client.localPlayer.aClass59_1.aBoolean141 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3328) {
                        intStack[isp++] = Client.aBoolean157 && !Client.aBoolean236 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3329) {
                        intStack[isp++] = Client.aBoolean129 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3330) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = ClientInvCache.method446(local809);
                        continue;
                    }
                    if (local226 == 3331) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = ClientInvCache.method3319(false, local809, local803);
                        continue;
                    }
                    if (local226 == 3332) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = ClientInvCache.method3319(true, local809, local803);
                        continue;
                    }
                    if (local226 == 3333) {
                        intStack[isp++] = Static5.anInt39;
                        continue;
                    }
                    if (local226 == 3335) {
                        intStack[isp++] = Client.lang;
                        continue;
                    }
                    if (local226 == 3336) {
                        isp -= 4;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        local809 += local803 << 14;
                        local652 = intStack[isp + 3];
                        local1052 = intStack[isp + 2];
                        local809 += local1052 << 28;
                        local809 += local652;
                        intStack[isp++] = local809;
                        continue;
                    }
                    if (local226 == 3337) {
                        intStack[isp++] = Client.affid;
                        continue;
                    }
                } else if (local226 < 3500) {
                    @Pc(3422) EnumType local3422;
                    if (local226 == 3400) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local3422 = EnumType.list(local809);
                        if (local3422.anInt3950 == 115) {
                        }
                        stringStack[ssp++] = local3422.getString(local803);
                        continue;
                    }
                    if (local226 == 3408) {
                        isp -= 4;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local652 = intStack[isp + 3];
                        local1052 = intStack[isp + 2];
                        @Pc(3469) EnumType local3469 = EnumType.list(local1052);
                        if (local3469.anInt3957 == local809 && local3469.anInt3950 == local803) {
                            if (local803 == 115) {
                                stringStack[ssp++] = local3469.getString(local652);
                            } else {
                                intStack[isp++] = local3469.method3089(local652);
                            }
                            continue;
                        }
                        throw new RuntimeException("C3408-1");
                    }
                    if (local226 == 3409) {
                        isp -= 3;
                        local803 = intStack[isp + 1];
                        local1052 = intStack[isp + 2];
                        local809 = intStack[isp];
                        if (local803 == -1) {
                            throw new RuntimeException("C3409-2");
                        }
                        @Pc(3549) EnumType local3549 = EnumType.list(local803);
                        if (local3549.anInt3950 != local809) {
                            throw new RuntimeException("C3409-1");
                        }
                        intStack[isp++] = local3549.method3090(local1052) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3410) {
                        isp--;
                        local809 = intStack[isp];
                        ssp--;
                        local2522 = stringStack[ssp];
                        if (local809 == -1) {
                            throw new RuntimeException("C3410-2");
                        }
                        local3422 = EnumType.list(local809);
                        if (local3422.anInt3950 != 115) {
                            throw new RuntimeException("C3410-1");
                        }
                        intStack[isp++] = local3422.method3086(local2522) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3411) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(3645) EnumType local3645 = EnumType.list(local809);
                        intStack[isp++] = local3645.aClass133_16.method3864();
                        continue;
                    }
                } else if (local226 < 3700) {
                    if (local226 == 3600) {
                        if (Static166.anInt4054 == 0) {
                            intStack[isp++] = -2;
                        } else if (Static166.anInt4054 == 1) {
                            intStack[isp++] = -1;
                        } else {
                            intStack[isp++] = Static9.anInt178;
                        }
                        continue;
                    }
                    if (local226 == 3601) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static166.anInt4054 == 2 && local809 < Static9.anInt178) {
                            stringStack[ssp++] = Static122.aClass100Array92[local809];
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 3602) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static166.anInt4054 == 2 && Static9.anInt178 > local809) {
                            intStack[isp++] = Static104.anIntArray255[local809];
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3603) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static166.anInt4054 == 2 && Static9.anInt178 > local809) {
                            intStack[isp++] = Static106.anIntArray258[local809];
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3604) {
                        isp--;
                        local803 = intStack[isp];
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.method3221(local609, local803);
                        continue;
                    }
                    if (local226 == 3605) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.method1496(local609.method3158());
                        continue;
                    }
                    if (local226 == 3606) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Static193.method3500(local609.method3158());
                        continue;
                    }
                    if (local226 == 3607) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Static140.method2707(local609.method3158());
                        continue;
                    }
                    if (local226 == 3608) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.method1542(local609.method3158());
                        continue;
                    }
                    if (local226 == 3609) {
                        ssp--;
                        local609 = stringStack[ssp];
                        if (local609.method3138(Static72.aClass100_446) || local609.method3138(Static101.aClass100_537)) {
                            local609 = local609.method3136(7);
                        }
                        intStack[isp++] = Static98.method1965(local609) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3610) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static166.anInt4054 == 2 && Static9.anInt178 > local809) {
                            stringStack[ssp++] = Static214.aClass100Array170[local809];
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 3611) {
                        if (Static15.aClass100_87 == null) {
                            stringStack[ssp++] = AUTO_EMPTY;
                        } else {
                            stringStack[ssp++] = Static15.aClass100_87.method3125();
                        }
                        continue;
                    }
                    if (local226 == 3612) {
                        if (Static15.aClass100_87 == null) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = Static214.anInt5577;
                        }
                        continue;
                    }
                    if (local226 == 3613) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static15.aClass100_87 != null && Static214.anInt5577 > local809) {
                            stringStack[ssp++] = Static199.aClass3_Sub22Array1[local809].displayName.method3125();
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 3614) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static15.aClass100_87 != null && local809 < Static214.anInt5577) {
                            intStack[isp++] = Static199.aClass3_Sub22Array1[local809].world;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3615) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static15.aClass100_87 != null && Static214.anInt5577 > local809) {
                            intStack[isp++] = Static199.aClass3_Sub22Array1[local809].rank;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3616) {
                        intStack[isp++] = Static50.aByte6;
                        continue;
                    }
                    if (local226 == 3617) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.method3318(local609);
                        continue;
                    }
                    if (local226 == 3618) {
                        intStack[isp++] = Static160.aByte14;
                        continue;
                    }
                    if (local226 == 3619) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Static156.method2956(local609.method3158());
                        continue;
                    }
                    if (local226 == 3620) {
                        Static134.method2623();
                        continue;
                    }
                    if (local226 == 3621) {
                        if (Static166.anInt4054 == 0) {
                            intStack[isp++] = -1;
                        } else {
                            intStack[isp++] = Static35.anInt1093;
                        }
                        continue;
                    }
                    if (local226 == 3622) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static166.anInt4054 != 0 && Static35.anInt1093 > local809) {
                            stringStack[ssp++] = Static79.toBaseDisplayName(Static190.aLongArray6[local809]).method3125();
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 3623) {
                        ssp--;
                        local609 = stringStack[ssp];
                        if (local609.method3138(Static72.aClass100_446) || local609.method3138(Static101.aClass100_537)) {
                            local609 = local609.method3136(7);
                        }
                        intStack[isp++] = Static238.method4144(local609) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3624) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static199.aClass3_Sub22Array1 != null && Static214.anInt5577 > local809 && Static199.aClass3_Sub22Array1[local809].displayName.method3111(Client.localPlayer.aClass100_364)) {
                            intStack[isp++] = 1;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3625) {
                        if (Static270.aClass100_1094 == null) {
                            stringStack[ssp++] = AUTO_EMPTY;
                        } else {
                            stringStack[ssp++] = Static270.aClass100_1094.method3125();
                        }
                        continue;
                    }
                    if (local226 == 3626) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static15.aClass100_87 != null && Static214.anInt5577 > local809) {
                            stringStack[ssp++] = Static199.aClass3_Sub22Array1[local809].aClass100_635;
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 3627) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static166.anInt4054 == 2 && local809 >= 0 && local809 < Static9.anInt178) {
                            intStack[isp++] = Static3.aBooleanArray135[local809] ? 1 : 0;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 3628) {
                        ssp--;
                        local609 = stringStack[ssp];
                        if (local609.method3138(Static72.aClass100_446) || local609.method3138(Static101.aClass100_537)) {
                            local609 = local609.method3136(7);
                        }
                        intStack[isp++] = Static4.method25(local609);
                        continue;
                    }
                    if (local226 == 3629) {
                        intStack[isp++] = Client.country;
                        continue;
                    }
                } else if (local226 < 4000) {
                    if (local226 == 3903) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static229.aClass136Array1[local809].method3905();
                        continue;
                    }
                    if (local226 == 3904) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static229.aClass136Array1[local809].anInt5094;
                        continue;
                    }
                    if (local226 == 3905) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static229.aClass136Array1[local809].anInt5099;
                        continue;
                    }
                    if (local226 == 3906) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static229.aClass136Array1[local809].anInt5090;
                        continue;
                    }
                    if (local226 == 3907) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static229.aClass136Array1[local809].anInt5089;
                        continue;
                    }
                    if (local226 == 3908) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static229.aClass136Array1[local809].anInt5092;
                        continue;
                    }
                    if (local226 == 3910) {
                        isp--;
                        local809 = intStack[isp];
                        local803 = Static229.aClass136Array1[local809].method3904();
                        intStack[isp++] = local803 == 0 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3911) {
                        isp--;
                        local809 = intStack[isp];
                        local803 = Static229.aClass136Array1[local809].method3904();
                        intStack[isp++] = local803 == 2 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3912) {
                        isp--;
                        local809 = intStack[isp];
                        local803 = Static229.aClass136Array1[local809].method3904();
                        intStack[isp++] = local803 == 5 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 3913) {
                        isp--;
                        local809 = intStack[isp];
                        local803 = Static229.aClass136Array1[local809].method3904();
                        intStack[isp++] = local803 == 1 ? 1 : 0;
                        continue;
                    }
                } else if (local226 < 4100) {
                    if (local226 == 4000) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local803 + local809;
                        continue;
                    }
                    if (local226 == 4001) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local809 - local803;
                        continue;
                    }
                    if (local226 == 4002) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local803 * local809;
                        continue;
                    }
                    if (local226 == 4003) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local809 / local803;
                        continue;
                    }
                    if (local226 == 4004) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = (int) ((double) local809 * Math.random());
                        continue;
                    }
                    if (local226 == 4005) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = (int) (Math.random() * (double) (local809 + 1));
                        continue;
                    }
                    if (local226 == 4006) {
                        isp -= 5;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local652 = intStack[isp + 3];
                        local1052 = intStack[isp + 2];
                        local4859 = intStack[isp + 4];
                        intStack[isp++] = (local803 - local809) * (local4859 + -local1052) / (local652 - local1052) + local809;
                        continue;
                    }
                    @Pc(4899) long local4899;
                    @Pc(4892) long local4892;
                    if (local226 == 4007) {
                        isp -= 2;
                        local4892 = intStack[isp];
                        local4899 = intStack[isp + 1];
                        intStack[isp++] = (int) (local4892 * local4899 / 100L + local4892);
                        continue;
                    }
                    if (local226 == 4008) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local809 | 0x1 << local803;
                        continue;
                    }
                    if (local226 == 4009) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = -(0x1 << local803) - 1 & local809;
                        continue;
                    }
                    if (local226 == 4010) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = (local809 & 0x1 << local803) == 0 ? 0 : 1;
                        continue;
                    }
                    if (local226 == 4011) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = local809 % local803;
                        continue;
                    }
                    if (local226 == 4012) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        if (local809 == 0) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = (int) Math.pow((double) local809, (double) local803);
                        }
                        continue;
                    }
                    if (local226 == 4013) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        if (local809 == 0) {
                            intStack[isp++] = 0;
                        } else if (local803 == 0) {
                            intStack[isp++] = Integer.MAX_VALUE;
                        } else {
                            intStack[isp++] = (int) Math.pow((double) local809, 1.0D / (double) local803);
                        }
                        continue;
                    }
                    if (local226 == 4014) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = local803 & local809;
                        continue;
                    }
                    if (local226 == 4015) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local809 | local803;
                        continue;
                    }
                    if (local226 == 4016) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        intStack[isp++] = local809 < local803 ? local809 : local803;
                        continue;
                    }
                    if (local226 == 4017) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = local803 >= local809 ? local803 : local809;
                        continue;
                    }
                    if (local226 == 4018) {
                        isp -= 3;
                        local4892 = intStack[isp];
                        local4899 = intStack[isp + 1];
                        @Pc(5251) long local5251 = (long) intStack[isp + 2];
                        intStack[isp++] = (int) (local4892 * local5251 / local4899);
                        continue;
                    }
                } else if (local226 < 4200) {
                    if (local226 == 4100) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp--;
                        local803 = intStack[isp];
                        stringStack[ssp++] = JagString.join(new JagString[]{local609, JagString.parseInt(local803)});
                        continue;
                    }
                    if (local226 == 4101) {
                        ssp -= 2;
                        local2522 = stringStack[ssp + 1];
                        local609 = stringStack[ssp];
                        stringStack[ssp++] = JagString.join(new JagString[]{local609, local2522});
                        continue;
                    }
                    if (local226 == 4102) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp--;
                        local803 = intStack[isp];
                        stringStack[ssp++] = JagString.join(new JagString[]{local609, JagString.method2285(local803)});
                        continue;
                    }
                    if (local226 == 4103) {
                        ssp--;
                        local609 = stringStack[ssp];
                        stringStack[ssp++] = local609.method3114();
                        continue;
                    }
                    if (local226 == 4104) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(11770) long local11770 = (long) local809 * 86400000L + 1014768000000L;
                        Static102.aCalendar2.setTime(new Date(local11770));
                        local652 = Static102.aCalendar2.get(5);
                        local4859 = Static102.aCalendar2.get(2);
                        local1087 = Static102.aCalendar2.get(1);
                        stringStack[ssp++] = JagString.join(new JagString[]{JagString.parseInt(local652), Static163.aClass100_767, months[local4859], Static163.aClass100_767, JagString.parseInt(local1087)});
                        continue;
                    }
                    if (local226 == 4105) {
                        ssp -= 2;
                        local2522 = stringStack[ssp + 1];
                        local609 = stringStack[ssp];
                        if (Client.localPlayer.aClass59_1 != null && Client.localPlayer.aClass59_1.aBoolean141) {
                            stringStack[ssp++] = local2522;
                            continue;
                        }
                        stringStack[ssp++] = local609;
                        continue;
                    }
                    if (local226 == 4106) {
                        isp--;
                        local809 = intStack[isp];
                        stringStack[ssp++] = JagString.parseInt(local809);
                        continue;
                    }
                    if (local226 == 4107) {
                        ssp -= 2;
                        intStack[isp++] = stringStack[ssp].method3126(stringStack[ssp + 1]);
                        continue;
                    }
                    if (local226 == 4108) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp -= 2;
                        local1052 = intStack[isp + 1];
                        local803 = intStack[isp];
                        intStack[isp++] = Static148.method2768(local1052).method2860(local609, local803);
                        continue;
                    }
                    if (local226 == 4109) {
                        isp -= 2;
                        ssp--;
                        local609 = stringStack[ssp];
                        local1052 = intStack[isp + 1];
                        local803 = intStack[isp];
                        intStack[isp++] = Static148.method2768(local1052).method2856(local609, local803);
                        continue;
                    }
                    if (local226 == 4110) {
                        ssp -= 2;
                        local609 = stringStack[ssp];
                        local2522 = stringStack[ssp + 1];
                        isp--;
                        if (intStack[isp] == 1) {
                            stringStack[ssp++] = local609;
                        } else {
                            stringStack[ssp++] = local2522;
                        }
                        continue;
                    }
                    if (local226 == 4111) {
                        ssp--;
                        local609 = stringStack[ssp];
                        stringStack[ssp++] = Static218.method2862(local609);
                        continue;
                    }
                    if (local226 == 4112) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp--;
                        local803 = intStack[isp];
                        if (local803 == -1) {
                            throw new RuntimeException("null char");
                        }
                        stringStack[ssp++] = local609.method3128(local803);
                        continue;
                    }
                    if (local226 == 4113) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static273.method3213(local809) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 4114) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static83.method433(local809) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 4115) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static258.method4428(local809) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 4116) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static24.method671(local809) ? 1 : 0;
                        continue;
                    }
                    if (local226 == 4117) {
                        ssp--;
                        local609 = stringStack[ssp];
                        if (local609 == null) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local609.length();
                        }
                        continue;
                    }
                    if (local226 == 4118) {
                        isp -= 2;
                        ssp--;
                        local609 = stringStack[ssp];
                        local803 = intStack[isp];
                        local1052 = intStack[isp + 1];
                        stringStack[ssp++] = local609.method3137(local1052, local803);
                        continue;
                    }
                    if (local226 == 4119) {
                        ssp--;
                        local609 = stringStack[ssp];
                        local2522 = JagString.newStringBuilder(local609.length());
                        @Pc(12220) boolean local12220 = false;
                        for (local652 = 0; local652 < local609.length(); local652++) {
                            local4859 = local609.method3149(local652);
                            if (local4859 == 60) {
                                local12220 = true;
                            } else if (local4859 == 62) {
                                local12220 = false;
                            } else if (!local12220) {
                                local2522.method3152(local4859);
                            }
                        }
                        local2522.method3156();
                        stringStack[ssp++] = local2522;
                        continue;
                    }
                    if (local226 == 4120) {
                        isp -= 2;
                        ssp--;
                        local609 = stringStack[ssp];
                        local803 = intStack[isp];
                        local1052 = intStack[isp + 1];
                        intStack[isp++] = local609.method3135(local803, local1052);
                        continue;
                    }
                    if (local226 == 4121) {
                        ssp -= 2;
                        local609 = stringStack[ssp];
                        local2522 = stringStack[ssp + 1];
                        isp--;
                        local1052 = intStack[isp];
                        intStack[isp++] = local609.method3146(local2522, local1052);
                        continue;
                    }
                    if (local226 == 4122) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static231.method3984(local809);
                        continue;
                    }
                    if (local226 == 4123) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = Static143.method2733(local809);
                        continue;
                    }
                    if (local226 == 4124) {
                        isp--;
                        local12388 = intStack[isp] != 0;
                        isp--;
                        local803 = intStack[isp];
                        stringStack[ssp++] = Static182.method3360(Client.lang, local12388, 0, (long) local803);
                        continue;
                    }
                } else if (local226 < 4300) {
                    if (local226 == 4200) {
                        isp--;
                        local809 = intStack[isp];
                        stringStack[ssp++] = ObjType.list(local809).name;
                        continue;
                    }
                    @Pc(11269) ObjType local11269;
                    if (local226 == 4201) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local11269 = ObjType.list(local809);
                        if (local803 >= 1 && local803 <= 5 && local11269.aClass100Array72[local803 - 1] != null) {
                            stringStack[ssp++] = local11269.aClass100Array72[local803 - 1];
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 4202) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local11269 = ObjType.list(local809);
                        if (local803 >= 1 && local803 <= 5 && local11269.aClass100Array71[local803 - 1] != null) {
                            stringStack[ssp++] = local11269.aClass100Array71[local803 - 1];
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 4203) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = ObjType.list(local809).anInt2325;
                        continue;
                    }
                    if (local226 == 4204) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = ObjType.list(local809).anInt2336 == 1 ? 1 : 0;
                        continue;
                    }
                    @Pc(11417) ObjType local11417;
                    if (local226 == 4205) {
                        isp--;
                        local809 = intStack[isp];
                        local11417 = ObjType.list(local809);
                        if (local11417.anInt2358 == -1 && local11417.anInt2356 >= 0) {
                            intStack[isp++] = local11417.anInt2356;
                            continue;
                        }
                        intStack[isp++] = local809;
                        continue;
                    }
                    if (local226 == 4206) {
                        isp--;
                        local809 = intStack[isp];
                        local11417 = ObjType.list(local809);
                        if (local11417.anInt2358 >= 0 && local11417.anInt2356 >= 0) {
                            intStack[isp++] = local11417.anInt2356;
                            continue;
                        }
                        intStack[isp++] = local809;
                        continue;
                    }
                    if (local226 == 4207) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = ObjType.list(local809).aBoolean131 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 4208) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local5294 = ParamType.list(local803);
                        if (local5294.method2078()) {
                            stringStack[ssp++] = ObjType.list(local809).method1819(local5294.aClass100_544, local803);
                        } else {
                            intStack[isp++] = ObjType.list(local809).method1829(local5294.anInt2667, local803);
                        }
                        continue;
                    }
                    if (local226 == 4210) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp--;
                        local803 = intStack[isp];
                        Static155.method2941(local803 == 1, local609);
                        intStack[isp++] = Static111.anInt2905;
                        continue;
                    }
                    if (local226 == 4211) {
                        if (Static169.aShortArray52 != null && Static67.anInt3356 < Static111.anInt2905) {
                            intStack[isp++] = Static169.aShortArray52[Static67.anInt3356++] & 0xFFFF;
                            continue;
                        }
                        intStack[isp++] = -1;
                        continue;
                    }
                    if (local226 == 4212) {
                        Static67.anInt3356 = 0;
                        continue;
                    }
                } else if (local226 < 4400) {
                    if (local226 == 4300) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local5294 = ParamType.list(local803);
                        if (local5294.method2078()) {
                            stringStack[ssp++] = NPCType.list(local809).method2938(local803, local5294.aClass100_544);
                        } else {
                            intStack[isp++] = NPCType.list(local809).method2936(local803, local5294.anInt2667);
                        }
                        continue;
                    }
                } else if (local226 < 4500) {
                    if (local226 == 4400) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        local5294 = ParamType.list(local803);
                        if (local5294.method2078()) {
                            stringStack[ssp++] = LocType.list(local809).method3430(local5294.aClass100_544, local803);
                        } else {
                            intStack[isp++] = LocType.list(local809).method3423(local5294.anInt2667, local803);
                        }
                        continue;
                    }
                } else if (local226 < 4600) {
                    if (local226 == 4500) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        local5294 = ParamType.list(local803);
                        if (local5294.method2078()) {
                            stringStack[ssp++] = StructType.list(local809).method2802(local5294.aClass100_544, local803);
                        } else {
                            intStack[isp++] = StructType.list(local809).method2798(local803, local5294.anInt2667);
                        }
                        continue;
                    }
                } else if (local226 < 5100) {
                    if (local226 == 5000) {
                        intStack[isp++] = Static59.anInt1812;
                        continue;
                    }
                    if (local226 == 5001) {
                        isp -= 3;
                        Static59.anInt1812 = intStack[isp];
                        Static49.anInt1459 = intStack[isp + 1];
                        Static84.anInt2256 = intStack[isp + 2];
                        Client.out.p1Enc(157);
                        Client.out.p1(Static59.anInt1812);
                        Client.out.p1(Static49.anInt1459);
                        Client.out.p1(Static84.anInt2256);
                        continue;
                    }
                    if (local226 == 5002) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp -= 2;
                        local803 = intStack[isp];
                        local1052 = intStack[isp + 1];
                        Client.out.p1Enc(99);
                        Client.out.p8(local609.method3158());
                        Client.out.p1(local803 - 1);
                        Client.out.p1(local1052);
                        continue;
                    }
                    if (local226 == 5003) {
                        local2522 = null;
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 100) {
                            local2522 = Client.aClass100Array158[local809];
                        }
                        if (local2522 == null) {
                            local2522 = AUTO_EMPTY;
                        }
                        stringStack[ssp++] = local2522;
                        continue;
                    }
                    if (local226 == 5004) {
                        isp--;
                        local809 = intStack[isp];
                        local803 = -1;
                        if (local809 < 100 && Client.aClass100Array158[local809] != null) {
                            local803 = Client.anIntArray67[local809];
                        }
                        intStack[isp++] = local803;
                        continue;
                    }
                    if (local226 == 5005) {
                        intStack[isp++] = Static49.anInt1459;
                        continue;
                    }
                    if (local226 == 5008) {
                        ssp--;
                        local609 = stringStack[ssp];
                        if (!local609.method3138(Static12.aClass100_74)) {
                            if (Client.anInt4502 == 0 && (Client.aBoolean157 && !Client.aBoolean236 || Client.aBoolean129)) {
                                continue;
                            }
                            local2522 = local609.method3114();
                            @Pc(5555) byte local5555 = 0;
                            if (local2522.method3138(Static21.aClass100_126)) {
                                local5555 = 0;
                                local609 = local609.method3136(Static21.aClass100_126.length());
                            } else if (local2522.method3138(Static141.aClass100_666)) {
                                local609 = local609.method3136(Static141.aClass100_666.length());
                                local5555 = 1;
                            } else if (local2522.method3138(Static4.aClass100_496)) {
                                local609 = local609.method3136(Static4.aClass100_496.length());
                                local5555 = 2;
                            } else if (local2522.method3138(Static36.aClass100_234)) {
                                local5555 = 3;
                                local609 = local609.method3136(Static36.aClass100_234.length());
                            } else if (local2522.method3138(Static92.aClass100_512)) {
                                local609 = local609.method3136(Static92.aClass100_512.length());
                                local5555 = 4;
                            } else if (local2522.method3138(Static16.aClass100_95)) {
                                local609 = local609.method3136(Static16.aClass100_95.length());
                                local5555 = 5;
                            } else if (local2522.method3138(Static157.aClass100_750)) {
                                local5555 = 6;
                                local609 = local609.method3136(Static157.aClass100_750.length());
                            } else if (local2522.method3138(Static245.aClass100_1019)) {
                                local5555 = 7;
                                local609 = local609.method3136(Static245.aClass100_1019.length());
                            } else if (local2522.method3138(Static138.aClass100_643)) {
                                local609 = local609.method3136(Static138.aClass100_643.length());
                                local5555 = 8;
                            } else if (local2522.method3138(Static2.aClass100_3)) {
                                local5555 = 9;
                                local609 = local609.method3136(Static2.aClass100_3.length());
                            } else if (local2522.method3138(Static262.aClass100_1078)) {
                                local5555 = 10;
                                local609 = local609.method3136(Static262.aClass100_1078.length());
                            } else if (local2522.method3138(aClass100_191)) {
                                local609 = local609.method3136(aClass100_191.length());
                                local5555 = 11;
                            } else if (Client.lang != 0) {
                                if (local2522.method3138(Text.aClass100_123)) {
                                    local5555 = 0;
                                    local609 = local609.method3136(Text.aClass100_123.length());
                                } else if (local2522.method3138(Text.aClass100_663)) {
                                    local609 = local609.method3136(Text.aClass100_663.length());
                                    local5555 = 1;
                                } else if (local2522.method3138(Text.aClass100_498)) {
                                    local609 = local609.method3136(Text.aClass100_498.length());
                                    local5555 = 2;
                                } else if (local2522.method3138(Text.aClass100_233)) {
                                    local609 = local609.method3136(Text.aClass100_233.length());
                                    local5555 = 3;
                                } else if (local2522.method3138(Text.aClass100_508)) {
                                    local609 = local609.method3136(Text.aClass100_508.length());
                                    local5555 = 4;
                                } else if (local2522.method3138(Text.aClass100_94)) {
                                    local5555 = 5;
                                    local609 = local609.method3136(Text.aClass100_94.length());
                                } else if (local2522.method3138(Text.aClass100_752)) {
                                    local609 = local609.method3136(Text.aClass100_752.length());
                                    local5555 = 6;
                                } else if (local2522.method3138(Text.aClass100_1022)) {
                                    local5555 = 7;
                                    local609 = local609.method3136(Text.aClass100_1022.length());
                                } else if (local2522.method3138(Text.aClass100_648)) {
                                    local5555 = 8;
                                    local609 = local609.method3136(Text.aClass100_648.length());
                                } else if (local2522.method3138(Text.aClass100_4)) {
                                    local5555 = 9;
                                    local609 = local609.method3136(Text.aClass100_4.length());
                                } else if (local2522.method3138(Text.aClass100_1079)) {
                                    local609 = local609.method3136(Text.aClass100_1079.length());
                                    local5555 = 10;
                                } else if (local2522.method3138(Text.aClass100_190)) {
                                    local609 = local609.method3136(Text.aClass100_190.length());
                                    local5555 = 11;
                                }
                            }
                            @Pc(5943) byte local5943 = 0;
                            local2522 = local609.method3114();
                            if (local2522.method3138(Static41.aClass100_270)) {
                                local609 = local609.method3136(Static41.aClass100_270.length());
                                local5943 = 1;
                            } else if (local2522.method3138(Static191.aClass100_843)) {
                                local5943 = 2;
                                local609 = local609.method3136(Static191.aClass100_843.length());
                            } else if (local2522.method3138(Static220.aClass100_932)) {
                                local609 = local609.method3136(Static220.aClass100_932.length());
                                local5943 = 3;
                            } else if (local2522.method3138(Static56.aClass100_388)) {
                                local5943 = 4;
                                local609 = local609.method3136(Static56.aClass100_388.length());
                            } else if (local2522.method3138(Text.aClass100_389)) {
                                local5943 = 5;
                                local609 = local609.method3136(Text.aClass100_389.length());
                            } else if (Client.lang != 0) {
                                if (local2522.method3138(Text.aClass100_272)) {
                                    local609 = local609.method3136(Text.aClass100_272.length());
                                    local5943 = 1;
                                } else if (local2522.method3138(Text.aClass100_846)) {
                                    local5943 = 2;
                                    local609 = local609.method3136(Text.aClass100_846.length());
                                } else if (local2522.method3138(Text.aClass100_931)) {
                                    local5943 = 3;
                                    local609 = local609.method3136(Text.aClass100_931.length());
                                } else if (local2522.method3138(Text.aClass100_385)) {
                                    local5943 = 4;
                                    local609 = local609.method3136(Text.aClass100_385.length());
                                } else if (local2522.method3138(Text.aClass100_391)) {
                                    local609 = local609.method3136(Text.aClass100_391.length());
                                    local5943 = 5;
                                }
                            }
                            Client.out.p1Enc(237);
                            Client.out.p1(0);
                            local4859 = Client.out.pos;
                            Client.out.p1(local5555);
                            Client.out.p1(local5943);
                            Static146.method2748(Client.out, local609);
                            Client.out.psize1(Client.out.pos - local4859);
                            continue;
                        }
                        Static127.method2470(local609);
                        continue;
                    }
                    if (local226 == 5009) {
                        ssp -= 2;
                        local2522 = stringStack[ssp + 1];
                        local609 = stringStack[ssp];
                        if (Client.anInt4502 != 0 || (!Client.aBoolean157 || Client.aBoolean236) && !Client.aBoolean129) {
                            Client.out.p1Enc(201);
                            Client.out.p1(0);
                            local1052 = Client.out.pos;
                            Client.out.p8(local609.method3158());
                            Static146.method2748(Client.out, local2522);
                            Client.out.psize1(Client.out.pos - local1052);
                        }
                        continue;
                    }
                    if (local226 == 5010) {
                        isp--;
                        local809 = intStack[isp];
                        local2522 = null;
                        if (local809 < 100) {
                            local2522 = Client.aClass100Array112[local809];
                        }
                        if (local2522 == null) {
                            local2522 = AUTO_EMPTY;
                        }
                        stringStack[ssp++] = local2522;
                        continue;
                    }
                    if (local226 == 5011) {
                        isp--;
                        local809 = intStack[isp];
                        local2522 = null;
                        if (local809 < 100) {
                            local2522 = Client.aClass100Array62[local809];
                        }
                        if (local2522 == null) {
                            local2522 = AUTO_EMPTY;
                        }
                        stringStack[ssp++] = local2522;
                        continue;
                    }
                    if (local226 == 5012) {
                        isp--;
                        local809 = intStack[isp];
                        local803 = -1;
                        if (local809 < 100) {
                            local803 = Client.anIntArray521[local809];
                        }
                        intStack[isp++] = local803;
                        continue;
                    }
                    if (local226 == 5015) {
                        if (Client.localPlayer == null || Client.localPlayer.aClass100_364 == null) {
                            local609 = TitleScreen.loginUser;
                        } else {
                            local609 = Client.localPlayer.method1264();
                        }
                        stringStack[ssp++] = local609;
                        continue;
                    }
                    if (local226 == 5016) {
                        intStack[isp++] = Static84.anInt2256;
                        continue;
                    }
                    if (local226 == 5017) {
                        intStack[isp++] = Client.anInt1941;
                        continue;
                    }
                    if (local226 == 5050) {
                        isp--;
                        local809 = intStack[isp];
                        stringStack[ssp++] = QuickChatCatType.list(local809).aClass100_79;
                        continue;
                    }
                    @Pc(6378) QuickChatCatType local6378;
                    if (local226 == 5051) {
                        isp--;
                        local809 = intStack[isp];
                        local6378 = QuickChatCatType.list(local809);
                        if (local6378.anIntArray30 == null) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local6378.anIntArray30.length;
                        }
                        continue;
                    }
                    if (local226 == 5052) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        @Pc(6416) QuickChatCatType local6416 = QuickChatCatType.list(local809);
                        local652 = local6416.anIntArray30[local803];
                        intStack[isp++] = local652;
                        continue;
                    }
                    if (local226 == 5053) {
                        isp--;
                        local809 = intStack[isp];
                        local6378 = QuickChatCatType.list(local809);
                        if (local6378.anIntArray32 == null) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local6378.anIntArray32.length;
                        }
                        continue;
                    }
                    if (local226 == 5054) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = QuickChatCatType.list(local809).anIntArray32[local803];
                        continue;
                    }
                    if (local226 == 5055) {
                        isp--;
                        local809 = intStack[isp];
                        stringStack[ssp++] = QuickChatPhraseType.list(local809).method769();
                        continue;
                    }
                    if (local226 == 5056) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(6527) QuickChatPhraseType local6527 = QuickChatPhraseType.list(local809);
                        if (local6527.anIntArray72 == null) {
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local6527.anIntArray72.length;
                        }
                        continue;
                    }
                    if (local226 == 5057) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = QuickChatPhraseType.list(local809).anIntArray72[local803];
                        continue;
                    }
                    if (local226 == 5058) {
                        Static122.aClass12_1 = new QuickChatPhrase();
                        isp--;
                        Static122.aClass12_1.anInt439 = intStack[isp];
                        Static122.aClass12_1.aClass3_Sub2_Sub6_1 = QuickChatPhraseType.list(Static122.aClass12_1.anInt439);
                        Static122.aClass12_1.anIntArray33 = new int[Static122.aClass12_1.aClass3_Sub2_Sub6_1.method767()];
                        continue;
                    }
                    if (local226 == 5059) {
                        Client.out.p1Enc(167);
                        Client.out.p1(0);
                        local809 = Client.out.pos;
                        Client.out.p1(0);
                        Client.out.p2(Static122.aClass12_1.anInt439);
                        Static122.aClass12_1.aClass3_Sub2_Sub6_1.method760(Client.out, Static122.aClass12_1.anIntArray33);
                        Client.out.psize1(Client.out.pos - local809);
                        continue;
                    }
                    if (local226 == 5060) {
                        ssp--;
                        local609 = stringStack[ssp];
                        Client.out.p1Enc(178);
                        Client.out.p1(0);
                        local803 = Client.out.pos;
                        Client.out.p8(local609.method3158());
                        Client.out.p2(Static122.aClass12_1.anInt439);
                        Static122.aClass12_1.aClass3_Sub2_Sub6_1.method760(Client.out, Static122.aClass12_1.anIntArray33);
                        Client.out.psize1(Client.out.pos - local803);
                        continue;
                    }
                    if (local226 == 5061) {
                        Client.out.p1Enc(167);
                        Client.out.p1(0);
                        local809 = Client.out.pos;
                        Client.out.p1(1);
                        Client.out.p2(Static122.aClass12_1.anInt439);
                        Static122.aClass12_1.aClass3_Sub2_Sub6_1.method760(Client.out, Static122.aClass12_1.anIntArray33);
                        Client.out.psize1(Client.out.pos - local809);
                        continue;
                    }
                    if (local226 == 5062) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = QuickChatCatType.list(local809).anIntArray31[local803];
                        continue;
                    }
                    if (local226 == 5063) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        intStack[isp++] = QuickChatCatType.list(local809).anIntArray29[local803];
                        continue;
                    }
                    if (local226 == 5064) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        if (local803 == -1) {
                            intStack[isp++] = -1;
                        } else {
                            intStack[isp++] = QuickChatCatType.list(local809).method469(local803);
                        }
                        continue;
                    }
                    if (local226 == 5065) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        if (local803 == -1) {
                            intStack[isp++] = -1;
                        } else {
                            intStack[isp++] = QuickChatCatType.list(local809).method466(local803);
                        }
                        continue;
                    }
                    if (local226 == 5066) {
                        isp--;
                        local809 = intStack[isp];
                        intStack[isp++] = QuickChatPhraseType.list(local809).method767();
                        continue;
                    }
                    if (local226 == 5067) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        local1052 = QuickChatPhraseType.list(local809).method765(local803);
                        intStack[isp++] = local1052;
                        continue;
                    }
                    if (local226 == 5068) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        Static122.aClass12_1.anIntArray33[local809] = local803;
                        continue;
                    }
                    if (local226 == 5069) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        Static122.aClass12_1.anIntArray33[local809] = local803;
                        continue;
                    }
                    if (local226 == 5070) {
                        isp -= 3;
                        local809 = intStack[isp];
                        local1052 = intStack[isp + 2];
                        local803 = intStack[isp + 1];
                        @Pc(6996) QuickChatPhraseType local6996 = QuickChatPhraseType.list(local809);
                        if (local6996.method765(local803) != 0) {
                            throw new RuntimeException("bad command");
                        }
                        intStack[isp++] = local6996.method764(local1052, local803);
                        continue;
                    }
                    if (local226 == 5071) {
                        ssp--;
                        local609 = stringStack[ssp];
                        isp--;
                        local1552 = intStack[isp] == 1;
                        Static24.method668(local1552, local609);
                        intStack[isp++] = Static111.anInt2905;
                        continue;
                    }
                    if (local226 == 5072) {
                        if (Static169.aShortArray52 != null && Static67.anInt3356 < Static111.anInt2905) {
                            intStack[isp++] = Static169.aShortArray52[Static67.anInt3356++] & 0xFFFF;
                            continue;
                        }
                        intStack[isp++] = -1;
                        continue;
                    }
                    if (local226 == 5073) {
                        Static67.anInt3356 = 0;
                        continue;
                    }
                } else if (local226 < 5200) {
                    if (local226 == 5100) {
                        if (ClientKeyboardListener.keyHeld[86]) {
                            intStack[isp++] = 1;
                        } else {
                            intStack[isp++] = 0;
                        }
                        continue;
                    }
                    if (local226 == 5101) {
                        if (ClientKeyboardListener.keyHeld[82]) {
                            intStack[isp++] = 1;
                        } else {
                            intStack[isp++] = 0;
                        }
                        continue;
                    }
                    if (local226 == 5102) {
                        if (ClientKeyboardListener.keyHeld[81]) {
                            intStack[isp++] = 1;
                        } else {
                            intStack[isp++] = 0;
                        }
                        continue;
                    }
                } else if (local226 < 5300) {
                    if (local226 == 5200) {
                        isp--;
                        WorldMap.method2940(intStack[isp]);
                        continue;
                    }
                    if (local226 == 5201) {
                        intStack[isp++] = WorldMap.method1874();
                        continue;
                    }
                    if (local226 == 5202) {
                        isp--;
                        Static258.method4444(intStack[isp]);
                        continue;
                    }
                    if (local226 == 5203) {
                        ssp--;
                        Static3.method4656(stringStack[ssp]);
                        continue;
                    }
                    if (local226 == 5204) {
                        stringStack[ssp - 1] = Static211.method923(stringStack[ssp - 1]);
                        continue;
                    }
                    if (local226 == 5205) {
                        ssp--;
                        Static90.method1853(stringStack[ssp]);
                        continue;
                    }
                    if (local226 == 5206) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(7264) Map local7264 = Static29.method803(local809 >> 14 & 0x3FFF, local809 & 0x3FFF);
                        if (local7264 == null) {
                            stringStack[ssp++] = AUTO_EMPTY;
                        } else {
                            stringStack[ssp++] = local7264.aClass100_138;
                        }
                        continue;
                    }
                    @Pc(7293) Map local7293;
                    if (local226 == 5207) {
                        ssp--;
                        local7293 = Static124.method2434(stringStack[ssp]);
                        if (local7293 != null && local7293.aClass100_137 != null) {
                            stringStack[ssp++] = local7293.aClass100_137;
                            continue;
                        }
                        stringStack[ssp++] = AUTO_EMPTY;
                        continue;
                    }
                    if (local226 == 5208) {
                        intStack[isp++] = Static89.anInt2387;
                        intStack[isp++] = Static37.anInt1176;
                        continue;
                    }
                    if (local226 == 5209) {
                        intStack[isp++] = WorldMap.anInt3846 + WorldMap.anInt435;
                        intStack[isp++] = WorldMap.anInt13 + WorldMap.anInt4296 - WorldMap.anInt919 - 1;
                        continue;
                    }
                    if (local226 == 5210) {
                        local7293 = Static214.method4361();
                        if (local7293 == null) {
                            intStack[isp++] = 0;
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local7293.anInt769 * 64;
                            intStack[isp++] = local7293.anInt764 * 64;
                        }
                        continue;
                    }
                    if (local226 == 5211) {
                        local7293 = Static214.method4361();
                        if (local7293 == null) {
                            intStack[isp++] = 0;
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local7293.anInt770 - local7293.anInt763;
                            intStack[isp++] = local7293.anInt758 - local7293.anInt771;
                        }
                        continue;
                    }
                    if (local226 == 5212) {
                        local809 = Static118.method2352();
                        local1052 = 0;
                        if (local809 == -1) {
                            local2522 = AUTO_EMPTY;
                        } else {
                            local2522 = WorldMap.aClass134_1.aClass100Array153[local809];
                            local1052 = WorldMap.aClass134_1.method3894(local809);
                        }
                        local2522 = local2522.method3140(Static67.aClass100_639, Static5.aClass100_10);
                        stringStack[ssp++] = local2522;
                        intStack[isp++] = local1052;
                        continue;
                    }
                    if (local226 == 5213) {
                        local1052 = 0;
                        local809 = Static119.method2385();
                        if (local809 == -1) {
                            local2522 = AUTO_EMPTY;
                        } else {
                            local2522 = WorldMap.aClass134_1.aClass100Array153[local809];
                            local1052 = WorldMap.aClass134_1.method3894(local809);
                        }
                        local2522 = local2522.method3140(Static67.aClass100_639, Static5.aClass100_10);
                        stringStack[ssp++] = local2522;
                        intStack[isp++] = local1052;
                        continue;
                    }
                    if (local226 == 5214) {
                        isp--;
                        local809 = intStack[isp];
                        Static80.method3616(local809 >> 14 & 0x3FFF, local809 & 0x3FFF);
                        continue;
                    }
                    if (local226 == 5215) {
                        isp--;
                        local809 = intStack[isp];
                        ssp--;
                        local2522 = stringStack[ssp];
                        local7566 = false;
                        @Pc(7577) LinkList2 local7577 = Static183.method3333(local809 >> 14 & 0x3FFF, local809 & 0x3FFF);
                        for (@Pc(7582) Map local7582 = (Map) local7577.method795(); local7582 != null; local7582 = (Map) local7577.method797()) {
                            if (local7582.aClass100_138.method3111(local2522)) {
                                local7566 = true;
                                break;
                            }
                        }
                        if (local7566) {
                            intStack[isp++] = 1;
                        } else {
                            intStack[isp++] = 0;
                        }
                        continue;
                    }
                    if (local226 == 5216) {
                        isp--;
                        local809 = intStack[isp];
                        Static253.method4332(local809);
                        continue;
                    }
                    if (local226 == 5217) {
                        isp--;
                        local809 = intStack[isp];
                        if (Static90.method1855(local809)) {
                            intStack[isp++] = 1;
                        } else {
                            intStack[isp++] = 0;
                        }
                        continue;
                    }
                    if (local226 == 5218) {
                        local7293 = Static214.method4361();
                        if (local7293 == null) {
                            intStack[isp++] = -1;
                        } else {
                            intStack[isp++] = local7293.anInt772;
                        }
                        continue;
                    }
                    if (local226 == 5219) {
                        ssp--;
                        WorldMap.method1149(stringStack[ssp]);
                        continue;
                    }
                    if (local226 == 5220) {
                        intStack[isp++] = WorldMap.stage == 100 ? 1 : 0;
                        continue;
                    }
                } else if (local226 < 5400) {
                    if (local226 == 5300) {
                        isp -= 2;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        Static241.method4540(false, 3, local809, local803);
                        intStack[isp++] = GameShell.aFrame2 == null ? 0 : 1;
                        continue;
                    }
                    if (local226 == 5301) {
                        if (GameShell.aFrame2 != null) {
                            Static241.method4540(false, Static214.anInt5581, -1, -1);
                        }
                        continue;
                    }
                    if (local226 == 5302) {
                        @Pc(7780) DisplayMode[] local7780 = Static3.method4660();
                        intStack[isp++] = local7780.length;
                        continue;
                    }
                    if (local226 == 5303) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(7800) DisplayMode[] local7800 = Static3.method4660();
                        intStack[isp++] = local7800[local809].anInt4248;
                        intStack[isp++] = local7800[local809].anInt4250;
                        continue;
                    }
                    if (local226 == 5305) {
                        local803 = Static22.anInt729;
                        local809 = Static114.anInt5831;
                        local1052 = -1;
                        @Pc(7833) DisplayMode[] local7833 = Static3.method4660();
                        for (local4859 = 0; local4859 < local7833.length; local4859++) {
                            @Pc(7843) DisplayMode local7843 = local7833[local4859];
                            if (local809 == local7843.anInt4248 && local7843.anInt4250 == local803) {
                                local1052 = local4859;
                                break;
                            }
                        }
                        intStack[isp++] = local1052;
                        continue;
                    }
                    if (local226 == 5306) {
                        intStack[isp++] = Static144.method2736();
                        continue;
                    }
                    if (local226 == 5307) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0 || local809 > 2) {
                            local809 = 0;
                        }
                        Static241.method4540(false, local809, -1, -1);
                        continue;
                    }
                    if (local226 == 5308) {
                        intStack[isp++] = Static214.anInt5581;
                        continue;
                    }
                    if (local226 == 5309) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0 || local809 > 2) {
                            local809 = 0;
                        }
                        Static214.anInt5581 = local809;
                        Static203.method3663(GameShell.signlink);
                        continue;
                    }
                } else if (local226 < 5500) {
                    if (local226 == 5400) {
                        ssp -= 2;
                        local609 = stringStack[ssp];
                        local2522 = stringStack[ssp + 1];
                        isp--;
                        local1052 = intStack[isp];
                        Client.out.p1Enc(117);
                        Client.out.p1(Static229.method3937(local609) + Static229.method3937(local2522) + 1);
                        Client.out.pjstr(local609);
                        Client.out.pjstr(local2522);
                        Client.out.p1(local1052);
                        continue;
                    }
                    if (local226 == 5401) {
                        isp -= 2;
                        Client.aShortArray88[intStack[isp]] = (short) Static105.method2253(intStack[isp + 1]);
                        Static211.method924();
                        Static269.method2172();
                        Static278.method4649();
                        Static11.method443();
                        Client.method1807();
                        continue;
                    }
                    if (local226 == 5405) {
                        isp -= 2;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1];
                        if (local809 >= 0 && local809 < 2) {
                            Static107.anIntArrayArrayArray9[local809] = new int[local803 << 1][4];
                        }
                        continue;
                    }
                    if (local226 == 5406) {
                        isp -= 7;
                        local809 = intStack[isp];
                        local803 = intStack[isp + 1] << 1;
                        local652 = intStack[isp + 3];
                        local1052 = intStack[isp + 2];
                        local4859 = intStack[isp + 4];
                        @Pc(8108) int local8108 = intStack[isp + 6];
                        local1087 = intStack[isp + 5];
                        if (local809 >= 0 && local809 < 2 && Static107.anIntArrayArrayArray9[local809] != null && local803 >= 0 && Static107.anIntArrayArrayArray9[local809].length > local803) {
                            Static107.anIntArrayArrayArray9[local809][local803] = new int[]{(local1052 >> 14 & 0x3FFF) * 128, local652, (local1052 & 0x3FFF) * 128, local8108};
                            Static107.anIntArrayArrayArray9[local809][local803 + 1] = new int[]{(local4859 >> 14 & 0x3FFF) * 128, local1087, (local4859 & 0x3FFF) * 128};
                        }
                        continue;
                    }
                    if (local226 == 5407) {
                        isp--;
                        local809 = Static107.anIntArrayArrayArray9[intStack[isp]].length >> 1;
                        intStack[isp++] = local809;
                        continue;
                    }
                    if (local226 == 5411) {
                        if (GameShell.aFrame2 != null) {
                            Static241.method4540(false, Static214.anInt5581, -1, -1);
                        }
                        if (GameShell.frame == null) {
                            Static169.method3175(Static15.method479(), false);
                        } else {
                            System.exit(0);
                        }
                        continue;
                    }
                    if (local226 == 5419) {
                        local609 = AUTO_EMPTY;
                        if (Static232.aClass212_5 != null) {
                            local609 = Static181.method3341(Static232.aClass212_5.intArg);
                            try {
                                if (Static232.aClass212_5.result != null) {
                                    @Pc(8281) byte[] local8281 = ((String) Static232.aClass212_5.result).getBytes("ISO-8859-1");
                                    local609 = Static10.method346(local8281, local8281.length, 0);
                                }
                            } catch (@Pc(8290) UnsupportedEncodingException local8290) {
                            }
                        }
                        stringStack[ssp++] = local609;
                        continue;
                    }
                    if (local226 == 5420) {
                        intStack[isp++] = SignLink.anInt5928 == 3 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 5421) {
                        if (GameShell.aFrame2 != null) {
                            Static241.method4540(false, Static214.anInt5581, -1, -1);
                        }
                        isp--;
                        local1552 = intStack[isp] == 1;
                        ssp--;
                        local609 = stringStack[ssp];
                        @Pc(8356) JagString local8356 = JagString.join(new JagString[]{Static15.method479(), local609});
                        if (GameShell.frame != null || local1552 && SignLink.anInt5928 != 3 && SignLink.osNameLower.startsWith("win") && !Client.haveie6) {
                            Static164.aBoolean194 = local1552;
                            Static175.aClass100_797 = local8356;
                            Static33.aClass212_1 = GameShell.signlink.method5131(new String(local8356.builderToString(), "ISO-8859-1"));
                            continue;
                        }
                        Static169.method3175(local8356, local1552);
                        continue;
                    }
                    if (local226 == 5422) {
                        isp--;
                        local1052 = intStack[isp];
                        ssp -= 2;
                        local2522 = stringStack[ssp + 1];
                        local609 = stringStack[ssp];
                        if (local609.length() > 0) {
                            if (Static103.aClass100Array88 == null) {
                                Static103.aClass100Array88 = new JagString[Static132.anIntArray309[Client.game]];
                            }
                            Static103.aClass100Array88[local1052] = local609;
                        }
                        if (local2522.length() > 0) {
                            if (Static263.aClass100Array174 == null) {
                                Static263.aClass100Array174 = new JagString[Static132.anIntArray309[Client.game]];
                            }
                            Static263.aClass100Array174[local1052] = local2522;
                        }
                        continue;
                    }
                    if (local226 == 5423) {
                        ssp--;
                        stringStack[ssp].method3129();
                        continue;
                    }
                    if (local226 == 5424) {
                        isp -= 11;
                        Static40.anInt1275 = intStack[isp];
                        Static111.anInt2910 = intStack[isp + 1];
                        Static251.anInt5457 = intStack[isp + 2];
                        Static232.anInt5208 = intStack[isp + 3];
                        Static55.anInt1736 = intStack[isp + 4];
                        Static169.anInt4073 = intStack[isp + 5];
                        Static85.anInt2261 = intStack[isp + 6];
                        Static136.anInt3324 = intStack[isp + 7];
                        Static254.anInt5556 = intStack[isp + 8];
                        Static195.anInt4581 = intStack[isp + 9];
                        Static262.anInt5752 = intStack[isp + 10];
                        Client.sprites.method4506(Static55.anInt1736);
                        Client.sprites.method4506(Static169.anInt4073);
                        Client.sprites.method4506(Static85.anInt2261);
                        Client.sprites.method4506(Static136.anInt3324);
                        Client.sprites.method4506(Static254.anInt5556);
                        Static261.aBoolean298 = true;
                        continue;
                    }
                    if (local226 == 5425) {
                        Static114.method4637();
                        Static261.aBoolean298 = false;
                        continue;
                    }
                    if (local226 == 5426) {
                        isp--;
                        Static270.anInt5794 = intStack[isp];
                        continue;
                    }
                    if (local226 == 5427) {
                        isp -= 2;
                        Static169.anInt4075 = intStack[isp];
                        Static225.anInt5073 = intStack[isp + 1];
                        continue;
                    }
                } else if (local226 < 5600) {
                    if (local226 == 5500) {
                        isp -= 4;
                        local809 = intStack[isp];
                        local652 = intStack[isp + 3];
                        local1052 = intStack[isp + 2];
                        local803 = intStack[isp + 1];
                        Static141.method2722(false, local1052, local803, local652, (local809 & 0x3FFF) - Client.mapBuildBaseZ, (local809 >> 14 & 0x3FFF) - Client.mapBuildBaseX);
                        continue;
                    }
                    if (local226 == 5501) {
                        isp -= 4;
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        local652 = intStack[isp + 3];
                        local1052 = intStack[isp + 2];
                        Static260.method3849(local803, (local809 & 0x3FFF) - Client.mapBuildBaseZ, local1052, (local809 >> 14 & 0x3FFF) - Client.mapBuildBaseX, local652);
                        continue;
                    }
                    if (local226 == 5502) {
                        isp -= 6;
                        local809 = intStack[isp];
                        if (local809 >= 2) {
                            throw new RuntimeException();
                        }
                        Static155.anInt3718 = local809;
                        local803 = intStack[isp + 1];
                        if (Static107.anIntArrayArrayArray9[Static155.anInt3718].length >> 1 <= local803 + 1) {
                            throw new RuntimeException();
                        }
                        Static127.anInt3125 = local803;
                        Static233.anInt5224 = 0;
                        Static228.anInt5101 = intStack[isp + 2];
                        Static114.anInt5843 = intStack[isp + 3];
                        local1052 = intStack[isp + 4];
                        if (local1052 >= 2) {
                            throw new RuntimeException();
                        }
                        Static52.anInt1694 = local1052;
                        local652 = intStack[isp + 5];
                        if (Static107.anIntArrayArrayArray9[Static52.anInt1694].length >> 1 <= local652 + 1) {
                            throw new RuntimeException();
                        }
                        Static75.anInt2119 = local652;
                        Client.anInt5096 = 3;
                        continue;
                    }
                    if (local226 == 5503) {
                        Static35.method902();
                        continue;
                    }
                    if (local226 == 5504) {
                        isp -= 2;
                        Client.anInt2031 = intStack[isp];
                        Client.anInt1747 = intStack[isp + 1];
                        if (Client.anInt5096 == 2) {
                            Client.anInt4358 = Client.anInt1747;
                            Client.anInt5333 = Client.anInt2031;
                        }
                        Client.followCamera();
                        continue;
                    }
                    if (local226 == 5505) {
                        intStack[isp++] = Client.anInt2031;
                        continue;
                    }
                    if (local226 == 5506) {
                        intStack[isp++] = Client.anInt1747;
                        continue;
                    }
                } else if (local226 < 5700) {
                    if (local226 == 5600) {
                        ssp -= 2;
                        local609 = stringStack[ssp];
                        local2522 = stringStack[ssp + 1];
                        isp--;
                        local1052 = intStack[isp];
                        if (Client.state == 10 && Client.worldHopStep == 0 && Client.loginStep == 0 && Client.accountCreateStep == 0 && Static82.anInt2231 == 0) {
                            TitleScreen.method3896(local609, local2522, local1052);
                        }
                        continue;
                    }
                    if (local226 == 5601) {
                        Static185.method3395();
                        continue;
                    }
                    if (local226 == 5602) {
                        if (Client.loginStep == 0) {
                            Client.worldHopError = -2;
                        }
                        continue;
                    }
                    if (local226 == 5603) {
                        isp -= 4;
                        if (Client.state == 10 && Client.worldHopStep == 0 && Client.loginStep == 0 && Client.accountCreateStep == 0 && Static82.anInt2231 == 0) {
                            Client.method2448(intStack[isp + 2], intStack[isp + 3], intStack[isp], intStack[isp + 1]);
                        }
                        continue;
                    }
                    if (local226 == 5604) {
                        ssp--;
                        if (Client.state == 10 && Client.worldHopStep == 0 && Client.loginStep == 0 && Client.accountCreateStep == 0 && Static82.anInt2231 == 0) {
                            Client.method1691(stringStack[ssp].method3158());
                        }
                        continue;
                    }
                    if (local226 == 5605) {
                        isp -= 4;
                        ssp -= 2;
                        if (Client.state == 10 && Client.worldHopStep == 0 && Client.loginStep == 0 && Client.accountCreateStep == 0 && Static82.anInt2231 == 0) {
                            Static40.method1016(intStack[isp], intStack[isp + 3], intStack[isp + 1], stringStack[ssp + 1], stringStack[ssp].method3158(), intStack[isp + 2]);
                        }
                        continue;
                    }
                    if (local226 == 5606) {
                        if (Client.accountCreateStep == 0) {
                            Client.accountCreateError = -2;
                        }
                        continue;
                    }
                    if (local226 == 5607) {
                        intStack[isp++] = Client.worldHopError;
                        continue;
                    }
                    if (local226 == 5608) {
                        intStack[isp++] = Static231.anInt5202;
                        continue;
                    }
                    if (local226 == 5609) {
                        intStack[isp++] = Client.accountCreateError;
                        continue;
                    }
                    if (local226 == 5610) {
                        for (local809 = 0; local809 < 5; local809++) {
                            stringStack[ssp++] = Static229.aClass100Array156.length > local809 ? Static229.aClass100Array156[local809].method3125() : AUTO_EMPTY;
                        }
                        Static229.aClass100Array156 = null;
                        continue;
                    }
                    if (local226 == 5611) {
                        intStack[isp++] = Static204.anInt4765;
                        continue;
                    }
                } else if (local226 < 6100) {
                    if (local226 == 6001) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 1) {
                            local809 = 1;
                        }
                        if (local809 > 4) {
                            local809 = 4;
                        }
                        Static113.anInt4609 = local809;
                        if (!GameShell.glRenderer || !Static178.highDetailLighting) {
                            if (Static113.anInt4609 == 1) {
                                Pix3D.method1911(0.9F);
                            }
                            if (Static113.anInt4609 == 2) {
                                Pix3D.method1911(0.8F);
                            }
                            if (Static113.anInt4609 == 3) {
                                Pix3D.method1911(0.7F);
                            }
                            if (Static113.anInt4609 == 4) {
                                Pix3D.method1911(0.6F);
                            }
                        }
                        if (GameShell.glRenderer) {
                            Static86.method1799();
                            if (!Static178.highDetailLighting) {
                                Client.method2742();
                            }
                        }
                        Static269.method2172();
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6002) {
                        isp--;
                        Static53.method1293(intStack[isp] == 1);
                        LocType.method1854();
                        Client.method2742();
                        Static269.method2218();
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6003) {
                        isp--;
                        Static80.aBoolean231 = intStack[isp] == 1;
                        Static269.method2218();
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6005) {
                        isp--;
                        Static250.aBoolean283 = intStack[isp] == 1;
                        Client.method2742();
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6006) {
                        isp--;
                        Static53.aBoolean99 = intStack[isp] == 1;
                        ((WorldTextureProvider) Pix3D.anInterface1_2).method3245(!Static53.aBoolean99);
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6007) {
                        isp--;
                        Static15.aBoolean33 = intStack[isp] == 1;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6008) {
                        isp--;
                        Static11.aBoolean15 = intStack[isp] == 1;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6009) {
                        isp--;
                        Static159.aBoolean189 = intStack[isp] == 1;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6010) {
                        isp--;
                        Static209.aBoolean240 = intStack[isp] == 1;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6011) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0 || local809 > 2) {
                            local809 = 0;
                        }
                        Static139.anInt3451 = local809;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6012) {
                        if (GameShell.glRenderer) {
                            Static27.method766(0, 0);
                        }
                        isp--;
                        Static178.highDetailLighting = intStack[isp] == 1;
                        if (GameShell.glRenderer && Static178.highDetailLighting) {
                            Pix3D.method1911(0.7F);
                        } else {
                            if (Static113.anInt4609 == 1) {
                                Pix3D.method1911(0.9F);
                            }
                            if (Static113.anInt4609 == 2) {
                                Pix3D.method1911(0.8F);
                            }
                            if (Static113.anInt4609 == 3) {
                                Pix3D.method1911(0.7F);
                            }
                            if (Static113.anInt4609 == 4) {
                                Pix3D.method1911(0.6F);
                            }
                        }
                        Client.method2742();
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6014) {
                        isp--;
                        Static220.aBoolean244 = intStack[isp] == 1;
                        if (GameShell.glRenderer) {
                            Client.method2742();
                        }
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6015) {
                        isp--;
                        Static71.aBoolean107 = intStack[isp] == 1;
                        if (GameShell.glRenderer) {
                            Static86.method1799();
                        }
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6016) {
                        isp--;
                        local809 = intStack[isp];
                        if (GameShell.glRenderer) {
                            GameShell.canvasReplaceRecommended = true;
                        }
                        if (local809 < 0 || local809 > 2) {
                            local809 = 0;
                        }
                        Static186.anInt4392 = local809;
                        continue;
                    }
                    if (local226 == 6017) {
                        isp--;
                        Client.lowMem = intStack[isp] == 1;
                        Static211.method930();
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6018) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0) {
                            local809 = 0;
                        }
                        if (local809 > 127) {
                            local809 = 127;
                        }
                        Client.waveVolume = local809;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6019) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0) {
                            local809 = 0;
                        }
                        if (local809 > 255) {
                            local809 = 255;
                        }
                        if (local809 != Client.midiVolume) {
                            if (Client.midiVolume == 0 && Client.anInt4363 != -1) {
                                MidiManager.play(Client.songs, Client.anInt4363, local809);
                                Client.aBoolean173 = false;
                            } else if (local809 == 0) {
                                Static241.method4548();
                                Client.aBoolean173 = false;
                            } else {
                                MidiManager.method3956(local809);
                            }
                            Client.midiVolume = local809;
                        }
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6020) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0) {
                            local809 = 0;
                        }
                        if (local809 > 127) {
                            local809 = 127;
                        }
                        Client.ambientVolume = local809;
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        continue;
                    }
                    if (local226 == 6021) {
                        isp--;
                        Static127.aBoolean160 = intStack[isp] == 1;
                        Static269.method2218();
                        continue;
                    }
                    if (local226 == 6023) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0) {
                            local809 = 0;
                        }
                        if (local809 > 2) {
                            local809 = 2;
                        }
                        local1552 = false;
                        if (Static238.anInt5316 < 96) {
                            local1552 = true;
                            local809 = 0;
                        }
                        Static76.method1645(local809);
                        Static203.method3663(GameShell.signlink);
                        Static18.aBoolean39 = false;
                        intStack[isp++] = local1552 ? 0 : 1;
                        continue;
                    }
                    if (local226 == 6024) {
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0 || local809 > 2) {
                            local809 = 0;
                        }
                        Static102.anInt2679 = local809;
                        Static203.method3663(GameShell.signlink);
                        continue;
                    }
                    if (local226 == 6028) {
                        isp--;
                        Static64.aBoolean111 = intStack[isp] != 0;
                        Static203.method3663(GameShell.signlink);
                        continue;
                    }
                } else if (local226 < 6200) {
                    if (local226 == 6101) {
                        intStack[isp++] = Static113.anInt4609;
                        continue;
                    }
                    if (local226 == 6102) {
                        intStack[isp++] = Static138.method2697() ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6103) {
                        intStack[isp++] = Static80.aBoolean231 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6105) {
                        intStack[isp++] = Static250.aBoolean283 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6106) {
                        intStack[isp++] = Static53.aBoolean99 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6107) {
                        intStack[isp++] = Static15.aBoolean33 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6108) {
                        intStack[isp++] = Static11.aBoolean15 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6109) {
                        intStack[isp++] = Static159.aBoolean189 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6110) {
                        intStack[isp++] = Static209.aBoolean240 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6111) {
                        intStack[isp++] = Static139.anInt3451;
                        continue;
                    }
                    if (local226 == 6112) {
                        intStack[isp++] = Static178.highDetailLighting ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6114) {
                        intStack[isp++] = Static220.aBoolean244 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6115) {
                        intStack[isp++] = Static71.aBoolean107 ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6116) {
                        intStack[isp++] = Static186.anInt4392;
                        continue;
                    }
                    if (local226 == 6117) {
                        intStack[isp++] = Client.lowMem ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6118) {
                        intStack[isp++] = Client.waveVolume;
                        continue;
                    }
                    if (local226 == 6119) {
                        intStack[isp++] = Client.midiVolume;
                        continue;
                    }
                    if (local226 == 6120) {
                        intStack[isp++] = Client.ambientVolume;
                        continue;
                    }
                    if (local226 == 6121) {
                        if (GameShell.glRenderer) {
                            intStack[isp++] = Static239.aBoolean270 ? 1 : 0;
                        } else {
                            intStack[isp++] = 0;
                        }
                        continue;
                    }
                    if (local226 == 6123) {
                        intStack[isp++] = Static76.method1644();
                        continue;
                    }
                    if (local226 == 6124) {
                        intStack[isp++] = Static102.anInt2679;
                        continue;
                    }
                    if (local226 == 6128) {
                        intStack[isp++] = Static64.aBoolean111 ? 1 : 0;
                        continue;
                    }
                } else if (local226 < 6300) {
                    if (local226 == 6200) {
                        isp -= 2;
                        Static178.aShort25 = (short) intStack[isp];
                        if (Static178.aShort25 <= 0) {
                            Static178.aShort25 = 256;
                        }
                        Static10.aShort9 = (short) intStack[isp + 1];
                        if (Static10.aShort9 <= 0) {
                            Static10.aShort9 = 205;
                        }
                        continue;
                    }
                    if (local226 == 6201) {
                        isp -= 2;
                        Static263.aShort30 = (short) intStack[isp];
                        if (Static263.aShort30 <= 0) {
                            Static263.aShort30 = 256;
                        }
                        Static187.aShort27 = (short) intStack[isp + 1];
                        if (Static187.aShort27 <= 0) {
                            Static187.aShort27 = 320;
                        }
                        continue;
                    }
                    if (local226 == 6202) {
                        isp -= 4;
                        Static153.aShort22 = (short) intStack[isp];
                        if (Static153.aShort22 <= 0) {
                            Static153.aShort22 = 1;
                        }
                        Static4.aShort1 = (short) intStack[isp + 1];
                        if (Static4.aShort1 <= 0) {
                            Static4.aShort1 = 32767;
                        } else if (Static153.aShort22 > Static4.aShort1) {
                            Static4.aShort1 = Static153.aShort22;
                        }
                        Static55.aShort12 = (short) intStack[isp + 2];
                        if (Static55.aShort12 <= 0) {
                            Static55.aShort12 = 1;
                        }
                        Static131.aShort21 = (short) intStack[isp + 3];
                        if (Static131.aShort21 <= 0) {
                            Static131.aShort21 = 32767;
                        } else if (Static131.aShort21 < Static55.aShort12) {
                            Static131.aShort21 = Static55.aShort12;
                        }
                        continue;
                    }
                    if (local226 == 6203) {
                        Static115.method2314(Static280.aClass13_26.anInt445, 0, Static280.aClass13_26.anInt459, 0, false);
                        intStack[isp++] = Static166.anInt4055;
                        intStack[isp++] = Static245.anInt5377;
                        continue;
                    }
                    if (local226 == 6204) {
                        intStack[isp++] = Static263.aShort30;
                        intStack[isp++] = Static187.aShort27;
                        continue;
                    }
                    if (local226 == 6205) {
                        intStack[isp++] = Static178.aShort25;
                        intStack[isp++] = Static10.aShort9;
                        continue;
                    }
                } else if (local226 < 6400) {
                    if (local226 == 6300) {
                        intStack[isp++] = (int) (MonotonicTime.currentTime() / 60000L);
                        continue;
                    }
                    if (local226 == 6301) {
                        intStack[isp++] = (int) (MonotonicTime.currentTime() / 86400000L) - 11745;
                        continue;
                    }
                    if (local226 == 6302) {
                        isp -= 3;
                        local1052 = intStack[isp + 2];
                        local803 = intStack[isp + 1];
                        local809 = intStack[isp];
                        Static102.aCalendar2.clear();
                        Static102.aCalendar2.set(11, 12);
                        Static102.aCalendar2.set(local1052, local803, local809);
                        intStack[isp++] = (int) (Static102.aCalendar2.getTime().getTime() / 86400000L) - 11745;
                        continue;
                    }
                    if (local226 == 6303) {
                        Static102.aCalendar2.clear();
                        Static102.aCalendar2.setTime(new Date(MonotonicTime.currentTime()));
                        intStack[isp++] = Static102.aCalendar2.get(1);
                        continue;
                    }
                    if (local226 == 6304) {
                        local1552 = true;
                        isp--;
                        local809 = intStack[isp];
                        if (local809 < 0) {
                            local1552 = (local809 + 1) % 4 == 0;
                        } else if (local809 < 1582) {
                            local1552 = local809 % 4 == 0;
                        } else if (local809 % 4 != 0) {
                            local1552 = false;
                        } else if (local809 % 100 != 0) {
                            local1552 = true;
                        } else if (local809 % 400 != 0) {
                            local1552 = false;
                        }
                        intStack[isp++] = local1552 ? 1 : 0;
                        continue;
                    }
                } else if (local226 < 6500) {
                    if (local226 == 6405) {
                        intStack[isp++] = Static87.method1802() ? 1 : 0;
                        continue;
                    }
                    if (local226 == 6406) {
                        intStack[isp++] = Static267.method4527() ? 1 : 0;
                        continue;
                    }
                } else if (local226 < 6600) {
                    if (local226 == 6500) {
                        if (Client.state == 10 && Client.worldHopStep == 0 && Client.loginStep == 0 && Client.accountCreateStep == 0) {
                            intStack[isp++] = Static207.method3684() == -1 ? 0 : 1;
                            continue;
                        }
                        intStack[isp++] = 1;
                        continue;
                    }
                    @Pc(10247) WorldInfo local10247;
                    @Pc(10191) GWCWorld local10191;
                    if (local226 == 6501) {
                        local10191 = Static18.method556();
                        if (local10191 == null) {
                            intStack[isp++] = -1;
                            intStack[isp++] = 0;
                            stringStack[ssp++] = AUTO_EMPTY;
                            intStack[isp++] = 0;
                            stringStack[ssp++] = AUTO_EMPTY;
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local10191.anInt382;
                            intStack[isp++] = local10191.anInt381;
                            stringStack[ssp++] = local10191.aClass100_69;
                            local10247 = local10191.method445();
                            intStack[isp++] = local10247.anInt1739;
                            stringStack[ssp++] = local10247.aClass100_378;
                            intStack[isp++] = local10191.anInt379;
                        }
                        continue;
                    }
                    if (local226 == 6502) {
                        local10191 = GWCWorld.method1821();
                        if (local10191 == null) {
                            intStack[isp++] = -1;
                            intStack[isp++] = 0;
                            stringStack[ssp++] = AUTO_EMPTY;
                            intStack[isp++] = 0;
                            stringStack[ssp++] = AUTO_EMPTY;
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local10191.anInt382;
                            intStack[isp++] = local10191.anInt381;
                            stringStack[ssp++] = local10191.aClass100_69;
                            local10247 = local10191.method445();
                            intStack[isp++] = local10247.anInt1739;
                            stringStack[ssp++] = local10247.aClass100_378;
                            intStack[isp++] = local10191.anInt379;
                        }
                        continue;
                    }
                    if (local226 == 6503) {
                        isp--;
                        local809 = intStack[isp];
                        if (Client.state == 10 && Client.worldHopStep == 0 && Client.loginStep == 0 && Client.accountCreateStep == 0) {
                            intStack[isp++] = Static176.method3303(local809) ? 1 : 0;
                            continue;
                        }
                        intStack[isp++] = 0;
                        continue;
                    }
                    if (local226 == 6504) {
                        isp--;
                        Static164.anInt3988 = intStack[isp];
                        Static203.method3663(GameShell.signlink);
                        continue;
                    }
                    if (local226 == 6505) {
                        intStack[isp++] = Static164.anInt3988;
                        continue;
                    }
                    if (local226 == 6506) {
                        isp--;
                        local809 = intStack[isp];
                        @Pc(10440) GWCWorld local10440 = Static54.method1310(local809);
                        if (local10440 == null) {
                            intStack[isp++] = -1;
                            stringStack[ssp++] = AUTO_EMPTY;
                            intStack[isp++] = 0;
                            stringStack[ssp++] = AUTO_EMPTY;
                            intStack[isp++] = 0;
                        } else {
                            intStack[isp++] = local10440.anInt381;
                            stringStack[ssp++] = local10440.aClass100_69;
                            @Pc(10458) WorldInfo local10458 = local10440.method445();
                            intStack[isp++] = local10458.anInt1739;
                            stringStack[ssp++] = local10458.aClass100_378;
                            intStack[isp++] = local10440.anInt379;
                        }
                        continue;
                    }
                    if (local226 == 6507) {
                        isp -= 4;
                        local1052 = intStack[isp + 2];
                        local809 = intStack[isp];
                        local7566 = intStack[isp + 3] == 1;
                        local1552 = intStack[isp + 1] == 1;
                        Static228.method3908(local1052, local1552, local809, local7566);
                        continue;
                    }
                } else if (local226 < 6700) {
                    if (local226 == 6600) {
                        isp--;
                        Static33.aBoolean63 = intStack[isp] == 1;
                        Static203.method3663(GameShell.signlink);
                        continue;
                    }
                    if (local226 == 6601) {
                        intStack[isp++] = Static33.aBoolean63 ? 1 : 0;
                        continue;
                    }
                }
                throw new IllegalStateException();
			}
		} catch (@Pc(14378) Exception ex) {
			if (script.name == null) {
				if (Client.modewhere != 0) {
					Client.addChat(AUTO_EMPTY, 0, AUTO_CS_ERROR_LIVE);
				}

				JagException.report("CS2 - scr:" + script.key + " op:" + lastOp, ex);
			} else {
				@Pc(14385) JagString builder = JagString.newStringBuilder(30);

				builder.append(AUTO_CS_IN).append(script.name);

				for (int i = fp - 1; i >= 0; i--) {
					builder.append(AUTO_CS_VIA).append(frames[i].script.name);
				}

				if (lastOp == 40) {
					int procId = intOperands[pc];
					builder.append(AUTO_NON_EXISTANT).append(JagString.parseInt(procId));
				}

				if (Client.modewhere != 0) {
					Client.addChat(AUTO_EMPTY, 0, JagString.join(new JagString[] {AUTO_CS_ERROR, script.name}));
				}

				JagException.report("CS2 - scr:" + script.key + " op:" + lastOp + new String(builder.builderToString()), ex);
			}
		}
	}

	@OriginalMember(owner = "client!fn", name = "c", descriptor = "(II)V")
	public static void method1626(@OriginalArg(0) int arg0) {
		if (arg0 == -1 || !IfType.openInterface(arg0)) {
			return;
		}
		@Pc(31) IfType[] local31 = IfType.list[arg0];
		for (@Pc(33) int local33 = 0; local33 < local31.length; local33++) {
			@Pc(41) IfType local41 = local31[local33];
			if (local41.onload != null) {
				@Pc(50) HookReq local50 = new HookReq();
				local50.onop = local41.onload;
				local50.component = local41;
				executeScript(2000000, local50);
			}
		}
	}

	@OriginalMember(owner = "client!gi", name = "a", descriptor = "(ILclient!jl;)V")
	public static void executeScript(@OriginalArg(1) HookReq arg0) {
		executeScript(200000, arg0);
	}
}
