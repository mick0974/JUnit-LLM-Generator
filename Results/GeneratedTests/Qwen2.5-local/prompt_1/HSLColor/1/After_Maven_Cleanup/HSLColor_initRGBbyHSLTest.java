// HSLColor_initRGBbyHSLTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public initRGBbyHSL(int H, int S, int L) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_initRGBbyHSLTest {
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
    public void testInitRGBbyHSL_Hue0_Sat0_Lum0() {
        hslColor.initRGBbyHSL(0, 0, 0);
        assertEquals(0, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }









}