// OutputFormat_setXHTMLTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setXHTML(boolean xhtml) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setXHTMLTest {

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
    public void testSetXHTMLTrue() {
        // Arrange
        boolean expected = true;

        // Act
        outputFormat.setXHTML(expected);

        // Assert
        assertTrue(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLFalse() {
        // Arrange
        boolean expected = false;

        // Act
        outputFormat.setXHTML(expected);

        // Assert
        assertFalse(outputFormat.isXHTML());
    }

    @Test
    public void testXHTMLDefaultFalse() {
        // Assert
        assertFalse(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLTrueThenFalse() {
        // Arrange
        outputFormat.setXHTML(true);

        // Act
        outputFormat.setXHTML(false);

        // Assert
        assertFalse(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLFalseThenTrue() {
        // Arrange
        outputFormat.setXHTML(false);

        // Act
        outputFormat.setXHTML(true);

        // Assert
        assertTrue(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLIdempotentTrue() {
        // Arrange & Act
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(true);

        // Assert
        assertTrue(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLIdempotentFalse() {
        // Arrange & Act
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(false);

        // Assert
        assertFalse(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLMultipleToggles() {
        // Arrange & Act
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);

        // Assert
        assertFalse(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLMultipleTogglesBackToTrue() {
        // Arrange & Act
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(true);
        outputFormat.setXHTML(false);
        outputFormat.setXHTML(true);

        // Assert
        assertTrue(outputFormat.isXHTML());
    }

    @Test
    public void testSetXHTMLSurvivesGarbageInMemory() {
        // Arrange
        outputFormat.setXHTML(true);

        // Simulating memory corruption (hypothetically)
        OutputFormat tempFormat = new OutputFormat();
        tempFormat.setXHTML(false);
        assertFalse(tempFormat.isXHTML());

        // Assert
        assertTrue(outputFormat.isXHTML());
    }
}