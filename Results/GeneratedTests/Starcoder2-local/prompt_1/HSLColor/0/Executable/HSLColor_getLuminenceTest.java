// HSLColor_getLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getLuminence() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getLuminenceTest {
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
	public void test_01_Luminence_01() {
		hsl.initHSLbyRGB(0, 0, 0);
		assertEquals(0, hsl.getLuminence());
	}

	@Test
	public void test_02_Luminence_02() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(255, hsl.getLuminence());
	}







	@Test
	public void test_09_Luminence_09() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(255, hsl.getLuminence());
	}

	@Test
	public void test_10_Luminence_10() {
		hsl.initHSLbyRGB(255, 255, 255);
		assertEquals(255, hsl.getLuminence());
	}
}