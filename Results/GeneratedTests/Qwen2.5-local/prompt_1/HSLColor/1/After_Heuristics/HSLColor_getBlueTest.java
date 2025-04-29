// HSLColor_getBlueTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getBlue() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getBlueTest {
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
    public void testGetBlueTypicalCase() {
        // Arrange
        int expected = 128;
        hslColor.setBlue(expected);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueEdgeCaseMin() {
        // Arrange
        int expected = 0;
        hslColor.setBlue(expected);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueEdgeCaseMax() {
        // Arrange
        int expected = 255;
        hslColor.setBlue(expected);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueNegativeInput() {
        // Arrange
        int input = -1;
        try {
            hslColor.setBlue(input);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetBlueOverRangeInput() {
        // Arrange
        int input = 256;
        try {
            hslColor.setBlue(input);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetBlueAfterReverseColor() {
        // Arrange
        int expected = 128;
        hslColor.setBlue(expected);
        hslColor.reverseColor();

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueAfterBrighten() {
        // Arrange
        int expected = 192;
        hslColor.setBlue(128);
        hslColor.brighten(1.5f);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueAfterBlend() {
        // Arrange
        int expected = 160;
        hslColor.setBlue(128);
        hslColor.blend(200, 200, 200, 0.5f);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueAfterSetLuminence() {
        // Arrange
        int expected = 128;
        hslColor.setBlue(128);
        hslColor.setLuminence(128);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBlueAfterSetSaturation() {
        // Arrange
        int expected = 128;
        hslColor.setBlue(128);
        hslColor.setSaturation(128);

        // Act
        int actual = hslColor.getBlue();

        // Assert
        assertEquals(expected, actual);
    }
}