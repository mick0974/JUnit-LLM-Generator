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
        fontInfo = new FontInfo(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testDoesFontMatch_sameFont() {
        Font sameFont = new Font("Arial", Font.BOLD | Font.ITALIC, 14);
        assertTrue(fontInfo.doesFontMatch(sameFont));
    }


    @Test
    public void testDoesFontMatch_differentSize() {
        Font differentSize = new Font("Arial", Font.BOLD | Font.ITALIC, 15);
        assertFalse(fontInfo.doesFontMatch(differentSize));
    }

    @Test
    public void testDoesFontMatch_differentStyle() {
        Font differentStyle = new Font("Arial", Font.PLAIN, 14);
        assertFalse(fontInfo.doesFontMatch(differentStyle));
    }

    @Test
    public void testDoesFontMatch_nullFont() {
        Font nullFont = null;
        assertFalse(fontInfo.doesFontMatch(nullFont));
    }

    @Test
    public void testDoesFontMatch_boldOnly() {
        Font boldOnly = new Font("Arial", Font.BOLD, 14);
        assertFalse(fontInfo.doesFontMatch(boldOnly));
    }

    @Test
    public void testDoesFontMatch_italicOnly() {
        Font italicOnly = new Font("Arial", Font.ITALIC, 14);
        assertFalse(fontInfo.doesFontMatch(italicOnly));
    }

    @Test
    public void testDoesFontMatch_plainOnly() {
        Font plainOnly = new Font("Arial", Font.PLAIN, 14);
        assertFalse(fontInfo.doesFontMatch(plainOnly));
    }

    @Test
    public void testDoesFontMatch_sameFamilyDifferentSize() {
        Font sameFamilyDifferentSize = new Font("Arial", Font.BOLD | Font.ITALIC, 16);
        assertFalse(fontInfo.doesFontMatch(sameFamilyDifferentSize));
    }
    
    @Test
    public void testDoesFontMatch_sameFamilySameSizeDifferentStyle() {
        Font sameFamilySameSizeDifferentStyle = new Font("Arial", Font.PLAIN, 14);
        assertFalse(fontInfo.doesFontMatch(sameFamilySameSizeDifferentStyle));
    }
}