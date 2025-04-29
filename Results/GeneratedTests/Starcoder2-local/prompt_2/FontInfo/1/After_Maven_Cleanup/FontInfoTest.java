

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Font;

import org.junit.Test;

public class FontInfoTest {



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