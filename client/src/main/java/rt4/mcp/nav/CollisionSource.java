package rt4.mcp.nav;

/** MCP-13 — read-only collision flags, so A* can be tested against a fixture grid. */
public interface CollisionSource {
	int flags(int x, int y);

	int width();

	int height();
}
