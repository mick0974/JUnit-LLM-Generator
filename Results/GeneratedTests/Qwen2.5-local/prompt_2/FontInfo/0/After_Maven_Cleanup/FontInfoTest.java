import org.junit.Test;
import static org.junit.Assert.*;

public class FontInfoTest {

    @Test
    public void constructorDefaultTest() {
        FontInfo fontInfo = new FontInfo();
        assertEquals("Monospaced", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(12, fontInfo.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullFontTest() {
        new FontInfo(null);
    }

    @Test
    public void setFamilyValidTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily("Arial");
        assertEquals("Arial", fontInfo.getFamily());
    }

    @Test
    public void setFamilyNullTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily(null);
        assertEquals("Monospaced", fontInfo.getFamily());
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
    public void setSizePositiveTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setSize(16);
        assertEquals(16, fontInfo.getSize());
    }

    @Test
    public void setSizeZeroTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setSize(0);
        assertEquals(0, fontInfo.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setFontNullTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFont(null);
    }











    @Test
    public void toStringTest() {
        FontInfo fontInfo = new FontInfo();
        assertEquals("Monospaced, 12", fontInfo.toString());
    }

    @Test
    public void hashCodeTest() {
        FontInfo fontInfo = new FontInfo();
        FontInfo sameFontInfo = new FontInfo();
        assertEquals(fontInfo.hashCode(), sameFontInfo.hashCode());

        FontInfo differentFontInfo = new FontInfo();
        differentFontInfo.setFamily("Arial");
        assertNotEquals(fontInfo.hashCode(), differentFontInfo.hashCode());
    }

    @Test
    public void equalsNullTest() {
        FontInfo fontInfo = new FontInfo();
        assertFalse(fontInfo.equals(null));
    }

    @Test
    public void equalsDifferentClassTest() {
        FontInfo fontInfo = new FontInfo();
        assertFalse(fontInfo.equals(new Object()));
    }

    @Test
    public void equalsSameObjectTest() {
        FontInfo fontInfo = new FontInfo();
        assertTrue(fontInfo.equals(fontInfo));
    }

    @Test
    public void equalsDifferentFamilyTest() {
        FontInfo fontInfo = new FontInfo();
        FontInfo differentFontInfo = new FontInfo();
        differentFontInfo.setFamily("Arial");
        assertFalse(fontInfo.equals(differentFontInfo));
    }

    @Test
    public void equalsDifferentSizeTest() {
        FontInfo fontInfo = new FontInfo();
        FontInfo differentFontInfo = new FontInfo();
        differentFontInfo.setSize(16);
        assertFalse(fontInfo.equals(differentFontInfo));
    }

    @Test
    public void equalsDifferentStyleTest() {
        FontInfo fontInfo = new FontInfo();
        FontInfo differentFontInfo = new FontInfo();
        differentFontInfo.setIsBold(true);
        assertFalse(fontInfo.equals(differentFontInfo));
    }

    @Test
    public void equalsSameTest() {
        FontInfo fontInfo = new FontInfo();
        FontInfo sameFontInfo = new FontInfo();
        assertTrue(fontInfo.equals(sameFontInfo));
    }
}