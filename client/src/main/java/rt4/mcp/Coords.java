package rt4.mcp;

import rt4.Camera;

/**
 * MCP-05 — scene ↔ world coordinate conversion.
 *
 * <p>The client scene is 104×104 tiles. {@code world = scene + Camera.origin}, which is the
 * same arithmetic {@code MiniMenu.doAction} uses when it builds loc and obj packets
 * ({@code Camera.originX + menuArg1}). {@code Camera.originX/Y} is set in
 * {@code LoginManager} to {@code (centralZone - 6) * 8}, matching
 * {@code SceneGraph.centralZoneX * 8 - 48}.</p>
 */
public final class Coords {
	public static final int SCENE_SIZE = 104;
	public static final int PLANES = 4;

	private Coords() {
	}

	/** Pure: {@code world - origin}. */
	public static int toScene(int world, int origin) {
		return world - origin;
	}

	/** Pure: {@code scene + origin}. */
	public static int toWorld(int scene, int origin) {
		return scene + origin;
	}

	public static boolean inScene(int sceneX, int sceneY) {
		return sceneX >= 0 && sceneX < SCENE_SIZE && sceneY >= 0 && sceneY < SCENE_SIZE;
	}

	public static boolean validPlane(int plane) {
		return plane >= 0 && plane < PLANES;
	}

	public static int sceneX(int worldX) {
		return worldX - Camera.originX;
	}

	public static int sceneY(int worldY) {
		return worldY - Camera.originY;
	}

	public static int worldX(int sceneX) {
		return Camera.originX + sceneX;
	}

	public static int worldY(int sceneY) {
		return Camera.originY + sceneY;
	}
}
