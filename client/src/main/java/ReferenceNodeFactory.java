import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!q")
public abstract class ReferenceNodeFactory {

    @OriginalMember(owner = "client!dh", name = "b", descriptor = "(I)Lclient!q;")
    public static ReferenceNodeFactory method1147() {
        try {
            return (ReferenceNodeFactory) Class.forName("SoftReferenceNodeFactory").getDeclaredConstructor().newInstance();
        } catch (@Pc(15) Throwable local15) {
            return null;
        }
    }

    @OriginalMember(owner = "client!q", name = "a", descriptor = "(Lclient!gf;I)Lclient!gf;")
	public abstract ReferenceNode create(@OriginalArg(0) ReferenceNode node);
}
