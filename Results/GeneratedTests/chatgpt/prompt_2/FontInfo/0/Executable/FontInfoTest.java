import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Font;

public class FontInfoTest {

    @Test
    public void constructorDefaultTest() {
        FontInfo fontInfo = new FontInfo();
        assertEquals("Monospaced", fontInfo.getFamily());
        assertEquals(12, fontInfo.getSize());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorFontNullTest() {
        new FontInfo(null);
    }


    @Test
    public void cloneTest() {
        FontInfo fontInfo = new FontInfo();
        FontInfo clone = (FontInfo) fontInfo.clone();
        assertEquals(fontInfo, clone);
    }

    @Test
    public void setFamilyNullTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily(null);
        assertEquals("Monospaced", fontInfo.getFamily());
    }

    @Test
    public void setFamilyValidTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily("Courier");
        assertEquals("Courier", fontInfo.getFamily());
    }

    @Test
    public void setIsBoldTrueTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsBold(true);
        assertTrue(fontInfo.isBold());
    }

    @Test
    public void setIsBoldFalseTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsBold(false);
        assertFalse(fontInfo.isBold());
    }

    @Test
    public void setIsItalicTrueTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsItalic(true);
        assertTrue(fontInfo.isItalic());
    }

    @Test
    public void setIsItalicFalseTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsItalic(false);
        assertFalse(fontInfo.isItalic());
    }

    @Test
    public void setSizeTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setSize(16);
        assertEquals(16, fontInfo.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFontNullTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFont(null);
    }


    @Test
    public void doesFontMatchTrueTest() {
        Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 14);
        FontInfo fontInfo = new FontInfo(font);
        assertTrue(fontInfo.doesFontMatch(font));
    }

    @Test
    public void doesFontMatchFalseTest() {
        Font font1 = new Font("Arial", Font.BOLD | Font.ITALIC, 14);
        Font font2 = new Font("Courier", Font.PLAIN, 10);
        FontInfo fontInfo = new FontInfo(font1);
        assertFalse(fontInfo.doesFontMatch(font2));
    }

    @Test
    public void generateStylePlainTest() {
        FontInfo fontInfo = new FontInfo();
        assertEquals(Font.PLAIN, fontInfo.generateStyle());
    }

    @Test
    public void generateStyleBoldTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsBold(true);
        assertEquals(Font.BOLD, fontInfo.generateStyle());
    }

    @Test
    public void generateStyleItalicTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsItalic(true);
        assertEquals(Font.ITALIC, fontInfo.generateStyle());
    }

    @Test
    public void generateStyleBoldItalicTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals(Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
    }

    @Test
    public void createFontTest() {
        FontInfo fontInfo = new FontInfo();
        Font font = fontInfo.createFont();
        assertEquals("Monospaced", font.getFamily());
        assertEquals(12, font.getSize());
        assertEquals(Font.PLAIN, font.getStyle());
    }

    @Test
    public void toStringTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily("Arial");
        fontInfo.setSize(14);
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals("Arial, 14, bold, italic", fontInfo.toString());
    }

    @Test
    public void hashCodeEqualObjectsTest() {
        FontInfo fontInfo1 = new FontInfo();
        FontInfo fontInfo2 = new FontInfo();
        assertEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void hashCodeDifferentObjectsTest() {
        FontInfo fontInfo1 = new FontInfo();
        FontInfo fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Arial");
        assertNotEquals(fontInfo1.hashCode(), fontInfo2.hashCode());
    }

    @Test
    public void equalsSameObjectTest() {
        FontInfo fontInfo = new FontInfo();
        assertTrue(fontInfo.equals(fontInfo));
    }

    @Test
    public void equalsNullTest() {
        FontInfo fontInfo = new FontInfo();
        assertFalse(fontInfo.equals(null));
    }

    @Test
    public void equalsDifferentTypeTest() {
        FontInfo fontInfo = new FontInfo();
        assertFalse(fontInfo.equals(new Object()));
    }

    @Test
    public void equalsDifferentObjectsTest() {
        FontInfo fontInfo1 = new FontInfo();
        FontInfo fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Arial");
        assertFalse(fontInfo1.equals(fontInfo2));
    }

    @Test
    public void equalsEqualObjectsTest() {
        FontInfo fontInfo1 = new FontInfo();
        FontInfo fontInfo2 = new FontInfo();
        assertTrue(fontInfo1.equals(fontInfo2));
    }
}