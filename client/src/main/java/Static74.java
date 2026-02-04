import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static74 {

	@OriginalMember(owner = "client!fn", name = "Z", descriptor = "Lclient!na;")
	public static final JagString aClass100_461 = JagString.wrap(")1 ");

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(ZIIIIIII)V")
	public static void method1623(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (arg3 == arg6) {
			Static152.method2826(arg0, arg2, arg5, arg6, arg1, arg4);
		} else if (arg1 - arg6 >= Static172.anInt4164 && arg6 + arg1 <= Static224.anInt5063 && Static267.anInt5773 <= arg2 - arg3 && Static106.anInt2869 >= arg2 + arg3) {
			Static270.method4594(arg5, arg1, arg2, arg4, arg6, arg3, arg0);
		} else {
			Static231.method3981(arg6, arg5, arg4, arg0, arg2, arg1, arg3);
		}
	}

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(ILclient!be;)V")
	public static void method1625(@OriginalArg(1) IfType arg0) {
		@Pc(7) IfType local7 = Static280.method4668(arg0);
		@Pc(19) int local19;
		@Pc(17) int local17;
		if (local7 == null) {
			local17 = GameShell.anInt5554;
			local19 = GameShell.anInt1448;
		} else {
			local17 = local7.anInt459;
			local19 = local7.anInt445;
		}
		Static150.method2801(local17, local19, arg0, false);
		Static111.method2291(arg0, local17, local19);
	}

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(Lclient!ve;Lclient!ve;Z)I")
	public static int method1628(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
		@Pc(5) int local5 = 0;
		if (arg0.method4506(Static166.anInt4049)) {
			local5++;
		}
		if (arg0.method4506(Static130.anInt3161)) {
			local5++;
		}
		if (arg0.method4506(Static73.anInt2077)) {
			local5++;
		}
		if (arg1.method4506(Static166.anInt4049)) {
			local5++;
		}
		if (arg1.method4506(Static130.anInt3161)) {
			local5++;
		}
		if (arg1.method4506(Static73.anInt2077)) {
			local5++;
		}
		return local5;
	}
}
