import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
// Unused import, kept for completeness based on original template comment
// import java.io.Serializable; 

/**
 * Test class for the FontInfo#setSize(int value) method.
 * It contains 10 unit test cases covering typical and edge cases, 
 * following the Arrange-Act-Assert pattern.
 */
public class FontInfo_setSizeTest {

    private FontInfo fontInfo;
    private final int DEFAULT_INITIAL_SIZE = 12; // As set by FontInfo() constructor

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        // Arrange: Create a new FontInfo instance before each test
        fontInfo = new FontInfo();
        // Optional: Verify initial state if relevant (though default constructor test does this)
        // assertEquals(DEFAULT_INITIAL_SIZE, fontInfo.getSize()); 
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        fontInfo = null; // Help garbage collection
    }

    /**
     * Test setting a typical positive font size.
     */
    @Test
    public void testSetSizePositiveValue() {
        // Arrange
        int expectedSize = 10;

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should be updated to the positive value.", expectedSize, actualSize);
    }

    /**
     * Test setting the font size to zero. While potentially not visually useful,
     * the method itself does not prevent it.
     */
    @Test
    public void testSetSizeZero() {
        // Arrange
        int expectedSize = 0;

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should be updatable to zero.", expectedSize, actualSize);
    }

    /**
     * Test setting a negative font size. The method allows this, even if AWT Font might handle it specially.
     */
    @Test
    public void testSetSizeNegativeValue() {
        // Arrange
        int expectedSize = -5;

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should be updatable to a negative value.", expectedSize, actualSize);
    }

    /**
     * Test setting a large positive font size.
     */
    @Test
    public void testSetSizeLargePositiveValue() {
        // Arrange
        int expectedSize = 72; // A common large font size

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should handle large positive values.", expectedSize, actualSize);
    }
    
    /**
     * Test setting the font size to the maximum integer value.
     */
    @Test
    public void testSetSizeMaximumIntegerValue() {
        // Arrange
        int expectedSize = Integer.MAX_VALUE;

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should handle Integer.MAX_VALUE.", expectedSize, actualSize);
    }

    /**
     * Test setting the font size to the minimum integer value.
     */
    @Test
    public void testSetSizeMinimumIntegerValue() {
        // Arrange
        int expectedSize = Integer.MIN_VALUE;

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should handle Integer.MIN_VALUE.", expectedSize, actualSize);
    }

    /**
     * Test setting the font size to the same value it already has (the default).
     */
    @Test
    public void testSetSizeToCurrentValue() {
        // Arrange
        int expectedSize = fontInfo.getSize(); // Get current size (should be default 12)
        assertEquals("Initial size should be default", DEFAULT_INITIAL_SIZE, expectedSize);


        // Act
        fontInfo.setSize(expectedSize); // Set it to the same value
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should remain the same when set to its current value.", expectedSize, actualSize);
    }

    /**
     * Test setting the font size multiple times, ensuring the last call takes effect.
     */
    @Test
    public void testSetSizeMultipleTimes() {
        // Arrange
        int firstSize = 20;
        int expectedFinalSize = 8;

        // Act
        fontInfo.setSize(firstSize); // First set
        fontInfo.setSize(expectedFinalSize); // Second set (should overwrite)
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("The last call to setSize should determine the final font size.", expectedFinalSize, actualSize);
    }

    /**
     * Test setting the font size immediately after default construction.
     * This verifies the method works correctly on a newly initialized object.
     */
    @Test
    public void testSetSizeImmediatelyAfterConstruction() {
        // Arrange: fontInfo is already created by setUp() with default size 12
        int expectedSize = 14;
        assertNotEquals("Test setup assumption check: expected size should differ from default", 
                        expectedSize, fontInfo.getSize());

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should be correctly set on a newly constructed object.", expectedSize, actualSize);
    }
    
    /**
     * Test setting the font size to 1, a small positive boundary case.
     */
    @Test
    public void testSetSizeToOne() {
        // Arrange
        int expectedSize = 1;

        // Act
        fontInfo.setSize(expectedSize);
        int actualSize = fontInfo.getSize();

        // Assert
        assertEquals("Font size should be updatable to 1.", expectedSize, actualSize);
    }
}