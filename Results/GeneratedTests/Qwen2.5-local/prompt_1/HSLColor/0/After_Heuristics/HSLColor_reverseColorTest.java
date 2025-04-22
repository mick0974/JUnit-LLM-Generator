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
    public void testReverseColorTypicalCase() {
        hslColor.initRGBbyHSL(120, 255, 128); // Green color
        hslColor.reverseColor();
        assertEquals(240, hslColor.getHue());
    }

    @Test
    public void testReverseColorEdgeCaseHueUndefined() {
        hslColor.initRGBbyHSL(UNDEFINED, 255, 128); // Greyscale color
        hslColor.reverseColor();
        assertTrue(hslColor.getHue() == UNDEFINED);
    }
    
    @Test
    public void testReverseColorEdgeCaseHueMax() {
        hslColor.initRGBbyHSL(HSLMAX, 255, 128); // Red color
        hslColor.reverseColor();
        assertEquals(0, hslColor.getHue());
    }
    
    @Test
    public void testReverseColorEdgeCaseHueMin() {
        hslColor.initRGBbyHSL(0, 255, 128); // Red color
        hslColor.reverseColor();
        assertEquals(HSLMAX, hslColor.getHue());
    }
    
    @Test
    public void testReverseColorNegativeHue() {
        hslColor.initRGBbyHSL(-100, 255, 128); // Invalid negative hue
        hslColor.reverseColor();
        assertEquals(170, hslColor.getHue()); // Undefined value
    }
    
    @Test
    public void testReverseColorLargeHue() {
        hslColor.initRGBbyHSL(300, 255, 128); // Invalid large hue
        hslColor.reverseColor();
        assertEquals(100, hslColor.getHue()); // Wraps around
    }
    
    @Test
    public void testReverseColorZeroSaturation() {
        hslColor.initRGBbyHSL(120, 0, 128); // Greyscale color
        hslColor.reverseColor();
        assertTrue(hslColor.getHue() == UNDEFINED);
    }
    
    @Test
    public void testReverseColorMaximumSaturation() {
        hslColor.initRGBbyHSL(120, 255, 128); // Full saturation green
        hslColor.reverseColor();
        assertEquals(240, hslColor.getHue());
    }
    
    @Test
    public void testReverseColorMinimumLuminance() {
        hslColor.initRGBbyHSL(120, 255, 0); // Black
        hslColor.reverseColor();
        assertEquals(120, hslColor.getHue());
    }
    
    @Test
    public void testReverseColorMaximumLuminance() {
        hslColor.initRGBbyHSL(120, 255, HSLMAX); // White
        hslColor.reverseColor();
        assertEquals(120, hslColor.getHue());
    }
}