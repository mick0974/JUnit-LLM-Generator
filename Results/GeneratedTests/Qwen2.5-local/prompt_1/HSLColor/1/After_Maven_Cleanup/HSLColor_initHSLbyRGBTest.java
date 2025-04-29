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



    @Test
    public void testInitHSLbyRGBWithRed() {
        hslColor.initHSLbyRGB(255, 0, 0);
        assertEquals(0, hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }







}