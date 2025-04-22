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
    private OutputFormat outputFormat;

    @Before
    public void setUp() {
        outputFormat = new OutputFormat();
    }

    @After
    public void tearDown() {
        outputFormat = null;
    }

    /**
     * Test case for isExpandEmptyElements when it is initially false.
     */
    @Test
    public void testIsExpandEmptyElementsInitiallyFalse() {
        assertFalse(outputFormat.isExpandEmptyElements());
    }

    /**
     * Test case for isExpandEmptyElements when it is set to true.
     */
    @Test
    public void testIsExpandEmptyElementsTrue() {
        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());
    }

    /**
     * Test case for isExpandEmptyElements when it is set to false.
     */
    @Test
    public void testIsExpandEmptyElementsFalse() {
        outputFormat.setExpandEmptyElements(false);
        assertFalse(outputFormat.isExpandEmptyElements());
    }

    /**
     * Test case for isExpandEmptyElements with null input.
     */
    @Test(expected = NullPointerException)
    public void testIsExpandEmptyElementsNullInput() {
        outputFormat = null;
        outputFormat.isExpandEmptyElements();
    }
    
    /**
     * Test case for isExpandEmptyElements with multiple calls.
     */
    @Test
    public void testIsExpandEmptyElementsMultipleCalls() {
        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());
        outputFormat.setExpandEmptyElements(false);
        assertFalse(outputFormat.isExpandEmptyElements());
    }
    
    /**
     * Test case for isExpandEmptyElements with invalid data type.
     */
    @Test(expected = IllegalArgumentException)
    public void testIsExpandEmptyElementsInvalidDataType() {
        outputFormat.setAttributeQuoteCharacter('a');
        outputFormat.isExpandEmptyElements();
    }
    
    /**
     * Test case for isExpandEmptyElements with large number of calls.
     */
    @Test
    public void testIsExpandEmptyElementsLargeNumberOfCalls() {
        for (int i = 0; i < 1000; i++) {
            outputFormat.setExpandEmptyElements(i % 2 == 0);
            assertEquals((i % 2 == 0), outputFormat.isExpandEmptyElements());
        }
    }
    
    /**
     * Test case for isExpandEmptyElements with extreme values.
     */
    @Test
    public void testIsExpandEmptyElementsExtremeValues() {
        outputFormat.setExpandEmptyElements(Integer.MAX_VALUE);
        assertTrue(outputFormat.isExpandEmptyElements());
        outputFormat.setExpandEmptyElements(Integer.MIN_VALUE);
        assertFalse(outputFormat.isExpandEmptyElements());
    }
    
    /**
     * Test case for isExpandEmptyElements with same value multiple times.
     */
    @Test
    public void testIsExpandEmptyElementsSameValueMultipleTimes() {
        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());
        assertTrue(outputFormat.isExpandEmptyElements());
        outputFormat.setExpandEmptyElements(false);
        assertFalse(outputFormat.isExpandEmptyElements());
        assertFalse(outputFormat.isExpandEmptyElements());
    }
}