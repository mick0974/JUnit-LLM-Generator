import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor.
 * Contains 10 unit test cases specifically for the
 * HSLColor#brighten(float fPercent) method.
 * Tests follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 */
public class HSLColor_brightenTest {

    private HSLColor color;
    private final int HSLMAX = 255; // Maximum value for HSL components

    /**
     * Sets up a default HSLColor object before each test.
     * Initializes the color with H=120, S=150, L=100.
     */
    @Before
    public void setUp() {
        color = new HSLColor();
        // Initialize with a known, non-edge-case color
        color.initRGBbyHSL(120, 150, 100);
        assertEquals("Initial Luminance setup failed", 100, color.getLuminence());
        assertEquals("Initial Saturation setup failed", 150, color.getSaturation());
        assertEquals("Initial Hue setup failed", 120, color.getHue());
    }

    /**
     * Test brightening with a factor of 0.0f.
     * Expect no change in luminance.
     */
    @Test
    public void testBrightenZeroPercent() {
        // Arrange
        int initialLuminance = color.getLuminence();
        int initialHue = color.getHue();
        int initialSaturation = color.getSaturation();

        // Act
        color.brighten(0.0f);

        // Assert
        assertEquals("Luminance should not change with 0% factor", initialLuminance, color.getLuminence());
        // Verify other properties remain unchanged
        assertEquals("Hue should not change", initialHue, color.getHue());
        assertEquals("Saturation should not change", initialSaturation, color.getSaturation());
    }

    /**
     * Test brightening with a factor slightly greater than 1.
     * Expect luminance to increase proportionally.
     */
    @Test
    public void testBrightenSlightlyPositive() {
        // Arrange
        // Initial L = 100
        int expectedLuminance = (int) (100 * 1.1f); // 110

        // Act
        color.brighten(1.1f);

        // Assert
        assertEquals("Luminance should increase by 10%", expectedLuminance, color.getLuminence());
    }

    /**
     * Test brightening with a factor significantly greater than 1.
     * Expect luminance to increase proportionally.
     */
    @Test
    public void testBrightenSignificantlyPositive() {
        // Arrange
        // Initial L = 100
        int expectedLuminance = (int) (100 * 1.5f); // 150

        // Act
        color.brighten(1.5f);

        // Assert
        assertEquals("Luminance should increase by 50%", expectedLuminance, color.getLuminence());
    }

    /**
     * Test dimming with a factor slightly less than 1.
     * Expect luminance to decrease proportionally.
     */
    @Test
    public void testDimSlightly() {
        // Arrange
        // Initial L = 100
        int expectedLuminance = (int) (100 * 0.9f); // 90

        // Act
        color.brighten(0.9f); // Note: Method name is brighten, but factor < 1 means dimming

        // Assert
        assertEquals("Luminance should decrease by 10%", expectedLuminance, color.getLuminence());
    }

     /**
     * Test dimming with a factor significantly less than 1.
     * Expect luminance to decrease proportionally.
     */
    @Test
    public void testDimSignificantly() {
        // Arrange
        // Initial L = 100
        int expectedLuminance = (int) (100 * 0.5f); // 50

        // Act
        color.brighten(0.5f);

        // Assert
        assertEquals("Luminance should decrease by 50%", expectedLuminance, color.getLuminence());
    }

    /**
     * Test brightening that would exceed the maximum luminance (255).
     * Expect luminance to be clamped at 255.
     */

     /**
     * Test dimming that would result in a luminance below 0.
     * Expect luminance to be clamped at 0.
     */

    /**
     * Test dimming when already at minimum luminance (0).
     * Expect luminance to remain 0.
     */

    /**
     * Test brightening when already at maximum luminance (255).
     * Expect luminance to remain 255.
     */
    @Test
    public void testBrightenWhenAlreadyMax() {
        // Arrange
        color.initRGBbyHSL(120, 150, HSLMAX); // Start L=255 (Note: S must be 0 for L=255)
        color.initHSLbyRGB(255, 255, 255); // Explicitly set to white (L=255, S=0)
        assertEquals("Setup Luminance should be MAX", HSLMAX, color.getLuminence());
        assertEquals("Setup Saturation should be 0 for white", 0, color.getSaturation());

        int expectedLuminance = HSLMAX; // (int)(255 * 1.2f) = 306, clamped to 255

        // Act
        color.brighten(1.2f);

        // Assert
        assertEquals("Luminance should remain at HSLMAX when brightening max", expectedLuminance, color.getLuminence());
        assertEquals("Saturation should remain 0 for white", 0, color.getSaturation());
        assertEquals("Red should remain 255 for white", 255, color.getRed());
        assertEquals("Green should remain 255 for white", 255, color.getGreen());
        assertEquals("Blue should remain 255 for white", 255, color.getBlue());
    }

    /**
     * Test brightening a color with low initial luminance.
     * Expect luminance to increase correctly.
     */
    @Test
    public void testBrightenLowLuminance() {
         // Arrange
        color.initRGBbyHSL(120, 150, 20); // Start L=20
        int expectedLuminance = (int) (20 * 2.5f); // 50

        // Act
        color.brighten(2.5f);

        // Assert
        assertEquals("Luminance should increase from low value", expectedLuminance, color.getLuminence());
        // Optional: Check if H/S are reasonably preserved if possible
        assertEquals("Hue should remain the same", 120, color.getHue());
        assertEquals("Saturation should remain the same", 150, color.getSaturation());
    }

}