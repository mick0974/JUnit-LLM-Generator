```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor#setSaturation(int).
 * Contains 10 unit test cases targeting the public method HSLColor#setSaturation(int iToValue).
 * Tests cover typical values, boundary conditions (min/max, below min, above max),
 * setting saturation on grayscale colors, and idempotency.
 * Follows the Arrange-Act-Assert pattern.
 */
public class HSLColor_setSaturationTest {

    // Class constants from HSLColor for comparison/reference
    private final static int HSLMAX = 255;
    private final static int RGBMAX = 255;
    private final static int UNDEFINED = 170;

    private HSLColor hslColor;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     * Initializes a new HSLColor object with a known starting color (e.g., a blueish color).
     */
    @Before
    public void setUp() {
        hslColor = new HSLColor();
        // Initialize with a non-grayscale color (R=50, G=100, B=200) -> Approx H=160, S=171, L=125
        hslColor.initHSLbyRGB(50, 100, 200);
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     * (Currently no specific tear down needed, but good practice to include)
     */
    @After
    public void tearDown() {
        hslColor = null;
    }

    /**
     * Test case 1: Set saturation to a typical value within the valid range [0, 255].
     */
    @Test
    public void testSetSaturationTypicalValue() {
        // Arrange
        int initialHue = hslColor.getHue();
        int initialLum = hslColor.getLuminence();
        int targetSaturation = 128; // A mid-range value

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be set to the target value.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged.", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
        // RGB values are expected to change, asserting H, S, L is sufficient here
    }

    /**
     * Test case 2: Set saturation to the minimum valid value (0).
     * This should result in a grayscale color (R=G=B).
     */
    @Test
    public void testSetSaturationMinValueZero() {
        // Arrange
        int initialHue = hslColor.getHue();
        int initialLum = hslColor.getLuminence();
        int targetSaturation = 0;

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be set to 0.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged when setting Saturation.", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
        assertEquals("Red component should equal Green for grayscale.", hslColor.getRed(), hslColor.getGreen());
        assertEquals("Green component should equal Blue for grayscale.", hslColor.getGreen(), hslColor.getBlue());
    }

    /**
     * Test case 3: Set saturation to a value below the minimum valid value (< 0).
     * The value should be clamped to 0.
     */
    @Test
    public void testSetSaturationBelowMinValue() {
        // Arrange
        int initialHue = hslColor.getHue();
        int initialLum = hslColor.getLuminence();
        int targetSaturation = -50; // Value below minimum

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be clamped to 0.", 0, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged.", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
        assertEquals("Red component should equal Green for grayscale.", hslColor.getRed(), hslColor.getGreen());
        assertEquals("Green component should equal Blue for grayscale.", hslColor.getGreen(), hslColor.getBlue());
    }

    /**
     * Test case 4: Set saturation to the maximum valid value (HSLMAX = 255).
     */
    @Test
    public void testSetSaturationMaxValue() {
        // Arrange
        int initialHue = hslColor.getHue();
        int initialLum = hslColor.getLuminence();
        int targetSaturation = HSLMAX; // Maximum valid value

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be set to HSLMAX.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged.", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
    }

    /**
     * Test case 5: Set saturation to a value above the maximum valid value (> HSLMAX).
     * The value should be clamped to HSLMAX.
     */
    @Test
    public void testSetSaturationAboveMaxValue() {
        // Arrange
        int initialHue = hslColor.getHue();
        int initialLum = hslColor.getLuminence();
        int targetSaturation = HSLMAX + 50; // Value above maximum

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be clamped to HSLMAX.", HSLMAX, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged.", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
    }

    /**
     * Test case 6: Set saturation on an initially grayscale color (S=0) to a non-zero value.
     * This should introduce color (R, G, B components should differ, unless L=0 or L=HSLMAX).
     */
    @Test
    public void testSetSaturationOnGrayscale() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128); // Initialize to mid-gray (S=0, L=128)
        assertEquals("Initial saturation should be 0.", 0, hslColor.getSaturation());
        int initialHue = hslColor.getHue(); // Should be UNDEFINED (170) for grayscale
        int initialLum = hslColor.getLuminence(); // Should be 128
        int targetSaturation = 150;

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be set to the target value.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged (still UNDEFINED).", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
        // For mid-gray, introducing saturation should make RGB values different
        assertFalse("RGB components should differ after adding saturation to mid-gray.",
                    hslColor.getRed() == hslColor.getGreen() && hslColor.getGreen() == hslColor.getBlue());
    }

    /**
     * Test case 7: Set saturation to its current value (idempotency check).
     * No change in H, S, L, or RGB values should occur.
     */
    @Test
    public void testSetSaturationNoChange() {
        // Arrange
        int initialHue = hslColor.getHue();
        int initialSat = hslColor.getSaturation();
        int initialLum = hslColor.getLuminence();
        int initialRed = hslColor.getRed();
        int initialGreen = hslColor.getGreen();
        int initialBlue = hslColor.getBlue();

        // Act
        hslColor.setSaturation(initialSat); // Set to the same value

        // Assert
        assertEquals("Saturation should remain unchanged.", initialSat, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged.", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
        assertEquals("Red component should remain unchanged.", initialRed, hslColor.getRed());
        assertEquals("Green component should remain unchanged.", initialGreen, hslColor.getGreen());
        assertEquals("Blue component should remain unchanged.", initialBlue, hslColor.getBlue());
    }

    /**
     * Test case 8: Set saturation on black (L=0).
     * Saturation value should be updated internally, but RGB should remain black (0,0,0).
     */
    @Test
    public void testSetSaturationOnBlack() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0); // Initialize to black (L=0, S=0)
        assertEquals("Initial saturation should be 0.", 0, hslColor.getSaturation());
        assertEquals("Initial luminance should be 0.", 0, hslColor.getLuminence());
        int initialHue = hslColor.getHue(); // UNDEFINED
        int targetSaturation = 100;

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Internal saturation value should be updated.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged (UNDEFINED).", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain 0.", 0, hslColor.getLuminence());
        assertEquals("Red component should remain 0 for black.", 0, hslColor.getRed());
        assertEquals("Green component should remain 0 for black.", 0, hslColor.getGreen());
        assertEquals("Blue component should remain 0 for black.", 0, hslColor.getBlue());
    }

    /**
     * Test case 9: Set saturation on white (L=HSLMAX).
     * Saturation value should be updated internally, but RGB should remain white (255,255,255).
     */
    @Test
    public void testSetSaturationOnWhite() {
        // Arrange
        hslColor.initHSLbyRGB(RGBMAX, RGBMAX, RGBMAX); // Initialize to white (L=HSLMAX, S=0)
        assertEquals("Initial saturation should be 0.", 0, hslColor.getSaturation());
        assertEquals("Initial luminance should be HSLMAX.", HSLMAX, hslColor.getLuminence());
        int initialHue = hslColor.getHue(); // UNDEFINED
        int targetSaturation = 100;

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Internal saturation value should be updated.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged (UNDEFINED).", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain HSLMAX.", HSLMAX, hslColor.getLuminence());
        assertEquals("Red component should remain RGBMAX for white.", RGBMAX, hslColor.getRed());
        assertEquals("Green component should remain RGBMAX for white.", RGBMAX, hslColor.getGreen());
        assertEquals("Blue component should remain RGBMAX for white.", RGBMAX, hslColor.getBlue());
    }

     /**
     * Test case 10: Set saturation slightly above 0 starting from pure gray.
     * Verify that it's no longer perfectly gray (unless L=0 or L=HSLMAX).
     */
    @Test
    public void testSetSaturationFromZeroToSmallValue() {
        // Arrange
        hslColor.initHSLbyRGB(150, 150, 150); // Mid-gray
        assertEquals("Initial saturation should be 0.", 0, hslColor.getSaturation());
        int initialLum = hslColor.getLuminence();
        int initialHue = hslColor.getHue(); // UNDEFINED
        int targetSaturation = 1; // Minimal saturation

        // Act
        hslColor.setSaturation(targetSaturation);

        // Assert
        assertEquals("Saturation should be set to the target value.", targetSaturation, hslColor.getSaturation());
        assertEquals("Hue should remain unchanged (UNDEFINED).", initialHue, hslColor.getHue());
        assertEquals("Luminance should remain unchanged.", initialLum, hslColor.getLuminence());
        // Check it's not perfectly gray anymore
        assertFalse("RGB components should differ slightly after adding minimal saturation to mid-gray.",
                    hslColor.getRed() == 150 && hslColor.getGreen() == 150 && hslColor.getBlue() == 150);
        assertFalse("RGB components should differ slightly after adding minimal saturation to mid-gray (R!=G or G!=B).",
                    hslColor.getRed() == hslColor.getGreen() && hslColor.getGreen() == hslColor.getBlue());
    }
}
```