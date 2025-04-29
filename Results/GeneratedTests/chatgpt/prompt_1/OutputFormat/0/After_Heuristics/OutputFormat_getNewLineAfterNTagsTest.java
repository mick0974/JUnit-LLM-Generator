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
    public void testDefaultNewLineAfterNTags() {
        assertEquals("Default newLineAfterNTags should be 0", 0, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsZero() {
        outputFormat.setNewLineAfterNTags(0);
        assertEquals("NewLineAfterNTags should be set to 0", 0, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsPositive() {
        outputFormat.setNewLineAfterNTags(10);
        assertEquals("NewLineAfterNTags should be set to 10", 10, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsBoundary() {
        outputFormat.setNewLineAfterNTags(1);
        assertEquals("NewLineAfterNTags should be set to 1", 1, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsLargeValue() {
        outputFormat.setNewLineAfterNTags(1000);
        assertEquals("NewLineAfterNTags should be set to 1000", 1000, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testChangeNewLineAfterNTagsMultipleTimes() {
        outputFormat.setNewLineAfterNTags(5);
        assertEquals("NewLineAfterNTags should be set to 5", 5, outputFormat.getNewLineAfterNTags());

        outputFormat.setNewLineAfterNTags(20);
        assertEquals("NewLineAfterNTags should be updated to 20", 20, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testNegativeValueIgnored() {
        outputFormat.setNewLineAfterNTags(-1);
        assertEquals("Negative value should be ignored, remain default 0", 0, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testInteractWithNewlinesFlagTrue() {
        outputFormat.setNewlines(true);
        outputFormat.setNewLineAfterNTags(5);
        assertEquals("NewLineAfterNTags should be effective with newlines true", 5, outputFormat.getNewLineAfterNTags());
    }
    
    @Test
    public void testInteractWithNewlinesFlagFalse() {
        outputFormat.setNewlines(false);
        outputFormat.setNewLineAfterNTags(5);
        assertEquals("NewLineAfterNTags should be set independently of newlines", 5, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testResetNewLineAfterNTags() {
        outputFormat.setNewLineAfterNTags(8);
        assertEquals("NewLineAfterNTags should be set to 8", 8, outputFormat.getNewLineAfterNTags());

        outputFormat.setNewLineAfterNTags(0);
        assertEquals("NewLineAfterNTags should be reset to 0", 0, outputFormat.getNewLineAfterNTags());
    }
}