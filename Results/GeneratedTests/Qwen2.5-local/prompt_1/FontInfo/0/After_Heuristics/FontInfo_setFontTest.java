// FontInfo_setFontTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setFont(Font font) throws IllegalArgumentException  method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setFontTest {
    private FontInfo fontInfo;
    private Font font;

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
        font = new Font("Serif", Font.BOLD, 14);
    }

    @After
    public void tearDown() {
        fontInfo = null;
        font = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetFont_nullFont() {
        fontInfo.setFont(null);
    }

    @Test
    public void testSetFont_validFont() {
        fontInfo.setFont(font);
        assertEquals("Serif", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertEquals(14, fontInfo.getSize());
    }

    @Test
    public void testSetFont_sameFont() {
        fontInfo.setFont(font);
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();
        assertNotSame(clonedFontInfo, fontInfo);
        assertEquals(fontInfo, clonedFontInfo);
    }
    
    @Test
    public void testSetFont_differentFont() {
        Font anotherFont = new Font("SansSerif", Font.ITALIC, 16);
        fontInfo.setFont(anotherFont);
        assertEquals("SansSerif", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(16, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithNoStyle() {
        Font noStyleFont = new Font("Monospaced", Font.PLAIN, 12);
        fontInfo.setFont(noStyleFont);
        assertEquals("Monospaced", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(12, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithAllStyles() {
        Font allStylesFont = new Font("Dialog", Font.BOLD | Font.ITALIC, 18);
        fontInfo.setFont(allStylesFont);
        assertEquals("Dialog", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(18, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithMixedStyles() {
        Font mixedStylesFont = new Font("Lucida Sans Unicode", Font.BOLD | Font.ITALIC | Font.ROMAN_BASED, 20);
        fontInfo.setFont(mixedStylesFont);
        assertEquals("Lucida Sans Unicode", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(20, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithUnknownFamily() {
        Font unknownFamilyFont = new Font("NonExistentFont", Font.PLAIN, 10);
        fontInfo.setFont(unknownFamilyFont);
        assertEquals("NonExistentFont", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(10, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithLargeSize() {
        Font largeSizeFont = new Font("Arial", Font.PLAIN, 50);
        fontInfo.setFont(largeSizeFont);
        assertEquals("Arial", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(50, fontInfo.getSize());
    }
    
    @Test
    public void testSetFont_fontWithSmallSize() {
        Font smallSizeFont = new Font("Verdana", Font.PLAIN, 8);
        fontInfo.setFont(smallSizeFont);
        assertEquals("Verdana", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(8, fontInfo.getSize());
    }
}