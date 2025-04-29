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
    public void testCreateFont() {
        Font createdFont = fontInfo.createFont();
        assertEquals("Arial", createdFont.getName());
        assertEquals(14, createdFont.getSize());
        assertTrue(createdFont.isBold());
        assertFalse(createdFont.isItalic());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testSetFontWithNullArgument() {
        fontInfo.setFont(null);
    }
    
    @Test
    public void testDoesFontMatch() {
        Font anotherFont = new Font("Arial", Font.BOLD, 14);
        assertTrue(fontInfo.doesFontMatch(anotherFont));
        
        anotherFont = new Font("Times New Roman", Font.BOLD, 14);
        assertFalse(fontInfo.doesFontMatch(anotherFont));
    }
    
    @Test
    public void testGenerateStyle() {
        assertEquals(Font.BOLD, fontInfo.generateStyle());
        
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);
        assertEquals(Font.ITALIC, fontInfo.generateStyle());
        
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals(Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
        
        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(false);
        assertEquals(Font.PLAIN, fontInfo.generateStyle());
    }
    
    @Test
    public void testToString() {
        String expectedString = "Arial, 14, bold";
        assertEquals(expectedString, fontInfo.toString());
    }
    
    @Test
    public void testEqualsAndHashCode() {
        FontInfo anotherFontInfo = new FontInfo();
        anotherFontInfo.setFamily("Arial");
        anotherFontInfo.setSize(14);
        anotherFontInfo.setIsBold(true);
        anotherFontInfo.setIsItalic(false);
        
        assertTrue(fontInfo.equals(anotherFontInfo));
        assertEquals(fontInfo.hashCode(), anotherFontInfo.hashCode());
        
        anotherFontInfo.setIsBold(false);
        assertFalse(fontInfo.equals(anotherFontInfo));
        assertNotEquals(fontInfo.hashCode(), anotherFontInfo.hashCode());
        
        anotherFontInfo.setIsBold(true);
        anotherFontInfo.setIsItalic(true);
        assertFalse(fontInfo.equals(anotherFontInfo));
        assertNotEquals(fontInfo.hashCode(), anotherFontInfo.hashCode());
        
        anotherFontInfo.setIsBold(false);
        anotherFontInfo.setIsItalic(false);
        assertFalse(fontInfo.equals(anotherFontInfo));
        assertNotEquals(fontInfo.hashCode(), anotherFontInfo.hashCode());
    }
}