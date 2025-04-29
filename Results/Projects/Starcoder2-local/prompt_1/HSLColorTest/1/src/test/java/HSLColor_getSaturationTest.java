// HSLColor_getSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getSaturation() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getSaturationTest {
	private HSLColor pHSLColor;

	@Before
	public void setUp() {
		pHSLColor = new HSLColor();
	}

	@After
	public void tearDown() {
		pHSLColor = null;
	}

}