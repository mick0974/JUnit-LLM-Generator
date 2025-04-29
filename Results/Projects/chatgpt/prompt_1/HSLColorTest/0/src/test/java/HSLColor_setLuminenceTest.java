// HSLColor_setLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setLuminence(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setLuminenceTest {

private HSLColor hslColor;

    @Before
    public void setUp() {
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(255, 0, 0); // Initialize with a valid HSL color (red)
    }

    @After
    public void tearDown() {
        hslColor = null;
    }

    @Test
    public void testSetLuminenceZero() {
        // Arrange
        int expectedLum = 0;

        // Act
        hslColor.setLuminence(expectedLum);

        // Assert
        assertEquals(expectedLum, hslColor.getLuminence());
    }

    @Test
    public void testSetLuminenceMax() {
        // Arrange
        int expectedLum = 255;

        // Act
        hslColor.setLuminence(expectedLum);

        // Assert
        assertEquals(expectedLum, hslColor.getLuminence());
    }

    @Test
    public void testSetLuminenceValidMiddleValue() {
        // Arrange
        int expectedLum = 128;

        // Act
        hslColor.setLuminence(expectedLum);

        // Assert
        assertEquals(expectedLum, hslColor.getLuminence());
    }

    @Test
    public void testSetLuminenceNegativeValue() {
        // Arrange
        int invalidLum = -10;
        int expectedLum = 0;

        // Act
        hslColor.setLuminence(invalidLum);

        // Assert
        assertEquals(expectedLum, hslColor.getLuminence());
    }

    @Test
    public void testSetLuminenceAboveMaxValue() {
        // Arrange
        int invalidLum = 300;
        int expectedLum = 255;

        // Act
        hslColor.setLuminence(invalidLum);

        // Assert
        assertEquals(expectedLum, hslColor.getLuminence());
    }

    @Test
    public void testSetLuminenceCausesRGBChange() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 0); // Initial green color
        int newLum = 128;

        // Act
        hslColor.setLuminence(newLum);

        // Assert
        assertNotEquals(255, hslColor.getRed());  // The Red channel should adjust
        assertEquals(255, hslColor.getGreen());   // Green should stay same
        assertNotEquals(0, hslColor.getBlue());   // Blue channel should adjust as well
    }

    @Test
    public void testSetLuminenceKeepsHueSame() {
        // Arrange
        int initialHue = hslColor.getHue();
        int newLum = 128;

        // Act
        hslColor.setLuminence(newLum);

        // Assert
        assertEquals(initialHue, hslColor.getHue()); // Hue should not change
    }

    @Test
    public void testSetLuminenceKeepsSaturationSame() {
        // Arrange
        int initialSaturation = hslColor.getSaturation();
        int newLum = 128;

        // Act
        hslColor.setLuminence(newLum);

        // Assert
        assertEquals(initialSaturation, hslColor.getSaturation()); // Saturation should not change
    }


    @Test
    public void testSetLuminenceColorWithoutSaturation() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128); // Already grayscale
        int newLum = 150;

        // Act
        hslColor.setLuminence(newLum);

        // Assert
        assertEquals(newLum, hslColor.getLuminence());
        assertEquals(hslColor.getRed(), hslColor.getGreen());
        assertEquals(hslColor.getGreen(), hslColor.getBlue()); // Ensure it remains grayscale
    }
}