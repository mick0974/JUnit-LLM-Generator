// FontInfo_equalsTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public equals(Object obj) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_equalsTest {

    private FontInfo fontInfo1;
    private FontInfo fontInfo2;

    @Before
    public void setUp() {
        fontInfo1 = new FontInfo();
        fontInfo2 = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo1 = null;
        fontInfo2 = null;
    }

    @Test
    public void testEquals_SameObject() {
        // Arrange: The two objects are the same reference
        // Act & Assert
        assertTrue(fontInfo1.equals(fontInfo1));
    }

    @Test
    public void testEquals_NullObject() {
        // Arrange: FontInfo compared to null
        // Act & Assert
        assertFalse(fontInfo1.equals(null));
    }

    @Test
    public void testEquals_DifferentClasses() {
        // Arrange: FontInfo compared with an object of a different type
        Serializable obj = "";
        // Act & Assert
        assertFalse(fontInfo1.equals(obj));
    }

    @Test
    public void testEquals_DifferentFamily() {
        // Arrange: FontInfo objects with different family names
        fontInfo1.setFamily("Serif");
        fontInfo2.setFamily("SansSerif");
        // Act & Assert
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_DifferentBold() {
        // Arrange: FontInfo objects with different bold settings
        fontInfo1.setIsBold(true);
        fontInfo2.setIsBold(false);
        // Act & Assert
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_DifferentItalic() {
        // Arrange: FontInfo objects with different italic settings
        fontInfo1.setIsItalic(true);
        fontInfo2.setIsItalic(false);
        // Act & Assert
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_DifferentSize() {
        // Arrange: FontInfo objects with different sizes
        fontInfo1.setSize(12);
        fontInfo2.setSize(14);
        // Act & Assert
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_AllPropertiesEqual() {
        // Arrange: FontInfo objects with all properties equal
        fontInfo1.setFamily("Arial");
        fontInfo1.setIsBold(true);
        fontInfo1.setIsItalic(true);
        fontInfo1.setSize(16);

        fontInfo2.setFamily("Arial");
        fontInfo2.setIsBold(true);
        fontInfo2.setIsItalic(true);
        fontInfo2.setSize(16);

        // Act & Assert
        assertTrue(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_NullFamilyInOne() {
        // Arrange: FontInfo with one having null family
        fontInfo1.setFamily(null);
        fontInfo2.setFamily("Monospaced");

        // Act & Assert
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void testEquals_NullFamilyInBoth() {
        // Arrange: Both FontInfo instances have null family
        fontInfo1.setFamily(null);
        fontInfo2.setFamily(null);

        // Act & Assert
        assertTrue(fontInfo1.equals(fontInfo2));
    }
}