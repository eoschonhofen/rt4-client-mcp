import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class TitleScreen {
    @OriginalMember(owner = "client!cb", name = "cb", descriptor = "Lclient!na;")
    public static final JagString aClass100_165 = JagString.wrap("titlebg");
    @OriginalMember(owner = "client!jm", name = "A", descriptor = "Lclient!na;")
    private static final JagString aClass100_603 = JagString.wrap("");
    @OriginalMember(owner = "client!jm", name = "z", descriptor = "Lclient!na;")
    public static JagString loadString = aClass100_603;
    @OriginalMember(owner = "client!qc", name = "P", descriptor = "I")
    public static int loadPos = 10;
    @OriginalMember(owner = "client!pa", name = "O", descriptor = "Lclient!na;")
    public static final JagString AUTO_EMPTY = JagString.wrap("");
    @OriginalMember(owner = "client!pa", name = "P", descriptor = "Lclient!na;")
    public static JagString loginPass = AUTO_EMPTY;
    @OriginalMember(owner = "client!pa", name = "S", descriptor = "Lclient!na;")
    public static JagString loginUser = AUTO_EMPTY;

    @OriginalMember(owner = "client!se", name = "a", descriptor = "(Lclient!na;Lclient!na;IB)V")
    public static void method3896(@OriginalArg(0) JagString arg0, @OriginalArg(1) JagString arg1, @OriginalArg(2) int arg2) {
        loginPass = arg1;
        Static5.anInt39 = arg2;
        loginUser = arg0;
        if (loginUser.equalsInner(AUTO_EMPTY) || loginPass.equalsInner(AUTO_EMPTY)) {
            Client.worldHopError = 3;
        } else if (Client.anInt3103 == -1) {
            Client.worldListWaitingTime = 0;
            Client.worldHopFailCount = 0;
            Client.worldHopError = -3;
            Client.worldHopStep = 1;
            @Pc(43) Packet local43 = new Packet(128);
            local43.p1(10);
            local43.p2((int) (Math.random() * 99999.0D));
            local43.p2(530);
            local43.p8(loginUser.method3158());
            local43.p4((int) (Math.random() * 9.9999999E7D));
            local43.pjstr(loginPass);
            local43.p4((int) (Math.random() * 9.9999999E7D));
            local43.rsaenc(Static86.aBigInteger1, Static256.aBigInteger2);
            Client.out.pos = 0;
            Client.out.p1(210);
            Client.out.p1(local43.pos);
            Client.out.tinyenc(local43.data, local43.pos);
        } else {
            Client.method1208();
        }
    }

    @OriginalMember(owner = "client!nd", name = "a", descriptor = "(ILclient!ve;)V")
    public static void method3172(@OriginalArg(1) Js5 arg0) {
        Static262.anInt5754 = arg0.getGroupId(aClass100_165);
        Static136.anInt3322 = arg0.getGroupId(Static165.aClass100_776);
    }
}
