// OutputFormat_getNewLineAfterNTagsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public getNewLineAfterNTags() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_getNewLineAfterNTagsTest {
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
    public void testGetNewLineAfterNTags_defaultValue() {
        assertEquals(0, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testGetNewLineAfterNTags_setToPositiveNumber() {
        outputFormat.setNewLineAfterNTags(5);
        assertEquals(5, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testGetNewLineAfterNTags_setToZero() {
        outputFormat.setNewLineAfterNTags(0);
        assertEquals(0, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testGetNewLineAfterNTags_setToNegativeNumber() {
        outputFormat.setNewLineAfterNTags(-3);
        assertEquals(-3, outputFormat.getNewLineAfterNTags());
    }
    
    
    
    @Test(expected = NullPointerException)
    public void testGetNewLineAfterNTags_nullOutputFormat() {
        OutputFormat nullOutputFormat = null;
        nullOutputFormat.getNewLineAfterNTags();
    }
}