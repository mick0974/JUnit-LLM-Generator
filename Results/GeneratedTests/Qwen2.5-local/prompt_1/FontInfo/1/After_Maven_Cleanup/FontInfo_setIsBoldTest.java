// FontInfo_setIsBoldTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public setIsBold(boolean value) method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_setIsBoldTest {
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
    public void testSetIsBold_true() {
        fontInfo.setIsBold(true);
        assertTrue("Expected setIsBold to be true", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_false() {
        fontInfo.setIsBold(false);
        assertFalse("Expected setIsBold to be false", fontInfo.isBold());
    }


    @Test
    public void testSetIsBold_defaultValue() {
        fontInfo.setIsBold(false); // Default value
        assertFalse("Expected setIsBold to be false", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_toggle() {
        fontInfo.setIsBold(true);
        fontInfo.setIsBold(false);
        assertFalse("Expected setIsBold to be false", fontInfo.isBold());
    }

    @Test
    public void testSetIsBold_clone() {
        FontInfo clonedFontInfo = (FontInfo) fontInfo.clone();
        clonedFontInfo.setIsBold(true);
        assertFalse("Expected original setIsBold to be false", fontInfo.isBold());
        assertTrue("Expected cloned setIsBold to be true", clonedFontInfo.isBold());
    }

    @Test
    public void testSetIsBold_fontCreation() {
        fontInfo.setIsBold(true);
        assertEquals("Expected bold style in created Font", Font.BOLD, fontInfo.createFont().getStyle());
    }

    @Test
    public void testSetIsBold_styleCombination() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals("Expected bold and italic styles in created Font", Font.BOLD | Font.ITALIC, fontInfo.createFont().getStyle());
    }

    @Test
    public void testSetIsBold_noChange() {
        fontInfo.setIsBold(false);
        fontInfo.setIsBold(false);
        assertFalse("Expected setIsBold to remain false", fontInfo.isBold());
    }
}