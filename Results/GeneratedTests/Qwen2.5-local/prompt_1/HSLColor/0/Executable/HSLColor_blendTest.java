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
    public void testBlend_WhenFPercentIsNegative() {
        hslColor.blend(255, 0, 0, -0.5f); // Red
        assertEquals(180, hslColor.getHue());
        assertEquals(100, hslColor.getSaturation());
        assertEquals(128, hslColor.getLuminence());
    }





}