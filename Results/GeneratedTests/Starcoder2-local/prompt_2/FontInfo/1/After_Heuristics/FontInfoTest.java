

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Font;

import org.junit.Test;

public class FontInfoTest {

	@Test
	public void testFontInfo() {
		FontInfo fontInfo = new FontInfo();
		assertEquals(FontInfo.DEFAULT_FAMILY, fontInfo.getFamily());
		assertEquals(12, fontInfo.getSize());
		assertFalse(fontInfo.isBold());
		assertFalse(fontInfo.isItalic());
	}

	@Test
	public void testFontInfoFont() {
		Font font = new Font("Arial", Font.BOLD, 12);
		FontInfo fontInfo = new FontInfo(font);
		assertEquals(font.getFamily(), fontInfo.getFamily());
		assertEquals(font.getSize(), fontInfo.getSize());
		assertEquals(font.getStyle(), fontInfo.generateStyle());
		assertTrue(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testSetFont() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 12);
		fontInfo.setFont(font);
		assertEquals(font.getFamily(), fontInfo.getFamily());
		assertEquals(font.getSize(), fontInfo.getSize());
		assertEquals(font.getStyle(), fontInfo.generateStyle());
		assertTrue(fontInfo.doesFontMatch(font));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetFontNull() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setFont(null);
	}

	@Test
	public void testDoesFontMatch() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 12);
		assertTrue(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testDoesFontMatchNull() {
		FontInfo fontInfo = new FontInfo();
		assertFalse(fontInfo.doesFontMatch(null));
	}

	@Test
	public void testDoesFontMatchDifferentFamily() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Courier", Font.BOLD, 12);
		assertFalse(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testDoesFontMatchDifferentSize() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 10);
		assertFalse(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testDoesFontMatchDifferentStyle() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD | Font.ITALIC, 12);
		assertFalse(fontInfo.doesFontMatch(font));
	}

	@Test
	public void testCreateFont() {
		FontInfo fontInfo = new FontInfo();
		Font font = fontInfo.createFont();
		assertEquals(fontInfo.getFamily(), font.getFamily());
		assertEquals(fontInfo.getSize(), font.getSize());
		assertEquals(fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFontNull() {
		FontInfo fontInfo = new FontInfo();
		Font font = fontInfo.createFont();
		assertEquals(fontInfo.getFamily(), font.getFamily());
		assertEquals(fontInfo.getSize(), font.getSize());
		assertEquals(fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testToString() {
		FontInfo fontInfo = new FontInfo();
		assertEquals("Monospaced, 12", fontInfo.toString());
		fontInfo.setFamily("Arial");
		assertEquals("Arial, 12", fontInfo.toString());
		fontInfo.setIsBold(true);
		assertEquals("Arial, 12, bold", fontInfo.toString());
		fontInfo.setIsItalic(true);
		assertEquals("Arial, 12, bold, italic", fontInfo.toString());
	}

	@Test
	public void testHashCode() {
		FontInfo fontInfo = new FontInfo();
		Font font = new Font("Arial", Font.BOLD, 12);
		fontInfo.setFont(font);
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
		assertEquals(fontInfo.hashCode(), fontInfo.hashCode());
}
}