import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the getLineSeparator() method of OutputFormat.
 * Verifies that the correct line separator string is returned under various conditions.
 * Contains 10 unit test cases following the Arrange-Act-Assert pattern.
 */
public class OutputFormat_getLineSeparatorTest {

    private OutputFormat format;

    /**
     * Sets up a new OutputFormat instance before each test.
     */
    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    /**
     * Test Case 1: Verifies the default line separator is "\n".
     */
    @Test
    public void testGetDefaultLineSeparator() {
        // Arrange: Default OutputFormat created in setUp() with default separator "\n"

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Default line separator should be '\\n'", "\n", separator);
    }

    /**
     * Test Case 2: Verifies getting a custom line separator (Windows style) after setting it.
     */
    @Test
    public void testGetCustomLineSeparatorWindows() {
        // Arrange
        String customSeparator = "\r\n"; // Windows style
        format.setLineSeparator(customSeparator);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should match the custom Windows value set", customSeparator, separator);
    }

    /**
     * Test Case 3: Verifies getting a custom line separator (Classic Mac style) after setting it.
     */
    @Test
    public void testGetCustomLineSeparatorMacClassic() {
        // Arrange
        String customSeparator = "\r"; // Classic Mac style
        format.setLineSeparator(customSeparator);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should match the custom Classic Mac value set", customSeparator, separator);
    }

    /**
     * Test Case 4: Verifies getting the system default line separator after setting it explicitly.
     */
    @Test
    public void testGetSystemLineSeparator() {
        // Arrange
        String systemSeparator = System.getProperty("line.separator");
        format.setLineSeparator(systemSeparator);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should match the system default value set", systemSeparator, separator);
    }

    /**
     * Test Case 5: Verifies getting an empty string as the line separator after setting it.
     */
    @Test
    public void testGetEmptyLineSeparator() {
        // Arrange
        String emptySeparator = "";
        format.setLineSeparator(emptySeparator);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should be an empty string when set to empty", emptySeparator, separator);
    }

    /**
     * Test Case 6: Verifies getting a null value as the line separator after setting it.
     * Note: The behavior of a null separator might depend on the consuming XMLWriter,
     * but the getter should return the set null value.
     */
    @Test
    public void testGetNullLineSeparator() {
        // Arrange
        format.setLineSeparator(null);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertNull("Line separator should be null when set to null", separator);
    }

    /**
     * Test Case 7: Verifies getting a line separator containing only whitespace characters.
     */
    @Test
    public void testGetWhitespaceLineSeparator() {
        // Arrange
        String whitespaceSeparator = " \t\t ";
        format.setLineSeparator(whitespaceSeparator);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should match the whitespace string set", whitespaceSeparator, separator);
    }

    /**
     * Test Case 8: Verifies getting the line separator after setting it multiple times,
     * ensuring the last value set is retrieved.
     */
    @Test
    public void testGetLineSeparatorAfterMultipleSets() {
        // Arrange
        String firstSeparator = "\r";
        String secondSeparator = "\n\r"; // Different separator
        String finalSeparator = "---";   // Final value to check

        format.setLineSeparator(firstSeparator);
        format.setLineSeparator(secondSeparator);
        format.setLineSeparator(finalSeparator); // Overwrite previous values

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should match the last value set", finalSeparator, separator);
    }

    /**
     * Test Case 9: Verifies the line separator remains unchanged even after modifying
     * other unrelated properties of the OutputFormat object.
     */
    @Test
    public void testGetLineSeparatorAfterOtherPropertyChanges() {
        // Arrange
        String expectedSeparator = "\n"; // Default value
        // Modify other properties
        format.setIndent("    ");
        format.setNewlines(true);
        format.setEncoding("ISO-8859-1");
        format.setTrimText(true);
        format.setXHTML(true);

        // Act
        String separator = format.getLineSeparator();

        // Assert
        assertEquals("Line separator should remain unchanged after modifying other properties", expectedSeparator, separator);
    }

    /**
     * Test Case 10: Verifies the line separator is correctly copied to a cloned object.
     * Assumes the clone() method performs a correct copy of the lineSeparator field.
     */
}