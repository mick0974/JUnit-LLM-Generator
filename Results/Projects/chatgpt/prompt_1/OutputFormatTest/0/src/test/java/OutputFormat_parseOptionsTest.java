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
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testParseOptions_SuppressDeclaration() {
        String[] args = {"-suppressDeclaration"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isSuppressDeclaration());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_OmitEncoding() {
        String[] args = {"-omitEncoding"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isOmitEncoding());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_Indent() {
        String[] args = {"-indent", "    "};
        int result = outputFormat.parseOptions(args, 0);
        assertEquals("    ", outputFormat.getIndent());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_IndentSize() {
        String[] args = {"-indentSize", "3"};
        int result = outputFormat.parseOptions(args, 0);
        assertEquals("   ", outputFormat.getIndent());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_ExpandEmptyElements() {
        String[] args = {"-expandEmpty"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isExpandEmptyElements());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_Newlines() {
        String[] args = {"-newlines"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isNewlines());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_LineSeparator() {
        String[] args = {"-lineSeparator", "\r\n"};
        int result = outputFormat.parseOptions(args, 0);
        assertEquals("\r\n", outputFormat.getLineSeparator());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_TrimText() {
        String[] args = {"-trimText"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isTrimText());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_PadText() {
        String[] args = {"-padText"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isPadText());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_XHTML() {
        String[] args = {"-xhtml"};
        int result = outputFormat.parseOptions(args, 0);
        assertTrue(outputFormat.isXHTML());
        assertEquals(args.length, result);
    }

    @Test
    public void testParseOptions_UnknownOption() {
        String[] args = {"-unknownOption"};
        int result = outputFormat.parseOptions(args, 0);
        assertEquals(0, result); // Should return the index of the unknown option
    }
}