// OutputFormat_getEncodingTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getEncoding() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getEncodingTest {
	private OutputFormat format;

	@Before
	public void setUp() {
		format = new OutputFormat();
	}

	@After
	public void tearDown() {
		format = null;
	}

	@Test
	public void testGetEncoding_InitialValue() {
		String expected = "UTF-8";
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_SetToNull() {
		format.setEncoding(null);
		String expected = null;
		String actual = format.getEncoding();
	 assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_SetToEmptyString() {
		format.setEncoding("");
		String expected = "";
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_SetToNonDefault() {
		String nonDefaultEncoding = "ISO-8859-1";
		format.setEncoding(nonDefaultEncoding);
		String expected = nonDefaultEncoding;
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_ChangeAndRetrieveAgain() {
		String initialEncoding = "US-ASCII";
		String updatedEncoding = "GB2312";
		format.setEncoding(initialEncoding);
		format.setEncoding(updatedEncoding);
		String expected = updatedEncoding;
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_MultipleSetCalls() {
		String firstEncoding = "Shift_JIS";
		String secondEncoding = "EUC-JP";
		String thirdEncoding = "Big5";
		format.setEncoding(firstEncoding);
		format.setEncoding(secondEncoding);
		format.setEncoding(thirdEncoding);
		String expected = thirdEncoding;
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_AfterSettingOmitEncoding() {
		boolean omitEncoding = true;
		format.setOmitEncoding(omitEncoding);
		String expected = "UTF-8"; // Should still return default encoding
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_AfterSettingSuppressDeclaration() {
		boolean suppressDeclaration = true;
		format.setSuppressDeclaration(suppressDeclaration);
		String expected = "UTF-8"; // Should still return default encoding
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_AfterSettingNewLines() {
		boolean newLines = true;
		format.setNewlines(newLines);
		String expected = "UTF-8"; // Should still return default encoding
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetEncoding_AfterSettingIndent() {
		String indent = "  ";
		format.setIndent(indent);
		String expected = "UTF-8"; // Should still return default encoding
		String actual = format.getEncoding();
		assertEquals(expected, actual);
	}
}