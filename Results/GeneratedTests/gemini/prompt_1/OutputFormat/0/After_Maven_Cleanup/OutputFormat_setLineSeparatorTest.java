// OutputFormat_setLineSeparatorTest.java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the OutputFormat#setLineSeparator(String) method.
 * It verifies that the line separator can be correctly set to various values,
 * including typical and edge cases.
 * Uses JUnit 4 annotations and assertions.
 */
public class OutputFormat_setLineSeparatorTest {

    private OutputFormat format;

    /**
     * Sets up a new OutputFormat object before each test.
     */
    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    /**
     * Test case 1: Set the line separator to the standard Unix/Linux newline character "\n".
     */
    @Test
    public void testSetLineSeparatorUnix() {
        // Arrange
        String expectedSeparator = "\n";

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should be set to \\n", expectedSeparator, format.getLineSeparator());
    }

    /**
     * Test case 2: Set the line separator to the standard Windows newline sequence "\r\n".
     */
    @Test
    public void testSetLineSeparatorWindows() {
        // Arrange
        String expectedSeparator = "\r\n";

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should be set to \\r\\n", expectedSeparator, format.getLineSeparator());
    }

    /**
     * Test case 3: Set the line separator to the classic Mac OS newline character "\r".
     * (Although less common now, it's a valid test case).
     */
    @Test
    public void testSetLineSeparatorClassicMac() {
        // Arrange
        String expectedSeparator = "\r";

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should be set to \\r", expectedSeparator, format.getLineSeparator());
    }

    /**
     * Test case 4: Set the line separator to an empty string "".
     */
    @Test
    public void testSetLineSeparatorEmpty() {
        // Arrange
        String expectedSeparator = "";

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should be set to an empty string", expectedSeparator, format.getLineSeparator());
    }

    /**
     * Test case 5: Set the line separator to null.
     */
    @Test
    public void testSetLineSeparatorNull() {
        // Arrange
        String initialSeparator = format.getLineSeparator(); // Get initial default if needed for comparison
        String nullSeparator = null;

        // Act
        format.setLineSeparator(nullSeparator);

        // Assert
        assertNull("Line separator should be set to null", format.getLineSeparator());
    }

    /**
     * Test case 6: Set the line separator to a custom multi-character string.
     */
    @Test
    public void testSetLineSeparatorCustom() {
        // Arrange
        String expectedSeparator = "--END--";

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should be set to the custom string", expectedSeparator, format.getLineSeparator());
    }

    /**
     * Test case 7: Set the line separator using the system property.
     */
    @Test
    public void testSetLineSeparatorSystemDefault() {
        // Arrange
        String expectedSeparator = System.getProperty("line.separator");
        assertNotNull("System line separator should not be null", expectedSeparator); // Pre-condition check

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should be set to the system default", expectedSeparator, format.getLineSeparator());
    }

    /**
     * Test case 8: Verify the default line separator is "\n" initially.
     */
    @Test
    public void testDefaultLineSeparator() {
        // Arrange
        String expectedDefaultSeparator = "\n";

        // Act
        // No action needed, testing the default state after setUp()

        // Assert
        assertEquals("Default line separator should be \\n", expectedDefaultSeparator, format.getLineSeparator());
    }

    /**
     * Test case 9: Set the line separator, then set it back to null.
     */
    @Test
    public void testSetLineSeparatorThenNull() {
        // Arrange
        format.setLineSeparator("\r\n"); // Set to a non-null value first

        // Act
        format.setLineSeparator(null);

        // Assert
        assertNull("Line separator should be null after setting it back", format.getLineSeparator());
    }

    /**
     * Test case 10: Set the line separator to a string containing special characters like tabs.
     */
    @Test
    public void testSetLineSeparatorWithSpecialChars() {
        // Arrange
        String expectedSeparator = "\t--\t";

        // Act
        format.setLineSeparator(expectedSeparator);

        // Assert
        assertEquals("Line separator should handle special characters correctly", expectedSeparator, format.getLineSeparator());
    }
}