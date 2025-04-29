// FontInfo_doesFontMatchTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public doesFontMatch(Font font) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_doesFontMatchTest {


    private FontInfo fontInfo;
    private Font testFont;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
        testFont = null;
    }

    @Test
    public void testDoesFontMatch_FullyMatchingFont() {
        // Arrange
        testFont = new Font("Monospaced", Font.PLAIN, 12);
        fontInfo.setFont(testFont);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertTrue("The font should match fully.", result);
    }

    @Test
    public void testDoesFontMatch_NonMatchingFamily() {
        // Arrange
        testFont = new Font("Arial", Font.PLAIN, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertFalse("Font family does not match.", result);
    }

    @Test
    public void testDoesFontMatch_NonMatchingSize() {
        // Arrange
        testFont = new Font("Monospaced", Font.PLAIN, 14);
        
        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertFalse("Font size does not match.", result);
    }

    @Test
    public void testDoesFontMatch_BoldNotMatching() {
        // Arrange
        fontInfo.setIsBold(true);
        testFont = new Font("Monospaced", Font.PLAIN, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertFalse("Bold attribute does not match.", result);
    }

    @Test
    public void testDoesFontMatch_ItalicNotMatching() {
        // Arrange
        fontInfo.setIsItalic(true);
        testFont = new Font("Monospaced", Font.PLAIN, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertFalse("Italic attribute does not match.", result);
    }

    @Test
    public void testDoesFontMatch_NullFont() {
        // Act
        boolean result = fontInfo.doesFontMatch(null);

        // Assert
        assertFalse("Null font should not match.", result);
    }

    @Test
    public void testDoesFontMatch_BoldItalicMatch() {
        // Arrange
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        testFont = new Font("Monospaced", Font.BOLD + Font.ITALIC, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertTrue("Both bold and italic should match.", result);
    }

    @Test
    public void testDoesFontMatch_StyleMismatch() {
        // Arrange
        fontInfo.setIsBold(true);
        testFont = new Font("Monospaced", Font.ITALIC, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertFalse("Style mismatch should return false.", result);
    }

    @Test
    public void testDoesFontMatch_MatchingBoldOnly() {
        // Arrange
        fontInfo.setIsBold(true);
        testFont = new Font("Monospaced", Font.BOLD, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertTrue("Bold style should match.", result);
    }

    @Test
    public void testDoesFontMatch_MatchingItalicOnly() {
        // Arrange
        fontInfo.setIsItalic(true);
        testFont = new Font("Monospaced", Font.ITALIC, 12);

        // Act
        boolean result = fontInfo.doesFontMatch(testFont);

        // Assert
        assertTrue("Italic style should match.", result);
    }
}