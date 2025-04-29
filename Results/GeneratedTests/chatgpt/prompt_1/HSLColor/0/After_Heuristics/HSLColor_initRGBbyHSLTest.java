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
        int H = 0, S = 0, L = 128; // Middle grey

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(128, hslColor.getRed());
        assertEquals(128, hslColor.getGreen());
        assertEquals(128, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_FullSaturationRed() {
        // Arrange
        int H = 0, S = 255, L = 128; // Vivid red

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(64, hslColor.getGreen());
        assertEquals(64, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_FullSaturationGreen() {
        // Arrange
        int H = 85, S = 255, L = 128; // Vivid green

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(64, hslColor.getRed());
        assertEquals(255, hslColor.getGreen());
        assertEquals(64, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_FullSaturationBlue() {
        // Arrange
        int H = 170, S = 255, L = 128; // Vivid blue

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(64, hslColor.getRed());
        assertEquals(64, hslColor.getGreen());
        assertEquals(255, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_HalfSaturation() {
        // Arrange
        int H = 0, S = 128, L = 128; // Moderate red

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(192, hslColor.getRed());
        assertEquals(96, hslColor.getGreen());
        assertEquals(96, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_FullBrilliance() {
        // Arrange
        int H = 0, S = 255, L = 255; // White

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(255, hslColor.getGreen());
        assertEquals(255, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_NoBrilliance() {
        // Arrange
        int H = 0, S = 255, L = 0; // Black

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
        int H = -20, S = 255, L = 128; // Wrap around

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(255, hslColor.getRed());
        assertEquals(93, hslColor.getGreen());
        assertEquals(64, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_HueGreaterThanMax() {
        // Arrange
        int H = 275, S = 255, L = 128; // Wrap around again

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(191, hslColor.getRed());
        assertEquals(64, hslColor.getGreen());
        assertEquals(255, hslColor.getBlue());
    }

    @Test
    public void testInitRGBbyHSL_NoSaturation() {
        // Arrange
        int H = 0, S = 0, L = 64; // Darker grey

        // Act
        hslColor.initRGBbyHSL(H, S, L);

        // Assert
        assertEquals(64, hslColor.getRed());
        assertEquals(64, hslColor.getGreen());
        assertEquals(64, hslColor.getBlue());
    }
}