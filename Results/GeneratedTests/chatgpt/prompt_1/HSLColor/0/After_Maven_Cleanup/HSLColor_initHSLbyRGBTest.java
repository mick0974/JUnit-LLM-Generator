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
    public void testRed() {
        int R = 255, G = 0, B = 0;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(0, hslColor.getHue());
    }

    @Test
    public void testLimeGreen() {
        int R = 0, G = 255, B = 0;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(85, hslColor.getHue());
    }

    @Test
    public void testBlue() {
        int R = 0, G = 0, B = 255;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(170, hslColor.getHue());
    }




    @Test
    public void testMagenta() {
        int R = 255, G = 0, B = 255;

        hslColor.initHSLbyRGB(R, G, B);

        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
        assertEquals(213, hslColor.getHue());
    }

}