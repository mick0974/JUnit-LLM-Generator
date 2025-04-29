```java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor#initHSLbyRGB(int R, int G, int B).
 * Contains unit test cases covering typical and edge cases using the
 * Arrange-Act-Assert pattern.
 */
public class HSLColor_initHSLbyRGBTest {

    private HSLColor hslColor;
    // Using a delta for comparing integer results derived from floating point math
    // Although the original code uses integer math, internal calculations might
    // implicitly represent fractions, leading to potential off-by-one errors
    // depending on rounding. Let's start with 0, but keep it in mind.
    private static final int DELTA = 1;
    private static final int UNDEFINED_HUE = 170; // As defined in HSLColor

    @Before
    public void setUp() {
        // Arrange: Create a new HSLColor instance before each test
        hslColor = new HSLColor();
    }

    @After
    public void tearDown() {
        // Clean up the instance after each test
        hslColor = null;
    }

    // --- Typical Cases ---

    @Test
    public void testInitHSLbyRGB_PureRed() {
        // Arrange: RGB for pure Red
        int r = 255;
        int g = 0;
        int b = 0;
        // Expected HSL (approx): H=0, S=255 (100%), L=128 (50%)
        // Let's calculate based on the provided code's logic:
        // cMax=255, cMin=0, cMinus=255, cPlus=255
        // L = ((255*255)+255)/(2*255) = 65280 / 510 = 128
        // L > 127, so S = (((255*255)+0.5)/(2*255 - 255)) = (65025.5 / 255) = 255
        // H: RDelta=0, GDelta=42, BDelta=42. cMax=R, H = BDelta - GDelta = 42 - 42 = 0
        int expectedH = 0;
        int expectedS = 255;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for pure Red", r, hslColor.getRed());
        assertEquals("Green value mismatch for pure Red", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for pure Red", b, hslColor.getBlue());
        assertEquals("Hue mismatch for pure Red", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for pure Red", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for pure Red", expectedL, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_PureGreen() {
        // Arrange: RGB for pure Green
        int r = 0;
        int g = 255;
        int b = 0;
        // Expected HSL (approx): H=85 (120 deg), S=255 (100%), L=128 (50%)
        // cMax=255, cMin=0, cMinus=255, cPlus=255 -> L=128, S=255 (as above)
        // H: RDelta=42, GDelta=0, BDelta=42. cMax=G.
        // H = (255/3) + RDelta - BDelta = 85 + 42 - 42 = 85
        int expectedH = 85; // 255 / 3
        int expectedS = 255;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for pure Green", r, hslColor.getRed());
        assertEquals("Green value mismatch for pure Green", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for pure Green", b, hslColor.getBlue());
        assertEquals("Hue mismatch for pure Green", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for pure Green", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for pure Green", expectedL, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_PureBlue() {
        // Arrange: RGB for pure Blue
        int r = 0;
        int g = 0;
        int b = 255;
        // Expected HSL (approx): H=170 (240 deg), S=255 (100%), L=128 (50%)
        // cMax=255, cMin=0, cMinus=255, cPlus=255 -> L=128, S=255 (as above)
        // H: RDelta=42, GDelta=42, BDelta=0. cMax=B.
        // H = (2*255/3) + GDelta - RDelta = 170 + 42 - 42 = 170
        int expectedH = 170; // 2 * 255 / 3
        int expectedS = 255;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for pure Blue", r, hslColor.getRed());
        assertEquals("Green value mismatch for pure Blue", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for pure Blue", b, hslColor.getBlue());
        assertEquals("Hue mismatch for pure Blue", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for pure Blue", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for pure Blue", expectedL, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_Yellow() {
        // Arrange: RGB for Yellow
        int r = 255;
        int g = 255;
        int b = 0;
        // Expected HSL (approx): H=42 (60 deg), S=255 (100%), L=128 (50%)
        // cMax=255, cMin=0, cMinus=255, cPlus=255 -> L=128, S=255 (as above)
        // H: RDelta=0, GDelta=0, BDelta=42. cMax=R (or G). If R: H = BDelta-GDelta=42. If G: H = 85+RDelta-BDelta = 85+0-42=43.
        // Code checks R first: cMax == R is true. H = BDelta - GDelta = 42 - 0 = 42.
        int expectedH = 42; // 255 / 6
        int expectedS = 255;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for Yellow", r, hslColor.getRed());
        assertEquals("Green value mismatch for Yellow", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for Yellow", b, hslColor.getBlue());
        // Allow delta due to potential rounding variations in calculation paths
        assertEquals("Hue mismatch for Yellow", expectedH, hslColor.getHue(), DELTA);
        assertEquals("Saturation mismatch for Yellow", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for Yellow", expectedL, hslColor.getLuminence());
    }

     @Test
    public void testInitHSLbyRGB_Cyan() {
        // Arrange: RGB for Cyan
        int r = 0;
        int g = 255;
        int b = 255;
        // Expected HSL (approx): H=127 (180 deg), S=255 (100%), L=128 (50%)
        // cMax=255, cMin=0, cMinus=255, cPlus=255 -> L=128, S=255 (as above)
        // H: RDelta=42, GDelta=0, BDelta=0. cMax=G (or B). If G: H = 85 + RDelta - BDelta = 85+42-0=127. If B: H = 170 + GDelta - RDelta = 170+0-42=128.
        // Code checks G before B: cMax == G is true. H = 85 + RDelta - BDelta = 85 + 42 - 0 = 127.
        int expectedH = 127; // 255 / 2 (approx)
        int expectedS = 255;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for Cyan", r, hslColor.getRed());
        assertEquals("Green value mismatch for Cyan", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for Cyan", b, hslColor.getBlue());
        assertEquals("Hue mismatch for Cyan", expectedH, hslColor.getHue(), DELTA);
        assertEquals("Saturation mismatch for Cyan", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for Cyan", expectedL, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_Magenta() {
        // Arrange: RGB for Magenta
        int r = 255;
        int g = 0;
        int b = 255;
        // Expected HSL (approx): H=212 (300 deg), S=255 (100%), L=128 (50%)
        // cMax=255, cMin=0, cMinus=255, cPlus=255 -> L=128, S=255 (as above)
        // H: RDelta=0, GDelta=42, BDelta=0. cMax=R (or B). If R: H = BDelta-GDelta = 0-42 = -42 -> +255 = 213. If B: H=170+GDelta-RDelta = 170+42-0=212.
        // Code checks R first: cMax == R is true. H = BDelta - GDelta = 0 - 42 = -42. If H < 0, H = H + 255 -> -42 + 255 = 213.
        int expectedH = 213; // 5 * 255 / 6 (approx)
        int expectedS = 255;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for Magenta", r, hslColor.getRed());
        assertEquals("Green value mismatch for Magenta", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for Magenta", b, hslColor.getBlue());
        assertEquals("Hue mismatch for Magenta", expectedH, hslColor.getHue(), DELTA);
        assertEquals("Saturation mismatch for Magenta", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for Magenta", expectedL, hslColor.getLuminence());
    }


    // --- Edge Cases (Greyscale) ---

    @Test
    public void testInitHSLbyRGB_Black() {
        // Arrange: RGB for Black
        int r = 0;
        int g = 0;
        int b = 0;
        // Expected: H=UNDEFINED, S=0, L=0
        // cMax=0, cMin=0 -> cMax == cMin is true.
        // L = ((0*255)+255)/(2*255) = 255/510 = 0
        // S = 0, H = UNDEFINED (170)
        int expectedH = UNDEFINED_HUE;
        int expectedS = 0;
        int expectedL = 0;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for Black", r, hslColor.getRed());
        assertEquals("Green value mismatch for Black", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for Black", b, hslColor.getBlue());
        assertEquals("Hue mismatch for Black", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for Black", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for Black", expectedL, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_White() {
        // Arrange: RGB for White
        int r = 255;
        int g = 255;
        int b = 255;
        // Expected: H=UNDEFINED, S=0, L=255
        // cMax=255, cMin=255 -> cMax == cMin is true.
        // cPlus=510. L = ((510*255)+255)/(2*255) = (130050+255)/510 = 130305/510 = 255
        // S = 0, H = UNDEFINED (170)
        int expectedH = UNDEFINED_HUE;
        int expectedS = 0;
        int expectedL = 255;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for White", r, hslColor.getRed());
        assertEquals("Green value mismatch for White", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for White", b, hslColor.getBlue());
        assertEquals("Hue mismatch for White", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for White", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for White", expectedL, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_MidGrey() {
        // Arrange: RGB for Mid-Grey
        int r = 128;
        int g = 128;
        int b = 128;
        // Expected: H=UNDEFINED, S=0, L=128
        // cMax=128, cMin=128 -> cMax == cMin is true.
        // cPlus=256. L = ((256*255)+255)/(2*255) = (65280+255)/510 = 65535/510 = 128
        // S = 0, H = UNDEFINED (170)
        int expectedH = UNDEFINED_HUE;
        int expectedS = 0;
        int expectedL = 128;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for MidGrey", r, hslColor.getRed());
        assertEquals("Green value mismatch for MidGrey", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for MidGrey", b, hslColor.getBlue());
        assertEquals("Hue mismatch for MidGrey", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for MidGrey", expectedS, hslColor.getSaturation());
        assertEquals("Luminence mismatch for MidGrey", expectedL, hslColor.getLuminence());
    }

    // --- Edge Case for Saturation Calculation Branch ---

    @Test
    public void testInitHSLbyRGB_DarkRed() {
        // Arrange: RGB for a dark red where L should be <= HSLMAX/2 (127)
        int r = 100;
        int g = 0;
        int b = 0;
        // Expected L calculation:
        // cMax=100, cMin=0, cMinus=100, cPlus=100
        // L = ((100*255)+255)/(2*255) = (25500+255)/510 = 25755/510 = 50
        // Since L (50) <= 127, Saturation uses the first branch:
        // S = (((100 * 255) + 0.5) / 100) = (25500.5 / 100) = 255
        // H: RDelta=0, GDelta=42, BDelta=42. cMax=R, H = BDelta - GDelta = 42 - 42 = 0
        int expectedH = 0;
        int expectedS = 255;
        int expectedL = 50;

        // Act
        hslColor.initHSLbyRGB(r, g, b);

        // Assert
        assertEquals("Red value mismatch for DarkRed", r, hslColor.getRed());
        assertEquals("Green value mismatch for DarkRed", g, hslColor.getGreen());
        assertEquals("Blue value mismatch for DarkRed", b, hslColor.getBlue());
        assertEquals("Hue mismatch for DarkRed", expectedH, hslColor.getHue());
        assertEquals("Saturation mismatch for DarkRed", expectedS, hslColor.getSaturation());
        // Check Luminance calculation specifically for branch test
        assertEquals("Luminence mismatch for DarkRed (L <= HSLMAX/2 branch)", expectedL, hslColor.getLuminence());
    }
}
```