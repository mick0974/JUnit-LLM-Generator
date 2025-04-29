// FontInfo_isItalicTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public isItalic() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_isItalicTest {
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
	public void testIsItalic_false() {
		fontInfo.setIsItalic(false);
		assertFalse(fontInfo.isItalic());
	}

	@Test
	public void testIsItalic_true() {
		fontInfo.setIsItalic(true);
		assertTrue(fontInfo.isItalic());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetFont_null() {
		fontInfo.setFont(null);
	}


	@Test
	public void testDoesFontMatch_false() {
		fontInfo.setFamily("Times New Roman");
		fontInfo.setSize(16);
		fontInfo.setIsItalic(false);
		fontInfo.setIsBold(true);
		Font font = new Font("Arial", Font.BOLD, 16);
		assertFalse(fontInfo.doesFontMatch(font));
	}


	@Test
	public void testGenerateStyle_plain() {
		fontInfo.setIsBold(false);
		fontInfo.setIsItalic(false);
	 assertEquals(Font.PLAIN, fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_bold() {
		fontInfo.setIsBold(true);
		fontInfo.setIsItalic(false);
	 assertEquals(Font.BOLD, fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_italic() {
		fontInfo.setIsBold(false);
		fontInfo.setIsItalic(true);
	 assertEquals(Font.ITALIC, fontInfo.generateStyle());
	}

	@Test
	public void testGenerateStyle_boldItalic() {
		fontInfo.setIsBold(true);
		fontInfo.setIsItalic(true);
	 assertEquals(Font.BOLD | Font.ITALIC, fontInfo.generateStyle());
	}
}