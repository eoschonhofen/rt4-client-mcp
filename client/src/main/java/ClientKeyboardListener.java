import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!uf")
public final class ClientKeyboardListener implements KeyListener, FocusListener {

	@OriginalMember(owner = "client!pl", name = "c", descriptor = "[I")
	public static final int[] anIntArray407 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, 85, 80, 84, -1, 91, -1, -1, -1, 81, 82, 86, -1, -1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, 83, 104, 105, 103, 102, 96, 98, 97, 99, -1, -1, -1, -1, -1, -1, -1, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1, -1, -1, -1, -1, -1, -1, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54, 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37, 64, -1, -1, -1, -1, -1, 228, 231, 227, 233, 224, 219, 225, 230, 226, 232, 89, 87, -1, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, -1, -1, -1, 101, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	@OriginalMember(owner = "client!bg", name = "A", descriptor = "[I")
	public static final int[] anIntArray53 = new int[128];

	@OriginalMember(owner = "client!s", name = "e", descriptor = "[I")
	public static final int[] anIntArray375 = new int[128];

	@OriginalMember(owner = "client!vh", name = "u", descriptor = "[I")
	public static final int[] anIntArray413 = new int[128];
	@OriginalMember(owner = "client!pb", name = "q", descriptor = "[Z")
	public static final boolean[] keyHeld = new boolean[112];

	@OriginalMember(owner = "client!an", name = "ab", descriptor = "Lclient!uf;")
	public static ClientKeyboardListener aClass149_1 = new ClientKeyboardListener();

	@OriginalMember(owner = "client!si", name = "eb", descriptor = "I")
	public static volatile int anInt5140 = 0;

	@OriginalMember(owner = "client!j", name = "L", descriptor = "I")
	public static int anInt5844 = 0;

	@OriginalMember(owner = "client!sg", name = "c", descriptor = "I")
	public static int anInt5087 = 0;

	@OriginalMember(owner = "client!ec", name = "q", descriptor = "I")
	public static int anInt1708 = 0;

	@OriginalMember(owner = "client!hn", name = "V", descriptor = "I")
	public static int anInt2678 = 0;
	@OriginalMember(owner = "client!sh", name = "h", descriptor = "I")
	public static int anInt5105 = 0;
    @OriginalMember(owner = "client!hn", name = "Z", descriptor = "I")
    public static int code;
	@OriginalMember(owner = "client!pi", name = "Y", descriptor = "I")
	public static int ch;

	@OriginalMember(owner = "client!mf", name = "e", descriptor = "(I)V")
	public static void setupKeyCodeMap() {
		if (SignLink.javaVendor.toLowerCase().indexOf("microsoft") != -1) {
			anIntArray407[187] = 27;
			anIntArray407[223] = 28;
			anIntArray407[221] = 43;
			anIntArray407[188] = 71;
			anIntArray407[222] = 59;
			anIntArray407[192] = 58;
			anIntArray407[191] = 73;
			anIntArray407[219] = 42;
			anIntArray407[190] = 72;
			anIntArray407[186] = 57;
			anIntArray407[220] = 74;
			anIntArray407[189] = 26;
			return;
		}
		if (SignLink.setTraversalKeysEnabled == null) {
			anIntArray407[192] = 58;
			anIntArray407[222] = 59;
		} else {
			anIntArray407[222] = 58;
			anIntArray407[192] = 28;
			anIntArray407[520] = 59;
		}
		anIntArray407[45] = 26;
		anIntArray407[61] = 27;
		anIntArray407[91] = 42;
		anIntArray407[59] = 57;
		anIntArray407[93] = 43;
		anIntArray407[44] = 71;
		anIntArray407[92] = 74;
		anIntArray407[46] = 72;
		anIntArray407[47] = 73;
	}

	@OriginalMember(owner = "client!bi", name = "a", descriptor = "(BLjava/awt/Component;)V")
	public static void addListeners(@OriginalArg(1) Component arg0) {
		@Pc(10) Method local10 = SignLink.setTraversalKeysEnabled;
		if (local10 != null) {
			try {
				local10.invoke(arg0, Boolean.FALSE);
			} catch (@Pc(25) Throwable local25) {
			}
		}
		arg0.addKeyListener(aClass149_1);
		arg0.addFocusListener(aClass149_1);
	}

    @OriginalMember(owner = "client!ag", name = "h", descriptor = "(I)V")
    public static void method82() {
        if (aClass149_1 != null) {
            @Pc(4) ClientKeyboardListener local4 = aClass149_1;
            synchronized (aClass149_1) {
                aClass149_1 = null;
            }
        }
    }

	@OriginalMember(owner = "client!ch", name = "a", descriptor = "(Ljava/awt/Component;I)V")
	public static void shutdown(@OriginalArg(0) Component arg0) {
		arg0.removeKeyListener(aClass149_1);
		arg0.removeFocusListener(aClass149_1);
		anInt5844 = -1;
	}

