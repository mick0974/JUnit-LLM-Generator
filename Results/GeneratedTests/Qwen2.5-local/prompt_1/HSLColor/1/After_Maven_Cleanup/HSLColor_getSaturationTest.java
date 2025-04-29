// HSLColor_getSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getSaturation() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getSaturationTest {
    private HSLColor hslcolor;

    @Before
    public void setUp() {
        hslcolor = new HSLColor();
    }

    @After
    public void tearDown() {
        hslcolor = null;
    }

    @Test
    public void testGetSaturation_withValidInput() {
        //Arrange
        int expectedSaturation = 128;
        hslcolor.setSaturation(expectedSaturation);

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withZeroInput() {
        //Arrange
        int expectedSaturation = 0;
        hslcolor.setSaturation(expectedSaturation);

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withMaxInput() {
        //Arrange
        int expectedSaturation = 255;
        hslcolor.setSaturation(expectedSaturation);

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withNegativeInput() {
        //Arrange
        int expectedSaturation = 0;
        hslcolor.setSaturation(-1);

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withLargePositiveInput() {
        //Arrange
        int expectedSaturation = 255;
        hslcolor.setSaturation(256);

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withNullObject() {
        //Arrange
        HSLColor hslcolor = null;
        int expectedSaturation = 0;

        //Act
        try {
            int actualSaturation = hslcolor.getSaturation();
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            //Assert
            assertNull(hslcolor);
        }
    }

    @Test
    public void testGetSaturation_withUninitializedObject() {
        //Arrange
        HSLColor hslcolor = new HSLColor();
        int expectedSaturation = 0;

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withMixedCaseInput() {
        //Arrange
        int expectedSaturation = 128;
        hslcolor.setSaturation(expectedSaturation);

        //Act
        int actualSaturation = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation);
    }

    @Test
    public void testGetSaturation_withSameInputMultipleTimes() {
        //Arrange
        int expectedSaturation = 128;
        hslcolor.setSaturation(expectedSaturation);

        //Act
        int actualSaturation1 = hslcolor.getSaturation();
        int actualSaturation2 = hslcolor.getSaturation();
        int actualSaturation3 = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation, actualSaturation1);
        assertEquals(expectedSaturation, actualSaturation2);
        assertEquals(expectedSaturation, actualSaturation3);
    }

    @Test
    public void testGetSaturation_withDifferentInputsSequentially() {
        //Arrange
        int expectedSaturation1 = 128;
        int expectedSaturation2 = 255;
        int expectedSaturation3 = 0;

        hslcolor.setSaturation(expectedSaturation1);
        hslcolor.setSaturation(expectedSaturation2);
        hslcolor.setSaturation(expectedSaturation3);

        //Act
        int actualSaturation1 = hslcolor.getSaturation();
        int actualSaturation2 = hslcolor.getSaturation();
        int actualSaturation3 = hslcolor.getSaturation();

        //Assert
        assertEquals(expectedSaturation3, actualSaturation1);
        assertEquals(expectedSaturation3, actualSaturation2);
        assertEquals(expectedSaturation3, actualSaturation3);
    }
}