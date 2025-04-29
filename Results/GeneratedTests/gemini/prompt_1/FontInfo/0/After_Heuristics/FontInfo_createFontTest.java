import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
// No need to import Serializable for the test class itself

/**
 * Test class for the FontInfo#createFont() method.
 * Contains 10 unit test cases following the Arrange-Act-Assert pattern,
 * covering typical and edge cases for font creation based on FontInfo state.
 */
public class FontInfo_createFontTest {

    // No common setup needed for these tests, each test arranges its own FontInfo
    // @Before and @After are not strictly necessary here but kept for structure if needed later.

    @Before
    public void setUp() {
        // Setup logic before each test if needed
    }

    @After
    public void tearDown() {
        // Teardown logic after each test if needed
    }

    /**
     * Test creating a font from a FontInfo object initialized with default values.
     * Expected: Monospaced, Plain, Size 12.
     */
    @Test
    public void testCreateFontDefault() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Monospaced";
        int expectedStyle = Font.PLAIN;
        int expectedSize = 12;

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match default", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should match default (PLAIN)", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match default", expectedSize, createdFont.getSize());
    }

    /**
     * Test creating a font with a specific family, size, and plain style.
     * Expected: Serif, Plain, Size 14.
     */
    @Test
    public void testCreateFontSpecificPlain() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Serif";
        int expectedStyle = Font.PLAIN;
        int expectedSize = 14;
        fontInfo.setFamily(expectedFamily);
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be PLAIN", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
    }

    /**
     * Test creating a font with bold style only.
     * Expected: Dialog, Bold, Size 10.
     */
    @Test
    public void testCreateFontBoldOnly() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Dialog";
        int expectedStyle = Font.BOLD;
        int expectedSize = 10;
        fontInfo.setFamily(expectedFamily);
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be BOLD", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
        assertTrue("Font should report as bold", createdFont.isBold());
        assertFalse("Font should not report as italic", createdFont.isItalic());
    }

    /**
     * Test creating a font with italic style only.
     * Expected: SansSerif, Italic, Size 16.
     */
    @Test
    public void testCreateFontItalicOnly() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "SansSerif";
        int expectedStyle = Font.ITALIC;
        int expectedSize = 16;
        fontInfo.setFamily(expectedFamily);
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be ITALIC", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
        assertFalse("Font should not report as bold", createdFont.isBold());
        assertTrue("Font should report as italic", createdFont.isItalic());
    }

    /**
     * Test creating a font with both bold and italic styles.
     * Expected: Monospaced, Bold + Italic, Size 12.
     */
    @Test
    public void testCreateFontBoldAndItalic() {
        // Arrange
        FontInfo fontInfo = new FontInfo(); // Starts with Monospaced, 12
        String expectedFamily = "Monospaced";
        int expectedStyle = Font.BOLD | Font.ITALIC;
        int expectedSize = 12;
        // fontInfo.setFamily(expectedFamily); // Already default
        // fontInfo.setSize(expectedSize);   // Already default
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be BOLD + ITALIC", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
        assertTrue("Font should report as bold", createdFont.isBold());
        assertTrue("Font should report as italic", createdFont.isItalic());
    }

    /**
     * Test creating a font with a larger size.
     * Expected: Serif, Plain, Size 36.
     */
    @Test
    public void testCreateFontLargeSize() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Serif";
        int expectedStyle = Font.PLAIN;
        int expectedSize = 36;
        fontInfo.setFamily(expectedFamily);
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be PLAIN", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
    }

    /**
     * Test creating a font with a small size (1).
     * Expected: Dialog, Plain, Size 1.
     */
    @Test
    public void testCreateFontSmallSize() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Dialog";
        int expectedStyle = Font.PLAIN;
        int expectedSize = 1;
        fontInfo.setFamily(expectedFamily);
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be PLAIN", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
    }

    /**
     * Test creating a font with zero size. java.awt.Font allows size 0.
     * Expected: Monospaced, Plain, Size 0.
     */
    @Test
    public void testCreateFontZeroSize() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Monospaced"; // Default
        int expectedStyle = Font.PLAIN;
        int expectedSize = 0;
        // fontInfo.setFamily(expectedFamily); // Default
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(false); // Default
        fontInfo.setIsItalic(false); // Default

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should match default", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be PLAIN", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should be zero", expectedSize, createdFont.getSize());
    }

    /**
     * Test creating a font after setting family to null (should revert to default).
     * Expected: Monospaced, Bold, Size 14.
     */
    @Test
    public void testCreateFontAfterSetFamilyNull() {
        // Arrange
        FontInfo fontInfo = new FontInfo();
        String expectedFamily = "Monospaced"; // Default family
        int expectedStyle = Font.BOLD;
        int expectedSize = 14;
        fontInfo.setFamily("Temporary"); // Set something else first
        fontInfo.setFamily(null); // Set to null, should use default
        fontInfo.setSize(expectedSize);
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);

        // Act
        Font createdFont = fontInfo.createFont();

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        assertEquals("Font family should revert to default", expectedFamily, createdFont.getFamily());
        assertEquals("Font style should be BOLD", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match", expectedSize, createdFont.getSize());
    }

    /**
     * Test creating a font after initializing FontInfo with an existing Font object.
     * Ensures consistency between Font -> FontInfo -> Font.
     * Expected: Arial, Bold + Italic, Size 18.
     */
    @Test
    public void testCreateFontFromFontConstructor() {
        // Arrange
        String expectedFamily = "Arial"; // Use a common logical font name
        int expectedStyle = Font.BOLD | Font.ITALIC;
        int expectedSize = 18;
        Font sourceFont = new Font(expectedFamily, expectedStyle, expectedSize);
        FontInfo fontInfo = new FontInfo(sourceFont); // Initialize FontInfo from Font

        // Act
        Font createdFont = fontInfo.createFont(); // Create Font from FontInfo

        // Assert
        assertNotNull("Created font should not be null", createdFont);
        // Note: Font.getFamily() might return the actual physical font name
        // if "Arial" is mapped. However, it should still match the family
        // stored in fontInfo derived from the source font.
        // assertEquals("Font family should match source", sourceFont.getFamily(), createdFont.getFamily());
        // Safer check: ensure it matches what FontInfo stored:
        assertEquals("Font family should match FontInfo's family", fontInfo.getFamily(), createdFont.getFamily());
        assertEquals("Font style should match source", expectedStyle, createdFont.getStyle());
        assertEquals("Font size should match source", expectedSize, createdFont.getSize());
        // Cross-check properties
        assertEquals("Font family should match expected", expectedFamily, fontInfo.getFamily());
        assertEquals("Font style should match expected", expectedStyle, fontInfo.generateStyle());
        assertEquals("Font size should match expected", expectedSize, fontInfo.getSize());
    }
}