// HSLColor_brightenTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public brighten(float fPercent) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_brightenTest {
    private HSLColor hslcolor;

    @Before
    public void setUp() {
        hslcolor = new HSLColor();
        hslcolor.initHSLbyRGB(255, 0, 0); // red
    }

    @After
    public void tearDown() {
        hslcolor = null;
    }

    @Test
    public void testBrighten_0_percent() {
        hslcolor.brighten(0);
        assertEquals("Brightness should remain unchanged", 128, hslcolor.getLuminence());
    }

    @Test
    public void testBrighten_less_than_1_percent() {
        hslcolor.brighten(0.49f);
        assertTrue("Brightness should be less than original", hslcolor.getLuminence() < 128);
    }

    @Test
    public void testBrighten_1_percent() {
        hslcolor.brighten(1.0f);
        assertEquals("Brightness should be maximum", 255, hslcolor.getLuminence());
    }

    @Test
    public void testBrighten_more_than_1_percent() {
        hslcolor.brighten(1.5f);
        assertEquals("Brightness should be maximum", 255, hslcolor.getLuminence());
    }

    @Test
    public void testBrighten_negative_value() {
        hslcolor.brighten(-0.5f);
        assertEquals("Brightness should be unchanged", 128, hslcolor.getLuminence());
    }
    
    @Test
    public void testBrighten_max_luminance() {
        hslcolor.setLuminence(HSLColor.HSLMAX);
        hslcolor.brighten(2.0f);
        assertEquals("Brightness should be unchanged", HSLColor.HSLMAX, hslcolor.getLuminence());
    }
    
    @Test
    public void testBrighten_min_luminance() {
        hslcolor.setLuminence(0);
        hslcolor.brighten(-2.0f);
        assertEquals("Brightness should be unchanged", 0, hslcolor.getLuminence());
    }
    
    @Test
    public void testBrighten_half_luminance() {
        hslcolor.setLuminence(HSLColor.HSLMAX / 2);
        hslcolor.brighten(2.0f);
        assertEquals("Brightness should be unchanged", HSLColor.HSLMAX / 2, hslcolor.getLuminence());
    }
    
    @Test
    public void testBrighten_small_positive_value() {
        hslcolor.brighten(0.01f);
        assertTrue("Brightness should be greater than original", hslcolor.getLuminence() > 128);
    }
    
    @Test
    public void testBrighten_large_positive_value() {
        hslcolor.brighten(10.0f);
        assertEquals("Brightness should be maximum", HSLColor.HSLMAX, hslcolor.getLuminence());
    }
}