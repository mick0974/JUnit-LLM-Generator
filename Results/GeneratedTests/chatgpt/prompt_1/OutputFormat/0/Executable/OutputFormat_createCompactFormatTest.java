// OutputFormat_createCompactFormatTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public static createCompactFormat() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_createCompactFormatTest {


    private OutputFormat format;

    @Before
    public void setUp() {
        // Create a compact format instance before each test
        format = OutputFormat.createCompactFormat();
    }

    @After
    public void tearDown() {
        // Clean up after each test
        format = null;
    }

    @Test
    public void testDefaultIndentIsNull() {
        // Verify that indent is null in compact format
        assertNull(format.getIndent());
    }

    @Test
    public void testNewlinesIsFalse() {
        // Verify that newlines is false in compact format
        assertFalse(format.isNewlines());
    }

    @Test
    public void testTrimTextIsTrue() {
        // Verify that trimText is true in compact format
        assertTrue(format.isTrimText());
    }

    @Test
    public void testPadTextIsFalseByDefault() {
        // Verify that padText is false by default in compact format
        assertFalse(format.isPadText());
    }

    @Test
    public void testSuppressDeclarationIsFalseByDefault() {
        // Verify that suppressDeclaration is false by default
        assertFalse(format.isSuppressDeclaration());
    }

    @Test
    public void testOmitEncodingIsFalseByDefault() {
        // Verify that omitEncoding is false by default
        assertFalse(format.isOmitEncoding());
    }

    @Test
    public void testExpandEmptyElementsIsFalseByDefault() {
        // Verify that expandEmptyElements is false by default
        assertFalse(format.isExpandEmptyElements());
    }

    @Test
    public void testDoXHTMLIsFalseByDefault() {
        // Verify that doXHTML is false by default
        assertFalse(format.isXHTML());
    }

    @Test
    public void testLineSeparatorIsDefaultNewline() {
        // Verify line separator is default to newline character
        assertEquals("\n", format.getLineSeparator());
    }

    @Test
    public void testEncodingIsUTF8ByDefault() {
        // Verify that default encoding is UTF-8
        assertEquals("UTF-8", format.getEncoding());
    }
}