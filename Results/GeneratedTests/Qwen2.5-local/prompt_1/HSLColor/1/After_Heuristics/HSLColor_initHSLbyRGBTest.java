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
    public void testInitHSLbyRGBWithBlack() {
        hslColor.initHSLbyRGB(0, 0, 0);
        assertEquals(0, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(0, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithWhite() {
        hslColor.initHSLbyRGB(255, 255, 255);
        assertEquals(0, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(255, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithRed() {
        hslColor.initHSLbyRGB(255, 0, 0);
        assertEquals(0, hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithGreen() {
        hslColor.initHSLbyRGB(0, 255, 0);
        assertEquals((int)(Math.PI/2), hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithBlue() {
        hslColor.initHSLbyRGB(0, 0, 255);
        assertEquals((int)(Math.PI), hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithYellow() {
        hslColor.initHSLbyRGB(255, 255, 0);
        assertEquals((int)(Math.PI/6), hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithCyan() {
        hslColor.initHSLbyRGB(0, 255, 255);
        assertEquals((int)(5*(Math.PI/6)), hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithMagenta() {
        hslColor.initHSLbyRGB(255, 0, 255);
        assertEquals((int)(4*(Math.PI/6)), hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithPurple() {
        hslColor.initHSLbyRGB(128, 0, 128);
        assertEquals((int)(3*(Math.PI/6)), hslColor.getHue());
        assertEquals(255, hslColor.getSaturation());
        assertEquals(64, hslColor.getLuminence());
    }

    @Test
    public void testInitHSLbyRGBWithGrey() {
        hslColor.initHSLbyRGB(128, 128, 128);
        assertEquals(0, hslColor.getHue());
        assertEquals(0, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }
}