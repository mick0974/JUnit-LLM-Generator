// OutputFormat_setAttributeQuoteCharacterTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setAttributeQuoteCharacter(char quoteChar) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setAttributeQuoteCharacterTest {

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
    public void testSetAttributeQuoteCharacter_DoubleQuote() {
        // Arrange
        char quoteChar = '"';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(quoteChar);
        
        // Assert
        assertEquals(quoteChar, outputFormat.getAttributeQuoteCharacter());
    }

    @Test
    public void testSetAttributeQuoteCharacter_SingleQuote() {
        // Arrange
        char quoteChar = '\'';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(quoteChar);
        
        // Assert
        assertEquals(quoteChar, outputFormat.getAttributeQuoteCharacter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAttributeQuoteCharacter_InvalidCharacter() {
        // Arrange
        char quoteChar = '`';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(quoteChar);
        
        // Assert
        // Expect IllegalArgumentException to be thrown
    }

    @Test
    public void testDefaultAttributeQuoteCharacter() {
        // Arrange & Act
        char defaultQuoteChar = outputFormat.getAttributeQuoteCharacter();
        
        // Assert
        assertEquals('"', defaultQuoteChar);
    }

    @Test
    public void testSetAttributeQuoteCharacter_CheckStateChange() {
        // Arrange
        char initialQuoteChar = outputFormat.getAttributeQuoteCharacter();
        char newQuoteChar = '\'';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(newQuoteChar);
        
        // Assert
        assertNotEquals(initialQuoteChar, outputFormat.getAttributeQuoteCharacter());
        assertEquals(newQuoteChar, outputFormat.getAttributeQuoteCharacter());
    }

    @Test
    public void testSetAttributeQuoteCharacter_NoEffectOnIndent() {
        // Arrange
        String initialIndent = outputFormat.getIndent();
        char quoteChar = '\'';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(quoteChar);
        
        // Assert
        assertEquals(initialIndent, outputFormat.getIndent());
    }

    @Test
    public void testSetAttributeQuoteCharacter_AfterIndentChange() {
        // Arrange
        outputFormat.setIndentSize(4);
        char quoteChar = '\'';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(quoteChar);
        
        // Assert
        assertEquals(quoteChar, outputFormat.getAttributeQuoteCharacter());
        assertEquals("    ", outputFormat.getIndent());
    }

    @Test
    public void testSetAttributeQuoteCharacter_MultipleSet() {
        // Arrange
        char firstChar = '\'';
        char secondChar = '"';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(firstChar);
        outputFormat.setAttributeQuoteCharacter(secondChar);
        
        // Assert
        assertEquals(secondChar, outputFormat.getAttributeQuoteCharacter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAttributeQuoteCharacter_InvalidAfterValid() {
        // Arrange
        char validChar = '"';
        outputFormat.setAttributeQuoteCharacter(validChar);
        
        // Act
        char invalidChar = '!';
        outputFormat.setAttributeQuoteCharacter(invalidChar);
        
        // Assert
        // Expect IllegalArgumentException to be thrown
    }

    @Test
    public void testSetAttributeQuoteCharacter_ResetToDefault() {
        // Arrange 
        char initialChar = outputFormat.getAttributeQuoteCharacter();
        char newChar = '\'';
        
        // Act
        outputFormat.setAttributeQuoteCharacter(newChar);
        outputFormat.setAttributeQuoteCharacter(initialChar);
        
        // Assert
        assertEquals(initialChar, outputFormat.getAttributeQuoteCharacter());
    }
}