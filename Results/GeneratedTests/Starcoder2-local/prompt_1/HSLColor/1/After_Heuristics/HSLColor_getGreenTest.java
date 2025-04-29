// HSLColor_getGreenTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getGreen() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getGreenTest {
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
	public void test_getGreen_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_02() {
		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(255, hsl.getGreen());
	}

	@Test
	public void test_getGreen_03() {
		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(255, hsl.getGreen());
	}

	@Test
	public void test_getGreen_04() {
		hsl.initHSLbyRGB(0, 255, 255);
		assertEquals(255, hsl.getGreen());
	}

	@Test
	public void test_getGreen_05() {
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_06() {
		hsl.initHSLbyRGB(255, 0, 255);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_07() {
		hsl.initHSLbyRGB(255, 255, 0);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_08() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(0, hsl.getGreen());
	}

	@Test
	public void test_getGreen_09() {
		hsl.initHSLbyRGB(128, 128, 128);
		assertEquals(128, hsl.getGreen());
	}

	@Test
	public void test_getGreen_10() {
		hsl.initHSLbyRGB(128, 128, 255);
		assertEquals(128, hsl.getGreen());
	}
}