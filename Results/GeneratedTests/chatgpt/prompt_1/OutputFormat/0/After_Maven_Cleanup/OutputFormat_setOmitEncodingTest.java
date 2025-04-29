// OutputFormat_setOmitEncodingTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setOmitEncoding(boolean omitEncoding) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setOmitEncodingTest {


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
    public void testDefaultOmitEncoding() {
        assertFalse("By default, omitEncoding should be false", outputFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingTrue() {
        // Arrange & Act
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("omitEncoding should be set to true", outputFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingFalse() {
        // Arrange
        outputFormat.setOmitEncoding(true); // Set it to true first

        // Act
        outputFormat.setOmitEncoding(false);

        // Assert
        assertFalse("omitEncoding should be set to false", outputFormat.isOmitEncoding());
    }

    @Test
    public void testSetOmitEncodingTrueTwice() {
        // Arrange
        outputFormat.setOmitEncoding(true);

        // Act
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("omitEncoding should remain true after setting it to true again", outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testSetOmitEncodingFalseTwice() {
        // Arrange & Act
        outputFormat.setOmitEncoding(false);

        // Act
        outputFormat.setOmitEncoding(false);

        // Assert
        assertFalse("omitEncoding should remain false after setting it to false again", outputFormat.isOmitEncoding());
    }

    @Test
    public void testToggleOmitEncoding() {
        // Arrange
        outputFormat.setOmitEncoding(false);

        // Act
        outputFormat.setOmitEncoding(true);
        assertTrue(outputFormat.isOmitEncoding());
        
        outputFormat.setOmitEncoding(false);

        // Assert
        assertFalse("omitEncoding should be false after toggling from true", outputFormat.isOmitEncoding());
    }

    @Test
    public void testInitialStateChange() {
        // Act
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("After initialization, setting omitEncoding to true should reflect correctly", outputFormat.isOmitEncoding());
    }

    @Test
    public void testConsecutiveStateChanges() {
        // Act
        outputFormat.setOmitEncoding(false);
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("omitEncoding should be true after setting it consecutively to false and true", outputFormat.isOmitEncoding());
    }

    @Test
    public void testRedundantFalseSetting() {
        // Act
        outputFormat.setOmitEncoding(false);

        // Assert
        assertFalse("Redundantly setting omitEncoding to false should not affect its value", outputFormat.isOmitEncoding());
    }

    @Test
    public void testRedundantTrueSetting() {
        // Arrange
        outputFormat.setOmitEncoding(true);

        // Act
        outputFormat.setOmitEncoding(true);

        // Assert
        assertTrue("Redundantly setting omitEncoding to true should not affect its value", outputFormat.isOmitEncoding());
    }
}