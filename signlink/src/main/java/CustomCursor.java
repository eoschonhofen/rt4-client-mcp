import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!g")
public final class CustomCursor {

	@OriginalMember(owner = "signlink!g", name = "b", descriptor = "Ljava/awt/Component;")
	private Component component;

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "Ljava/awt/Robot;")
	private final Robot robot = new Robot();

	@OriginalMember(owner = "signlink!g", name = "<init>", descriptor = "()V")
	public CustomCursor() throws Exception {
	}

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "(BLjava/awt/Point;ILjava/awt/Component;I[I)V")
	public final void setCursor(@OriginalArg(1) Point arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int[] arg4) {
        if (arg4 != null) {
            @Pc(13) BufferedImage local13 = new BufferedImage(arg1, arg3, 2);
            local13.setRGB(0, 0, arg1, arg3, arg4, 0, arg1);
            arg2.setCursor(arg2.getToolkit().createCustomCursor(local13, arg0, null));
        } else {
            arg2.setCursor(null);
        }
    }

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "(III)V")
	public final void mouseMove(@OriginalArg(0) int x, @OriginalArg(2) int y) {
		this.robot.mouseMove(x, y);
	}

	@OriginalMember(owner = "signlink!g", name = "a", descriptor = "(Ljava/awt/Component;IZ)V")
	public final void setComponent(@OriginalArg(0) Component component, @OriginalArg(2) boolean reset) {
		if (reset) {
			component = null;
		} else if (component == null) {
			throw new NullPointerException();
		}

		if (component == this.component) {
			return;
		}

		if (this.component != null) {
			this.component.setCursor(null);
			this.component = null;
		}

		if (component != null) {
			component.setCursor(component.getToolkit().createCustomCursor(new BufferedImage(1, 1, 2), new Point(0, 0), null));
			this.component = component;
		}
	}
}
