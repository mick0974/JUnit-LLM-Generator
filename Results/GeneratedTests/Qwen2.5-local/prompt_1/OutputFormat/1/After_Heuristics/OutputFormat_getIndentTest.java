// OutputFormat_getIndentTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getIndent() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getIndentTest {
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
     * Test getIndent when indent is null
     */
    @Test
    public void testGetIndent_WhenIndentIsNull() {
        assertEquals(null, outputFormat.getIndent());
    }

    /**
     * Test getIndent when indent is non-null
     */
    @Test
    public void testGetIndent_WhenIndentIsNonNull() {
        outputFormat.setIndent("  ");
        assertEquals("  ", outputFormat.getIndent());
    }
}