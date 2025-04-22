// HSLColor_initHSLbyRGBTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public initHSLbyRGB(int R, int G, int B) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_initHSLbyRGBTest {
    private HSLColor hslColor;

    @Before
    public void setUp() {
        hslColor = new HSLColor();
    }

    @After
    public void tearDown() {
        hslColor = null;
    }




    @Test(expected = IllegalArgumentException.class)
    public void testInitHSLbyRGB_withNegativeValues() {
        hslColor.initHSLbyRGB(-1, -1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitHSLbyRGB_withLargeValues() {
        hslColor.initHSLbyRGB(256, 256, 256);
    }
    
    
    
}