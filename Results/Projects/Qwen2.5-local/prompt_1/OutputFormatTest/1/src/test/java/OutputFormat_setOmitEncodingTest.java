// OutputFormat_setOmitEncodingTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setOmitEncoding(boolean omitEncoding) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setOmitEncodingTest {
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
	public void testSetOmitEncoding_true() {
		outputFormat.setOmitEncoding(true);
		assertTrue(outputFormat.isOmitEncoding());
	}

	@Test
	public void testSetOmitEncoding_false() {
		outputFormat.setOmitEncoding(false);
		assertFalse(outputFormat.isOmitEncoding());
	}








}