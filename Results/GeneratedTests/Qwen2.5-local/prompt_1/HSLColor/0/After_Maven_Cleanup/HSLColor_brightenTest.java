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
    private HSLColor hslcolor;

    @Before
    public void setUp() {
        hslcolor = new HSLColor();
        hslcolor.initHSLbyRGB(128, 128, 128); // Gray
    }

    @After
    public void tearDown() {
        hslcolor = null;
    }



    @Test
    public void testBrighten_0_percent() {
        hslcolor.brighten((float) 0.0);
        assertEquals(128, hslcolor.getRed());
        assertEquals(128, hslcolor.getGreen());
        assertEquals(128, hslcolor.getBlue());
    }


    @Test
    public void testBrighten_large_positive_percent() {
        hslcolor.brighten((float) 2.0);
        assertEquals(255, hslcolor.getRed());
        assertEquals(255, hslcolor.getGreen());
        assertEquals(255, hslcolor.getBlue());
    }

    @Test
    public void testBrighten_large_negative_percent() {
        hslcolor.brighten(-(float) 2.0);
        assertEquals(0, hslcolor.getRed());
        assertEquals(0, hslcolor.getGreen());
        assertEquals(0, hslcolor.getBlue());
    }



}