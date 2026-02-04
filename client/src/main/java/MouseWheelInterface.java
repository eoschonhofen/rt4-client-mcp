import java.awt.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!uc")
public abstract class MouseWheelInterface {

    @OriginalMember(owner = "client!dh", name = "a", descriptor = "(B)Lclient!uc;")
    public static MouseWheelInterface method1150() {
        try {
            return (MouseWheelInterface) Class.forName("ClientMouseWheelListener").getDeclaredConstructor().newInstance();
        } catch (@Pc(15) Throwable local15) {
            return null;
        }
    }

    @OriginalMember(owner = "client!uc", name = "a", descriptor = "(I)I")
	public abstract int getRotation();

	@OriginalMember(owner = "client!uc", name = "a", descriptor = "(ZLjava/awt/Component;)V")
	public abstract void removeListeners(@OriginalArg(1) Component arg0);

	@OriginalMember(owner = "client!uc", name = "a", descriptor = "(Ljava/awt/Component;I)V")
	public abstract void addListeners(@OriginalArg(0) Component arg0);
}
