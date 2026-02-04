import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!bd")
public final class QuickChatPhrase {

	@OriginalMember(owner = "client!bd", name = "c", descriptor = "I")
	public int anInt439;

	@OriginalMember(owner = "client!bd", name = "f", descriptor = "Lclient!cb;")
	public QuickChatPhraseType aClass3_Sub2_Sub6_1;

	@OriginalMember(owner = "client!bd", name = "h", descriptor = "[I")
	public int[] anIntArray33;

    @OriginalMember(owner = "client!vh", name = "a", descriptor = "(ILclient!wa;)Lclient!bd;")
    public static QuickChatPhrase method3568(@OriginalArg(1) Packet arg0) {
        @Pc(3) QuickChatPhrase local3 = new QuickChatPhrase();
        local3.anInt439 = arg0.g2();
        local3.aClass3_Sub2_Sub6_1 = QuickChatPhraseType.list(local3.anInt439);
        return local3;
    }
}
