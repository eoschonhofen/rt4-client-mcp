import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static223 {

	@OriginalMember(owner = "client!sc", name = "f", descriptor = "Lclient!na;")
	public static final JagString aClass100_946 = JagString.wrap("(R");

	@OriginalMember(owner = "client!sc", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_947 = JagString.wrap(" )2> <col=ff9040>");

	@OriginalMember(owner = "client!sc", name = "p", descriptor = "I")
	public static int anInt5029 = 0;

	@OriginalMember(owner = "client!sc", name = "z", descriptor = "[Z")
	public static final boolean[] aBooleanArray116 = new boolean[100];

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "(IIILclient!km;)V")
	public static void method3855(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) ClientNPC arg2) {
		if (arg2.anInt3369 == arg1 && arg1 != -1) {
			@Pc(10) SeqType local10 = SeqType.list(arg1);
			@Pc(13) int local13 = local10.anInt5347;
			if (local13 == 1) {
				arg2.anInt3373 = 1;
				arg2.anInt3425 = 0;
				arg2.anInt3360 = 0;
				arg2.anInt3371 = 0;
				arg2.anInt3420 = arg0;
				Static152.method2836(arg2.z, local10, arg2.x, false, arg2.anInt3425);
			}
			if (local13 == 2) {
				arg2.anInt3371 = 0;
			}
		} else if (arg1 == -1 || arg2.anInt3369 == -1 || SeqType.list(arg1).anInt5355 >= SeqType.list(arg2.anInt3369).anInt5355) {
			arg2.anInt3360 = 0;
			arg2.anInt3369 = arg1;
			arg2.anInt3373 = 1;
			arg2.anInt3371 = 0;
			arg2.anInt3420 = arg0;
			arg2.anInt3405 = arg2.anInt3409;
			arg2.anInt3425 = 0;
			if (arg2.anInt3369 != -1) {
				Static152.method2836(arg2.z, SeqType.list(arg2.anInt3369), arg2.x, false, arg2.anInt3425);
			}
		}
	}

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "()V")
	public static void method3858() {
		for (@Pc(1) int local1 = 0; local1 < World.anInt726; local1++) {
			@Pc(8) Sprite local8 = World.aClass31Array3[local1];
			Static266.method4193(local8);
			World.aClass31Array3[local1] = null;
		}
		World.anInt726 = 0;
	}

}
