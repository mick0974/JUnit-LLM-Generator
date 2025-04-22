// HSLColor_setSaturationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of HSLColor.
 * It contains 10 unit test cases for the
 * HSLColor#public setSaturation(int iToValue) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class HSLColor_setSaturationTest {
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
    public void testSetSaturationValidInput() {
        //Arrange
        int input = 128;
        int expectedOutput = 128;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationNegativeInput() {
        //Arrange
        int input = -5;
        int expectedOutput = 0;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationExceedingMaxInput() {
        //Arrange
        int input = 260;
        int expectedOutput = 255;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationZeroInput() {
        //Arrange
        int input = 0;
        int expectedOutput = 0;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationMaxInput() {
        //Arrange
        int input = 255;
        int expectedOutput = 255;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationHalfMaxInput() {
        //Arrange
        int input = 127;
        int expectedOutput = 127;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationOneThirdMaxInput() {
        //Arrange
        int input = 85;
        int expectedOutput = 85;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationTwoThirdsMaxInput() {
        //Arrange
        int input = 170;
        int expectedOutput = 170;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationQuarterMaxInput() {
        //Arrange
        int input = 64;
        int expectedOutput = 64;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
    
    @Test
    public void testSetSaturationThreeQuartersMaxInput() {
        //Arrange
        int input = 191;
        int expectedOutput = 191;
        
        //Act
        hslColor.setSaturation(input);
        
        //Assert
        assertEquals(expectedOutput, hslColor.getSaturation());
    }
}