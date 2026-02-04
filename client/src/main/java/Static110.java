import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static110 {

	@OriginalMember(owner = "client!ih", name = "l", descriptor = "Lclient!n;")
	public static final SoftLruCache aClass99_15 = new SoftLruCache(4);

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(I[Lclient!hg;)V")
	public static void method2280(@OriginalArg(0) int arg0, @OriginalArg(1) GlSquare[] arg1) {
		World.activeGlTiles[arg0] = arg1;
	}

}
