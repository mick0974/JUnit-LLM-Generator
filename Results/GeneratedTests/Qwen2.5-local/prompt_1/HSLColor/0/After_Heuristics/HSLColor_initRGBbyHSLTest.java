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
    public void testInitRGBbyHSL_Greyscale() {
        // Arrange
        int H = 0;
        int S = 0;
        int L = 128;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(128, hslColor.getRed());
        assertEquals(128, hslColor.getGreen());
        assertEquals(128, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_MaximumSaturation() {
        // Arrange
        int H = 0;
        int S = 255;
        int L = 128;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_MinimumSaturation() {
        // Arrange
        int H = 0;
        int S = 0;
        int L = 128;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(128, hslColor.getRed());
        assertEquals(128, hslColor.getGreen());
        assertEquals(128, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_White() {
        // Arrange
        int H = 0;
        int S = 0;
        int L = 255;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(255, hslColor.getGreen());
        assertEquals(255, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_Black() {
        // Arrange
        int H = 0;
        int S = 0;
        int L = 0;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(0, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_HalfSaturation() {
        // Arrange
        int H = 0;
        int S = 128;
        int L = 128;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(192, hslColor.getRed());
        assertEquals(64, hslColor.getGreen());
        assertEquals(64, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_HalfLightness() {
        // Arrange
        int H = 0;
        int S = 255;
        int L = 128;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_MaximumLightness() {
        // Arrange
        int H = 0;
        int S = 255;
        int L = 255;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_MinimumLightness() {
        // Arrange
        int H = 0;
        int S = 255;
        int L = 0;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(0, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_NegativeHue() {
        // Arrange
        int H = -1;
        int S = 255;
        int L = 128;

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(0, hslColor.getGreen());
        assertEquals(0, hslColor.getBlue());
    }
}