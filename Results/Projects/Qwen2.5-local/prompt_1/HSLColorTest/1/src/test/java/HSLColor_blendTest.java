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
    
    
    
    
    
    
    
    
    
}