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

    /**
     * Test case for setSuppressDeclaration with true value.
     */
    @Test
    public void testSetSuppressDeclarationTrue() {
        outputFormat.setSuppressDeclaration(true);
        assertTrue(outputFormat.isSuppressDeclaration());
    }

    /**
     * Test case for setSuppressDeclaration with false value.
     */
    @Test
    public void testSetSuppressDeclarationFalse() {
        outputFormat.setSuppressDeclaration(false);
        assertFalse(outputFormat.isSuppressDeclaration());
    }

    /**
     * Test case for setSuppressDeclaration with null value.
     * Although null value is not allowed, this test case is provided for completeness.
     */
    @Test(expected = NullPointerException.class)
    public void testSetSuppressDeclarationNull() {
        outputFormat.setSuppressDeclaration(null); // Should throw NullPointerException
    }
}