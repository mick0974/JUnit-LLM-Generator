// OutputFormat_isPadTextTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isPadText() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isPadTextTest {
    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    @Test
    public void testIsPadTextInitiallyFalse() {
        assertFalse(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextTrue() {
        outputFormat.setPadText(true);
        assertTrue(outputFormat.isPadText());
    }

    @Test
    public void testSetPadTextFalse() {
        outputFormat.setPadText(false);
        assertFalse(outputFormat.isPadText());
    }


    
    
    
    
    
}