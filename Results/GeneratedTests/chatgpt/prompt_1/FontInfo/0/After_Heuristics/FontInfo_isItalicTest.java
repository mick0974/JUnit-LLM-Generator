// FontInfo_isItalicTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isItalic() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_isItalicTest {

private FontInfo fontInfo;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testDefaultIsItalic() {
        // Arrange & Act
        boolean isItalic = fontInfo.isItalic();
        
        // Assert
        assertFalse("Default font should not be italic", isItalic);
    }

    @Test
    public void testSetIsItalicTrue() {
        // Arrange
        fontInfo.setIsItalic(true);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertTrue("Font should be italic after setting to true", isItalic);
    }

    @Test
    public void testSetIsItalicFalse() {
        // Arrange
        fontInfo.setIsItalic(true);
        fontInfo.setIsItalic(false);

        // Act
        boolean isItalic = fontInfo.isItalic();

        // Assert
        assertFalse("Font should not be italic after setting to false", isItalic);
    }

    @Test
    public void testClonePreservesItalic() throws CloneNotSupportedException {
        // Arrange
        fontInfo.setIsItalic(true);
        
        // Act
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();

        // Assert
        assertEquals("Cloned font should preserve italic property", fontInfo.isItalic(), clonedFontInfo.isItalic());
    }

    @Test
    public void testFontConstructorItalic() {
        // Arrange
        Font italicFont = new Font("Arial", Font.ITALIC, 12);

        // Act
        FontInfo italicFontInfo = new FontInfo(italicFont);

        // Assert
        assertTrue("FontInfo should be italic when created with an italic Font", italicFontInfo.isItalic());
    }

    @Test
    public void testFontConstructorNotItalic() {
        // Arrange
        Font plainFont = new Font("Arial", Font.PLAIN, 12);

        // Act
        FontInfo plainFontInfo = new FontInfo(plainFont);

        // Assert
        assertFalse("FontInfo should not be italic when created with a plain Font", plainFontInfo.isItalic());
    }

    @Test
    public void testDoesFontMatchForItalicFont() {
        // Arrange
        Font italicFont = new Font("Arial", Font.ITALIC, 12);
        fontInfo.setFont(italicFont);

        // Act & Assert
        assertTrue("FontInfo should match an identical italic Font", fontInfo.doesFontMatch(italicFont));
    }

    @Test
    public void testDoesFontMatchForNonItalicFont() {
        // Arrange
        Font plainFont = new Font("Arial", Font.PLAIN, 12);
        fontInfo.setFont(plainFont);

        // Act & Assert
        assertTrue("FontInfo should match an identical non-italic Font", fontInfo.doesFontMatch(plainFont));
    }

    @Test
    public void testDoesFontMatchDifferentStyle() {
        // Arrange
        Font font = new Font("Arial", Font.ITALIC, 12);
        Font differentStyleFont = new Font("Arial", Font.BOLD, 12);
        fontInfo.setFont(font);
        
        // Act & Assert
        assertFalse("FontInfo should not match a Font with a different style", fontInfo.doesFontMatch(differentStyleFont));
    }

    @Test
    public void testGenerateStyleForItalic() {
        // Arrange
        fontInfo.setIsItalic(true);

        // Act
        int style = fontInfo.generateStyle();

        // Assert
        assertEquals("Generated style should be Font.ITALIC", Font.ITALIC, style);
    }
}