import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!cj")
public final class AudioThread implements Runnable {

	@OriginalMember(owner = "client!cj", name = "m", descriptor = "Lsignlink!ll;")
	public SignLink signlink;

	@OriginalMember(owner = "client!cj", name = "p", descriptor = "[Lclient!vh;")
	public final PcmPlayer[] players = new PcmPlayer[2];

	@OriginalMember(owner = "client!cj", name = "g", descriptor = "Z")
	public volatile boolean shutdown = false;

	@OriginalMember(owner = "client!cj", name = "t", descriptor = "Z")
	public volatile boolean running = false;

	@OriginalMember(owner = "client!cj", name = "run", descriptor = "()V")
	@Override
	public final void run() {
		this.running = true;
		try {
			while (!this.shutdown) {
				for (@Pc(9) int local9 = 0; local9 < 2; local9++) {
					@Pc(19) PcmPlayer local19 = this.players[local9];
					if (local19 != null) {
						local19.method3565();
					}
				}
				Static231.sleepPrecise(10L);
				Static140.flushEvents(this.signlink, null);
			}
		} catch (@Pc(43) Exception local43) {
			JagException.report(null, local43);
		} finally {
			this.running = false;
		}
	}
}
