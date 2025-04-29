```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the isPadText() method of the OutputFormat class.
 * It follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class OutputFormat_isPadTextTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        // Arrange: Create a new OutputFormat instance before each test
        format = new OutputFormat();
    }

    /**
     * Test case 1: Verify the default value of padText is false for the default constructor.
     */
    @Test
    public void testDefaultConstructorDefaultValue() {
        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("Default value of padText should be false", isPadText);
    }

    /**
     * Test case 2: Verify isPadText() returns true after explicitly setting padText to true.
     */
    @Test
    public void testSetPadTextTrue() {
        // Arrange
        format.setPadText(true);

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertTrue("isPadText should return true after setting padText to true", isPadText);
    }

    /**
     * Test case 3: Verify isPadText() returns false after explicitly setting padText to false.
     */
    @Test
    public void testSetPadTextFalse() {
        // Arrange
        format.setPadText(false); // Explicitly set to default

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("isPadText should return false after setting padText to false", isPadText);
    }

    /**
     * Test case 4: Verify isPadText() returns false after setting padText to true and then back to false.
     */
    @Test
    public void testSetPadTextTrueThenFalse() {
        // Arrange
        format.setPadText(true);
        format.setPadText(false);

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("isPadText should return false after setting true then false", isPadText);
    }

    /**
     * Test case 5: Verify the default value is false when using the constructor with indent.
     */
    @Test
    public void testIndentConstructorDefaultValue() {
        // Arrange
        format = new OutputFormat("  "); // Use constructor with indent

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("Default value of padText should be false for indent constructor", isPadText);
    }

    /**
     * Test case 6: Verify the default value is false when using the constructor with indent and newlines.
     */
    @Test
    public void testIndentNewlinesConstructorDefaultValue() {
        // Arrange
        format = new OutputFormat("  ", true); // Use constructor with indent and newlines

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("Default value of padText should be false for indent/newlines constructor", isPadText);
    }

     /**
     * Test case 7: Verify the default value is false when using the constructor with indent, newlines, and encoding.
     */
    @Test
    public void testIndentNewlinesEncodingConstructorDefaultValue() {
        // Arrange
        format = new OutputFormat("  ", true, "ISO-8859-1"); // Use constructor with indent, newlines, and encoding

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("Default value of padText should be false for full constructor", isPadText);
    }

    /**
     * Test case 8: Verify isPadText() returns true for the format created by createPrettyPrint().
     */
    @Test
    public void testCreatePrettyPrint() {
        // Arrange
        format = OutputFormat.createPrettyPrint();

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertTrue("isPadText should return true for createPrettyPrint format", isPadText);
    }

    /**
     * Test case 9: Verify isPadText() returns false for the format created by createCompactFormat().
     */
    @Test
    public void testCreateCompactFormat() {
        // Arrange
        format = OutputFormat.createCompactFormat();

        // Act
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("isPadText should return false for createCompactFormat format", isPadText);
    }

    /**
     * Test case 10: Verify isPadText() returns true after parsing the '-padText' command line option.
     */
    @Test
    public void testParseOptionsWithPadText() {
        // Arrange
        String[] args = {"-padText"};

        // Act
        format.parseOptions(args, 0);
        boolean isPadText = format.isPadText();

        // Assert
        assertTrue("isPadText should return true after parsing '-padText' option", isPadText);
    }

     /**
     * Test case 11: Verify isPadText() returns false after parsing options without '-padText'.
     */
    @Test
    public void testParseOptionsWithoutPadText() {
        // Arrange
        String[] args = {"-newlines", "-indent", "  "};

        // Act
        format.parseOptions(args, 0);
        boolean isPadText = format.isPadText();

        // Assert
        assertFalse("isPadText should remain false after parsing options without '-padText'", isPadText);
    }
}
```