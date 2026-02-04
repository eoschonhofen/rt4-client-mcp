import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ug")
public final class ClientMouseListener implements MouseListener, MouseMotionListener, FocusListener {

	@OriginalMember(owner = "client!he", name = "bb", descriptor = "Lclient!ug;")
	public static ClientMouseListener aClass150_1 = new ClientMouseListener();

	@OriginalMember(owner = "client!he", name = "Y", descriptor = "I")
	public static volatile int anInt2467 = 0;

	@OriginalMember(owner = "client!lh", name = "u", descriptor = "I")
	public static volatile int anInt3521 = -1;

	@OriginalMember(owner = "client!nb", name = "j", descriptor = "I")
	public static volatile int anInt4039 = -1;

	@OriginalMember(owner = "client!eg", name = "w", descriptor = "I")
	public static volatile int anInt1759 = 0;

	@OriginalMember(owner = "client!ck", name = "k", descriptor = "I")
	public static volatile int anInt1034 = 0;

	@OriginalMember(owner = "client!sa", name = "Y", descriptor = "I")
	public static volatile int anInt4973 = 0;

	@OriginalMember(owner = "client!ra", name = "jb", descriptor = "J")
	public static volatile long aLong161 = 0L;

	@OriginalMember(owner = "client!dc", name = "W", descriptor = "I")
	public static volatile int anInt1313 = 0;
    @OriginalMember(owner = "client!ah", name = "s", descriptor = "I")
    public static int mouseClickX = 0;
	@OriginalMember(owner = "client!em", name = "y", descriptor = "I")
	public static int mouseClickY = 0;
	@OriginalMember(owner = "client!rh", name = "o", descriptor = "I")
	public static int mouseX = 0;
	@OriginalMember(owner = "client!sc", name = "v", descriptor = "I")
	public static int mouseY = 0;
	@OriginalMember(owner = "client!bl", name = "Q", descriptor = "I")
	public static int mouseButton = 0;
	@OriginalMember(owner = "client!lk", name = "Z", descriptor = "I")
	public static int mouseClickButton = 0;
	@OriginalMember(owner = "client!kf", name = "c", descriptor = "J")
	public static long aLong175 = 0L;

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(Ljava/awt/Component;Z)V")
    public static void addListeners(@OriginalArg(0) Component arg0) {
        arg0.addMouseListener(aClass150_1);
        arg0.addMouseMotionListener(aClass150_1);
        arg0.addFocusListener(aClass150_1);
    }

    @OriginalMember(owner = "client!ug", name = "a", descriptor = "(I)V")
    public static void method4277() {
        if (aClass150_1 != null) {
            @Pc(5) ClientMouseListener local5 = aClass150_1;
            synchronized (aClass150_1) {
                aClass150_1 = null;
            }
        }
    }

	@OriginalMember(owner = "client!ii", name = "b", descriptor = "(I)V")
	public static void loop() {
		@Pc(2) ClientMouseListener local2 = aClass150_1;
		synchronized (aClass150_1) {
			mouseButton = anInt1759;
			mouseX = anInt3521;
			mouseY = anInt4039;
			mouseClickButton = anInt1313;
			mouseClickX = anInt1034;
			anInt2467++;
			mouseClickY = anInt4973;
			aLong175 = aLong161;
			anInt1313 = 0;
		}
	}

	@OriginalMember(owner = "client!lc", name = "a", descriptor = "(B)I")
	public static int getIdleTimer() {
		return anInt2467;
	}

	@OriginalMember(owner = "client!dl", name = "a", descriptor = "(II)V")
	public static void setIdleTimer(@OriginalArg(1) int arg0) {
		@Pc(10) ClientMouseListener local10 = aClass150_1;
		synchronized (aClass150_1) {
			anInt2467 = arg0;
		}
	}

    @OriginalMember(owner = "client!sc", name = "a", descriptor = "(ILjava/awt/Component;)V")
    public static void shutdown(@OriginalArg(1) Component arg0) {
        arg0.removeMouseListener(aClass150_1);
        arg0.removeMouseMotionListener(aClass150_1);
        arg0.removeFocusListener(aClass150_1);
        anInt1759 = 0;
    }

    @OriginalMember(owner = "client!ug", name = "mouseMoved", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseMoved(@OriginalArg(0) MouseEvent arg0) {
		if (aClass150_1 != null) {
			anInt2467 = 0;
			anInt3521 = arg0.getX();
			anInt4039 = arg0.getY();
		}
	}

	@OriginalMember(owner = "client!ug", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final synchronized void focusLost(@OriginalArg(0) FocusEvent arg0) {
		if (aClass150_1 != null) {
			anInt1759 = 0;
		}
	}

	@OriginalMember(owner = "client!ug", name = "mouseDragged", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseDragged(@OriginalArg(0) MouseEvent arg0) {
		if (aClass150_1 != null) {
			anInt2467 = 0;
			anInt3521 = arg0.getX();
			anInt4039 = arg0.getY();
		}
	}

	@OriginalMember(owner = "client!ug", name = "mouseReleased", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseReleased(@OriginalArg(0) MouseEvent arg0) {
		if (aClass150_1 != null) {
			anInt2467 = 0;
			anInt1759 = 0;
			@Pc(14) int local14 = arg0.getModifiers();
			if ((local14 & 0x10) == 0) {
			}
			if ((local14 & 0x4) == 0) {
			}
			if ((local14 & 0x8) == 0) {
			}
		}
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "client!ug", name = "mouseClicked", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final void mouseClicked(@OriginalArg(0) MouseEvent arg0) {
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "client!ug", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent arg0) {
	}

	@OriginalMember(owner = "client!ug", name = "mousePressed", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mousePressed(@OriginalArg(0) MouseEvent arg0) {
		if (aClass150_1 != null) {
			anInt2467 = 0;
			anInt1034 = arg0.getX();
			anInt4973 = arg0.getY();
			aLong161 = MonotonicTime.currentTime();
			if ((arg0.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == 0) {
				anInt1313 = 1;
				anInt1759 = 1;
			} else {
				anInt1313 = 2;
				anInt1759 = 2;
			}
			@Pc(29) int local29 = arg0.getModifiers();
			if ((local29 & 0x10) == 0) {
			}
			if ((local29 & 0x4) != 0) {
			}
			if ((local29 & 0x8) != 0) {
			}
		}
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "client!ug", name = "mouseExited", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseExited(@OriginalArg(0) MouseEvent arg0) {
		if (aClass150_1 != null) {
			anInt2467 = 0;
			anInt3521 = -1;
			anInt4039 = -1;
		}
	}

	@OriginalMember(owner = "client!ug", name = "mouseEntered", descriptor = "(Ljava/awt/event/MouseEvent;)V")
	@Override
	public final synchronized void mouseEntered(@OriginalArg(0) MouseEvent arg0) {
		if (aClass150_1 != null) {
			anInt2467 = 0;
			anInt3521 = arg0.getX();
			anInt4039 = arg0.getY();
		}
	}
}
