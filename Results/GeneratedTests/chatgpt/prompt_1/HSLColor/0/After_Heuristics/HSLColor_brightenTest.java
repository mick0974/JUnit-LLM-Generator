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

private HSLColor color;

    @Before
    public void setUp() {
        color = new HSLColor();
    }

    @After
    public void tearDown() {
        color = null;
    }

    @Test
    public void testBrightenZeroPercent() {
        color.initHSLbyRGB(100, 100, 150);
        int initialLum = color.getLuminence();
        color.brighten(0.0f);
        assertEquals(initialLum, color.getLuminence());
    }

    @Test
    public void testBrightenToMaximum() {
        color.initHSLbyRGB(100, 100, 150);
        color.brighten(1.0f);
        assertEquals(255, color.getLuminence());
    }

    @Test
    public void testBrighten100PercentLum0() {
        color.initHSLbyRGB(0, 0, 0);
        color.brighten(1.0f);
        assertEquals(0, color.getLuminence());
    }

    @Test
    public void testBrighten100PercentLumMax() {
        color.initHSLbyRGB(255, 255, 255);
        color.brighten(1.0f);
        assertEquals(255, color.getLuminence());
    }

    @Test
    public void testBrightenNegativePercent() {
        color.initHSLbyRGB(100, 100, 150);
        int initialLum = color.getLuminence();
        color.brighten(-0.5f);
        assertEquals(initialLum, color.getLuminence());
    }

    @Test
    public void testHalfBrighten() {
        color.initHSLbyRGB(100, 100, 150);
        int initialLum = color.getLuminence();
        color.brighten(0.5f);
        assertTrue(color.getLuminence() > initialLum);
    }

    @Test
    public void testBrightenOverMaxCap() {
        color.initHSLbyRGB(100, 100, 255);
        color.brighten(1.5f);
        assertEquals(255, color.getLuminence());
    }

    @Test
    public void testBrighten10Percent() {
        color.initHSLbyRGB(100, 100, 50);
        int expectedLum = Math.min(255, (int)(50 * 1.1));
        color.brighten(0.1f);
        assertEquals(expectedLum, color.getLuminence());
    }

    @Test
    public void testBrighten75Percent() {
        color.initHSLbyRGB(30, 60, 90);
        int expectedLum = Math.min(255, (int)(90 * 1.75));
        color.brighten(0.75f);
        assertEquals(expectedLum, color.getLuminence());
    }

    @Test
    public void testBrighten50PercentWithNormalColor() {
        color.initHSLbyRGB(120, 180, 240);
        int initialLum = color.getLuminence();
        int expectedLum = Math.min(255, (int)(initialLum * 1.50));
        color.brighten(0.5f);
        assertEquals(expectedLum, color.getLuminence());
    }
}