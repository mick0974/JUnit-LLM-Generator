// HSLColor_setLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setLuminence(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setLuminenceTest {
	private HSLColor pHSLColor;

	@Before
	public void setUp() {
		pHSLColor = new HSLColor();
	}

	@After
	public void tearDown() {
		pHSLColor = null;
	}

	@Test
	public void testSetLuminence_01() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(0);
		assertEquals(0, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_02() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(255);
		assertEquals(255, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_03() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(128);
		assertEquals(128, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_04() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(256);
		assertEquals(255, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_05() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(-1);
		assertEquals(0, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_06() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(-256);
		assertEquals(0, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_07() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(1000);
		assertEquals(255, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_08() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(-1000);
		assertEquals(0, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_09() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(1000000);
		assertEquals(255, pHSLColor.getLuminence());
	}

	@Test
	public void testSetLuminence_10() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setLuminence(-1000000);
		assertEquals(0, pHSLColor.getLuminence());
	}
}