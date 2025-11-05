package jagex3.sound;

import jagex3.datastruct.Linkable;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ik")
public abstract class Sound extends Linkable {

	@OriginalMember(owner = "client!ik", name = "p", descriptor = "I")
	public int anInt3313;

	@OriginalMember(owner = "client!ik", name = "<init>", descriptor = "()V")
	protected Sound() {
	}
}
