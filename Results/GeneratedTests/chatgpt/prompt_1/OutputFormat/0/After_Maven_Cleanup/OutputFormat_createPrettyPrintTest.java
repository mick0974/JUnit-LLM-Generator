// OutputFormat_createPrettyPrintTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public static createPrettyPrint() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_createPrettyPrintTest {


    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = OutputFormat.createPrettyPrint();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testIndentSize() {
        assertEquals("Indent size should be 2 spaces", "  ", outputFormat.getIndent());
    }

    @Test
    public void testNewlines() {
        assertTrue("Newlines should be enabled", outputFormat.isNewlines());
    }

    @Test
    public void testTrimText() {
        assertTrue("Text trimming should be enabled", outputFormat.isTrimText());
    }

    @Test
    public void testPadText() {
        assertTrue("Text padding should be enabled", outputFormat.isPadText());
    }

    @Test
    public void testSuppressDeclaration() {
        assertFalse("XML declaration suppression should be disabled by default", outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testOmitEncoding() {
        assertFalse("Encoding omission should be disabled by default", outputFormat.isOmitEncoding());
    }

    @Test
    public void testExpandEmptyElements() {
        assertFalse("Empty elements expansion should be disabled by default", outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testLineSeparator() {
        assertEquals("Default line separator should be newline character", "\n", outputFormat.getLineSeparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAttributeQuoteCharacter() {
        outputFormat.setAttributeQuoteCharacter('`'); // Invalid character
    }

    @Test
    public void testXHTML() {
        assertFalse("XHTML should be disabled by default", outputFormat.isXHTML());
    }
}