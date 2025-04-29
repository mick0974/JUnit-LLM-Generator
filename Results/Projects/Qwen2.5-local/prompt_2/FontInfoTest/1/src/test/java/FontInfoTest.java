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