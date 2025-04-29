// OutputFormat_setIndentSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setIndentSize(int indentSize) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setIndentSizeTest {

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
    public void testSetIndentSizeZero() {
        outputFormat.setIndentSize(0);
        assertEquals("", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeNegative() {
        outputFormat.setIndentSize(-3);
        assertEquals("", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeOne() {
        outputFormat.setIndentSize(1);
        assertEquals(" ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeTwo() {
        outputFormat.setIndentSize(2);
        assertEquals("  ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeFive() {
        outputFormat.setIndentSize(5);
        assertEquals("     ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeTen() {
        outputFormat.setIndentSize(10);
        assertEquals("          ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeLargeNumber() {
        outputFormat.setIndentSize(100);
        String expected = new String(new char[100]).replace("\0", " ");
        assertEquals(expected, outputFormat.getIndent());
    }

    @Test
    public void testRepeatedSetIndentSize() {
        outputFormat.setIndentSize(3);
        outputFormat.setIndentSize(6);
        assertEquals("      ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeAfterSetIndentFalse() {
        outputFormat.setIndent(false);
        assertNull(outputFormat.getIndent());
        outputFormat.setIndentSize(4);
        assertEquals("    ", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeAfterSetIndentTrue() {
        outputFormat.setIndent(true);
        assertEquals(OutputFormat.STANDARD_INDENT, outputFormat.getIndent());
        outputFormat.setIndentSize(7);
        assertEquals("       ", outputFormat.getIndent());
    }
}