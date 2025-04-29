// OutputFormat_setTrimTextTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setTrimText(boolean trimText) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setTrimTextTest {
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
    public void testSetTrimTextTrue() {
        // Arrange
        boolean expected = true;

        // Act
        outputFormat.setTrimText(expected);

        // Assert
        assertEquals(expected, outputFormat.isTrimText());
    }

    @Test
    public void testSetTrimTextFalse() {
        // Arrange
        boolean expected = false;

        // Act
        outputFormat.setTrimText(expected);

        // Assert
        assertEquals(expected, outputFormat.isTrimText());
    }
}