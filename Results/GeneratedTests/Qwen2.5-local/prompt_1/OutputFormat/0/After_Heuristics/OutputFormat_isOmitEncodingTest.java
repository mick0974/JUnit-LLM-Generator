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
        outputFormat = null;
    }

    @Test
    public void testIsOmitEncoding_defaultValue() {
        assertFalse(outputFormat.isOmitEncoding());
    }

    @Test
    public void testIsOmitEncoding_setToTrue() {
        outputFormat.setOmitEncoding(true);
        assertTrue(outputFormat.isOmitEncoding());
    }

    @Test
    public void testIsOmitEncoding_setToFalse() {
        outputFormat.setOmitEncoding(false);
        assertFalse(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_nullInput() {
        outputFormat.setAttributeQuoteCharacter('\'');
        assertTrue(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_emptyStringInput() {
        outputFormat.setAttributeQuoteCharacter('"');
        assertFalse(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_spaceInput() {
        outputFormat.setAttributeQuoteCharacter(' ');
        assertTrue(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_tabInput() {
        outputFormat.setAttributeQuoteCharacter('\t');
        assertFalse(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_newlineInput() {
        outputFormat.setAttributeQuoteCharacter('\n');
        assertTrue(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_backslashInput() {
        outputFormat.setAttributeQuoteCharacter('\\');
        assertFalse(outputFormat.isOmitEncoding());
    }
    
    @Test
    public void testIsOmitEncoding_forwardslashInput() {
        outputFormat.setAttributeQuoteCharacter('/');
        assertTrue(outputFormat.isOmitEncoding());
    }
}