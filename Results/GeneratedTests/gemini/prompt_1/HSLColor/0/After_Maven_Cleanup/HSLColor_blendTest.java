import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public blend(int R, int G, int B, float fPercent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Contains only valid Java code.
 */
public class HSLColor_blendTest {

    private HSLColor color;
    // Define initial color for testing (e.g., Pure Red)
    private static final int INITIAL_R = 255;
    private static final int INITIAL_G = 0;
    private static final int INITIAL_B = 0;

    @Before
    public void setUp() {
        // Arrange: Initialize a HSLColor object with a known state before each test
        color = new HSLColor();
        // Initialize with RGB which also sets internal HSL values
        color.initHSLbyRGB(INITIAL_R, INITIAL_G, INITIAL_B);
    }

    @After
    public void tearDown() {
        // Clean up resources if needed (optional here)
        color = null;
    }

    /**
     * Test Case 1: Blend with 0% (fPercent = 0.0f).
     * Expected: The color should remain unchanged.
     */
    @Test
    public void testBlendWithZeroPercent() {
        // Arrange
        int blendR = 0;
        int blendG = 255;
        int blendB = 0; // Pure Green
        float percent = 0.0f;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should be the initial Red
        assertEquals("Red component should remain unchanged", INITIAL_R, color.getRed());
        assertEquals("Green component should remain unchanged", INITIAL_G, color.getGreen());
        assertEquals("Blue component should remain unchanged", INITIAL_B, color.getBlue());
    }

    /**
     * Test Case 2: Blend with 100% (fPercent = 1.0f).
     * Expected: The color should become the target blend color.
     */
    @Test
    public void testBlendWithOneHundredPercent() {
        // Arrange
        int blendR = 0;
        int blendG = 0;
        int blendB = 255; // Pure Blue
        float percent = 1.0f;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should now be the blend color (Blue)
        assertEquals("Red component should match blend color", blendR, color.getRed());
        assertEquals("Green component should match blend color", blendG, color.getGreen());
        assertEquals("Blue component should match blend color", blendB, color.getBlue());
    }

    /**
     * Test Case 3: Blend with 50% (fPercent = 0.5f).
     * Expected: The color should be an equal mix of the initial and blend colors.
     * Initial: Red (255, 0, 0)
     * Blend Target: Blue (0, 0, 255)
     * Expected Result: Purple (127, 0, 127) - based on integer calculation:
     * R = (int) ( (0 * 0.5) + (255 * (1-0.5)) ) = (int) (0 + 127.5) = 127
     * G = (int) ( (0 * 0.5) + (0 * (1-0.5)) )   = (int) (0 + 0) = 0
     * B = (int) ( (255 * 0.5) + (0 * (1-0.5)) ) = (int) (127.5 + 0) = 127
     */
    @Test
    public void testBlendWithFiftyPercent() {
        // Arrange
        int blendR = 0;
        int blendG = 0;
        int blendB = 255; // Pure Blue
        float percent = 0.5f;
        int expectedR = 127;
        int expectedG = 0;
        int expectedB = 127;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should be the calculated blend
        assertEquals("Red component should be the 50% blend value", expectedR, color.getRed());
        assertEquals("Green component should be the 50% blend value", expectedG, color.getGreen());
        assertEquals("Blue component should be the 50% blend value", expectedB, color.getBlue());
    }

    /**
     * Test Case 4: Blend with 25% (fPercent = 0.25f).
     * Expected: The color should be 25% of the blend color and 75% of the initial color.
     * Initial: Red (255, 0, 0)
     * Blend Target: Green (0, 255, 0)
     * Expected Result: Orangey-Red (191, 63, 0) - based on integer calculation:
     * R = (int) ( (0 * 0.25) + (255 * (1-0.25)) ) = (int) (0 + 191.25) = 191
     * G = (int) ( (255 * 0.25) + (0 * (1-0.25)) ) = (int) (63.75 + 0) = 63
     * B = (int) ( (0 * 0.25) + (0 * (1-0.25)) )   = (int) (0 + 0) = 0
     */
    @Test
    public void testBlendWithTwentyFivePercent() {
        // Arrange
        int blendR = 0;
        int blendG = 255; // Pure Green
        int blendB = 0;
        float percent = 0.25f;
        int expectedR = 191;
        int expectedG = 63;
        int expectedB = 0;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should be the calculated blend
        assertEquals("Red component should be the 25% blend value", expectedR, color.getRed());
        assertEquals("Green component should be the 25% blend value", expectedG, color.getGreen());
        assertEquals("Blue component should be the 25% blend value", expectedB, color.getBlue());
    }

    /**
     * Test Case 5: Blend with 75% (fPercent = 0.75f).
     * Expected: The color should be 75% of the blend color and 25% of the initial color.
     * Initial: Red (255, 0, 0)
     * Blend Target: Green (0, 255, 0)
     * Expected Result: Yellowish-Green (63, 191, 0) - based on integer calculation:
     * R = (int) ( (0 * 0.75) + (255 * (1-0.75)) ) = (int) (0 + 63.75) = 63
     * G = (int) ( (255 * 0.75) + (0 * (1-0.75)) ) = (int) (191.25 + 0) = 191
     * B = (int) ( (0 * 0.75) + (0 * (1-0.75)) )   = (int) (0 + 0) = 0
     */
    @Test
    public void testBlendWithSeventyFivePercent() {
        // Arrange
        int blendR = 0;
        int blendG = 255; // Pure Green
        int blendB = 0;
        float percent = 0.75f;
        int expectedR = 63;
        int expectedG = 191;
        int expectedB = 0;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should be the calculated blend
        assertEquals("Red component should be the 75% blend value", expectedR, color.getRed());
        assertEquals("Green component should be the 75% blend value", expectedG, color.getGreen());
        assertEquals("Blue component should be the 75% blend value", expectedB, color.getBlue());
    }

    /**
     * Test Case 6: Edge Case - Blend with percentage less than 0 (fPercent = -0.5f).
     * Expected: Should behave like 0%, the color remains unchanged.
     */
    @Test
    public void testBlendWithNegativePercent() {
        // Arrange
        int blendR = 0;
        int blendG = 255;
        int blendB = 255; // Cyan
        float percent = -0.5f;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should be the initial Red
        assertEquals("Red component should remain unchanged", INITIAL_R, color.getRed());
        assertEquals("Green component should remain unchanged", INITIAL_G, color.getGreen());
        assertEquals("Blue component should remain unchanged", INITIAL_B, color.getBlue());
    }

    /**
     * Test Case 7: Edge Case - Blend with percentage greater than 1 (fPercent = 1.5f).
     * Expected: Should behave like 100%, the color becomes the target blend color.
     */
    @Test
    public void testBlendWithPercentGreaterThanOne() {
        // Arrange
        int blendR = 100;
        int blendG = 150;
        int blendB = 200; // A custom color
        float percent = 1.5f;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert: Color should now be the blend color
        assertEquals("Red component should match blend color", blendR, color.getRed());
        assertEquals("Green component should match blend color", blendG, color.getGreen());
        assertEquals("Blue component should match blend color", blendB, color.getBlue());
    }

    /**
     * Test Case 8: Blend with Black (0, 0, 0).
     * Expected: The color components should be scaled down towards 0.
     * Initial: Red (255, 0, 0)
     * Blend Target: Black (0, 0, 0) at 50%
     * Expected Result: Dark Red (127, 0, 0)
     * R = (int) ( (0 * 0.5) + (255 * 0.5) ) = 127
     * G = (int) ( (0 * 0.5) + (0 * 0.5) )   = 0
     * B = (int) ( (0 * 0.5) + (0 * 0.5) )   = 0
     */
    @Test
    public void testBlendWithBlack() {
        // Arrange
        int blendR = 0;
        int blendG = 0;
        int blendB = 0; // Black
        float percent = 0.5f;
        int expectedR = 127;
        int expectedG = 0;
        int expectedB = 0;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert
        assertEquals("Red component should be blended towards black", expectedR, color.getRed());
        assertEquals("Green component should be blended towards black", expectedG, color.getGreen());
        assertEquals("Blue component should be blended towards black", expectedB, color.getBlue());
    }

    /**
     * Test Case 9: Blend with White (255, 255, 255).
     * Expected: The color components should be scaled up towards 255.
     * Initial: Red (255, 0, 0)
     * Blend Target: White (255, 255, 255) at 50%
     * Expected Result: Pink (255, 127, 127)
     * R = (int) ( (255 * 0.5) + (255 * 0.5) ) = 255
     * G = (int) ( (255 * 0.5) + (0 * 0.5) )   = 127
     * B = (int) ( (255 * 0.5) + (0 * 0.5) )   = 127
     */
    @Test
    public void testBlendWithWhite() {
        // Arrange
        int blendR = 255;
        int blendG = 255;
        int blendB = 255; // White
        float percent = 0.5f;
        int expectedR = 255;
        int expectedG = 127;
        int expectedB = 127;

        // Act
        color.blend(blendR, blendG, blendB, percent);

        // Assert
        assertEquals("Red component should be blended towards white", expectedR, color.getRed());
        assertEquals("Green component should be blended towards white", expectedG, color.getGreen());
        assertEquals("Blue component should be blended towards white", expectedB, color.getBlue());
    }

     /**
     * Test Case 10: Blend with the same color.
     * Expected: The color should remain exactly the same, regardless of percentage.
     */
}