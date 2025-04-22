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
    public void testGetBlueEdgeCaseFullBlack() {
        // Arrange
        hslColor.initRGBbyHSL(0, 0, 0); // Initialize with black color
        
        // Act
        int blue = hslColor.getBlue();
        
        // Assert
        assertEquals(0, blue); // Black has no blue component
    }

    
    
    
    
    
}