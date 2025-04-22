// HSLColor_setSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setSaturation(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setSaturationTest {
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
	public void testSetSaturation_01() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(0);
		assertEquals(0, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_02() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(255);
		assertEquals(255, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_03() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(128);
		assertEquals(128, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_04() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(127);
		assertEquals(127, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_05() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(126);
		assertEquals(126, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_06() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(125);
		assertEquals(125, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_07() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(124);
		assertEquals(124, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_08() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(123);
		assertEquals(123, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_09() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(122);
		assertEquals(122, pHSLColor.getSaturation());
	}

	@Test
	public void testSetSaturation_10() {
		pHSLColor.initHSLbyRGB(255, 255, 255);
		pHSLColor.setSaturation(121);
		assertEquals(121, pHSLColor.getSaturation());
	}
}