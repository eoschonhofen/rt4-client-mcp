import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!pc")
public interface MaterialRenderer {

    @OriginalMember(owner = "client!te", name = "e", descriptor = "(I)V")
    static void method4145() {
        Static151.method2809();
        Static2.anInterface4Array1 = new MaterialRenderer[7];
        Static2.anInterface4Array1[1] = new SpecularMaterialRenderer();
        Static2.anInterface4Array1[2] = new LiquidMaterialRenderer();
        Static2.anInterface4Array1[3] = new UnderwaterMaterialRenderer();
        Static2.anInterface4Array1[4] = new WaterMaterialRenderer();
        Static2.anInterface4Array1[5] = new WaterfallMaterialRenderer();
        Static2.anInterface4Array1[6] = new UnlitMaterialRenderer();
    }

    @OriginalMember(owner = "client!pc", name = "a", descriptor = "()V")
	void method4602();

	@OriginalMember(owner = "client!pc", name = "b", descriptor = "()V")
	void method4603();

	@OriginalMember(owner = "client!pc", name = "a", descriptor = "(I)V")
	void method4604(@OriginalArg(0) int arg0);

	@OriginalMember(owner = "client!pc", name = "c", descriptor = "()I")
	int method4605();
}
