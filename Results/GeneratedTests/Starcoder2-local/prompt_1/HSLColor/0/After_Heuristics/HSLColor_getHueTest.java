// HSLColor_getHueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getHue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getHueTest {
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
	public void test_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(170, hsl.getHue());
	}

	@Test
	public void test_02() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(0, hsl.getHue());
	}

	@Test
	public void test_03() {
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getHue());
	}

	@Test
	public void test_04() {
		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(120, hsl.getHue());
	}

	@Test
	public void test_05() {
		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(240, hsl.getHue());
	}

	@Test
	public void test_06() {
		hsl.initHSLbyRGB(255, 255, 0);
		assertEquals(60, hsl.getHue());
	}

	@Test
	public void test_07() {
		hsl.initHSLbyRGB(255, 0, 255);
		assertEquals(300, hsl.getHue());
	}

	@Test
	public void test_08() {
		hsl.initHSLbyRGB(0, 255, 255);
		assertEquals(180, hsl.getHue());
	}

	@Test
	public void test_09() {
		hsl.initHSLbyRGB(128, 128, 128);
		assertEquals(170, hsl.getHue());
	}

	@Test
	public void test_10() {
		hsl.initHSLbyRGB(128, 0, 128);
		assertEquals(300, hsl.getHue());
	}
}