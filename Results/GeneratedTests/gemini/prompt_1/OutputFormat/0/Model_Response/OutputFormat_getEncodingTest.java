```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the getEncoding() method of OutputFormat.
 * It focuses on verifying the retrieval of the encoding setting under various conditions.
 * Uses JUnit 4 framework and follows the Arrange-Act-Assert pattern.
 */
public class OutputFormat_getEncodingTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        // Default setup - create a new OutputFormat instance before each test
        format = new OutputFormat();
    }

    /**
     * Test case 1: Verify the default encoding is "UTF-8" when using the default constructor.
     */
    @Test
    public void testGetEncoding_DefaultConstructor() {
        // Arrange: format is initialized in setUp() with default constructor

        // Act
        String encoding = format.getEncoding();

        // Assert
        assertEquals("Default encoding should be UTF-8", "UTF-8", encoding);
    }

    /**
     * Test case 2: Verify the encoding set via the constructor with encoding parameter.
     */
    @Test
    public void testGetEncoding_ConstructorWithEncoding() {
        // Arrange
        String expectedEncoding = "ISO-8859-1";
        OutputFormat customFormat = new OutputFormat("  ", true, expectedEncoding);

        // Act
        String actualEncoding = customFormat.getEncoding();

        // Assert
        assertEquals("Encoding set via constructor should be retrieved correctly", expectedEncoding, actualEncoding);
    }

    /**
     * Test case 3: Verify the encoding after setting it using the setEncoding method.
     */
    @Test
    public void testGetEncoding_AfterSetEncoding() {
        // Arrange
        String expectedEncoding = "UTF-16";
        format.setEncoding(expectedEncoding);

        // Act
        String actualEncoding = format.getEncoding();

        // Assert
        assertEquals("Encoding set via setter should be retrieved correctly", expectedEncoding, actualEncoding);
    }

    /**
     * Test case 4: Verify that setting a null encoding does not change the existing encoding (default).
     */
    @Test
    public void testGetEncoding_AfterSetNullEncoding_Default() {
        // Arrange
        String initialEncoding = format.getEncoding(); // Should be default "UTF-8"
        format.setEncoding(null); // Attempt to set null

        // Act
        String actualEncoding = format.getEncoding();

        // Assert
        assertEquals("Setting null encoding should not change the default encoding", initialEncoding, actualEncoding);
        assertEquals("Encoding should still be the default UTF-8", "UTF-8", actualEncoding);
    }

    /**
     * Test case 5: Verify that setting a null encoding does not change a previously set, non-default encoding.
     */
    @Test
    public void testGetEncoding_AfterSetNullEncoding_Custom() {
        // Arrange
        String customEncoding = "ASCII";
        format.setEncoding(customEncoding); // Set a custom encoding first
        String initialEncoding = format.getEncoding(); // Store the custom encoding
        assertEquals("Pre-condition failed: Custom encoding not set", customEncoding, initialEncoding);

        format.setEncoding(null); // Attempt to set null

        // Act
        String actualEncoding = format.getEncoding();

        // Assert
        assertEquals("Setting null encoding should not change a previously set custom encoding", initialEncoding, actualEncoding);
        assertEquals("Encoding should remain the custom set ASCII", customEncoding, actualEncoding);
    }

    /**
     * Test case 6: Verify setting and getting an empty string as encoding.
     * Although unusual, the current implementation allows it.
     */
    @Test
    public void testGetEncoding_AfterSetEmptyEncoding() {
        // Arrange
        String emptyEncoding = "";
        format.setEncoding(emptyEncoding);

        // Act
        String actualEncoding = format.getEncoding();

        // Assert
        assertEquals("Setting an empty string encoding should be reflected", emptyEncoding, actualEncoding);
    }

    /**
     * Test case 7: Verify the encoding for OutputFormat created using createPrettyPrint().
     * It should use the default encoding.
     */
    @Test
    public void testGetEncoding_PrettyPrintFormat() {
        // Arrange
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();

        // Act
        String actualEncoding = prettyFormat.getEncoding();

        // Assert
        assertEquals("PrettyPrint format should use default encoding UTF-8", "UTF-8", actualEncoding);
    }

    /**
     * Test case 8: Verify the encoding for OutputFormat created using createCompactFormat().
     * It should use the default encoding.
     */
    @Test
    public void testGetEncoding_CompactFormat() {
        // Arrange
        OutputFormat compactFormat = OutputFormat.createCompactFormat();

        // Act
        String actualEncoding = compactFormat.getEncoding();

        // Assert
        assertEquals("CompactFormat should use default encoding UTF-8", "UTF-8", actualEncoding);
    }

    /**
     * Test case 9: Verify encoding remains default when using constructor without encoding param (indent only).
     */
    @Test
    public void testGetEncoding_ConstructorWithIndentOnly() {
        // Arrange
        OutputFormat indentFormat = new OutputFormat("    ");

        // Act
        String actualEncoding = indentFormat.getEncoding();

        // Assert
        assertEquals("Constructor with indent only should use default encoding UTF-8", "UTF-8", actualEncoding);
    }

     /**
     * Test case 10: Verify encoding remains default when using constructor without encoding param (indent and newlines).
     */
    @Test
    public void testGetEncoding_ConstructorWithIndentAndNewlines() {
        // Arrange
        OutputFormat indentNewlinesFormat = new OutputFormat("\t", true);

        // Act
        String actualEncoding = indentNewlinesFormat.getEncoding();

        // Assert
        assertEquals("Constructor with indent and newlines should use default encoding UTF-8", "UTF-8", actualEncoding);
    }

}
```