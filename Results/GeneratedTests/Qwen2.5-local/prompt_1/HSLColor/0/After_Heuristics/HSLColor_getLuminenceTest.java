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
    public void testGetLuminence_typicalCase() {
        // Arrange
        int expected = 128;
        hslColor.setLuminence(expected);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_edgeCase_min() {
        // Arrange
        int expected = 0;
        hslColor.setLuminence(expected);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_edgeCase_max() {
        // Arrange
        int expected = 255;
        hslColor.setLuminence(expected);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_negativeInput() {
        // Arrange
        int expected = 0;
        hslColor.setLuminence(-10);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_overMaxInput() {
        // Arrange
        int expected = 255;
        hslColor.setLuminence(300);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_zeroInput() {
        // Arrange
        int expected = 0;
        hslColor.setLuminence(0);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_maxNegativeInput() {
        // Arrange
        int expected = 0;
        hslColor.setLuminence(Integer.MIN_VALUE);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_maxPositiveInput() {
        // Arrange
        int expected = 255;
        hslColor.setLuminence(Integer.MAX_VALUE);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLuminence_nullInput() {
        // Arrange
        int expected = 0;
        hslColor.setLuminence(null);

        // Act
        int actual = hslColor.getLuminence();

        // Assert
        assertEquals(expected, actual);
    }
}