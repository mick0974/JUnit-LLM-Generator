import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for OutputFormat#setTrimText(boolean).
 * This class contains unit tests focused specifically on the setTrimText method
 * of the OutputFormat class, ensuring it correctly updates the trimText property.
 * It verifies the default state, setting to true, setting to false, and state transitions.
 * Uses JUnit 4 annotations and assertions.
 */
public class OutputFormat_setTrimTextTest {

    private OutputFormat format;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Initializes a new OutputFormat object with default settings.
     */
    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     * Cleans up resources by setting the format object to null.
     */
    @After
    public void tearDown() {
        format = null;
    }

    /**
     * Test Case 1: Verify the default value of trimText.
     * Arrange: A new OutputFormat object is created in setUp().
     * Act: No action needed as we are testing the default state.
     * Assert: The default value of trimText should be false.
     */
    @Test
    public void testDefaultTrimTextIsFalse() {
        // Assert
        assertFalse("Default value of trimText should be false", format.isTrimText());
    }

    /**
     * Test Case 2: Set trimText to true from the default state (false).
     * Arrange: A default OutputFormat object (trimText=false).
     * Act: Call setTrimText(true).
     * Assert: isTrimText() should return true.
     */
    @Test
    public void testSetTrimTextTrueFromDefault() {
        // Act
        format.setTrimText(true);
        // Assert
        assertTrue("trimText should be true after setting to true", format.isTrimText());
    }

    /**
     * Test Case 3: Set trimText to false from the default state (false).
     * Arrange: A default OutputFormat object (trimText=false).
     * Act: Call setTrimText(false).
     * Assert: isTrimText() should return false.
     */
    @Test
    public void testSetTrimTextFalseFromDefault() {
        // Act
        format.setTrimText(false);
        // Assert
        assertFalse("trimText should remain false after explicitly setting to false", format.isTrimText());
    }

    /**
     * Test Case 4: Set trimText to true, then back to false.
     * Arrange: A default OutputFormat object.
     * Act: Call setTrimText(true), then call setTrimText(false).
     * Assert: isTrimText() should return false.
     */
    @Test
    public void testSetTrimTextTrueThenFalse() {
        // Arrange & Act 1
        format.setTrimText(true);
        assertTrue("Interim check: trimText should be true", format.isTrimText());

        // Act 2
        format.setTrimText(false);

        // Assert
        assertFalse("trimText should be false after being set to true then false", format.isTrimText());
    }

    /**
     * Test Case 5: Set trimText to false (explicitly), then to true.
     * Arrange: A default OutputFormat object.
     * Act: Call setTrimText(false), then call setTrimText(true).
     * Assert: isTrimText() should return true.
     */
    @Test
    public void testSetTrimTextFalseThenTrue() {
        // Arrange & Act 1
        format.setTrimText(false); // Explicitly set to default value
        assertFalse("Interim check: trimText should be false", format.isTrimText());

        // Act 2
        format.setTrimText(true);

        // Assert
        assertTrue("trimText should be true after being set to false then true", format.isTrimText());
    }

    /**
     * Test Case 6: Set trimText to true multiple times.
     * Arrange: A default OutputFormat object.
     * Act: Call setTrimText(true) twice.
     * Assert: isTrimText() should return true.
     */
    @Test
    public void testSetTrimTextTrueMultipleTimes() {
        // Act
        format.setTrimText(true);
        format.setTrimText(true); // Call again
        // Assert
        assertTrue("trimText should remain true after setting to true multiple times", format.isTrimText());
    }

    /**
     * Test Case 7: Set trimText to false multiple times after setting it true initially.
     * Arrange: A default OutputFormat object, set trimText to true.
     * Act: Call setTrimText(false) twice.
     * Assert: isTrimText() should return false.
     */
    @Test
    public void testSetTrimTextFalseMultipleTimesAfterTrue() {
        // Arrange
        format.setTrimText(true);
        assertTrue("Precondition check: trimText should be true", format.isTrimText());

        // Act
        format.setTrimText(false);
        format.setTrimText(false); // Call again
        // Assert
        assertFalse("trimText should remain false after setting to false multiple times", format.isTrimText());
    }

    /**
     * Test Case 8: Verify trimText state after using a constructor that doesn't set it.
     * Arrange: Create OutputFormat using constructor OutputFormat(String indent).
     * Act: Call setTrimText(true).
     * Assert: isTrimText() should return true, confirming independence from indent setting.
     */
    @Test
    public void testSetTrimTextTrueAfterIndentConstructor() {
        // Arrange
        format = new OutputFormat("  "); // trimText defaults to false
        assertFalse("Initial trimText should be false with indent constructor", format.isTrimText());
        
        // Act
        format.setTrimText(true);
        
        // Assert
        assertTrue("trimText should be true after setting on object from indent constructor", format.isTrimText());
    }

    /**
     * Test Case 9: Verify trimText state after using another constructor.
     * Arrange: Create OutputFormat using constructor OutputFormat(String indent, boolean newlines).
     * Act: Call setTrimText(true).
     * Assert: isTrimText() should return true, confirming independence from other settings.
     */
    @Test
    public void testSetTrimTextTrueAfterIndentNewlinesConstructor() {
        // Arrange
        format = new OutputFormat("  ", true); // trimText defaults to false
        assertFalse("Initial trimText should be false with indent/newlines constructor", format.isTrimText());
        
        // Act
        format.setTrimText(true);
        
        // Assert
        assertTrue("trimText should be true after setting on object from indent/newlines constructor", format.isTrimText());
    }
    
    /**
     * Test Case 10: Verify trimText state after using the full constructor.
     * Arrange: Create OutputFormat using constructor OutputFormat(String indent, boolean newlines, String encoding).
     * Act: Call setTrimText(true).
     * Assert: isTrimText() should return true, confirming independence from other settings.
     */
    @Test
    public void testSetTrimTextTrueAfterFullConstructor() {
        // Arrange
        format = new OutputFormat("  ", true, "UTF-16"); // trimText defaults to false
        assertFalse("Initial trimText should be false with full constructor", format.isTrimText());
        
        // Act
        format.setTrimText(true);
        
        // Assert
        assertTrue("trimText should be true after setting on object from full constructor", format.isTrimText());
    }
}