// HSLColor_getRedTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getRed() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getRedTest {
    private HSLColor hslColor;

    @Before
    public void setUp() {
        hslColor = new HSLColor();
    }

    @After




    @Test(expected = NullPointerException)
    public void testGetRed_nullObject() {
        hslColor = null;
        hslColor.getRed();
    }
    
    @Test
    public void testGetRed_afterSettingHueAndSaturation() {
        hslColor.initHSLbyRGB(100, 100, 100);
        assertEquals(100, hslColor.getRed());
    }
    
    @Test
    public void testGetRed_afterSettingLuminance() {
        hslColor.initHSLbyRGB(100, 100, 100);
        hslColor.setLuminence(200);
        assertEquals(200, hslColor.getRed());
    }
    
    @Test
    public void testGetRed_afterReversingColor() {
        hslColor.initHSLbyRGB(100, 100, 100);
        hslColor.reverseColor();
        assertEquals(155, hslColor.getRed());
    }
    
    
    @Test
    public void testGetRed_afterBrightening() {
        hslColor.initHSLbyRGB(100, 100, 100);
        hslColor.brighten(2);
        assertEquals(200, hslColor.getRed());
    }
    
    @Test
    public void testGetRed_afterBlending() {
        hslColor.initHSLbyRGB(100, 100, 100);
        hslColor.blend(200, 200, 200, 0.5f);
        assertEquals(150, hslColor.getRed());
    }
}