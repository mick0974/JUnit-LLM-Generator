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

    @Test
    public void testSetNewLineAfterDeclarationTrue() {
        outputFormat.setNewLineAfterDeclaration(true);
        assertTrue(outputFormat.isNewLineAfterDeclaration());
    }

    @Test
    public void testSetNewLineAfterDeclarationFalse() {
        outputFormat.setNewLineAfterDeclaration(false);
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationNull() {
        outputFormat.setNewLineAfterDeclaration(null);
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationEmptyString() {
        outputFormat.setNewLineAfterDeclaration("");
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationWhitespace() {
        outputFormat.setNewLineAfterDeclaration(" ");
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationSpecialCharacters() {
        outputFormat.setNewLineAfterDeclaration("!@#$%^&*()");
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationNumbers() {
        outputFormat.setNewLineAfterDeclaration("1234567890");
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationBooleanValues() {
        outputFormat.setNewLineAfterDeclaration(Boolean.TRUE);
        assertTrue(outputFormat.isNewLineAfterDeclaration());

        outputFormat.setNewLineAfterDeclaration(Boolean.FALSE);
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationFloatValues() {
        outputFormat.setNewLineAfterDeclaration(Float.valueOf(1.0f));
        assertFalse(outputFormat.isNewLineAfterDeclaration());

        outputFormat.setNewLineAfterDeclaration(Float.valueOf(0.0f));
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
    
    @Test
    public void testSetNewLineAfterDeclarationDoubleValues() {
        outputFormat.setNewLineAfterDeclaration(Double.valueOf(1.0d));
        assertFalse(outputFormat.isNewLineAfterDeclaration());

        outputFormat.setNewLineAfterDeclaration(Double.valueOf(0.0d));
        assertFalse(outputFormat.isNewLineAfterDeclaration());
    }
}