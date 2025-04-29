// OutputFormat_isTrimTextTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public isTrimText() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_isTrimTextTest {
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
     * Test Case ID: OutputFormat-isTrimText-TC001
     * Test Case Description: Check initial state of isTrimText()
     * Expected Result: isTrimText() should return false initially.
     */
    @Test
    public void testInitialIsTrimTextState() {
        assertFalse(outputFormat.isTrimText());
    }

    /**
     * Test Case ID: OutputFormat-isTrimText-TC002
     * Test Case Description: Verify that isTrimText() returns true after calling setTrimText(true)
     * Expected Result: isTrimText() should return true.
     */
    @Test
    public void testSetTrimTextTrue() {
        outputFormat.setTrimText(true);
        assertTrue(outputFormat.isTrimText());
    }

    /**
     * Test Case ID: OutputFormat-isTrimText-TC003
     * Test Case Description: Verify that isTrimText() returns false after calling setTrimText(false)
     * Expected Result: isTrimText() should return false.
     */
    @Test
    public void testSetTrimTextFalse() {
        outputFormat.setTrimText(false);
        assertFalse(outputFormat.isTrimText());
    }

    /**
     * Test Case ID: OutputFormat-isTrimText-TC004
     * Test Case Description: Verify that isTrimText() remains unchanged after multiple calls
     * Expected Result: isTrimText() should return the same value after multiple calls.
     */
    @Test
    public void testMultipleCallsToSetTrimText() {
        outputFormat.setTrimText(true);
        outputFormat.setTrimText(false);
        outputFormat.setTrimText(true);
        assertTrue(outputFormat.isTrimText());
    }

    /**
     * Test Case ID: OutputFormat-isTrimText-TC005
     * Test Case Description: Verify that isTrimText() remains unchanged after calling setTrimText with different parameters
     * Expected Result: isTrimText() should return the same value regardless of the parameter passed.
     */

    /**
     * Test Case ID: OutputFormat-isTrimText-TC006
     * Test Case Description: Verify that isTrimText() returns correct value after calling setTrimText with negative parameter
     * Expected Result: isTrimText() should return false for negative parameter.
     */

    /**
     * Test Case ID: OutputFormat-isTrimText-TC007
     * Test Case Description: Verify that isTrimText() returns correct value after calling setTrimText with large positive parameter
     * Expected Result: isTrimText() should return true for large positive parameter.
     */

    /**
     * Test Case ID: OutputFormat-isTrimText-TC008
     * Test Case Description: Verify that isTrimText() returns correct value after calling setTrimText with zero
     * Expected Result: isTrimText() should return false for zero.
     */

    /**
     * Test Case ID: OutputFormat-isTrimText-TC009
     * Test Case Description: Verify that isTrimText() returns correct value after calling setTrimText with Boolean.TRUE
     * Expected Result: isTrimText() should return true for Boolean.TRUE.
     */
    @Test
    public void testSetTrimTextWithBooleanTRUE() {
        outputFormat.setTrimText(Boolean.TRUE);
        assertTrue(outputFormat.isTrimText());
    }

    /**
     * Test Case ID: OutputFormat-isTrimText-TC010
     * Test Case Description: Verify that isTrimText() returns correct value after calling setTrimText with Boolean.FALSE
     * Expected Result: isTrimText() should return false for Boolean.FALSE.
     */
    @Test
    public void testSetTrimTextWithBooleanFALSE() {
        outputFormat.setTrimText(Boolean.FALSE);
        assertFalse(outputFormat.isTrimText());
    }
}