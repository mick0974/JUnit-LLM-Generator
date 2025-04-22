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
    public void testSetNewLineAfterNTags_WithZeroTagCount() {
        // Arrange
        int tagCount = 0;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(0, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTags_WithPositiveTagCount() {
        // Arrange
        int tagCount = 5;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(5, outputFormat.getNewLineAfterNTags());
    }

    @Test
    public void testSetNewLineAfterNTags_WithNegativeTagCount() {
        // Arrange
        int tagCount = -3;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(-3, outputFormat.getNewLineAfterNTags());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSetNewLineAfterNTags_WithNullArgument() {
        // Arrange
        Integer tagCount = null;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        fail("Expected IllegalArgumentException");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSetNewLineAfterNTags_WithNonIntegerArgument() {
        // Arrange
        Object tagCount = "abc";

        // Act
        outputFormat.setNewLineAfterNTags((Integer)tagCount);

        // Assert
        fail("Expected IllegalArgumentException");
    }
    
    @Test
    public void testSetNewLineAfterNTags_WithMaxIntValue() {
        // Arrange
        int tagCount = Integer.MAX_VALUE;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(Integer.MAX_VALUE, outputFormat.getNewLineAfterNTags());
    }
    
    @Test
    public void testSetNewLineAfterNTags_WithMinIntValue() {
        // Arrange
        int tagCount = Integer.MIN_VALUE;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(Integer.MIN_VALUE, outputFormat.getNewLineAfterNTags());
    }
    
    @Test
    public void testSetNewLineAfterNTags_WithLargePositiveNumber() {
        // Arrange
        int tagCount = 1000000000;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(1000000000, outputFormat.getNewLineAfterNTags());
    }
    
    @Test
    public void testSetNewLineAfterNTags_WithSmallPositiveNumber() {
        // Arrange
        int tagCount = 1;

        // Act
        outputFormat.setNewLineAfterNTags(tagCount);

        // Assert
        assertEquals(1, outputFormat.getNewLineAfterNTags());
    }
}