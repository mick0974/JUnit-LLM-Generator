

import org.junit.Test;
import static org.junit.Assert.*;

public class HSLColorTest {
	@Test
	public void testInitHSLbyRGB() {
		HSLColor hsl = new HSLColor();
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());

		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(120, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(0, hsl.getBlue());

		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(240, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(255, hsl.getBlue());

		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(UNDEFINED, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void testInitRGBbyHSL() {
		HSLColor hsl = new HSLColor();
		hsl.initRGBbyHSL(0, 255, 255);
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());

		hsl.initRGBbyHSL(120, 255, 255);
		assertEquals(120, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(0, hsl.getBlue());

		hsl.initRGBbyHSL(240, 255, 255);
		assertEquals(240, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(255, hsl.getBlue());

		hsl.initRGBbyHSL(UNDEFINED, 0, 255);
		assertEquals(UNDEFINED, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void testHue() {
		HSLColor hsl = new HSLColor();
		hsl.setHue(120);
		assertEquals(120, hsl.getHue());
		hsl.setHue(240);
		assertEquals(240, hsl.getHue());
		hsl.setHue(0);
		assertEquals(0, hsl.getHue());
		hsl.setHue(360);
		assertEquals(0, hsl.getHue());
		hsl.setHue(361);
		assertEquals(1, hsl.getHue());
		hsl.setHue(-1);
		assertEquals(359, hsl.getHue());
	}

	@Test
	public void testSaturation() {
		HSLColor hsl = new HSLColor();
		hsl.setSaturation(120);
		assertEquals(120, hsl.getSaturation());
		hsl.setSaturation(240);
		assertEquals(240, hsl.getSaturation());
		hsl.setSaturation(0);
		assertEquals(0, hsl.getSaturation());
		hsl.setSaturation(360);
		assertEquals(0, hsl.getSaturation());
		hsl.setSaturation(361);
		assertEquals(1, hsl.getSaturation());
		hsl.setSaturation(-1);
		assertEquals(359, hsl.getSaturation());
	}

	@Test
	public void testLuminence() {
		HSLColor hsl = new HSLColor();
		hsl.setLuminence(120);
		assertEquals(120, hsl.getLuminence());
		hsl.setLuminence(240);
		assertEquals(240, hsl.getLuminence());
		hsl.setLuminence(0);
		assertEquals(0, hsl.getLuminence());
		hsl.setLuminence(360);
		assertEquals(0, hsl.getLuminence());
		hsl.setLuminence(361);
		assertEquals(1, hsl.getLuminence());
		hsl.setLuminence(-1);
		assertEquals(359, hsl.getLuminence());
	}

	@Test
	public void testRed() {
		HSLColor hsl = new HSLColor();
		hsl.setRed(120);
		assertEquals(120, hsl.getRed());
		hsl.setRed(240);
		assertEquals(240, hsl.getRed());
		hsl.setRed(0);
		assertEquals(0, hsl.getRed());
		hsl.setRed(360);
		assertEquals(0, hsl.getRed());
		hsl.setRed(361);
		assertEquals(1, hsl.getRed());
		hsl.setRed(-1);
		assertEquals(359, hsl.getRed());
	}

	@Test
	public void testGreen() {
		HSLColor hsl = new HSLColor();
		hsl.setGreen(120);
		assertEquals(120, hsl.getGreen());
		hsl.setGreen(240);
		assertEquals(240, hsl.getGreen());
		hsl.setGreen(0);
		assertEquals(0, hsl.getGreen());
		hsl.setGreen(360);
		assertEquals(0, hsl.getGreen());
		hsl.setGreen(361);
		assertEquals(1, hsl.getGreen());
		hsl.setGreen(-1);
		assertEquals(359, hsl.getGreen());
	}

	@Test
	public void testBlue() {
		HSLColor hsl = new HSLColor();
		hsl.setBlue(120);
		assertEquals(120, hsl.getBlue());
		hsl.setBlue(240);
		assertEquals(240, hsl.getBlue());
		hsl.setBlue(0);
		assertEquals(0, hsl.getBlue());
		hsl.setBlue(360);
		assertEquals(0, hsl.getBlue());
		hsl.setBlue(361);
		assertEquals(1, hsl.getBlue());
		hsl.setBlue(-1);
		assertEquals(359, hsl.getBlue());
	}

	@Test
	public void testReverseColor() {
}
}