	@OriginalMember(owner = "client!fc", name = "b", descriptor = "(I)V")
	public static void loop() {
		@Pc(12) ClientKeyboardListener local12 = aClass149_1;
		synchronized (aClass149_1) {
			anInt2678 = anInt5105;
			anInt5140++;
			@Pc(23) int local23;
			if (anInt5844 < 0) {
				for (local23 = 0; local23 < 112; local23++) {
					keyHeld[local23] = false;
				}
				anInt5844 = anInt5087;
			} else {
				while (anInt5844 != anInt5087) {
					local23 = anIntArray53[anInt5087];
					anInt5087 = anInt5087 + 1 & 0x7F;
					if (local23 >= 0) {
						keyHeld[local23] = true;
					} else {
						keyHeld[~local23] = false;
					}
				}
			}
			anInt5105 = anInt1708;
		}
	}

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(I)Z")
	public static boolean pollKey() {
		@Pc(6) ClientKeyboardListener local6 = aClass149_1;
		synchronized (aClass149_1) {
			if (anInt5105 == anInt2678) {
				return false;
			} else {
				code = anIntArray375[anInt2678];
				ch = anIntArray413[anInt2678];
				anInt2678 = anInt2678 + 1 & 0x7F;
				return true;
			}
		}
	}

	@OriginalMember(owner = "client!pk", name = "f", descriptor = "(B)I")
	public static int getIdleTimer() {
		return anInt5140;
	}

	@OriginalMember(owner = "client!uf", name = "keyPressed", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final synchronized void keyPressed(@OriginalArg(0) KeyEvent arg0) {
		if (aClass149_1 == null) {
			return;
		}
		anInt5140 = 0;
		@Pc(7) int local7 = arg0.getKeyCode();
		if (local7 >= 0 && anIntArray407.length > local7) {
			local7 = anIntArray407[local7];
			if ((local7 & 0x80) != 0) {
				local7 = -1;
			}
		} else {
			local7 = -1;
		}
		if (anInt5844 >= 0 && local7 >= 0) {
			anIntArray53[anInt5844] = local7;
			anInt5844 = anInt5844 + 1 & 0x7F;
			if (anInt5844 == anInt5087) {
				anInt5844 = -1;
			}
		}
		@Pc(68) int local68;
		if (local7 >= 0) {
			local68 = anInt1708 + 1 & 0x7F;
			if (local68 != anInt2678) {
				anIntArray375[anInt1708] = local7;
				anIntArray413[anInt1708] = -1;
				anInt1708 = local68;
			}
		}
		local68 = arg0.getModifiers();
		if ((local68 & 0xA) != 0 || local7 == 85 || local7 == 10) {
			arg0.consume();
		}
	}

	@OriginalMember(owner = "client!uf", name = "keyTyped", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final void keyTyped(@OriginalArg(0) KeyEvent arg0) {
		if (aClass149_1 != null) {
			@Pc(9) int local9 = Static136.method2650(arg0);
			if (local9 >= 0) {
				@Pc(21) int local21 = anInt1708 + 1 & 0x7F;
				if (anInt2678 != local21) {
					anIntArray375[anInt1708] = -1;
					anIntArray413[anInt1708] = local9;
					anInt1708 = local21;
				}
			}
		}
		arg0.consume();
	}

	@OriginalMember(owner = "client!uf", name = "focusLost", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final synchronized void focusLost(@OriginalArg(0) FocusEvent arg0) {
		if (aClass149_1 != null) {
			anInt5844 = -1;
		}
	}

	@OriginalMember(owner = "client!uf", name = "keyReleased", descriptor = "(Ljava/awt/event/KeyEvent;)V")
	@Override
	public final synchronized void keyReleased(@OriginalArg(0) KeyEvent arg0) {
		if (aClass149_1 != null) {
			anInt5140 = 0;
			@Pc(11) int local11 = arg0.getKeyCode();
			if (local11 >= 0 && anIntArray407.length > local11) {
				local11 = anIntArray407[local11] & 0xFFFFFF7F;
			} else {
				local11 = -1;
			}
			if (anInt5844 >= 0 && local11 >= 0) {
				anIntArray53[anInt5844] = ~local11;
				anInt5844 = anInt5844 + 1 & 0x7F;
				if (anInt5087 == anInt5844) {
					anInt5844 = -1;
				}
			}
		}
		arg0.consume();
	}

	@OriginalMember(owner = "client!uf", name = "focusGained", descriptor = "(Ljava/awt/event/FocusEvent;)V")
	@Override
	public final void focusGained(@OriginalArg(0) FocusEvent arg0) {
	}
}
