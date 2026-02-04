import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!j")
public abstract class TextureOp extends Linkable {

	@OriginalMember(owner = "client!j", name = "t", descriptor = "Lclient!nd;")
	protected MonochromeImageCache aClass103_41;

	@OriginalMember(owner = "client!j", name = "G", descriptor = "I")
	public int anInt5840;

	@OriginalMember(owner = "client!j", name = "H", descriptor = "Lclient!pf;")
	protected ColourImageCache aClass121_41;

	@OriginalMember(owner = "client!j", name = "u", descriptor = "[Lclient!j;")
	public final TextureOp[] aClass3_Sub1Array42;

	@OriginalMember(owner = "client!j", name = "p", descriptor = "Z")
	public boolean aBoolean309;

	@OriginalMember(owner = "client!j", name = "<init>", descriptor = "(IZ)V")
	protected TextureOp(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1) {
		this.aClass3_Sub1Array42 = new TextureOp[arg0];
		this.aBoolean309 = arg1;
	}

    @OriginalMember(owner = "client!sc", name = "a", descriptor = "(IZ)Lclient!j;")
    public static TextureOp method3860(@OriginalArg(0) int arg0) {
        if (arg0 == 0) {
            return new TextureOp0();
        } else if (arg0 == 1) {
            return new TextureOp1();
        } else if (arg0 == 2) {
            return new TextureOp2();
        } else if (arg0 == 3) {
            return new TextureOp3();
        } else if (arg0 == 4) {
            return new TextureOp4();
        } else if (arg0 == 5) {
            return new TextureOp5();
        } else if (arg0 == 6) {
            return new TextureOp6();
        } else if (arg0 == 7) {
            return new TextureOp7();
        } else if (arg0 == 8) {
            return new TextureOp8();
        } else if (arg0 == 9) {
            return new TextureOp9();
        } else if (arg0 == 10) {
            return new TextureOp10();
        } else if (arg0 == 11) {
            return new TextureOp11();
        } else if (arg0 == 12) {
            return new TextureOp12();
        } else if (arg0 == 13) {
            return new TextureOp13();
        } else if (arg0 == 14) {
            return new TextureOp14();
        } else if (arg0 == 15) {
            return new TextureOp15();
        } else if (arg0 == 16) {
            return new TextureOp16();
        } else if (arg0 == 17) {
            return new TextureOp17();
        } else if (arg0 == 18) {
            return new TextureOp18();
        } else if (arg0 == 19) {
            return new TextureOp19();
        } else if (arg0 == 20) {
            return new TextureOp20();
        } else if (arg0 == 21) {
            return new TextureOp21();
        } else if (arg0 == 22) {
            return new TextureOp22();
        } else if (arg0 == 23) {
            return new TextureOp23();
        } else if (arg0 == 24) {
            return new TextureOp24();
        } else if (arg0 == 25) {
            return new TextureOp25();
        } else if (arg0 == 26) {
            return new TextureOp26();
        } else if (arg0 == 27) {
            return new TextureOp27();
        } else if (arg0 == 28) {
            return new TextureOp28();
        } else if (arg0 == 29) {
            return new TextureOp29();
        } else if (arg0 == 30) {
            return new TextureOp30();
        } else if (arg0 == 31) {
            return new TextureOp31();
        } else if (arg0 == 32) {
            return new TextureOp32();
        } else if (arg0 == 33) {
            return new TextureOp33();
        } else if (arg0 == 34) {
            return new TextureOp34();
        } else if (arg0 == 35) {
            return new TextureOp35();
        } else if (arg0 == 36) {
            return new TextureOp36();
        } else if (arg0 == 37) {
            return new TextureOp37();
        } else if (arg0 == 38) {
            return new TextureOp38();
        } else if (arg0 == 39) {
            return new TextureOp39();
        } else {
            return null;
        }
    }

    @OriginalMember(owner = "client!j", name = "a", descriptor = "(III)[I")
	protected final int[] method4624(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		return this.aClass3_Sub1Array42[arg0].aBoolean309 ? this.aClass3_Sub1Array42[arg0].method4626(arg1) : this.aClass3_Sub1Array42[arg0].method4638(arg1)[0];
	}

	@OriginalMember(owner = "client!j", name = "a", descriptor = "(IB)[I")
	public int[] method4626(@OriginalArg(0) int arg0) {
		throw new IllegalStateException("This operation does not have a monochrome output");
	}

	@OriginalMember(owner = "client!j", name = "d", descriptor = "(B)I")
	public int method4627() {
		return -1;
	}

	@OriginalMember(owner = "client!j", name = "a", descriptor = "(ILclient!wa;Z)V")
	public void method4629(@OriginalArg(0) int arg0, @OriginalArg(1) Packet arg1) {
	}

	@OriginalMember(owner = "client!j", name = "e", descriptor = "(I)V")
	public void method4630() {
	}

	@OriginalMember(owner = "client!j", name = "f", descriptor = "(I)I")
	public int method4631() {
		return -1;
	}

	@OriginalMember(owner = "client!j", name = "b", descriptor = "(III)V")
	public final void method4632(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(15) int local15 = this.anInt5840 == 255 ? arg0 : this.anInt5840;
		if (this.aBoolean309) {
			this.aClass121_41 = new ColourImageCache(local15, arg0, arg1);
		} else {
			this.aClass103_41 = new MonochromeImageCache(local15, arg0, arg1);
		}
	}

	@OriginalMember(owner = "client!j", name = "e", descriptor = "(B)V")
	public void method4633() {
		if (this.aBoolean309) {
			this.aClass121_41.method3442();
			this.aClass121_41 = null;
		} else {
			this.aClass103_41.method3169();
			this.aClass103_41 = null;
		}
	}

	@OriginalMember(owner = "client!j", name = "a", descriptor = "(IIB)[[I")
	protected final int[][] method4634(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (this.aClass3_Sub1Array42[arg1].aBoolean309) {
			@Pc(32) int[] local32 = this.aClass3_Sub1Array42[arg1].method4626(arg0);
			return new int[][] { local32, local32, local32 };
		} else {
			return this.aClass3_Sub1Array42[arg1].method4638(arg0);
		}
	}

	@OriginalMember(owner = "client!j", name = "b", descriptor = "(II)[[I")
	public int[][] method4638(@OriginalArg(1) int arg0) {
		throw new IllegalStateException("This operation does not have a colour output");
	}
}
