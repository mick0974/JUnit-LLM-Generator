import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for HSLColor#getRed().
 * Contains 10 unit test cases covering various scenarios,
 * including primary colors, mixed colors, greyscale, black, white,
 * initialized via both RGB and HSL.
 * Tests follow the Arrange-Act-Assert pattern.
 */
public class HSLColor_getRedTest {

    private HSLColor hslColor;
    // Define a tolerance for floating point comparisons if needed, although results here seem integer-based.
    // private static final double DELTA = 1e-9; // Example tolerance

    @Before
    public void setUp() {
        // Arrange: Initialize a new HSLColor object before each test
        hslColor = new HSLColor();
    }

    // Test Case 1: Initialize with pure Red via RGB
    @Test
    public void testGetRed_PureRedRGB() {
        // Arrange
        int expectedRed = 255;
        int green = 0;
        int blue = 0;

        // Act
        hslColor.initHSLbyRGB(expectedRed, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 255 for pure red initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 2: Initialize with pure Green via RGB
    @Test
    public void testGetRed_PureGreenRGB() {
        // Arrange
        int red = 0;
        int expectedRed = 0; // Red component should be 0 for pure green
        int green = 255;
        int blue = 0;

        // Act
        hslColor.initHSLbyRGB(red, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 0 for pure green initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 3: Initialize with pure Blue via RGB
    @Test
    public void testGetRed_PureBlueRGB() {
        // Arrange
        int red = 0;
        int expectedRed = 0; // Red component should be 0 for pure blue
        int green = 0;
        int blue = 255;

        // Act
        hslColor.initHSLbyRGB(red, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 0 for pure blue initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 4: Initialize with Yellow via RGB
    @Test
    public void testGetRed_YellowRGB() {
        // Arrange
        int expectedRed = 255;
        int green = 255;
        int blue = 0;

        // Act
        hslColor.initHSLbyRGB(expectedRed, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 255 for yellow initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 5: Initialize with Grey via RGB
    @Test
    public void testGetRed_GreyRGB() {
        // Arrange
        int expectedRed = 128;
        int green = 128;
        int blue = 128;

        // Act
        hslColor.initHSLbyRGB(expectedRed, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 128 for grey initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 6: Initialize with Black via RGB
    @Test
    public void testGetRed_BlackRGB() {
        // Arrange
        int expectedRed = 0;
        int green = 0;
        int blue = 0;

        // Act
        hslColor.initHSLbyRGB(expectedRed, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 0 for black initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 7: Initialize with White via RGB
    @Test
    public void testGetRed_WhiteRGB() {
        // Arrange
        int expectedRed = 255;
        int green = 255;
        int blue = 255;

        // Act
        hslColor.initHSLbyRGB(expectedRed, green, blue);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 255 for white initialized by RGB", expectedRed, actualRed);
    }

    // Test Case 8: Initialize representing Red via HSL (H=0, S=255, L=128) -> results in RGB(254, 2, 2)
    @Test
    public void testGetRed_NearRedHSL() {
        // Arrange
        int hue = 0;
        int saturation = 255;
        int luminence = 128; // Mid-luminence for full saturation
        int expectedRed = 254; // Calculation based on the provided HSL->RGB logic

        // Act
        hslColor.initRGBbyHSL(hue, saturation, luminence);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be approx 254 for red initialized by HSL(0, 255, 128)", expectedRed, actualRed);
    }

    // Test Case 9: Initialize representing Green via HSL (H=85, S=255, L=128) -> results in RGB(2, 254, 2)
    @Test
    public void testGetRed_NearGreenHSL() {
         // Arrange
        int hue = 85; // Approx 120 degrees for Green
        int saturation = 255;
        int luminence = 128;
        int expectedRed = 2; // Calculation based on the provided HSL->RGB logic

        // Act
        hslColor.initRGBbyHSL(hue, saturation, luminence);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be approx 2 for green initialized by HSL(85, 255, 128)", expectedRed, actualRed);
    }

    // Test Case 10: Initialize representing Grey via HSL (S=0)
    @Test
    public void testGetRed_GreyHSL() {
        // Arrange
        int hue = 100; // Hue is irrelevant for greyscale
        int saturation = 0; // Greyscale
        int luminence = 128; // Mid-grey
        int expectedRed = 128; // Greyscale R=G=B=L mapped to RGB range

        // Act
        hslColor.initRGBbyHSL(hue, saturation, luminence);
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("Red component should be 128 for grey initialized by HSL(any, 0, 128)", expectedRed, actualRed);
    }
}