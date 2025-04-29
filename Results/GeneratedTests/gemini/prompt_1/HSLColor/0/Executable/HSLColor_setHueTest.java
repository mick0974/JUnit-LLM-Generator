import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setHue(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases.
 * Contains only valid Java code.
 */
public class HSLColor_setHueTest {

    private HSLColor color;
    // Define HSLMAX locally as it's private in HSLColor
    private final static int HSLMAX = 255;
    // Define UNDEFINED locally if needed for specific assertions
    private final static int UNDEFINED = 170;
    // Define a tolerance for potential floating point inaccuracies during conversion
    // Although the class uses ints, the intermediate calculations might mimic float behavior.
    // A small tolerance for RGB values is prudent.
    private final static int RGB_TOLERANCE = 1; // Allow +/- 1 difference in RGB components

    @Before
    public void setUp() {
        // Initialize a default color before each test
        color = new HSLColor();
        // Start with a known color, e.g., Blue (R=0, G=0, B=255)
        // H=170, S=255, L=127 (approximately, based on the class's calculations)
        color.initHSLbyRGB(0, 0, 255);
    }

    @After
    public void tearDown() {
        color = null;
    }

    /**
     * Helper method to assert RGB values within tolerance.
     */
    private void assertRGBEquals(String message, int expectedR, int expectedG, int expectedB, HSLColor actualColor) {
        assertTrue(message + " Red component mismatch.", Math.abs(expectedR - actualColor.getRed()) <= RGB_TOLERANCE);
        assertTrue(message + " Green component mismatch.", Math.abs(expectedG - actualColor.getGreen()) <= RGB_TOLERANCE);
        assertTrue(message + " Blue component mismatch.", Math.abs(expectedB - actualColor.getBlue()) <= RGB_TOLERANCE);
    }

    /**
     * Helper method to calculate expected RGB for a given HSL using the class's logic.
     */
    private HSLColor getExpectedColor(int H, int S, int L) {
        HSLColor expected = new HSLColor();
        expected.initRGBbyHSL(H, S, L);
        return expected;
    }

    // --- Test Cases ---

    /**
     * Test setting hue to a typical value (0 - Red).
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to 0.
     */
    @Test
    public void testSetHueToZero() {
        // Arrange
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();
        int targetHue = 0;
        HSLColor expected = getExpectedColor(targetHue, initialSat, initialLum);

        // Act
        color.setHue(targetHue);

        // Assert
        assertEquals("Hue should be updated", targetHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB should correspond to H=0, S=" + initialSat + ", L=" + initialLum,
                expected.getRed(), expected.getGreen(), expected.getBlue(), color);
        // Expected RGB for H=0, S=255, L=127 is roughly Red (255, 0, 0)
        assertRGBEquals("RGB should be close to Red", 255, 0, 0, color);
    }

    /**
     * Test setting hue to another typical value (85 - Green).
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to 85.
     */
    @Test
    public void testSetHueToMidValueGreen() {
        // Arrange
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();
        int targetHue = 85; // Approximately Green
        HSLColor expected = getExpectedColor(targetHue, initialSat, initialLum);

        // Act
        color.setHue(targetHue);

        // Assert
        assertEquals("Hue should be updated", targetHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB should correspond to H=85, S=" + initialSat + ", L=" + initialLum,
                expected.getRed(), expected.getGreen(), expected.getBlue(), color);
         // Expected RGB for H=85, S=255, L=127 is roughly Green (0, 255, 0)
        assertRGBEquals("RGB should be close to Green", 0, 255, 0, color);
    }

    /**
     * Test setting hue to the maximum value (HSLMAX).
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to 255.
     */
    @Test
    public void testSetHueToMax() {
        // Arrange
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();
        int targetHue = HSLMAX; // 255
        HSLColor expected = getExpectedColor(targetHue, initialSat, initialLum);

        // Act
        color.setHue(targetHue);

        // Assert
        assertEquals("Hue should be updated", targetHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB should correspond to H=255, S=" + initialSat + ", L=" + initialLum,
                expected.getRed(), expected.getGreen(), expected.getBlue(), color);
        // Expected RGB for H=255, S=255, L=127 is very close to Red (255, 0, 0)
        assertRGBEquals("RGB should be close to Red", 255, 0, 0, color);
    }

    /**
     * Test setting hue to a value just below 0 (-1).
     * Should wrap around to HSLMAX (255).
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to -1.
     */

    /**
     * Test setting hue to a value just above HSLMAX (256).
     * Should wrap around to 0.
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to 256.
     */

    /**
     * Test setting hue to a large negative value (-300).
     * Should wrap around correctly. -300 + 2*255 = 210.
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to -300.
     */
    @Test
    public void testSetHueLargeNegativeWrapAround() {
        // Arrange
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();
        int inputHue = -300;
        // Expected hue calculation: -300 mod 255 handled by the loop
        // -300 + 255 = -45
        // -45 + 255 = 210
        int expectedHue = 210;
        HSLColor expected = getExpectedColor(expectedHue, initialSat, initialLum);

        // Act
        color.setHue(inputHue);

        // Assert
        assertEquals("Hue should wrap around correctly", expectedHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB should correspond to wrapped H=" + expectedHue,
                expected.getRed(), expected.getGreen(), expected.getBlue(), color);
    }

     /**
     * Test setting hue to a large positive value (520).
     * Should wrap around correctly. 520 - 2*255 = 10.
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to 520.
     */
    @Test
    public void testSetHueLargePositiveWrapAround() {
        // Arrange
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();
        int inputHue = 520;
         // Expected hue calculation: 520 mod 255 handled by the loop
         // 520 - 255 = 265
         // 265 - 255 = 10
        int expectedHue = 10;
        HSLColor expected = getExpectedColor(expectedHue, initialSat, initialLum);

        // Act
        color.setHue(inputHue);

        // Assert
        assertEquals("Hue should wrap around correctly", expectedHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB should correspond to wrapped H=" + expectedHue,
                expected.getRed(), expected.getGreen(), expected.getBlue(), color);
    }

    /**
     * Test setting hue on a grayscale color (S=0).
     * The color should remain grayscale (R=G=B).
     * Starts from Gray (R=128, G=128, B=128).
     * Sets Hue to 100.
     */
    @Test
    public void testSetHueOnGrayscale() {
        // Arrange
        color.initHSLbyRGB(128, 128, 128); // S=0, L=127 (approx)
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();
        int initialR = color.getRed();
        int initialG = color.getGreen();
        int initialB = color.getBlue();
        int targetHue = 100; // Arbitrary hue

        assertEquals("Initial saturation should be 0 for grayscale", 0, initialSat);
        //assertEquals("Initial hue should be UNDEFINED", UNDEFINED, color.getHue());

        // Act
        color.setHue(targetHue);

        // Assert
        // When S=0, initRGBbyHSL ignores H, so pHue might not be updated internally
        // according to the logic flow, but the *color* should not change.
        // However, the setHue method *does* update pHue internally before calling initRGBbyHSL.
        assertEquals("Internal Hue should be updated", targetHue, color.getHue());
        assertEquals("Saturation should remain 0", 0, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        // Because S=0, the RGB values should remain unchanged, based on Luminance only.
        assertRGBEquals("RGB should remain grayscale", initialR, initialG, initialB, color);
    }

    /**
     * Test setting hue on a color with non-maximum saturation and luminance.
     * Starts with a less saturated, lighter color (e.g., pale cyan).
     * Sets Hue to a different value (e.g., yellow).
     */
    @Test
    public void testSetHueOnDesaturatedColor() {
        // Arrange: Start with H=150, S=128, L=192 (pale cyan/greenish)
        color.initRGBbyHSL(150, 128, 192);
        int initialSat = color.getSaturation(); // Should be 128
        int initialLum = color.getLuminence(); // Should be 192
        int targetHue = 42; // Approximately Yellow/Orange
        HSLColor expected = getExpectedColor(targetHue, initialSat, initialLum);

        assertEquals("Initial Saturation setup incorrect", 128, initialSat);
        assertEquals("Initial Luminance setup incorrect", 192, initialLum);
        assertEquals("Initial Hue setup incorrect", 150, color.getHue());


        // Act
        color.setHue(targetHue);

        // Assert
        assertEquals("Hue should be updated", targetHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB should correspond to H=" + targetHue + ", S=" + initialSat + ", L=" + initialLum,
                expected.getRed(), expected.getGreen(), expected.getBlue(), color);
    }

     /**
     * Test setting hue multiple times, including wrap-around.
     * Starts from Blue (H=170, S=255, L=127).
     * Sets Hue to 300 (wraps to 45).
     * Then sets Hue to -10 (wraps to 245).
     */
    @Test
    public void testSetHueMultipleTimesWithWrap() {
        // Arrange
        int initialSat = color.getSaturation();
        int initialLum = color.getLuminence();

        // Act 1: Set Hue to 300 (wraps to 45)
        color.setHue(300);
        int intermediateHue = 45; // 300 - 255
        HSLColor expected1 = getExpectedColor(intermediateHue, initialSat, initialLum);

        // Assert 1
        assertEquals("Hue should be 45 after first set", intermediateHue, color.getHue());
        assertEquals("Saturation should remain unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should remain unchanged", initialLum, color.getLuminence());
        assertRGBEquals("RGB mismatch after first set H=45",
                expected1.getRed(), expected1.getGreen(), expected1.getBlue(), color);

        // Act 2: Set Hue to -10 (wraps to 245)
        color.setHue(-10);
        int finalHue = 245; // -10 + 255
        HSLColor expected2 = getExpectedColor(finalHue, initialSat, initialLum);

        // Assert 2
        assertEquals("Hue should be 245 after second set", finalHue, color.getHue());
        assertEquals("Saturation should still be unchanged", initialSat, color.getSaturation());
        assertEquals("Luminance should still be unchanged", initialLum, color.getLuminence());
         assertRGBEquals("RGB mismatch after second set H=245",
                expected2.getRed(), expected2.getGreen(), expected2.getBlue(), color);
    }
}