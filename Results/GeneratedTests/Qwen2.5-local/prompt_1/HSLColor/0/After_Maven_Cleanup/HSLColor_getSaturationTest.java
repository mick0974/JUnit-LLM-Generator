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
    private HSLColor hslColor;

    @Before
    public void setUp() throws Exception {
        hslColor = new HSLColor();
    }

    @After
    public void tearDown() throws Exception {
        hslColor = null;
    }

    @Test
    public void testGetSaturationTypicalCase() {
        // arrange
        hslColor.initRGBbyHSL(0, 128, 128); 

        // act
        int result = hslColor.getSaturation();

        // assert
        assertEquals(128, result);
    }


    @Test
    public void testGetSaturationEdgeCaseMin() {
        // arrange
        hslColor.initRGBbyHSL(0, 0, 128); 

        // act
        int result = hslColor.getSaturation();

        // assert
        assertEquals(0, result);
    }

    @Test(expected=NullPointerException.class)
    public void testGetSaturationNullObject() {
        // arrange
        hslColor = null;

        // act
        int result = hslColor.getSaturation(); // should throw NullPointerException

        // assert
        fail("Expected NullPointerException");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGetSaturationNegativeValue() {
        // arrange
        hslColor.initRGBbyHSL(0, -1, 128); 

        // act
        int result = hslColor.getSaturation(); // should throw IllegalArgumentException

        // assert
        fail("Expected IllegalArgumentException");
    }

}