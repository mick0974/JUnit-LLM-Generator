import org.junit.Before;
// No need for @After in this case as no external resources are used
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor#reverseColor().
 * Contains 10 unit test cases for the HSLColor#reverseColor() method.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Uses JUnit 4 annotations.
 */
public class HSLColor_reverseColorTest {

    private HSLColor color;
    private final static int HSLMAX = 255;
    private final static int UNDEFINED = 170; // As defined in HSLColor

    // Delta for comparing integer results where precision might be slightly off
    // Although in this case, hue calculations should be exact integers.
    private final static int DELTA = 0;

    @Before
    public void setUp() {
        // Arrange: Create a new HSLColor object before each test
        color = new HSLColor();
    }

    /**
     * Test reversing color with a low hue value (Red).
     * Expected: Hue changes by +127. Saturation and Luminance remain the same.
     */
    @Test
    public void testReverseColorLowHue() {
        // Arrange: Initialize with a color having low hue (e.g., close to Red H=0)
        int initialHue = 30;
        int initialSat = 200;
        int initialLum = 100;
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check if hue is reversed, saturation and luminance unchanged
        int expectedHue = initialHue + (HSLMAX / 2); // 30 + 127 = 157
        assertEquals("Hue should be reversed", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence(), DELTA);
    }

    /**
     * Test reversing color with a high hue value causing wrap-around.
     * Expected: Hue changes by +127 and wraps around HSLMAX. Sat/Lum unchanged.
     */
    @Test
    public void testReverseColorHighHueWrapAround() {
        // Arrange: Initialize with a color having high hue (e.g., close to Magenta H=213)
        int initialHue = 200;
        int initialSat = 200;
        int initialLum = 100;
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check if hue is reversed with wrap-around
        // Expected: 200 + 127 = 327. 327 > 255. New Hue = 327 - 255 = 72
        int expectedHue = (initialHue + (HSLMAX / 2)) - HSLMAX;
        assertEquals("Hue should be reversed and wrap around", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence(), DELTA);
    }

    /**
     * Test reversing color exactly at the halfway point (Hue = 127).
     * Expected: Hue becomes 254 (127 + 127).
     */
    @Test
    public void testReverseColorMidHueLow() {
        // Arrange: Initialize with hue at HSLMAX / 2 - 1 = 127
        int initialHue = HSLMAX / 2; // 127
        int initialSat = 150;
        int initialLum = 180;
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check the new hue
        int expectedHue = initialHue + (HSLMAX / 2); // 127 + 127 = 254
        assertEquals("Hue should be reversed", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence(), DELTA);
    }

    /**
     * Test reversing color just past the halfway point (Hue = 128).
     * Expected: Hue becomes 255 (128 + 127).
     */
    @Test
    public void testReverseColorMidHueHigh() {
        // Arrange: Initialize with hue at HSLMAX / 2 + 1 = 128
        int initialHue = (HSLMAX / 2) + 1; // 128
        int initialSat = 150;
        int initialLum = 180;
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check the new hue
        int expectedHue = initialHue + (HSLMAX / 2); // 128 + 127 = 255
        assertEquals("Hue should be reversed", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence(), DELTA);
    }

    /**
     * Test reversing color with Hue = 0.
     * Expected: Hue becomes 127.
     */
    @Test
    public void testReverseColorHueZero() {
        // Arrange: Initialize with hue 0
        int initialHue = 0;
        int initialSat = 255;
        int initialLum = 127; // Pure Red
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check the new hue
        int expectedHue = initialHue + (HSLMAX / 2); // 0 + 127 = 127
        assertEquals("Hue should be reversed", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence(), DELTA);
    }

    /**
     * Test reversing color with Hue = HSLMAX (255).
     * Expected: Hue becomes 127 (wraps around).
     */
    @Test
    public void testReverseColorHueMax() {
        // Arrange: Initialize with hue HSLMAX
        int initialHue = HSLMAX; // 255
        int initialSat = 200;
        int initialLum = 100;
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check the new hue after wrap-around
        // Expected: 255 + 127 = 382. 382 > 255. New Hue = 382 - 255 = 127
        int expectedHue = (initialHue + (HSLMAX / 2)) - HSLMAX;
        assertEquals("Hue should be reversed and wrap around", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence(), DELTA);
    }

