// HSLColor_getLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getLuminence() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getLuminenceTest {
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
    public void testGetLuminenceTypicalCase() {
        hslColor.setLuminence(128); // Typical case: luminance is within valid range
        assertEquals(128, hslColor.getLuminence());
    }

    @Test
    public void testGetLuminenceEdgeCaseLow() {
        hslColor.setLuminence(0); // Edge case: luminance at lower limit
        assertEquals(0, hslColor.getLuminence());
    }

    @Test
    public void testGetLuminenceEdgeCaseHigh() {
        hslColor.setLuminence(255); // Edge case: luminance at higher limit
        assertEquals(255, hslColor.getLuminence());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetLuminenceNegativeValueThrowsException() {
        hslColor.setLuminence(-1); // Invalid input: negative value
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetLuminenceExceedingMaxValueThrowsException() {
        hslColor.setLuminence(256); // Invalid input: exceeds maximum value
    }
    
    
    @Test
    public void testGetLuminenceAfterBrightening() {
        hslColor.setLuminence(128);
        hslColor.brighten(1.5f);
        assertEquals(192, hslColor.getLuminence()); // Brightened by 50%
    }
    
    
    @Test
    public void testGetLuminenceAfterSettingHueAndSaturation() {
        hslColor.initRGBbyHSL(120, 255, 128);
        assertEquals(128, hslColor.getLuminence()); // Should retain original luminance after setting hue and saturation
    }
    
}