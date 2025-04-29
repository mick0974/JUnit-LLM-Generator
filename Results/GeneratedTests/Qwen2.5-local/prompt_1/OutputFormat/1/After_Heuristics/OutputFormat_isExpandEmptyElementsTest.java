// OutputFormat_isExpandEmptyElementsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isExpandEmptyElements() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isExpandEmptyElementsTest {
    private OutputFormat outputFormatUnderTest;

    @Before
    public void setUp() {
        outputFormatUnderTest = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormatUnderTest = null;
    }

    @Test
    public void testIsExpandEmptyElements_InitialValue() {
        boolean actualResult = outputFormatUnderTest.isExpandEmptyElements();
        assertFalse(actualResult);
    }

    @Test
    public void testIsExpandEmptyElements_SetTrue() {
        outputFormatUnderTest.setExpandEmptyElements(true);
        boolean actualResult = outputFormatUnderTest.isExpandEmptyElements();
        assertTrue(actualResult);
    }

    @Test
    public void testIsExpandEmptyElements_SetFalse() {
        outputFormatUnderTest.setExpandEmptyElements(false);
        boolean actualResult = outputFormatUnderTest.isExpandEmptyElements();
        assertFalse(actualResult);
    }
}