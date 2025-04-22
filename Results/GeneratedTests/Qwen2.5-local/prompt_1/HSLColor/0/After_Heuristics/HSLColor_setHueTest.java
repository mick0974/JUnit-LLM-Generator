// HSLColor_setHueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setHue(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setHueTest {
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
    public void testSetHueValidRange() {
        hslColor.setHue(180);
        assertEquals(180, hslColor.getHue());
    }

    @Test
    public void testSetHueNegativeInput() {
        hslColor.setHue(-45);
        assertEquals(210, hslColor.getHue()); // Wrap around to 210
    }

    @Test
    public void testSetHuePositiveInputExceedingMax() {
        hslColor.setHue(315);
        assertEquals(15, hslColor.getHue()); // Wrap around to 15
    }

    @Test
    public void testSetHueZero() {
        hslColor.setHue(0);
        assertEquals(0, hslColor.getHue());
    }

    @Test
    public void testSetHueHalfwayPoint() {
        hslColor.setHue(90);
        assertEquals(90, hslColor.getHue());
    }

    @Test
    public void testSetHueFullCircle() {
        hslColor.setHue(360);
        assertEquals(0, hslColor.getHue()); // Wrap around to 0
    }

    @Test
    public void testSetHueExactBoundary() {
        hslColor.setHue(255);
        assertEquals(0, hslColor.getHue()); // Wrap around to 0
    }

    @Test
    public void testSetHueLargeNegativeInput() {
        hslColor.setHue(-1000);
        assertEquals(170, hslColor.getHue()); // Wrap around to 170
    }

    @Test
    public void testSetHueLargePositiveInput() {
        hslColor.setHue(1000);
        assertEquals(30, hslColor.getHue()); // Wrap around to 30
    }

    @Test
    public void testSetHueNullObject() {
        try {
            hslColor.setHue(150);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}