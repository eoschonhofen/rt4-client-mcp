import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!qe")
public final class ClientInvCache extends Linkable {

	@OriginalMember(owner = "client!bj", name = "v", descriptor = "Lclient!sc;")
	public static HashTable invList = new HashTable(32);

	@OriginalMember(owner = "client!qe", name = "p", descriptor = "[I")
	public int[] objId = new int[] { -1 };

	@OriginalMember(owner = "client!qe", name = "u", descriptor = "[I")
	public int[] objCount = new int[] { 0 };

	@OriginalMember(owner = "client!ba", name = "a", descriptor = "(IB)I")
	public static int method446(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return 0;
		}
		@Pc(17) ClientInvCache local17 = (ClientInvCache) invList.find((long) arg0);
		if (local17 == null) {
			return InvType.list(arg0).size;
		}
		@Pc(31) int local31 = 0;
		for (@Pc(33) int local33 = 0; local33 < local17.objId.length; local33++) {
			if (local17.objId[local33] == -1) {
				local31++;
			}
		}
		return local31 + InvType.list(arg0).size - local17.objId.length;
	}

	@OriginalMember(owner = "client!hn", name = "f", descriptor = "(B)V")
	public static void deleteAll() {
		invList = new HashTable(32);
	}

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "(II)V")
	public static void delete(@OriginalArg(0) int arg0) {
		@Pc(14) ClientInvCache local14 = (ClientInvCache) invList.find((long) arg0);
		if (local14 != null) {
			local14.unlink();
		}
	}

	@OriginalMember(owner = "client!bd", name = "a", descriptor = "(BI)V")
	public static void method475(@OriginalArg(1) int arg0) {
		@Pc(8) ClientInvCache local8 = (ClientInvCache) invList.find((long) arg0);
		if (local8 != null) {
			for (@Pc(24) int local24 = 0; local24 < local8.objId.length; local24++) {
				local8.objId[local24] = -1;
				local8.objCount[local24] = 0;
			}
		}
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(III)I")
	public static int getType(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) ClientInvCache local10 = (ClientInvCache) invList.find((long) arg0);
		if (local10 == null) {
			return -1;
		} else if (arg1 < 0 || arg1 >= local10.objId.length) {
			return -1;
		} else {
			return local10.objId[arg1];
		}
    }

	@OriginalMember(owner = "client!od", name = "a", descriptor = "(IZII)I")
	public static int method3319(@OriginalArg(1) boolean arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(19) ClientInvCache local19 = (ClientInvCache) invList.find((long) arg1);
		if (local19 == null) {
			return 0;
		}
		@Pc(27) int local27 = 0;
		for (@Pc(29) int local29 = 0; local29 < local19.objId.length; local29++) {
			if (local19.objId[local29] >= 0 && ObjType.anInt3245 > local19.objId[local29]) {
				@Pc(56) ObjType local56 = ObjType.list(local19.objId[local29]);
				if (local56.aClass133_6 != null) {
					@Pc(68) IntNode local68 = (IntNode) local56.aClass133_6.find((long) arg2);
					if (local68 != null) {
						if (arg0) {
							local27 += local19.objCount[local29] * local68.anInt3141;
						} else {
							local27 += local68.anInt3141;
						}
					}
				}
			}
		}
		return local27;
	}

	@OriginalMember(owner = "client!bm", name = "a", descriptor = "(III)I")
	public static int getCount(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) ClientInvCache local10 = (ClientInvCache) invList.find((long) arg0);
		if (local10 == null) {
			return 0;
		} else if (arg1 < 0 || arg1 >= local10.objCount.length) {
			return 0;
		} else {
			return local10.objCount[arg1];
		}
    }

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(BII)I")
	public static int invTotal(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(8) ClientInvCache local8 = (ClientInvCache) invList.find((long) arg0);
		if (local8 == null) {
			return 0;
		} else if (arg1 == -1) {
			return 0;
		} else {
			@Pc(25) int local25 = 0;
			for (@Pc(27) int local27 = 0; local27 < local8.objCount.length; local27++) {
				if (arg1 == local8.objId[local27]) {
					local25 += local8.objCount[local27];
				}
			}
			return local25;
		}
	}

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IIIIB)V")
	public static void set(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(12) ClientInvCache local12 = (ClientInvCache) invList.find((long) arg3);
		if (local12 == null) {
			local12 = new ClientInvCache();
			invList.put(local12, (long) arg3);
		}
		if (arg1 >= local12.objId.length) {
			@Pc(39) int[] local39 = new int[arg1 + 1];
			@Pc(44) int[] local44 = new int[arg1 + 1];
			@Pc(46) int local46;
			for (local46 = 0; local46 < local12.objId.length; local46++) {
				local39[local46] = local12.objId[local46];
				local44[local46] = local12.objCount[local46];
			}
			for (local46 = local12.objId.length; local46 < arg1; local46++) {
				local39[local46] = -1;
				local44[local46] = 0;
			}
			local12.objId = local39;
			local12.objCount = local44;
		}
		local12.objId[arg1] = arg0;
		local12.objCount[arg1] = arg2;
	}
}
