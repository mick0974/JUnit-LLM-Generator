// HSLColor_brightenTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public brighten(float fPercent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_brightenTest {
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
	public void testBrighten_01() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_02() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_03() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_04() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_05() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_06() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_07() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_08() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_09() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}

	@Test
	public void testBrighten_10() {
		// Arrange
		hsl.initHSLbyRGB(255, 255, 255);
		float fPercent = 0.5f;

		// Act
		hsl.brighten(fPercent);

		// Assert
		assertEquals(127, hsl.getLuminence());
	}
}