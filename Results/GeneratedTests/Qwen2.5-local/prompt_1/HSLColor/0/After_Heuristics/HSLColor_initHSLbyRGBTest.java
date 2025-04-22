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
    public void testInitHSLbyRGB_withValidValues() {
        hslColor.initHSLbyRGB(255, 0, 0);
        assertEquals(0, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(50, hslColor.getLuminence());

        hslColor.initHSLbyRGB(0, 255, 0);
        assertEquals(120, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(50, hslColor.getLuminence());

        hslColor.initHSLbyRGB(0, 0, 255);
        assertEquals(240, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(50, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_withMaxValues() {
        hslColor.initHSLbyRGB(255, 255, 255);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(100, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGB_withMinValues() {
        hslColor.initHSLbyRGB(0, 0, 0);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(0, hslColor.getLuminence());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitHSLbyRGB_withNegativeValues() {
        hslColor.initHSLbyRGB(-1, -1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitHSLbyRGB_withLargeValues() {
        hslColor.initHSLbyRGB(256, 256, 256);
    }
    
    @Test
    public void testInitHSLbyRGB_withEqualValues() {
        hslColor.initHSLbyRGB(128, 128, 128);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(50, hslColor.getLuminence());
    }
    
    @Test
    public void testInitHSLbyRGB_withGrayScaleValues() {
        hslColor.initHSLbyRGB(128, 128, 128);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(50, hslColor.getLuminence());
        
        hslColor.initHSLbyRGB(64, 64, 64);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(25, hslColor.getLuminence());
        
        hslColor.initHSLbyRGB(192, 192, 192);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(75, hslColor.getLuminence());
    }
    
    @Test
    public void testInitHSLbyRGB_withAlmostEqualValues() {
        hslColor.initHSLbyRGB(254, 254, 254);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(100, hslColor.getLuminence());
        
        hslColor.initHSLbyRGB(3, 3, 3);
        assertEquals(UNDEFINED, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(0, hslColor.getLuminence());
    }
}