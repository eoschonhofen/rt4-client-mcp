import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!al")
public final class Js5Loader {

	@OriginalMember(owner = "client!al", name = "a", descriptor = "Lclient!wa;")
	private Packet masterIndexBuffer;

	@OriginalMember(owner = "client!al", name = "s", descriptor = "[Lclient!bg;")
	private Js5CachedResourceProvider[] aClass14_Sub1Array1;

	@OriginalMember(owner = "client!al", name = "j", descriptor = "Lclient!k;")
	private final Js5CacheQueue aClass80_1;

	@OriginalMember(owner = "client!al", name = "f", descriptor = "Lclient!jb;")
	private final Js5Net js5Net;

	@OriginalMember(owner = "client!al", name = "c", descriptor = "Lclient!pm;")
	private Js5NetRequest masterIndexRequest;

	@OriginalMember(owner = "client!al", name = "<init>", descriptor = "(Lclient!jb;Lclient!k;)V")
	public Js5Loader(@OriginalArg(0) Js5Net arg0, @OriginalArg(1) Js5CacheQueue arg1) {
		this.aClass80_1 = arg1;
		this.js5Net = arg0;
		if (!this.js5Net.method2326()) {
			this.masterIndexRequest = this.js5Net.queueRequest(255, (byte) 0, 255, true);
		}
	}

	@OriginalMember(owner = "client!al", name = "b", descriptor = "(I)Z")
	public final boolean method178() {
		if (this.masterIndexBuffer != null) {
			return true;
		}

		if (this.masterIndexRequest == null) {
			if (this.js5Net.method2326()) {
				return false;
			}

			this.masterIndexRequest = this.js5Net.queueRequest(255, (byte) 0, 255, true);
		}

		if (this.masterIndexRequest.aBoolean226) {
			return false;
		} else {
			this.masterIndexBuffer = new Packet(this.masterIndexRequest.method3554());
			this.aClass14_Sub1Array1 = new Js5CachedResourceProvider[(this.masterIndexBuffer.data.length - 5) / 8];
			return true;
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(B)V")
	public final void method179() {
		if (this.aClass14_Sub1Array1 == null) {
			return;
		}

		@Pc(13) int local13;
		for (local13 = 0; local13 < this.aClass14_Sub1Array1.length; local13++) {
			if (this.aClass14_Sub1Array1[local13] != null) {
				this.aClass14_Sub1Array1[local13].method537();
			}
		}

		for (local13 = 0; local13 < this.aClass14_Sub1Array1.length; local13++) {
			if (this.aClass14_Sub1Array1[local13] != null) {
				this.aClass14_Sub1Array1[local13].method534();
			}
		}
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(IILclient!ge;Lclient!ge;)Lclient!bg;")
	public final Js5CachedResourceProvider method180(@OriginalArg(1) int arg0, @OriginalArg(2) DataFile arg1, @OriginalArg(3) DataFile arg2) {
		return this.method188(arg2, arg0, arg1);
	}

	@OriginalMember(owner = "client!al", name = "a", descriptor = "(Lclient!ge;IIZLclient!ge;)Lclient!bg;")
	private Js5CachedResourceProvider method188(@OriginalArg(0) DataFile arg0, @OriginalArg(2) int arg1, @OriginalArg(4) DataFile arg2) {
		if (this.masterIndexBuffer == null) {
			throw new RuntimeException();
		}

		this.masterIndexBuffer.pos = arg1 * 8 + 5;
		if (this.masterIndexBuffer.data.length <= this.masterIndexBuffer.pos) {
			throw new RuntimeException();
		} else if (this.aClass14_Sub1Array1[arg1] == null) {
			@Pc(56) int local56 = this.masterIndexBuffer.g4();
			@Pc(61) int local61 = this.masterIndexBuffer.g4();
			@Pc(75) Js5CachedResourceProvider local75 = new Js5CachedResourceProvider(arg1, arg0, arg2, this.js5Net, this.aClass80_1, local56, local61, true);
			this.aClass14_Sub1Array1[arg1] = local75;
			return local75;
		} else {
			return this.aClass14_Sub1Array1[arg1];
		}
	}
}
