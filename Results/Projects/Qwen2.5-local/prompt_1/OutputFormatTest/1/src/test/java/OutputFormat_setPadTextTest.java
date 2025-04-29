// OutputFormat_setPadTextTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setPadText(boolean padText) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setPadTextTest {
	private OutputFormat outputFormat;

	@Before
	public void setUp() {
		outputFormat = new OutputFormat();
	}

	@After
	public void tearDown() {
		outputFormat = null;
	}

	@Test
	public void testSetPadText_true() {
		outputFormat.setPadText(true);
		assertTrue(outputFormat.isPadText());
	}



	@Test
	public void testSetPadText_initialValue() {
		boolean initialValue = outputFormat.isPadText();
		outputFormat.setPadText(!initialValue);
		assertNotEquals(initialValue, outputFormat.isPadText());
	}

	@Test
	public void testSetPadText_noChange() {
		boolean currentValue = outputFormat.isPadText();
		outputFormat.setPadText(currentValue);
		assertEquals(currentValue, outputFormat.isPadText());
	}

}