import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!dm")
public final class IdkType {

	@OriginalMember(owner = "client!fe", name = "jb", descriptor = "Lclient!n;")
	public static final SoftLruCache recentUse = new SoftLruCache(64);

	@OriginalMember(owner = "client!bd", name = "g", descriptor = "Lclient!ve;")
	public static Js5 aClass153_8;

	@OriginalMember(owner = "client!ri", name = "c", descriptor = "Lclient!ve;")
	public static Js5 clientConfig;

	@OriginalMember(owner = "client!ec", name = "z", descriptor = "I")
	public static int anInt1716;

	@OriginalMember(owner = "client!dm", name = "b", descriptor = "[S")
	private short[] aShortArray11;

	@OriginalMember(owner = "client!dm", name = "i", descriptor = "[S")
	private short[] aShortArray12;

	@OriginalMember(owner = "client!dm", name = "s", descriptor = "[S")
	private short[] aShortArray13;

	@OriginalMember(owner = "client!dm", name = "t", descriptor = "[S")
	private short[] aShortArray14;

	@OriginalMember(owner = "client!dm", name = "w", descriptor = "[I")
	private int[] anIntArray112;

	@OriginalMember(owner = "client!dm", name = "x", descriptor = "[I")
	private final int[] anIntArray113 = new int[] { -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!dm", name = "k", descriptor = "I")
	public int anInt1457 = -1;

	@OriginalMember(owner = "client!dm", name = "A", descriptor = "Z")
	public boolean aBoolean85 = false;

	@OriginalMember(owner = "client!jl", name = "a", descriptor = "(ILclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		aClass153_8 = arg0;
		clientConfig = arg1;
		anInt1716 = clientConfig.getFileIdLimit(3);
	}

	@OriginalMember(owner = "client!gg", name = "d", descriptor = "(II)Lclient!dm;")
	public static IdkType list(@OriginalArg(0) int arg0) {
		@Pc(10) IdkType local10 = (IdkType) recentUse.find((long) arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(21) byte[] local21 = clientConfig.getFile(3, arg0);
		local10 = new IdkType();
		if (local21 != null) {
			local10.decode(new Packet(local21));
		}
		recentUse.put(local10, (long) arg0);
		return local10;
	}

	@OriginalMember(owner = "client!oi", name = "a", descriptor = "(I)V")
	public static void method3342() {
		recentUse.clear();
	}

	@OriginalMember(owner = "client!t", name = "b", descriptor = "(I)V")
	public static void method3999() {
		recentUse.method3104();
	}

	@OriginalMember(owner = "client!te", name = "a", descriptor = "(IZ)V")
	public static void method4142() {
		recentUse.method3102(5);
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(Z)Lclient!gb;")
	public final ModelUnlit method1198() {
		@Pc(13) int local13 = 0;
		@Pc(16) ModelUnlit[] local16 = new ModelUnlit[5];
		for (@Pc(18) int local18 = 0; local18 < 5; local18++) {
			if (this.anIntArray113[local18] != -1) {
				local16[local13++] = Static77.method1686(aClass153_8, this.anIntArray113[local18]);
			}
		}
		@Pc(52) ModelUnlit local52 = new ModelUnlit(local16, local13);
		@Pc(58) int local58;
		if (this.aShortArray12 != null) {
			for (local58 = 0; local58 < this.aShortArray12.length; local58++) {
				local52.method1687(this.aShortArray12[local58], this.aShortArray11[local58]);
			}
		}
		if (this.aShortArray14 != null) {
			for (local58 = 0; local58 < this.aShortArray14.length; local58++) {
				local52.method1669(this.aShortArray14[local58], this.aShortArray13[local58]);
			}
		}
		return local52;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(I)Z")
	public final boolean method1199() {
		if (this.anIntArray112 == null) {
			return true;
		}
		@Pc(13) boolean local13 = true;
		for (@Pc(22) int local22 = 0; local22 < this.anIntArray112.length; local22++) {
			if (!aClass153_8.method4491(0, this.anIntArray112[local22])) {
				local13 = false;
			}
		}
		return local13;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(BLclient!wa;I)V")
	private void decode(@OriginalArg(1) Packet arg0, @OriginalArg(2) int arg1) {
		if (arg1 == 1) {
			this.anInt1457 = arg0.g1();
			return;
		}
		@Pc(22) int local22;
		@Pc(28) int local28;
		if (arg1 == 2) {
			local22 = arg0.g1();
			this.anIntArray112 = new int[local22];
			for (local28 = 0; local28 < local22; local28++) {
				this.anIntArray112[local28] = arg0.g2();
			}
		} else if (arg1 == 3) {
			this.aBoolean85 = true;
		} else if (arg1 == 40) {
			local22 = arg0.g1();
			this.aShortArray11 = new short[local22];
			this.aShortArray12 = new short[local22];
			for (local28 = 0; local28 < local22; local28++) {
				this.aShortArray12[local28] = (short) arg0.g2();
				this.aShortArray11[local28] = (short) arg0.g2();
			}
		} else if (arg1 == 41) {
			local22 = arg0.g1();
			this.aShortArray14 = new short[local22];
			this.aShortArray13 = new short[local22];
			for (local28 = 0; local28 < local22; local28++) {
				this.aShortArray14[local28] = (short) arg0.g2();
				this.aShortArray13[local28] = (short) arg0.g2();
			}
		} else if (arg1 >= 60 && arg1 < 70) {
			this.anIntArray113[arg1 - 60] = arg0.g2();
		}
	}

	@OriginalMember(owner = "client!dm", name = "b", descriptor = "(B)Lclient!gb;")
	public final ModelUnlit method1204() {
		if (this.anIntArray112 == null) {
			return null;
		}
		@Pc(16) ModelUnlit[] local16 = new ModelUnlit[this.anIntArray112.length];
		for (@Pc(18) int local18 = 0; local18 < this.anIntArray112.length; local18++) {
			local16[local18] = Static77.method1686(aClass153_8, this.anIntArray112[local18]);
		}
		@Pc(56) ModelUnlit local56;
		if (local16.length == 1) {
			local56 = local16[0];
		} else {
			local56 = new ModelUnlit(local16, local16.length);
		}
		@Pc(70) int local70;
		if (this.aShortArray12 != null) {
			for (local70 = 0; local70 < this.aShortArray12.length; local70++) {
				local56.method1687(this.aShortArray12[local70], this.aShortArray11[local70]);
			}
		}
		if (this.aShortArray14 != null) {
			for (local70 = 0; local70 < this.aShortArray14.length; local70++) {
				local56.method1669(this.aShortArray14[local70], this.aShortArray13[local70]);
			}
		}
		return local56;
	}

	@OriginalMember(owner = "client!dm", name = "c", descriptor = "(I)Z")
	public final boolean method1205() {
		@Pc(3) boolean local3 = true;
		for (@Pc(12) int local12 = 0; local12 < 5; local12++) {
			if (this.anIntArray113[local12] != -1 && !aClass153_8.method4491(0, this.anIntArray113[local12])) {
				local3 = false;
			}
		}
		return local3;
	}

	@OriginalMember(owner = "client!dm", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(13) int local13 = arg0.g1();
			if (local13 == 0) {
				return;
			}
			this.decode(arg0, local13);
		}
	}
}
