

public class HSLColorTest {
	private HSLColor pHSLColor;

	@Before
	public void setUp() {
		pHSLColor = new HSLColor();
	}

	@Test
	public void testInitHSLbyRGB() {
		pHSLColor.initHSLbyRGB(255, 0, 0);
		assertEquals(0, pHSLColor.getHue());
		assertEquals(255, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(255, pHSLColor.getRed());
		assertEquals(0, pHSLColor.getGreen());
		assertEquals(0, pHSLColor.getBlue());

		pHSLColor.initHSLbyRGB(0, 255, 0);
		assertEquals(120, pHSLColor.getHue());
		assertEquals(255, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(255, pHSLColor.getGreen());
		assertEquals(0, pHSLColor.getBlue());

		pHSLColor.initHSLbyRGB(0, 0, 255);
		assertEquals(240, pHSLColor.getHue());
		assertEquals(255, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(0, pHSLColor.getGreen());
		assertEquals(255, pHSLColor.getBlue());

		pHSLColor.initHSLbyRGB(255, 255, 255);
		assertEquals(UNDEFINED, pHSLColor.getHue());
		assertEquals(0, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(255, pHSLColor.getRed());
		assertEquals(255, pHSLColor.getGreen());
		assertEquals(255, pHSLColor.getBlue());

		pHSLColor.initHSLbyRGB(0, 0, 0);
		assertEquals(UNDEFINED, pHSLColor.getHue());
		assertEquals(0, pHSLColor.getSaturation());
		assertEquals(0, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(0, pHSLColor.getGreen());
		assertEquals(0, pHSLColor.getBlue());
	}

	@Test
	public void testInitRGBbyHSL() {
		pHSLColor.initRGBbyHSL(0, 255, 255);
		assertEquals(0, pHSLColor.getHue());
		assertEquals(0, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(255, pHSLColor.getRed());
		assertEquals(255, pHSLColor.getGreen());
		assertEquals(255, pHSLColor.getBlue());

		pHSLColor.initRGBbyHSL(120, 255, 255);
		assertEquals(120, pHSLColor.getHue());
		assertEquals(255, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(255, pHSLColor.getGreen());
		assertEquals(0, pHSLColor.getBlue());

		pHSLColor.initRGBbyHSL(240, 255, 255);
		assertEquals(240, pHSLColor.getHue());
		assertEquals(255, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(0, pHSLColor.getGreen());
		assertEquals(255, pHSLColor.getBlue());

		pHSLColor.initRGBbyHSL(UNDEFINED, 0, 255);
		assertEquals(UNDEFINED, pHSLColor.getHue());
		assertEquals(0, pHSLColor.getSaturation());
		assertEquals(255, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(0, pHSLColor.getGreen());
		assertEquals(255, pHSLColor.getBlue());

		pHSLColor.initRGBbyHSL(UNDEFINED, 0, 0);
		assertEquals(UNDEFINED, pHSLColor.getHue());
		assertEquals(0, pHSLColor.getSaturation());
		assertEquals(0, pHSLColor.getLuminence());
		assertEquals(0, pHSLColor.getRed());
		assertEquals(0, pHSLColor.getGreen());
		assertEquals(0, pHSLColor.getBlue());
	}

	@Test
	public void testHue() {
		pHSLColor.setHue(0);
		assertEquals(0, pHSLColor.getHue());

		pHSLColor.setHue(120);
		assertEquals(120, pHSLColor.getHue());

		pHSLColor.setHue(240);
		assertEquals(240, pHSLColor.getHue());

		pHSLColor.setHue(UNDEFINED);
		assertEquals(UNDEFINED, pHSLColor.getHue());
	}

	@Test
	public void testSaturation() {
		pHSLColor.setSaturation(0);
		assertEquals(0, pHSLColor.getSaturation());

		pHSLColor.setSaturation(255);
		assertEquals(255, pHSLColor.getSaturation());

		pHSLColor.setSaturation(120);
		assertEquals(120, pHSLColor.getSaturation());
	}

	@Test
	public void testLuminence() {
		pHSLColor.setLuminence(0);
		assertEquals(0, pHSLColor.getLuminence());

		pHSLColor.setLuminence(255);
		assertEquals(255, pHSLColor.getLuminence());

		pHSLColor.setLuminence(120);
		assertEquals(120, pHSLColor.getLuminence());
	}

	@Test
	public void testRed() {
		pHSLColor.setRed(0);
		assertEquals(0, pHSLColor.getRed());

		pHSLColor.setRed(255);
		assertEquals(255, pHSLColor.getRed());

		pHSLColor.setRed(120);
		assertEquals(120, pHSLColor.getRed());
	}

	@Test
	public void testGreen() {
		pHSLColor.setGreen(0);
		assertEquals(0, pHSLColor.getGreen());

		pHSLColor.setGreen(255);
		assertEquals(255, pHSLColor.getGreen());

		pHSLColor.setGreen(120);
		assertEquals(120, pHSLColor.getGreen());
	}

	@Test
	public void testBlue() {
		pHSLColor.setBlue(0);
		assertEquals(0, pHSLColor.getBlue());

		pHSLColor.setBlue(255);
		assertEquals(255, pHSLColor.getBlue());

		pHSLColor.setBlue(120);
		assertEquals(120, pHSLColor.getBlue());
	}

	@Test
	public void testReverseColor() {
		pHSLColor.setHue(0);
		pHSLColor.reverseColor();
		assertEquals(120, pHSLColor.getHue());

		pHSLColor.setHue(120);
		pHSLColor.reverseColor();
		assertEquals(0, pHSLColor.getHue());

		pHSLColor.setHue(240);
		pHSLColor.reverseColor();
		assertEquals(300, pHSLColor.getHue());

}
}