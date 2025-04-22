// OutputFormat_setIndentSizeTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setIndentSize(int indentSize) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setIndentSizeTest {
    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test(expected = NullPointerException.class)
    public void testSetIndentSizeWithNullArgument() {
        outputFormat.setIndentSize(null);
    }

    @Test
    public void testSetIndentSizeWithValidArgument() {
        outputFormat.setIndentSize(5);
        assertEquals("The indent size should be 5", 5, outputFormat.getIndent().length());
    }

    @Test
    public void testSetIndentSizeWithZeroArgument() {
        outputFormat.setIndentSize(0);
        assertNull("The indent should be null", outputFormat.getIndent());
    }

    @Test
    public void testSetIndentSizeWithNegativeArgument() {
        outputFormat.setIndentSize(-1);
        assertNull("The indent should be null", outputFormat.getIndent());
    }
}