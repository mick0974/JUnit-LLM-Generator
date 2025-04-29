import org.junit.Test;
import static org.junit.Assert.*;

public class FontInfoTest {

    @Test
    public void defaultConstructorTest() {
        FontInfo fontInfo = new FontInfo();
        assertEquals("Monospaced", fontInfo.getFamily());
        assertFalse(fontInfo.isBold());
        assertFalse(fontInfo.isItalic());
        assertEquals(12, fontInfo.getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullFontTest() {
        new FontInfo(null);
    }

    @Test
    public void setterAndGettersTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily("Arial");
        assertTrue("Arial".equals(fontInfo.getFamily()));

        fontInfo.setIsBold(true);
        assertTrue(fontInfo.isBold());

        fontInfo.setIsItalic(true);
        assertTrue(fontInfo.isItalic());

        fontInfo.setSize(16);
        assertEquals(16, fontInfo.getSize());
    }

    @Test
    public void setFontTest() {
        FontInfo fontInfo = new FontInfo();
        Font font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14);
        fontInfo.setFont(font);

        assertEquals("Times New Roman", fontInfo.getFamily());
        assertTrue(fontInfo.isBold());
        assertTrue(fontInfo.isItalic());
        assertEquals(14, fontInfo.getSize());
    }

    @Test
    public void doesFontMatchTrueTest() {
        FontInfo fontInfo = new FontInfo();
        Font font = new Font("Monospaced", Font.PLAIN, 12);
        assertTrue(fontInfo.doesFontMatch(font));
    }

    @Test
    public void doesFontMatchFalseTest() {
        FontInfo fontInfo = new FontInfo();
        Font font = new Font("Arial", Font.BOLD, 12);
        assertFalse(fontInfo.doesFontMatch(font));
    }

    @Test
    public void generateStyleTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);
        assertEquals(Font.BOLD, fontInfo.generateStyle());

        fontInfo.setIsBold(false);
        fontInfo.setIsItalic(true);
        assertEquals(Font.ITALIC, fontInfo.generateStyle());

        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        assertEquals(Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
    }

    @Test
    public void createFontTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily("Courier New");
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(true);
        fontInfo.setSize(10);

        Font createdFont = fontInfo.createFont();
        assertEquals("Courier New", createdFont.getFamily());
        assertTrue(createdFont.isBold());
        assertTrue(createdFont.isItalic());
        assertEquals(10, createdFont.getSize());
    }

    @Test
    public void cloneTest() {
        FontInfo original = new FontInfo();
        original.setFamily("Verdana");
        original.setIsBold(false);
        original.setIsItalic(true);
        original.setSize(14);

        FontInfo cloned = (FontInfo) original.clone();

        assertEquals(original.getFamily(), cloned.getFamily());
        assertEquals(original.isBold(), cloned.isBold());
        assertEquals(original.isItalic(), cloned.isItalic());
        assertEquals(original.getSize(), cloned.getSize());
    }

    @Test
    public void hashCodeTest() {
        FontInfo fontInfo = new FontInfo();
        fontInfo.setFamily("Helvetica");
        fontInfo.setIsBold(true);
        fontInfo.setIsItalic(false);
        fontInfo.setSize(12);

        int hash = fontInfo.hashCode();
        assertEquals(hash, fontInfo.hashCode());
    }

    @Test
    public void equalsTest() {
        FontInfo fontInfo1 = new FontInfo();
        fontInfo1.setFamily("Georgia");
        fontInfo1.setIsBold(false);
        fontInfo1.setIsItalic(true);
        fontInfo1.setSize(16);

        FontInfo fontInfo2 = new FontInfo();
        fontInfo2.setFamily("Georgia");
        fontInfo2.setIsBold(false);
        fontInfo2.setIsItalic(true);
        fontInfo2.setSize(16);

        assertTrue(fontInfo1.equals(fontInfo2));

        fontInfo2.setIsBold(true);
        assertFalse(fontInfo1.equals(fontInfo2));
    }
}