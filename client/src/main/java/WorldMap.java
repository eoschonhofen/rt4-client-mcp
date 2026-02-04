import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WorldMap {
    @OriginalMember(owner = "client!wa", name = "ub", descriptor = "Lclient!bn;")
    public static Map aClass3_Sub2_Sub4_2;
    @OriginalMember(owner = "client!dc", name = "O", descriptor = "I")
    public static int stage = 0;
    @OriginalMember(owner = "client!je", name = "W", descriptor = "Lclient!ve;")
    public static Js5 aClass153_44;
    @OriginalMember(owner = "client!mh", name = "S", descriptor = "I")
    public static int anInt3846;
    @OriginalMember(owner = "client!aa", name = "j", descriptor = "I")
    public static int anInt13;
    @OriginalMember(owner = "client!oi", name = "m", descriptor = "I")
    public static int anInt4296;
    @OriginalMember(owner = "client!dl", name = "e", descriptor = "I")
    public static int anInt1449;
    @OriginalMember(owner = "client!gj", name = "r", descriptor = "F")
    public static float aFloat3;
    @OriginalMember(owner = "client!km", name = "uc", descriptor = "F")
    public static float aFloat14;
    @OriginalMember(owner = "client!bc", name = "W", descriptor = "I")
    public static int anInt435;
    @OriginalMember(owner = "client!cd", name = "u", descriptor = "I")
    public static int anInt919;
    @OriginalMember(owner = "client!lf", name = "b", descriptor = "[I")
    public static int[] anIntArray330;
    @OriginalMember(owner = "client!hb", name = "v", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray8;
    @OriginalMember(owner = "client!fi", name = "m", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray7;
    @OriginalMember(owner = "client!gj", name = "i", descriptor = "[[[I")
    public static int[][][] anIntArrayArrayArray3;
    @OriginalMember(owner = "client!ck", name = "J", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray3;
    @OriginalMember(owner = "client!uc", name = "d", descriptor = "[[[I")
    public static int[][][] anIntArrayArrayArray17;
    @OriginalMember(owner = "client!si", name = "R", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray12;
    @OriginalMember(owner = "client!jl", name = "I", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray10;
    @OriginalMember(owner = "client!eh", name = "g", descriptor = "[[[I")
    public static int[][][] anIntArrayArrayArray5;
    @OriginalMember(owner = "client!qh", name = "a", descriptor = "Lclient!se;")
    public static MapElementList aClass134_1;
    @OriginalMember(owner = "client!we", name = "v", descriptor = "Lclient!fd;")
    public static WorldMapFont f11;
    @OriginalMember(owner = "client!ma", name = "q", descriptor = "Lclient!fd;")
    public static WorldMapFont f12;
    @OriginalMember(owner = "client!nf", name = "d", descriptor = "Lclient!fd;")
    public static WorldMapFont f14;
    @OriginalMember(owner = "client!kc", name = "n", descriptor = "Lclient!fd;")
    public static WorldMapFont f17;
    @OriginalMember(owner = "client!qh", name = "d", descriptor = "Lclient!fd;")
    public static WorldMapFont f19;
    @OriginalMember(owner = "client!kc", name = "C", descriptor = "Lclient!fd;")
    public static WorldMapFont f22;
    @OriginalMember(owner = "client!wb", name = "l", descriptor = "Lclient!fd;")
    public static WorldMapFont f26;
    @OriginalMember(owner = "client!mj", name = "n", descriptor = "Lclient!fd;")
    public static WorldMapFont f30;

    @OriginalMember(owner = "client!pa", name = "d", descriptor = "(I)V")
    public static void method3413() {
        if (aClass3_Sub2_Sub4_2 == null) {
            return;
        }
        if (stage < 10) {
            if (!aClass153_44.method4489(aClass3_Sub2_Sub4_2.aClass100_138)) {
                stage = Client.worldmap.method4478(aClass3_Sub2_Sub4_2.aClass100_138) / 10;
                return;
            }
            Client.method84();
            stage = 10;
        }
        if (stage == 10) {
            anInt3846 = aClass3_Sub2_Sub4_2.anInt763 >> 6 << 6;
            anInt13 = aClass3_Sub2_Sub4_2.anInt771 >> 6 << 6;
            anInt4296 = (aClass3_Sub2_Sub4_2.anInt758 >> 6 << 6) + 64 - anInt13;
            anInt1449 = (aClass3_Sub2_Sub4_2.anInt770 >> 6 << 6) + 64 - anInt3846;
            if (aClass3_Sub2_Sub4_2.anInt772 == 37) {
                aFloat3 = 3.0F;
                aFloat14 = 3.0F;
            } else if (aClass3_Sub2_Sub4_2.anInt772 == 50) {
                aFloat3 = 4.0F;
                aFloat14 = 4.0F;
            } else if (aClass3_Sub2_Sub4_2.anInt772 == 75) {
                aFloat3 = 6.0F;
                aFloat14 = 6.0F;
            } else if (aClass3_Sub2_Sub4_2.anInt772 == 100) {
                aFloat3 = 8.0F;
                aFloat14 = 8.0F;
            } else if (aClass3_Sub2_Sub4_2.anInt772 == 200) {
                aFloat3 = 16.0F;
                aFloat14 = 16.0F;
            } else {
                aFloat3 = 8.0F;
                aFloat14 = 8.0F;
            }
            @Pc(144) int local144 = (Client.localPlayer.x >> 7) + Client.mapBuildBaseX - anInt3846;
            @Pc(153) int local153 = local144 + (int) (Math.random() * 10.0D) - 5;
            @Pc(168) int local168 = anInt13 + anInt4296 - Client.mapBuildBaseZ - (Client.localPlayer.z >> 7) - 1;
            @Pc(177) int local177 = local168 + (int) (Math.random() * 10.0D) - 5;
            if (local153 >= 0 && anInt1449 > local153 && local177 >= 0 && local177 < anInt4296) {
                anInt435 = local153;
                anInt919 = local177;
            } else {
                anInt919 = anInt13 + anInt4296 - aClass3_Sub2_Sub4_2.anInt764 * 64 - 1;
                anInt435 = aClass3_Sub2_Sub4_2.anInt769 * 64 - anInt3846;
            }
            method965();
            anIntArray330 = new int[FloType.anInt2510 + 1];
            @Pc(235) int local235 = anInt4296 >> 6;
            @Pc(239) int local239 = anInt1449 >> 6;
            aByteArrayArrayArray8 = new byte[local239][local235][];
            @Pc(249) int local249 = ClientBuild.ligOff >> 2 << 10;
            aByteArrayArrayArray7 = new byte[local239][local235][];
            anIntArrayArrayArray3 = new int[local239][local235][];
            aByteArrayArrayArray3 = new byte[local239][local235][];
            anIntArrayArrayArray17 = new int[local239][local235][];
            aByteArrayArrayArray12 = new byte[local239][local235][];
            @Pc(273) int local273 = ClientBuild.hueOff >> 1;
            aByteArrayArrayArray10 = new byte[local239][local235][];
            anIntArrayArrayArray5 = new int[local239][local235][];
            method1549(local273, local249);
            stage = 20;
        } else if (stage == 20) {
            method868(new Packet(aClass153_44.getFile(Static166.aClass100_779, aClass3_Sub2_Sub4_2.aClass100_138)));
            stage = 30;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 30) {
            method3998(new Packet(aClass153_44.getFile(Static4.aClass100_7, aClass3_Sub2_Sub4_2.aClass100_138)));
            stage = 40;
            GameShell.doneslowupdate();
        } else if (stage == 40) {
            method3980(new Packet(aClass153_44.getFile(Static73.aClass100_455, aClass3_Sub2_Sub4_2.aClass100_138)));
            stage = 50;
            GameShell.doneslowupdate();
        } else if (stage == 50) {
            method3166(new Packet(aClass153_44.getFile(Static42.aClass100_331, aClass3_Sub2_Sub4_2.aClass100_138)));
            stage = 60;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 60) {
            if (aClass153_44.method4497(JagString.join(new JagString[]{aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}))) {
                if (!aClass153_44.method4489(JagString.join(new JagString[]{aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}))) {
                    return;
                }
                aClass134_1 = Static140.method2711(JagString.join(new JagString[]{aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}), aClass153_44);
            } else {
                aClass134_1 = new MapElementList(0);
            }
            stage = 70;
            GameShell.doneslowupdate();
        } else if (stage == 70) {
            f11 = new WorldMapFont(11, true, GameShell.canvas);
            stage = 73;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 73) {
            f12 = new WorldMapFont(12, true, GameShell.canvas);
            stage = 76;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 76) {
            f14 = new WorldMapFont(14, true, GameShell.canvas);
            stage = 79;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 79) {
            f17 = new WorldMapFont(17, true, GameShell.canvas);
            stage = 82;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 82) {
            f19 = new WorldMapFont(19, true, GameShell.canvas);
            stage = 85;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 85) {
            f22 = new WorldMapFont(22, true, GameShell.canvas);
            stage = 88;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else if (stage == 88) {
            f26 = new WorldMapFont(26, true, GameShell.canvas);
            stage = 91;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
        } else {
            f30 = new WorldMapFont(30, true, GameShell.canvas);
            stage = 100;
            Client.preventTimeout(true);
            GameShell.doneslowupdate();
            System.gc();
        }
    }

    @OriginalMember(owner = "client!fi", name = "a", descriptor = "(III)V")
    public static void method1549(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        for (@Pc(11) int local11 = 0; local11 < FloType.anInt2510; local11++) {
            @Pc(18) FloType local18 = FloType.method4395(local11);
            if (local18 != null) {
                @Pc(24) int local24 = local18.anInt5892;
                if (local24 >= 0 && !Pix3D.anInterface1_2.method3236(local24)) {
                    local24 = -1;
                }
                @Pc(53) int local53;
                @Pc(66) int local66;
                @Pc(72) int local72;
                @Pc(95) int local95;
                if (local18.anInt5894 >= 0) {
                    local66 = local18.anInt5894;
                    local72 = (local66 & 0x7F) + arg0;
                    if (local72 < 0) {
                        local72 = 0;
                    } else if (local72 > 127) {
                        local72 = 127;
                    }
                    local95 = (local66 & 0x380) + (arg1 + local66 & 0xFC00) + local72;
                    local53 = Pix3D.anIntArray220[Static230.method3949(local95, 96)];
                } else if (local24 >= 0) {
                    local53 = Pix3D.anIntArray220[Static230.method3949(Pix3D.anInterface1_2.method3234(local24), 96)];
                } else if (local18.anInt5899 == -1) {
                    local53 = -1;
                } else {
                    local66 = local18.anInt5899;
                    local72 = arg0 + (local66 & 0x7F);
                    if (local72 < 0) {
                        local72 = 0;
                    } else if (local72 > 127) {
                        local72 = 127;
                    }
                    local95 = local72 + (local66 & 0x380) + (local66 + arg1 & 0xFC00);
                    local53 = Pix3D.anIntArray220[Static230.method3949(local95, 96)];
                }
                anIntArray330[local11 + 1] = local53;
            }
        }
    }

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(BLclient!wa;)V")
    public static void method868(@OriginalArg(1) Packet arg0) {
        @Pc(13) int local13 = Static266.anInt5338 >> 1;
        @Pc(19) int local19 = Static131.anInt3254 >> 2 << 10;
        @Pc(23) byte[][] local23 = new byte[anInt1449][anInt4296];
        @Pc(33) int local33;
        @Pc(102) int local102;
        @Pc(114) int local114;
        while (arg0.pos < arg0.data.length) {
            @Pc(31) int local31 = 0;
            local33 = 0;
            @Pc(35) boolean local35 = false;
            if (arg0.g1() == 1) {
                local33 = arg0.g1();
                local31 = arg0.g1();
                local35 = true;
            }
            @Pc(57) int local57 = arg0.g1();
            @Pc(61) int local61 = arg0.g1();
            @Pc(68) int local68 = local57 * 64 - anInt3846;
            @Pc(78) int local78 = anInt4296 + anInt13 - local61 * 64 - 1;
            if (local68 >= 0 && local78 - 63 >= 0 && anInt1449 > local68 + 63 && anInt4296 > local78) {
                for (local102 = 0; local102 < 64; local102++) {
                    @Pc(112) byte[] local112 = local23[local68 + local102];
                    for (local114 = 0; local114 < 64; local114++) {
                        if (!local35 || local102 >= local33 * 8 && local33 * 8 + 8 > local102 && local114 >= local31 * 8 && local114 < local31 * 8 + 8) {
                            local112[local78 - local114] = arg0.g1b();
                        }
                    }
                }
            } else if (local35) {
                arg0.pos += 64;
            } else {
                arg0.pos += 4096;
            }
        }
        @Pc(175) int local175 = anInt1449;
        local33 = anInt4296;
        @Pc(180) int[] local180 = new int[local33];
        @Pc(183) int[] local183 = new int[local33];
        @Pc(186) int[] local186 = new int[local33];
        @Pc(189) int[] local189 = new int[local33];
        @Pc(192) int[] local192 = new int[local33];
        for (local102 = -5; local102 < local175; local102++) {
            @Pc(225) int local225;
            @Pc(293) int local293;
            for (@Pc(203) int local203 = 0; local203 < local33; local203++) {
                local114 = local102 + 5;
                @Pc(272) int local272;
                if (local175 > local114) {
                    local225 = local23[local114][local203] & 0xFF;
                    if (local225 > 0) {
                        @Pc(236) FluType local236 = FluType.list(local225 - 1);
                        local183[local203] += local236.chroma;
                        local180[local203] += local236.hue;
                        local186[local203] += local236.saturation;
                        local189[local203] += local236.luminance;
                        local272 = local192[local203]++;
                    }
                }
                local225 = local102 - 5;
                if (local225 >= 0) {
                    local293 = local23[local225][local203] & 0xFF;
                    if (local293 > 0) {
                        @Pc(302) FluType local302 = FluType.list(local293 - 1);
                        local183[local203] -= local302.chroma;
                        local180[local203] -= local302.hue;
                        local186[local203] -= local302.saturation;
                        local189[local203] -= local302.luminance;
                        local272 = local192[local203]--;
                    }
                }
            }
            if (local102 >= 0) {
                @Pc(355) int[][] local355 = anIntArrayArrayArray17[local102 >> 6];
                local114 = 0;
                local225 = 0;
                @Pc(361) int local361 = 0;
                @Pc(363) int local363 = 0;
                local293 = 0;
                for (@Pc(367) int local367 = -5; local367 < local33; local367++) {
                    @Pc(378) int local378 = local367 + 5;
                    if (local33 > local378) {
                        local363 += local192[local378];
                        local225 += local180[local378];
                        local293 += local186[local378];
                        local114 += local183[local378];
                        local361 += local189[local378];
                    }
                    @Pc(415) int local415 = local367 - 5;
                    if (local415 >= 0) {
                        local293 -= local186[local415];
                        local361 -= local189[local415];
                        local114 -= local183[local415];
                        local363 -= local192[local415];
                        local225 -= local180[local415];
                    }
                    if (local367 >= 0 && local363 > 0) {
                        @Pc(462) int[] local462 = local355[local367 >> 6];
                        @Pc(480) int local480 = local361 == 0 ? 0 : Static54.getTable(local293 / local363, local225 / local363, local114 * 256 / local361);
                        if (local23[local102][local367] != 0) {
                            if (local462 == null) {
                                local462 = local355[local367 >> 6] = new int[4096];
                            }
                            @Pc(519) int local519 = local13 + (local480 & 0x7F);
                            if (local519 < 0) {
                                local519 = 0;
                            } else if (local519 > 127) {
                                local519 = 127;
                            }
                            @Pc(541) int local541 = local519 + (local480 & 0x380) + (local480 + local19 & 0xFC00);
                            local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = Pix3D.anIntArray220[ClientBuild.method1814(96, local541)];
                        } else if (local462 != null) {
                            local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = 0;
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!dk", name = "a", descriptor = "(Lclient!wa;Z)V")
    public static void method3998(@OriginalArg(0) Packet arg0) {
        label87: while (true) {
            if (arg0.pos < arg0.data.length) {
                @Pc(22) int local22 = 0;
                @Pc(24) boolean local24 = false;
                @Pc(26) int local26 = 0;
                if (arg0.g1() == 1) {
                    local24 = true;
                    local22 = arg0.g1();
                    local26 = arg0.g1();
                }
                @Pc(46) int local46 = arg0.g1();
                @Pc(50) int local50 = arg0.g1();
                @Pc(62) int local62 = anInt13 + anInt4296 - local50 * 64 - 1;
                @Pc(69) int local69 = local46 * 64 - anInt3846;
                @Pc(147) byte local147;
                @Pc(91) int local91;
                if (local69 >= 0 && local62 - 63 >= 0 && local69 + 63 < anInt1449 && local62 < anInt4296) {
                    local91 = local69 >> 6;
                    @Pc(95) int local95 = local62 >> 6;
                    @Pc(97) int local97 = 0;
                    while (true) {
                        if (local97 >= 64) {
                            continue label87;
                        }
                        for (@Pc(104) int local104 = 0; local104 < 64; local104++) {
                            if (!local24 || local97 >= local22 * 8 && local97 < local22 * 8 + 8 && local104 >= local26 * 8 && local104 < local26 * 8 + 8) {
                                local147 = arg0.g1b();
                                if (local147 != 0) {
                                    if (aByteArrayArrayArray3[local91][local95] == null) {
                                        aByteArrayArrayArray3[local91][local95] = new byte[4096];
                                    }
                                    aByteArrayArrayArray3[local91][local95][local97 + (63 - local104 << 6)] = local147;
                                    @Pc(186) byte local186 = arg0.g1b();
                                    if (aByteArrayArrayArray8[local91][local95] == null) {
                                        aByteArrayArrayArray8[local91][local95] = new byte[4096];
                                    }
                                    aByteArrayArrayArray8[local91][local95][local97 + (63 - local104 << 6)] = local186;
                                }
                            }
                        }
                        local97++;
                    }
                }
                local91 = 0;
                while (true) {
                    if ((local24 ? 64 : 4096) <= local91) {
                        continue label87;
                    }
                    local147 = arg0.g1b();
                    if (local147 != 0) {
                        arg0.pos++;
                    }
                    local91++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "client!sk", name = "a", descriptor = "(ILclient!wa;)V")
    public static void method3980(@OriginalArg(1) Packet arg0) {
        label83: while (true) {
            if (arg0.pos < arg0.data.length) {
                @Pc(23) int local23 = 0;
                @Pc(25) boolean local25 = false;
                @Pc(27) int local27 = 0;
                if (arg0.g1() == 1) {
                    local25 = true;
                    local23 = arg0.g1();
                    local27 = arg0.g1();
                }
                @Pc(46) int local46 = arg0.g1();
                @Pc(50) int local50 = arg0.g1();
                @Pc(57) int local57 = local46 * 64 - anInt3846;
                @Pc(68) int local68 = anInt4296 + anInt13 - local50 * 64 - 1;
                @Pc(146) byte local146;
                @Pc(96) int local96;
                if (local57 >= 0 && local68 - 63 >= 0 && anInt1449 > local57 + 63 && local68 < anInt4296) {
                    local96 = local57 >> 6;
                    @Pc(100) int local100 = local68 >> 6;
                    @Pc(102) int local102 = 0;
                    while (true) {
                        if (local102 >= 64) {
                            continue label83;
                        }
                        for (@Pc(107) int local107 = 0; local107 < 64; local107++) {
                            if (!local25 || local23 * 8 <= local102 && local23 * 8 + 8 > local102 && local107 >= local27 * 8 && local27 * 8 + 8 > local107) {
                                local146 = arg0.g1b();
                                if (local146 != 0) {
                                    if (aByteArrayArrayArray12[local96][local100] == null) {
                                        aByteArrayArrayArray12[local96][local100] = new byte[4096];
                                    }
                                    aByteArrayArrayArray12[local96][local100][(63 - local107 << 6) + local102] = local146;
                                    @Pc(182) byte local182 = arg0.g1b();
                                    if (aByteArrayArrayArray10[local96][local100] == null) {
                                        aByteArrayArrayArray10[local96][local100] = new byte[4096];
                                    }
                                    aByteArrayArrayArray10[local96][local100][local102 + (63 - local107 << 6)] = local182;
                                }
                            }
                        }
                        local102++;
                    }
                }
                local96 = 0;
                while (true) {
                    if (local96 >= (local25 ? 64 : 4096)) {
                        continue label83;
                    }
                    local146 = arg0.g1b();
                    if (local146 != 0) {
                        arg0.pos++;
                    }
                    local96++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "client!nc", name = "a", descriptor = "(BLclient!wa;)V")
    public static void method3166(@OriginalArg(1) Packet arg0) {
        label123: while (true) {
            if (arg0.data.length > arg0.pos) {
                @Pc(17) boolean local17 = false;
                @Pc(19) int local19 = 0;
                @Pc(21) int local21 = 0;
                if (arg0.g1() == 1) {
                    local19 = arg0.g1();
                    local17 = true;
                    local21 = arg0.g1();
                }
                @Pc(42) int local42 = arg0.g1();
                @Pc(46) int local46 = arg0.g1();
                @Pc(53) int local53 = local42 * 64 - anInt3846;
                @Pc(65) int local65 = anInt13 + anInt4296 - local46 * 64 - 1;
                @Pc(84) int local84;
                @Pc(95) int local95;
                if (local53 >= 0 && local65 - 63 >= 0 && anInt1449 > local53 + 63 && local65 < anInt4296) {
                    local84 = local53 >> 6;
                    local95 = local65 >> 6;
                    @Pc(150) int local150 = 0;
                    while (true) {
                        if (local150 >= 64) {
                            continue label123;
                        }
                        for (@Pc(155) int local155 = 0; local155 < 64; local155++) {
                            if (!local17 || local19 * 8 <= local150 && local150 < local19 * 8 + 8 && local155 >= local21 * 8 && local155 < local21 * 8 + 8) {
                                @Pc(202) int local202 = arg0.g1();
                                if (local202 != 0) {
                                    @Pc(214) int local214;
                                    if ((local202 & 0x1) == 1) {
                                        local214 = arg0.g1();
                                        if (aByteArrayArrayArray7[local84][local95] == null) {
                                            aByteArrayArrayArray7[local84][local95] = new byte[4096];
                                        }
                                        aByteArrayArrayArray7[local84][local95][local150 + (63 - local155 << 6)] = (byte) local214;
                                    }
                                    if ((local202 & 0x2) == 2) {
                                        local214 = arg0.g3();
                                        if (anIntArrayArrayArray5[local84][local95] == null) {
                                            anIntArrayArrayArray5[local84][local95] = new int[4096];
                                        }
                                        anIntArrayArrayArray5[local84][local95][(63 - local155 << 6) + local150] = local214;
                                    }
                                    if ((local202 & 0x4) == 4) {
                                        local214 = arg0.g3();
                                        if (anIntArrayArrayArray3[local84][local95] == null) {
                                            anIntArrayArrayArray3[local84][local95] = new int[4096];
                                        }
                                        local214--;
                                        @Pc(312) LocType local312 = LocType.list(local214);
                                        if (local312.anIntArray380 != null) {
                                            local312 = local312.getMultiLoc();
                                            if (local312 == null || local312.anInt4400 == -1) {
                                                continue;
                                            }
                                        }
                                        anIntArrayArrayArray3[local84][local95][(63 - local155 << 6) + local150] = local312.id + 1;
                                        @Pc(353) MapElement local353 = new MapElement();
                                        local353.anInt4308 = local312.anInt4400;
                                        local353.anInt4307 = local53;
                                        local353.anInt4314 = local65;
                                        Static145.aClass69_84.push(local353);
                                    }
                                }
                            }
                        }
                        local150++;
                    }
                }
                local84 = 0;
                while (true) {
                    if (local84 >= (local17 ? 64 : 4096)) {
                        continue label123;
                    }
                    local95 = arg0.g1();
                    if (local95 != 0) {
                        if ((local95 & 0x1) == 1) {
                            arg0.pos++;
                        }
                        if ((local95 & 0x2) == 2) {
                            arg0.pos += 2;
                        }
                        if ((local95 & 0x4) == 4) {
                            arg0.pos += 3;
                        }
                    }
                    local84++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "client!me", name = "a", descriptor = "(IB)V")
    public static void method2940(@OriginalArg(0) int arg0) {
        Static217.anInt4901 = -1;
        if (arg0 == 37) {
            aFloat14 = 3.0F;
        } else if (arg0 == 50) {
            aFloat14 = 4.0F;
        } else if (arg0 == 75) {
            aFloat14 = 6.0F;
        } else if (arg0 == 100) {
            aFloat14 = 8.0F;
        } else if (arg0 == 200) {
            aFloat14 = 16.0F;
        }
        Static217.anInt4901 = -1;
    }

    @OriginalMember(owner = "client!bb", name = "a", descriptor = "(I)V")
    public static void method447() {
        if (aFloat3 < aFloat14) {
            aFloat3 = (float) ((double) aFloat3 + (double) aFloat3 / 30.0D);
            if (aFloat14 < aFloat3) {
                aFloat3 = aFloat14;
            }
            method965();
        } else if (aFloat14 < aFloat3) {
            aFloat3 = (float) ((double) aFloat3 - (double) aFloat3 / 30.0D);
            if (aFloat14 > aFloat3) {
                aFloat3 = aFloat14;
            }
            method965();
        }
        if (Static142.anInt3482 == -1 || Static217.anInt4901 == -1) {
            return;
        }
        @Pc(60) int local60 = Static142.anInt3482 - anInt435;
        if (local60 < 2 || local60 > 2) {
            local60 >>= 0x4;
        }
        @Pc(78) int local78 = Static217.anInt4901 - anInt919;
        if (local78 < 2 || local78 > 2) {
            local78 >>= 0x4;
        }
        anInt919 -= -local78;
        anInt435 += local60;
        if (local60 == 0 && local78 == 0) {
            Static142.anInt3482 = -1;
            Static217.anInt4901 = -1;
        }
        method965();
    }

    @OriginalMember(owner = "client!cn", name = "a", descriptor = "(BIIIIIIII)V")
    public static void method959(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
        for (@Pc(11) int local11 = 0; local11 < aClass134_1.anInt5074; local11++) {
            if (aClass134_1.method3890(local11)) {
                @Pc(32) int local32 = aClass134_1.aShortArray73[local11] - anInt3846;
                @Pc(43) int local43 = anInt13 + anInt4296 - aClass134_1.aShortArray72[local11] - 1;
                @Pc(59) int local59 = arg0 + (arg3 - arg0) * (local32 - arg2) / (arg6 - arg2);
                @Pc(64) int local64 = aClass134_1.method3894(local11);
                @Pc(80) int local80 = (arg7 - arg1) * (local43 - arg5) / (arg4 - arg5) + arg1;
                @Pc(82) int local82 = 16777215;
                @Pc(84) WorldMapFont local84 = null;
                if (local64 == 0) {
                    if ((double) aFloat3 == 3.0D) {
                        local84 = f11;
                    }
                    if ((double) aFloat3 == 4.0D) {
                        local84 = f12;
                    }
                    if ((double) aFloat3 == 6.0D) {
                        local84 = f14;
                    }
                    if ((double) aFloat3 >= 8.0D) {
                        local84 = f17;
                    }
                }
                if (local64 == 1) {
                    if ((double) aFloat3 == 3.0D) {
                        local84 = f14;
                    }
                    if ((double) aFloat3 == 4.0D) {
                        local84 = f17;
                    }
                    if ((double) aFloat3 == 6.0D) {
                        local84 = f19;
                    }
                    if ((double) aFloat3 >= 8.0D) {
                        local84 = f22;
                    }
                }
                if (local64 == 2) {
                    if ((double) aFloat3 == 3.0D) {
                        local84 = f19;
                    }
                    local82 = 16755200;
                    if ((double) aFloat3 == 4.0D) {
                        local84 = f22;
                    }
                    if ((double) aFloat3 == 6.0D) {
                        local84 = f26;
                    }
                    if ((double) aFloat3 >= 8.0D) {
                        local84 = f30;
                    }
                }
                if (aClass134_1.anIntArray444[local11] != -1) {
                    local82 = aClass134_1.anIntArray444[local11];
                }
                if (local84 != null) {
                    @Pc(211) int local211 = Static114.aClass3_Sub2_Sub9_42.method2867(aClass134_1.aClass100Array153[local11], null, Static45.aClass100Array53);
                    local80 -= local84.method1503() * (local211 - 1) / 2;
                    local80 += local84.method1511() / 2;
                    for (@Pc(231) int local231 = 0; local231 < local211; local231++) {
                        @Pc(242) JagString local242 = Static45.aClass100Array53[local231];
                        if (local211 - 1 > local231) {
                            local242.method3133(local242.length() - 4);
                        }
                        local84.method1508(local242, local59, local80, local82);
                        local80 += local84.method1503();
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!cn", name = "e", descriptor = "(B)V")
    public static void method965() {
        if (anInt435 < 0) {
            Static217.anInt4901 = -1;
            anInt435 = 0;
            Static142.anInt3482 = -1;
        }
        if (anInt435 > anInt1449) {
            Static217.anInt4901 = -1;
            anInt435 = anInt1449;
            Static142.anInt3482 = -1;
        }
        if (anInt919 < 0) {
            Static142.anInt3482 = -1;
            Static217.anInt4901 = -1;
            anInt919 = 0;
        }
        if (anInt4296 < anInt919) {
            anInt919 = anInt4296;
            Static217.anInt4901 = -1;
            Static142.anInt3482 = -1;
        }
    }

    @OriginalMember(owner = "client!hc", name = "d", descriptor = "(I)I")
    public static int method1874() {
        if ((double) aFloat14 == 3.0D) {
            return 37;
        } else if ((double) aFloat14 == 4.0D) {
            return 50;
        } else if ((double) aFloat14 == 6.0D) {
            return 75;
        } else if ((double) aFloat14 == 8.0D) {
            return 100;
        } else {
            return 200;
        }
    }

    @OriginalMember(owner = "client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
    public static int method1879(@OriginalArg(0) JagString arg0) {
        if (aClass134_1 == null || arg0.length() == 0) {
            return -1;
        }
        for (@Pc(20) int local20 = 0; local20 < aClass134_1.anInt5074; local20++) {
            if (aClass134_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).equalsInner(arg0)) {
                return local20;
            }
        }
        return -1;
    }

    @OriginalMember(owner = "client!rb", name = "a", descriptor = "(Lclient!wa;Z)Lclient!bn;")
    public static Map method3713(@OriginalArg(0) Packet arg0) {
        @Pc(35) Map local35 = new Map(arg0.gjstr(), arg0.gjstr(), arg0.g2(), arg0.g2(), arg0.g4(), arg0.g1() == 1, arg0.g1());
        @Pc(39) int local39 = arg0.g1();
        for (@Pc(41) int local41 = 0; local41 < local39; local41++) {
            local35.aClass69_23.push(new MapChunk(arg0.g2(), arg0.g2(), arg0.g2(), arg0.g2()));
        }
        local35.method665();
        return local35;
    }

    @OriginalMember(owner = "client!ni", name = "a", descriptor = "(ILclient!na;)I")
    public static int method3218(@OriginalArg(1) JagString arg0) {
        if (aClass134_1 == null || arg0.length() == 0) {
            return -1;
        }
        for (@Pc(20) int local20 = 0; local20 < aClass134_1.anInt5074; local20++) {
            if (aClass134_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).method3142(arg0)) {
                return local20;
            }
        }
        return -1;
    }

    @OriginalMember(owner = "client!sm", name = "a", descriptor = "(IIIIIIIIIII)V")
    public static void method3991(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(8) int arg5, @OriginalArg(9) int arg6, @OriginalArg(10) int arg7) {
        @Pc(9) int local9 = arg2 - arg4;
        @Pc(11) int local11 = -1;
        if (Static201.anInt1864 > 0) {
            if (Static91.anInt2428 <= 10) {
                local11 = Static91.anInt2428 * 5;
            } else {
                local11 = 50 - (Static91.anInt2428 - 10) * 5;
            }
        }
        @Pc(39) int local39 = arg1 - arg6;
        @Pc(43) int local43 = 983040 / arg5;
        @Pc(47) int local47 = 983040 / arg3;
        for (@Pc(50) int local50 = -local43; local50 < local9 + local43; local50++) {
            @Pc(65) int local65 = local50 * arg5 >> 16;
            @Pc(75) int local75 = arg5 * (local50 + 1) >> 16;
            @Pc(80) int local80 = local75 - local65;
            if (local80 > 0) {
                @Pc(91) int local91 = arg4 + local50 >> 6;
                local65 += arg0;
                if (local91 >= 0 && local91 <= anIntArrayArrayArray3.length - 1) {
                    @Pc(116) int[][] local116 = anIntArrayArrayArray3[local91];
                    for (@Pc(119) int local119 = -local47; local119 < local39 + local47; local119++) {
                        @Pc(136) int local136 = arg3 * (local119 + 1) >> 16;
                        @Pc(144) int local144 = local119 * arg3 >> 16;
                        @Pc(149) int local149 = local136 - local144;
                        if (local149 > 0) {
                            local144 += arg7;
                            @Pc(163) int local163 = arg6 + local119 >> 6;
                            if (local163 >= 0 && local163 <= local116.length - 1 && local116[local163] != null) {
                                @Pc(203) int local203 = (local50 + arg4 & 0x3F) + ((arg6 + local119 & 0x3F) << 6);
                                @Pc(209) int local209 = local116[local163][local203];
                                if (local209 != 0) {
                                    @Pc(222) LocType local222 = LocType.list(local209 - 1);
                                    if (!Static258.aBooleanArray130[local222.anInt4400]) {
                                        if (local11 != -1 && local222.anInt4400 == Static9.anInt172) {
                                            @Pc(243) MapElement local243 = new MapElement();
                                            local243.anInt4307 = local65;
                                            local243.anInt4314 = local144;
                                            local243.anInt4308 = local222.anInt4400;
                                            Static172.aClass69_97.push(local243);
                                        } else {
                                            Static241.aClass3_Sub2_Sub1_Sub1Array13[local222.anInt4400].method1423(local65 - 7, local144 + -7);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (@Pc(285) MapElement local285 = (MapElement) Static172.aClass69_97.head(); local285 != null; local285 = (MapElement) Static172.aClass69_97.next()) {
            Pix2D.method2502(local285.anInt4307, local285.anInt4314, 15, local11);
            Pix2D.method2502(local285.anInt4307, local285.anInt4314, 13, local11);
            Pix2D.method2502(local285.anInt4307, local285.anInt4314, 11, local11);
            Pix2D.method2502(local285.anInt4307, local285.anInt4314, 9, local11);
            Static241.aClass3_Sub2_Sub1_Sub1Array13[local285.anInt4308].method1423(local285.anInt4307 - 7, local285.anInt4314 + -7);
        }
        Static172.aClass69_97.method2278();
    }

    @OriginalMember(owner = "client!dh", name = "a", descriptor = "(Lclient!na;I)V")
    public static void method1149(@OriginalArg(0) JagString arg0) {
        @Pc(7) int local7 = method1879(arg0);
        if (local7 != -1) {
            Static80.method3616(aClass134_1.aShortArray73[local7], aClass134_1.aShortArray72[local7]);
        }
    }
}
