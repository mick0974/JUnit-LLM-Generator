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
    
    /**
     * Test case for normal operation where line separator is set to a non-empty string.
     */
    @Test
    public void testSetLineSeparator_NonEmptyString() {
        outputFormat.setLineSeparator("\r\n");
        assertEquals("\r\n", outputFormat.getLineSeparator());
    }

    /**
     * Test case for normal operation where line separator is set to an empty string.
     */
    @Test
    public void testSetLineSeparator_EmptyString() {
        outputFormat.setLineSeparator("");
        assertEquals("", outputFormat.getLineSeparator());
    }

    /**
     * Test case for normal operation where line separator is set to null.
     */
    @Test
    public void testSetLineSeparator_Null() {
        outputFormat.setLineSeparator(null);
        assertNull(outputFormat.getLineSeparator());
    }

    /**
     * Test case for normal operation where line separator is set to a white-space character.
     */
    @Test
    public void testSetLineSeparator_Whitespace() {
        outputFormat.setLineSeparator(" ");
        assertEquals(" ", outputFormat.getLineSeparator());
    }

    /**
     * Test case for boundary condition where line separator is set to a very large string.
     */
    @Test
    public void testSetLineSeparator_LargeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append('a');
        }
        outputFormat.setLineSeparator(sb.toString());
        assertEquals(sb.toString(), outputFormat.getLineSeparator());
    }

    /**
     * Test case for exception handling where line separator is set to an invalid string.
     */
    @Test(expected = NullPointerException.class)
    public void testSetLineSeparator_ThrowsNullPointerException() {
        outputFormat.setLineSeparator((String) null);
    }

    /**
     * Test case for exception handling where line separator is set to an invalid string.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetLineSeparator_ThrowsIllegalArgumentException() {
        outputFormat.setLineSeparator("invalid separator");
    }

    /**
     * Test case for normal operation where line separator is set to a system-specific line separator.
     */
    @Test
    public void testSetLineSeparator_SystemProperty() {
        outputFormat.setLineSeparator(System.getProperty("line.separator"));
        assertEquals(System.getProperty("line.separator"), outputFormat.getLineSeparator());
    }
}