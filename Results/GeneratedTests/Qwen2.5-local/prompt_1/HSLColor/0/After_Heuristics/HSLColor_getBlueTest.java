// HSLColor_getBlueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getBlue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getBlueTest {
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
    public void testGetBlueTypicalCase() {
        // Arrange
        hslColor.initRGBbyHSL(0, 100, 50); // Initialize with vibrant red color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Red has no blue component
    }

    @Test
    public void testGetBlueEdgeCaseFullWhite() {
        // Arrange
        hslColor.initRGBbyHSL(0, 0, 100); // Initialize with white color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // White has no blue component
    }

    @Test
    public void testGetBlueEdgeCaseFullBlack() {
        // Arrange
        hslColor.initRGBbyHSL(0, 0, 0); // Initialize with black color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Black has no blue component
    }

    @Test
    public void testGetBlueTypicalCaseWithSaturation() {
        // Arrange
        hslColor.initRGBbyHSL(120, 50, 50); // Initialize with vibrant green color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Green has no blue component
    }
    
    @Test
    public void testGetBlueTypicalCaseWithHighSaturation() {
        // Arrange
        hslColor.initRGBbyHSL(90, 80, 50); // Initialize with vibrant yellow color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Yellow has no blue component
    }
    
    @Test
    public void testGetBlueTypicalCaseWithLowSaturation() {
        // Arrange
        hslColor.initRGBbyHSL(180, 10, 50); // Initialize with light cyan color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertTrue(blue > 0); // Cyan should have some blue component
    }
    
    @Test
    public void testGetBlueTypicalCaseWithMediumSaturation() {
        // Arrange
        hslColor.initRGBbyHSL(240, 50, 50); // Initialize with vibrant purple color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertTrue(blue > 0); // Purple should have some blue component
    }
    
    @Test
    public void testGetBlueTypicalCaseWithComplementaryColor() {
        // Arrange
        hslColor.initRGBbyHSL(270, 100, 50); // Initialize with vibrant orange color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Orange has no blue component
    }
    
    @Test
    public void testGetBlueTypicalCaseWithMonochromaticColor() {
        // Arrange
        hslColor.initRGBbyHSL(0, 0, 75); // Initialize with mid-light grey color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Grey has no blue component
    }
}