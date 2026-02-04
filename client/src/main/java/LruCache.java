import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gn")
public final class LruCache {

	@OriginalMember(owner = "client!gn", name = "l", descriptor = "Lclient!rg;")
	private Linkable2 aClass3_Sub2_37 = new Linkable2();

	@OriginalMember(owner = "client!gn", name = "s", descriptor = "Lclient!ce;")
	private final LinkList2 aClass16_1 = new LinkList2();

	@OriginalMember(owner = "client!gn", name = "u", descriptor = "I")
	private int anInt2314;

	@OriginalMember(owner = "client!gn", name = "r", descriptor = "I")
	private final int anInt2313;

	@OriginalMember(owner = "client!gn", name = "q", descriptor = "Lclient!sc;")
	private final HashTable aClass133_5;

	@OriginalMember(owner = "client!gn", name = "<init>", descriptor = "(I)V")
	public LruCache(@OriginalArg(0) int arg0) {
		@Pc(13) int local13 = 1;
		this.anInt2314 = arg0;
		while (arg0 > local13 + local13) {
			local13 += local13;
		}
		this.anInt2313 = arg0;
		this.aClass133_5 = new HashTable(local13);
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(JI)Lclient!rg;")
	public final Linkable2 method1806(@OriginalArg(0) long arg0) {
		@Pc(16) Linkable2 local16 = (Linkable2) this.aClass133_5.find(arg0);
		if (local16 != null) {
			this.aClass16_1.pushFront(local16);
		}
		return local16;
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(I)Lclient!ab;")
	public final Linkable method1808() {
		return this.aClass133_5.search();
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(Lclient!rg;JB)V")
	public final void method1811(@OriginalArg(0) Linkable2 arg0, @OriginalArg(1) long arg1) {
		if (this.anInt2314 == 0) {
			@Pc(14) Linkable2 local14 = this.aClass16_1.search();
			local14.unlink();
			local14.unlink2();
			if (this.aClass3_Sub2_37 == local14) {
				local14 = this.aClass16_1.search();
				local14.unlink();
				local14.unlink2();
			}
		} else {
			this.anInt2314--;
		}
		this.aClass133_5.put(arg0, arg1);
		this.aClass16_1.pushFront(arg0);
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(I)Lclient!ab;")
	public final Linkable method1813() {
		return this.aClass133_5.findnext();
	}

	@OriginalMember(owner = "client!gn", name = "c", descriptor = "(I)V")
	public final void method1815() {
		this.aClass16_1.method802();
		this.aClass133_5.method3856();
		this.aClass3_Sub2_37 = new Linkable2();
		this.anInt2314 = this.anInt2313;
	}
}
