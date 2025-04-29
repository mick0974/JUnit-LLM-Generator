import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;

/**
 * Test class for FontInfo#doesFontMatch(Font font).
 * Contains 10 unit test cases covering typical and edge cases,
 * following the Arrange-Act-Assert pattern.
 */
public class FontInfo_doesFontMatchTest {

    private FontInfo fontInfo;
    private static final String TEST_FAMILY = "Arial";
    private static final int TEST_SIZE = 14;

    @Before
    public void setUp() {
        // Arrange: Initialize a common FontInfo instance before each test
        fontInfo = new FontInfo();
        fontInfo.setFamily(TEST_FAMILY);
        fontInfo.setSize(TEST_SIZE);
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);
    }

    @After
    public void tearDown() {
        // No resources to release, but good practice to include
        fontInfo = null;
    }

    // --- Test Cases ---

    /**
     * Test case 1: Input font is null.
     * Expected: false
     */
    @Test
    public void testDoesFontMatch_NullFont_ReturnsFalse() {
        // Arrange: fontInfo is set up in @Before, input font is null
        Font nullFont = null;

        // Act
        boolean result = fontInfo.doesFontMatch(nullFont);

        // Assert
        assertFalse("doesFontMatch should return false for a null Font", result);
    }

    /**
     * Test case 2: Exact match with plain font.
     * Expected: true
     */
    @Test
    public void testDoesFontMatch_ExactMatchPlain_ReturnsTrue() {
        // Arrange: fontInfo is plain (default in setUp)
        Font matchingFont = new Font(TEST_FAMILY, Font.PLAIN, TEST_SIZE);

        // Act
        boolean result = fontInfo.doesFontMatch(matchingFont);

        // Assert
        assertTrue("doesFontMatch should return true for an exact plain font match", result);
    }

    /**
     * Test case 3: Exact match with bold font.
     * Expected: true
     */
    @Test
    public void testDoesFontMatch_ExactMatchBold_ReturnsTrue() {
        // Arrange: Set fontInfo to bold
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false); // Ensure only bold
        Font matchingFont = new Font(TEST_FAMILY, Font.BOLD, TEST_SIZE);

        // Act
        boolean result = fontInfo.doesFontMatch(matchingFont);

        // Assert
        assertTrue("doesFontMatch should return true for an exact bold font match", result);
    }

    /**
     * Test case 4: Exact match with italic font.
     * Expected: true
     */
    @Test
    public void testDoesFontMatch_ExactMatchItalic_ReturnsTrue() {
        // Arrange: Set fontInfo to italic
        fontInfo.setIsBold(false); // Ensure only italic
        fontInfo.setIsItalic(true);
        Font matchingFont = new Font(TEST_FAMILY, Font.ITALIC, TEST_SIZE);

        // Act
        boolean result = fontInfo.doesFontMatch(matchingFont);

        // Assert
        assertTrue("doesFontMatch should return true for an exact italic font match", result);
    }

    /**
     * Test case 5: Exact match with bold and italic font.
     * Expected: true
     */
    @Test
    public void testDoesFontMatch_ExactMatchBoldItalic_ReturnsTrue() {
        // Arrange: Set fontInfo to bold and italic
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        Font matchingFont = new Font(TEST_FAMILY, Font.BOLD | Font.ITALIC, TEST_SIZE);

        // Act
        boolean result = fontInfo.doesFontMatch(matchingFont);

        // Assert
        assertTrue("doesFontMatch should return true for an exact bold+italic font match", result);
    }

    /**
     * Test case 6: Mismatch in font family.
     * Expected: false
     */
    @Test
    public void testDoesFontMatch_DifferentFamily_ReturnsFalse() {
        // Arrange: fontInfo is plain (default in setUp)
        Font nonMatchingFont = new Font("Times New Roman", Font.PLAIN, TEST_SIZE);

        // Act
        boolean result = fontInfo.doesFontMatch(nonMatchingFont);

        // Assert
        assertFalse("doesFontMatch should return false when font families differ", result);
    }

    /**
     * Test case 7: Mismatch in font size.
     * Expected: false
     */
    @Test
    public void testDoesFontMatch_DifferentSize_ReturnsFalse() {
        // Arrange: fontInfo is plain (default in setUp)
        Font nonMatchingFont = new Font(TEST_FAMILY, Font.PLAIN, TEST_SIZE + 2); // Different size

        // Act
        boolean result = fontInfo.doesFontMatch(nonMatchingFont);

        // Assert
        assertFalse("doesFontMatch should return false when font sizes differ", result);
    }

    /**
     * Test case 8: Mismatch in style (FontInfo is plain, Font is bold).
     * Expected: false
     */
    @Test
    public void testDoesFontMatch_DifferentStylePlainVsBold_ReturnsFalse() {
        // Arrange: fontInfo is plain (default in setUp)
        Font nonMatchingFont = new Font(TEST_FAMILY, Font.BOLD, TEST_SIZE); // Different style

        // Act
        boolean result = fontInfo.doesFontMatch(nonMatchingFont);

        // Assert
        assertFalse("doesFontMatch should return false when styles differ (plain vs bold)", result);
    }

    /**
     * Test case 9: Mismatch in style (FontInfo is bold, Font is italic).
     * Expected: false
     */
    @Test
    public void testDoesFontMatch_DifferentStyleBoldVsItalic_ReturnsFalse() {
        // Arrange: Set fontInfo to bold
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);
        Font nonMatchingFont = new Font(TEST_FAMILY, Font.ITALIC, TEST_SIZE); // Different style

        // Act
        boolean result = fontInfo.doesFontMatch(nonMatchingFont);

        // Assert
        assertFalse("doesFontMatch should return false when styles differ (bold vs italic)", result);
    }

    /**
     * Test case 10: Mismatch in style (FontInfo is bold+italic, Font is bold only).
     * Expected: false
     */
    @Test
    public void testDoesFontMatch_DifferentStyleBoldItalicVsBold_ReturnsFalse() {
        // Arrange: Set fontInfo to bold and italic
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        Font nonMatchingFont = new Font(TEST_FAMILY, Font.BOLD, TEST_SIZE); // Different style (missing italic)

        // Act
        boolean result = fontInfo.doesFontMatch(nonMatchingFont);

        // Assert
        assertFalse("doesFontMatch should return false when styles differ (bold+italic vs bold)", result);
    }
}