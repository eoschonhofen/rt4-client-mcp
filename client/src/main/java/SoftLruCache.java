import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!n")
public final class SoftLruCache {

	@OriginalMember(owner = "client!n", name = "k", descriptor = "Lclient!ce;")
	private final LinkList2 aClass16_8 = new LinkList2();

	@OriginalMember(owner = "client!n", name = "m", descriptor = "I")
	private final int anInt3970;

	@OriginalMember(owner = "client!n", name = "g", descriptor = "I")
	private int anInt3966;

	@OriginalMember(owner = "client!n", name = "n", descriptor = "Lclient!sc;")
	private final HashTable aClass133_18;

	@OriginalMember(owner = "client!n", name = "<init>", descriptor = "(I)V")
	public SoftLruCache(@OriginalArg(0) int arg0) {
		this.anInt3970 = arg0;
		@Pc(11) int local11;
		for (local11 = 1; local11 + local11 < arg0; local11 += local11) {
		}
		this.anInt3966 = arg0;
		this.aClass133_18 = new HashTable(local11);
	}

	@OriginalMember(owner = "client!n", name = "a", descriptor = "(BLjava/lang/Object;J)V")
	public final void put(@OriginalArg(1) Object arg0, @OriginalArg(2) long arg1) {
		this.method3098(arg1);
		if (this.anInt3966 == 0) {
			@Pc(26) ReferenceNode local26 = (ReferenceNode) this.aClass16_8.search();
			local26.unlink();
			local26.unlink2();
		} else {
			this.anInt3966--;
		}
		@Pc(44) HardReferenceNode local44 = new HardReferenceNode(arg0);
		this.aClass133_18.put(local44, arg1);
		this.aClass16_8.pushFront(local44);
		local44.key2 = 0L;
	}

	@OriginalMember(owner = "client!n", name = "a", descriptor = "(JB)V")
	public final void method3098(@OriginalArg(0) long arg0) {
		@Pc(6) ReferenceNode local6 = (ReferenceNode) this.aClass133_18.find(arg0);
		if (local6 != null) {
			local6.unlink();
			local6.unlink2();
			this.anInt3966++;
		}
	}

	@OriginalMember(owner = "client!n", name = "a", descriptor = "(I)I")
	public final int size() {
		@Pc(10) int local10 = 0;
		for (@Pc(16) ReferenceNode local16 = (ReferenceNode) this.aClass16_8.method795(); local16 != null; local16 = (ReferenceNode) this.aClass16_8.method797()) {
			if (!local16.method3619()) {
				local10++;
			}
		}
		return local10;
	}

	@OriginalMember(owner = "client!n", name = "c", descriptor = "(II)V")
	public final void method3102(@OriginalArg(1) int arg0) {
		if (Static252.aClass22_1 == null) {
			return;
		}
		for (@Pc(9) ReferenceNode local9 = (ReferenceNode) this.aClass16_8.method795(); local9 != null; local9 = (ReferenceNode) this.aClass16_8.method797()) {
			if (local9.method3619()) {
				if (local9.get() == null) {
					local9.unlink();
					local9.unlink2();
					this.anInt3966++;
				}
			} else if (++local9.key2 > (long) arg0) {
				@Pc(33) ReferenceNode local33 = Static252.aClass22_1.create(local9);
				this.aClass133_18.put(local33, local9.key);
				Static84.method1772(local9, local33);
				local9.unlink();
				local9.unlink2();
			}
		}
	}

	@OriginalMember(owner = "client!n", name = "b", descriptor = "(B)V")
	public final void clear() {
		for (@Pc(7) ReferenceNode local7 = (ReferenceNode) this.aClass16_8.method795(); local7 != null; local7 = (ReferenceNode) this.aClass16_8.method797()) {
			if (local7.method3619()) {
				local7.unlink();
				local7.unlink2();
				this.anInt3966++;
			}
		}
	}

	@OriginalMember(owner = "client!n", name = "c", descriptor = "(I)V")
	public final void method3104() {
		this.aClass16_8.method802();
		this.aClass133_18.method3856();
		this.anInt3966 = this.anInt3970;
	}

	@OriginalMember(owner = "client!n", name = "b", descriptor = "(JB)Ljava/lang/Object;")
	public final Object find(@OriginalArg(0) long arg0) {
		@Pc(12) ReferenceNode local12 = (ReferenceNode) this.aClass133_18.find(arg0);
		if (local12 == null) {
			return null;
		}
		@Pc(27) Object local27 = local12.get();
		if (local27 == null) {
			local12.unlink();
			local12.unlink2();
			this.anInt3966++;
			return null;
		}
		if (local12.method3619()) {
			@Pc(53) HardReferenceNode local53 = new HardReferenceNode(local27);
			this.aClass133_18.put(local53, local12.key);
			this.aClass16_8.pushFront(local53);
			local53.key2 = 0L;
			local12.unlink();
			local12.unlink2();
		} else {
			this.aClass16_8.pushFront(local12);
			local12.key2 = 0L;
		}
		return local27;
	}
}
