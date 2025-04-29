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
        hslColor.initHSLbyRGB(255, 0, 0); // Red
    }

    @After
    public void tearDown() {
        hslColor = null;
    }

    @Test
    public void testReverseColor_typicalCase() {
        hslColor.reverseColor();
        assertEquals("The hue after reversing should be 180", 180, hslColor.getHue());
    }

    @Test
    public void testReverseColor_edgeCase_black() {
        hslColor.initHSLbyRGB(0, 0, 0); // Black
        hslColor.reverseColor();
        assertEquals("The hue after reversing black should still be 0", 0, hslColor.getHue());
    }

    @Test
    public void testReverseColor_edgeCase_white() {
        hslColor.initHSLbyRGB(255, 255, 255); // White
        hslColor.reverseColor();
        assertEquals("The hue after reversing white should still be 0", 0, hslColor.getHue());
    }
    
    @Test
    public void testReverseColor_negativeHue() {
        hslColor.setHue(-45);
        hslColor.reverseColor();
        assertEquals("The hue after reversing should be 135", 135, hslColor.getHue());
    }

    @Test
    public void testReverseColor_hueGreaterThan360() {
        hslColor.setHue(450);
        hslColor.reverseColor();
        assertEquals("The hue after reversing should be 90", 90, hslColor.getHue());
    }

    @Test
    public void testReverseColor_saturationZero() {
        hslColor.setSaturation(0);
        hslColor.reverseColor();
        assertEquals("The hue after reversing should still be 0", 0, hslColor.getHue());
    }

    @Test
    public void testReverseColor_luminanceZero() {
        hslColor.setLuminance(0);
        hslColor.reverseColor();
        assertEquals("The hue after reversing should still be 0", 0, hslColor.getHue());
    }

}