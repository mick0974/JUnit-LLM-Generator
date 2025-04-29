```java
// FontInfo_toStringTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
// No need to import Serializable for this test class

/**
 * Test class for FontInfo.
 * It contains 10 unit test cases specifically for the
 * FontInfo#toString() method.
 * Follows the Arrange-Act-Assert pattern and covers typical and edge cases.
 * Contains only valid Java code.
 */
public class FontInfo_toStringTest {

    private FontInfo fontInfo;

    @Before
    public void setUp() {
        // Optional: Initialize a common FontInfo instance if needed,
        // but for toString tests, creating specific instances per test
        // often makes the Arrange step clearer.
        // For demonstrating structure, we'll create a default one here,
        // but most tests will create their own specific instance.
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        // Clean up resources if necessary (not needed for FontInfo)
        fontInfo = null;
    }

    /**
     * Test toString() with default font settings (Monospaced, 12, plain).
     */
    @Test
    public void testToStringDefault() {
        // Arrange
        FontInfo fi = new FontInfo(); // Uses default constructor
        String expected = "Monospaced, 12";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should return family and size for default plain font", expected, actual);
    }

    /**
     * Test toString() with bold style only.
     */
    @Test
    public void testToStringBoldOnly() {
        // Arrange
        FontInfo fi = new FontInfo();
        fi.setFamily("Arial");
        fi.setSize(14);
        fi.setIsBold(true);
        fi.setIsItalic(false); // Explicitly set for clarity
        String expected = "Arial, 14, bold";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should include ', bold' when bold is true", expected, actual);
    }

    /**
     * Test toString() with italic style only.
     */
    @Test
    public void testToStringItalicOnly() {
        // Arrange
        FontInfo fi = new FontInfo();
        fi.setFamily("Times New Roman");
        fi.setSize(10);
        fi.setIsBold(false); // Explicitly set for clarity
        fi.setIsItalic(true);
        String expected = "Times New Roman, 10, italic";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should include ', italic' when italic is true", expected, actual);
    }

    /**
     * Test toString() with both bold and italic styles.
     */
    @Test
    public void testToStringBoldAndItalic() {
        // Arrange
        FontInfo fi = new FontInfo();
        fi.setFamily("Verdana");
        fi.setSize(16);
        fi.setIsBold(true);
        fi.setIsItalic(true);
        String expected = "Verdana, 16, bold, italic";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should include ', bold, italic' when both are true", expected, actual);
    }

    /**
     * Test toString() with a different font family and default size/style.
     */
    @Test
    public void testToStringDifferentFamily() {
        // Arrange
        FontInfo fi = new FontInfo();
        fi.setFamily("Courier New");
        // Size remains default 12, style remains default plain
        String expected = "Courier New, 12";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should reflect the changed family name", expected, actual);
    }

    /**
     * Test toString() with a different size and default family/style.
     */
    @Test
    public void testToStringDifferentSize() {
        // Arrange
        FontInfo fi = new FontInfo();
        fi.setSize(20);
        // Family remains default Monospaced, style remains default plain
        String expected = "Monospaced, 20";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should reflect the changed size", expected, actual);
    }

    /**
     * Test toString() when constructed from a specific Font object (plain).
     */
    @Test
    public void testToStringFromPlainFontObject() {
        // Arrange
        Font awtFont = new Font("Dialog", Font.PLAIN, 11);
        FontInfo fi = new FontInfo(awtFont);
        String expected = "Dialog, 11";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should match Font object's properties (plain)", expected, actual);
    }

    /**
     * Test toString() when constructed from a specific Font object (bold).
     */
    @Test
    public void testToStringFromBoldFontObject() {
        // Arrange
        Font awtFont = new Font("SansSerif", Font.BOLD, 18);
        FontInfo fi = new FontInfo(awtFont);
        String expected = "SansSerif, 18, bold";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should match Font object's properties (bold)", expected, actual);
    }

    /**
     * Test toString() when constructed from a specific Font object (italic).
     */
    @Test
    public void testToStringFromItalicFontObject() {
        // Arrange
        Font awtFont = new Font("Serif", Font.ITALIC, 9);
        FontInfo fi = new FontInfo(awtFont);
        String expected = "Serif, 9, italic";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should match Font object's properties (italic)", expected, actual);
    }

    /**
     * Test toString() when constructed from a specific Font object (bold and italic).
     */
    @Test
    public void testToStringFromBoldItalicFontObject() {
        // Arrange
        Font awtFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 13);
        FontInfo fi = new FontInfo(awtFont);
        String expected = "Monospaced, 13, bold, italic";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should match Font object's properties (bold+italic)", expected, actual);
    }

    // Adding one more distinct case for edge size like 0, although Font might handle it differently.
    // FontInfo itself allows any int.
    /**
     * Test toString() with zero size.
     */
    @Test
    public void testToStringZeroSize() {
        // Arrange
        FontInfo fi = new FontInfo();
        fi.setFamily("Arial");
        fi.setSize(0);
        fi.setIsBold(false);
        fi.setIsItalic(false);
        String expected = "Arial, 0";

        // Act
        String actual = fi.toString();

        // Assert
        assertEquals("toString() should handle zero size correctly", expected, actual);
    }

}
```