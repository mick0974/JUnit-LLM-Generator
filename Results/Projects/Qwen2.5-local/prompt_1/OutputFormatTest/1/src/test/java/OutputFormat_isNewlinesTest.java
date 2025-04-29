// OutputFormat_isNewlinesTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isNewlines() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isNewlinesTest {
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
	public void testIsNewlines_defaultValue() {
		boolean result = outputFormat.isNewlines();
		assertFalse(result);
	}

	@Test
	public void testIsNewlines_setToTrue() {
		outputFormat.setNewlines(true);
		boolean result = outputFormat.isNewlines();
		assertTrue(result);
	}

	@Test
	public void testIsNewlines_setToFalse() {
		outputFormat.setNewlines(false);
		boolean result = outputFormat.isNewlines();
		assertFalse(result);
	}

	@Test
	public void testIsNewlines_afterSettingIndent() {
		outputFormat.setIndent("  ");
		outputFormat.setNewlines(true);
		boolean result = outputFormat.isNewlines();
		assertTrue(result);
	}

	@Test
	public void testIsNewlines_afterSettingEncoding() {
		outputFormat.setEncoding("UTF-8");
		outputFormat.setNewlines(false);
		boolean result = outputFormat.isNewlines();
		assertFalse(result);
	}

	@Test
	public void testIsNewlines_afterSettingOmitEncoding() {
		outputFormat.setOmitEncoding(true);
		outputFormat.setNewlines(true);
		boolean result = outputFormat.isNewlines();
		assertTrue(result);
	}

	@Test
	public void testIsNewlines_afterSettingSuppressDeclaration() {
		outputFormat.setSuppressDeclaration(false);
		outputFormat.setNewlines(false);
		boolean result = outputFormat.isNewlines();
		assertFalse(result);
	}

	@Test
	public void testIsNewlines_afterSettingNewLineAfterDeclaration() {
		outputFormat.setNewLineAfterDeclaration(true);
		outputFormat.setNewlines(true);
		boolean result = outputFormat.isNewlines();
		assertTrue(result);
	}

	@Test
	public void testIsNewlines_afterSettingExpandEmptyElements() {
		outputFormat.setExpandEmptyElements(true);
		outputFormat.setNewlines(false);
		boolean result = outputFormat.isNewlines();
		assertFalse(result);
	}

	@Test
	public void testIsNewlines_afterSettingTrimText() {
		outputFormat.setTrimText(true);
		outputFormat.setNewlines(true);
		boolean result = outputFormat.isNewlines();
		assertTrue(result);
	}
}