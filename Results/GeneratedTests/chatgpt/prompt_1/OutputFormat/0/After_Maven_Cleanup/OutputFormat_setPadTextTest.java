// OutputFormat_setPadTextTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setPadText(boolean padText) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setPadTextTest {

private OutputFormat outputFormat;

    @Before
    public void setUp() {
        // Arrange: Create a fresh instance of OutputFormat before each test.
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        // Optionally: Cleanup resources if needed after each test.
        outputFormat = null;
    }

    @Test
    public void testPadTextDefaultFalse() {
        // Act & Assert: Verify default is false
        assertFalse(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextTrue() {
        // Act: Set padText to true.
        outputFormat.setPadText(true);
        
        // Assert: Verify it was set correctly.
        assertTrue(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextFalseAfterBeingTrue() {
        // Arrange: Start by setting PadText to true.
        outputFormat.setPadText(true);

        // Act: Set padText to false.
        outputFormat.setPadText(false);
        
        // Assert: Verify it was set correctly.
        assertFalse(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextTrueTwice() {
        // Arrange: Set PadText to true.
        outputFormat.setPadText(true);

        // Act: Set PadText to true again.
        outputFormat.setPadText(true);
        
        // Assert: It should remain true.
        assertTrue(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextFalseTwice() {
        // Arrange: Ensure PadText is false.
        outputFormat.setPadText(false);
        
        // Act: Attempt to set PadText to false again.
        outputFormat.setPadText(false);

        // Assert: It should remain false.
        assertFalse(outputFormat.isPadText());
    }
    
    @Test
    public void testTogglePadTextMultipleTimes() {
        // Act: Toggle padText multiple times.
        outputFormat.setPadText(true);
        outputFormat.setPadText(false);
        outputFormat.setPadText(true);
        outputFormat.setPadText(false);

        // Assert: Final state should be false.
        assertFalse(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextTrueWithIndentation() {
        // Arrange: Set indent.
        outputFormat.setIndent("    ");

        // Act: Set padText to true.
        outputFormat.setPadText(true);

        // Assert: Verify padText is true regardless of indentation setting.
        assertTrue(outputFormat.isPadText());
    }
    
    @Test
    public void testSetPadTextFalseWithIndentation() {
        // Arrange: Set indent.
        outputFormat.setIndent("    ");
        
        // Act: Set padText to false.
        outputFormat.setPadText(false);
        
        // Assert: Verify padText is false regardless of indentation setting.
        assertFalse(outputFormat.isPadText());
    }
    
    @Test
    public void testSetPadTextDoesNotAffectIndentation() {
        // Arrange: Set a specific indent.
        String initialIndent = "    ";
        outputFormat.setIndent(initialIndent);
        
        // Act: Toggle padText.
        outputFormat.setPadText(true);
        
        // Assert: Indentation should remain unchanged.
        assertEquals(initialIndent, outputFormat.getIndent());
    }
    
    @Test
    public void testSetPadTextTrueWithNewLines() {
        // Arrange: Enable newlines.
        outputFormat.setNewlines(true);

        // Act: Set padText to true.
        outputFormat.setPadText(true);
        
        // Assert: Verify padText is true regardless of newlines setting.
        assertTrue(outputFormat.isPadText());
    }
}