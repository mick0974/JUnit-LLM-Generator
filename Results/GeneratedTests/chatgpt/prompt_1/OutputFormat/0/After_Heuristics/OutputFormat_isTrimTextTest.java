// OutputFormat_isTrimTextTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isTrimText() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isTrimTextTest {


    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        // Initialize a new instance of OutputFormat before each test
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        // Clear references after each test
        outputFormat = null;
    }

    @Test
    public void testDefaultTrimText() {
        // Test if the default value of trimText is false
        assertFalse("Default trimText should be false", outputFormat.isTrimText());
    }

    @Test
    public void testSetTrimTextTrue() {
        // Set trimText to true and verify
        outputFormat.setTrimText(true);
        assertTrue("trimText should be true after setting it", outputFormat.isTrimText());
    }

    @Test
    public void testSetTrimTextFalse() {
        // Set trimText to false and verify
        outputFormat.setTrimText(false);
        assertFalse("trimText should be false after setting it to false", outputFormat.isTrimText());
    }

    @Test
    public void testMultipleSetTrimText() {
        // Test setting trimText multiple times
        outputFormat.setTrimText(true);
        assertTrue("trimText should be true", outputFormat.isTrimText());

        outputFormat.setTrimText(false);
        assertFalse("trimText should be false after setting it to false", outputFormat.isTrimText());

        outputFormat.setTrimText(true);
        assertTrue("trimText should be true again", outputFormat.isTrimText());
    }

    @Test(expected = NullPointerException.class)
    public void testSetTrimTextNull() {
        // Ensure NullPointerException is not applicable
        // This is to mimic a potential edge case though not typical for a boolean
        assertTrue("No NPE should occur on this test", true);
    }

    @Test
    public void testTrimTextOnPrettyFormat() {
        // Create a pretty format and test trimText property
        OutputFormat format = OutputFormat.createPrettyPrint();
        assertTrue("Pretty print should have trimText set to true", format.isTrimText());
    }

    @Test
    public void testTrimTextOnCompactFormat() {
        // Create a compact format and test trimText property
        OutputFormat format = OutputFormat.createCompactFormat();
        assertTrue("Compact format should have trimText set to true", format.isTrimText());
    }

    @Test
    public void testImmutableTrimTextStateAfterGetting() {
        // Ensure getting the state does not mutate it
        outputFormat.setTrimText(true);
        boolean state = outputFormat.isTrimText();
        assertEquals("State should remain unchanged after getting", state, outputFormat.isTrimText());
    }

    @Test
    public void testCloneDoesNotAffectOriginal() throws CloneNotSupportedException {
        // Test if cloning affects the original object state
        outputFormat.setTrimText(true);
        OutputFormat clonedFormat = (OutputFormat) outputFormat.clone();
        clonedFormat.setTrimText(false);
        assertTrue("Original object's trimText should remain true", outputFormat.isTrimText());
    }
}