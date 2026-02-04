import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PixLoader {
    @OriginalMember(owner = "client!gk", name = "a", descriptor = "([BI)V")
    public static void method1770(@OriginalArg(0) byte[] arg0) {
        @Pc(4) Packet local4 = new Packet(arg0);
        local4.pos = arg0.length - 2;
        Static165.anInt4038 = local4.g2();
        Static26.anIntArray66 = new int[Static165.anInt4038];
        Static254.anIntArray488 = new int[Static165.anInt4038];
        Static274.anIntArray440 = new int[Static165.anInt4038];
        Static159.aBooleanArray87 = new boolean[Static165.anInt4038];
        Static64.aByteArrayArray9 = new byte[Static165.anInt4038][];
        Static269.anIntArray252 = new int[Static165.anInt4038];
        Static7.aByteArrayArray5 = new byte[Static165.anInt4038][];
        local4.pos = arg0.length - Static165.anInt4038 * 8 - 7;
        Static124.anInt3080 = local4.g2();
        Static227.anInt5091 = local4.g2();
        @Pc(66) int local66 = (local4.g1() & 0xFF) + 1;
        @Pc(68) int local68;
        for (local68 = 0; local68 < Static165.anInt4038; local68++) {
            Static274.anIntArray440[local68] = local4.g2();
        }
        for (local68 = 0; local68 < Static165.anInt4038; local68++) {
            Static269.anIntArray252[local68] = local4.g2();
        }
        for (local68 = 0; local68 < Static165.anInt4038; local68++) {
            Static254.anIntArray488[local68] = local4.g2();
        }
        for (local68 = 0; local68 < Static165.anInt4038; local68++) {
            Static26.anIntArray66[local68] = local4.g2();
        }
        local4.pos = arg0.length + 3 - Static165.anInt4038 * 8 - local66 * 3 - 7;
        Static259.anIntArray513 = new int[local66];
        for (local68 = 1; local68 < local66; local68++) {
            Static259.anIntArray513[local68] = local4.g3();
            if (Static259.anIntArray513[local68] == 0) {
                Static259.anIntArray513[local68] = 1;
            }
        }
        local4.pos = 0;
        for (local68 = 0; local68 < Static165.anInt4038; local68++) {
            @Pc(195) int local195 = Static254.anIntArray488[local68];
            @Pc(199) int local199 = Static26.anIntArray66[local68];
            @Pc(203) int local203 = local195 * local199;
            @Pc(206) byte[] local206 = new byte[local203];
            @Pc(208) boolean local208 = false;
            Static7.aByteArrayArray5[local68] = local206;
            @Pc(215) byte[] local215 = new byte[local203];
            Static64.aByteArrayArray9[local68] = local215;
            @Pc(223) int local223 = local4.g1();
            @Pc(232) int local232;
            if ((local223 & 0x1) == 0) {
                for (local232 = 0; local232 < local203; local232++) {
                    local206[local232] = local4.g1b();
                }
                if ((local223 & 0x2) != 0) {
                    for (local232 = 0; local232 < local203; local232++) {
                        @Pc(343) byte local343 = local215[local232] = local4.g1b();
                        local208 |= local343 != -1;
                    }
                }
            } else {
                local232 = 0;
                label88:
                while (true) {
                    @Pc(241) int local241;
                    if (local232 >= local195) {
                        if ((local223 & 0x2) == 0) {
                            break;
                        }
                        local232 = 0;
                        while (true) {
                            if (local232 >= local195) {
                                break label88;
                            }
                            for (local241 = 0; local241 < local199; local241++) {
                                @Pc(291) byte local291 = local215[local195 * local241 + local232] = local4.g1b();
                                local208 |= local291 != -1;
                            }
                            local232++;
                        }
                    }
                    for (local241 = 0; local241 < local199; local241++) {
                        local206[local232 + local241 * local195] = local4.g1b();
                    }
                    local232++;
                }
            }
            Static159.aBooleanArray87[local68] = local208;
        }
    }

    @OriginalMember(owner = "client!jg", name = "a", descriptor = "(I)[Lclient!ek;")
    public static SoftwarePix8[] method2406() {
        @Pc(2) SoftwarePix8[] local2 = new SoftwarePix8[Static165.anInt4038];
        for (@Pc(8) int local8 = 0; local8 < Static165.anInt4038; local8++) {
            local2[local8] = new SoftwarePix8(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local8], Static269.anIntArray252[local8], Static254.anIntArray488[local8], Static26.anIntArray66[local8], Static7.aByteArrayArray5[local8], Static259.anIntArray513);
        }
        Static75.method1631();
        return local2;
    }

    @OriginalMember(owner = "client!ui", name = "h", descriptor = "(I)[Lclient!ok;")
    public static Pix8[] method4331() {
        @Pc(8) Pix8[] local8 = new Pix8[Static165.anInt4038];
        for (@Pc(10) int local10 = 0; local10 < Static165.anInt4038; local10++) {
            if (GameShell.glRenderer) {
                local8[local10] = new GlPix8(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], Static7.aByteArrayArray5[local10], Static259.anIntArray513);
            } else {
                local8[local10] = new SoftwarePix8(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], Static7.aByteArrayArray5[local10], Static259.anIntArray513);
            }
        }
        Static75.method1631();
        return local8;
    }

    @OriginalMember(owner = "client!uj", name = "a", descriptor = "(BLclient!ve;I)Z")
    public static boolean method4346(@OriginalArg(1) Js5 arg0, @OriginalArg(2) int arg1) {
        @Pc(13) byte[] local13 = arg0.method4500(arg1);
        if (local13 == null) {
            return false;
        } else {
            method1770(local13);
            return true;
        }
    }

    @OriginalMember(owner = "client!wh", name = "b", descriptor = "(B)Lclient!ok;")
    public static Pix8 method4614() {
        @Pc(27) Pix8 local27;
        if (GameShell.glRenderer) {
            local27 = new GlPix8(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], Static7.aByteArrayArray5[0], Static259.anIntArray513);
        } else {
            local27 = new SoftwarePix8(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], Static7.aByteArrayArray5[0], Static259.anIntArray513);
        }
        Static75.method1631();
        return local27;
    }

    @OriginalMember(owner = "client!ml", name = "a", descriptor = "(BILclient!ve;)[Lclient!ek;")
    public static SoftwarePix8[] method3088(@OriginalArg(1) int arg0, @OriginalArg(2) Js5 arg1) {
        return method4346(arg1, arg0) ? method2406() : null;
    }
}
