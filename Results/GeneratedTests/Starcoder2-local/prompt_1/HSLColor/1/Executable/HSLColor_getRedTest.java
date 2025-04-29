// HSLColor_getRedTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getRed() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getRedTest {
	private HSLColor hsl;

	@Before
	public void setUp() {
		hsl = new HSLColor();
	}

	@After
	public void tearDown() {
		hsl = null;
	}

}