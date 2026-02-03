import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!e")
public final class FullScreen {

	@OriginalMember(owner = "signlink!e", name = "b", descriptor = "Ljava/awt/DisplayMode;")
	private DisplayMode previousDisplayMode;

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "Ljava/awt/GraphicsDevice;")
	private GraphicsDevice device;

	@OriginalMember(owner = "signlink!e", name = "<init>", descriptor = "()V")
	public FullScreen() throws Exception {
		@Pc(3) GraphicsEnvironment local3 = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.device = local3.getDefaultScreenDevice();

		if (!this.device.isFullScreenSupported()) {
			@Pc(15) GraphicsDevice[] local15 = local3.getScreenDevices();
			for (@Pc(19) int local19 = 0; local19 < local15.length; local19++) {
				@Pc(27) GraphicsDevice local27 = local15[local19];
				if (local27 != null && local27.isFullScreenSupported()) {
					this.device = local27;
					return;
				}
			}

			throw new Exception();
		}
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(Ljava/awt/Frame;B)V")
	private void setWindow(@OriginalArg(0) Frame arg0) {
		@Pc(1) boolean local1 = false;
		try {
			@Pc(6) Field local6 = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
			local6.setAccessible(true);
			@Pc(16) boolean local16 = (Boolean) local6.get(this.device);
			if (local16) {
				local6.set(this.device, Boolean.FALSE);
				local1 = true;
			}
		} catch (@Pc(27) Throwable ignore) {
		}

		try {
			this.device.setFullScreenWindow(arg0);
		} finally {
			if (local1) {
				try {
					@Pc(66) Field local66 = Class.forName("sun.awt.Win32GraphicsDevice").getDeclaredField("valid");
					local66.set(this.device, Boolean.TRUE);
				} catch (@Pc(73) Throwable ignore) {
				}
			}
		}
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(IIIILjava/awt/Frame;I)V")
	public final void enter(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Frame arg3, @OriginalArg(5) int arg4) {
		this.previousDisplayMode = this.device.getDisplayMode();
		if (this.previousDisplayMode == null) {
			throw new NullPointerException();
		}

		arg3.setUndecorated(true);
		arg3.enableInputMethods(false);
		this.setWindow(arg3);
		if (arg0 == 0) {
			@Pc(37) int local37 = this.previousDisplayMode.getRefreshRate();
			@Pc(41) DisplayMode[] local41 = this.device.getDisplayModes();
			@Pc(43) boolean local43 = false;
			for (@Pc(45) int local45 = 0; local45 < local41.length; local45++) {
				if (local41[local45].getWidth() == arg4 && local41[local45].getHeight() == arg2 && arg1 == local41[local45].getBitDepth()) {
					@Pc(77) int local77 = local41[local45].getRefreshRate();
					if (!local43 || Math.abs(local77 - local37) < Math.abs(arg0 - local37)) {
						local43 = true;
						arg0 = local77;
					}
				}
			}
			if (!local43) {
				arg0 = local37;
			}
		}
		this.device.setDisplayMode(new DisplayMode(arg4, arg2, arg1, arg0));
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(Z)[I")
	public final int[] getDisplayModes() {
		@Pc(9) DisplayMode[] local9 = this.device.getDisplayModes();
		@Pc(15) int[] local15 = new int[local9.length << 2];
		for (@Pc(17) int local17 = 0; local17 < local9.length; local17++) {
			local15[local17 << 2] = local9[local17].getWidth();
			local15[(local17 << 2) + 1] = local9[local17].getHeight();
			local15[(local17 << 2) + 2] = local9[local17].getBitDepth();
			local15[(local17 << 2) + 3] = local9[local17].getRefreshRate();
		}
		return local15;
	}

	@OriginalMember(owner = "signlink!e", name = "a", descriptor = "(I)V")
	public final void exit() {
		if (this.previousDisplayMode != null) {
			this.device.setDisplayMode(this.previousDisplayMode);
			if (!this.device.getDisplayMode().equals(this.previousDisplayMode)) {
				throw new RuntimeException("Did not return to correct resolution!");
			}

			this.previousDisplayMode = null;
		}

		this.setWindow(null);
	}
}
