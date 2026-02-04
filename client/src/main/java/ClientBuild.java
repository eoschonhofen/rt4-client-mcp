import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ClientBuild {
    @OriginalMember(owner = "client!bb", name = "g", descriptor = "[[[B")
    public static final byte[][][] mapl = new byte[4][104][104];
    @OriginalMember(owner = "client!qc", name = "ab", descriptor = "[I")
    public static final int[] anIntArray417 = new int[1000];
    @OriginalMember(owner = "client!lf", name = "d", descriptor = "[I")
    public static final int[] anIntArray331 = new int[1000];
    @OriginalMember(owner = "client!he", name = "eb", descriptor = "[I")
    public static final int[] anIntArray219 = new int[1000];
    @OriginalMember(owner = "client!ph", name = "b", descriptor = "[[Lclient!li;")
    public static final Environment[][] aClass92ArrayArray1 = new Environment[13][13];
    @OriginalMember(owner = "client!ck", name = "d", descriptor = "[I")
    public static final int[] anIntArray80 = new int[] { 1, 0, -1, 0 };
    @OriginalMember(owner = "client!te", name = "B", descriptor = "[I")
    public static final int[] anIntArray469 = new int[] { 0, -1, 0, 1 };
    @OriginalMember(owner = "client!j", name = "O", descriptor = "[I")
    public static final int[] anIntArray565 = new int[] { 1, -1, -1, 1 };

    @OriginalMember(owner = "client!s", name = "i", descriptor = "[I")
    public static int[] huetot;

    @OriginalMember(owner = "client!l", name = "l", descriptor = "[I")
    public static int[] sattot;

    @OriginalMember(owner = "client!lg", name = "k", descriptor = "I")
    public static int minusedlevel = 99;

    @OriginalMember(owner = "client!ug", name = "d", descriptor = "[I")
    public static int[] comtot;

    @OriginalMember(owner = "client!n", name = "h", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray11;

    @OriginalMember(owner = "client!ka", name = "r", descriptor = "[I")
    public static int[] tot;

    @OriginalMember(owner = "client!em", name = "t", descriptor = "[[[I")
    public static int[][][] anIntArrayArrayArray6;

    @OriginalMember(owner = "client!jd", name = "d", descriptor = "[[[B")
    public static byte[][][] shadow;

    @OriginalMember(owner = "client!tg", name = "g", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray14;

    @OriginalMember(owner = "client!wk", name = "v", descriptor = "[I")
    public static int[] ligtot;

    @OriginalMember(owner = "client!ac", name = "e", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray1;

    @OriginalMember(owner = "client!ui", name = "eb", descriptor = "[[[B")
    public static byte[][][] floort1;
    @OriginalMember(owner = "client!ok", name = "c", descriptor = "I")
    public static int hueOff = (int) (Math.random() * 33.0D) - 16;
    @OriginalMember(owner = "client!gm", name = "R", descriptor = "I")
    public static int ligOff = (int) (Math.random() * 17.0D) - 8;
    @OriginalMember(owner = "client!ta", name = "B", descriptor = "I")
    public static int anInt5245 = 0;
    @OriginalMember(owner = "client!ug", name = "m", descriptor = "I")
    public static int anInt5454 = 0;
    @OriginalMember(owner = "client!ef", name = "j", descriptor = "Lclient!mm;")
    public static Pix32 aClass3_Sub2_Sub1_Sub1_1;

    @OriginalMember(owner = "client!pl", name = "a", descriptor = "(ZI)V")
    public static void init(@OriginalArg(0) boolean arg0) {
        huetot = new int[104];
        sattot = new int[104];
        minusedlevel = 99;
        comtot = new int[104];
        @Pc(14) byte local14;
        if (arg0) {
            local14 = 1;
        } else {
            local14 = 4;
        }
        aByteArrayArrayArray11 = new byte[local14][104][104];
        tot = new int[104];
        anIntArrayArrayArray6 = new int[local14][105][105];
        shadow = new byte[local14][105][105];
        aByteArrayArrayArray14 = new byte[local14][104][104];
        ligtot = new int[104];
        aByteArrayArrayArray1 = new byte[local14][104][104];
        floort1 = new byte[local14][104][104];
    }

    @OriginalMember(owner = "client!ib", name = "b", descriptor = "(I)V")
    public static void quit() {
        comtot = null;
        anIntArrayArrayArray6 = null;
        tot = null;
        aByteArrayArrayArray11 = null;
        aByteArrayArrayArray1 = null;
        shadow = null;
        aByteArrayArrayArray14 = null;
        floort1 = null;
        sattot = null;
        huetot = null;
        ligtot = null;
    }

    @OriginalMember(owner = "client!di", name = "a", descriptor = "([Lclient!mj;ZI)V")
    public static void finishBuild(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) boolean arg1) {
        @Pc(10) int local10;
        @Pc(15) int local15;
        if (!arg1) {
            for (local10 = 0; local10 < 4; local10++) {
                for (local15 = 0; local15 < 104; local15++) {
                    for (@Pc(22) int local22 = 0; local22 < 104; local22++) {
                        if ((mapl[local10][local15][local22] & 0x1) == 1) {
                            @Pc(43) int local43 = local10;
                            if ((mapl[1][local15][local22] & 0x2) == 2) {
                                local43 = local10 - 1;
                            }
                            if (local43 >= 0) {
                                arg0[local43].blockGround(local22, local15);
                            }
                        }
                    }
                }
            }
            hueOff += (int) (Math.random() * 5.0D) - 2;
            if (hueOff < -16) {
                hueOff = -16;
            }
            if (hueOff > 16) {
                hueOff = 16;
            }
            ligOff += (int) (Math.random() * 5.0D) - 2;
            if (ligOff < -8) {
                ligOff = -8;
            }
            if (ligOff > 8) {
                ligOff = 8;
            }
        }
        @Pc(128) byte local128;
        if (arg1) {
            local128 = 1;
        } else {
            local128 = 4;
        }
        local10 = ligOff >> 2 << 10;
        @Pc(142) int[][] local142 = new int[104][104];
        @Pc(146) int[][] local146 = new int[104][104];
        local15 = hueOff >> 1;
        @Pc(152) int local152;
        @Pc(168) int local168;
        @Pc(173) int local173;
        @Pc(178) int local178;
        @Pc(194) int local194;
        @Pc(200) int local200;
        @Pc(202) int local202;
        @Pc(209) int local209;
        @Pc(349) int local349;
        @Pc(234) int local234;
        @Pc(254) int local254;
        @Pc(267) int local267;
        for (local152 = 0; local152 < local128; local152++) {
            @Pc(159) byte[][] local159 = shadow[local152];
            @Pc(273) int local273;
            @Pc(326) int local326;
            @Pc(332) int local332;
            @Pc(322) int local322;
            if (!GameShell.glRenderer) {
                local168 = (int) Math.sqrt(5100.0D);
                local173 = local168 * 768 >> 8;
                for (local178 = 1; local178 < 103; local178++) {
                    for (local194 = 1; local194 < 103; local194++) {
                        local209 = World.groundh[local152][local194][local178 + 1] - World.groundh[local152][local194][local178 - 1];
                        local202 = World.groundh[local152][local194 + 1][local178] - World.groundh[local152][local194 - 1][local178];
                        local349 = (int) Math.sqrt((double) (local202 * local202 + local209 * local209 + 65536));
                        local267 = (local209 << 8) / local349;
                        local254 = -65536 / local349;
                        local234 = (local202 << 8) / local349;
                        local273 = (local159[local194][local178] >> 1) + (local159[local194][local178 - 1] >> 2) + (local159[local194 - -1][local178] >> 3) + (local159[local194 - 1][local178] >> 2) + (local159[local194][local178 + 1] >> 3);
                        local200 = (local267 * -50 + local234 * -50 + local254 * -10) / local173 + 74;
                        local146[local194][local178] = local200 - local273;
                    }
                }
            } else if (Static178.highDetailLighting) {
                for (local168 = 1; local168 < 103; local168++) {
                    for (local173 = 1; local173 < 103; local173++) {
                        local194 = (local159[local173 + 1][local168] >> 3) + (local159[local173 - 1][local168] >> 2) + (local159[local173][local168 + -1] >> 2) + (local159[local173][local168 + 1] >> 3) + (local159[local173][local168] >> 1);
                        local146[local173][local168] = 74 - local194;
                    }
                }
            } else {
                local168 = (int) Static161.aFloatArray18[0];
                local173 = (int) Static161.aFloatArray18[1];
                local178 = (int) Static161.aFloatArray18[2];
                local194 = (int) Math.sqrt((double) (local173 * local173 + local168 * local168 + local178 * local178));
                local200 = local194 * 1024 >> 8;
                for (local202 = 1; local202 < 103; local202++) {
                    for (local209 = 1; local209 < 103; local209++) {
                        local234 = World.groundh[local152][local209 + 1][local202] - World.groundh[local152][local209 - 1][local202];
                        local254 = World.groundh[local152][local209][local202 + 1] - World.groundh[local152][local209][local202 - 1];
                        local267 = (int) Math.sqrt((double) (local234 * local234 + local254 * local254 + 65536));
                        local273 = (local234 << 8) / local267;
                        local322 = (local159[local209][local202 + 1] >> 3) + (local159[local209][local202 - 1] >> 2) + (local159[local209 - 1][local202] >> 2) + (local159[local209 + 1][local202] >> 3) + (local159[local209][local202] >> 1);
                        local326 = -65536 / local267;
                        local332 = (local254 << 8) / local267;
                        local349 = (local178 * local332 + local168 * local273 + local326 * local173) / local200 + 96;
                        local146[local209][local202] = local349 - (int) ((float) local322 * 1.7F);
                    }
                }
            }
            for (local168 = 0; local168 < 104; local168++) {
                huetot[local168] = 0;
                sattot[local168] = 0;
                ligtot[local168] = 0;
                comtot[local168] = 0;
                tot[local168] = 0;
            }
            for (local168 = -5; local168 < 104; local168++) {
                for (local173 = 0; local173 < 104; local173++) {
                    local178 = local168 + 5;
                    @Pc(729) int local729;
                    if (local178 < 104) {
                        local194 = floort1[local152][local178][local173] & 0xFF;
                        if (local194 > 0) {
                            @Pc(693) FluType local693 = FluType.list(local194 - 1);
                            huetot[local173] += local693.chroma;
                            sattot[local173] += local693.hue;
                            ligtot[local173] += local693.saturation;
                            comtot[local173] += local693.luminance;
                            local729 = tot[local173]++;
                        }
                    }
                    local194 = local168 - 5;
                    if (local194 >= 0) {
                        local200 = floort1[local152][local194][local173] & 0xFF;
                        if (local200 > 0) {
                            @Pc(758) FluType local758 = FluType.list(local200 - 1);
                            huetot[local173] -= local758.chroma;
                            sattot[local173] -= local758.hue;
                            ligtot[local173] -= local758.saturation;
                            comtot[local173] -= local758.luminance;
                            local729 = tot[local173]--;
                        }
                    }
                }
                if (local168 >= 0) {
                    local173 = 0;
                    local194 = 0;
                    local178 = 0;
                    local200 = 0;
                    local202 = 0;
                    for (local209 = -5; local209 < 104; local209++) {
                        local349 = local209 + 5;
                        if (local349 < 104) {
                            local178 += sattot[local349];
                            local202 += tot[local349];
                            local173 += huetot[local349];
                            local200 += comtot[local349];
                            local194 += ligtot[local349];
                        }
                        local234 = local209 - 5;
                        if (local234 >= 0) {
                            local178 -= sattot[local234];
                            local200 -= comtot[local234];
                            local173 -= huetot[local234];
                            local202 -= tot[local234];
                            local194 -= ligtot[local234];
                        }
                        if (local209 >= 0 && local202 > 0) {
                            local142[local168][local209] = Static54.getTable(local194 / local202, local178 / local202, local173 * 256 / local200);
                        }
                    }
                }
            }
            for (local168 = 1; local168 < 103; local168++) {
                label771: for (local173 = 1; local173 < 103; local173++) {
                    if (arg1 || Client.highDetail() || (mapl[0][local168][local173] & 0x2) != 0 || (mapl[local152][local168][local173] & 0x10) == 0 && method22(local173, local168, local152) == Static41.anInt1316) {
                        if (minusedlevel > local152) {
                            minusedlevel = local152;
                        }
                        local178 = floort1[local152][local168][local173] & 0xFF;
                        local194 = aByteArrayArrayArray14[local152][local168][local173] & 0xFF;
                        if (local178 > 0 || local194 > 0) {
                            local202 = World.groundh[local152][local168 + 1][local173];
                            local200 = World.groundh[local152][local168][local173];
                            local349 = World.groundh[local152][local168][local173 + 1];
                            local209 = World.groundh[local152][local168 + 1][local173 + 1];
                            if (local152 > 0) {
                                @Pc(1067) boolean local1067 = true;
                                if (local178 == 0 && aByteArrayArrayArray11[local152][local168][local173] != 0) {
                                    local1067 = false;
                                }
                                if (local194 > 0 && !FloType.method4395(local194 - 1).aBoolean312) {
                                    local1067 = false;
                                }
                                if (local1067 && local200 == local202 && local200 == local209 && local349 == local200) {
                                    anIntArrayArrayArray6[local152][local168][local173] |= 0x4;
                                }
                            }
                            if (local178 <= 0) {
                                local234 = -1;
                                local254 = 0;
                            } else {
                                local234 = local142[local168][local173];
                                local267 = (local234 & 0x7F) + local15;
                                if (local267 < 0) {
                                    local267 = 0;
                                } else if (local267 > 127) {
                                    local267 = 127;
                                }
                                local273 = (local234 & 0x380) + (local234 + local10 & 0xFC00) + local267;
                                local254 = Pix3D.anIntArray220[method1814(96, local273)];
                            }
                            local267 = local146[local168][local173];
                            local332 = local146[local168][local173 + 1];
                            local273 = local146[local168 + 1][local173];
                            local326 = local146[local168 + 1][local173 + 1];
                            if (local194 == 0) {
                                World.method3305(local152, local168, local173, 0, 0, -1, local200, local202, local209, local349, method1814(local267, local234), method1814(local273, local234), method1814(local326, local234), method1814(local332, local234), 0, 0, 0, 0, local254, 0);
                                if (GameShell.glRenderer && local152 > 0 && local234 != -1 && FluType.list(local178 - 1).aBoolean198) {
                                    Static242.method4197(0, 0, true, false, local168, local173, local200 - World.groundh[0][local168][local173], -World.groundh[0][local168 + 1][local173] + local202, local209 - World.groundh[0][local168 + 1][local173 + 1], local349 - World.groundh[0][local168][local173 + 1]);
                                }
                                if (GameShell.glRenderer && !arg1 && World.anIntArrayArray11 != null && local152 == 0) {
                                    for (local322 = local168 - 1; local322 <= local168 + 1; local322++) {
                                        for (@Pc(1794) int local1794 = local173 - 1; local1794 <= local173 + 1; local1794++) {
                                            if ((local322 != local168 || local173 != local1794) && local322 >= 0 && local322 < 104 && local1794 >= 0 && local1794 < 104) {
                                                @Pc(1834) int local1834 = aByteArrayArrayArray14[local152][local322][local1794] & 0xFF;
                                                if (local1834 != 0) {
                                                    @Pc(1842) FloType local1842 = FloType.method4395(local1834 - 1);
                                                    if (local1842.anInt5892 != -1 && Pix3D.anInterface1_2.method3237(local1842.anInt5892) == 4) {
                                                        World.anIntArrayArray11[local168][local173] = local1842.anInt5889 + (local1842.anInt5898 << 24);
                                                        continue label771;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                local322 = aByteArrayArrayArray11[local152][local168][local173] + 1;
                                @Pc(1242) byte local1242 = aByteArrayArrayArray1[local152][local168][local173];
                                @Pc(1248) FloType local1248 = FloType.method4395(local194 - 1);
                                @Pc(1301) int local1301;
                                @Pc(1353) int local1353;
                                @Pc(1288) int local1288;
                                if (GameShell.glRenderer && !arg1 && World.anIntArrayArray11 != null && local152 == 0) {
                                    if (local1248.anInt5892 != -1 && Pix3D.anInterface1_2.method3237(local1248.anInt5892) == 4) {
                                        World.anIntArrayArray11[local168][local173] = (local1248.anInt5898 << 24) + local1248.anInt5889;
                                    } else {
                                        label737: for (local1288 = local168 - 1; local1288 <= local168 + 1; local1288++) {
                                            for (local1301 = local173 - 1; local1301 <= local173 + 1; local1301++) {
                                                if ((local168 != local1288 || local1301 != local173) && local1288 >= 0 && local1288 < 104 && local1301 >= 0 && local1301 < 104) {
                                                    local1353 = aByteArrayArrayArray14[local152][local1288][local1301] & 0xFF;
                                                    if (local1353 != 0) {
                                                        @Pc(1366) FloType local1366 = FloType.method4395(local1353 - 1);
                                                        if (local1366.anInt5892 != -1 && Pix3D.anInterface1_2.method3237(local1366.anInt5892) == 4) {
                                                            World.anIntArrayArray11[local168][local173] = local1366.anInt5889 + (local1366.anInt5898 << 24);
                                                            break label737;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                local1288 = local1248.anInt5892;
                                if (local1288 >= 0 && !Pix3D.anInterface1_2.method3236(local1288)) {
                                    local1288 = -1;
                                }
                                @Pc(1458) int local1458;
                                @Pc(1429) int local1429;
                                if (local1288 >= 0) {
                                    local1301 = -1;
                                    local1353 = Pix3D.anIntArray220[World.getOCol(Pix3D.anInterface1_2.method3234(local1288), 96)];
                                } else if (local1248.anInt5899 == -1) {
                                    local1301 = -2;
                                    local1353 = 0;
                                } else {
                                    local1301 = local1248.anInt5899;
                                    local1429 = local15 + (local1301 & 0x7F);
                                    if (local1429 < 0) {
                                        local1429 = 0;
                                    } else if (local1429 > 127) {
                                        local1429 = 127;
                                    }
                                    local1458 = (local1301 & 0x380) + ((local1301 + local10 & 0xFC00) + local1429);
                                    local1353 = Pix3D.anIntArray220[World.getOCol(local1458, 96)];
                                }
                                if (local1248.anInt5894 >= 0) {
                                    local1429 = local1248.anInt5894;
                                    local1458 = local15 + (local1429 & 0x7F);
                                    if (local1458 < 0) {
                                        local1458 = 0;
                                    } else if (local1458 > 127) {
                                        local1458 = 127;
                                    }
                                    @Pc(1529) int local1529 = (local1429 & 0x380) + ((local1429 + local10 & 0xFC00) + local1458);
                                    local1353 = Pix3D.anIntArray220[World.getOCol(local1529, 96)];
                                }
                                World.method3305(local152, local168, local173, local322, local1242, local1288, local200, local202, local209, local349, method1814(local267, local234), method1814(local273, local234), method1814(local326, local234), method1814(local332, local234), World.getOCol(local1301, local267), World.getOCol(local1301, local273), World.getOCol(local1301, local326), World.getOCol(local1301, local332), local254, local1353);
                                if (GameShell.glRenderer && local152 > 0) {
                                    Static242.method4197(local322, local1242, local1301 == -2 || !local1248.aBoolean311, local234 == -1 || !FluType.list(local178 - 1).aBoolean198, local168, local173, local200 - World.groundh[0][local168][local173], local202 - World.groundh[0][local168 + 1][local173], local209 - World.groundh[0][local168 + 1][local173 + 1], -World.groundh[0][local168][local173 + 1] + local349);
                                }
                            }
                        }
                    }
                }
            }
            if (GameShell.glRenderer) {
                @Pc(1888) float[][] local1888 = new float[105][105];
                @Pc(1892) int[][] local1892 = World.groundh[local152];
                @Pc(1896) float[][] local1896 = new float[105][105];
                @Pc(1900) float[][] local1900 = new float[105][105];
                local200 = 1;
                while (true) {
                    if (local200 > 103) {
                        @Pc(2025) GlSquare[] local2025;
                        if (arg1) {
                            local2025 = Static193.method3501(mapl, aByteArrayArrayArray11[local152], floort1[local152], local146, local1896, World.anIntArrayArray11, aByteArrayArrayArray14[local152], aByteArrayArrayArray1[local152], local1888, local152, local1900, local142, World.groundh[local152], World.normalGroundh[0]);
                            Static110.method2280(local152, local2025);
                            break;
                        }
                        local2025 = Static193.method3501(mapl, aByteArrayArrayArray11[local152], floort1[local152], local146, local1896, null, aByteArrayArrayArray14[local152], aByteArrayArrayArray1[local152], local1888, local152, local1900, local142, World.groundh[local152], null);
                        @Pc(2049) GlSquare[] local2049 = Static1.method2(local1896, local1888, World.groundh[local152], local152, local1900, aByteArrayArrayArray1[local152], local146, aByteArrayArrayArray11[local152], floort1[local152], aByteArrayArrayArray14[local152], mapl);
                        @Pc(2057) GlSquare[] local2057 = new GlSquare[local2025.length + local2049.length];
                        for (local349 = 0; local349 < local2025.length; local349++) {
                            local2057[local349] = local2025[local349];
                        }
                        for (local349 = 0; local349 < local2049.length; local349++) {
                            local2057[local2025.length + local349] = local2049[local349];
                        }
                        Static110.method2280(local152, local2057);
                        Static221.method3393(local1900, floort1[local152], aByteArrayArrayArray1[local152], World.aClass51Array1, local152, World.anInt3034, local1896, aByteArrayArrayArray11[local152], aByteArrayArrayArray14[local152], World.groundh[local152], local1888);
                        break;
                    }
                    for (local202 = 1; local202 <= 103; local202++) {
                        local349 = local1892[local202][local200 + 1] - local1892[local202][local200 - 1];
                        local209 = local1892[local202 + 1][local200] - local1892[local202 - 1][local200];
                        @Pc(1962) float local1962 = (float) Math.sqrt((double) (local209 * local209 + local349 * local349 + 65536));
                        local1888[local202][local200] = (float) local209 / local1962;
                        local1896[local202][local200] = -256.0F / local1962;
                        local1900[local202][local200] = (float) local349 / local1962;
                    }
                    local200++;
                }
            }
            floort1[local152] = null;
            aByteArrayArrayArray14[local152] = null;
            aByteArrayArrayArray11[local152] = null;
            aByteArrayArrayArray1[local152] = null;
            shadow[local152] = null;
        }
        World.method3801();
        if (arg1) {
            return;
        }
        @Pc(2204) int local2204;
        for (local152 = 0; local152 < 104; local152++) {
            for (local2204 = 0; local2204 < 104; local2204++) {
                if ((mapl[1][local152][local2204] & 0x2) == 2) {
                    World.method3884(local152, local2204);
                }
            }
        }
        for (local152 = 0; local152 < 4; local152++) {
            for (local2204 = 0; local2204 <= 104; local2204++) {
                for (local168 = 0; local168 <= 104; local168++) {
                    if ((anIntArrayArrayArray6[local152][local168][local2204] & 0x1) != 0) {
                        local200 = local152;
                        for (local173 = local2204; local173 > 0 && (anIntArrayArrayArray6[local152][local168][local173 - 1] & 0x1) != 0; local173--) {
                        }
                        local194 = local152;
                        for (local178 = local2204; local178 < 104 && (anIntArrayArrayArray6[local152][local168][local178 + 1] & 0x1) != 0; local178++) {
                        }
                        label454: while (local194 > 0) {
                            for (local202 = local173; local202 <= local178; local202++) {
                                if ((anIntArrayArrayArray6[local194 - 1][local168][local202] & 0x1) == 0) {
                                    break label454;
                                }
                            }
                            local194--;
                        }
                        label443: while (local200 < 3) {
                            for (local202 = local173; local202 <= local178; local202++) {
                                if ((anIntArrayArrayArray6[local200 + 1][local168][local202] & 0x1) == 0) {
                                    break label443;
                                }
                            }
                            local200++;
                        }
                        local202 = (local200 + 1 - local194) * (-local173 + (local178 - -1));
                        if (local202 >= 8) {
                            local349 = World.groundh[local200][local168][local173] - 240;
                            local234 = World.groundh[local194][local168][local173];
                            World.method4647(1, local168 * 128, local168 * 128, local173 * 128, local178 * 128 + 128, local349, local234);
                            for (local254 = local194; local254 <= local200; local254++) {
                                for (local267 = local173; local267 <= local178; local267++) {
                                    anIntArrayArrayArray6[local254][local168][local267] &= 0xFFFFFFFE;
                                }
                            }
                        }
                    }
                    if ((anIntArrayArrayArray6[local152][local168][local2204] & 0x2) != 0) {
                        for (local173 = local168; local173 > 0 && (anIntArrayArrayArray6[local152][local173 - 1][local2204] & 0x2) != 0; local173--) {
                        }
                        local200 = local152;
                        local194 = local152;
                        for (local178 = local168; local178 < 104 && (anIntArrayArrayArray6[local152][local178 + 1][local2204] & 0x2) != 0; local178++) {
                        }
                        label508: while (local194 > 0) {
                            for (local202 = local173; local202 <= local178; local202++) {
                                if ((anIntArrayArrayArray6[local194 - 1][local202][local2204] & 0x2) == 0) {
                                    break label508;
                                }
                            }
                            local194--;
                        }
                        label497: while (local200 < 3) {
                            for (local202 = local173; local202 <= local178; local202++) {
                                if ((anIntArrayArrayArray6[local200 + 1][local202][local2204] & 0x2) == 0) {
                                    break label497;
                                }
                            }
                            local200++;
                        }
                        local202 = (local178 + 1 - local173) * (-local194 + local200 - -1);
                        if (local202 >= 8) {
                            local349 = World.groundh[local200][local173][local2204] - 240;
                            local234 = World.groundh[local194][local173][local2204];
                            World.method4647(2, local173 * 128, local178 * 128 + 128, local2204 * 128, local2204 * 128, local349, local234);
                            for (local254 = local194; local254 <= local200; local254++) {
                                for (local267 = local173; local267 <= local178; local267++) {
                                    anIntArrayArrayArray6[local254][local267][local2204] &= 0xFFFFFFFD;
                                }
                            }
                        }
                    }
                    if ((anIntArrayArrayArray6[local152][local168][local2204] & 0x4) != 0) {
                        local173 = local168;
                        local178 = local168;
                        for (local194 = local2204; local194 > 0 && (anIntArrayArrayArray6[local152][local168][local194 - 1] & 0x4) != 0; local194--) {
                        }
                        for (local200 = local2204; local200 < 104 && (anIntArrayArrayArray6[local152][local168][local200 + 1] & 0x4) != 0; local200++) {
                        }
                        label562: while (local173 > 0) {
                            for (local202 = local194; local202 <= local200; local202++) {
                                if ((anIntArrayArrayArray6[local152][local173 - 1][local202] & 0x4) == 0) {
                                    break label562;
                                }
                            }
                            local173--;
                        }
                        label551: while (local178 < 104) {
                            for (local202 = local194; local202 <= local200; local202++) {
                                if ((anIntArrayArrayArray6[local152][local178 + 1][local202] & 0x4) == 0) {
                                    break label551;
                                }
                            }
                            local178++;
                        }
                        if ((local178 + 1 - local173) * (local200 - (local194 - 1)) >= 4) {
                            local202 = World.groundh[local152][local173][local194];
                            World.method4647(4, local173 * 128, local178 * 128 + 128, local194 * 128, local200 * 128 + 128, local202, local202);
                            for (local209 = local173; local209 <= local178; local209++) {
                                for (local349 = local194; local349 <= local200; local349++) {
                                    anIntArrayArrayArray6[local152][local209][local349] &= 0xFFFFFFFB;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!jk", name = "a", descriptor = "(IZ[BII[Lclient!mj;)V")
    public static void method2437(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) byte[] arg2, @OriginalArg(3) int arg3, @OriginalArg(5) CollisionMap[] arg4) {
        @Pc(10) Packet local10 = new Packet(arg2);
        @Pc(12) int local12 = -1;
        while (true) {
            @Pc(16) int local16 = local10.method2199();
            if (local16 == 0) {
                return;
            }
            local12 += local16;
            @Pc(27) int local27 = 0;
            while (true) {
                @Pc(31) int local31 = local10.method2204();
                if (local31 == 0) {
                    break;
                }
                local27 += local31 - 1;
                @Pc(46) int local46 = local27 & 0x3F;
                @Pc(50) int local50 = local27 >> 12;
                @Pc(56) int local56 = local27 >> 6 & 0x3F;
                @Pc(60) int local60 = local10.g1();
                @Pc(64) int local64 = local60 >> 2;
                @Pc(68) int local68 = local60 & 0x3;
                @Pc(72) int local72 = arg0 + local56;
                @Pc(76) int local76 = local46 + arg3;
                if (local72 > 0 && local76 > 0 && local72 < 103 && local76 < 103) {
                    @Pc(90) CollisionMap local90 = null;
                    if (!arg1) {
                        @Pc(95) int local95 = local50;
                        if ((mapl[1][local72][local76] & 0x2) == 2) {
                            local95 = local50 - 1;
                        }
                        if (local95 >= 0) {
                            local90 = arg4[local95];
                        }
                    }
                    method3397(local50, !arg1, local50, arg1, local90, local12, local64, local72, local76, local68);
                }
            }
        }
    }

    @OriginalMember(owner = "client!kl", name = "b", descriptor = "(II)Z")
    public static boolean method2665(@OriginalArg(1) int arg0) {
        @Pc(35) int local35;
        @Pc(37) int local37;
        @Pc(76) int local76;
        @Pc(80) int local80;
        if (aClass3_Sub2_Sub1_Sub1_1 == null) {
            if (GameShell.glRenderer || Static89.aClass3_Sub2_Sub1_5 == null) {
                aClass3_Sub2_Sub1_Sub1_1 = new Pix32(512, 512);
            } else {
                aClass3_Sub2_Sub1_Sub1_1 = (Pix32) Static89.aClass3_Sub2_Sub1_5;
            }
            @Pc(32) int[] local32 = aClass3_Sub2_Sub1_Sub1_1.anIntArray20;
            local35 = local32.length;
            for (local37 = 0; local37 < local35; local37++) {
                local32[local37] = 1;
            }
            for (local37 = 1; local37 < 103; local37++) {
                local76 = 4 * 512 * (103 - local37) + 24628;
                for (local80 = 1; local80 < 103; local80++) {
                    if ((mapl[arg0][local80][local37] & 0x18) == 0) {
                        World.method2835(local32, local76, arg0, local80, local37);
                    }
                    if (arg0 < 3 && (mapl[arg0 + 1][local80][local37] & 0x8) != 0) {
                        World.method2835(local32, local76, arg0 + 1, local80, local37);
                    }
                    local76 += 4;
                }
            }
            anInt5454 = 0;
            for (local37 = 0; local37 < 104; local37++) {
                for (local76 = 0; local76 < 104; local76++) {
                    @Pc(169) long local169 = World.method602(Client.minusedlevel, local37 + 0, local76);
                    if (local169 != 0L) {
                        @Pc(184) LocType local184 = LocType.list((int) (local169 >>> 32) & Integer.MAX_VALUE);
                        @Pc(187) int local187 = local184.anInt4400;
                        @Pc(194) int local194;
                        if (local184.anIntArray380 != null) {
                            for (local194 = 0; local194 < local184.anIntArray380.length; local194++) {
                                if (local184.anIntArray380[local194] != -1) {
                                    @Pc(216) LocType local216 = LocType.list(local184.anIntArray380[local194]);
                                    if (local216.anInt4400 >= 0) {
                                        local187 = local216.anInt4400;
                                        break;
                                    }
                                }
                            }
                        }
                        if (local187 >= 0) {
                            @Pc(237) int local237 = local76;
                            local194 = local37;
                            if (local187 != 22 && local187 != 29 && local187 != 34 && local187 != 36 && local187 != 46 && local187 != 47 && local187 != 48) {
                                @Pc(269) int[][] local269 = Client.levelCollisionMap[Client.minusedlevel].flags;
                                for (@Pc(271) int local271 = 0; local271 < 10; local271++) {
                                    @Pc(281) int local281 = (int) (Math.random() * 4.0D);
                                    if (local281 == 0 && local194 > 0 && local37 - 3 < local194 && (local269[local194 - 1][local237] & 0x12C0108) == 0) {
                                        local194--;
                                    }
                                    if (local281 == 1 && local194 < 103 && local37 + 3 > local194 && (local269[local194 + 1][local237] & 0x12C0180) == 0) {
                                        local194++;
                                    }
                                    if (local281 == 2 && local237 > 0 && local76 - 3 < local237 && (local269[local194][local237 - 1] & 0x12C0102) == 0) {
                                        local237--;
                                    }
                                    if (local281 == 3 && local237 < 103 && local237 < local76 + 3 && (local269[local194][local237 + 1] & 0x12C0120) == 0) {
                                        local237++;
                                    }
                                }
                            }
                            anIntArray417[anInt5454] = local184.id;
                            anIntArray331[anInt5454] = local194;
                            anIntArray219[anInt5454] = local237;
                            anInt5454++;
                        }
                    }
                }
            }
        }
        aClass3_Sub2_Sub1_Sub1_1.method304();
        @Pc(455) int local455 = ((int) (Math.random() * 20.0D) + 238 - 10 << 8) + ((int) (Math.random() * 20.0D) + 238 - 10 << 16) + (int) (Math.random() * 20.0D) + 228;
        local35 = (int) (Math.random() * 20.0D) + 238 - 10 << 16;
        for (local37 = 1; local37 < 103; local37++) {
            for (local76 = 1; local76 < 103; local76++) {
                if ((mapl[arg0][local76][local37] & 0x18) == 0 && !World.method3109(local76, local455, local37, local35, arg0)) {
                    if (GameShell.glRenderer) {
                        Pix2D.anIntArray297 = null;
                    } else {
                        GameShell.drawArea.method4189();
                    }
                    return false;
                }
                if (arg0 < 3 && (mapl[arg0 + 1][local76][local37] & 0x8) != 0 && !World.method3109(local76, local455, local37, local35, arg0 + 1)) {
                    if (GameShell.glRenderer) {
                        Pix2D.anIntArray297 = null;
                    } else {
                        GameShell.drawArea.method4189();
                    }
                    return false;
                }
            }
        }
        if (GameShell.glRenderer) {
            @Pc(576) int[] local576 = aClass3_Sub2_Sub1_Sub1_1.anIntArray20;
            local76 = local576.length;
            for (local80 = 0; local80 < local76; local80++) {
                if (local576[local80] == 0) {
                    local576[local80] = 1;
                }
            }
            Static89.aClass3_Sub2_Sub1_5 = new GlPix32(aClass3_Sub2_Sub1_Sub1_1);
        } else {
            Static89.aClass3_Sub2_Sub1_5 = aClass3_Sub2_Sub1_Sub1_1;
        }
        if (GameShell.glRenderer) {
            Pix2D.anIntArray297 = null;
        } else {
            GameShell.drawArea.method4189();
        }
        aClass3_Sub2_Sub1_Sub1_1 = null;
        return true;
    }

    @OriginalMember(owner = "client!p", name = "a", descriptor = "(IZIZLclient!mj;IIIBII)V")
    public static void method3397(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) CollisionMap arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9) {
        if (arg1 && !Client.highDetail() && (mapl[0][arg7][arg8] & 0x2) == 0) {
            if ((mapl[arg2][arg7][arg8] & 0x10) != 0) {
                return;
            }
            if (method22(arg8, arg7, arg2) != Static41.anInt1316) {
                return;
            }
        }
        if (arg2 < minusedlevel) {
            minusedlevel = arg2;
        }
        @Pc(62) LocType local62 = LocType.list(arg5);
        if (GameShell.glRenderer && local62.aBoolean216) {
            return;
        }
        @Pc(84) int local84;
        @Pc(81) int local81;
        if (arg9 == 1 || arg9 == 3) {
            local81 = local62.anInt4397;
            local84 = local62.anInt4403;
        } else {
            local84 = local62.anInt4397;
            local81 = local62.anInt4403;
        }
        @Pc(103) int local103;
        @Pc(112) int local112;
        if (arg7 + local84 <= 104) {
            local103 = arg7 + (local84 >> 1);
            local112 = arg7 + (local84 + 1 >> 1);
        } else {
            local112 = arg7 + 1;
            local103 = arg7;
        }
        @Pc(129) int local129;
        @Pc(133) int local133;
        if (local81 + arg8 > 104) {
            local129 = arg8;
            local133 = arg8 + 1;
        } else {
            local129 = (local81 >> 1) + arg8;
            local133 = arg8 + (local81 + 1 >> 1);
        }
        @Pc(153) int[][] local153 = World.groundh[arg0];
        @Pc(165) int local165 = (local84 << 6) + (arg7 << 7);
        @Pc(173) int local173 = (local81 << 6) + (arg8 << 7);
        @Pc(199) int local199 = local153[local103][local133] + local153[local112][local129] + local153[local103][local129] + local153[local112][local133] >> 2;
        @Pc(201) int local201 = 0;
        @Pc(213) int[][] local213;
        if (GameShell.glRenderer && arg0 != 0) {
            local213 = World.groundh[0];
            local201 = local199 - (local213[local112][local133] + local213[local112][local129] + local213[local103][local129] + local213[local103][local133] >> 2);
        }
        local213 = null;
        @Pc(261) long local261 = (long) (arg7 | 0x40000000 | arg8 << 7 | arg6 << 14 | arg9 << 20);
        if (arg3) {
            local213 = World.normalGroundh[0];
        } else if (arg0 < 3) {
            local213 = World.groundh[arg0 + 1];
        }
        if (local62.anInt4429 == 0 || arg3) {
            local261 |= Long.MIN_VALUE;
        }
        if (local62.anInt4438 == 1) {
            local261 |= 0x400000L;
        }
        if (local62.aBoolean213) {
            local261 |= 0x80000000L;
        }
        if (local62.method3422()) {
            BgSound.method2411(arg8, local62, arg9, null, arg7, arg2, null);
        }
        @Pc(330) boolean local330 = local62.aBoolean212 & !arg3;
        local261 |= (long) arg5 << 32;
        @Pc(387) ModelSource local387;
        @Pc(403) Class139 local403;
        if (arg6 == 22) {
            if (Static250.aBoolean283 || local62.anInt4429 != 0 || local62.blockwalk == 1 || local62.aBoolean206) {
                if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                    local403 = local62.method3428(arg9, local165, local153, 22, local199, local213, arg1, null, local330, local173);
                    if (GameShell.glRenderer && local330) {
                        Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                    }
                    local387 = local403.aClass8_10;
                } else {
                    local387 = new ClientLocAnim(arg5, 22, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                }
                World.method2570(arg2, arg7, arg8, local199, local387, local261, local62.aBoolean211);
                if (local62.blockwalk == 1 && arg4 != null) {
                    arg4.method3057(arg7, arg8);
                }
            }
        } else if (arg6 == 10 || arg6 == 11) {
            if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                local403 = local62.method3428(arg6 == 11 ? arg9 + 4 : arg9, local165, local153, 10, local199, local213, arg1, null, local330, local173);
                if (GameShell.glRenderer && local330) {
                    Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                }
                local387 = local403.aClass8_10;
            } else {
                local387 = new ClientLocAnim(arg5, 10, arg6 == 11 ? arg9 + 4 : arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
            }
            if (local387 != null) {
                @Pc(531) boolean local531 = World.method35(arg2, arg7, arg8, local199, local84, local81, local387, local261);
                if (local62.aBoolean215 && local531 && arg1) {
                    @Pc(541) int local541 = 15;
                    if (local387 instanceof ModelLit) {
                        local541 = ((ModelLit) local387).method4566() / 4;
                        if (local541 > 30) {
                            local541 = 30;
                        }
                    }
                    for (@Pc(560) int local560 = 0; local560 <= local84; local560++) {
                        for (@Pc(565) int local565 = 0; local565 <= local81; local565++) {
                            if (shadow[arg2][arg7 + local560][local565 + arg8] < local541) {
                                shadow[arg2][arg7 + local560][arg8 + local565] = (byte) local541;
                            }
                        }
                    }
                }
            }
            if (local62.blockwalk != 0 && arg4 != null) {
                arg4.method3043(arg7, local62.blockrange, arg8, local84, local81);
            }
        } else if (arg6 >= 12) {
            if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                local403 = local62.method3428(arg9, local165, local153, arg6, local199, local213, arg1, null, local330, local173);
                if (GameShell.glRenderer && local330) {
                    Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                }
                local387 = local403.aClass8_10;
            } else {
                local387 = new ClientLocAnim(arg5, arg6, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
            }
            World.method35(arg2, arg7, arg8, local199, 1, 1, local387, local261);
            if (arg1 && arg6 >= 12 && arg6 <= 17 && arg6 != 13 && arg2 > 0) {
                anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x4;
            }
            if (local62.blockwalk != 0 && arg4 != null) {
                arg4.method3043(arg7, local62.blockrange, arg8, local84, local81);
            }
        } else if (arg6 == 0) {
            if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                local403 = local62.method3428(arg9, local165, local153, 0, local199, local213, arg1, null, local330, local173);
                if (GameShell.glRenderer && local330) {
                    Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                }
                local387 = local403.aClass8_10;
            } else {
                local387 = new ClientLocAnim(arg5, 0, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
            }
            World.method4508(arg2, arg7, arg8, local199, local387, null, Static267.anIntArray517[arg9], 0, local261);
            if (arg1) {
                if (arg9 == 0) {
                    if (local62.aBoolean215) {
                        shadow[arg2][arg7][arg8] = 50;
                        shadow[arg2][arg7][arg8 + 1] = 50;
                    }
                    if (local62.aBoolean220) {
                        anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x1;
                    }
                } else if (arg9 == 1) {
                    if (local62.aBoolean215) {
                        shadow[arg2][arg7][arg8 + 1] = 50;
                        shadow[arg2][arg7 + 1][arg8 + 1] = 50;
                    }
                    if (local62.aBoolean220) {
                        anIntArrayArrayArray6[arg2][arg7][arg8 + 1] |= 0x2;
                    }
                } else if (arg9 == 2) {
                    if (local62.aBoolean215) {
                        shadow[arg2][arg7 + 1][arg8] = 50;
                        shadow[arg2][arg7 + 1][arg8 + 1] = 50;
                    }
                    if (local62.aBoolean220) {
                        anIntArrayArrayArray6[arg2][arg7 + 1][arg8] |= 0x1;
                    }
                } else if (arg9 == 3) {
                    if (local62.aBoolean215) {
                        shadow[arg2][arg7][arg8] = 50;
                        shadow[arg2][arg7 + 1][arg8] = 50;
                    }
                    if (local62.aBoolean220) {
                        anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x2;
                    }
                }
            }
            if (local62.blockwalk != 0 && arg4 != null) {
                arg4.method3040(arg9, arg6, local62.blockrange, arg8, arg7);
            }
            if (local62.anInt4428 != 16) {
                Static18.method559(arg2, arg7, arg8, local62.anInt4428);
            }
        } else if (arg6 == 1) {
            if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                local403 = local62.method3428(arg9, local165, local153, 1, local199, local213, arg1, null, local330, local173);
                if (GameShell.glRenderer && local330) {
                    Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                }
                local387 = local403.aClass8_10;
            } else {
                local387 = new ClientLocAnim(arg5, 1, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
            }
            World.method4508(arg2, arg7, arg8, local199, local387, null, Static78.anIntArray204[arg9], 0, local261);
            if (local62.aBoolean215 && arg1) {
                if (arg9 == 0) {
                    shadow[arg2][arg7][arg8 + 1] = 50;
                } else if (arg9 == 1) {
                    shadow[arg2][arg7 + 1][arg8 + 1] = 50;
                } else if (arg9 == 2) {
                    shadow[arg2][arg7 + 1][arg8] = 50;
                } else if (arg9 == 3) {
                    shadow[arg2][arg7][arg8] = 50;
                }
            }
            if (local62.blockwalk != 0 && arg4 != null) {
                arg4.method3040(arg9, arg6, local62.blockrange, arg8, arg7);
            }
        } else {
            @Pc(1226) int local1226;
            if (arg6 == 2) {
                local1226 = arg9 + 1 & 0x3;
                @Pc(1269) ModelSource local1269;
                @Pc(1254) ModelSource local1254;
                if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                    @Pc(1287) Class139 local1287 = local62.method3428(arg9 + 4, local165, local153, 2, local199, local213, arg1, null, local330, local173);
                    if (GameShell.glRenderer && local330) {
                        Static242.method4211(local1287.aClass36_Sub1_3, local165, local201, local173);
                    }
                    local1254 = local1287.aClass8_10;
                    local1287 = local62.method3428(local1226, local165, local153, 2, local199, local213, arg1, null, local330, local173);
                    if (GameShell.glRenderer && local330) {
                        Static242.method4211(local1287.aClass36_Sub1_3, local165, local201, local173);
                    }
                    local1269 = local1287.aClass8_10;
                } else {
                    local1254 = new ClientLocAnim(arg5, 2, arg9 + 4, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                    local1269 = new ClientLocAnim(arg5, 2, local1226, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                }
                World.method4508(arg2, arg7, arg8, local199, local1254, local1269, Static267.anIntArray517[arg9], Static267.anIntArray517[local1226], local261);
                if (local62.aBoolean220 && arg1) {
                    if (arg9 == 0) {
                        anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x1;
                        anIntArrayArrayArray6[arg2][arg7][arg8 + 1] |= 0x2;
                    } else if (arg9 == 1) {
                        anIntArrayArrayArray6[arg2][arg7][arg8 + 1] |= 0x2;
                        anIntArrayArrayArray6[arg2][arg7 + 1][arg8] |= 0x1;
                    } else if (arg9 == 2) {
                        anIntArrayArrayArray6[arg2][arg7 + 1][arg8] |= 0x1;
                        anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x2;
                    } else if (arg9 == 3) {
                        anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x2;
                        anIntArrayArrayArray6[arg2][arg7][arg8] |= 0x1;
                    }
                }
                if (local62.blockwalk != 0 && arg4 != null) {
                    arg4.method3040(arg9, arg6, local62.blockrange, arg8, arg7);
                }
                if (local62.anInt4428 != 16) {
                    Static18.method559(arg2, arg7, arg8, local62.anInt4428);
                }
            } else if (arg6 == 3) {
                if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                    local403 = local62.method3428(arg9, local165, local153, 3, local199, local213, arg1, null, local330, local173);
                    if (GameShell.glRenderer && local330) {
                        Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                    }
                    local387 = local403.aClass8_10;
                } else {
                    local387 = new ClientLocAnim(arg5, 3, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                }
                World.method4508(arg2, arg7, arg8, local199, local387, null, Static78.anIntArray204[arg9], 0, local261);
                if (local62.aBoolean215 && arg1) {
                    if (arg9 == 0) {
                        shadow[arg2][arg7][arg8 + 1] = 50;
                    } else if (arg9 == 1) {
                        shadow[arg2][arg7 + 1][arg8 + 1] = 50;
                    } else if (arg9 == 2) {
                        shadow[arg2][arg7 + 1][arg8] = 50;
                    } else if (arg9 == 3) {
                        shadow[arg2][arg7][arg8] = 50;
                    }
                }
                if (local62.blockwalk != 0 && arg4 != null) {
                    arg4.method3040(arg9, arg6, local62.blockrange, arg8, arg7);
                }
            } else if (arg6 == 9) {
                if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                    local403 = local62.method3428(arg9, local165, local153, arg6, local199, local213, arg1, null, local330, local173);
                    if (GameShell.glRenderer && local330) {
                        Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                    }
                    local387 = local403.aClass8_10;
                } else {
                    local387 = new ClientLocAnim(arg5, arg6, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                }
                World.method35(arg2, arg7, arg8, local199, 1, 1, local387, local261);
                if (local62.blockwalk != 0 && arg4 != null) {
                    arg4.method3043(arg7, local62.blockrange, arg8, local84, local81);
                }
                if (local62.anInt4428 != 16) {
                    Static18.method559(arg2, arg7, arg8, local62.anInt4428);
                }
            } else if (arg6 == 4) {
                if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                    local403 = local62.method3428(arg9, local165, local153, 4, local199, local213, arg1, null, local330, local173);
                    if (GameShell.glRenderer && local330) {
                        Static242.method4211(local403.aClass36_Sub1_3, local165, local201, local173);
                    }
                    local387 = local403.aClass8_10;
                } else {
                    local387 = new ClientLocAnim(arg5, 4, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                }
                World.method1880(arg2, arg7, arg8, local199, local387, null, Static267.anIntArray517[arg9], 0, 0, 0, local261);
            } else {
                @Pc(1889) long local1889;
                @Pc(1934) ModelSource local1934;
                @Pc(1950) Class139 local1950;
                if (arg6 == 5) {
                    local1226 = 16;
                    local1889 = World.method4521(arg2, arg7, arg8);
                    if (local1889 != 0L) {
                        local1226 = LocType.list(Integer.MAX_VALUE & (int) (local1889 >>> 32)).anInt4428;
                    }
                    if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                        local1950 = local62.method3428(arg9, local165, local153, 4, local199, local213, arg1, null, local330, local173);
                        if (GameShell.glRenderer && local330) {
                            Static242.method4211(local1950.aClass36_Sub1_3, local165 - anIntArray80[arg9] * 8, local201, local173 - anIntArray469[arg9] * 8);
                        }
                        local1934 = local1950.aClass8_10;
                    } else {
                        local1934 = new ClientLocAnim(arg5, 4, arg9, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                    }
                    World.method1880(arg2, arg7, arg8, local199, local1934, null, Static267.anIntArray517[arg9], 0, local1226 * anIntArray80[arg9], anIntArray469[arg9] * local1226, local261);
                } else if (arg6 == 6) {
                    local1226 = 8;
                    local1889 = World.method4521(arg2, arg7, arg8);
                    if (local1889 != 0L) {
                        local1226 = LocType.list(Integer.MAX_VALUE & (int) (local1889 >>> 32)).anInt4428 / 2;
                    }
                    if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                        local1950 = local62.method3428(arg9 + 4, local165, local153, 4, local199, local213, arg1, null, local330, local173);
                        if (GameShell.glRenderer && local330) {
                            Static242.method4211(local1950.aClass36_Sub1_3, local165 - anIntArray565[arg9] * 8, local201, local173 - Static64.anIntArray154[arg9] * 8);
                        }
                        local1934 = local1950.aClass8_10;
                    } else {
                        local1934 = new ClientLocAnim(arg5, 4, arg9 + 4, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                    }
                    World.method1880(arg2, arg7, arg8, local199, local1934, null, 256, arg9, local1226 * anIntArray565[arg9], local1226 * Static64.anIntArray154[arg9], local261);
                } else if (arg6 == 7) {
                    @Pc(2137) int local2137 = arg9 + 2 & 0x3;
                    if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                        @Pc(2183) Class139 local2183 = local62.method3428(local2137 + 4, local165, local153, 4, local199, local213, arg1, null, local330, local173);
                        if (GameShell.glRenderer && local330) {
                            Static242.method4211(local2183.aClass36_Sub1_3, local165, local201, local173);
                        }
                        local387 = local2183.aClass8_10;
                    } else {
                        local387 = new ClientLocAnim(arg5, 4, local2137 + 4, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                    }
                    World.method1880(arg2, arg7, arg8, local199, local387, null, 256, local2137, 0, 0, local261);
                } else if (arg6 == 8) {
                    local1226 = 8;
                    local1889 = World.method4521(arg2, arg7, arg8);
                    if (local1889 != 0L) {
                        local1226 = LocType.list(Integer.MAX_VALUE & (int) (local1889 >>> 32)).anInt4428 / 2;
                    }
                    @Pc(2244) int local2244 = arg9 + 2 & 0x3;
                    @Pc(2289) ModelSource local2289;
                    if (local62.anInt4430 == -1 && local62.anIntArray380 == null && !local62.aBoolean214) {
                        @Pc(2297) int local2297 = Static64.anIntArray154[arg9] * 8;
                        @Pc(2303) int local2303 = anIntArray565[arg9] * 8;
                        @Pc(2319) Class139 local2319 = local62.method3428(arg9 + 4, local165, local153, 4, local199, local213, arg1, null, local330, local173);
                        if (GameShell.glRenderer && local330) {
                            Static242.method4211(local2319.aClass36_Sub1_3, local165 - local2303, local201, local173 - local2297);
                        }
                        local1934 = local2319.aClass8_10;
                        local2319 = local62.method3428(local2244 + 4, local165, local153, 4, local199, local213, arg1, null, local330, local173);
                        if (GameShell.glRenderer && local330) {
                            Static242.method4211(local2319.aClass36_Sub1_3, local165 - local2303, local201, local173 - local2297);
                        }
                        local2289 = local2319.aClass8_10;
                    } else {
                        local1934 = new ClientLocAnim(arg5, 4, arg9 + 4, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                        local2289 = new ClientLocAnim(arg5, 4, local2244 + 4, arg0, arg7, arg8, local62.anInt4430, local62.aBoolean209, null);
                    }
                    World.method1880(arg2, arg7, arg8, local199, local1934, local2289, 256, arg9, local1226 * anIntArray565[arg9], Static64.anIntArray154[arg9] * local1226, local261);
                }
            }
        }
    }

    @OriginalMember(owner = "client!rj", name = "a", descriptor = "([Lclient!mj;I[BIIIIZIIB)V")
    public static void method3771(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) byte[] arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) boolean arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9) {
        @Pc(7) int local7 = -1;
        @Pc(12) Packet local12 = new Packet(arg2);
        while (true) {
            @Pc(20) int local20 = local12.method2199();
            if (local20 == 0) {
                return;
            }
            local7 += local20;
            @Pc(31) int local31 = 0;
            while (true) {
                @Pc(35) int local35 = local12.method2204();
                if (local35 == 0) {
                    break;
                }
                local31 += local35 - 1;
                @Pc(50) int local50 = local31 & 0x3F;
                @Pc(56) int local56 = local31 >> 6 & 0x3F;
                @Pc(60) int local60 = local31 >> 12;
                @Pc(64) int local64 = local12.g1();
                @Pc(68) int local68 = local64 >> 2;
                @Pc(72) int local72 = local64 & 0x3;
                if (arg3 == local60 && local56 >= arg8 && local56 < arg8 + 8 && arg9 <= local50 && arg9 + 8 > local50) {
                    @Pc(103) LocType local103 = LocType.list(local7);
                    @Pc(120) int local120 = Static52.method1286(local50 & 0x7, arg4, local72, local103.anInt4403, local103.anInt4397, local56 & 0x7) + arg5;
                    @Pc(137) int local137 = Static241.method4541(local103.anInt4397, arg4, local103.anInt4403, local56 & 0x7, local72, local50 & 0x7) + arg6;
                    if (local120 > 0 && local137 > 0 && local120 < 103 && local137 < 103) {
                        @Pc(154) CollisionMap local154 = null;
                        if (!arg7) {
                            @Pc(159) int local159 = arg1;
                            if ((mapl[1][local120][local137] & 0x2) == 2) {
                                local159 = arg1 - 1;
                            }
                            if (local159 >= 0) {
                                local154 = arg0[local159];
                            }
                        }
                        method3397(arg1, !arg7, arg1, arg7, local154, local7, local68, local120, local137, local72 + arg4 & 0x3);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!tm", name = "a", descriptor = "(III[Lclient!mj;IB[BIIIZ)V")
    public static void method4228(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) CollisionMap[] arg3, @OriginalArg(4) int arg4, @OriginalArg(6) byte[] arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) boolean arg9) {
        @Pc(17) int local17;
        if (!arg9) {
            for (@Pc(10) int local10 = 0; local10 < 8; local10++) {
                for (local17 = 0; local17 < 8; local17++) {
                    if (arg1 + local10 > 0 && local10 + arg1 < 103 && local17 + arg4 > 0 && arg4 + local17 < 103) {
                        arg3[arg2].flags[local10 + arg1][local17 + arg4] &= 0xFEFFFFFF;
                    }
                }
            }
        }
        @Pc(87) byte local87;
        if (arg9) {
            local87 = 1;
        } else {
            local87 = 4;
        }
        @Pc(96) Packet local96 = new Packet(arg5);
        @Pc(103) int local103;
        @Pc(108) int local108;
        for (local17 = 0; local17 < local87; local17++) {
            for (local103 = 0; local103 < 64; local103++) {
                for (local108 = 0; local108 < 64; local108++) {
                    if (arg6 == local17 && arg8 <= local103 && arg8 + 8 > local103 && arg7 <= local108 && local108 < arg7 + 8) {
                        method4651(0, 0, arg9, local96, Static202.method3659(arg0, local103 & 0x7, local108 & 0x7) + arg4, Static214.method4360(arg0, local108 & 0x7, local103 & 0x7) + arg1, arg0, arg2);
                    } else {
                        method4651(0, 0, arg9, local96, -1, -1, 0, 0);
                    }
                }
            }
        }
        @Pc(232) int local232;
        @Pc(417) int local417;
        @Pc(255) int local255;
        @Pc(266) int local266;
        @Pc(316) int local316;
        while (local96.data.length > local96.pos) {
            local103 = local96.g1();
            if (local103 != 129) {
                local96.pos--;
                break;
            }
            for (local108 = 0; local108 < 4; local108++) {
                @Pc(223) byte local223 = local96.g1b();
                @Pc(237) int local237;
                if (local223 == 0) {
                    if (local108 <= arg6) {
                        local237 = arg1 + 7;
                        local232 = arg1;
                        local255 = arg4 + 7;
                        if (local255 < 0) {
                            local255 = 0;
                        } else if (local255 >= 104) {
                            local255 = 104;
                        }
                        if (local237 < 0) {
                            local237 = 0;
                        } else if (local237 >= 104) {
                            local237 = 104;
                        }
                        local417 = arg4;
                        if (arg4 < 0) {
                            local417 = 0;
                        } else if (arg4 >= 104) {
                            local417 = 104;
                        }
                        if (arg1 < 0) {
                            local232 = 0;
                        } else if (arg1 >= 104) {
                            local232 = 104;
                        }
                        while (local237 > local232) {
                            while (local417 < local255) {
                                World.aByteArrayArrayArray13[arg2][local232][local417] = 0;
                                local417++;
                            }
                            local232++;
                        }
                    }
                } else if (local223 == 1) {
                    for (local232 = 0; local232 < 64; local232 += 4) {
                        for (local237 = 0; local237 < 64; local237 += 4) {
                            @Pc(246) byte local246 = local96.g1b();
                            if (local108 <= arg6) {
                                for (local255 = local232; local255 < local232 + 4; local255++) {
                                    for (local266 = local237; local266 < local237 + 4; local266++) {
                                        if (local255 >= arg8 && local255 < arg8 + 8 && local266 >= arg7 && arg7 + 8 > arg7) {
                                            local316 = arg1 + Static214.method4360(arg0, local266 & 0x7, local255 & 0x7);
                                            @Pc(328) int local328 = Static202.method3659(arg0, local255 & 0x7, local266 & 0x7) + arg4;
                                            if (local316 >= 0 && local316 < 104 && local328 >= 0 && local328 < 104) {
                                                World.aByteArrayArrayArray13[arg2][local316][local328] = local246;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (local223 == 2) {
                }
            }
        }
        @Pc(497) int local497;
        if (GameShell.glRenderer && !arg9) {
            @Pc(472) Environment local472 = null;
            label207: while (true) {
                label200: do {
                    while (local96.data.length > local96.pos) {
                        local108 = local96.g1();
                        if (local108 != 0) {
                            if (local108 != 1) {
                                throw new IllegalStateException();
                            }
                            local497 = local96.g1();
                            continue label200;
                        }
                        local472 = new Environment(local96);
                    }
                    if (local472 == null) {
                        local472 = new Environment();
                    }
                    aClass92ArrayArray1[arg1 >> 3][arg4 >> 3] = local472;
                    break label207;
                } while (local497 <= 0);
                for (local232 = 0; local232 < local497; local232++) {
                    @Pc(517) Light local517 = new Light(local96);
                    if (local517.anInt2243 == 31) {
                        @Pc(529) LightType local529 = LightType.list(local96.g2());
                        local517.method1762(local529.anInt2865, local529.anInt2873, local529.anInt2867, local529.anInt2872);
                    }
                    local417 = local517.anInt2240 >> 7;
                    local255 = local517.anInt2245 >> 7;
                    if (arg6 == local517.anInt2241 && local417 >= arg8 && arg8 + 8 > local417 && arg7 <= local255 && arg7 + 8 > local255) {
                        local266 = Static204.method3675(arg0, local517.anInt2240 & 0x3FF, local517.anInt2245 & 0x3FF) + (arg1 << 7);
                        local316 = method3388(local517.anInt2240 & 0x3FF, arg0, local517.anInt2245 & 0x3FF) + (arg4 << 7);
                        local517.anInt2240 = local266;
                        local517.anInt2245 = local316;
                        local417 = local517.anInt2240 >> 7;
                        local255 = local517.anInt2245 >> 7;
                        if (local417 >= 0 && local255 >= 0 && local417 < 104 && local255 < 104) {
                            local517.aBoolean125 = (mapl[1][local417][local255] & 0x2) != 0;
                            local517.anInt2235 = World.groundh[local517.anInt2241][local417][local255] - local517.anInt2235;
                            World.method2389(local517);
                        }
                    }
                }
            }
        }
        local103 = arg1 + 7;
        local108 = arg4 + 7;
        for (local497 = arg1; local497 < local103; local497++) {
            for (local232 = arg4; local232 < local108; local232++) {
                World.aByteArrayArrayArray13[arg2][local497][local232] = 0;
            }
        }
    }

    @OriginalMember(owner = "client!wa", name = "a", descriptor = "([Lclient!mj;ZIIIII[B)V")
    public static void method2203(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) byte[] arg6) {
        @Pc(14) int local14;
        @Pc(21) int local21;
        if (!arg1) {
            for (@Pc(9) int local9 = 0; local9 < 4; local9++) {
                for (local14 = 0; local14 < 64; local14++) {
                    for (local21 = 0; local21 < 64; local21++) {
                        if (arg4 + local14 > 0 && local14 + arg4 < 103 && arg3 + local21 > 0 && local21 + arg3 < 103) {
                            arg0[local9].flags[local14 + arg4][arg3 + local21] &= 0xFEFFFFFF;
                        }
                    }
                }
            }
        }
        @Pc(95) Packet local95 = new Packet(arg6);
        @Pc(99) byte local99;
        if (arg1) {
            local99 = 1;
        } else {
            local99 = 4;
        }
        @Pc(117) int local117;
        for (local14 = 0; local14 < local99; local14++) {
            for (local21 = 0; local21 < 64; local21++) {
                for (local117 = 0; local117 < 64; local117++) {
                    method4651(arg2, arg5, arg1, local95, local117 + arg3, arg4 + local21, 0, local14);
                }
            }
        }
        @Pc(146) boolean local146 = false;
        @Pc(243) int local243;
        @Pc(188) int local188;
        @Pc(190) int local190;
        @Pc(194) int local194;
        while (local95.pos < local95.data.length) {
            local21 = local95.g1();
            if (local21 != 129) {
                local95.pos--;
                break;
            }
            for (local117 = 0; local117 < 4; local117++) {
                @Pc(168) byte local168 = local95.g1b();
                if (local168 == 0) {
                    local243 = arg4;
                    if (arg4 < 0) {
                        local243 = 0;
                    } else if (arg4 >= 104) {
                        local243 = 104;
                    }
                    local190 = arg3;
                    if (arg3 < 0) {
                        local190 = 0;
                    } else if (arg3 >= 104) {
                        local190 = 104;
                    }
                    local188 = arg4 + 64;
                    local194 = arg3 + 64;
                    if (local194 < 0) {
                        local194 = 0;
                    } else if (local194 >= 104) {
                        local194 = 104;
                    }
                    if (local188 < 0) {
                        local188 = 0;
                    } else if (local188 >= 104) {
                        local188 = 104;
                    }
                    while (local243 < local188) {
                        while (local190 < local194) {
                            World.aByteArrayArrayArray13[local117][local243][local190] = 0;
                            local190++;
                        }
                        local243++;
                    }
                } else if (local168 == 1) {
                    for (local243 = 0; local243 < 64; local243 += 4) {
                        for (local188 = 0; local188 < 64; local188 += 4) {
                            @Pc(305) byte local305 = local95.g1b();
                            for (local194 = local243 + arg4; local194 < arg4 + local243 + 4; local194++) {
                                for (@Pc(320) int local320 = arg3 + local188; local320 < arg3 + local188 + 4; local320++) {
                                    if (local194 >= 0 && local194 < 104 && local320 >= 0 && local320 < 104) {
                                        World.aByteArrayArrayArray13[local117][local194][local320] = local305;
                                    }
                                }
                            }
                        }
                    }
                } else if (local168 == 2 && local117 > 0) {
                    local188 = arg4 + 64;
                    local190 = arg3;
                    local194 = arg3 + 64;
                    if (local188 < 0) {
                        local188 = 0;
                    } else if (local188 >= 104) {
                        local188 = 104;
                    }
                    if (arg3 < 0) {
                        local190 = 0;
                    } else if (arg3 >= 104) {
                        local190 = 104;
                    }
                    if (local194 < 0) {
                        local194 = 0;
                    } else if (local194 >= 104) {
                        local194 = 104;
                    }
                    local243 = arg4;
                    if (arg4 < 0) {
                        local243 = 0;
                    } else if (arg4 >= 104) {
                        local243 = 104;
                    }
                    while (local188 > local243) {
                        while (local190 < local194) {
                            World.aByteArrayArrayArray13[local117][local243][local190] = World.aByteArrayArrayArray13[local117 - 1][local243][local190];
                            local190++;
                        }
                        local243++;
                    }
                }
            }
            local146 = true;
        }
        @Pc(515) int local515;
        if (GameShell.glRenderer && !arg1) {
            @Pc(490) Environment local490 = null;
            label270: while (true) {
                label263: do {
                    while (local95.pos < local95.data.length) {
                        local117 = local95.g1();
                        if (local117 != 0) {
                            if (local117 != 1) {
                                throw new IllegalStateException();
                            }
                            local515 = local95.g1();
                            continue label263;
                        }
                        local490 = new Environment(local95);
                    }
                    if (local490 == null) {
                        local490 = new Environment();
                    }
                    for (local117 = 0; local117 < 8; local117++) {
                        for (local515 = 0; local515 < 8; local515++) {
                            local243 = local117 + (arg4 >> 3);
                            local188 = (arg3 >> 3) + local515;
                            if (local243 >= 0 && local243 < 13 && local188 >= 0 && local188 < 13) {
                                aClass92ArrayArray1[local243][local188] = local490;
                            }
                        }
                    }
                    break label270;
                } while (local515 <= 0);
                for (local243 = 0; local243 < local515; local243++) {
                    @Pc(529) Light local529 = new Light(local95);
                    if (local529.anInt2243 == 31) {
                        @Pc(541) LightType local541 = LightType.list(local95.g2());
                        local529.method1762(local541.anInt2865, local541.anInt2873, local541.anInt2867, local541.anInt2872);
                    }
                    local529.anInt2245 += arg3 << 7;
                    local529.anInt2240 += arg4 << 7;
                    local194 = local529.anInt2245 >> 7;
                    local190 = local529.anInt2240 >> 7;
                    if (local190 >= 0 && local194 >= 0 && local190 < 104 && local194 < 104) {
                        local529.aBoolean125 = (mapl[1][local190][local194] & 0x2) != 0;
                        local529.anInt2235 = World.groundh[local529.anInt2241][local190][local194] - local529.anInt2235;
                        World.method2389(local529);
                    }
                }
            }
        }
        if (local146) {
            return;
        }
        for (local21 = 0; local21 < 4; local21++) {
            for (local117 = 0; local117 < 16; local117++) {
                for (local515 = 0; local515 < 16; local515++) {
                    local243 = (arg4 >> 2) + local117;
                    local188 = local515 + (arg3 >> 2);
                    if (local243 >= 0 && local243 < 26 && local188 >= 0 && local188 < 26) {
                        World.aByteArrayArrayArray13[local21][local243][local188] = 0;
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!wj", name = "a", descriptor = "(IIZLclient!wa;IIBII)V")
    public static void method4651(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) Packet arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
        @Pc(32) int local32;
        if (arg5 < 0 || arg5 >= 104 || arg4 < 0 || arg4 >= 104) {
            while (true) {
                local32 = arg3.g1();
                if (local32 == 0) {
                    break;
                }
                if (local32 == 1) {
                    arg3.g1();
                    break;
                }
                if (local32 <= 49) {
                    arg3.g1();
                }
            }
            return;
        }
        if (!arg2) {
            mapl[arg7][arg5][arg4] = 0;
        }
        while (true) {
            local32 = arg3.g1();
            if (local32 == 0) {
                if (arg2) {
                    World.groundh[0][arg5][arg4] = World.normalGroundh[0][arg5][arg4];
                } else if (arg7 == 0) {
                    World.groundh[0][arg5][arg4] = -perlinNoise(arg4 + arg1 + 556238, arg0 + arg5 + 932731) * 8;
                } else {
                    World.groundh[arg7][arg5][arg4] = World.groundh[arg7 - 1][arg5][arg4] - 240;
                }
                break;
            }
            if (local32 == 1) {
                @Pc(111) int local111 = arg3.g1();
                if (arg2) {
                    World.groundh[0][arg5][arg4] = World.normalGroundh[0][arg5][arg4] + local111 * 8;
                } else {
                    if (local111 == 1) {
                        local111 = 0;
                    }
                    if (arg7 == 0) {
                        World.groundh[0][arg5][arg4] = -local111 * 8;
                    } else {
                        World.groundh[arg7][arg5][arg4] = World.groundh[arg7 - 1][arg5][arg4] - local111 * 8;
                    }
                }
                break;
            }
            if (local32 <= 49) {
                aByteArrayArrayArray14[arg7][arg5][arg4] = arg3.g1b();
                aByteArrayArrayArray11[arg7][arg5][arg4] = (byte) ((local32 - 2) / 4);
                aByteArrayArrayArray1[arg7][arg5][arg4] = (byte) (local32 + arg6 - 2 & 0x3);
            } else if (local32 > 81) {
                floort1[arg7][arg5][arg4] = (byte) (local32 - 81);
            } else if (!arg2) {
                mapl[arg7][arg5][arg4] = (byte) (local32 - 49);
            }
        }
    }

    @OriginalMember(owner = "client!ac", name = "a", descriptor = "(IIII)I")
    public static int method22(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
        if ((mapl[arg2][arg1][arg0] & 0x8) == 0) {
            return arg2 <= 0 || (mapl[1][arg1][arg0] & 0x2) == 0 ? arg2 : arg2 - 1;
        } else {
            return 0;
        }
    }

    @OriginalMember(owner = "client!gn", name = "a", descriptor = "(IZI)I")
    public static int method1814(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        if (arg1 == -1) {
            return 12345678;
        }
        arg0 = arg0 * (arg1 & 0x7F) >> 7;
        if (arg0 < 2) {
            arg0 = 2;
        } else if (arg0 > 126) {
            arg0 = 126;
        }
        return arg0 + (arg1 & 0xFF80);
    }

    @OriginalMember(owner = "client!il", name = "a", descriptor = "(BII)Z")
    public static boolean changeLocAvailable(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        if (arg1 == 11) {
            arg1 = 10;
        }
        if (arg1 >= 5 && arg1 <= 8) {
            arg1 = 4;
        }
        @Pc(30) LocType local30 = LocType.list(arg0);
        return local30.method3416(arg1);
    }

    @OriginalMember(owner = "client!fc", name = "a", descriptor = "(III)I")
    public static int perlinNoise(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        @Pc(36) int local36 = interpolatedNoise(4, arg1 + 45365, arg0 - -91923) + (interpolatedNoise(2, arg1 + 10294, arg0 + 37821) - 128 >> 1) + (interpolatedNoise(1, arg1, arg0) + -128 >> 2) - 128;
        local36 = (int) ((double) local36 * 0.3D) + 35;
        if (local36 < 10) {
            local36 = 10;
        } else if (local36 > 60) {
            local36 = 60;
        }
        return local36;
    }

    @OriginalMember(owner = "client!ja", name = "a", descriptor = "(IIII)I")
    public static int interpolatedNoise(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) int local7 = arg1 / arg0;
        @Pc(11) int local11 = arg2 / arg0;
        @Pc(17) int local17 = arg2 & arg0 - 1;
        @Pc(23) int local23 = arg0 - 1 & arg1;
        @Pc(28) int local28 = method670(local7, local11);
        @Pc(35) int local35 = method670(local7 + 1, local11);
        @Pc(42) int local42 = method670(local7, local11 + 1);
        @Pc(56) int local56 = method670(local7 + 1, local11 + 1);
        @Pc(63) int local63 = method2569(local28, local35, local23, arg0);
        @Pc(70) int local70 = method2569(local42, local56, local23, arg0);
        return method2569(local63, local70, local17, arg0);
    }

    @OriginalMember(owner = "client!bn", name = "a", descriptor = "(IIB)I")
    public static int method670(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
        @Pc(47) int local47 = method2695(arg0 - 1, arg1 + -1) + method2695(arg0 + 1, arg1 + -1) + method2695(arg0 + -1, arg1 - -1) + method2695(arg0 + 1, arg1 - -1);
        @Pc(76) int local76 = method2695(arg0 - 1, arg1) + method2695(arg0 + 1, arg1) + method2695(arg0, arg1 + -1) + method2695(arg0, arg1 + 1);
        @Pc(81) int local81 = method2695(arg0, arg1);
        return local76 / 8 + local47 / 16 + local81 / 4;
    }

    @OriginalMember(owner = "client!nh", name = "a", descriptor = "(IIIII)I")
    public static int method2569(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
        @Pc(22) int local22 = 65536 - Pix3D.cosTable[arg2 * 1024 / arg3] >> 1;
        return (arg0 * (65536 - local22) >> 16) + (arg1 * local22 >> 16);
    }

    @OriginalMember(owner = "client!km", name = "b", descriptor = "(III)I")
    public static int method2695(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        @Pc(14) int local14 = arg1 * 57 + arg0;
        @Pc(20) int local20 = local14 ^ local14 << 13;
        @Pc(34) int local34 = Integer.MAX_VALUE & (local20 * local20 * 15731 + 789221) * local20 + 1376312589;
        return local34 >> 19 & 0xFF;
    }

    @OriginalMember(owner = "client!fm", name = "a", descriptor = "(ZII)V")
    public static void loadGround(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        Static85.anInt2263 = aClass92ArrayArray1[arg1][arg0].anInt3530;
        Static159.anInt3893 = aClass92ArrayArray1[arg1][arg0].anInt3528;
        Static148.anInt3534 = aClass92ArrayArray1[arg1][arg0].anInt3527;
        Static161.method3063((float) Static85.anInt2263, (float) Static159.anInt3893, (float) Static148.anInt3534);
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(BII[B)Z")
    public static boolean checkLocations(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) byte[] arg2) {
        @Pc(15) boolean local15 = true;
        @Pc(17) int local17 = -1;
        @Pc(22) Packet local22 = new Packet(arg2);
        label70: while (true) {
            @Pc(26) int local26 = local22.method2199();
            if (local26 == 0) {
                return local15;
            }
            @Pc(33) int local33 = 0;
            local17 += local26;
            @Pc(39) boolean local39 = false;
            while (true) {
                @Pc(78) int local78;
                @Pc(95) LocType local95;
                do {
                    @Pc(72) int local72;
                    @Pc(68) int local68;
                    do {
                        do {
                            do {
                                do {
                                    @Pc(45) int local45;
                                    while (local39) {
                                        local45 = local22.method2204();
                                        if (local45 == 0) {
                                            continue label70;
                                        }
                                        local22.g1();
                                    }
                                    local45 = local22.method2204();
                                    if (local45 == 0) {
                                        continue label70;
                                    }
                                    local33 += local45 - 1;
                                    @Pc(58) int local58 = local33 & 0x3F;
                                    @Pc(64) int local64 = local33 >> 6 & 0x3F;
                                    local68 = arg1 + local58;
                                    local72 = arg0 + local64;
                                    local78 = local22.g1() >> 2;
                                } while (local72 <= 0);
                            } while (local68 <= 0);
                        } while (local72 >= 103);
                    } while (local68 >= 103);
                    local95 = LocType.list(local17);
                } while (local78 == 22 && !Static250.aBoolean283 && local95.anInt4429 == 0 && local95.blockwalk != 1 && !local95.aBoolean206);
                local39 = true;
                if (!local95.method3426()) {
                    local15 = false;
                    Client.locModelLoadCount++;
                }
            }
        }
    }

    @OriginalMember(owner = "client!ol", name = "a", descriptor = "(IIZI)I")
    public static int method3388(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
        @Pc(3) int local3 = arg1 & 0x3;
        if (local3 == 0) {
            return arg2;
        } else if (local3 == 1) {
            return 1023 - arg0;
        } else if (local3 == 2) {
            return 1023 - arg2;
        } else {
            return arg0;
        }
    }
}
