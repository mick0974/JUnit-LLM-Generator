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
    private HSLColor hslColor;

    @Before
    public void setUp() throws Exception {
        hslColor = new HSLColor();
        hslColor.initRGBbyHSL(180, 100, 128); // Blue
    }

    @After
    public void tearDown() throws Exception {
        hslColor = null;
    }

    @Test
    public void testBlend_WhenFPercentIsZero() {
        hslColor.blend(255, 0, 0, 0); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenFPercentIsOne() {
        hslColor.blend(255, 0, 0, 1); // Red
        assertEquals(0, hslColor.getHue()); // Should become black
        assertEquals(0, hslColor.getSaturation());
        assertEquals(0, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenFPercentIsHalf() {
        hslColor.blend(255, 0, 0, 0.5f); // Red
        assertEquals(90, hslColor.getHue()); // Should be somewhere between blue and red
        assertTrue(hslColor.getSaturation() < 100);
        assertTrue(hslColor.getLuminence() > 0);
    }

    @Test
    public void testBlend_WhenFPercentIsNegative() {
        hslColor.blend(255, 0, 0, -0.5f); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenFPercentIsGreaterThanOne() {
        hslColor.blend(255, 0, 0, 1.5f); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenRIsOutOfRange() {
        hslColor.blend(-1, 0, 0, 0.5f); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenGIsOutOfRange() {
        hslColor.blend(0, -1, 0, 0.5f); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenBIsOutOfRange() {
        hslColor.blend(0, 0, -1, 0.5f); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testBlend_WhenAllParametersAreValid() {
        hslColor.blend(255, 255, 0, 0.5f); // Yellow
        assertEquals(45, hslColor.getHue()); // Should be yellow
        assertTrue(hslColor.getSaturation() < 100);
        assertTrue(hslColor.getLuminence() > 0);
    }
}