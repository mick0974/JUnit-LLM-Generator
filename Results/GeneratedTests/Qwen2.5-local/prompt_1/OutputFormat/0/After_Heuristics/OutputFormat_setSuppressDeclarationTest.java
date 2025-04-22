// OutputFormat_setSuppressDeclarationTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setSuppressDeclaration(boolean suppressDeclaration) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setSuppressDeclarationTest {
    
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
    public void testSetSuppressDeclarationTrue() {
        outputFormat.setSuppressDeclaration(true);
        assertTrue(outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testSetSuppressDeclarationFalse() {
        outputFormat.setSuppressDeclaration(false);
        assertFalse(outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testSetSuppressDeclarationNull() {
        try {
            outputFormat.setSuppressDeclaration(null);
        } catch (NullPointerException e) {
            fail("Expected NullPointerException");
        }
    }

    @Test
    public void testSetSuppressDeclarationEmptyString() {
        outputFormat.setSuppressDeclaration("");
        assertFalse(outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testSetSuppressDeclarationWhitespace() {
        outputFormat.setSuppressDeclaration(" ");
        assertFalse(outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testSetSuppressDeclarationNonBooleanValue() {
        try {
            outputFormat.setSuppressDeclaration(new Object());
        } catch (IllegalArgumentException e) {
            fail("Expected IllegalArgumentException");
        }
    }

    @Test
    public void testSetSuppressDeclarationDefault() {
        outputFormat.setSuppressDeclaration(OutputFormat.DEFAULT_SUPPRESS_DECLARATION);
        assertEquals(OutputFormat.DEFAULT_SUPPRESS_DECLARATION, outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testSetSuppressDeclarationMultipleCalls() {
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setSuppressDeclaration(false);
        assertFalse(outputFormat.isSuppressDeclaration());
    }

    @Test
    public void testSetSuppressDeclarationThreadSafety() throws InterruptedException {
        Runnable task = () -> {
            outputFormat.setSuppressDeclaration(Thread.currentThread().getId() % 2 == 0? true : false);
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertNotEquals(outputFormat.isSuppressDeclaration(), outputFormat.isSuppressDeclaration());
    }
}