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

private HSLColor color;

    @Before
    public void setUp() {
        color = new HSLColor();
    }

    @After
    public void tearDown() {
        color = null;
    }

    @Test
    public void testGetRed_MaxRed() {
        // Arrange
        color.initHSLbyRGB(255, 0, 0);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(255, red);
    }

    @Test
    public void testGetRed_MinRed() {
        // Arrange
        color.initHSLbyRGB(0, 255, 255);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(0, red);
    }

    @Test
    public void testGetRed_MediumRed() {
        // Arrange
        color.initHSLbyRGB(128, 128, 128);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(128, red);
    }

    @Test
    public void testGetRed_Grey() {
        // Arrange
        color.initHSLbyRGB(50, 50, 50);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(50, red);
    }

    @Test
    public void testGetRed_Black() {
        // Arrange
        color.initHSLbyRGB(0, 0, 0);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(0, red);
    }

    @Test
    public void testGetRed_White() {
        // Arrange
        color.initHSLbyRGB(255, 255, 255);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(255, red);
    }

    @Test
    public void testGetRed_Blue() {
        // Arrange
        color.initHSLbyRGB(0, 0, 255);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(0, red);
    }

    @Test
    public void testGetRed_Green() {
        // Arrange
        color.initHSLbyRGB(0, 255, 0);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(0, red);
    }

    @Test
    public void testGetRed_Cyan() {
        // Arrange
        color.initHSLbyRGB(0, 255, 255);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(0, red);
    }

    @Test
    public void testGetRed_Yellow() {
        // Arrange
        color.initHSLbyRGB(255, 255, 0);
        
        // Act
        int red = color.getRed();
        
        // Assert
        assertEquals(255, red);
    }
}