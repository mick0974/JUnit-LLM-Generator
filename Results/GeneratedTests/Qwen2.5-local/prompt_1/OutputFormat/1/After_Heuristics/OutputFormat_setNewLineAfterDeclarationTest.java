// OutputFormat_setNewLineAfterDeclarationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setNewLineAfterDeclaration(boolean newLineAfterDeclaration) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setNewLineAfterDeclarationTest {
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
     * Test case for setting newLineAfterDeclaration to true.
     * Expected behavior: newLineAfterDeclaration should be true.
     */
    @Test
    public void testSetNewLineAfterDeclarationTrue() {
        outputFormat.setNewLineAfterDeclaration(true);
        assertTrue(outputFormat.isNewLineAfterDeclaration());
    }

    /**
     * Test case for setting newLineAfterDeclaration to false.
     * Expected behavior: newLineAfterDeclaration should be false.
     */
    @Test
    public void testSetNewLineAfterDeclarationFalse() {
        outputFormat.setNewLineAfterDeclaration(false);
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }

    /**
     * Test case for setting newLineAfterDeclaration to null.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test(expected = NullPointerException.class)
    public void testSetNewLineAfterDeclarationNull() {
        outputFormat.setNewLineAfterDeclaration(null);
    }

    /**
     * Test case for setting newLineAfterDeclaration to a non-boolean value.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test(expected = ClassCastException.class)
    public void testSetNewLineAfterDeclarationNonBoolean() {
        outputFormat.setNewLineAfterDeclaration(new Object());
    }

    /**
     * Test case for setting newLineAfterDeclaration to a negative integer.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test
    public void testSetNewLineAfterDeclarationNegativeInteger() {
        outputFormat.setNewLineAfterDeclaration(-1);
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }

    /**
     * Test case for setting newLineAfterDeclaration to a positive integer.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test
    public void testSetNewLineAfterDeclarationPositiveInteger() {
        outputFormat.setNewLineAfterDeclaration(1);
        assertTrue(outputFormat.isNewLineAfterDeclaration());
    }

    /**
     * Test case for setting newLineAfterDeclaration to zero.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test
    public void testSetNewLineAfterDeclarationZero() {
        outputFormat.setNewLineAfterDeclaration(0);
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    /**
     * Test case for setting newLineAfterDeclaration to a floating point number.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test
    public void testSetNewLineAfterDeclarationFloatingPointNumber() {
        outputFormat.setNewLineAfterDeclaration(1.5f);
        assertTrue(outputFormat.isNewLineAfterDeclaration());
    }

    /**
     * Test case for setting newLineAfterDeclaration to a double precision number.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test
    public void testSetNewLineAfterDeclarationDoublePrecisionNumber() {
        outputFormat.setNewLineAfterDeclaration(1.5d);
        assertTrue(outputFormat.isNewLineAfterDeclaration());
    }

    /**
     * Test case for setting newLineAfterDeclaration to a character.
     * Expected behavior: newLineAfterDeclaration should remain unchanged.
     */
    @Test
    public void testSetNewLineAfterDeclarationCharacter() {
        outputFormat.setNewLineAfterDeclaration('a');
        assertTrue(outputFormat.isNewLineAfterDeclaration());
    }
}