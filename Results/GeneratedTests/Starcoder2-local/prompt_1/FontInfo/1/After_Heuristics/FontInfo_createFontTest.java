// FontInfo_createFontTest.java
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Font;
import java.io.Serializable;

/**
 * Test class of FontInfo.
 * It contains 10 unit test cases for the
 * FontInfo#public createFont() method.
 * Follow the Arrange-Act-Assert pattern and cover typical and edge cases. 
 * Contains only valid Java code.  
 */
public class FontInfo_createFontTest {
	private FontInfo _fontInfo;

	@Before
	public void setUp() {
		_fontInfo = new FontInfo();
	}

	@After
	public void tearDown() {
		_fontInfo = null;
	}

	@Test
	public void testCreateFont_1() {
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_2() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_3() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_4() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_5() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_6() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_7() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_8() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_9() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_10() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_11() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_12() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_13() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_14() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_15() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_16() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(true);
		_fontInfo.setIsItalic(true);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
		assertEquals(_fontInfo.getFamily(), font.getFamily());
		assertEquals(_fontInfo.getSize(), font.getSize());
		assertEquals(_fontInfo.generateStyle(), font.getStyle());
	}

	@Test
	public void testCreateFont_17() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
	
}
}