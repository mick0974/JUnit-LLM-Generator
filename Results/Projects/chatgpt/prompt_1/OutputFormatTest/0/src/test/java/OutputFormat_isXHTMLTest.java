// OutputFormat_isXHTMLTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isXHTML() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isXHTMLTest {


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
    public void testDefaultXHTMLSetting() {
        // Arrange is done in setUp()

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertFalse("XHTML should be false by default", result);
    }

    @Test
    public void testSettingXHTMLToTrue() {
        // Arrange
        outputFormat.setXHTML(true);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertTrue("XHTML should be true after setting", result);
    }

    @Test
    public void testSettingXHTMLToFalse() {
        // Arrange
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertFalse("XHTML should be false after setting to false", result);
    }

    @Test
    public void testToggleXHTMLMultipleTimes() {
        // Arrange
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(true);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertTrue("XHTML should reflect the latest true setting", result);
    }

    @Test
    public void testToggleXHTMLMultipleTimesEndingInFalse() {
        // Arrange
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertFalse("XHTML should reflect the latest false setting", result);
    }

    @Test
    public void testSetXHTMLTrueTwice() {
        // Arrange
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(true);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertTrue("XHTML should be true even if set to true twice", result);
    }

    @Test
    public void testSetXHTMLFalseTwice() {
        // Arrange
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(false);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertFalse("XHTML should be false even if set to false twice", result);
    }

    @Test
    public void testXHTMLStatePersistence() {
        // Arrange
        outputFormat.setXHTML(true);

        // Act
        boolean firstCheck = outputFormat.isXHTML();
        boolean secondCheck = outputFormat.isXHTML();

        // Assert
        assertTrue("XHTML should remain true after repeated checks", firstCheck);
        assertTrue("XHTML should remain true after repeated checks", secondCheck);
    }

    @Test
    public void testSettingXHTMLWithCompactFormat() {
        // Arrange
        outputFormat = OutputFormat.createCompactFormat();
        outputFormat.setXHTML(true);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertTrue("XHTML should be true when set even for compact format", result);
    }

    @Test
    public void testSettingXHTMLWithPrettyPrintFormat() {
        // Arrange
        outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setXHTML(false);

        // Act
        boolean result = outputFormat.isXHTML();

        // Assert
        assertFalse("XHTML should be false when set even for pretty print format", result);
    }
}