

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Font;

import org.junit.Test;

public class FontInfoTest {

	@Test
	public void testClone() {
		FontInfo fontInfo = new FontInfo();
		FontInfo clone = (FontInfo) fontInfo.clone();
		assertEquals(fontInfo, clone);
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
	public void testDoesFontMatch() {
		FontInfo fontInfo = new FontInfo();
		Font font = fontInfo.createFont();
		assertTrue(fontInfo.doesFontMatch(font));
		assertFalse(fontInfo.doesFontMatch(null));
	}

	@Test
	public void testEquals() {
		FontInfo fontInfo = new FontInfo();
		FontInfo clone = (FontInfo) fontInfo.clone();
		assertTrue(fontInfo.equals(clone));
		assertFalse(fontInfo.equals(null));
		assertFalse(fontInfo.equals(new Object()));
	}


	@Test
	public void testSetFont() {
		FontInfo fontInfo = new FontInfo();
		Font font = fontInfo.createFont();
		fontInfo.setFont(font);
		assertEquals(fontInfo.getFamily(), font.getFamily());
		assertEquals(fontInfo.getSize(), font.getSize());
		assertEquals(fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testSetFontNull() {
		FontInfo fontInfo = new FontInfo();
		try {
			fontInfo.setFont(null);
			assertTrue(false);
		} catch (IllegalArgumentException ex) {
			assertTrue(true);
		}
	}

	@Test
	public void testSetFamily() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setFamily("Arial");
		assertEquals("Arial", fontInfo.getFamily());
	}

	@Test
	public void testSetFamilyNull() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setFamily(null);
		assertEquals("Monospaced", fontInfo.getFamily());
	}

	@Test
	public void testSetIsBold() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setIsBold(true);
		assertEquals(true, fontInfo.isBold());
	}

	@Test
	public void testSetIsItalic() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setIsItalic(true);
		assertEquals(true, fontInfo.isItalic());
	}

	@Test
	public void testSetSize() {
		FontInfo fontInfo = new FontInfo();
		fontInfo.setSize(12);
		assertEquals(12, fontInfo.getSize());
	}

}