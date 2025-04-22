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




    @Test(expected=NullPointerException)
    public void testGetGreen_nullObject() {
        hslColor = null; // arrange
        hslColor.getGreen(); // act and assert
    }
    
    
    
    
    
    
}