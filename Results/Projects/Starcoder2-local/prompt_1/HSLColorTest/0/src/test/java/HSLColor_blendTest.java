// HSLColor_blendTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public blend(int R, int G, int B, float fPercent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_blendTest {
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
	public void test_blend_01() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_02() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_03() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_04() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_05() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_06() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_07() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_08() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_09() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}

	@Test
	public void test_blend_10() {
		// Arrange
		int R = 255;
		int G = 255;
		int B = 255;
		float fPercent = 0.5f;

		// Act
		hsl.blend(R, G, B, fPercent);

		// Assert
		assertEquals(127, hsl.getRed());
		assertEquals(127, hsl.getGreen());
		assertEquals(127, hsl.getBlue());
	}
}