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


    private OutputFormat format;

    @Before
    public void setUp() {
        format = new OutputFormat();
    }

    @After
    public void tearDown() {
        format = null;
    }
    
    /**
     * Test the default value of padText
     */
    @Test
    public void testDefaultPadText() {
        assertFalse("Default padText should be false", format.isPadText());
    }

    /**
     * Test setPadText with true value
     */
    @Test
    public void testSetPadTextTrue() {
        format.setPadText(true);
        assertTrue("padText should be true after setting it to true", format.isPadText());
    }

    /**
     * Test setPadText with false value after setting it to true
     */
    @Test
    public void testSetPadTextFalseAfterTrue() {
        format.setPadText(true);
        format.setPadText(false);
        assertFalse("padText should be false after setting it back to false", format.isPadText());
    }

    /**
     * Test setPadText with false value
     */
    @Test
    public void testSetPadTextFalse() {
        format.setPadText(false);
        assertFalse("padText should remain false after explicitly setting it to false", format.isPadText());
    }

    /**
     * Test the interaction with other boolean settings
     */
    @Test
    public void testPadTextWithOtherSettings() {
        format.setTrimText(true);
        assertFalse("padText should remain false when other setting is true", format.isPadText());
        format.setPadText(true);
        assertTrue("padText should be true when explicitly set even if another boolean is true", format.isPadText());
    }

    /**
     * Test after creating pretty print format
     */
    @Test
    public void testPadTextWithPrettyPrint() {
        format = OutputFormat.createPrettyPrint();
        assertTrue("padText should be true when using pretty print", format.isPadText());
    }

    /**
     * Test after creating compact format
     */
    @Test
    public void testPadTextWithCompactFormat() {
        format = OutputFormat.createCompactFormat();
        assertFalse("padText should be false when using compact format", format.isPadText());
    }

    /**
     * Test create default format and check padText value
     */
    @Test
    public void testDefaultFormatPadText() {
        format = new OutputFormat();
        assertFalse("padText should be false for default OutputFormat object", format.isPadText());
    }
    
    /**
     * Test chaining setters that include enabling padText
     */
    @Test
    public void testChainedSettersWithPadTextEnabled() {
        format.setNewlines(true).setSuppressDeclaration(true).setPadText(true);
        assertTrue("padText should be true after setting with chaining", format.isPadText());
    }

    /**
     * Test chaining setters without enabling padText
     */
    @Test
    public void testChainedSettersWithoutPadTextEnabled() {
        format.setNewlines(true).setSuppressDeclaration(true).setTrimText(true);
        assertFalse("padText should be false without setting it in chaining", format.isPadText());
    }

}