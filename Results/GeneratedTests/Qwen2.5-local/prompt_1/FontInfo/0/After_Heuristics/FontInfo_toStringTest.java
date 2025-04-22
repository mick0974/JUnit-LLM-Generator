// FontInfo_toStringTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public toString() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_toStringTest {
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
    public void testToString_typical_case() {
        assertEquals("Arial, 14, bold", fontInfo.toString());
    }

    @Test
    public void testToString_no_style() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);
        assertEquals("Arial, 14", fontInfo.toString());
    }

    @Test
    public void testToString_only_italic() {
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);
        assertEquals("Arial, 14, italic", fontInfo.toString());
    }

    @Test
    public void testToString_null_family() {
        fontInfo.setFamily(null);
        assertEquals("Monospaced, 14, bold", fontInfo.toString());
    }

    @Test
    public void testToString_zero_size() {
        fontInfo.setSize(0);
        assertEquals("Arial, 0, bold", fontInfo.toString());
    }

    @Test
    public void testToString_negative_size() {
        fontInfo.setSize(-5);
        assertEquals("Arial, -5, bold", fontInfo.toString());
    }

    @Test
    public void testToString_max_int_size() {
        fontInfo.setSize(Integer.MAX_VALUE);
        assertEquals("Arial, 2147483647, bold", fontInfo.toString());
    }

    @Test
    public void testToString_min_int_size() {
        fontInfo.setSize(Integer.MIN_VALUE);
        assertEquals("Arial, -2147483648, bold", fontInfo.toString());
    }
    
    @Test
    public void testToString_font_info_clone() {
    	fontInfo.setFamily("Times New Roman");
    	fontInfo.setSize(12);
    	fontInfo.setIsBold(true);
    	fontInfo.setIsItalic(true);
    	FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();
    	assertNotSame(fontInfo, clonedFontInfo);
    	assertEquals(fontInfo, clonedFontInfo);
    }
}