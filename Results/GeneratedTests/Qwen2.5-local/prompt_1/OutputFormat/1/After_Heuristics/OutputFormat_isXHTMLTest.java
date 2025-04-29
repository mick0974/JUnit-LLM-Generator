// OutputFormat_isXHTMLTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isXHTML() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isXHTMLTest {
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
    public void testIsXHTML_default() {
        assertFalse(outputFormat.isXHTML());
    }

    @Test
    public void testIsXHTML_setTrue() {
        outputFormat.setXHTML(true);
        assertTrue(outputFormat.isXHTML());
    }

    @Test
    public void testIsXHTML_setFalse() {
        outputFormat.setXHTML(false);
        assertFalse(outputFormat.isXHTML());
    }
    
    @Test(expected = NullPointerException)
    public void testSetXHTML_null() {
        outputFormat.setXHTML(null);
    }
    
    @Test(expected = IllegalArgumentException)
    public void testSetXHTML_invalidArgument() {
        outputFormat.setAttributeQuoteCharacter('!');
    }
    
    @Test(expected = IllegalArgumentException)
    public void testParseOptions_invalidOption() {
        String[] args = {"-invalidOption"};
        assertEquals(0, outputFormat.parseOptions(args, 0));
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException)
    public void testParseOptions_missingValue() {
        String[] args = {"-indentSize"};
        outputFormat.parseOptions(args, 0);
    }
    
    @Test(expected = NumberFormatException)
    public void testParseOptions_nonNumericValue() {
        String[] args = {"-indentSize", "abc"};
        outputFormat.parseOptions(args, 0);
    }
    
    @Test
    public void testClone() throws CloneNotSupportedException {
        OutputFormat clone = (OutputFormat) outputFormat.clone();
        assertNotSame(outputFormat, clone);
        assertEquals(outputFormat.getXHTML(), clone.getXHTML());
    }
}