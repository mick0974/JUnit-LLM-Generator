import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the OutputFormat#setNewlines(boolean newlines) method.
 * It contains unit tests covering typical and edge cases for setting the newlines property.
 * Uses the Arrange-Act-Assert pattern.
 */
public class OutputFormat_setNewlinesTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        // Arrange: Create a default OutputFormat instance before each test
        format = new OutputFormat();
    }

    /**
     * Test case 1: Setting newlines to true when the default is false.
     */
    @Test
    public void testSetNewlinesTrueOnDefault() {
        // Arrange: Default format has newlines = false
        assertFalse("Initial state should be false", format.isNewlines());

        // Act: Set newlines to true
        format.setNewlines(true);

        // Assert: Verify newlines is now true
        assertTrue("Newlines should be true after setting to true", format.isNewlines());
    }

    /**
     * Test case 2: Setting newlines to false when the default is false.
     * Verifies that setting it to its current value has no adverse effect.
     */
    @Test
    public void testSetNewlinesFalseOnDefault() {
        // Arrange: Default format has newlines = false
        assertFalse("Initial state should be false", format.isNewlines());

        // Act: Set newlines to false
        format.setNewlines(false);

        // Assert: Verify newlines remains false
        assertFalse("Newlines should remain false after setting to false", format.isNewlines());
    }

    /**
     * Test case 3: Setting newlines to false after it was explicitly set to true.
     */
    @Test
    public void testSetNewlinesFalseAfterTrue() {
        // Arrange: Set initial state to true
        format.setNewlines(true);
        assertTrue("Initial state should be true", format.isNewlines());

        // Act: Set newlines to false
        format.setNewlines(false);

        // Assert: Verify newlines is now false
        assertFalse("Newlines should be false after setting to false", format.isNewlines());
    }

    /**
     * Test case 4: Setting newlines to true after it was explicitly set to false (which is the default).
     * This is similar to testSetNewlinesTrueOnDefault but ensures the transition works.
     */
    @Test
    public void testSetNewlinesTrueAfterFalse() {
        // Arrange: Explicitly set initial state to false (default)
        format.setNewlines(false);
        assertFalse("Initial state should be false", format.isNewlines());

        // Act: Set newlines to true
        format.setNewlines(true);

        // Assert: Verify newlines is now true
        assertTrue("Newlines should be true after setting to true", format.isNewlines());
    }

    /**
     * Test case 5: Using the constructor that sets newlines to true initially.
     * Then set it to false.
     */
    @Test
    public void testConstructorSetsTrueThenSetFalse() {
        // Arrange: Create format with newlines initially true
        OutputFormat formatWithTrue = new OutputFormat("  ", true);
        assertTrue("Constructor should set newlines to true", formatWithTrue.isNewlines());

        // Act: Set newlines to false
        formatWithTrue.setNewlines(false);

        // Assert: Verify newlines is now false
        assertFalse("Newlines should be false after setting to false", formatWithTrue.isNewlines());
    }

    /**
     * Test case 6: Using the constructor that sets newlines to false initially.
     * Then set it to true.
     */
    @Test
    public void testConstructorSetsFalseThenSetTrue() {
        // Arrange: Create format with newlines initially false
        OutputFormat formatWithFalse = new OutputFormat("  ", false);
        assertFalse("Constructor should set newlines to false", formatWithFalse.isNewlines());

        // Act: Set newlines to true
        formatWithFalse.setNewlines(true);

        // Assert: Verify newlines is now true
        assertTrue("Newlines should be true after setting to true", formatWithFalse.isNewlines());
    }

    /**
     * Test case 7: Setting newlines to true when it was already true (via constructor).
     */
    @Test
    public void testSetNewlinesTrueWhenAlreadyTrue() {
        // Arrange: Create format with newlines initially true
        OutputFormat formatWithTrue = new OutputFormat("  ", true);
        assertTrue("Initial state should be true", formatWithTrue.isNewlines());

        // Act: Set newlines to true again
        formatWithTrue.setNewlines(true);

        // Assert: Verify newlines remains true
        assertTrue("Newlines should remain true after setting to true again", formatWithTrue.isNewlines());
    }

    /**
     * Test case 8: Setting newlines to false when it was already false (via constructor).
     */
    @Test
    public void testSetNewlinesFalseWhenAlreadyFalse() {
         // Arrange: Create format with newlines initially false
        OutputFormat formatWithFalse = new OutputFormat("  ", false);
        assertFalse("Initial state should be false", formatWithFalse.isNewlines());

        // Act: Set newlines to false again
        formatWithFalse.setNewlines(false);

        // Assert: Verify newlines remains false
        assertFalse("Newlines should remain false after setting to false again", formatWithFalse.isNewlines());
    }

     /**
     * Test case 9: Verify the default value in the default constructor is false.
     */
    @Test
    public void testDefaultConstructorDefaultIsFalse() {
        // Arrange: Use the format created in setUp()
        // Act: No action needed, just check the initial state.
        // Assert: Verify the default is false
        assertFalse("Default newlines value should be false", format.isNewlines());
    }

    /**
     * Test case 10: Verify the default value in the constructor with only indent is false.
     */
    @Test
    public void testIndentOnlyConstructorDefaultIsFalse() {
        // Arrange: Create format with only indent specified
        OutputFormat formatIndentOnly = new OutputFormat("    ");

        // Act: No action needed, just check the initial state.
        // Assert: Verify the default is false
        assertFalse("Default newlines value should be false for indent-only constructor", formatIndentOnly.isNewlines());

        // Act 2: Set to true to ensure it works on this instance type
        formatIndentOnly.setNewlines(true);
         // Assert 2: Verify it changed
        assertTrue("Setting newlines to true should work", formatIndentOnly.isNewlines());
    }
}