import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor#getLuminence().
 * Contains 10 unit test cases focusing on retrieving the luminance value
 * after initializing the HSLColor object using various RGB or HSL values.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 */
public class HSLColor_getLuminenceTest {

    private HSLColor hslColor;
    private final static int HSLMAX = 255; // Assuming HSLMAX is 255 as per HSLColor class

    @Before
    public void setUp() {
        // Arrange: Create a new HSLColor instance before each test
        hslColor = new HSLColor();
    }

    /**
     * Test case 1: Luminance for black color (RGB: 0, 0, 0).
     * Expected Luminance: 0 (minimum).
     */
    @Test
    public void testGetLuminence_BlackRGB() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0);
        int expectedLuminence = 0;

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for black (0,0,0) should be 0", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 2: Luminance for white color (RGB: 255, 255, 255).
     * Expected Luminance: 255 (maximum).
     */
    @Test
    public void testGetLuminence_WhiteRGB() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 255);
        int expectedLuminence = HSLMAX;

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for white (255,255,255) should be HSLMAX", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 3: Luminance for mid-gray color (RGB: 128, 128, 128).
     * Expected Luminance: 128.
     */
    @Test
    public void testGetLuminence_MidGrayRGB() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128);
        int expectedLuminence = 128; // Calculated: ((128+128)*255 + 255) / (2*255) = (256*255+255)/510 = 65535/510 = 128

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for mid-gray (128,128,128) should be 128", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 4: Luminance for pure red color (RGB: 255, 0, 0).
     * Expected Luminance: 128.
     */
    @Test
    public void testGetLuminence_PureRedRGB() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0);
        int expectedLuminence = 128; // Calculated: ((255+0)*255 + 255) / (2*255) = (255*255+255)/510 = 65280/510 = 128

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for pure red (255,0,0) should be 128", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 5: Luminance for pure green color (RGB: 0, 255, 0).
     * Expected Luminance: 128.
     */
    @Test
    public void testGetLuminence_PureGreenRGB() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 0);
        int expectedLuminence = 128; // Calculated: ((255+0)*255 + 255) / (2*255) = 128

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for pure green (0,255,0) should be 128", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 6: Luminance for pure blue color (RGB: 0, 0, 255).
     * Expected Luminance: 128.
     */
    @Test
    public void testGetLuminence_PureBlueRGB() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 255);
        int expectedLuminence = 128; // Calculated: ((255+0)*255 + 255) / (2*255) = 128

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for pure blue (0,0,255) should be 128", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 7: Luminance for a dark color (RGB: 50, 20, 80).
     * Expected Luminance: 50.
     */
    @Test
    public void testGetLuminence_DarkColorRGB() {
        // Arrange
        // cMax = 80, cMin = 20. cPlus = 100.
        // Lum = ((100 * 255) + 255) / (2 * 255) = (25500 + 255) / 510 = 25755 / 510 = 50
        hslColor.initHSLbyRGB(50, 20, 80);
        int expectedLuminence = 50;

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for dark color (50,20,80) should be 50", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 8: Luminance for a light color (RGB: 200, 220, 180).
     * Expected Luminance: 200.
     */
    @Test
    public void testGetLuminence_LightColorRGB() {
        // Arrange
        // cMax = 220, cMin = 180. cPlus = 400.
        // Lum = ((400 * 255) + 255) / (2 * 255) = (102000 + 255) / 510 = 102255 / 510 = 200
        hslColor.initHSLbyRGB(200, 220, 180);
        int expectedLuminence = 200;

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance for light color (200,220,180) should be 200", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 9: Get luminance after setting via HSL (minimum luminance).
     * Expected Luminance: 0.
     */
    @Test
    public void testGetLuminence_SetViaHSLMin() {
        // Arrange
        int hue = 120;
        int saturation = 150;
        int luminence = 0; // Minimum luminance
        hslColor.initRGBbyHSL(hue, saturation, luminence);
        int expectedLuminence = 0;

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance set via HSL(120, 150, 0) should be 0", expectedLuminence, actualLuminence);
    }

    /**
     * Test case 10: Get luminance after setting via HSL (specific luminance).
     * Expected Luminance: 191.
     */
    @Test
    public void testGetLuminence_SetViaHSLSpecific() {
        // Arrange
        int hue = 200;
        int saturation = 100;
        int luminence = 191; // Specific non-edge luminance
        hslColor.initRGBbyHSL(hue, saturation, luminence);
        int expectedLuminence = 191;

        // Act
        int actualLuminence = hslColor.getLuminence();

        // Assert
        assertEquals("Luminance set via HSL(200, 100, 191) should be 191", expectedLuminence, actualLuminence);
    }
}