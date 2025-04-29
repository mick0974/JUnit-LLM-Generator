// OutputFormat_setIndentTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setIndent(String indent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setIndentTest {
	private OutputFormat outputFormat;

	@Before
	public void setUp() {
		outputFormat = new OutputFormat();
	}

	@After
	public void tearDown() {
		outputFormat = null;
	}

	@Test(expected = NullPointerException.class)
	public void testSetIndentWithNull() {
		outputFormat.setIndent(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIndentWithEmptyString() {
		outputFormat.setIndent("");
	}

	@Test
	public void testSetIndentWithWhitespace() {
		outputFormat.setIndent("  ");
		assertEquals("  ", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithOneSpace() {
		outputFormat.setIndent(" ");
		assertEquals(" ", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithMultipleSpaces() {
		outputFormat.setIndent("     ");
		assertEquals("     ", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithTabs() {
		outputFormat.setIndent("\t");
		assertEquals("\t", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithNewLines() {
		outputFormat.setIndent("\n");
		assertEquals("\n", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithMixedCharacters() {
		outputFormat.setIndent("  \t\n");
		assertEquals("  \t\n", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithSingleLetter() {
		outputFormat.setIndent("a");
		assertEquals("a", outputFormat.getIndent());
	}

	@Test
	public void testSetIndentWithSpecialCharacters() {
		outputFormat.setIndent("!@#$%^&*()");
		assertEquals("!@#$%^&*()", outputFormat.getIndent());
	}
}