import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class for the FontInfo#setIsItalic(boolean value) method.
 * It contains 10 unit test cases covering various scenarios,
 * following the Arrange-Act-Assert pattern.
 */
public class FontInfo_setIsItalicTest {

    private FontInfo fontInfo;
    private static final String DEFAULT_FAMILY = "Monospaced";
    private static final int DEFAULT_SIZE = 12;

    /**
     * Sets up a default FontInfo object before each test.
     */
    @Before
    public void setUp() {
        // Arrange: Create a default FontInfo object
        fontInfo = new FontInfo();
        // Ensure default state is not italic
        assertFalse("Default FontInfo should not be italic", fontInfo.isItalic());
        assertEquals("Default style should be PLAIN", Font.PLAIN, fontInfo.generateStyle());
    }

    /**
     * Cleans up after each test. (Not strictly necessary here, but good practice)
     */
    @After
    public void tearDown() {
        fontInfo = null;
    }

    /**
     * Test case 1: Set italic to true when initially false.
     */
    @Test
    public void testSetIsItalicTrueFromFalse() {
        // Arrange: fontInfo is initialized to non-italic in setUp()

        // Act: Set italic to true
        fontInfo.setIsItalic(true);

        // Assert: Verify the italic flag is set and style is updated
        assertTrue("isItalic should return true after setting to true", fontInfo.isItalic());
        assertEquals("generateStyle should return ITALIC", Font.ITALIC, fontInfo.generateStyle());
    }

    /**
     * Test case 2: Set italic to false when initially false (no change).
     */
    @Test
    public void testSetIsItalicFalseFromFalse() {
        // Arrange: fontInfo is initialized to non-italic in setUp()

        // Act: Set italic to false
        fontInfo.setIsItalic(false);

        // Assert: Verify the italic flag remains false and style is unchanged
        assertFalse("isItalic should return false after setting to false", fontInfo.isItalic());
        assertEquals("generateStyle should remain PLAIN", Font.PLAIN, fontInfo.generateStyle());
    }

    /**
     * Test case 3: Set italic to false when initially true.
     */
    @Test
    public void testSetIsItalicFalseFromTrue() {
        // Arrange: Set initial state to italic
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: fontInfo should be italic", fontInfo.isItalic());
        assertEquals("Precondition failed: style should be ITALIC", Font.ITALIC, fontInfo.generateStyle());

        // Act: Set italic to false
        fontInfo.setIsItalic(false);

        // Assert: Verify the italic flag is unset and style is updated
        assertFalse("isItalic should return false after setting to false", fontInfo.isItalic());
        assertEquals("generateStyle should return PLAIN", Font.PLAIN, fontInfo.generateStyle());
    }

    /**
     * Test case 4: Set italic to true when initially true (no change).
     */
    @Test
    public void testSetIsItalicTrueFromTrue() {
        // Arrange: Set initial state to italic
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: fontInfo should be italic", fontInfo.isItalic());
        assertEquals("Precondition failed: style should be ITALIC", Font.ITALIC, fontInfo.generateStyle());

        // Act: Set italic to true
        fontInfo.setIsItalic(true);

        // Assert: Verify the italic flag remains true and style is unchanged
        assertTrue("isItalic should remain true after setting to true", fontInfo.isItalic());
        assertEquals("generateStyle should remain ITALIC", Font.ITALIC, fontInfo.generateStyle());
    }

