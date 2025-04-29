import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the isNewlines() method of the OutputFormat class.
 * It contains unit tests covering various scenarios, including default states,
 * constructor initializations, and explicit setting via the setter method.
 * Each test follows the Arrange-Act-Assert pattern.
 */
public class OutputFormat_isNewlinesTest {

    private OutputFormat format;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        // Arrange: Initialize a default OutputFormat object
        format = new OutputFormat();
    }

    /**
     * Tests the default value of the newlines property using the default constructor.
     * Expects the default value to be false.
     */
    @Test
    public void testIsNewlinesDefaultConstructor() {
        // Act: Get the value using isNewlines()
        boolean newlines = format.isNewlines();

        // Assert: Verify the default value is false
        assertFalse("Default constructor should set newlines to false", newlines);
    }

    /**
     * Tests the newlines property when initialized using the constructor with indent only.
     * Expects the newlines value to remain the default (false).
     */
    @Test
    public void testIsNewlinesConstructorWithIndent() {
        // Arrange: Create OutputFormat with indent specified
        OutputFormat formatWithIndent = new OutputFormat("  ");

        // Act: Get the value using isNewlines()
        boolean newlines = formatWithIndent.isNewlines();

        // Assert: Verify the value is still the default false
        assertFalse("Constructor with indent only should default newlines to false", newlines);
    }

    /**
     * Tests the newlines property when explicitly set to true using the constructor.
     */
    @Test
    public void testIsNewlinesConstructorSetTrue() {
        // Arrange: Create OutputFormat with newlines set to true
        OutputFormat formatWithNewlines = new OutputFormat("  ", true);

        // Act: Get the value using isNewlines()
        boolean newlines = formatWithNewlines.isNewlines();

        // Assert: Verify the value is true
        assertTrue("Constructor should set newlines to true when specified", newlines);
    }

    /**
     * Tests the newlines property when explicitly set to false using the constructor.
     */
    @Test
    public void testIsNewlinesConstructorSetFalse() {
        // Arrange: Create OutputFormat with newlines set to false
        OutputFormat formatWithoutNewlines = new OutputFormat("  ", false);

        // Act: Get the value using isNewlines()
        boolean newlines = formatWithoutNewlines.isNewlines();

        // Assert: Verify the value is false
        assertFalse("Constructor should set newlines to false when specified", newlines);
    }

    /**
     * Tests the newlines property when initialized using the constructor with indent, newlines, and encoding,
     * with newlines set to true.
     */
    @Test
    public void testIsNewlinesFullConstructorSetTrue() {
        // Arrange: Create OutputFormat with newlines set to true via full constructor
        OutputFormat fullFormat = new OutputFormat("  ", true, "UTF-16");

        // Act: Get the value using isNewlines()
        boolean newlines = fullFormat.isNewlines();

        // Assert: Verify the value is true
        assertTrue("Full constructor should set newlines to true when specified", newlines);
    }

    /**
     * Tests the newlines property when initialized using the constructor with indent, newlines, and encoding,
     * with newlines set to false.
     */
    @Test
    public void testIsNewlinesFullConstructorSetFalse() {
        // Arrange: Create OutputFormat with newlines set to false via full constructor
        OutputFormat fullFormat = new OutputFormat("  ", false, "UTF-16");

        // Act: Get the value using isNewlines()
        boolean newlines = fullFormat.isNewlines();

        // Assert: Verify the value is false
        assertFalse("Full constructor should set newlines to false when specified", newlines);
    }

    /**
     * Tests setting the newlines property to true using the setNewlines method.
     */
    @Test
    public void testSetNewlinesTrue() {
        // Arrange: Initial state is false (from setUp)

        // Act: Set newlines to true
        format.setNewlines(true);
        boolean newlines = format.isNewlines();

        // Assert: Verify the value is now true
        assertTrue("setNewlines(true) should update the property to true", newlines);
    }

    /**
     * Tests setting the newlines property to false using the setNewlines method
     * after it was initially true.
     */
    @Test
    public void testSetNewlinesFalse() {
        // Arrange: Set initial state to true
        format.setNewlines(true);
        assertTrue("Precondition failed: newlines should be true", format.isNewlines());


        // Act: Set newlines back to false
        format.setNewlines(false);
        boolean newlines = format.isNewlines();

        // Assert: Verify the value is now false
        assertFalse("setNewlines(false) should update the property to false", newlines);
    }

    /**
     * Tests the newlines property value returned by the createPrettyPrint static factory method.
     * Expects it to be true.
     */
    @Test
    public void testIsNewlinesForPrettyPrint() {
        // Arrange: Create OutputFormat using createPrettyPrint()
        OutputFormat prettyFormat = OutputFormat.createPrettyPrint();

        // Act: Get the value using isNewlines()
        boolean newlines = prettyFormat.isNewlines();

        // Assert: Verify the value is true
        assertTrue("createPrettyPrint() should set newlines to true", newlines);
    }

    /**
     * Tests the newlines property value returned by the createCompactFormat static factory method.
     * Expects it to be false.
     */
    @Test
    public void testIsNewlinesForCompactFormat() {
        // Arrange: Create OutputFormat using createCompactFormat()
        OutputFormat compactFormat = OutputFormat.createCompactFormat();

        // Act: Get the value using isNewlines()
        boolean newlines = compactFormat.isNewlines();

        // Assert: Verify the value is false
        assertFalse("createCompactFormat() should set newlines to false", newlines);
    }
}