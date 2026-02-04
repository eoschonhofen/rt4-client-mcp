import javax.media.opengl.GL;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static156 {

	@OriginalMember(owner = "client!mf", name = "X", descriptor = "I")
	public static int anInt3783;

	@OriginalMember(owner = "client!mf", name = "x", descriptor = "Lclient!ha;")
	public static final GZip gzip = new GZip();

	@OriginalMember(owner = "client!mf", name = "a", descriptor = "()V")
	public static void method2959() {
		@Pc(1) GL local1 = Static239.gl;
		local1.glDisableClientState(GL.GL_COLOR_ARRAY);
		Static239.setLightingEnabled(false);
		local1.glDisable(GL.GL_DEPTH_TEST);
		local1.glPushAttrib(GL.GL_FOG_BIT);
		local1.glFogf(GL.GL_FOG_START, 3072.0F);
		Static239.method4178();
		for (@Pc(19) int local19 = 0; local19 < World.glTiles[0].length; local19++) {
			@Pc(31) GlSquare local31 = World.glTiles[0][local19];
			if (local31.anInt2485 >= 0 && Pix3D.anInterface1_2.method3237(local31.anInt2485) == 4) {
				local1.glColor4fv(Static190.method3441(local31.anInt2486), 0);
				@Pc(57) float local57 = 201.5F - (local31.aBoolean140 ? 1.0F : 0.5F);
				local31.method1944(World.activeTiles, local57, true);
			}
		}
		local1.glEnableClientState(GL.GL_COLOR_ARRAY);
		Static239.method4173();
		local1.glEnable(GL.GL_DEPTH_TEST);
		local1.glPopAttrib();
		Static239.method4157();
	}

}
