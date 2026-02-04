import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static200 {

	@OriginalMember(owner = "client!qe", name = "b", descriptor = "(II)V")
	public static void method3628(@OriginalArg(1) int arg0) {
		Client.menuNumEntries--;
		if (Client.menuNumEntries == arg0) {
			return;
		}
		Static289.method2617(Client.aClass100Array168, arg0 + 1, Client.aClass100Array168, arg0, Client.menuNumEntries - arg0);
		Static289.method2617(Client.aClass100Array160, arg0 + 1, Client.aClass100Array160, arg0, Client.menuNumEntries - arg0);
		Static289.method2613(Client.anIntArray382, arg0 + 1, Client.anIntArray382, arg0, Client.menuNumEntries - arg0);
		Static289.method2616(Client.menuAction, arg0 + 1, Client.menuAction, arg0, Client.menuNumEntries - arg0);
		Static289.method2611(Client.aLongArray5, arg0 + 1, Client.aLongArray5, arg0, Client.menuNumEntries - arg0);
		Static289.method2613(Client.anIntArray408, arg0 + 1, Client.anIntArray408, arg0, Client.menuNumEntries - arg0);
		Static289.method2613(Client.anIntArray142, arg0 + 1, Client.anIntArray142, arg0, Client.menuNumEntries - arg0);
	}
}
