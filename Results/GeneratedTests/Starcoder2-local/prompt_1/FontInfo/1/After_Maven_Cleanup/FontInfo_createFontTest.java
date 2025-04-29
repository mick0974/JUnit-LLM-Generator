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
	public void testCreateFont_17() {
		_fontInfo.setFamily("Arial");
		_fontInfo.setSize(12);
		_fontInfo.setIsBold(false);
		_fontInfo.setIsItalic(false);
		Font font = _fontInfo.createFont();
		assertNotNull(font);
	
}
}