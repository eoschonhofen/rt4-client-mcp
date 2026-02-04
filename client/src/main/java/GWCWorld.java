import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ba")
public final class GWCWorld extends AbstractWorld {

	@OriginalMember(owner = "client!ba", name = "t", descriptor = "Lclient!na;")
	public JagString aClass100_69;

	@OriginalMember(owner = "client!ba", name = "u", descriptor = "I")
	public int anInt382;

	@OriginalMember(owner = "client!ba", name = "A", descriptor = "Lclient!na;")
	public JagString aClass100_71;

    @OriginalMember(owner = "client!h", name = "a", descriptor = "(I)Lclient!ba;")
    public static GWCWorld method1821() {
        return Static101.aClass10_Sub1Array1.length > Static51.anInt1682 ? Static101.aClass10_Sub1Array1[Static51.anInt1682++] : null;
    }

    @OriginalMember(owner = "client!ba", name = "g", descriptor = "(I)Lclient!ee;")
	public final WorldInfo method445() {
		return Static203.aClass32Array1[this.anInt377];
	}
}
