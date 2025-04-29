import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#getAttributeQuoteCharacter() method.
 */
// OutputFormat_getAttributeQuoteCharacterTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getAttributeQuoteCharacter() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getAttributeQuoteCharacterTest {


    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @Test
    public void testDefaultQuoteCharacter() {
        // Arrange
        // Act
        char quoteChar = outputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\"', quoteChar);
    }

    @Test
    public void testSetQuoteCharacterToSingleQuote() {
        // Arrange
        outputFormat.setAttributeQuoteCharacter('\'');
        // Act
        char quoteChar = outputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\'', quoteChar);
    }

    @Test
    public void testSetQuoteCharacterToDoubleQuote() {
        // Arrange
        outputFormat.setAttributeQuoteCharacter('\"');
        // Act
        char quoteChar = outputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\"', quoteChar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidQuoteCharacter() {
        // Arrange
        // Act
        outputFormat.setAttributeQuoteCharacter('`');
        // Assert is handled by expected exception
    }

    @Test
    public void testMaintainingStateAfterInvalidQuoteCharacter() {
        // Arrange
        outputFormat.setAttributeQuoteCharacter('\'');
        try {
            // Act
            outputFormat.setAttributeQuoteCharacter('`');
        } catch (IllegalArgumentException expected) {
            // Expected exception
        }
        // Assert
        assertEquals('\'', outputFormat.getAttributeQuoteCharacter());
    }

    @Test
    public void testSetQuoteCharacterTwice() {
        // Arrange
        outputFormat.setAttributeQuoteCharacter('\'');
        outputFormat.setAttributeQuoteCharacter('\"');
        // Act
        char quoteChar = outputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\"', quoteChar);
    }

    @Test
    public void testResetQuoteCharacterToDefault() {
        // Arrange
        outputFormat.setAttributeQuoteCharacter('\'');
        outputFormat.setAttributeQuoteCharacter('\"');
        // Act
        char quoteChar = outputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\"', quoteChar);
    }

    @Test
    public void testSetQuoteCharacterWithSameValue() {
        // Arrange
        outputFormat.setAttributeQuoteCharacter('\'');
        // Act
        char quoteChar = outputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\'', quoteChar);
    }

    @Test
    public void testQuoteCharacterInNewInstance() {
        // Arrange
        OutputFormat newOutputFormat = new OutputFormat();
        // Act
        char quoteChar = newOutputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\"', quoteChar);
    }

    @Test
    public void testMultipleInstancesWithDifferentQuoteCharacters() {
        // Arrange
        OutputFormat anotherOutputFormat = new OutputFormat();
        anotherOutputFormat.setAttributeQuoteCharacter('\'');
        // Act
        char quoteChar1 = outputFormat.getAttributeQuoteCharacter();
        char quoteChar2 = anotherOutputFormat.getAttributeQuoteCharacter();
        // Assert
        assertEquals('\"', quoteChar1);
        assertEquals('\'', quoteChar2);
    }
}