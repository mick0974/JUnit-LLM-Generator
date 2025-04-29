// OutputFormat_setExpandEmptyElementsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Test class of OutputFormat.
 * It contains 10 unit test cases for the
 * OutputFormat#public setExpandEmptyElements(boolean expandEmptyElements) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class OutputFormat_setExpandEmptyElementsTest {

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
    public void testSetExpandEmptyElementsTrue() {
        // Arrange
        boolean expected = true;
        
        // Act
        outputFormat.setExpandEmptyElements(expected);

        // Assert
        assertEquals(expected, outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testSetExpandEmptyElementsFalse() {
        // Arrange
        boolean expected = false;
        
        // Act
        outputFormat.setExpandEmptyElements(expected);

        // Assert
        assertEquals(expected, outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testDefaultExpandEmptyElements() {
        // Assert default value
        assertFalse(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testSetExpandEmptyElementsTrueThenFalse() {
        // Arrange-Act
        outputFormat.setExpandEmptyElements(true);
        outputFormat.setExpandEmptyElements(false);
        
        // Assert
        assertFalse(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testSetExpandEmptyElementsFalseThenTrue() {
        // Arrange-Act
        outputFormat.setExpandEmptyElements(false);
        outputFormat.setExpandEmptyElements(true);
        
        // Assert
        assertTrue(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testMultipleSetExpandEmptyElementsValues() {
        // Arrange-Act
        outputFormat.setExpandEmptyElements(false);
        assertFalse(outputFormat.isExpandEmptyElements());

        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());

        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());

        outputFormat.setExpandEmptyElements(false);
        assertFalse(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testSetExpandEmptyElementsMultipleTimesSameValueTrue() {
        // Arrange-Act
        outputFormat.setExpandEmptyElements(true);
        outputFormat.setExpandEmptyElements(true);
        
        // Assert
        assertTrue(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testSetExpandEmptyElementsMultipleTimesSameValueFalse() {
        // Arrange-Act
        outputFormat.setExpandEmptyElements(false);
        outputFormat.setExpandEmptyElements(false);
        
        // Assert
        assertFalse(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testToggleExpandEmptyElements() {
        // Arrange-Act
        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());

        outputFormat.setExpandEmptyElements(false);
        assertFalse(outputFormat.isExpandEmptyElements());

        outputFormat.setExpandEmptyElements(true);
        assertTrue(outputFormat.isExpandEmptyElements());
    }

    @Test
    public void testSetExpandEmptyElementsNoChangeDefault() {
        // Arrange
        boolean initial = outputFormat.isExpandEmptyElements();

        // Act
        outputFormat.setExpandEmptyElements(initial);

        // Assert
        assertEquals(initial, outputFormat.isExpandEmptyElements());
    }
}