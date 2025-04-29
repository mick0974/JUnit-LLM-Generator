// OutputFormat_setEncodingTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setEncoding(String encoding) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setEncodingTest {


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
    public void testSetEncodingValidUTF8() {
        // Arrange
        String encoding = "UTF-8";
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("UTF-8", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingValidUTF16() {
        // Arrange
        String encoding = "UTF-16";
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("UTF-16", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingValidISO88591() {
        // Arrange
        String encoding = "ISO-8859-1";
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("ISO-8859-1", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingEmptyString() {
        // Arrange
        String encoding = "";
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("UTF-8", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingNull() {
        // Arrange
        String encoding = null;
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("UTF-8", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingARandomString() {
        // Arrange
        String encoding = "RANDOM_ENCODING";
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("RANDOM_ENCODING", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingCaseSensitivity() {
        // Arrange
        String encoding = "UTF-8";
        String encodingLowerCase = "utf-8";

        // Act
        outputFormat.setEncoding(encodingLowerCase);

        // Assert
        assertEquals(encodingLowerCase, outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingSpecialCharacters() {
        // Arrange
        String encoding = "ISO-8859_1";
        
        // Act
        outputFormat.setEncoding(encoding);
        
        // Assert
        assertEquals("ISO-8859_1", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingInitialDefault() {
        // Assert
        assertEquals("UTF-8", outputFormat.getEncoding());
    }

    @Test
    public void testSetEncodingOverride() {
        // Arrange
        String initialEncoding = "UTF-16";
        String newEncoding = "ISO-8859-1";

        // Act
        outputFormat.setEncoding(initialEncoding);
        outputFormat.setEncoding(newEncoding);

        // Assert
        assertEquals("ISO-8859-1", outputFormat.getEncoding());
    }
}