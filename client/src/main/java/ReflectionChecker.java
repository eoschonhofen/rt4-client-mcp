import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionChecker {
    @OriginalMember(owner = "client!qi", name = "u", descriptor = "Lclient!ih;")
    public static LinkList checks = new LinkList();

    @OriginalMember(owner = "client!ag", name = "a", descriptor = "(ILjava/lang/String;)Ljava/lang/Class;")
    public static Class method85(@OriginalArg(1) String arg0) throws ClassNotFoundException {
        if (arg0.equals("B")) {
            return Byte.TYPE;
        } else if (arg0.equals("I")) {
            return Integer.TYPE;
        } else if (arg0.equals("S")) {
            return Short.TYPE;
        } else if (arg0.equals("J")) {
            return Long.TYPE;
        } else if (arg0.equals("Z")) {
            return Boolean.TYPE;
        } else if (arg0.equals("F")) {
            return Float.TYPE;
        } else if (arg0.equals("D")) {
            return Double.TYPE;
        } else if (arg0.equals("C")) {
            return Character.TYPE;
        } else {
            return Class.forName(arg0);
        }
    }

    @OriginalMember(owner = "client!qg", name = "a", descriptor = "(Lsignlink!ll;Lclient!wa;IB)V")
    public static void method3654(@OriginalArg(0) SignLink arg0, @OriginalArg(1) Packet arg1, @OriginalArg(2) int arg2) {
        @Pc(17) ReflectionCheck local17 = new ReflectionCheck();
        local17.size = arg1.g1();
        local17.id = arg1.g4();
        local17.method = new PrivilegedRequest[local17.size];
        local17.error = new int[local17.size];
        local17.methodArgs = new byte[local17.size][][];
        local17.field = new PrivilegedRequest[local17.size];
        local17.type = new int[local17.size];
        local17.fieldValue = new int[local17.size];
        for (@Pc(59) int local59 = 0; local59 < local17.size; local59++) {
            try {
                @Pc(71) int local71 = arg1.g1();
                @Pc(93) String local93;
                @Pc(104) String local104;
                @Pc(95) int local95;
                if (local71 == 0 || local71 == 1 || local71 == 2) {
                    local93 = new String(arg1.gjstr().builderToString());
                    local95 = 0;
                    local104 = new String(arg1.gjstr().builderToString());
                    if (local71 == 1) {
                        local95 = arg1.g4();
                    }
                    local17.type[local59] = local71;
                    local17.fieldValue[local59] = local95;
                    local17.field[local59] = arg0.method5126(local104, method85(local93));
                } else if (local71 == 3 || local71 == 4) {
                    local93 = new String(arg1.gjstr().builderToString());
                    local104 = new String(arg1.gjstr().builderToString());
                    local95 = arg1.g1();
                    @Pc(171) String[] local171 = new String[local95];
                    for (@Pc(173) int local173 = 0; local173 < local95; local173++) {
                        local171[local173] = new String(arg1.gjstr().builderToString());
                    }
                    @Pc(193) byte[][] local193 = new byte[local95][];
                    @Pc(210) int local210;
                    if (local71 == 3) {
                        for (@Pc(199) int local199 = 0; local199 < local95; local199++) {
                            local210 = arg1.g4();
                            local193[local199] = new byte[local210];
                            arg1.method2190(local210, local193[local199]);
                        }
                    }
                    local17.type[local59] = local71;
                    @Pc(234) Class[] local234 = new Class[local95];
                    for (local210 = 0; local210 < local95; local210++) {
                        local234[local210] = method85(local171[local210]);
                    }
                    local17.method[local59] = arg0.method5122(method85(local93), local234, local104);
                    local17.methodArgs[local59] = local193;
                }
            } catch (@Pc(269) ClassNotFoundException local269) {
                local17.error[local59] = -1;
            } catch (@Pc(276) SecurityException local276) {
                local17.error[local59] = -2;
            } catch (@Pc(283) NullPointerException local283) {
                local17.error[local59] = -3;
            } catch (@Pc(290) Exception local290) {
                local17.error[local59] = -4;
            } catch (@Pc(297) Throwable local297) {
                local17.error[local59] = -5;
            }
        }
        checks.push(local17);
    }

    @OriginalMember(owner = "client!t", name = "a", descriptor = "(Lclient!i;II)V")
    public static void performCheck(@OriginalArg(0) PacketBit arg0) {
        while (true) {
            @Pc(18) ReflectionCheck local18 = (ReflectionCheck) checks.head();
            if (local18 == null) {
                return;
            }
            @Pc(23) boolean local23 = false;
            @Pc(25) int local25;
            for (local25 = 0; local25 < local18.size; local25++) {
                if (local18.field[local25] != null) {
                    if (local18.field[local25].status == 2) {
                        local18.error[local25] = -5;
                    }
                    if (local18.field[local25].status == 0) {
                        local23 = true;
                    }
                }
                if (local18.method[local25] != null) {
                    if (local18.method[local25].status == 2) {
                        local18.error[local25] = -6;
                    }
                    if (local18.method[local25].status == 0) {
                        local23 = true;
                    }
                }
            }
            if (local23) {
                return;
            }
            arg0.p1Enc(163);
            arg0.p1(0);
            local25 = arg0.pos;
            arg0.p4(local18.id);
            for (@Pc(121) int local121 = 0; local121 < local18.size; local121++) {
                if (local18.error[local121] == 0) {
                    try {
                        @Pc(151) int local151 = local18.type[local121];
                        @Pc(168) Field local168;
                        @Pc(195) int local195;
                        if (local151 == 0) {
                            local168 = (Field) local18.field[local121].result;
                            local195 = local168.getInt(null);
                            arg0.p1(0);
                            arg0.p4(local195);
                        } else if (local151 == 1) {
                            local168 = (Field) local18.field[local121].result;
                            local168.setInt(null, local18.fieldValue[local121]);
                            arg0.p1(0);
                        } else if (local151 == 2) {
                            local168 = (Field) local18.field[local121].result;
                            local195 = local168.getModifiers();
                            arg0.p1(0);
                            arg0.p4(local195);
                        }
                        @Pc(234) Method local234;
                        if (local151 == 3) {
                            local234 = (Method) local18.method[local121].result;
                            @Pc(239) byte[][] local239 = local18.methodArgs[local121];
                            @Pc(243) Object[] local243 = new Object[local239.length];
                            for (@Pc(245) int local245 = 0; local245 < local239.length; local245++) {
                                @Pc(259) ObjectInputStream local259 = new ObjectInputStream(new ByteArrayInputStream(local239[local245]));
                                local243[local245] = local259.readObject();
                            }
                            @Pc(272) Object local272 = local234.invoke(null, local243);
                            if (local272 == null) {
                                arg0.p1(0);
                            } else if (local272 instanceof Number) {
                                arg0.p1(1);
                                arg0.p8(((Number) local272).longValue());
                            } else if (local272 instanceof JagString) {
                                arg0.p1(2);
                                arg0.pjstr((JagString) local272);
                            } else {
                                arg0.p1(4);
                            }
                        } else if (local151 == 4) {
                            local234 = (Method) local18.method[local121].result;
                            local195 = local234.getModifiers();
                            arg0.p1(0);
                            arg0.p4(local195);
                        }
                    } catch (@Pc(338) ClassNotFoundException local338) {
                        arg0.p1(-10);
                    } catch (@Pc(344) InvalidClassException local344) {
                        arg0.p1(-11);
                    } catch (@Pc(350) StreamCorruptedException local350) {
                        arg0.p1(-12);
                    } catch (@Pc(356) OptionalDataException local356) {
                        arg0.p1(-13);
                    } catch (@Pc(362) IllegalAccessException local362) {
                        arg0.p1(-14);
                    } catch (@Pc(368) IllegalArgumentException local368) {
                        arg0.p1(-15);
                    } catch (@Pc(374) InvocationTargetException local374) {
                        arg0.p1(-16);
                    } catch (@Pc(380) SecurityException local380) {
                        arg0.p1(-17);
                    } catch (@Pc(386) IOException local386) {
                        arg0.p1(-18);
                    } catch (@Pc(392) NullPointerException local392) {
                        arg0.p1(-19);
                    } catch (@Pc(398) Exception local398) {
                        arg0.p1(-20);
                    } catch (@Pc(404) Throwable local404) {
                        arg0.p1(-21);
                    }
                } else {
                    arg0.p1(local18.error[local121]);
                }
            }
            arg0.addcrc(local25);
            arg0.psize1(arg0.pos - local25);
            local18.unlink();
        }
    }
}
