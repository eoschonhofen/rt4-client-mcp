package rt4.mcp.nav;

/**
 * MCP-12/MCP-13 — placeholder for the navigation driver so {@code wait_for(nav_done)} can be
 * declared before MCP-13 lands. MCP-13 replaces this body with the real driver.
 */
public final class NavTask {
	private NavTask() {
	}

	/** Whether the task (or any task when {@code task < 0}) has finished, successfully or not. */
	public static boolean isFinished(int task) {
		return false;
	}
}
