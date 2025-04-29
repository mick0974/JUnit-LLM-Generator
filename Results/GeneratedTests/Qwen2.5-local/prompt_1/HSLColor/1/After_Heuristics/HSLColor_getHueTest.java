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
    public void setUp() throws Exception {
        hslColor = new HSLColor();
    }

    @After
    public void tearDown() throws Exception {
        hslColor = null;
    }

    @Test
    public void testGetHueTypicalCase() {
        // Arrange
        int expectedHue = 120;
        hslColor.setHue(expectedHue);

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("The hue should be as expected", expectedHue, actualHue);
    }

    @Test
    public void testGetHueEdgeCaseNegative() {
        // Arrange
        int expectedHue = 359;
        hslColor.setHue(expectedHue);

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("The hue should be as expected", expectedHue, actualHue);
    }

    @Test
    public void testGetHueEdgeCasePositive() {
        // Arrange
        int expectedHue = 0;
        hslColor.setHue(expectedHue);

        // Act
        int actualHue = hslColor.getHue();

        // Assert
        assertEquals("The hue should be as expected", expectedHue, actualHue);
    }
    
    @Test
    public void testGetHueNullObject() {
        // Arrange
        hslColor = null;

        // Act & Assert
        try {
            hslColor.getHue();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
    
    @Test
    public void testGetHueZeroSaturation() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 0); // Black
        
        // Act
        int actualHue = hslColor.getHue();
        
        // Assert
        assertEquals("The hue should be undefined", HSLColor.UNDEFINED, actualHue);
    }
    
    @Test
    public void testGetHueWhite() {
        // Arrange
        hslColor.initHSLbyRGB(255, 255, 255); // White
        
        // Act
        int actualHue = hslColor.getHue();
        
        // Assert
        assertEquals("The hue should be undefined", HSLColor.UNDEFINED, actualHue);
    }
    
    @Test
    public void testGetHueRed() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0); // Red
        
        // Act
        int actualHue = hslColor.getHue();
        
        // Assert
        assertEquals("The hue should be 0", 0, actualHue);
    }
    
    @Test
    public void testGetHueGreen() {
        // Arrange
        hslColor.initHSLbyRGB(0, 255, 0); // Green
        
        // Act
        int actualHue = hslColor.getHue();
        
        // Assert
        assertEquals("The hue should be 120", 120, actualHue);
    }
    
    @Test
    public void testGetHueBlue() {
        // Arrange
        hslColor.initHSLbyRGB(0, 0, 255); // Blue
        
        // Act
        int actualHue = hslColor.getHue();
        
        // Assert
        assertEquals("The hue should be 240", 240, actualHue);
    }
}