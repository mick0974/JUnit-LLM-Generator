// HSLColor_blendTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public blend(int R, int G, int B, float fPercent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_blendTest {
    private HSLColor hslcolor;

    @Before
    public void setUp() {
        hslcolor = new HSLColor();
    }

    @After
    public void tearDown() {
        hslcolor = null;
    }
    
    @Test
    public void testBlendWithFullFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 1.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertEquals(r, hslcolor.getRed());
        assertEquals(g, hslcolor.getGreen());
        assertEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithZeroFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithNegativeFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = -1.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithHalfFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.5f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
    
    @Test
    public void testBlendWithLargeFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 2.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithOneAndHalfFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 1.5f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithTwoThirdsFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 2.0f / 3.0f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertNotEquals(r, hslcolor.getRed());
        assertNotEquals(g, hslcolor.getGreen());
        assertNotEquals(b, hslcolor.getBlue());
    }
    
    @Test
    public void testBlendWithQuarterFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.25f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
    
    @Test
    public void testBlendWithSmallFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.01f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
    
    @Test
    public void testBlendWithOneMinusSmallFactor() {
        // Arrange
        int r = 255, g = 0, b = 0;
        float factor = 0.99f;
        
        // Act
        hslcolor.blend(r, g, b, factor);
        
        // Assert
        assertTrue(hslcolor.getRed()!= r && hslcolor.getRed()!= g && hslcolor.getRed()!= b);
        assertTrue(hslcolor.getGreen()!= r && hslcolor.getGreen()!= g && hslcolor.getGreen()!= b);
        assertTrue(hslcolor.getBlue()!= r && hslcolor.getBlue()!= g && hslcolor.getBlue()!= b);
    }
}