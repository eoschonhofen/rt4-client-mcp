import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!vk")
public abstract class PixMap {

	@OriginalMember(owner = "client!vk", name = "e", descriptor = "[I")
	protected int[] anIntArray472;

	@OriginalMember(owner = "client!vk", name = "g", descriptor = "Ljava/awt/Image;")
	protected Image anImage4;

	@OriginalMember(owner = "client!vk", name = "i", descriptor = "I")
	protected int anInt5339;

	@OriginalMember(owner = "client!vk", name = "k", descriptor = "I")
	protected int anInt5341;

	@OriginalMember(owner = "client!kd", name = "a", descriptor = "(IIZLjava/awt/Component;)Lclient!vk;")
	public static PixMap method2579(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) Component arg2) {
		try {
			@Pc(12) Class local12 = Class.forName("JavaPixMap");
			@Pc(16) PixMap local16 = (PixMap) local12.getDeclaredConstructor().newInstance();
			local16.method4192(arg0, arg1, arg2);
			return local16;
		} catch (@Pc(25) Throwable local25) {
			@Pc(29) JavaSafePixMap local29 = new JavaSafePixMap();
			local29.method4192(arg0, arg1, arg2);
			return local29;
		}
	}

	@OriginalMember(owner = "client!vk", name = "a", descriptor = "(IILjava/awt/Graphics;I)V")
	public abstract void method4186(@OriginalArg(2) Graphics arg0);

	@OriginalMember(owner = "client!vk", name = "a", descriptor = "(I)V")
	public final void method4189() {
		Pix2D.method2491(this.anIntArray472, this.anInt5341, this.anInt5339);
	}

	@OriginalMember(owner = "client!vk", name = "a", descriptor = "(IIIILjava/awt/Graphics;I)V")
	public abstract void draw(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Graphics arg3, @OriginalArg(5) int arg4);

	@OriginalMember(owner = "client!vk", name = "a", descriptor = "(IZILjava/awt/Component;)V")
	public abstract void method4192(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component arg2);
}
