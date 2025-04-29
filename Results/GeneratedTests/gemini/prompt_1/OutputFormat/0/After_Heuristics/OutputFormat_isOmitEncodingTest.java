import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the isOmitEncoding() method of OutputFormat.
 * It verifies the behavior of the omitEncoding flag under various conditions.
 * Uses the Arrange-Act-Assert pattern.
 */
public class OutputFormat_isOmitEncodingTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        // Arrange: Create a new OutputFormat instance before each test
        format = new OutputFormat();
    }

    /**
     * Test case 1: Verify the default value of omitEncoding.
     * Expected: false
     */
    @Test
    public void testIsOmitEncodingDefault() {
        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("Default value for omitEncoding should be false", omitEncoding);
    }

    /**
     * Test case 2: Set omitEncoding to true and verify the getter.
     */
    @Test
    public void testIsOmitEncodingSetToTrue() {
        // Arrange
        format.setOmitEncoding(true);

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertTrue("isOmitEncoding should return true after setting to true", omitEncoding);
    }

    /**
     * Test case 3: Set omitEncoding to false and verify the getter.
     */
    @Test
    public void testIsOmitEncodingSetToFalse() {
        // Arrange
        format.setOmitEncoding(false); // Explicitly set to the default

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("isOmitEncoding should return false after setting to false", omitEncoding);
    }

    /**
     * Test case 4: Set omitEncoding to true, then back to false.
     */
    @Test
    public void testIsOmitEncodingSetTrueThenFalse() {
        // Arrange
        format.setOmitEncoding(true);
        format.setOmitEncoding(false);

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("isOmitEncoding should return false after setting true then false", omitEncoding);
    }

    /**
     * Test case 5: Set omitEncoding to false, then to true.
     */
    @Test
    public void testIsOmitEncodingSetFalseThenTrue() {
        // Arrange
        format.setOmitEncoding(false); // Set to default first
        format.setOmitEncoding(true);

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertTrue("isOmitEncoding should return true after setting false then true", omitEncoding);
    }

    /**
     * Test case 6: Verify omitEncoding using the constructor with indent.
     * (Constructor doesn't affect omitEncoding, but check initialization consistency)
     */
    @Test
    public void testIsOmitEncodingWithIndentConstructor() {
        // Arrange
        format = new OutputFormat("  ");

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("Default omitEncoding should be false even with indent constructor", omitEncoding);
    }

    /**
     * Test case 7: Verify omitEncoding using the constructor with indent and newlines.
     * (Constructor doesn't affect omitEncoding, but check initialization consistency)
     */
    @Test
    public void testIsOmitEncodingWithIndentNewlinesConstructor() {
        // Arrange
        format = new OutputFormat("  ", true);

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("Default omitEncoding should be false even with indent/newlines constructor", omitEncoding);
    }

    /**
     * Test case 8: Verify omitEncoding using the constructor with indent, newlines, and encoding.
     * (Constructor doesn't affect omitEncoding, but check initialization consistency)
     */
    @Test
    public void testIsOmitEncodingWithFullConstructor() {
        // Arrange
        format = new OutputFormat("\t", false, "ISO-8859-1");

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("Default omitEncoding should be false even with full constructor", omitEncoding);
    }

    /**
     * Test case 9: Verify omitEncoding state after using createPrettyPrint factory method.
     */
    @Test
    public void testIsOmitEncodingWithPrettyPrint() {
        // Arrange
        format = OutputFormat.createPrettyPrint();

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("omitEncoding should be false for pretty print format", omitEncoding);
    }

    /**
     * Test case 10: Verify omitEncoding state after using createCompactFormat factory method.
     */
    @Test
    public void testIsOmitEncodingWithCompactFormat() {
        // Arrange
        format = OutputFormat.createCompactFormat();

        // Act
        boolean omitEncoding = format.isOmitEncoding();

        // Assert
        assertFalse("omitEncoding should be false for compact format", omitEncoding);
    }
}