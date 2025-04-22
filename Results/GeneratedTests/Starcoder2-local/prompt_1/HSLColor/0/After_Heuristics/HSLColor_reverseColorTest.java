// HSLColor_reverseColorTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public reverseColor() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_reverseColorTest {
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
	public void test_reverseColor_01() {
		hsl.initHSLbyRGB(255, 0, 0);
		hsl.reverseColor();
		assertEquals(255, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_02() {
		hsl.initHSLbyRGB(255, 255, 255);
		hsl.reverseColor();
		assertEquals(255, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_03() {
		hsl.initHSLbyRGB(0, 0, 0);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_04() {
		hsl.initHSLbyRGB(0, 255, 255);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_05() {
		hsl.initHSLbyRGB(0, 255, 0);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_06() {
		hsl.initHSLbyRGB(0, 0, 255);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_07() {
		hsl.initHSLbyRGB(255, 0, 255);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_08() {
		hsl.initHSLbyRGB(255, 255, 0);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_09() {
		hsl.initHSLbyRGB(255, 0, 0);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_reverseColor_10() {
		hsl.initHSLbyRGB(0, 0, 0);
		hsl.reverseColor();
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}
}