    /**
     * Test reversing color for a greyscale color (Saturation = 0).
     * Expected: No change in visual color (RGB values), Saturation remains 0.
     * The internal hue state might change, but initRGBbyHSL ignores H when S=0.
     */
    @Test
    public void testReverseColorGreyscale() {
        // Arrange: Initialize with a grey color (S=0)
        int initialR = 128;
        int initialG = 128;
        int initialB = 128;
        color.initHSLbyRGB(initialR, initialG, initialB);
        assertEquals("Initial saturation should be 0 for greyscale", 0, color.getSaturation());
        // Hue is UNDEFINED (170) for greyscale in this implementation

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Saturation should still be 0, Luminance unchanged, RGB unchanged
        assertEquals("Saturation should remain 0", 0, color.getSaturation(), DELTA);
        // Check Luminance remains unchanged
        // Calculate expected Lum: cMax=128, cMin=128, cPlus=256. Lum = ((256*255)+255)/(2*255) = (256*255+255)/510 = 65535/510 = 128.5 -> 128
        assertEquals("Luminance should remain unchanged", 128, color.getLuminence(), DELTA);
        // Check that RGB values remain the same as initRGBbyHSL ignores Hue when Sat=0
        assertEquals("Red should remain unchanged", initialR, color.getRed());
        assertEquals("Green should remain unchanged", initialG, color.getGreen());
        assertEquals("Blue should remain unchanged", initialB, color.getBlue());
        // Note: The internal pHue will change (170 + 127 = 297 -> wraps to 42), but it doesn't affect the grey color.
        // Asserting pHue is not useful here as the observable color is the key.
    }

    /**
     * Test reversing color for black (Luminance = 0).
     * Expected: No change in visual color (RGB remains 0,0,0). Hue changes, Sat/Lum unchanged.
     */
    @Test
    public void testReverseColorBlack() {
        // Arrange: Initialize with black (L=0)
        int initialHue = 50; // Arbitrary hue
        int initialSat = 150; // Arbitrary saturation
        int initialLum = 0;   // Black
        color.initRGBbyHSL(initialHue, initialSat, initialLum);
        assertEquals("Initial luminance should be 0", 0, color.getLuminence());
        assertEquals("Initial Red should be 0", 0, color.getRed());
        assertEquals("Initial Green should be 0", 0, color.getGreen());
        assertEquals("Initial Blue should be 0", 0, color.getBlue());

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Luminance and Saturation unchanged, Hue changed, RGB still black.
        int expectedHue = initialHue + (HSLMAX / 2); // 50 + 127 = 177
        assertEquals("Hue should be reversed", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain 0", 0, color.getLuminence(), DELTA);
        assertEquals("Red should remain 0", 0, color.getRed());
        assertEquals("Green should remain 0", 0, color.getGreen());
        assertEquals("Blue should remain 0", 0, color.getBlue());
    }

    /**
     * Test reversing color for white (Luminance = 255, Saturation = 0).
     * Expected: No change in visual color (RGB remains 255,255,255).
     * Behaves like greyscale test because Saturation is 0.
     */
    @Test
    public void testReverseColorWhite() {
        // Arrange: Initialize with white (L=255, S=0)
        int initialR = 255;
        int initialG = 255;
        int initialB = 255;
        color.initHSLbyRGB(initialR, initialG, initialB);
        assertEquals("Initial saturation should be 0 for white", 0, color.getSaturation());
        assertEquals("Initial luminance should be 255 for white", HSLMAX, color.getLuminence());
        // Hue is UNDEFINED (170)

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Saturation should still be 0, Luminance unchanged, RGB unchanged
        assertEquals("Saturation should remain 0", 0, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain 255", HSLMAX, color.getLuminence(), DELTA);
        assertEquals("Red should remain 255", initialR, color.getRed());
        assertEquals("Green should remain 255", initialG, color.getGreen());
        assertEquals("Blue should remain 255", initialB, color.getBlue());
    }

    /**
     * Test reversing color with maximum saturation and luminance (not pure white).
     * Expected: Hue changes, Saturation and Luminance remain at max.
     */
    @Test
    public void testReverseColorMaxSatMaxLum() {
        // Arrange: Initialize with max saturation and luminance, arbitrary hue
        int initialHue = 100;
        int initialSat = HSLMAX; // 255
        int initialLum = HSLMAX; // 255
        color.initRGBbyHSL(initialHue, initialSat, initialLum);

        // Act: Reverse the color
        color.reverseColor();

        // Assert: Check if hue is reversed, saturation and luminance unchanged
        int expectedHue = initialHue + (HSLMAX / 2); // 100 + 127 = 227
        assertEquals("Hue should be reversed", expectedHue, color.getHue(), DELTA);
        assertEquals("Saturation should remain unchanged at max", HSLMAX, color.getSaturation(), DELTA);
        assertEquals("Luminance should remain unchanged at max", HSLMAX, color.getLuminence(), DELTA);
    }
}