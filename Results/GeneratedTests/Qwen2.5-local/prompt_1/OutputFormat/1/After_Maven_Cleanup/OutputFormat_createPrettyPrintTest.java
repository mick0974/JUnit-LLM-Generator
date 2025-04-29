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
    private OutputFormat format;

    @Before
    public void setUp() throws Exception {
        format = OutputFormat.createPrettyPrint();
    }

    @After
    public void tearDown() throws Exception {
        format = null;
    }

    @Test
    public void testCreatePrettyPrint_IndentSize() {
        assertEquals(2, format.getIndent().length());
    }

    @Test
    public void testCreatePrettyPrint_NewLines() {
        assertTrue(format.isNewlines());
    }

    @Test
    public void testCreatePrettyPrint_TrimText() {
        assertTrue(format.isTrimText());
    }

    @Test
    public void testCreatePrettyPrint_PadText() {
        assertTrue(format.isPadText());
    }

    @Test
    public void testCreatePrettyPrint_XHTML() {
        assertFalse(format.isXHTML());
    }

    @Test
    public void testCreatePrettyPrint_SuppressDeclaration() {
        assertFalse(format.isSuppressDeclaration());
    }

    @Test
    public void testCreatePrettyPrint_OmitEncoding() {
        assertFalse(format.isOmitEncoding());
    }

    @Test
    public void testCreatePrettyPrint_ExpandEmptyElements() {
        assertFalse(format.isExpandEmptyElements());
    }

    @Test
    public void testCreatePrettyPrint_LineSeparator() {
        assertEquals("\n", format.getLineSeparator());
    }
}