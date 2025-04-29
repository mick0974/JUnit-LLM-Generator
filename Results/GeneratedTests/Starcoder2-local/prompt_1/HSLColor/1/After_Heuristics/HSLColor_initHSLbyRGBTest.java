// HSLColor_initHSLbyRGBTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public initHSLbyRGB(int R, int G, int B) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_initHSLbyRGBTest {
	private HSLColor hsl;

	@Before
	public void setUp() {
		hsl = new HSLColor();
	}

	@After
	public void tearDown() {
		hsl = null;
	}

	@Test
	public void test_initHSLbyRGB_01() {
		// Arrange
		int R = 0;
		int G = 0;
		int B = 0;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 0);
		assertEquals(hsl.getSaturation(), 0);
		assertEquals(hsl.getLuminence(), 0);
		assertEquals(hsl.getRed(), 0);
		assertEquals(hsl.getGreen(), 0);
		assertEquals(hsl.getBlue(), 0);
	}

	@Test
	public void test_initHSLbyRGB_02() {
		// Arrange
		int R = 0;
		int G = 0;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 0);
		assertEquals(hsl.getSaturation(), 0);
		assertEquals(hsl.getLuminence(), 255);
		assertEquals(hsl.getRed(), 0);
		assertEquals(hsl.getGreen(), 0);
		assertEquals(hsl.getBlue(), 255);
	}

	@Test
	public void test_initHSLbyRGB_03() {
		// Arrange
		int R = 255;
		int G = 0;
		int B = 0;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 120);
		assertEquals(hsl.getSaturation(), 255);
		assertEquals(hsl.getLuminence(), 127);
		assertEquals(hsl.getRed(), 255);
		assertEquals(hsl.getGreen(), 0);
		assertEquals(hsl.getBlue(), 0);
	}

	@Test
	public void test_initHSLbyRGB_04() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 0;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 60);
		assertEquals(hsl.getSaturation(), 255);
		assertEquals(hsl.getLuminence(), 127);
		assertEquals(hsl.getRed(), 255);
		assertEquals(hsl.getGreen(), 255);
		assertEquals(hsl.getBlue(), 0);
	}

	@Test
	public void test_initHSLbyRGB_05() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 0);
		assertEquals(hsl.getSaturation(), 0);
		assertEquals(hsl.getLuminence(), 255);
		assertEquals(hsl.getRed(), 255);
		assertEquals(hsl.getGreen(), 255);
		assertEquals(hsl.getBlue(), 255);
	}

	@Test
	public void test_initHSLbyRGB_06() {
		// Arrange
		int R = 0;
		int G = 255;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 120);
		assertEquals(hsl.getSaturation(), 255);
		assertEquals(hsl.getLuminence(), 127);
		assertEquals(hsl.getRed(), 0);
		assertEquals(hsl.getGreen(), 255);
		assertEquals(hsl.getBlue(), 255);
	}

	@Test
	public void test_initHSLbyRGB_07() {
		// Arrange
		int R = 0;
		int G = 0;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 240);
		assertEquals(hsl.getSaturation(), 255);
		assertEquals(hsl.getLuminence(), 127);
		assertEquals(hsl.getRed(), 0);
		assertEquals(hsl.getGreen(), 0);
		assertEquals(hsl.getBlue(), 255);
	}

	@Test
	public void test_initHSLbyRGB_08() {
		// Arrange
		int R = 255;
		int G = 0;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 300);
		assertEquals(hsl.getSaturation(), 255);
		assertEquals(hsl.getLuminence(), 127);
		assertEquals(hsl.getRed(), 255);
		assertEquals(hsl.getGreen(), 0);
		assertEquals(hsl.getBlue(), 255);
	}

	@Test
	public void test_initHSLbyRGB_09() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 0);
		assertEquals(hsl.getSaturation(), 0);
		assertEquals(hsl.getLuminence(), 255);
		assertEquals(hsl.getRed(), 255);
		assertEquals(hsl.getGreen(), 255);
		assertEquals(hsl.getBlue(), 255);
	}

	@Test
	public void test_initHSLbyRGB_10() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;

		// Act
		hsl.initHSLbyRGB(R, G, B);

		// Assert
		assertEquals(hsl.getHue(), 0);
		assertEquals(hsl.getSaturation(), 0);
		assertEquals(hsl.getLuminence(), 255);
		assertEquals(hsl.getRed(), 255);
		assertEquals(hsl.getGreen(), 255);
		assertEquals(hsl.getBlue(), 255);
	}
}