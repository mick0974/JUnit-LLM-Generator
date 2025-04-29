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
    public void testGetGreen_WhenHueIsZeroAndSaturationIsZero() {
        hslColor.initHSLbyRGB(0, 0, 0); // Black
        assertEquals(0, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsOneThirdAndSaturationIsOne() {
        hslColor.initHSLbyRGB(85, 255, 128); // Light Blue
        assertEquals(128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsTwoThirdsAndSaturationIsOne() {
        hslColor.initHSLbyRGB(170, 255, 128); // Yellow
        assertEquals(255, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsOneAndSaturationIsHalf() {
        hslColor.initHSLbyRGB(255, 128, 128); // Red
        assertEquals(128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsThreeFourthAndSaturationIsHalf() {
        hslColor.initHSLbyRGB(204, 128, 128); // Orange
        assertEquals(128, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsZeroAndSaturationIsOne() {
        hslColor.initHSLbyRGB(0, 255, 128); // Cyan
        assertEquals(255, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsOneAndSaturationIsOne() {
        hslColor.initHSLbyRGB(255, 255, 128); // White
        assertEquals(255, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsNegativeOneAndSaturationIsOne() {
        hslColor.initHSLbyRGB(-255, 255, 128); // Wrap around
        assertEquals(255, hslColor.getGreen());
    }

    @Test
    public void testGetGreen_WhenHueIsPositiveOneAndSaturationIsOne() {
        hslColor.initHSLbyRGB(256, 255, 128); // Wrap around
        assertEquals(255, hslColor.getGreen());
    }
}