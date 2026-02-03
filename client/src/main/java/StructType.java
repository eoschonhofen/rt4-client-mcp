import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!lk")
public final class StructType extends Linkable2 {

	@OriginalMember(owner = "client!sk", name = "bb", descriptor = "Lclient!gn;")
	public static final LruCache aClass54_13 = new LruCache(64);

	@OriginalMember(owner = "client!bm", name = "e", descriptor = "Lclient!ve;")
	public static Js5 clientConfig;

	@OriginalMember(owner = "client!lk", name = "I", descriptor = "Lclient!sc;")
	private HashTable aClass133_14;

    @OriginalMember(owner = "client!jj", name = "a", descriptor = "(BI)Lclient!lk;")
    public static StructType list(@OriginalArg(1) int arg0) {
        @Pc(10) StructType local10 = (StructType) aClass54_13.method1806((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(26) byte[] local26 = clientConfig.getFile(26, arg0);
        local10 = new StructType();
        if (local26 != null) {
            local10.decode(new Packet(local26));
        }
        aClass54_13.method1811(local10, (long) arg0);
        return local10;
    }

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		clientConfig = arg0;
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(IIB)I")
	public final int method2798(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (this.aClass133_14 == null) {
			return arg1;
		} else {
			@Pc(29) IntNode local29 = (IntNode) this.aClass133_14.find((long) arg0);
			return local29 == null ? arg1 : local29.anInt3141;
		}
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(Lclient!wa;IB)V")
	private void decode(@OriginalArg(0) Packet arg0, @OriginalArg(1) int arg1) {
		if (arg1 != 249) {
			return;
		}
		@Pc(17) int local17 = arg0.g1();
		@Pc(25) int local25;
		if (this.aClass133_14 == null) {
			local25 = Static165.method3164(local17);
			this.aClass133_14 = new HashTable(local25);
		}
		for (local25 = 0; local25 < local17; local25++) {
			@Pc(45) boolean local45 = arg0.g1() == 1;
			@Pc(49) int local49 = arg0.g3();
			@Pc(58) Linkable local58;
			if (local45) {
				local58 = new StringNode(arg0.gjstr());
			} else {
				local58 = new IntNode(arg0.g4());
			}
			this.aClass133_14.put(local58, (long) local49);
		}
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(Lclient!na;BI)Lclient!na;")
	public final JagString method2802(@OriginalArg(0) JagString arg0, @OriginalArg(2) int arg1) {
		if (this.aClass133_14 == null) {
			return arg0;
		} else {
			@Pc(16) StringNode local16 = (StringNode) this.aClass133_14.find((long) arg1);
			return local16 == null ? arg0 : local16.aClass100_980;
		}
	}

	@OriginalMember(owner = "client!lk", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(5) int local5 = arg0.g1();
			if (local5 == 0) {
				return;
			}
			this.decode(arg0, local5);
		}
	}
}
