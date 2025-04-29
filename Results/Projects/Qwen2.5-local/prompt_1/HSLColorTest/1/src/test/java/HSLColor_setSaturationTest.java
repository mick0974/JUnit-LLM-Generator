// HSLColor_setSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setSaturation(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setSaturationTest {
    private HSLColor hslcolor;

    @Before
    public void setUp() throws Exception {
        hslcolor = new HSLColor();
    }

    @After
    public void tearDown() throws Exception {
        hslcolor = null;
    }

    /**
     * Test case with typical value.
     * The expected result is that the saturation is set to 128.
     */
    @Test
    public void testSetSaturation_typical() {
        hslcolor.setSaturation(128);
        assertEquals(128, hslcolor.getSaturation());
    }

    /**
     * Test case with negative value.
     * The expected result is that the saturation is set to 0.
     */
    @Test
    public void testSetSaturation_negative() {
        hslcolor.setSaturation(-10);
        assertEquals(0, hslcolor.getSaturation());
    }

    /**
     * Test case with maximum value.
     * The expected result is that the saturation is set to 255.
     */
    @Test
    public void testSetSaturation_maximum() {
        hslcolor.setSaturation(255);
        assertEquals(255, hslcolor.getSaturation());
    }

    /**
     * Test case with value greater than maximum.
     * The expected result is that the saturation is set to 255.
     */
    @Test
    public void testSetSaturation_greaterThanMaximum() {
        hslcolor.setSaturation(300);
        assertEquals(255, hslcolor.getSaturation());
    }

    /**
     * Test case with zero value.
     * The expected result is that the saturation is set to 0.
     */
    @Test
    public void testSetSaturation_zero() {
        hslcolor.setSaturation(0);
        assertEquals(0, hslcolor.getSaturation());
    }

    /**
     * Test case with value less than minimum.
     * The expected result is that the saturation is set to 0.
     */
    @Test
    public void testSetSaturation_lessThanMinimum() {
        hslcolor.setSaturation(-50);
        assertEquals(0, hslcolor.getSaturation());
    }

    /**
     * Test case with value equal to minimum.
     * The expected result is that the saturation is set to 0.
     */
    @Test
    public void testSetSaturation_equalToMinimum() {
        hslcolor.setSaturation(0);
        assertEquals(0, hslcolor.getSaturation());
    }

    /**
     * Test case with value equal to maximum.
     * The expected result is that the saturation is set to 255.
     */
    @Test
    public void testSetSaturation_equalToMaximum() {
        hslcolor.setSaturation(255);
        assertEquals(255, hslcolor.getSaturation());
    }

    /**
     * Test case with NaN value.
     * The expected result is that the saturation remains unchanged.
     */

    /**
     * Test case with infinity value.
     * The expected result is that the saturation remains unchanged.
     */
}