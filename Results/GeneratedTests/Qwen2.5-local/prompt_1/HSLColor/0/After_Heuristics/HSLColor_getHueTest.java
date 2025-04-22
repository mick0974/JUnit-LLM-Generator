// HSLColor_getHueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getHue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getHueTest {
    private HSLColor hslColor;

    @Before
    public void setUp() {
        hslColor = new HSLColor();
        hslColor.initHSLbyRGB(255, 0, 0); // Red
    }

    @After
    public void tearDown() {
        hslColor = null;
    }

    @Test
    public void testGetHue_validCase() {
        assertEquals(0, hslColor.getHue());
    }

    @Test
    public void testGetHue_reverseColor() {
        hslColor.reverseColor();
        assertEquals(128, hslColor.getHue());
    }

    @Test
    public void testGetHue_brighten() {
        hslColor.brighten(2.0f);
        assertEquals(0, hslColor.getHue());
    }

    @Test
    public void testGetHue_blend() {
        hslColor.blend(0, 255, 0, 0.5f);
        assertEquals(192, hslColor.getHue());
    }

    @Test
    public void testGetHue_setHue() {
        hslColor.setHue(240);
        assertEquals(240, hslColor.getHue());
    }

    @Test
    public void testGetHue_greyscale() {
        hslColor.greyscale();
        assertEquals(UNDEFINED, hslColor.getHue());
    }

    @Test(expected = AssertionError.class)
    public void testGetHue_negativeHue() {
        hslColor.setHue(-1);
    }

    @Test(expected = AssertionError.class)
    public void testGetHue_exceedingHue() {
        hslColor.setHue(256);
    }

    @Test
    public void testGetHue_defaultConstructor() {
        HSLColor defaultHslColor = new HSLColor();
        assertEquals(UNDEFINED, defaultHslColor.getHue());
    }
}