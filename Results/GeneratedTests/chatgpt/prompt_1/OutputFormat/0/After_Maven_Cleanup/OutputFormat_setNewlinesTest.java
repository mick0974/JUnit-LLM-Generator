// OutputFormat_setNewlinesTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setNewlines(boolean newlines) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setNewlinesTest {

private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testSetNewlinesTrue() {
        // Arrange
        boolean newlines = true;

        // Act
        outputFormat.setNewlines(newlines);

        // Assert
        assertTrue(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesFalse() {
        // Arrange
        boolean newlines = false;

        // Act
        outputFormat.setNewlines(newlines);

        // Assert
        assertFalse(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesDefaultsToFalse() {
        // Assert
        assertFalse(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesWithTrueTwice() {
        // Arrange
        boolean newlines = true;

        // Act
        outputFormat.setNewlines(newlines);
        outputFormat.setNewlines(newlines);

        // Assert
        assertTrue(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesWithFalseTwice() {
        // Arrange
        boolean newlines = false;

        // Act
        outputFormat.setNewlines(newlines);
        outputFormat.setNewlines(newlines);

        // Assert
        assertFalse(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesSwitchFromTrueToFalse() {
        // Arrange
        boolean newlinesTrue = true;
        boolean newlinesFalse = false;

        // Act
        outputFormat.setNewlines(newlinesTrue);
        outputFormat.setNewlines(newlinesFalse);

        // Assert
        assertFalse(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesSwitchFromFalseToTrue() {
        // Arrange
        boolean newlinesFalse = false;
        boolean newlinesTrue = true;

        // Act
        outputFormat.setNewlines(newlinesFalse);
        outputFormat.setNewlines(newlinesTrue);

        // Assert
        assertTrue(outputFormat.isNewlines());
    }

    @Test
    public void testSetNewlinesAfterIndent() {
        // Arrange
        String indent = "  ";
        outputFormat.setIndent(indent);
        boolean newlines = true;

        // Act
        outputFormat.setNewlines(newlines);

        // Assert
        assertTrue(outputFormat.isNewlines());
        assertEquals(indent, outputFormat.getIndent());
    }

    @Test
    public void testSetNewlinesIntegrationWithLineSeparator() {
        // Arrange
        String separator = "\r\n";
        outputFormat.setLineSeparator(separator);
        boolean newlines = true;

        // Act
        outputFormat.setNewlines(newlines);

        // Assert
        assertTrue(outputFormat.isNewlines());
        assertEquals(separator, outputFormat.getLineSeparator());
    }

    @Test
    public void testSetNewlinesWhenDefaultLineSeparator() {
        // Arrange
        String defaultSeparator = "\n";

        // Act
        outputFormat.setNewlines(true);

        // Assert
        assertTrue(outputFormat.isNewlines());
        assertEquals(defaultSeparator, outputFormat.getLineSeparator());
    }
}