// OutputFormat_setLineSeparatorTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setLineSeparator(String separator) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setLineSeparatorTest {
    
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
    public void testSetLineSeparatorWithNull() {
        outputFormat.setLineSeparator(null);
        assertEquals("\n", outputFormat.getLineSeparator());
    }
    
    @Test
    public void testSetLineSeparatorWithEmptyString() {
        outputFormat.setLineSeparator("");
        assertEquals("\n", outputFormat.getLineSeparator());
    }
    
    @Test
    public void testSetLineSeparatorWithSystemLineSeparator() {
        outputFormat.setLineSeparator(System.getProperty("line.separator"));
        assertEquals(System.getProperty("line.separator"), outputFormat.getLineSeparator());
    }
    
    @Test
    public void testSetLineSeparatorWithCustomString() {
        outputFormat.setLineSeparator("\r\n");
        assertEquals("\r\n", outputFormat.getLineSeparator());
    }
    
    @Test
    public void testSetLineSeparatorWithWhitespace() {
        outputFormat.setLineSeparator(" ");
        assertEquals(" ", outputFormat.getLineSeparator());
    }
    
    @Test(expected=NullPointerException)
    public void testSetLineSeparatorWithNullPointer() {
        outputFormat.setLineSeparator((String)null);
    }
    
    @Test(expected=IllegalArgumentException)
    public void testSetLineSeparatorWithInvalidCharacters() {
        outputFormat.setLineSeparator("\t");
    }
    
    @Test(expected=IllegalArgumentException)
    public void testSetLineSeparatorWithSpecialCharacters() {
        outputFormat.setLineSeparator("<>");
    }
    
    @Test(expected=IllegalArgumentException)
    public void testSetLineSeparatorWithUnicodeEscape() {
        outputFormat.setLineSeparator("\\u000A");
    }
    
    @Test(expected=IllegalArgumentException)
    public void testSetLineSeparatorWithControlCharacters() {
        outputFormat.setLineSeparator("\u0001");
    }
}