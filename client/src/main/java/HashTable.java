import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!sc")
public final class HashTable {

	@OriginalMember(owner = "client!sc", name = "q", descriptor = "Lclient!ab;")
	private Linkable aClass3_192;

	@OriginalMember(owner = "client!sc", name = "u", descriptor = "J")
	private long aLong168;

	@OriginalMember(owner = "client!sc", name = "C", descriptor = "Lclient!ab;")
	private Linkable aClass3_193;

	@OriginalMember(owner = "client!sc", name = "F", descriptor = "I")
	private int anInt5037 = 0;

	@OriginalMember(owner = "client!sc", name = "c", descriptor = "[Lclient!ab;")
	public final Linkable[] buckets;

	@OriginalMember(owner = "client!sc", name = "h", descriptor = "I")
	public final int anInt5023;

	@OriginalMember(owner = "client!sc", name = "<init>", descriptor = "(I)V")
	public HashTable(@OriginalArg(0) int arg0) {
		this.buckets = new Linkable[arg0];
		this.anInt5023 = arg0;
		for (@Pc(13) int local13 = 0; local13 < arg0; local13++) {
			@Pc(25) Linkable local25 = this.buckets[local13] = new Linkable();
			local25.prev = local25;
			local25.next = local25;
		}
	}

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "(I)V")
	public final void method3856() {
		for (@Pc(5) int local5 = 0; local5 < this.anInt5023; local5++) {
			@Pc(14) Linkable local14 = this.buckets[local5];
			while (true) {
				@Pc(17) Linkable local17 = local14.next;
				if (local14 == local17) {
					break;
				}
				local17.unlink();
			}
		}
		this.aClass3_193 = null;
		this.aClass3_192 = null;
	}

	@OriginalMember(owner = "client!sc", name = "c", descriptor = "(I)Lclient!ab;")
	public final Linkable search() {
		this.anInt5037 = 0;
		return this.findnext();
	}

	@OriginalMember(owner = "client!sc", name = "d", descriptor = "(I)Lclient!ab;")
	public final Linkable findnext() {
		@Pc(24) Linkable local24;
		if (this.anInt5037 > 0 && this.aClass3_193 != this.buckets[this.anInt5037 - 1]) {
			local24 = this.aClass3_193;
			this.aClass3_193 = local24.next;
			return local24;
		}
		do {
			if (this.anInt5037 >= this.anInt5023) {
				return null;
			}
			local24 = this.buckets[this.anInt5037++].next;
		} while (this.buckets[this.anInt5037 - 1] == local24);
		this.aClass3_193 = local24.next;
		return local24;
	}

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "(ILclient!ab;J)V")
	public final void put(@OriginalArg(1) Linkable arg0, @OriginalArg(2) long arg1) {
		if (arg0.prev != null) {
			arg0.unlink();
		}
		@Pc(21) Linkable local21 = this.buckets[(int) (arg1 & (long) (this.anInt5023 - 1))];
		arg0.next = local21;
		arg0.key = arg1;
		arg0.prev = local21.prev;
		arg0.prev.next = arg0;
		arg0.next.prev = arg0;
	}

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "(JI)Lclient!ab;")
	public final Linkable find(@OriginalArg(0) long arg0) {
		this.aLong168 = arg0;
		@Pc(24) Linkable local24 = this.buckets[(int) (arg0 & (long) (this.anInt5023 - 1))];
		for (this.aClass3_192 = local24.next; this.aClass3_192 != local24; this.aClass3_192 = this.aClass3_192.next) {
			if (arg0 == this.aClass3_192.key) {
				@Pc(46) Linkable local46 = this.aClass3_192;
				this.aClass3_192 = this.aClass3_192.next;
				return local46;
			}
		}
		this.aClass3_192 = null;
		return null;
	}

	@OriginalMember(owner = "client!sc", name = "e", descriptor = "(I)I")
	public final int method3864() {
		@Pc(15) int local15 = 0;
		for (@Pc(17) int local17 = 0; local17 < this.anInt5023; local17++) {
			@Pc(26) Linkable local26 = this.buckets[local17];
			@Pc(29) Linkable local29 = local26.next;
			while (local29 != local26) {
				local29 = local29.next;
				local15++;
			}
		}
		return local15;
	}

	@OriginalMember(owner = "client!sc", name = "a", descriptor = "([Lclient!ab;I)I")
	public final int method3865(@OriginalArg(0) Linkable[] arg0) {
		@Pc(13) int local13 = 0;
		for (@Pc(15) int local15 = 0; local15 < this.anInt5023; local15++) {
			@Pc(24) Linkable local24 = this.buckets[local15];
			for (@Pc(27) Linkable local27 = local24.next; local27 != local24; local27 = local27.next) {
				arg0[local13++] = local27;
			}
		}
		return local13;
	}

	@OriginalMember(owner = "client!sc", name = "f", descriptor = "(I)Lclient!ab;")
	public final Linkable method3867() {
		if (this.aClass3_192 == null) {
			return null;
		}
		@Pc(23) Linkable local23 = this.buckets[(int) (this.aLong168 & (long) (this.anInt5023 - 1))];
		while (local23 != this.aClass3_192) {
			if (this.aClass3_192.key == this.aLong168) {
				@Pc(45) Linkable local45 = this.aClass3_192;
				this.aClass3_192 = this.aClass3_192.next;
				return local45;
			}
			this.aClass3_192 = this.aClass3_192.next;
		}
		this.aClass3_192 = null;
		return null;
	}

	@OriginalMember(owner = "client!sc", name = "g", descriptor = "(I)I")
	public final int method3868() {
		return this.anInt5023;
	}
}
