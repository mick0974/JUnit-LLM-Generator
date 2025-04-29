// HSLColor_reverseColorTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public reverseColor() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_reverseColorTest {

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
    public void testReverseColor_MinHue() {
        color.initHSLbyRGB(255, 0, 0); // Red
        color.reverseColor();
        assertEquals(85, color.getHue()); // Should be Cyan
    }

    @Test
    public void testReverseColor_CoverGrayScale() {
        color.initHSLbyRGB(128, 128, 128); // Medium gray
        color.reverseColor();
        assertEquals(170, color.getHue()); // Hue should be undefined (170) on grayscale
    }

    @Test
    public void testReverseColor_MaxHue() {
        color.initHSLbyRGB(255, 255, 0); // Yellow
        color.reverseColor();
        assertEquals(43, color.getHue()); // Should be Blue
    }

    @Test
    public void testReverseColor_ExactMiddleHue() {
        color.initHSLbyRGB(0, 255, 0); // Green
        color.reverseColor();
        assertEquals(128, color.getHue()); // Should be Magenta
    }
    
    @Test
    public void testReverseColor_NearWhite() {
        color.initHSLbyRGB(250, 250, 250); // Near White
        color.reverseColor();
        assertEquals(170, color.getHue()); // Hue should remain undefined due to greyscale
    }

    @Test
    public void testReverseColor_NearBlack() {
        color.initHSLbyRGB(5, 5, 5); // Near Black
        color.reverseColor();
        assertEquals(170, color.getHue()); // Hue should remain undefined due to greyscale
    }

    @Test
    public void testReverseColor_NoChange() {
        int initialHue = 42;
        color.initRGBbyHSL(initialHue, 255, 255); // Set specific HSL
        color.reverseColor();
        int expectedHue = (initialHue + 128) % 255;
        assertEquals(expectedHue, color.getHue()); // Result should be opposite hue
    }

    @Test
    public void testReverseColor_FromBlue() {
        color.initHSLbyRGB(0, 0, 255); // Blue
        color.reverseColor();
        assertEquals(213, color.getHue()); // Should be Yellow
    }

    @Test
    public void testReverseColor_FromPurple() {
        color.initHSLbyRGB(128, 0, 128); // Purple
        color.reverseColor();
        assertEquals(43, color.getHue()); // Should be a Greenish yellow
    }

    @Test
    public void testReverseColor_FromOrange() {
        color.initHSLbyRGB(255, 165, 0); // Orange
        color.reverseColor();
        assertEquals(127, color.getHue()); // Should be blueish
    }
}