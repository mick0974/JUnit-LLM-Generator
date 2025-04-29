// HSLColor_getSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getSaturation() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getSaturationTest {

private HSLColor hslColor;

    @Before
    public void setUp() {
        hslColor = new HSLColor();
    }

    @After
    public void tearDown() {
        hslColor = null;
    }

    @Test
    public void testGetSaturation_ZeroSaturation() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128); // Arranging a greyscale color
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertEquals(0, saturation);
    }

    @Test
    public void testGetSaturation_MaximumSaturation() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0); // Arranging a full red color
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertEquals(255, saturation);
    }

    @Test
    public void testGetSaturation_HalfSaturation() {
        // Arrange
        hslColor.initHSLbyRGB(192, 128, 128);
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertTrue(saturation > 0 && saturation < 255);
    }

    @Test
    public void testGetSaturation_OnGreyBoundary() {
        // Arrange
        hslColor.initHSLbyRGB(64, 64, 64); 
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertEquals(0, saturation);
    }

    @Test
    public void testGetSaturation_NearMaximumSaturation() {
        // Arrange
        hslColor.initHSLbyRGB(255, 10, 10);
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertTrue(saturation > 240 && saturation <= 255);
    }

    @Test
    public void testGetSaturation_Black() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0); 
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertEquals(0, saturation);
    }

    @Test
    public void testGetSaturation_White() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 255); 
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertEquals(0, saturation);
    }

    @Test
    public void testGetSaturation_LightBlue() {
        // Arrange
        hslColor.initHSLbyRGB(173, 216, 230); 
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertTrue(saturation > 100 && saturation < 200);
    }

    @Test
    public void testGetSaturation_LightnessBoundary() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128); 
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertEquals(0, saturation);
    }

    @Test
    public void testGetSaturation_DarkColor() {
        // Arrange
        hslColor.initHSLbyRGB(50, 100, 150); 
        // Act
        int saturation = hslColor.getSaturation();
        // Assert
        assertTrue(saturation > 0 && saturation < 200);
    }
}