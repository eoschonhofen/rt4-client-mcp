import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!jl")
public final class HookReq extends Linkable {

	@OriginalMember(owner = "client!jl", name = "q", descriptor = "Lclient!be;")
	public IfType drop;

	@OriginalMember(owner = "client!jl", name = "r", descriptor = "Lclient!na;")
	public JagString opbase;

	@OriginalMember(owner = "client!jl", name = "w", descriptor = "I")
	public int mouseY;

	@OriginalMember(owner = "client!jl", name = "z", descriptor = "I")
	public int keyChar;

	@OriginalMember(owner = "client!jl", name = "A", descriptor = "I")
	public int keyCode;

	@OriginalMember(owner = "client!jl", name = "B", descriptor = "I")
	public int opindex;

	@OriginalMember(owner = "client!jl", name = "C", descriptor = "Z")
	public boolean aBoolean158;

	@OriginalMember(owner = "client!jl", name = "D", descriptor = "I")
	public int mouseX;

	@OriginalMember(owner = "client!jl", name = "E", descriptor = "[Ljava/lang/Object;")
	public Object[] onop;

	@OriginalMember(owner = "client!jl", name = "F", descriptor = "Lclient!be;")
	public IfType component;
}
