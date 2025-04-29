// OutputFormat_isOmitEncodingTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isOmitEncoding() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isOmitEncodingTest {


    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        // Reset or clean up resources after each test if necessary
    }

    @Test
    public void testDefaultOmitEncoding() {
        // Arrange & Act
        boolean result = outputFormat.isOmitEncoding();

        // Assert
        assertFalse("Default omitEncoding should be false", result);
    }

    @Test
    public void testSetOmitEncodingTrue() {
        // Arrange
        outputFormat.setOmitEncoding(true);

        // Act
        boolean result = outputFormat.isOmitEncoding();

        // Assert
        assertTrue("omitEncoding should be true after setting it to true", result);
    }

    @Test
    public void testSetOmitEncodingFalse() {
        // Arrange
        outputFormat.setOmitEncoding(true);
        outputFormat.setOmitEncoding(false);

        // Act
        boolean result = outputFormat.isOmitEncoding();

        // Assert
        assertFalse("omitEncoding should be false after setting it to false", result);
    }

    @Test
    public void testOmitEncodingWithMultipleCalls() {
        // Arrange
        outputFormat.setOmitEncoding(false);
        outputFormat.setOmitEncoding(true);
        outputFormat.setOmitEncoding(true);

        // Act
        boolean result = outputFormat.isOmitEncoding();

        // Assert
        assertTrue("omitEncoding should be true after multiple true settings", result);
    }

    @Test
    public void testOmitEncodingAfterInstantiation() {
        // Act
        OutputFormat newFormat = new OutputFormat();

        // Assert
        assertFalse("omitEncoding should be false for a newly instantiated object", newFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingAfterOtherSettings() {
        // Arrange
        outputFormat.setIndentSize(4);
        outputFormat.setNewlines(true);
        outputFormat.setTrimText(true);

        // Act
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("omitEncoding should remain true regardless of other settings", outputFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingAfterInitSettings() {
        // Arrange
        OutputFormat newFormat = OutputFormat.createPrettyPrint();

        // Act
        newFormat.setOmitEncoding(false);

        // Assert
        assertFalse("omitEncoding should be false after explicitly setting to false", newFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingNotAffectedByIndent() {
        // Arrange
        outputFormat.setIndent(true);

        // Act
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("omitEncoding setting should not be affected by setting indent", outputFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingWithMixedOrder() {
        // Arrange
        outputFormat.setIndent(true);
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setOmitEncoding(true);
        outputFormat.setNewLineAfterNTags(5);

        // Act
        boolean result = outputFormat.isOmitEncoding();

        // Assert
        assertTrue("omitEncoding should be true as it was set to true explicitly", result);
    }

    @Test
    public void testOmitEncodingWithClone() {
        // Arrange
        outputFormat.setOmitEncoding(true);

        // Act
        OutputFormat clonedFormat = (OutputFormat) outputFormat.clone();
        boolean result = clonedFormat.isOmitEncoding();

        // Assert
        assertTrue("Cloned object should maintain omitEncoding setting", result);
    }
}