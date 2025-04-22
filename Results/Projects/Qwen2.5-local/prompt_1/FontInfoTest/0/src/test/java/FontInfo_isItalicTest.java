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
    public void testIsItalic_false() {
        assertFalse(fontInfo.isItalic());
    }

    @Test
    public void testIsItalic_true() {
        fontInfo.setIsItalic(true);
        assertTrue(fontInfo.isItalic());
    }
    
    
    @Test
    public void testIsItalic_afterSetFontWithItalic_true() {
        Font font = new Font("Serif", Font.ITALIC, 12);
        fontInfo.setFont(font);
        assertTrue(fontInfo.isItalic());
    }
    
    @Test
    public void testIsItalic_afterSetFontWithBoldAndItalic_true() {
        Font font = new Font("Serif", Font.BOLD | Font.ITALIC, 12);
        fontInfo.setFont(font);
        assertTrue(fontInfo.isItalic());
    }
    
    @Test
    public void testIsItalic_afterSetFontWithOnlyBold_false() {
        Font font = new Font("Serif", Font.BOLD, 12);
        fontInfo.setFont(font);
        assertFalse(fontInfo.isItalic());
    }
    
    
    
    
}