// FontInfo_isBoldTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isBold() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_isBoldTest {
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
    public void testIsBoldInitiallyFalse() {
        assertFalse("Initially isBold should be false", fontInfo.isBold());
    }

    @Test
    public void testSetAndGetIsBoldTrue() {
        fontInfo.setIsBold(true);
        assertTrue("setIsBold(true) should make isBold return true", fontInfo.isBold());
    }

    @Test
    public void testSetAndGetIsBoldFalse() {
        fontInfo.setIsBold(false);
        assertFalse("setIsBold(false) should make isBold return false", fontInfo.isBold());
    }

    @Test
    public void testSetFontWithBoldAttribute() {
        Font font = new Font("Arial", Font.BOLD, 12);
        fontInfo.setFont(font);
        assertTrue("setFont with bold attribute should make isBold return true", fontInfo.isBold());
    }

    @Test
    public void testSetFontWithNoBoldAttribute() {
        Font font = new Font("Arial", Font.PLAIN, 12);
        fontInfo.setFont(font);
        assertFalse("setFont with no bold attribute should make isBold return false", fontInfo.isBold());
    }

    @Test
    public void testDoesFontMatchWhenBoldDiffers() {
        FontInfo otherFontInfo = new FontInfo();
        otherFontInfo.setFamily("Arial");
        otherFontInfo.setSize(12);
        otherFontInfo.setIsBold(!fontInfo.isBold());

        assertFalse("doesFontMatch should return false when isBold differs", fontInfo.doesFontMatch(otherFontInfo.createFont()));
    }

    @Test
    public void testDoesFontMatchWhenAllAttributesMatch() {
        FontInfo otherFontInfo = new FontInfo();
        otherFontInfo.setFamily("Arial");
        otherFontInfo.setSize(12);
        otherFontInfo.setIsBold(fontInfo.isBold());

        assertTrue("doesFontMatch should return true when all attributes match", fontInfo.doesFontMatch(otherFontInfo.createFont()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetFontWithNullFont() {
        fontInfo.setFont(null);
    }

    @Test
    public void testGenerateStyleWhenBothBoldAndItalicAreTrue() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);

        assertEquals("generateStyle should return Font.BOLD | Font.ITALIC when both bold and italic are true", Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
    }
}