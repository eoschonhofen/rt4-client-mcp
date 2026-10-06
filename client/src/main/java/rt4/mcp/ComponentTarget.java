package rt4.mcp;

/**
 * MCP-05 — {@code if:<interfaceId>:<childId>} for a component, or
 * {@code if:<interfaceId>:<childId>:<slot>} for a slot inside an inventory-type component.
 * A slot of {@code -1} means the target is the component itself.
 */
public final class ComponentTarget extends Target {
	public final int interfaceId;
	public final int childId;
	public final int slot;

	public ComponentTarget(int interfaceId, int childId, int slot) {
		this.interfaceId = interfaceId;
		this.childId = childId;
		this.slot = slot;
	}

	public static ComponentTarget component(int interfaceId, int childId) {
		return new ComponentTarget(interfaceId, childId, -1);
	}

	public boolean isSlot() {
		return slot >= 0;
	}

	@Override
	public String kind() {
		return "if";
	}

	@Override
	public String format() {
		return slot >= 0 ? Targets.slot(interfaceId, childId, slot) : Targets.component(interfaceId, childId);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ComponentTarget)) {
			return false;
		}
		ComponentTarget that = (ComponentTarget) other;
		return interfaceId == that.interfaceId && childId == that.childId && slot == that.slot;
	}

	@Override
	public int hashCode() {
		int result = interfaceId;
		result = 31 * result + childId;
		result = 31 * result + slot;
		return result;
	}
}
