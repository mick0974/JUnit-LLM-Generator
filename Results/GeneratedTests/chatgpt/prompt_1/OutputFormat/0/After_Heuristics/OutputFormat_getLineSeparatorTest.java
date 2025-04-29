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

    /**
     * Test case for default line separator.
     */
    @Test
    public void testDefaultLineSeparator() {
        assertEquals("\n", outputFormat.getLineSeparator());
    }

    /**
     * Test case for setting a custom line separator to "\r\n".
     */
    @Test
    public void testSetLineSeparatorToCRLF() {
        outputFormat.setLineSeparator("\r\n");
        assertEquals("\r\n", outputFormat.getLineSeparator());
    }

    /**
     * Test case for setting a custom line separator to an empty string.
     */
    @Test
    public void testSetLineSeparatorToEmptyString() {
        outputFormat.setLineSeparator("");
        assertEquals("", outputFormat.getLineSeparator());
    }

    /**
     * Test case to ensure line separator is correctly updated.
     */
    @Test
    public void testUpdateLineSeparator() {
        outputFormat.setLineSeparator("\r");
        assertEquals("\r", outputFormat.getLineSeparator());
    }

    /**
     * Test case to verify setting line separator to a non-standard string works.
     */
    @Test
    public void testSetLineSeparatorToCustomString() {
        String customSeparator = "||";
        outputFormat.setLineSeparator(customSeparator);
        assertEquals(customSeparator, outputFormat.getLineSeparator());
    }

    /**
     * Test case to verify setting the system's default line separator.
     */
    @Test
    public void testSetSystemLineSeparator() {
        String systemSeparator = System.getProperty("line.separator");
        outputFormat.setLineSeparator(systemSeparator);
        assertEquals(systemSeparator, outputFormat.getLineSeparator());
    }

    /**
     * Test case setting null as line separator doesn't change from default.
     */
    @Test(expected = NullPointerException.class)
    public void testSetLineSeparatorToNull() {
        outputFormat.setLineSeparator(null);
    }

    /**
     * Test case to verify that changing line separator multiple times works.
     */
    @Test
    public void testChangeLineSeparatorMultipleTimes() {
        outputFormat.setLineSeparator("\n");
        assertEquals("\n", outputFormat.getLineSeparator());
        outputFormat.setLineSeparator("\r\n");
        assertEquals("\r\n", outputFormat.getLineSeparator());
        outputFormat.setLineSeparator("\r");
        assertEquals("\r", outputFormat.getLineSeparator());
    }

    /**
     * Test handling of long custom separator strings.
     */
    @Test
    public void testLongCustomLineSeparator() {
        String longSeparator = "===";
        outputFormat.setLineSeparator(longSeparator);
        assertEquals(longSeparator, outputFormat.getLineSeparator());
    }

    /**
     * Test that line separator can be set to special characters.
     */
    @Test
    public void testSpecialCharacterLineSeparator() {
        String specialSeparator = "\u2028";  // Line separator character
        outputFormat.setLineSeparator(specialSeparator);
        assertEquals(specialSeparator, outputFormat.getLineSeparator());
    }
}