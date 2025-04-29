import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor#getHue().
 * Contains unit test cases focusing on retrieving the Hue component
 * after initializing the color object using different methods (RGB, HSL)
 * and covers various color types including primary, secondary, greyscale,
 * black, and white.
 * Follows the Arrange-Act-Assert pattern.
 */
public class HSLColor_getHueTest {

    private HSLColor hslColor;
    private final static int HSLMAX = 255;
    private final static int UNDEFINED = 170; // As defined in HSLColor

    // Tolerance for integer division/rounding discrepancies if needed,
    // but aiming for exact matches based on the implementation.
    private static final double DELTA = 0.99; // Use small delta for int comparisons due to potential rounding

    @Before
    public void setUp() {
        // Arrange: Create a new HSLColor instance before each test
        hslColor = new HSLColor();
    }

    /**
     * Test getHue() after initializing with pure Red (RGB).
     * Expected Hue: 0
     */
    @Test
    public void testGetHue_WhenRGBIsRed() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0);
        int expectedHue = 0;

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("Hue for pure Red should be 0", expectedHue, actualHue);
    }

    /**
     * Test getHue() after initializing with pure Green (RGB).
     * Expected Hue: HSLMAX / 3 = 85
     */
    @Test
    public void testGetHue_WhenRGBIsGreen() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 0);
        // Expected Hue calculation based on the formula: (HSLMAX / 3) + RDelta - BDelta
        // cMax=G=255, cMin=0, cMinus=255, cPlus=255
        // pLum = ((255*255)+255)/(2*255) = 128
        // pSat = (int)(((255*255)+0.5)/(2*255-255)) = 255 (since L > HSLMAX/2)
        // RDelta = (int)((((255-0)*(255/6))+0.5)/255) = 42
        // GDelta = (int)((((255-255)*(255/6))+0.5)/255) = 0
        // BDelta = (int)((((255-0)*(255/6))+0.5)/255) = 42
        // pHue = (HSLMAX / 3) + RDelta - BDelta = (255 / 3) + 42 - 42 = 85
        int expectedHue = 85;


        // Act
        int actualHue = hslColor.getHue();

        // Assert
        // Use delta due to integer division in formula (255/3) = 85
        assertEquals("Hue for pure Green should be close to HSLMAX/3", expectedHue, actualHue, DELTA);
    }

    /**
     * Test getHue() after initializing with pure Blue (RGB).
     * Expected Hue: (2 * HSLMAX) / 3 = 170
     */
    @Test
    public void testGetHue_WhenRGBIsBlue() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 255);
        // Expected Hue calculation: ((2 * HSLMAX) / 3) + GDelta - RDelta
        // cMax=B=255, cMin=0, cMinus=255, cPlus=255
        // pLum = 128, pSat = 255
        // RDelta = 42, GDelta = 42, BDelta = 0
        // pHue = ((2 * 255) / 3) + GDelta - RDelta = (510 / 3) + 42 - 42 = 170
        int expectedHue = 170;

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("Hue for pure Blue should be close to 2*HSLMAX/3", expectedHue, actualHue, DELTA);
    }

    /**
     * Test getHue() after initializing with Yellow (RGB).
     * Expected Hue: HSLMAX / 6 = 42
     */
    @Test
    public void testGetHue_WhenRGBIsYellow() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 0);
        // Expected Hue calculation: BDelta - GDelta (since cMax = R=255 or G=255, let's trace)
        // cMax=255, cMin=0, cMinus=255, cPlus=510
        // pLum = ((510*255)+255)/(2*255) = (510*255+255)/510 = 255/2 + 0.5 = 128
        // pSat = 255 (since L > HSLMAX/2)
        // RDelta = (int)((((255-255)*(255/6))+0.5)/255) = 0
        // GDelta = (int)((((255-255)*(255/6))+0.5)/255) = 0
        // BDelta = (int)((((255-0)*(255/6))+0.5)/255) = 42
        // If cMax == R: pHue = BDelta - GDelta = 42 - 0 = 42
        // If cMax == G: pHue = (HSLMAX / 3) + RDelta - BDelta = 85 + 0 - 42 = 43
        // The code uses iMax which might return R or G first depending on implementation, but logically Yellow is H=60deg -> HSLMAX/6.
        // Let's assume standard iMax picks R first or the formula yields the expected color wheel value.
        int expectedHue = 42; // HSLMAX / 6 = 255 / 6 = 42.5 -> 42 (integer division)

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        // Allow for slight variation due to integer math and cMax tie-breaking.
        assertTrue("Hue for Yellow should be close to HSLMAX/6", Math.abs(expectedHue - actualHue) <= DELTA);
     }

    /**
     * Test getHue() after initializing with Cyan (RGB).
     * Expected Hue: HSLMAX / 2 = 127
     */
    @Test
    public void testGetHue_WhenRGBIsCyan() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 255);
        // Expected Hue: H = 180 deg -> HSLMAX / 2
        // cMax=G=255 (or B=255), cMin=0, cMinus=255, cPlus=510
        // pLum = 128, pSat = 255
        // RDelta = 42, GDelta = 0, BDelta = 0
        // If cMax == G: pHue = (HSLMAX / 3) + RDelta - BDelta = 85 + 42 - 0 = 127
        // If cMax == B: pHue = ((2 * HSLMAX) / 3) + GDelta - RDelta = 170 + 0 - 42 = 128
        int expectedHue = 127; // Expecting 127 or 128 based on iMax tie-break

        // Act
        int actualHue = hslColor.getHue();

        // Assert
         assertTrue("Hue for Cyan should be close to HSLMAX/2", Math.abs(expectedHue - actualHue) <= DELTA || Math.abs(128 - actualHue) <= DELTA);
    }

    /**
     * Test getHue() after initializing with Magenta (RGB).
     * Expected Hue: (5 * HSLMAX) / 6 = 212
     */
    @Test
    public void testGetHue_WhenRGBIsMagenta() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 255);
        // Expected Hue: H = 300 deg -> 5 * HSLMAX / 6
        // cMax=R=255 (or B=255), cMin=0, cMinus=255, cPlus=510
        // pLum = 128, pSat = 255
        // RDelta = 0, GDelta = 42, BDelta = 0
        // If cMax == R: pHue = BDelta - GDelta = 0 - 42 = -42. Then pHue = -42 + HSLMAX = -42 + 255 = 213
        // If cMax == B: pHue = ((2 * HSLMAX) / 3) + GDelta - RDelta = 170 + 42 - 0 = 212
        int expectedHue = 212; // Expecting 212 or 213 based on iMax tie-break and negative hue adjustment

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertTrue("Hue for Magenta should be close to 5*HSLMAX/6", Math.abs(expectedHue - actualHue) <= DELTA || Math.abs(213 - actualHue) <= DELTA);
    }

    /**
     * Test getHue() after initializing with a Greyscale color (RGB).
     * Expected Hue: UNDEFINED (170)
     */
    @Test
    public void testGetHue_WhenRGBIsGreyscale() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128);
        int expectedHue = UNDEFINED;

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("Hue for Greyscale should be UNDEFINED", expectedHue, actualHue);
    }

    /**
     * Test getHue() after initializing with Black (RGB).
     * Expected Hue: UNDEFINED (170)
     */
    @Test
    public void testGetHue_WhenRGBIsBlack() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0);
        int expectedHue = UNDEFINED;

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("Hue for Black should be UNDEFINED", expectedHue, actualHue);
    }

    /**
     * Test getHue() after initializing with White (RGB).
     * Expected Hue: UNDEFINED (170)
     */
    @Test
    public void testGetHue_WhenRGBIsWhite() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 255);
        int expectedHue = UNDEFINED;

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("Hue for White should be UNDEFINED", expectedHue, actualHue);
    }

    /**
     * Test getHue() after initializing directly with HSL values.
     */
    @Test
    public void testGetHue_WhenInitializedWithHSL() {
        // Arrange
        int expectedHue = 100;
        int saturation = 200;
        int luminence = 150;
        hslColor.initRGBbyHSL(expectedHue, saturation, luminence); // This sets pHue directly

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("getHue should return the value set by initRGBbyHSL", expectedHue, actualHue);
    }
}