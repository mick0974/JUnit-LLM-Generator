// OutputFormat_setNewLineAfterNTagsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setNewLineAfterNTags(int tagCount) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setNewLineAfterNTagsTest {


    private OutputFormat format;

    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    @After
    public void tearDown() {
        format = null;
    }

    @Test
    public void testSetNewLineAfterNTagsDefault() {
        assertEquals(0, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsPositiveNumber() {
        format.setNewLineAfterNTags(5);
        assertEquals(5, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsZero() {
        format.setNewLineAfterNTags(0);
        assertEquals(0, format.getNewLineAfterNTags());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNewLineAfterNTagsNegativeNumber() {
        format.setNewLineAfterNTags(-1);
    }

    @Test
    public void testSetNewLineAfterNTagsLargeNumber() {
        format.setNewLineAfterNTags(1000);
        assertEquals(1000, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsToggleValues() {
        format.setNewLineAfterNTags(5);
        assertEquals(5, format.getNewLineAfterNTags());
        format.setNewLineAfterNTags(10);
        assertEquals(10, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsMaximumInt() {
        format.setNewLineAfterNTags(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsAfterSetToZero() {
        format.setNewLineAfterNTags(0);
        assertEquals(0, format.getNewLineAfterNTags());
        format.setNewLineAfterNTags(7);
        assertEquals(7, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsResetToDefault() {
        format.setNewLineAfterNTags(8);
        assertEquals(8, format.getNewLineAfterNTags());
        format.setNewLineAfterNTags(0);
        assertEquals(0, format.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTagsRepeatedSameValue() {
        format.setNewLineAfterNTags(4);
        assertEquals(4, format.getNewLineAfterNTags());
        format.setNewLineAfterNTags(4);
        assertEquals(4, format.getNewLineAfterNTags());
    }
}