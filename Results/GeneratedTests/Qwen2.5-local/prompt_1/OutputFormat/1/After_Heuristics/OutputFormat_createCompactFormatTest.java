// OutputFormat_createCompactFormatTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public static createCompactFormat() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_createCompactFormatTest {
    
    private OutputFormat format;

    @Before
    public void setUp() {
        format = OutputFormat.createCompactFormat();
    }

    @After
    public void tearDown() {
        format = null;
    }

    @Test
    public void testCreateCompactFormatIndent() {
        assertEquals(null, format.getIndent());
    }

    @Test
    public void testCreateCompactFormatNewLines() {
        assertFalse(format.isNewlines());
    }

    @Test
    public void testCreateCompactFormatTrimText() {
        assertTrue(format.isTrimText());
    }

    @Test
    public void testCreateCompactFormatPadText() {
        assertFalse(format.isPadText());
    }

    @Test
    public void testCreateCompactFormatExpandEmptyElements() {
        assertFalse(format.isExpandEmptyElements());
    }

    @Test
    public void testCreateCompactFormatSuppressDeclaration() {
        assertFalse(format.isSuppressDeclaration());
    }

    @Test
    public void testCreateCompactFormatOmitEncoding() {
        assertFalse(format.isOmitEncoding());
    }

    @Test
    public void testCreateCompactFormatXHTML() {
        assertFalse(format.isXHTML());
    }

    @Test
    public void testCreateCompactFormatNewLineAfterNTags() {
        assertEquals(0, format.getNewLineAfterNTags());
    }
}