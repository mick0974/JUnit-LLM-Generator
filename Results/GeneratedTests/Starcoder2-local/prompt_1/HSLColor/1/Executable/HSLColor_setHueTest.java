// HSLColor_setHueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setHue(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setHueTest {
	private HSLColor pHSL;

	@Before
	public void setUp() {
		pHSL = new HSLColor();
	}

	@After
	public void tearDown() {
		pHSL = null;
	}

	@Test
	public void test_setHue_01() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(0);
		assertEquals(0, pHSL.getHue());
	}

	@Test
	public void test_setHue_02() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(128);
		assertEquals(128, pHSL.getHue());
	}

	@Test
	public void test_setHue_03() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(255);
		assertEquals(255, pHSL.getHue());
	}




	@Test
	public void test_setHue_07() {
		pHSL.initHSLbyRGB(255, 0, 0);
		pHSL.setHue(255);
		assertEquals(255, pHSL.getHue());
	}



}