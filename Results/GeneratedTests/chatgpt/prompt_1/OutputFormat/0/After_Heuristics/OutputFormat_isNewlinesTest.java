// OutputFormat_isNewlinesTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isNewlines() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isNewlinesTest {


    private OutputFormat outputFormat;

    @Before
    public void setUp() throws Exception {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() throws Exception {
        outputFormat = null;
    }

    @Test
    public void testDefaultIsNewlines() {
        // Arrange & Act
        boolean result = outputFormat.isNewlines();

        // Assert
        assertTrue("By default, newlines should be true", result);
    }

    @Test
    public void testSetNewlinesToTrue() {
        // Arrange
        outputFormat.setNewlines(true);

        // Act
        boolean result = outputFormat.isNewlines();

        // Assert
        assertTrue("Newlines should be true after being set to true", result);
    }

    @Test
    public void testSetNewlinesToFalse() {
        // Arrange
        outputFormat.setNewlines(false);

        // Act
        boolean result = outputFormat.isNewlines();

        // Assert
        assertFalse("Newlines should be false after being set to false", result);
    }

    @Test
    public void testSetNewlinesMultipleTimes() {
        // Arrange
        outputFormat.setNewlines(false);
        outputFormat.setNewlines(true);

        // Act
        boolean result = outputFormat.isNewlines();

        // Assert
        assertTrue("Newlines should be true after being set to true", result);
    }

    @Test
    public void testCreatePrettyPrintInheritsNewlinesTrue() {
        // Arrange & Act
        OutputFormat format = OutputFormat.createPrettyPrint();

        // Assert
        assertTrue("Pretty print format should have newlines set to true", format.isNewlines());
    }

    @Test
    public void testCreateCompactFormatInheritsNewlinesFalse() {
        // Arrange & Act
        OutputFormat format = OutputFormat.createCompactFormat();

        // Assert
        assertFalse("Compact format should have newlines set to false", format.isNewlines());
    }

    @Test
    public void testIsNewlinesWhenIndentWithNewlinesConstructor() {
        // Arrange
        OutputFormat format = new OutputFormat("  ", true);

        // Act
        boolean result = format.isNewlines();

        // Assert
        assertTrue("Newlines should be true when set via constructor", result);
    }

    @Test
    public void testIsNewlinesWhenIndentWithoutNewlinesConstructor() {
        // Arrange
        OutputFormat format = new OutputFormat("  ", false);

        // Act
        boolean result = format.isNewlines();

        // Assert
        assertFalse("Newlines should be false when set via false constructor", result);
    }

    @Test
    public void testToggleNewlines() {
        // Arrange
        outputFormat.setNewlines(false);
        outputFormat.setNewlines(true);
        outputFormat.setNewlines(false);

        // Act
        boolean result = outputFormat.isNewlines();

        // Assert
        assertFalse("Newlines should be false after being toggled off", result);
    }

    @Test(expected = IllegalStateException.class)
    public void testUnsetLineSeparatorWhenNewlinesFalseThrowsException() {
        // Arrange
        outputFormat.setNewlines(false);
        
        // Act
        outputFormat.setLineSeparator(null); // Should trigger an error if used incorrectly
    }
}