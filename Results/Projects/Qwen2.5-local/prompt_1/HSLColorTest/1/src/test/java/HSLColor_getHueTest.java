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
    public void testGetHueRed() {
        // Arrange
        hslColor.initHSLbyRGB(255, 0, 0); // Red
        
        // Act
        int actualHue = hslColor.getHue();
        
        // Assert
        assertEquals("The hue should be 0", 0, actualHue);
    }
    
    
}