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


    
    
    
    
    
    @Test(expected = NumberFormatException)
    public void testParseOptions_nonNumericValue() {
        String[] args = {"-indentSize", "abc"};
        outputFormat.parseOptions(args, 0);
    }
    
}