// HSLColor_setLuminenceTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setLuminence(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setLuminenceTest {
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
    public void testSetLuminence_validRange() {
        int initialLuminance = hslColor.getLuminence();
        hslColor.setLuminence(128); // Typical value in the valid range
        assertEquals("Luminance should be updated correctly", 128, hslColor.getLuminence());
    }

    @Test
    public void testSetLuminence_belowValidRange() {
        int initialLuminance = hslColor.getLuminence();
        hslColor.setLuminence(-1); // Below the valid range
        assertEquals("Luminance should remain unchanged", initialLuminance, hslColor.getLuminence());
    }

    
    @Test
    public void testSetLuminence_maxValue() {
        int initialValue = hslColor.getLuminence();
        hslColor.setLuminence(255);
        assertEquals("Luminance should be max value", 255, hslColor.getLuminence());
    }
    
    @Test
    public void testSetLuminence_minValue() {
        int initialValue = hslColor.getLuminence();
        hslColor.setLuminence(0);
        assertEquals("Luminance should be min value", 0, hslColor.getLuminence());
    }
    
    @Test
    public void testSetLuminence_middleValue() {
        int initialValue = hslColor.getLuminence();
        hslColor.setLuminence(130);
        assertEquals("Luminance should be middle value", 130, hslColor.getLuminence());
    }
    
    @Test
    public void testSetLuminence_sameValue() {
        int initialValue = hslColor.getLuminence();
        hslColor.setLuminence(initialValue);
        assertEquals("Luminance should not change", initialValue, hslColor.getLuminence());
    }
    
    @Test
    public void testSetLuminence_negativeValue() {
        int initialValue = hslColor.getLuminence();
        hslColor.setLuminence(-10);
        assertEquals("Luminance should not change", initialValue, hslColor.getLuminence());
    }
    
    
    @Test
    public void testSetLuminence_nullObject() {
        try {
            HSLColor hslColor = null;
            hslColor.setLuminence(100);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}