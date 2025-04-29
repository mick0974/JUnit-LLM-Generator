// OutputFormat_setSuppressDeclarationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setSuppressDeclaration(boolean suppressDeclaration) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setSuppressDeclarationTest {

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
    public void testSuppressDeclaration_Default() {
        // Arrange
        // Already done in setUp()
        
        // Act
        boolean result = outputFormat.isSuppressDeclaration();
        
        // Assert
        assertFalse("By default, suppressDeclaration should be false", result);
    }

    @Test
    public void testSuppressDeclaration_SetTrue() {
        // Arrange
        // Already done in setUp()

        // Act
        outputFormat.setSuppressDeclaration(true);
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true after setting to true", result);
    }

    @Test
    public void testSuppressDeclaration_SetFalse() {
        // Arrange
        outputFormat.setSuppressDeclaration(true); // Set to true first

        // Act
        outputFormat.setSuppressDeclaration(false);
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should be false after setting to false", result);
    }

    @Test
    public void testSuppressDeclaration_ToggleTwice() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        
        // Act
        outputFormat.setSuppressDeclaration(false); // First toggle
        outputFormat.setSuppressDeclaration(true);  // Second toggle
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true after toggling twice", result);
    }

    @Test
    public void testMultipleTrueAssignments() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        
        // Act
        outputFormat.setSuppressDeclaration(true);
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should remain true after setting true multiple times", result);
    }

    @Test
    public void testMultipleFalseAssignments() {
        // Arrange
        outputFormat.setSuppressDeclaration(false);
        
        // Act
        outputFormat.setSuppressDeclaration(false);
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should remain false after setting false multiple times", result);
    }

    @Test
    public void testAlternatingAssignTrueThenFalse() {
        // Arrange
        // Already done in setUp()

        // Act
        outputFormat.setSuppressDeclaration(true);  // Set to true
        outputFormat.setSuppressDeclaration(false); // Set back to false
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should be false after setting true then false", result);
    }

    @Test
    public void testAlternatingAssignFalseThenTrue() {
        // Arrange
        // Already done in setUp()

        // Act
        outputFormat.setSuppressDeclaration(false); // Set explicitly to false
        outputFormat.setSuppressDeclaration(true);  // Then set to true
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should be true after setting false then true", result);
    }

    @Test
    public void testSuppressDeclaration_UnchangedAfterTrue() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        
        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertTrue("suppressDeclaration should remain unchanged as true after being set", result);
    }
    
    @Test
    public void testSuppressDeclaration_UnchangedAfterFalse() {
        // Arrange
        outputFormat.setSuppressDeclaration(false);
        
        // Act
        boolean result = outputFormat.isSuppressDeclaration();

        // Assert
        assertFalse("suppressDeclaration should remain unchanged as false after being set", result);
    }
}