package rt4.mcp;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScreenshotTest {
	private static int[] frame(int width, int height) {
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[y * width + x] = 0xFF000000 | y << 8 | x;
			}
		}
		return pixels;
	}

	@Test
	void flipVerticallySwapsRows() {
		int[] pixels = {1, 2, 3, 4, 5, 6};
		Screenshot.flipVertically(pixels, 3, 2);
		assertArrayEqualsInts(new int[]{4, 5, 6, 1, 2, 3}, pixels);
	}

	@Test
	void flipVerticallyLeavesTheMiddleRowAlone() {
		int[] pixels = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		Screenshot.flipVertically(pixels, 3, 3);
		assertArrayEqualsInts(new int[]{7, 8, 9, 4, 5, 6, 1, 2, 3}, pixels);
	}

	@Test
	void cropCopiesTheRequestedRegionAndDropsAlpha() {
		int[] pixels = frame(4, 3);
		BufferedImage image = Screenshot.crop(pixels, 4, 3, 1, 1, 2, 2);

		assertEquals(2, image.getWidth());
		assertEquals(2, image.getHeight());
		assertEquals(0x000101, image.getRGB(0, 0) & 0xFFFFFF);
		assertEquals(0x000102, image.getRGB(1, 0) & 0xFFFFFF);
		assertEquals(0x000201, image.getRGB(0, 1) & 0xFFFFFF);
		assertEquals(0x000202, image.getRGB(1, 1) & 0xFFFFFF);
	}

	@Test
	void cropClampsRegionsToTheFrame() {
		int[] pixels = frame(4, 3);

		BufferedImage beyond = Screenshot.crop(pixels, 4, 3, -5, -5, 100, 100);
		assertEquals(4, beyond.getWidth());
		assertEquals(3, beyond.getHeight());

		BufferedImage partial = Screenshot.crop(pixels, 4, 3, 3, 2, 10, 10);
		assertEquals(1, partial.getWidth());
		assertEquals(1, partial.getHeight());
	}

	@Test
	void cropNeverReturnsAnEmptyImage() {
		int[] pixels = frame(4, 3);
		BufferedImage image = Screenshot.crop(pixels, 4, 3, 50, 50, 0, 0);
		assertEquals(1, image.getWidth());
		assertEquals(1, image.getHeight());
	}

	@Test
	void toImageGivesTheWholeFrame() {
		BufferedImage image = Screenshot.toImage(new Screenshot.Frame(frame(5, 4), 5, 4));
		assertEquals(5, image.getWidth());
		assertEquals(4, image.getHeight());
	}

	@Test
	void scaleRoundsToWholePixels() {
		BufferedImage source = new BufferedImage(10, 8, BufferedImage.TYPE_INT_RGB);

		BufferedImage half = Screenshot.scale(source, 0.5D);
		assertEquals(5, half.getWidth());
		assertEquals(4, half.getHeight());

		BufferedImage full = Screenshot.scale(source, 1.0D);
		assertEquals(10, full.getWidth());

		BufferedImage quarter = Screenshot.scale(source, 0.25D);
		assertEquals(3, quarter.getWidth());
		assertEquals(2, quarter.getHeight());
	}

	@Test
	void scaleNeverGoesBelowOnePixel() {
		BufferedImage source = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
		BufferedImage scaled = Screenshot.scale(source, 0.25D);
		assertTrue(scaled.getWidth() >= 1);
		assertTrue(scaled.getHeight() >= 1);
	}

	@Test
	void scaleClampsToTheAllowedRange() {
		assertEquals(Screenshot.DEFAULT_SCALE, Screenshot.clampScale(0.0D));
		assertEquals(Screenshot.DEFAULT_SCALE, Screenshot.clampScale(-1.0D));
		assertEquals(Screenshot.DEFAULT_SCALE, Screenshot.clampScale(Double.NaN));
		assertEquals(Screenshot.MIN_SCALE, Screenshot.clampScale(0.01D));
		assertEquals(Screenshot.MAX_SCALE, Screenshot.clampScale(4.0D));
		assertEquals(0.75D, Screenshot.clampScale(0.75D));
	}

	@Test
	void pngEncodingProducesAPng() throws Exception {
		byte[] png = Screenshot.toPng(new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB));
		assertEquals((byte) 0x89, png[0]);
		assertEquals('P', png[1]);
		assertEquals('N', png[2]);
		assertEquals('G', png[3]);
		assertTrue(ToolResult.base64Length(png) > 0);
	}

	private static void assertArrayEqualsInts(int[] expected, int[] actual) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i], "index " + i);
		}
	}
}
