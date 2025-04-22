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
     * Test case for setting luminence within normal range
     */
    @Test
    public void testSetLuminenceWithinRange() {
        int expectedLuminance = 128;
        hslcolor.setLuminence(expectedLuminance);
        assertEquals(expectedLuminance, hslcolor.getLuminence());
    }

    /**
     * Test case for setting luminence at minimum value
     */
    @Test
    public void testSetLuminenceAtMinimum() {
        int expectedLuminance = 0;
        hslcolor.setLuminence(expectedLuminance);
        assertEquals(expectedLuminance, hslcolor.getLuminence());
    }

    /**
     * Test case for setting luminence at maximum value
     */
    @Test
    public void testSetLuminenceAtMaximum() {
        int expectedLuminance = 255;
        hslcolor.setLuminence(expectedLuminance);
        assertEquals(expectedLuminance, hslcolor.getLuminence());
    }

    /**
     * Test case for setting luminence below minimum value
     */
    @Test
    public void testSetLuminenceBelowMinimum() {
        int expectedLuminance = -10;
        hslcolor.setLuminence(expectedLuminance);
        assertEquals(0, hslcolor.getLuminence());
    }

    /**
     * Test case for setting luminence above maximum value
     */
    @Test
    public void testSetLuminenceAboveMaximum() {
        int expectedLuminance = 300;
        hslcolor.setLuminence(expectedLuminance);
        assertEquals(255, hslcolor.getLuminence());
    }
}