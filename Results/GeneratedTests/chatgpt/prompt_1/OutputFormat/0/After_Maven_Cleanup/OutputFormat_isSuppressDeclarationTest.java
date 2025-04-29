// OutputFormat_isSuppressDeclarationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isSuppressDeclaration() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isSuppressDeclarationTest {


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
    public void testDefaultSuppressDeclaration() {
        // Arrange & Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("Default suppressDeclaration should be false", result);
    }

    @Test
    public void testSetSuppressDeclarationTrue() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true after setting it", result);
    }

    @Test
    public void testSetSuppressDeclarationFalse() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setSuppressDeclaration(false);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should be false after setting it", result);
    }

    @Test
    public void testRepeatedSetSuppressDeclarationTrue() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setSuppressDeclaration(true);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should remain true after setting it repeatedly", result);
    }

    @Test
    public void testRepeatedSetSuppressDeclarationFalse() {
        // Arrange
        outputFormat.setSuppressDeclaration(false);
        outputFormat.setSuppressDeclaration(false);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should remain false after setting it repeatedly", result);
    }

    @Test
    public void testSwitchSuppressDeclaration() {
        // Arrange
        outputFormat.setSuppressDeclaration(false);
        outputFormat.setSuppressDeclaration(true);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true after switching from false to true", result);
    }

    @Test
    public void testSwitchBackSuppressDeclaration() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setSuppressDeclaration(false);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should be false after switching from true to false", result);
    }

    @Test
    public void testSuppressDeclarationWithOtherSettings() {
        // Arrange
        outputFormat.setNewlines(true);
        outputFormat.setIndent("  ");
        outputFormat.setSuppressDeclaration(true);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true even with other settings applied", result);
    }

    @Test
    public void testSuppressDeclarationWithEncodingSet() {
        // Arrange
        outputFormat.setEncoding("ISO-8859-1");
        outputFormat.setSuppressDeclaration(true);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true with encoding set", result);
    }

    @Test
    public void testSuppressDeclarationNotAffectedByOmitEncoding() {
        // Arrange
        outputFormat.setOmitEncoding(true);
        outputFormat.setSuppressDeclaration(true);

        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true and not affected by omitEncoding", result);
    }
}