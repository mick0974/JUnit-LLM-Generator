// HSLColor_getHueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getHue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getHueTest {

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
    public void testGetHueForRed() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for red (255,0,0) should be 0", 0, hue);
    }

    @Test
    public void testGetHueForGreen() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 0);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for green (0,255,0) should be 85", 85, hue);
    }

    @Test
    public void testGetHueForBlue() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 255);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for blue (0,0,255) should be 170", 170, hue);
    }

    @Test
    public void testGetHueForGray() {
        // Arrange
        hslColor.initHSLbyRGB(128, 128, 128);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for gray (128,128,128) should be 170 (undefined)", 170, hue);
    }

    @Test
    public void testGetHueForWhite() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 255);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for white (255,255,255) should be 170 (undefined)", 170, hue);
    }

    @Test
    public void testGetHueForBlack() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for black (0,0,0) should be 170 (undefined)", 170, hue);
    }


    @Test
    public void testGetHueForMagenta() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 255);
        
        // Act
        int hue = hslColor.getHue();
        
        // Assert
        assertEquals("Hue for magenta (255,0,255) should be around 213", 213, hue);
    }


}