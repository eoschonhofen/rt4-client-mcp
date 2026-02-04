import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static48 {

	@OriginalMember(owner = "client!dl", name = "c", descriptor = "I")
	public static int anInt1447 = 0;

	@OriginalMember(owner = "client!dl", name = "a", descriptor = "(B)Lclient!wa;")
	public static Packet method1196() {
		@Pc(4) Packet local4 = new Packet(34);
		local4.p1(11);
		local4.p1(Static113.anInt4609);
		local4.p1(Static162.aBoolean190 ? 1 : 0);
		local4.p1(Static80.aBoolean231 ? 1 : 0);
		local4.p1(Static250.aBoolean283 ? 1 : 0);
		local4.p1(Static53.aBoolean99 ? 1 : 0);
		local4.p1(Static15.aBoolean33 ? 1 : 0);
		local4.p1(Static11.aBoolean15 ? 1 : 0);
		local4.p1(Static159.aBoolean189 ? 1 : 0);
		local4.p1(Static209.aBoolean240 ? 1 : 0);
		local4.p1(Static139.anInt3451);
		local4.p1(Static178.highDetailLighting ? 1 : 0);
		local4.p1(Static220.aBoolean244 ? 1 : 0);
		local4.p1(Static71.aBoolean107 ? 1 : 0);
		local4.p1(Static102.anInt2679);
		local4.p1(Client.lowMem ? 1 : 0);
		local4.p1(Client.waveVolume);
		local4.p1(Client.midiVolume);
		local4.p1(Client.ambientVolume);
		local4.p2(Static114.anInt5831);
		local4.p2(Static22.anInt729);
		local4.p1(Static76.method1644());
		local4.p4(Static164.anInt3988);
		local4.p1(Static214.anInt5581);
		local4.p1(Static164.aBoolean191 ? 1 : 0);
		local4.p1(Client.aBoolean63 ? 1 : 0);
		local4.p1(Static141.anInt3474);
		local4.p1(Static127.aBoolean159 ? 1 : 0);
		local4.p1(Static64.aBoolean111 ? 1 : 0);
		return local4;
	}

}
