```java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the OutputFormat#setSuppressDeclaration(boolean) method.
 * Focuses on verifying the correct setting and retrieval of the suppressDeclaration flag.
 * Uses JUnit 4 framework.
 */
public class OutputFormat_setSuppressDeclarationTest {

    private OutputFormat format;

    /**
     * Sets up a default OutputFormat instance before each test.
     * The default value for suppressDeclaration is false.
     */
    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    /**
     * Test case 1: Set suppressDeclaration to true from the default (false).
     * Verifies that the getter returns true after setting the flag to true.
     */
    @Test
    public void testSetSuppressDeclarationTrueFromDefault() {
        // Arrange: Default format has suppressDeclaration = false
        assertFalse("Initial state should be false", format.isSuppressDeclaration());

        // Act
        format.setSuppressDeclaration(true);

        // Assert
        assertTrue("suppressDeclaration should be true after setting to true", format.isSuppressDeclaration());
    }

    /**
     * Test case 2: Set suppressDeclaration to false from the default (false).
     * Verifies that the getter returns false after setting the flag to false (no change).
     */
    @Test
    public void testSetSuppressDeclarationFalseFromDefault() {
        // Arrange: Default format has suppressDeclaration = false
        assertFalse("Initial state should be false", format.isSuppressDeclaration());

        // Act
        format.setSuppressDeclaration(false);

        // Assert
        assertFalse("suppressDeclaration should remain false after setting to false", format.isSuppressDeclaration());
    }

    /**
     * Test case 3: Set suppressDeclaration to true when it's already true.
     * Verifies that the getter still returns true.
     */
    @Test
    public void testSetSuppressDeclarationTrueWhenAlreadyTrue() {
        // Arrange: Set suppressDeclaration to true initially
        format.setSuppressDeclaration(true);
        assertTrue("Intermediate state should be true", format.isSuppressDeclaration());

        // Act: Set it to true again
        format.setSuppressDeclaration(true);

        // Assert
        assertTrue("suppressDeclaration should remain true after setting to true again", format.isSuppressDeclaration());
    }

    /**
     * Test case 4: Set suppressDeclaration to false when it's already false.
     * Verifies that the getter still returns false.
     */
    @Test
    public void testSetSuppressDeclarationFalseWhenAlreadyFalse() {
        // Arrange: Default state is false
        assertFalse("Initial state should be false", format.isSuppressDeclaration());
        // Optionally set it to false explicitly
        format.setSuppressDeclaration(false);
        assertFalse("Intermediate state should be false", format.isSuppressDeclaration());

        // Act: Set it to false again
        format.setSuppressDeclaration(false);

        // Assert
        assertFalse("suppressDeclaration should remain false after setting to false again", format.isSuppressDeclaration());
    }

    /**
     * Test case 5: Set suppressDeclaration to false after setting it to true.
     * Verifies that the getter returns false after toggling the flag from true to false.
     */
    @Test
    public void testSetSuppressDeclarationFalseFromTrue() {
        // Arrange: Set suppressDeclaration to true initially
        format.setSuppressDeclaration(true);
        assertTrue("Intermediate state should be true", format.isSuppressDeclaration());

        // Act
        format.setSuppressDeclaration(false);

        // Assert
        assertFalse("suppressDeclaration should be false after setting back to false", format.isSuppressDeclaration());
    }

    /**
     * Test case 6: Set suppressDeclaration to true after setting it to false (redundant with case 1, but confirms toggle).
     * Verifies that the getter returns true after toggling the flag from false to true.
     */
    @Test
    public void testSetSuppressDeclarationTrueFromFalse() {
        // Arrange: Default state is false
        assertFalse("Initial state should be false", format.isSuppressDeclaration());
        // Optionally set it to false explicitly
        format.setSuppressDeclaration(false);
        assertFalse("Intermediate state should be false", format.isSuppressDeclaration());


        // Act
        format.setSuppressDeclaration(true);

        // Assert
        assertTrue("suppressDeclaration should be true after setting back to true", format.isSuppressDeclaration());
    }

    /**
     * Test case 7: Verify the default value using the getter without calling the setter.
     * Confirms the documented default behavior.
     */
    @Test
    public void testDefaultValueIsFalse() {
        // Arrange: New default OutputFormat instance created in setUp()
        // Act: No action needed, just checking initial state
        // Assert
        assertFalse("Default value of suppressDeclaration should be false", format.isSuppressDeclaration());
    }

    /**
     * Test case 8: Set suppressDeclaration on an instance created with the indent constructor.
     * Ensures the setter works independently of the constructor used.
     */
    @Test
    public void testSetSuppressDeclarationWithIndentConstructor() {
        // Arrange
        OutputFormat formatWithIndent = new OutputFormat("  ");
        assertFalse("Initial state should be false", formatWithIndent.isSuppressDeclaration());

        // Act
        formatWithIndent.setSuppressDeclaration(true);

        // Assert
        assertTrue("suppressDeclaration should be true after setting", formatWithIndent.isSuppressDeclaration());
    }

    /**
     * Test case 9: Set suppressDeclaration on an instance created with the indent/newlines constructor.
     * Ensures the setter works independently of the constructor used.
     */
    @Test
    public void testSetSuppressDeclarationWithIndentNewlinesConstructor() {
        // Arrange
        OutputFormat formatWithIndentNewlines = new OutputFormat("  ", true);
        assertFalse("Initial state should be false", formatWithIndentNewlines.isSuppressDeclaration());

        // Act
        formatWithIndentNewlines.setSuppressDeclaration(true);

        // Assert
        assertTrue("suppressDeclaration should be true after setting", formatWithIndentNewlines.isSuppressDeclaration());
    }

    /**
     * Test case 10: Set suppressDeclaration on an instance created with the full constructor.
     * Ensures the setter works independently of the constructor used.
     */
    @Test
    public void testSetSuppressDeclarationWithFullConstructor() {
        // Arrange
        OutputFormat fullFormat = new OutputFormat("  ", true, "ISO-8859-1");
        assertFalse("Initial state should be false", fullFormat.isSuppressDeclaration());

        // Act
        fullFormat.setSuppressDeclaration(true);

        // Assert
        assertTrue("suppressDeclaration should be true after setting", fullFormat.isSuppressDeclaration());
    }
}
```