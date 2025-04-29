// HSLColor_getLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getLuminence() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getLuminenceTest {

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
    public void testGetLuminenceWithGrey() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128); // A middle grey color
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithWhite() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 255); // White color
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(255, lum);
    }

    @Test
    public void testGetLuminenceWithBlack() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0); // Black color
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(0, lum);
    }

    @Test
    public void testGetLuminenceWithRed() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0); // Pure red color
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithGreen() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 0); // Pure green color
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithBlue() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 255); // Pure blue color
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithYellow() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 0); // Yellow
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithCyan() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 255); // Cyan
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithMagenta() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 255); // Magenta
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(128, lum);
    }

    @Test
    public void testGetLuminenceWithEdgeCase() {
        // Arrange
        hslColor.initHSLbyRGB(123, 123, 123); // Close to grey, should produce similar lum
        // Act
        int lum = hslColor.getLuminence();
        // Assert
        assertEquals(123, lum);
    }
}