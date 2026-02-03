import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!lj")
public final class MillisTimer extends Timer {

	@OriginalMember(owner = "client!lj", name = "o", descriptor = "I")
	private int opos;

	@OriginalMember(owner = "client!lj", name = "x", descriptor = "[J")
	private final long[] otim = new long[10];

	@OriginalMember(owner = "client!lj", name = "r", descriptor = "I")
	private int ratio = 256;

	@OriginalMember(owner = "client!lj", name = "u", descriptor = "I")
	private int delta = 1;

	@OriginalMember(owner = "client!lj", name = "v", descriptor = "I")
	private int count = 0;

	@OriginalMember(owner = "client!lj", name = "k", descriptor = "J")
	private long ntime = MonotonicTime.currentTime();

	@OriginalMember(owner = "client!lj", name = "<init>", descriptor = "()V")
	public MillisTimer() {
		for (@Pc(22) int local22 = 0; local22 < 10; local22++) {
			this.otim[local22] = this.ntime;
		}
	}

	@OriginalMember(owner = "client!lj", name = "b", descriptor = "(I)V")
	@Override
	public final void reset() {
		for (@Pc(7) int local7 = 0; local7 < 10; local7++) {
			this.otim[local7] = 0L;
		}
	}

	@OriginalMember(owner = "client!lj", name = "a", descriptor = "(III)I")
	@Override
	public final int count(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(17) int local17 = this.delta;
		@Pc(20) int local20 = this.ratio;
		this.ratio = 300;
		this.delta = 1;
		this.ntime = MonotonicTime.currentTime();
		if (this.otim[this.opos] == 0L) {
			this.ratio = local20;
			this.delta = local17;
		} else if (this.otim[this.opos] < this.ntime) {
			this.ratio = (int) ((long) (arg1 * 2560) / (this.ntime - this.otim[this.opos]));
		}
		if (this.ratio < 25) {
			this.ratio = 25;
		}
		if (this.ratio > 256) {
			this.ratio = 256;
			this.delta = (int) ((long) arg1 - (this.ntime - this.otim[this.opos]) / 10L);
		}
		if (arg1 < this.delta) {
			this.delta = arg1;
		}
		this.otim[this.opos] = this.ntime;
		this.opos = (this.opos + 1) % 10;
		@Pc(139) int local139;
		if (this.delta > 1) {
			for (local139 = 0; local139 < 10; local139++) {
				if (this.otim[local139] != 0L) {
					this.otim[local139] += this.delta;
				}
			}
		}
		if (arg0 > this.delta) {
			this.delta = arg0;
		}
		Static231.sleepPrecise((long) this.delta);
		local139 = 0;
		while (this.count < 256) {
			this.count += this.ratio;
			local139++;
		}
		this.count &= 0xFF;
		return local139;
	}
}