    /**
     * Test case 5: Set italic to true when bold is also true.
     */
    @Test
    public void testSetIsItalicTrueWhenBold() {
        // Arrange: Set initial state to bold but not italic
        fontInfo.setIsBold(true);
        assertTrue("Precondition failed: fontInfo should be bold", fontInfo.isBold());
        assertFalse("Precondition failed: fontInfo should not be italic", fontInfo.isItalic());
        assertEquals("Precondition failed: style should be BOLD", Font.BOLD, fontInfo.generateStyle());

        // Act: Set italic to true
        fontInfo.setIsItalic(true);

        // Assert: Verify both flags are true and style is BOLD | ITALIC
        assertTrue("isItalic should return true after setting to true", fontInfo.isItalic());
        assertTrue("isBold should remain true", fontInfo.isBold());
        assertEquals("generateStyle should return BOLD | ITALIC", Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
    }

    /**
     * Test case 6: Set italic to false when bold is true (should only remove italic).
     */
    @Test
    public void testSetIsItalicFalseWhenBold() {
        // Arrange: Set initial state to bold and italic
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: fontInfo should be bold", fontInfo.isBold());
        assertTrue("Precondition failed: fontInfo should be italic", fontInfo.isItalic());
        assertEquals("Precondition failed: style should be BOLD | ITALIC", Font.BOLD | Font.ITALIC, fontInfo.generateStyle());

        // Act: Set italic to false
        fontInfo.setIsItalic(false);

        // Assert: Verify italic is false, bold remains true, and style is BOLD
        assertFalse("isItalic should return false after setting to false", fontInfo.isItalic());
        assertTrue("isBold should remain true", fontInfo.isBold());
        assertEquals("generateStyle should return BOLD", Font.BOLD, fontInfo.generateStyle());
    }

    /**
     * Test case 7: Verify createFont reflects setting italic to true.
     */
    @Test
    public void testCreateFontReflectsSetItalicTrue() {
        // Arrange: fontInfo is non-italic by default

        // Act: Set italic to true and create the font
        fontInfo.setIsItalic(true);
        Font createdFont = fontInfo.createFont();

        // Assert: Check the created font properties
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", DEFAULT_FAMILY, createdFont.getFamily());
        assertEquals("Font size should match", DEFAULT_SIZE, createdFont.getSize());
        assertTrue("Created font should be italic", createdFont.isItalic());
        assertFalse("Created font should not be bold", createdFont.isBold());
        assertEquals("Created font style should be ITALIC", Font.ITALIC, createdFont.getStyle());
    }

    /**
     * Test case 8: Verify createFont reflects setting italic to false from true.
     */
    @Test
    public void testCreateFontReflectsSetItalicFalse() {
        // Arrange: Set initial state to italic
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: fontInfo should be italic", fontInfo.isItalic());

        // Act: Set italic to false and create the font
        fontInfo.setIsItalic(false);
        Font createdFont = fontInfo.createFont();

        // Assert: Check the created font properties
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", DEFAULT_FAMILY, createdFont.getFamily());
        assertEquals("Font size should match", DEFAULT_SIZE, createdFont.getSize());
        assertFalse("Created font should not be italic", createdFont.isItalic());
        assertFalse("Created font should not be bold", createdFont.isBold());
        assertEquals("Created font style should be PLAIN", Font.PLAIN, createdFont.getStyle());
    }

    /**
     * Test case 9: Verify toString output reflects setting italic to true.
     */
    @Test
    public void testToStringReflectsSetItalicTrue() {
        // Arrange: fontInfo is non-italic by default

        // Act: Set italic to true
        fontInfo.setIsItalic(true);
        String toStringOutput = fontInfo.toString();

        // Assert: Check if the output string contains ", italic"
        assertTrue("toString output should contain ', italic'", toStringOutput.contains(", italic"));
        assertFalse("toString output should not contain ', bold'", toStringOutput.contains(", bold")); // Ensure only italic is added
    }

    /**
     * Test case 10: Verify toString output reflects setting italic to false from true.
     */
    @Test
    public void testToStringReflectsSetItalicFalse() {
        // Arrange: Set initial state to italic
        fontInfo.setIsItalic(true);
        assertTrue("Precondition failed: fontInfo should be italic", fontInfo.isItalic());
        assertTrue("Precondition failed: toString should contain ', italic'", fontInfo.toString().contains(", italic"));

        // Act: Set italic to false
        fontInfo.setIsItalic(false);
        String toStringOutput = fontInfo.toString();

        // Assert: Check if the output string no longer contains ", italic"
        assertFalse("toString output should not contain ', italic'", toStringOutput.contains(", italic"));
        assertFalse("toString output should not contain ', bold'", toStringOutput.contains(", bold"));
    }
}