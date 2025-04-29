// HSLColor_getRedTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getRed() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getRedTest {
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
    public void testGetRed_InitialRedValue() {
        // Arrange
        int expectedRed = 255;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 0, 0); // Red is 255, Green and Blue are 0
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 255", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_BlackColor() {
        // Arrange
        int expectedRed = 0;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 0, 0); // Black is 0, Green and Blue are 0
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 0", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_GreyscaleColor() {
        // Arrange
        int expectedRed = 128;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 0, 128); // Greyscale with 50% luminance
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 128", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_CompletelyDesaturated() {
        // Arrange
        int expectedRed = 255;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 0, 255); // Completely desaturated
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 255", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_MaximumSaturation() {
        // Arrange
        int expectedRed = 255;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 255, 128); // Maximum saturation
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 255", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_MidwaySaturation() {
        // Arrange
        int expectedRed = 192;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 128, 128); // Midway saturation
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 192", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_LowSaturation() {
        // Arrange
        int expectedRed = 240;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 10, 128); // Low saturation
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 240", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_HighLuminosity() {
        // Arrange
        int expectedRed = 255;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 255, 255); // High luminosity
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 255", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_LowLuminosity() {
        // Arrange
        int expectedRed = 0;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, 255, 0); // Low luminosity
        int actualRed = hslColor.getRed();

        // Assert
        assertEquals("The red value should be 0", expectedRed, actualRed);
    }

    @Test
    public void testGetRed_AllColors() {
        // Arrange
        int expectedRed = 128;
        int expectedGreen = 128;
        int expectedBlue = 128;
        
        // Act
        hslColor.initHSLbyRGB(expectedRed, expectedGreen, expectedBlue); // All colors equally
        int actualRed = hslColor.getRed();
        int actualGreen = hslColor.getGreen();
        int actualBlue = hslColor.getBlue();

        // Assert
        assertEquals("The red value should be 128", expectedRed, actualRed);
        assertEquals("The green value should be 128", expectedGreen, actualGreen);
        assertEquals("The blue value should be 128", expectedBlue, actualBlue);
    }
}