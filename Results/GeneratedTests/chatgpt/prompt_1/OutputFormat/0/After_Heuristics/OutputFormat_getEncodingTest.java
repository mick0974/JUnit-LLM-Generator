// OutputFormat_getEncodingTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getEncoding() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getEncodingTest {


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
    public void testDefaultEncoding() {
        // Arrange & Act
        String encoding = outputFormat.getEncoding();
        
        // Assert
        assertEquals("UTF-8", encoding);
    }

    @Test
    public void testEncodingAfterSettingToNull() {
        // Arrange
        outputFormat.setEncoding(null);

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("UTF-8", encoding);
    }

    @Test
    public void testSetEncodingToUTF16() {
        // Arrange
        outputFormat.setEncoding("UTF-16");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("UTF-16", encoding);
    }

    @Test
    public void testSetEncodingToISO88591() {
        // Arrange
        outputFormat.setEncoding("ISO-8859-1");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("ISO-8859-1", encoding);
    }

    @Test
    public void testSetEncodingTwice() {
        // Arrange
        outputFormat.setEncoding("ISO-8859-1");
        outputFormat.setEncoding("UTF-16");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("UTF-16", encoding);
    }

    @Test
    public void testSetEncodingToEmptyStringDoesNotChange() {
        // Arrange
        outputFormat.setEncoding("");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("UTF-8", encoding);
    }

    @Test
    public void testSetEncodingToNonStandardFormat() {
        // Arrange
        outputFormat.setEncoding("Custom-Encoding");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("Custom-Encoding", encoding);
    }

    @Test
    public void testSetEncodingWithWhitespace() {
        // Arrange
        outputFormat.setEncoding("   UTF-8   ");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("   UTF-8   ", encoding);
    }

    @Test
    public void testSetEncodingAfterSuppression() {
        // Arrange
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setEncoding("ISO-8859-1");

        // Act
        String encoding = outputFormat.getEncoding();

        // Assert
        assertEquals("ISO-8859-1", encoding);
    }

    @Test
    public void testSetEncodingOverridesCreatePrettyPrintDefault() {
        // Arrange
        OutputFormat prettyPrintFormat = OutputFormat.createPrettyPrint();
        prettyPrintFormat.setEncoding("ISO-8859-1");

        // Act
        String encoding = prettyPrintFormat.getEncoding();

        // Assert
        assertEquals("ISO-8859-1", encoding);
    }
}