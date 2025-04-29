// OutputFormat_getIndentTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getIndent() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getIndentTest {

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
    public void testDefaultIndent() {
        assertNull("Default indent should be null", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentNull() {
        outputFormat.setIndent(null);
        assertNull("Indent should be null", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentEmptyString() {
        outputFormat.setIndent("");
        assertNull("Indent should be null for empty string", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSpaces() {
        outputFormat.setIndent("    "); // 4 spaces
        assertEquals("Indent should be '    '", "    ", outputFormat.getIndent());
    }

    @Test
    public void testSetStandardIndent() {
        outputFormat.setIndent(true);
        assertEquals("Indent should be standard indent (two spaces)", "  ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentWithStandardIndentConstant() {
        outputFormat.setIndent(OutputFormat.STANDARD_INDENT);
        assertEquals("Indent should match the standard indent of two spaces", OutputFormat.STANDARD_INDENT, outputFormat.getIndent());
    }

    @Test
    public void testIndentSizeEffectOnIndent() {
        outputFormat.setIndentSize(4);
        assertEquals("Indent should be 4 spaces", "    ", outputFormat.getIndent());
    }

    @Test
    public void testResetIndent() {
        outputFormat.setIndent("test");
        outputFormat.setIndent(false);
        assertNull("Indent should be null after resetting", outputFormat.getIndent());
    }

    @Test
    public void testIndentedOutputFormatConstructor() {
        OutputFormat indentedFormat = new OutputFormat("    "); // 4 spaces
        assertEquals("Constructor with indent should set indent to 4 spaces", "    ", indentedFormat.getIndent());
    }

    @Test
    public void testPrettyPrintIndent() {
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        assertEquals("Pretty print format should have indent of 2 spaces", "  ", prettyFormat.getIndent());
    }
}