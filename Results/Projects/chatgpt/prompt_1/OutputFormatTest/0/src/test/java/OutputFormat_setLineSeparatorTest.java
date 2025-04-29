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
        assertNull(outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithEmptyString() {
        outputFormat.setLineSeparator("");
        assertEquals("", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithSingleCharacter() {
        outputFormat.setLineSeparator("\r");
        assertEquals("\r", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithDoubleCharacters() {
        outputFormat.setLineSeparator("\n");
        assertEquals("\n", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithMultipleCharacters() {
        outputFormat.setLineSeparator("\r\n");
        assertEquals("\r\n", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithNonStandardSeparator() {
        outputFormat.setLineSeparator("#");
        assertEquals("#", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithWhitespaceCharacters() {
        outputFormat.setLineSeparator(" \t ");
        assertEquals(" \t ", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorMultipleTimes() {
        outputFormat.setLineSeparator("\n");
        assertEquals("\n", outputFormat.getLineSeparator());
        outputFormat.setLineSeparator("\r");
        assertEquals("\r", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithEscapeCharacters() {
        outputFormat.setLineSeparator("\\n");
        assertEquals("\\n", outputFormat.getLineSeparator());
    }

    @Test
    public void testSetLineSeparatorWithCustomString() {
        outputFormat.setLineSeparator("custom-separator");
        assertEquals("custom-separator", outputFormat.getLineSeparator());
    }
}