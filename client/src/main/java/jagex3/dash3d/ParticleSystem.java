package jagex3.dash3d;

import jagex3.datastruct.DoublyHashTable;
import jagex3.io.Packet;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ga")
public final class ParticleSystem extends ParticleNode {

	static {
		new DoublyHashTable(8);
		new Packet(131056);
	}

	@OriginalMember(owner = "client!ga", name = "d", descriptor = "()V")
	public final void method1646() {
	}
}
