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
    }

    @After
    public void tearDown() {
        fontInfo = null;
    }

    @Test
    public void testToString_DefaultConstructor() {
        assertEquals("Monospaced, 12", fontInfo.toString());
    }

    @Test
    public void testToString_BoldFont() {
        fontInfo.setIsBold(true);
        assertEquals("Monospaced, 12, bold", fontInfo.toString());
    }

    @Test
    public void testToString_ItalicFont() {
        fontInfo.setIsItalic(true);
        assertEquals("Monospaced, 12, italic", fontInfo.toString());
    }

    @Test
    public void testToString_BoldItalicFont() {
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals("Monospaced, 12, bold, italic", fontInfo.toString());
    }

    @Test
    public void testToString_CustomFamily() {
        fontInfo.setFamily("Serif");
        assertEquals("Serif, 12", fontInfo.toString());
    }

    @Test
    public void testToString_CustomSize() {
        fontInfo.setSize(14);
        assertEquals("Monospaced, 14", fontInfo.toString());
    }

    @Test
    public void testToString_CustomFamilyAndSize() {
        fontInfo.setFamily("Serif");
        fontInfo.setSize(16);
        assertEquals("Serif, 16", fontInfo.toString());
    }

    @Test
    public void testToString_CustomFont() {
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 14);
        fontInfo.setFont(font);
        assertEquals("Arial, 14, bold, italic", fontInfo.toString());
    }

    @Test
    public void testToString_NullFamily() {
        fontInfo.setFamily(null);
        assertEquals("Monospaced, 12", fontInfo.toString());
    }

    @Test
    public void testToString_DefaultAfterAddingFont() {
        Font font = new Font("Verdana", Font.PLAIN, 10);
        fontInfo.setFont(font);
        assertEquals("Verdana, 10", fontInfo.toString());
    }
}