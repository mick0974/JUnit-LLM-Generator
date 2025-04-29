import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;

/**
 * Test class for the FontInfo#generateStyle() method.
 * Ensures that the correct Font style integer is generated based on
 * the bold and italic properties of the FontInfo instance.
 * Follows the Arrange-Act-Assert pattern.
 */
public class FontInfo_generateStyleTest {

    private FontInfo fontInfo;

    /**
     * Sets up a new FontInfo object before each test.
     * This ensures test isolation.
     */
    @Before
    public void setUp() {
        // Arrange: Create a default FontInfo instance
        fontInfo = new FontInfo();
        // Default state is expected to be: family="Monospaced", size=12, isBold=false, isItalic=false
    }

    // Test Cases

    /**
     * Test case 1: Default state (Plain)
     * Verifies that a default FontInfo generates Font.PLAIN style.
     */
    @Test
    public void testGenerateStyleDefaultIsPlain() {
        // Arrange: Default state set in setUp() (isBold=false, isItalic=false)
        int expectedStyle = Font.PLAIN;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Default style should be PLAIN", expectedStyle, actualStyle);
    }

    /**
     * Test case 2: Bold only
     * Verifies that setting only bold generates Font.BOLD style.
     */
    @Test
    public void testGenerateStyleBoldOnly() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false); // Ensure italic is off
        int expectedStyle = Font.BOLD;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be BOLD when only bold is set", expectedStyle, actualStyle);
    }

    /**
     * Test case 3: Italic only
     * Verifies that setting only italic generates Font.ITALIC style.
     */
    @Test
    public void testGenerateStyleItalicOnly() {
        // Arrange
        fontInfo.setIsBold(false); // Ensure bold is off
        fontInfo.setIsItalic(true);
        int expectedStyle = Font.ITALIC;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be ITALIC when only italic is set", expectedStyle, actualStyle);
    }

    /**
     * Test case 4: Bold and Italic
     * Verifies that setting both bold and italic generates Font.BOLD | Font.ITALIC style.
     */
    @Test
    public void testGenerateStyleBoldAndItalic() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        int expectedStyle = Font.BOLD | Font.ITALIC;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be BOLD | ITALIC when both are set", expectedStyle, actualStyle);
    }

    /**
     * Test case 5: Set Bold true, then false (back to Plain)
     * Verifies resetting bold results in Font.PLAIN style.
     */
    @Test
    public void testGenerateStyleSetBoldTrueThenFalse() {
        // Arrange
        fontInfo.setIsBold(true); // Set bold
        fontInfo.setIsBold(false); // Unset bold
        fontInfo.setIsItalic(false); // Ensure italic is off
        int expectedStyle = Font.PLAIN;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be PLAIN after setting bold then unsetting it", expectedStyle, actualStyle);
    }

    /**
     * Test case 6: Set Italic true, then false (back to Plain)
     * Verifies resetting italic results in Font.PLAIN style.
     */
    @Test
    public void testGenerateStyleSetItalicTrueThenFalse() {
        // Arrange
        fontInfo.setIsItalic(true); // Set italic
        fontInfo.setIsItalic(false); // Unset italic
        fontInfo.setIsBold(false); // Ensure bold is off
        int expectedStyle = Font.PLAIN;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be PLAIN after setting italic then unsetting it", expectedStyle, actualStyle);
    }

    /**
     * Test case 7: Set Bold and Italic, then unset Bold (back to Italic)
     * Verifies resetting bold from bold+italic state results in Font.ITALIC style.
     */
    @Test
    public void testGenerateStyleSetBothThenUnsetBold() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true); // Both set
        fontInfo.setIsBold(false); // Unset bold
        int expectedStyle = Font.ITALIC;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be ITALIC after setting both then unsetting bold", expectedStyle, actualStyle);
    }

    /**
     * Test case 8: Set Bold and Italic, then unset Italic (back to Bold)
     * Verifies resetting italic from bold+italic state results in Font.BOLD style.
     */
    @Test
    public void testGenerateStyleSetBothThenUnsetItalic() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true); // Both set
        fontInfo.setIsItalic(false); // Unset italic
        int expectedStyle = Font.BOLD;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be BOLD after setting both then unsetting italic", expectedStyle, actualStyle);
    }

     /**
     * Test case 9: Set Italic, then set Bold (Bold and Italic)
     * Verifies setting italic then bold results in Font.BOLD | Font.ITALIC style.
     * (Order of setting should not matter).
     */
    @Test
    public void testGenerateStyleSetItalicThenBold() {
        // Arrange
        fontInfo.setIsItalic(true);
        fontInfo.setIsBold(true);
        int expectedStyle = Font.BOLD | Font.ITALIC;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be BOLD | ITALIC when setting italic then bold", expectedStyle, actualStyle);
    }

    /**
     * Test case 10: Set Plain, Set Bold, Set Plain, Set Italic (Italic only)
     * Verifies a sequence of settings leading to italic only state.
     */
    @Test
    public void testGenerateStyleSequenceLeadingToItalic() {
        // Arrange
        fontInfo.setIsBold(false);     // Explicitly Plain
        fontInfo.setIsItalic(false);
        fontInfo.setIsBold(true);      // Now Bold
        fontInfo.setIsBold(false);     // Back to Plain
        fontInfo.setIsItalic(true);    // Now Italic
        int expectedStyle = Font.ITALIC;

        // Act
        int actualStyle = fontInfo.generateStyle();

        // Assert
        assertEquals("Style should be ITALIC after sequence leading to italic only", expectedStyle, actualStyle);
    }

}