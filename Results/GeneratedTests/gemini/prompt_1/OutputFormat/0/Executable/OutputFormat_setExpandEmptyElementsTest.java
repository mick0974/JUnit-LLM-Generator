import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the OutputFormat#setExpandEmptyElements(boolean) method.
 * It follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class OutputFormat_setExpandEmptyElementsTest {

    private OutputFormat format;

    @Before
    public void setUp() {
        // Arrange: Create a new OutputFormat instance before each test
        format = new OutputFormat();
    }

    /**
     * Test case 1: Verify the default value of expandEmptyElements is false.
     */
    @Test
    public void testDefaultValueIsFalse() {
        // Arrange: format is already initialized in setUp() with default values

        // Act: No action needed, just checking the default state

        // Assert: Verify the default value is false
        assertFalse("Default value for expandEmptyElements should be false", format.isExpandEmptyElements());
    }

    /**
     * Test case 2: Set expandEmptyElements to true and verify the change.
     */
    @Test
    public void testSetExpandEmptyElementsToTrue() {
        // Arrange: format is initialized in setUp()

        // Act: Set expandEmptyElements to true
        format.setExpandEmptyElements(true);

        // Assert: Verify the value is now true
        assertTrue("expandEmptyElements should be true after setting to true", format.isExpandEmptyElements());
    }

    /**
     * Test case 3: Set expandEmptyElements to false explicitly and verify the change.
     * Although the default is false, this test ensures the setter works correctly for false.
     */
    @Test
    public void testSetExpandEmptyElementsToFalse() {
        // Arrange: format is initialized in setUp()
        // Optionally set it to true first to ensure the change to false works
        format.setExpandEmptyElements(true);
        assertTrue("Precondition failed: expandEmptyElements should be true", format.isExpandEmptyElements());


        // Act: Set expandEmptyElements to false
        format.setExpandEmptyElements(false);

        // Assert: Verify the value is now false
        assertFalse("expandEmptyElements should be false after setting to false", format.isExpandEmptyElements());
    }

    /**
     * Test case 4: Set expandEmptyElements to true, then back to false.
     */
    @Test
    public void testSetTrueThenFalse() {
        // Arrange: format is initialized in setUp()

        // Act: Set to true, then set to false
        format.setExpandEmptyElements(true);
        format.setExpandEmptyElements(false);

        // Assert: Verify the final value is false
        assertFalse("expandEmptyElements should be false after setting true then false", format.isExpandEmptyElements());
    }

    /**
     * Test case 5: Set expandEmptyElements to false, then back to true.
     */
    @Test
    public void testSetFalseThenTrue() {
         // Arrange: format is initialized in setUp() (default is false)

        // Act: Set to false (redundant but for clarity), then set to true
        format.setExpandEmptyElements(false);
        format.setExpandEmptyElements(true);

        // Assert: Verify the final value is true
        assertTrue("expandEmptyElements should be true after setting false then true", format.isExpandEmptyElements());
    }

    /**
     * Test case 6: Set expandEmptyElements to true multiple times.
     */
    @Test
    public void testSetTrueMultipleTimes() {
        // Arrange: format is initialized in setUp()

        // Act: Set to true multiple times
        format.setExpandEmptyElements(true);
        format.setExpandEmptyElements(true);
        format.setExpandEmptyElements(true);

        // Assert: Verify the value remains true
        assertTrue("expandEmptyElements should still be true after multiple sets to true", format.isExpandEmptyElements());
    }

    /**
     * Test case 7: Set expandEmptyElements to false multiple times.
     */
    @Test
    public void testSetFalseMultipleTimes() {
        // Arrange: format is initialized in setUp()
        // Start by setting it to true
        format.setExpandEmptyElements(true);

        // Act: Set to false multiple times
        format.setExpandEmptyElements(false);
        format.setExpandEmptyElements(false);
        format.setExpandEmptyElements(false);

        // Assert: Verify the value remains false
        assertFalse("expandEmptyElements should still be false after multiple sets to false", format.isExpandEmptyElements());
    }

    /**
     * Test case 8: Test setting on an instance created with the indent constructor.
     */
    @Test
    public void testSetOnInstanceWithIndentConstructor() {
        // Arrange: Create instance using a different constructor
        OutputFormat formatWithIndent = new OutputFormat("  ");
        assertFalse("Default expandEmptyElements should be false for indent constructor instance", formatWithIndent.isExpandEmptyElements());


        // Act: Set expandEmptyElements to true
        formatWithIndent.setExpandEmptyElements(true);

        // Assert: Verify the value is now true
        assertTrue("expandEmptyElements should be true after setting on indent constructor instance", formatWithIndent.isExpandEmptyElements());
    }

     /**
     * Test case 9: Test setting on an instance created with the indent/newlines constructor.
     */
    @Test
    public void testSetOnInstanceWithIndentNewlinesConstructor() {
        // Arrange: Create instance using another constructor
        OutputFormat formatWithIndentNL = new OutputFormat("  ", true);
        assertFalse("Default expandEmptyElements should be false for indent/newlines constructor instance", formatWithIndentNL.isExpandEmptyElements());

        // Act: Set expandEmptyElements to true
        formatWithIndentNL.setExpandEmptyElements(true);

        // Assert: Verify the value is now true
        assertTrue("expandEmptyElements should be true after setting on indent/newlines constructor instance", formatWithIndentNL.isExpandEmptyElements());
    }

     /**
     * Test case 10: Test setting on an instance created with the indent/newlines/encoding constructor.
     */
    @Test
    public void testSetOnInstanceWithFullConstructor() {
        // Arrange: Create instance using the full constructor
        OutputFormat formatFull = new OutputFormat("    ", false, "ISO-8859-1");
        assertFalse("Default expandEmptyElements should be false for full constructor instance", formatFull.isExpandEmptyElements());

        // Act: Set expandEmptyElements to true
        formatFull.setExpandEmptyElements(true);

        // Assert: Verify the value is now true
        assertTrue("expandEmptyElements should be true after setting on full constructor instance", formatFull.isExpandEmptyElements());
    }
}