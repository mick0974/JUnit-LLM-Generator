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

    /**
     * Test case to check if the attribute quote character is correctly set
     * when a valid quote character ('\' or '"') is provided.
     */
    @Test
    public void testSetAttributeQuoteCharacter_validCharacters() {
        outputFormat.setAttributeQuoteCharacter('\'');
        assertEquals("\'", outputFormat.getAttributeQuoteCharacter());

        outputFormat.setAttributeQuoteCharacter('"');
        assertEquals("\"", outputFormat.getAttributeQuoteCharacter());
    }

    /**
     * Test case to check if the setAttributeQuoteCharacter method throws
     * IllegalArgumentException when an invalid quote character is provided.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAttributeQuoteCharacter_invalidCharacters() {
        outputFormat.setAttributeQuoteCharacter('a'); // Invalid quote character
    }
}