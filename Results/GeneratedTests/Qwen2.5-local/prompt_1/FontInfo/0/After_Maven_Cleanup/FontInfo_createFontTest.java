// FontInfo_createFontTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public createFont() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_createFontTest {
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
    public void testCreateFont_defaultValues() {
        Font font = fontInfo.createFont();
        assertEquals("Monospaced", font.getFamily());
        assertEquals(12, font.getSize());
        assertFalse(font.isBold());
        assertFalse(font.isItalic());
    }


    @Test
    public void testCreateFont_noStyle() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);

        Font font = fontInfo.createFont();
        assertEquals(Font.PLAIN, font.getStyle());
    }

    @Test
    public void testCreateFont_boldOnly() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);

        Font font = fontInfo.createFont();
        assertEquals(Font.BOLD, font.getStyle());
    }

    @Test
    public void testCreateFont_italicOnly() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);

        Font font = fontInfo.createFont();
        assertEquals(Font.ITALIC, font.getStyle());
    }

    @Test
    public void testCreateFont_bothBoldAndItalic() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);

        Font font = fontInfo.createFont();
        assertEquals(Font.BOLD | Font.ITALIC, font.getStyle());
    }
}