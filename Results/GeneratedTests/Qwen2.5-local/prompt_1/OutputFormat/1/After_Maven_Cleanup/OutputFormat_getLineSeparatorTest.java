// OutputFormat_getLineSeparatorTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getLineSeparator() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getLineSeparatorTest {
    
    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }
    


    @Test(expected=NullPointerException)
    public void testGetLineSeparator_nullValue() {
        outputFormat.setLineSeparator(null);
        outputFormat.getLineSeparator();
    }

    @Test
    public void testGetLineSeparator_emptyString() {
        outputFormat.setLineSeparator("");
        assertEquals("", outputFormat.getLineSeparator());
    }

    @Test
    public void testGetLineSeparator_whitespaceCharacters() {
        outputFormat.setLineSeparator(" \t\r\n");
        assertEquals(" \t\r\n", outputFormat.getLineSeparator());
    }

    @Test
    public void testGetLineSeparator_largeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append('a');
        }
        outputFormat.setLineSeparator(sb.toString());
        assertEquals(sb.toString(), outputFormat.getLineSeparator());
    }

    @Test
    public void testGetLineSeparator_specialCharacters() {
        outputFormat.setLineSeparator("!@#$%^&*()_+");
        assertEquals("!@#$%^&*()_+", outputFormat.getLineSeparator());
    }

    @Test
    public void testGetLineSeparator_newLinesInString() {
        outputFormat.setLineSeparator("Hello\nWorld");
        assertEquals("Hello\nWorld", outputFormat.getLineSeparator());
    }

    @Test
    public void testGetLineSeparator_tabsInString() {
        outputFormat.setLineSeparator("Hello\tWorld");
        assertEquals("Hello\tWorld", outputFormat.getLineSeparator());
    }

    @Test
    public void testGetLineSeparator_carriageReturnsInString() {
        outputFormat.setLineSeparator("Hello\rWorld");
        assertEquals("Hello\rWorld", outputFormat.getLineSeparator());
    }
}