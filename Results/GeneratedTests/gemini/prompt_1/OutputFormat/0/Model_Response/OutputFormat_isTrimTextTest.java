```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the isTrimText() method of the OutputFormat class.
 * This class focuses specifically on testing the behavior of the trimText property.
 * It follows the Arrange-Act-Assert pattern for each test case.
 */
public class OutputFormat_isTrimTextTest {

    private OutputFormat format;

    /**
     * Sets up a new OutputFormat instance before each test method.
     */
    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    /**
     * Test case 1: Verify the default value of trimText.
     * Expects: false
     */
    @Test
    public void testDefaultTrimTextIsFalse() {
        // Arrange: format is initialized in setUp() with default constructor
        // Act
        boolean isTrim = format.isTrimText();
        // Assert
        assertFalse("Default value of trimText should be false", isTrim);
    }

    /**
     * Test case 2: Set trimText to true and verify.
     * Expects: true
     */
    @Test
    public void testSetTrimTextTrue() {
        // Arrange
        format.setTrimText(true);
        // Act
        boolean isTrim = format.isTrimText();
        // Assert
        assertTrue("trimText should be true after setting it to true", isTrim);
    }

    /**
     * Test case 3: Set trimText to false explicitly and verify.
     * Expects: false
     */
    @Test
    public void testSetTrimTextFalse() {
        // Arrange
        format.setTrimText(false); // Explicitly setting to the default value
        // Act
        boolean isTrim = format.isTrimText();
        // Assert
        assertFalse("trimText should be false after setting it to false", isTrim);
    }

    /**
     * Test case 4: Set trimText to true, then back to false, and verify.
     * Expects: false
     */
    @Test
    public void testSetTrimTextTrueThenFalse() {
        // Arrange
        format.setTrimText(true);
        format.setTrimText(false);
        // Act
        boolean isTrim = format.isTrimText();
        // Assert
        assertFalse("trimText should be false after setting true then false", isTrim);
    }

    /**
     * Test case 5: Set trimText to false, then to true, and verify.
     * Expects: true
     */
    @Test
    public void testSetTrimTextFalseThenTrue() {
        // Arrange
        format.setTrimText(false);
        format.setTrimText(true);
        // Act
        boolean isTrim = format.isTrimText();
        // Assert
        assertTrue("trimText should be true after setting false then true", isTrim);
    }

    /**
     * Test case 6: Verify trimText state after using the indent constructor.
     * Expects: false (constructors don't modify trimText default)
     */
    @Test
    public void testTrimTextWithIndentConstructor() {
        // Arrange
        OutputFormat indentFormat = new OutputFormat("  ");
        // Act
        boolean isTrim = indentFormat.isTrimText();
        // Assert
        assertFalse("trimText should remain default (false) when using indent constructor", isTrim);
    }

    /**
     * Test case 7: Verify trimText state after using the indent/newlines constructor.
     * Expects: false (constructors don't modify trimText default)
     */
    @Test
    public void testTrimTextWithIndentNewlinesConstructor() {
        // Arrange
        OutputFormat indentNewlinesFormat = new OutputFormat("  ", true);
        // Act
        boolean isTrim = indentNewlinesFormat.isTrimText();
        // Assert
        assertFalse("trimText should remain default (false) when using indent/newlines constructor", isTrim);
    }

    /**
     * Test case 8: Verify trimText state after using the indent/newlines/encoding constructor.
     * Expects: false (constructors don't modify trimText default)
     */
    @Test
    public void testTrimTextWithFullConstructor() {
        // Arrange
        OutputFormat fullFormat = new OutputFormat("  ", true, "ISO-8859-1");
        // Act
        boolean isTrim = fullFormat.isTrimText();
        // Assert
        assertFalse("trimText should remain default (false) when using full constructor", isTrim);
    }

    /**
     * Test case 9: Verify trimText state after using createPrettyPrint().
     * Expects: true
     */
    @Test
    public void testTrimTextWithCreatePrettyPrint() {
        // Arrange
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
        // Act
        boolean isTrim = prettyFormat.isTrimText();
        // Assert
        assertTrue("trimText should be true when using createPrettyPrint()", isTrim);
    }

    /**
     * Test case 10: Verify trimText state after using createCompactFormat().
     * Expects: true
     */
    @Test
    public void testTrimTextWithCreateCompactFormat() {
        // Arrange
        OutputFormat compactFormat = OutputFormat.createCompactFormat();
        // Act
        boolean isTrim = compactFormat.isTrimText();
        // Assert
        assertTrue("trimText should be true when using createCompactFormat()", isTrim);
    }
}
```