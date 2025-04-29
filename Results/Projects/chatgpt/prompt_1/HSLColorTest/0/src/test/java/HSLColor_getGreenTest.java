// HSLColor_getGreenTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getGreen() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getGreenTest {

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
    public void testGetGreen_InitialIsZero() {
        assertEquals("Initial green value should be zero", 0, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_SingleGreenValue() {
        hslColor.initHSLbyRGB(0, 120, 0);
        assertEquals("Green should be 120", 120, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_FullGreenColor() {
        hslColor.initHSLbyRGB(0, 255, 0);
        assertEquals("Green should be 255 for full green color", 255, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_BlueAndGreenMixture() {
        hslColor.initHSLbyRGB(0, 128, 128);
        assertEquals("Green should be 128 for equal blue and green mixture", 128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_RedAndGreenMixture() {
        hslColor.initHSLbyRGB(128, 128, 0);
        assertEquals("Green should be 128 for equal red and green mixture", 128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_GrayColor() {
        hslColor.initHSLbyRGB(128, 128, 128);
        assertEquals("Green should be 128 for gray color", 128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_DarkShade() {
        hslColor.initHSLbyRGB(0, 64, 0);
        assertEquals("Green should be 64 for dark green shade", 64, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_LightShade() {
        hslColor.initHSLbyRGB(192, 192, 192);
        assertEquals("Green should be 192 for light gray shade", 192, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_SaturationZero() {
        hslColor.initRGBbyHSL(120, 0, 128);
        assertEquals("Green should be 128 for zero saturation", 128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_ChangeHue() {
        hslColor.initHSLbyRGB(0, 255, 0);
        hslColor.setHue(60); // shifting hue towards yellow
        assertTrue("Green component should be adjusted when hue changes, expected range 170 to 255", 
                   hslColor.getGreen() >= 170 && hslColor.getGreen() <= 255);
    }
}