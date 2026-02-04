import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MidiManager {
    @OriginalMember(owner = "client!cb", name = "hb", descriptor = "Lclient!le;")
    public static WaveCache loadingWaveCache;
    @OriginalMember(owner = "client!bd", name = "i", descriptor = "I")
    public static int state = 0;
    @OriginalMember(owner = "client!nj", name = "e", descriptor = "Lclient!va;")
    public static MidiPlayer midiPlayer;
    @OriginalMember(owner = "client!nj", name = "g", descriptor = "Lclient!ve;")
    public static Js5 midis;
    @OriginalMember(owner = "client!sf", name = "j", descriptor = "I")
    public static int anInt5085;
    @OriginalMember(owner = "client!ui", name = "R", descriptor = "I")
    public static int anInt5527;
    @OriginalMember(owner = "client!wi", name = "ab", descriptor = "I")
    public static int anInt5853;
    @OriginalMember(owner = "client!fl", name = "u", descriptor = "Z")
    public static boolean aBoolean116;
    @OriginalMember(owner = "client!eg", name = "t", descriptor = "I")
    public static int anInt1757;
    @OriginalMember(owner = "client!le", name = "c", descriptor = "Lclient!rf;")
    public static MidiFile loadingMidiFile;
    @OriginalMember(owner = "client!rb", name = "f", descriptor = "Lclient!ve;")
    public static Js5 aClass153_87;
    @OriginalMember(owner = "client!gd", name = "m", descriptor = "Lclient!ve;")
    public static Js5 aClass153_32;
    @OriginalMember(owner = "client!uh", name = "P", descriptor = "Lclient!ve;")
    public static Js5 aClass153_103;

    @OriginalMember(owner = "client!sj", name = "a", descriptor = "(Z)V")
    public static void updateFadeOut() {
        try {
            if (state == 1) {
                @Pc(16) int local16 = midiPlayer.method4440();
                if (local16 > 0 && midiPlayer.loaded()) {
                    local16 -= anInt1757;
                    if (local16 < 0) {
                        local16 = 0;
                    }
                    midiPlayer.method4447(local16);
                    return;
                }
                midiPlayer.stop();
                midiPlayer.method4426();
                loadingMidiFile = null;
                loadingWaveCache = null;
                if (midis == null) {
                    state = 0;
                } else {
                    state = 2;
                }
            }
        } catch (@Pc(62) Exception local62) {
            local62.printStackTrace();
            midiPlayer.stop();
            midis = null;
            loadingMidiFile = null;
            state = 0;
            loadingWaveCache = null;
        }
    }

    @OriginalMember(owner = "client!kk", name = "a", descriptor = "(I)Z")
    public static boolean isInitialised() {
        return state == 0 ? midiPlayer.loaded() : true;
    }

    @OriginalMember(owner = "client!jh", name = "a", descriptor = "(Lclient!ve;ZIIZI)V")
    public static void play(@OriginalArg(0) Js5 arg0, @OriginalArg(2) int arg1, @OriginalArg(5) int arg2) {
        midis = arg0;
        state = 1;
        anInt5527 = arg2;
        anInt5085 = 0;
        anInt5853 = arg1;
        aBoolean116 = false;
        anInt1757 = 10000;
    }

    @OriginalMember(owner = "client!v", name = "a", descriptor = "(ZIILclient!ve;ZII)V")
    public static void method526(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1, @OriginalArg(5) int arg2) {
        midis = arg1;
        anInt5085 = 0;
        anInt5853 = arg0;
        aBoolean116 = false;
        state = 1;
        anInt1757 = 2;
        anInt5527 = arg2;
    }

    @OriginalMember(owner = "client!ck", name = "a", descriptor = "(ILclient!va;Lclient!ve;Lclient!ve;Lclient!ve;)Z")
    public static boolean init(@OriginalArg(1) MidiPlayer arg0, @OriginalArg(2) Js5 arg1, @OriginalArg(3) Js5 arg2, @OriginalArg(4) Js5 arg3) {
        aClass153_87 = arg1;
        aClass153_32 = arg3;
        aClass153_103 = arg2;
        midiPlayer = arg0;
        return true;
    }

    @OriginalMember(owner = "client!ce", name = "a", descriptor = "(II)V")
    public static void method801() {
        anInt5527 = 0;
        anInt5085 = -1;
        state = 1;
        anInt1757 = 2;
        aBoolean116 = false;
        midis = null;
        anInt5853 = -1;
    }

    @OriginalMember(owner = "client!sj", name = "c", descriptor = "(II)V")
    public static void method3956(@OriginalArg(0) int arg0) {
        if (state == 0) {
            midiPlayer.method4447(arg0);
        } else {
            anInt5527 = arg0;
        }
    }

    @OriginalMember(owner = "client!km", name = "c", descriptor = "(Z)Z")
    public static boolean updateLoading() {
        try {
            if (state == 2) {
                if (loadingMidiFile == null) {
                    loadingMidiFile = MidiFile.load(midis, anInt5853, anInt5085);
                    if (loadingMidiFile == null) {
                        return false;
                    }
                }

                if (loadingWaveCache == null) {
                    loadingWaveCache = new WaveCache(aClass153_32, aClass153_103);
                }

                if (midiPlayer.method4411(loadingMidiFile, aClass153_87, loadingWaveCache)) {
                    midiPlayer.method4412();
                    midiPlayer.method4447(anInt5527);
                    midiPlayer.method4431(aBoolean116, loadingMidiFile);
                    state = 0;
                    loadingMidiFile = null;
                    loadingWaveCache = null;
                    midis = null;
                    return true;
                }
            }
        } catch (@Pc(68) Exception ex) {
            ex.printStackTrace();
            midiPlayer.stop();
            midis = null;
            loadingMidiFile = null;
            state = 0;
            loadingWaveCache = null;
        }

        return false;
    }

    @OriginalMember(owner = "client!th", name = "a", descriptor = "(Z)V")
    public static void method4548() {
        midiPlayer.stop();
        state = 1;
        midis = null;
    }
}
