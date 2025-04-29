```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor focusing on the setLuminence method.
 * Contains 10 unit test cases for the HSLColor#setLuminence(int iToValue) method.
 * Tests follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 */
public class HSLColor_setLuminenceTest {

    private HSLColor hslColor;
    private final int HSLMAX = 255;
    private final int initialHue = 170; // ~Blue
    private final int initialSat = 128; // Medium saturation
    private final int initialLum = 128; // Medium luminance
    // Expected initial RGB for H=170, S=128, L=128 based on HSLColor logic
    private final int initialRed = 64;
    private final int initialGreen = 64;
    private final int initialBlue = 192;


    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Initializes a HSLColor object with a known HSL value.
     */
    @Before
    public void setUp() {
        hslColor = new HSLColor();
        // Initialize with H=170, S=128, L=128 which should give R=64, G=64, B=192
        hslColor.initRGBbyHSL(initialHue, initialSat, initialLum);
        // Verify initial state (optional but good practice)
        assertEquals("Initial Luminance setup failed", initialLum, hslColor.getLuminence());
        assertEquals("Initial Saturation setup failed", initialSat, hslColor.getSaturation());
        assertEquals("Initial Hue setup failed", initialHue, hslColor.getHue());
        assertEquals("Initial Red setup failed", initialRed, hslColor.getRed());
        assertEquals("Initial Green setup failed", initialGreen, hslColor.getGreen());
        assertEquals("Initial Blue setup failed", initialBlue, hslColor.getBlue());
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown() {
        hslColor = null;
    }

    /**
     * Test case 1: Setting luminance to a typical mid-range value.
     */
    @Test
    public void testSetLuminenceToMidValue() {
        // Arrange
        int targetLum = 100;
        // Expected RGB for H=170, S=128, L=100 (calculated based on HSLColor logic)
        int expectedRed = 50;
        int expectedGreen = 50;
        int expectedBlue = 150;

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated", targetLum, hslColor.getLuminence());
        assertEquals("Hue should remain unchanged", initialHue, hslColor.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, hslColor.getSaturation());
        assertEquals("Red value is incorrect after setting luminance", expectedRed, hslColor.getRed());
        assertEquals("Green value is incorrect after setting luminance", expectedGreen, hslColor.getGreen());
        assertEquals("Blue value is incorrect after setting luminance", expectedBlue, hslColor.getBlue());
    }

    /**
     * Test case 2: Setting luminance to the minimum value (0).
     * Should result in black color (R=0, G=0, B=0).
     */
    @Test
    public void testSetLuminenceToZero() {
        // Arrange
        int targetLum = 0;
        int expectedRed = 0;
        int expectedGreen = 0;
        int expectedBlue = 0;

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated to 0", targetLum, hslColor.getLuminence());
        // Note: When L=0, S becomes 0 and H becomes UNDEFINED in the RGB->HSL conversion,
        // but setting L should preserve the original H, S internally for the HSL->RGB calculation.
        // Let's verify the resulting RGB which is the primary effect.
        assertEquals("Red should be 0 for L=0", expectedRed, hslColor.getRed());
        assertEquals("Green should be 0 for L=0", expectedGreen, hslColor.getGreen());
        assertEquals("Blue should be 0 for L=0", expectedBlue, hslColor.getBlue());
         // Verify internal H/S are preserved by the set call itself
        assertEquals("Hue should seem unchanged internally after set", initialHue, hslColor.getHue());
        assertEquals("Saturation should seem unchanged internally after set", initialSat, hslColor.getSaturation());
    }

    /**
     * Test case 3: Setting luminance to the maximum value (HSLMAX).
     * Should result in white color (R=255, G=255, B=255).
     */
    @Test
    public void testSetLuminenceToMax() {
        // Arrange
        int targetLum = HSLMAX; // 255
        int expectedRgb = HSLMAX; // 255

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated to HSLMAX", targetLum, hslColor.getLuminence());
        // Similar to L=0, when L=HSLMAX, S becomes 0 and H is undefined in RGB->HSL,
        // but setting L should preserve original H, S for the HSL->RGB calculation.
        assertEquals("Red should be MAX for L=MAX", expectedRgb, hslColor.getRed());
        assertEquals("Green should be MAX for L=MAX", expectedRgb, hslColor.getGreen());
        assertEquals("Blue should be MAX for L=MAX", expectedRgb, hslColor.getBlue());
        // Verify internal H/S are preserved by the set call itself
        assertEquals("Hue should seem unchanged internally after set", initialHue, hslColor.getHue());
        assertEquals("Saturation should seem unchanged internally after set", initialSat, hslColor.getSaturation());
    }

    /**
     * Test case 4: Setting luminance to a value below the minimum (negative).
     * Should be clamped to 0, resulting in black.
     */
    @Test
    public void testSetLuminenceBelowZero() {
        // Arrange
        int targetLum = -50;
        int expectedClampedLum = 0;
        int expectedRgb = 0;

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be clamped to 0", expectedClampedLum, hslColor.getLuminence());
        assertEquals("Red should be 0 for L clamped to 0", expectedRgb, hslColor.getRed());
        assertEquals("Green should be 0 for L clamped to 0", expectedRgb, hslColor.getGreen());
        assertEquals("Blue should be 0 for L clamped to 0", expectedRgb, hslColor.getBlue());
    }

    /**
     * Test case 5: Setting luminance to a value above the maximum.
     * Should be clamped to HSLMAX, resulting in white.
     */
    @Test
    public void testSetLuminenceAboveMax() {
        // Arrange
        int targetLum = HSLMAX + 50; // e.g., 305
        int expectedClampedLum = HSLMAX; // 255
        int expectedRgb = HSLMAX; // 255

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be clamped to HSLMAX", expectedClampedLum, hslColor.getLuminence());
        assertEquals("Red should be MAX for L clamped to MAX", expectedRgb, hslColor.getRed());
        assertEquals("Green should be MAX for L clamped to MAX", expectedRgb, hslColor.getGreen());
        assertEquals("Blue should be MAX for L clamped to MAX", expectedRgb, hslColor.getBlue());
    }

    /**
     * Test case 6: Setting luminance on an initially greyscale color.
     * The color should remain greyscale (R=G=B).
     */
    @Test
    public void testSetLuminenceOnGreyscale() {
        // Arrange
        hslColor.initRGBbyHSL(100, 0, 128); // Initial grey (R=128, G=128, B=128)
        int targetLum = 50;
        int expectedRgb = 50; // Greyscale R=G=B should match new luminance mapping

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated", targetLum, hslColor.getLuminence());
        // When S=0, initRGBbyHSL sets R=G=B based on L.
        assertEquals("Red should be equal to Green for greyscale", hslColor.getRed(), hslColor.getGreen());
        assertEquals("Green should be equal to Blue for greyscale", hslColor.getGreen(), hslColor.getBlue());
        assertEquals("RGB value should match expected grey level", expectedRgb, hslColor.getRed());
        // Hue remains the same internally but Saturation should still be 0
        assertEquals("Saturation should remain 0", 0, hslColor.getSaturation());
    }

    /**
     * Test case 7: Setting luminance on a fully saturated color.
     */
    @Test
    public void testSetLuminenceOnSaturatedColor() {
        // Arrange
        // Initial: H=170, S=255, L=128 -> R=1, G=1, B=255 (Calculated)
        hslColor.initRGBbyHSL(170, 255, 128);
        assertEquals("Saturated Red setup failed", 1, hslColor.getRed());
        assertEquals("Saturated Green setup failed", 1, hslColor.getGreen());
        assertEquals("Saturated Blue setup failed", 255, hslColor.getBlue());

        int targetLum = 192;
        // Expected RGB for H=170, S=255, L=192 -> R=129, G=129, B=255 (Calculated)
        int expectedRed = 129;
        int expectedGreen = 129;
        int expectedBlue = 255;

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated", targetLum, hslColor.getLuminence());
        assertEquals("Hue should remain unchanged", 170, hslColor.getHue());
        assertEquals("Saturation should remain unchanged", 255, hslColor.getSaturation());
        assertEquals("Red value is incorrect after setting luminance on saturated color", expectedRed, hslColor.getRed());
        assertEquals("Green value is incorrect after setting luminance on saturated color", expectedGreen, hslColor.getGreen());
        assertEquals("Blue value is incorrect after setting luminance on saturated color", expectedBlue, hslColor.getBlue());
    }

    /**
     * Test case 8: Setting luminance to its current value.
     * No change in HSL or RGB values should occur.
     */
    @Test
    public void testSetLuminenceToCurrentValue() {
        // Arrange
        int targetLum = initialLum; // 128

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should remain unchanged", initialLum, hslColor.getLuminence());
        assertEquals("Hue should remain unchanged", initialHue, hslColor.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, hslColor.getSaturation());
        assertEquals("Red should remain unchanged", initialRed, hslColor.getRed());
        assertEquals("Green should remain unchanged", initialGreen, hslColor.getGreen());
        assertEquals("Blue should remain unchanged", initialBlue, hslColor.getBlue());
    }

    /**
     * Test case 9: Setting luminance when starting from black (L=0).
     * Should correctly calculate new RGB based on original H/S if available,
     * or become greyscale if S was 0.
     */
    @Test
    public void testSetLuminenceFromBlack() {
        // Arrange
        // Start with Black (L=0), but retain original H/S if possible.
        // initRGBbyHSL internally uses the H/S fields, so let's set them first.
        hslColor = new HSLColor();
        hslColor.initRGBbyHSL(initialHue, initialSat, 0); // L=0 -> R=0, G=0, B=0
        assertEquals("Luminance setup failed", 0, hslColor.getLuminence());
        assertEquals("Red setup failed", 0, hslColor.getRed());

        int targetLum = 128;
        // Expected RGB should be the same as our initial setup (H=170, S=128, L=128)
        int expectedRed = initialRed;   // 64
        int expectedGreen = initialGreen; // 64
        int expectedBlue = initialBlue;  // 192

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated", targetLum, hslColor.getLuminence());
        assertEquals("Hue should be restored", initialHue, hslColor.getHue());
        assertEquals("Saturation should be restored", initialSat, hslColor.getSaturation());
        assertEquals("Red value is incorrect after setting luminance from black", expectedRed, hslColor.getRed());
        assertEquals("Green value is incorrect after setting luminance from black", expectedGreen, hslColor.getGreen());
        assertEquals("Blue value is incorrect after setting luminance from black", expectedBlue, hslColor.getBlue());
    }

    /**
     * Test case 10: Setting luminance when starting from white (L=HSLMAX).
     * Should correctly calculate new RGB based on original H/S if available,
     * or become greyscale if S was 0.
     */
    @Test
    public void testSetLuminenceFromWhite() {
        // Arrange
        // Start with White (L=255), retaining original H/S.
        hslColor = new HSLColor();
        hslColor.initRGBbyHSL(initialHue, initialSat, HSLMAX); // L=255 -> R=255, G=255, B=255
        assertEquals("Luminance setup failed", HSLMAX, hslColor.getLuminence());
        assertEquals("Red setup failed", HSLMAX, hslColor.getRed());

        int targetLum = 128;
         // Expected RGB should be the same as our initial setup (H=170, S=128, L=128)
        int expectedRed = initialRed;   // 64
        int expectedGreen = initialGreen; // 64
        int expectedBlue = initialBlue;  // 192

        // Act
        hslColor.setLuminence(targetLum);

        // Assert
        assertEquals("Luminance should be updated", targetLum, hslColor.getLuminence());
        assertEquals("Hue should be restored", initialHue, hslColor.getHue());
        assertEquals("Saturation should be restored", initialSat, hslColor.getSaturation());
        assertEquals("Red value is incorrect after setting luminance from white", expectedRed, hslColor.getRed());
        assertEquals("Green value is incorrect after setting luminance from white", expectedGreen, hslColor.getGreen());
        assertEquals("Blue value is incorrect after setting luminance from white", expectedBlue, hslColor.getBlue());
    }
}
```