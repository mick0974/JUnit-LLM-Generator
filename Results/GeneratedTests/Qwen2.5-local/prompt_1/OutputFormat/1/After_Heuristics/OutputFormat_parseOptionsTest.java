// OutputFormat_parseOptionsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public parseOptions(String[] args, int i) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_parseOptionsTest {
	private OutputFormat outputFormat;

	@Before
	public void setUp() throws Exception {
		outputFormat = new OutputFormat();
	}

	@After
	public void tearDown() throws Exception {
		outputFormat = null;
	}

	@Test
	public void testParseOptionsWithNullArgs() {
		int result = outputFormat.parseOptions(null, 0);
		assertEquals(0, result);
	}

	@Test
	public void testParseOptionsWithEmptyArgs() {
		String[] args = {};
		int result = outputFormat.parseOptions(args, 0);
		assertEquals(0, result);
	}

	@Test
	public void testParseOptionsWithSingleOption() {
		String[] args = {"-suppressDeclaration"};
		int result = outputFormat.parseOptions(args, 0);
		assertTrue(outputFormat.isSuppressDeclaration());
		assertEquals(1, result);
	}

	@Test
	public void testParseOptionsWithMultipleOptions() {
		String[] args = {"-suppressDeclaration", "-indentSize", "3", "-newlines"};
		int result = outputFormat.parseOptions(args, 0);
		assertTrue(outputFormat.isSuppressDeclaration());
		assertEquals(3, outputFormat.getIndentSize());
		assertTrue(outputFormat.isNewlines());
		assertEquals(4, result);
	}

	@Test
	public void testParseOptionsWithUnknownOption() {
		String[] args = {"-unknownOption"};
		int result = outputFormat.parseOptions(args, 0);
		assertEquals(1, result);
	}

	@Test
	public void testParseOptionsWithWhitespaceInOptionValue() {
		String[] args = {"-indentSize", "3 5"};
		int result = outputFormat.parseOptions(args, 0);
	 assertEquals(2, result);
	}

	@Test
	public void testParseOptionsWithNegativeIndentSize() {
		String[] args = {"-indentSize", "-1"};
		int result = outputFormat.parseOptions(args, 0);
	 assertEquals(2, result);
	}

	@Test
	public void testParseOptionsWithLargeIndentSize() {
		String[] args = {"-indentSize", "1000"};
		int result = outputFormat.parseOptions(args, 0);
	 assertEquals(2, result);
	}

	@Test
	public void testParseOptionsWithZeroIndentSize() {
		String[] args = {"-indentSize", "0"};
		int result = outputFormat.parseOptions(args, 0);
	 assertEquals(2, result);
	}

	@Test
	public void testParseOptionsWithNonNumericIndentSize() {
		String[] args = {"-indentSize", "abc"};
		int result = outputFormat.parseOptions(args, 0);
		assertEquals(2, result);
	}
}