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

    @Before
    public void setUp() {
        fontInfo = new FontInfo();
        fontInfo.setFamily("Arial");
        fontInfo.setSize(14);
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testDoesFontMatch_sameFont() {
        Font sameFont = new Font("Arial", Font.BOLD, 14);
        assertTrue(fontInfo.doesFontMatch(sameFont));
    }

    @Test
    public void testDoesFontMatch_differentFamily() {
        Font differentFamily = new Font("Times New Roman", Font.BOLD, 14);
        assertFalse(fontInfo.doesFontMatch(differentFamily));
    }

    @Test
    public void testDoesFontMatch_differentSize() {
        Font differentSize = new Font("Arial", Font.BOLD, 16);
        assertFalse(fontInfo.doesFontMatch(differentSize));
    }

    @Test
    public void testDoesFontMatch_differentStyle() {
        Font differentStyle = new Font("Arial", Font.ITALIC, 14);
        assertFalse(fontInfo.doesFontMatch(differentStyle));
    }

    @Test
    public void testDoesFontMatch_nullFont() {
        assertFalse(fontInfo.doesFontMatch(null));
    }

    @Test
    public void testDoesFontMatch_emptyFamily() {
        fontInfo.setFamily("");
        Font emptyFamilyFont = new Font("", Font.BOLD, 14);
        assertFalse(fontInfo.doesFontMatch(emptyFamilyFont));
    }

    @Test
    public void testDoesFontMatch_whitespaceFamily() {
        fontInfo.setFamily(" ");
        Font whitespaceFamilyFont = new Font(" ", Font.BOLD, 14);
        assertFalse(fontInfo.doesFontMatch(whitespaceFamilyFont));
    }

    @Test
    public void testDoesFontMatch_zeroSize() {
        fontInfo.setSize(0);
        Font zeroSizeFont = new Font("Arial", Font.BOLD, 0);
        assertFalse(fontInfo.doesFontMatch(zeroSizeFont));
    }

    @Test
    public void testDoesFontMatch_negativeSize() {
        fontInfo.setSize(-5);
        Font negativeSizeFont = new Font("Arial", Font.BOLD, -5);
        assertFalse(fontInfo.doesFontMatch(negativeSizeFont));
    }
}