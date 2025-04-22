// HSLColor_getBlueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getBlue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getBlueTest {
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
	public void test_getBlue_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_02() {
		hsl.initHSLbyRGB(0, 0, 255);
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_getBlue_03() {
		hsl.initHSLbyRGB(0, 255, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_04() {
		hsl.initHSLbyRGB(0, 255, 255);
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_getBlue_05() {
		hsl.initHSLbyRGB(255, 0, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_06() {
		hsl.initHSLbyRGB(255, 0, 255);
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_getBlue_07() {
		hsl.initHSLbyRGB(255, 255, 0);
		assertEquals(0, hsl.getBlue());
	}

	@Test
	public void test_getBlue_08() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(255, hsl.getBlue());
	}

	@Test
	public void test_getBlue_09() {
		hsl.initHSLbyRGB(128, 128, 128);
		assertEquals(128, hsl.getBlue());
	}

	@Test
	public void test_getBlue_10() {
		hsl.initHSLbyRGB(128, 128, 255);
		assertEquals(128, hsl.getBlue());
	}
}