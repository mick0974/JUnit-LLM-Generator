// HSLColor_initRGBbyHSLTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public initRGBbyHSL(int H, int S, int L) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_initRGBbyHSLTest {
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
	public void test_initRGBbyHSL_01() {
		//Arrange
		int H = 0;
		int S = 0;
		int L = 0;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_02() {
		//Arrange
		int H = 0;
		int S = 0;
		int L = 255;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(0, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_03() {
		//Arrange
		int H = 0;
		int S = 255;
		int L = 0;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(0, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_04() {
		//Arrange
		int H = 0;
		int S = 255;
		int L = 255;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(0, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_05() {
		//Arrange
		int H = 128;
		int S = 0;
		int L = 0;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(128, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_06() {
		//Arrange
		int H = 128;
		int S = 0;
		int L = 255;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(128, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_07() {
		//Arrange
		int H = 128;
		int S = 255;
		int L = 0;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(128, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_08() {
		//Arrange
		int H = 128;
		int S = 255;
		int L = 255;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(128, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_09() {
		//Arrange
		int H = 255;
		int S = 0;
		int L = 0;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(255, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_10() {
		//Arrange
		int H = 255;
		int S = 0;
		int L = 255;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(255, hsl.getHue());
		assertEquals(0, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_11() {
		//Arrange
		int H = 255;
		int S = 255;
		int L = 0;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(255, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(0, hsl.getLuminence());
		assertEquals(0, hsl.getRed());
		assertEquals(0, hsl.getGreen());
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_initRGBbyHSL_12() {
		//Arrange
		int H = 255;
		int S = 255;
		int L = 255;

		//Act
		hsl.initRGBbyHSL(H, S, L);

		//Assert
		assertEquals(255, hsl.getHue());
		assertEquals(255, hsl.getSaturation());
		assertEquals(255, hsl.getLuminence());
		assertEquals(255, hsl.getRed());
		assertEquals(255, hsl.getGreen());
		assertEquals(255, hsl.getBlue());
	}
}