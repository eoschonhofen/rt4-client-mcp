import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!jd")
public final class MouseTracking implements Runnable {

	@OriginalMember(owner = "client!jd", name = "e", descriptor = "Z")
	public boolean active = true;

	@OriginalMember(owner = "client!jd", name = "f", descriptor = "Ljava/lang/Object;")
	public final Object lock = new Object();

	@OriginalMember(owner = "client!jd", name = "k", descriptor = "I")
	public int length = 0;

	@OriginalMember(owner = "client!jd", name = "l", descriptor = "[I")
	public final int[] y = new int[500];

	@OriginalMember(owner = "client!jd", name = "n", descriptor = "[I")
	public final int[] x = new int[500];

	@OriginalMember(owner = "client!jd", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		while (this.active) {
			@Pc(12) Object local12 = this.lock;
			synchronized (this.lock) {
				if (this.length < 500) {
					this.x[this.length] = Static215.mouseX;
					this.y[this.length] = Static223.mouseY;
					this.length++;
				}
			}

			Static231.sleepPrecise(50L);
		}
	}
}
