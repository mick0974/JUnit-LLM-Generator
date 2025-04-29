import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the OutputFormat#isSuppressDeclaration() method.
 * This class focuses specifically on testing the getter and related setter
 * for the suppressDeclaration property.
 * It follows the Arrange-Act-Assert pattern.
 */
public class OutputFormat_isSuppressDeclarationTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        // Optional: Initialize a common object if needed,
        // but for simple getter tests, creating fresh objects in tests is often clearer.
        // format = new OutputFormat();
    }

    @After
    public void tearDown() {
        // Optional: Clean up resources if needed.
        format = null;
    }

    /**
     * Test case 1: Default value should be false.
     * Tests the initial state of suppressDeclaration after using the default constructor.
     */
    @Test
    public void testIsSuppressDeclaration_DefaultConstructor_ShouldBeFalse() {
        // Arrange
        format = new OutputFormat();

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false", result);
    }

    /**
     * Test case 2: Set suppressDeclaration to true.
     * Tests setting the flag to true using the setter and verifying with the getter.
     */
    @Test
    public void testIsSuppressDeclaration_SetToTrue_ShouldReturnTrue() {
        // Arrange
        format = new OutputFormat();
        format.setSuppressDeclaration(true);

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertTrue("isSuppressDeclaration should return true after setting to true", result);
    }

    /**
     * Test case 3: Set suppressDeclaration to false explicitly.
     * Tests setting the flag explicitly to false (its default value) and verifying with the getter.
     */
    @Test
    public void testIsSuppressDeclaration_SetToFalse_ShouldReturnFalse() {
        // Arrange
        format = new OutputFormat();
        // Explicitly set to false, even though it's the default
        format.setSuppressDeclaration(false);

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("isSuppressDeclaration should return false after setting to false", result);
    }

    /**
     * Test case 4: Set suppressDeclaration to true then back to false.
     * Tests toggling the value and ensuring the getter reflects the final state.
     */
    @Test
    public void testIsSuppressDeclaration_SetTrueThenFalse_ShouldReturnFalse() {
        // Arrange
        format = new OutputFormat();
        format.setSuppressDeclaration(true); // Set to true first
        format.setSuppressDeclaration(false); // Then set back to false

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("isSuppressDeclaration should return false after being set true then false", result);
    }

    /**
     * Test case 5: Check default value with indent constructor.
     * Verifies that the suppressDeclaration default remains false when using the constructor with indent.
     */
    @Test
    public void testIsSuppressDeclaration_IndentConstructor_ShouldBeFalse() {
        // Arrange
        format = new OutputFormat("  ");

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false with indent constructor", result);
    }

    /**
     * Test case 6: Check default value with indent and newlines constructor.
     * Verifies that the suppressDeclaration default remains false when using the constructor with indent and newlines.
     */
    @Test
    public void testIsSuppressDeclaration_IndentNewlinesConstructor_ShouldBeFalse() {
        // Arrange
        format = new OutputFormat("  ", true);

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false with indent/newlines constructor", result);
    }

    /**
     * Test case 7: Check default value with indent, newlines, and encoding constructor.
     * Verifies that the suppressDeclaration default remains false when using the full constructor.
     */
    @Test
    public void testIsSuppressDeclaration_FullConstructor_ShouldBeFalse() {
        // Arrange
        format = new OutputFormat("  ", true, "ISO-8859-1");

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false with full constructor", result);
    }

    /**
     * Test case 8: Set suppressDeclaration via parseOptions.
     * Tests if the parseOptions method correctly sets the suppressDeclaration flag.
     */
    @Test
    public void testIsSuppressDeclaration_SetViaParseOptions_ShouldReturnTrue() {
        // Arrange
        format = new OutputFormat();
        String[] args = {"-suppressDeclaration"};

        // Act
        format.parseOptions(args, 0);
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertTrue("isSuppressDeclaration should return true after parsing '-suppressDeclaration'", result);
    }

     /**
     * Test case 9: Check default value with createPrettyPrint factory method.
     * Verifies that the suppressDeclaration default remains false when using the pretty print factory.
     */
    @Test
    public void testIsSuppressDeclaration_CreatePrettyPrint_ShouldBeFalse() {
        // Arrange
        format = OutputFormat.createPrettyPrint();

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false for createPrettyPrint", result);
    }

    /**
     * Test case 10: Check default value with createCompactFormat factory method.
     * Verifies that the suppressDeclaration default remains false when using the compact format factory.
     */
    @Test
    public void testIsSuppressDeclaration_CreateCompactFormat_ShouldBeFalse() {
        // Arrange
        format = OutputFormat.createCompactFormat();

        // Act
        boolean result = format.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false for createCompactFormat", result);
    }
}