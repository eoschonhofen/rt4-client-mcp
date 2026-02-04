import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!qc")
public final class ClientScript extends Linkable2 {

	@OriginalMember(owner = "client!qc", name = "I", descriptor = "I")
	public int intArgCount;

	@OriginalMember(owner = "client!qc", name = "L", descriptor = "I")
	public int intLocalCount;

	@OriginalMember(owner = "client!qc", name = "N", descriptor = "I")
	public int stringArgCount;

	@OriginalMember(owner = "client!qc", name = "O", descriptor = "[I")
	public int[] instructions;

	@OriginalMember(owner = "client!qc", name = "Q", descriptor = "[Lclient!sc;")
	public HashTable[] aClass133Array1;

	@OriginalMember(owner = "client!qc", name = "R", descriptor = "Lclient!na;")
	public JagString name;

	@OriginalMember(owner = "client!qc", name = "S", descriptor = "I")
	public int stringLocalCount;

	@OriginalMember(owner = "client!qc", name = "T", descriptor = "[Lclient!na;")
	public JagString[] stringOperands;

	@OriginalMember(owner = "client!qc", name = "W", descriptor = "[I")
	public int[] intOperands;

    @OriginalMember(owner = "client!hc", name = "a", descriptor = "(IB)Lclient!qc;")
    public static ClientScript get(@OriginalArg(0) int arg0) {
        @Pc(12) ClientScript local12 = (ClientScript) Static105.aClass54_9.method1806((long) arg0);
        if (local12 != null) {
            return local12;
        }
        @Pc(22) byte[] local22 = Client.scripts.getFile(arg0, 0);
        if (local22 == null) {
            return null;
        }
        local12 = new ClientScript();
        @Pc(42) Packet local42 = new Packet(local22);
        local42.pos = local42.data.length - 2;
        @Pc(53) int local53 = local42.g2();
        @Pc(63) int local63 = local42.data.length - local53 - 12 - 2;
        local42.pos = local63;
        @Pc(70) int local70 = local42.g4();
        local12.intLocalCount = local42.g2();
        local12.stringLocalCount = local42.g2();
        local12.intArgCount = local42.g2();
        local12.stringArgCount = local42.g2();
        @Pc(98) int local98 = local42.g1();
        @Pc(107) int local107;
        @Pc(114) int local114;
        if (local98 > 0) {
            local12.aClass133Array1 = new HashTable[local98];
            for (local107 = 0; local107 < local98; local107++) {
                local114 = local42.g2();
                @Pc(121) HashTable local121 = new HashTable(Static165.method3164(local114));
                local12.aClass133Array1[local107] = local121;
                while (local114-- > 0) {
                    @Pc(136) int local136 = local42.g4();
                    @Pc(140) int local140 = local42.g4();
                    local121.put(new IntNode(local140), (long) local136);
                }
            }
        }
        local42.pos = 0;
        local12.name = local42.method2176();
        local12.instructions = new int[local70];
        local12.stringOperands = new JagString[local70];
        local107 = 0;
        local12.intOperands = new int[local70];
        while (local63 > local42.pos) {
            local114 = local42.g2();
            if (local114 == 3) {
                local12.stringOperands[local107] = local42.gjstr();
            } else if (local114 >= 100 || local114 == 21 || local114 == 38 || local114 == 39) {
                local12.intOperands[local107] = local42.g1();
            } else {
                local12.intOperands[local107] = local42.g4();
            }
            local12.instructions[local107++] = local114;
        }
        Static105.aClass54_9.method1811(local12, (long) arg0);
        return local12;
    }
}
