// HSLColor_getGreenTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public getGreen() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_getGreenTest {
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
    public void testGetGreen_typicalCase() {
        hslColor.setGreen(100); // arrange
        assertEquals(100, hslColor.getGreen()); // act and assert
    }

    @Test
    public void testGetGreen_edgeCase_min() {
        hslColor.setGreen(0); // arrange
        assertEquals(0, hslColor.getGreen()); // act and assert
    }

    @Test
    public void testGetGreen_edgeCase_max() {
        hslColor.setGreen(255); // arrange
        assertEquals(255, hslColor.getGreen()); // act and assert
    }

    @Test(expected=NullPointerException)
    public void testGetGreen_nullObject() {
        hslColor = null; // arrange
        hslColor.getGreen(); // act and assert
    }
    
    @Test
    public void testGetGreen_setWithNegativeValue() {
        hslColor.setGreen(-1); // arrange
        assertEquals(0, hslColor.getGreen()); // act and assert
    }
    
    @Test
    public void testGetGreen_setWithHighValue() {
        hslColor.setGreen(256); // arrange
        assertEquals(255, hslColor.getGreen()); // act and assert
    }
    
    @Test
    public void testGetGreen_afterReverseLight() {
        hslColor.setGreen(128); // arrange
        hslColor.reverseLight(); // act
        assertEquals(128, hslColor.getGreen()); // assert
    }
    
    @Test
    public void testGetGreen_afterBrighten() {
        hslColor.setGreen(128); // arrange
        hslColor.brighten(1.5f); // act
        assertEquals(192, hslColor.getGreen()); // assert
    }
    
    @Test
    public void testGetGreen_afterBlend() {
        hslColor.setGreen(128); // arrange
        hslColor.blend(255, 255, 255, 0.5f); // act
        assertEquals(192, hslColor.getGreen()); // assert
    }
    
    @Test
    public void testGetGreen_afterReverseColor() {
        hslColor.setGreen(128); // arrange
        hslColor.reverseColor(); // act
        assertEquals(128, hslColor.getGreen()); // assert
    }
}