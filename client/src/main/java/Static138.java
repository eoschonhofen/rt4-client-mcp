import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static138 {

	@OriginalMember(owner = "client!km", name = "sc", descriptor = "[Lclient!qf;")
	public static AbstractPix32[] aClass3_Sub2_Sub1Array5;

	@OriginalMember(owner = "client!km", name = "Yc", descriptor = "I")
	public static int anInt3443;

	@OriginalMember(owner = "client!km", name = "pc", descriptor = "Z")
	public static boolean aBoolean172 = false;

	@OriginalMember(owner = "client!km", name = "tc", descriptor = "Lclient!na;")
	private static final JagString aClass100_641 = JagString.wrap("Dec");

	@OriginalMember(owner = "client!km", name = "vc", descriptor = "Lclient!na;")
	private static final JagString aClass100_642 = JagString.wrap("Jul");

	@OriginalMember(owner = "client!km", name = "wc", descriptor = "Lclient!na;")
	public static final JagString aClass100_643 = Text.aClass100_647;

	@OriginalMember(owner = "client!km", name = "xc", descriptor = "Lclient!na;")
	private static final JagString aClass100_644 = JagString.wrap("May");

	@OriginalMember(owner = "client!km", name = "yc", descriptor = "Lclient!na;")
	private static final JagString aClass100_645 = JagString.wrap("Nov");

	@OriginalMember(owner = "client!km", name = "zc", descriptor = "Lclient!na;")
	private static final JagString aClass100_646 = JagString.wrap("Mar");

	@OriginalMember(owner = "client!km", name = "Gc", descriptor = "Lclient!na;")
	private static final JagString aClass100_649 = JagString.wrap("Jan");

	@OriginalMember(owner = "client!km", name = "Hc", descriptor = "Lclient!na;")
	private static final JagString aClass100_650 = JagString.wrap("Feb");

	@OriginalMember(owner = "client!km", name = "Tc", descriptor = "Lclient!na;")
	private static final JagString aClass100_655 = JagString.wrap("Apr");

	@OriginalMember(owner = "client!km", name = "Wc", descriptor = "Lclient!na;")
	private static final JagString aClass100_656 = JagString.wrap("Jun");

	@OriginalMember(owner = "client!km", name = "Qc", descriptor = "Lclient!na;")
	private static final JagString aClass100_653 = JagString.wrap("Aug");

	@OriginalMember(owner = "client!km", name = "cd", descriptor = "Lclient!na;")
	private static final JagString aClass100_657 = JagString.wrap("Sep");

	@OriginalMember(owner = "client!km", name = "dd", descriptor = "Lclient!na;")
	private static final JagString aClass100_658 = JagString.wrap("Oct");

	@OriginalMember(owner = "client!km", name = "Ac", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array102 = new JagString[] { aClass100_649, aClass100_650, aClass100_646, aClass100_655, aClass100_644, aClass100_656, aClass100_642, aClass100_653, aClass100_657, aClass100_658, aClass100_645, aClass100_641 };

	@OriginalMember(owner = "client!km", name = "Mc", descriptor = "Lclient!na;")
	public static final JagString aClass100_652 = JagString.wrap("loginscreen");

	@OriginalMember(owner = "client!km", name = "Rc", descriptor = "[I")
	public static final int[] anIntArray324 = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };

	@OriginalMember(owner = "client!km", name = "Sc", descriptor = "Lclient!na;")
	public static final JagString aClass100_654 = JagString.wrap(":trade:");

	@OriginalMember(owner = "client!km", name = "a", descriptor = "(ILjava/lang/Object;Z)[B")
	public static byte[] method2696(@OriginalArg(1) Object arg0, @OriginalArg(2) boolean arg1) {
		if (arg0 == null) {
			return null;
		} else if (arg0 instanceof byte[]) {
			@Pc(14) byte[] local14 = (byte[]) arg0;
			return arg1 ? Static23.method648(local14) : local14;
		} else if (arg0 instanceof ByteArrayWrapper) {
			@Pc(34) ByteArrayWrapper local34 = (ByteArrayWrapper) arg0;
			return local34.method4236();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@OriginalMember(owner = "client!km", name = "f", descriptor = "(I)Z")
	public static boolean method2697() {
		return GameShell.glRenderer ? true : Static162.aBoolean190;
	}

	@OriginalMember(owner = "client!km", name = "c", descriptor = "(Z)Z")
	public static boolean updateLoading() {
		try {
			if (MidiManager.state == 2) {
				if (MidiManager.loadingMidiFile == null) {
					MidiManager.loadingMidiFile = MidiFile.load(MidiManager.midis, MidiManager.anInt5853, MidiManager.anInt5085);
					if (MidiManager.loadingMidiFile == null) {
						return false;
					}
				}

				if (MidiManager.loadingWaveCache == null) {
					MidiManager.loadingWaveCache = new WaveCache(MidiManager.aClass153_32, MidiManager.aClass153_103);
				}

				if (MidiManager.midiPlayer.method4411(MidiManager.loadingMidiFile, MidiManager.aClass153_87, MidiManager.loadingWaveCache)) {
					MidiManager.midiPlayer.method4412();
					MidiManager.midiPlayer.method4447(MidiManager.anInt5527);
					MidiManager.midiPlayer.method4431(MidiManager.aBoolean116, MidiManager.loadingMidiFile);
					MidiManager.state = 0;
					MidiManager.loadingMidiFile = null;
					MidiManager.loadingWaveCache = null;
					MidiManager.midis = null;
					return true;
				}
			}
		} catch (@Pc(68) Exception ex) {
			ex.printStackTrace();
			MidiManager.midiPlayer.stop();
			MidiManager.midis = null;
			MidiManager.loadingMidiFile = null;
			MidiManager.state = 0;
			MidiManager.loadingWaveCache = null;
		}

		return false;
	}
}
